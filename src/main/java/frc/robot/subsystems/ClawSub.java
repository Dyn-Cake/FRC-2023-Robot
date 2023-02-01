package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.RobotContainer;
import frc.robot.SparkMotorManager;


public class ClawSub extends SubsystemBase {
    private final Spark clawMotorLeft;
    private final Spark clawMotorRight;
    private RobotContainer container;

    public ClawSub(RobotContainer container) {
        SparkMotorManager sparkMotorManager = SparkMotorManager.getInstance();
        this.container = container;
        clawMotorLeft = sparkMotorManager.getMotor(Constants.clawMotorLeft);
        clawMotorRight = sparkMotorManager.getMotor(Constants.clawMotorRight);
    }
    /**
    * Sets the voltage output of the SpeedController. This is equivillant to a call to
    * SetReference(output, rev::ControlType::kVoltage). The behavior of this call differs slightly
    * from the WPILib documetation for this call since the device internally sets the desired voltage
     * (not a compensation value). That means that this *can* be a 'set-and-forget' call.
     *
     * @param outputVolts The voltage to output.
     */

     public void open(){
        clawMotorLeft.setVoltage(Constants.clawMotorLeftVolt);
        clawMotorRight.setVoltage(-Constants.clawMotorRightVolt);
    }
    public void close(){
        clawMotorLeft.setVoltage(-Constants.clawMotorLeftVolt);
        clawMotorRight.setVoltage(Constants.clawMotorRightVolt);
    }
    public void stop(){
        clawMotorLeft.setVoltage(0);
        clawMotorRight.setVoltage(0);
    }

    public void clawControl(double speed){
         double voltage = container.deadBand(
                 speed,
                 Constants.deadband
         );
        clawMotorLeft.setVoltage(voltage * Constants.clawMotorLeftVolt);
        clawMotorRight.setVoltage(-voltage * Constants.clawMotorRightVolt);
    }
}
