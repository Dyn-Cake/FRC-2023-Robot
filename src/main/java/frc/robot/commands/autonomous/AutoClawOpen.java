package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class AutoClawOpen extends CommandBase {
    private final ClawSub claw;
    private final double duration;
    private double startTime;

    public AutoClawOpen(ClawSub subsystem) {
        claw = subsystem;
        addRequirements(claw);
        duration = 550;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        claw.open();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        claw.stop();
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
