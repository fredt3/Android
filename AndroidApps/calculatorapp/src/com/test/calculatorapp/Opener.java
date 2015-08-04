package com.test.calculatorapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Opener extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opener);
		
		Intent intent = getIntent();
		String fileName = intent.getExtras().getString("fileName");
		String fileValue = getObjectFromFile(getBaseContext(), fileName);
		
		TextView fileContent = (TextView)findViewById(R.id.fileContent);
		fileContent.setText(fileValue);
	
	
	}
	
	public static String getObjectFromFile(Context context, String filename) {

	    try {      
	        FileInputStream fis = context.openFileInput(filename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        String object = (String) ois.readObject();
	        ois.close();
	  
	        return object;

	    } catch (FileNotFoundException e) {
	        //Log.e(LOG_TAG, "getObjectFromFile FileNotFoundException: " + e.getMessage());
	        return "Error";
	    } catch (IOException e) {
	        //Log.e(LOG_TAG, "getObjectFromFile IOException: " + e.getMessage());
	        return "error";
	    } catch (ClassNotFoundException e) {
	        //Log.e(LOG_TAG, "getObjectFromFile ClassNotFoundException: " + e.getMessage());
	        return "error";       
	    } catch (Exception e) {// Catch exception if any
	        //Log.e(LOG_TAG, "getBookmarksFromFile Exception: " + e.getMessage());
	        return "error";
	    }
	}
}
	
