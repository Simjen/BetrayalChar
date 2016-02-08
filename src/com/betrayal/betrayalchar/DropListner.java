package com.betrayal.betrayalchar;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;

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


