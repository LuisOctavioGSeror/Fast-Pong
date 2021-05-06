package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball { 
	
		public double x,y;
		public int width,height;
		
		public double dx,dy;
		public static double speed = 28;
		public static double aceleration = 0.05;
		public static int playerPoints = 0;
		public static int enemyPoints = 0;
		
		public Ball(int x,int y) {
			this.x = x;
			this.y = y;
			this.width = 28;
			this.height = 28;
			
			int angle = new Random().nextInt(45) + 135; //direção inicial
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
		}
		
		public void tick() {
			
			if(y+(dy*speed) + width >= Game.HEIGHT ) {
				dy*=-1;
			}else if(y+(dy*speed) <= 172) {
				dy*=-1;
			}
			
			if(x > Game.WIDTH)
			{
				//Ponto do inimigo.
				enemyPoints++;
				Sound.point.play();
				aceleration = 0.05;
				new Game();
				//Game.gameState = "wait";
				return;
			}else if(x < 0) {
				//Ponto do jogador.
				playerPoints++;
				Sound.point.play();
				aceleration = 0.05;
				new Game();
				//Game.gameState = "wait";
				return;
			}
			
			Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
			
			Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
			Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
			
			if(bounds.intersects(boundsPlayer)) {
				Sound.ballHit.play();
				int angle = new Random().nextInt(45);
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				if(dx > 0)
					dx*=-1;
			}else if(bounds.intersects(boundsEnemy)) {
				Sound.ballHit.play();
				int angle = new Random().nextInt(45);
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				if(dx < 0)
					dx*=-1;
			}
			
			if(aceleration < 1)
				aceleration += 0.05;
			
			x+=dx*speed*aceleration;
			y+=dy*speed*aceleration;
			
			
			
		}
		
		public void render(Graphics g) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, Game.WIDTH, 172);
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 72));
			g.drawString("PC:", 70, 110); 
			g.drawString("Player:", 840, 110);
			g.drawString(String.valueOf(enemyPoints), 245, 110); 
			g.drawString(String.valueOf(playerPoints), 1155, 110);
			g.setColor(Color.white);
			g.fillOval((int)x,(int)y,width,height);
		}
}
