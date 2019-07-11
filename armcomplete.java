import ShefRobot.*;

public class armcomplete {
	
    private static Robot robot;
    private static Motor armMotor;
    private static Speaker speaker;
    private static ColorSensor sensor;
    private static ColorSensor.Color col;			
    

    public static void main(String[] args) {
      	robot = new Robot("dia-lego-f7");
    	armMotor = robot.getLargeMotor(Motor.Port.D);
    	speaker = robot.getSpeaker();
    	armMotor.setSpeed(100);
    	sensor = robot.getColorSensor(Sensor.Port.S2);
        goForward(80);
        for (int i=0;i<10;i++) {
            goBackward(160);
            goForward(160);
    	}
    	robot.close();
    }
    protected static void goForward(int count) {
    	armMotor.resetTachoCount();
    	armMotor.forward();
    	waitfor(count,true);
    }
    protected static void goBackward(int count) {
    	armMotor.resetTachoCount();
    	armMotor.backward();
    	waitfor(count,true);
    }

    private static void waitfor(int count, boolean armback) {
    	boolean af = false; 
    	do {
    		int ac = armMotor.getTachoCount();
    		if(armback && (ac < (0-count))) {
    			armMotor.stop();
    			af = true;
    		} else if(ac > count) {
    			armMotor.stop();
    			af = true;
    		}
    	} while(!(af));
    }
	
}