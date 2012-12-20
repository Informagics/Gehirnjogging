package com.informagics.gehirnjogging;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.app.Activity;

public class Highscore extends Activity {
	
	MediaPlayer click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);
	
		((TextView)findViewById(R.id.txt_HS_Titel)).setText("Kabelbinder");
		((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("cable",this));
	}
	
	public void tabele_change(View view)
	{
		click.start();
		switch(view.getId())
		{
		case R.id.btn_BitOut:
			((TextView)findViewById(R.id.txt_HS_Titel)).setText("BitOut");
			((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("bitout",this));
			break;
		case R.id.btn_Cable:
			((TextView)findViewById(R.id.txt_HS_Titel)).setText("Kabelbinder");
			((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("cable",this));
			break;
		case R.id.btn_Quiz:
			((TextView)findViewById(R.id.txt_HS_Titel)).setText("Quiz");
			((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("quiz",this));
			break;
		case R.id.btn_Memory:
			((TextView)findViewById(R.id.txt_HS_Titel)).setText("Memory");
			((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("memory",this));
			break;
		case R.id.btn_Mathe:
			((TextView)findViewById(R.id.txt_HS_Titel)).setText("Mathe");
			((TextView)findViewById(R.id.txt_HS)).setText(InputOutput.HS_int_auslesen("mathe",this));
			break;
		}
	}
}
