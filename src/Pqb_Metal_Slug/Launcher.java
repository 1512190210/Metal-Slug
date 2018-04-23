package Pqb_Metal_Slug;

import java.util.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.event.*;

import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;

import Pqb_Metal_Slug.Launcher;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Launcher extends JPanel{
	//����ͼƬ�Ĵ�С863*560
    public static final int WIDTH = 863;  
    public static final int HEIGHT = 560;  
    //��Ϸ����̶���С880*560  
    public static final int FRAME_WIDTH = 863;  
    public static final int FRAME_HEIGHT = 560;  
    
    public static BufferedImage[] heros = new BufferedImage[8];
    public static BufferedImage[] reverseheros = new BufferedImage[8];
    public static BufferedImage[] heroDeath = new BufferedImage[11];
    public static BufferedImage[] heroJump = new BufferedImage[5];
    public static BufferedImage[] heroJumpLeft = new BufferedImage[5];
    public static BufferedImage[] heroSquat = new BufferedImage[5];
    public static BufferedImage[] reverseheroSquat = new BufferedImage[5];
    public static BufferedImage[] first_enemies = new BufferedImage[2];
    public static BufferedImage[] second_enemies = new BufferedImage[6];
    public static BufferedImage[] reverse_second_enemies = new BufferedImage[6];
    public static BufferedImage[] enemyAssassinate = new BufferedImage[3];
    public static BufferedImage[] enemyDeath = new BufferedImage[5];
    public static BufferedImage[] reverse_enemyAssassinate = new BufferedImage[3];
    public static BufferedImage[] tank = new BufferedImage[2];
    public static BufferedImage[] tankDeath = new BufferedImage[10];
    public static BufferedImage[] bucketBlock = new BufferedImage[5];
    public static BufferedImage slug;
    public static BufferedImage slug_left;
    public static BufferedImage enemyslug;
    public static BufferedImage tankslug;
    public static BufferedImage background; 
    public static BufferedImage staterow;
    public static BufferedImage[] lifenum = new BufferedImage[3];
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage gameover;
    
    
    enum Hero_State{
    	right_slugs, no_slugs, left_slugs
    }
    
    Hero_State hero_state = Hero_State.right_slugs;
    
    //��Ϸ״̬
    enum State{
    	start, pause, game_over, running
    }
    
    Boolean isDeath = false; 	//�ж�һ����������ʧ
    Boolean isJumping = false;	//�ж��Ƿ�����Ծ;
    Boolean isRight = false, isLeft = false; //�ж�ĿǰӢ�۷���
    Boolean Right = false, Left = false, Up = false, Shooting = false;
    int background_x_pos, kill_enemy_count, kill_tank_count;
    int deathTime;
    State current_state = State.start;
    public Hero hero = new Hero();
    Slug []slugs = {};
    Slug []enemyslugs = {};
    Slug []tankslugs = {};
    Enemy []enemies = {};
    Tank []tanks = {};
    Block []blocks= {};
    
    //��̬����������̬�����ĳ�ʼ�����ھ�̬���ڱ���
    static{  
	    try{
		    heros[0] = ImageIO.read(Launcher.class.getResource("/images/hero-3.png"));
		    heros[1] = ImageIO.read(Launcher.class.getResource("/images/hero-2.png"));
		    heros[2] = ImageIO.read(Launcher.class.getResource("/images/hero-1.png"));
		    heros[3] = ImageIO.read(Launcher.class.getResource("/images/hero0.png"));
		    heros[4] = ImageIO.read(Launcher.class.getResource("/images/hero1.png"));
		    heros[5] = ImageIO.read(Launcher.class.getResource("/images/hero2.png"));
		    heros[6] = ImageIO.read(Launcher.class.getResource("/images/hero3.png"));
		    heros[7] = ImageIO.read(Launcher.class.getResource("/images/hero4.png"));
		    reverseheros[0] = ImageIO.read(Launcher.class.getResource("/images/hero-3_2.png"));
		    reverseheros[1] = ImageIO.read(Launcher.class.getResource("/images/hero-2_2.png"));
		    reverseheros[2] = ImageIO.read(Launcher.class.getResource("/images/hero-1_2.png"));
		    reverseheros[3] = ImageIO.read(Launcher.class.getResource("/images/hero0_2.png"));
		    reverseheros[4] = ImageIO.read(Launcher.class.getResource("/images/hero1_2.png"));
		    reverseheros[5] = ImageIO.read(Launcher.class.getResource("/images/hero2_2.png"));
		    reverseheros[6] = ImageIO.read(Launcher.class.getResource("/images/hero3_2.png"));
		    reverseheros[7] = ImageIO.read(Launcher.class.getResource("/images/hero4_2.png"));
		    heroDeath[0] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath0.png"));
		    heroDeath[1] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath1.png"));
		    heroDeath[2] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath2.png"));
		    heroDeath[3] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath3.png"));
		    heroDeath[4] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath4.png"));
		    heroDeath[5] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath5.png"));
		    heroDeath[6] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath6.png"));
		    heroDeath[7] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath7.png"));
		    heroDeath[8] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath8.png"));
		    heroDeath[9] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath9.png"));
		    heroDeath[10] = ImageIO.read(Launcher.class.getResource("/images/HeroDeath10.png"));
		    heroJump[0] = ImageIO.read(Launcher.class.getResource("/images/HeroJump0.png"));
		    heroJump[1] = ImageIO.read(Launcher.class.getResource("/images/HeroJump1.png"));
		    heroJump[2] = ImageIO.read(Launcher.class.getResource("/images/HeroJump2.png"));
		    heroJump[3] = ImageIO.read(Launcher.class.getResource("/images/HeroJump3.png"));
		    heroJump[4] = ImageIO.read(Launcher.class.getResource("/images/HeroJump4.png"));
		    heroJumpLeft[0] = ImageIO.read(Launcher.class.getResource("/images/HeroJumpLeft0.png"));
		    heroJumpLeft[1] = ImageIO.read(Launcher.class.getResource("/images/HeroJumpLeft1.png"));
		    heroJumpLeft[2] = ImageIO.read(Launcher.class.getResource("/images/HeroJumpLeft2.png"));
		    heroJumpLeft[3] = ImageIO.read(Launcher.class.getResource("/images/HeroJumpLeft3.png"));
		    heroJumpLeft[4] = ImageIO.read(Launcher.class.getResource("/images/HeroJumpLeft4.png"));
		    heroSquat[0] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat1.png"));
		    heroSquat[1] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat2.png"));
		    heroSquat[2] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat3.png"));
		    heroSquat[3] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat4.png"));
		    heroSquat[4] = ImageIO.read(Launcher.class.getResource("/images/HeroSquat5.png"));
		    reverseheroSquat[0] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat1.png"));
		    reverseheroSquat[1] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat2.png"));
		    reverseheroSquat[2] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat3.png"));
		    reverseheroSquat[3] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat4.png"));
		    reverseheroSquat[4] = ImageIO.read(Launcher.class.getResource("/images/reverseHeroSquat5.png"));
		    first_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/enemy_1_0.png"));
		    first_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/enemy_1_1.png"));
		    second_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_-2.png"));
		    second_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_-1.png"));
		    second_enemies[2]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_0.png"));
		    second_enemies[3]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_1.png"));
		    second_enemies[4]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_2.png"));
		    second_enemies[5]  = ImageIO.read(Launcher.class.getResource("/images/enemy_2_3.png"));
		    reverse_second_enemies[0]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_-2.png"));
		    reverse_second_enemies[1]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_-1.png"));
		    reverse_second_enemies[2]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_0.png"));
		    reverse_second_enemies[3]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_1.png"));
		    reverse_second_enemies[4]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_2.png"));
		    reverse_second_enemies[5]  = ImageIO.read(Launcher.class.getResource("/images/reverseEnemy_2_3.png"));
		    enemyAssassinate[0] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate0.png"));
		    enemyAssassinate[1] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate1.png"));
		    enemyAssassinate[2] = ImageIO.read(Launcher.class.getResource("/images/EnemyAssassinate2.png"));
		    reverse_enemyAssassinate[0] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate0.png"));
		    reverse_enemyAssassinate[1] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate1.png"));
		    reverse_enemyAssassinate[2] = ImageIO.read(Launcher.class.getResource("/images/reverseEnemyAssassinate2.png"));
		    enemyDeath[0] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath0.png"));
		    enemyDeath[1] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath1.png"));
		    enemyDeath[2] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath2.png"));
		    enemyDeath[3] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath3.png"));
		    enemyDeath[4] = ImageIO.read(Launcher.class.getResource("/images/EnemyDeath4.png"));
		    tank[0] = ImageIO.read(Launcher.class.getResource("/images/tank1.png"));
		    tank[1] = ImageIO.read(Launcher.class.getResource("/images/tank2.png"));
		    tankDeath[0]=ImageIO.read(Launcher.class.getResource("/images/tank_death.png"));
		    tankDeath[1]=ImageIO.read(Launcher.class.getResource("/images/tank_death1.png"));
		    tankDeath[2]=ImageIO.read(Launcher.class.getResource("/images/tank_death2.png"));
		    tankDeath[3]=ImageIO.read(Launcher.class.getResource("/images/tank_death3.png"));
		    tankDeath[4]=ImageIO.read(Launcher.class.getResource("/images/tank_death4.png"));
		    tankDeath[5]=ImageIO.read(Launcher.class.getResource("/images/tank_death5.png"));
		    tankDeath[6]=ImageIO.read(Launcher.class.getResource("/images/tank_death6.png"));
		    tankDeath[7]=ImageIO.read(Launcher.class.getResource("/images/tank_death7.png"));
		    tankDeath[8]=ImageIO.read(Launcher.class.getResource("/images/tank_death8.png"));
		    tankDeath[9]=ImageIO.read(Launcher.class.getResource("/images/tank_death9.png"));
		    background = ImageIO.read(Launcher.class.getResource("/images/background.png"));
		    staterow = ImageIO.read(Launcher.class.getResource("/images/StateRow0.png"));
		    lifenum[0] = ImageIO.read(Launcher.class.getResource("/images/life_0.png"));
		    lifenum[1] = ImageIO.read(Launcher.class.getResource("/images/life_1.png"));
		    lifenum[2] = ImageIO.read(Launcher.class.getResource("/images/life_2.png"));
		    bucketBlock[0]=ImageIO.read(Launcher.class.getResource("/images/bucketBlock.png"));
		    bucketBlock[1]=ImageIO.read(Launcher.class.getResource("/images/bucketBlock1.png"));
		    bucketBlock[2]=ImageIO.read(Launcher.class.getResource("/images/bucketBlock2.png"));
		    bucketBlock[3]=ImageIO.read(Launcher.class.getResource("/images/bucketBlock2.png"));
		    bucketBlock[4]=ImageIO.read(Launcher.class.getResource("/images/bucketBlock2.png"));
		    slug = ImageIO.read(Launcher.class.getResource("/images/slug.png"));
		    slug_left = ImageIO.read(Launcher.class.getResource("/images/slug_left.png"));
		    enemyslug = ImageIO.read(Launcher.class.getResource("/images/enemySlug.png"));
		    tankslug=ImageIO.read(Launcher.class.getResource("/images/tankSlug.png"));
		    /*pause = ImageIO.read(Launcher.class.getResource("pause.png"));*/
		    start = ImageIO.read(Launcher.class.getResource("/images/start.png"));
		    gameover = ImageIO.read(Launcher.class.getResource("/images/gameover.png"));
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }
    
    public static void main(String args[])
    {	
    	JFrame frame = new JFrame("�Ͻ�ͷ--By ƽ����");
    	//���ô������ơ�Ĭ�Ϲرշ�ʽ����С��λ��
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//frame.setAlwaysOnTop(true); //���ô����ö�  
    	frame.setLocationRelativeTo(null);
    	frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    	//�м�����Jpanel
    	final Launcher Iden = new Launcher();
    	frame.add(Iden);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	Iden.launch();
    	frame.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e) {
    	        Iden.processParentEvent(e);
    	    }
    		
    		public void keyReleased(KeyEvent e) {
    	        Iden.processParentEvent(e);
    	    }
    	});
    }
    
    static void playMusic(int i){//�������ֲ���  
        try {
            URL cb;  
            File f = new File("1.wav"); // ����������������ļ����ڵ�·��  
            cb = f.toURL();  
            AudioClip aau;  
            aau = Applet.newAudioClip(cb);  
            aau.play();   
            aau.loop();//ѭ������  
        	if(i==0) {
	            // ѭ������ aau.play()  
	            aau.stop();  
        	}
  
        } catch (MalformedURLException e) {  
              
            e.printStackTrace();  
        }  
    }
    
    public void launch()
    {
    	//���ü��̼����¼�
    	KeyAdapter myKeyAdapter = new KeyAdapter(){
    		
    		@Override
    		public void keyPressed(KeyEvent e)
    		{
    			int keycode = e.getKeyCode();
    			if(keycode==KeyEvent.VK_ENTER&&current_state!=State.running){			//���»س�������running״̬
    				init_data();
    				current_state = State.running;
    			}else if(keycode==KeyEvent.VK_D)		//����D��״̬ΪRunningʱӢ�������ƶ�
    			{
    				Right = true;
    				isRight = true;
    				isLeft = false;
    			}else if(keycode==KeyEvent.VK_A)		//����A��״̬ΪRunningʱӢ�������ƶ�
    			{
    				Left = true;
    				isLeft = true;
    				isRight = false;
    			}else if(keycode==KeyEvent.VK_J&&current_state==State.running&&Shooting==false&&!isDeath)
    			{
    				Shooting = true;
    				Slug []newslug = new Slug[1];
    				if(isRight)
    					newslug[0] = new Slug((hero.x_pos+hero.width-15),(hero.y_pos+hero.height/2-13),true, "hero", 5);	//�ӵ���������, Ӣ���ӵ�	
    				else	
    					newslug[0] = new Slug((hero.x_pos-15),(hero.y_pos+hero.height/2-13),false, "hero", 5);	//�ӵ��������� Ӣ���ӵ�
    				slugs = Arrays.copyOf(slugs, slugs.length + newslug.length);  
    			    //��newBullets�����п�������Ԫ�ص�bullets����ĩβ  
    			    System.arraycopy(newslug, 0, slugs, slugs.length - newslug.length, newslug.length); 
    			}else if(keycode==KeyEvent.VK_K&&current_state==State.running&&!isJumping)
    			{
    				Up = true;
    				isJumping = true;
    			}else if(keycode==KeyEvent.VK_S&&current_state==State.running)
    			{
    				hero.isSquating = true;
    				hero.y_pos = hero.Hero_initial_y + 30;
    				hero.squat(isLeft, isRight); 				
    			}
    		}
    		
    		@Override
    		public void keyReleased(KeyEvent e)
    		{	
    			int keycode = e.getKeyCode();
    			if(keycode==KeyEvent.VK_D)
    			{
    				Right = false;
					hero.setBackgroundMove(false);
    			}else if(keycode == KeyEvent.VK_A)
    			{
    				Left = false;
    			}
    			else if(keycode == KeyEvent.VK_K)
    			{
    				Up = false;
    			}else if(keycode == KeyEvent.VK_J)
    			{
    				Shooting = false;
    			}else if(keycode==KeyEvent.VK_S)
    			{
    				hero.isSquating = false;
    				hero.y_pos = hero.Hero_initial_y;
    				hero.step();
    			}
    		}
    	};
    	
    	//this.setFocusable(true);
        this.addKeyListener(myKeyAdapter);
   	 	playMusic(1);
    	//������ʱ��
    	Timer timer = new Timer();  
    	timer.schedule(new TimerTask(){
    	    private int runTimes = 0;

    		//Ҫ��ʲô��--�����㷨
			public void run() {
				// TODO �Զ����ɵķ������
				if(current_state==State.running)
				{
					runTimes++;
					
		    		if(Right==true&&current_state==State.running&&!isDeath)
		    		{
		    			hero.step();
	    				if(!BlockRight()){		//ֻ�е��ұ����谭ʱ������
		    				if(hero.get_Hero_x_pos() >= hero.Hero_initial_x 
		    						&& background_x_pos + WIDTH < background.getWidth() - 100){//�����ƶ�
		    					hero.setBackgroundMove(true);//�ٶȼ���
		    					movewithBackground(hero.get_Hero_Speed());
		    				}
		    				else
		    					hero.moveRight();
	    				}
	    				hero_state = Hero_State.right_slugs;							//�������ң� �ӵ���������
		    		}else if(Left==true&&current_state==State.running&&!isDeath&&!hero.outOfLeftBounds())
		    		{
		    			hero.stepReverse();
	    				if(!BlockLeft())		//ֻ�е�������谭ʱ������
	    					hero.moveLeft();
	    				hero_state = Hero_State.left_slugs;								//���������ӵ���������
		    		}
					
		    		if(runTimes%500==0) {
		    			EnemyShoot();
		    			TankShoot();
		    		}
		    		
					EnemyStep();
					
					moveAllEnemies();
					
					moveAllSlugs();
					
					SlugHitEnemy();
					
					SlugHitTank();
					
					SlugHitBlock();
					
					for(int j=0;j<enemies.length;j++)			//���ÿ��ʿ����Ѫ���� ��ʿ�����������������Ч
						if(enemies[j].health_point<=0)
						{
							if(enemies[j].Death())
							{
								kill_enemy_count++;
								enemies[j] = enemies[enemies.length-1];
								enemies = Arrays.copyOf(enemies,enemies.length-1);
							}
						}
					for(int j=0;j<tanks.length;j++)			//���ÿ��̹�˵�Ѫ���� ��̹�����������������Ч
						if(tanks[j].health_point<=0)
						{
							if(tanks[j].Death())
							{
								kill_tank_count++;
								tanks[j] = tanks[tanks.length-1];
								tanks = Arrays.copyOf(tanks,tanks.length-1);
							}
						}
					for(int j=0;j<blocks.length;j++)			//���ÿ���ϰ����Ѫ���� ���ϰ��ﱻ�ƻ�������ƻ���Ч
						if(blocks[j].health_point<=0)
						{
							if(blocks[j].Death())
							{
								blocks[j] = blocks[blocks.length-1];
								blocks = Arrays.copyOf(blocks,blocks.length-1);
							}
						}

					SlugHitHero(runTimes);
					TankSlugHitHero(runTimes);
					
					if(isJumping)
					{
						if(hero.Jump(isRight,isLeft))
						{
							isJumping = false;
							hero.y_pos = hero.Hero_initial_y;
						}
					}
					
    				if(hero.health_point == 0)			//Ѫ�����㣬game over
    				{
    					if(hero.Death()){
    						current_state = State.game_over;
    						//playMusic(0);
    					}
    				}else if(isDeath)
    				{
    					deathTime = runTimes;
    					if(hero.Death())
    					{
    						hero.revive();    	
    						isDeath = false;
    					}
    				}
					outOfBounds();
					if(background_x_pos > 7100 && tanks.length == 0){
						current_state = State.game_over;
						JOptionPane.showMessageDialog(null, 
								"ɱ�У� " + kill_enemy_count + "\n����̹�ˣ� " 
						+ kill_tank_count, "��ϲͨ��", 
								JOptionPane.INFORMATION_MESSAGE); 
					}
				}
				repaint();
			}
    	 },5, 5);

    }
    
    public void init_data()
    {
    	isDeath = false;
    	isJumping = false;
    	isRight = true;
    	isLeft = false;
    	hero = new Hero();
    	slugs = new Slug[0];
    	enemyslugs = new Slug[0];
    	tankslugs = new Slug[0];
    	enemies = new Enemy[0];
    	tanks = new Tank[0];
    	blocks = new Block[0];
    	deathTime = 0;
		background_x_pos = 0;
		kill_enemy_count = 0;
		kill_tank_count = 0;
    	init_enemy();
    }
    
    public void processParentEvent(AWTEvent e){
        this.processEvent(e);
    }
    
  //����ͬ״̬��ʾ��ͬ�Ľ���
    @Override
    public void paint(Graphics g){
    	g.drawImage(background, -background_x_pos, 0, null);
    	g.drawImage(staterow,0,0,null);
    	if(hero.health_point>=1)
    		g.drawImage(lifenum[hero.health_point-1], 55, 10, null);
    	paintHero(g);
    	paintSlug(g);
    	paintEnemy(g);
    	if(current_state==State.game_over)
    		g.drawImage(gameover,255,0,null);
    	if(current_state==State.start)
    		g.drawImage(start,245,180,null);
    }
    
    //��ʼ�������Լ��ϰ������   
    private void init_enemy(){
    	//��ʼ������
    	createAnEnemy(510, 1);
    	createAnEnemy(800, 2);
	    //��ʼ��̹��
    	createATank(700);
	    //��ʼ���ϰ���
	    createABlock(450);
    }
    
    //��ʾ�����ҷ��ӵ�
    private void paintSlug(Graphics g) {
		// TODO �Զ����ɵķ������
		for(Slug slug_:slugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
		
		for(Slug slug_:enemyslugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
		
		for(Slug slug_:tankslugs){
			g.drawImage(slug_.image, slug_.x_pos,slug_.y_pos,null);
		}
	}

    //
    private void movewithBackground(int speed){
		// TODO �Զ����ɵķ������
		background_x_pos += speed;
		for(Enemy enemy_:enemies){
			enemy_.x_pos -= speed;
		}
		for(Tank tank_:tanks){
			tank_.x_pos -= speed;
		}
		for(Block block_:blocks){
			block_.x_pos -= speed;
		}
		for(Slug slug_:slugs){
			slug_.x_pos -= speed;
		}
		for(Slug slug_:enemyslugs){
			slug_.x_pos -= speed;
		}
		for(Slug slug_:tankslugs){
			slug_.x_pos -= speed;
		}

		if(background_x_pos % 800 == 0){
    		createABlock(WIDTH + WIDTH / 2);
    	}
    	//���ӵ���
		if(background_x_pos % 510 == 0){
    		createAnEnemy(WIDTH,1);
    	}
    	if(background_x_pos % 800 == 0){
    		createAnEnemy(((int)Math.random())%WIDTH+WIDTH,2); 
    	}
	    //����̹��
    	if(background_x_pos % 2100 == 0 || background_x_pos == 7100){
    	    createATank(WIDTH);
    	}
    }
    
    //��ʾ���е���
    private void paintEnemy(Graphics g) {
		// TODO �Զ����ɵķ������
		for(Enemy enemy_:enemies){
			g.drawImage(enemy_.image, enemy_.x_pos,enemy_.y_pos,null);
		}
		for(Tank tank_:tanks){
			g.drawImage(tank_.image, tank_.x_pos,tank_.y_pos,null);
		}
		for(Block block_:blocks){
			g.drawImage(block_.image, block_.x_pos,block_.y_pos,null);
		}
	}
    
    public void paintHero(Graphics g)
    {
    	g.drawImage(hero.image, hero.x_pos, hero.y_pos, null);
    }
    
    //���е��˵��ƶ�
    public void moveAllEnemies()
    {
    	for(Enemy enemy_:enemies){
    		if(enemy_.health_point<=0)
    			continue;
    		if(enemy_.x_pos>hero.x_pos+hero.width)			//ʼ�ճ���Ӣ�۷����ƶ�
    		{
    			if(!BlockLeftForEnemy(enemy_)) {
    			enemy_.move();
    			}
    			enemy_.AssassinatedCount2 = 0;		//Ӣ�۶���˴�ɱ
    			
    		}
    		else if(enemy_.x_pos+enemy_.width<hero.x_pos)		//
    		{ 
    			enemy_.moveReverse();  	
    			enemy_.AssassinatedCount2 = 0;
    		}
    		else
    		{
    			if(enemy_.x_pos>hero.x_pos)
    				enemy_.Assassinate(false); 		//̫�ӽ��ˣ������õ���ɱ
    			else
    				enemy_.Assassinate(true);
    			if(!isDeath)				//Ӣ���������ټ���
    				enemy_.AssassinatedCount2++;
    			//System.out.println(enemy_.AssassinatedCount2);
    			if(enemy_.AssassinatedCount2 == 200)				//���һ�δ�ɱ
    			{
    				if(hero.health_point>0)
    					hero.health_point--;
    				enemy_.AssassinatedCount2 = 0;
    				isDeath = true;
    			}
    				
    		}
		}
    }
    
    //���е��˵Ķ���
    public void EnemyStep()
    {
    	for(Enemy enemy_:enemies){
			enemy_.step();
		}
    	for(Tank tank_:tanks){
			tank_.step();
		}
    }
    
    //��ǹ�������
    public void EnemyShoot()
    {
    	for(Enemy enemy_:enemies){
    		if(Math.abs(enemy_.x_pos - hero.x_pos)<200)
    			continue;
    		Slug []T_enemyslugs = enemy_.shoot();
    		enemyslugs = Arrays.copyOf(enemyslugs, enemyslugs.length + T_enemyslugs.length);
    	    System.arraycopy(T_enemyslugs, 0, enemyslugs, enemyslugs.length-T_enemyslugs.length, T_enemyslugs.length);
    	}
    }
    //̹�����
    public void TankShoot()
    {
    	for(Tank tank_:tanks){
    		if(Math.abs( tank_.x_pos-hero.x_pos)<100)
    			continue;
    		Slug []T_tankslugs = tank_.shoot();
    		tankslugs = Arrays.copyOf(tankslugs, tankslugs.length + T_tankslugs.length);
    	    System.arraycopy(T_tankslugs, 0, tankslugs, tankslugs.length-T_tankslugs.length, T_tankslugs.length);
    	}
    }
    
	//�����ӵ����ƶ�
	public void moveAllSlugs()
	{
		for(Slug slug_:slugs){
			slug_.step();
		}
		for(Slug slug_:enemyslugs){
			slug_.step();
		}
		for(Slug slug_:tankslugs){
			slug_.step();
		}
	}
	
	//����һ������
	public void createAnEnemy(int x_pos, int type)
	{
		if(x_pos + background_x_pos > background.getWidth() - 100)
			return;
		Enemy en;
		if(type == 1){
			en = new Enemy(x_pos,Hero.Hero_initial_y+hero.height-first_enemies[0].getHeight()-10,1,1,false);
		}
		else{
			en = new Enemy(x_pos,Hero.Hero_initial_y+hero.height-second_enemies[0].getHeight()-10,1,2,false);
		}
		 //��enemies��������1  
		enemies = Arrays.copyOf(enemies,enemies.length + 1);  
        //���µ��˷�������ĩβ  
		enemies[enemies.length - 1] = en;
	}

	//����һ��̹��
	public void createATank(int x_pos){
		if(x_pos + background_x_pos > background.getWidth() - 500)
			return;
	    Tank tnk = new Tank(x_pos,Hero.Hero_initial_y+hero.height-tank[0].getHeight());
	    tanks = Arrays.copyOf(tanks, tanks.length + 1);
		tanks[tanks.length - 1] = tnk;
	}

	//����һ���ϰ���
	public void createABlock(int x_pos){
		if(x_pos + background_x_pos > background.getWidth() - 500)
			return;
	    Block block = new Block(x_pos,Hero.Hero_initial_y+hero.height-bucketBlock[0].getHeight(),1);
	    blocks = Arrays.copyOf(blocks, blocks.length + 1);
	    blocks[blocks.length-1] = block;
	}
	
	//���Ӣ���ҷ�Block(�������˺��ϰ���)
	public boolean BlockRight()
	{
		for(Enemy enemy_:enemies)
			if((enemy_.x_pos>hero.x_pos)&&(enemy_.x_pos-hero.x_pos<hero.width)
					&&hero.y_pos+hero.height>=enemy_.y_pos)		//�������б�ʽ�ж��ҷ��Ƿ���Ծ
				return true;
		for(Block block_:blocks)
			if((block_.x_pos>hero.x_pos)&&(block_.x_pos-hero.x_pos<hero.width)
					&&hero.y_pos+hero.height>=block_.y_pos)		//�������б�ʽ�ж��ҷ��Ƿ���Ծ
				return true;
		for(Tank tank_:tanks)
			if((tank_.x_pos>hero.x_pos)&&(tank_.x_pos-hero.x_pos<hero.width)
					&&hero.y_pos+hero.height>=tank_.y_pos+80)		//�������б�ʽ�ж��ҷ��Ƿ���Ծ
				return true;
		return false;
	}
	//���Ӣ����Block(�������˺��ϰ���)
	public boolean BlockLeft()
	{
		for(Enemy enemy_:enemies)
			if((hero.x_pos>enemy_.x_pos)&&(hero.x_pos-enemy_.x_pos<enemy_.width)
					&&hero.y_pos+hero.height>=enemy_.y_pos)
				return true;
		for(Block block_:blocks)
			if((hero.x_pos>block_.x_pos)&&(hero.x_pos-block_.x_pos<block_.width)
					&&hero.y_pos+hero.height>=block_.y_pos)
				return true;
		for(Tank tank_:tanks)
			if((hero.x_pos>tank_.x_pos)&&(hero.x_pos-tank_.x_pos<tank_.width)
					&&hero.y_pos+hero.height>=tank_.y_pos+80)
				return true;
		return false;
	}
	
	//��������Block
	public boolean BlockLeftForEnemy(Enemy en)
	{
		for(Block block_:blocks)
			if((en.x_pos>block_.x_pos)&&(en.x_pos-block_.x_pos<15))
				return true;
		return false;
	}
	
	//�������ҷ�Block
	public boolean BlockRightForEnemy(Enemy en)
	{
		for(Block block_:blocks)
			if((en.x_pos<block_.x_pos)&&(block_.x_pos-en.x_pos<block_.width))
				return true;
		return false;
	}
	
	//����ӵ��͵�����ײ
	public void SlugHitEnemy()
	{
		for(int i=0;i<slugs.length;i++)
		{
			for(int j=0;j<enemies.length;j++)
			{
				if(Substance.hit(slugs[i], enemies[j]))
				{
					//�ֱ�ʹ�õ��˺��ӵ������һ��ʵ�������ײλ��
					enemies[j].health_point--;
					
					slugs[i] = slugs[slugs.length-1];
					slugs = Arrays.copyOf(slugs, slugs.length-1);
					
					i--;	//���¼�⵱ǰλ�õ��ӵ����µģ�
					break;   //��Ȼһ���ӵ�����������������
				}
			}
		}	
	}
	
	//����ӵ����ϰ�����ײ
	public void SlugHitBlock()
	{
		for(int i=0;i<slugs.length;i++)
		{
			for(int j=0;j<blocks.length;j++)
			{
				if(Substance.hit(slugs[i], blocks[j]))
				{
					//�ֱ�ʹ���ϰ�����ӵ������һ��ʵ�������ײλ��
					blocks[j].health_point--;
					slugs[i] = slugs[slugs.length-1];
					slugs = Arrays.copyOf(slugs, slugs.length-1);
					i--;	//���¼�⵱ǰλ�õ��ӵ����µģ�
					break;   //��Ȼһ���ӵ�����������������
				}
			}
		}	
	}
	//����ӵ���̹����ײ
	public void SlugHitTank()
	{
		for(int i=0;i<slugs.length;i++)
		{
			for(int j=0;j<tanks.length;j++)
			{
				if(Substance.hit(slugs[i], tanks[j]))
				{
					//�ֱ�ʹ�õ��˺��ӵ������һ��ʵ�������ײλ��
					tanks[j].health_point--;
					slugs[i] = slugs[slugs.length-1];
					slugs = Arrays.copyOf(slugs, slugs.length-1);
					i--;	//���¼�⵱ǰλ�õ��ӵ����µģ�
					break;   //��Ȼһ���ӵ�����������������
				}
			}
		}	
	}
	//����ӵ���Ӣ����ײ
	public void SlugHitHero(int runTimes)
	{
		for(int i=0;i<enemyslugs.length;i++)
		{
				if(Substance.hit(enemyslugs[i], hero))
				{				
					if(hero.health_point>0 && !isDeath && deathTime + 300 < runTimes)
					{
						hero.health_point--;
						isDeath = true;
					}
					enemyslugs[i] = enemyslugs[enemyslugs.length-1];
					enemyslugs = Arrays.copyOf(enemyslugs, enemyslugs.length-1);
					
					break;   //��Ȼһ���ӵ�����������������
				}
		}
	}
	//���̹���ӵ���Ӣ����ײ
	public void TankSlugHitHero(int runTimes)
	{
		for(int i=0;i<tankslugs.length;i++)
		{
				if(Substance.hit(tankslugs[i], hero))
				{				
					if(hero.health_point>0 && !isDeath && deathTime + 300 < runTimes)
					{
						hero.health_point--;
						isDeath = true;
					}
					tankslugs[i] = tankslugs[tankslugs.length-1];
					tankslugs = Arrays.copyOf(tankslugs, tankslugs.length-1);
					
					break;   //��Ȼһ���ӵ�����������������
				}
		}
	}
	
	
	
	//������е��˺��ӵ��Ƿ�Խ��
	public void outOfBounds(){
		//������е����Ƿ�Խ��
		Enemy []EnemyLives = new Enemy[enemies.length];
		int index = 0;
		for(Enemy enemy_:enemies){
			if(!enemy_.outOfLeftBounds())
			{
				EnemyLives[index++]=enemy_;
			}
		}
		enemies = Arrays.copyOf(EnemyLives, index);
		
		//�������̹���Ƿ�Խ��
		Tank []TankLives = new Tank[tanks.length];
		index = 0;
		for(Tank tank_:tanks){
			if(!tank_.outOfLeftBounds())
			{
				TankLives[index++]=tank_;
			}
		}
		tanks = Arrays.copyOf(TankLives, index);
		
		
		//��������ӵ��Ƿ�Խ��
		Slug []SlugLives = new Slug[slugs.length];
		index = 0;
		for(Slug slug_:slugs){
			if(!slug_.outOfRightBounds()&&!slug_.outOfLeftBounds())
			{
				SlugLives[index++] = slug_;
			}
		}
		slugs = Arrays.copyOf(SlugLives,index);
		
		SlugLives = new Slug[enemyslugs.length];
		index = 0;
		for(Slug slug_:enemyslugs){
			if(!slug_.outOfRightBounds()&&!slug_.outOfLeftBounds())
			{
				SlugLives[index++] = slug_;
			}
		}
		enemyslugs = Arrays.copyOf(SlugLives,index);
	}
	
}