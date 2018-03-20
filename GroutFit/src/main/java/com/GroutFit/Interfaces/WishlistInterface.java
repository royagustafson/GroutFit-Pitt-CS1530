package com.GroutFit.Interfaces;

import com.GroutFit.Model.ClothingItem;

public interface WishlistInterface {
    // add item to wishlist
    void add(ClothingItem item);

    // remove item from wishlist
    void remove(ClothingItem item);
}
