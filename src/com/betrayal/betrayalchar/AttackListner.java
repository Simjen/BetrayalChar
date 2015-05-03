package com.betrayal.betrayalchar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AttackListner implements OnMenuItemClickListener {
	
	MainPlayer p;
	public AttackListner(MainPlayer mainPlayer){
		this.p = mainPlayer;
	}
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		int i = Integer.parseInt(((String) item.getTitle()).substring(0, 1));
		p.mightAttack(p.inventory.get(i));
		
		return true;
	}
}


