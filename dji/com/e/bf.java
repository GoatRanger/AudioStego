package com.e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.h.a;
import com.alipay.sdk.j.i;
import com.autonavi.aps.amapapi.model.AmapLoc;
import dji.pilot.usercenter.mode.n;
import org.json.JSONObject;

public class bf {
    private static bf a = null;
    private String b = "2.0.201501131131".replace(".", "");
    private String c = null;

    public static synchronized bf a() {
        bf bfVar;
        synchronized (bf.class) {
            if (a == null) {
                a = new bf();
            }
            bfVar = a;
        }
        return bfVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.database.sqlite.SQLiteDatabase r7, java.lang.String r8) {
        /*
        r6 = this;
        r2 = 0;
        r1 = 1;
        r0 = 0;
        r3 = android.text.TextUtils.isEmpty(r8);
        if (r3 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r3.<init>();	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r4 = "SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '";
        r3.append(r4);	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r4 = r8.trim();	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r4 = r3.append(r4);	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r5 = r6.b;	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r5 = "' ";
        r4.append(r5);	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        r5 = 0;
        r2 = r7.rawQuery(r4, r5);	 Catch:{ Throwable -> 0x004e, all -> 0x0057 }
        if (r2 == 0) goto L_0x0040;
    L_0x0032:
        r4 = r2.moveToFirst();	 Catch:{ Throwable -> 0x005e, all -> 0x0057 }
        if (r4 == 0) goto L_0x0040;
    L_0x0038:
        r4 = 0;
        r4 = r2.getInt(r4);	 Catch:{ Throwable -> 0x005e, all -> 0x0057 }
        if (r4 <= 0) goto L_0x0040;
    L_0x003f:
        r0 = r1;
    L_0x0040:
        r4 = 0;
        r5 = r3.length();	 Catch:{ Throwable -> 0x005e, all -> 0x0057 }
        r3.delete(r4, r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0057 }
        if (r2 == 0) goto L_0x0009;
    L_0x004a:
        r2.close();
        goto L_0x0009;
    L_0x004e:
        r0 = move-exception;
        r0 = r2;
    L_0x0050:
        if (r0 == 0) goto L_0x0055;
    L_0x0052:
        r0.close();
    L_0x0055:
        r0 = r1;
        goto L_0x0009;
    L_0x0057:
        r0 = move-exception;
        if (r2 == 0) goto L_0x005d;
    L_0x005a:
        r2.close();
    L_0x005d:
        throw r0;
    L_0x005e:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bf.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized void a(android.content.Context r13) throws java.lang.Exception {
        /*
        r12 = this;
        r1 = 0;
        r3 = 0;
        monitor-enter(r12);
        if (r13 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r12);
        return;
    L_0x0007:
        r0 = 0;
        r2 = "hmdb";
        r4 = 0;
        r5 = 0;
        r7 = r13.openOrCreateDatabase(r2, r4, r5);	 Catch:{ Throwable -> 0x030e, all -> 0x0300 }
        r2 = "hist";
        r2 = r12.a(r7, r2);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        if (r2 != 0) goto L_0x0038;
    L_0x0018:
        if (r7 == 0) goto L_0x0023;
    L_0x001a:
        r2 = r7.isOpen();	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        if (r2 == 0) goto L_0x0023;
    L_0x0020:
        r7.close();	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
    L_0x0023:
        r2 = 0;
        if (r1 == 0) goto L_0x0029;
    L_0x0026:
        r0.close();	 Catch:{ all -> 0x0035 }
    L_0x0029:
        if (r1 == 0) goto L_0x0005;
    L_0x002b:
        r0 = r2.isOpen();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0005;
    L_0x0031:
        r2.close();	 Catch:{ all -> 0x0035 }
        goto L_0x0005;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0038:
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r9.<init>();	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0 = "SELECT feature, nb, loc FROM ";
        r9.append(r0);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0 = "hist";
        r0 = r9.append(r0);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r2 = r12.b;	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0.append(r2);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r4 = com.e.br.a();	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r10 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r4 = r4 - r10;
        r0 = " WHERE time > ";
        r0 = r9.append(r0);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0.append(r4);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0 = " ORDER BY time ASC";
        r0 = r9.append(r0);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r2 = ";";
        r0.append(r2);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0 = r9.toString();	 Catch:{ Throwable -> 0x019b, all -> 0x0305 }
        r2 = 0;
        r1 = r7.rawQuery(r0, r2);	 Catch:{ Throwable -> 0x019b, all -> 0x0305 }
        r6 = r1;
    L_0x0073:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r12.c;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 != 0) goto L_0x0088;
    L_0x007c:
        r0 = "MD5";
        r1 = r13.getPackageName();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.bb.a(r0, r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r12.c = r0;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x0088:
        if (r6 == 0) goto L_0x0189;
    L_0x008a:
        r0 = r6.moveToFirst();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x0189;
    L_0x0090:
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "{";
        r0 = r0.startsWith(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x01f2;
    L_0x009d:
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 0;
        r4 = r2.length();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.delete(r0, r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 != 0) goto L_0x01b8;
    L_0x00ba:
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x00c2:
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = 2;
        r4 = r6.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.<init>(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "type";
        r4 = com.e.br.a(r0, r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r4 == 0) goto L_0x00db;
    L_0x00d4:
        r4 = "type";
        r5 = "new";
        r0.put(r4, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x00db:
        r8 = r3 + 1;
        r3 = new com.autonavi.aps.amapapi.model.AmapLoc;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = "";
        r0 = "mmac";
        r0 = com.e.br.a(r1, r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x02a9;
    L_0x00ec:
        r0 = "cgi";
        r0 = com.e.br.a(r1, r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x02a9;
    L_0x00f4:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "cgi";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "network#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "cgi";
        r1 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "#";
        r1 = r1.contains(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r1 == 0) goto L_0x0294;
    L_0x012e:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "cgiwifi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x0141:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "&";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = r3.isOffset();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "&";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = r3.isReversegeo();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.be.a();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = 0;
        r4 = r13;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x0173:
        r0 = r6.moveToNext();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 != 0) goto L_0x0316;
    L_0x0179:
        r0 = 0;
        r1 = r2.length();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.delete(r0, r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 0;
        r1 = r9.length();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r9.delete(r0, r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x0189:
        if (r6 == 0) goto L_0x018e;
    L_0x018b:
        r6.close();	 Catch:{ all -> 0x0035 }
    L_0x018e:
        if (r7 == 0) goto L_0x0005;
    L_0x0190:
        r0 = r7.isOpen();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0005;
    L_0x0196:
        r7.close();	 Catch:{ all -> 0x0035 }
        goto L_0x0005;
    L_0x019b:
        r0 = move-exception;
        r2 = "DB";
        r4 = "fetchHist";
        com.e.bc.a(r0, r2, r4);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        if (r2 != 0) goto L_0x01b5;
    L_0x01ad:
        r2 = "no such table";
        r0 = r0.contains(r2);	 Catch:{ Throwable -> 0x0312, all -> 0x0305 }
        if (r0 == 0) goto L_0x01b5;
    L_0x01b5:
        r6 = r1;
        goto L_0x0073;
    L_0x01b8:
        r0 = "mmac";
        r0 = com.e.br.a(r1, r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x00c2;
    L_0x01c0:
        r0 = "#";
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "mmac";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = ",access";
        r2.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        goto L_0x00c2;
    L_0x01d6:
        r0 = move-exception;
        r1 = r6;
        r2 = r7;
    L_0x01d9:
        r3 = "DB";
        r4 = "fetchHist p2";
        com.e.bc.a(r0, r3, r4);	 Catch:{ all -> 0x0309 }
        if (r1 == 0) goto L_0x01e5;
    L_0x01e2:
        r1.close();	 Catch:{ all -> 0x0035 }
    L_0x01e5:
        if (r2 == 0) goto L_0x0005;
    L_0x01e7:
        r0 = r2.isOpen();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0005;
    L_0x01ed:
        r2.close();	 Catch:{ all -> 0x0035 }
        goto L_0x0005;
    L_0x01f2:
        r0 = 0;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.cy.b(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = new java.lang.String;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = r12.c;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.bb.d(r0, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = "UTF-8";
        r4.<init>(r0, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 0;
        r4 = r2.length();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.delete(r0, r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 != 0) goto L_0x0277;
    L_0x0220:
        r0 = 1;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.cy.b(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = new java.lang.String;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = r12.c;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = com.e.bb.d(r0, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = "UTF-8";
        r4.<init>(r0, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r2.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
    L_0x0239:
        r0 = 2;
        r0 = r6.getString(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = com.e.cy.b(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r5 = new java.lang.String;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r8 = r12.c;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = com.e.bb.d(r4, r8);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r8 = "UTF-8";
        r5.<init>(r4, r8);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.<init>(r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "type";
        r4 = com.e.br.a(r0, r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r4 == 0) goto L_0x00db;
    L_0x025c:
        r4 = "type";
        r5 = "new";
        r0.put(r4, r5);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        goto L_0x00db;
    L_0x0265:
        r0 = move-exception;
    L_0x0266:
        if (r6 == 0) goto L_0x026b;
    L_0x0268:
        r6.close();	 Catch:{ all -> 0x0035 }
    L_0x026b:
        if (r7 == 0) goto L_0x0276;
    L_0x026d:
        r1 = r7.isOpen();	 Catch:{ all -> 0x0035 }
        if (r1 == 0) goto L_0x0276;
    L_0x0273:
        r7.close();	 Catch:{ all -> 0x0035 }
    L_0x0276:
        throw r0;	 Catch:{ all -> 0x0035 }
    L_0x0277:
        r0 = "mmac";
        r0 = com.e.br.a(r1, r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x0239;
    L_0x027f:
        r0 = "#";
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "mmac";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = ",access";
        r2.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        goto L_0x0239;
    L_0x0294:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "wifi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        goto L_0x0141;
    L_0x02a9:
        r0 = "cgi";
        r0 = com.e.br.a(r1, r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r0 == 0) goto L_0x0173;
    L_0x02b1:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "cgi";
        r4 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "network#";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "cgi";
        r1 = r1.getString(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r4 = "#";
        r1 = r1.contains(r4);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        if (r1 == 0) goto L_0x0173;
    L_0x02eb:
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1.<init>();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r1 = "cgi";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01d6, all -> 0x0265 }
        goto L_0x0141;
    L_0x0300:
        r0 = move-exception;
        r6 = r1;
        r7 = r1;
        goto L_0x0266;
    L_0x0305:
        r0 = move-exception;
        r6 = r1;
        goto L_0x0266;
    L_0x0309:
        r0 = move-exception;
        r6 = r1;
        r7 = r2;
        goto L_0x0266;
    L_0x030e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x01d9;
    L_0x0312:
        r0 = move-exception;
        r2 = r7;
        goto L_0x01d9;
    L_0x0316:
        r3 = r8;
        goto L_0x0090;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bf.a(android.content.Context):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r9, int r10) throws java.lang.Exception {
        /*
        r8 = this;
        r2 = 0;
        monitor-enter(r8);
        if (r9 != 0) goto L_0x0006;
    L_0x0004:
        monitor-exit(r8);
        return;
    L_0x0006:
        r0 = "hmdb";
        r1 = 0;
        r3 = 0;
        r1 = r9.openOrCreateDatabase(r0, r1, r3);	 Catch:{ Throwable -> 0x00b0, all -> 0x00a0 }
        r0 = "hist";
        r0 = r8.a(r1, r0);	 Catch:{ Throwable -> 0x006e }
        if (r0 != 0) goto L_0x0031;
    L_0x0016:
        if (r1 == 0) goto L_0x0021;
    L_0x0018:
        r0 = r1.isOpen();	 Catch:{ Throwable -> 0x006e }
        if (r0 == 0) goto L_0x0021;
    L_0x001e:
        r1.close();	 Catch:{ Throwable -> 0x006e }
    L_0x0021:
        r0 = 0;
        if (r2 == 0) goto L_0x0004;
    L_0x0024:
        r1 = r0.isOpen();	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0004;
    L_0x002a:
        r0.close();	 Catch:{ all -> 0x002e }
        goto L_0x0004;
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0031:
        switch(r10) {
            case 1: goto L_0x0059;
            case 2: goto L_0x0082;
            default: goto L_0x0034;
        };
    L_0x0034:
        r0 = r2;
    L_0x0035:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0085 }
        r3.<init>();	 Catch:{ Throwable -> 0x0085 }
        r4 = "hist";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0085 }
        r4 = r8.b;	 Catch:{ Throwable -> 0x0085 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0085 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0085 }
        r1.delete(r3, r0, r2);	 Catch:{ Throwable -> 0x0085 }
    L_0x004d:
        if (r1 == 0) goto L_0x0004;
    L_0x004f:
        r0 = r1.isOpen();	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x0004;
    L_0x0055:
        r1.close();	 Catch:{ all -> 0x002e }
        goto L_0x0004;
    L_0x0059:
        r0 = "time<?";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Throwable -> 0x006e }
        r3 = 0;
        r4 = com.e.br.a();	 Catch:{ Throwable -> 0x006e }
        r6 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r4 = r4 - r6;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Throwable -> 0x006e }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x006e }
        goto L_0x0035;
    L_0x006e:
        r0 = move-exception;
    L_0x006f:
        r2 = "DB";
        r3 = "clearHist p2";
        com.e.bc.a(r0, r2, r3);	 Catch:{ all -> 0x00ae }
        if (r1 == 0) goto L_0x0004;
    L_0x0078:
        r0 = r1.isOpen();	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x0004;
    L_0x007e:
        r1.close();	 Catch:{ all -> 0x002e }
        goto L_0x0004;
    L_0x0082:
        r0 = "1";
        goto L_0x0035;
    L_0x0085:
        r0 = move-exception;
        r2 = "DB";
        r3 = "clearHist";
        com.e.bc.a(r0, r2, r3);	 Catch:{ Throwable -> 0x006e }
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x006e }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x006e }
        if (r2 != 0) goto L_0x004d;
    L_0x0097:
        r2 = "no such table";
        r0 = r0.contains(r2);	 Catch:{ Throwable -> 0x006e }
        if (r0 == 0) goto L_0x004d;
    L_0x009f:
        goto L_0x004d;
    L_0x00a0:
        r0 = move-exception;
        r1 = r2;
    L_0x00a2:
        if (r1 == 0) goto L_0x00ad;
    L_0x00a4:
        r2 = r1.isOpen();	 Catch:{ all -> 0x002e }
        if (r2 == 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();	 Catch:{ all -> 0x002e }
    L_0x00ad:
        throw r0;	 Catch:{ all -> 0x002e }
    L_0x00ae:
        r0 = move-exception;
        goto L_0x00a2;
    L_0x00b0:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bf.a(android.content.Context, int):void");
    }

    protected synchronized void a(Context context, String str, String str2, long j) throws Exception {
        SQLiteDatabase openOrCreateDatabase;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || context == null)) {
                String c = br.c(str);
                String c2 = br.c(str2);
                try {
                    int i;
                    ContentValues contentValues;
                    StringBuilder stringBuilder = new StringBuilder();
                    openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                    try {
                        stringBuilder.append("CREATE TABLE IF NOT EXISTS ").append("hm");
                        stringBuilder.append(this.b);
                        stringBuilder.append(" (hash VARCHAR PRIMARY KEY, num INTEGER, extra VARCHAR, time VARCHAR);");
                        openOrCreateDatabase.execSQL(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("SELECT num FROM ").append("hm");
                        stringBuilder.append(this.b);
                        stringBuilder.append(" WHERE hash = '").append(c).append("';");
                        cursor = openOrCreateDatabase.rawQuery(stringBuilder.toString(), null);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            bc.a(th, "DB", "updateHm p2");
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (cursor != null) {
                                cursor.close();
                            }
                            openOrCreateDatabase.close();
                            throw th;
                        }
                    }
                    if (cursor != null) {
                        if (cursor.moveToNext()) {
                            i = cursor.getInt(0);
                            if (i <= 0) {
                                i++;
                                contentValues = new ContentValues();
                                contentValues.put("num", Integer.valueOf(i));
                                contentValues.put("extra", c2);
                                contentValues.put(n.ax, Long.valueOf(j));
                                openOrCreateDatabase.update("hm" + this.b, contentValues, "hash = '" + c + "'", null);
                            } else {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("REPLACE INTO ");
                                stringBuilder.append("hm").append(this.b);
                                stringBuilder.append(" VALUES (?, ?, ?, ?)");
                                openOrCreateDatabase.execSQL(stringBuilder.toString(), new Object[]{c, Integer.valueOf(1), c2, Long.valueOf(j)});
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            stringBuilder.delete(0, stringBuilder.length());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                        }
                    }
                    i = 0;
                    if (i <= 0) {
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("REPLACE INTO ");
                        stringBuilder.append("hm").append(this.b);
                        stringBuilder.append(" VALUES (?, ?, ?, ?)");
                        openOrCreateDatabase.execSQL(stringBuilder.toString(), new Object[]{c, Integer.valueOf(1), c2, Long.valueOf(j)});
                    } else {
                        i++;
                        contentValues = new ContentValues();
                        contentValues.put("num", Integer.valueOf(i));
                        contentValues.put("extra", c2);
                        contentValues.put(n.ax, Long.valueOf(j));
                        openOrCreateDatabase.update("hm" + this.b, contentValues, "hash = '" + c + "'", null);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    stringBuilder.delete(0, stringBuilder.length());
                    if (cursor != null) {
                        cursor.close();
                    }
                    openOrCreateDatabase.close();
                } catch (Throwable th4) {
                    th = th4;
                    openOrCreateDatabase = cursor;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                        openOrCreateDatabase.close();
                    }
                    throw th;
                }
            }
        }
    }

    protected void a(String str, AmapLoc amapLoc, StringBuilder stringBuilder, Context context) throws Exception {
        Throwable th;
        Cursor cursor = null;
        if (context != null) {
            if (this.c == null) {
                this.c = bb.a("MD5", context.getPackageName());
            }
            JSONObject jSONObject = new JSONObject();
            if (str.contains(a.b)) {
                str = str.substring(0, str.indexOf(a.b));
            }
            String substring = str.substring(str.lastIndexOf("#") + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - (("network".length() + 2) + "cgi".length())));
            } else if (!(TextUtils.isEmpty(stringBuilder) || stringBuilder.indexOf("access") == -1)) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + ("network".length() + 2))));
                String[] split = stringBuilder.toString().split(",access");
                jSONObject.put("mmac", split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0]);
            }
            if (br.a(jSONObject, "cgi") || br.a(jSONObject, "mmac")) {
                StringBuilder stringBuilder2 = new StringBuilder();
                SQLiteDatabase openOrCreateDatabase;
                try {
                    openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                    try {
                        stringBuilder2.append("CREATE TABLE IF NOT EXISTS ").append("hist");
                        stringBuilder2.append(this.b);
                        stringBuilder2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
                        openOrCreateDatabase.execSQL(stringBuilder2.toString());
                        stringBuilder2.delete(0, stringBuilder2.length());
                        stringBuilder2.append("REPLACE INTO ");
                        stringBuilder2.append("hist").append(this.b);
                        stringBuilder2.append(" VALUES (?, ?, ?, ?)");
                        Object[] objArr = new Object[]{bb.c(jSONObject.toString().getBytes("UTF-8"), this.c), bb.c(stringBuilder.toString().getBytes("UTF-8"), this.c), bb.c(amapLoc.toStr().getBytes("UTF-8"), this.c), Long.valueOf(amapLoc.getTime())};
                        for (int i = 0; i < objArr.length - 1; i++) {
                            objArr[i] = cy.a((byte[]) objArr[i]);
                        }
                        openOrCreateDatabase.execSQL(stringBuilder2.toString(), objArr);
                        stringBuilder2.delete(0, stringBuilder2.length());
                        stringBuilder2.append("SELECT COUNT(*) AS total FROM ");
                        stringBuilder2.append("hist").append(this.b).append(i.b);
                        cursor = openOrCreateDatabase.rawQuery(stringBuilder2.toString(), null);
                        if (cursor == null || cursor.moveToFirst()) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            stringBuilder2.delete(0, stringBuilder2.length());
                            if (openOrCreateDatabase == null || !openOrCreateDatabase.isOpen()) {
                                return;
                            }
                            openOrCreateDatabase.close();
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        stringBuilder2.delete(0, stringBuilder2.length());
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            bc.a(th, "DB", "updateHist");
                            if (cursor != null) {
                                cursor.close();
                            }
                            stringBuilder2.delete(0, stringBuilder2.length());
                            if (openOrCreateDatabase != null) {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (cursor != null) {
                                cursor.close();
                            }
                            stringBuilder2.delete(0, stringBuilder2.length());
                            if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    openOrCreateDatabase = cursor;
                    if (cursor != null) {
                        cursor.close();
                    }
                    stringBuilder2.delete(0, stringBuilder2.length());
                    openOrCreateDatabase.close();
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<java.lang.String> b(android.content.Context r11, int r12) throws java.lang.Exception {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        if (r11 != 0) goto L_0x0006;
    L_0x0004:
        monitor-exit(r10);
        return r0;
    L_0x0006:
        r1 = 0;
        r2 = "hmdb";
        r3 = 0;
        r4 = 0;
        r3 = r11.openOrCreateDatabase(r2, r3, r4);	 Catch:{ Throwable -> 0x0152, all -> 0x0137 }
        r2 = "hm";
        r2 = r10.a(r3, r2);	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        if (r2 != 0) goto L_0x0037;
    L_0x0017:
        if (r3 == 0) goto L_0x0022;
    L_0x0019:
        r2 = r3.isOpen();	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        if (r2 == 0) goto L_0x0022;
    L_0x001f:
        r3.close();	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
    L_0x0022:
        r2 = 0;
        if (r0 == 0) goto L_0x0028;
    L_0x0025:
        r1.close();	 Catch:{ all -> 0x0034 }
    L_0x0028:
        if (r0 == 0) goto L_0x0004;
    L_0x002a:
        r1 = r2.isOpen();	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0004;
    L_0x0030:
        r2.close();	 Catch:{ all -> 0x0034 }
        goto L_0x0004;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0037:
        r1 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        r1.<init>();	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        switch(r12) {
            case 1: goto L_0x00ce;
            case 2: goto L_0x00ff;
            default: goto L_0x003f;
        };
    L_0x003f:
        r2 = r0;
        r4 = r0;
    L_0x0041:
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r5.<init>();	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r6 = "hm";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r6 = r10.b;	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        if (r2 == 0) goto L_0x0105;
    L_0x0058:
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r4.<init>();	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r6 = "SELECT hash, num, extra FROM ";
        r4.append(r6);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r4.append(r5);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r5 = " WHERE time < ";
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r6 = 0;
        r2 = r2[r6];	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r2 = r5.append(r2);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r5 = ";";
        r2.append(r5);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r2 = r4.toString();	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r4 = 0;
        r2 = r3.rawQuery(r2, r4);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        if (r2 == 0) goto L_0x00aa;
    L_0x0082:
        r4 = r2.moveToFirst();	 Catch:{ Throwable -> 0x0158 }
        if (r4 == 0) goto L_0x00aa;
    L_0x0088:
        r4 = 0;
        r4 = r2.getString(r4);	 Catch:{ Throwable -> 0x0158 }
        r5 = 2;
        r5 = r2.getString(r5);	 Catch:{ Throwable -> 0x0158 }
        r6 = "{";
        r6 = r5.startsWith(r6);	 Catch:{ Throwable -> 0x0158 }
        if (r6 != 0) goto L_0x00a1;
    L_0x009a:
        r4 = com.e.br.d(r4);	 Catch:{ Throwable -> 0x0158 }
        com.e.br.d(r5);	 Catch:{ Throwable -> 0x0158 }
    L_0x00a1:
        r1.add(r4);	 Catch:{ Throwable -> 0x0158 }
        r4 = r2.moveToNext();	 Catch:{ Throwable -> 0x0158 }
        if (r4 != 0) goto L_0x0088;
    L_0x00aa:
        if (r2 == 0) goto L_0x00af;
    L_0x00ac:
        r2.close();	 Catch:{ Throwable -> 0x0158 }
    L_0x00af:
        if (r3 == 0) goto L_0x00ba;
    L_0x00b1:
        r4 = r3.isOpen();	 Catch:{ Throwable -> 0x0158 }
        if (r4 == 0) goto L_0x00ba;
    L_0x00b7:
        r3.close();	 Catch:{ Throwable -> 0x0158 }
    L_0x00ba:
        r3 = 0;
        if (r2 == 0) goto L_0x00c0;
    L_0x00bd:
        r2.close();	 Catch:{ all -> 0x0034 }
    L_0x00c0:
        if (r0 == 0) goto L_0x00cb;
    L_0x00c2:
        r0 = r3.isOpen();	 Catch:{ all -> 0x0034 }
        if (r0 == 0) goto L_0x00cb;
    L_0x00c8:
        r3.close();	 Catch:{ all -> 0x0034 }
    L_0x00cb:
        r0 = r1;
        goto L_0x0004;
    L_0x00ce:
        r4 = "time<?";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        r5 = 0;
        r6 = com.e.br.a();	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        r8 = 1209600000; // 0x48190800 float:156704.0 double:5.97621805E-315;
        r6 = r6 - r8;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        r2[r5] = r6;	 Catch:{ Throwable -> 0x00e4, all -> 0x014c }
        goto L_0x0041;
    L_0x00e4:
        r1 = move-exception;
        r2 = r0;
    L_0x00e6:
        r4 = "DB";
        r5 = "clearHm p2";
        com.e.bc.a(r1, r4, r5);	 Catch:{ all -> 0x0150 }
        if (r2 == 0) goto L_0x00f2;
    L_0x00ef:
        r2.close();	 Catch:{ all -> 0x0034 }
    L_0x00f2:
        if (r3 == 0) goto L_0x0004;
    L_0x00f4:
        r1 = r3.isOpen();	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0004;
    L_0x00fa:
        r3.close();	 Catch:{ all -> 0x0034 }
        goto L_0x0004;
    L_0x00ff:
        r2 = "1";
        r4 = r2;
        r2 = r0;
        goto L_0x0041;
    L_0x0105:
        r3.delete(r5, r4, r2);	 Catch:{ Throwable -> 0x011b, all -> 0x014c }
        r2 = r0;
    L_0x0109:
        if (r2 == 0) goto L_0x010e;
    L_0x010b:
        r2.close();	 Catch:{ all -> 0x0034 }
    L_0x010e:
        if (r3 == 0) goto L_0x0004;
    L_0x0110:
        r1 = r3.isOpen();	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0004;
    L_0x0116:
        r3.close();	 Catch:{ all -> 0x0034 }
        goto L_0x0004;
    L_0x011b:
        r1 = move-exception;
        r2 = r0;
    L_0x011d:
        r4 = "DB";
        r5 = "clearHm";
        com.e.bc.a(r1, r4, r5);	 Catch:{ Throwable -> 0x0156 }
        r1 = r1.getMessage();	 Catch:{ Throwable -> 0x0156 }
        r4 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x0156 }
        if (r4 != 0) goto L_0x0109;
    L_0x012e:
        r4 = "no such table";
        r1 = r1.contains(r4);	 Catch:{ Throwable -> 0x0156 }
        if (r1 == 0) goto L_0x0109;
    L_0x0136:
        goto L_0x0109;
    L_0x0137:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x013b:
        if (r2 == 0) goto L_0x0140;
    L_0x013d:
        r2.close();	 Catch:{ all -> 0x0034 }
    L_0x0140:
        if (r3 == 0) goto L_0x014b;
    L_0x0142:
        r1 = r3.isOpen();	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x014b;
    L_0x0148:
        r3.close();	 Catch:{ all -> 0x0034 }
    L_0x014b:
        throw r0;	 Catch:{ all -> 0x0034 }
    L_0x014c:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x013b;
    L_0x0150:
        r0 = move-exception;
        goto L_0x013b;
    L_0x0152:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x00e6;
    L_0x0156:
        r1 = move-exception;
        goto L_0x00e6;
    L_0x0158:
        r1 = move-exception;
        goto L_0x011d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bf.b(android.content.Context, int):java.util.ArrayList<java.lang.String>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized void b(android.content.Context r12) throws java.lang.Exception {
        /*
        r11 = this;
        r1 = 0;
        r2 = 0;
        monitor-enter(r11);
        r0 = com.e.at.a;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        if (r12 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r11);
        return;
    L_0x000b:
        r0 = 0;
        r3 = "hmdb";
        r4 = 0;
        r5 = 0;
        r10 = r12.openOrCreateDatabase(r3, r4, r5);	 Catch:{ Throwable -> 0x00f0, all -> 0x010b }
        r3 = "hm";
        r3 = r11.a(r10, r3);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        if (r3 != 0) goto L_0x003c;
    L_0x001c:
        if (r10 == 0) goto L_0x0027;
    L_0x001e:
        r2 = r10.isOpen();	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r10.close();	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
    L_0x0027:
        r2 = 0;
        if (r1 == 0) goto L_0x002d;
    L_0x002a:
        r0.close();	 Catch:{ all -> 0x0039 }
    L_0x002d:
        if (r1 == 0) goto L_0x0009;
    L_0x002f:
        r0 = r2.isOpen();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0009;
    L_0x0035:
        r2.close();	 Catch:{ all -> 0x0039 }
        goto L_0x0009;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x003c:
        r4 = com.e.br.a();	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r6 = 1209600000; // 0x48190800 float:156704.0 double:5.97621805E-315;
        r4 = r4 - r6;
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r3.<init>();	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = "SELECT hash, num, extra, time FROM ";
        r3.append(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = "hm";
        r0 = r3.append(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r6 = r11.b;	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0.append(r6);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = " WHERE time > ";
        r0 = r3.append(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0.append(r4);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = " ORDER BY num DESC LIMIT 0,";
        r3.append(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r0 = r3.append(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r4 = ";";
        r0.append(r4);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00d4, all -> 0x011e }
        r4 = 0;
        r1 = r10.rawQuery(r0, r4);	 Catch:{ Throwable -> 0x00d4, all -> 0x011e }
        r9 = r1;
    L_0x007c:
        r0 = 0;
        r1 = r3.length();	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r3.delete(r0, r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        if (r9 == 0) goto L_0x00c2;
    L_0x0086:
        r0 = r9.moveToFirst();	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        if (r0 == 0) goto L_0x00c2;
    L_0x008c:
        r0 = r2;
    L_0x008d:
        r0 = r0 + 1;
        r1 = 0;
        r3 = r9.getString(r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r1 = 1;
        r5 = r9.getInt(r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r1 = 2;
        r4 = r9.getString(r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r1 = 3;
        r6 = r9.getLong(r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r1 = "{";
        r1 = r4.startsWith(r1);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        if (r1 != 0) goto L_0x00b3;
    L_0x00ab:
        r3 = com.e.br.d(r3);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r4 = com.e.br.d(r4);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
    L_0x00b3:
        r1 = com.e.bh.a();	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r8 = 0;
        r2 = r12;
        r1.a(r2, r3, r4, r5, r6, r8);	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        r1 = r9.moveToNext();	 Catch:{ Throwable -> 0x0129, all -> 0x0120 }
        if (r1 != 0) goto L_0x008d;
    L_0x00c2:
        if (r9 == 0) goto L_0x00c7;
    L_0x00c4:
        r9.close();	 Catch:{ all -> 0x0039 }
    L_0x00c7:
        if (r10 == 0) goto L_0x0009;
    L_0x00c9:
        r0 = r10.isOpen();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0009;
    L_0x00cf:
        r10.close();	 Catch:{ all -> 0x0039 }
        goto L_0x0009;
    L_0x00d4:
        r0 = move-exception;
        r4 = "DB";
        r5 = "fetchHm";
        com.e.bc.a(r0, r4, r5);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r0 = r0.getMessage();	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        if (r4 != 0) goto L_0x00ee;
    L_0x00e6:
        r4 = "no such table";
        r0 = r0.contains(r4);	 Catch:{ Throwable -> 0x0126, all -> 0x011e }
        if (r0 == 0) goto L_0x00ee;
    L_0x00ee:
        r9 = r1;
        goto L_0x007c;
    L_0x00f0:
        r0 = move-exception;
        r2 = r1;
    L_0x00f2:
        r3 = "DB";
        r4 = "fetchHm p2";
        com.e.bc.a(r0, r3, r4);	 Catch:{ all -> 0x0123 }
        if (r1 == 0) goto L_0x00fe;
    L_0x00fb:
        r1.close();	 Catch:{ all -> 0x0039 }
    L_0x00fe:
        if (r2 == 0) goto L_0x0009;
    L_0x0100:
        r0 = r2.isOpen();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0009;
    L_0x0106:
        r2.close();	 Catch:{ all -> 0x0039 }
        goto L_0x0009;
    L_0x010b:
        r0 = move-exception;
        r10 = r1;
    L_0x010d:
        if (r1 == 0) goto L_0x0112;
    L_0x010f:
        r1.close();	 Catch:{ all -> 0x0039 }
    L_0x0112:
        if (r10 == 0) goto L_0x011d;
    L_0x0114:
        r1 = r10.isOpen();	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x011d;
    L_0x011a:
        r10.close();	 Catch:{ all -> 0x0039 }
    L_0x011d:
        throw r0;	 Catch:{ all -> 0x0039 }
    L_0x011e:
        r0 = move-exception;
        goto L_0x010d;
    L_0x0120:
        r0 = move-exception;
        r1 = r9;
        goto L_0x010d;
    L_0x0123:
        r0 = move-exception;
        r10 = r2;
        goto L_0x010d;
    L_0x0126:
        r0 = move-exception;
        r2 = r10;
        goto L_0x00f2;
    L_0x0129:
        r0 = move-exception;
        r1 = r9;
        r2 = r10;
        goto L_0x00f2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bf.b(android.content.Context):void");
    }
}
