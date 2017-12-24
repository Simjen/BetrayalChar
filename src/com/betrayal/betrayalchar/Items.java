package com.betrayal.betrayalchar;

import java.util.ArrayList;


public enum Items {
	//ITEMS (might, speed, knowalge, useable, rollName, weaponloss, itemName, attack, statloss)
	blodDagger(0, 0, 0, 0, false, "", "speed","BloodDagger", 3, 1),
	sacrificialDagger(0,0,0,0,false, "", "","SacrificialDagger", 3),
	spear(0,0,0,0,false,"","","Spear",2),
	axe(0,0,0,0,false,"","","Axe",1),
	NONE;

	public boolean useable;
	public int might;
	public int speed;
	public int sanity;
	public int knowledge;
	public String rollName;
	public String weaponLoss;
	public String itemName;
	public int[] useRoll;
	
	Items(int might, int speed, int sanity, int knowledge, boolean useable, String rollName, String weaponLoss, String itemName, int... useRoll) {
		this.might = might;
		this.speed = speed;
		this.sanity = sanity;
		this.knowledge = knowledge;
		this.useable = useable;
		this.useRoll = useRoll;
		this.rollName = rollName;
		this.weaponLoss = weaponLoss;
		this.itemName = itemName;
	}
	Items(){
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

			else if(rollName.equals("knowledge")){
				roll.addAll(mainPlayer.player.doKnowledgeRoll());
			}
			else{

			}

		}
		else{

				roll.addAll(Player.doRoll(useRoll[0]+mainPlayer.player.getMight()));
				

			if(weaponLoss.equals("might")){			
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.player.mightStat.Decrease();
					mainPlayer.mightView.setCurrentDigit(mainPlayer.player.mightStat);
				}
			}
			else if(weaponLoss.equals("speed")){
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.player.speedStat.Decrease();
					mainPlayer.speedView.setCurrentDigit(mainPlayer.player.speedStat);
				}
			}
			else if(weaponLoss.equals("sanity")){
				
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.player.sanityStat.Decrease();
					mainPlayer.sanityView.setCurrentDigit(mainPlayer.player.sanityStat
					);
				}
			}
			else if(weaponLoss.equals("knowledge")){
				for(int i = 0; i<useRoll[1]; i++){
					mainPlayer.player.knowledgeStat.Decrease();
					mainPlayer.knowledgeView.setCurrentDigit(mainPlayer.player.knowledgeStat);
				}
			}
			
		}
		return roll;
	}
	public static Items getItem(int itemId){
		switch (itemId) {
			case R.id.axe:
				return Items.axe;
			case R.id.spear:
				return spear;
			case R.id.sacrificialDagger:
				return sacrificialDagger;
			case R.id.bloodDagger:
				return blodDagger;
			default:
				return NONE;
		}
	}
}

