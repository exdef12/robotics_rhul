import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class DrawGrid implements Behavior {

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		
		//draws the outline for the grid by calling outline twice as it only draws half the shape
		outLine();
		outLine();
		
		//draws the width lines of the grid, passes required turning angle as argument 
		firstWidthLine();
		widthLines(-130);
		widthLines(130);
		widthLines(-130);
		widthLines(130);
		//rotates robot so its in position for drawing verticle lines  
		penUp();
		Driver.diffPilot.rotate(130);
		Driver.diffPilot.travel(10);
		Driver.diffPilot.rotate(130);
		penDown();
		
		//draws all of the verticle lines of the grid. passes required turning angle as argument
		VerticleLine(130);
		VerticleLine(-130);
		VerticleLine(130);
		VerticleLine(-130);
		VerticleLine(130);
	}

	@Override
	public void suppress() {
		Driver.diffPilot.stop();

	}

	public void widthLines(int turnValue)
	{
		//turnValue used for how much the robot needs to turn
		penUp();
		Driver.diffPilot.rotate(turnValue);
		penDown();
		Driver.diffPilot.travel(10);
		penUp();
		Driver.diffPilot.rotate(turnValue);
		penDown();
		Driver.diffPilot.travel(100);	
	}
	
	public void firstWidthLine()
	{
		//turnValue used for how much the robot needs to turn
		Driver.diffPilot.travel(10);
		penUp();
		Driver.diffPilot.rotate(130);
		penDown();
		Driver.diffPilot.travel(100);
	}
	
	public void VerticleLine(int turnValue)
	{
		//turnValue used for how much the robot needs to turn
		Driver.diffPilot.travel(20);
		penUp();
		Driver.diffPilot.rotate(turnValue);
		penDown();
		Driver.diffPilot.travel(50);
		penUp();
		Driver.diffPilot.rotate(turnValue * -1);
		penDown();
	}
	
	public void outLine()
	{
		Driver.diffPilot.travel(50);
		penUp();
		Driver.diffPilot.rotate(130);
		penDown();
		Driver.diffPilot.travel(100);
		penUp();
		Driver.diffPilot.rotate(130);	
		penDown();
	}
	
	public void penUp()
	{
		Motor.B.rotate(-40);
	}
	
	public void penDown()
	{
		Motor.B.rotate(40);
	}
}