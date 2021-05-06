package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	
	public boolean up,down;
	
	public int x,y;
	
	public int width,height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 14;
		this.height = 210;
	}
	
	public void tick(){
		if(up)
		{
			y-=60;
		}
		else if(down) {
			y+=60;
		}
		
		if(y + height > Game.HEIGHT)
		{
			y = Game.HEIGHT - height;
		}
		
		else if(y < 172) {
			y = 172;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x,y,width,height);
	}
	
}
