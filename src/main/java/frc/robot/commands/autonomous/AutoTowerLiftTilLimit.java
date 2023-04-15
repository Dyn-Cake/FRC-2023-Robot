package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSub;

public class AutoTowerLiftTilLimit extends CommandBase {
    private final TowerSub tower;

    public AutoTowerLiftTilLimit(TowerSub subsystem) {
        tower = subsystem;
        addRequirements(tower);
    }

    @Override
    public void execute() {
        tower.lift();
    }

    @Override
    public void end(boolean interrupted) {
        tower.stop();
    }

    @Override
    public boolean isFinished() {
        // return tower.getLimitSwitch().get();
        return false;
    }

}
