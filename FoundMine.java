import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class FoundMine implements Behavior {

	@Override
	public boolean takeControl() {
		
		//when light sensor detects dark light
		int lV = Driver.liSen.readValue();
		
		if (lV < 10)
		{
		  return true;
		}
		return false;
	}

	@Override
	public void action() {
		
		//plots mine location in array using current counter values
        Driver.mineField[Driver.counter2][Driver.counter] = 1;
        Sound.playTone(1000, 250);
        
        Driver.diffPilot.travel(35);
        if(Driver.counter2 % 2 == 0) {
			Driver.counter++;
		}
		else if(Driver.counter2 % 2 == 1) {
			Driver.counter--;
		}
	}

	@Override
	public void suppress() {
		
       Driver.diffPilot.stop();
	}

}
