package com.betrayal.betrayalchar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
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
                                        inventory.remove(Items.getItem(itemView.getId()));
                                        itemView.setVisibility(View.GONE);
                                        itemsLayout.invalidate();
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
                        CardDialogFragment cardDialogFragment = new CardDialogFragment();
                        cardDialogFragment.setImage(Items.getItem(itemView.getId()).getDrawable());
                        cardDialogFragment.show(activity.getFragmentManager(), "CardImage");
                        return true;
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
        for (Items i : Items.weapons) {
            popup.getMenu().add(Menu.NONE, i.getID(), Menu.NONE, i.getItemNameID());
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Items item = Items.getItem(menuItem.getItemId());
                if (inventory.add(item)) {
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
        return inventory.addAll(collection);
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

    public Set<Items> getInventory() {
        return inventory;
    }
}
