package com.suse.snrjjc30m;

import java.awt.Graphics;
import java.util.Random;

public class Bullet {
	private int x;
	private int y;
	private int step=5;
	private int width =5;
	private int height=5;
	private Random r=new Random();
	private int direct;         //�ӵ����ַ��� 0 up 1 down 2left 3 right
	private int dx;				//�ӵ���ɻ���x����
	private int dy;
	private double cos;			//�ӵ���ɻ���cosֵ
	private double sin;
	private double dLine;		//�ӵ���ɻ��ľ���
	private Plane plane;
	public Bullet(Plane plane){//���캯�������Ը�ֵ
		this.plane=plane;
		init();
		
	}
	public void paint(Graphics g,GamePanel gp){
		g.drawImage(Contains.BULLET,x,y,gp);
	}

	public void move(){			//�ӵ��ƶ�
		this.x=(int)(this.x+step*cos);
		this.y=(int)(this.y+step*sin);
		if(x<0||y<0||x>Contains.SCREEN_WIDTH-width||y>Contains.SCREEN_HEIGHT-height){
			init();				//�����ж������µ���init
		}
	}
	private void init(){			//��ʼ������
		direct =r.nextInt(4);
		switch(direct){
		case 0:
			this.x=r.nextInt(Contains.SCREEN_WIDTH-width);
			this.y=0;
			break;
		case 1:
			this.x=r.nextInt(Contains.SCREEN_WIDTH-width);
			this.y=Contains.SCREEN_HEIGHT-height;
			break;
		case 2:
			this.x=0;
			this.y=r.nextInt(Contains.SCREEN_HEIGHT-height);
			break;
		case 3:
			this.x=Contains.SCREEN_WIDTH-width;
			this.y=r.nextInt(Contains.SCREEN_HEIGHT-height);
			break;
		default:
			break;
		}
		dx=plane.getX()-this.x;				//��ʼ���й켣
		dy=plane.getY()-this.y;
		dLine=Math.sqrt(dx*dx+dy*dy);
		cos=dx/dLine;
		sin=dy/dLine;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
