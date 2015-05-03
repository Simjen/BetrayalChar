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
	private static Random rand = new Random();
	private ArrayList<Items> useableItems = new ArrayList<Items>();
	
	
	public static ArrayList<Integer> doRoll(int r){
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
			return doRoll(getMight());
		}
	}
	public ArrayList<Integer> doSpeedRoll(){
		
		if (speedTimes >=8) {
			return doRoll(speed.stat[8]);
		}
		else{
			
			return doRoll(getSpeed());
		}
	}
	public ArrayList<Integer> doKnowlageRoll(){
		if (knowlageTimes >=8) {
			return doRoll(knowlage.stat[8]);
		}
		else{
			return doRoll(getKnowlage());
		}
	}
	public ArrayList<Integer> doSanityRoll(){
		if (sanityTimes >= 8) {
			return doRoll(sanity.stat[8]);
		}
		else{
			return doRoll(getSanity());
		}
	}

	public ArrayList<Integer> doHauntRoll(){
		return new ArrayList<Integer>();
	}
	
	
	public int upStat(String statName){
		if(statName.equals("might")){
			if(mightTimes >= 8){
				mightTimes++;
				return might.stat[8];
			}
			else{
				mightTimes++;
				return getMight();
			}
		}
		else if(statName.equals("speed")){
			if(speedTimes >= 8){
				speedTimes++;
				return speed.stat[8];
			}
			else{
				speedTimes++;
				return getSpeed();
			}
		}
		else if(statName.equals("knowlage")){
			if(knowlageTimes >= 8){
				knowlageTimes++;
				return knowlage.stat[8];
			}
			else{
				knowlageTimes++;
				return getKnowlage();
			}
		}
		else{
			if(sanityTimes >= 8){
				sanityTimes++;
				return sanity.stat[8];
			}
			else{
				sanityTimes++;
				return getSanity();
			}
		}
		
	}
	
	public int downStat(String statName){
		if(statName.equals("might")){
			if(mightTimes == 0){
				return getMight();
			}
			else{
				if(mightTimes > 8){
					mightTimes--;
					return might.stat[8];
				}
				else{
					mightTimes--;

					return getMight();
				}
			}
		}
		else if(statName.equals("speed")){
			if(speedTimes == 0){
				return getSpeed();
			}
			else{
				if(speedTimes > 8){
					speedTimes--;
					return speed.stat[8];
				}
				else{
					speedTimes--;

					return getSpeed();
				}
			}
		}
		else if(statName.equals("knowlage")){
			if(knowlageTimes == 0){
				return getKnowlage();
			}
			else{
				if(knowlageTimes > 8){
					knowlageTimes--;
					return knowlage.stat[8];
				}
				else{
					knowlageTimes--;

					return getKnowlage();
				}
			}
		}
		else{
			if(sanityTimes == 0){
				return getSanity();
			}
			else{
				if(sanityTimes > 8){
					sanityTimes--;
					return sanity.stat[8];
				}
				else{
					sanityTimes--;

					return getSanity();
				}
			}
		}
		
	}
	public int prevStat(String statName){
		if(statName.equals("might")){
			if(mightTimes < 1) return 0;
			else if(mightTimes > 8) return might.stat[8];
			else return might.stat[mightTimes-1];
		}
		else if(statName.equals("speed")){
			if(speedTimes < 1) return 0;
			else if(speedTimes > 8) return speed.stat[8];
			else return speed.stat[speedTimes-1];
		}
		else if(statName.equals("knowlage")){
			if(knowlageTimes < 1) return 0;
			else if(knowlageTimes > 8) return knowlage.stat[8];
			else return knowlage.stat[knowlageTimes-1];
		}
		else{
			if(sanityTimes < 1) return 0;
			else if(sanityTimes > 8) return sanity.stat[8];
			else return sanity.stat[sanityTimes-1];
		}
	}
	public int nextStat(String statName){
		if(statName.equals("might")){
			if(mightTimes > 7) return might.stat[8];
			else if(mightTimes == 0) return 0;
			else return might.stat[mightTimes+1];
		}
		else if(statName.equals("speed")){
			if(speedTimes > 7) return speed.stat[8];
			else if(speedTimes == 0) return 0;
			else return speed.stat[speedTimes+1];
		}
		else if(statName.equals("knowlage")){
			if(knowlageTimes > 7) return knowlage.stat[8];
			else if(knowlageTimes == 0) return 0;
			else return knowlage.stat[knowlageTimes+1];
		}
		else{
			if(sanityTimes > 7) return sanity.stat[8];
			else if(sanityTimes == 0) return 0;
			else return sanity.stat[sanityTimes+1];
		}
	}
	
	
	public int getMight(){
		return might.stat[mightTimes];
	}
	
	public int getSpeed(){
		return speed.stat[speedTimes];
	}
	
	public int getKnowlage(){
		return knowlage.stat[knowlageTimes];
	}
	
	public int getSanity(){
		return sanity.stat[sanityTimes];
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
			mightTimesInit = mightTimes = Stats.PLAYER2MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER2SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER2KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER2SANITY.start;
			might = Stats.PLAYER2MIGHT;
			speed = Stats.PLAYER2SPEED;
			knowlage = Stats.PLAYER2KNOWLAGE;
			sanity = Stats.PLAYER2SANITY;
		}
		else if(player == 3){
			mightTimesInit = mightTimes = Stats.PLAYER3MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER3SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER3KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER3SANITY.start;
			might = Stats.PLAYER3MIGHT;
			speed = Stats.PLAYER3SPEED;
			knowlage = Stats.PLAYER2KNOWLAGE;
			sanity = Stats.PLAYER2SANITY;
		}
		else if(player == 4){
			mightTimesInit = mightTimes = Stats.PLAYER4MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER4SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER4KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER4SANITY.start;
			might = Stats.PLAYER4MIGHT;
			speed = Stats.PLAYER4SPEED;
			knowlage = Stats.PLAYER4KNOWLAGE;
			sanity = Stats.PLAYER4SANITY;
		}
		else if(player == 5){
			mightTimesInit = mightTimes = Stats.PLAYER5MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER5SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER5KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER5SANITY.start;
			might = Stats.PLAYER5MIGHT;
			speed = Stats.PLAYER5SPEED;
			knowlage = Stats.PLAYER5KNOWLAGE;
			sanity = Stats.PLAYER5SANITY;
		}
		else if(player == 6){
			mightTimesInit = mightTimes = Stats.PLAYER6MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER6SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER6KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER6SANITY.start;
			might = Stats.PLAYER6MIGHT;
			speed = Stats.PLAYER6SPEED;
			knowlage = Stats.PLAYER6KNOWLAGE;
			sanity = Stats.PLAYER6SANITY;
		}
		else if(player == 7){
			mightTimesInit = mightTimes = Stats.PLAYER7MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER7SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER7KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER7SANITY.start;
			might = Stats.PLAYER7MIGHT;
			speed = Stats.PLAYER7SPEED;
			knowlage = Stats.PLAYER7KNOWLAGE;
			sanity = Stats.PLAYER7SANITY;
		}
		else if(player == 8){
			mightTimesInit = mightTimes = Stats.PLAYER8MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER8SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER8KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER8SANITY.start;
			might = Stats.PLAYER8MIGHT;
			speed = Stats.PLAYER8SPEED;
			knowlage = Stats.PLAYER8KNOWLAGE;
			sanity = Stats.PLAYER8SANITY;
		}
		else if(player == 9){
			mightTimesInit = mightTimes = Stats.PLAYER9MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER9SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER9KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER9SANITY.start;
			might = Stats.PLAYER9MIGHT;
			speed = Stats.PLAYER9SPEED;
			knowlage = Stats.PLAYER9KNOWLAGE;
			sanity = Stats.PLAYER9SANITY;
		}
		else if(player == 10){
			mightTimesInit = mightTimes = Stats.PLAYER10MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER10SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER10KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER10SANITY.start;
			might = Stats.PLAYER10MIGHT;
			speed = Stats.PLAYER10SPEED;
			knowlage = Stats.PLAYER10KNOWLAGE;
			sanity = Stats.PLAYER10SANITY;
		}
		else if(player == 11){
			mightTimesInit = mightTimes = Stats.PLAYER11MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER11SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER11KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER11SANITY.start;
			might = Stats.PLAYER11MIGHT;
			speed = Stats.PLAYER11SPEED;
			knowlage = Stats.PLAYER11KNOWLAGE;
			sanity = Stats.PLAYER11SANITY;
		}
		else{
			mightTimesInit = mightTimes = Stats.PLAYER12MIGHT.start;
			speedTimesInit = speedTimes = Stats.PLAYER12SPEED.start;
			knowlageTimesInit = knowlageTimes = Stats.PLAYER12KNOWLAGE.start;
			sanityTimesInit = sanityTimes = Stats.PLAYER12SANITY.start;
			might = Stats.PLAYER12MIGHT;
			speed = Stats.PLAYER12SPEED;
			knowlage = Stats.PLAYER12KNOWLAGE;
			sanity = Stats.PLAYER12SANITY;
		}

	}
}
