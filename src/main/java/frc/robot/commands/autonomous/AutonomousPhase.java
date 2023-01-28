package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.StrafeDirection;
import frc.robot.subsystems.DriveTrainSub;

public class AutonomousPhase extends SequentialCommandGroup {
    public AutonomousPhase(DriveTrainSub driveTrainSub) {
        addCommands(
            //number at the end is the distance the command runs for (in feet)
            //dont delete for comment out commands, just set the time duration to 0
            //autoDrive(subsystem, distance(in feet))
            new AutoDrive(driveTrainSub, 2.6, StrafeDirection.LEFT),
            new WaitCommand(0),
            new AutoDrive(driveTrainSub, 4.5, StrafeDirection.FORWARD),
            new WaitCommand(1)
        );
    }
}
