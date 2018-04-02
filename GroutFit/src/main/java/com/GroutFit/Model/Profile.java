package com.GroutFit.Model;

import com.GroutFit.Interfaces.ProfileInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Profile implements ProfileInterface {
    private String email;
    private String password;
    private String sizeShirt;
    private String sizePants;
    private String sizeDress;

   private Set outfits = new HashSet();

   private Set wishlist = new HashSet();

   public Profile() {}
   public Profile(String username, String password) {
       this.email = username;
       this.password = password;
   }

    // getters and setters
    public String getEmail() { return this.email; }

    private void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getSizeShirt() { return sizeShirt; }

    private void setSizeShirt(String sizeShirt) { this.sizeShirt = sizeShirt; }

    public String getSizePants() { return sizePants; }

    private void setSizePants(String sizePants) { this.sizePants = sizePants; }

    public ArrayList<ClothingItem> getWishlist() { return wishlist; }

    public void setWishlist(ArrayList<ClothingItem> wishlist) { this.wishlist = wishlist; }

    public ArrayList<Outfit> getOutfits() { return outfits; }

    public void setOutfits(ArrayList<Outfit> outfits) { this.outfits = outfits; }

    public String getSizeDress() { return sizeDress; }

    public void setSizeDress(String sizeDress) { this.sizeDress = sizeDress; }

    //TODO: this either needs to be a static method or moved to another interface/class
    public void register(String username, String password) {

    }

    public boolean login(String username, String password) {
        return this.email.equals(username) && this.password.equals(password);
    }

    public void saveSizes(String shirtSize, String pantSize, String dressSize) {
        if(shirtSize != null) this.setSizeShirt(shirtSize);
        if(pantSize != null)  this.setSizePants(pantSize);
        if(dressSize != null) this.setSizeDress(dressSize);
    }
}
