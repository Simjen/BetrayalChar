package com.betrayal.betrayalchar;

import java.util.List;

/**
 * Created by Baljenurface on 21-01-2018.
 */

public interface Weapon extends ItemCard<List<Integer>> {

    List<Integer> useItem(MainPlayer player);
}
