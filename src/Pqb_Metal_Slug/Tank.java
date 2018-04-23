package Pqb_Metal_Slug;

import java.util.Random;

public class Tank extends Substance{
	
	int cur_image;
	int death_image=-1;
	int initial_y_pos;
	protected boolean direction;
	
	public Tank(int x, int y){
		x_pos = x;
		y_pos = y;
		initial_y_pos = y;
		cur_image = 0;
		direction=false;
		image = Launcher.tank[cur_image];
		width = image.getWidth();
		height = image.getHeight();
		health_point = 20;
	}

	@Override
	public void step() {
		// TODO 自动生成的方法存根
		Random r = new Random();
		cur_image = (cur_image+1)%200;
		image = Launcher.tank[cur_image/100];
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return x_pos<=-20;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return false;
	}
	
	public boolean Death()
	{
		death_image = (death_image+1)%100;
		image = Launcher.tankDeath[death_image/10];
		this.y_pos = initial_y_pos + height - image.getHeight()-10;
		if(death_image==99)
			return true;
		return false;
	}
	//坦克射击
	public Slug[] shoot()
	{
		Random r = new Random();
		if(r.nextInt(2)==1)
		{
			Slug []slugs = new Slug[1];
			if(direction==true)
				slugs[0] = new Slug((x_pos+width/2)+50,(y_pos+height/2-10),true, "tank", 1);
			else
				slugs[0] = new Slug((x_pos+width/2)-120,(y_pos+height/2-10),false, "tank", 1);
			return slugs;
		}
		return new Slug[0];
	}


}
