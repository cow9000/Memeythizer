package meme.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Key
{

	private Clip clip;
	private int keyNumber;
	private boolean playing;
	private double x;
	private double y;
	private double increaseXAmount;
	private double keyHeight;
	private Color color;

	// SOUND SETTINGS
	private AudioInputStream stream;
	private SourceDataLine line;

	private float speed, pitch, rate, volume;
	private boolean emulateChordPitch;
	private int quality, sampleRate, numChannels;
	private List<NoteBlock> drawNotesPlayed;

	private static File tempFile;
	
	Thread playKeyThread;
	
	private URL pathToSound;

	// Run sonic. From https://github.com/waywardgeek/sonic/blob/master/Main.java
	private static void runSonic(AudioInputStream audioStream, SourceDataLine line, float speed, float pitch, float rate, float volume, boolean emulateChordPitch, int quality, int sampleRate,
			int numChannels) throws IOException, UnsupportedAudioFileException
	{
		Sonic sonic = new Sonic(sampleRate, numChannels);
		int bufferSize = line.getBufferSize() * 2;
		byte inBuffer[] = new byte[bufferSize];
		byte outBuffer[] = new byte[bufferSize];
		int numRead, numWritten;
		tempFile = new File("tempFile.wav");
		tempFile.delete();
		tempFile = new File("tempFile.wav");
		sonic.setSpeed(speed);
		sonic.setPitch(pitch);
		sonic.setRate(rate);
		sonic.setVolume(volume);
		sonic.setChordPitch(emulateChordPitch);
		sonic.setQuality(quality);
		do
		{
			numRead = audioStream.read(inBuffer, 0, bufferSize);
			if (numRead <= 0)
			{
				sonic.flushStream();
			}
			else
			{
				sonic.writeBytesToStream(inBuffer, numRead);
			}
			do
			{
				numWritten = sonic.readBytesFromStream(outBuffer, bufferSize);
				if (numWritten > 0)
				{
					ByteArrayInputStream bais = new ByteArrayInputStream(outBuffer);
					AudioInputStream audioInputStream = new AudioInputStream(bais, audioStream.getFormat(), (long) (8 * (bufferSize / 2)));
					AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, tempFile);
				}
			} while (numWritten > 0);
		} while (numRead > 0);

	}

	public Key(int keyNumber, URL pathToSound)
	{
		this.keyNumber = keyNumber;
		this.pathToSound = pathToSound;
		drawNotesPlayed = new ArrayList<NoteBlock>();
		// SOUND SETTINGS
		this.speed = 1.0f;
		this.pitch = (float) Math.pow((Math.pow(2, 1.0 / 12)), (keyNumber + 1) - 49);
		this.rate = 1.0f;
		this.volume = 1.0f;
		this.emulateChordPitch = false;
		this.quality = 0;

		try
		{
			stream = AudioSystem.getAudioInputStream(pathToSound);
			AudioFormat format = stream.getFormat();
			int sampleRate = (int) format.getSampleRate();
			int numChannels = format.getChannels();
			SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, format, ((int) stream.getFrameLength() * format.getFrameSize()));
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(stream.getFormat());

			runSonic(stream, line, speed, pitch, rate, volume, emulateChordPitch, quality, sampleRate, numChannels);

		}
		catch (Exception e)
		{
			// e.printStackTrace();
			System.out.println("PATH NOT FOUND! " + pathToSound);
		}

		try
		{
			AudioInputStream sound = AudioSystem.getAudioInputStream(tempFile);

			// load the sound into memory (a Clip)
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		

	}

	public void setPath(URL path)
	{

		if (!this.pathToSound.equals(path))
		{
			this.pathToSound = path;

			try
			{
				stream = AudioSystem.getAudioInputStream(pathToSound);
				AudioFormat format = stream.getFormat();
				int sampleRate = (int) format.getSampleRate();
				int numChannels = format.getChannels();
				SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, format, ((int) stream.getFrameLength() * format.getFrameSize()));
				SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(stream.getFormat());

				runSonic(stream, line, speed, pitch, rate, volume, emulateChordPitch, quality, sampleRate, numChannels);

			}
			catch (Exception e)
			{
				// e.printStackTrace();
				System.out.println("PATH NOT FOUND! " + pathToSound);
			}

			try
			{
				AudioInputStream sound = AudioSystem.getAudioInputStream(tempFile);

				// load the sound into memory (a Clip)
				DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(sound);
				System.out.println("SETTING URL");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}

	public boolean isPlaying()
	{

		return this.playing;
	}

	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}

	public void playKey()
	{
		if (!isPlaying())
		{
			drawNotesPlayed.add(new NoteBlock(keyNumber));

			this.playing = true;

			// PLAY THE CLIP AND STUFF

			clip.setFramePosition(0);
			clip.start();
			
		}
		
		

	}

	public void stopKey()
	{
		if (isPlaying())
		{
			for (int i = 0; i < drawNotesPlayed.size(); i++)
			{
				NoteBlock currentNote = drawNotesPlayed.get(i);
				currentNote.setPlaying(false);
			}

			playing = false;
			if(clip.isRunning()) clip.stop();
		}
	}

	// GRAPHICS
	public void draw(Graphics g, double x, double y, double increaseXAmount, double keyHeight, Color color)
	{
		Graphics2D g2 = (Graphics2D) g;

		// DRAW NOTES
		for (int i = 0; i < drawNotesPlayed.size(); i++)
		{
			boolean flat = false;

			if (color.equals(Color.BLACK))
			{
				flat = true;
			}

			if (drawNotesPlayed.get(i).shouldDraw())
			{
				drawNotesPlayed.get(i).draw(g, x, y, increaseXAmount, flat);
			}
			else
			{
				drawNotesPlayed.remove(i);
			}

		}

		// DRAW KEY
		this.x = x;
		this.y = y;
		this.increaseXAmount = increaseXAmount;
		this.keyHeight = keyHeight;
		this.color = color;

		// System.out.println(this.keyNumber);
		g2.setColor(this.color);

		// Do stuff here if key is playing
		if (isPlaying())
		{
			g2.setColor(new Color(30, 183, 235));
		}

		g2.fill(new Rectangle2D.Double(this.x, this.y, this.increaseXAmount, this.keyHeight));

		g2.setColor(new Color(215, 215, 215));
		g2.fill(new Rectangle2D.Double(this.x - 1, this.y, 1, this.keyHeight));

	}

	// Getters
	public int getKeyNumber()
	{
		return keyNumber;
	}

	// Setters

}
