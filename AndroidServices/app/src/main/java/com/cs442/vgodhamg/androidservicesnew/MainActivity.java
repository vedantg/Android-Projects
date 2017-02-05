package com.cs442.vgodhamg.androidservicesnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startedService(View view) {
        Intent intent = new Intent(this, StartedServiceActivity.class);
        startActivity(intent);
    }

    public void localBoundService(View view) {
        Intent intent = new Intent(this, LocalBoundServiceActivity.class);
        startActivity(intent);
    }

    public void remoteBoundService(View view) {
        Intent intent = new Intent(this, RemoteBoundServiceActivity.class);
        startActivity(intent);
    }

    // Start remote bound service
    public void notification(View view) {
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
