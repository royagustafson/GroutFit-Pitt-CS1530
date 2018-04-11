package com.GroutFit.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.persistence.*;

@Entity
@Table(name = "clothing_item")
public class ClothingItem {

    @Id
    private int item_id;
    private String color;
    private String size;
    private int quantity;
    private Boolean gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id")
    private ClothingType type;

    // getters and setters
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int id) {
        this.item_id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }

    public JsonObject toJsonWithTypeID() {
        return new Gson().fromJson(String.format(
                "{" +
                        "\"item_id\": %d, " +
                        "\"type_id\": %d, " +
                        "\"color\": \"%s\", " +
                        "\"size\": \"%s\", " +
                        "\"quantity\": %d, " +
                        "\"gender\": \"%s\"" +
                        "}",
                item_id,
                getType().getType_id(),
                color,
                size,
                quantity,
                gender
        ), JsonObject.class);
    }
    public JsonObject toJson() {
        return new Gson().fromJson(toString(), JsonObject.class);
    }

    public JsonObject typeToJson() {
        return getType().toJson();
    }

    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\"item_id\": %d, " +
                        "\"color\": \"%s\", " +
                        "\"size\": \"%s\", " +
                        "\"quantity\": %d, " +
                        "\"gender\": \"%s\"" +
                        "}",
                item_id,
                color,
                size,
                quantity,
                gender
        );
    }
}
