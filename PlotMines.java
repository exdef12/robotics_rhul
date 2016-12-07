import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class PlotMines implements Behavior {

	@Override
	public boolean takeControl() {
		
		//takes control when the global variable 'plottingMines' becomes true
		if(Driver.plotMines) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		
		//waits for someone to press the orange button, indicating the robot is ready in position to begin plotting
		LCD.clear();
		LCD.drawString("Waiting...", 2, 2);
		LCD.refresh();
		
		Button.ENTER.waitForPressAndRelease();
		
		LCD.clear();
		
		//a loop which cycles through the grid array, using the pen to plot a mark on the paper every time it finds a mine in the array
		for(int i = 0; i < 5; i++) {
			
			for(int j = 0; j < 10; j++) {
				
				if(Driver.mineField[i][j] == 1) {
					Motor.B.rotate(85);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Driver.diffPilot.travel(3);
					Motor.B.rotate(-85);
					
				}
				
				Driver.diffPilot.travel(12);
			}
			
			//turns in an alternate direction each time (left or right)
			if(i % 2 == 0) {
				
				Driver.diffPilot.travel(250);
				
				Driver.diffPilot.steer(160, 270);
	

			}
			else {
				
				Driver.diffPilot.travel(250);
				
				Driver.diffPilot.steer(160, -270);


			}
		}
		
		
		//the whole program is finished, therefore it waits for another orange button press to exit the program
		LCD.clear();
		LCD.drawString("Program Finished.", 2, 2);
		LCD.refresh();
		
		Button.ENTER.waitForPressAndRelease();
		

		
		

	}

	@Override
	public void suppress() {
		
		Driver.diffPilot.stop();

	}

}
