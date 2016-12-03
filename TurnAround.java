import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class TurnAround implements Behavior {

	public boolean takeControl() {
		
		//takes control when robots has driven forward 10 times in either direction whilst scanning the mine field
		if(Driver.columnCounter == 10 || Driver.columnCounter == -1) {
			return true;
		}
		return false;
	}

	public void action() {
		
		//turns off the mineDetection global variable so that the FoundMine behaviour does not take control by accident when the robot is trying to turn around.
		Driver.mineDetection = false;

		Sound.setVolume(100);
		Sound.playTone(500, 500);
	
		boolean stopTurning = false;
		
		//turns in an alternate direction (left or right) depending on which direction the robot was travelling along the grid
		if(Driver.rowCounter % 2 == 0) {
			
			Driver.diffPilot.travel(250);
			
			Driver.diffPilot.steer(148);
			
			//stops the robot turning once it finds a black marker, indicating its facing the next row to be scanned
			while(!stopTurning) {
				
				if(Driver.liSen.readValue() < 10) {
					
					Driver.diffPilot.stop();
					stopTurning = true;
				}
			}
			
			//adjusts the column counters, ready for scanning the next row, and moves the robot forward onto the first grid cell.
			Driver.columnCounter--;
			
			Driver.diffPilot.travel(35);
			
			Driver.columnCounter--;
			
			
		}
		else {
			
			Driver.diffPilot.travel(250);
			
			Driver.diffPilot.steer(-148);
			
			//stops the robot turning once it finds a black marker, indicating its facing the next row to be scanned
			while(!stopTurning) {
				
				if(Driver.liSen.readValue() < 10) {
					
					Driver.diffPilot.stop();
					stopTurning = true;
				}
			}
			
			Driver.columnCounter++;
			
			Driver.diffPilot.travel(35);
			Driver.mineDetection = true;
			
			Driver.columnCounter++;

		}
		
		//increments the row counter because it will now scan the next row
		Driver.rowCounter++;
	}

	public void suppress() {

		Driver.diffPilot.stop();
	}

}
