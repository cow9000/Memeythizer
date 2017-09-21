package meme.controller;

import meme.model.Key;
import meme.model.MidiHandler;
import meme.view.PianoScreenDisplay;

public class MemeController
{
	PianoScreenDisplay pianoScreen;
	MidiHandler handler;
	public void start() {
		handler = new MidiHandler();
		pianoScreen = new PianoScreenDisplay();
		
	}
}
