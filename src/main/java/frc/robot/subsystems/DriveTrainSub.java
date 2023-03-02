package frc.robot.subsystems;

//imports for the Spark Maxs
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
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
    private final Encoder frontRightEncoder = new Encoder(Constants.frontRightEncoder1, Constants.frontRightEncoder2);
    private final Encoder frontLeftEncoder = new Encoder(Constants.frontLeftEncoder1, Constants.frontLeftEncoder2);
    private final Encoder backRightEncoder = new Encoder(Constants.backRightEncoder1, Constants.backRightEncoder2);
    private final Encoder backLeftEncoder = new Encoder(Constants.backLeftEncoder1, Constants.backLeftEncoder2);
    private final MecanumDrive mecanumDrive;

    public DriveTrainSub() {

        CANSparkMaxMotorManager motorManager = CANSparkMaxMotorManager.getInstance();

        frontRight = motorManager.retrieveMotor(Constants.frontRightDrive, MotorType.kBrushless);
        frontRight.setInverted(true);

        backRight = motorManager.retrieveMotor(Constants.backRightDrive, MotorType.kBrushless);
        backRight.setInverted(true);

        frontLeft = motorManager.retrieveMotor(Constants.frontLeftDrive, MotorType.kBrushless);
        backLeft = motorManager.retrieveMotor(Constants.backLeftDrive, MotorType.kBrushless);

        frontRightEncoder.setDistancePerPulse(100); //subject to change
        frontLeftEncoder.setDistancePerPulse(100); //subject to change
        backRightEncoder.setDistancePerPulse(100); //subject to change
        backLeftEncoder.setDistancePerPulse(100); //subject to change

        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecanumDrive.setDeadband(0.2);
    }

    //resets the encoders to 0
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
    public void autoDrive(CustomaryLength length, StrafeDirection strafeDirection) {
        double feet = length.get(CustomaryLengthUnit.FEET);
        double brake = 1 - getDistance() / feet;

        switch (strafeDirection) {
            case FORWARD: {
                while (getDistance() < feet) {
                    mecanumDrive(-Constants.autoDrive * brake, 0, 0);
                }
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
        mecanumDrive.driveCartesian(-ySpeed / 1.3, xSpeed, zRotation / 1.7);
    }
}
