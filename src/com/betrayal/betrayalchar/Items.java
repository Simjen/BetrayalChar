package com.betrayal.betrayalchar;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import java.util.Arrays;
import java.util.List;


public enum Items {
    blodDagger(new WeaponImpl("speed", 3, 1), R.drawable.item_blooddagger, R.id.bloodDagger, R.string.blooddagger, false),
    sacrificialDagger(new WeaponImpl( 3), R.drawable.item_sacrificialdagger, R.id.sacrificialDagger, R.string.sacrificialdagger, false),
    spear(new WeaponImpl(2), R.drawable.omen_spear, R.id.spear,  R.string.spear, true),
    axe(new WeaponImpl(1), R.drawable.item_axe, R.id.axe, R.string.axe, false),
    NONE;

    static final List<Items> items = Arrays.asList(blodDagger, sacrificialDagger, spear, axe);
    private boolean omen;
    public boolean usable;
    private ItemCard item;
    private int itemNameID;
    private int drawable;
    private int ID;

    Items() {
    }

    Items(ItemCard item, @DrawableRes int imageResource, @IdRes int id, @StringRes int itemNameID, boolean omen ){
        this.item = item;
        this.itemNameID = itemNameID;
        ID = id;
        drawable = imageResource;
        this.omen = omen;

    }

    public static Items getItem(int itemId) {
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

    /**
     * @param mainPlayer The main Player
     * @return List of Integers if it is a weapon.
     */
    public Object useItem(MainPlayer mainPlayer){
        return item.useItem(mainPlayer);
    }

    public boolean isOmen(){
        return omen;
    }

    public boolean isWeapon(){
        return item instanceof Weapon;
    }

    public @DrawableRes int getDrawable() {
        return drawable;
    }

    public @IdRes int getID() {
        return ID;
    }

    public @StringRes int getItemNameID() {
        return itemNameID;
    }
}

