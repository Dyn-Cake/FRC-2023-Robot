package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.shuffleboard.CANSparkMaxMotorManager;

public class ArmSub extends SubsystemBase {
    private final CANSparkMax armMotor;

    public ArmSub() {
        CANSparkMaxMotorManager sparkMaxMotorManager = CANSparkMaxMotorManager.getInstance();
        armMotor = sparkMaxMotorManager.retrieveMotor(Constants.armMotor, CANSparkMaxLowLevel.MotorType.kBrushed);

    }

    public void extend() {
        armMotor.setVoltage(Constants.armMotorVolt);
    }

    public void retract() {
        armMotor.setVoltage(-Constants.armMotorVolt);
    }

    public void stop() {
        armMotor.setVoltage(0);
    }
}
