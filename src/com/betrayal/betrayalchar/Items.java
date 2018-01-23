package com.betrayal.betrayalchar;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;


public class Items<T> implements Serializable{
    private static final ItemCard<Void> noAction = new ItemCard<Void>() {
        @Override
        public Void useItem(MainPlayer mainPlayer) {
            return null;
        }
    };
    // TODO right now only one weapon is able to be used at a time :O
    //region Weapons
    public static final Items bloodDagger =
            new Items<>(new WeaponImpl("speed", 3, 1), R.drawable.item_blooddagger,
                    R.id.bloodDagger, R.string.blooddagger, false);
    public static final Items sacrificialDagger =
            new Items<>(new WeaponImpl(3), R.drawable.item_sacrificialdagger,
                    R.id.sacrificialDagger, R.string.sacrificialdagger, false);
    public static final Items spear =
            new Items<>(new WeaponImpl(2), R.drawable.omen_spear, R.id.spear, R.string.spear, true);

    public static final Items axe =
            new Items<>(new WeaponImpl(1), R.drawable.item_axe, R.id.axe, R.string.axe, false);

    public static final Items revolver = new Items<>(new Weapon() {
        @Override
        public List<Integer> useItem(MainPlayer player) {
            return Player.doRoll(player.player.getSpeed() + 1);
        }
    }, R.drawable.item_revolver, R.id.revolver, R.string.revolver, false);
    //endregion

    //region Omen
    //TODO Omen effects
    public static final Items bite = new Items<>(noAction, R.drawable.omen_bite, R.id.bite, R
            .string.bite, true);
    public static final Items book = new Items<>(noAction, R.drawable.omen_book, R.id.book, R
            .string.book, true);
    public static final Items crystalBall = new Items<>(noAction, R.drawable.omen_crystalball, R
            .id.crystal_ball, R
            .string.crystal_ball, true);
    public static final Items dog = new Items<>(noAction, R.drawable.omen_dog, R.id.dog, R
            .string.dog, true);
    public static final Items girl = new Items<>(noAction, R.drawable.omen_girl, R.id.girl, R
            .string.girl, true);
    public static final Items holysymbol = new Items<>(noAction, R.drawable.omen_holysymbol, R.id
            .holysymbol, R
            .string.holysymbol, true);
    public static final Items madman = new Items<>(noAction, R.drawable.omen_madman, R.id.madman, R
            .string.madman, true);
    public static final Items mask = new Items<>(noAction, R.drawable.omen_mask, R.id.mask, R
            .string.mask, true);
    public static final Items medallion = new Items<>(noAction, R.drawable.omen_medallion, R.id
            .medallion, R
            .string.medallion, true);
    public static final Items ring = new Items<>(noAction, R.drawable.omen_ring, R.id.ring, R
            .string.ring, true);
    public static final Items skull = new Items<>(noAction, R.drawable.omen_skull, R.id.skull, R
            .string.skull, true);
    public static final Items spiritBord = new Items<>(noAction, R.drawable.omen_spiritboard, R
            .id.spirit_bord, R.string.spirit_bord, true);
    //endregion

    //region items
    //TODO Item effects
    public static final Items adrenalineShot = new Items<>(noAction, R.drawable.item_adrenalineshot, R.id
            .adrenaline_shot, R
            .string.adrenaline_shot, false);
    public static final Items amuletOfTheAges = new Items<>(noAction, R.drawable.item_amuletoftheages, R.id
            .amulet_of_the_ages, R
            .string.amulet_of_the_ages, false);
    public static final Items angelFeather = new Items<>(noAction, R.drawable.item_angelfeather,
            R.id
            .angel_feather, R
            .string.angel_feather, false);
    public static final Items armor = new Items<>(noAction, R.drawable.item_armor, R.id
            .armor, R
            .string.armor, false);
    public static final Items bell = new Items<>(noAction, R.drawable.item_bell, R.id
            .bell, R
            .string.bell, false);
    public static final Items bottle = new Items<>(noAction, R.drawable.item_bottle, R.id
            .bottle, R
            .string.bottle, false);
    public static final Items candle = new Items<>(noAction, R.drawable.item_candle, R.id
            .candle, R
            .string.candle, false);
    public static final Items darkDice = new Items<>(noAction, R.drawable.item_darkdice, R.id
            .dark_dice, R
            .string.dark_dice, false);
    public static final Items dynamite = new Items<>(noAction, R.drawable.item_dynamite, R.id
            .dynamite, R
            .string.dynamite, false);
    public static final Items healingSalve = new Items<>(noAction, R.drawable.item_healingsalve,
            R.id.healing_salve, R
            .string.healing_salve, false);
    public static final Items idol = new Items<>(noAction, R.drawable.item_idol, R.id
            .idol, R
            .string.idol, false);

