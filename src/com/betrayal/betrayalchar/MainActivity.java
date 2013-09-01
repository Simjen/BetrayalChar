package com.betrayal.betrayalchar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity{
	private ListView chooser;
	
	String[] names = new String[]{
			"Brandon","Darrin","Father","Heather","Jenny","Madame","Missy",
			"Ox","Peter","Professor","Vivian","Zoe"};
	private OnItemClickListener itemClickedListner = new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
	    	runActivity(p);
	    }
	    
	    public  void runActivity(int p){
	    	Intent i;
			if(p == 0){
				i = new Intent(MainActivity.this, Player1.class);
			}
			else if(p == 1){
				i = new Intent(MainActivity.this, Player2.class);
			}
			else if(p == 2){
				i = new Intent(MainActivity.this, Player3.class);
			}
			else if(p == 3){
				i = new Intent(MainActivity.this, Player4.class);
			}
			else if(p == 4){
				i = new Intent(MainActivity.this, Player5.class);
			}
			else if(p == 5){
				i = new Intent(MainActivity.this, Player6.class);
			}
			else if(p == 6){
				i = new Intent(MainActivity.this, Player7.class);
			}
			else if(p == 7){
				i = new Intent(MainActivity.this, Player8.class);
			}
			else if(p == 8){
				i = new Intent(MainActivity.this, Player9.class);
			}
			else if(p == 9){
				i = new Intent(MainActivity.this, Player10.class);
			}
			else if(p == 10){
				i = new Intent(MainActivity.this, Player11.class);
			}
			else{
				i = new Intent(MainActivity.this, Player12.class);
			}
			
			startActivity(i);
		}
	};
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstproject.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		chooser = (ListView) findViewById(R.id.chooser);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_layout, names);
		chooser.setAdapter(adapter);
		chooser.setOnItemClickListener(itemClickedListner);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View v){
		Intent i = new Intent(this,  Player1.class);
		startActivity(i);
		
	}

}
