package frc.robot.commands.autonomous.commandgroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.StrafeDirection;
import frc.robot.commands.autonomous.AutoArmExtend;
import frc.robot.commands.autonomous.AutoArmRetract;
import frc.robot.commands.autonomous.AutoClawOpen;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutoTowerLift;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.TowerSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class AutonomousLeaveCommunity extends SequentialCommandGroup {
    public AutonomousLeaveCommunity(ClawSub claw, TowerSub tower, ArmSub arm, DriveTrainSub driveTrainSub) {
        addCommands(
            new AutoArmRetract(arm),
            new AutoTowerLift(tower),
            new AutoArmExtend(arm),
            new AutoClawOpen(claw),
            new AutoTowerLift(tower, 500),
            new AutoDrive(
                        driveTrainSub,
                        new CustomaryLength(13, CustomaryLengthUnit.FEET),
                        StrafeDirection.BACKWARDS
            )
        );
 
    }
}