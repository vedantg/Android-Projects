package com.cs442.vgodhamg.foodmenuassignments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by vedant on 10-02-2016.
 */
public class FoodDetail extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(R.layout.foodetail);

        TextView txtProduct = (TextView) findViewById(R.id.product_label);
        ImageView v = (ImageView) findViewById(R.id.imageView);
        Intent i = getIntent();
        Bundle bundle=this.getIntent().getExtras();
        int pic=bundle.getInt("image");
        v.setImageResource(pic);
        // getting attached intent data
        String product = i.getStringExtra("product");

        // displaying selected product name
        txtProduct.setText(product);

    }

    public void finishDialog(View v){

        FoodDetail.this.finish();
    }
}





