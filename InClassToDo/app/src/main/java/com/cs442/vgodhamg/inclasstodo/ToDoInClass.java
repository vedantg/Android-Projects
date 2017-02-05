package com.cs442.vgodhamg.inclasstodo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ToDoInClass extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText enter;
    Button btn_enter;
    String latitude;
    String str1;
    ObjectOutputStream out;
   // TextView data;
   public ArrayList<String> al;
    ArrayAdapter<String> adap;
    ListView lv;
    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_in_class);


        al = new ArrayList<String>();
        lv = (ListView) findViewById(R.id.listView);

        btn_enter= (Button)findViewById(R.id.button);
        enter= (EditText)findViewById(R.id.editText);
        al = getArray();
        adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adap);
        Object[] objs = new Object[lv.getCount()];

        for (int i = 0 ; i < lv.getCount();i++) {
            Object obj = (Object)lv.getItemAtPosition(i);
            objs[i] = obj;
        }

        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(yourFile.txt)));
            out.writeObject(objs);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                str1 = enter.getText().toString();
                al.add(str1);
                enter.setText("");
                }



        });




        }


    public boolean saveArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(al);
        mEdit1.putStringSet("list", set);
        return mEdit1.commit();
    }

    public ArrayList<String> getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);

        //NOTE: if shared preference is null, the method return empty Hashset and not null
        Set<String> set = sp.getStringSet("list", new HashSet<String>());

        return new ArrayList<String>(set);
    }

    public void onStop() {
        saveArray();
        super.onStop();
    }
  /*  public void onSaveInstanceState(Bundle savedInstanceState ){
        super.onSaveInstanceState(savedInstanceState);

        // Note: getValues() is a method in your ArrayAdaptor subclass
        String[] values = adap();
        savedState.putStringArray("myKey", values);
    }*/
    }

