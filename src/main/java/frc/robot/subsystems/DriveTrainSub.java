package frc.robot.subsystems;

//imports for the Spark Maxs
/*import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;*/

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.autonomous.StrafeDirection;
import frc.robot.shuffleboard.SparkMotorManager;

public class DriveTrainSub extends SubsystemBase {

    private final Spark frontRight;
    private final Spark backRight;
    private final Spark frontLeft;
    private final Spark backLeft;

    //encoders for drive
    private final Encoder frontRightEncoder = new Encoder(Constants.frontRightEncoder1, Constants.frontRightEncoder2);
    private final Encoder frontLeftEncoder = new Encoder(Constants.frontLeftEncoder1, Constants.frontLeftEncoder2);
    private final Encoder backRightEncoder = new Encoder(Constants.backRightEncoder1, Constants.backRightEncoder2);
    private final Encoder backLeftEncoder = new Encoder(Constants.backLeftEncoder1, Constants.backLeftEncoder2);
    private final MecanumDrive mecanumDrive;

    public DriveTrainSub() {

        SparkMotorManager motorManager = SparkMotorManager.getInstance();

        frontRight = motorManager.getMotor(Constants.frontRightDrive);
        frontRight.setInverted(true);

        backRight = motorManager.getMotor(Constants.backRightDrive);
        backRight.setInverted(true);

        frontLeft = motorManager.getMotor(Constants.frontLeftDrive);
        backLeft = motorManager.getMotor(Constants.backLeftDrive);

        frontRightEncoder.setDistancePerPulse(100); //subject to change
        frontLeftEncoder.setDistancePerPulse(100); //subject to change
        backRightEncoder.setDistancePerPulse(100); //subject to change
        backLeftEncoder.setDistancePerPulse(100); //subject to change

        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecanumDrive.setDeadband(0.2);
    }


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
    public void resetEncoder() {
        frontRightEncoder.reset();
        frontLeftEncoder.reset();
        backRightEncoder.reset();
        backLeftEncoder.reset();
    }

    //tells the distance of how far you've traveled
    public double getDistance() {
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
                while (getDistance() < distance) {
                    mecanumDrive(-Constants.autoDrive * brake, 0, 0);
                }
                break;
            }
            case LEFT: {
                while (getDistance() < distance) {
                    mecanumDrive(0, -Constants.autoDrive * brake, 0);
                }
                break;
            }

            case RIGHT: {
                while (getDistance() < distance) {
                    mecanumDrive(0, Constants.autoDrive * brake, 0);
                }
                break;
            }

            case BACKWARDS: {
                while (getDistance() < distance) {
                    mecanumDrive(Constants.autoDrive * brake, 0, 0);
                }
                break;
            }

        }
    }

    public void mecanumDrive(double ySpeed, double xSpeed, double zRotation) {
        mecanumDrive.driveCartesian(-ySpeed / 1.3, xSpeed, zRotation / 1.7);
    }
}
