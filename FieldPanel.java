import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import GameData.Field;
import GameData.Figures.Figure;


/*
 * 游戏版面类
 * 作者:唐宇翔
 * 日期:2010/7/9
 */

public class FieldPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	//额外宽度(单位:像素),用于显示分数等信息
	protected static final int extrawidth = 60;
	
	//private JLabel lbScore;
	
	//边框宽度
	protected int borderWidth = 3;
		
		
	private Image oimg;

	private Graphics og;
	
	//容器长(非本Panel,指游戏容器格数)
	private int width;
	//容器宽(非本Panel,指游戏容器格数)
	private int height;
	//每格宽多少像素
	private int cellwidth;
	
	ColorConfig colorConfig;
	
	String[] info;

	public FieldPanel(int Width,int Height,int CellWidth){
		this.width = Width;
		this.height = Height;
		this.cellwidth = CellWidth;
		/* 设置大小和布局 */
		//System.out.println(width*CellWidth+extrawidth + borderWidth*2+1+","+ (Height*CellWidth + borderWidth*2));
		this.setSize(width*CellWidth+extrawidth + borderWidth*2+1, Height*CellWidth + borderWidth*2);
		//this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.setFocusable(false);
		
		//废弃的label控件,直接在画板上输出文字显示分数
		//lbScore = new JLabel("Score:0");
		//lbScore.setBounds(width*CellWidth+30, 5, 30, 20);
		//add(lbScore);
		colorConfig = new ColorConfig();
		
		this.setVisible(true);
		
		
	}

	/**
	 * 重新显示 Ground, Shape
	 * 
	 * @param ground
	 * @param shape
	 */
	public synchronized void redisplay(Field field, Figure figure, boolean drawGrid) {

		/* 重新显示 */
		if (og == null) 
		{
			oimg = createImage(getSize().width, getSize().height);
			if (oimg != null)
				og = oimg.getGraphics();
		}
		if (og != null) 
		{
			og.setColor(colorConfig.backgroundColor);
			og.fillRect(borderWidth, borderWidth,getSize().width, getSize().height);
			//og.fillRect(borderWidth, borderWidth, width*cellwidth, height*cellwidth);
			
			og.setColor(colorConfig.textColor);
			for(int i=0;i<borderWidth;i++)
				og.drawRect(i, i, getSize().width-2*i, getSize().height-2*i);
			
			//画右边的分数信息部分
			og.setColor(colorConfig.extraColor);
			og.fillRect(width*cellwidth+borderWidth+1, borderWidth, extrawidth, height*cellwidth);
			
			
			og.setColor(colorConfig.textColor);
			if(info!=null)
				for(int i=0;i<info.length;i++)
					og.drawString(info[i], width*cellwidth+borderWidth+2, 15+i*15);
			
			og.setColor(colorConfig.blockColor);
			for(int i=0;i<width;i++)
			{
				for(int j=0;j<height;j++)
				{
					if(!field.IsVacant( i, j))
					{
						og.fillRect(i*cellwidth+borderWidth, j*cellwidth+borderWidth, cellwidth, cellwidth);
					}
					else if(drawGrid)
					{
						og.drawRect(i*cellwidth+borderWidth, j*cellwidth+borderWidth, cellwidth, cellwidth);
					}
				}
			}
			if (figure != null)
			{
				og.setColor(colorConfig.figureColor);
				Point []pnt = figure.LocationOfBlocks();
				
				//System.out.println(this.getSize().width+","+this.getSize().height);
				//og.fillRect(pnt[2].x*10, pnt[2].y*20, pnt[2].x*10+9, pnt[2].y*20+19);
				for(int i=0;i<pnt.length;i++)
				{
					og.fillRect(pnt[i].x*cellwidth+borderWidth, pnt[i].y*cellwidth+borderWidth, cellwidth,cellwidth);
				}
			}
			this.paint(this.getGraphics());
			;
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(oimg, 0, 0, this);
	}

	public void setColorConfig(ColorConfig colorConfig) {
		this.colorConfig = colorConfig;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setInfo(String[] info) {
		this.info = info;
	}
	
	


}
