package meme.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import meme.model.Key;

public class PianoScreenDisplay extends JFrame
{

	private MouseListener mouseListener;

	private ArrayList<Key> Keys;

	private static String TITLE = "Memeythizer";

	private int testPlayKey = 0;
	private boolean playBack = false;
	private Timer timer = new Timer();
	private URL pathToSound;
	private CustomComponents cc;

	private void loadSettings()
	{
		pathToSound = this.getClass().getResource("Airhorn.wav");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu soundMenu = new JMenu("Settings");
		menuBar.add(soundMenu);

		JMenuItem importSound = new JMenuItem("Import Sound");
		JMenuItem importMidi = new JMenuItem("Import Midi File");

		soundMenu.add(importSound);
		soundMenu.add(importMidi);

		importSound.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("IMPORT SOUND");
				final JFileChooser fc = new JFileChooser();

				// File filter
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Wav", "wav", "Waveform Audio File Format");
				fc.setFileFilter(filter);

				int x = fc.showOpenDialog(null);
				if (x == JFileChooser.APPROVE_OPTION)
				{
					System.out.println(fc.getSelectedFile().getAbsolutePath());
					try
					{
						pathToSound = new URL("file:" + fc.getSelectedFile().getAbsolutePath());
						cc.setUrl(pathToSound);
					}
					catch (MalformedURLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		this.mouseListener = new MouseListener();

		this.addMouseListener(mouseListener);

		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public PianoScreenDisplay()
	{

		loadSettings();

		Keys = new ArrayList<Key>();
		for (int i = 0; i < 88; i++)
		{

			Keys.add(new Key(i));

		}

		display();

	}

	public void display()
	{
		cc = new CustomComponents(this);
		add(cc, BorderLayout.CENTER);
		pack();

		setMinimumSize(getSize());
		setSize(getPreferredSize());
		setVisible(true);

		// TESTING THE FREQUENCIES
		
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				Random rand = new Random();
				Keys.get(testPlayKey).playKey();

				if (playBack == false)
				{
					if (testPlayKey != 0)
						Keys.get(testPlayKey - 1).stopKey();
					testPlayKey += 1;

					if (testPlayKey == 87)
						playBack = true;

				}
				else
				{
					if (testPlayKey != 87)
						Keys.get(testPlayKey + 1).stopKey();
					testPlayKey -= 1;

					if (testPlayKey == 0)
						playBack = false;
				}
				
			

			}
		}, 0, 500);
		
		///////////////////////////////////////////////////////////
	}

	public Key returnKey(int i)
	{
		return Keys.get(i);
	}

	public URL returnPath()
	{
		return pathToSound;
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
	private URL pathToSound;

	PianoScreenDisplay piano;

	CustomComponents(PianoScreenDisplay piano)
	{
		this.pathToSound = this.getClass().getResource("Airhorn.wav");
		this.piano = piano;
	}

	public void setUrl(URL url)
	{
		pathToSound = url;
		System.out.println(pathToSound);
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

	// CHANGE T
	public Key returnKey(int i)
	{
		// RETURN KEY HERE NEED TO FETCH FROM MAIN CLASS UP THERE ^^

		// SET SOUND URL
		piano.returnKey(i).setPath(pathToSound);
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

						returnKey(i).draw(g, x, y, increaseXAmountNormal, keyHeight, new Color(255, 255, 255));

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
