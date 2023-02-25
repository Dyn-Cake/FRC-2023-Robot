package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawOpen extends CommandBase {
    private final ClawSub claw;

    public ClawOpen(ClawSub subsystem) {
        claw = subsystem;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.open();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
