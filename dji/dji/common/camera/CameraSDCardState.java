package dji.common.camera;

public class CameraSDCardState {
    private long availableCaptureCount;
    private int availableRecordingTime;
    private boolean hasError;
    private boolean isFormatted;
    private boolean isFormatting;
    private boolean isFull;
    private boolean isInitializing;
    private boolean isInserted;
    private boolean isInvalidFormat;
    private boolean isReadOnly;
    private boolean isVerified;
    private int remainingSpace;
    private int totalSpace;

    public boolean isInitializing() {
        return this.isInitializing;
    }

    public void setIsInitializing(boolean z) {
        this.isInitializing = z;
    }

    public boolean hasError() {
        return this.hasError;
    }

    public void setHasError(boolean z) {
        this.hasError = z;
    }

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public void setReadOnly(boolean z) {
        this.isReadOnly = z;
    }

    public boolean isInvalidFormat() {
        return this.isInvalidFormat;
    }

    public void setInvalidFormat(boolean z) {
        this.isInvalidFormat = z;
    }

    public boolean isFormatted() {
        return this.isFormatted;
    }

    public void setFormated(boolean z) {
        this.isFormatted = z;
    }

    public boolean isFormatting() {
        return this.isFormatting;
    }

    public void setFormating(boolean z) {
        this.isFormatting = z;
    }

    public boolean isFull() {
        return this.isFull;
    }

    public void setFull(boolean z) {
        this.isFull = z;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void setValid(boolean z) {
        this.isVerified = z;
    }

    public boolean isInserted() {
        return this.isInserted;
    }

    public void setInserted(boolean z) {
        this.isInserted = z;
    }

    public int getTotalSpaceInMegaBytes() {
        return this.totalSpace;
    }

    public void totalSpaceInMegaBytes(int i) {
        this.totalSpace = i;
    }

    public int getRemainingSpaceInMegaBytes() {
        return this.remainingSpace;
    }

    public void remainingSpaceInMegaBytes(int i) {
        this.remainingSpace = i;
    }

    public long getAvailableCaptureCount() {
        return this.availableCaptureCount;
    }

    public void setAvailableCaptureCount(long j) {
        this.availableCaptureCount = j;
    }

    public int getAvailableRecordingTime() {
        return this.availableRecordingTime;
    }

    public void setAvailableRecordingTime(int i) {
        this.availableRecordingTime = i;
    }
}
