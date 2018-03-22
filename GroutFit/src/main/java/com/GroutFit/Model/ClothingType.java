package com.GroutFit.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="clothing_type", schema="schema" )
public class ClothingType {
    @Id
    private int typeId;
    private String article;
    private double price;
    private String description;
    @OneToMany(mappedBy = "clothing_type")
    private ArrayList<ClothingItem> items;

    // getters and setters
    public int getTypeId() { return typeId; }

    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getArticle() { return article; }

    public void setArticle(String article) { this.article = article; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public ArrayList<ClothingItem> getItems() { return items; }

    public void setItems(ArrayList<ClothingItem> items) { this.items = items; }
}
