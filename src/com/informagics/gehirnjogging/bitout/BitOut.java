package com.informagics.gehirnjogging.bitout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import com.informagics.gehirnjogging.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class BitOut extends Activity {

	/*To-Do:
	 * Fehler wenn Map im letzen Kästchen eine 0 hat
	 * Übersichtliches und ansprechendes Layout machen
	*/
	
	int moves=0;
	int rätselanzahl=14;
	int mode=0; //0=noch nicht gelöst 1=gelöst
	int[][] mapwerte = new int[5][5];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bit_out);
		
		//Levelinit((int)(Math.random()*rätselanzahl*10)%rätselanzahl);
		Levelinit(0);
	}
	
	public void Levelinit(int Map)
	{
		String textdatei = null;
		try {
			textdatei=convertStreamToString("bitout.map");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] Zeilen=textdatei.split("\n");
		for(int y=0;y<5;y++){
			String[] Spalten=Zeilen[Map].split(" ");
			for(int x=0;x<5;x++){
				mapwerte[x][y] = Spalten[x+y*5].charAt(0) - 0x30;
				((Button) findViewById(R.id.b00+x+6*y)).setBackgroundResource(R.drawable.bits_button_zero - mapwerte[x][y]);
			}
		}
	}
	
	public String convertStreamToString(String filename) throws IOException
    {
    	InputStream is = getResources().getAssets().open(filename);
    	Writer writer = new StringWriter();
    	char[] buffer = new char[2048];
    	try
    	{
    		Reader reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
    		int n;
    		while ((n = reader.read(buffer)) != -1) //Solange das Ende der Datei noch erreicht wurd
    		{
    			writer.write(buffer, 0, n); //Schreiben buffer von 0 bis n
    		}
    	}
    	finally
    	{
    		is.close(); //Zugriff auf die Datei schließen
    	}
    	String text = writer.toString(); // In Var. text schreiben und in von Bit in String wandeln
    	return text; //Text zurückgeben
    }
	
	void domove(int y,int x){		
		int x1 = x - 1, x2 = x + 1, y1 = y - 1, y2 = y + 1;
        
        if (x > 0)
        	wechsel((Button)findViewById(R.id.b00+6*y+x1));
        if (x < 4)
        	wechsel((Button)findViewById(R.id.b00+6*y+x2));
        if (y > 0)
        	wechsel((Button)findViewById(R.id.b00+6*y1+x));
        if (y < 4)
        	wechsel((Button)findViewById(R.id.b00+6*y2+x));
        
        wechsel((Button)findViewById(R.id.b00+6*y+x));
	}
	
	void wechsel(Button view)
	{
		int zahl=view.getId()-R.id.b00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		mapwerte[x][y] = (mapwerte[x][y] + 1) % 2;
		
		if(mapwerte[x][y] == 1)
			view.setBackgroundResource(R.drawable.bits_button_one);
		else
			view.setBackgroundResource(R.drawable.bits_button_zero);
	}
	
	public void clicked(View view){
		int zahl=view.getId()-R.id.b00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
	    //clicked on cell (y,x)
	   	if(mode==0)
	   	{
    		moves++;
    		((TextView) findViewById(R.id.Clicks)).setText("Clicks: "+moves);
    		domove(y,x);
    	}
	   	solved();
	}
	
	public void solved()
	{
		for(int y=0;y<5;y++){
			for(int x=0;x<5;x++){
				if(mapwerte[x][y] == 1)
					return;
			}
		}
		Toast.makeText(getApplicationContext(), "gelöst", Toast.LENGTH_SHORT).show();
		mode=1;
	}
}
