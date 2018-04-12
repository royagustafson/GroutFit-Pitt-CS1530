package com.GroutFit.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Map;
import java.util.Random;

@Entity
public class Outfit {

    @Id
    private int outfit_id;
    private boolean full_body;

    @ManyToOne(optional = false)
    @JoinColumn(name = "top")
    private ClothingItem top;

    @ManyToOne
    @JoinColumn(name = "bottom")
    private ClothingItem bottom;

    @ManyToOne
    @JoinColumn(name = "jacket")
    private ClothingItem jacket;

    @ManyToOne
    @JoinColumn(name = "creator")
    private Profile profile;

    public Outfit() {
    }

    // getters and setters
    public int getOutfit_id() {
        return outfit_id;
    }

    public void setOutfit_id(int outfitId) {
        this.outfit_id = outfitId;
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
    private void add(ClothingItem item) {
        if (item == null) return;
        switch (item.getType().getCategory()) {
            case "T - Shirt":
            case "Shirt":
            case "Dress":
                this.setTop(item);
                break;
            case "Sweatshirt":
            case "Jacket":
                this.setJacket(item);
                break;
            case "Jeans":
            case "Pants":
            case "Sweatpants":
            case "Skirt":
                this.setBottom(item);
                break;
        }
    }

    public static Outfit build(Session session, Map<String, String> params) {
        Outfit out = new Outfit();
        do {
            out.setOutfit_id(new Random().nextInt((899999999) + 100000000));
        } while (session.get(Outfit.class, out.outfit_id) != null);
        out.setProfile(session.get(Profile.class, params.get("username")));
        out.setFull_body(Boolean.parseBoolean(params.get("full_body")));
        out.add(session.get(ClothingItem.class,
                Integer.parseInt(params.get("top_id"))));
        if (!out.full_body)
            out.add(session.get(ClothingItem.class,
                    Integer.parseInt(params.get("bottom_id"))));
        out.add(session.get(ClothingItem.class,
                Integer.parseInt(params.get("jacket_id"))));
        return out;
    }

    public JsonObject toJson() {
        return new Gson().fromJson(toString(), JsonObject.class);
    }

    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\"outfit_id\": \"%s\", " +
                        "\"full_body\": %b, " +
                        "\"top\": %s, " +
                        "\"bottom\": %s, " +
                        "\"jacket\": %s, " +
                        "\"profile_id\": \"%s\"",
                outfit_id,
                full_body,
                (top == null) ? "null" : getTop().toString(),
                (bottom == null) ? "null" : getBottom().toString(),
                (jacket == null) ? "null" : getTop().toString(),
                getProfile().getEmail()
        );
    }
}