package com.example.fred.milfinapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Deductions  extends Activity
{
    Profile activeProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deductions);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        activeProfile = SerializationUtil.getObjectFromFile(getBaseContext(), getIntent().getStringExtra("profile"));


        final TextView tax = (TextView) findViewById(R.id.tax);
        tax.setText(String.valueOf(activeProfile.getTax()));

        final Button buttonProceed = (Button) findViewById(R.id.btnProceed);
        buttonProceed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " =)", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), Savings.class);
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

    public String [] initBAHlocations()
    {
        String [] loc = {"empty"};


        return loc;
    }
}
