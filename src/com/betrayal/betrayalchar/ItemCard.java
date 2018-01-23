package com.betrayal.betrayalchar;

import java.io.Serializable;

/**
 * Created by Baljenurface on 21-01-2018.
 */

public interface ItemCard<T> extends Serializable{
    T useItem(MainPlayer mainPlayer);
}
