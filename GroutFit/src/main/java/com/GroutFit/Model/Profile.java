package com.GroutFit.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="profile", schema="schema")
public class Profile {
    @Id
    private int profile_id;
    private String email;
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getSizeShirt() { return sizeShirt; }

    public void setSizeShirt(String sizeShirt) { this.sizeShirt = sizeShirt; }

    public String getSizePants() { return sizePants; }

    public void setSizePants(String sizePants) { this.sizePants = sizePants; }

    public ArrayList<ClothingItem> getWishlist() { return wishlist; }

    public void setWishlist(ArrayList<ClothingItem> wishlist) { this.wishlist = wishlist; }

    public ArrayList<Outfit> getOutfits() { return outfits; }

    public void setOutfits(ArrayList<Outfit> outfits) { this.outfits = outfits; }
}
