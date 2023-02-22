package frc.robot.shuffleboard;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.AutonomousPhaseType;
import frc.robot.utils.LimelightHelpers;

import java.util.HashMap;
import java.util.Map;

public class ShuffleboardUpdater {
    private long lastTriggered;
    private HashMap<Integer, ShuffleboardElement<Spark>> motors;
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();
    private final ShuffleboardTab tab;
    private GenericEntry gyroPitch;
    private NetworkTableEntry limelight;

    private final AHRS gyro;
    /*private NetworkTable limelight;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;*/


    /**
     * @param motors A hashmap where the key is the port of the motor and the string is the name
     */
    public ShuffleboardUpdater(HashMap<Integer, String> motors, AHRS gyro) {
        tab = Shuffleboard.getTab("Robot");
        //Variables init only
        lastTriggered = System.currentTimeMillis();
        this.gyro = gyro;



        //limelight = NetworkTableInstance.getDefault().getTable("limelight");

        //logic
        init(motors);
    }

    private void init(HashMap<Integer, String> motors) {

        // motors
        SparkMotorManager motorManager = SparkMotorManager.getInstance();

        HashMap<Integer, ShuffleboardElement<Spark>> sparkMotors = new HashMap<>();
        for (Integer port : motors.keySet()) {

            GenericEntry extraMotor =
                    tab.add(
                                    motors.get(port) + " voltage",
                                    0
                            )
                            .withWidget(BuiltInWidgets.kVoltageView)
                            .withProperties(Map.of("min", -12, "max", 12))
                            .getEntry();

            sparkMotors.put(port, new ShuffleboardElement<>(motorManager.getMotor(1), motors.get(port), extraMotor));
        }
        this.motors = sparkMotors;

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
        LimelightHelpers.LimelightResults llresults = LimelightHelpers.getLatestResults("");
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
            ShuffleboardElement<Spark> motor = motors.get(port);
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
