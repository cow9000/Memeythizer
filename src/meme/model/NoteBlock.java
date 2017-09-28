package meme.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class NoteBlock
{

	private double addedHeight;
	
	private double timePlaying;
	private int keyType;
	private boolean playing;
	
	NoteBlock(int keyType){
		
		addedHeight = 1;
		playing = true;
		
	}
	
	//GETTERS
	public boolean isPlaying() {
		return playing;
	}
	
	public int getKeyType() {
		return keyType;
	}
	//SETTER
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	
	
	//GRAPHICS
	public void draw(Graphics g, double x, double y, double width) {
		
		
		System.out.println("Drawn NOTE BLOCK");
		timePlaying += 0.05;
		y -= timePlaying;
		if(playing) {
			addedHeight = timePlaying + 1;
		}
		
		//Draw noteBlock
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.RED);
		g2.fill(new Rectangle2D.Double(x, y, width, addedHeight));
		
	}
	
	
	
}
