import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class TurnAround implements Behavior {

	public boolean takeControl() {
		
		if(Driver.counter == 10 || Driver.counter == -1) {
			return true;
		}
		return false;
	}

	public void action() {
		
		LCD.drawInt(Driver.counter2, 4, 2);
		LCD.refresh();
		
		Sound.setVolume(100);
		Sound.playTone(500, 500);
	
		
		if(Driver.counter2 % 2 == 0) {
			
			Driver.diffPilot.travel(25);
			Driver.diffPilot.rotate(120);
			Driver.diffPilot.travel(50);
			Driver.diffPilot.rotate(120);
			Driver.diffPilot.travel(25);
			Driver.counter--;
			
		}
		else {
			
			Driver.diffPilot.travel(25);
			Driver.diffPilot.rotate(-120);
			Driver.diffPilot.travel(50);
			Driver.diffPilot.rotate(-120);
			Driver.diffPilot.travel(25);
			Driver.counter++;

		}
		
		Driver.counter2++;
	}

	public void suppress() {

		Driver.diffPilot.stop();
	}

}
