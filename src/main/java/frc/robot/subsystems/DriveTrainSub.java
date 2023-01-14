package frc.robot.subsystems;

//imports for the Spark Maxs
/*import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;*/

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSub extends SubsystemBase {

    Spark frontRight = new Spark(Constants.frontRightDrive);
    Spark backRight = new Spark (Constants.backRightDrive);
    Spark frontLeft = new Spark (Constants.frontLeftDrive);
    Spark backLeft = new Spark (Constants.backLeftDrive);

      //encoders for drive (which we didnt use)
    private Encoder frontRightEncoder = new Encoder (Constants.frontRightEncoder1, Constants.frontRightEncoder2);
    private Encoder frontLeftEncoder = new Encoder (Constants.frontLeftEncoder1, Constants.frontLeftEncoder2);
    private Encoder backRightEncoder = new Encoder (Constants.backRightEncoder1, Constants.backRightEncoder2);
    private Encoder backLeftEncoder = new Encoder (Constants.backLeftEncoder1, Constants.backLeftEncoder2);

    public DriveTrainSub(){
        frontRight.setInverted(true);
        backRight.setInverted(true);
        mecanumDrive.setDeadband(0.15);
        frontRightEncoder.setDistancePerPulse(100);
        frontLeftEncoder.setDistancePerPulse(100);
        backRightEncoder.setDistancePerPulse(100);
        backLeftEncoder.setDistancePerPulse(100);
    }
    
    
    MecanumDrive mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);


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
        return Math.abs((backRightEncoder.getDistance()+backLeftEncoder.getDistance()+frontRightEncoder.getDistance()+frontLeftEncoder.getDistance())/4);
    }

  //command for autodrive
//also gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
    public void autoDrive(double distance, String strafeDirection) {
        if(strafeDirection.equals("none")){
            while (getDistance()<distance){
                double brake = 1-getDistance()/distance;
                mecanumDrive(-Constants.autoDrive*brake, 0, 0);
            }
        }
        if(strafeDirection.equals("left")){
            while (getDistance()<distance){
                double brake = 1-getDistance()/distance;
                mecanumDrive(0, -Constants.autoDrive*brake, 0);
            }
        }
        if(strafeDirection.equals("none")){
            while (getDistance()<distance){
                double brake = 1-getDistance()/distance;
                mecanumDrive(0, Constants.autoDrive*brake, 0);
            }
        }
    }

    public void mecanumDrive(double ySpeed, double xSpeed, double zRotation){
        mecanumDrive.driveCartesian(-ySpeed/2, xSpeed, zRotation);
    }
}
