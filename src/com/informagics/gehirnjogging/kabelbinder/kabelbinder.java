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
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class kabelbinder extends Activity {
	
	int[] Bilder={R.drawable.cable_straight_1,R.drawable.cable_straight_2,
			R.drawable.cable_curve_1,R.drawable.cable_curve_2,
			R.drawable.cable_curve_3,R.drawable.cable_curve_4,
			R.drawable.cable_double_1,R.drawable.cable_double_2,
			R.drawable.cable_plug_1,R.drawable.cable_plug_2,
			R.drawable.cable_plug_3,R.drawable.cable_plug_4};
	
	int[] mapwerte = {1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kabelbinder);
		
		Levelinit(0);//rand für zufalls level
	}
	
	public void Clicked(View view){
		BitmapDrawable drawable = (BitmapDrawable) ((ImageView)findViewById(view.getId())).getDrawable();
		Bitmap result = drawable.getBitmap();

		// Rotate it to show as a landscape
		Matrix m = ((ImageView)findViewById(R.id.i00)).getImageMatrix();
		m.postRotate(90);
		result = Bitmap.createBitmap(result, 0, 0, result.getWidth(), result.getHeight(), m, true);
		((ImageView)findViewById(view.getId())).setImageBitmap(result);
	}
	
	public void Levelinit(int Map)
	{
		String textdatei = null;
		try {
			textdatei=convertStreamToString("kabelstrecken.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] Zeilen=textdatei.split("\n");
		for(int y=0;y<5;y++){
			String[] Spalten=Zeilen[Map].split(" ");
			for(int x=0;x<5;x++){
				//mapwerte[0]=Integer.parseInt(Spalten[x]);
				((ImageView)findViewById(R.id.i00+x+y*6)).setImageResource(Bilder[0]);
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
    		Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
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
    	return text; //Text zurÃ¼ckgeben
    }
}
