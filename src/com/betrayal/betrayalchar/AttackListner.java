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
		if(i == 0){
			p.mightDefence(p.findViewById(R.id.main_layout));
		}
		else {
			p.mightAttack(p.inventory.get(i - 1));
		}
		return true;
	}

}


