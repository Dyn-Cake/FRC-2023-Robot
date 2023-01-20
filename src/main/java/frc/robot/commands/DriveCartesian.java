package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;

import java.util.function.DoubleSupplier;

public class DriveCartesian extends CommandBase {
    
    private final DriveTrainSub drive;
    private final DoubleSupplier xSpeed;
    private final DoubleSupplier ySpeed;
    private final DoubleSupplier zRotation;

    public DriveCartesian(DriveTrainSub subsystem, DoubleSupplier ySpeed, DoubleSupplier xSpeed, DoubleSupplier zRotation){
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
   drive.mecanumDrive(
       ySpeed.getAsDouble(), 
       xSpeed.getAsDouble(), 
       zRotation.getAsDouble()
    );
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
