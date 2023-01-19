package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawSub extends SubsystemBase {
    private final CANSparkMax clawMotorLeft;
    private final CANSparkMax clawMotorRight;

    public ClawSub() {
        clawMotorLeft = new CANSparkMax(Constants.clawMotorLeft, MotorType.kBrushless);
        clawMotorRight = new CANSparkMax(Constants.clawMotorRight, MotorType.kBrushless);
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
}
