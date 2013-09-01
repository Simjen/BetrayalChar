package com.betrayal.betrayalchar;

import java.util.ArrayList;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;


public class Player0 extends Activity {
	public Player player;
	public TextView sanityView;
	private TextView knowlageView;
	private TextView speedView;
	private TextView mightView;
	public TextView rollText;
	public TextView rollTextPrefix;
	public LinearLayout mainView;
	public boolean rollVisible = false;
	public ArrayList<Items> inventory = new ArrayList<Items>();
	private Resources res;

	public Player0(int number){
		player = new Player(number);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player1);
		res = getResources();
		mightView = (TextView) findViewById(R.id.might_1);
		speedView = (TextView) findViewById(R.id.speed_1);
		knowlageView = (TextView) findViewById(R.id.knowlage_1);
		sanityView = (TextView) findViewById(R.id.sanity_1);
		mightView.setText(player.getMightTxt());
		speedView.setText(player.getSpeedTxt());
		knowlageView.setText(player.getKnowlageTxt());
		sanityView.setText(player.getSanityTxt());
		//ImageView ll = (ImageView) findViewById(R.id.player_img);
		//ll.setImageDrawable(res.getDrawable(playerPic));
		rollText = (TextView) findViewById(R.id.roll_layout_number);
		rollTextPrefix = (TextView) findViewById(R.id.roll_layout);
		if(rollVisible){
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
		}
	}



	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onResume(){
		super.onResume();
		if(rollVisible){

			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
		}
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

		public void resetMight(View v){
			player.mightTimes = player.mightTimesInit;
			mightView.setText(Integer.toString(player.might.stat[player.mightTimesInit]));

		}

		public void resetSpeed(View v){
			player.speedTimes = player.speedTimesInit;
			speedView.setText(Integer.toString(player.speed.stat[player.speedTimesInit]));

		}

		public void resetSanity(View v){
			player.sanityTimes = player.sanityTimesInit;
			sanityView.setText(Integer.toString(player.sanity.stat[player.sanityTimesInit]));

		}

		public void resetKnowlage(View v){
			player.knowlageTimes = player.knowlageTimesInit;
			knowlageView.setText(Integer.toString(player.knowlage.stat[player.knowlageTimesInit]));
		}

		public void mightAttack(Items i){
			unsetDice();
			ArrayList<Integer> diceRoll = i.useItem(this);
			int roll = 0;
			for(Integer in : diceRoll){
				roll += in.intValue();
			}
			setDice(diceRoll);
			rollText.setText(Integer.toString(roll));
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
			rollVisible = true;
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
			int roll = 0;
			for(Integer i: diceRoll){
				roll += i.intValue();
			}
			setDice(diceRoll);
			rollText.setText(Integer.toString(roll));
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
			rollVisible = true;
		}

		public void speedRoll(View v){
			unsetDice();
			ArrayList<Integer> diceRoll = player.doSpeedRoll();
			int roll = 0;
			for(Integer i: diceRoll){
				roll += i.intValue();
			}
			setDice(diceRoll);
			rollText.setText(Integer.toString(roll));
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
			rollVisible = true;
		}

		public void knowlageRoll(View v){
			unsetDice();
			ArrayList<Integer> diceRoll = player.doKnowlageRoll();
			int roll = 0;
			for(Integer i: diceRoll){
				roll += i.intValue();
			}
			setDice(diceRoll);
			rollText.setText(Integer.toString(roll));
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
			rollVisible = true;

		}

		public void sanityRoll(View v){
			unsetDice();
			ArrayList<Integer> diceRoll = player.doSanityRoll();
			int roll = 0;
			for(Integer i: diceRoll){
				roll += i.intValue();
			}
			setDice(diceRoll);
			rollText.setText(Integer.toString(roll));
			rollText.setVisibility(0);
			rollTextPrefix.setVisibility(0);
			rollVisible = true;

		}

		public void hauntRoll(View v){

			player.doHauntRoll();
			rollVisible = true;
		}

		public void hideRoll(View v){
			rollText.setVisibility(4);
			rollTextPrefix.setVisibility(4);
			rollVisible = false;
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
				((ImageButton)findDice(i)).setVisibility(8);
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


