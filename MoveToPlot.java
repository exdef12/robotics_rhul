import lejos.robotics.subsumption.Behavior;

public class MoveToPlot implements Behavior {

	@Override
	public boolean takeControl() {
		
		//takes control when the global variable 'moveToPlot' becomes true
		if(Driver.moveToPlot) {
			
			return true;
		}
		return false;
	}

	@Override
	public void action() {

		boolean stopTurning = false;
		Driver.diffPilot.forward();
		
		//the robot travels forward in a straight line until it finds a black marker, then it will stop
		while(!stopTurning) {
			
			if(Driver.liSen.readValue() < 10) {
				
				Driver.diffPilot.stop();
				stopTurning = true;
			}
		}
		
		//turns the robot roughly to the right
		Driver.diffPilot.rotate(150);
		

		Driver.diffPilot.forward();
		stopTurning = false;
		
		//the robot travels forward in a straight line until it finds a black marker, then it will stop
		while(!stopTurning) {
			
			if(Driver.liSen.readValue() < 10) {
				
				Driver.diffPilot.stop();
				stopTurning = true;
			}
		}
		
		//adjusts the global variables to make drawNewGrid true, allowing the NewGrid behaviour to take control
		Driver.drawNewGrid = true;
		Driver.moveToPlot = false;

	}

	@Override
	public void suppress() {
		
		Driver.diffPilot.stop();

	}

}