package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawClose extends CommandBase {
    private final ClawSub claw;

    public ClawClose(ClawSub subsystem) {
        claw = subsystem;
        addRequirements(claw);
    }

    @Override
    public void execute(){
        claw.close();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
