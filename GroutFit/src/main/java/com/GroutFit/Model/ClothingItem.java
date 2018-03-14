package com.GroutFit.Model;

import javax.persistence.*;


// TODO fix schema name in all

@Entity
@Table(name="clothing_item", schema="schema")
public class ClothingItem {

    @Id
    private int itemId;
    private String color;
    private String size;
    private int quantity;
    private String gender;
    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
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

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public ClothingType getType() { return type; }

    public void setType(ClothingType type) { this.type = type; }
}
