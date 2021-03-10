package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

	public double x,y;
	public int width,height;
	
	public Enemy(int x,int y) {
		this.x = x;
		this.y = y;
		this.width = 2;
		this.height = 30;
	}
	
	public void tick() {
		y+= (Game.ball.y - y - 6) * 0.17;
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
