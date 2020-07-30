package brickBraker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JFrame  obj=new JFrame();
        GamePlay g=new GamePlay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    obj.add(g);
	    //map=MapGenerator(3,7);
	}
	

}
