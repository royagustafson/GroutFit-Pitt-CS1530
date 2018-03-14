package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO fix schema name in all

@Entity
@Table(name="clothing_item", schema="schema")
public class ClothingItem {

    private int itemId;
    private int typeId;
    private String color;
    private String size;
    private int quantity;
    private String gender;

    @Id
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int id) {
        this.itemId = id;
    }

    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
