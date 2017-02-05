package com.example.ll;

import BE.Thoughtbeat.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

public class pop extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);

		// Add action item
		ActionItem addAction = new ActionItem();

		addAction.setTitle("Home");
	//	addAction.setIcon(getResources().getDrawable(R.drawable.home1));

		// Accept action item
		ActionItem accAction = new ActionItem();

		accAction.setTitle("Search");
		accAction.setIcon(getResources().getDrawable(R.drawable.cute));

		// Upload action item
		ActionItem upAction = new ActionItem();

		upAction.setTitle("Help");
		//upAction.setIcon(getResources().getDrawable(R.drawable.help2));

		ActionItem upAction1 = new ActionItem();

		upAction1.setTitle("Sign Out");
		//upAction1.setIcon(getResources().getDrawable(R.drawable.signout));


		final QuickAction mQuickAction = new QuickAction(this);

		mQuickAction.addActionItem(addAction);
		mQuickAction.addActionItem(accAction);
		mQuickAction.addActionItem(upAction);
		mQuickAction.addActionItem(upAction1);
		// setup the action item click listener
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					public void onItemClick(int pos) {

						if (pos == 0) { // Add item selected
							Toast.makeText(pop.this,
									"PHONE item selected", Toast.LENGTH_SHORT)
									.show();
						} else if (pos == 1) { // Accept item selected
							Toast.makeText(pop.this,
									"GMAIL item selected", Toast.LENGTH_SHORT)
									.show();
						} else if (pos == 2) { // Upload item selected
							Toast.makeText(pop.this, "TALK selected",
									Toast.LENGTH_SHORT).show();
						}else if (pos == 3) { // Upload item selected
							Toast.makeText(pop.this, "vik selected",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		ImageView ivPic1 = (ImageView) this.findViewById(R.id.imageView1);
		ivPic1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
			}
		});

		/*Button btClickMe = (Button) this.findViewById(R.id.button1);
		btClickMe.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_RIGHT);
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
