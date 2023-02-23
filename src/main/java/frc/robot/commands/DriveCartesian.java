package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;

import java.util.function.DoubleSupplier;

public class DriveCartesian extends CommandBase {
    
    private final DriveTrainSub drive;
    private final double xSpeed;
    private final double ySpeed;
    private final double zRotation;

    public DriveCartesian(
            DriveTrainSub subsystem,
            DoubleSupplier xSpeed,
            DoubleSupplier ySpeed,
            DoubleSupplier zRotation
    ) {
        drive = subsystem;
        addRequirements(drive);
        this.ySpeed = ySpeed.getAsDouble();
        this.xSpeed = xSpeed.getAsDouble();
        this.zRotation = zRotation.getAsDouble();
    }

    public DriveCartesian(
            DriveTrainSub subsystem,
            double xSpeed,
            double ySpeed,
            double zRotation
    ) {
        drive = subsystem;
        addRequirements(drive);
        this.ySpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.zRotation = zRotation;
    }



    // only goes once at beginning when command is called
    @Override
    public void initialize() {

    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
       drive.mecanumDrive(ySpeed, xSpeed, zRotation);
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {

    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {

        return false;

    }

}