    public static final Items luckyStone = new Items<>(noAction, R.drawable.item_luckystone,
            R.id
            .lucky_stone, R
            .string.lucky_stone, false);
    public static final Items medicalKit = new Items<>(noAction, R.drawable.item_medicalkit,
            R.id
            .medical_kit, R
            .string.medical_kit, false);
    public static final Items musicBox = new Items<>(noAction, R.drawable.item_musicbox, R.id
            .music_box, R
            .string.music_box, false);

    public static final Items pickpocketGloves = new Items<>(noAction, R.drawable
            .item_pickpocketsgloves, R.id
            .pickpocket_gloves, R
            .string.pickpocket_gloves, false);

    public static final Items puzzleBox = new Items<>(noAction, R.drawable
            .item_puzzlebox, R.id
            .puzzle_box, R
            .string.puzzle_box, false);
    public static final Items rabbitsFoot = new Items<>(noAction, R.drawable
            .item_rabbitsfoot, R.id
            .rabbits_foot, R
            .string.rabbits_foot, false);
    public static final Items smellingSalt = new Items<>(noAction, R.drawable
            .item_smellingsalts, R.id
            .smelling_salts, R
            .string.smelling_salts, false);

    //endregion

    static final List<Items> allItems = Arrays.asList(bloodDagger, sacrificialDagger, spear, axe, bite, book,
            crystalBall,
            dog, girl, holysymbol, madman, mask, medallion, ring, spiritBord,
            adrenalineShot, amuletOfTheAges, angelFeather, armor, bottle, candle,
            darkDice,dynamite, healingSalve, idol, luckyStone, medicalKit, musicBox,
            pickpocketGloves, puzzleBox, rabbitsFoot, smellingSalt);

    private boolean omen;
    private ItemCard<T> item;
    private int itemNameID;
    private int drawable;
    private int ID;

    private Items(ItemCard<T> item, @DrawableRes int imageResource, @IdRes int id,
                  @StringRes int itemNameID, boolean omen) {
        this.item = item;
        this.itemNameID = itemNameID;
        ID = id;
        drawable = imageResource;
        this.omen = omen;

    }

    @SuppressWarnings("unchecked")
    public static Items<List<Integer>> getWeapon(@IdRes int itemId) throws ItemNotFoundException{
        for (Items i : allItems){
            if(itemId == i.getID() && i.isWeapon()){
                return (Items<List<Integer>>)i;
            }
        }
        throw new ItemNotFoundException();
    }

    public static Items getItem(@IdRes int itemId) throws ItemNotFoundException {
        for (Items i : allItems) {
            if (i.getID() == itemId) {
                return i;
            }
        }
        throw new ItemNotFoundException();
    }

    /**
     * @param mainPlayer The main Player
     * @return List of Integers if it is a weapon.
     */
    public T useItem(MainPlayer mainPlayer) {
        return item.useItem(mainPlayer);
    }

    public boolean isOmen() {
        return omen;
    }

    public boolean isWeapon() {
        return item instanceof Weapon;
    }

    public @DrawableRes
    int getDrawable() {
        return drawable;
    }

    public @IdRes
    int getID() {
        return ID;
    }

    public @StringRes
    int getItemNameID() {
        return itemNameID;
    }

    public static class ItemNotFoundException extends Throwable {
    }
}

