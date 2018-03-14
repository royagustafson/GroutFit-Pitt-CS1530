package com.GroutFit.Model;

import javax.persistence.*;

@Entity
@Table(name="outfit", schema="schema" )
public class Outfit {
    @Id
    private int outfitId;
    @ManyToOne
    @JoinColumn(name="creator", nullable=false)
    private Profile creator;
    private boolean fullBody;
    @ManyToOne
    @JoinColumn(name="top")
    private ClothingItem top;
    @ManyToOne
    @JoinColumn(name="bottom")
    private ClothingItem bottom;
    @ManyToOne
    @JoinColumn(name="jacket")
    private ClothingItem jacket;

    // getters and setters
    public int getOutfitId() { return outfitId; }

    public void setOutfitId(int outfitId) { this.outfitId = outfitId; }

    public Profile getCreator() { return creator; }

    public void setCreator(Profile creator) { this.creator = creator; }

    public boolean isFullBody() { return fullBody; }

    public void setFullBody(boolean fullBody) { this.fullBody = fullBody; }

    public ClothingItem getTop() { return top; }

    public void setTop(ClothingItem top) { this.top = top; }

    public ClothingItem getBottom() { return bottom; }

    public void setBottom(ClothingItem bottom) { this.bottom = bottom; }

    public ClothingItem getJacket() { return jacket; }

    public void setJacket(ClothingItem jacket) { this.jacket = jacket; }
}
