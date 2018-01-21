package com.betrayal.betrayalchar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baljenurface on 21-01-2018.
 */

public class WeaponImpl implements Weapon {
    private int[] useRoll;
    private String weaponLoss = "";

    public WeaponImpl(String weaponLoss, int... useRoll) {
        this.useRoll = useRoll;
        this.weaponLoss = weaponLoss;
    }

    public WeaponImpl(int... useRoll) {
        this.useRoll = useRoll;
    }

    @Override
    public List<Integer> useItem(MainPlayer mainPlayer) {
        List<Integer> roll = new ArrayList<>();
        roll.addAll(Player.doRoll(useRoll[0] + mainPlayer.player.getMight()));


        switch (weaponLoss) {
            case "might":
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.mightStat.Decrease();
                    mainPlayer.mightView.setCurrentDigit(mainPlayer.player.mightStat);
                }
                break;
            case "speed":
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.speedStat.Decrease();
                    mainPlayer.speedView.setCurrentDigit(mainPlayer.player.speedStat);
                }
                break;
            case "sanity":

                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.sanityStat.Decrease();
                    mainPlayer.sanityView.setCurrentDigit(mainPlayer.player.sanityStat
                    );
                }
                break;
            case "knowledge":
                for (int i = 0; i < useRoll[1]; i++) {
                    mainPlayer.player.knowledgeStat.Decrease();
                    mainPlayer.knowledgeView.setCurrentDigit(mainPlayer.player.knowledgeStat);
                }
                break;
        }
        return roll;
    }
}
