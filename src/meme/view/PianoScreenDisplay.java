package meme.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
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
	private KeyListener listener;
	
	private CustomComponents cc;

	
	public KeyListener getShortcutKeyListener() {
	    listener = new KeyListener() {
	    		
	        @Override
	        public void keyReleased(KeyEvent evt) {
        		//TOP ROW 1 to 0
	            if (evt.getKeyCode() == KeyEvent.VK_1 && !evt.isShiftDown()) {
	                returnKey(0).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_2 && !evt.isShiftDown()) {
	            		returnKey(2).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_3 && !evt.isShiftDown()) {
	            		returnKey(3).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_4 && !evt.isShiftDown()) {
	            		returnKey(5).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_5 && !evt.isShiftDown()) {
	            		returnKey(7).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_6 && !evt.isShiftDown()) {
	            		returnKey(8).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_7 && !evt.isShiftDown()) {
	            		returnKey(10).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_8 && !evt.isShiftDown()) {
	            		returnKey(12).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_9 && !evt.isShiftDown()) {
	            		returnKey(14).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_0 && !evt.isShiftDown()) {
	            		returnKey(15).stopKey();
	            }
	            //SHIFTED
	            else if(evt.getKeyCode() == KeyEvent.VK_1 && evt.isShiftDown()) {
	            		returnKey(1).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_2 && evt.isShiftDown()) {
            			returnKey(4).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_4 && evt.isShiftDown()) {
        				returnKey(6).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_5 && evt.isShiftDown()) {
        				returnKey(9).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_6 && evt.isShiftDown()) {
        				returnKey(11).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_8 && evt.isShiftDown()) {
        				returnKey(13).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_9 && evt.isShiftDown()) {
        				returnKey(16).stopKey();
	            }
	            ///////
	            
	            
	            //SECOND ROW q to p
	            else if(evt.getKeyCode() == KeyEvent.VK_Q && !evt.isShiftDown()) {
    					returnKey(17).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_W && !evt.isShiftDown()) {
    					returnKey(19).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_E && !evt.isShiftDown()) {
    					returnKey(20).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_R && !evt.isShiftDown()) {
    					returnKey(22).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_T && !evt.isShiftDown()) {
    					returnKey(24).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_Y && !evt.isShiftDown()) {
    					returnKey(26).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_U && !evt.isShiftDown()) {
    					returnKey(27).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_I && !evt.isShiftDown()) {
    					returnKey(29).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_O && !evt.isShiftDown()) {
    					returnKey(31).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_P && !evt.isShiftDown()) {
    					returnKey(32).stopKey();
	            }
	            //SHIFTED
	            if(evt.getKeyCode() == KeyEvent.VK_Q && evt.isShiftDown()) {
    					returnKey(16).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_W && evt.isShiftDown()) {
    					returnKey(19).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_E && evt.isShiftDown()) {
    					returnKey(21).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_T && evt.isShiftDown()) {
    					returnKey(24).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_Y && evt.isShiftDown()) {
    					returnKey(26).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_I && evt.isShiftDown()) {
    					returnKey(28).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_O && evt.isShiftDown()) {
    					returnKey(30).stopKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_P && evt.isShiftDown()) {
    					returnKey(33).stopKey();
	            }else {
	            		returnKey(1).stopKey();
	            }
	            	/////////
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	            // Do nothing
	        	System.out.println("TYPED");
	        }

	        @Override
	        public void keyPressed(KeyEvent evt) {
	        	
	        		//TOP ROW 1 to 0
	            if (evt.getKeyCode() == KeyEvent.VK_1 && !evt.isShiftDown()) {
	                returnKey(0).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_2 && !evt.isShiftDown()) {
	            		returnKey(2).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_3 && !evt.isShiftDown()) {
	            		returnKey(3).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_4 && !evt.isShiftDown()) {
	            		returnKey(5).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_5 && !evt.isShiftDown()) {
	            		returnKey(7).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_6 && !evt.isShiftDown()) {
	            		returnKey(8).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_7 && !evt.isShiftDown()) {
	            		returnKey(10).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_8 && !evt.isShiftDown()) {
	            		returnKey(12).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_9 && !evt.isShiftDown()) {
	            		returnKey(14).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_0 && !evt.isShiftDown()) {
	            		returnKey(15).playKey();
	            }
	            //SHIFTED
	            else if(evt.getKeyCode() == KeyEvent.VK_1 && evt.isShiftDown()) {
	            		returnKey(1).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_2 && evt.isShiftDown()) {
            			returnKey(4).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_4 && evt.isShiftDown()) {
        				returnKey(6).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_5 && evt.isShiftDown()) {
        				returnKey(9).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_6 && evt.isShiftDown()) {
        				returnKey(11).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_8 && evt.isShiftDown()) {
        				returnKey(13).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_9 && evt.isShiftDown()) {
        				returnKey(16).playKey();
	            }
	            ///////
	            
	            
	            //SECOND ROW q to p
	            else if(evt.getKeyCode() == KeyEvent.VK_Q && !evt.isShiftDown()) {
    					returnKey(17).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_W && !evt.isShiftDown()) {
    					returnKey(19).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_E && !evt.isShiftDown()) {
    					returnKey(20).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_R && !evt.isShiftDown()) {
    					returnKey(22).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_T && !evt.isShiftDown()) {
    					returnKey(24).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_Y && !evt.isShiftDown()) {
    					returnKey(26).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_U && !evt.isShiftDown()) {
    					returnKey(27).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_I && !evt.isShiftDown()) {
    					returnKey(29).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_O && !evt.isShiftDown()) {
    					returnKey(31).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_P && !evt.isShiftDown()) {
    					returnKey(32).playKey();
	            }
	            //SHIFTED
	            else if(evt.getKeyCode() == KeyEvent.VK_Q && evt.isShiftDown()) {
    					returnKey(16).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_W && evt.isShiftDown()) {
    					returnKey(19).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_E && evt.isShiftDown()) {
    					returnKey(21).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_T && evt.isShiftDown()) {
    					returnKey(24).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_Y && evt.isShiftDown()) {
    					returnKey(26).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_I && evt.isShiftDown()) {
    					returnKey(28).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_O && evt.isShiftDown()) {
    					returnKey(30).playKey();
	            }else if(evt.getKeyCode() == KeyEvent.VK_P && evt.isShiftDown()) {
    					returnKey(33).playKey();
	            }else {
            			returnKey(1).playKey();
	            }
	            	/////////
	            
	        }
	    };
	    return listener;
	}
	
	
	
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
		getShortcutKeyListener();
		this.addMouseListener(mouseListener);
		this.addKeyListener(listener);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public PianoScreenDisplay()
	{

		loadSettings();

		Keys = new ArrayList<Key>();
		for (int i = 0; i < 88; i++)
		{

			Keys.add(new Key(i,pathToSound));

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
