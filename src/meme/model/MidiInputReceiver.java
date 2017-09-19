package meme.model;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver
{
	
	public String name;
	
	public MidiInputReceiver(String name) {
		this.name = name;
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{
		// TODO Auto-generated method stub
		System.out.println(message.toString());
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
	
}
