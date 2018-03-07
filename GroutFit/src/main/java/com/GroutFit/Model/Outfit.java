package com.GroutFit.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="outfit", schema="schema" )
public class Outfit {
    private int outfitId;
    private int creator;
    private boolean fullBody;
    private int top;
    private int bottom;
    private int jacket;
    private int shoes;
    private int acc1;
    private int acc2;
    private int acc3;

    @Id
    public int getOutfitId() { return outfitId; }
    public void setOutfitId(int outfitId) { this.outfitId = outfitId; }

    public int getCreator() { return creator; }
    public void setCreator(int creator) { this.creator = creator; }

    public boolean isFullBody() { return fullBody; }
    public void setFullBody(boolean fullBody) { this.fullBody = fullBody; }

    public int getTop() { return top; }
    public void setTop(int top) { this.top = top; }

    public int getBottom() { return bottom; }
    public void setBottom(int bottom) { this.bottom = bottom; }

    public int getJacket() { return jacket; }
    public void setJacket(int jacket) { this.jacket = jacket; }

    public int getShoes() { return shoes; }
    public void setShoes(int shoes) { this.shoes = shoes; }

    public int getAcc1() { return acc1; }
    public void setAcc1(int acc1) { this.acc1 = acc1; }

    public int getAcc2() { return acc2; }
    public void setAcc2(int acc2) { this.acc2 = acc2; }

    public int getAcc3() { return acc3; }
    public void setAcc3(int acc3) { this.acc3 = acc3; }
}
