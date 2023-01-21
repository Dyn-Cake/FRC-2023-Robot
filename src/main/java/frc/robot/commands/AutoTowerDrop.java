package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class AutoTowerDrop extends CommandBase{
    private final TowerSub tower;
    private double startTime;
    private double duration;

    public AutoTowerDrop(TowerSub subsystem){
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
    tower.drop();
}

//only goes once at end when command is finishing
@Override
public void end(boolean interrupted) {
    tower.stop();
}

//condition for the command to end on its own
@Override
public boolean isFinished() {
    if (System.currentTimeMillis()-startTime<duration){
        return false;
    }
    else {
        return true;
    }
}
}
