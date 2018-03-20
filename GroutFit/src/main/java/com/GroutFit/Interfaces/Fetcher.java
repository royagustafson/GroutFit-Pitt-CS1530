package com.GroutFit.Interfaces;

import com.GroutFit.Model.ClothingItem;

import java.util.ArrayList;

public interface Fetcher {
	// returns item with given id
	ClothingItem fetchTile(String itemId);

	// returns all clothing items in a category 
	ArrayList<ClothingItem> fetchByCategory(String category);

	// fetches all ClothingItem instances of a ClothingType object
	ArrayList<ClothingItem> fetchAll(ClothingItem type);

	// search for items matching a query
	ArrayList<ClothingItem> search(String keyword);

	// returns all items sorted by field
	ArrayList<ClothingItem> fetchSorted(String field, boolean ascending);
}