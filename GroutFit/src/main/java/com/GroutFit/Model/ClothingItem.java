package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO fix schema name, attributes

@Entity
@Table(name="ClothingItem", schema="schema")
public class ClothingItem {

    int id;
    int typeID;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
}

