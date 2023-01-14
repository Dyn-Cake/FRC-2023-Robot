package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrainSub;

public class AutonomousPhase extends SequentialCommandGroup{
    public AutonomousPhase(DriveTrainSub driveTrainSub){
        addCommands(
            //number at the end is the distance the command runs for (in ???)
            //dont delete for comment out commands, just set the time duration to 0
            //autoDrive(subsystem, distance)
            new AutoDrive(driveTrainSub, 2.6, "none"),
            new WaitCommand(1)
        );
    }
}
