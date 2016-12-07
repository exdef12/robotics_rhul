import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	
	//passes the wheel measurements to a differential pilot which will perform any of the robot's movements
	static DifferentialPilot diffPilot = new DifferentialPilot(30, 115, Motor.A, Motor.C);
	
	//declares a light sensor and sound sensor for use throughout all of the behaviour classes
	static LightSensor liSen = new LightSensor(SensorPort.S1, true);
	static SoundSensor souSen = new SoundSensor(SensorPort.S2);
	
	//the size of the paper grid used as the mine field
	static int[][] mineField = new int[5][10];
	
	//these counters are used by the robot to remember its grid position as it traverses the whole grid
	static int columnCounter = 0;
	static int rowCounter = 0;
	
	//these are global variables that determine which stage of the program the robot should do next
	static boolean plotMines = false;
	static boolean moveToPlot = false;
	static boolean drawNewGrid = false;
	static boolean mineDetection = true;

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

		//creates behaviour objects for all of the behaviour classes
		Behavior travelForward = new TravelForward();
		Behavior turnAround = new TurnAround();
		Behavior endOfField= new EndOfField();
		Behavior foundMine = new FoundMine();
		Behavior exit = new Exit();
		Behavior plotMines = new PlotMines();
		Behavior kill = new Kill();
		Behavior moveToPlot = new MoveToPlot();
		Behavior newGrid = new NewGrid();
		
		//puts all the behaviour objects into an array
		Behavior[] behaviours = {travelForward, turnAround, foundMine, endOfField, kill, moveToPlot, newGrid, plotMines, exit};
		//passes the behaviour array to an arbitrator
		Arbitrator arbitrator = new Arbitrator(behaviours);
		
		//sets the robots travelling speed
		diffPilot.setRotateSpeed(360);
		
		//sets the pen arm's turning speed.
		Motor.B.setSpeed(40);
		
		//starts the arbitrator, which cycles through every behaviour, effectively running the program.
		arbitrator.start();

		
	}

}
