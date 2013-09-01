package com.betrayal.betrayalchar;

import java.util.ArrayList;


public enum Items {
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
	private Items(int m, int s, int sa, int k, boolean u, String rn, String wl, String in, int... ur) {
		might = m;
		speed = s;
		sanity = k;
		knowlage = k;
		useable = u;
		useRoll = ur;
		rollName = rn;
		weaponLoss = wl;
		itemName = in;
	}

	public ArrayList<Integer> useItem(Player0 p){
		ArrayList<Integer> roll = new ArrayList<Integer>();
		if(useable){
			if(rollName.equals("might")){
				roll.addAll(p.player.doMightRoll());		}

			else if(rollName.equals("speed")){
				roll.addAll(p.player.doSpeedRoll());
			}

			else if(rollName.equals("sanity")){
				roll.addAll(p.player.doSanityRoll());
			}

			else if(rollName.equals("knowlage")){
				roll.addAll(p.player.doKnowlageRoll());
			}
			else{

			}

		}
		else{
			if(weaponLoss.equals("might")){
				if(p.player.mightTimes >  8){
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[8]));
				}
				else{
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[p.player.mightTimes]));

				}				
				for(int i = 0; i<useRoll[1]; i++){
					p.downMight(p.mainView);
				}
			}
			else if(weaponLoss.equals("speed")){
				if(p.player.mightTimes >  8){
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[8]));
				}
				else{
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[p.player.mightTimes]));

				}
				for(int i = 0; i<useRoll[1]; i++){
					p.downSpeed(p.mainView);
				}
			}
			else if(weaponLoss.equals("sanity")){
				if(p.player.mightTimes >  8){
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[8]));
				}
				else{
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[p.player.mightTimes]));

				}
				for(int i = 0; i<useRoll[1]; i++){
					p.downSanity(p.mainView);
				}
			}
			else if(weaponLoss.equals("knowlage")){
				if(p.player.mightTimes >  8){
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[8]));
				}
				else{
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[p.player.mightTimes]));

				}
				for(int i = 0; i<useRoll[1]; i++){
					p.downKnowlage(p.mainView);
				}
			}
			else{
				if(p.player.mightTimes >  8){
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[8]));
				}
				else{
					roll.addAll(p.player.doRoll(useRoll[0]+p.player.might.stat[p.player.mightTimes]));

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

