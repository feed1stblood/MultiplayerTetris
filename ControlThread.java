import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

import GameData.Field;
import GameData.FieldListener;
import GameData.Figures.Figure;

public class ControlThread extends Thread implements FieldListener {
	Field fld;
	FieldPanel fp;
	public int delayTime = 500;
	String []info;
	public ControlThread(Field fld,FieldPanel fp,int delayTime,String []info)
	{
		this.fld = fld;
		this.fp = fp;
		this.delayTime = delayTime;
		this.info = info;
		fld.addFieldListener(this);
	}
	
	public void run()
	{
		while(true)
		{
			try {
				sleep(delayTime);
				fld.MoveOn();
				info[0] = "Score:" + fld.score;
				fp.setInfo(info);
				fp.redisplay(fld, fld.getFigure(),false);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void LinesRemoving(Field field, int[] indice) {

			if(indice.length == 4)
			{
				Player player;
				try {
					player = Manager.createPlayer(new File("constr_complete.wav").toURI().toURL());
			        player.prefetch();   
			        player.start();  
				} catch (NoPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			else
			{
				Player player;
				try {
					player = Manager.createPlayer(new File("Ready.wav").toURI().toURL());
			        player.prefetch();   
			        player.start();  
				} catch (NoPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		
	}

	public void OverFlow(Field field){
		info[0] = "…‘µ»º∏√Î...";
		int delayT = delayTime;
		delayTime = 5000;
		fp.setInfo(info);
		fp.redisplay(fld, fld.getFigure(),false);
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delayTime = delayT;
		fld.score -=10;			
		fld.clear();
	}

	
	public void Attaching(Field field, Figure figure) {
		// TODO Auto-generated method stub
		Player player;
		try {
			player = Manager.createPlayer(new File("usp1.wav").toURI().toURL());
	        player.prefetch();   
	        player.start();  
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
