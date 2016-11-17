import lejos.robotics.subsumption.Behavior;

public class EndOfField implements Behavior {

	public boolean takeControl() {
		
		if(Driver.counter2 == 5) {
			return true;
		}
		return false;
	}

	public void action() {

		Driver.diffPilot.stop();
	}

	public void suppress() {
		
		Driver.diffPilot.stop();
	}

}
