package meme.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver
{
	
	private String name;
	private List<Key> Keys = new ArrayList<Key>();
	
	public MidiInputReceiver(String name) {
		this.name = name;
		
	}

	@Override
	public void send(MidiMessage message, long timeStamp)
	{
		
		//Get message data
		Byte keyByte = message.getMessage()[1];
		
		//Check if message is a valid key press
		if(message.getLength() > 1) {
			//If the array is empty, it is a key press
			if(Keys.isEmpty()) {
				
				Key pressedKey = new Key(keyByte);
				
				pressedKey.playKey();
				
				Keys.add(pressedKey);
				System.out.print("Key pressed (");
				System.out.printf("%02X)\n", keyByte);
				
			}
			//Otherwise it must either be a key press or a key release
			else {
				boolean keyPressed = true;
				//For loop to loop through Keys array and check if 
				for(int i = 0; i < Keys.size(); i++) {
					if(keyByte.equals(Keys.get(i).getKeyType())) {
						//This is a key release
						keyPressed = false;
						break;
					}
				}
				//Then after we know if it is a key press or a key release do this.
				if(keyPressed) {
					
					Key pressedKey = new Key(keyByte);
					
					
					//Play the sound of the pressed key
					//pressedKey.play();
					
					pressedKey.playKey();
					
					Keys.add(pressedKey);
					
					//Here calculate the key Frequency and then 
					
					
					System.out.print("Key pressed (");
					System.out.printf("%02X)\n", keyByte);
					
				}else {
					System.out.println("Key released");
					for(int i = 0; i < Keys.size(); i++) {
						if(keyByte.equals(Keys.get(i).getKeyType())) {
							
							//Stop sound from playing and then remove the key from array.
							
							//Keys.get(i).stop();
							
							Keys.remove(i);
							break;
							
						}
					}
				}
				
			}
			
		}
		
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
	
}
