package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainsub;
import java.util.function.DoubleSupplier;

public class drivecartesian extends CommandBase {
    
    private final drivetrainsub m_Drive;
    private final DoubleSupplier xSpeed;
    private final DoubleSupplier ySpeed;
    private final DoubleSupplier zRotation;

    public drivecartesian (drivetrainsub subsystem, DoubleSupplier ySpeed, DoubleSupplier xSpeed, DoubleSupplier zRotation){
        m_Drive = subsystem;
        addRequirements(m_Drive);
        this.ySpeed = ySpeed;
        this.xSpeed = xSpeed;
        this.zRotation = zRotation;
    }

// only goes once at beginning when command is called
@Override
public void initialize(){

}

// keeps repeating until the command ends
@Override
public void execute(){
   m_Drive.mecanumdrive(
       ySpeed.getAsDouble(), 
       xSpeed.getAsDouble(), 
       zRotation.getAsDouble()
    );
}

//only goes once at end when command is finishing
@Override
public void end(boolean inerrupted){

}

//condition for the command to end on its own
@Override
public boolean isFinished(){

    return false;

}

}
