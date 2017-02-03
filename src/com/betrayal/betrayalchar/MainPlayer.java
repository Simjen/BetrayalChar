package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.Random;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainPlayer extends Activity {

	public Player player;
	private String name;
	public StatView sanityView;
	public StatView knowledgeView;
	public StatView speedView;
	public StatView mightView;
	public boolean rollVisible = false;
	public ArrayList<Items> inventory = new ArrayList<>();
	private Resources res;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_player1);
		int number = getIntent().getIntExtra("PLAYERNUMBER", 0);
		name = MainActivity.names[number];
		setTitle(name);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		player = new Player(number+1);
		// Setup Shared Preferences for each name
		SharedPreferences prefs = getSharedPreferences(name,0);
		player.mightStat.setStatIndex(prefs.getInt("mightinit", player.mightStat.getStatIndex()));
		player.speedStat.setStatIndex(prefs.getInt("speedinit", player.speedStat.getStatIndex()));
	 	player.knowledgeStat.setStatIndex(prefs.getInt("knowledgeinit", player.knowledgeStat.getStatIndex()));
		player.sanityStat.setStatIndex(prefs.getInt("sanityinit", player.sanityStat.getStatIndex()));

		res = getResources();
		mightView = (StatView) findViewById(R.id.single_spinner_might);
		speedView = (StatView) findViewById(R.id.single_spinner_speed);
		knowledgeView = (StatView) findViewById(R.id.single_spinner_knowlage);
		sanityView = (StatView) findViewById(R.id.single_spinner_sanity);
		mightView.setmCurrentStat(player.mightStat);
		speedView.setmCurrentStat(player.speedStat);
		sanityView.setmCurrentStat(player.sanityStat);
		knowledgeView.setmCurrentStat(player.knowledgeStat);
		mightView.setCurrentDigit(player.mightStat);
		speedView.setCurrentDigit(player.speedStat);
		knowledgeView.setCurrentDigit(player.knowledgeStat);
		sanityView.setCurrentDigit(player.sanityStat);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		SharedPreferences prefs = getSharedPreferences(name,0);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt("mightinit", player.mightStat.getStatIndex());
		edit.putInt("speedinit", player.speedStat.getStatIndex());
		edit.putInt("knowledgeinit", player.knowledgeStat.getStatIndex());
		edit.putInt("sanityinit", player.sanityStat.getStatIndex());
		edit.commit();
	}


	public void resetMight(View v){
		player.mightStat.resetStat();
		mightView.setCurrentDigit(player.mightStat);

	}

	public void resetSpeed(View v){
		player.speedStat.resetStat();
		speedView.setCurrentDigit(player.speedStat);
	}

	public void resetSanity(View v){
		player.sanityStat.resetStat();
		sanityView.setCurrentDigit(player.sanityStat);
	}

	public void resetKnowlage(View v){
		player.knowledgeStat.resetStat();
		knowledgeView.setCurrentDigit(player.knowledgeStat);
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
			popup.getMenu().add("0 Attack");
			for( int i = 0; i < inventory.size(); i++){
				if(!inventory.get(i).useable){
					popup.getMenu().add(Integer.toString(i+1) + " " + inventory.get(i).itemName);
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
		ImageButton dice;
		for(int i = 0; i < diceRoll.size(); i++){
			if(diceRoll.get(i) == 0){
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice0));
				dice.setVisibility(View.VISIBLE);
			}
			else if(diceRoll.get(i) == 1){
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice1));
				dice.setVisibility(View.VISIBLE);
			}
			else{
				dice = (ImageButton) findDice(i);
				dice.setImageDrawable(res.getDrawable(R.drawable.dice2));
				dice.setVisibility(View.VISIBLE);
			}
		}
	}

	public void unsetDice(){
		for(int i = 0; i < 8; i++){
			ImageButton dice = (ImageButton) findDice(i);
			dice.setVisibility(View.INVISIBLE);
			dice.invalidate();
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
 /*-------------------------------
 --------------Listners-----------
 ---------------------------------
   */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public class AttackListner implements OnMenuItemClickListener {

		MainPlayer p;
		public AttackListner(MainPlayer mainPlayer){
			this.p = mainPlayer;
		}
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			int i = Integer.parseInt(((String) item.getTitle()).substring(0, 1));
			if(i == 0){
				p.mightDefence(p.findViewById(R.id.main_layout));
			}
			else {
				p.mightAttack(p.inventory.get(i - 1));
			}
			return true;
		}

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public class DropListner implements OnMenuItemClickListener {

		MainPlayer p;
		public DropListner(MainPlayer mainPlayer){
			this.p = mainPlayer;
		}

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			int i = Integer.parseInt(((String) item.getTitle()).substring(0, 1));
			p.inventory.remove(i-1);

			return true;
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public class PickupListner implements OnMenuItemClickListener {

		MainPlayer p;
		public PickupListner(MainPlayer mainPlayer){
			this.p = mainPlayer;
		}
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			int i = Integer.parseInt(((String) item.getTitle()).substring(0, 1));
			p.inventory.add(Items.getItem(i-1));
			return true;
		}
	}
}
