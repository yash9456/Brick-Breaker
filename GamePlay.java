package brickBraker;
import brickBraker.MapGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score=0;
    
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerX=310;
    private int ballY=120;
    private int ballX=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private MapGenerator map;
    public GamePlay() {
    	map=new MapGenerator(3,7);
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
     	timer=new Timer(delay,this);
     	timer.start();
    }
    public void paint(Graphics g) {
    	// background
    	g.setColor(Color.black);
    	g.fillRect(1,1,692,592);
    	
    	//drawing map
    	map.draw((Graphics2D) g);
    	//borders
    	g.setColor(Color.yellow);
    	g.fillRect(0, 0, 3, 592);
    	g.fillRect(0, 0, 692, 3);
    	g.fillRect(691, 0, 3, 592);
    	
    	//scores
    	g.setColor(Color.white);
    	g.setFont(new Font("serif",Font.BOLD,25));
    	g.drawString(""+score, 590, 30);
    	
    	//the paddle
    	g.setColor(Color.green);
    	g.fillRect(playerX, 550, 100, 8);
    	
    	//the ball
    	g.setColor(Color.yellow);
    	g.fillOval(ballX,ballY,20,30);
    	
    	if(totalBricks<=0) {
     		play=false;
    		ballXdir=0;
    		ballYdir=0;
    		g.setColor(Color.RED);
    		g.setFont(new Font("serif",Font.BOLD,25));
        	g.drawString("YOU WON:", 190, 300);
        	g.setFont(new Font("serif",Font.BOLD,25));
        	g.drawString("PRESS ENTER TO RESTART", 190,350);
    	}
    	if(ballY>=570) {
    		play=false;
    		ballXdir=0;
    		ballYdir=0;
    		g.setColor(Color.RED);
    		g.setFont(new Font("serif",Font.BOLD,25));
        	g.drawString("GAME OVER,Score:"+score, 190, 300);
        	g.setFont(new Font("serif",Font.BOLD,25));
        	g.drawString("PRESS ENTER TO RESTART", 190,350);
    	}
    	 
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if(play)
		{
			if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8)))
				ballYdir=-ballYdir;
		A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j] > 0) {
						int brickX=j*map.brickWidth+80;
						int brickY=i*map.brickHeight+50;
						int brickWidth= map.brickWidth;
						int brickHeight=map.brickHeight;
						Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballrect=new Rectangle(ballX,ballY,20,20);
						Rectangle brickRect=rect;
						if(ballrect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score+=5;
							if(ballX+19<=brickRect.x||ballX+1>=brickRect.x+brickRect.width) {
								ballXdir=-ballXdir;
							}
							else {
								ballYdir=-ballYdir;
							}
							break A;
						}
						}
				}
			}
			
			ballX+=ballXdir;
			ballY+=ballYdir;
			if(ballX<0)
				ballXdir=-ballXdir;
			if(ballY<0)
				ballYdir=-ballYdir;
			if(ballX>670)
				ballXdir=-ballXdir;
			repaint();
			
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
	if(playerX>=600)
		playerX=600;
	else
	{
		moveRight();
	}
}
if(e.getKeyCode()==KeyEvent.VK_ENTER) { 
	if(!play) {
		play=true;
		ballX=120;
		ballY=350;
		ballXdir = -1;
		ballYdir=-2;
		score=0;
		playerX=310;
		totalBricks=21;
		map=new MapGenerator(3,7);
		repaint();
	}
}
if(e.getKeyCode()==KeyEvent.VK_LEFT) {
	if(playerX<10)
		playerX=10;
	else
	{
		moveLeft();
	}
}
	}
	public void moveRight() {
		play=true;
		playerX+=20;
	}
	public void moveLeft() {
		play=true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
