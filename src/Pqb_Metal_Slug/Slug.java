package Pqb_Metal_Slug;

import Pqb_Metal_Slug.Substance;;

public class Slug extends Substance{
	int speed;
	
	protected boolean direction;		//direction = true的话子弹往右， direction =false的话子弹往左
	
	public Slug(int x,int y, boolean _direction, String Kinds, int _speed) 	//kinds判断子弹来源从而判断子弹类型
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
		// TODO 自动生成的方法存根
		if(direction == true)
			x_pos += speed;
		else
			x_pos -= speed;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return x_pos >= 850;
	}
}
