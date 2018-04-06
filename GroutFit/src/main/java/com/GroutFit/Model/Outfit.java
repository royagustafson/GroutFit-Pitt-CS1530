package com.GroutFit.Model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Outfit {

    @Id
    private int outfit_id;
    private Profile creator;
    private boolean full_body;
    private ClothingItem top;
    private ClothingItem bottom;
    private ClothingItem jacket;

    @ManyToOne
    @JoinColumn(name = "profile")
    private Profile profile;

    // getters and setters
    public int getOutfit_id() {
        return outfit_id;
    }

    public void setOutfit_id(int outfitId) {
        this.outfit_id = outfitId;
    }

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public boolean isFull_body() {
        return full_body;
    }

    public void setFull_body(boolean fullBody) {
        this.full_body = fullBody;
    }

    public ClothingItem getTop() {
        return top;
    }

    private void setTop(ClothingItem top) {
        this.top = top;
    }

    public ClothingItem getBottom() {
        return bottom;
    }

    private void setBottom(ClothingItem bottom) {
        this.bottom = bottom;
    }

    public ClothingItem getJacket() {
        return jacket;
    }

    private void setJacket(ClothingItem jacket) {
        this.jacket = jacket;
    }

    public Profile getProfile() {
        return profile;
    }

    private void setProfile(Profile profile) {
        this.profile = profile;
    }

    //TODO: currently works with "shirt", "jacket", "pants"
    public void add(ClothingItem item) {
        String type = item.getType().getCategory();
        if (type.equals("shirt")) this.setTop(item);
        else if (type.equals("jacket")) this.setJacket(item);
        else if (type.equals("pants")) this.setBottom(item);
    }

}