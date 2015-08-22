package com.example.fred.milfinapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainActivity  extends Activity
{
    public Profile activeProfile;
    protected static String LOG_TAG = "Saving";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Create Profile

        //activeProfile = new Profile("Fred");
        //activeProfile.setRank("Admiral");

        //saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
        activeProfile = getObjectFromFile(getBaseContext(), getIntent().getStringExtra("profile"));
        Toast.makeText(getApplicationContext(), activeProfile.getName(), Toast.LENGTH_LONG).show();

        final Button buttonEarn = (Button) findViewById(R.id.buttonEarnings);
        final Button buttonDed = (Button) findViewById(R.id.buttonDeductions);
        final Button buttonSav = (Button) findViewById(R.id.buttonSavings);
        final Button buttonTip = (Button) findViewById(R.id.buttonTips);
        final Button buttonSet = (Button) findViewById(R.id.buttonPortfolio);

        buttonEarn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Toast.makeText(getApplicationContext(), " =)", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), Earnings.class);
                startActivity(i);
            }
        });
        buttonDed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Deductions.class);
                startActivity(i);
            }
        });
        buttonSav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Savings.class);
                startActivity(i);
            }
        });
        buttonTip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Tips.class);
                startActivity(i);
            }
        });
        buttonSet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Portfolio.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
