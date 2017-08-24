package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.Random;

public class Player {

	public Stat mightStat;
	public Stat speedStat;
	public Stat knowledgeStat;
	public Stat sanityStat;
	public int omens = 0;
	private static Random rand = new Random();


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

			return doRoll(getMight());

	}
	public ArrayList<Integer> doSpeedRoll(){

			return doRoll(getSpeed());

	}
	public ArrayList<Integer> doKnowlageRoll(){

			return doRoll(getKnowlage());

	}
	public ArrayList<Integer> doSanityRoll(){
			return doRoll(getSanity());

	}

	public ArrayList<Integer> doHauntRoll(){
		return doRoll(6);
	}

	public int getMight(){
		return mightStat.getStatValue();
	}
	
	public int getSpeed(){
		return speedStat.getStatValue();
	}
	
	public int getKnowlage(){

		return knowledgeStat.getStatValue();
	}
	
	public int getSanity(){
			return sanityStat.getStatValue();
	}

	
	public Player(int player){
		if(player == 1){
			mightStat = new Stat(Stats.PLAYER1MIGHT.start,Stats.PLAYER1MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER1SPEED.start,Stats.PLAYER1SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER1KNOWLAGE.start,Stats.PLAYER1KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER1SANITY.start, Stats.PLAYER1SANITY.stat);

		}
		else  if(player == 2){
			mightStat = new Stat(Stats.PLAYER2MIGHT.start,Stats.PLAYER2MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER2SPEED.start,Stats.PLAYER2SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER2KNOWLAGE.start,Stats.PLAYER2KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER2SANITY.start, Stats.PLAYER2SANITY.stat);
		}
		else if(player == 3){
			mightStat = new Stat(Stats.PLAYER3MIGHT.start,Stats.PLAYER3MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER3SPEED.start,Stats.PLAYER3SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER3KNOWLAGE.start,Stats.PLAYER3KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER3SANITY.start, Stats.PLAYER3SANITY.stat);
		}
		else if(player == 4){
			mightStat = new Stat(Stats.PLAYER4MIGHT.start,Stats.PLAYER4MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER4SPEED.start,Stats.PLAYER4SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER4KNOWLAGE.start,Stats.PLAYER4KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER4SANITY.start, Stats.PLAYER4SANITY.stat);
		}
		else if(player == 5){
			mightStat = new Stat(Stats.PLAYER5MIGHT.start,Stats.PLAYER5MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER5SPEED.start,Stats.PLAYER5SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER5KNOWLAGE.start,Stats.PLAYER5KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER5SANITY.start, Stats.PLAYER5SANITY.stat);
		}
		else if(player == 6){
			mightStat = new Stat(Stats.PLAYER6MIGHT.start,Stats.PLAYER6MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER6SPEED.start,Stats.PLAYER6SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER6KNOWLAGE.start,Stats.PLAYER6KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER6SANITY.start, Stats.PLAYER6SANITY.stat);
		}
		else if(player == 7){
			mightStat = new Stat(Stats.PLAYER7MIGHT.start,Stats.PLAYER7MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER7SPEED.start,Stats.PLAYER7SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER7KNOWLAGE.start,Stats.PLAYER7KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER7SANITY.start, Stats.PLAYER7SANITY.stat);
		}
		else if(player == 8){
			mightStat = new Stat(Stats.PLAYER8MIGHT.start,Stats.PLAYER8MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER8SPEED.start,Stats.PLAYER8SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER8KNOWLAGE.start,Stats.PLAYER8KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER8SANITY.start, Stats.PLAYER8SANITY.stat);
		}
		else if(player == 9){
			mightStat = new Stat(Stats.PLAYER9MIGHT.start,Stats.PLAYER9MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER9SPEED.start,Stats.PLAYER9SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER9KNOWLAGE.start,Stats.PLAYER9KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER9SANITY.start, Stats.PLAYER9SANITY.stat);
		}
		else if(player == 10){
			mightStat = new Stat(Stats.PLAYER10MIGHT.start,Stats.PLAYER10MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER10SPEED.start,Stats.PLAYER10SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER10KNOWLAGE.start,Stats.PLAYER10KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER10SANITY.start, Stats.PLAYER10SANITY.stat);
		}
		else if(player == 11){
			mightStat = new Stat(Stats.PLAYER11MIGHT.start,Stats.PLAYER11MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER11SPEED.start,Stats.PLAYER11SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER11KNOWLAGE.start,Stats.PLAYER11KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER11SANITY.start, Stats.PLAYER11SANITY.stat);
		}
		else{
			mightStat = new Stat(Stats.PLAYER12MIGHT.start,Stats.PLAYER12MIGHT.stat);
			speedStat = new Stat(Stats.PLAYER12SPEED.start,Stats.PLAYER12SPEED.stat);
			knowledgeStat = new Stat(Stats.PLAYER12KNOWLAGE.start,Stats.PLAYER12KNOWLAGE.stat);
			sanityStat = new Stat(Stats.PLAYER12SANITY.start, Stats.PLAYER12SANITY.stat);
		}

	}
}
