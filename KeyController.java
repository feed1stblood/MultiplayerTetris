

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameData.Field;
import GameData.Figures.Figure;

/**
 * 键盘控制类
 * @author 唐宇翔
 * @日期:2010/7/11
 */

public class KeyController extends ControllerBase implements KeyListener{
	//protected Figure figure;
	public KeyController(Field field,FieldPanel fp)
	{
		super(field,fp);
	}
	
	public void keyPressed(KeyEvent e) {
		 switch(e.getKeyCode())
		 {
		 
		 case KeyEvent.VK_W:		     
			 super.Transform();
			 break;
		 
		 case KeyEvent.VK_S:
			 super.MoveDown();
			 break;
		 case KeyEvent.VK_A:
			 super.MoveLeft();
			 break;
		 case KeyEvent.VK_D:
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
