package meme.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver
{
	
	private String name;
	private int number;
	private List<Byte> KeysOn = new ArrayList<Byte>();
	
	public MidiInputReceiver(String name) {
		this.name = name;
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{
		//message.getMessage()
		//https://www.midi.org/specifications/item/table-1-summary-of-midi-message
		// TODO Auto-generated method stub
		
		byte keyByte = message.getMessage()[1];
		
		if(message.getLength() > 1) {
			if(KeysOn.contains(keyByte)) {
				//key has been released.				
				
				for(int i = 0; i < KeysOn.size(); i++) {
					if(KeysOn.get(i).equals(keyByte)) {
						KeysOn.remove(i);
						System.out.println("Key Released");
						break;
					}
				}
				
			}else {
				//Key has been pressed
				KeysOn.add(keyByte);
				System.out.println("Key pressed");
			}
			
			//System.out.printf("%02X\n", message.getMessage()[1]);
			
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
