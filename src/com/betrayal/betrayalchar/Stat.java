package com.betrayal.betrayalchar;

/**
 * Created by Baljenurface on 05-05-2015.
 */
public class Stat {
    private int statIndex;
    private int[] statArray;
    private int statStart;

    public Stat(int start, int[] stat){
        statIndex = start;
        statStart = start;
        statArray = stat;
    }

    public void Increase(){
          statIndex ++;
    }
    public void Increase(int inc){
        statIndex += inc;
    }

    public void Decrease(int dec){
        statIndex -= dec;
    }

    public void Decrease(){

        statIndex --;
    }

    public int getStatValue(){
        if (statIndex > 8)
        return statArray[8];
        else if (statIndex < 0)
            return statArray[0];
        else return statArray[statIndex];
    }

    public void resetStat(){
        statIndex = statStart;
    }
    public int nextStat(){
        return 0;
    }

    public int prevStat(){
        return 0;
    }

}

