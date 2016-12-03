import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class NewGrid implements Behavior {
	
	static int turnAngle = 126;
	static int negTurnAngle = -126;
	
	@Override
	public boolean takeControl() {
		
		//takes control when the global variable 'drawNewGrid' becomes true
		if(Driver.drawNewGrid) {
			
			return true;
		}
		return false;

	}

	@Override
	public void action() {
		
		//waits for someone to press the orange button, indicating the robot is ready in position to begin drawing
		LCD.clear();
		LCD.drawString("Waiting...", 2, 2);
		LCD.refresh();
		
		Button.ENTER.waitForPressAndRelease();
		
		//calls the outline method (found below in this class) twice, to draw a full rectangle.
		outLine();
		outLine();
		
		Driver.plotMines = true;
		Driver.drawNewGrid = false;
		
		
	}

	@Override
	public void suppress() 
	{
		Driver.diffPilot.stop();
	}

	public void outLine()
	{
		//this method draws one line width-ways and one line length ways
		Driver.diffPilot.travel(200);
		Motor.B.rotate(-20);
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
		//this method pulls the pen arm up
		Motor.B.rotate(-20);
	}
	
	public void penDown()
	{
		//this method puts the pen arm down
		Motor.B.rotate(25);
	}
	
	public void moveBack() 
	{
		Driver.diffPilot.travel(-150);
		
	}
	
	public void moveForward()
	{
		Driver.diffPilot.travel(150);
	}
	
}