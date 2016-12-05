import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import GameData.Field;
import GameData.Figures.Figure;
/**
 * 
 */

/**
 * @author 唐宇翔
 * @日期:2010/7/15
 */
public class MouseController extends ControllerBase implements MouseListener, MouseMotionListener, MouseWheelListener{

	protected Field fld;
	protected FieldPanel fp;
	
	//上次事件的X值
	protected int preX;

	//protected Figure figure;
	public MouseController(Field field,FieldPanel fp)
	{
		super(field,fp);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mod = arg0.getModifiers();
		if ((mod & InputEvent.BUTTON3_MASK) != 0)
		{
			//super.Transform();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mod = arg0.getModifiers();
		if ((mod & InputEvent.BUTTON1_MASK) != 0)
		{
			preX = arg0.getX();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getX()<preX-20)
		{
			super.MoveLeft();
			preX = arg0.getX();
		}
		if(arg0.getX()>preX+20)
		{
			super.MoveRight();
			preX = arg0.getX();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		int count = arg0.getWheelRotation();
		if(count>0)
			super.MoveDown();
		else if(count<0)
			super.Transform();
	}

}
