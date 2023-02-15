package frc.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import frc.robot.utils.LimelightHelpers;
import frc.robot.utils.LimelightHelpers.LimelightResults;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;

import com.kauailabs.navx.frc.AHRS;

public class ShuffleboardUpdater {
    private long lastTriggered;
    private HashMap<Integer, ShuffleboardElement<Spark>> motors;
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();
    private final AHRS gyro;
    private final ShuffleboardTab tab;
    private GenericEntry gyroPitch;
    private NetworkTableEntry limelight;
    /*private NetworkTable limelight;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;*/

    public ShuffleboardUpdater(HashMap<Integer, String> motors) {
        tab = Shuffleboard.getTab("Robot");
        //Variables init only
        lastTriggered = System.currentTimeMillis();



        gyro = new AHRS(SPI.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

        //limelight = NetworkTableInstance.getDefault().getTable("limelight");

        //logic
        init(motors);
    }

    public void init(HashMap<Integer, String> motors) {

        Preferences.setString("key", "value");


        // motors
        SparkMotorManager motorManager = SparkMotorManager.getInstance();

        HashMap<Integer, ShuffleboardMotor> sparkMotors = new HashMap<>();
        /*for (Integer port : motors.keySet()) {
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
        }*/
        this.motors = sparkMotors;

        // gyro
        gyro.reset();

        //adding widgets to shuffleboard
        gyroPitch = tab.add("gyroPitch", gyro.getPitch())
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        tab.add(gyro)
        .withWidget(BuiltInWidgets.kGyro);

        // Selections


        chooser.addOption("Default", AutonomousPhaseType.DEFAULT);
        chooser.addOption("Alt", AutonomousPhaseType.ALTERNATIVE);
        tab.add("autonomous", chooser)
                .withWidget(BuiltInWidgets.kSplitButtonChooser);
        // limelight
        LimelightResults llresults = LimelightHelpers.getLatestResults("");
        LimelightHelpers.setCropWindow("",-1,1,-1,1);
        LimelightHelpers.setLEDMode_ForceBlink("");
        LimelightHelpers.getLimelightURLString("limelight", "");


        //ledMode = limelight.setEntry(2);

        
    }

    public void update() {
        updateMotors();
        updateDebug();
        updateGyro();
        //updateLimelight();
    }

    private void updateMotors() {

        /*for(Integer port : motors.keySet()) {
            ShuffleboardMotor motor = motors.get(port);
            motor.getGenericEntry().setDouble(motor.getMotor().get());
        }*/

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
