package pong;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Pause {
	
	public boolean paused = false;
	
	public String[] options = {"Continue", "New Game", "Dificult:", "Exit Game"};
	
	public int currentOption = 0;
	
	public static int dificult = 1;
	
	public int maxOption = options.length - 1;
	
	public boolean up, down, enter = false;
	
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
			if(options[currentOption] == "Continue") {
				Game.gameState = "NORMAL";
				currentOption = 0;
			}
			

			else if(options[currentOption] == "New Game") {
				Ball.enemyPoints = 0;
				Ball.playerPoints = 0;
				new Game();
				Game.gameState = "NORMAL";
				currentOption = 0;


			}
			
			else if(options[currentOption] == "Dificult:") {
				Ball.speed++;
				dificult++;
				Enemy.dificult += 0.03;

			}
			
			else if(options[currentOption] == "Exit Game") {
				System.exit(1);
			}
		}
		
		if(dificult > 8) {
			dificult = 1;
			Ball.speed = 6;
			Enemy.dificult = 0.21;
		}	
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
		g.drawString("PAUSED", (Game.WIDTH*Game.SCALE/2) - 60, (Game.HEIGHT*Game.SCALE/2) - 100 );
		
		//menu options
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.CENTER_BASELINE, 19));
		g.drawString("Continue", (Game.WIDTH*Game.SCALE/2) - 45, (Game.HEIGHT*Game.SCALE/2) - 30 );
		g.drawString("New Game", (Game.WIDTH*Game.SCALE/2) - 48, (Game.HEIGHT*Game.SCALE/2) + 20 );
		g.drawString("Dificult:   " + String.valueOf(dificult), (Game.WIDTH*Game.SCALE/2) - 48, (Game.HEIGHT*Game.SCALE/2) + 70 );
		g.drawString("Exit Game", (Game.WIDTH*Game.SCALE/2) - 45, (Game.HEIGHT*Game.SCALE/2) + 120 );
		
		if(options[currentOption] == "Continue") {
			g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 90, (Game.HEIGHT*Game.SCALE/2) - 30 );
		}
		
		if(options[currentOption] == "New Game") {
			g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 90, (Game.HEIGHT*Game.SCALE/2) + 20 );
		}
		
		if(options[currentOption] == "Dificult:") {
			g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 90, (Game.HEIGHT*Game.SCALE/2) + 70 );
		}
		
		if(options[currentOption] == "Exit Game") {
			g.drawString(">", (Game.WIDTH*Game.SCALE/2) - 90, (Game.HEIGHT*Game.SCALE/2) + 120 );
		}
	}

}