package com.cs442.vgodhamg.mad_assignment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vedant on 24-02-2016.
 */
public class FoodDBHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "foodoradatabase.db";
    private static final String TABLE_NAME = "mytable";
  //  private static final int DATABASE_VERSION = 6;
    private static final String UID = "_id";
    private static final String FOOD_ITEM_NAME = "Food_Item";
    private static final String FOOD_TOTAL_BILL= "Total_Bill";
    private static final String FOOD_ITEM_TIMESTMP = "Timestamp";
    //FOOD_ITEM_TIMESTMP DATETIME DEFAULT CURRENT_TIMESTAMP

   // private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("_id INTEGER PRIMARY KEY AUTOINCREMENT,Food_Item TEXT)";);


    public FoodDBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){

        db.execSQL("create table " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, Food_Item TEXT, Total_Bill TEXT , Timestamp TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
       onCreate(db);
    }




  /*  private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    } */

    public boolean addContact(String food_item_name, String food_item_price) {
       // dbHelper = new FoodDBHelper(this);
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
       // String strDate = sdf.format(new Date());

        ContentValues values = new ContentValues();
        values.put("Food_Item",food_item_name);
        values.put("Total_Bill",food_item_price);
        values.put("Timestamp", sdf.format(date));
       // values.put("Timestamp", getDateTime());
      //  values.put("Timestamp", timeNow.getTime());

        // Inserting Row
       long a= db.insert(TABLE_NAME, null, values);
       // db.close(); // Closing database connection

        if(a==1){
            return true;
        }
        else return false;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
