package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawSub;

public class AutoClawClose extends CommandBase{
    private final ClawSub claw;
    private double startTime;
    private double duration;

    public AutoClawClose(ClawSub subsystem){
        claw = subsystem;
        duration = 500;
    }

    // only goes once at beginning when command is called
@Override
public void initialize() {
    startTime = System.currentTimeMillis();
}

// keeps repeating until the command ends
@Override
public void execute() {
    claw.close();
}

//only goes once at end when command is finishing
@Override
public void end(boolean interrupted) {
    claw.stop();
}

//condition for the command to end on its own
@Override
public boolean isFinished() {
    if (System.currentTimeMillis()-startTime<duration){
        return false;
    }
    else {
        return true;
    }
}
}
