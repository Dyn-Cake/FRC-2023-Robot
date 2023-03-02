package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.shuffleboard.CANSparkMaxMotorManager;

public class TowerSub extends SubsystemBase {
    private final CANSparkMax towerMotor;
    private final DigitalInput limitSwitch;

    public TowerSub() {
        // towerMotor = new CANSparkMax(Constants.towerMotor, MotorType.kBrushless);
        // towerEncoder = towerMotor.getEncoder(Type.kHallSensor, 42);
        // towerEncoder.setPosition(0);
        CANSparkMaxMotorManager sparkMaxMotorManager = CANSparkMaxMotorManager.getInstance();
        towerMotor = sparkMaxMotorManager.retrieveMotor(Constants.clawMotor, MotorType.kBrushless);
        towerMotor.setIdleMode(IdleMode.kBrake);

        limitSwitch = new DigitalInput(Constants.armLimitSwitch);
    }


    public void lift() {
        if (limitSwitch.get())
            towerMotor.setVoltage(-Constants.towerMotorVolt);
        else
            towerMotor.setVoltage(0);
    }

    public void drop() {
        towerMotor.setVoltage(Constants.towerMotorVolt);
    }

    public void stop() {
        towerMotor.setVoltage(0);
    }

    public DigitalInput getLimitSwitch() {
        return limitSwitch;
    }
}
