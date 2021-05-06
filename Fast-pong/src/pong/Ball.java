package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
		public double x,y;
		public int width,height;
		
		public double dx,dy;
		public double speed = 4;
		public static int playerPoints = 0;
		public static int enemyPoints = 0;
		
		public Ball(int x,int y) {
			this.x = x;
			this.y = y;
			this.width = 4;
			this.height = 4;
			
			int angle = new Random().nextInt(45); //direção inicial
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
		}
		
		public void tick() {
			
			if(y+(dy*speed) + width >= Game.HEIGHT ) {
				dy*=-1;
			}else if(y+(dy*speed) <= 29) {
				dy*=-1;
			}
			
			if(x > Game.WIDTH - width)
			{
				//Ponto do inimigo.
				enemyPoints++;
				new Game();
				return;
			}else if(x < 0) {
				//Ponto do jogador.
				playerPoints++;
				new Game();
				return;
			}
			
			Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
			
			Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
			Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
			
			if(bounds.intersects(boundsPlayer)) {
				int angle = new Random().nextInt(45);
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				if(dx > 0)
					dx*=-1;
			}else if(bounds.intersects(boundsEnemy)) {
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
			g.drawString("PC:", 10, 20); 
			g.drawString("Player:", 90, 20);
			g.drawString(String.valueOf(enemyPoints), 35, 20); 
			g.drawString(String.valueOf(playerPoints), 135, 20);
			g.setColor(Color.white);
			g.fillRect((int)x,(int)y,width,height);
		}
}
