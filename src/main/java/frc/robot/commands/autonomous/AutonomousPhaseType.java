package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public enum AutonomousPhaseType {

    CHARGE_STATION("Charge Station"),
    LEAVE_COMMUNITY("Leave Community"),
    SCORE("score");

    private final String name;

    AutonomousPhaseType(String name) {
        this.name = name;
    }

    public void register(SendableChooser<AutonomousPhaseType> chooser) {
        chooser.addOption(this.getName(), this);
    }

    public String getName() {
        return name;
    }
}
