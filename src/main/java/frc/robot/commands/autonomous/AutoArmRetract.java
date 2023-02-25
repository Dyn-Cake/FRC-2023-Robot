package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

public class AutoArmRetract extends CommandBase {
    private final ArmSub arm;
    private double startTime;
    private final double duration;

    public AutoArmRetract(ArmSub subsystem) {
        arm = subsystem;
        duration = 1000;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        arm.retract();
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