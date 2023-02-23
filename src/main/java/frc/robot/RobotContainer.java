// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCartesian;
//import frc.robot.commands.arm.ArmControl;
import frc.robot.commands.arm.ExtendArm;
import frc.robot.commands.arm.RetractArm;
import frc.robot.commands.arm.StopArm;
import frc.robot.commands.autonomous.AutoAdjustChargeStation;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.commandgroup.AutonomousPhase;
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
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;


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
    private final AHRS gyro;
    Joystick flightStick = new Joystick(0);


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer(AHRS gyro) {
        this.gyro = gyro;

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
        configureButtonBindings();*/ //WONT NEED DEPENDING ON DRIVER
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    public void configureButtonBindings() {
        final JoystickButton butt7 = new JoystickButton(flightStick, 7);
        final JoystickButton butt8 = new JoystickButton(flightStick, 8);
        final JoystickButton butt9 = new JoystickButton(flightStick, 9);
        final JoystickButton butt10 = new JoystickButton(flightStick, 10);
        final JoystickButton butt11 = new JoystickButton(flightStick, 11);
        final JoystickButton butt12 = new JoystickButton(flightStick, 12);
        butt8.onTrue(new ClawOpen(clawSub));
        butt8.onFalse(new ClawStop(clawSub));
        butt7.onTrue(new ClawClose(clawSub));
        butt7.onFalse(new ClawStop(clawSub));
        butt10.onTrue(new LiftTower(towerSub));
        butt10.onFalse(new StopTower(towerSub));
        butt9.onTrue(new DropTower(towerSub));
        butt9.onFalse(new StopTower(towerSub));
        butt12.onTrue(new ExtendArm(armSub));
        butt12.onFalse(new StopArm(armSub));
        butt11.onTrue(new RetractArm(armSub));
        butt11.onFalse(new StopArm(armSub));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand(AutonomousPhaseType chosen) {
        switch (chosen) {
            case DEFAULT: {
                return new AutonomousPhase(driveSub);
            }
            case CHARGE_STATION: {
                return new AutoAdjustChargeStation(driveSub, gyro);
            }
            default: {
                System.out.println("Bruh, none");
                return null;
            }
        }

    }

    // Should be static :/
    public double deadBand(double input, double deadband) {
        if (input > deadband || input < -deadband) {
            return input;
        } else {
            return 0;
        }
    }

}
