import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameData.Field;

/**
 * 
 */

/**
 * ���̿�����2
 * @author ������
 * @����:2010/7/15
 */
public class KeyController2 extends ControllerBase implements KeyListener {

	/**
	 * @param field
	 * @param fp
	 */
	public KeyController2(Field field, FieldPanel fp) {
		super(field, fp);
		// TODO Auto-generated constructor stub
	}

	public void keyPressed(KeyEvent e) {
		 switch(e.getKeyCode())
		 {
		 
		 case KeyEvent.VK_UP:		     
			 super.Transform();
			 break;
		 
		 case KeyEvent.VK_DOWN:
			 super.MoveDown();
			 break;
		 case KeyEvent.VK_LEFT:
			 super.MoveLeft();
			 break;
		 case KeyEvent.VK_RIGHT:
			 super.MoveRight();
		 }
		 fp.redisplay(fld, fld.getFigure(),false);
	 }
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	} 
}
