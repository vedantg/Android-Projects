package com.cs442.vgodhamg.foodmenuassignments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class FoodMenu extends AppCompatActivity {

    public ArrayList<String> al;
    public String str1, str2, str3, str4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        al = new ArrayList<String>();
        final ListView l1 = (ListView) findViewById(R.id.listview);



        al.add("HamBurger $50");
        al.add("French Fries $20");
        al.add("Chhoole $40");
        al.add("Burrito $30");
        al.add("Sandwich $10");
        al.add("Milk Shake $20");
        al.add("Ice Cream $20");
        al.add("Soft Drinks $5");


        if (savedInstanceState!=null){
            str1=savedInstanceState.getString("ved");
            str2=savedInstanceState.getString("god");
            al.add(str1+str2);
        }



        final EditText edit = (EditText) findViewById(R.id.editText);
        final EditText edit1 = (EditText) findViewById(R.id.editText2);
        final EditText edit2 = (EditText) findViewById(R.id.editText3);
        final EditText edit3 = (EditText) findViewById(R.id.editText4);

        edit.setTextColor(Color.parseColor("#ffffff"));
        edit1.setTextColor(Color.parseColor("#ffffff"));
        edit2.setTextColor(Color.parseColor("#ffffff"));
        edit3.setTextColor(Color.parseColor("#ffffff"));

        edit.setHintTextColor(Color.parseColor("#ffffff"));
        edit1.setHintTextColor(Color.parseColor("#ffffff"));
        edit2.setHintTextColor(Color.parseColor("#ffffff"));
        edit3.setHintTextColor(Color.parseColor("#ffffff"));

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        final  ArrayAdapter<String> adap = new ArrayAdapter<String>(this, R.layout.custometextview, al);
        l1.setBackgroundResource(R.drawable.customshape);

        l1.setAdapter(adap);



        final Button buttonAdd = (Button) findViewById(R.id.addButton);
        buttonAdd.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                str1 = edit.getText().toString();
                str2 = edit1.getText().toString();
                str3 = edit2.getText().toString();
                str4 = edit3.getText().toString();
                al.add(str1 + str2);

                edit.setText("");
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");


            }
        });


        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String itemsChoice = ((TextView) view).getText().toString();


                String s1 = "HamBurger $50";
                String s2 = "French Fries $20";
                String s3 = "Chhoole $40";
                String s4 = "Burrito $30";
                String s5 = "Sandwich $10";
                String s6 = "Milk Shake $20";
                String s7 = "Ice Cream $20";
                String s8 = "Soft Drinks $5";
                String s9 = itemsChoice;

                if (itemsChoice.equals(s1)) {
                    String x = "Price : $50 \n" +
                            "Taste: Spicy \n" +
                            "\tIngredients:Ground beef, Onion, Cheese,\n" +
                            "\tsoy sauce, Worcestershire sauce, egg \n" +
                            "Rating : ****";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.hamburger);

                    i.putExtras(bundle);
                    i.putExtra("product", x);


                    startActivity(i);

                } else if (itemsChoice.equals(s2)) {
                    String b = "Price : $20 \n" +
                            "Taste: Medium Spicy \n" +
                            "\tIngredients:Potatoes, Vegetable Oil,\n" +
                            "\tDextrose, Sodium Acid Pyrophosphate,Salt \n" +
                            "Rating : *****";
                    Toast.makeText(getBaseContext(), itemsChoice + b, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.frenchfries);
                    i.putExtra("product", b);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s3)) {
                    String x = "Price : $40 \n" +
                            "Taste: Medium Spicy \n" +
                            "\tIngredients:Green Cardamom, Vegetable Oil\n" +
                            "\tCaraway Seeds,Coriander Seeds, Salt \n" +
                            "Rating : *****";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.chhole);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s4)) {
                    String x = "Price : $30 \n" +
                            "Taste:Mild \n" +
                            "\tIngredients:Mexican-style rice or plain rice\n" +
                            "\tVegetable Oil (Canola Oil),\n" +
                            "Rating : ***";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.burrito);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s5)) {
                    String x = "Price : $10 \n" +
                            "Taste:Mild \n" +
                            "\tIngredients:cucumber, vinegar, oil, brown sugar, salt, lettuce\n" +
                            "\tVegetable Oil (Canola Oil),\n" +
                            "Rating : *****";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.sandwich);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s6)) {
                    String x = "Price : $20 \n" +
                            "Taste:Sweet \n" +
                            "\tIngredients:Blend of vanilla ice cream,cup milk,teaspoon vanilla and a pinch of sal\n" +
                            "\tToasted Marshmallow,\n" +
                            "Rating : *****";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.milkshakes);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s7)) {
                    String x = "Price : $20 \n" +
                            "Taste:Sweet \n" +
                            "\tIngredients: milk,teaspoon vanilla and suger\n" +
                            "\tToasted Marshmallow,\n" +
                            "Rating : *****";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.milkshakes);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                } else if (itemsChoice.equals(s8)) {
                    String x = "Price : $5 \n" +
                            "Taste:Sweet \n" +
                            "\tIngredients: sugar or high-fructose corn syrup\n" +
                            "\tRating : ***";
                    Toast.makeText(getBaseContext(), itemsChoice + x, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.softdrink);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else if (itemsChoice.equals(s9)) {
                    String x = "Price :"+str2+"\n"+
                            "Taste:"+str4+"\n"+
                            "\tIngredients:"+str3+"\n" ;

                    Toast.makeText(getBaseContext(), itemsChoice +"\n"+ x, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), FoodDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", R.drawable.defaultfoodimg);
                    i.putExtra("product", x);
                    i.putExtras(bundle);
                    startActivity(i);

                }
            }


        });

        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                String selectedItem = al.get(pos);
                al.remove(selectedItem);
                adap.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), selectedItem + " is removed from the list", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    public void onSaveInstanceState(Bundle savedInstanceState ){
        savedInstanceState.putString("ved",str1);
        savedInstanceState.putString("god",str2);
    }
}
