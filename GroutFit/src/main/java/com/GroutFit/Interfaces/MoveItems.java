package com.GroutFit.Interfaces;

import com.GroutFit.Model.ClothingItem;
import com.GroutFit.Model.Outfit;

import java.util.ArrayList;

public interface MoveItems {
	// saves outfit with provided items (may use null if item is not included)
	void createOutfit(ClothingItem shirt, ClothingItem pants, ClothingItem jacket);

	// posts outfit to feed
	void postOutfit(Outfit outfit);

	// moves items from cart to wishlist
	void moveFromCartToWishlist(ArrayList<ClothingItem> items);

	// moves items from wishlist to cart
	void moveFromWishlistToCart(ArrayList<ClothingItem> items);

	// adds outfit to user's wishlist
	void addOutfitToWishlist(Outfit outfit);

	// adds outfit to cart
	void addOutfitToCart(Outfit outfit);

	// begin checkout with items in cart
	void proceedToCheckout(ArrayList<ClothingItem> cart);
}