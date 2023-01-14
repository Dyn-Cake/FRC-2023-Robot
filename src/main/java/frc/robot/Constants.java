// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static int frontRightDrive = 7;
    public static int backRightDrive = 8;
    
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
    public static double autoDrive = .3;

    //autodrive speed is out of 1
    public static double autodriveleft = .3;
    public static double autodriveright = .3;
    
}
