package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class AutoTowerDrop extends CommandBase {
    private final TowerSub tower;
    private final double duration;
    private double startTime;

    /**
     * @param subsystem       The subsystem that the robot uses for tower
     * @param duration        Time to travel for
     */
    public AutoTowerDrop(TowerSub subsystem) {
        tower = subsystem;
        duration = 750;
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
        return !(System.currentTimeMillis() - startTime < duration);
    }
}
