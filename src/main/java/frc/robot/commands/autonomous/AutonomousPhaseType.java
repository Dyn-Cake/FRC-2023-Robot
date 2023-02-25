package frc.robot.commands.autonomous;

public enum AutonomousPhaseType {
    DEFAULT("default"),
    ALTERNATIVE("alt"),

    CHARGE_STATION("Charge Station"),
    LEAVE_COMMUNITY("Leave Community"),
    SCORE("score");

    AutonomousPhaseType(String name) {
    }

}
