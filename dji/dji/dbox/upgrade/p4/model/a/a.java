package dji.dbox.upgrade.p4.model.a;

public class a {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static dji.dbox.upgrade.p4.model.DJIUpCfgModel a(java.io.File r9) throws java.io.FileNotFoundException {
        /*
        r1 = 0;
        r4 = new java.io.FileInputStream;
        r4.<init>(r9);
        r5 = android.util.Xml.newPullParser();
        r0 = "UTF-8";
        r5.setInput(r4, r0);	 Catch:{ Exception -> 0x00f6 }
        r0 = r5.getEventType();	 Catch:{ Exception -> 0x00f6 }
        r2 = r1;
        r3 = r0;
        r0 = r1;
    L_0x0016:
        r6 = 1;
        if (r3 == r6) goto L_0x00f2;
    L_0x0019:
        switch(r3) {
            case 0: goto L_0x0027;
            case 1: goto L_0x001c;
            case 2: goto L_0x0030;
            default: goto L_0x001c;
        };	 Catch:{ Exception -> 0x00f6 }
    L_0x001c:
        r8 = r2;
        r2 = r0;
        r0 = r8;
    L_0x001f:
        r3 = r5.next();	 Catch:{ Exception -> 0x00f6 }
        r8 = r0;
        r0 = r2;
        r2 = r8;
        goto L_0x0016;
    L_0x0027:
        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00f6 }
        r2.<init>();	 Catch:{ Exception -> 0x00f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x0030:
        r3 = r5.getName();	 Catch:{ Exception -> 0x00f6 }
        r6 = "device";
        r6 = r3.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x00f6 }
        if (r6 == 0) goto L_0x004e;
    L_0x003c:
        r0 = new dji.dbox.upgrade.p4.model.DJIUpCfgModel;	 Catch:{ Exception -> 0x00f6 }
        r0.<init>();	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r6 = "id";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r0.a = r3;	 Catch:{ Exception -> 0x00f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x004e:
        if (r0 == 0) goto L_0x001c;
    L_0x0050:
        r6 = "firmware";
        r6 = r3.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x00f6 }
        if (r6 == 0) goto L_0x005c;
    L_0x0058:
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x005c:
        r6 = "release";
        r6 = r3.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x00f6 }
        if (r6 == 0) goto L_0x009d;
    L_0x0064:
        r3 = 0;
        r6 = "version";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r0.b = r3;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r6 = "antirollback";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x00f6 }
        r0.d = r3;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r6 = "enforce";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x00f6 }
        r0.c = r3;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r6 = "from";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r0.e = r3;	 Catch:{ Exception -> 0x00f6 }
        r3 = 0;
        r6 = "expire";
        r3 = r5.getAttributeValue(r3, r6);	 Catch:{ Exception -> 0x00f6 }
        r0.f = r3;	 Catch:{ Exception -> 0x00f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x009d:
        r6 = "module";
        r3 = r3.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x00f6 }
        if (r3 == 0) goto L_0x001c;
    L_0x00a5:
        r3 = r0.h;	 Catch:{ Exception -> 0x00f6 }
        if (r3 != 0) goto L_0x00ab;
    L_0x00a9:
        r0.h = r2;	 Catch:{ Exception -> 0x00f6 }
    L_0x00ab:
        r3 = new dji.dbox.upgrade.p4.model.DJIUpCfgModel$a;	 Catch:{ Exception -> 0x00f6 }
        r3.<init>();	 Catch:{ Exception -> 0x00f6 }
        r6 = 0;
        r7 = "id";
        r6 = r5.getAttributeValue(r6, r7);	 Catch:{ Exception -> 0x00f6 }
        r3.a(r6);	 Catch:{ Exception -> 0x00f6 }
        r6 = 0;
        r7 = "version";
        r6 = r5.getAttributeValue(r6, r7);	 Catch:{ Exception -> 0x00f6 }
        r3.b = r6;	 Catch:{ Exception -> 0x00f6 }
        r6 = 0;
        r7 = "size";
        r6 = r5.getAttributeValue(r6, r7);	 Catch:{ Exception -> 0x00f6 }
        r6 = java.lang.Integer.parseInt(r6);	 Catch:{ Exception -> 0x00f6 }
        r3.h = r6;	 Catch:{ Exception -> 0x00f6 }
        r6 = 0;
        r7 = "group";
        r6 = r5.getAttributeValue(r6, r7);	 Catch:{ Exception -> 0x00f6 }
        r6 = r3.b(r6);	 Catch:{ Exception -> 0x00f6 }
        r3.i = r6;	 Catch:{ Exception -> 0x00f6 }
        r6 = r0.g;	 Catch:{ Exception -> 0x00f6 }
        r7 = r3.h;	 Catch:{ Exception -> 0x00f6 }
        r6 = r6 + r7;
        r0.g = r6;	 Catch:{ Exception -> 0x00f6 }
        r6 = r5.nextText();	 Catch:{ Exception -> 0x00f6 }
        r3.c = r6;	 Catch:{ Exception -> 0x00f6 }
        r2.add(r3);	 Catch:{ Exception -> 0x00f6 }
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x001f;
    L_0x00f2:
        r4.close();	 Catch:{ Exception -> 0x00f6 }
    L_0x00f5:
        return r0;
    L_0x00f6:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x00f5;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.dbox.upgrade.p4.model.a.a.a(java.io.File):dji.dbox.upgrade.p4.model.DJIUpCfgModel");
    }
}
