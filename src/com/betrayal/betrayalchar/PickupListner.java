package com.betrayal.betrayalchar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PickupListner implements OnMenuItemClickListener {
	
	MainPlayer p;
	public PickupListner(MainPlayer mainPlayer){
		this.p = mainPlayer;
	}
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		int i = Integer.parseInt(((String) item.getTitle()).substring(0, 1));
		p.inventory.add(Items.getItem(i));
		return true;
	}
}


