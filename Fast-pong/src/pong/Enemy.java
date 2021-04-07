package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

	public double x,y;
	public int width,height;
	public static double dificult = 0.15; 
	
	public Enemy(int x,int y) {
		this.x = x;
		this.y = y;
		this.width = 14;
		this.height = 210;
	}
	
	public void tick() {
		y+= (Game.ball.y - y - 6) * dificult;
		if(y + height > Game.HEIGHT ) {
			y = Game.HEIGHT - height;
		}
		else if(y < 25) {
			y = 25;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,width,height);
	}
	
}
