import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon titleImage;
	
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;
	private ImageIcon leftMouth;
	
	private int lentghOfSnake = 3;
	private int moves=0;
	
	private Timer timer;
	private int delay = 100;
	
	private int score =0;
	
	private boolean ifNotGameOver=true;
	
	private ImageIcon snakeImage;
	
	private int [] enemyXPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyYPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	private ImageIcon enemyImage;
	private Random rand = new Random();
	private int xPos = rand.nextInt(34);
	private int yPos = rand.nextInt(23);
	
	public GamePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
	}
	
	public void paint(Graphics g) {
		
		if(moves==0) {
			snakeXlength[2]= 50;
			snakeXlength[1]= 75;
			snakeXlength[0]= 100;
			
			snakeYlength[2]= 100;
			snakeYlength[1]= 100;
			snakeYlength[0]= 100;
			
		}
		//Draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 852, 55);
		
		//Draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//Draw border for GamePlay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//Draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//Draw Scores
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Score: "+score,780,30);
		
		//Draw the length of snake
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Length: "+lentghOfSnake,780,50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		for(int i=0;i<lentghOfSnake;i++) {
			if(i==0&& right) {
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);				
			}
			if(i==0&& left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0&& up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0&& down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			
			if(i!=0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		if((enemyXPos[xPos]==snakeXlength[0])&& (enemyYPos[yPos]==snakeYlength[0])) {
			score++;
			lentghOfSnake++;
			xPos = rand.nextInt(34);
			yPos = rand.nextInt(23);
		}
		enemyImage.paintIcon(this, g, enemyXPos[xPos], enemyYPos[yPos]);
		
		//if Game Over
		for(int b=1;b<lentghOfSnake;b++) {
			if(snakeXlength[b]==snakeXlength[0] && snakeYlength[b]==snakeYlength[0]) {
				left = false;
				right = false;
				up = false;
				down = false;
				ifNotGameOver=false;				
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("Arial",Font.BOLD,20));
				g.drawString("Space to restart!", 350, 340);
				
			}
		}
		
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int r= lentghOfSnake-1;r>=0;r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=lentghOfSnake;r>=0;r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r]+25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r]>850) {
					snakeXlength[r] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int r= lentghOfSnake-1;r>=0;r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=lentghOfSnake;r>=0;r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r]-25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r]<25) {
					snakeXlength[r] = 850;
				}
			}
			repaint();			
		}
		if(up) {
			for(int r= lentghOfSnake-1;r>=0;r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=lentghOfSnake;r>=0;r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]-25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r]<75) {
					snakeYlength[r] = 625;
				}
			}
			repaint();	
		}
		if(down) {
			for(int r= lentghOfSnake-1;r>=0;r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=lentghOfSnake;r>=0;r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]+25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r]>625) {
					snakeYlength[r] = 75;
				}
			}
			repaint();	
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE &&ifNotGameOver==false) {
			moves = 0;
			score = 0;
			lentghOfSnake = 3;
			ifNotGameOver=true;
			repaint();
		}
		
		
		if(ifNotGameOver&& (e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT)) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		if(ifNotGameOver&& (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		
		if(ifNotGameOver&& (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			
			right = false;
			left = false;
		}
		
		if(ifNotGameOver&& (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up = true;
			}
			
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	

}
