package dji.common.airlink;

public enum WiFiFrequencyBand {
    FrequencyBand2Dot4G(0),
    FrequencyBand5G(1),
    FrequencyBandDual(2),
    Unknown(255);
    
    private int value;

    private WiFiFrequencyBand(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static WiFiFrequencyBand find(int i) {
        WiFiFrequencyBand wiFiFrequencyBand = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return wiFiFrequencyBand;
    }
}
