package com.cs442.vgodhamg.servicenotificationcounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnStop;
    private EditText  editTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTxt = (EditText) findViewById(R.id.editText);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void ServiceNotificationCounterStart(View view) {

        // Taking input from EditText and passing as an Input for the Intent, being forwarded to the Service
         Intent serviceIntent = new Intent(getApplicationContext(),ServiceNotificationSetCounter.class);

            serviceIntent.putExtra("counter_value", editTxt.getText().toString());
            // Calling the onStartCommand in the ServiceNotificationSetCounter
            startService(serviceIntent);
            editTxt.setText("");

    }

    public void ServiceNotificationCounterStop(View view) {

        Intent intent = new Intent(this, ServiceNotificationSetCounter.class);

        // Calling the onDestroy in the ServiceNotificationSetCounter
        stopService(intent);

        btnStart.setEnabled(true);
        btnStop.setEnabled(true);

    }

}
