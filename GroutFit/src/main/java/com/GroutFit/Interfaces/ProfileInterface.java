package com.GroutFit.Model;

public interface ProfileInterface {
   void register(String username, String password);
   boolean login(String username, String password);
   void saveSizes(String shirtSize, String pantSize, String dressSize);
}
