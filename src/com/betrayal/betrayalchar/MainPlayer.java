package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.Random;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainPlayer extends Activity {

	public Player player;
	public ScrollingCounterView sanityView;
	public ScrollingCounterView knowlageView;
	public ScrollingCounterView speedView;
	public ScrollingCounterView mightView;
	public LinearLayout mainView;
	public boolean rollVisible = false;
	public ArrayList<Items> inventory = new ArrayList<Items>();
	private Resources res;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player1);
		int number = getIntent().getIntExtra("PLAYERNUMBER", 0);
		String name = MainActivity.names[number];
		setTitle(name);
		player = new Player(number);
		res = getResources();
		mightView = (ScrollingCounterView) findViewById(R.id.single_spinner_might);
		speedView = (ScrollingCounterView) findViewById(R.id.single_spinner_speed);
		knowlageView = (ScrollingCounterView) findViewById(R.id.single_spinner_knowlage);
		sanityView = (ScrollingCounterView) findViewById(R.id.single_spinner_sanity);
		mightView.setStat("might");
		speedView.setStat("speed");
		sanityView.setStat("sanity");
		knowlageView.setStat("knowlage");
		
		mightView.setCurrentDigit(player.getMight());
		speedView.setCurrentDigit(player.getSpeed());
		knowlageView.setCurrentDigit(player.getKnowlage());
		sanityView.setCurrentDigit(player.getSanity());
	}



	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onResume(){
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player1, menu);
		return true;
	}

	public void resetMight(View v){
		player.mightTimes = player.mightTimesInit;
		mightView.setCurrentDigit(player.getMight());

	}

	public void resetSpeed(View v){
		player.speedTimes = player.speedTimesInit;
		speedView.setCurrentDigit(player.getSpeed());
	}

	public void resetSanity(View v){
		player.sanityTimes = player.sanityTimesInit;
		sanityView.setCurrentDigit(player.getSanity());
	}

	public void resetKnowlage(View v){
		player.knowlageTimes = player.knowlageTimesInit;
		knowlageView.setCurrentDigit(player.getKnowlage());
	}

	public void mightAttack(Items i){
		unsetDice();
		ArrayList<Integer> diceRoll = i.useItem(this);
		setDice(diceRoll);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void mightAttackBtn(View v){
		if(inventory.isEmpty()){
			mightDefence(findViewById(R.id.main_layout));
		}
		else{
			setPopup(R.menu.popup, v, new AttackListner(this), true);
		}
	}

	public void mightDefence(View v){
		unsetDice();
		ArrayList<Integer> diceRoll = player.doMightRoll();
		setDice(diceRoll);
	}

	public void speedRoll(View v){
		unsetDice();
		ArrayList<Integer> diceRoll = player.doSpeedRoll();
		setDice(diceRoll);
	}

	public void knowlageRoll(View v){
		unsetDice();
		ArrayList<Integer> diceRoll = player.doKnowlageRoll();
		setDice(diceRoll);

	}

	public void sanityRoll(View v){
		unsetDice();
		ArrayList<Integer> diceRoll = player.doSanityRoll();
		setDice(diceRoll);

	}

	public void hauntRoll(View v){

		player.doHauntRoll();
		rollVisible = true;
	}

	public void pickUp(View v){
		setPopup(R.menu.popup2, v, new PickupListner(this), false);

	}
	public void drop(View v){
		setPopup(R.menu.popup, v, new DropListner(this), true);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setPopup(int menuRes, View v, OnMenuItemClickListener listner, boolean isAttack){
		PopupMenu popup = new PopupMenu(this, v);
		if(isAttack){
			for( int i = 0; i < inventory.size(); i++){
				if(!inventory.get(i).useable){
					popup.getMenu().add(Integer.toString(i) + " " + inventory.get(i).itemName);
				}
			}
			popup.setOnMenuItemClickListener(listner);
			popup.show();
		}
		else{
			popup.getMenuInflater().inflate(menuRes, popup.getMenu());
			popup.setOnMenuItemClickListener(listner);
			popup.show();
		}
	}

	public void newRoll(View v){
		ImageButton ib = (ImageButton) v;
		Random r = new Random();
		int i = r.nextInt(3);
		if(i == 0){
			ib.setImageDrawable(res.getDrawable(R.drawable.dice0));
		}
		else if(i == 1){
			ib.setImageDrawable(res.getDrawable(R.drawable.dice1));
		}
		else{
			ib.setImageDrawable(res.getDrawable(R.drawable.dice2));
		}

	}
	
	public void setDice(ArrayList<Integer> diceRoll){
		ImageButton dice = new ImageButton(this);
		for(int i = 0; i < diceRoll.size(); i++){
			if(diceRoll.get(i).intValue() == 0){
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice0));
				dice.setVisibility(0);
			}
			else if(diceRoll.get(i).intValue() == 1){
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice1));
				dice.setVisibility(0);
			}
			else{
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice2));
				dice.setVisibility(0);
			}
		}
	}

	public void unsetDice(){
		for(int i = 0; i < 8; i++){
			((ImageButton)findDice(i)).setVisibility(4);
		}
	}

	public View findDice(int i){
		if(i == 0){
			return findViewById(R.id.dice1);
		}
		else if(i == 1){
			return findViewById(R.id.dice2);
		}
		else if(i == 2){
			return findViewById(R.id.dice3);
		}
		else if(i == 3){
			return findViewById(R.id.dice4);
		}
		else if(i == 4){
			return findViewById(R.id.dice5);
		}
		else if(i == 5){
			return findViewById(R.id.dice6);
		}
		else if(i == 6){
			return findViewById(R.id.dice7);
		}
		else {
			return findViewById(R.id.dice8);
		}
	}


}
