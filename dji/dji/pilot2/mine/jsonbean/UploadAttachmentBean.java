package dji.pilot2.mine.jsonbean;

public class UploadAttachmentBean {
    private String FileContent;
    private String FileName;
    private String MimeType;
    private String NoteText;
    private String ObjectId;
    private String Subject;

    public String getSubject() {
        return this.Subject;
    }

    public void setSubject(String str) {
        this.Subject = str;
    }

    public String getNoteText() {
        return this.NoteText;
    }

    public void setNoteText(String str) {
        this.NoteText = str;
    }

    public String getFileName() {
        return this.FileName;
    }

    public void setFileName(String str) {
        this.FileName = str;
    }

    public String getMimeType() {
        return this.MimeType;
    }

    public void setMimeType(String str) {
        this.MimeType = str;
    }

    public String getFileContent() {
        return this.FileContent;
    }

    public void setFileContent(String str) {
        this.FileContent = str;
    }

    public String getObjectId() {
        return this.ObjectId;
    }

    public void setObjectId(String str) {
        this.ObjectId = str;
    }
}
