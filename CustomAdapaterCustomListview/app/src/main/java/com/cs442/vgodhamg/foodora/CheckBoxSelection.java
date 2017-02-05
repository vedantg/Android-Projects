package com.cs442.vgodhamg.foodora;

/**
 * Created by vedant on 16-02-2016.
 */
public class CheckBoxSelection {

    private String name;
    private String price;
    private boolean selected;

    public CheckBoxSelection(String name,String price) {
        this.name = name;
        this.price= price;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
