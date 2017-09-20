package meme.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PianoScreenDisplay extends JFrame
{
	
	private static String TITLE = "Memeythizer";
	
	public PianoScreenDisplay(){
		setSize(640,480);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Dimension windowSize = getContentPane().getSize();
		//windowSize.getWidth();
		
		//We need to piant 88 keys to the piano
		//Based on the window size and height, draw them according to size of window.
		for(int i = 0; i < 88; i++) {
			g.setColor(Color.WHITE);
			g.fillRect(10, i*10, 10, 10);
			//g.drawRect(i*10, i*10, 10, 10);
		}
	}
	
	

}
