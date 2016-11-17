import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Button;
import lejos.nxt.Sound;


public class Exit implements Behavior {

	
	public boolean takeControl() {
		if (Button.ENTER.isDown())
		{
			return true;
		}
		return false;
	}

	
	public void action() {
		
		Sound.playTone(800, 250);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Sound.playTone(600, 250);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Sound.playTone(400, 250);
		
		System.exit(0);

	}

	public void suppress() {
		Driver.diffPilot.stop();

	}

}
