package frc.robot.shuffleboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.HashMap;

public class CANSparkMaxMotorManager {
    private HashMap<Integer, CANSparkMax> motors;
    private static CANSparkMaxMotorManager instance;
    public CANSparkMaxMotorManager() {
        motors = new HashMap<>();

    }
    public CANSparkMax getMotor(int port) {
        if (motors.containsKey(port))
            return motors.get(port);
        else {
            CANSparkMax sparkMax = new CANSparkMax(port, MotorType.kBrushless); // Every motor is brushless
            motors.put(port, sparkMax);
            return sparkMax;
        }
    }

    public static CANSparkMaxMotorManager getInstance() {
        if (instance == null) {
            instance = new CANSparkMaxMotorManager();
        }

        return instance;
    }
}

