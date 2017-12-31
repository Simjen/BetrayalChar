package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum Items {
    //ITEMS (might, speed, knowalge, usable, rollName, weaponloss, itemName, attack, statloss)
    blodDagger(ItemStat.noStat(), false, R.drawable.blood_dagger, R.id.bloodDagger, "", "speed", R.string.blooddagger, 3, 1),
    sacrificialDagger(ItemStat.noStat(), false, R.drawable.sacrificial_dagger, R.id.sacrificialDagger, "", "", R.string.sacrificialdagger, 3),
    spear(ItemStat.noStat(), false, R.drawable.spear, R.id.spear, "", "", R.string.spear, 2),
    axe(ItemStat.noStat(), false, R.drawable.axe, R.id.axe, "", "", R.string.axe, 1),
    NONE;
    static final List<Items> items = Arrays.asList(blodDagger, sacrificialDagger, spear, axe);
    private ItemStat stat;
    public boolean usable;
    public String rollName;
    public String weaponLoss;
    private int itemNameID;
    public int[] useRoll;
    private int drawable;
    private int ID;

    Items(ItemStat stat, boolean usable, int imageResource, int id, String rollName, String weaponLoss, int itemNameID, int... useRoll) {
        this.stat = stat;
        this.usable = usable;
        this.itemNameID = itemNameID;
        this.useRoll = useRoll;
        this.rollName = rollName;
        this.weaponLoss = weaponLoss;
        drawable = imageResource;
        ID = id;
    }

    Items() {
    }

    public ArrayList<Integer> useItem(MainPlayer mainPlayer) {
        ArrayList<Integer> roll = new ArrayList<Integer>();
        if (usable) {
            if (rollName.equals("might")) {
                roll.addAll(mainPlayer.player.doMightRoll());
            } else if (rollName.equals("speed")) {
                roll.addAll(mainPlayer.player.doSpeedRoll());
            } else if (rollName.equals("sanity")) {
                roll.addAll(mainPlayer.player.doSanityRoll());
            } else if (rollName.equals("knowledge")) {
                roll.addAll(mainPlayer.player.doKnowledgeRoll());
            } else {

            }

        } else {

            roll.addAll(Player.doRoll(useRoll[0] + mainPlayer.player.getMight()));


            if (weaponLoss.equals("might")) {
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.mightStat.Decrease();
                    mainPlayer.mightView.setCurrentDigit(mainPlayer.player.mightStat);
                }
            } else if (weaponLoss.equals("speed")) {
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.speedStat.Decrease();
                    mainPlayer.speedView.setCurrentDigit(mainPlayer.player.speedStat);
                }
            } else if (weaponLoss.equals("sanity")) {

                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.sanityStat.Decrease();
                    mainPlayer.sanityView.setCurrentDigit(mainPlayer.player.sanityStat
                    );
                }
            } else if (weaponLoss.equals("knowledge")) {
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.knowledgeStat.Decrease();
                    mainPlayer.knowledgeView.setCurrentDigit(mainPlayer.player.knowledgeStat);
                }
            }

        }
        return roll;
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

    public int getDrawable() {
        return drawable;
    }

    public int getID() {
        return ID;
    }

    public int getItemNameID() {
        return itemNameID;
    }
}

