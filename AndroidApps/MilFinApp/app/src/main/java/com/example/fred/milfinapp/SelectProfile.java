package com.example.fred.milfinapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;


public class SelectProfile  extends Activity implements AdapterView.OnItemClickListener
{
    public Profile activeProfile;
    protected static String LOG_TAG = "Saving";
    boolean isOpen = true, isDel = false, isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selprof);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        String[] files = fileList();
        String[] spinner_array = {"Open","Edit","Delete"};
        List<String> list = Arrays.asList(files);

        ListView fileManager = (ListView) findViewById(R.id.profile_manager);

        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        fileManager.setAdapter(listAdapter);
        fileManager.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        String profileClicked = ((TextView)view).getText().toString();
        Toast.makeText(getBaseContext(), profileClicked, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("profile", profileClicked);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                //editNote(info.id);
                return true;
            case R.id.delete:
                //deleteNote(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
    }

    public static void saveObjectToFile(Context context, String fileName, Profile obj) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();

        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, "saveObjectToFile FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(LOG_TAG, "saveObjectToFile IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, "saveObjectToFile Exception: " + e.getMessage());
        }
    }
    public static Profile getObjectFromFile(Context context, String filename) {

        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Profile object = (Profile) ois.readObject();
            ois.close();
            fis.close();

            return object;

        } catch (FileNotFoundException e) {
            Log.e(LOG_TAG, "getObjectFromFile FileNotFoundException: " + e.getMessage());
            saveObjectToFile(context, "CounterInfo.txt", new Profile());
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, "getObjectFromFile IOException: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            Log.e(LOG_TAG, "getObjectFromFile ClassNotFoundException: " + e.getMessage());
            return null;
        } catch (Exception e) {// Catch exception if any
            Log.e(LOG_TAG, "getBookmarksFromFile Exception: " + e.getMessage());
            return null;
        }
    }
}
