import lejos.robotics.subsumption.Behavior;
import lejos.nxt.Button;
import lejos.nxt.Sound;


System.out.print("test");

public class Exit implements Behavior {

	
	public boolean takeControl() {
		
		//takes control when the orange button is pressed down
		if (Button.ENTER.isDown())
		{
			return true;
		}
		return false;
	}

	
	public void action() {
		
		//plays a three note tune before exiting the program
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
