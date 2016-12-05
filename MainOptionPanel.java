import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import GameData.Field;
import GameData.FieldListener;
import GameData.Figures.Figure;

/**
 * @author tyx
 * @date 2010/7/15
 */
public class MainOptionPanel extends JPanel implements Runnable {

	public int count_Player = 2;
	public int game_speed;
	public int game_time;
	
	final JComboBox cbCount;
	JSlider slSpeed;
	JTextField txtTime;
	JButton btnStart;
	final JComboBox cbBgm;
	
	Field demonField;
	FieldPanel demonFP;
	
	Thread th;
	
	public MainOptionPanel(final TetrisMainFrame tmf,FieldPanel demonstrator) {
		this.setLayout(new GridLayout(5,2));
		
		demonField = new Field(demonstrator.getWidth(),demonstrator.getHeight(),true);
		this.demonFP = demonstrator;
		this.setFocusable(false);
		this.add(new Label("N players:"));
		cbCount = new JComboBox();
		cbCount.addItem(1);
		cbCount.addItem(2);
		cbCount.addItem(3);
		this.add(cbCount);
		cbCount.setSelectedIndex(2);
		cbCount.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent arg0) {
						System.out.println(cbCount.getSelectedIndex() + 1);
						tmf.ChangeCountOfPlayers(cbCount.getSelectedIndex() + 1);
						
					}
			
				}
		);
		
		
		this.add(new Label("Speed:"));
		slSpeed = new JSlider(200,800);
		this.add(slSpeed);
		
		this.add(new Label("Duration(mins):"));
		txtTime = new JTextField("5");
		this.add(txtTime);
		
		this.add(new Label("BGM:"));
		cbBgm = new JComboBox();
		cbBgm.addItem("01.MP3");
		//cbBgm.addItem("02.MP3");
		//cbBgm.addItem("03.MP3");
		cbBgm.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent arg0) {
						// TODO Auto-generated method stub
						tmf.play(new File(cbBgm.getSelectedItem().toString()));
					}			
				}
		);
		this.add(cbBgm);
		
		this.add(new Label());
		btnStart = new JButton("Start Game");
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tmf.start(Integer.parseInt(txtTime.getText()));
			}
		});
		this.add(btnStart);
		
		
		th = new Thread(this);
		th.start();
	}
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(1000-slSpeed.getValue());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			demonField.MoveOn();
			demonFP.redisplay(demonField, demonField.getFigure(),false);
			
		}
	}
	
	public void stop()
	{
		th.stop();
	}



}
