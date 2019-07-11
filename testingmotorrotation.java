import ShefRobot.*;

public class testingmotorrotation {
    private static Robot robot;
    private static Motor armMotor;	
    public static void main(String[] args) {
    	robot = new Robot("dia-lego-f7");
    	armMotor = robot.getLargeMotor(Motor.Port.A);
    	armMotor.setSpeed(10);
        for (int i=0;i<5;i++) {
            armMotor.forward();
        }
        for (int i=0;i<5;i++) {
            armMotor.backward();
        }
    	robot.close();
    }
}