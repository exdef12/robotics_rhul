import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class Kill implements Behavior {
	double lastHeardSoundAt = System.currentTimeMillis();
	@Override
	public boolean takeControl() {
		//if robot hears a clap and it has not detected a clap in last 50 milliseconds
		if (Driver.soSen.readValue() > 40)
		{
			if (System.currentTimeMillis() - lastHeardSoundAt < 50)
			{
				lastHeardSoundAt = System.currentTimeMillis();
				//updates when it last heard a clap and returns true
				lastHeardSoundAt = System.currentTimeMillis();
				return true;
			}
		}
		return false;	
	}

	@Override
	public void action() {
		//plays deep sound for explosion
		
		
		Sound.playTone(3000, 250);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	
		//robot spins
		Driver.diffPilot.rotate(1000);
		//displays on screen "RIP"
		LCD.drawString("RIP", 3, 1);
		
		
	}

	@Override
	public void suppress() {
		Driver.diffPilot.stop();
	}

}
