package dji.common.flightcontroller;

public class DJIVisionDetectionSector {
    private float obstacleDistanceInMeters = 0.0f;
    private DJIVisionSectorWarning warningLevel = DJIVisionSectorWarning.Unknown;

    public void setWarningLevel(DJIVisionSectorWarning dJIVisionSectorWarning) {
        this.warningLevel = dJIVisionSectorWarning;
    }

    public void setObstacleDistanceInMeters(float f) {
        this.obstacleDistanceInMeters = f;
    }

    public DJIVisionSectorWarning getWarningLevel() {
        return this.warningLevel;
    }

    public float getObstacleDistanceInMeters() {
        return this.obstacleDistanceInMeters;
    }

    public DJIVisionDetectionSector(DJIVisionSectorWarning dJIVisionSectorWarning, float f) {
        this.warningLevel = dJIVisionSectorWarning;
        this.obstacleDistanceInMeters = f;
    }
}
