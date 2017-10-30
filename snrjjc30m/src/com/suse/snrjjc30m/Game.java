package com.suse.snrjjc30m;
import javax.swing.JFrame;
public class Game {
	public static void main(String[] args){
		JFrame window =new JFrame();
		window.setBounds(200,100,Contains.SCREEN_WIDTH,Contains.SCREEN_HEIGHT);  //设置边框大小
		window.setTitle("是男人就坚持30秒");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);		//设置不可改边框大小				
		window.setUndecorated(true);     //设置去边框
		GamePanel gp=new GamePanel();    //创建画布对象
		window.add(gp);
		window.setVisible(true);	//设置边框可见
		new Thread(gp).start();		//开始线程
		gp.addKeyListener(gp);		//添加监听器
	}

}
