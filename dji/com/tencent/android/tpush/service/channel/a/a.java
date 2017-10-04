package com.tencent.android.tpush.service.channel.a;

import android.support.v4.media.TransportMediator;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.b.d;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class a extends Thread {
    protected b a;
    public SocketChannel b = null;
    protected Selector c = null;
    protected TpnsSecurity d = new TpnsSecurity();
    protected d e = null;
    protected e f = null;
    protected String g = "";
    protected int h = 0;
    protected int i = 0;
    protected long j = IPositioningSession.NotSet;
    protected com.tencent.android.tpush.service.channel.a k = null;
    private volatile boolean l = false;

    public a(SocketChannel socketChannel, b bVar) {
        super("TpnsClient");
        if (socketChannel.socket().isConnected()) {
            this.g = socketChannel.socket().getInetAddress() == null ? "" : socketChannel.socket().getInetAddress().getHostAddress();
            this.h = socketChannel.socket().getPort();
            this.i = 0;
            com.tencent.android.tpush.a.a.e("TpnsClient", "Connect to Xinge Server succeed!");
        } else {
            com.tencent.android.tpush.a.a.h("TpnsClient", "TpnsClient -> the socketChannel is not connected");
        }
        this.b = socketChannel;
        this.a = bVar;
    }

    protected boolean a() {
        if (this.e == null) {
            this.e = new g();
            ((g) this.e).a(this.d);
        }
        return true;
    }

    protected boolean b() {
        if (this.f == null) {
            ArrayList a = this.a.a(this, 1);
            if (!a.isEmpty()) {
                this.f = (e) a.get(0);
            }
            if (this.f != null) {
                ((h) this.f).a(this.d);
            }
        }
        return this.f != null;
    }

    public void a(a aVar, d dVar) {
        this.a.b(aVar, (i) dVar);
    }

    public void a(a aVar, e eVar) {
        this.a.a(aVar, (i) eVar);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14 = this;
        r0 = com.tencent.android.tpush.XGPushConfig.enableDebug;
        if (r0 == 0) goto L_0x000b;
    L_0x0004:
        r0 = "TpnsClient";
        r1 = "TpnsClient is running and ready for send and recevie msg.";
        com.tencent.android.tpush.a.a.d(r0, r1);
    L_0x000b:
        r4 = 0;
        r0 = java.nio.channels.Selector.open();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r14.c = r0;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r14.b;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = 0;
        r0.configureBlocking(r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r5 = java.nio.ByteBuffer.allocateDirect(r0);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r6 = new com.tencent.android.tpush.service.channel.c.a;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r1 = 0;
        r6.<init>(r0, r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = 24576; // 0x6000 float:3.4438E-41 double:1.2142E-319;
        r7 = new byte[r0];	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r8 = java.nio.ByteBuffer.allocateDirect(r0);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r9 = new com.tencent.android.tpush.service.channel.c.a;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = -1;
        r1 = 0;
        r9.<init>(r0, r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r10 = new byte[r0];	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r8.flip();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r2 = 0;
    L_0x0040:
        r0 = r14.l;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 != 0) goto L_0x007b;
    L_0x0044:
        r0 = r14.b;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = r14.c;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = 1;
        r0.register(r1, r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r14.b();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 != 0) goto L_0x005e;
    L_0x0052:
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 > 0) goto L_0x005e;
    L_0x0058:
        r0 = r9.c();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 <= 0) goto L_0x0066;
    L_0x005e:
        r0 = r14.b;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = r14.c;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = 4;
        r0.register(r1, r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x0066:
        r0 = r14.g();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 == 0) goto L_0x00a7;
    L_0x006c:
        r0 = r14.e;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 != 0) goto L_0x00a7;
    L_0x0070:
        r0 = r14.f;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 != 0) goto L_0x00a7;
    L_0x0074:
        r0 = "TpnsClient";
        r1 = ">> retired!!!";
        com.tencent.android.tpush.a.a.h(r0, r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x007b:
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x0274 }
        r0.close();	 Catch:{ Exception -> 0x0274 }
    L_0x0081:
        r0 = r14.b;	 Catch:{ Exception -> 0x0292 }
        r0.close();	 Catch:{ Exception -> 0x0292 }
    L_0x0086:
        monitor-exit(r14);	 Catch:{ all -> 0x028f }
        if (r4 == 0) goto L_0x02ad;
    L_0x0089:
        r0 = "TpnsClient";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "<<< Run <<< exit!!! cause: ";
        r1 = r1.append(r2);
        r1 = r1.append(r4);
        r1 = r1.toString();
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14, r4);
    L_0x00a6:
        return;
    L_0x00a7:
        r0 = r14.c;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0.select(r2);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r2 = 0;
        r0 = r14.f;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 == 0) goto L_0x0108;
    L_0x00b2:
        r0 = r14.f;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r0.a();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = 0;
        r11 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1));
        if (r11 > 0) goto L_0x0103;
    L_0x00be:
        r0 = new java.util.concurrent.TimeoutException;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = "发送超时";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        throw r0;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x00c6:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel IOException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x04c6 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x04c6 }
        r2 = 10103; // 0x2777 float:1.4157E-41 double:4.9915E-320;
        r3 = "TpnsClient发生IO异常，链路可能被关闭";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x04c6 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x02cd }
        r0.close();	 Catch:{ Exception -> 0x02cd }
    L_0x00dd:
        r0 = r14.b;	 Catch:{ Exception -> 0x02eb }
        r0.close();	 Catch:{ Exception -> 0x02eb }
    L_0x00e2:
        monitor-exit(r14);	 Catch:{ all -> 0x02e8 }
        if (r1 == 0) goto L_0x0306;
    L_0x00e5:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00a6;
    L_0x0103:
        r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r11 >= 0) goto L_0x015e;
    L_0x0107:
        r2 = r0;
    L_0x0108:
        r0 = r14.e;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 == 0) goto L_0x0165;
    L_0x010c:
        r0 = r14.e;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r0.a();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = 0;
        r11 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1));
        if (r11 > 0) goto L_0x0160;
    L_0x0118:
        r0 = new java.util.concurrent.TimeoutException;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = "接收超时";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        throw r0;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x0120:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel InnerException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x04c6 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x04c6 }
        r2 = 10104; // 0x2778 float:1.4159E-41 double:4.992E-320;
        r3 = "TpnsClient发生内部异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x04c6 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x0326 }
        r0.close();	 Catch:{ Exception -> 0x0326 }
    L_0x0137:
        r0 = r14.b;	 Catch:{ Exception -> 0x0344 }
        r0.close();	 Catch:{ Exception -> 0x0344 }
    L_0x013c:
        monitor-exit(r14);	 Catch:{ all -> 0x0341 }
        if (r1 == 0) goto L_0x035f;
    L_0x013f:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00a6;
    L_0x015e:
        r0 = r2;
        goto L_0x0107;
    L_0x0160:
        r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r11 >= 0) goto L_0x01de;
    L_0x0164:
        r2 = r0;
    L_0x0165:
        r0 = r14.c;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r0.selectedKeys();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = r0.iterator();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x016f:
        r0 = r1.hasNext();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 == 0) goto L_0x0040;
    L_0x0175:
        r0 = r1.next();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = (java.nio.channels.SelectionKey) r0;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r0.isReadable();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r11 == 0) goto L_0x01f3;
    L_0x0181:
        r5.clear();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r6.d();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r5.limit(r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r14.b;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = r5.slice();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r11.read(r12);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = -1;
        if (r11 != r12) goto L_0x01e0;
    L_0x0198:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r1 = "socket channel read return -1";
        r0.<init>(r1);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        throw r0;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x01a0:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel UnexpectedDataException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x04c6 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x04c6 }
        r2 = 10108; // 0x277c float:1.4164E-41 double:4.994E-320;
        r3 = "TpnsClient发生非预期数据异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x04c6 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x037f }
        r0.close();	 Catch:{ Exception -> 0x037f }
    L_0x01b7:
        r0 = r14.b;	 Catch:{ Exception -> 0x039d }
        r0.close();	 Catch:{ Exception -> 0x039d }
    L_0x01bc:
        monitor-exit(r14);	 Catch:{ all -> 0x039a }
        if (r1 == 0) goto L_0x03b8;
    L_0x01bf:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00a6;
    L_0x01de:
        r0 = r2;
        goto L_0x0164;
    L_0x01e0:
        r12 = 0;
        r5.get(r7, r12, r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = r6.a();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r13 = 0;
        r12.write(r7, r13, r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r6.b();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r14.a(r11);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x01f3:
        r0 = r0.isWritable();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 == 0) goto L_0x022c;
    L_0x01f9:
        r0 = r9.a();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r14.a(r0);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r9.c();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 <= 0) goto L_0x022c;
    L_0x0206:
        r8.compact();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = r9.c();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        if (r0 >= r11) goto L_0x026f;
    L_0x0213:
        r0 = r8.remaining();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x0217:
        r11 = r9.b();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r12 = 0;
        r0 = r11.read(r10, r12, r0);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r11 = 0;
        r8.put(r10, r11, r0);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r8.flip();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0 = r14.b;	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        r0.write(r8);	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
    L_0x022c:
        r1.remove();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        goto L_0x016f;
    L_0x0231:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel TimeoutException";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x04c6 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x04c6 }
        r2 = 10105; // 0x2779 float:1.416E-41 double:4.9925E-320;
        r3 = "TpnsClient发生超时异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x04c6 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x03d8 }
        r0.close();	 Catch:{ Exception -> 0x03d8 }
    L_0x0248:
        r0 = r14.b;	 Catch:{ Exception -> 0x03f6 }
        r0.close();	 Catch:{ Exception -> 0x03f6 }
    L_0x024d:
        monitor-exit(r14);	 Catch:{ all -> 0x03f3 }
        if (r1 == 0) goto L_0x0411;
    L_0x0250:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00a6;
    L_0x026f:
        r0 = r9.c();	 Catch:{ IOException -> 0x00c6, InnerException -> 0x0120, UnexpectedDataException -> 0x01a0, TimeoutException -> 0x0231, Exception -> 0x0431 }
        goto L_0x0217;
    L_0x0274:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x028f }
        r2.<init>();	 Catch:{ all -> 0x028f }
        r3 = ">>> Run >>> selector.close() ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x028f }
        r0 = r2.append(r0);	 Catch:{ all -> 0x028f }
        r0 = r0.toString();	 Catch:{ all -> 0x028f }
        com.tencent.android.tpush.a.a.h(r1, r0);	 Catch:{ all -> 0x028f }
        goto L_0x0081;
    L_0x028f:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x028f }
        throw r0;
    L_0x0292:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x028f }
        r2.<init>();	 Catch:{ all -> 0x028f }
        r3 = ">>> Run >>> socketChannel.close(): ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x028f }
        r0 = r2.append(r0);	 Catch:{ all -> 0x028f }
        r0 = r0.toString();	 Catch:{ all -> 0x028f }
        com.tencent.android.tpush.a.a.h(r1, r0);	 Catch:{ all -> 0x028f }
        goto L_0x0086;
    L_0x02ad:
        r0 = r14.l;
        if (r0 == 0) goto L_0x02bf;
    L_0x02b1:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x02bf:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x02cd:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02e8 }
        r3.<init>();	 Catch:{ all -> 0x02e8 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x02e8 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x02e8 }
        r0 = r0.toString();	 Catch:{ all -> 0x02e8 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x02e8 }
        goto L_0x00dd;
    L_0x02e8:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x02e8 }
        throw r0;
    L_0x02eb:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02e8 }
        r3.<init>();	 Catch:{ all -> 0x02e8 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x02e8 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x02e8 }
        r0 = r0.toString();	 Catch:{ all -> 0x02e8 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x02e8 }
        goto L_0x00e2;
    L_0x0306:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0318;
    L_0x030a:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x0318:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x0326:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0341 }
        r3.<init>();	 Catch:{ all -> 0x0341 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0341 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0341 }
        r0 = r0.toString();	 Catch:{ all -> 0x0341 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0341 }
        goto L_0x0137;
    L_0x0341:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0341 }
        throw r0;
    L_0x0344:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0341 }
        r3.<init>();	 Catch:{ all -> 0x0341 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0341 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0341 }
        r0 = r0.toString();	 Catch:{ all -> 0x0341 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0341 }
        goto L_0x013c;
    L_0x035f:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0371;
    L_0x0363:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x0371:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x037f:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x039a }
        r3.<init>();	 Catch:{ all -> 0x039a }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x039a }
        r0 = r3.append(r0);	 Catch:{ all -> 0x039a }
        r0 = r0.toString();	 Catch:{ all -> 0x039a }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x039a }
        goto L_0x01b7;
    L_0x039a:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x039a }
        throw r0;
    L_0x039d:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x039a }
        r3.<init>();	 Catch:{ all -> 0x039a }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x039a }
        r0 = r3.append(r0);	 Catch:{ all -> 0x039a }
        r0 = r0.toString();	 Catch:{ all -> 0x039a }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x039a }
        goto L_0x01bc;
    L_0x03b8:
        r0 = r14.l;
        if (r0 == 0) goto L_0x03ca;
    L_0x03bc:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x03ca:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x03d8:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03f3 }
        r3.<init>();	 Catch:{ all -> 0x03f3 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x03f3 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x03f3 }
        r0 = r0.toString();	 Catch:{ all -> 0x03f3 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x03f3 }
        goto L_0x0248;
    L_0x03f3:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x03f3 }
        throw r0;
    L_0x03f6:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03f3 }
        r3.<init>();	 Catch:{ all -> 0x03f3 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x03f3 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x03f3 }
        r0 = r0.toString();	 Catch:{ all -> 0x03f3 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x03f3 }
        goto L_0x024d;
    L_0x0411:
        r0 = r14.l;
        if (r0 == 0) goto L_0x0423;
    L_0x0415:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x0423:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x0431:
        r0 = move-exception;
        r1 = "TpnsClient";
        r2 = "<<< Run <<< socketChannel Exception";
        com.tencent.android.tpush.a.a.c(r1, r2, r0);	 Catch:{ all -> 0x04c6 }
        r1 = new com.tencent.android.tpush.service.channel.exception.ChannelException;	 Catch:{ all -> 0x04c6 }
        r2 = 10109; // 0x277d float:1.4166E-41 double:4.9945E-320;
        r3 = "TpnsClient发生未知异常";
        r1.<init>(r2, r3, r0);	 Catch:{ all -> 0x04c6 }
        monitor-enter(r14);
        r0 = r14.c;	 Catch:{ Exception -> 0x046f }
        r0.close();	 Catch:{ Exception -> 0x046f }
    L_0x0448:
        r0 = r14.b;	 Catch:{ Exception -> 0x048c }
        r0.close();	 Catch:{ Exception -> 0x048c }
    L_0x044d:
        monitor-exit(r14);	 Catch:{ all -> 0x0489 }
        if (r1 == 0) goto L_0x04a6;
    L_0x0450:
        r0 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r0, r2);
        r0 = r14.a;
        r0.a(r14, r1);
        goto L_0x00a6;
    L_0x046f:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0489 }
        r3.<init>();	 Catch:{ all -> 0x0489 }
        r4 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0489 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0489 }
        r0 = r0.toString();	 Catch:{ all -> 0x0489 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0489 }
        goto L_0x0448;
    L_0x0489:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x0489 }
        throw r0;
    L_0x048c:
        r0 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0489 }
        r3.<init>();	 Catch:{ all -> 0x0489 }
        r4 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0489 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0489 }
        r0 = r0.toString();	 Catch:{ all -> 0x0489 }
        com.tencent.android.tpush.a.a.h(r2, r0);	 Catch:{ all -> 0x0489 }
        goto L_0x044d;
    L_0x04a6:
        r0 = r14.l;
        if (r0 == 0) goto L_0x04b8;
    L_0x04aa:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.a(r14);
        goto L_0x00a6;
    L_0x04b8:
        r0 = "TpnsClient";
        r1 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r0, r1);
        r0 = r14.a;
        r0.b(r14);
        goto L_0x00a6;
    L_0x04c6:
        r0 = move-exception;
        monitor-enter(r14);
        r1 = r14.c;	 Catch:{ Exception -> 0x04f3 }
        r1.close();	 Catch:{ Exception -> 0x04f3 }
    L_0x04cd:
        r1 = r14.b;	 Catch:{ Exception -> 0x0510 }
        r1.close();	 Catch:{ Exception -> 0x0510 }
    L_0x04d2:
        monitor-exit(r14);	 Catch:{ all -> 0x050d }
        if (r4 == 0) goto L_0x052a;
    L_0x04d5:
        r1 = "TpnsClient";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "<<< Run <<< exit!!! cause: ";
        r2 = r2.append(r3);
        r2 = r2.append(r4);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.a(r14, r4);
    L_0x04f2:
        throw r0;
    L_0x04f3:
        r1 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x050d }
        r3.<init>();	 Catch:{ all -> 0x050d }
        r5 = ">>> Run >>> selector.close() ";
        r3 = r3.append(r5);	 Catch:{ all -> 0x050d }
        r1 = r3.append(r1);	 Catch:{ all -> 0x050d }
        r1 = r1.toString();	 Catch:{ all -> 0x050d }
        com.tencent.android.tpush.a.a.h(r2, r1);	 Catch:{ all -> 0x050d }
        goto L_0x04cd;
    L_0x050d:
        r0 = move-exception;
        monitor-exit(r14);	 Catch:{ all -> 0x050d }
        throw r0;
    L_0x0510:
        r1 = move-exception;
        r2 = "TpnsClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x050d }
        r3.<init>();	 Catch:{ all -> 0x050d }
        r5 = ">>> Run >>> socketChannel.close(): ";
        r3 = r3.append(r5);	 Catch:{ all -> 0x050d }
        r1 = r3.append(r1);	 Catch:{ all -> 0x050d }
        r1 = r1.toString();	 Catch:{ all -> 0x050d }
        com.tencent.android.tpush.a.a.h(r2, r1);	 Catch:{ all -> 0x050d }
        goto L_0x04d2;
    L_0x052a:
        r1 = r14.l;
        if (r1 == 0) goto L_0x053b;
    L_0x052e:
        r1 = "TpnsClient";
        r2 = "<<< Run <<< exit!!! cancelled! ";
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.a(r14);
        goto L_0x04f2;
    L_0x053b:
        r1 = "TpnsClient";
        r2 = "<<< Run <<< exit!!! Retired! ";
        com.tencent.android.tpush.a.a.h(r1, r2);
        r1 = r14.a;
        r1.b(r14);
        goto L_0x04f2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.channel.a.a.run():void");
    }

    protected int a(InputStream inputStream) {
        int i = 0;
        while (inputStream.available() > 0) {
            a();
            if (this.e != null) {
                i += this.e.a(inputStream);
                if (!this.e.b()) {
                    com.tencent.android.tpush.a.a.h(Constants.TcpRecvPackLogTag, ">> recvHandle not success");
                    break;
                }
                a(this, this.e);
                this.e = null;
            }
        }
        return i;
    }

    protected int a(OutputStream outputStream) {
        if (!g()) {
            b();
        }
        if (this.f == null) {
            return 0;
        }
        int a = this.f.a(outputStream);
        if (this.f.b()) {
            if ((((h) this.f).h() & TransportMediator.KEYCODE_MEDIA_PAUSE) != 7) {
                a(this, this.f);
            }
            this.f = null;
        }
        if (b()) {
            h();
        }
        return a;
    }

    public synchronized void start() {
        super.start();
    }

    public synchronized void c() {
        this.l = true;
        h();
    }

    public synchronized boolean d() {
        boolean isConnected;
        if (this.b != null) {
            isConnected = this.b.isConnected();
        } else {
            isConnected = false;
        }
        return isConnected;
    }

    public boolean e() {
        return this.i == 1;
    }

    public com.tencent.android.tpush.service.channel.a f() {
        boolean z = true;
        if (this.k == null) {
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(0);
            objArr[1] = this.g;
            objArr[2] = Integer.valueOf(1);
            objArr[3] = Integer.valueOf(this.h);
            objArr[4] = Integer.valueOf(2);
            if (this.i != 1) {
                z = false;
            }
            objArr[5] = Boolean.valueOf(z);
            this.k = new com.tencent.android.tpush.service.channel.a(objArr);
        }
        return this.k;
    }

    protected boolean g() {
        return System.currentTimeMillis() > this.j;
    }

    public void h() {
        try {
            if (this.c != null && this.c.isOpen()) {
                this.c.wakeup();
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("TpnsClient", ">>selector wakeup err", th);
        }
    }

    public String toString() {
        return new StringBuffer(getClass().getSimpleName()).append("(ip:").append(this.g).append(",port:").append(this.h).append(",protocol:").append(this.i == 1 ? "http" : "tcp").append(")").toString();
    }
}
