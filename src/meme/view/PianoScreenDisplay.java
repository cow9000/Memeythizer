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

	public PianoScreenDisplay()
	{

		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		display();

	}

	public void display()
	{
		CustomComponents cc = new CustomComponents();
		add(cc, BorderLayout.CENTER);

		pack();

		setMinimumSize(getSize());
		setSize(getPreferredSize());
		setVisible(true);
	}

}

class CustomComponents extends JComponent
{
	private static final long serialVersionUID = 1L;

	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(600, 600);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(640, 640);
	}

	@Override
	public Dimension getMaximumSize()
	{
		return new Dimension(800, 800);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Dimension windowSize = getSize();

		// We need to piant 88 keys to the piano
		// Based on the window size and height, draw them according to size of window.

		
		boolean testColor = false;

		
		int increaseXAmountFlat = new Double(windowSize.getWidth() / 88).intValue();
		int increaseXAmountNormal = new Double(windowSize.getWidth() / 52).intValue();
		
		//Draw normal keys
		for (int i = 0; i < 52; i++)
		{
				
	
				int keyHeight = (int) Math.floor(windowSize.getHeight() * .6);
				
				int y = (int) (windowSize.getHeight() - keyHeight + 64);
				int x = increaseXAmountNormal * (i);
				
				// divide i by twelve, based on that calculate if it is a # or a normal key.
				g.setColor(Color.WHITE);
				testColor = !testColor;
				if(testColor) {
					g.setColor(Color.LIGHT_GRAY);

				}
				
				g.drawString(Integer.toString(i), x, y);
				g.fillRect(x, y, increaseXAmountNormal, keyHeight);	
	
				// Now based on keyType that will determine if it is a # or a normal key
				
		}
		
		//Draw sharp keys
		
		
		
		for (int i = 0; i < 88; i++)
		{
	
				int keyHeight = (int) Math.floor(windowSize.getHeight() * .6);
				
				int y = (int) (windowSize.getHeight() - keyHeight + 64);
				int x = increaseXAmountFlat * (i) ;
				
				// divide i by twelve, based on that calculate if it is a # or a normal key.
				int keyType = i % 12;
				
				
				if(keyType == 0 || keyType == 2 || keyType == 3 || keyType == 5 || keyType == 7 || keyType == 8 || keyType == 10 || keyType == 12) {
					
				}else{
					//Sharp
					g.setColor(Color.BLACK);
					keyHeight -= keyHeight/2;
					g.fillRect(x, y, increaseXAmountFlat, keyHeight);
					g.drawString(Integer.toString(i), x, y);
				}
	
				// Now based on keyType that will determine if it is a # or a normal key
				
		}
	}

}
