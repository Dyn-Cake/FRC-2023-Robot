package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.shuffleboard.CANSparkMaxMotorManager;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmSub extends SubsystemBase {
    private final CANSparkMax armMotor;
    private RobotContainer container;

    public ArmSub(RobotContainer container){
        CANSparkMaxMotorManager sparkMaxMotorManager = CANSparkMaxMotorManager.getInstance();
        this.container = container;
        armMotor = sparkMaxMotorManager.getMotor(Constants.armMotor);
    }

    public void extend(){
        armMotor.setVoltage(Constants.armMotorVolt);
    }

    public void retract(){
        armMotor.setVoltage(-Constants.armMotorVolt);
    }
    public void stop(){
        armMotor.setVoltage(0);
    }
    public void armControl(double speed) {
        armMotor.setVoltage(
            container.deadBand(
                speed,
                Constants.deadband
            ) * Constants.armMotorVolt
        );
    }
}
