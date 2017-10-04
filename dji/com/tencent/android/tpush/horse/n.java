package com.tencent.android.tpush.horse;

import android.text.TextUtils;
import com.f.a.a.f;
import com.f.a.a.g;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.service.channel.b.b;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.exception.HorseIgnoreException;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp;
import com.tencent.android.tpush.service.d.e;
import com.tencent.android.tpush.service.l;
import dji.pilot.usercenter.protocol.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;

public class n {
    private SocketChannel a;
    private ArrayBlockingQueue b = new ArrayBlockingQueue(1);
    private StrategyItem c;
    private long d;
    private long e;

    public void a(StrategyItem strategyItem) {
        Throwable th;
        this.d = System.currentTimeMillis();
        this.c = strategyItem;
        try {
            String debugServerInfo = XGPush4Msdk.getDebugServerInfo(l.e());
            if (!e.a(debugServerInfo)) {
                String[] split = debugServerInfo.split(",");
                if (split.length == 2 && split[0].length() > 4) {
                    this.c = new StrategyItem(split[0], Integer.valueOf(split[1]).intValue(), strategyItem.c(), strategyItem.e(), strategyItem.d(), 0);
                }
            }
        } catch (Throwable e) {
            a.c("SocketClient", " XGPush4Msdk.getDebugServerInfo", e);
        }
        try {
            this.a = SocketChannel.open();
            this.a.configureBlocking(true);
            this.a.socket().connect(b(this.c), e.b());
            this.a.socket().setSoTimeout(e.c());
        } catch (Throwable e2) {
            th = e2;
            a.c("SocketClient", "", th);
            d();
            throw new HorseIgnoreException(strategyItem == null ? "null" : strategyItem.toString(), th);
        } catch (Throwable e22) {
            th = e22;
            a.c("SocketClient", "", th);
            d();
            throw new HorseIgnoreException(strategyItem == null ? "null" : strategyItem.toString(), th);
        }
    }

    private InetSocketAddress b(StrategyItem strategyItem) {
        if (strategyItem.d() == 1 && strategyItem.h()) {
            return new InetSocketAddress(strategyItem.c(), strategyItem.e());
        }
        return new InetSocketAddress(strategyItem.a(), strategyItem.b());
    }

    public SocketChannel a() {
        return this.a;
    }

