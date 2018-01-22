package com.betrayal.betrayalchar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class MainPlayer extends Activity {

    //region fields
    private final IntentFilter intentFilter = new IntentFilter();
    private static final String[] names = new String[]{
            "Brandon", "Darrin", "Father", "Heather", "Jenny", "Madame", "Missy",
            "Ox", "Peter", "Professor", "Vivian", "Zoe"};
    public Player player;
    private String name;
    public StatView sanityView;
    public StatView knowledgeView;
    public StatView speedView;
    public StatView mightView;
    private Resources res;
    private View scrollView;
    private ActionBarDrawerToggle drawerToggle;
    private List<Integer> currentPlayers = new ArrayList<>();
    //endregion

    //region ActivityOverrides
    @SuppressLint("ClickableViewAccessibility")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player1);


        //  WIFIP2P
        //  Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        res = getResources();
        DrawerLayout drawer = findViewById(R.id.drawer);
        ListView navigationView = findViewById(R.id.navigation_drawer);
        navigationView
                .setAdapter(new ArrayAdapter<>(this, R.layout.simple_list_item_layout, names));
        navigationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (player != null) {
                    currentPlayers.remove((Integer) player.getPlayer());
                    adapterView.getChildAt(player.getPlayer()).setBackgroundColor(Color.WHITE);
                }
                currentPlayers.add(i);
                view.setBackgroundColor(Color.GREEN);
                initializePlayer(i);
                view.invalidate();
            }
        });
        navigationView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (player != null && player.getPlayer() == i) {
                    return false;
                }
                if (currentPlayers.contains(i)) {
                    currentPlayers.remove((Integer) i);
                    view.setBackgroundColor(Color.WHITE);
                } else {
                    currentPlayers.add(i);
                    view.setBackgroundColor(Color.BLUE);
                }
                view.invalidate();
                return true;
            }
        });
        drawerToggle =
                new ActionBarDrawerToggle(this, drawer, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(drawerToggle);

        //noinspection ConstantConditions
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mightView = findViewById(R.id.single_spinner_might);
        speedView = findViewById(R.id.single_spinner_speed);
        knowledgeView = findViewById(R.id.single_spinner_knowledge);
        sanityView = findViewById(R.id.single_spinner_sanity);
        scrollView = findViewById(R.id.scrollView);

        registerForContextMenu(mightView);
        registerForContextMenu(speedView);
        registerForContextMenu(knowledgeView);
        registerForContextMenu(sanityView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("PLAYER")) {
            initializePlayer(savedInstanceState.getInt("PLAYER"));
        }
    }

    private void initializePlayer(int number) {
        if (player != null) {
            //we switch from one player to another
            saveSharedPrefs();
        }
        name = MainActivity.names[number];
        player = new Player(number, this);
        setTitle(name);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        SharedPreferences prefs = getSharedPreferences(name, 0);
        // Setup Shared Preferences for each name

        String inventory = prefs.getString("Inventory", "");
        if (inventory.isEmpty()) {
            player.setInventory(new HashSet<Items>());
        } else {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Items>>() {}.getType();
            Collection<Items> items = gson.fromJson(inventory, collectionType);
            player.setInventory(items);
        }
        player.mightStat.setStatIndex(prefs.getInt("mightinit", player.mightStat.getStatIndex()));
        player.speedStat.setStatIndex(prefs.getInt("speedinit", player.speedStat.getStatIndex()));
        player.knowledgeStat
                .setStatIndex(prefs.getInt("knowledgeinit", player.knowledgeStat.getStatIndex()));
        player.sanityStat
                .setStatIndex(prefs.getInt("sanityinit", player.sanityStat.getStatIndex()));

        speedView.setCurrentStat(player.speedStat);
        sanityView.setCurrentStat(player.sanityStat);
        knowledgeView.setCurrentStat(player.knowledgeStat);
        mightView.setCurrentDigit(player.mightStat);
        speedView.setCurrentDigit(player.speedStat);
        knowledgeView.setCurrentDigit(player.knowledgeStat);
        sanityView.setCurrentDigit(player.sanityStat);

        mightView.setCurrentStat(player.mightStat);
        scrollView.setVisibility(View.VISIBLE);
    }
    //endregion

    //region ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stat_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        StatView.StatContextInfo info = (StatView.StatContextInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case (R.id.reset):
                switch (info.id) {
                    case R.id.single_spinner_might:
                        resetMight(mightView);
                    case R.id.single_spinner_knowledge:
                        resetKnowledge(knowledgeView);
                    case R.id.single_spinner_sanity:
                        resetSanity(sanityView);
                    case R.id.single_spinner_speed:
                        resetSpeed(speedView);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
    //endregion

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null) {
            outState.putInt("PLAYER", player.getPlayer());
            saveSharedPrefs();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void saveSharedPrefs() {
        SharedPreferences prefs = getSharedPreferences(name, 0);
        SharedPreferences.Editor edit = prefs.edit();
        Gson gson = new Gson();
        edit.putString("Inventory", gson.toJson(player.getInventory().getInventory()));
        edit.putInt("mightinit", player.mightStat.getStatIndex());
        edit.putInt("speedinit", player.speedStat.getStatIndex());
        edit.putInt("knowledgeinit", player.knowledgeStat.getStatIndex());
        edit.putInt("sanityinit", player.sanityStat.getStatIndex());
        edit.apply();
    }


    public void startNewGame(View v) {
        noPlayerToast();
    }

    //region StatStuff
    public void resetMight(View v) {
        if (player != null) {
            player.mightStat.resetStat();
            mightView.setCurrentDigit(player.mightStat);
        } else {
            noPlayerToast();
        }

    }

    public void resetSpeed(View v) {
        if (player != null) {
            player.speedStat.resetStat();
            speedView.setCurrentDigit(player.speedStat);
        } else {
            noPlayerToast();
        }
    }

    public void resetSanity(View v) {
        if (player != null) {
            player.sanityStat.resetStat();
            sanityView.setCurrentDigit(player.sanityStat);
        } else {
            noPlayerToast();
        }
    }

    public void resetKnowledge(View v) {
        if (player != null) {
            player.knowledgeStat.resetStat();
            knowledgeView.setCurrentDigit(player.knowledgeStat);
        } else {
            noPlayerToast();
        }
    }

    public void mightAttack(Items<List<Integer>> i) {
        List<Integer> diceRoll = i.useItem(this);
        setDice(diceRoll);
    }


    public void mightDefence(View v) {
        if (player != null) {
            ArrayList<Integer> diceRoll = player.doMightRoll();
            setDice(diceRoll);
        } else {
            noPlayerToast();
        }
    }

    public void speedRoll(View v) {
        if (player != null) {
            ArrayList<Integer> diceRoll = player.doSpeedRoll();
            setDice(diceRoll);
        } else {
            noPlayerToast();
        }
    }

    public void knowledgeRoll(View v) {
        if (player != null) {
            ArrayList<Integer> diceRoll = player.doKnowledgeRoll();
            setDice(diceRoll);
        } else {
            noPlayerToast();
        }

    }

    public void sanityRoll(View v) {
        if (player != null) {
            ArrayList<Integer> diceRoll = player.doSanityRoll();
            setDice(diceRoll);
        } else {
            noPlayerToast();
        }

    }

    public void hauntRoll(View v) {
        if (player != null) {
            ArrayList<Integer> diceRoll = player.doHauntRoll();
            setDice(diceRoll);
        } else {
            noPlayerToast();
        }

    }

    public void resetDropdown(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.reset_popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainPlayer.this);
                builder.setTitle("Confirm Reset");
                switch (menuItem.getItemId()) {
                    case R.id.reset_might:
                        builder.setMessage("Are you sure you want to reset your might")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        resetMight(mightView);
                                    }
                                });
                        break;
                    case R.id.reset_speed:
                        builder.setMessage("Are you sure you want to reset your speed")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        resetSpeed(speedView);
                                    }
                                });
                        break;
                    case R.id.reset_knowledge:
                        builder.setMessage("Are you sure you want to reset your knowledge")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        resetKnowledge(knowledgeView);
                                    }
                                });
                        break;
                    case R.id.reset_sanity:
                        builder.setMessage("Are you sure you want to reset your sanity")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        resetSanity(sanityView);
                                    }
                                });
                        break;
                    default:
                        return false;
                }
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        popup.show();
    }

    public void backpack(View view) {
        if (player != null) {
            player.getInventory().showInventoryView();
        } else {
            noPlayerToast();
        }
    }

    public void noPlayerToast() {
        Toast.makeText(this, R.string.no_player, Toast.LENGTH_SHORT).show();
    }


    private enum Popup {
        Attack,
        Pickup,
        Drop
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setPopup(View v, OnMenuItemClickListener listener, Popup placement) {
        PopupMenu popup = new PopupMenu(this, v);

        if (placement == Popup.Attack) {
            popup.getMenu().add(Menu.NONE, R.id.attack, Menu.NONE, R.string.attack);
        }
        for (Items item : player.getInventory()) {
            if (!item.usable) {
                popup.getMenu().add(Menu.NONE, item.getID(), Menu.NONE, item.getItemNameID());
            }
        }

        popup.setOnMenuItemClickListener(listener);
        popup.show();
    }
    //endregion

    //region Dice
    public void newRoll(View v) {
        ImageButton ib = (ImageButton) v;
        Random r = new Random();
        int i = r.nextInt(3);
        if (i == 0) {
            ib.setImageDrawable(res.getDrawable(R.drawable.dice0));
        } else if (i == 1) {
            ib.setImageDrawable(res.getDrawable(R.drawable.dice1));
        } else {
            ib.setImageDrawable(res.getDrawable(R.drawable.dice2));
        }

    }

    public void setDice(List<Integer> diceRoll) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainPlayer.this);
        @SuppressLint("InflateParams") final View view =
                getLayoutInflater().inflate(R.layout.dice, null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                unsetDice(view);
            }
        }).setTitle("Dice Roll").setView(view);
        AlertDialog dialog = builder.create();
        ImageButton dice;
        for (int i = 0; i < diceRoll.size(); i++) {
            if (diceRoll.get(i) == 0) {
                dice = (ImageButton) findDice(view, i);
                dice.setImageDrawable(res.getDrawable(R.drawable.dice0));
                dice.setVisibility(View.VISIBLE);
            } else if (diceRoll.get(i) == 1) {
                dice = (ImageButton) findDice(view, i);
                dice.setImageDrawable(res.getDrawable(R.drawable.dice1));
                dice.setVisibility(View.VISIBLE);
            } else {
                dice = (ImageButton) findDice(view, i);
                dice.setImageDrawable(res.getDrawable(R.drawable.dice2));
                dice.setVisibility(View.VISIBLE);
            }
        }
        dialog.show();
    }

    public void unsetDice(View view) {
        for (int i = 0; i < 8; i++) {
            ImageButton dice = (ImageButton) findDice(view, i);
            dice.setVisibility(View.INVISIBLE);
            dice.invalidate();
        }
    }

    public View findDice(View view, int i) {
        if (i == 0) {
            return view.findViewById(R.id.dice1);
        } else if (i == 1) {
            return view.findViewById(R.id.dice2);
        } else if (i == 2) {
            return view.findViewById(R.id.dice3);
        } else if (i == 3) {
            return view.findViewById(R.id.dice4);
        } else if (i == 4) {
            return view.findViewById(R.id.dice5);
        } else if (i == 5) {
            return view.findViewById(R.id.dice6);
        } else if (i == 6) {
            return view.findViewById(R.id.dice7);
        } else {
            return view.findViewById(R.id.dice8);
        }
    }
    //endregion

    //region Listeners

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void mightAttackBtn(View v) {
        if (player.getInventory().isEmpty()) {
            mightDefence(findViewById(R.id.main_layout));
        } else {
            setPopup(v, new AttackListener(this), Popup.Attack);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public class AttackListener implements OnMenuItemClickListener {

        MainPlayer p;

        AttackListener(MainPlayer mainPlayer) {
            this.p = mainPlayer;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.attack) {
                p.mightDefence(p.findViewById(R.id.main_layout));
                return true;
            }
            p.mightAttack(Items.getWeapon(menuItem.getItemId()));

            return true;
        }

    }
    //endregion

    //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds weapons to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.omen_roll:
                if (player != null) {
                    ArrayList<Integer> diceRoll = player.doHauntRoll();
                    setDice(diceRoll);
                } else {
                    noPlayerToast();
                }
                return true;
            case R.id.haunt_tomes:
                Intent tomes = new Intent(this, HauntTomes.class);
                startActivity(tomes);
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
}
