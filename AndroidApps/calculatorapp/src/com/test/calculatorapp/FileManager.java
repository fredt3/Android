package com.test.calculatorapp;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ToggleButton;

public class FileManager extends Activity implements OnItemSelectedListener {

	boolean isOpen = false, isDel = false, isEdit = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_filemanager);
		
		final ToggleButton toggleButtonOpen = (ToggleButton) findViewById(R.id.buttonOpen);
		final ToggleButton toggleButtonEdit = (ToggleButton) findViewById(R.id.buttonEdit);
		final ToggleButton toggleButtonDel = (ToggleButton) findViewById(R.id.buttonDel);
		
		String[] files = fileList();
		String[] spinner_array = {"Open","Edit","Delete"};
		List<String> list = Arrays.asList(files); 
		
		ListView fileManager = (ListView) findViewById(R.id.file_manager);
		
		ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		fileManager.setAdapter(listAdapter);
		
		toggleButtonOpen.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
				if(isChecked){
					isOpen = true;
				} else{
					isOpen = false;
				}
				
			}
		});
		toggleButtonEdit.setOnCheckedChangeListener(new OnCheckedChangeListener(){
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
						if(isChecked){
							isEdit = true;
						} else{
							isEdit = false;
						}
						
					}
				});
		toggleButtonDel.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
				if(isChecked){
					isDel = true;
				} else{
					isDel = false;
				}
				
			}
		});
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		
		if(isOpen){
		String fileName = ((ListView)view).getContext().toString();
		Intent i = new Intent(getBaseContext(), Opener.class);
		i.putExtra("fileName", fileName);
		startActivity(i);
		
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}





