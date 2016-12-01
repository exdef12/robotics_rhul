import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class NewGrid implements Behavior {
	static int turnAngle = 126;
	static int negTurnAngle = -126;
	
	@Override
	public boolean takeControl() {
		return true;

	}

	@Override
	public void action() {
		outLine();
		outLine();
		System.exit(0);
	}

	@Override
	public void suppress() {
		Driver.diffPilot.stop();
	}

	public void outLine()
	{
		Driver.diffPilot.travel(200);
		//penUp();
		Motor.B.rotate(-20);
		//move forwards
		moveForward();
		Driver.diffPilot.rotate(turnAngle);
		moveBack();
		penDown();
		Driver.diffPilot.travel(200);
		penUp();
		moveForward();
		Driver.diffPilot.rotate(turnAngle);
		moveBack();
		penDown();
	}
	
	public void penUp()
	{
		Motor.B.rotate(-20);
	}
	
	public void penDown()
	{
		Motor.B.rotate(25);
	}
	
	public void moveBack()
	{
		//VALUE NOT FINAL
		Driver.diffPilot.travel(-150);
		
	}
	
	public void moveForward()
	{
		Driver.diffPilot.travel(150);
	}
	
}
