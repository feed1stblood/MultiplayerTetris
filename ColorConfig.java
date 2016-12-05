import java.awt.Color;

public class ColorConfig {
	public Color backgroundColor;
	public Color blockColor;
	public Color figureColor;
	public Color extraColor;
	public Color textColor;
	public ColorConfig(Color color, Color color2, Color color3, Color color4, Color color5) {
		super();
		// TODO Auto-generated constructor stub
		backgroundColor = color;
		blockColor = color2;
		extraColor = color3;
		figureColor = color4;
		textColor = color5;
	}
	public ColorConfig()
	{
		backgroundColor = Color.CYAN;	
		extraColor = Color.BLACK;
		textColor = Color.ORANGE;	
		blockColor = Color.BLUE;	
		figureColor = Color.RED;
		/*
		backgroundColor = new Color((int)Math.random()*256,(int)Math.random()*256,(int)Math.random()*256);
		blockColor = new Color((int)Math.random()*256,(int)Math.random()*256,(int)Math.random()*256);
		extraColor = new Color((int)Math.random()*256,(int)Math.random()*256,(int)Math.random()*256);
		figureColor = new Color((int)Math.random()*256,(int)Math.random()*256,(int)Math.random()*256);
		textColor =  new Color((int)Math.random()*256,(int)Math.random()*256,(int)Math.random()*256);*/
	}
	
}
