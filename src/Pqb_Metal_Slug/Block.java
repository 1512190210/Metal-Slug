package Pqb_Metal_Slug;

import java.util.Random;

public class Block extends Substance{
	
	int initial_y_pos;
	
	int enemy_kind;
	
	int death_image = -1;
	
	int cur_image;
	
	public Block(int x, int y, int _enemy_kind){
		x_pos = x;
		y_pos = y;
		initial_y_pos = y;
		enemy_kind = _enemy_kind;
		if(enemy_kind == 1)      //种类1为拿刀无枪敌人，2为拿刀有枪的敌人
			image = Launcher.bucketBlock[0];
		/*else if(enemy_kind == 3)
			image = Launcher.bucketBlock[0];*/
		width = image.getWidth();
		height = image.getHeight();
		cur_image = 0;
		health_point = 5;
	}
	
	public boolean Death()
	{
		death_image = (death_image+1)%100;
		image = Launcher.bucketBlock[death_image/20];
		this.y_pos = initial_y_pos + height - image.getHeight();
		if(death_image==99)
			return true;
		return false;
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





	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

}

