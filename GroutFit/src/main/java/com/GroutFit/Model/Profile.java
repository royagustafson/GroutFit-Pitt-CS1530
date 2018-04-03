package com.GroutFit.Model;

import com.GroutFit.Interfaces.ProfileInterface;

import java.util.List;

public class Profile implements ProfileInterface {
    private String email;
    private String password;
    private String sizeShirt;
    private String sizePants;
    private String sizeDress;

   private List<Outfit> outfits;
   private List<Wishlist> wishlist;

    // getters and setters
    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getSizeShirt() { return sizeShirt; }

    public void setSizeShirt(String sizeShirt) { this.sizeShirt = sizeShirt; }

    public String getSizePants() { return sizePants; }

    public void setSizePants(String sizePants) { this.sizePants = sizePants; }

    public List<Wishlist> getWishlist() { return wishlist; }

    public void setWishlist(List<Wishlist> wishlist) { this.wishlist = wishlist; }

    public List<Outfit> getOutfits() { return outfits; }

    public void setOutfits(List<Outfit> outfits) { this.outfits = outfits; }

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
