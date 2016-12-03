import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class FoundMine implements Behavior {

	@Override
	public boolean takeControl() {
		
		//take control when the light sensor reads a value below 10, indicating its found a black mine.
		int lV = Driver.liSen.readValue();
		
		if (lV < 10 && Driver.mineDetection)
		{
		  return true;
		}
		return false;
	}

	@Override
	public void action() {
		
		//plots mine location in the grid array using current row and column counter values
       
        Sound.playTone(1000, 250);
        
        
        if(Driver.rowCounter % 2 == 0) {
        	
			Driver.columnCounter++;
			Driver.mineField[Driver.rowCounter][Driver.columnCounter-1] = 1;
			LCD.drawString("("+Driver.rowCounter+","+(Driver.columnCounter-1)+")", 2, 3);
		    LCD.refresh();
		}
		else if(Driver.rowCounter % 2 == 1) {
			
			Driver.columnCounter--;
			Driver.mineField[Driver.rowCounter][Driver.columnCounter+1] = 1;
			LCD.drawString("("+Driver.rowCounter+","+(Driver.columnCounter+1)+")", 2, 3);
		    LCD.refresh();
		}
        
        //moves the robot forward to the next grid cell
        Driver.diffPilot.travel(35);
	}

	@Override
	public void suppress() {
		
       Driver.diffPilot.stop();
	}

}
