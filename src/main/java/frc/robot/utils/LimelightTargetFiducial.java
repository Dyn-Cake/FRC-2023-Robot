package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class LimelightTargetFiducial {


    @Getter
    @Setter
    @JsonProperty("fID")
    double fiducialID;

    @Getter
    @Setter
    @JsonProperty("fam")
    String fiducialFamily;

    @Getter
    @Setter
    @JsonProperty("t6c_ts")
    double[] cameraPoseTargetSpace;

    @Getter
    @Setter
    @JsonProperty("t6r_fs")
    double[] robotPoseFieldSpace;

    @Getter
    @Setter
    @JsonProperty("t6r_ts")
    double[] robotPoseTargetSpace;

    @Getter
    @Setter
    @JsonProperty("t6t_cs")
    double[] targetPoseCameraSpace;

    @Getter
    @Setter
    @JsonProperty("t6t_rs")
    double[] targetPoseRobotSpace;

    @Getter
    @Setter
    @JsonProperty("ta")
    double ta;

    @Getter
    @Setter
    @JsonProperty("tx")
    double tx;

    @Getter
    @Setter
    @JsonProperty("txp")
    double tx_pixels;

    @Getter
    @Setter
    @JsonProperty("ty")
    double ty;

    @Getter
    @Setter
    @JsonProperty("typ")
    double ty_pixels;

    @Getter
    @Setter
    @JsonProperty("ts")
    double ts;

    public LimelightTargetFiducial() {
        cameraPoseTargetSpace = new double[6];
        robotPoseFieldSpace = new double[6];
        robotPoseTargetSpace = new double[6];
        targetPoseCameraSpace = new double[6];
        targetPoseRobotSpace = new double[6];
    }

}
