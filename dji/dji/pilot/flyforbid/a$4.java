package dji.pilot.flyforbid;

class a$4 implements Runnable {
    final /* synthetic */ double a;
    final /* synthetic */ double b;
    final /* synthetic */ a c;

    a$4(a aVar, double d, double d2) {
        this.c = aVar;
        this.a = d;
        this.b = d2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r13 = this;
        r12 = 65530; // 0xfffa float:9.1827E-41 double:3.2376E-319;
        r10 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r9 = 2;
        r8 = 1;
        r3 = 0;
        r0 = dji.midware.data.forbid.DJIFlyForbidController.getInstance();
        r0.setIsCheckingData(r8);
        r0 = "onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 3";
        dji.midware.data.forbid.NFZLogUtil.LOGD(r0);
        r0 = dji.midware.data.forbid.DJIFlyForbidController.getInstance();
        r4 = r13.a;
        r6 = r13.b;
        r0 = r0.checkNearFlyForbidArea(r4, r6);
        r1 = dji.midware.data.forbid.DJIFlyForbidController.getInstance();
        r1.setIsCheckingData(r3);
        if (r0 != 0) goto L_0x020d;
    L_0x002c:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r0;
    L_0x0032:
        r0 = r13.c;
        r0.e(r8);
        r0 = r13.c;
        r2 = dji.midware.data.forbid.FlyForbidProtocol.DJIWarningAreaState.None;
        dji.pilot.flyforbid.a.a(r0, r2);
        if (r1 == 0) goto L_0x020a;
    L_0x0040:
        r0 = r1.size();
        r2 = r0;
    L_0x0045:
        r5 = r3;
        r4 = r3;
    L_0x0047:
        if (r5 >= r2) goto L_0x0070;
    L_0x0049:
        r0 = r1.get(r5);
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;
        r0 = r0.level;
        r6 = dji.midware.data.forbid.FlyForbidProtocol.LevelType.WARNING;
        r6 = r6.value();
        if (r0 == r6) goto L_0x0207;
    L_0x0059:
        r0 = r1.get(r5);
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;
        r0 = r0.level;
        r6 = dji.midware.data.forbid.FlyForbidProtocol.LevelType.STRONG_WARNING;
        r6 = r6.value();
        if (r0 == r6) goto L_0x0207;
    L_0x0069:
        r0 = r4 + 1;
    L_0x006b:
        r4 = r5 + 1;
        r5 = r4;
        r4 = r0;
        goto L_0x0047;
    L_0x0070:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlycVersion();
        r2 = r0 & 255;
        r0 = r13.c;
        r5 = dji.pilot.flyforbid.a.f(r0);
        monitor-enter(r5);
        if (r4 > 0) goto L_0x008d;
    L_0x0083:
        r0 = dji.midware.data.forbid.DJIFlyForbidController.getInstance();	 Catch:{ all -> 0x0204 }
        r0 = r0.needPushLimitData();	 Catch:{ all -> 0x0204 }
        if (r0 == 0) goto L_0x0177;
    L_0x008d:
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0.clear();	 Catch:{ all -> 0x0204 }
        r0 = "onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 5";
        dji.midware.data.forbid.NFZLogUtil.LOGD(r0);	 Catch:{ all -> 0x0204 }
    L_0x009b:
        r0 = r1.size();	 Catch:{ all -> 0x0204 }
        if (r3 >= r0) goto L_0x0177;
    L_0x00a1:
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.level;	 Catch:{ all -> 0x0204 }
        r4 = dji.midware.data.forbid.FlyForbidProtocol.LevelType.WARNING;	 Catch:{ all -> 0x0204 }
        r4 = r4.value();	 Catch:{ all -> 0x0204 }
        if (r0 == r4) goto L_0x0144;
    L_0x00b1:
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.level;	 Catch:{ all -> 0x0204 }
        r4 = dji.midware.data.forbid.FlyForbidProtocol.LevelType.STRONG_WARNING;	 Catch:{ all -> 0x0204 }
        r4 = r4.value();	 Catch:{ all -> 0x0204 }
        if (r0 == r4) goto L_0x0144;
    L_0x00c1:
        r4 = new dji.midware.data.forbid.DJISetFlyForbidAreaModel;	 Catch:{ all -> 0x0204 }
        r4.<init>();	 Catch:{ all -> 0x0204 }
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r6 = r0.lat;	 Catch:{ all -> 0x0204 }
        r6 = r6 * r10;
        r0 = (int) r6;	 Catch:{ all -> 0x0204 }
        r4.latitude = r0;	 Catch:{ all -> 0x0204 }
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r6 = r0.lng;	 Catch:{ all -> 0x0204 }
        r6 = r6 * r10;
        r0 = (int) r6;	 Catch:{ all -> 0x0204 }
        r4.longitude = r0;	 Catch:{ all -> 0x0204 }
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.radius;	 Catch:{ all -> 0x0204 }
        r4.radius = r0;	 Catch:{ all -> 0x0204 }
        r0 = r4.radius;	 Catch:{ all -> 0x0204 }
        if (r0 <= r12) goto L_0x00f1;
    L_0x00ec:
        r0 = 65530; // 0xfffa float:9.1827E-41 double:3.2376E-319;
        r4.radius = r0;	 Catch:{ all -> 0x0204 }
    L_0x00f1:
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.country;	 Catch:{ all -> 0x0204 }
        r4.contryCode = r0;	 Catch:{ all -> 0x0204 }
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.type;	 Catch:{ all -> 0x0204 }
        r4.type = r0;	 Catch:{ all -> 0x0204 }
        r0 = 9;
        if (r2 >= r0) goto L_0x0110;
    L_0x0109:
        r0 = r4.type;	 Catch:{ all -> 0x0204 }
        if (r0 <= r9) goto L_0x0110;
    L_0x010d:
        r0 = 2;
        r4.type = r0;	 Catch:{ all -> 0x0204 }
    L_0x0110:
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.area_id;	 Catch:{ all -> 0x0204 }
        r4.id = r0;	 Catch:{ all -> 0x0204 }
        r0 = r4.type;	 Catch:{ all -> 0x0204 }
        r6 = 29;
        if (r0 != r6) goto L_0x0148;
    L_0x0120:
        r0 = r1.get(r3);	 Catch:{ all -> 0x0204 }
        r0 = (dji.midware.data.forbid.FlyForbidElement) r0;	 Catch:{ all -> 0x0204 }
        r0 = r0.level;	 Catch:{ all -> 0x0204 }
        if (r0 != r8) goto L_0x0148;
    L_0x012a:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();	 Catch:{ all -> 0x0204 }
        r0 = r0.isMotorUp();	 Catch:{ all -> 0x0204 }
        if (r0 == 0) goto L_0x0148;
    L_0x0134:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();	 Catch:{ all -> 0x0204 }
        r0 = r0.groundOrSky();	 Catch:{ all -> 0x0204 }
        if (r0 != r9) goto L_0x0148;
    L_0x013e:
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r4 = 1;
        dji.pilot.flyforbid.a.e(r0, r4);	 Catch:{ all -> 0x0204 }
    L_0x0144:
        r3 = r3 + 1;
        goto L_0x009b;
    L_0x0148:
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0.add(r4);	 Catch:{ all -> 0x0204 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0204 }
        r0.<init>();	 Catch:{ all -> 0x0204 }
        r6 = "";
        r0 = r0.append(r6);	 Catch:{ all -> 0x0204 }
        r4 = r4.id;	 Catch:{ all -> 0x0204 }
        r0 = r0.append(r4);	 Catch:{ all -> 0x0204 }
        r0 = r0.toString();	 Catch:{ all -> 0x0204 }
        dji.midware.data.forbid.NFZLogUtil.LOGD(r0);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0 = r0.size();	 Catch:{ all -> 0x0204 }
        r4 = 100;
        if (r0 < r4) goto L_0x0144;
    L_0x0177:
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0 = r0.size();	 Catch:{ all -> 0x0204 }
        if (r0 <= 0) goto L_0x01b6;
    L_0x0183:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0204 }
        r0.<init>();	 Catch:{ all -> 0x0204 }
        r1 = "onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 7: ";
        r0 = r0.append(r1);	 Catch:{ all -> 0x0204 }
        r1 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = dji.pilot.flyforbid.a.f(r1);	 Catch:{ all -> 0x0204 }
        r1 = r1.size();	 Catch:{ all -> 0x0204 }
        r0 = r0.append(r1);	 Catch:{ all -> 0x0204 }
        r0 = r0.toString();	 Catch:{ all -> 0x0204 }
        dji.midware.data.forbid.NFZLogUtil.LOGD(r0);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = 0;
        dji.pilot.flyforbid.a.a(r0, r1);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = dji.pilot.flyforbid.a.g(r1);	 Catch:{ all -> 0x0204 }
        dji.pilot.flyforbid.a.b(r0, r1);	 Catch:{ all -> 0x0204 }
    L_0x01b4:
        monitor-exit(r5);	 Catch:{ all -> 0x0204 }
        return;
    L_0x01b6:
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0 = r0.size();	 Catch:{ all -> 0x0204 }
        if (r0 != 0) goto L_0x01b4;
    L_0x01c2:
        r0 = "onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 8";
        dji.midware.data.forbid.NFZLogUtil.LOGD(r0);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r0 = dji.pilot.flyforbid.a.f(r0);	 Catch:{ all -> 0x0204 }
        r0.clear();	 Catch:{ all -> 0x0204 }
        r0 = new dji.midware.data.forbid.DJISetFlyForbidAreaModel;	 Catch:{ all -> 0x0204 }
        r0.<init>();	 Catch:{ all -> 0x0204 }
        r1 = 0;
        r0.latitude = r1;	 Catch:{ all -> 0x0204 }
        r1 = 0;
        r0.longitude = r1;	 Catch:{ all -> 0x0204 }
        r1 = 1;
        r0.radius = r1;	 Catch:{ all -> 0x0204 }
        r1 = 804; // 0x324 float:1.127E-42 double:3.97E-321;
        r0.contryCode = r1;	 Catch:{ all -> 0x0204 }
        r1 = 1;
        r0.type = r1;	 Catch:{ all -> 0x0204 }
        r1 = 10086; // 0x2766 float:1.4133E-41 double:4.983E-320;
        r0.id = r1;	 Catch:{ all -> 0x0204 }
        r1 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = dji.pilot.flyforbid.a.f(r1);	 Catch:{ all -> 0x0204 }
        r1.add(r0);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = 0;
        dji.pilot.flyforbid.a.a(r0, r1);	 Catch:{ all -> 0x0204 }
        r0 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = r13.c;	 Catch:{ all -> 0x0204 }
        r1 = dji.pilot.flyforbid.a.g(r1);	 Catch:{ all -> 0x0204 }
        dji.pilot.flyforbid.a.b(r0, r1);	 Catch:{ all -> 0x0204 }
        goto L_0x01b4;
    L_0x0204:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0204 }
        throw r0;
    L_0x0207:
        r0 = r4;
        goto L_0x006b;
    L_0x020a:
        r2 = r3;
        goto L_0x0045;
    L_0x020d:
        r1 = r0;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.flyforbid.a$4.run():void");
    }
}
