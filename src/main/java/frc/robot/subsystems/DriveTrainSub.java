package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.StrafeDirection;
import frc.robot.shuffleboard.CANSparkMaxMotorManager;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;

public class DriveTrainSub extends SubsystemBase {

    private final CANSparkMax frontRight;
    private final CANSparkMax backRight;
    private final CANSparkMax frontLeft;
    private final CANSparkMax backLeft;

    //encoders for drive
    private final RelativeEncoder frontRightEncoder;
    private final RelativeEncoder frontLeftEncoder;
    private final RelativeEncoder backRightEncoder;
    private final RelativeEncoder backLeftEncoder;
    private final MecanumDrive mecanumDrive;

    public DriveTrainSub() {

        CANSparkMaxMotorManager motorManager = CANSparkMaxMotorManager.getInstance();

        frontRight = motorManager.retrieveMotor(Constants.frontRightDrive, MotorType.kBrushless);
        frontRight.setInverted(true);

        backRight = motorManager.retrieveMotor(Constants.backRightDrive, MotorType.kBrushless);
        backRight.setInverted(true);

        frontLeft = motorManager.retrieveMotor(Constants.frontLeftDrive, MotorType.kBrushless);
        backLeft = motorManager.retrieveMotor(Constants.backLeftDrive, MotorType.kBrushless);

        // frontRight = new CANSparkMax(Constants.frontRightDrive, MotorType.kBrushless);
        // frontRight.setInverted(true);
        // backRight = new CANSparkMax(Constants.backRightDrive, MotorType.kBrushless);
        // backRight.setInverted(true);
        // frontLeft = new CANSparkMax(Constants.frontLeftDrive, MotorType.kBrushless);
        // backLeft = new CANSparkMax(Constants.backLeftDrive, MotorType.kBrushless);

        //initializing encoders
        frontRightEncoder = frontRight.getEncoder(Type.kHallSensor, 42);
        frontRightEncoder.setPositionConversionFactor(.37180417/1.2); //1.0169

        backRightEncoder = backRight.getEncoder(Type.kHallSensor, 42);
        backRightEncoder.setPositionConversionFactor(.380041671/1.2); //1.04

        frontLeftEncoder = frontLeft.getEncoder(Type.kHallSensor, 42);
        frontLeftEncoder.setPositionConversionFactor(.365625/1.2); //1

        backLeftEncoder = backLeft.getEncoder(Type.kHallSensor, 42);
        backLeftEncoder.setPositionConversionFactor(.35465625/1.2); //.97

        //initializing the mecanum drive
        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecanumDrive.setDeadband(0.3);

        //resetting encoders
        frontRightEncoder.setPosition(0);
        frontLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
    }

    //resets the encoders to 0
    public void resetEncoder() {
        frontRightEncoder.setPosition(0);
        frontLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
    }

    //tells the distance of how far you've traveled
    public double getDistance() {
        // if(backRightEncoder.getPosition() > 156.6666666 && backLeftEncoder.getPosition() > 156.6666666 && 
        // frontRightEncoder.getPosition() > 156.6666666 && frontLeftEncoder.getPosition() > 156.6666666)
        return Math.abs(
                (
                        backRightEncoder.getPosition() +
                        backLeftEncoder.getPosition() +
                        frontRightEncoder.getPosition() +
                        frontLeftEncoder.getPosition()
                ) / 4
        );
    }

    //command for autodrive
    //also gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
    public void autoDrive(CustomaryLength length, StrafeDirection strafeDirection) {
        double feet = length.get(CustomaryLengthUnit.FEET);
        double brake = 1 - getDistance() / feet;

        switch (strafeDirection) {
            case FORWARD: {
                while (getDistance() < feet) {
                    mecanumDrive(-Constants.autoDrive * brake, 0, 0);
                }
                System.out.println(backRightEncoder.getPosition());
                System.out.println(backLeftEncoder.getPosition());
                System.out.println(frontRightEncoder.getPosition());
                System.out.println(frontLeftEncoder.getPosition());
                break;
            }
            case LEFT: {
                while (getDistance() < feet) {
                    mecanumDrive(0, -Constants.autoDrive * brake, 0);
                }
                break;
            }

            case RIGHT: {
                while (getDistance() < feet) {
                    mecanumDrive(0, Constants.autoDrive * brake, 0);
                }
                break;
            }

            case BACKWARDS: {
                while (getDistance() < feet) {
                    mecanumDrive(Constants.autoDrive * brake, 0, 0);
                }
                break;
            }

        }
    }

    public void mecanumDrive(double ySpeed, double xSpeed, double zRotation) {
        if(zRotation > .25)
            mecanumDrive.driveCartesian(-ySpeed / 1.3, xSpeed, (zRotation-.25)/1.2);
        else if(zRotation < -.25)
            mecanumDrive.driveCartesian(-ySpeed / 1.3, xSpeed, (zRotation+.25)/1.2);
        else
            mecanumDrive.driveCartesian(-ySpeed / 1.3, xSpeed, 0);
    }
    public void cripMecanumDrive(double ySpeed, double xSpeed, double zRotation) {
        if(zRotation > .25)
            mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, (zRotation-.25)/2);
        else if(zRotation < -.25)
            mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, (zRotation+.25)/2);
        else
            mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, 0);
    }
}
