package com.betrayal.betrayalchar;

import java.io.Serializable;

/**
 * Created by Baljenurface on 05-05-2015.
 */
public class Stat implements StatI{

    public int[] getStatArray() {
        return statArray;
    }

    public int getStatStart() {
        return statStart;
    }

    private int statIndex;
    private int[] statArray;
    private int statStart;

    public Stat(int start, int[] stat){
        statIndex = start;
        statStart = start;
        statArray = stat;
    }

    @Override
    public void Increase(){
          statIndex ++;
    }
    @Override
    public void Increase(int inc){
        statIndex += inc;
    }

    @Override
    public void Decrease(int dec){
        statIndex -= dec;
    }

    @Override
    public void Decrease(){

        statIndex --;
    }

    @Override
    public int getStatValue(){
        if (statIndex > 8)
        return statArray[8];
        else if (statIndex < 0)
            return statArray[0];
        else return statArray[statIndex];
    }

    @Override
    public void resetStat(){
        statIndex = statStart;
    }
    @Override
    public Stat nextStat(){
        return new Stat(statIndex + 1, statArray);
    }

    @Override
    public Stat prevStat(){
        return new Stat(statIndex - 1, statArray);
    }

    public int getStatIndex(){ return statIndex;}

    public void setStatIndex(int index){ statIndex = index;}

    @Override
    public String toString() {
        return Integer.toString(getStatValue());
    }
}

