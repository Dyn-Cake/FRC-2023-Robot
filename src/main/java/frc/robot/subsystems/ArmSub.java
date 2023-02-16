package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.shuffleboard.SparkMotorManager;

public class ArmSub extends SubsystemBase {
    private final Spark armMotor;
    private RobotContainer container;

    public ArmSub(RobotContainer container){
        SparkMotorManager sparkMotorManager = SparkMotorManager.getInstance();
        this.container = container;
        armMotor = sparkMotorManager.getMotor(Constants.armMotor);
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
