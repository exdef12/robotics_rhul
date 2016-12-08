import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class Kill implements Behavior {
	
	double lastHeardSoundAt = System.currentTimeMillis();
	
	@Override
	public boolean takeControl() {
		
		//takes control if robot hears a loud clap noise, and it has not already detected a clap in last 500 milliseconds
		if(Driver.mineDetection) 
		{
			if (Driver.souSen.readValue() > 85)
			{
				if (System.currentTimeMillis() - lastHeardSoundAt > 500)
				{
					//updates when it last heard a clap
					lastHeardSoundAt = System.currentTimeMillis();
					return true;
				}
			}
		}
		
		return false;	
	}

	@Override
	public void action() {
		
		//plays deep sound for explosion
		Sound.playTone(200, 250);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	
		//displays on screen "RIP"
		LCD.drawString("RIP", 5, 5);
		LCD.refresh();
		
		//robot spins
		Driver.diffPilot.setRotateSpeed(720);
		Driver.diffPilot.rotate(545);
		Driver.diffPilot.setRotateSpeed(360);
		
		LCD.clear();
		
	}

	@Override
	public void suppress() {
		Driver.diffPilot.stop();
	}

}