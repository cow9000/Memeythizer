package meme.model;

import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiHandler
{
	MidiDevice device;
	MidiInputReceiver keyboardReciever;
	
	public MidiHandler()
	{
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		
		for (int i = 0; i < infos.length; i++)
		{
			try
			{
				device = MidiSystem.getMidiDevice(infos[i]);
				List<Transmitter> transmitter = device.getTransmitters();
				for (int trans = 0; trans < transmitter.size(); trans++)
				{
					transmitter.get(trans).setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
				}

				Transmitter keyboardTransmitter = device.getTransmitter();
				
				keyboardReciever = new MidiInputReceiver(device.getDeviceInfo().toString());
				
				keyboardTransmitter.setReceiver(keyboardReciever);
				
				device.open();

				System.out.println(device.getDeviceInfo() + " - Opened");

			}
			catch (MidiUnavailableException e)
			{
				e.printStackTrace();
			}

		}
		
		
		
	}
	
	
	public MidiInputReceiver returnReciever() {
		return keyboardReciever;
	}
	

}
