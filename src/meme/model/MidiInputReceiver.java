package meme.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

import meme.view.PianoScreenDisplay;

public class MidiInputReceiver implements Receiver
{

	private String name;
	int pressedKeys = 0;

	PianoScreenDisplay piano;

	public MidiInputReceiver(String name, PianoScreenDisplay pianoScreen)
	{

		piano = pianoScreen;
		this.name = name;

	}

	public MidiInputReceiver returnThis()
	{
		return this;
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{

		// Get message data
		Byte keyByte = message.getMessage()[1];
		// System.out.println(Byte.toString(keyByte));
		// Check if message is a valid key press
		if (message.getLength() > 1)
		{

			// Check if key is currently playing
			if (piano.returnKey(translateKeyType(keyByte)).isPlaying())
			{
				piano.returnKey(translateKeyType(keyByte)).stopKey();
			}
			else
			{
				piano.returnKey(translateKeyType(keyByte)).playKey();

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

		keyNum = fullNum - 21;

		return keyNum;
	}

	public Key returnKey(int i)
	{
		return piano.returnKey(i);
	}

}
