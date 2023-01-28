package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawStop extends CommandBase{
    private final ClawSub claw;
    
    public ClawStop(ClawSub subsystem){
        claw = subsystem;
        addRequirements(claw);
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize(){
        
    }
    
    // keeps repeating until the command ends
    @Override
    public void execute(){
        claw.stop();
    }
  
    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted){
    
    }
    
    //condition for the command to end on its own
    @Override
    public boolean isFinished(){

        return false;
    }
}
