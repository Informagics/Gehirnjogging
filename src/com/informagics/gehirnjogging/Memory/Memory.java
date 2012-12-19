package com.informagics.gehirnjogging.Memory;

import com.informagics.gehirnjogging.InputOutput;
import com.informagics.gehirnjogging.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Memory extends Activity
{
	int[][] Bilder=
	{
			{R.drawable.mem_card_bin,R.drawable.mem_card_coax,R.drawable.mem_card_dozentoman,R.drawable.mem_card_hdd},
			{R.drawable.mem_card_rj45,R.drawable.mem_card_fcard,R.drawable.mem_card_ram,R.drawable.mem_card_mouse}		
	};
	
	MediaPlayer click,over;
   
	int Kartenfeld[][] = new int[4][4];    
	int compare[]=new int [2];
	int check=0,g=0,score=0,finish=0,mode=1;
	int reset[]=new int [2];	
	
	final Handler _newDelayHandler = new Handler();
	
	 final Runnable _Delay= new Runnable()
	 {
		 public void run()
		 {
			 Auswertung();
		 }
	 };
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        click=MediaPlayer.create(this, R.raw.menuepunkt_auswahl);  
        over=MediaPlayer.create(this, R.raw.ratsel_gewonnen);  
        Spielfeld();
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    void Spielfeld()
    {
    	int i=0,x=0,y=0,j=0;
    	 
     	
     	for(i=0;i<=3;i++)
    	 {
    		 for(j=0;j<=3;j++)
    		 {
    		 	Kartenfeld[i][j]=0;
    		 }
    	 }
     	
    	 for(i=0;i<=8;i++)
    	 {
    		 for(j=1;j<=2;j++)
    		 {
    			 do
    			 {
    				 
    				 x=(int) (Math.random() * 4)%4;
    				 y=(int) (Math.random() * 4)%4;
    			 
    			 }while(Kartenfeld[x][y]!=0);
    			 
    			 Kartenfeld[x][y]=i;
    		 }
    	 }
    }
    
    
    
    public void Clicked(View view)
    {
    	
    	int zahl=view.getId()-R.id.c00;
		int x=zahl%5;
		int y=(zahl-x)/5;
        
		if(view.getId()!=reset[0] && check!=2)
		{
			
	        
	        
			if(Kartenfeld[x][y]==1)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[0][0]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
			else if(Kartenfeld[x][y]==2)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[0][1]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
			else if(Kartenfeld[x][y]==3)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[0][2]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;		
			}
			else if(Kartenfeld[x][y]==4)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[0][3]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
			else if(Kartenfeld[x][y]==5)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[1][0]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;	
			}
			else if(Kartenfeld[x][y]==6)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[1][1]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
			else if(Kartenfeld[x][y]==7)
			{
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[1][2]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
			else if(Kartenfeld[x][y]==8)
			{	
				((ImageView)findViewById(view.getId())).setImageResource(Bilder[1][3]);
				click.start();
				reset[check]=view.getId();
				compare[check]=Kartenfeld[x][y];
				check++;
			}
		}
		
		
		if(check==2)
		{
			if(g==0)
			{
			g=1;
			_newDelayHandler.postDelayed(_Delay, 1000);
			}
			
		}
		else if(check>2)
		{
			return;
		}		
	}
    
    public void Auswertung()
	{ 	
		if(compare[0]==compare[1])
		{
			((ImageView)findViewById(reset[0])).setVisibility(4);
			((ImageView)findViewById(reset[1])).setVisibility(4);
			((TextView)findViewById(R.id.txtMemoryScore)).setText("Score : " + String.valueOf(score+=5));
			
			finish++;
			g=0;
			check=0;
			
			if(finish==8)
			{
				gameend();
			}
			
		}
		else
		{
			((ImageView)findViewById(reset[0])).setImageResource(R.drawable.mem_card_back);
			((ImageView)findViewById(reset[1])).setImageResource(R.drawable.mem_card_back);
			
			if(score>0)
			((TextView)findViewById(R.id.txtMemoryScore)).setText("Score : " + String.valueOf(score-=1));
			
			g=0;
			check=0;
		}		
	}
    
    public void gameend()
    {
    	((LinearLayout)findViewById(R.id.LinearLayoutMemory3)).setBackgroundResource(R.drawable.background_memory_over);
    	over.start();
    	((ImageView)findViewById(R.id.IMVRetryMem)).setVisibility(1);
    	InputOutput.HS_int_eintragen("memory",String.valueOf(score),this);
    	mode=0;
    }
    
    public void Refresh(View view)
    {
    	if(mode==0)
    	{
    		score=0;
	    	((TextView)findViewById(R.id.txtMemoryScore)).setText("Score : " + String.valueOf(score));
	    	finish=0;
	    	compare[0]=0;
	    	compare[1]=1;
	    	reset[0]=0;
	    	reset[1]=1;
	    	((LinearLayout)findViewById(R.id.LinearLayoutMemory3)).setBackgroundResource(R.drawable.background_memory);
	    	((ImageView)findViewById(R.id.c00)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c00)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c01)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c01)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c02)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c02)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c03)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c03)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c10)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c10)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c11)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c11)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c12)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c12)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c13)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c13)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c20)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c20)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c21)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c21)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c22)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c22)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c23)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c23)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c30)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c30)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c31)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c31)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c32)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c32)).setVisibility(1);
	    	((ImageView)findViewById(R.id.c33)).setImageResource(R.drawable.mem_card_back);
	    	((ImageView)findViewById(R.id.c33)).setVisibility(1);
	    	((ImageView)findViewById(R.id.IMVRetryMem)).setVisibility(4);
	    	mode=1;
	    	Spielfeld();
    	}
    }
}