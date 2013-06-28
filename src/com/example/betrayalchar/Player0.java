package com.example.betrayalchar;

import java.util.Timer;

import com.example.myfirstproject.*;

import android.os.Bundle;
import android.widget.LinearLayout.*;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


public class Player0 extends Activity {
	public Player player;
	private TextView sanityView;
	private TextView knowlageView;
	private TextView speedView;
	private TextView mightView;
	private TextView rollText;
	private LinearLayout layout;
	private int playerPic;
	private PopupWindow rollResult;
	private LayoutParams parms;
	private Timer t = new Timer();

	public Player0(int number, int i){
		player = new Player(number);
		playerPic = i;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player1);
		Resources res = getResources();
		mightView = (TextView) findViewById(R.id.might_1);
		speedView = (TextView) findViewById(R.id.speed_1);
		knowlageView = (TextView) findViewById(R.id.knowlage_1);
		sanityView = (TextView) findViewById(R.id.sanity_1);
		mightView.setText(player.getMightTxt());
		speedView.setText(player.getSpeedTxt());
		knowlageView.setText(player.getKnowlageTxt());
		sanityView.setText(player.getSanityTxt());
		layout = new LinearLayout(this);
		parms = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rollText = new TextView(this);
		rollText.setGravity(Gravity.CENTER);
		layout.addView(rollText, parms);
		rollResult = new PopupWindow(this);
		rollResult.setContentView(layout);
		ImageView ll = (ImageView) findViewById(R.id.player_img);
		ll.setImageDrawable(res.getDrawable(playerPic));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player1, menu);
		return true;
	}

	public void upMight(View v){
		mightView.setText(player.upMight());
	}
	public void upSpeed(View v){
		speedView.setText(player.upSpeed());
	}
	public void upKnowlage(View v){
		knowlageView.setText(player.upKnowlage());
	}
	public void upSanity(View v){
		sanityView.setText(player.upSanity());
	}

	public void downMight(View v){
		mightView.setText(player.downMight());
	}
	public void downSpeed(View v){
		speedView.setText(player.downSpeed());
	}
	public void downKnowlage(View v){
		knowlageView.setText(player.downKnowlage());
	}
	public void downSanity(View v){
		sanityView.setText(player.downSanity());
	}

	public void upOmen(View v){
		player.upOmen();
	}

	public void reset(View v){
		player.mightTimes = player.mightTimesInit;
		player.speedTimes = player.speedTimesInit;
		player.knowlageTimes = player.knowlageTimesInit;
		player.sanityTimes = player.sanityTimesInit;
	}

	public void mightRoll(View v){
		rollText.setText(Integer.toString(player.doMightRoll()));
		rollResult.showAtLocation(findViewById(R.id.layout_player), Gravity.CENTER, 0, 0);
	}

	public void speedRoll(View v){

		player.doSpeedRoll();
	}

	public void knowlageRoll(View v){

		player.doKnowlageRoll();
	}

	public void sanityRoll(View v){

		player.doSanityRoll();
	}

	public void hauntRoll(View v){

		player.doHauntRoll();
	}


}
