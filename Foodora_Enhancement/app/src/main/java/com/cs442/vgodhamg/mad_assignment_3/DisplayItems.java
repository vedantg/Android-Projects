package com.cs442.vgodhamg.mad_assignment_3;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DisplayItems extends AppCompatActivity {

    Button button1,button2;
    ListView lvMain;
    String b,x;
    TextView text;
    String product,s1;
    int price,img,a;
   public static int new_message1=0;
    boolean check1;
    ArrayList<FoodProducts> products ;
    ListAdapterOne boxAdapter;
    ArrayAdapter<String> stradap;
    ArrayList<String> strfoodname;
    FoodDBHelper dbHelper;
    ContentValues values;

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);
        dbHelper = new FoodDBHelper(this);
strfoodname = new ArrayList<String >();

        text=(TextView)findViewById(R.id.textView);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        button1=(Button)findViewById(R.id.button);
        button1.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);
        button2=(Button)findViewById(R.id.button2);
        button2.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);

        Intent i = getIntent();

        products =  (ArrayList<FoodProducts>)getIntent().getSerializableExtra("FILES_TO_SEND_NEW");

        strfoodname = i.getStringArrayListExtra("FILES_TO_SEND");
      //  stradap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strfoodname);
      //  product = i.getStringExtra("product");
      //  price = i.getIntExtra("price", 0);


        a = i.getIntExtra("result", 0);
        //   img = i.getIntExtra("image", 0);
        //   check1 = i.getBooleanExtra("foodcheckbox", true); */

       // new_message1=a;
        b = Integer.toString(a);
      //  x=b;
       // x= Integer.toString(price);

        boxAdapter = new ListAdapterOne(this, products);
        lvMain = (ListView) findViewById(R.id.listView2);
        lvMain.setAdapter(boxAdapter);

        //products.add(x);
     /*   ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.cutomview,products);
        ListView listView=(ListView) findViewById(R.id.listView2);
        listView.setAdapter(adp); */

        EditText total = (EditText) findViewById(R.id.editText1);
        total.setTextColor(Color.parseColor("#ffffff"));
        total.setText(b);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

               // c = Integer.parseInt(message);
                new_message1 = new_message1 + a;
                s1 = Integer.toString(new_message1);

                for (int z = 0; z < strfoodname.size(); z++) {
                    dbHelper.addContact(strfoodname.get(z),s1);
                }
                //   lvMain.setAdapter(null);
                //    boxAdapter.notifyDataSetChanged();
                String message = b;
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", message);
                setResult(Activity.RESULT_OK, intent);


              //  boolean b = dbHelper.addContact(product);

               // if (b == true)
                  //  Toast.makeText(DisplayItems.this, "inserted", Toast.LENGTH_SHORT).show();

                //   boxAdapter.clearAdapter(); */

                // for (int j = 0; j < strfoodname.size(); j++) {
                // strfoodname.clear();

                //  }
                // stradap.notifyDataSetChanged();
                //stradap.clear();
                // lvMain.setAdapter(null);
               // strfoodname.clear();
               // stradap.clear();
                finish();//finishing activity
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();

            }
        });



    }




}