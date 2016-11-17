import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	
	static DifferentialPilot diffPilot = new DifferentialPilot(30, 115, Motor.A, Motor.C);
	static LightSensor liSen = new LightSensor(SensorPort.S1, true);
	static int[][] mineField = new int[5][10];
	static Arbitrator arbitrator;
	static int counter = 0;
	static int counter2 = 0;


	public static void main(String[] args) {
		
		LCD.drawString("Point to white", 2,2);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		liSen.calibrateHigh();
		
		LCD.drawString("Point to mine", 2, 3);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		liSen.calibrateLow();
		
		LCD.drawString("Ready?", 2, 4);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		
		LCD.clear();
		
		Behavior travelForward = new TravelForward();
		Behavior turnAround = new TurnAround();
		Behavior endOfField= new EndOfField();
		Behavior foundMine = new FoundMine();
		Behavior exit = new Exit();
		
		Behavior[] behaviours = {travelForward, turnAround, foundMine, endOfField, exit};
		arbitrator = new Arbitrator(behaviours);
		
		diffPilot.setTravelSpeed(360);
		diffPilot.setRotateSpeed(360);
		
		arbitrator.start();

		
	}

}
