package meme.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	
	//SOUND SETTINGS
	private AudioInputStream stream;
	private SourceDataLine line;
	
	private float speed, pitch, rate, volume;
	private boolean emulateChordPitch;
	private int quality, sampleRate, numChannels;
	private Thread playKeyThread;
	private List<NoteBlock> drawNotesPlayed;

	
	// Run sonic. From https://github.com/waywardgeek/sonic/blob/master/Main.java
    private static void runSonic(
        AudioInputStream audioStream,
        SourceDataLine line,
        float speed,
        float pitch,
        float rate,
        float volume,
        boolean emulateChordPitch,
        int quality,
        int sampleRate,
        int numChannels) throws IOException
    {
        Sonic sonic = new Sonic(sampleRate, numChannels);
        int bufferSize = line.getBufferSize();
        byte inBuffer[] = new byte[bufferSize];
        byte outBuffer[] = new byte[bufferSize];
        int numRead, numWritten;

        sonic.setSpeed(speed);
        sonic.setPitch(pitch);
        sonic.setRate(rate);
        sonic.setVolume(volume);
        sonic.setChordPitch(emulateChordPitch);
        sonic.setQuality(quality);
        do {
            numRead = audioStream.read(inBuffer, 0, bufferSize);
            if(numRead <= 0) {
                sonic.flushStream();
            } else {
                sonic.writeBytesToStream(inBuffer, numRead);
            }
            do {
                numWritten = sonic.readBytesFromStream(outBuffer, bufferSize);
                if(numWritten > 0) {
                    line.write(outBuffer, 0, numWritten);
                }
            } while(numWritten > 0);
        } while(numRead > 0);
    }
	
	
	public Key(int keyNumber)
	{
		this.keyNumber = keyNumber;
		drawNotesPlayed = new ArrayList<NoteBlock>();
		//SOUND SETTINGS
		try
		{
			this.stream = AudioSystem.getAudioInputStream(this.getClass().getResource("greenscreen-wow.wav"));
		}
		catch (UnsupportedAudioFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.speed = 1.0f;
		this.pitch = (float) Math.pow((Math.pow(2, 1.0/12)),(keyNumber+1) - 49) + .2f;
		this.rate = 1.0f;
        this.volume = 1.0f;
        this.emulateChordPitch = false;
        this.quality = 0;
		
	}

	public boolean isPlaying()
	{
		
		return this.playing;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public void playKey()
	{
		
			drawNotesPlayed.add(new NoteBlock(keyNumber));

			this.playing = true;
	        
	        
    		playKeyThread = new Thread("Play Key") {
    			public void run() {	        
		        try {
	
				        	stream = AudioSystem.getAudioInputStream(this.getClass().getResource("greenscreen-wow.wav"));
				        AudioFormat format = stream.getFormat();
				        int sampleRate = (int)format.getSampleRate();
				        int numChannels = format.getChannels(); 
				        SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, format,
				        	((int)stream.getFrameLength()*format.getFrameSize()));
				        SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
				        line.open(stream.getFormat());
				        
				        runSonic(stream, line, speed, pitch, rate, volume, emulateChordPitch, quality,
				            sampleRate, numChannels);
				        	line.start();
	
		        }catch(Exception e) {
		        		e.printStackTrace();
		        }
    			}
    		};
    		
    		playKeyThread.start();
			
	        

	}

	public void stopKey()
	{
		if(playing == true) {
			for(int i = 0; i < drawNotesPlayed.size(); i++) {
				NoteBlock currentNote = drawNotesPlayed.get(i);
				currentNote.setPlaying(false);
				
				
			}			
			
			playing = false;
			//line.stop();
			//line.drain();
		}

		// Send data to screen that key is released

	}
	
	//GRAPHICS
	public void draw(Graphics g, double x, double y, double increaseXAmount, double keyHeight, Color color) {
		
		
		
		Graphics2D g2 = (Graphics2D) g;
	
		
		//DRAW NOTES
		for(int i = 0; i < drawNotesPlayed.size(); i++) {
			boolean flat = false;
			
			if(color.equals(Color.BLACK)) {
				flat = true;
			}
			
			if(drawNotesPlayed.get(i).shouldDraw()) {
				drawNotesPlayed.get(i).draw(g,x,y,increaseXAmount,flat);
			}else {
				drawNotesPlayed.remove(i);
			}
			
		}
		
		
		
		//DRAW KEY
		this.x = x;
		this.y = y;
		this.increaseXAmount = increaseXAmount;
		this.keyHeight = keyHeight;
		this.color = color;
		
		//System.out.println(this.keyNumber);
		g2.setColor(this.color);
		
		//Do stuff here if key is playing
		if(isPlaying()) {
			g2.setColor(new Color(30, 183, 235));
		}
		
		g2.fill(new Rectangle2D.Double(this.x, this.y, this.increaseXAmount, this.keyHeight));
		
		g2.setColor(new Color(215, 215, 215));
		g2.fill(new Rectangle2D.Double(this.x-1, this.y, 1, this.keyHeight));
		
		
	}
	

	// Getters
	public int getKeyNumber()
	{
		return keyNumber;
	}

	// Setters

}
