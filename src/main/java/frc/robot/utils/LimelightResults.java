package frc.robot.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LimelightResults {
    @JsonProperty("Results")
    private Results results;

    public LimelightResults() {
        results = new Results();
    }

    public Results getResults(){
        return results;
    }
}