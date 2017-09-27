package meme.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Key
{

	private AudioInputStream audioInputStream;
	private Clip clip;
	private int keyNumber;
	private boolean playing;
	private double x;
	private double y;
	private double increaseXAmount;
	private double keyHeight;
	private Color color;
	
	public Key(int keyNumber)
	{
		this.keyNumber = keyNumber;
	}

	public boolean isPlaying()
	{
		
		return this.playing;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	// FROM http://www.technetexperts.com/web/change-the-pitch-of-audio-using-java-sound-api/
	public AudioFormat getOutFormat(AudioFormat inFormat, int frequency)
	{	
		
		
		int ch = inFormat.getChannels();
		float rate = inFormat.getSampleRate();
		int sampleSize = frequency;
		return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleSize, 16, ch, ch * 2, rate, inFormat.isBigEndian());
	}

	public void playKey()
	{
		
			// A1 = 15, A2 = 21, A3 = 2D, A4 = 39,
			// A#1 = 16, A#2 = 22, A#3 = 2E, A#4 = 3A,
			// B1 = 17, B2 = 23, B3 = 2F, B4 = 3B,
			// C1 = 18, C2 = 24, C3 = 30, C4 = 3C,
			// C#1 = 19, C#2 = 25, C#3 = 31, C#4 = 3D,
			// D1 = 1A, D2 = 26, D3 = 32, D4 = 3E,
			// D#1 = 1B, D#2 = 27, D#3 = 33, D#4 = 3F,
			// E1 = 1C, E2 = 28, E3 = 34, E4 = 40,
			// F1 = 1D, F2 = 29, F3 = 35, F4 = 41,
			// F#1 = 1E, F#2 = 2A, F#3 = 36, F#4 = 42,
			// G1 = 1F, G2 = 2B, G3 = 37, G4 = 43,
			// G#1 = 20, G#2 = 2C, G#3 = 38, G#4 = 44,
			this.playing = true;
			//System.out.println(isPlaying());
			// A1 is 440Hz
	
			// Follow tutorial http://www.technetexperts.com/web/change-the-pitch-of-audio-using-java-sound-api/
	
			// Play sound
			try
			{
	
				audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Grand Piano.wav"));
	
				AudioFormat inFormat = getOutFormat(audioInputStream.getFormat(), 700 + (keyNumber * 20));
	
				AudioInputStream in2 = AudioSystem.getAudioInputStream(inFormat, audioInputStream);
	
				clip = AudioSystem.getClip();
				clip.open(in2);
				clip.start();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		

	}

	public void stopKey()
	{
		playing = false;
		clip.stop();

		// Send data to screen that key is released

	}
	
	//GRAPHICS
	public void draw(Graphics g, double x, double y, double increaseXAmount, double keyHeight, Color color) {
		
		boolean p = this.playing;
		
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		this.x = x;
		this.y = y;
		this.increaseXAmount = increaseXAmount;
		this.keyHeight = keyHeight;
		this.color = color;
		
		//System.out.println(this.keyNumber);
		g2.setColor(this.color);
		
		//Do stuff here if key is playing
		if(p) {
			
			g2.setColor(Color.green);
			
		}
		
		g2.fill(new Rectangle2D.Double(this.x, this.y, this.increaseXAmount, this.keyHeight));
		
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(new Rectangle2D.Double(this.x-1, this.y, 1, this.keyHeight));
		
		
	}
	

	// Getters
	public int getKeyNumber()
	{
		return keyNumber;
	}

	// Setters

}
