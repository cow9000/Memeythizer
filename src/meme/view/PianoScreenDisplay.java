package meme.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import meme.model.Key;
import meme.model.MidiHandler;
import meme.model.MidiInputReceiver;

public class PianoScreenDisplay extends JFrame
{


	MouseListener mouseListener;

	private ArrayList<Key> Keys;
	
	private static String TITLE = "Memeythizer";

	public PianoScreenDisplay()
	{	
		
		
		Keys = new ArrayList<Key>();
		for (int i = 0; i < 88; i++)
		{
			
			Keys.add(new Key(i));
			
		}
		
		this.mouseListener = new MouseListener();

		this.addMouseListener(mouseListener);

		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display();

	}

	public void display()
	{
		CustomComponents cc = new CustomComponents(this);
		add(cc, BorderLayout.CENTER);

		pack();

		setMinimumSize(getSize());
		setSize(getPreferredSize());
		setVisible(true);
	}
	
	public Key returnKey(int i) {
		return Keys.get(i);
	}

}

class MouseListener extends MouseAdapter
{
	public void mouseClicker(MouseEvent e)
	{
		
	}
}

class CustomComponents extends JComponent
{
	private static final long serialVersionUID = 1L;

	PianoScreenDisplay piano;
	
	CustomComponents(PianoScreenDisplay piano)
	{
		this.piano = piano;
	}
	
	

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
	
	
	//CHANGE T
	public Key returnKey(int i) {
		//RETURN KEY HERE NEED TO FETCH FROM MAIN CLASS UP THERE ^^
		return piano.returnKey(i);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		Dimension windowSize = getSize();

		// We need to piant 88 keys to the piano
		// Based on the window size and height, draw them according to size of window.

		double increaseXAmountNormal = windowSize.getWidth() / 52;
		double increaseXAmountFlat = increaseXAmountNormal / 2;
		// Draw normal keys

		// Draw sharp keys

		int normalKeys = 0;
		int flatKeys = 0;
		double totalKeys = 0;
		for (int whichOneToPaint = 0; whichOneToPaint < 2; whichOneToPaint++)
		{

			// Reset variables so it doesn't mess with positions
			totalKeys = 0;
			flatKeys = 0;

			for (int i = 0; i < 88; i++)
			{
				totalKeys += 1;
				int keyHeight = (int) Math.floor(windowSize.getHeight() * .6);

				int y = (int) (windowSize.getHeight() - keyHeight + 64);

				// X will equal (Totalcount-flatcount) * (NormalIncreaseAmount) - FlatIncreaseAmount/2
				double x = (i - flatKeys) * (increaseXAmountNormal) - increaseXAmountFlat / 2;

				// divide i by twelve, based on that calculate if it is a # or a normal key.
				int keyType = i % 12;

				// Check if we are drawing a Flat key or a normal key
				if (keyType == 0 || keyType == 2 || keyType == 3 || keyType == 5 || keyType == 7 || keyType == 8 || keyType == 10 || keyType == 12)
				{

					// First we check if we are on the normal Key drawing phase or flat key phase
					if (whichOneToPaint == 0)
					{
						// Get where the x position will be
						x = increaseXAmountNormal * (normalKeys);

						// Set color to white then draw

						returnKey(i).draw(g, x, y, increaseXAmountNormal, keyHeight, Color.white);

						// Update normalKey variable
						normalKeys += 1;
					}
				}
				// If we are not drawing a normal key, draw a flat key
				else
				{
					// If we are not in the normal drawing phase, draw the flat key
					if (whichOneToPaint == 1)
					{
						// Sharp

						// update flat Keys
						flatKeys += 1;

						// Calculate the x value
						x = ((totalKeys - flatKeys) * (increaseXAmountNormal)) - increaseXAmountFlat / 2;

						// draw the key
						g2.setColor(Color.BLACK);
						keyHeight -= keyHeight / 2;
					
						returnKey(i).draw(g, x, y, increaseXAmountFlat, keyHeight, Color.BLACK);

					}
				}
			}
		}
		repaint();
	}

}


































