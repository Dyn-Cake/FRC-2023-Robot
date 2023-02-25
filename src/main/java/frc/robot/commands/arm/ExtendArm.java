package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

public class ExtendArm extends CommandBase {
    private final ArmSub arm;

    public ExtendArm(ArmSub subsystem) {
        arm = subsystem;
        addRequirements(arm);
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        arm.extend();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}
