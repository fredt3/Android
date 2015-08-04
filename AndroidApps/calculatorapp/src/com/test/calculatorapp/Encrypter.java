package com.test.calculatorapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Encrypter extends Activity {
	static final String TAG = "EncrypterRSA";
	protected static String LOG_TAG = "EncrypterSaving";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//THIS IS THE LAYOUT THAT WILL ACTUALLY SEE IF IT IS WORKING
		//ONLY USED FOR TESTING PURPOSES
		setContentView(R.layout.asym);
		
		
		
		//USED FOR TESTING PURPOSES
		//String theTestText = "This is just a simple test!";
		
		//THIS IS THE TEXT PASSED FROM THE "SecondActivity.class"
		Intent intent = getIntent();
		String theTestText = intent.getExtras().getString("text");
		
		//USED FOR TESTING PURPOSES
		TextView tvorig = (TextView)findViewById(R.id.tvorig);
		tvorig.setText("\n[ORIGINAL]:\n" + theTestText + "\n");
		
		// Generate key pair for 1024-bit RSA encryption and decryption
		Key publicKey = null;
		Key privateKey = null;
		try {
		    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		    kpg.initialize(1024);
		    KeyPair kp = kpg.genKeyPair();
		    publicKey = kp.getPublic();
		    privateKey = kp.getPrivate();
		} catch (Exception e) {
		    Log.e(TAG, "RSA key pair error");
		}
		
		// Encode the original data with RSA private key
		byte[] encodedBytes = null;
		try {
		    Cipher c = Cipher.getInstance("RSA");
		    c.init(Cipher.ENCRYPT_MODE, privateKey);
		    encodedBytes = c.doFinal(theTestText.getBytes());
		} catch (Exception e) {
		    Log.e(TAG, "RSA encryption error");
		}
		
		//USED FOR TESTING PURPOSES
		TextView tvencoded = (TextView)findViewById(R.id.tvencoded);
		tvencoded.setText("[ENCODED]:\n" + 
		    Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");
		
		/**
		 * VERSION 2.0
		 * 
		 * CREATING FILENAME WITH A COUNTER --- INCLUDES A COUNTERINFO.TXT FILE
		 */
		
		String count = getObjectFromFile(this,"CounterInfo.txt");
		
		String file = "TestTestTest";
		String textFile = file + count + ".txt";
		Integer counter = Integer.parseInt(count);
		counter++;
		saveObjectToFile(getBaseContext(), "CounterInfo.txt", counter.toString());
		
		
		
		
		
		
		//textFile
		saveObjectToFile(getBaseContext(), textFile, Base64.encodeToString(encodedBytes, Base64.DEFAULT));
		/**
		 * END OF VERSION 2.0
		 */
		
		
		// Decode the encoded data with RSA public key
		byte[] decodedBytes = null;
		try {
		    Cipher c = Cipher.getInstance("RSA");
		    c.init(Cipher.DECRYPT_MODE, publicKey);
		    decodedBytes = c.doFinal(encodedBytes);
		} catch (Exception e) {
		    Log.e(TAG, "RSA decryption error");
		}
	}
	public static void saveObjectToFile(Context context, String fileName, String obj) {
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
	public static String getObjectFromFile(Context context, String filename) {

	    try {      
	        FileInputStream fis = context.openFileInput(filename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        String object = (String) ois.readObject();
	        ois.close();
	  
	        return object;

	    } catch (FileNotFoundException e) {
	        Log.e(LOG_TAG, "getObjectFromFile FileNotFoundException: " + e.getMessage());
	        saveObjectToFile(context, "CounterInfo.txt", "1");
	        return "1";
	    } catch (IOException e) {
	        Log.e(LOG_TAG, "getObjectFromFile IOException: " + e.getMessage());
	        return "error";
	    } catch (ClassNotFoundException e) {
	        Log.e(LOG_TAG, "getObjectFromFile ClassNotFoundException: " + e.getMessage());
	        return "error";       
	    } catch (Exception e) {// Catch exception if any
	        Log.e(LOG_TAG, "getBookmarksFromFile Exception: " + e.getMessage());
	        return "error";
	    }
	}
	
	
	
	
}

