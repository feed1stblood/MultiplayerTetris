package GameData.Figures;

import GameData.Field;


/**
 * �����η�������
 * ����:������
 * ����:2010/7/9
 */

public class OShape extends Figure {  

	/**
	 * @param args
	 */
	public OShape(Field fld)
	{
		super(fld);
		blocks=new int[][]{
			{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0},
			{0,0,0,0}
		};
	}
	
	public boolean transform()
	{
		return false;
	}
	
	

}
