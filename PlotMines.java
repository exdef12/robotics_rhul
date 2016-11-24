import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class PlotMines implements Behavior {

	@Override
	public boolean takeControl() {

		if(Driver.stageTwo) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		
		LCD.clear();
		LCD.drawString("Waiting...", 2, 2);
		LCD.refresh();
		
		Button.ENTER.waitForPressAndRelease();
		
		LCD.clear();
		
		for(int i = 0; i < 5; i++) {
			
			for(int j = 0; j < 10; j++) {
				
				if(Driver.mineField[i][j] == 1) {
					Motor.B.rotate(40);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Motor.B.rotate(-40);
					
				}
				
				Driver.diffPilot.travel(10);
			}
			
			if(i % 2 == 0) {
				
				Driver.diffPilot.travel(50);
				Driver.diffPilot.rotate(120);
				Driver.diffPilot.travel(50);
				Driver.diffPilot.rotate(120);
				Driver.diffPilot.travel(25);

			}
			else {
				
				Driver.diffPilot.travel(50);
				Driver.diffPilot.rotate(-120);
				Driver.diffPilot.travel(50);
				Driver.diffPilot.rotate(-120);
				Driver.diffPilot.travel(25);

			}
		}
		
		Driver.stageTwo = false;

	}

	@Override
	public void suppress() {
		
		Driver.diffPilot.stop();

	}

}
