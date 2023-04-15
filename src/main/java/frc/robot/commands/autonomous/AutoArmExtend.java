package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

public class AutoArmExtend extends CommandBase {
    private final ArmSub arm;
    private final double duration;
    private double startTime;

    public AutoArmExtend(ArmSub subsystem) {
        arm = subsystem;
        addRequirements(arm);
        duration = 100;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        arm.extend();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        arm.stop();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(
                System.currentTimeMillis()
                        - startTime < duration
        );
    }
}
