package com.suse.snrjjc30m;
import javax.swing.JFrame;
public class Game {
	public static void main(String[] args){
		JFrame window =new JFrame();
		window.setBounds(200,100,Contains.SCREEN_WIDTH,Contains.SCREEN_HEIGHT);  //���ñ߿��С
		window.setTitle("�����˾ͼ��30��");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);		//���ò��ɸı߿��С				
		window.setUndecorated(true);     //����ȥ�߿�
		GamePanel gp=new GamePanel();    //������������
		window.add(gp);
		window.setVisible(true);	//���ñ߿�ɼ�
		new Thread(gp).start();		//��ʼ�߳�
		gp.addKeyListener(gp);		//��Ӽ�����
	}

}
