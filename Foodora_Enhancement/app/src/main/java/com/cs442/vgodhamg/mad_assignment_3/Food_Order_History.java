package com.cs442.vgodhamg.mad_assignment_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Food_Order_History extends AppCompatActivity {

    ListView lv;
    Intent i;
    ArrayAdapter <String> ad;
    ArrayList<String>al;
    ArrayList<String> s1;
    FoodDBHelper fb;
    ArrayList<String> s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__order__history);

        fb= new FoodDBHelper(this);
        s3 = new ArrayList<String>();
      i = getIntent();


        s1 = i.getStringArrayListExtra("list");
        //s3=new String[s1.size()];

        for(int j=0; j<s1.size();j++) {
           // fb.getAllData(s1[j]);
            s3.add(s1.get(j));
        }


       /* for(int k=0; k<s1.size();k++) {
            String put=s3[k];
          //  fb.getAllData(put);
        } */

     //   al = new ArrayList<String>();
       // al.add(s1);

        ad = new ArrayAdapter<String>(this,R.layout.cutomview,s3);

        lv = (ListView)findViewById(R.id.listViewOrderID);
        lv.setAdapter(ad);


    }

}
