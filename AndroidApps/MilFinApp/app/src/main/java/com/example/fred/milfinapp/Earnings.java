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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Earnings  extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        String [] ranks = {"Choose Rank", "E-1", "E-2", "E-3", "E-4", "E-5","E-6","E-7","E-8",
                "E-9","W-2","W-3","W-4","W-5","O-1E","O-2E","O-3E","O-1","O-2","O-3","O-4","0-5","0-6","0-7","0-8","O-9", "O-10"};
        Spinner spinnerRank = (Spinner) findViewById(R.id.spinnerRank);
        List rankList =
                Arrays.asList(ranks);
        spinnerRank.setAdapter(
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, rankList));



        ArrayList years = new ArrayList();
        years.add("Choose # of Years");
        for(int i = 1; i < 38; i++)
        {
            years.add(i);
        }
        years.add("38+");

        Spinner spinnerYears = (Spinner) findViewById(R.id.spinnerYears);
        List yearList =
                Arrays.asList(years);
        spinnerYears.setAdapter(
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, years));
        //spinner.setOnItemSelectedListener(this);


        String [] yesNo = {"Yes/No","Yes","No"};

        Spinner spinnerDependents = (Spinner) findViewById(R.id.spinnerDep);
        List depList =
                Arrays.asList(yesNo);
        spinnerDependents.setAdapter(
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, depList));


        final Button buttonProceed = (Button) findViewById(R.id.btnProceed);

        buttonProceed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " =)", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), Deductions.class);
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
