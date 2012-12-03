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
	int Clickz�hler = 0;
	int r�tselanzahl = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kabelbinder);
		
		//Levelinit((int)(Math.random()*r�tselanzahl*10)%r�tselanzahl);//rand f�r zufalls level
		Levelinit(2);
	}
	
	public void Clicked(View view)
	{
		int zahl=view.getId()-R.id.i00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		String str_element[]=mapwerte[x][y].split(":");
		int rotation=Integer.valueOf(str_element[1]);
		int element=Integer.valueOf(str_element[0]);
		
		rotation=(rotation+1)%4;
		mapwerte[x][y]=String.valueOf(element) + ":" + String.valueOf(rotation);
		
		((ImageView)findViewById(view.getId())).setImageResource(Bilder[element][rotation]);
		
		Clickz�hler++;
		
		((TextView)findViewById(R.id.Clickskabel)).setText("Clicks: "+Clickz�hler);
		L�sung();
	}
	
	public void Levelinit(int Map)
	{
		String textdatei = null;
		try {
			textdatei=convertStreamToString("kabelstrecken.map");
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
	
	public void L�sung()
	{
		int x=0,y=0;
		int element=0;
		int rotation=0;
		int rotationneu=0;
		
		do//suche nach einem Stecker Baustein (Element 3)
		{
			String str_element[]=mapwerte[x][y].split(":");
			rotation=Integer.parseInt(str_element[1]);
			element=Integer.parseInt(str_element[0]);
			
			if(element!=3)
			{
				if(x==4){ y++; x=0; }
				else x++;
			}
			
		}while(element!=3);
		
		//Debug
		((TextView)findViewById(R.id.Clickskabel)).setText("e: "+element+" x: "+x+" y: "+y+" Ra: "+rotation);
		
		do//Durchlauf der Map bis fehlerhafter Baustein oder zweiter Stecker Baustein erreicht wird
		{
			if(element==0 || element==3)
			{
				if(rotation%2==1)
					x+=rotation-2;//x manipulation
				else
					y+=rotation-1;//y manipulation
			}
			else if(element==1)
			{
				if(rotation==rotationneu)
				{
					switch (rotationneu)
					{
					case 0:
						x-=1;
						break;
					case 1:
						y+=1;
						break;
					case 2:
						x+=1;
						break;
					case 3:
						y-=1;
						break;
					}
					rotation=(rotationneu+1)%4;
				}
				else if((rotation+1)%4==rotationneu)
				{
					switch (rotationneu)
					{
					case 0:
						y+=1;
						break;
					case 1:
						x+=1;
						break;
					case 2:
						y-=1;
						break;
					case 3:
						x-=1;
						break;
					}
					rotation=(rotationneu+2)%4;
				}
				else
					return;
			}
			else
			{
				if(rotationneu%2==0)
				{
					switch (rotation)
					{
					case 0:
						rotation=1;
						x-=1;
						break;
					case 1:
						rotation=0;
						y-=1;
						break;
					case 2:
						rotation=3;
						x+=1;
						break;
					case 3:
						rotation=2;
						y+=1;
						break;
					}
				}
				else
				{
					switch (rotation)
					{
					case 0:
						rotation=3;
						x+=1;
						break;
					case 1:
						rotation=2;
						y+=1;
						break;
					case 2:
						rotation=1;
						x-=1;
						break;
					case 3:
						rotation=0;
						y-=1;
						break;
					}
				}
			}
			
			if(x==-1||x==5||y==-1||y==5)
				return;
			
			String str_element[]=mapwerte[x][y].split(":");
			rotationneu=Integer.parseInt(str_element[1]);
			element=Integer.parseInt(str_element[0]);
			
			//Debug
			((TextView)findViewById(R.id.Clickskabel)).setText(((TextView)findViewById(R.id.Clickskabel)).getText()+" | e: "+element+" x: "+x+" y: "+y+" Ra: "+rotation+" Rn: "+rotationneu);
			
			if(element == 3 && rotationneu%2==rotation%2 && rotationneu!=rotation)//ignorieren der Stecker
				break;
			else if(element == 3 && rotationneu%2==rotation%2 && rotationneu==rotation)
				return;
			
			if(element!=1 && element!=2 && rotation%2!=rotationneu%2)//falsche rotierung des n�chsten Bauteils
				return;
			
		}while(element!=3);
		((TextView)findViewById(R.id.Clickskabel)).setText(((TextView)findViewById(R.id.Clickskabel)).getText()+" "+rotation+" "+rotationneu+" Gel�st");
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
    		is.close(); //Zugriff auf die Datei schlie�en
    	}
    	// In Var. text schreiben und in von Bit in String wandeln
    	return writer.toString(); //Text zur�ckgeben
    }
}
