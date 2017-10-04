package com.tencent.android.tpush.service.channel.a;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.tencent.android.tpush.service.channel.b.a;
import com.tencent.android.tpush.service.channel.b.b;
import com.tencent.android.tpush.service.channel.b.e;
import com.tencent.android.tpush.service.channel.b.g;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.exception.InnerException;
import dji.pilot.usercenter.protocol.d;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends a {
    protected String l = null;
    protected String m = null;
    private boolean n = false;

    public c(SocketChannel socketChannel, b bVar) {
        super(socketChannel, bVar);
        this.m = this.g + (this.h == 80 ? "" : ":" + this.h);
        this.l = d.t;
        this.i = 1;
    }

    protected c(SocketChannel socketChannel, b bVar, String str, int i, String str2) {
        super(socketChannel, bVar);
        this.g = str;
        this.h = i;
        this.m = str + (i == 80 ? "" : ":" + i);
        this.l = str2;
    }

    public void a(a aVar, com.tencent.android.tpush.service.channel.b.d dVar) {
        if (dVar instanceof a) {
            Iterator it = ((a) dVar).i.iterator();
            while (it.hasNext()) {
                this.a.b(aVar, (g) it.next());
            }
            c();
            return;
        }
        throw new InnerException("packet is not instance of Http****Packet!");
    }

    public void a(a aVar, e eVar) {
        this.n = true;
        if (eVar instanceof b) {
            Iterator it = ((b) eVar).d.iterator();
            while (it.hasNext()) {
                this.a.a(aVar, (i) ((e) it.next()));
            }
            return;
        }
        throw new InnerException("packet is not instance of Http****Packet!");
    }

    protected boolean b() {
        if (this.f == null && !this.n) {
            ArrayList a = this.a.a((a) this, 16);
            if (a.size() > 0) {
                e bVar = new b(this.m, this.l);
                bVar.a(this.d);
                bVar.a(MANConfig.NETWORK_SINGLE_REQUEST_HOST_KEY, this.m);
                bVar.a("User-Agent", "TPNS_CLIENT/0.1");
                bVar.a(AsyncHttpClient.HEADER_CONTENT_TYPE, "application/binary");
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    bVar.a((h) it.next());
                }
                this.f = bVar;
            }
        }
        return this.f != null;
    }

    protected boolean a() {
        if (!this.n) {
            return false;
        }
        if (this.e == null) {
            this.e = new a();
            this.e.a(this.d);
        }
        if (this.e != null) {
            return true;
        }
        return false;
    }
}
