package GameData.Figures;

import GameData.Field;

/**
 * J字型方块类
 * 作者:唐宇翔
 * 日期:2010/7/11
 */

public class JShape extends Figure {

//	当前形态
	private int current_shape;
	//所有形态
	private int shape[][][]=new int[][][]{
		{
			{1,1,1,0},
			{0,0,1,0},
			{0,0,0,0},
			{0,0,0,0}
		},
		{
			{0,0,1,0},
			{0,0,1,0},
			{0,1,1,0},
			{0,0,0,0}
		},
		{
			{1,0,0,0},
			{1,1,1,0},
			{0,0,0,0},
			{0,0,0,0}
		},
		{
			{0,1,1,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,0,0,0}
		}			
	};
	
	
	public JShape(Field fld) {
		super(fld);
		// TODO Auto-generated constructor stub
		current_shape = (int)(Math.random()*shape.length);
		blocks = shape[current_shape];
	}

	@Override
	public boolean transform() {
		// TODO Auto-generated method stub
		//转换到下一种形态
		current_shape = (current_shape + 1) % shape.length;
		blocks = shape[current_shape];
		
		//检查是否冲突
		if(super.Move(Figure.stay))
			return true;
		//若冲突尝试左移
		else if(this.Move(Figure.left))
			return true;
		//若冲突尝试右移
		else if(this.Move(Figure.right))
			return true;
		
		//无法变形,还原到开始形态
		current_shape = (current_shape + shape.length -1) % shape.length;
		blocks = shape[current_shape];
		return false;
	}

}
