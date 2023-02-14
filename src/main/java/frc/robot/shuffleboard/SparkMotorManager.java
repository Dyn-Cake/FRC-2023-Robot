package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import java.util.HashMap;

public class SparkMotorManager {
    private HashMap<Integer, Spark> motors;
    private static SparkMotorManager instance;
    public SparkMotorManager() {
        motors = new HashMap<>();

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

    public static SparkMotorManager getInstance() {
        if (instance == null) {
            instance = new SparkMotorManager();
        }

        return instance;
    }







}
