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
		//message.getMessage()
		// TODO Auto-generated method stub
		System.out.println(message.toString());
		
		//Use MidiMessage to translate keys to sound.
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
	
}
