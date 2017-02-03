package com.betrayal.betrayalchar;

import android.content.SharedPreferences;
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
	
	static String[] names = new String[]{
			"Brandon","Darrin","Father","Heather","Jenny","Madame","Missy",
			"Ox","Peter","Professor","Vivian","Zoe"};
	private OnItemClickListener itemClickedListner = new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
	    	runActivity(p);
	    }
	    
	    public  void runActivity(int p){
	    	Intent i = new Intent(MainActivity.this, MainPlayer.class);
			i.putExtra("PLAYERNUMBER", p);
			startActivityForResult(i,0);
		}
	};

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(String s : names){
            SharedPreferences.Editor edit = getSharedPreferences(s,0).edit();
            edit.clear();
            edit.commit();
        }
    }

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

}
