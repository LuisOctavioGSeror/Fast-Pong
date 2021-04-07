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
				new Game();
				//Game.gameState = "wait";
				return;
			}else if(x < 0) {
				//Ponto do jogador.
				playerPoints++;
				Sound.point.play();
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
			
			
			x+=dx*speed;
			y+=dy*speed;
			
			
			
		}
		
		public void render(Graphics g) {
			g.setFont(new Font("arial", Font.BOLD, 72));
			g.drawString("PC:", 70, 140); 
			g.drawString("Player:", 840, 140);
			g.drawString(String.valueOf(enemyPoints), 245, 140); 
			g.drawString(String.valueOf(playerPoints), 1155, 140);
			g.setColor(Color.white);
			g.fillOval((int)x,(int)y,width,height);
		}
}
