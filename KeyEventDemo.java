import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import com.sun.org.apache.xpath.internal.operations.And;
import ShefRobot.*;
import java.util.Scanner;
 
public class KeyEventDemo extends JFrame
        implements KeyListener,
        ActionListener
{
    private static boolean robotOn;
    private static int speed;
    private static Robot robot;
	private static Motor propellerMotor;
    private static Motor leftMotor;
    private static Motor rightMotor;
    private static Speaker speaker;
    private static ColorSensor sensor;
    private static ColorSensor.Color col;			
    private static GyroSensor gyro;
    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });



        //######CODE HERE######//


        // First, we need to get the Robot object
        robot = new Robot("dia-lego-f8");
        robotOn = true;
    	// Then we get our left and right Motor objects and our Speaker object
    	leftMotor = robot.getLargeMotor(Motor.Port.A);
    	rightMotor = robot.getLargeMotor(Motor.Port.B);
		propellerMotor = robot.getLargeMotor(Motor.Port.C);
    	speaker = robot.getSpeaker();
    	
    	// You can try different speeds for the motors,
    	// but if you run them too fast the code that counts
        // the steps won't keep up and you will get less accurate movements.
        speed = 200;
    	leftMotor.setSpeed(speed);
    	rightMotor.setSpeed(speed);
    	propellerMotor.setSpeed(1000);
		propellerMotor.forward();
    	
    	// **** Make your changes to these lines below **** //

    	// Get a colour sensor (note: we are using the US spelling of color in the code...)
    	sensor = robot.getColorSensor(Sensor.Port.S1);

        while (robotOn){

        }

        robot.close();
        //#####################//
    }
    //######FUNCTIONS HERE#####//

    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY TYPED: ");
    }   
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");

        if (e.getKeyCode() == 38) { //If up key pressed
            //Go forward
            leftMotor.forward();
            rightMotor.forward();
        }
        else if (e.getKeyCode() == 40) { //If down key pressed
            //Go backward
            leftMotor.backward();
            rightMotor.backward();

        }
        else if (e.getKeyCode() == 37) { //If left key pressed
            //turn left
            leftMotor.setSpeed(speed/2);
            rightMotor.setSpeed(speed/2);
            leftMotor.backward();
            rightMotor.forward();
        }
        else if (e.getKeyCode() == 39) { //If right key pressed
            //turn right
            leftMotor.setSpeed(speed/2);
            rightMotor.setSpeed(speed/2);
            leftMotor.forward();
            rightMotor.backward();
        }
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");

        if (e.getKeyCode() == 38) { //If up key pressed
            //Go forward
            leftMotor.stop();
            rightMotor.stop();
        }
        if (e.getKeyCode() == 40) { //If down key pressed
            //Go backward
            leftMotor.stop();
            rightMotor.stop();

        }
        if (e.getKeyCode() == 37) { //If left key pressed
            //turn left
            leftMotor.stop();
            rightMotor.stop();
            leftMotor.setSpeed(speed);
            rightMotor.setSpeed(speed);
        }
        if (e.getKeyCode() == 39) { //If right key pressed
            //turn right
            leftMotor.stop();
            rightMotor.stop();
            leftMotor.setSpeed(speed);
            rightMotor.setSpeed(speed);
        }
        if (e.getKeyCode() == 16) { //If shift pressed
            //turn right
            speed = incrementSpeed(speed);
            leftMotor.setSpeed(speed);
            rightMotor.setSpeed(speed);
        }
        if (e.getKeyCode() == 17) { //If control pressed
            //turn right
            speed = decrementSpeed(speed);
            leftMotor.setSpeed(speed);
            rightMotor.setSpeed(speed);
        }
        if (e.getKeyCode() == 65) { //If 'a' pressed
            //go to scout mode
            System.out.println(e.getKeyCode());
        }
        System.out.println(speed);
        //System.out.println(e.getKeyCode());
    }
     
    public static int incrementSpeed(int speed) {
        int result = speed + 50;
        if (result > 1000) {
            result = 1000;
        }
        return result;
    }
    public static int decrementSpeed(int speed) {
        int result = speed - 50;
        if (result < 0) {
            result = 0; 
        }
        return result;
    }
    

    //#########################//







    //IGNORE BELOW
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        KeyEventDemo frame = new KeyEventDemo("KeyEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Set up the content pane.
        frame.addComponentsToPane();
         
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    private void addComponentsToPane() {
         
        JButton button = new JButton("Clear");
        button.addActionListener(this);
         
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
         
        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        //typingArea.setFocusTraversalKeysEnabled(false);
         
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));
         
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.PAGE_END);
    }
     
    public KeyEventDemo(String name) {
        super(name);
    }
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        //Clear the text components.
        displayArea.setText("");
        typingArea.setText("");
         
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }
    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private void displayInfo(KeyEvent e, String keyStatus) {
         
        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }
         
        int modifiersEx = e.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }
         
        String actionString = "action key? ";
        if (e.isActionKey()) {
            actionString += "YES";
        } else {
            actionString += "NO";
        }
         
        String locationString = "key location: ";
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            locationString += "standard";
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
            locationString += "left";
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
            locationString += "right";
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
            locationString += "numpad";
        } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
            locationString += "unknown";
        }
         
        displayArea.append(keyStatus + newline
                + "    " + keyString + newline
                + "    " + modString + newline
                + "    " + actionString + newline
                + "    " + locationString + newline);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }
}