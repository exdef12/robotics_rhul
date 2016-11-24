import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class TravelForward implements Behavior {

	
	public boolean takeControl() {
 
		return true;
		
	}
	
	public void action() {
		
		//drives forward, changing counting value depending on direction
		LCD.drawInt(Driver.counter, 2, 2);
		LCD.refresh();
	
		Driver.diffPilot.travel(35);
		
		if(Driver.counter2 % 2 == 0) {
			Driver.counter++;
		}
		else if(Driver.counter2 % 2 == 1) {
			Driver.counter--;
		}
		
	}

	public void suppress() {
		
		Driver.diffPilot.stop();
	}

}
