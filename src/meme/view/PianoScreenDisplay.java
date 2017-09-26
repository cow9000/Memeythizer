package meme.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

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
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		Dimension windowSize = getSize();

		// We need to piant 88 keys to the piano
		// Based on the window size and height, draw them according to size of window.

		
		boolean testColor = false;

		
		
		double increaseXAmountNormal = windowSize.getWidth() / 52;
		double increaseXAmountFlat = increaseXAmountNormal/2;
		double increaseXAmountByNumber = increaseXAmountNormal/increaseXAmountFlat;
		//Draw normal keys

		
		//Draw sharp keys
		
		
		int normalKeys = 0;
		int flatKeys = 0;
		for(int whichOneToPaint = 0; whichOneToPaint < 2; whichOneToPaint ++) {
			for (int i = 0; i < 88; i++)
			{
				int keyHeight = (int) Math.floor(windowSize.getHeight() * .6);
				
				int y = (int) (windowSize.getHeight() - keyHeight + 64);
				double x = increaseXAmountFlat * (i);
				
				// divide i by twelve, based on that calculate if it is a # or a normal key.
				int keyType = i % 12;
				
				
				if(keyType == 0 || keyType == 2 || keyType == 3 || keyType == 5 || keyType == 7 || keyType == 8 || keyType == 10 || keyType == 12) {
					if(whichOneToPaint == 0) {
						x = increaseXAmountNormal * (normalKeys) ;
						g2.setColor(Color.WHITE);
						testColor = !testColor;
						if(testColor) {
							g2.setColor(Color.LIGHT_GRAY);
	
						}
						
						g2.fill(new Rectangle2D.Double(x, y, increaseXAmountNormal, keyHeight));
						
						
						
						normalKeys += 1;
					}
				}else{
					if(whichOneToPaint == 1) {
						//Sharp
						g2.setColor(Color.BLACK);
						keyHeight -= keyHeight/2;
						g2.fill(new Rectangle2D.Double(x, y, increaseXAmountFlat, keyHeight));
						flatKeys += 1;
					}
				}
	
				// Now based on keyType that will determine if it is a # or a normal key
			}
		}
	}

}
