package com.cs442.vgodhamg.mad_assignment_3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FoodOrder extends AppCompatActivity {
    ArrayList<FoodProducts> products = new ArrayList<FoodProducts>();
    ListAdapter boxAdapter;
    final Context context = this;
    ListView lvMain;
    int totalAmount;
    CheckBox checkBox;
    EditText return_total;
    String message, str;
    int c, new_message = 0;
    String s;
    Button btn_reset, btn_place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);


        fillData();
        boxAdapter = new ListAdapter(this, products);



        btn_reset = (Button) findViewById(R.id.button4);
        btn_reset.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);

        btn_place = (Button) findViewById(R.id.button3);
        btn_place.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);

        return_total = (EditText) findViewById(R.id.editText);
        lvMain = (ListView) findViewById(R.id.listView1);


        lvMain.setAdapter(boxAdapter);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("Do You Want To Reset??");

                alertDialogBuilder
                        .setMessage("Click Yes to Reset!!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                // MainActivity.this.finish();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
             /*   Intent intent = getIntent();
                finish();
                startActivity(intent); */

            }
        });

        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String result = "Selected Food Items Are :";
                totalAmount = 0;
                // totalAmount=foo;
                for (FoodProducts p : boxAdapter.getBox()) {
                    if (p.foodCheckBox) {
                        result += "\n" + p.foodName;
                        totalAmount = totalAmount + (p.foodPrice);
                        //  p.foodCheckBox=UnCheck();
                    }

                }


                Intent i = new Intent(getApplicationContext(), DisplayItems.class);

                i.putExtra("product", result);
                i.putExtra("result", totalAmount);
                startActivityForResult(i, 1);
            }
        });

    }


    void fillData() {

        products.add(new FoodProducts("Pizza ", 10, R.drawable.hotpizza, false));
        products.add(new FoodProducts("Burrito ", 10, R.drawable.burrito, false));
        products.add(new FoodProducts("Chhole ", 10, R.drawable.chhole, false));
        products.add(new FoodProducts("HamBurger ", 10, R.drawable.hamburger, false));
        products.add(new FoodProducts("Sanwich ", 10, R.drawable.sandwich, false));
        products.add(new FoodProducts("IceCream ", 10, R.drawable.icecream, false));
        products.add(new FoodProducts("MilkShake ", 10, R.drawable.milkshake, false));
        products.add(new FoodProducts("SoftDrinks ", 10, R.drawable.colddrink, false));
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                message = data.getStringExtra("MESSAGE");
                c = Integer.parseInt(message);
                new_message = new_message + c;
                s = Integer.toString(new_message);
                return_total.setText(s);
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                // int s = Integer.toString(foo);
                // foo = Integer.parseInt("message");

                return_total.setText(s);
                //    foo = Integer.parseInt("message");

            }

        }
    }


}