package com.GroutFit.Interfaces;

import java.util.ArrayList;

public interface Fetcher {
	// returns item with given id
	CLothingItem fetchTile(String itemId);

	// returns all clothing items in a category 
	ArrayList<CLothingItem> fetchByCategory(String category);

	// fetches all ClothingItem instances of a ClothingType object
	ArrayList<CLothingItem> fetchAll(CLothingType type);

	// search for items matching a query
	ArrayList<CLothingType> search(String keyword);

	// returns all items sorted by field
	ArrayList<CLothingType> fetchSorted(String field, boolean ascending);
}