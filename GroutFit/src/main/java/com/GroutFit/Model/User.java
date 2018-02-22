package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO fix schema name, attributes

@Entity
@Table(name="user", schema="schema")
public class User {

    int id;
    String name;
    String email;
    String password;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // TODO hash password correctly
        this.password = password;
    }
}

