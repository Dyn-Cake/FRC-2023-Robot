package frc.robot.shuffleboard;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import lombok.Getter;
import lombok.NonNull;

public class ShuffleboardMotor {

    @NonNull
    @Getter
    private String name;
    @Getter
    private Spark motor;
    @Getter
    private GenericEntry genericEntry;

    public ShuffleboardMotor(Spark spark, String name, GenericEntry genericEntry) {
        this.motor = spark;
        this.name = name;
        this.genericEntry = genericEntry;
    }

    public ShuffleboardMotor(String name) {
        this.name = name;
        this.motor = null;
        this.genericEntry = null;
    }

    public GenericEntry getGenericEntry() {
        return genericEntry;
    }

    public Spark getMotor() {
        return motor;
    }

    public String getName() {
        return name;
    }
}
