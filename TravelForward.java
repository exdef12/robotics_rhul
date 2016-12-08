import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class TravelForward implements Behavior {

	
	public boolean takeControl() {
		
		//always returns true to travel forward, but this behaviour has the lowest priority so it can be interrupted by other behaviours
		return true;
		
	}
	
	public void action() {
		
		//drives forward to the next grid cell, changing the column counter value depending on which direction it is travelling
		LCD.drawInt(Driver.columnCounter, 2, 2);
		LCD.refresh();
	
		Driver.mineDetection = true;
		Driver.diffPilot.travel(35);
		
		if(Driver.rowCounter % 2 == 0) {
			Driver.columnCounter++;
		}
		else if(Driver.rowCounter % 2 == 1) {
			Driver.columnCounter--;
		}
		
	}

	public void suppress() {
		
		Driver.diffPilot.stop();
	}

}
