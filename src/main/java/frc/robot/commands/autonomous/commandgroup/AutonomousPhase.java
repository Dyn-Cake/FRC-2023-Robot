package frc.robot.commands.autonomous.commandgroup;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.StrafeDirection;
import frc.robot.commands.autonomous.AutoAdjustChargeStation;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class AutonomousPhase extends SequentialCommandGroup {
    public AutonomousPhase(DriveTrainSub driveTrainSub) {
        addRequirements(driveTrainSub);
        System.out.println("DEFAULT");

        addCommands(
                new AutoDrive(
                        driveTrainSub,
                        new CustomaryLength(2.6, CustomaryLengthUnit.FEET),
                        StrafeDirection.LEFT
                ),
                new WaitCommand(0),
                new AutoDrive(
                        driveTrainSub,
                        new CustomaryLength(4.5, CustomaryLengthUnit.FEET),
                        StrafeDirection.FORWARD
                ),
                new WaitCommand(1)
        );
    }
}
