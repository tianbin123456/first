package com.suse.snrjjc30m;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends Panel implements Runnable,KeyListener{
	private Map map=new Map();						//创建map对象
	private Plane plane =new Plane();				//创建plane对象
	private long start;
	private long end;
	private boolean up,down,left,right;
	private int num=30;
	private Bullet[] bullets=new Bullet[num];		//新建bullets数组 num个数据
	private boolean alive=true;
	public GamePanel(){								//构造函数给bullets数组创建对象
		for(int i=0;i<num;i++){
			bullets[i]=new Bullet(plane);
		}
	}
	private void planeBoom(){
		for(int i=0;i<num;i++){
			if(plane.getX()+plane.getWidth()>bullets[i].getX()
					&&bullets[i].getX()+bullets[i].getWidth()>plane.getX()
					&&plane.getY()+plane.getHeight()>bullets[i].getY()
					&&bullets[i].getY()+bullets[i].getHeight()>plane.getY()){
				alive=false;
			}
		}
	}
	public void update (Graphics g){
		Image tmp=createImage(Contains.SCREEN_WIDTH,Contains.SCREEN_HEIGHT);			//创建tmp图片装对象
		Graphics g1=tmp.getGraphics();			//获取tmp图片画笔，在图上绘制
		map.paint(g1, this);					
		plane.paint(g1, this,alive);
		for(int i=0;i<num;i++){
			bullets[i].paint(g1,this);
		}
		end=System.currentTimeMillis();			//获取当前时间距1970-0-0-0-0的时间 
		long sub=end-start;
		g1.setColor(Color.white);
		g1.drawString(sub/1000.0+"", 180, 20);	//显示时间字符串   在后面加空字符串类型变成字符串类型
		if(!alive){
			g1.setColor(Color.white);
			g1.drawString("GAME OVER", 80, 100);
			g1.drawString("press A to restart", 70, 120);
		}
		g.drawImage(tmp, 0, 0, this);
		if(!alive){
			g1.setColor(Color.white);
			g1.drawString("GAME OVER", 80, 100);
			g1.drawString("press A to restart", 80, 120);
		}
	}
	public void run(){						//
		start=System.currentTimeMillis();		//获取当前时间距1970-0-0-0-0的时间 
		while(alive){
			map.move();
			plane.move(up, down, left, right);
			for(int i=0;i<num;i++){
				bullets[i].move();
			}
			planeBoom();
			repaint();
			try {
				Thread.sleep(40L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {		//键盘按下
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_A:
			if(!alive){
				alive=true;
				map=new Map();
				plane=new Plane();
				for(int i=0;i<num;i++){
					bullets[i]=new Bullet(plane);
				}
				start=System.currentTimeMillis();
				new Thread(this).start();
				
			}
			break;
 		} 
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {//键盘弹起操作
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
 		} 
		
	}
	@Override
	public void keyTyped(KeyEvent e) {		//键盘按住
		// TODO Auto-generated method stub
		
	}

}
