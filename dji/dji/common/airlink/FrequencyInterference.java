package dji.common.airlink;

public class FrequencyInterference {
    public float frequencyFrom;
    public float frequencyTo;
    public byte rssi;

    public FrequencyInterference(float f, float f2, byte b) {
        this.frequencyFrom = f;
        this.frequencyTo = f2;
        this.rssi = b;
    }
}
