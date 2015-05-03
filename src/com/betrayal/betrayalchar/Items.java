package com.betrayal.betrayalchar;

import java.util.ArrayList;


public enum Items {
	//ITEMS (might, speed, knowalge, useable, rollName, weaponloss, itemName, 
	blodDagger(0, 0, 0, 0, false, "", "speed","BlodDagger", 3, 1),
	sacrificialDagger(0,0,0,0,false, "", "","SacrificialDagger", 3),
	spear(0,0,0,0,false,"","","Spear",2),
	axe(0,0,0,0,false,"","","Axe",1);

	public boolean useable;
	public int might;
	public int speed;
	public int sanity;
	public int knowlage;
	public String rollName;
	public String weaponLoss;
	public String itemName;
	public int[] useRoll;
	
	private Items(int might, int speed, int sanity, int knowlage, boolean useable, String rollName, String weaponLoss, String itemName, int... useRoll) {
		this.might = might;
		this.speed = speed;
		this.sanity = sanity;
		this.knowlage = knowlage;
		this.useable = useable;
		this.useRoll = useRoll;
		this.rollName = rollName;
		this.weaponLoss = weaponLoss;
		this.itemName = itemName;
	}

	public ArrayList<Integer> useItem(MainPlayer mainPlayer){
		ArrayList<Integer> roll = new ArrayList<Integer>();
		if(useable){
			if(rollName.equals("might")){
				roll.addAll(mainPlayer.player.doMightRoll());		}

			else if(rollName.equals("speed")){
				roll.addAll(mainPlayer.player.doSpeedRoll());
			}

			else if(rollName.equals("sanity")){
				roll.addAll(mainPlayer.player.doSanityRoll());
			}

			else if(rollName.equals("knowlage")){
				roll.addAll(mainPlayer.player.doKnowlageRoll());
			}
			else{

			}

		}
		else{
			if(mainPlayer.player.mightTimes >  8){
				roll.addAll(Player.doRoll(useRoll[0]+mainPlayer.player.might.stat[8]));
			}
			else{
				roll.addAll(Player.doRoll(useRoll[0]+mainPlayer.player.getMight()));
				
			}
			if(weaponLoss.equals("might")){			
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.mightView.setCurrentDigit(mainPlayer.player.downStat(weaponLoss));
				}
			}
			else if(weaponLoss.equals("speed")){
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.speedView.setCurrentDigit(mainPlayer.player.downStat(weaponLoss));
				}
			}
			else if(weaponLoss.equals("sanity")){
				
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.sanityView.setCurrentDigit(mainPlayer.player.downStat(weaponLoss));
				}
			}
			else if(weaponLoss.equals("knowlage")){
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.knowlageView.setCurrentDigit(mainPlayer.player.downStat(weaponLoss));
				}
			}
			
		}
		return roll;
	}
	public static Items getItem(int i){
		if(i == 0){
			return blodDagger;
		}
		else if(i == 1){
			return sacrificialDagger;
		}
		else if(i == 2){
			return axe;
		}
		else{
			return spear;
		}
		/**
		 * else if(i==X){
		 * return Items
		 * }
		 */
	}
}

