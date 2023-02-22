package frc.robot.commands.autonomous;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.StrafeDirection;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class AutoAdjustChargeStation extends CommandBase {
    private final DriveTrainSub driveTrainSub;
    private AHRS gyro;
    private double duration;

    public AutoAdjustChargeStation(DriveTrainSub subsystem, AHRS gyro){
        driveTrainSub = subsystem;
        this.gyro = gyro;
        duration = 1000;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
    }

    //TODO Please fix, I doubt this will work
    @Override
    public void execute() {
        double degrees = gyro.getAngle() % 360;

        if (degrees > 170) {
            driveTrainSub.autoDrive(
                    new CustomaryLength(0.1, CustomaryLengthUnit.INCHES),
                    StrafeDirection.FORWARD
            );
        } else if (degrees < 190) {
            driveTrainSub.autoDrive(
                    new CustomaryLength(0.1, CustomaryLengthUnit.INCHES),
                    StrafeDirection.BACKWARDS
            );
        }
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
    }

    //condition for the command to end on its own
    //TODO CHANGE THIS
    @Override
    public boolean isFinished() {
        return false;
    }

}
