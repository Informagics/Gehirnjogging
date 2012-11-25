package com.informagics.gehirnjogging.kabelbinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import com.informagics.gehirnjogging.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class kabelbinder extends Activity {
	
	int[][] Bilder=
		{
			{R.drawable.cable_straight_1,R.drawable.cable_straight_2,R.drawable.cable_straight_1,R.drawable.cable_straight_2},
			{R.drawable.cable_curve_1,R.drawable.cable_curve_2,R.drawable.cable_curve_3,R.drawable.cable_curve_4},
			{R.drawable.cable_double_1,R.drawable.cable_double_2,R.drawable.cable_double_1,R.drawable.cable_double_2},
			{R.drawable.cable_plug_1,R.drawable.cable_plug_2,R.drawable.cable_plug_3,R.drawable.cable_plug_4}
		};
	
	String[][] mapwerte = new String[5][5];
	int Clickzähler=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kabelbinder);
		
		Levelinit(1);//rand für zufalls level
	}
	
	public void Clicked(View view)
	{
		int zahl=view.getId()-R.id.i00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		String str_element[]=mapwerte[x][y].split(":");
		int rotation=Integer.parseInt(str_element[1]);
		int element=Integer.parseInt(str_element[0]);
		
		rotation=(rotation+1)%4;
		mapwerte[x][y]=String.valueOf(element)+":"+String.valueOf(rotation);
		
		((ImageView)findViewById(view.getId())).setImageResource(Bilder[element][rotation]);
		
		Clickzähler++;
		((TextView)findViewById(R.id.Clickskabel)).setText("Clicks: "+Clickzähler);
	}
	
	public void Levelinit(int Map)
	{
		String textdatei = null;
		try {
			textdatei=convertStreamToString("kabelstrecken.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] Zeilen=textdatei.split("\n");
		for(int y=0;y<5;y++){
			String[] Spalten=Zeilen[Map].split(" ");
			for(int x=0;x<5;x++){
				mapwerte[x][y]=Spalten[x+5*y];
				String str_element[]=mapwerte[x][y].split(":");
				int rotation=Integer.parseInt(str_element[1]);
				int element=Integer.parseInt(str_element[0]);
				((ImageView)findViewById(R.id.i00+x+y*6)).setImageResource(Bilder[element][rotation]);
			}
		}
	}
	
	public void Lösung()
	{
		int Baum=0;
		
		do//suche nach einem element 3 baustein (Stecker)
		{
			
		}while(Baum==1);
		
		do//Durchlauf der Map bis fehler oder zweites element 3 Baustein gefunden
		{
			
		}while(Baum==1);
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
}
