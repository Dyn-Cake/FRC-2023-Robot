// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCartesian;
//import frc.robot.commands.arm.ArmControl;
import frc.robot.commands.arm.ExtendArm;
import frc.robot.commands.arm.RetractArm;
import frc.robot.commands.arm.StopArm;
import frc.robot.commands.autonomous.AutonomousPhase;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import frc.robot.commands.claw.ClawClose;
//import frc.robot.commands.claw.ClawControl;
import frc.robot.commands.claw.ClawOpen;
import frc.robot.commands.claw.ClawStop;
import frc.robot.commands.tower.DropTower;
import frc.robot.commands.tower.LiftTower;
import frc.robot.commands.tower.StopTower;
//import frc.robot.commands.tower.TowerControl;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.TowerSub;


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
    Joystick flightStick = new Joystick(0);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

        // Configure the button bindings
        driveSub.setDefaultCommand(
        new DriveCartesian(
            driveSub,
            ()-> flightStick.getRawAxis(1), //y speed - forwards & backwards
            ()-> flightStick.getRawAxis(0), //x speed - strafe
            ()-> flightStick.getRawAxis(2)  //z rotation - turning
        ));

        /*clawSub.setDefaultCommand(new ClawControl(clawSub, ()->flightStick.getRawAxis(1))); //y axis - forwards & backwards
        armSub.setDefaultCommand(new ArmControl(armSub, ()->flightStick.getRawAxis(0))); //x axis - left & right
        towerSub.setDefaultCommand(new TowerControl(towerSub, ()->flightStick.getRawAxis(2))); //z rotation - rotate/turn
        configureButtonBindings();*/
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    public void configureButtonBindings() {
        final JoystickButton trigger = new JoystickButton(flightStick, 1);
        final JoystickButton thumbButt = new JoystickButton(flightStick, 2);
        final JoystickButton butt3 = new JoystickButton(flightStick, 3);
        final JoystickButton butt4 = new JoystickButton(flightStick, 4);
        final JoystickButton butt5 = new JoystickButton(flightStick, 5);
        final JoystickButton butt6 = new JoystickButton(flightStick, 6);
        //final JoystickButton butt6 = new JoystickButton(flightStick, 6);
        trigger.onTrue(new ClawOpen(clawSub));
        trigger.onFalse(new ClawStop(clawSub));
        thumbButt.onTrue(new ClawClose(clawSub));
        thumbButt.onFalse(new ClawStop(clawSub));
        butt3.onTrue(new LiftTower(towerSub));
        butt3.onFalse(new StopTower(towerSub));
        butt4.onTrue(new DropTower(towerSub));
        butt4.onFalse(new StopTower(towerSub));
        butt5.onTrue(new ExtendArm(armSub));
        butt5.onFalse(new StopArm(armSub));
        butt6.onTrue(new RetractArm(armSub));
        butt6.onFalse(new StopArm(armSub)); //WONT NEED DEPENDING ON DRIVER
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
