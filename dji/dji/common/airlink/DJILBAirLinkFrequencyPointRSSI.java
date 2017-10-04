package dji.common.airlink;

public class DJILBAirLinkFrequencyPointRSSI {
    public float frequencyFrom;
    public float frequencyTo;
    public int rssi;

    public DJILBAirLinkFrequencyPointRSSI(float f, float f2, int i) {
        this.frequencyFrom = f;
        this.frequencyTo = f2;
        this.rssi = i;
    }
}
