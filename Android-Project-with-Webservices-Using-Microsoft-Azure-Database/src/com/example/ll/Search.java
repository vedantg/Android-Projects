package com.example.ll;

import BE.Thoughtbeat.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

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
import android.widget.SlidingDrawer;
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











public class Search extends Activity implements OnItemClickListener, QuickAction.OnActionItemClickListener,OnClickListener, Runnable {
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
    alert weather_data[];
    alertAdapter adapter;
    Context c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
		 Bundle bundle = this.getIntent().getExtras();     
		   if (bundle != null){       
		  username = bundle.getString("username");
		  
		   }
		   else
		   {username="BES5003";
		  	  
		   }
		   ActionItem addAction = new ActionItem();
		   Toast.makeText( Search.this,"Helps to find institutes for available courses.", Toast.LENGTH_LONG).show();
			addAction.setTitle("Home");
			addAction.setIcon(getResources().getDrawable(R.drawable.pop_home));

			// Accept action item
			ActionItem accAction = new ActionItem();

			accAction.setTitle("Search");
			accAction.setIcon(getResources().getDrawable(R.drawable.cute));

			 
		     /*   ImageView im1 = (ImageView) findViewById(R.id.ImageView03);
		        im1.setAlpha(128);
		      
		        ImageView im2 = (ImageView) findViewById(R.id.ImageView04);
		        im.setAlpha(128);
		      
		        ImageView im2 = (ImageView) findViewById(R.id.ImageView05);
		        im.setAlpha(128);*/
		      
			// Upload action item
		/*	ActionItem upAction = new ActionItem();

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
						
			

			ImageView ivPic1 = (ImageView) this.findViewById(R.id.search_home);
			ivPic1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mQuickAction.show(v);
					mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
					
				}
			});
			c=this;
			ImageView ivPic2 = (ImageView) this.findViewById(R.id.search_butt);
			//Button button = (Button) findViewById(R.id.imageButton1);
			ivPic2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					 int cols=0;
					
			        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
					  request.addProperty("username",username);
					  course=((EditText) findViewById(R.id.search_txt)).getText().toString().trim();
					  course=course.toUpperCase();
					  request.addProperty("course",course);
					  course=((EditText) findViewById(R.id.search_txt)).getText().toString().trim();
					  
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
			          
			           cols =response.getPropertyCount();
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
			     { Toast.makeText( Search.this,e.toString(), Toast.LENGTH_LONG).show();
			     	
			     e.printStackTrace();
			     }
			        
			         
			         weather_data = new alert[Values.length/3];
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
			        adapter = new alertAdapter(c, R.layout.listview_item_row, weather_data);
			       
			        

			                
			        
			        listView1 = (ListView)findViewById(R.id.listView1);
			         
			   
			       
			        listView1.setAdapter(adapter);
			      
					
					if(cols==0)
						 Toast.makeText( Search.this,"Please Enter Correct Data", Toast.LENGTH_LONG).show();
				}
			});
			
	
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
	       	bundle.putString("password","");
	       	bundle.putString("course",course);
	       	  
	    	  Intent newIntent = new Intent(this.getApplicationContext(), information.class);
	    	  newIntent.putExtras(bundle);
	    	  startActivityForResult(newIntent, 0);
	    	  finish();
	            return;
    	}
    }
    public void onClick(View v) {
    	
    	
    	
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
	/*	Toast.makeText(Search.this, "Help selected",
				Toast.LENGTH_SHORT).show();
	}else if (pos == 3) {*/ // Upload item selected
		Intent intent = new Intent(this, login.class);
        intent.putExtra("finish", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        startActivity(intent);
        finish(); 
	}
}

public int x;
public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {
	ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
x=0;
position=position+1;
for(int i=0;i<position;i++)
{x=x+2;

}
x=x+position-1;
	    	  vik=1;
		            handler = new Handler();
		  		  
		            handler.postDelayed((Runnable)this,100); 
	 
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
