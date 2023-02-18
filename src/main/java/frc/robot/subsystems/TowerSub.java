package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TowerSub extends SubsystemBase{
    private final CANSparkMax towerMotor;
    private RobotContainer container;
    private RelativeEncoder towerEncoder;

    public TowerSub(RobotContainer container){
        this.container = container;
        towerMotor = new CANSparkMax(Constants.towerMotor, MotorType.kBrushless);
        towerMotor.setIdleMode(IdleMode.kBrake);
        towerEncoder = towerMotor.getEncoder(Type.kQuadrature, 30);
        towerEncoder.setPosition(0);

    }
    

    public void lift(){
        if(towerEncoder.getPosition() > .25)
        towerMotor.setVoltage(-Constants.towerMotorVolt);
        else
        towerMotor.setVoltage(0);
    }

    public void drop(){
        towerMotor.setVoltage(Constants.towerMotorVolt);
    }
    public void stop(){
        towerMotor.setVoltage(0);
    }
    public void towerControl(double speed){
        towerMotor.setVoltage(
                container.deadBand(
                        speed,
                        Constants.deadband
                ) * Constants.towerMotorVolt
        );

    }
}
