package dji.common.camera;

public class ThermalAreaTemperatureAggregations {
    private float averageTemperature;
    private float maxTemperature;
    private int maxTemperaturePositionX;
    private int maxTemperaturePositionY;
    private float minTemperature;
    private int minTemperaturePositionX;
    private int minTemperaturePositionY;

    public ThermalAreaTemperatureAggregations(float f, float f2, int i, int i2, float f3, int i3, int i4) {
        this.averageTemperature = f;
        this.minTemperature = f2;
        this.minTemperaturePositionX = i;
        this.minTemperaturePositionY = i2;
        this.maxTemperature = f3;
        this.maxTemperaturePositionX = i3;
        this.maxTemperaturePositionY = i4;
    }

    public float getAverageTemperature() {
        return this.averageTemperature;
    }

    public float getMinTemperature() {
        return this.minTemperature;
    }

    public int getMinTemperaturePositionX() {
        return this.minTemperaturePositionX;
    }

    public int getMinTemperaturePositionY() {
        return this.minTemperaturePositionY;
    }

    public float getMaxTemperature() {
        return this.maxTemperature;
    }

    public int getMaxTemperaturePositionX() {
        return this.maxTemperaturePositionX;
    }

    public int getMaxTemperaturePositionY() {
        return this.maxTemperaturePositionY;
    }
}
