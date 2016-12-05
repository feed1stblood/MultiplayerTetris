import GameData.Field;
import GameData.Figures.Figure;

/**
 * 
 */

/**
 * @author ÌÆÓîÏè
 * @ÈÕÆÚ 2010/7/15
 */
public class ControllerBase {

	protected Field fld;
	protected FieldPanel fp;
	
	//protected Figure figure;
	public ControllerBase(Field field,FieldPanel fp)
	{
		this.fld = field;
		this.fp = fp;
	}
	
	public void MoveLeft()
	{
		fld.getFigure().Move(Figure.left);
	}
	public void MoveRight()
	{
		fld.getFigure().Move(Figure.right);
	}
	public void MoveDown()
	{
		fld.getFigure().Move(Figure.down);
	}
	public void Transform()
	{
		fld.getFigure().transform();
	}
}
