package com.informagics.gehirnjogging.bitout;

import com.informagics.gehirnjogging.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class BitOut extends Activity {

	int moves=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bit_out);
	}
	
	void domove(int y,int x){		
		int x1 = x - 1, x2 = x + 1, y1 = y - 1, y2 = y + 1;

        if (y == 0)
            y1 = -1;
        else if (y == 4)
            y2 = -1;

        if (x == 0)
            x1 = -1;
        else if (x == 4)
            x2 = -1;
        
        if (x1 != -1)
        	wechsel((Button)findViewById(R.id.b00+6*y+x1));
        if (x2 != -1)
        	wechsel((Button)findViewById(R.id.b00+6*y+x2));
        if (y1 != -1)
        	wechsel((Button)findViewById(R.id.b00+6*y1+x));
        if (y2 != -1)
        	wechsel((Button)findViewById(R.id.b00+6*y2+x));
        
        wechsel((Button)findViewById(R.id.b00+6*y+x));
	}
	
	void wechsel(Button view)
	{
		if(view.getText()=="1")
			view.setText("0");
		else
			view.setText("1");
	}
	
	public void clicked(View view){
		int zahl=view.getId()-R.id.b00;
		int x=zahl%6;
		int y=(zahl-x)/6;
		
		//Button button = (Button) findViewById(view.getId());
		//Button button2=(Button)findViewById(R.id.b00+8*y+x);
		//ImageView iw;
		//button2.setText("Test");
		//Toast.makeText(getApplicationContext(), ""+y+" "+x, 100).show();
		
	    //clicked on cell (y,x)
	    domove(y,x);
	   	moves++;
	   	/*if(mode==1 && solved())
	   	{
	   		mode=0;
    	}*/
	}
}
