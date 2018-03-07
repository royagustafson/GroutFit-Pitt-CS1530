package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// IMPORTANT: since primary key is both columns of table where does @Id go?

@Entity
@Table(name="wishlist", schema="schema" )
public class Wishlist {
    private int profileId;
    private int itemId;

    public int getProfileId() { return profileId; }
    public void setProfileId(int profileId) { this.profileId = profileId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
}
