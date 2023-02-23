package frc.robot.commands.autonomous;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;

public class AutoAdjustChargeStation extends CommandBase {
    private final DriveTrainSub driveTrainSub;
    private final AHRS gyro;
    private boolean isFinished = false;
    private static final double stopThreshold = 10;

    public AutoAdjustChargeStation(DriveTrainSub subsystem, AHRS gyro){
        driveTrainSub = subsystem;
        this.gyro = gyro;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
    }

    //TODO Please fix, I doubt this will work
    @Override
    public void execute() {
        double degrees = gyro.getRoll();

        if (degrees > stopThreshold) {
            driveTrainSub.mecanumDrive(0, 1, 0);
        } else if (degrees < -stopThreshold) {
            driveTrainSub.mecanumDrive(0, -1, 0);
        } else {
            isFinished = false;
        }
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        driveTrainSub.mecanumDrive(0, 0, 0);
    }

    //condition for the command to end on its own
    //TODO CHANGE THIS
    @Override
    public boolean isFinished() {
        return isFinished;
    }

}
