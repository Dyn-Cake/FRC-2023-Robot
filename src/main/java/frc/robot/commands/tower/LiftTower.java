package frc.robot.commands.tower;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class LiftTower extends CommandBase {
    private final TowerSub tower;

    public LiftTower(TowerSub subsystem) {
        tower = subsystem;
        addRequirements(tower);
    }

    @Override
    public void execute() {
        tower.lift();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
