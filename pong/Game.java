package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 190;
	public static int HEIGHT = 120;
	public static int SCALE = 7;
	
	public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Menu menu;
	public Pause pause;
	
	public static String gameState = "MENU";
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(WIDTH-2, 50);
		enemy = new Enemy(0,50);
		ball = new Ball(WIDTH - 40,HEIGHT/2 - 1);
		
		menu = new Menu();
		pause = new Pause();
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		if(gameState == "NORMAL") {
			player.tick();
			enemy.tick();
			ball.tick();
		}
		else if(gameState == "MENU") 
			menu.tick(); 
		
		else if(gameState == "PAUSE");
			pause.tick();
		
			
		if(gameState == "wait") {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameState = "NORMAL";
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(4);
			return;
		}
		Graphics g = layer.getGraphics();
		//Graphics d = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
		
		if(gameState == "MENU") 
			menu.render(g);
		
		else if(gameState == "PAUSE") {
			pause.render(g);
		}
		
		
		bs.show();
	}
	
	@Override
	public void run() {
		requestFocus();
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
			if(gameState == "MENU") {
				menu.up = true;
				Sound.item.play();

			}
			else if(gameState == "PAUSE") {
				pause.up = true;
				Sound.item.play();

			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			if(gameState == "MENU") {
				menu.down = true;
				Sound.item.play();

			}
			else if(gameState == "PAUSE") {
				pause.down = true;
				Sound.item.play();

			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState == "MENU") {
				menu.enter = true;
				Sound.item.play();

			}
			else if(gameState == "PAUSE") {
				pause.enter = true;
				Sound.item.play();

			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "NORMAL") {
				gameState = "PAUSE";
				Sound.item.play();
			}
			else if(gameState == "PAUSE") {
				gameState = "NORMAL";
				pause.currentOption = 0;
				Sound.item.play();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			player.up = false;
			if(gameState == "MENU") {
				menu.up = false;
			}
			else if(gameState == "PAUSE") {
				pause.up = false;
			}
			
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
			
			if(gameState == "MENU") {
				menu.down = false;
			}
			else if(gameState == "PAUSE") {
				pause.down = false;
			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState == "MENU") {
				menu.enter = false;
			}
			else if(gameState == "PAUSE") {
				pause.enter = false;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
