package com.GroutFit.Model;

import com.GroutFit.Helper.pHash;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
public class Profile {

    @Id
    private String email;
    private String password;
    private String size_shirt;
    private String size_pants;
    private String size_dress;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Outfit> outfits;

    @JoinTable
    @OneToMany(targetEntity = ClothingItem.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClothingItem> wishlist;

    public Profile() {
    }

    public Profile(String email) {
        this.email = email;
    }

    // getters and setters
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSize_shirt() {
        return size_shirt;
    }

    public void setSize_shirt(String sizeShirt) {
        this.size_shirt = sizeShirt;
    }

    public String getSize_pants() {
        return size_pants;
    }

    public void setSize_pants(String sizePants) {
        this.size_pants = sizePants;
    }

    public Set<ClothingItem> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<ClothingItem> wishlist) {
        this.wishlist = wishlist;
    }

    public Set<Outfit> getOutfits() {
        return outfits;
    }

    public void setOutfits(Set<Outfit> outfits) {
        this.outfits = outfits;
    }

    public String getSize_dress() {
        return size_dress;
    }

    public void setSize_dress(String sizeDress) {
        this.size_dress = sizeDress;
    }

    //TODO: this either needs to be a static method or moved to another interface/class
    public static Profile register(Map<String, String> map) {
        assert map.get("username") != null && map.get("password") != null;
        return register(map.get("username"), map.get("password"), map.get("size_shirt"), map.get("size_pant"), map.get("size_dress"));
    }

    public static Profile register(String email, String password, String sizeShirt, String sizePants, String sizeDress) {
        Profile pro = new Profile(email);
        pro.setPassword(pHash.hash(password));
        pro.setSizes(sizeShirt, sizePants, sizeDress);
        return pro;
    }

    public boolean login(String password) {
        return pHash.verify(password, this.getPassword());
    }

    public void setSizes(String size_shirt, String size_pants, String size_dress) {
        if (size_shirt != null) this.size_shirt = size_shirt;
        if (size_pants != null) this.size_pants = size_pants;
        if (size_dress != null) this.size_dress = size_dress;

    }
}
