package dji.common.camera;

public class ThermalExternalSceneSettings {
    private short atmosphericTemp;
    private short atmosphericTransCoefficient;
    private short bckgrndTemp;
    private short sceneEmissivity;
    private short windowReflectedTemp;
    private short windowReflection;
    private short windowTemp;
    private short windowTransCoefficient;

    public ThermalExternalSceneSettings(short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8) {
        this.atmosphericTemp = s;
        this.atmosphericTransCoefficient = s2;
        this.bckgrndTemp = s3;
        this.sceneEmissivity = s4;
        this.windowReflection = s5;
        this.windowReflectedTemp = s6;
        this.windowTemp = s7;
        this.windowTransCoefficient = s8;
    }

    public void setAtmosphericTemp(short s) {
        this.atmosphericTemp = s;
    }

    public void setAtmosphericTransCoefficient(short s) {
        this.atmosphericTransCoefficient = s;
    }

    public void setBckgrndTemp(short s) {
        this.bckgrndTemp = s;
    }

    public void setSceneEmissivity(short s) {
        this.sceneEmissivity = s;
    }

    public void setWindowReflection(short s) {
        this.windowReflection = s;
    }

    public void setWindowReflectedTemp(short s) {
        this.windowReflectedTemp = s;
    }

    public void setWindowTemp(short s) {
        this.windowTemp = s;
    }

    public void setWindowTransCoefficient(short s) {
        this.windowTransCoefficient = s;
    }

    public short getAtmosphericTemp() {
        return this.atmosphericTemp;
    }

    public short getAtmosphericTransCoefficient() {
        return this.atmosphericTransCoefficient;
    }

    public short getBckgrndTemp() {
        return this.bckgrndTemp;
    }

    public short getSceneEmissivity() {
        return this.sceneEmissivity;
    }

    public short getWindowReflection() {
        return this.windowReflection;
    }

    public short getWindowReflectedTemp() {
        return this.windowReflectedTemp;
    }

    public short getWindowTemp() {
        return this.windowTemp;
    }

    public short getWindowTransCoefficient() {
        return this.windowTransCoefficient;
    }
}
