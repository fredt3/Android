package com.example.fred.milfinapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
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
import android.widget.EditText;
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
    File[] folders;// = dirFolder.listFiles();
    protected static String LOG_TAG = "Saving";
    boolean isOpen = true, isDel = false, isEdit = false;
    String[] files;
    ArrayAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selprof);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //File[] f =
        files = fileList();
        String[] spinner_array = {"Open","Edit","Delete"};
        List<String> list = Arrays.asList(files);

        ListView fileManager = (ListView) findViewById(R.id.profile_manager);

        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        fileManager.setAdapter(listAdapter);
        fileManager.setOnItemClickListener(this);
        registerForContextMenu(fileManager);


        final Button buttonCreate = (Button) findViewById(R.id.buttonCreate);
        final EditText newProfName = (EditText)findViewById(R.id.newProfileName);

        buttonCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Profile newProfile = new Profile(newProfName.getText().toString().trim());
                SerializationUtil.saveObjectToFile(getBaseContext(), newProfile.getName(), newProfile);
                finish();
                startActivity(getIntent());
            }
        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        String profileClicked = ((TextView)view).getText().toString();
        Toast.makeText(getBaseContext(), profileClicked, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("profile", profileClicked);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.profile_manager) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(files[info.position]);
            String[] menuItems = getResources().getStringArray(R.array.menu_options);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu_options);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = files[info.position];

        if(menuItemName.equals("Edit"))
        {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("profile", listItemName);
            startActivity(intent);
            finish();
        }
        else if(menuItemName.equals("Delete"))
        {

            deleteFile(listItemName);

            finish();
            startActivity(getIntent());
        }
        else {
            Intent intent = new Intent(getBaseContext(), Portfolio.class);
            intent.putExtra("profile", listItemName);
            startActivity(intent);
            finish();
        }

        return true;
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



}
