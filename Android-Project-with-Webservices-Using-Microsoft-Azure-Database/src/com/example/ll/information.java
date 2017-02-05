package com.example.ll;

import java.util.ArrayList;

import javax.crypto.spec.IvParameterSpec;

import BE.Thoughtbeat.R;
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.apache.http.util.ByteArrayBuffer;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.widget.Button;
import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import android.content.Intent;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;  
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class information extends Activity implements QuickAction.OnActionItemClickListener ,Runnable,OnClickListener {
	private   String SOAP_ACTION = "http://tempuri.org/Institute";
    private  String METHOD_NAME = "Institute";
    private  String NAMESPACE = "http://tempuri.org/";
    private  String URL =  "http://sme.azurewebsites.net/ext.asmx";
    
    private   String SOAP_ACTION1 = "http://tempuri.org/email";
    private  String METHOD_NAME1 = "email";
   
    private String[] Values;
   String username="";
    String username1="";
    String password="";
    String course="";
    Handler handler;
    ProgressDialog dialog ;
    public Context c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
	/*	Button button2 = (Button) findViewById(R.id.apply);
	     
        button2.setOnClickListener(this);*/
  
		
		 Bundle bundle = this.getIntent().getExtras();     
		   if (bundle != null){       
		  username = bundle.getString("username");
		  username1 = bundle.getString("username1");
		  // password = bundle.getString("password");
		   password=bundle.getString("password");
		   course=bundle.getString("course");
			
		  
		 
	      
		   ActionItem addAction = new ActionItem();

			addAction.setTitle("Home");
			addAction.setIcon(getResources().getDrawable(R.drawable.pop_home));

			// Accept action item
		//	ActionItem accAction = new ActionItem();

			//accAction.setTitle("Search");
			//accAction.setIcon(getResources().getDrawable(R.drawable.cute));

			// Upload action item
		/*	ActionItem upAction = new ActionItem();

			upAction.setTitle("Help");
			upAction.setIcon(getResources().getDrawable(R.drawable.help2));*/

			ActionItem upAction1 = new ActionItem();

			upAction1.setTitle("Sign Out");
			upAction1.setIcon(getResources().getDrawable(R.drawable.log_ohb));


			final QuickAction mQuickAction = new QuickAction(this);

			mQuickAction.addActionItem(addAction);
			//mQuickAction.addActionItem(accAction);
		///	mQuickAction.addActionItem(upAction);
			mQuickAction.addActionItem(upAction1);
			// setup the action item click listener
			mQuickAction
					.setOnActionItemClickListener((QuickAction.OnActionItemClickListener) this);
						
				

			ImageView ivPic1 = (ImageView) this.findViewById(R.id.act_home);
			ivPic1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mQuickAction.show(v);
					mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
				}
			});
			ImageView ivPic2 = (ImageView) this.findViewById(R.id.back_act);
			
			ivPic2.setOnClickListener( (OnClickListener) this);
			
			
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    request.addProperty("username",username);
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet = true;
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	        
	    try
          {
         	 androidHttpTransport.call(SOAP_ACTION,envelope);
         	SoapObject response = (SoapObject) envelope.getResponse();
            
            response.getPropertyCount();
            Values = new String[response.getPropertyCount()];
             TextView us=(TextView) findViewById(R.id.inst_name);
         		us.setText(response.getPropertyAsString(0).toString());
         		TextView us1=(TextView) findViewById(R.id.inst_add);
        		us1.setText(response.getPropertyAsString(1).toString());
        		TextView us2=(TextView) findViewById(R.id.inst_email);
        		us2.setText(response.getPropertyAsString(2).toString());
        		TextView us3=(TextView) findViewById(R.id.inst_contct);
        		us3.setText(response.getPropertyAsString(3).toString());
        		
         		 
            
          
          }
       catch(Exception e)
       { Toast.makeText( information.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
       }
	
		     
		   }
		   else
		   {
			   finish();
	            return;
		   }
		   
		   c=this;
		  ImageView ivPic1 = (ImageView) this.findViewById(R.id.apply_button);
			ivPic1.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					
					//dialog.show(c, "Loading", "Please wait...", true);
				
					 SOAP_ACTION = "http://tempuri.org/mail";
				     METHOD_NAME = "mail";
					  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
					  request.addProperty("username1",username1);
					  request.addProperty("username2",username);
					 
				        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				        envelope.dotNet = true;
				        envelope.setOutputSoapObject(request);
				        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
				       
				     SoapPrimitive response=null;
				        String str;
					try
			            {
			           	 androidHttpTransport.call(SOAP_ACTION,envelope);
			        
			            response = (SoapPrimitive) envelope.getResponse();
			       
			         str=response.toString();
			      if("send".equals(str)){
			    	  Toast.makeText(information.this,"You have applied succefully", Toast.LENGTH_LONG).show();
			       }else if ("error".equals(str)) {
					//dialog.dismiss(); 
					Toast.makeText(information.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(information.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
				}
				
			     
			   
			            
			            }
			            catch(Exception e)
			            { //dialog.dismiss(); 
			            	Toast.makeText(information.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
			            	
			          
			            }
					
			}
			});
	       
	}
	

	public void onClick(View v) {
		
		 Bundle bundle = new Bundle();
		 bundle.putString("username",username1 );
         bundle.putString("course", course);
         bundle.putString("password",password);
  	  Intent newIntent = new Intent(this.getApplicationContext(), inst.class);
  	  newIntent.putExtras(bundle);
  	  startActivityForResult(newIntent, 0);
  	  finish();
	}
	public int vik=0;
	 public void run() {
		 
		
		 Bundle bundle = new Bundle();
	   	  bundle.putString("username",username1);
	   	  bundle.putString("password",password);
	   	  	    	  Intent newIntent = new Intent(this.getApplicationContext(), HOME.class);
	   	  newIntent.putExtras(bundle);
	   	  startActivityForResult(newIntent, 0);
	   	 finish();
	     return;
		
	 }
public void onItemClick(int pos) {

	if (pos == 0) { // Add item selected
		ProgressDialog.show(this, "Loading", "Please wait...", true);
		vik=1;
     handler = new Handler();
	  
     handler.postDelayed((Runnable)this,100); 
	} else if (pos == 1) { // Accept item selected
		/* Bundle bundle = new Bundle();
    	  bundle.putString("username",username);
    	 Intent newIntent = new Intent(this.getApplicationContext(), Search.class);
    	  newIntent.putExtras(bundle);
    	  startActivityForResult(newIntent, 0);
    	  finish();
          return;
	} else if (pos == 2) { // Upload item selected
		Toast.makeText(information.this, "Help selected",
				Toast.LENGTH_SHORT).show();
	}else if (pos == 3) {*/ // Upload item selected
		Intent intent = new Intent(this, login.class);
        intent.putExtra("finish", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        startActivity(intent);
        finish();
	}
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
