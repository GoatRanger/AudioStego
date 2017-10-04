package com.e;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.APSServiceBase;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.common.Constants;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import org.json.JSONObject;

public class ab implements APSServiceBase {
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private long H = 0;
    private long I = 0;
    private aq J = null;
    private boolean K = true;
    private String L = "";
    Context a;
    boolean b = false;
    Messenger c = null;
    String d = null;
    Messenger e;
    b f = new b(this, this);
    a g = null;
    Vector<Messenger> h = new Vector();
    Vector<Messenger> i = new Vector();
    volatile boolean j = false;
    boolean k = false;
    Object l = new Object();
    AmapLoc m;
    long n = br.b();
    long o = 0;
    JSONObject p = new JSONObject();
    AmapLoc q;
    ServerSocket r = null;
    boolean s = false;
    Socket t = null;
    boolean u = false;
    c v;
    private volatile boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private int z = 0;

    class a extends Thread {
        final /* synthetic */ ab a;

        a(ab abVar) {
            this.a = abVar;
        }

        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00ef in list [B:42:0x00ea]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
            /*
            r8 = this;
            r7 = 0;
        L_0x0001:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.j;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x019b;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0007:
            r1 = 0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.k;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x0181;
        L_0x000e:
            r0 = r8.a;	 Catch:{ Throwable -> 0x00c1, RemoteException -> 0x00da, InterruptedException -> 0x010a }
            r0.i();	 Catch:{ Throwable -> 0x00c1, RemoteException -> 0x00da, InterruptedException -> 0x010a }
        L_0x0013:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.K;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x0120;
        L_0x001b:
            r0 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r2 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r2 = r2.f();	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0.m = r2;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r0.J;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            if (r0 == 0) goto L_0x0051;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
        L_0x002d:
            r0 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r0.m;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r2 = r0.getTime();	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r4 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r4 = r4.J;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r5 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r5 = r5.m;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r6 = 0;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r4 = r4.a(r5, r6);	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0.m = r4;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r0.m;	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0.setTime(r2);	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
        L_0x0051:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = r0.l;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            monitor-enter(r3);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.m;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x006e;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x005c:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.m;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.getErrorCode();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 != 0) goto L_0x006e;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0066:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = com.e.br.b();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.n = r4;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x006e:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = 0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.k = r2;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.h;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x01aa;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0079:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.h;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.size();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 <= 0) goto L_0x01aa;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0083:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.h;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.size();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = r0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x008c:
            if (r7 >= r2) goto L_0x014a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x008e:
            r1 = android.os.Message.obtain();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = new android.os.Bundle;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.<init>();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = "location";	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r5 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r5 = r5.m;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.putParcelable(r4, r5);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.setData(r0);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = 1;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.what = r0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.h;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = 0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.get(r4);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = (android.os.Messenger) r0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.send(r1);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r1.h;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = 0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.remove(r4);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r2 + -1;
            r2 = r1;
            r1 = r0;
            goto L_0x008c;
        L_0x00c1:
            r0 = move-exception;
            r2 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = r0.getMessage();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2.L = r3;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = 0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2.K = r3;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = "APSServiceCore";	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = "run part1";	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            com.e.bc.a(r0, r2, r3);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            goto L_0x0013;
        L_0x00da:
            r0 = move-exception;
            r1 = "APSServiceCore";	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r2 = "run part3";	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            com.e.bc.a(r0, r1, r2);	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;
            r0 = r0.e();
            if (r0 != 0) goto L_0x00ef;
        L_0x00ea:
            r0 = r8.a;
            r0.k();
        L_0x00ef:
            return;
        L_0x00f0:
            r0 = move-exception;
            r2 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = 8;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r5 = r0.getMessage();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = r3.a(r4, r5);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2.m = r3;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = "APSServiceCore";	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = "run part2";	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            com.e.bc.a(r0, r2, r3);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            goto L_0x0051;
        L_0x010a:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0.interrupt();	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;
            r0 = r0.e();
            if (r0 != 0) goto L_0x00ef;
        L_0x011a:
            r0 = r8.a;
            r0.k();
            goto L_0x00ef;
        L_0x0120:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r3 = 9;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r4 = r4.L;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = r2.a(r3, r4);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.m = r2;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            goto L_0x0051;
        L_0x0134:
            r0 = move-exception;
            r1 = "APSServiceCore";	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r2 = "run part5";	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            com.e.bc.a(r0, r1, r2);	 Catch:{ Throwable -> 0x00f0, RemoteException -> 0x00da, InterruptedException -> 0x010a, all -> 0x016f }
            r0 = r8.a;
            r0 = r0.e();
            if (r0 != 0) goto L_0x00ef;
        L_0x0144:
            r0 = r8.a;
            r0.k();
            goto L_0x00ef;
        L_0x014a:
            r0 = r1;
        L_0x014b:
            monitor-exit(r3);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x014c:
            r1 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r1.y;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r1 == 0) goto L_0x0001;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0154:
            r1 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.h();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.g();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r1.a(r0);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.d();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.l();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            goto L_0x0001;
        L_0x016f:
            r0 = move-exception;
            r1 = r8.a;
            r1 = r1.e();
            if (r1 != 0) goto L_0x017d;
        L_0x0178:
            r1 = r8.a;
            r1.k();
        L_0x017d:
            throw r0;
        L_0x017e:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            throw r0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0181:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r2 = r0.l;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            monitor-enter(r2);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.e();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            if (r0 == 0) goto L_0x0195;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x018e:
            r0 = r8.a;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r0.l;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0.wait();	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0195:
            monitor-exit(r2);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            r0 = r1;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            goto L_0x014c;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x0198:
            r0 = move-exception;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            monitor-exit(r2);	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
            throw r0;	 Catch:{ RemoteException -> 0x00da, InterruptedException -> 0x010a, Throwable -> 0x0134 }
        L_0x019b:
            r0 = r8.a;
            r0 = r0.e();
            if (r0 != 0) goto L_0x00ef;
        L_0x01a3:
            r0 = r8.a;
            r0.k();
            goto L_0x00ef;
        L_0x01aa:
            r0 = r1;
            goto L_0x014b;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.e.ab.a.run():void");
        }
    }

    class b extends Handler {
        ab a = null;
        final /* synthetic */ ab b;
        private boolean c = true;
        private boolean d = true;

