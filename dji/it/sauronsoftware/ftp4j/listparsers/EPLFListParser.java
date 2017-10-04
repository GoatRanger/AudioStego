package it.sauronsoftware.ftp4j.listparsers;

import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;
import java.util.Date;
import java.util.StringTokenizer;

public class EPLFListParser implements FTPListParser {
    public FTPFile[] parse(String[] strArr) throws FTPListParseException {
        int length = strArr.length;
        FTPFile[] fTPFileArr = null;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (str.charAt(0) != '+') {
                throw new FTPListParseException();
            }
            int indexOf = str.indexOf(9);
            if (indexOf == -1) {
                throw new FTPListParseException();
            }
            int i2;
            String substring = str.substring(1, indexOf);
            String substring2 = str.substring(indexOf + 1, str.length());
            Date date = null;
            Object obj = null;
            long j = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(substring, ",");
            while (stringTokenizer.hasMoreTokens()) {
                substring = stringTokenizer.nextToken();
                int length2 = substring.length();
                if (length2 > 0) {
                    if (length2 != 1) {
                        char charAt = substring.charAt(0);
                        substring = substring.substring(1, length2);
                        if (charAt == 's') {
                            try {
                                j = Long.parseLong(substring);
                            } catch (Throwable th) {
                            }
                        } else if (charAt == 'm') {
                            try {
                                date = new Date(Long.parseLong(substring) * 1000);
                            } catch (Throwable th2) {
                            }
                        }
                    } else if (substring.equals(d.t)) {
                        obj = 1;
                    }
                }
            }
            if (fTPFileArr == null) {
                fTPFileArr = new FTPFile[length];
            }
            fTPFileArr[i] = new FTPFile();
            fTPFileArr[i].setName(substring2);
            fTPFileArr[i].setModifiedDate(date);
            fTPFileArr[i].setSize(j);
            FTPFile fTPFile = fTPFileArr[i];
            if (obj != null) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            fTPFile.setType(i2);
        }
        return fTPFileArr;
    }

    public static void main(String[] strArr) throws Throwable {
        int i = 0;
        FTPFile[] parse = new EPLFListParser().parse(new String[]{"+i8388621.29609,m824255902,/,\tdev", "+i8388621.44468,m839956783,r,s10376,\tRFCEPLF"});
        while (i < parse.length) {
            System.out.println(parse[i]);
            i++;
        }
    }
}
