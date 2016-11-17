import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Button;


public class Exit implements Behavior {

	
	public boolean takeControl() {
		if (Button.ENTER.isDown())
		{
			return true;
		}
		return false;
	}

	
	public void action() {
		System.exit(0);

	}

	public void suppress() {
		Driver.diffPilot.stop();

	}

}
