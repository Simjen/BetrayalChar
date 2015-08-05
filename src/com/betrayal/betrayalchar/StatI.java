package com.betrayal.betrayalchar;

/**
 * Created by Baljenurface on 08-05-2015.
 */
public interface StatI {

    void Increase();

    void Increase(int inc);

    void Decrease(int dec);

    void Decrease();

    int getStatValue();

    void resetStat();

    /**
     *
     * @return Next Stat
     */
    Stat nextStat();

    /**
     *
     * @return Previuos Stat
     */
    Stat prevStat();
}
