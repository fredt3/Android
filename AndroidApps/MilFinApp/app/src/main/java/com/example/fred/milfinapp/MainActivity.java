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
        activeProfile = SerializationUtil.getObjectFromFile(getBaseContext(), getIntent().getStringExtra("profile"));
        Toast.makeText(getApplicationContext(), activeProfile.getName(), Toast.LENGTH_LONG).show();

        final Button buttonEarn = (Button) findViewById(R.id.buttonEarnings);
        final Button buttonDed = (Button) findViewById(R.id.buttonDeductions);
        final Button buttonSav = (Button) findViewById(R.id.buttonSavings);
        final Button buttonTip = (Button) findViewById(R.id.buttonTips);
        final Button buttonSet = (Button) findViewById(R.id.buttonPortfolio);

        buttonEarn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SerializationUtil.saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Toast.makeText(getApplicationContext(), " =)", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), Earnings.class);
                i.putExtra("profile", activeProfile.getName());
                startActivity(i);
                finish();
            }
        });
        buttonDed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SerializationUtil.saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Deductions.class);
                i.putExtra("profile", activeProfile.getName());
                startActivity(i);
                finish();
            }
        });
        buttonSav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SerializationUtil.saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Savings.class);
                i.putExtra("profile", activeProfile.getName());
                startActivity(i);
                finish();
            }
        });
        buttonTip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SerializationUtil.saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Tips.class);
                i.putExtra("profile", activeProfile.getName());
                startActivity(i);
                finish();
            }
        });
        buttonSet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SerializationUtil.saveObjectToFile(getBaseContext(), activeProfile.getName(), activeProfile);
                Intent i = new Intent(getBaseContext(), Portfolio.class);
                i.putExtra("profile", activeProfile.getName());
                startActivity(i);
                finish();
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
}
