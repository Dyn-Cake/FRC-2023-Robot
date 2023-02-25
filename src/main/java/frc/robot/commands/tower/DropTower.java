package frc.robot.commands.tower;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class DropTower extends CommandBase {
    private final TowerSub tower;

    public DropTower(TowerSub subsystem) {
        tower = subsystem;
        addRequirements(tower);
    }

    @Override
    public void execute() {
        tower.drop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
