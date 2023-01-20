package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class ClawOpen extends CommandBase{
    private final ClawSub claw;
    
    public ClawOpen(ClawSub subsystem){
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
        claw.open();
    }
  
    //only goes once at end when command is finishing
    @Override

    public void end(boolean inerrupted) {

    }
    
    //condition for the command to end on its own
    @Override
    public boolean isFinished(){

        return false;
    }
}
