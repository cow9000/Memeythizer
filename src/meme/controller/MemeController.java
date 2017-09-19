package meme.controller;

import meme.model.MidiHandler;

public class MemeController
{
	
	MidiHandler handler;
	public void start() {
		handler = new MidiHandler();
	}
}
