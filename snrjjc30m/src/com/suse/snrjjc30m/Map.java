package com.suse.snrjjc30m;

import java.awt.Graphics;

public class Map {
	private int y=0;
	public void paint(Graphics g,GamePanel gp){
		g.drawImage(Contains.MAP, 0, y,gp);
		g.drawImage(Contains.MAP,0, y-320, gp);
	}

	public void move(){
		y=y+2;
		if(y>320)
		{
			y=0;
		}
	}

}
