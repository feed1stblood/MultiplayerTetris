/**
 * 
 */
package GameData;

import GameData.Figures.Figure;

/**
 * @���� ������
 * DATE:2010/7/12
 * ���������¼��ӿ�
 */
public interface FieldListener extends java.util.EventListener{
	void Attaching(Field field,Figure figure);
	void LinesRemoving(Field field,int []indice);
	void OverFlow(Field field);
}
