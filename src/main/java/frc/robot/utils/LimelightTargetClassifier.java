package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

class LimelightTargetClassifier {

    @Getter
    @Setter
    @JsonProperty("class")
    String className;

    @Getter
    @Setter
    @JsonProperty("classID")
    double classID;

    @Getter
    @Setter
    @JsonProperty("conf")
    double confidence;

    @Getter
    @Setter
    @JsonProperty("zone")
    double zone;

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

    public LimelightTargetClassifier() {
    }
}
