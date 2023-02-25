package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.shuffleboard.SparkMotorManager;


public class ClawSub extends SubsystemBase {
    private final Spark clawMotorLeft;
    private final Spark clawMotorRight;

    public ClawSub() {
        SparkMotorManager sparkMotorManager = SparkMotorManager.getInstance();
        clawMotorLeft = sparkMotorManager.getMotor(Constants.clawMotorLeft);
        clawMotorRight = sparkMotorManager.getMotor(Constants.clawMotorRight);
    }

    public void open() {
        clawMotorLeft.setVoltage(Constants.clawMotorLeftVolt);
        clawMotorRight.setVoltage(-Constants.clawMotorRightVolt);
    }

    public void close() {
        clawMotorLeft.setVoltage(-Constants.clawMotorLeftVolt);
        clawMotorRight.setVoltage(Constants.clawMotorRightVolt);
    }

    public void stop() {
        clawMotorLeft.setVoltage(0);
        clawMotorRight.setVoltage(0);
    }
}
