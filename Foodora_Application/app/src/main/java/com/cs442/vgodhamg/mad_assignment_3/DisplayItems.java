package com.cs442.vgodhamg.mad_assignment_3;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;

public class DisplayItems extends AppCompatActivity {

    Button button1,button2;
    String b;
    TextView text;

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        text=(TextView)findViewById(R.id.textView);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        button1=(Button)findViewById(R.id.button);
        button1.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);
        button2=(Button)findViewById(R.id.button2);
        button2.getBackground().setColorFilter(0xFF40FFC9, PorterDuff.Mode.MULTIPLY);

        Intent i = getIntent();
        String product = i.getStringExtra("product");
        final int a = i.getIntExtra("result",0);

        b = Integer.toString(a);

        ArrayList<String> products = new ArrayList<String>();
        products.add(product);
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.cutomview,products);
        ListView listView=(ListView) findViewById(R.id.listView2);
        // listView.setBackgroundResource(R.drawable.colors);
        listView.setAdapter(adp);

        EditText total = (EditText) findViewById(R.id.editText1);
        total.setTextColor(Color.parseColor("#ffffff"));
        total.setText(b);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String message = b;
                Intent intent = new Intent();
                intent.putExtra("MESSAGE", message);
                setResult(Activity.RESULT_OK, intent);
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