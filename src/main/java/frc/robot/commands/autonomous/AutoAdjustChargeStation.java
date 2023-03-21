package frc.robot.commands.autonomous;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrainSub;

public class AutoAdjustChargeStation extends CommandBase {
    private static final double stopThreshold = 3;
    private final DriveTrainSub driveTrainSub;
    private final AHRS gyro;
    private final Robot robot;

    public AutoAdjustChargeStation(DriveTrainSub subsystem, AHRS gyro, Robot robot) {
        driveTrainSub = subsystem;
        addRequirements(driveTrainSub);
        this.gyro = new AHRS();
        // this.gyro = gyro;
        this.robot = robot;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double degrees = gyro.getPitch();
        if (degrees > stopThreshold){
            driveTrainSub.mecanumDrive(.6, 0, 0);
        } else if(degrees < -16){
            driveTrainSub.mecanumDrive(0, 0, 0);
        } else if (degrees < -stopThreshold) {
            driveTrainSub.mecanumDrive(-0.6, 0, 0);
        } else{
            driveTrainSub.mecanumDrive(0, 0, 0);
        }
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        driveTrainSub.mecanumDrive(0, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return !robot.isAutonomous();
    }

}
