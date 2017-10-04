package org.xeustechnologies.jtar;

import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.util.Date;

public class TarEntry {
    protected File file;
    protected TarHeader header;

    private TarEntry() {
        this.file = null;
        this.header = new TarHeader();
    }

    public TarEntry(File file, String str) {
        this();
        this.file = file;
        extractTarHeader(str);
    }

    public TarEntry(byte[] bArr) {
        this();
        parseTarHeader(bArr);
    }

    public boolean equals(TarEntry tarEntry) {
        return this.header.name.toString().equals(tarEntry.header.name.toString());
    }

    public boolean isDescendent(TarEntry tarEntry) {
        return tarEntry.header.name.toString().startsWith(this.header.name.toString());
    }

    public TarHeader getHeader() {
        return this.header;
    }

    public String getName() {
        return this.header.name.toString();
    }

    public void setName(String str) {
        this.header.name = new StringBuffer(str);
    }

    public int getUserId() {
        return this.header.userId;
    }

    public void setUserId(int i) {
        this.header.userId = i;
    }

    public int getGroupId() {
        return this.header.groupId;
    }

    public void setGroupId(int i) {
        this.header.groupId = i;
    }

    public String getUserName() {
        return this.header.userName.toString();
    }

    public void setUserName(String str) {
        this.header.userName = new StringBuffer(str);
    }

    public String getGroupName() {
        return this.header.groupName.toString();
    }

    public void setGroupName(String str) {
        this.header.groupName = new StringBuffer(str);
    }

    public void setIds(int i, int i2) {
        setUserId(i);
        setGroupId(i2);
    }

    public void setModTime(long j) {
        this.header.modTime = j / 1000;
    }

    public void setModTime(Date date) {
        this.header.modTime = date.getTime() / 1000;
    }

    public Date getModTime() {
        return new Date(this.header.modTime * 1000);
    }

    public File getFile() {
        return this.file;
    }

    public long getSize() {
        return this.header.size;
    }

    public void setSize(long j) {
        this.header.size = j;
    }

    public boolean isDirectory() {
        if (this.file != null) {
            return this.file.isDirectory();
        }
        if (this.header == null || (this.header.linkFlag != TarHeader.LF_DIR && !this.header.name.toString().endsWith(d.t))) {
            return false;
        }
        return true;
    }

    public void extractTarHeader(String str) {
        String replace = str.replace(File.separatorChar, '/');
        if (replace.startsWith(d.t)) {
            replace = replace.substring(1);
        }
        this.header.linkName = new StringBuffer("");
        this.header.name = new StringBuffer(replace);
        if (this.file.isDirectory()) {
            this.header.mode = 16877;
            this.header.linkFlag = TarHeader.LF_DIR;
            if (this.header.name.charAt(this.header.name.length() - 1) != '/') {
                this.header.name.append(d.t);
            }
            this.header.size = 0;
        } else {
            this.header.size = this.file.length();
            this.header.mode = 33188;
            this.header.linkFlag = TarHeader.LF_NORMAL;
        }
        this.header.modTime = this.file.lastModified() / 1000;
        this.header.checkSum = 0;
        this.header.devMajor = 0;
        this.header.devMinor = 0;
    }

    public long computeCheckSum(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += (long) (b & 255);
        }
        return j;
    }

    public void writeEntryHeader(byte[] bArr) {
        int longOctalBytes = Octal.getLongOctalBytes(this.header.modTime, bArr, Octal.getLongOctalBytes(this.header.size, bArr, Octal.getOctalBytes((long) this.header.groupId, bArr, Octal.getOctalBytes((long) this.header.userId, bArr, Octal.getOctalBytes((long) this.header.mode, bArr, TarHeader.getNameBytes(this.header.name, bArr, 0, 100), 8), 8), 8), 12), 12);
        int i = 0;
        int i2 = longOctalBytes;
        while (i < 8) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) 32;
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = this.header.linkFlag;
        i = Octal.getOctalBytes((long) this.header.devMinor, bArr, Octal.getOctalBytes((long) this.header.devMajor, bArr, TarHeader.getNameBytes(this.header.groupName, bArr, TarHeader.getNameBytes(this.header.userName, bArr, TarHeader.getNameBytes(this.header.magic, bArr, TarHeader.getNameBytes(this.header.linkName, bArr, i, 100), 8), 32), 32), 8), 8);
        while (i < bArr.length) {
            i2 = i + 1;
            bArr[i] = (byte) 0;
            i = i2;
        }
        Octal.getCheckSumOctalBytes(computeCheckSum(bArr), bArr, longOctalBytes, 8);
    }

    public void parseTarHeader(byte[] bArr) {
        this.header.name = TarHeader.parseName(bArr, 0, 100);
        this.header.mode = (int) Octal.parseOctal(bArr, 100, 8);
        this.header.userId = (int) Octal.parseOctal(bArr, 108, 8);
        this.header.groupId = (int) Octal.parseOctal(bArr, 116, 8);
        this.header.size = Octal.parseOctal(bArr, 124, 12);
        this.header.modTime = Octal.parseOctal(bArr, 136, 12);
        this.header.checkSum = (int) Octal.parseOctal(bArr, 148, 8);
        this.header.linkFlag = bArr[156];
        this.header.linkName = TarHeader.parseName(bArr, 157, 100);
        this.header.magic = TarHeader.parseName(bArr, 257, 8);
        this.header.userName = TarHeader.parseName(bArr, 265, 32);
        this.header.groupName = TarHeader.parseName(bArr, 297, 32);
        this.header.devMajor = (int) Octal.parseOctal(bArr, 329, 8);
        this.header.devMinor = (int) Octal.parseOctal(bArr, 337, 8);
    }
}
