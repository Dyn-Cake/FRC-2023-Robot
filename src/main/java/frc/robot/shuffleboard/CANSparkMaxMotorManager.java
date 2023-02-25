package frc.robot.shuffleboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.HashMap;

public class CANSparkMaxMotorManager {
    private static CANSparkMaxMotorManager instance;
    private final HashMap<Integer, CANSparkMax> motors;

    public CANSparkMaxMotorManager() {
        motors = new HashMap<>();
    }

    public static CANSparkMaxMotorManager getInstance() {
        if (instance == null) {
            instance = new CANSparkMaxMotorManager();
        }

        return instance;
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
}

