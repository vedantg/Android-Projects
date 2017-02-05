package com.example.ll;

import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import BE.Thoughtbeat.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;











public class inst extends Activity implements OnItemClickListener, QuickAction.OnActionItemClickListener,OnClickListener, Runnable {
    private ListView listView1;
    private static final String SOAP_ACTION = "http://tempuri.org/alerts";
    private static final String METHOD_NAME = "alerts";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL =  "http://sme.azurewebsites.net/ext.asmx";
    private String[] Values;
    private ListView lvItem;
    String username="";
    String password="";
    Handler handler;
    String course="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerts);
        
        ImageView im = (ImageView) findViewById(R.id.inst_fade);
        im.setAlpha(70);
      
		 Bundle bundle = this.getIntent().getExtras();     
		   if (bundle != null){       
		  username = bundle.getString("username");
		   course = bundle.getString("course");
		   password=bundle.getString("password");
		   }
		   else
		   {username="BES5003";
		   course="JAVA";
		   password="b";		  
		   }
		   ActionItem addAction = new ActionItem();

			addAction.setTitle("Home");
			addAction.setIcon(getResources().getDrawable(R.drawable.pop_home));

			// Accept action item
			ActionItem accAction = new ActionItem();

			accAction.setTitle("Search");
			accAction.setIcon(getResources().getDrawable(R.drawable.cute));

			// Upload action item
			/*ActionItem upAction = new ActionItem();

			upAction.setTitle("Help");
			upAction.setIcon(getResources().getDrawable(R.drawable.help2));*/

			ActionItem upAction1 = new ActionItem();

			upAction1.setTitle("Sign Out");
			upAction1.setIcon(getResources().getDrawable(R.drawable.log_ohb));


			final QuickAction mQuickAction = new QuickAction(this);

			mQuickAction.addActionItem(addAction);
			mQuickAction.addActionItem(accAction);
		//	mQuickAction.addActionItem(upAction);
			mQuickAction.addActionItem(upAction1);
			// setup the action item click listener
			mQuickAction.setOnActionItemClickListener((QuickAction.OnActionItemClickListener) this);
						
			

			ImageView ivPic1 = (ImageView) this.findViewById(R.id.inst_home);
			ivPic1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mQuickAction.show(v);
					mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
					
				}
			});
			ImageView ivPic2 = (ImageView) this.findViewById(R.id.back_inst);
			ivPic2.setOnClickListener((OnClickListener)this);
			
			
			
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("username",username);
		  request.addProperty("course",course);
		  
		  
		//  request.addProperty("password",((EditText) findViewById(R.id.editText1)).getText().toString().trim());
		 
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet = true;
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	        
	    // SoapPrimitive response=null;
	        String str="";
		try
        {
       	 androidHttpTransport.call(SOAP_ACTION,envelope);
       	SoapObject response = (SoapObject) envelope.getResponse();
          
          int cols =response.getPropertyCount();
          Values = new String[response.getPropertyCount()];
       // Values = new String[response.getAttributeCount()];
        for(int i=0;i<cols;i++)
           {     
      	 
            Values[i]=response.getPropertyAsString(i).toString();
            str+=Values[i];
           // addItemList(response.getPropertyAsString(i).toString());
           }
        
        }
     catch(Exception e)
     { Toast.makeText( inst.this,e.toString(), Toast.LENGTH_LONG).show();
     	
     e.printStackTrace();
     }
        
         
        alert weather_data[] = new alert[Values.length/3];
        int i=0;
        int j=0;
        try{
        while (i<Values.length/3) {
		
        weather_data[i]= new alert(Values[j],Values[j+1]);
        i++;
         j=j+3;
        }
        }
        catch (Exception e) {
			
		}
         alertAdapter adapter = new alertAdapter(this, 
                R.layout.listview_item_row, weather_data);
       
        

                
        
        listView1 = (ListView)findViewById(R.id.listView1);
         
   
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        TextView headerText = (TextView) header.findViewById(R.id.txtHeader);
        headerText.setText("Institute Alerts For "+ course);


        listView1.addHeaderView(header);
        
        listView1.setAdapter(adapter);
        lvItem = (ListView)this.findViewById(R.id.listView1);
        lvItem.setOnItemClickListener((OnItemClickListener) this);
    }
    public int vik;
    public void run() {
    	if(vik==0)
    	{
    	 Bundle bundle = new Bundle();
   	  bundle.putString("username",username);
   	  bundle.putString("password",password);
   	  	    	  Intent newIntent = new Intent(this.getApplicationContext(), HOME.class);
   	  newIntent.putExtras(bundle);
   	  startActivityForResult(newIntent, 0);
   	  finish();
         return;
    	}
    	else if(vik==1)
    	{
    		 Bundle bundle = new Bundle();
	       	  bundle.putString("username",Values[x]);
	       	bundle.putString("username1",username);
	       	bundle.putString("password",password);
	       	bundle.putString("course",course);
	       	  
	    	  Intent newIntent = new Intent(this.getApplicationContext(), information.class);
	    	  newIntent.putExtras(bundle);
	    	  startActivityForResult(newIntent, 0);
	    	  finish();
	            return;
    	}
    }
    public void onClick(View v) {
    	 Bundle bundle = new Bundle();
      	  bundle.putString("username",username);
      	  bundle.putString("password",password);
      	  	    	  Intent newIntent = new Intent(this.getApplicationContext(), HOME.class);
      	  newIntent.putExtras(bundle);
      	  startActivityForResult(newIntent, 0);
      	  finish();
            return;
	}
public void onItemClick(int pos) {

	if (pos == 0) { // Add item selected
		ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
          vik=0;
    	  handler = new Handler();
		  
          handler.postDelayed((Runnable)this,100); 
    	 
	} else if (pos == 1) { // Accept item selected
		 Bundle bundle = new Bundle();
     	  bundle.putString("username",username);
     	 Intent newIntent = new Intent(this.getApplicationContext(), Search.class);
     	  newIntent.putExtras(bundle);
     	  startActivityForResult(newIntent, 0);
     	  finish();
           return;
		
	} else if (pos == 2) { // Upload item selected
	/*	Toast.makeText(inst.this, "Help selected",
				Toast.LENGTH_SHORT).show();
	}else if (pos == 3) { */// Upload item selected
		Intent intent = new Intent(this, login.class);
        intent.putExtra("finish", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        startActivity(intent);
        finish(); 
	}
}
public int x;
public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {
	if(position!=0)
	{
		
	ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
x=0;
for(int i=0;i<position;i++)
{x=x+2;

}
x=x+position-1;
	    	  vik=1;
		            handler = new Handler();
		  		  
		            handler.postDelayed((Runnable)this,100); 
	}
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
