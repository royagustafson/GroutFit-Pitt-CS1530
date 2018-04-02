package com.GroutFit.Model;

// IMPORTANT: since primary key is both columns of table where does @Id go?

public class Wishlist {

    private int profileId;
    private int itemId;

    // getters and setters
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
