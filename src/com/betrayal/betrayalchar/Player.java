package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	public Stats might;
	public Stats speed;
	public Stats knowlage;
	public Stats sanity;
	public int mightTimesInit;
	public int speedTimesInit; 
	public int knowlageTimesInit;
	public int sanityTimesInit;
	public int mightTimes;
	public int speedTimes; 
	public int knowlageTimes;
	public int sanityTimes;
	public int omens = 0;
	private Random rand = new Random();
	private ArrayList<Items> useableItems = new ArrayList<Items>();
	
	public Player(int player){
		if(player == 1){
			mightTimesInit = mightTimes = Stats.PLAYER1MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER1SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER1KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER1SANITY.start;
			might = Stats.PLAYER1MIGHT;
			speed = Stats.PLAYER1SPEED;
			knowlage = Stats.PLAYER1KNOWLAGE;
			sanity = Stats.PLAYER1SANITY;
		}
		else  if(player == 2){
			mightTimes = Stats.PLAYER2MIGHT.start;
			speedTimes = Stats.PLAYER2SPEED.start;
			knowlageTimes = Stats.PLAYER2KNOWLAGE.start;
			sanityTimes = Stats.PLAYER2SANITY.start;
			might = Stats.PLAYER2MIGHT;
			speed = Stats.PLAYER2SPEED;
			knowlage = Stats.PLAYER2KNOWLAGE;
			sanity = Stats.PLAYER2SANITY;
		}
		else if(player == 3){
			mightTimes = Stats.PLAYER3MIGHT.start;
			speedTimes = Stats.PLAYER3SPEED.start;
			knowlageTimes = Stats.PLAYER3KNOWLAGE.start;
			sanityTimes = Stats.PLAYER3SANITY.start;
			might = Stats.PLAYER3MIGHT;
			speed = Stats.PLAYER3SPEED;
			knowlage = Stats.PLAYER2KNOWLAGE;
			sanity = Stats.PLAYER2SANITY;
		}
		else if(player == 4){
			mightTimes = Stats.PLAYER4MIGHT.start;
			speedTimes = Stats.PLAYER4SPEED.start;
			knowlageTimes = Stats.PLAYER4KNOWLAGE.start;
			sanityTimes = Stats.PLAYER4SANITY.start;
			might = Stats.PLAYER4MIGHT;
			speed = Stats.PLAYER4SPEED;
			knowlage = Stats.PLAYER4KNOWLAGE;
			sanity = Stats.PLAYER4SANITY;
		}
		else if(player == 5){
			mightTimes = Stats.PLAYER5MIGHT.start;
			speedTimes = Stats.PLAYER5SPEED.start;
			knowlageTimes = Stats.PLAYER5KNOWLAGE.start;
			sanityTimes = Stats.PLAYER5SANITY.start;
			might = Stats.PLAYER5MIGHT;
			speed = Stats.PLAYER5SPEED;
			knowlage = Stats.PLAYER5KNOWLAGE;
			sanity = Stats.PLAYER5SANITY;
		}
		else if(player == 6){
			mightTimes = Stats.PLAYER6MIGHT.start;
			speedTimes = Stats.PLAYER6SPEED.start;
			knowlageTimes = Stats.PLAYER6KNOWLAGE.start;
			sanityTimes = Stats.PLAYER6SANITY.start;
			might = Stats.PLAYER6MIGHT;
			speed = Stats.PLAYER6SPEED;
			knowlage = Stats.PLAYER6KNOWLAGE;
			sanity = Stats.PLAYER6SANITY;
		}
		else if(player == 8){
			mightTimes = Stats.PLAYER8MIGHT.start;
			speedTimes = Stats.PLAYER8SPEED.start;
			knowlageTimes = Stats.PLAYER8KNOWLAGE.start;
			sanityTimes = Stats.PLAYER8SANITY.start;
			might = Stats.PLAYER8MIGHT;
			speed = Stats.PLAYER8SPEED;
			knowlage = Stats.PLAYER8KNOWLAGE;
			sanity = Stats.PLAYER8SANITY;
		}
		else if(player == 9){
			mightTimes = Stats.PLAYER9MIGHT.start;
			speedTimes = Stats.PLAYER9SPEED.start;
			knowlageTimes = Stats.PLAYER9KNOWLAGE.start;
			sanityTimes = Stats.PLAYER9SANITY.start;
			might = Stats.PLAYER9MIGHT;
			speed = Stats.PLAYER9SPEED;
			knowlage = Stats.PLAYER9KNOWLAGE;
			sanity = Stats.PLAYER9SANITY;
		}
		else if(player == 10){
			mightTimes = Stats.PLAYER10MIGHT.start;
			speedTimes = Stats.PLAYER10SPEED.start;
			knowlageTimes = Stats.PLAYER10KNOWLAGE.start;
			sanityTimes = Stats.PLAYER10SANITY.start;
			might = Stats.PLAYER10MIGHT;
			speed = Stats.PLAYER10SPEED;
			knowlage = Stats.PLAYER10KNOWLAGE;
			sanity = Stats.PLAYER10SANITY;
		}
		else if(player == 11){
			mightTimes = Stats.PLAYER11MIGHT.start;
			speedTimes = Stats.PLAYER11SPEED.start;
			knowlageTimes = Stats.PLAYER11KNOWLAGE.start;
			sanityTimes = Stats.PLAYER11SANITY.start;
			might = Stats.PLAYER11MIGHT;
			speed = Stats.PLAYER11SPEED;
			knowlage = Stats.PLAYER11KNOWLAGE;
			sanity = Stats.PLAYER11SANITY;
		}
		else if(player == 12){
			mightTimes = Stats.PLAYER12MIGHT.start;
			speedTimes = Stats.PLAYER12SPEED.start;
			knowlageTimes = Stats.PLAYER12KNOWLAGE.start;
			sanityTimes = Stats.PLAYER12SANITY.start;
			might = Stats.PLAYER12MIGHT;
			speed = Stats.PLAYER12SPEED;
			knowlage = Stats.PLAYER12KNOWLAGE;
			sanity = Stats.PLAYER12SANITY;
		}
		else{
			mightTimes = Stats.PLAYER7MIGHT.start;
			speedTimes = Stats.PLAYER7SPEED.start;
			knowlageTimes = Stats.PLAYER7KNOWLAGE.start;
			sanityTimes = Stats.PLAYER7SANITY.start;
			might = Stats.PLAYER7MIGHT;
			speed = Stats.PLAYER7SPEED;
			knowlage = Stats.PLAYER7KNOWLAGE;
			sanity = Stats.PLAYER7SANITY;
		}
	}
	
	public ArrayList<Integer> doRoll(int r){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(r >= 8){
			for(int i=0; i<8; i++){
				res.add(rand.nextInt(3));
			}
		}
		else{
			for(int i=0; i<r; i++){
				res.add(rand.nextInt(3));
			}
		}
		return res;	
	}
	
	public ArrayList<Integer> doMightRoll(){
		if(mightTimes >= 8){
			
		return doRoll(might.stat[8]);
		}
		else{
			return doRoll(might.stat[mightTimes]);
		}
	}
	public ArrayList<Integer> doSpeedRoll(){
		
		if (speedTimes >=8) {
			return doRoll(speed.stat[8]);
		}
		else{
			
			return doRoll(speed.stat[speedTimes]);
		}
	}
	public ArrayList<Integer> doKnowlageRoll(){
		if (knowlageTimes >=8) {
			return doRoll(knowlage.stat[8]);
		}
		else{
			return doRoll(knowlage.stat[knowlageTimes]);
		}
	}
	public ArrayList<Integer> doSanityRoll(){
		if (sanityTimes >= 8) {
			return doRoll(sanity.stat[8]);
		}
		else{
			return doRoll(sanity.stat[sanityTimes]);
		}
	}

	public ArrayList<Integer> doHauntRoll(){
		return new ArrayList<Integer>();
	}
	
	public String upMight(){
		if(mightTimes >= 8){
			mightTimes++;
			return Integer.toString(might.stat[8]);
		}
		else{
			mightTimes++;
			return Integer.toString(might.stat[mightTimes]);
		}
	}
	
	public String downMight(){
		if(mightTimes == 0){
			return Integer.toString(might.stat[mightTimes]);
		}
		else{
			if(mightTimes > 8){
				mightTimes--;
				return Integer.toString(might.stat[8]);
			}
			else{
				mightTimes--;

				return Integer.toString(might.stat[mightTimes]);
			}
		}
	}
	
	public String upSpeed(){
		if(speedTimes >= 8){
			speedTimes++;
			return Integer.toString(speed.stat[8]);
		}
		else{
			speedTimes++;
			return Integer.toString(speed.stat[speedTimes]);
		}
	}
	
	public String downSpeed(){
		if(speedTimes == 0){
			return Integer.toString(speed.stat[speedTimes]);
		}
		else{
			if(speedTimes > 8){
				speedTimes--;
				return Integer.toString(speed.stat[8]);
			}
			else{
				speedTimes--;

				return Integer.toString(speed.stat[speedTimes]);
			}
		}
	}
	
	public String upKnowlage(){
		if(knowlageTimes >= 8){
			knowlageTimes++;
			return Integer.toString(knowlage.stat[8]);
		}
		else{
			knowlageTimes++;
			return Integer.toString(knowlage.stat[knowlageTimes]);
		}
	}
	
	public String downKnowlage(){
		if(knowlageTimes == 0){
			return Integer.toString(knowlage.stat[knowlageTimes]);
		}
		else{
			if(knowlageTimes > 8){
				knowlageTimes--;
				return Integer.toString(knowlage.stat[8]);
			}
			else{
				knowlageTimes--;

				return Integer.toString(knowlage.stat[knowlageTimes]);
			}
		}
	}
	
	public String upSanity(){
		if(sanityTimes >= 8){
			sanityTimes++;
			return Integer.toString(sanity.stat[8]);
		}
		else{
			sanityTimes++;
			return Integer.toString(sanity.stat[sanityTimes]);
		}
	}
	
	public String downSanity(){
		if(sanityTimes == 0){
			return Integer.toString(sanity.stat[sanityTimes]);
		}
		else{
			if(sanityTimes > 8){
				sanityTimes--;
				return Integer.toString(sanity.stat[8]);
			}
			else{
				sanityTimes--;

				return Integer.toString(sanity.stat[sanityTimes]);
			}
		}
	}
	
	public int upOmen(){
		omens++;
		return omens;
	}
	
	public int downOmen(){
		omens--;
		return omens;
	}
	
	public String getMightTxt(){
		return Integer.toString(might.stat[mightTimes]);
	}
	
	public String getSpeedTxt(){
		return Integer.toString(speed.stat[speedTimes]);
	}
	
	public String getKnowlageTxt(){
		return Integer.toString(knowlage.stat[knowlageTimes]);
	}
	
	public String getSanityTxt(){
		return Integer.toString(sanity.stat[sanityTimes]);
	}
	
	public void pickUpItem(Items i){
		mightTimes += i.might;
		speedTimes += i.speed;
		sanityTimes += i.sanity;
		knowlageTimes += i.knowlage;
		if (i.useable){
			useableItems.add(i);
		}
	}
	
	public void dropItem(Items i){
		mightTimes -= i.might;
		speedTimes -= i.speed;
		sanityTimes -= i.sanity;
		knowlageTimes -= i.knowlage;
		if (i.useable){
			useableItems.remove(i);
		}
	}
	
}
