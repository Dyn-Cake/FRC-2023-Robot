package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

class LimelightTargetRetro {

    @Getter
    @Setter
    @JsonProperty("t6c_ts")
    double[] cameraPose_TargetSpace;

    @Getter
    @Setter
    @JsonProperty("t6r_fs")
    double[] robotPose_FieldSpace;

    @Getter
    @Setter
    @JsonProperty("t6r_ts")
    double[] robotPose_TargetSpace;

    @Getter
    @Setter
    @JsonProperty("t6t_cs")
    double[] targetPose_CameraSpace;

    @Getter
    @Setter
    @JsonProperty("t6t_rs")
    double[] targetPose_RobotSpace;

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

    public LimelightTargetRetro() {
        cameraPose_TargetSpace = new double[6];
        robotPose_FieldSpace = new double[6];
        robotPose_TargetSpace = new double[6];
        targetPose_CameraSpace = new double[6];
        targetPose_RobotSpace = new double[6];
    }

}
