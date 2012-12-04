package com.informagics.gehirnjogging.menue;
import com.informagics.gehirnjogging.R;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;

public class Nachschlagewerk extends Activity 
{
	
	private WebView browser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nachschlagewerk);
		browser=(WebView)findViewById(R.id.webView1);
		browser.loadUrl("file:///android_asset/Index.html");
	}

}
