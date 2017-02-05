package com.example.ll;

import BE.Thoughtbeat.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class alertAdapter extends ArrayAdapter<alert>{

    Context context; 
    int layoutResourceId;    
    alert data[] = null;
    
    public alertAdapter(Context context, int layoutResourceId, alert[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new WeatherHolder();
         
            holder.txtTitle = (TextView)row.findViewById(R.id.inst_nm);
            holder.addrs=(TextView)row.findViewById(R.id.inst_add);
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
        
        alert weather = data[position];
       holder.addrs.setText(weather.addrs);
        holder.txtTitle.setText(weather.title);
     
        
        return row;
    }
    
    static class WeatherHolder
    {
     
        TextView txtTitle;
        TextView addrs;
    }
}
