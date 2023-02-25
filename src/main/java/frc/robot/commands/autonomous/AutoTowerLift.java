package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class AutoTowerLift extends CommandBase {
    private final TowerSub tower;
    private double startTime;
    private final double duration;

    public AutoTowerLift(TowerSub subsystem) {
        tower = subsystem;
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
        tower.lift();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        tower.stop();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
