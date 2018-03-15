package com.GroutFit.Interfaces;

public interface ProfileInterface {
   // creates user profile with given username and password
   void register(String username, String password);

   // returns true if login info is valid
   boolean login(String username, String password);

   // saves sizes to profile (unused fields can be null)
   void saveSizes(String shirtSize, String pantSize, String dressSize);
}
