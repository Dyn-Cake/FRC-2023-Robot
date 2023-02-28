package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import java.util.HashMap;

public class SparkMotorManager {
    private static SparkMotorManager instance;
    private final HashMap<Integer, Spark> motors;

    public SparkMotorManager() {
        motors = new HashMap<>();

    }

    public static SparkMotorManager getInstance() {
        if (instance == null) {
            instance = new SparkMotorManager();
        }

        return instance;
    }

    public Spark getMotor(int port) {
        if (motors.containsKey(port))
            return motors.get(port);
        else {
            Spark spark = new Spark(port);
            motors.put(port, spark);
            return spark;
        }
    }


}
