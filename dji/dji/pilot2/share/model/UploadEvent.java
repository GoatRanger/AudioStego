package dji.pilot2.share.model;

public class UploadEvent {
    public String id;
    public String localPath;
    public a result;

    public enum a {
        UPLOAD_SUCCESS,
        UPLOAD_FAILED,
        UPLOAD_CANCELED
    }

    public UploadEvent(String str, String str2, a aVar) {
        this.id = str;
        this.localPath = str2;
        this.result = aVar;
    }
}
