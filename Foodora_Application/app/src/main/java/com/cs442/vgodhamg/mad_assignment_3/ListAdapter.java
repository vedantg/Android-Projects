package com.cs442.vgodhamg.mad_assignment_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by vedant on 19-02-2016.
 */
public class ListAdapter extends BaseAdapter {

    Context ctx;
    CheckBox cbBuy;
    LayoutInflater lInflater;
    ArrayList<FoodProducts> objects;

    ListAdapter(Context context, ArrayList<FoodProducts> products) {
        this.ctx = context;
        //  this.context=context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.itemslayout, parent, false);
        }

        FoodProducts p = getFoodProducts(position);


        ((TextView) view.findViewById(R.id.textView1)).setText(p.foodName);

        ((TextView) view.findViewById(R.id.textView2)).setText("$"+p.foodPrice+ "");



        // ((EditText) view.findViewById(R.id.editText1)).setText(p.foodQty);

        ((ImageView) view.findViewById(R.id.imageView1)).setImageResource(p.foodImage);

         cbBuy = (CheckBox) view.findViewById(R.id.checkBox1);

        cbBuy.setOnCheckedChangeListener(myCheckChangList);

        cbBuy.setTag(position);
        cbBuy.setChecked(p.foodCheckBox);

       return view;


    }



    FoodProducts getFoodProducts(int position) {

        return ((FoodProducts) getItem(position));
    }



    ArrayList<FoodProducts> getBox() {

        ArrayList<FoodProducts> box = new ArrayList<FoodProducts>();
        for (FoodProducts p : objects) {
            if (p.foodCheckBox)
                box.add(p);

        }
       // cbBuy.toggle();
        return box;
    }


    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getFoodProducts((Integer) buttonView.getTag()).foodCheckBox = isChecked;

        }
    };


}