    public void a(g gVar) {
        f fVar = new f();
        fVar.a("UTF-8");
        gVar.writeTo(fVar);
        com.tencent.android.tpush.service.channel.b.e hVar = new h(1);
        hVar.b((short) 10);
        hVar.a((short) 10);
        hVar.a(fVar.a().array());
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (this.c.d() == 0) {
                while (!hVar.b()) {
                    hVar.a(byteArrayOutputStream);
                }
            } else {
                b bVar = new b(this.c.a(), "http://" + this.c.a() + ":" + this.c.b() + d.t);
                if (this.c.h()) {
                    bVar.a("X-Online-Host", this.c.a() + ":" + this.c.b());
                }
                bVar.a(hVar);
                while (!bVar.b()) {
                    bVar.a(byteArrayOutputStream);
                }
            }
            byteArrayOutputStream.writeTo(this.a.socket().getOutputStream());
            byteArrayOutputStream.flush();
        } catch (Exception e) {
            a.c("SocketClient", "SocketClient -> send ", e);
            d();
            throw new HorseIgnoreException(e);
        } catch (Exception e2) {
            a.c("SocketClient", "SocketClient -> send ", e2);
            d();
            throw new HorseIgnoreException(e2);
        } catch (Exception e22) {
            a.c("SocketClient", "SocketClient -> send ", e22);
            d();
            throw new HorseIgnoreException(e22);
        } catch (Throwable e3) {
            a.c("SocketClient", "SocketClient -> send ", e3);
            d();
        }
    }

    private void d() {
        try {
            o oVar = (o) this.b.remove();
            if (oVar != null) {
                oVar.b(this.c);
            }
        } catch (Throwable e) {
            a.c("SocketClient", "notifyFail", e);
        }
        this.e = System.currentTimeMillis();
    }

    public void b() {
        byte[] k;
        TpnsRedirectRsp tpnsRedirectRsp;
        int i = 0;
        if (this.c == null) {
            d();
            throw new HorseIgnoreException("Recv() fail,because mStrategyItem is null");
        }
        o oVar;
        InputStream inputStream;
        byte[] bArr;
        InputStream byteArrayInputStream;
        if (this.c == null || this.c.d() != 0) {
            com.tencent.android.tpush.service.channel.b.a aVar = new com.tencent.android.tpush.service.channel.b.a();
            try {
                inputStream = this.a.socket().getInputStream();
                bArr = new byte[1024];
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                while (!aVar.b()) {
                    i += inputStream.read(bArr, i, bArr.length - i);
                    aVar.a(byteArrayInputStream);
                }
                if (aVar != null) {
                    if (aVar.i != null && aVar.i.size() > 0) {
                        k = ((com.tencent.android.tpush.service.channel.b.g) aVar.i.get(0)).k();
                    }
                }
                a.h(Constants.ServiceLogTag, ">> packet is null or packet.recvPackets is null");
                d();
                return;
            } catch (Exception e) {
                a.c(Constants.ServiceLogTag, "", e);
                d();
                throw new HorseIgnoreException(e);
            } catch (Exception e2) {
                a.c(Constants.ServiceLogTag, "", e2);
                d();
                throw new HorseIgnoreException(e2);
            } catch (Exception e22) {
                a.c(Constants.ServiceLogTag, "", e22);
                d();
                throw new HorseIgnoreException(e22);
            } catch (Exception e222) {
                a.c(Constants.ServiceLogTag, "", e222);
                d();
                throw new HorseIgnoreException(e222);
            } catch (Throwable e3) {
                a.c(Constants.ServiceLogTag, "", e3);
                d();
                k = null;
                if (k == null) {
                    a.h(Constants.ServiceLogTag, ">> dataBuffer is null");
                    d();
                    return;
                }
                com.f.a.a.e eVar = new com.f.a.a.e(k);
                eVar.a("UTF-8");
                tpnsRedirectRsp = new TpnsRedirectRsp();
                tpnsRedirectRsp.readFrom(eVar);
                try {
                    oVar = (o) this.b.remove();
                } catch (Throwable e32) {
                    a.c(Constants.ServiceLogTag, "callBacks.remove()", e32);
                    oVar = null;
                }
                if (oVar != null) {
                    CharSequence a;
                    a = e.a(tpnsRedirectRsp.ip);
                    int i2 = tpnsRedirectRsp.port;
                    StrategyItem strategyItem = new StrategyItem(a, i2, this.c.c(), this.c.e(), this.c.d(), this.c.f());
                    if (!!TextUtils.isEmpty(a)) {
                    }
                    if (oVar != null) {
                        oVar.a(this.c);
                    }
                }
                this.e = System.currentTimeMillis();
            }
        }
        com.tencent.android.tpush.service.channel.b.g gVar = new com.tencent.android.tpush.service.channel.b.g();
        try {
            inputStream = this.a.socket().getInputStream();
            bArr = new byte[1024];
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            while (!gVar.b()) {
                i = inputStream.read(bArr, i, bArr.length - i);
                gVar.a(byteArrayInputStream);
            }
            k = gVar.k();
        } catch (Exception e2222) {
            a.c("SocketClient", "SocketClient -> recv ", e2222);
            d();
            throw new HorseIgnoreException(e2222);
        } catch (Exception e22222) {
            a.c("SocketClient", "SocketClient -> recv ", e22222);
            d();
            throw new HorseIgnoreException(e22222);
        } catch (Exception e222222) {
            a.c("SocketClient", "SocketClient -> recv ", e222222);
            d();
            throw new HorseIgnoreException(e222222);
        } catch (Exception e2222222) {
            a.c("SocketClient", "SocketClient -> recv ", e2222222);
            d();
            throw new HorseIgnoreException(e2222222);
        } catch (Throwable e322) {
            a.c("SocketClient", "SocketClient -> recv ", e322);
            d();
            k = null;
            if (k == null) {
                com.f.a.a.e eVar2 = new com.f.a.a.e(k);
                eVar2.a("UTF-8");
                tpnsRedirectRsp = new TpnsRedirectRsp();
                tpnsRedirectRsp.readFrom(eVar2);
                oVar = (o) this.b.remove();
                if (oVar != null) {
                    a = e.a(tpnsRedirectRsp.ip);
                    int i22 = tpnsRedirectRsp.port;
                    StrategyItem strategyItem2 = new StrategyItem(a, i22, this.c.c(), this.c.e(), this.c.d(), this.c.f());
                    if (!TextUtils.isEmpty(a)) {
                    }
                    if (oVar != null) {
                        oVar.a(this.c);
                    }
                }
                this.e = System.currentTimeMillis();
            }
            a.h(Constants.ServiceLogTag, ">> dataBuffer is null");
            d();
            return;
        }
        if (k == null) {
            a.h(Constants.ServiceLogTag, ">> dataBuffer is null");
            d();
            return;
        }
        com.f.a.a.e eVar22 = new com.f.a.a.e(k);
        eVar22.a("UTF-8");
        tpnsRedirectRsp = new TpnsRedirectRsp();
        tpnsRedirectRsp.readFrom(eVar22);
        oVar = (o) this.b.remove();
        if (oVar != null) {
            a = e.a(tpnsRedirectRsp.ip);
            int i222 = tpnsRedirectRsp.port;
            StrategyItem strategyItem22 = new StrategyItem(a, i222, this.c.c(), this.c.e(), this.c.d(), this.c.f());
            if (!TextUtils.isEmpty(a) && i222 != 0) {
                strategyItem22.a(1);
                if (oVar != null) {
                    oVar.a(this.c, strategyItem22);
                }
            } else if (oVar != null) {
                oVar.a(this.c);
            }
        }
        this.e = System.currentTimeMillis();
    }

    public void c() {
        try {
            this.a.close();
            this.b.clear();
        } catch (Throwable e) {
            a.c("SocketClient", "mSocketChannel.close()", e);
        }
    }

    public void a(o oVar) {
        try {
            this.b.add(oVar);
        } catch (Throwable e) {
            a.c("SocketClient", d.I, e);
        }
    }
}
