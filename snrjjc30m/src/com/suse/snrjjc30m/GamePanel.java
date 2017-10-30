package com.suse.snrjjc30m;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends Panel implements Runnable,KeyListener{
	private Map map=new Map();						//����map����
	private Plane plane =new Plane();				//����plane����
	private long start;
	private long end;
	private boolean up,down,left,right;
	private int num=30;
	private Bullet[] bullets=new Bullet[num];		//�½�bullets���� num������
	private boolean alive=true;
	public GamePanel(){								//���캯����bullets���鴴������
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
		Image tmp=createImage(Contains.SCREEN_WIDTH,Contains.SCREEN_HEIGHT);			//����tmpͼƬװ����
		Graphics g1=tmp.getGraphics();			//��ȡtmpͼƬ���ʣ���ͼ�ϻ���
		map.paint(g1, this);					
		plane.paint(g1, this,alive);
		for(int i=0;i<num;i++){
			bullets[i].paint(g1,this);
		}
		end=System.currentTimeMillis();			//��ȡ��ǰʱ���1970-0-0-0-0��ʱ�� 
		long sub=end-start;
		g1.setColor(Color.white);
		g1.drawString(sub/1000.0+"", 180, 20);	//��ʾʱ���ַ���   �ں���ӿ��ַ������ͱ���ַ�������
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
		start=System.currentTimeMillis();		//��ȡ��ǰʱ���1970-0-0-0-0��ʱ�� 
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
	public void keyPressed(KeyEvent e) {		//���̰���
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
	public void keyReleased(KeyEvent e) {//���̵������
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
	public void keyTyped(KeyEvent e) {		//���̰�ס
		// TODO Auto-generated method stub
		
	}

}
