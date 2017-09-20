package meme.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PianoScreenDisplay extends JFrame
{
	
	private static String TITLE = "Memeythizer";
	private boolean drawAtAll = false;
	
	
	public PianoScreenDisplay(){
		
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display();
		
		
	}
	
	public void display() {
		CustomComponents cc = new CustomComponents();
		add(cc, BorderLayout.CENTER);
		
		pack();
		
		setMinimumSize(getSize());
		setVisible(true);
	}
	
	
	

}

class CustomComponents extends JComponent{
	private static final long serialVersionUID = 1L;
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(400,400);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(480,640);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(800,800);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension windowSize = getSize();
		//windowSize.getWidth();
		
		//We need to piant 88 keys to the piano
		//Based on the window size and height, draw them according to size of window.
		
		double increaseXAmount = (windowSize.getWidth()/88);
		boolean testColor = false;
		for(int i = 0; i < 89; i++) {
			testColor = !testColor;
			if(testColor) {
				g.setColor(Color.WHITE);
			}else {
				
				g.setColor(Color.GRAY);
			}
			
			int keyHeight = (int)Math.floor(windowSize.getHeight()*.6);
			
			g.fillRect((int)(increaseXAmount * (i - 1)), (int) (windowSize.getHeight() - keyHeight + 64), (int)increaseXAmount, keyHeight);
			g.setColor(Color.RED);
			g.drawString(Integer.toString(i),(int)(increaseXAmount * (i - 1)), (int)Math.floor(windowSize.getHeight()*.75));
			//g.drawRect(i*10, i*10, 10, 10);
		}
	}
	
}

