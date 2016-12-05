package GameData;

/**
 * ��Ϸ������
 * ����:������
 * ����:2010/7/8
 */

import java.awt.Point;
import java.util.Random;

import javax.swing.event.EventListenerList;

import GameData.Figures.*;

public class Field {
	int Width;

	int Height;

	int[][] block;

	boolean demonstrating;
	
	//����
	public int score;
	
	protected EventListenerList listenerList = new EventListenerList(); 
	
	int pauseTime;
	
	private Figure figure;
	private Figure nextFigure;
	
	/*
	 * @param width ��
	 * @param height ��
	 */
	public Field(int width, int height) {
		this.Width = width;
		this.Height = height;
		block = new int[width][];
		for (int i = 0; i < width; i++) {
			block[i] = new int[Height];
		}
		
		figure = RandomFigure();
		nextFigure = RandomFigure();
	}

	public Field(int width, int height,boolean demonstrating) {		
		this.Width = width;
		this.Height = height;
		this.demonstrating = demonstrating;
		block = new int[width][];
		for (int i = 0; i < width; i++) {
			block[i] = new int[Height];
		}
		
		figure = RandomFigure();
		nextFigure = RandomFigure();
		
		if(demonstrating == true)
		{
			block[width/2][height-1]=1;
			block[width/2-1][height-1]=1;
			block[width/2+1][height-1]=1;
			block[width/2-1][height-2]=1;
		}
		figure.Move(Figure.down);
	}
	
	/*
	 * ��Ӽ����¼�
	 */
	public void addFieldListener(FieldListener fl)
	{
		listenerList.add(FieldListener.class, fl);
	}
	
	/*
	 * ��������
	 */
	public void MoveOn()
	{
		//�������ͣ(�Ϸ������)
		if(pauseTime>0)
		{
			pauseTime--;
		}
		else 
			figure.Move(Figure.down);
	}
	
	/*
	 *  ������һ��ͼ��
	 */
	private Figure RandomFigure()
	{
		int i = (int)(Math.random()*7);		
		switch(i)
		{
		default:
			return new OShape(this);
		case 1:
			return new TShape(this);
		case 2:
			return new IShape(this);
		case 3:
			return new JShape(this);
		case 4:
			return new LShape(this);
		case 5:
			return new SShape(this);
		case 6:
			return new ZShape(this);			
		}
	}
	
	/*
	 * ����x,y���Ƿ�Ϊ�հ�(�޷���)
	 */
	public boolean IsVacant(int x, int y) {
		if (x < 0 || x >= Width  || y >= Height)
			return false;
		else if(y>=0)
			return block[x][y]==0;
		else 
			return true;
	}

	/*
	 * ������ķ������������
	 */
	public void Attach(Figure f) {
		pauseTime = 1;
		
		if(demonstrating)
		{
			figure = nextFigure;
			nextFigure = RandomFigure();
			return;
		}
		
		Object[] listeners1 = listenerList.getListenerList();         
        for (int i = listeners1.length-2; i>=0; i-=2) {     
            if (listeners1[i]==FieldListener.class) {        
                ((FieldListener)listeners1[i+1]).Attaching(this,f);
            }     
        }
		
        if(f!=null){
        	Point[] p = f.LocationOfBlocks();
        	boolean overflow = false;
        	for (int i = 0; i < p.length; i++) {
        		//	�ж��Ƿ��Ϸ����
        		if(p[i].y>=0)
        			block[p[i].x][p[i].y] = 1;			
        		else
        			overflow = true;
        	}
        	
        	//��������¼�
        	if(overflow)
        	{
        		Object[] listeners = listenerList.getListenerList();         
        		for (int i = listeners.length-2; i>=0; i-=2) {     
        			if (listeners[i]==FieldListener.class) {        
        				((FieldListener)listeners[i+1]).OverFlow(this);
        			}     
        		}
        	}
        	
        	//�ж��Ƿ�������
        	int []indice = getFullLineIndice();
        	if(indice.length>0)
        	{
        		//���������¼�,����һ������,�����������к�
        		Object[] listeners = listenerList.getListenerList();         
        		for (int i = listeners.length-2; i>=0; i-=2) {     
        			if (listeners[i]==FieldListener.class) {        
        				((FieldListener)listeners[i+1]).LinesRemoving(this, indice);
        			}     
        		}
        		
        		switch(indice.length)
        		{
        		case 1:
        			score += 1;
        			break;
        		case 2:
        			score += 3;
        			break;
        		case 3:
        			score += 6;
        			break;
        		case 4:
        			score += 10;
        			break;
        		}
        		
        		removeLineByIndice(indice);
        	}
        	
        	
        }
		
		figure = nextFigure;
		nextFigure = RandomFigure();
		
		
	}

	/*
	 * return ����һ������,�����������к�
	 */
	protected int[] getFullLineIndice(){
		int []index = new int[4];
		int count_fullLine=0;
		for(int j=0;j<this.Height;j++)
		{
			boolean fullLine = true;
			for(int i=0;i<this.Width;i++)
			{
				if(this.IsVacant(i, j))
				{
					fullLine = false;
					break;
				}
			}
			if(fullLine)index[count_fullLine++]=j;
		}
		int []result = new int[count_fullLine];
		System.arraycopy(index, 0, result, 0, count_fullLine);
		return result;
	}
	
	/*
	 * ͨ���к�������
	 */
	protected void removeLineByIndice(int indice[])
	{
		for(int i=0;i<indice.length;i++)
		{
			for(int j=indice[i];j>0;j--)
			{
				for(int k=0;k<this.Width;k++)
				{
					block[k][j] = block[k][j-1];
				}
			}
		}
	}
	
	public void clear()
	{
		for(int i=0;i<Width;i++)
			for(int j=0;j<Height;j++)
				block[i][j] = 0;
		
	}
	
	public int getHeight() {
		return Height;
	}

	public int getWidth() {
		return Width;
	}

	public Figure getFigure() {
		return figure;
	}

	public Figure getNextFigure() {
		return nextFigure;
	}
	
	public boolean paused(){
		return pauseTime>0;
	}
	
}
