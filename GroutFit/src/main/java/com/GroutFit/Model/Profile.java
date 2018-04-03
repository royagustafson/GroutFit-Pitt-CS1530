package com.GroutFit.Model;

import com.GroutFit.Interfaces.ProfileInterface;
import com.GroutFit.Helper.pHash;

import java.util.Set;

public class Profile implements ProfileInterface {
    private String email;
    private String password;
    private String sizeShirt;
    private String sizePants;
    private String sizeDress;

   private Set<Outfit> outfits;
   private Set<ClothingItem> wishlist;

    // getters and setters
    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = pHash.hash(password); }

    public String getSizeShirt() { return sizeShirt; }

    public void setSizeShirt(String sizeShirt) { this.sizeShirt = sizeShirt; }

    public String getSizePants() { return sizePants; }

    public void setSizePants(String sizePants) { this.sizePants = sizePants; }

    public void setWishlist(Set<ClothingItem> wishlist) { this.wishlist = wishlist; }

    public Set<ClothingItem> getWishlist() { return wishlist; }

    public Set<Outfit> getOutfits() { return outfits; }

    public void setOutfits(Set<Outfit> outfits) { this.outfits = outfits; }

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
