package it.sauronsoftware.ftp4j.listparsers;

import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MLSDListParser implements FTPListParser {
    private static final DateFormat MLSD_DATE_FORMAT_1 = new SimpleDateFormat("yyyyMMddhhmmss.SSS Z");
    private static final DateFormat MLSD_DATE_FORMAT_2 = new SimpleDateFormat("yyyyMMddhhmmss Z");

    private it.sauronsoftware.ftp4j.FTPFile parseLine(java.lang.String r13) throws it.sauronsoftware.ftp4j.FTPListParseException {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.fixSplitterBlock(BlockFinish.java:63)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:34)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r12 = this;
        r2 = 0;
        r4 = 0;
        r3 = 0;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = new java.util.StringTokenizer;
        r6 = ";";
        r0.<init>(r13, r6);
    L_0x0010:
        r6 = r0.hasMoreElements();
        if (r6 == 0) goto L_0x0028;
    L_0x0016:
        r6 = r0.nextToken();
        r6 = r6.trim();
        r7 = r6.length();
        if (r7 <= 0) goto L_0x0010;
    L_0x0024:
        r1.add(r6);
        goto L_0x0010;
    L_0x0028:
        r0 = r1.size();
        if (r0 != 0) goto L_0x0034;
    L_0x002e:
        r0 = new it.sauronsoftware.ftp4j.FTPListParseException;
        r0.<init>();
        throw r0;
    L_0x0034:
        r0 = r1.size();
        r0 = r0 + -1;
        r0 = r1.remove(r0);
        r0 = (java.lang.String) r0;
        r6 = new java.util.Properties;
        r6.<init>();
        r7 = r1.iterator();
    L_0x0049:
        r1 = r7.hasNext();
        if (r1 == 0) goto L_0x0090;
    L_0x004f:
        r1 = r7.next();
        r1 = (java.lang.String) r1;
        r8 = 61;
        r8 = r1.indexOf(r8);
        r9 = -1;
        if (r8 != r9) goto L_0x0064;
    L_0x005e:
        r0 = new it.sauronsoftware.ftp4j.FTPListParseException;
        r0.<init>();
        throw r0;
    L_0x0064:
        r9 = r1.substring(r2, r8);
        r9 = r9.trim();
        r8 = r8 + 1;
        r10 = r1.length();
        r1 = r1.substring(r8, r10);
        r1 = r1.trim();
        r8 = r9.length();
        if (r8 == 0) goto L_0x0086;
    L_0x0080:
        r8 = r1.length();
        if (r8 != 0) goto L_0x008c;
    L_0x0086:
        r0 = new it.sauronsoftware.ftp4j.FTPListParseException;
        r0.<init>();
        throw r0;
    L_0x008c:
        r6.setProperty(r9, r1);
        goto L_0x0049;
    L_0x0090:
        r1 = "type";
        r1 = r6.getProperty(r1);
        if (r1 != 0) goto L_0x009e;
    L_0x0098:
        r0 = new it.sauronsoftware.ftp4j.FTPListParseException;
        r0.<init>();
        throw r0;
    L_0x009e:
        r7 = "file";
        r7 = r7.equalsIgnoreCase(r1);
        if (r7 == 0) goto L_0x00ee;
    L_0x00a6:
        r1 = r2;
    L_0x00a7:
        r2 = "modify";
        r2 = r6.getProperty(r2);
        if (r2 == 0) goto L_0x012d;
    L_0x00af:
        r7 = new java.lang.StringBuffer;
        r7.<init>();
        r2 = r7.append(r2);
        r7 = " +0000";
        r2 = r2.append(r7);
        r7 = r2.toString();
        r8 = MLSD_DATE_FORMAT_1;	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
        monitor-enter(r8);	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
        r2 = MLSD_DATE_FORMAT_1;	 Catch:{ all -> 0x0109 }
        r2 = r2.parse(r7);	 Catch:{ all -> 0x0109 }
        monitor-exit(r8);	 Catch:{ all -> 0x0126 }
    L_0x00cc:
        r3 = "size";
        r3 = r6.getProperty(r3);
        if (r3 == 0) goto L_0x00dc;
    L_0x00d4:
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x0121 }
    L_0x00d8:
        r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r3 >= 0) goto L_0x012b;
    L_0x00dc:
        r3 = new it.sauronsoftware.ftp4j.FTPFile;
        r3.<init>();
        r3.setType(r1);
        r3.setModifiedDate(r2);
        r3.setSize(r4);
        r3.setName(r0);
    L_0x00ed:
        return r3;
    L_0x00ee:
        r2 = "dir";
        r2 = r2.equalsIgnoreCase(r1);
        if (r2 == 0) goto L_0x00f8;
    L_0x00f6:
        r1 = 1;
        goto L_0x00a7;
    L_0x00f8:
        r0 = "cdir";
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 != 0) goto L_0x00ed;
    L_0x0100:
        r0 = "pdir";
        r0 = r0.equalsIgnoreCase(r1);
        if (r0 == 0) goto L_0x00ed;
    L_0x0108:
        goto L_0x00ed;
    L_0x0109:
        r2 = move-exception;
    L_0x010a:
        monitor-exit(r8);	 Catch:{ all -> 0x0109 }
        throw r2;	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
    L_0x010c:
        r2 = move-exception;
        r8 = MLSD_DATE_FORMAT_2;	 Catch:{ ParseException -> 0x011e }
        monitor-enter(r8);	 Catch:{ ParseException -> 0x011e }
        r2 = MLSD_DATE_FORMAT_2;	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
        r2 = r2.parse(r7);	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
        monitor-exit(r8);	 Catch:{ all -> 0x0118 }
        goto L_0x00cc;
    L_0x0118:
        r3 = move-exception;
        r11 = r3;
        r3 = r2;
        r2 = r11;
    L_0x011c:
        monitor-exit(r8);	 Catch:{ ParseException -> 0x010c, all -> 0x0124 }
        throw r2;	 Catch:{ ParseException -> 0x011e }
    L_0x011e:
        r2 = move-exception;
        r2 = r3;
        goto L_0x00cc;
    L_0x0121:
        r3 = move-exception;
        r6 = r4;
        goto L_0x00d8;
    L_0x0124:
        r2 = move-exception;
        goto L_0x011c;
    L_0x0126:
        r3 = move-exception;
        r11 = r3;
        r3 = r2;
        r2 = r11;
        goto L_0x010a;
    L_0x012b:
        r4 = r6;
        goto L_0x00dc;
    L_0x012d:
        r2 = r3;
        goto L_0x00cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.ftp4j.listparsers.MLSDListParser.parseLine(java.lang.String):it.sauronsoftware.ftp4j.FTPFile");
    }

    public FTPFile[] parse(String[] strArr) throws FTPListParseException {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (String parseLine : strArr) {
            FTPFile parseLine2 = parseLine(parseLine);
            if (parseLine2 != null) {
                arrayList.add(parseLine2);
            }
        }
        int size = arrayList.size();
        FTPFile[] fTPFileArr = new FTPFile[size];
        while (i < size) {
            fTPFileArr[i] = (FTPFile) arrayList.get(i);
            i++;
        }
        return fTPFileArr;
    }
}
