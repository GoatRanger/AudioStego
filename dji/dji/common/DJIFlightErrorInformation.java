package dji.common;

public class DJIFlightErrorInformation {
    private DJIFlightError flightError;
    private boolean hasError;

    public DJIFlightError getFlightError() {
        return this.flightError;
    }

    public void setFlightError(DJIFlightError dJIFlightError) {
        this.flightError = dJIFlightError;
    }

    public boolean hasError() {
        return this.hasError;
    }

    public void setHasError(boolean z) {
        this.hasError = z;
    }
}
