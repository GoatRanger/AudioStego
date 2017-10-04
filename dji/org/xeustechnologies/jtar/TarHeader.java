package org.xeustechnologies.jtar;

public class TarHeader {
    public static final int CHKSUMLEN = 8;
    public static final int DEVLEN = 8;
    public static final int GIDLEN = 8;
    public static final int GNAMELEN = 32;
    public static final String GNU_TMAGIC = "ustar  ";
    public static final byte LF_BLK = (byte) 52;
    public static final byte LF_CHR = (byte) 51;
    public static final byte LF_CONTIG = (byte) 55;
    public static final byte LF_DIR = (byte) 53;
    public static final byte LF_FIFO = (byte) 54;
    public static final byte LF_LINK = (byte) 49;
    public static final byte LF_NORMAL = (byte) 48;
    public static final byte LF_OLDNORM = (byte) 0;
    public static final byte LF_SYMLINK = (byte) 50;
    public static final int MAGICLEN = 8;
    public static final int MODELEN = 8;
    public static final int MODTIMELEN = 12;
    public static final int NAMELEN = 100;
    public static final int SIZELEN = 12;
    public static final String TMAGIC = "ustar";
    public static final int UIDLEN = 8;
    public static final int UNAMELEN = 32;
    public int checkSum;
    public int devMajor;
    public int devMinor;
    public int groupId;
    public StringBuffer groupName;
    public byte linkFlag;
    public StringBuffer linkName = new StringBuffer();
    public StringBuffer magic = new StringBuffer(TMAGIC);
    public long modTime;
    public int mode;
    public StringBuffer name = new StringBuffer();
    public long size;
    public int userId;
    public StringBuffer userName;

    public TarHeader() {
        String property = System.getProperty("user.name", "");
        if (property.length() > 31) {
            property = property.substring(0, 31);
        }
        this.userId = 0;
        this.groupId = 0;
        this.userName = new StringBuffer(property);
        this.groupName = new StringBuffer("");
    }

    public static StringBuffer parseName(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        int i3 = i + i2;
        while (i < i3 && bArr[i] != (byte) 0) {
            stringBuffer.append((char) bArr[i]);
            i++;
        }
        return stringBuffer;
    }

    public static int getNameBytes(StringBuffer stringBuffer, byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2 && i3 < stringBuffer.length()) {
            bArr[i + i3] = (byte) stringBuffer.charAt(i3);
            i3++;
        }
        while (i3 < i2) {
            bArr[i + i3] = (byte) 0;
            i3++;
        }
        return i + i2;
    }
}
