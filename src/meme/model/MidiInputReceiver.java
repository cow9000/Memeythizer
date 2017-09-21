package meme.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import meme.view.PianoScreenDisplay;

public class MidiInputReceiver implements Receiver
{

	private String name;
	private List<Key> Keys = new ArrayList<Key>();

	PianoScreenDisplay pianoScreen;
	
	public MidiInputReceiver(String name, PianoScreenDisplay pianoScreen)
	{
		this.name = name;
		for(int i = 0; i < 88; i++) {
			Keys.add(new Key(i, pianoScreen));
			Keys.get(i).playKey();
		}
		
		this.pianoScreen = pianoScreen;
		
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{

		// Get message data
		Byte keyByte = message.getMessage()[1];

		// Check if message is a valid key press
		if (message.getLength() > 1)
		{
			
			//Check if key is currently playing
			if(Keys.get(translateKeyType(keyByte)).isPlaying()) {
				Keys.get(translateKeyType(keyByte)).stopKey();
			}else {
				Keys.get(translateKeyType(keyByte)).playKey();
			}
		}

	}
	

	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}
	
	public int translateKeyType(byte keyType)
	{
		int keyNum = 1;
		String keyTypeToString = Byte.toString(keyType);

		int fullNum = 0;

		int num1 = Integer.parseInt(keyTypeToString.substring(0, 1));
		String num2String = keyTypeToString.substring(1, 2);

		int num2 = 0;

		// Check if it is a value like 1A
		// If so translate to 20, or 21
		if (num2String.equalsIgnoreCase("A"))
		{
			num1 = num1 * 10;
			num2 = 10;
			fullNum = num1 + num2;
		}
		else if (num2String.equalsIgnoreCase("B"))
		{
			num1 = num1 * 10;
			num2 = 11;
			fullNum = num1 + num2;
		}
		else if (num2String.equalsIgnoreCase("C"))
		{
			num1 = num1 * 10;
			num2 = 12;
			fullNum = num1 + num2;
		}
		else if (num2String.equalsIgnoreCase("D"))
		{
			num1 = num1 * 10;
			num2 = 13;
			fullNum = num1 + num2;
		}
		else if (num2String.equalsIgnoreCase("E"))
		{
			num1 = num1 * 10;
			num2 = 14;
			fullNum = num1 + num2;
		}
		else if (num2String.equalsIgnoreCase("F"))
		{
			num1 = num1 * 10;
			num2 = 15;
			fullNum = num1 + num2;
		}
		else
		{
			fullNum = Integer.parseInt(keyTypeToString);
		}

		keyNum = fullNum - 15;

		System.out.print(keyNum);

		return keyNum;
	}
	

}
