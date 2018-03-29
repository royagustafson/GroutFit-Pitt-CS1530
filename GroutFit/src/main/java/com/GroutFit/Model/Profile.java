package com.GroutFit.Model;

import com.GroutFit.Interfaces.ProfileInterface;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="profile", schema="schema")
public class Profile implements ProfileInterface {
    @Id
    private int profile_id;
    private String username;
    private String password;
    private String sizeShirt;
    private String sizePants;
    @OneToMany
    private ArrayList<Outfit> outfits;

    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(
            name = "profile_id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "item_id"
        )
    )
    @OneToMany
    private ArrayList<ClothingItem> wishlist;

    // getters and setters
    public int getId() { return profile_id; }

    public void setId(int Id) { profile_id = Id; }

    public String getUsername() { return this.username; }

    private void setUsername(String username) { this.username = username; }

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

    //TODO: this either needs to be a static method or moved to another interface/class
    public void register(String username, String password) {

    }

    public boolean login(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password))
            return true;
        return false;
    }

    //TODO: make dress size field and implement it here
    public void saveSizes(String shirtSize, String pantSize, String dressSize) {
        if(shirtSize != null) this.setSizeShirt(shirtSize);
        if(pantSize != null)  this.setSizePants(pantSize);
        if(dressSize != null) ;
    }
}
