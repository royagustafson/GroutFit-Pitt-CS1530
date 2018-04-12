package com.GroutFit.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Indexed
@Table(name = "clothing_type")
public class ClothingType {

    @Id
    private int type_id;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String name;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String category;
    private double price;
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String description;

    @OneToMany(mappedBy = "type")
    private List<ClothingItem> items;

    // getters and setters
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int typeId) {
        this.type_id = typeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String article) {
        this.category = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ClothingItem> getItems() {
        return items;
    }

    public void setItems(List<ClothingItem> items) {
        this.items = items;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject toJson() {
        return new Gson().fromJson(toString(), JsonObject.class);
    }

    public List<JsonObject> itemsToJson() {
        return getItems().stream()
                .map(ClothingItem::toJson)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\"type_id\": %d, " +
                        "\"name\": \"%s\", " +
                        "\"article\": \"%s\", " +
                        "\"price\": %f, " +
                        "\"description\": \"%s\"" +
                        "}",
                type_id,
                name,
                category,
                price,
                description
        );
    }
}