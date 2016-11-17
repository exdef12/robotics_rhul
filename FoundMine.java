import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class FoundMine implements Behavior {

	@Override
	public boolean takeControl() {
		int lV = Driver.liSen.readValue() ;
		if (lV < 10 )
		{
		  return true;
		}
		return false;
	}

	@Override
	public void action() {

        Driver.mineField[Driver.counter2][Driver.counter]=1;
        Sound.playTone(1000, 250);
	}

	@Override
	public void suppress() {
		
       Driver.diffPilot.stop();
	}

}
