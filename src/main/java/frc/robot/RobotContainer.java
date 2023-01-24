// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
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
    XboxController xboxController = new XboxController(0);

    AHRS gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        drive.setDefaultCommand(
        new DriveCartesian(
            drive,
            ()-> xboxController.getRawAxis(1), //y speed - forwards & backwards
            ()-> xboxController.getRawAxis(0), //x speed - strafe
            ()-> xboxController.getRawAxis(4)  //z rotation - turning
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
        final JoystickButton a = new JoystickButton(xboxController, 1);
        final JoystickButton b = new JoystickButton(xboxController, 2);
        final JoystickButton x = new JoystickButton(xboxController, 3);
        final JoystickButton y = new JoystickButton(xboxController, 4);
        final JoystickButton lb = new JoystickButton(xboxController, 5);
        final JoystickButton rb = new JoystickButton(xboxController, 6);

        a.onTrue(new ClawOpen(claw));

        b.onTrue(new ClawClose(claw));

        a.onFalse(new ClawStop(claw));
        b.onFalse(new ClawStop(claw));

        y.onTrue(new LiftTower(tower));
        y.onFalse(new StopTower(tower));

        x.onTrue(new DropTower(tower));
        x.onFalse(new StopTower(tower));

        lb.onTrue(new ExtendArm(arm));
        lb.onFalse(new StopArm(arm));

        rb.onTrue(new RetractArm(arm));
        rb.onFalse(new StopArm(arm));

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
