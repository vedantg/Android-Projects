package com.example.ll;

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





import android.app.Activity;
import android.view.Menu;

import android.widget.TextView;
import java.io.IOException;
  import java.io.InputStream;
  import java.io.OutputStream;
  import java.io.UnsupportedEncodingException;
  import java.util.Arrays;
  import java.io.BufferedInputStream;
  import java.io.InputStream;
  import java.net.URL;
  import java.net.URLConnection;





import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;


public class login extends Activity implements OnClickListener ,Runnable{
	private static final String SOAP_ACTION = "http://tempuri.org/login";
    private static final String METHOD_NAME = "login";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL =  "http://sme.azurewebsites.net/ext.asmx";
    Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView im = (ImageView) findViewById(R.id.imageView1);
        im.setAlpha(128);
        
        ImageView button = (ImageView) findViewById(R.id.login_button);
     
        button.setOnClickListener(this);
       
        // run a thread after 2 seconds to start the home screen
       
        
	}
	 public void run() {
		 
         // make sure we close the splash screen so the user won't come back when it presses back key

         finish();
         // start the home screen
        // dialog.dismiss(); 
         
         Bundle bundle = new Bundle();
   	  bundle.putString("username",((EditText) findViewById(R.id.userid_text)).getText().toString().trim());
   	  bundle.putString("password",((EditText) findViewById(R.id.editText1)).getText().toString().trim());
   	  	    	  Intent newIntent = new Intent(this.getApplicationContext(), HOME.class);
   	  newIntent.putExtras(bundle);
   
   	  startActivityForResult(newIntent, 0);
   	  finish();
   	  
   	  
   	  
           return;

     }
	public void onClick(View src) {

		
		ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
		 
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			  request.addProperty("username",((EditText) findViewById(R.id.userid_text)).getText().toString().trim());
			  request.addProperty("password",((EditText) findViewById(R.id.editText1)).getText().toString().trim());
			 
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
	      if("LS".equals(str)){
	    	  handler = new Handler();
			  
	            handler.postDelayed((Runnable)this,100); 
	            
	       }else if ("PI".equals(str)) {
	    	   dialog.dismiss(); 
	    	   Toast.makeText(login.this,"Your Password is invalid...!", Toast.LENGTH_LONG).show();
		}else if ("BI".equals(str)) {
			dialog.dismiss(); 
			Toast.makeText(login.this,"Your UserName and Password is invalid...!", Toast.LENGTH_LONG).show();
		}else if ("PFD".equals(str)) {
			dialog.dismiss(); 
			Toast.makeText(login.this,"Please Fill Details...!", Toast.LENGTH_LONG).show();
		}else if ("EXCEPTION".equals(str)) {
			dialog.dismiss(); 
			Toast.makeText(login.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
		} 
	     
	   
	            
	            }
	            catch(Exception e)
	            { dialog.dismiss(); 
	            	Toast.makeText(login.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
	            	
	          
	            }
	  }
	     
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
