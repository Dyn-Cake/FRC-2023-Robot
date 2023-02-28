package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawStop extends CommandBase {
    private final ClawSub claw;

    public ClawStop(ClawSub subsystem) {
        claw = subsystem;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        claw.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
