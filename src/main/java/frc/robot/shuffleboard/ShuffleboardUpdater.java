package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.SPI;

import java.util.HashMap;
import java.util.Map;

import com.kauailabs.navx.frc.AHRS;

public class ShuffleboardUpdater {
    private long lastTriggered;
    private HashMap<Integer, ShuffleboardMotor> motors;
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();
    private final AHRS gyro;
    private ShuffleboardTab tab = Shuffleboard.getTab("SmartDashboard");
    private GenericEntry gyroPitch;
    /*private NetworkTable limelight;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;*/

    public ShuffleboardUpdater(HashMap<Integer, String> motors) {
        //Variables init only
        lastTriggered = System.currentTimeMillis();

        gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

        //limelight = NetworkTableInstance.getDefault().getTable("limelight");

        //logic
        init(motors);
    }

    public void init(HashMap<Integer, String> motors) {


        // motors
        SparkMotorManager motorManager = SparkMotorManager.getInstance();

        HashMap<Integer, ShuffleboardMotor> sparkMotors = new HashMap<>();
        for (Integer port : motors.keySet()) {
            ShuffleboardMotor motor = this.motors.get(port);
            GenericEntry extraMotor =
                    tab.add(
                            motor.getName() + " voltage",
                            motor.getMotor().get()
                    )
                    .withWidget(BuiltInWidgets.kVoltageView)
                    .withProperties(Map.of("min", -12, "max", 12))
                    .getEntry();

            sparkMotors.put(port, new ShuffleboardMotor(motorManager.getMotor(1), motors.get(port), extraMotor));
        }
        this.motors = sparkMotors;

        // gyro
        gyro.reset();

        //adding widgets to shuffleboard
        gyroPitch = tab.add("gyroPitch", gyro.getPitch())
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        tab.add(gyro)
        .withWidget(BuiltInWidgets.kGyro);

        // limelight


        //ledMode = limelight.setEntry(2);

        
    }

    public void update() {
        updateMotors();
        updateDebug();
        updateGyro();
        //updateLimelight();
    }

    private void updateMotors() {

        for(Integer port : motors.keySet()) {
            ShuffleboardMotor motor = motors.get(port);
            motor.getGenericEntry().setDouble(motor.getMotor().get());
        }

    }

    private void updateGyro() {
        gyroPitch.setFloat(gyro.getPitch());
    }

    private void updateDebug() {
        SmartDashboard.putNumber("SystemTime", System.currentTimeMillis());
        SmartDashboard.putNumber("TriggerDelay", lastTriggered - System.currentTimeMillis());
        lastTriggered = System.currentTimeMillis();
    }

    /*private void updateLimelight(){
        ta = limelight.getEntry("ta");
        tx = limelight.getEntry("tx");
        ty = limelight.getEntry("ty");
    }*/

    public AutonomousPhaseType getChosen() {
        return chooser.getSelected();
    }

}
