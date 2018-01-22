package com.betrayal.betrayalchar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baljenurface on 03-09-2017.
 */

public class MultiPlayerBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager manager;
    private final Activity activity;
    private final WifiP2pManager.Channel channel;
    private List<WifiP2pDevice> devicesList = new ArrayList<>();
    private List<String> connectedAddresses = new ArrayList<>();
    private WifiP2pManager.PeerListListener peerListListener;


    public List<WifiP2pDevice> getDevicesList() {
        return devicesList;
    }

    public MultiPlayerBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                        Activity activity,
                                        WifiP2pManager.PeerListListener peerListListener) {
        this.manager = manager;
        this.activity = activity;
        this.channel = channel;
        this.peerListListener = peerListListener;
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i) {

            }
        });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Determine if Wifi P2P mode is enabled or not, alert
            // the Activity.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                //activity.setIsWifiP2pEnabled(true);
            } else {
                //activity.setIsWifiP2pEnabled(false);
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            if (manager != null) {
                manager.requestPeers(channel, peerListListener);
            }
            // The peer list has changed!  We should probably do something about
            // that.

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {


            // Connection state changed!  We should probably do something about
            // that.

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            /*
            DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()
                    .findFragmentById(R.id.frag_list);
            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));
            */
        }
    }

    public void connect(WifiP2pDevice device) {
        // Picking the first device found on the network.
        System.out.println(device.deviceAddress);
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        connectedAddresses.add(device.deviceAddress);
        config.wps.setup = WpsInfo.PBC;

        manager.connect(channel, config, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(activity, "Connect failed. Retry.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
