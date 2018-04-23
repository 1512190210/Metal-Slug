package Pqb_Metal_Slug;

import java.awt.image.BufferedImage;

public abstract class Substance {
	protected int x_pos;
	protected int y_pos;
	protected int width;
	protected int height;
	protected BufferedImage image;
	protected int health_point;    //Hp;
	
	/** 
     * 所有实体都能移动 
     * 移动的方式由子类自己实现 
     */  
    public abstract void step();  
      
    /** 
     * 检查越界的方法 
     * @return 是否越界 
     */  
    public abstract boolean outOfLeftBounds();  
    
    public abstract boolean outOfRightBounds();  
      
    /** 
     * 检测两个矩形实体是否碰撞的方法 
     * 和具体对象无关，所以定义为静态方法 
     * @param f1 实体1 
     * @param f2 实体2 
     * @return 是否碰撞 
     */  
    public static boolean hit(Substance s1,Substance s2){  
        //step1: 求出两个矩形的中心点  
        int s1x = s1.x_pos + s1.width/2;  
        int s1y = s1.y_pos + s1.height/2;  
        int s2x = s2.x_pos + s2.width/2;  
        int s2y = s2.y_pos + s2.height/2;  
        //step2: 横向和纵向碰撞检测  
        boolean H = Math.abs(s1x - s2x) < (s1.width + s2.width)/2;  
        boolean V = Math.abs(s1y -s2y) < (s1.height + s2.height)/2;  
        //step3: 必须两个方向同时碰撞  
        return H&V;  
    }
}
