import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import GameData.Field;


/**
 * 
 */

/**
 * @author tyx
 * @Date:2010/7/15
 */
public class PlayerConfig extends JPanel implements Runnable {
	ColorConfig ccfg;
	FieldPanel fp;
	JButton backgroundColor;
	JButton blockColor;
	JButton figureColor;
	JButton extraColor;
	JButton textColor;
	TetrisMainFrame tmf;
	Field demonstrator;
	int ctrlStyle;
	//JComboBox cbCtrlStyle;
	Checkbox chbDrawGrid;
	//how to control
	String[] description;
	Thread th;
	public PlayerConfig(final TetrisMainFrame tmf,final FieldPanel fp,final String[] description) {
		this.tmf = tmf;
		this.fp = fp;
		this.description = description;
		ccfg = new ColorConfig();
		this.setLayout(new GridLayout(3,2));
		this.setFocusable(false);
		demonstrator = new Field(fp.getWidth(),fp.getHeight(),true);
		
		fp.setInfo(description);
		
		backgroundColor = new JButton("Background Color");
		backgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(
						tmf, "Select Color", Color.DARK_GRAY);
				if (color != null) {
					ccfg.backgroundColor = color;
					fp.setColorConfig(ccfg);
					redraw();
				}
			}
		});
		this.add(backgroundColor);
		
		blockColor = new JButton("Bottom Blocks Color");
		blockColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(
						tmf, "Select Color", Color.DARK_GRAY);
				if (color != null) {
					ccfg.blockColor = color;
					fp.setColorConfig(ccfg);
					redraw();
				}
			}
		});
		this.add(blockColor);
		
		figureColor = new JButton("Droping Block Color");
		figureColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(
						tmf, "Select Color", Color.DARK_GRAY);
				if (color != null) {
					ccfg.figureColor = color;
					fp.setColorConfig(ccfg);
					redraw();
				}
			}
		});
		this.add(figureColor);
		
		extraColor = new JButton("Info Panel Color");
		extraColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(
						tmf, "Select Color", Color.DARK_GRAY);
				if (color != null) {
					ccfg.extraColor = color;
					fp.setColorConfig(ccfg);
					redraw();
				}
			}
		});
		this.add(extraColor);
		
		JButton textColor;
		textColor = new JButton("Text Color");
		textColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(
						tmf, "Select Color", Color.DARK_GRAY);
				if (color != null) {
					ccfg.textColor = color;
					fp.setColorConfig(ccfg);
					redraw();
				}
			}
		});
		this.add(textColor);
		
		//cbCtrlStyle = new JComboBox();
		
		chbDrawGrid = new Checkbox("Grid On");
		this.add(chbDrawGrid);
		
		
		redraw();
		
		start();
	}
	
	public void redraw()
	{

		
		
		fp.redisplay(demonstrator, demonstrator.getFigure(),chbDrawGrid.getState());
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			redraw();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void stop()
	{
		th.stop();
	}
	public void start()
	{
		th = new Thread(this);
		th.start();
	}


}
