package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.shuffleboard.CANSparkMaxMotorManager;


public class ClawSub extends SubsystemBase {
    private final CANSparkMax clawMotor;

    public ClawSub() {
        CANSparkMaxMotorManager sparkMaxMotorManager = CANSparkMaxMotorManager.getInstance();
        clawMotor = sparkMaxMotorManager.getMotor(Constants.clawMotorRight, MotorType.kBrushed);
    }

    public void open() {
        clawMotor.setVoltage(-Constants.clawMotorRightVolt);
    }

    public void close() {
        clawMotor.setVoltage(Constants.clawMotorRightVolt);
    }

    public void stop() {
        clawMotor.setVoltage(0);
    }
}
