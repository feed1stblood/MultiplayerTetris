package GameData.Figures;

import java.awt.Point;
import java.util.List;

import GameData.Field;

/*
 * 图形抽象基类
 * 作者:唐宇翔
 * 日期:2010/7/7
 */



public abstract class Figure {
	protected int x;
	protected int y;
	protected int blocks[][];
	protected Field field;
	
	//移动类型定义
	public static final int left=1;
	public static final int right=2;
	public static final int down=3;
	protected static final int stay=4;
	
	public abstract boolean transform();
	public Figure(Field fld)
	{
		blocks = new int[4][];
		for(int i=0;i<4;i++)
			blocks[i] = new int[4];
		this.field = fld;
		
		x = fld.getWidth()/2-1;
		y = -2;
	}
	
	public boolean Move(int movement)
	{
		if(field.paused())
			return false;
		switch(movement)
		{
		
		case left:
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					if(blocks[i][j]>0&&!field.IsVacant(x-1+i,y+j))
						return false;
			x--;
			return true;
			
		case right:
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					if(blocks[i][j]>0&&!field.IsVacant(x+1+i,y+j))
						return false;
			x++;
			return true;
			
		case down:
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					if(blocks[i][j]>0&&!field.IsVacant(x+i,y+j+1))
					{
						field.Attach(this);
						return false;
					}					
			y++;
			return true;
			
			//判断是否已经冲突
		case stay:
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					if(blocks[i][j]>0&&!field.IsVacant(x+i,y+j))
						return false;
			return true;
		}
		return true;
	}
	public Point[] LocationOfBlocks() {
		Point[]l = new Point[4];
		int count=0;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(blocks[i][j]>0)
				{
					l[count++]=new Point(x+i,y+j);
				}
		
		return l;
	}

}
