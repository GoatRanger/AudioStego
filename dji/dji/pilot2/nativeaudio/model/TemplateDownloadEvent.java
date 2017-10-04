package dji.pilot2.nativeaudio.model;

public class TemplateDownloadEvent {
    public int id;
    public boolean isDownloadSuccess;

    public TemplateDownloadEvent(int i, boolean z) {
        this.id = i;
        this.isDownloadSuccess = z;
    }
}
