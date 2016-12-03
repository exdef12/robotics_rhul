import lejos.robotics.subsumption.Behavior;

public class EndOfField implements Behavior {

	public boolean takeControl() {
		
		//takes control when robot has turned 5 times whilst scanning the mine field
		if(Driver.rowCounter == 5) {
			return true;
		}
		return false;
	}

	public void action() {
		
		//adjusts the global variables to make moveToPlot true, allowing the MoveToPlot behaviour to take control
		Driver.diffPilot.stop();
		Driver.mineDetection = false;
		Driver.moveToPlot = true;

	}

	public void suppress() {
		
		Driver.diffPilot.stop();
	}

}
