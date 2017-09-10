package com.tech4lifeapps.numbers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity {

	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	
	
        TextView welcomeText= (TextView) findViewById(R.id.textView_welcome);
		
		// https://www.youtube.com/watch?v=SGx03Uqn9JA LESSON 57
		// ACCESS OUR SHARED PREFERENCES
		/////////////////////////////////
		SharedPreferences getPrefs = PreferenceManager
			.getDefaultSharedPreferences(getBaseContext());
	
	//	String et = getPrefs.getString ("name", "Welcome!");
	//	if (et.length()==0) {
	//			et = "Welcome!";
	//	}
	//		welcomeText.setText(et);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);	
		String values = getPrefs.getString("list", "1");
			
			if (values.contentEquals("1")) {
				// English
				 welcomeText.setText("English");
				 imageView.setImageResource(R.drawable.gb_h200);				 
			} else if (values.contentEquals("2")) {
				// Indonesian
				 welcomeText.setText("Indonesian");
				 imageView.setImageResource(R.drawable.id_h200);		 
			} else if (values.contentEquals("3")) {
				// Khmer
				 welcomeText.setText("Khmer");
				 imageView.setImageResource(R.drawable.kh_h200);
			} else if (values.contentEquals("4")) {
				// Burmese
				 welcomeText.setText("Burmese");
				 imageView.setImageResource(R.drawable.mm_h200);
			} else if (values.contentEquals("5")) {
				// Thai
				 welcomeText.setText("Thai");
				 imageView.setImageResource(R.drawable.th_h200);
			}	 
					
		//	ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		//	Resources res = getResources();
		//	Drawable drawable = res.getDrawable(R.drawable.kh);
		//	imageView.setImageDrawable(drawable);
			
		ourSong = MediaPlayer.create(Splash.this, R.raw.morselowvol);

		// https://www.youtube.com/watch?v=SGx03Uqn9JA LESSON 57
		// ACCESS OUR SHARED PREFERENCES
		/////////////////////////////////
		
	//	SharedPreferences getPrefs = PreferenceManager
	//			.getDefaultSharedPreferences(getBaseContext());
	
		boolean music = getPrefs.getBoolean("checkbox", true);
		if (music == true) {
			ourSong.start();
		}
        // -----------------------
		
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent(
							"com.tech4lifeapps.numbers.MAINACTIVITY");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

}
