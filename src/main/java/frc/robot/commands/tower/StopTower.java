package frc.robot.commands.tower;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class StopTower extends CommandBase {
    private final TowerSub tower;

    public StopTower(TowerSub subsystem) {
        tower = subsystem;
        addRequirements(tower);
    }

    @Override
    public void execute() {
        tower.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
