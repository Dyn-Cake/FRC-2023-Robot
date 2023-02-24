package frc.robot.commands.autonomous.commandgroup;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.StrafeDirection;
import frc.robot.commands.autonomous.AutoAdjustChargeStation;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class AutonomousChargeStation extends SequentialCommandGroup {
    public AutonomousChargeStation(DriveTrainSub driveTrainSub, AHRS gyro, Robot robot) {
        addCommands(
                new AutoDrive(
                        driveTrainSub,
                        new CustomaryLength(13.7, CustomaryLengthUnit.FEET),
                        StrafeDirection.FORWARD
                ),
                new AutoDrive(
                    driveTrainSub, 
                    new CustomaryLength(2.5, CustomaryLengthUnit.FEET), 
                    StrafeDirection.BACKWARDS
                ),
                new AutoAdjustChargeStation(driveTrainSub, gyro, robot)

        );

    }


}
