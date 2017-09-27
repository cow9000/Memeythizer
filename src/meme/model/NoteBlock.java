package meme.model;

import java.awt.Graphics;

public class NoteBlock
{
	private double X;
	private double Y;
	private double width;
	private double height;
	
	private double addedY;
	private double addedHeight;
	
	private int keyType;
	private boolean playing;
	
	NoteBlock(int keyType){
		this.keyType = keyType;
	}
	
	public void draw(Graphics g) {
		if(playing == false) {
			addedY++;
		}else {
			addedHeight++;
		}
		
		//Draw noteBlock
	}
	
	
	
}
