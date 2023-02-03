// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCartesian;
import frc.robot.commands.autonomous.AutonomousPhase;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import frc.robot.commands.arm.ArmControl;
import frc.robot.commands.claw.ClawControl;
import frc.robot.commands.tower.TowerControl;
import frc.robot.subsystems.*;


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
    Joystick flightStickDrive = new Joystick(0);
    Joystick flightStickControl = new Joystick(1);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        driveSub.setDefaultCommand(
        new DriveCartesian(
            driveSub,
            ()-> flightStickDrive.getRawAxis(1), //y speed - forwards & backwards
            ()-> flightStickDrive.getRawAxis(0), //x speed - strafe
            ()-> flightStickDrive.getRawAxis(2)  //z rotation - turning
        ));

        clawSub.setDefaultCommand(new ClawControl(clawSub, ()->flightStickControl.getRawAxis(1))); //y axis - forwards & backwards
        armSub.setDefaultCommand(new ArmControl(armSub, ()->flightStickControl.getRawAxis(0))); //x axis - left & right
        towerSub.setDefaultCommand(new TowerControl(towerSub, ()->flightStickControl.getRawAxis(2))); //z rotation - rotate/turn
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
    public Command getAutonomousCommand(AutonomousPhaseType chosen) {
        return new AutonomousPhase(driveSub, chosen);
    }

    public double deadBand(double input, double deadband) {
        if (input > deadband || input < -deadband) {
            return input;
        } else {
            return 0;
        }
    }

}
