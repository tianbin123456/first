package com.suse.snrjjc30m;

import java.awt.Graphics;

public class Plane {
	private int x=110;//�ɻ�λ��
	private int y=140;
	private int step=5;
	private int width =15;//�ɻ����
	private int height=15;
	public void paint(Graphics g,GamePanel gp,boolean alive){
		if(alive){
					g.drawImage(Contains.PLANE, x, y, x+15, y+15,0,0,15,15,gp);
		}
		else{
					g.drawImage(Contains.PLANE, x, y, x+15, y+15,15,0,30,15,gp);

		}

	}
	
	public void move(boolean up,boolean down,boolean left,boolean right){	//�ɻ��������з����
		if(up){																//�����ж�
			y-= step;
			if(y<0){
				y=0;
			}
		}
		if(down){
			y+= step;
			if(y>Contains.SCREEN_HEIGHT-height){
				y=Contains.SCREEN_HEIGHT-height;
			}
		}
		if(left){
			x-= step;
			if(x<0){
				x=0;
			}
		}
		if(right){
			x+= step;
			if(x>Contains.SCREEN_WIDTH-width){
				x=Contains.SCREEN_WIDTH-width;
			}
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
