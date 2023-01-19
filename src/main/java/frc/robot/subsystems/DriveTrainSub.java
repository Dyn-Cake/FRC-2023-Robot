package frc.robot.subsystems;

//imports for the Spark Maxs
/*import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;*/

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.StrafeDirection;

public class DriveTrainSub extends SubsystemBase {

    //Spark frontRight = new Spark(Constants.frontRightDrive);  UNDO AFTER TEST
    //Spark backRight = new Spark (Constants.backRightDrive);  UNDO AFTER TEST
    Spark frontLeft = new Spark (Constants.frontLeftDrive);
    Spark backLeft = new Spark (Constants.backLeftDrive);

      //encoders for drive (which we didnt use)
    private Encoder frontRightEncoder = new Encoder (Constants.frontRightEncoder1, Constants.frontRightEncoder2);
    private Encoder frontLeftEncoder = new Encoder (Constants.frontLeftEncoder1, Constants.frontLeftEncoder2);
    private Encoder backRightEncoder = new Encoder (Constants.backRightEncoder1, Constants.backRightEncoder2);
    private Encoder backLeftEncoder = new Encoder (Constants.backLeftEncoder1, Constants.backLeftEncoder2);

    public DriveTrainSub(){
        //frontRight.setInverted(true);  UNDO AFTER TEST
        //backRight.setInverted(true);  UNDO AFTER TEST
        //mecanumDrive.setDeadband(0.15);  UNDO AFTER TEST
        frontRightEncoder.setDistancePerPulse(100); //subject to change
        frontLeftEncoder.setDistancePerPulse(100); //subject to change
        backRightEncoder.setDistancePerPulse(100); //subject to change
        backLeftEncoder.setDistancePerPulse(100); //subject to change
    }
    
    
    //MecanumDrive mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight); UNDO AFTER TEST


    //Spark Max instantiation and constructor for Mecanum Drive
    /*private final CANSparkMax frontRight;
    private final CANSparkMax frontLeft;
    private final CANSparkMax backRight;
    private final CANSparkMax backLeft;
    private final MecanumDrive mecanumDrive;

    public drivetrainsub(){
        frontRight = new CANSparkMax (Constants.frontRightDrive, MotorType.kBrushless);
        frontLeft = new CANSparkMax (Constants.frontLeftDrive, MotorType.kBrushless);
        backRight = new CANSparkMax (Constants.backRightDrive, MotorType.kBrushless);
        backLeft = new CANSparkMax (Constants.backLeftDrive, MotorType.kBrushless);
        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        
    }*/

//
    //resets the encoder to 0
    public void resetEncoder(){
        frontRightEncoder.reset();
        frontLeftEncoder.reset();
        backRightEncoder.reset();
        backLeftEncoder.reset();
  }

  //tells the distance of how far youve traveled
    public double getDistance(){
        return Math.abs(
                (
                    backRightEncoder.getDistance() +
                    backLeftEncoder.getDistance() +
                    frontRightEncoder.getDistance() +
                    frontLeftEncoder.getDistance()
                ) / 4
        );
    }

  //command for autodrive
//also gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
    public void autoDrive(double distance, StrafeDirection strafeDirection) {

        double brake = 1 - getDistance() / distance;
        switch (strafeDirection) {
            case FORWARD: {
                while (getDistance()<distance) {
                    mecanumDrive(-Constants.autoDrive*brake, 0, 0);
                }
                break;
            }
            case LEFT: {
                while (getDistance()<distance) {
                    mecanumDrive(0, -Constants.autoDrive*brake, 0);
                }
                break;
            }

            case RIGHT: {
                while (getDistance()<distance) {
                    mecanumDrive(0, Constants.autoDrive*brake, 0);
                }
                break;
            }

            case BACKWARDS: {
                while (getDistance()<distance) {
                    mecanumDrive(Constants.autoDrive*brake, 0, 0);
                }
                break;
            }

        }
    }

    public void mecanumDrive(double ySpeed, double xSpeed, double zRotation){
        //mecanumDrive.driveCartesian(-ySpeed/2, xSpeed, zRotation);  UNDO AFTER TEST
    }
}
