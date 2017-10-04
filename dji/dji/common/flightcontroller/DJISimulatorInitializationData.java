package dji.common.flightcontroller;

public class DJISimulatorInitializationData {
    public double latitude;
    public double longitude;
    public int numOfSatellites;
    public int simulationStateUpdateFrequency;

    public DJISimulatorInitializationData() {
        this(0.0d, 0.0d, 0, 0);
    }

    public DJISimulatorInitializationData(double d, double d2, int i, int i2) {
        this.latitude = d;
        this.longitude = d2;
        this.simulationStateUpdateFrequency = i;
        this.numOfSatellites = i2;
    }
}
