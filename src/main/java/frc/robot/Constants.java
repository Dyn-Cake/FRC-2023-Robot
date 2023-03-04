// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


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
    public static final int clawMotor = 4;
    public static final int towerMotor = 8;
    public static final int armMotor = 5;

    //the number is the CAN ID that the spark max is attached to :)
    //public static int towerMotor = 6;
    public static int backLeftDrive = 6;
    public static int frontLeftDrive = 7;
    public static int frontRightDrive = 3;
    public static int backRightDrive = 2;

    //list of dio ports for sensors
    public static final int armLimitSwitch = 1;
    public static final int frontRightEncoder1 = 2;
    public static final int frontRightEncoder2 = 3;
    public static final int frontLeftEncoder1 = 6;
    public static final int frontLeftEncoder2 = 7;
    public static final int backRightEncoder1 = 4;
    public static final int backRightEncoder2 = 5;
    public static final int backLeftEncoder1 = 8;
    public static final int backLeftEncoder2 = 9;

    //setting the voltage (power) for the motors
    //out of 12
    //change this later when figuring out the voltage for the robot
    //claw volt
    public static double clawMotorVolt = 7;
    //drive volt
    public static double autoDrive = 2;
    //tower volt
    public static double towerMotorVolt = 2;
    //arm volt
    public static double armMotorVolt = 5;

    //list of motors for SmartDashboard
    public static HashMap<Integer, String> extraMotors = new HashMap<>() {{
        put(clawMotor, "claw motor");
        put(towerMotor, "tower motor");
        put(armMotor, "arm motor");
    }};



}

