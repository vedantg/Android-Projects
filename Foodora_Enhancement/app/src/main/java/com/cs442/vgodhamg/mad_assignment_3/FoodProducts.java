package com.cs442.vgodhamg.mad_assignment_3;

import java.io.Serializable;

/**
 * Created by vedant on 19-02-2016.
 */
public class FoodProducts implements Serializable{

    String foodName;
    int foodPrice;
    int foodImage;
    //int foodQty;
    boolean foodCheckBox;


    FoodProducts(String _describe, int _price, int _image, boolean _box) {
        foodName = _describe;
        foodPrice = _price;
        foodImage = _image;
        //   foodQty = _qty;
        foodCheckBox = _box;
    }
}
