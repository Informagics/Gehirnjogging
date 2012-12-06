package com.informagics.gehirnjogging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class InputOutput
{	
	public static void HS_int_eintragen(String Spiel,String Punkte,Context con)
	{
	      SharedPreferences settings = con.getSharedPreferences("Highscore", 0);
	      SharedPreferences.Editor editor = settings.edit();
	      editor.putString(Spiel, "");
	      editor.putString(Spiel, HS_sortieren(HS_int_auslesen(Spiel, con)+Punkte+"\n"));
	      //editor.putString(Spiel, HS_int_auslesen(Spiel, con)+Punkte+"\n");
	      editor.commit();
	}
	
	public static String HS_int_auslesen(String Spiel,Context con)
	{
		SharedPreferences settings = con.getSharedPreferences("Highscore", 0);
	    return settings.getString(Spiel, "");
	}
	
	public static String HS_sortieren(String HS)
	{
		String text = "";
		String[] textarr = HS.split("\n");
		int[] textintarr = new int[11];
		for(int i=0;i<textarr.length-2;i++)
			textintarr[i]=Integer.valueOf(textarr[i]);
		
		for (int n=textarr.length-2; n>1; n=n-1){
			for (int i=0; i<n-1; i=i+1){
				if (textintarr[i] > textintarr[i+1]){
					int a = textintarr[i];
					textintarr[i]=textintarr[i+1];
					textintarr[i+1]=a;
				}
			}
		}
		
		for(int i=0;i<textarr.length-1;i++)
			text += String.valueOf(textintarr[i])+"\n";
		
		return text;
	}
	
	//Quelle : http://stackoverflow.com/questions/4789325/android-path-to-asset-txt-file
	public static String txt_int_auslesen(String Datei,String Format,Activity ac)
	{
    	InputStream is = null;
		try {
			is = ac.getResources().getAssets().open(Datei);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Writer writer = new StringWriter();
    	char[] buffer = new char[2048];
    	try
    	{
    		Reader reader = new BufferedReader(new InputStreamReader(is, Format));
    		int n;
    		while ((n = reader.read(buffer)) != -1) //Solange das Ende der Datei noch erreicht wurd
    		{
    			writer.write(buffer, 0, n); //Schreiben buffer von 0 bis n
    		}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	finally
    	{
    		try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} //Zugriff auf die Datei schließen
    	}
    	// In Var. text schreiben und in von Bit in String wandeln
    	return writer.toString(); //Text zurückgeben
	}
}
