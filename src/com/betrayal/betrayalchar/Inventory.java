package com.betrayal.betrayalchar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Inventory implements Iterable<Items>, Collection<Items> {

    private Set<Items> inventory;
    private Activity activity;

    public Inventory(Activity activity) {
        this.activity = activity;
        this.inventory = new HashSet<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addItemToInventoryView(final Items i, final GridLayout itemsLayout) {
        final ItemView itemView = (ItemView) View.inflate(activity, R.layout.inventory_item, null);
        Drawable drawable = activity.getResources().getDrawable(i.getDrawable());
        itemView.setImage(drawable);
        itemView.setId(i.getID());
        itemView.setText(i.getItemNameID());
        final GestureDetector gestureDetector =
                new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle(R.string.title_drop);
                        builder.setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        try {
                                            inventory.remove(Items.getItem(itemView.getId()));
                                            itemsLayout.removeView(itemView);
                                            itemsLayout.invalidate();
                                        } catch (Items.ItemNotFoundException e1) {
                                            Log.e("Item not Found", e1.getMessage());
                                        }
                                    }
                                });
                        builder.setNegativeButton(R.string.no,
                                new DialogInterface.OnClickListener() {
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
                        try {
                            CardDialogFragment cardDialogFragment = new CardDialogFragment();
                            cardDialogFragment.setImage(Items.getItem(itemView.getId())
                                        .getDrawable());
                            cardDialogFragment.show(activity.getFragmentManager(), "CardImage");
                            return true;
                        } catch (Items.ItemNotFoundException e1) {
                            Log.e("Item not Found", e1.getMessage());
                            return false;
                        }
                    }
                });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        itemView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
        itemsLayout.addView(itemView);
        itemsLayout.invalidate();
    }

    public void showInventoryView() {
        LinearLayout mainLayout = (LinearLayout) View.inflate(activity, R.layout.inventory, null);
        final GridLayout itemsLayout = mainLayout.findViewById(R.id.inventoryGrid);

        ImageView imageView = mainLayout.findViewById(R.id.addItem);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemPopup(view, itemsLayout);
            }
        });

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

    private void showAddItemPopup(View v, final GridLayout itemsLayout) {
        PopupMenu popup = new PopupMenu(activity, v);

        addItemsToPopup(popup, false);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    Items item = Items.getItem(menuItem.getItemId());
                    if (inventory.add(item)) {
                        addItemToInventoryView(item, itemsLayout);
                    }
                    return true;
                } catch (Items.ItemNotFoundException e) {
                    Log.e("Item not Found", e.getMessage());
                    return false;
                }
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
        return inventory.addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return inventory.removeAll
                (collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return inventory.retainAll
                (collection);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    public Set<Items> getInventory() {
        return inventory;
    }

    public List<Integer> getInventoryIds() {
        List<Integer> ids = new ArrayList<>();
        for(Items i : inventory){
            ids.add(i.getID());
        }
        return ids;
    }

    public void addItemsToPopup(PopupMenu popup, boolean onlyWeapons) {
        Collections.sort(Items.allItems, new Comparator<Items>() {
            @Override
            public int compare(Items item, Items otherItem) {
                return activity.getResources().getString(item.getItemNameID()).compareTo
                        (activity.getResources().getString(otherItem.getItemNameID()));
            }
        });
        for (Items i : Items.allItems) {
            if(onlyWeapons && !i.isWeapon()) {
                continue;
            }
            popup.getMenu().add(Menu.NONE, i.getID(), Menu.NONE, i.getItemNameID());
        }
    }
}
