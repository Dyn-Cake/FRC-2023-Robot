package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class LimelightResults {

    @Getter
    @Setter
    @JsonProperty("Results")
    private Results results;

    public LimelightResults() {
        results = new Results();
    }


}