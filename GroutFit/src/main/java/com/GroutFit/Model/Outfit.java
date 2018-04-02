package com.GroutFit.Model;

import javax.persistence.*;

import com.GroutFit.Interfaces.OutfitInterface;

public class Outfit implements OutfitInterface {
    private int outfitId;
    private Profile creator;
    private boolean fullBody;
    private ClothingItem top;
    private ClothingItem bottom;
    private ClothingItem jacket;

    // getters and setters
    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public boolean isFullBody() {
        return fullBody;
    }

    public void setFullBody(boolean fullBody) {
        this.fullBody = fullBody;
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

    //TODO: currently works with "shirt", "jacket", "pants"
    public void add(ClothingItem item) {
        String type = item.getType().getArticle();
        if (type.equals("shirt")) this.setTop(item);
        else if (type.equals("jacket")) this.setJacket(item);
        else if (type.equals("pants")) this.setBottom(item);
    }

    public void remove(String article) {
        if (article.equals("top")) this.setTop(null);
        else if (article.equals("bottom")) this.setBottom(null);
        else if (article.equals("jacket")) this.setJacket(null);
    }
}
