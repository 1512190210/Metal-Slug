package Pqb_Metal_Slug;

import Pqb_Metal_Slug.Substance;;

public class Slug extends Substance{
	int speed;
	
	protected boolean direction;		//direction = true�Ļ��ӵ����ң� direction =false�Ļ��ӵ�����
	
	public Slug(int x,int y, boolean _direction, String Kinds, int _speed) 	//kinds�ж��ӵ���Դ�Ӷ��ж��ӵ�����
	{
		x_pos = x;
		y_pos = y;
		if(Kinds=="hero"&&_direction==true)
			image = Launcher.slug;
		else if(Kinds=="hero"&&_direction==false)
			image = Launcher.slug_left;
		else if(Kinds=="enemy"&&_direction==true)
			image = Launcher.enemyslug;
		else if(Kinds=="enemy"&&_direction==false)
			image = Launcher.enemyslug;
		else if(Kinds=="tank"&&_direction==true)
			image = Launcher.tankslug;
		else if(Kinds=="tank"&&_direction==false)
			image = Launcher.tankslug;
		else
			image = Launcher.enemyslug;
		width = image.getWidth();
		height = image.getHeight();
		health_point = 1;
		direction = _direction;
		speed = _speed;
	}

	@Override
	public void step() {
		// TODO �Զ����ɵķ������
		if(direction == true)
			x_pos += speed;
		else
			x_pos -= speed;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO �Զ����ɵķ������
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO �Զ����ɵķ������
		return x_pos >= 850;
	}
}
