// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.TowerSub;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrainSub driveSub = new DriveTrainSub();
    private final ClawSub clawSub = new ClawSub(this);
    private final ArmSub armSub = new ArmSub(this);
    private final TowerSub towerSub = new TowerSub(this);
    //Joystick flightStickDrive = new Joystick(1);
    Joystick flightStickControl = new Joystick(0);

    EventLoop loop = new EventLoop();
    AHRS gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        /*drive.setDefaultCommand(
        new DriveCartesian(
            drive,
            ()-> flightStickDrive.getRawAxis(1), //y speed - forwards & backwards
            ()-> flightStickDrive.getRawAxis(0), //x speed - strafe
            ()-> flightStickDrive.getRawAxis(2)  //z rotation - turning
        ));*/


        /*claw.setDefaultCommand(new ClawControl(claw, ()->flightStickControl.getRawAxis(1))); //y axis - forwards & backwards
        arm.setDefaultCommand(new ArmControl(arm, ()->flightStickControl.getRawAxis(0))); //x axis - left & right
        tower.setDefaultCommand(new TowerControl(tower, ()->flightStickControl.getRawAxis(2))); //z rotation - rotate/turn*/
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {





        /*final JoystickButton trigger = new JoystickButton(flightStickControl, 1);
        final JoystickButton thumbButt = new JoystickButton(flightStickControl, 2);
        final JoystickButton butt3 = new JoystickButton(flightStickControl, 3);
        final JoystickButton butt4 = new JoystickButton(flightStickControl, 4);
        final JoystickButton butt5 = new JoystickButton(flightStickControl, 5);
        final JoystickButton butt6 = new JoystickButton(flightStickControl, 6);
        //final JoystickButton butt6 = new JoystickButton(flightStick, 6);


        trigger.onTrue(new ClawOpen(claw));
        trigger.onFalse(new ClawStop(claw));

        thumbButt.onTrue(new ClawClose(claw));
        thumbButt.onFalse(new ClawStop(claw));

        butt3.onTrue(new LiftTower(tower));
        butt3.onFalse(new StopTower(tower));

        butt4.onTrue(new DropTower(tower));
        butt4.onFalse(new StopTower(tower));

        butt5.onTrue(new ExtendArm(arm));
        butt5.onFalse(new StopArm(arm));

        butt6.onTrue(new RetractArm(arm));
        butt6.onFalse(new StopArm(arm));*/ //WONT NEED DEPENDING ON DRIVER

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }

    public AHRS getGyro() {
        return gyro;
    }

    public double deadBand(double input, double deadband) {
        if (input > deadband || input < -deadband) {
            return input;
        } else {
            return 0;
        }
    }

}
