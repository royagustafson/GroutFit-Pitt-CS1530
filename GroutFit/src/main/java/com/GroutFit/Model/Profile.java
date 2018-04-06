package com.GroutFit.Model;

import com.GroutFit.Helper.pHash;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;

public class Profile {

    @Id
    private String email;
    private String password;
    private String size_shirt;
    private String size_pants;
    private String size_dress;

    @OneToMany(mappedBy="profile")
    private List<Outfit> outfits;

    @JoinTable
    @OneToMany
    private List<ClothingItem> wishlist;

    public Profile() {
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

    public List<ClothingItem> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<ClothingItem> wishlist) {
        this.wishlist = wishlist;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<Outfit> outfits) {
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
        Profile pro = new Profile();
        pro.setEmail(email);
        pro.setPassword(pHash.hash(password));
        if (sizeShirt != null) pro.setSize_shirt(sizeShirt);
        if (sizePants != null) pro.setSize_pants(sizePants);
        if (sizeDress != null) pro.setSize_dress(sizeDress);
        return pro;
    }

    public boolean login(String password) {
        return pHash.verify(password, this.getPassword());
    }

}
