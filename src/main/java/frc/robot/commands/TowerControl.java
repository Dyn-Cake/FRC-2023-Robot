package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class TowerControl extends CommandBase{
    private final TowerSub tower;
    private DoubleSupplier speed;

    public TowerControl(TowerSub subsystem, DoubleSupplier speed){
        tower = subsystem;
        addRequirements(tower);
        this.speed = speed;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {

    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
       tower.towerControl(speed.getAsDouble());
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
