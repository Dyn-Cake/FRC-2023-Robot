package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

public class StopArm extends CommandBase {
    private final ArmSub arm;

    public StopArm(ArmSub subsystem) {
        arm = subsystem;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
