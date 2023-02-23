package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.StrafeDirection;
import frc.robot.subsystems.DriveTrainSub;

public class AutoDrive extends CommandBase {
    private final DriveTrainSub drive;
    private final double distance;
    private final StrafeDirection strafeDirection;
    //private double startTime;
    //private double duration;

    public AutoDrive(DriveTrainSub subsystem, double distance, StrafeDirection strafeDirection){
        drive = subsystem;
        this.distance = distance;
        this.strafeDirection = strafeDirection;
        //this.duration = duration*1000;

    }
    
    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        drive.resetEncoder();
        //startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        drive.autoDrive(distance, strafeDirection);

    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        drive.mecanumDrive(0, 0, 0);
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !( drive.getDistance() < distance );

        /*
        if (System.currentTimeMillis()-startTime<duration){
            return false;
        }
        else {
            return true;
        }
        */
    }
}
