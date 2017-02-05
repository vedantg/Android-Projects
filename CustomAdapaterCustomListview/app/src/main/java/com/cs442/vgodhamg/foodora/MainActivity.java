package com.cs442.vgodhamg.foodora;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by vedant on 16-02-2016.
 */
public class MainActivity extends ListActivity {

   public ArrayList<HashMap<String, Object>> fooditem;
    LayoutInflater inflater;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the LayoutInflater for inflating the customomView
        //this will be used in the custom adapter
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //these arrays are just the data that
        //I'll be using to populate the ArrayList
        //You can use our own methods to get the data
        String names[] = {"Ronaldo", "Messi", "Torres", "Iniesta",
                "Drogba", "Gerrard", "Rooney", "Xavi"};

        int teams[] = {11, 12, 13, 14, 15, 16, 17, 18};

        Integer[] photos={R.drawable.burrito,R.drawable.hamburger,
                R.drawable.milkshakes,R.drawable.hotdog,
                R.drawable.icecream,R.drawable.chhole,
                R.drawable.sandwich,R.drawable.softdrink};
        fooditem = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> temp;

        //total number of rows in the ListView
        int noOfPlayers = names.length;

        for (int i = 0; i < noOfPlayers; i++) {
            temp = new HashMap<String, Object>();

            temp.put("name", names[i]);
            temp.put("team", teams[i]);
             temp.put("photo", photos[i]);

            //add the row to the ArrayList
            fooditem.add(temp);
        }


        /*create the adapter
 *first param-the context
 *second param-the id of the layout file
  you will be using to fill a row
 *third param-the set of values that
   will populate the ListView */
        final CustomizedAdapter adapter = new CustomizedAdapter(this, R.layout.listviewrowdesign, fooditem);

        //finally,set the adapter to the default ListView
        setListAdapter(adapter);

    }

    public class CustomizedAdapter extends ArrayAdapter<HashMap<String, Object>> {

        boolean[] checkBoxState;
        ViewHolder viewHolder;
        Activity activity;

        public CustomizedAdapter(Context context, int textViewResourceId,
                                 ArrayList<HashMap<String, Object>> fooditem) {


            //let android do the initializing :)
            super(context, textViewResourceId, fooditem);
            //create the boolean array with
            //initial state as false
            checkBoxState = new boolean[fooditem.size()];
        }

        private class ViewHolder
        {
             ImageView photo;
            TextView name,team;
            CheckBox checkBox;
        }
        public View getView(final int position, View convertView, ViewGroup parent) {

         //   LayoutInflater inflater = activity.getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listviewrowdesign, null);
                viewHolder = new ViewHolder();

                //cache the views
                 viewHolder.photo=(ImageView) convertView.findViewById(R.id.imageView1);
                viewHolder.name = (TextView) convertView.findViewById(R.id.textView1);
                viewHolder.team = (TextView) convertView.findViewById(R.id.textView2);
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);

                //link the cached views to the convertview
                convertView.setTag(viewHolder);
            }

            else
                viewHolder = (ViewHolder) convertView.getTag();

                int photoId=(Integer)fooditem.get(position).get("photo");

              viewHolder.photo.setImageDrawable(getResources().getDrawable(photoId));
            viewHolder.name.setText(fooditem.get(position).get("name").toString());
            viewHolder.team.setText(fooditem.get(position).get("team").toString());

            viewHolder.checkBox.setChecked(checkBoxState[position]);


            //for managing the state of the boolean
            //array according to the state of the
            //CheckBox

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    CheckBox cb = (CheckBox) v ;
                  //  Country country = (Country) cb.getTag();

                   // country.setSelected(cb.isChecked());
                    for (FoodProducts p : boxAdapter.getBox()) {
                    if (cb.isChecked()) {
                        checkBoxState[position] = true;

                        viewHolder.team.getText();


                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + viewHolder.name.getText() + " is " +  viewHolder.team.getText(), Toast.LENGTH_LONG).show();
                    }else
                        checkBoxState[position] = false;

                }
            });
//return the view to be displayed
            return convertView;
        }

    }

    }




















