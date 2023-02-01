package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutonomousPhaseType;

import java.io.StringReader;
import java.util.HashMap;

/*public class SmartDashboardUpdater {
    private long lastTriggered;
    private HashMap<Spark, String> motors = new HashMap<Spark,String>();
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();

    public SmartDashboardUpdater(HashMap<Spark, String> motors) {
        //Variables init only
        lastTriggered = System.currentTimeMillis();

        //TODO think of better solution
        HashMap<Spark, String> sparkMotors = new HashMap<>();
        for (Spark port : motors.keySet()) {
            sparkMotors.put(new Spark(port), motors.get(port));
        }
        this.motors = sparkMotors;

        //logic
        init();
    }

    public void init() {

        for (AutonomousPhaseType type : AutonomousPhaseType.values()) {
            //TODO un-hardcode this
            if (type == AutonomousPhaseType.DEFAULT) {
                chooser.addOption("default", type);
                continue;
            }
            chooser.addOption(type.name(), type);
        }
    }

    public void update() {
        updateMotors();
        updateDebug();
        // updateGyro() not here due to gyro currently being disabled
    }

    private void updateMotors() {
        for (Spark motor : motors.keySet()) {
            SmartDashboard.putNumber(
                    motors.get(motor) + " motor voltage",
                    motor.get()
            );
        }
    }

    private void updateDebug() {
        SmartDashboard.putNumber("SystemTime", System.currentTimeMillis());
        SmartDashboard.putNumber("TriggerDelay", lastTriggered - System.currentTimeMillis());
        lastTriggered = System.currentTimeMillis();
    }

    public AutonomousPhaseType getChosen() {

        return chooser.getSelected();
    }

}
 */