import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	
	static DifferentialPilot diffPilot = new DifferentialPilot(30, 115, Motor.A, Motor.C);
	static LightSensor liSen = new LightSensor(SensorPort.S1, true);
	static SoundSensor souSen = new SoundSensor(SensorPort.S2);
	static int[][] mineField = new int[5][10];
	static Arbitrator arbitrator;
	static int counter = 0;
	static int counter2 = 0;
	static boolean stageOne = true;
	static boolean stageTwo = false;

	public static void main(String[] args) {
		
		//calibrates light sensor for bright light
		LCD.drawString("Point to white", 2,2);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		liSen.calibrateHigh();
		
		//calibrate light sensor for dark light
		LCD.drawString("Point to mine", 2, 3);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		liSen.calibrateLow();
		
		//waits for button press to start mine sweeping program
		LCD.drawString("Ready?", 2, 4);
		try {
			Button.ENTER.waitForPressAndRelease();
		} catch(Exception e){}
		
		LCD.clear();
		LCD.drawInt(Driver.counter2, 4, 2);
		LCD.refresh();
		
		Behavior travelForward = new TravelForward();
		Behavior turnAround = new TurnAround();
		Behavior endOfField= new EndOfField();
		Behavior foundMine = new FoundMine();
		Behavior exit = new Exit();
		Behavior plotMines = new PlotMines();
		Behavior kill = new Kill();
		//Behavior drawGrid = new DrawGrid();
		
		Behavior[] behaviours = {travelForward, turnAround, foundMine, endOfField, kill, plotMines, exit};
		//Behavior[] behaviours = {drawGrid};
		arbitrator = new Arbitrator(behaviours);
		
		diffPilot.setTravelSpeed(360);
		diffPilot.setRotateSpeed(360);
		Motor.B.setSpeed(40);
		
		arbitrator.start();

		
	}

}
