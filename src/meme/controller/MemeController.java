package meme.controller;

import meme.model.MidiHandler;
import meme.view.PianoScreenDisplay;

public class MemeController
{
	PianoScreenDisplay pianoScreen;
	MidiHandler handler;

	public void start()
	{
		pianoScreen = new PianoScreenDisplay();
		handler = new MidiHandler(pianoScreen);
	}
}
