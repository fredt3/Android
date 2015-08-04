/**
 * THIS CLASS WILL BE THE ONE USED TO PERSIST DATA TO A FILE.
 * IT WILL BE HERE THAT THE DATA WILL BE SAVED AND ADDED TO A SECURE(HIDDEN) FILE
 * LOCATION. 
 * 
 * THIS WILL ENCRYPT THE DATA BEFORE IT IS SAVED INTO THE FILE.
 * 
 * WE WILL NEED TO HIDE IT IN THE KERNEL LEVEL.
 */
package com.test.calculatorapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SecondActivity extends Activity implements OnClickListener{
	EditText textSavedToFile;
	
	@SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
        textSavedToFile = (EditText)findViewById(R.id.editText1);
        findViewById(R.id.magicButton).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		//This is where if the save button is clicked it will save all of that text to file.
		//NEEDS EXTRA WORK FOR THIS TO BE COMPLETED
		String tempString;
		tempString = textSavedToFile.getText().toString();
		
/**
 * VERSION 3.0
 * 
 * ENCRYPTING THE DATA AND THEN STORING IT TO A FILE
 */
		//THIS IS WHAT IS USED TO SEND THE TEXT TO THE "Encrypter.class" IT IS HERE
		//WHERE THAT TEXT WILL BE ENCRYPTED AND SAVED TO THE FILE.
		Intent intent = new Intent(this, Encrypter.class);
		// adding extended data to intent using key/value pair
		intent.putExtra("text", tempString);
		startActivity(intent);
/**
 * END OF VERSION 3.0
 */
		//SEND tempString TO Encrypter SO THAT IT CAN BE ENCRYPTED AND SAVED THERE.
		//saveObjectToFile(getBaseContext(), "temp1.txt", tempString);
		textSavedToFile.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.file_manager:
	        	Intent intent = new Intent(this, FileManager.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
