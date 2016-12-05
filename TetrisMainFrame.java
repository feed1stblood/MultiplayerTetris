import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.media.CannotRealizeException;   
import javax.media.Manager;   
import javax.media.NoPlayerException;   
import javax.media.Player; 

import GameData.Field;
import GameData.Figures.Figure;
/**
 * 
 * @author ÌÆÓîÏè
 * @ÈÕÆÚ:2010/7/11
 */
public class TetrisMainFrame extends JFrame {

	int count_Player = 3;
	
	Field []fld;
	Figure []figure;
	FieldPanel []fp;
	PlayerConfig []pcfg;
	ControllerBase []kc;
	ControlThread []ct;
	MainOptionPanel option;
	JPanel north;
	JPanel south;
	
	Player player;
	
	String description[][]={
			{"Score:0","W-Trans","S-QckDrop","A-Left","D-Right"},
			{"Score:0","Up-Trans","Down-QckDrop","Left","Right"},
			{"Score:0","WhlUp-Trans","WhlDwn-QckDrop","Drag-Left,Right"}
	};
	
	public TetrisMainFrame(){
		this.setBounds(0, 200, 1270, 500);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		north = new JPanel();
		north.setLayout(new GridLayout(1,4,10,5));
		south = new JPanel();
		south.setLayout(new GridLayout(1,4,10,5));
		
		
		
		fp = new FieldPanel[4];
		fld = new Field[4];
		pcfg = new PlayerConfig[3];
		for(int i=0;i<4;i++)
		{
			fld[i] = new Field(12, 16);
			fp[i] = new FieldPanel(12,16,20);
			north.add(fp[i]);				
		}
		for(int i=0;i<3;i++)
			pcfg[i] = new PlayerConfig(this,fp[i+1],description[i]);
		option = new MainOptionPanel(this,fp[0]);
		south.add(option);
		south.add(pcfg[0]);
		south.add(pcfg[1]);
		south.add(pcfg[2]);
		//this.add(arg0);
		
		this.add("Center",north);
		this.add("South",south);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		this.setVisible(true);
		
		for(int i=0;i<3;i++)
			pcfg[i].redraw();
		
		
		this.play(new File("01.mp3"));
		
		
	}
	
	public void start(final int time)
	{
		for(int i=0;i<count_Player;i++)
			pcfg[i].stop();
		option.stop();
		
		this.remove(south);
		north.remove(fp[0]);
		this.setSize(this.getSize().width - (307+7), 360);
		kc = new ControllerBase[count_Player];
		ct = new ControlThread[count_Player];
		
		
		for(int i=0;i<count_Player;i++)
		{
			fld[i] = new Field(12, 16);
			//fp[i].redisplay(fld[i], fld[i].getFigure());
			ct[i] = new ControlThread(fld[i],fp[i+1],1000-option.slSpeed.getValue(),description[i]);
			ct[i].start();
		}
		
		kc[0] = new KeyController(fld[0],fp[1]);
		if(count_Player>1)
			kc[1] = new KeyController2(fld[1],fp[2]);
		if(count_Player>2)
		kc[2] = new MouseController(fld[2],fp[3]);
		
		this.requestFocus();
		this.addKeyListener((KeyListener) kc[0]);
		/*
		fp[0].addKeyListener((KeyListener) kc[0]);
		fp[1].addKeyListener((KeyListener) kc[0]);
		fp[2].addKeyListener((KeyListener) kc[0]);		
		fp[3].addKeyListener((KeyListener) kc[0]);
		north.addKeyListener((KeyListener) kc[0]);
		south.addKeyListener((KeyListener) kc[0]);
		option.addKeyListener((KeyListener) kc[0]);
		pcfg[0].addKeyListener((KeyListener) kc[0]);
		pcfg[1].addKeyListener((KeyListener) kc[0]);
		pcfg[2].addKeyListener((KeyListener) kc[0]);
		*/
		
		if(count_Player>1)
			this.addKeyListener((KeyListener) kc[1]);
		if(count_Player>2)
		{
			this.addMouseListener((MouseListener) kc[2]);
			this.addMouseMotionListener((MouseMotionListener) kc[2]);
			this.addMouseWheelListener((MouseWheelListener) kc[2]);
		}
		
		Thread th = new Thread(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(time*60000);
					GameOver();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		th.start();
	}

	public void ChangeCountOfPlayers(int count)
	{
		if(count<count_Player)
		{
			for(int i=count;i<count_Player;i++)
			{
				north.remove(fp[i+1]);
				south.remove(pcfg[i]);
				pcfg[i].stop();
			}
			this.setSize(this.getSize().width - (count_Player-count)*(307+10), this.getSize().height);
			count_Player = count;
		}
		else
		{
			for(int i=count_Player;i<count;i++)
			{
				north.add(fp[i+1]);
				south.add(pcfg[i]);
				pcfg[i].start();
			}
			this.setSize(this.getSize().width - (count_Player-count)*(307+10), this.getSize().height);
			count_Player = count;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(9*3/2-0.5);
		new TetrisMainFrame();
	}

    public void play(File mediaFile)  {     
        if(player!=null)
        	player.stop();
		try {
			player = Manager.createPlayer(mediaFile.toURI().toURL());
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
    
    public final void GameOver()
    {
    	ct[0].stop();
    	int maxScore=fld[0].score;
    	int index=0;
    	for(int i=1;i<count_Player;i++){
    		ct[i].stop();
    		if(maxScore<fld[i].score){
    			maxScore = fld[i].score;
    			index = i;
    		}
    	}
		JOptionPane.showMessageDialog(null,"Game lasts "+option.txtTime.getText()+" minutes, Top Player is: Player"+(index+1)
				+"("+maxScore+" points)");
    }
    
}
