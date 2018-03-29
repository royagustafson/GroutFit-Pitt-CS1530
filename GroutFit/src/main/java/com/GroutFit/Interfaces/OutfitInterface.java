package com.GroutFit.Interfaces;

import com.GroutFit.Model.ClothingItem;

public interface OutfitInterface {
    // add item to wishlist
    void add(ClothingItem item);

    // remove Item from wishlist
    void remove(String article);
}
