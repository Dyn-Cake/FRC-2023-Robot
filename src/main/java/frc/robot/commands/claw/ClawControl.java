package frc.robot.commands.claw;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawControl extends CommandBase{
    private final ClawSub claw;
    private DoubleSupplier speed;

    public ClawControl(ClawSub subsystem, DoubleSupplier speed){
        claw = subsystem;
        addRequirements(claw);
        this.speed = speed;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {

    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
       claw.clawControl(speed.getAsDouble());
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {

    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {

        return false;

    }
}
