package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawStop extends CommandBase{
    private final ClawSub m_Claw;
    
    public ClawStop(ClawSub subsystem){
        m_Claw = subsystem;
        addRequirements(m_Claw);
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize(){
        
    }
    
    // keeps repeating until the command ends
    @Override
    public void execute(){
        m_Claw.stop();
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
