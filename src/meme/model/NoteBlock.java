package meme.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class NoteBlock
{

	private double addedHeight;

	private double timePlaying;
	private int keyType;
	private boolean playing;
	private double addedHeightAfterNotPlaying;

	NoteBlock(int keyType)
	{

		addedHeight = 1;
		addedHeightAfterNotPlaying = 0;
		playing = true;

	}

	public boolean shouldDraw()
	{
		if (addedHeightAfterNotPlaying > 6000)
		{
			return false;
		}
		return true;
	}

	// GETTERS
	public boolean isPlaying()
	{
		return playing;
	}

	public int getKeyType()
	{
		return keyType;
	}

	// SETTER
	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}

	// GRAPHICS
	public void draw(Graphics g, double x, double y, double width, boolean flat)
	{

		Graphics2D g2 = (Graphics2D) g;

		// System.out.println("Drawn NOTE BLOCK");
		timePlaying += 0.2;
		y -= timePlaying;
		if (playing)
		{
			addedHeight = timePlaying;
		}
		else
		{
			addedHeightAfterNotPlaying += 1;
		}

		// Draw noteBlock and set color based on key type

		g2.setPaint(Color.LIGHT_GRAY);
		if (flat)
			g2.setPaint(Color.DARK_GRAY);

		g2.fill(new Rectangle2D.Double(x, y, width, addedHeight));

	}

}
