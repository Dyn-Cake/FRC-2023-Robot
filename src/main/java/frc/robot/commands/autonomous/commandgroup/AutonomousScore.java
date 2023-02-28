package frc.robot.commands.autonomous.commandgroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.StrafeDirection;
import frc.robot.commands.autonomous.AutoArmExtend;
import frc.robot.commands.autonomous.AutoClawOpen;
import frc.robot.commands.autonomous.AutoDrive;
import frc.robot.commands.autonomous.AutoTowerLiftTilLimit;
import frc.robot.subsystems.ArmSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.TowerSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class AutonomousScore extends SequentialCommandGroup {
    public AutonomousScore(TowerSub towerSub, ArmSub armSub, ClawSub clawSub, DriveTrainSub driveTrainSub) {
        addRequirements(towerSub, armSub, clawSub, driveTrainSub);
        addCommands(
                new AutoTowerLiftTilLimit(towerSub),
                new AutoArmExtend(armSub),
                new AutoClawOpen(clawSub),
                new AutoDrive(
                        driveTrainSub,
                        new CustomaryLength(20, CustomaryLengthUnit.FEET),
                        StrafeDirection.BACKWARDS
                )
        );
    }
}
