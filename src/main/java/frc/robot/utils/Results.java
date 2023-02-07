package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Results {

    @Getter
    @Setter
    @JsonProperty("pID")
    private double pipelineID;

    @Getter
    @Setter
    @JsonProperty("tl")
    private double latency_pipeline;

    @Getter
    @Setter
    @JsonProperty("tl_cap")
    private double latency_capture;

    @Getter
    @Setter
    private double latency_jsonParse;

    @Getter
    @Setter
    @JsonProperty("ts")
    private double timestamp_LIMELIGHT_publish;

    @Getter
    @Setter
    @JsonProperty("ts_rio")
    private double timestamp_RIOFPGA_capture;

    @Getter
    @Setter
    @JsonProperty("v")
    private double valid;

    @Getter
    @Setter
    @JsonProperty("botpose")
    private double[] botpose;

    @Getter
    @Setter
    @JsonProperty("botpose_wpired")
    private double[] botpose_wpired;

    @Getter
    @Setter
    @JsonProperty("botpose_wpiblue")
    private double[] botpose_wpiblue;

    @Getter
    @Setter
    @JsonProperty("Retro")
    private LimelightTargetRetro[] targets_Retro;

    @Getter
    @Setter
    @JsonProperty("Fiducial")
    private LimelightTargetFiducial[] targets_Fiducials;


    @Getter
    @Setter
    @JsonProperty("Classifier")
    private LimelightTargetClassifier[] targets_Classifier;

    @Getter
    @Setter
    @JsonProperty("Detector")
    private LimelightTargetDetector[] targets_Detector;

    @Getter
    @Setter
    @JsonProperty("Barcode")
    private LimelightTargetBarcode[] targets_Barcode;

}
