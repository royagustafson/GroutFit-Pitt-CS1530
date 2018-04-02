package com.GroutFit.Model;

public class ClothingItem {
    private int itemId;
    private String color;
    private String size;
    private int quantity;
    private boolean gender;

    private ClothingType type;

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

    public boolean getGender() { return gender; }

    public void setGender(boolean gender) { this.gender = gender; }

    public ClothingType getType() { return type; }

    public void setType(ClothingType type) { this.type = type; }
}
