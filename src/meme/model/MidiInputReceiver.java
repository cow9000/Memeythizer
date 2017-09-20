package meme.model;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver
{
	
	private String name;
	private int number;
	
	
	public MidiInputReceiver(String name) {
		this.name = name;
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{
		//message.getMessage()
		//https://www.midi.org/specifications/item/table-1-summary-of-midi-message
		// TODO Auto-generated method stub
		if(message.getLength() > 1) {
			System.out.printf("%02X\n", message.getMessage()[1]);
			
			number = 1;
			//System.out.println(message.getMessage());
		}
		//http://www.music-software-development.com/midi-tutorial.html
		//Use this link to get data.
		
		
		//Use MidiMessage to translate keys to sound.
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
	
}
