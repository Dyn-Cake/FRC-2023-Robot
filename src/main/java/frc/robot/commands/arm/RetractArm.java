package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

public class RetractArm extends CommandBase {
    private final ArmSub arm;

    public RetractArm(ArmSub subsystem) {
        arm = subsystem;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.retract();
    }

    @Override
    public boolean isFinished() {

        return false;
    }
}
