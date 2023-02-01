// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.HashMap;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //the number is the pwm channel that the spark is attached to :)

    public static int backLeftDrive = 9;
    public static int frontLeftDrive = 6;
    public static int frontRightDrive = 2; //7
    public static int backRightDrive = 4; //8
    public static int clawMotorLeft = 7;
    public static int clawMotorRight = 8;
    public static int towerMotor = 1;
    public static int armMotor = 13;

    //the number is the CAN ID that the spark max is attached to :)
    //public static int towerExtension = 7;
    //list of dio ports for sensors

    public static int frontRightEncoder1 = 0;
    public static int frontRightEncoder2 = 1;
    public static int frontLeftEncoder1 = 8;
    public static int frontLeftEncoder2 = 9;
    public static int backRightEncoder1 = 2;
    public static int backRightEncoder2 = 3;
    public static int backLeftEncoder1 = 6;
    public static int backLeftEncoder2 = 7;
    //setting the voltage (power) for the motors
    //out of 12
    //change this later when figuring out the voltage for the robot
    //claw volt

    public static double clawMotorLeftVolt = 7;
    public static double clawMotorRightVolt = 7;
    //drive volt
    public static double autoDrive = .3;
    //tower volt
    public static double towerMotorVolt = 2;
    //arm volt
    public static double armMotorVolt = 4;
    //autodrive speed is out of 1

    public static double autoDriveLeft = .3;
    public static double autoDriveRight = .3;
    //deadband -1.0 to 1.0

    public static double deadband = 0.2;
    public static HashMap<Integer, String> motors = new HashMap<>() {{
        put(9, "back left drive");
        put(6, "front left drive");
        put(2, "front right drive");
        put(4, "back right drive");
        put(7, "claw motor left");
        put(8, "claw motor right");
        put(1, "claw motor right");
        put(13, "arm motor");
    }};

    static {

        //TODO look for pointers or references in java if possible, try create a hashmap<name, pointer>
        Preferences.setDouble("deadband", deadband);

    }

    public static void update() {
        deadband = Preferences.getDouble("deadband", deadband);
    }

}
