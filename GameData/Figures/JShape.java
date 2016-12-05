package GameData.Figures;

import GameData.Field;

/**
 * J���ͷ�����
 * ����:������
 * ����:2010/7/11
 */

public class JShape extends Figure {

//	��ǰ��̬
	private int current_shape;
	//������̬
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
		//ת������һ����̬
		current_shape = (current_shape + 1) % shape.length;
		blocks = shape[current_shape];
		
		//����Ƿ��ͻ
		if(super.Move(Figure.stay))
			return true;
		//����ͻ��������
		else if(this.Move(Figure.left))
			return true;
		//����ͻ��������
		else if(this.Move(Figure.right))
			return true;
		
		//�޷�����,��ԭ����ʼ��̬
		current_shape = (current_shape + shape.length -1) % shape.length;
		blocks = shape[current_shape];
		return false;
	}

}
