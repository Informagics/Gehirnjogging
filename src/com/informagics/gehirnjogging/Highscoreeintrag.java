package com.informagics.gehirnjogging;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;

public class Highscoreeintrag
{	
	public static void txt_eintragen(String Spiel,int Punkte,Activity ac) throws IOException
	{
		FileOutputStream fos = ac.openFileOutput(Spiel, Context.MODE_PRIVATE);
		fos.write(Punkte);
		fos.close();
	}
	
	public static String txt_ausgeben(String Spiel,Activity ac) throws IOException
	{
		byte[] buffer = null;
		FileInputStream fis = ac.openFileInput(Spiel);
		fis.read(buffer);
		fis.close();
		return buffer.toString();
	}
}
