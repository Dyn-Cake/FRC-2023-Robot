package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import edu.wpi.first.wpilibj.SPI;

import java.util.HashMap;
import java.util.Map;

import com.kauailabs.navx.frc.AHRS;

public class SmartDashboardUpdater {
    private long lastTriggered;
    private HashMap<Spark, String> motors;
    private HashMap<Spark, String> driveMotors;
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();
    AHRS gyro;

    public SmartDashboardUpdater(HashMap<Integer, String> motors, HashMap<Integer, String> driveMotors) {
        //Variables init only
        lastTriggered = System.currentTimeMillis();

        SparkMotorManager sparkMotorManager = SparkMotorManager.getInstance();
        SparkMotorManager driveSparkMotorManager = SparkMotorManager.getInstance();

        HashMap<Spark, String> sparkMotors = new HashMap<>();
        for (Integer port : motors.keySet()) {
            sparkMotors.put(sparkMotorManager.getMotor(port), motors.get(port));
        }
        this.motors = sparkMotors;

        HashMap<Spark, String> driveSparkMotors = new HashMap<>();
        for (Integer port : driveMotors.keySet()) {
            driveSparkMotors.put(driveSparkMotorManager.getMotor(port), motors.get(port));
        }
        this.driveMotors = driveSparkMotors;

        gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

        //logic
        init();
    }

    public void init() {

        /*Shuffleboard.getTab("SmartDashboard")
        .add("front left voltage", 1)
        .withWidget(BuiltInWidgets.kMecanumDrive)
        .getEntry();*/
        
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
        updateGyro();
    }

    private void updateMotors() {
        for (Spark motor : motors.keySet()) {
            Shuffleboard.getTab("SmartDashboard")
            .add(motors.get(motor) + " voltage", motor.get())
            .withWidget(BuiltInWidgets.kVoltageView)
            .withProperties(Map.of("min", -1, "max", 1));
            /*SmartDashboard.putNumber(
                    motors.get(motor) + " voltage",
                    motor.get()
            );*/
        }
        
        for (Spark motor : driveMotors.keySet()) {
            Shuffleboard.getTab("SmartDashboard")
            .add(motors.get(motor) + " voltage", motor.get())
            .withWidget(BuiltInWidgets.kMecanumDrive)
            .withProperties(Map.of("min", -1, "max", 1));
            /*SmartDashboard.putNumber(
                    motors.get(motor) + " voltage",
                    motor.get()
            );*/
        }
    }

    private void updateGyro(){
        Shuffleboard.getTab("SmartDashboard")
        .add("gyroPitch", gyro.getPitch());
        Shuffleboard.getTab("SmartDashboard")
        .add(gyro)
        .withWidget(BuiltInWidgets.kGyro);
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
