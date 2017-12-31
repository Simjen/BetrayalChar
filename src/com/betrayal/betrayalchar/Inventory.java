package com.betrayal.betrayalchar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;


public class Inventory implements Iterable<Items>, Collection<Items>{

    private Set<Items> inventory;
    private Activity activity;

    public Inventory(Activity activity) {
        this.activity = activity;
        this.inventory = new HashSet<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addItemToInventoryView(final Items i, final GridLayout itemsLayout) {
        final ImageView imageView = (ImageView) View.inflate(activity, R.layout.inventory_item, null);
        imageView.setImageResource(i.getDrawable());
        imageView.setId(i.getID());
        final GestureDetector gestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(R.string.title_drop);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        inventory.remove(Items.getItem(imageView.getId()));
                        imageView.setVisibility(View.GONE);
                        itemsLayout.invalidate();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //No action
                    }
                });
                builder.create().show();
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                super.onSingleTapConfirmed(e);

                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        itemsLayout.addView(imageView);
        itemsLayout.invalidate();
    }

    public void showInventoryView() {
        LinearLayout mainLayout;

        final GridLayout itemsLayout = (GridLayout) View.inflate(activity, R.layout.inventory_items,null);

        mainLayout = (LinearLayout) View.inflate(activity, R.layout.inventory, null);
        mainLayout.addView(itemsLayout);
        ImageView imageView = (ImageView) View.inflate(activity, R.layout.inventory_item, null);
        imageView.setImageResource(R.drawable.empty_square);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemPopup(view, itemsLayout);
            }
        });
        mainLayout.addView(imageView);

        for (Items i : inventory) {
            addItemToInventoryView(i, itemsLayout);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(mainLayout);
        builder.setTitle(R.string.inventory);
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void showAddItemPopup(View v, final GridLayout itemsLayout){
        PopupMenu popup = new PopupMenu(activity, v);
        for(Items i : Items.items) {
            popup.getMenu().add(Menu.NONE, i.getID(), Menu.NONE, i.getItemNameID());
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Items item = Items.getItem(menuItem.getItemId());
                if(inventory.add(item)) {
                    addItemToInventoryView(item, itemsLayout);
                }
                return true;
            }
        });
        popup.show();
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return inventory.contains(o);
    }

    @NonNull
    @Override
    public Iterator<Items> iterator() {
        return inventory.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return inventory.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        return inventory.toArray(ts);
    }

    @Override
    public boolean add(Items items) {
        return inventory.add(items);
    }

    @Override
    public boolean remove(Object o) {
        return inventory.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return inventory.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Items> collection) {
        return addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return retainAll(collection);
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
