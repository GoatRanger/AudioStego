package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraPhotoTimeLapseFileFormat;

public class CameraPhotoTimeLapseParam {
    private int duration;
    private CameraPhotoTimeLapseFileFormat fileFormat;
    private int interval;

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setFileFormat(CameraPhotoTimeLapseFileFormat cameraPhotoTimeLapseFileFormat) {
        this.fileFormat = cameraPhotoTimeLapseFileFormat;
    }

    public CameraPhotoTimeLapseFileFormat getFileFormat() {
        return this.fileFormat;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getInterval() {
        return this.interval;
    }
}
