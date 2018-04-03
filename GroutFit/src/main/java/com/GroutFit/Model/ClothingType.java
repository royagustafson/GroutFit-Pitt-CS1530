package com.GroutFit.Model;

import java.util.List;

public class ClothingType {
    private int typeId;
    private String name;
    private String article;
    private double price;
    private String description;

    private List<ClothingItem> items;

    // getters and setters
    public int getTypeId() { return typeId; }

    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getArticle() { return article; }

    public void setArticle(String article) { this.article = article; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<ClothingItem> getItems() { return items; }

    public void setItems(List<ClothingItem> items) { this.items = items; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "{" +
                "'typeId'=" + typeId +
                ", 'name'='" + name + '\'' +
                ", 'article'='" + article + '\'' +
                ", 'price'=" + price +
                ", 'description'='" + description + '\'' +
                ", 'items'=" + items +
                '}';
    }
}