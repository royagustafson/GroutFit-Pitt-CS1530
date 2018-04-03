package com.GroutFit.Model;

public class ClothingItem {
    private int itemId;
    private String color;
    private String size;
    private int quantity;
    private Boolean gender;

    private ClothingType type;
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Boolean getGender() { return gender; }

    public void setGender(Boolean gender) { this.gender = gender; }

    public ClothingType getType() { return type; }

    public void setType(ClothingType type) { this.type = type; }

    @Override
    public String toString() {
        return "{" +
                "itemId=" + itemId +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", gender=" + gender +
                ", type=" + type.getTypeId() +
                "}";
    }
}
