package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public enum AutonomousPhaseType {
    DEFAULT("Leave Community"),
    ALTERNATIVE("Alternitive");


    public String name;

    AutonomousPhaseType(String name) {
        this.name = name;
    }

    public void add(SendableChooser<AutonomousPhaseType> chooser) {
        chooser.addOption(this.name(), this);
    }
}
