import lejos.robotics.subsumption.Behavior;

public class EndOfField implements Behavior {

	public boolean takeControl() {
		
		//when robot has turned 5 times
		if(Driver.counter2 == 5) {
			return true;
		}
		return false;
	}

	public void action() {
		
		Driver.diffPilot.stop();
		Driver.stageOne = false;
		Driver.stageTwo = true;
	}

	public void suppress() {
		
		Driver.diffPilot.stop();
	}

}
