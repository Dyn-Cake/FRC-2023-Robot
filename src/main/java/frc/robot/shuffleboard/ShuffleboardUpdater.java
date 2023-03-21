package frc.robot.shuffleboard;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
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
    private final SendableChooser<AutonomousPhaseType> chooser = new SendableChooser<>();
    private final ShuffleboardTab tab;
    private final AHRS gyro;
    private long lastTriggered;
    private HashMap<Integer, ShuffleboardElement<CANSparkMax>> motors;
    private GenericEntry gyroPitch;
    private NetworkTable limeLight;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private GenericEntry txEntry;
    private GenericEntry tyEntry;
    private GenericEntry taEntry;


    /**
     * @param motors A hashmap where the key is the port of the motor and the string is the name
     * @param gyro   An instance of an AHRS gyro
     */
    public ShuffleboardUpdater(HashMap<Integer, String> motors, AHRS gyro) {
        tab = Shuffleboard.getTab("Robot");
        //Variables init only
        lastTriggered = System.currentTimeMillis();
        this.gyro = gyro;


        limeLight = NetworkTableInstance.getDefault().getTable("limelight");

        //logic
        init(motors);
    }

    private void init(HashMap<Integer, String> motors) {

        // motors
        CANSparkMaxMotorManager motorManager = CANSparkMaxMotorManager.getInstance();

        HashMap<Integer, ShuffleboardElement<CANSparkMax>> sparkMotors = new HashMap<>();
        for (Integer port : motors.keySet()) {

            GenericEntry extraMotor =
                    tab.add(
                                    motors.get(port) + " voltage",
                                    0
                            )
                            .withWidget(BuiltInWidgets.kVoltageView)
                            .withProperties(Map.of("min", -12, "max", 12))
                            .getEntry();

//            sparkMotors.put(port, new ShuffleboardElement<>(motorManager.getMotor(1), motors.get(port), extraMotor));
        }
        this.motors = sparkMotors;

        //adding widgets to shuffleboard
        gyroPitch = tab.add("gyroPitch", gyro.getYaw())
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();
        tab.add(gyro)
                .withWidget(BuiltInWidgets.kGyro);

        // Selections
        for (AutonomousPhaseType phaseType : AutonomousPhaseType.values()) {
            phaseType.register(chooser);
        }

        tab.add("autonomous", chooser)
                .withWidget(BuiltInWidgets.kSplitButtonChooser);

        // limelight
        //LimelightHelpers.LimelightResults llresults = LimelightHelpers.getLatestResults("");
        // LimelightHelpers.setCropWindow("", -1, 1, -1, 1);
        // LimelightHelpers.setLEDMode_ForceBlink("");
        // LimelightHelpers.getLimelightURLString("limelight", "");

        ta = limeLight.getEntry("ta");
        tx = limeLight.getEntry("tx");
        ty = limeLight.getEntry("ty");
        txEntry = tab.add("Horizontal Offset", tx.getDouble(0.0))
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        tyEntry = tab.add("Vertical Offset", ty.getDouble(0.0))
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        taEntry = tab.add("Target Area", ta.getDouble(0.0))
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();

        limeLight.getEntry("camMode").setInteger(1);

        LimelightHelpers.getLimelightURLString("limeLight", "");
        // tab.addCamera("limelight", "limelightcam", LimelightHelpers.getLimelightNTString("limeLight", ""));
        tab.addCamera("limelight", "limeLight", "http://10.24.41.54:5800");
    }

    public void update() {
        updateMotors();
        updateDebug();
        updateGyro();
        updateLimelight();
    }

    private void updateMotors() {
        for (Integer port : motors.keySet()) {
            ShuffleboardElement<CANSparkMax> motor = motors.get(port);
            motor.getGenericEntry().setDouble(motor.getElement().getBusVoltage());
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

    private void updateLimelight(){
        txEntry.setDouble(tx.getDouble(0.0));
        tyEntry.setDouble(ta.getDouble(0.0));
        taEntry.setDouble(ty.getDouble(0.0));
    }

    public AutonomousPhaseType getChosen() {
        return chooser.getSelected();
    }

}
