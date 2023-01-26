// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrainSub drive = new DriveTrainSub();
    private final ClawSub claw = new ClawSub();
    private final ArmSub arm = new ArmSub();
    private final TowerSub tower = new TowerSub();
    Joystick flightStick = new Joystick(0);

    AHRS gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        drive.setDefaultCommand(
        new DriveCartesian(
            drive,
            ()-> flightStick.getRawAxis(1), //y speed - forwards & backwards
            ()-> flightStick.getRawAxis(0), //x speed - strafe
            ()-> flightStick.getRawAxis(2)  //z rotation - turning
        ));
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        final JoystickButton trigger = new JoystickButton(flightStick, 1);
        final JoystickButton thumbButt = new JoystickButton(flightStick, 2);
        final JoystickButton butt3 = new JoystickButton(flightStick, 3);
        final JoystickButton butt4 = new JoystickButton(flightStick, 4);
        final JoystickButton butt5 = new JoystickButton(flightStick, 5);
        final JoystickButton butt6 = new JoystickButton(flightStick, 6);

        trigger.onTrue(new ClawOpen(claw));

        thumbButt.onTrue(new ClawClose(claw));

        trigger.onFalse(new ClawStop(claw));
        thumbButt.onFalse(new ClawStop(claw));

        butt3.onTrue(new LiftTower(tower));
        butt3.onFalse(new StopTower(tower));

        butt4.onTrue(new DropTower(tower));
        butt4.onFalse(new StopTower(tower));

        butt5.onTrue(new ExtendArm(arm));
        butt5.onFalse(new StopArm(arm));

        butt6.onTrue(new RetractArm(arm));
        butt6.onFalse(new StopArm(arm));

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

}
