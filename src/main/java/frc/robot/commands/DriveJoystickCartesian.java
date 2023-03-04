package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;

public class DriveJoystickCartesian extends CommandBase {

    private final DriveTrainSub drive;
    private final Joystick joystick;

    public DriveJoystickCartesian(
            DriveTrainSub subsystem,
            Joystick joystick
    ) {
        this.joystick = joystick;
        drive = subsystem;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.mecanumDrive(
            joystick.getRawAxis(0),
            joystick.getRawAxis(1),
            joystick.getRawAxis(2)
        );
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
