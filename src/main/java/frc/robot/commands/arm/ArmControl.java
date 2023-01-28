package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSub;

import java.util.function.DoubleSupplier;

public class ArmControl extends CommandBase{
    private final ArmSub arm;
    private DoubleSupplier speed;

    public ArmControl(ArmSub subsystem, DoubleSupplier speed){
        arm = subsystem;
        addRequirements(arm);
        this.speed = speed;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {

    }

    // keeps repeating until the command ends
    @Override
    public void execute() {

        arm.armControl(speed.getAsDouble());
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