        public b(ab abVar, ab abVar2) {
            this.b = abVar;
            this.a = abVar2;
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        synchronized (this.b.l) {
                            this.b.y = true;
                            this.b.c = message.replyTo;
                            this.a.l.notify();
                        }
                        break;
                    case 1:
                        try {
                            synchronized (this.b.l) {
                                this.b.y = true;
                                Bundle data = message.getData();
                                Messenger messenger = message.replyTo;
                                long b = br.b();
                                boolean z = data.getBoolean("isNeedAddress", true);
                                boolean z2 = data.getBoolean("isOffset", true);
                                boolean z3 = data.getBoolean("isLocationCacheEnable", true);
                                if (!(z == this.c && z2 == this.d)) {
                                    this.a.n = 0;
                                }
                                this.c = z;
                                this.d = z2;
                                if (this.b.m == null || this.b.m.getErrorCode() != 0 || b - this.a.n >= 800) {
                                    if (!this.b.h.contains(messenger)) {
                                        this.b.h.add(messenger);
                                    }
                                    this.b.k = true;
                                    this.a.l.notify();
                                } else {
                                    Message obtain = Message.obtain();
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(n.C, this.b.m);
                                    obtain.setData(bundle);
                                    obtain.what = 1;
                                    messenger.send(obtain);
                                }
                                boolean z4 = data.getBoolean("wifiactivescan");
                                this.b.b = data.getBoolean("isKillProcess");
                                b = data.getLong("httptimeout");
                                if (this.b.p != null) {
                                    this.b.p.put("reversegeo", z);
                                    this.b.p.put("isOffset", z2);
                                    this.b.p.put("wifiactivescan", z4 ? "1" : "0");
                                    this.b.p.put("httptimeout", b);
                                    this.b.p.put("isLocationCacheEnable", z3);
                                }
                                this.a.a(this.b.p);
                            }
                            break;
                        } catch (Throwable th) {
                            bc.a(th, "APSServiceCore", "handleMessage LOCATION");
                            break;
                        }
                        break;
                    case 2:
                        this.b.b();
                        break;
                    case 3:
                        this.b.c();
                        break;
                    case 4:
                        synchronized (this.b.l) {
                            if (this.b.z < bo.b()) {
                                this.b.E = true;
                                this.b.z = this.b.z + 1;
                            } else {
                                this.b.E = false;
                            }
                            this.a.l.notify();
                            this.b.f.sendEmptyMessageDelayed(4, 2000);
                        }
                        break;
                    case 5:
                        synchronized (this.b.l) {
                            if (bo.e()) {
                                this.b.F = true;
                            } else if (!br.d(this.b.a)) {
                                this.b.F = true;
                            }
                            this.a.l.notify();
                            this.b.f.sendEmptyMessageDelayed(5, (long) (bo.d() * 1000));
                        }
                        break;
                    case 7:
                        synchronized (this.b.l) {
                            this.b.G = true;
                            this.a.l.notify();
                        }
                        break;
                    case 8:
                        synchronized (this.b.l) {
                            if (this.b.m != null && (this.b.m.getLocationType() == 2 || this.b.m.getLocationType() == 4)) {
                                this.b.D = true;
                                this.b.C = true;
                                this.a.l.notify();
                            }
                        }
                        break;
                }
                super.handleMessage(message);
            } catch (Throwable th2) {
                bc.a(th2, "APSServiceCore", "handleMessage STARTCOLL");
            }
        }
    }

    class c extends Thread {
        final /* synthetic */ ab a;

        c(ab abVar) {
            this.a = abVar;
        }

        public void run() {
            try {
                if (!this.a.w) {
                    this.a.j();
                }
                if (!this.a.s) {
                    this.a.s = true;
                    this.a.r = new ServerSocket(43689);
                }
                while (this.a.s) {
                    this.a.t = this.a.r.accept();
                    this.a.a(this.a.t);
                }
            } catch (Throwable th) {
                bc.a(th, "APSServiceCore", "run");
            }
            super.run();
        }
    }

    public ab(Context context) {
        this.a = context.getApplicationContext();
    }

    private AmapLoc a(int i, String str) {
        try {
            AmapLoc amapLoc = new AmapLoc();
            amapLoc.setErrorCode(i);
            amapLoc.setLocationDetail(str);
            return amapLoc;
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    private void a(Messenger messenger) {
        try {
            Message obtain;
            if (bo.o() && messenger != null) {
                bo.a(false);
                obtain = Message.obtain();
                obtain.what = 100;
                messenger.send(obtain);
            }
            if (this.c != null) {
                obtain = Message.obtain();
                obtain.what = 6;
                this.c.send(obtain);
                this.c = null;
            }
            if (bo.a(this.I) && !this.C) {
                this.f.sendEmptyMessage(8);
            }
            if (bo.a() && !this.A) {
                this.A = true;
                this.f.sendEmptyMessage(4);
            }
            if (bo.c() && bo.d() > 2 && !this.B) {
                this.B = true;
                this.f.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "checkConfig");
        }
    }

    private void a(Socket socket) {
        BufferedReader bufferedReader;
        Throwable th;
        PrintStream printStream;
        PrintStream printStream2;
        String str = null;
        int i = 0;
        if (socket != null) {
            int i2 = 30000;
            try {
                String str2 = "jsonp1";
                System.currentTimeMillis();
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    String readLine = bufferedReader.readLine();
                    if (readLine != null && readLine.length() > 0) {
                        String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        if (split != null && split.length > 1) {
                            split = split[1].split("\\?");
                            if (split != null && split.length > 1) {
                                split = split[1].split(com.alipay.sdk.h.a.b);
                                if (split != null && split.length > 0) {
                                    String str3 = str2;
                                    int i3 = 30000;
                                    String str4 = str3;
                                    while (i < split.length) {
                                        try {
                                            String[] split2 = split[i].split("=");
                                            if (split2 != null && split2.length > 1) {
                                                if ("to".equals(split2[0])) {
                                                    i3 = Integer.parseInt(split2[1]);
                                                }
                                                if (com.alipay.sdk.a.a.c.equals(split2[0])) {
                                                    str4 = split2[1];
                                                }
                                                if ("_".equals(split2[0])) {
                                                    Long.parseLong(split2[1]);
                                                }
                                            }
                                            i++;
                                        } catch (Throwable th2) {
                                            Throwable th3 = th2;
                                            str2 = str4;
                                            th = th3;
                                        }
                                    }
                                    str3 = str4;
                                    i2 = i3;
                                    str2 = str3;
                                }
                            }
                        }
                    }
                    try {
                        i = bc.i;
                        bc.i = i2;
                        long currentTimeMillis = System.currentTimeMillis();
                        if ((this.q == null || currentTimeMillis - this.q.getTime() > OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) && !br.d(this.a)) {
                            this.q = this.J.a();
                            if (this.q.getErrorCode() != 0) {
                                str = str2 + "&&" + str2 + "({'package':'" + this.d + "','error_code':" + this.q.getErrorCode() + ",'error':'" + this.q.getErrorInfo() + "'})";
                            }
                            bc.i = i;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        try {
                            str = str2 + "&&" + str2 + "({'package':'" + this.d + "','error_code':1,'error':'params error'})";
                            bc.a(th, "APSServiceCore", "invoke part2");
                            try {
                                printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                printStream.println("HTTP/1.0 200 OK");
                                printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
                                printStream.println();
                                printStream.println(str);
                                printStream.close();
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th5) {
                                    bc.a(th5, "APSServiceCore", "invoke part4");
                                    return;
                                }
                            } catch (Throwable th52) {
                                bc.a(th52, "APSServiceCore", "invoke part4");
                                return;
                            }
                        } catch (Throwable th6) {
                            th52 = th6;
                            try {
                                printStream2 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                printStream2.println("HTTP/1.0 200 OK");
                                printStream2.println("Content-Length:" + str.getBytes("UTF-8").length);
                                printStream2.println();
                                printStream2.println(str);
                                printStream2.close();
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th22) {
                                    bc.a(th22, "APSServiceCore", "invoke part4");
                                }
                            } catch (Throwable th222) {
                                bc.a(th222, "APSServiceCore", "invoke part4");
                            }
                            throw th52;
                        }
                    }
                    if (str == null) {
                        if (this.q == null) {
                            str = str2 + "&&" + str2 + "({'package':'" + this.d + "','error_code':8,'error':'unknown error'})";
                        } else {
                            AmapLoc amapLoc = this.q;
                            str = str2 + "&&" + str2 + "({'package':'" + this.d + "','error_code':0,'error':'','location':{'y':" + amapLoc.getLat() + ",'precision':" + amapLoc.getAccuracy() + ",'x':" + amapLoc.getLon() + "},'version_code':'" + "2.5.0" + "','version':'" + "2.5.0" + "'})";
                        }
                        if (br.d(this.a)) {
                            str = str2 + "&&" + str2 + "({'package':'" + this.d + "','error_code':36,'error':'app is background'})";
                        }
                    }
                    try {
                        printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                        printStream.println("HTTP/1.0 200 OK");
                        printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
                        printStream.println();
                        printStream.println(str);
                        printStream.close();
                        bufferedReader.close();
                        socket.close();
                    } catch (Throwable th522) {
                        bc.a(th522, "APSServiceCore", "invoke part4");
                    }
                } catch (Throwable th7) {
                    th522 = th7;
                    bufferedReader = null;
                    printStream2 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                    printStream2.println("HTTP/1.0 200 OK");
                    printStream2.println("Content-Length:" + str.getBytes("UTF-8").length);
                    printStream2.println();
                    printStream2.println(str);
                    printStream2.close();
                    bufferedReader.close();
                    socket.close();
                    throw th522;
                }
            } catch (Throwable th5222) {
                bc.a(th5222, "APSServiceCore", "invoke part5");
            }
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            if (this.J != null) {
                this.J.a(jSONObject);
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "setExtra");
        }
    }

    private void d() {
        try {
            i();
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "doFusionLocaiton:init");
        }
        if (this.D) {
            this.I = br.b();
            this.D = false;
            this.J.b();
            this.C = false;
        }
        if (this.E) {
            this.E = false;
            this.J.b();
        }
        if (this.F) {
            this.F = false;
            this.J.b();
        }
        if (this.G && bo.b(this.H)) {
            this.H = br.a();
            this.G = false;
            this.J.b();
        }
    }

    private boolean e() {
        boolean z;
        synchronized (this.l) {
            z = this.j;
        }
        return z;
    }

    private AmapLoc f() throws Throwable {
        return this.J.a();
    }

    private void g() {
        try {
            this.J.g();
        } catch (Throwable th) {
        }
    }

    private void h() {
        try {
            if (!this.x) {
                this.x = true;
                this.J.c(this.a);
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "initAuth");
        }
    }

    private void i() {
        try {
            if (!this.w) {
                j();
            }
        } catch (Throwable th) {
            this.K = false;
            this.L = th.getMessage();
            bc.a(th, "APSServiceCore", "init");
        }
    }

    private void j() {
        try {
            bc.a(this.a);
            this.J.a("api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##" + cu.c(this.a) + "##" + cu.f(this.a));
            this.p.put(d.M, cu.f(this.a));
            this.p.put("User-Agent", "AMAP_Location_SDK_Android 2.5.0");
            this.p.put("netloc", "0");
            this.p.put("gpsstatus", "0");
            this.p.put("nbssid", "0");
            if (!this.p.has("reversegeo")) {
                this.p.put("reversegeo", true);
            }
            if (!this.p.has("isOffset")) {
                this.p.put("isOffset", true);
            }
            this.p.put("wait1stwifi", "0");
            this.p.put("autoup", "0");
            this.p.put("upcolmobile", 1);
            if (!this.p.has("enablegetreq")) {
                this.p.put("enablegetreq", 1);
            }
            if (!this.p.has("wifiactivescan")) {
                this.p.put("wifiactivescan", 1);
            }
            if (!this.p.has("isLocationCacheEnable")) {
                this.p.put("isLocationCacheEnable", true);
            }
        } catch (Throwable th) {
            this.L = th.getMessage();
            this.K = false;
            bc.a(th, "APSServiceCore", "doInit part3");
            return;
        }
        this.w = true;
        this.J.a(this.p);
        this.J.b(this.a);
    }

    private void k() {
        try {
            c();
            this.w = false;
            this.x = false;
            this.y = false;
            this.A = false;
            this.B = false;
            this.z = 0;
            this.J.c();
            this.h.clear();
            dj.a();
            if (this.b) {
                Process.killProcess(Process.myPid());
            }
            if (this.f != null) {
                this.f.removeCallbacksAndMessages(null);
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "threadDestroy");
        }
    }

    private void l() {
        try {
            if (this.J != null) {
                this.J.i();
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "startColl");
        }
    }

    public Handler a() {
        return this.f;
    }

    public synchronized void b() {
        try {
            if (!this.u) {
                this.v = new c(this);
                this.v.start();
                this.u = true;
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "startSocket");
        }
    }

    public synchronized void c() {
        try {
            if (this.r != null) {
                this.r.close();
            }
            if (this.t != null) {
                this.t.close();
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "stopScocket");
        }
        this.r = null;
        this.v = null;
        this.u = false;
        this.s = false;
    }

    public IBinder onBind(Intent intent) {
        Object stringExtra = intent.getStringExtra("apiKey");
        if (!TextUtils.isEmpty(stringExtra)) {
            cv.a(stringExtra);
        }
        String stringExtra2 = intent.getStringExtra(Constants.FLAG_PACKAGE_NAME);
        String stringExtra3 = intent.getStringExtra("sha1AndPackage");
        cu.a(stringExtra2);
        cu.b(stringExtra3);
        this.e = new Messenger(a());
        return this.e.getBinder();
    }

    public void onCreate() {
        try {
            this.J = new aq();
            this.d = this.a.getApplicationContext().getPackageName();
            this.j = true;
            this.g = new a(this);
            this.g.setName("serviceThread");
            this.g.start();
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "onCreate");
        }
    }

    public void onDestroy() {
        try {
            synchronized (this.l) {
                this.j = false;
                this.l.notify();
            }
        } catch (Throwable th) {
            bc.a(th, "APSServiceCore", "onDestroy");
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
