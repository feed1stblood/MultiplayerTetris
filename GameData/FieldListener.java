/**
 * 
 */
package GameData;

import GameData.Figures.Figure;

/**
 * @作者 唐宇翔
 * DATE:2010/7/12
 * 监听消行事件接口
 */
public interface FieldListener extends java.util.EventListener{
	void Attaching(Field field,Figure figure);
	void LinesRemoving(Field field,int []indice);
	void OverFlow(Field field);
}
