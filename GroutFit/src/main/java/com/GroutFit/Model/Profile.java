package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile", schema="schema")
public class Profile {
    private int profile_id;
    private String email;
    private String password;
    private String sizeShirt;
    private String sizePants;
    private String sizeShoes;

    @Id
    public int getId() { return profile_id; }
    public void setId(int Id) { profile_id = Id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSizeShirt() { return sizeShirt; }
    public void setSizeShirt(String sizeShirt) { this.sizeShirt = sizeShirt; }

    public String getSizePants() { return sizePants; }
    public void setSizePants(String sizePants) { this.sizePants = sizePants; }

    public String getSizeShoes() { return sizeShoes; }
    public void setSizeShoes(String sizeShoes) { this.sizeShoes = sizeShoes; }
}
