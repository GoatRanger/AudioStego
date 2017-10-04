package it.sauronsoftware.ftp4j;

import com.facebook.share.internal.n;
import dji.pilot.usercenter.protocol.d;
import java.util.Date;

public class FTPFile {
    public static final int TYPE_DIRECTORY = 1;
    public static final int TYPE_FILE = 0;
    public static final int TYPE_LINK = 2;
    private String link = null;
    private Date modifiedDate = null;
    private String name = null;
    private long size = -1;
    private int type;

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date date) {
        this.modifiedDate = date;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append(" [name=");
        stringBuffer.append(this.name);
        stringBuffer.append(", type=");
        if (this.type == 0) {
            stringBuffer.append("FILE");
        } else if (this.type == 1) {
            stringBuffer.append("DIRECTORY");
        } else if (this.type == 2) {
            stringBuffer.append(n.E);
            stringBuffer.append(", link=");
            stringBuffer.append(this.link);
        } else {
            stringBuffer.append("UNKNOWN");
        }
        stringBuffer.append(", size=");
        stringBuffer.append(this.size);
        stringBuffer.append(", modifiedDate=");
        stringBuffer.append(this.modifiedDate);
        stringBuffer.append(d.H);
        return stringBuffer.toString();
    }
}
