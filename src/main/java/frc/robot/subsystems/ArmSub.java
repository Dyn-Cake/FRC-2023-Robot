package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSub extends SubsystemBase {
    private final Spark armMotor;

    public ArmSub(){
        armMotor = new Spark(Constants.armMotor);

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
    public void armControl(double speed){
        armMotor.setVoltage(speed);
    }
}
