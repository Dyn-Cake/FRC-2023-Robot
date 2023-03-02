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

    /**
     * A method to retrieve a lazy loaded motor.
     *
     * @param port The motor's port
     * @param type The motor's motor type
     * @return The motor from the specified from the port and type
     * @throws IllegalArgumentException Gets thrown if a motor has already been initialized as a different type than provided
     */
    public CANSparkMax retrieveMotor(int port, MotorType type) throws IllegalArgumentException {
        CANSparkMax sparkMax;// NOT every motor is brushless
        if (motors.containsKey(port)) {
            sparkMax = motors.get(port);
            MotorType foundType = sparkMax.getMotorType();
            if (foundType != type) {
                throw new IllegalArgumentException(
                        "Cannot get a " + type + " motor when the motor in the port is a "
                                + foundType + " motor at port " + port
                );
            }
        } else {
            sparkMax = new CANSparkMax(port, type);
            motors.put(port, sparkMax);
        }
        return sparkMax;
    }

    /**
     * Gets the motor by the port
     * @param port The motor's port
     * @return returns the motor in the port, might be null if it isn't initialized
     */
    public CANSparkMax getMotor(int port) {
        return motors.get(port);
    }

    /**
     * Load the motor to the list
     * @param port The motor's port
     * @param type The motor's motor type
     * @throws IllegalArgumentException If the motor is already initialized
     */
    public void addMotor(int port, MotorType type) throws IllegalArgumentException {
        if (motors.containsKey(port)) {
            throw new IllegalArgumentException(type + " motor in port " + port + " already exists");
        }
        motors.put(port, new CANSparkMax(port, type));
    }


}

