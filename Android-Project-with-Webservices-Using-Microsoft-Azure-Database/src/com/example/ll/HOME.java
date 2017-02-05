package com.example.ll;

import java.util.ArrayList;
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

import javax.crypto.spec.IvParameterSpec;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
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

public class HOME extends Activity implements OnItemClickListener ,Runnable,OnClickListener{
	private static final String SOAP_ACTION = "http://tempuri.org/home";
    private static final String METHOD_NAME = "home";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL =  "http://sme.azurewebsites.net/ext.asmx";
    private String[] Values;
   private ListView lvItem;
    private ArrayList<String> itemArrey;
    private ArrayAdapter<String> itemAdapter;
    String username="";
    String password="";
    Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		
		
		 ImageView im = (ImageView) findViewById(R.id.fade_home);
	        im.setAlpha(70);
	      
		 Bundle bundle = this.getIntent().getExtras();     
		   if (bundle != null){       
		  username = bundle.getString("username");
		   password = bundle.getString("password");
		   }else {
			username="BES5003";
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
			upAction.setIcon(getResources().getDrawable(R.drawable.help2));
*/
			ActionItem upAction1 = new ActionItem();

			upAction1.setTitle("Sign Out");
			upAction1.setIcon(getResources().getDrawable(R.drawable.log_ohb));


			final QuickAction mQuickAction = new QuickAction(this);

			mQuickAction.addActionItem(addAction);
			mQuickAction.addActionItem(accAction);
		//	mQuickAction.addActionItem(upAction);
			mQuickAction.addActionItem(upAction1);
			// setup the action item click listener
			mQuickAction
					.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
						public void onItemClick(int pos) {

							if (pos == 0) { // Add item selected
								
							} else if (pos == 1) { // Accept item selected
								Toast.makeText(HOME.this,
										"Search item selected", Toast.LENGTH_SHORT)
										.show();
							} else if (pos == 2) { // Upload item selected
								Toast.makeText(HOME.this, "Help selected",
										Toast.LENGTH_SHORT).show();
							}else if (pos == 3) { // Upload item selected
								
							}
						}
					});

			ImageView ivPic1 = (ImageView) this.findViewById(R.id.home_back);
			ivPic1.setOnClickListener((OnClickListener) this);
				
			
		lvItem = (ListView)this.findViewById(R.id.mainListView);
        itemArrey = new ArrayList<String>();
        itemArrey.clear();
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itemArrey);
        lvItem.setAdapter(itemAdapter);
    	
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    request.addProperty("username",username);
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
             for(int i=0;i<cols;i++)
             {     
              Values[i]=response.getPropertyAsString(i).toString(); 
             }
          
          }
       catch(Exception e)
       { Toast.makeText( HOME.this,"Sorry error in data transfer , please try later...!", Toast.LENGTH_LONG).show();
      
       }
	
		course();
		
		TextView us=(TextView) findViewById(R.id.stud_id);
		us.setText(username);
		TextView pass=(TextView) findViewById(R.id.textView3);
		pass.setText(Values[0]+ " " + Values[1]);
		Toast.makeText( HOME.this,"Please click on list to view alerts of the courses...!", Toast.LENGTH_LONG).show();
		 lvItem.setOnItemClickListener((OnItemClickListener) this); 
	        
	              
	       
	}

public void onClick(View v) {
	Intent intent = new Intent(this, login.class);
    intent.putExtra("finish", true);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
    startActivity(intent);
    finish();
}
    public int conts(String s)
    {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '&')
                cnt++;

        }

        return cnt;
    }
	public void course() {
		  String corse=Values[2]+Values[3];
		  int cnt = conts(corse);
          String pr = "";
          int j = 0;
          try
          {

              for (int i = 0; i < cnt; i++)
              {
                  pr = "";
                  while (j < corse.length())
                  {
                      if (corse.charAt(j) == '&')
                      {
                    	  addItemList(pr);
                          
                          pr = "";
                          j++;
                          break;
                      }
                      pr += corse.charAt(j);

                      j++;
                  }

              }


          }
          catch (Exception ex)
          {

          }
				
	}
	 public void run() {
		 Bundle bundle = new Bundle();
      	  bundle.putString("username",username );
   	  bundle.putString("course", itemArrey.get(pos));
   	  bundle.putString("password",password);
   	  
   	  Intent newIntent = new Intent(this.getApplicationContext(), inst.class);
   	  newIntent.putExtras(bundle);
   	  startActivityForResult(newIntent, 0);
   	  finish();
           return;
	 }
	 public int pos;
public void onItemClick(AdapterView<?> adapter, View view, int position, long arg)   {

	ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
	//Toast.makeText( HOME.this,itemArrey.get(position), Toast.LENGTH_LONG).show();
	        	  pos=position;
		            handler = new Handler();
					  
		            handler.postDelayed((Runnable)this,100); 
		    	  
	    }

	    protected void addItemList(String str) {
		        itemArrey.add(str);
	     
	        itemAdapter.notifyDataSetChanged();

  

	    }
/*
	    protected boolean isInputValid(EditText etInput2) {
	        // TODO Auto-generatd method stub
	        if (etInput2.getText().toString().trim().length()<1) {
	            etInput2.setError("Please Enter Item");
	            return false;
	        } else {
	            return true;
	        }

	    }*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}

