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
		this.width = 2;
		this.height = 25;
	}
	
	public void tick(){
		if(up)
		{
			y-=4;
		}
		else if(down) {
			y+=4;
		}
		
		if(y + height > Game.HEIGHT)
		{
			y = Game.HEIGHT - height;
		}
		
		else if(y < 25) {
			y = 25;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x,y,width,height);
	}
	
}
