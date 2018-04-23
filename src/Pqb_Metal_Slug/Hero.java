package Pqb_Metal_Slug;

public class Hero extends Substance{
	
	public static final int Hero_initial_x = 200;
	
	public static final int Hero_initial_y = 350;
	
	int speed = 2;
	
	int cur_image = -1;
	
	int death_image = -1;
	
	int jump_image = -1;
	
	int squat_image = -1;
	
	boolean isSquating = false;
	
	boolean isBackgroundMove = false;
	
	
	public Hero(){
		image = Launcher.heros[0];
		cur_image = 0;
		x_pos = Hero_initial_x;
		y_pos = Hero_initial_y;
		width = image.getWidth();
		height = image.getHeight();
		health_point = 3;
	}
	
	//返回英雄x轴位置
	public int get_Hero_Speed(){
		return speed;
	}
	
	//返回英雄x轴位置
	public int get_Hero_x_pos(){
		return x_pos;
	}
	
	//控制英雄移动速度
	public void setBackgroundMove(boolean state){
		isBackgroundMove = state;
	}

	//英雄走路的动画（8张图切换），在Launcher中10ms刷新一次，因此在这里再*10即100ms刷新一次
	@Override
	public void step() {
		//TODO 自动生成的方法存根
		if(!isSquating){
			cur_image = (cur_image+1)%80;
			image = Launcher.heros[cur_image/10];
		}else{
			squat_image = (squat_image+1)%50;
			image = Launcher.heroSquat[squat_image/10];
		}
	}
	
	public void stepReverse() {
		 //TODO 自动生成的方法存根
		if(!isSquating){
			cur_image = (cur_image+1)%80;
			image = Launcher.reverseheros[cur_image/10];
		}
		else{
			squat_image = (squat_image+1)%50;
			image = Launcher.reverseheroSquat[squat_image/10];
		}
	}
	
	public void squat(boolean left, boolean right){
		if(right){
			squat_image = (squat_image+1)%50;
			image = Launcher.heroSquat[squat_image/10];
		}
		else {
			squat_image = (squat_image+1)%50;
			image = Launcher.reverseheroSquat[squat_image/10];
		}
	}
	
	public void revive()
	{
		this.y_pos = Hero_initial_y;
		death_image = -1;
		cur_image = 0;
		image = Launcher.heros[cur_image/2];
	}
	
	public boolean Death()		//死亡特效
	{
		death_image = (death_image+1)%220;
		image = Launcher.heroDeath[death_image/20];
		this.y_pos = Hero_initial_y + height - image.getHeight()-10;
		if(death_image==219)
			return true;
		return false;
	}
	
	public boolean Jump(boolean right,boolean left)		//英雄跳跃特效
	{
		jump_image = (jump_image+1)%80;
		if(right==true)
			image = Launcher.heroJump[jump_image/16];
		else
			image = Launcher.heroJumpLeft[jump_image/16];
		
		if(jump_image/16==0||jump_image/16==4)
			this.y_pos = Hero_initial_y-50;
		else if(jump_image/16==1||jump_image/16==3)
			this.y_pos = Hero_initial_y-90;
		else if(jump_image/16==2)
			this.y_pos = Hero_initial_y-140;
		if(jump_image==79)
		{
			cur_image = 0;
			image = Launcher.heros[cur_image/2];
			return true;
		}
		return false;
	}
	
	public boolean RightJump()
	{
		jump_image = (jump_image+1)%100;
		image = Launcher.heroJump[jump_image/20];
		switch(jump_image/16)
		{
		case 0:
			this.y_pos = Hero_initial_y-50;
			this.x_pos+=0;
			break;
		case 1:
			this.y_pos = Hero_initial_y-90;
			this.x_pos+=0;
			break;
		case 2:
			this.y_pos = Hero_initial_y-120;
			this.x_pos+=0;
			break;
		case 3:
			this.y_pos = Hero_initial_y-90;
			this.x_pos+=0;
			break;
		case 4:
			this.y_pos = Hero_initial_y-50;
			this.x_pos+=0;
			break;
		}
		if(jump_image==79)
		{
			cur_image = 0;
			image = Launcher.heros[cur_image/2];
			return true;
		}
		return false;
	}
	
	//控制英雄左右移动的方法，speed参数可控
	public void moveRight()
	{
		if(x_pos <= Launcher.WIDTH - 80){
			if(!isSquating || !isBackgroundMove)
				x_pos = x_pos + speed;
			else
				x_pos = x_pos + speed/2;
		}
	}
	
	public void moveLeft()
	{
		if(!isSquating)
			x_pos = x_pos - speed;
		else
			x_pos = x_pos - speed/2;
	}

	@Override
	public boolean outOfLeftBounds() {
		// TODO 自动生成的方法存根
		return x_pos<=0;
	}

	@Override
	public boolean outOfRightBounds() {
		// TODO 自动生成的方法存根
		return false;
	}
}
