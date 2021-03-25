package pong;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {
	
	public String[] options = {"New Game", "Dificult:", "Exit Game"};
	
	public int currentOption = 0;
	
	public int maxOption = options.length - 1;
	
	public boolean up, down, enter = false;
	
	public int i = 0;
	
	
	public void tick() {
		if(up) { 
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}
		
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}
		
		if(enter) {
			enter = false;
			if(options[currentOption] == "New Game") {
				Game.gameState = "NORMAL";
			}
			
			else if(options[currentOption] == "Dificult:") {
				Pause.dificult++;
				Ball.speed++;
				Enemy.dificult += 0.1;
			}
			
			else if(options[currentOption] == "Exit Game") {
				System.exit(1);
			}
		}
		
		
	}
	
	public void render(Graphics g) {
		
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 72));
			g.drawString("Fast Pong", (Game.WIDTH*Game.SCALE/2) - 140, (Game.HEIGHT*Game.SCALE/2) - 150 );
			
			//menu options
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.CENTER_BASELINE, 32));
			g.drawString("Start Game", (Game.WIDTH*Game.SCALE/2) - 60, (Game.HEIGHT*Game.SCALE/2) - 30 );
			g.drawString("Dificult:   " + String.valueOf(Pause.dificult), (Game.WIDTH*Game.SCALE/2) - 63, (Game.HEIGHT*Game.SCALE/2) + 60 );
			g.drawString("Exit Game", (Game.WIDTH*Game.SCALE/2) - 60, (Game.HEIGHT*Game.SCALE/2) + 150 );
			
			if(options[currentOption] == "New Game") {
				g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 120, (Game.HEIGHT*Game.SCALE/2) - 30 );
			}
			if(options[currentOption] == "Dificult:") {
				g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 120, (Game.HEIGHT*Game.SCALE/2) + 60 );
			}
			if(options[currentOption] == "Exit Game") {
				g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 120, (Game.HEIGHT*Game.SCALE/2) + 150 );
			}
		
	}
	
	
}