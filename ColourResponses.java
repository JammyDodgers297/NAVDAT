import com.sun.org.apache.xpath.internal.operations.And;

import ShefRobot.*;
import java.util.Scanner;
public class ColourResponses {
	
    // These lines define the names (variables) we will use elsewhere in the program
    // Having all these names at the top, outside of the main method is 
    // not beautiful, Object-Oriented programming style, but we wanted
    // to keep this exercise simple ;)
    private static Robot robot;
	private static Motor frontMotor;
    private static Motor leftMotor;
    private static Motor rightMotor;
    private static Speaker speaker;
    private static ColorSensor sensor;
    private static ColorSensor.Color col;			
    private static GyroSensor gyro;
    public static void main(String[] args) {	
    	robot = new Robot("dia-lego-f8");
    	leftMotor = robot.getLargeMotor(Motor.Port.A);
    	rightMotor = robot.getLargeMotor(Motor.Port.B);
        frontMotor = robot.getLargeMotor(Motor.Port.C);
    	speaker = robot.getSpeaker();
        leftMotor.setSpeed(150);
    	rightMotor.setSpeed(150);
        frontMotor.setSpeed(10);
        sensor = robot.getColorSensor(Sensor.Port.S1);
        
        
        
        
        while (true){
            leftMotor.setSpeed(150);
    	    rightMotor.setSpeed(150);
            frontMotor.setSpeed(10);
            leftMotor.forward();
		    rightMotor.forward(); 
            checkcolor();
            if (col==ColorSensor.Color.BLUE){
                speaker.playTone(796,123);
		 	    speaker.playTone(567,077);
		 	    System.out.println("WATER DETECTED");
                blue();
            }
            else if (col == ColorSensor.Color.GREEN) {
			    speaker.playTone(100,453);
			    speaker.playTone(789,456);
			    System.out.println("RUBBLE DETECTED");
                green();
            }
            else if (col == ColorSensor.Color.RED) {
			    speaker.playTone(345,978);
			    speaker.playTone(556,100);
			    System.out.println("FIRE DETECTED DANGER! DANGER!");
                red();
            }
            else if (col==ColorSensor.Color.YELLOW){
                speaker.playTone(123,456);
			    speaker.playTone(856,435);
			    System.out.println("PERSON IN DANGER");
            }


        }
      


    }
        
    
    private static void blue() {
        frontMotor.setSpeed(0);
        checkcolor();
        while (col==ColorSensor.Color.BLUE){
            gobackward(2);
            checkcolor();
            System.out.println(col);
        }
    

        turnRight(100);//turn 90 degrees
        goforward(100);//forwards radius of circle
        turnLeft(100);//turn towards top of circle
        goforward(300);//go along length of circle
        turnLeft(100);
        goforward(100);
    }
        
    private static void red() {
        frontMotor.setSpeed(0);
        col = sensor.getColor();
        while (col==ColorSensor.Color.RED){
            gobackward(2);
            col = sensor.getColor();
            System.out.println(col);

        }
    

        turnRight(70);//turn 90 degrees
        goforward(120);//forwards radius of circle
        turnLeft(70);//turn towards top of circle
        goforward(300);//go along length of circle
        turnLeft(70);
        goforward(120);
        }
    private static void green() {
        leftMotor.setSpeed(0);
    	rightMotor.setSpeed(0);
        frontMotor.setSpeed(0);
        System.out.println("home :)");        }
protected static void goforward(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.forward();
    	waitfor(count,false,false);
    }
protected static void gobackward(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.backward();
    	waitfor(count,false,false);
    }   

protected static void turnRight(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.backward();
    	waitfor(count,false,true);
    }
protected static void turnLeft(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.forward();
    	waitfor(count,true,false);
    }
private static void waitfor(int count, boolean leftback, boolean rightback) {
    	boolean lf = false; 
    	boolean rf = true;
    	do {
    		int ltc = leftMotor.getTachoCount();
    		int rtc = rightMotor.getTachoCount();
    		if(leftback && (ltc < (0-count))) {
    			leftMotor.stop();
    			lf = true;
    		} else if(ltc > count) {
    			leftMotor.stop();
    			lf = true;
    		}
    		if(rightback && (rtc < (0-count))) {
    			rightMotor.stop();
    			rf = true;
    		} else if(rtc > count) {
    			rightMotor.stop();
    			rf = true;
    		}
    	} while(!(lf && rf));
    }
private static ColorSensor.Color checkcolor(){
    boolean hooray = false; //Used to break from loop when finds colour
		//Traverse the line
		//while (hooray == false) {
		//	leftMotor.forward();
    	//	rightMotor.forward();
		//	col = sensor.getColor();

			// Turn until found line
		//	while (col == ColorSensor.Color.BLACK && col != ColorSensor.Color.WHITE) {
				//Turning left
		//		leftMotor.resetTachoCount();
    	//		rightMotor.resetTachoCount();
    	//		leftMotor.backward();
    	//		rightMotor.forward();
    	//		waitfor(45,true,false);
		//		col = sensor.getColor();
				// If the line is not found, turn right
		//		if (col == ColorSensor.Color.BLACK) {
		//			leftMotor.resetTachoCount();
    	//			rightMotor.resetTachoCount();
    	//			leftMotor.forward();
    	//			rightMotor.backward();
		//			waitfor(90,false,true);
		//			col = sensor.getColor();
		//		}
			// Hack cuz sensor trash. Must check the colour 10 times
            int count = 0;
            ColorSensor.Color precol = sensor.getColor();
			for (int i = 0; i < 15; i++) {
				sensor.setFloodlightState(ColorSensor.FloodlightState.WHITE);
				col = sensor.getColor();
				//if (col != ColorSensor.Color.BLACK && col != ColorSensor.Color.WHITE) {
					if (precol == col) {
                        count++;
                    }
                    
				//	System.out.println(col);
				}
			//}
			if (count == 15) {
				hooray = true; // Will break out of the loop
			}
		
        
    return col;
}
}

