package dji.pilot.fpv.control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.Toast;
import com.autonavi.amap.mapcore.MapCore;
import com.dji.frame.c.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraAckReceiveFiles;
import dji.midware.data.model.P3.DataCameraAckReceiveFiles.AckCcode;
import dji.midware.data.model.P3.DataCameraGetPushFiles;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraRequestSendFiles;
import dji.midware.data.model.P3.DataCameraRequestSendFiles.FILE_SELECT_MODE;
import dji.midware.data.model.P3.DataCameraSetResendFiles;
import dji.midware.data.model.P3.DataCameraVirtualKey;
import dji.midware.data.model.P3.DataCameraVirtualKey.KEY;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.f.b;
import dji.pilot.R;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class f {
    public static final String a = "DJI Album/";
    public static final String b = ".tmp";
    private boolean A = false;
    private DJIAlbumFileInfo B = new DJIAlbumFileInfo();
    private boolean C = false;
    private boolean D = true;
    private float E = 0.0f;
    private float F = 0.0f;
    private float G = 0.0f;
    private DataCameraRequestSendFiles c;
    private DataCameraGetPushPlayBackParams d;
    private int e = -1;
    private int f = 1;
    private int g = 1;
    private String h;
    private volatile boolean i = false;
    private long j;
    private long k;
    private File l;
    private File m;
    private Context n;
    private FileOutputStream o;
    private byte[] p = new byte[0];
    private int q = 0;
    private int r = 4194304;
    private int s = 200;
    private long t = 0;
    private a u;
    private Handler v = new Handler(new Callback(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.C = false;
                    break;
                case 1:
                    DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "没有数据了 需要重传 " + System.currentTimeMillis(), false, true);
                    if (!this.a.C) {
                        this.a.i();
                    }
                    this.a.v.removeMessages(3);
                    this.a.v.sendEmptyMessageDelayed(3, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                    break;
                case 2:
                    Toast.makeText(this.a.n, R.string.fpv_playback_download_exist, 0).show();
                    break;
                case 3:
                    DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "5s没有收到数据 出bug了! 强行退出!" + System.currentTimeMillis(), false, true);
                    ServiceManager.getInstance().e().resumeStatusCheck();
                    this.a.u.b();
                    DataCameraAckReceiveFiles.getInstance().setAckCcode(AckCcode.UnableReceive).start();
                    DJIVideoPackManager.getInstance().stop();
                    break;
            }
            return false;
        }
    });
    private Timer w;
    private boolean x = false;
    private int y = 0;
    private boolean z = false;

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[p.values().length];

        static {
            try {
                a[p.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[p.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public interface a {
        void a();

        void a(int i);

        void a(int i, int i2, float f);

        void a(Exception exception);

        void a(boolean z);

        void b();
    }

    public f(Context context, a aVar) {
        this.n = context;
        this.u = aVar;
        this.c = DataCameraRequestSendFiles.getInstance();
        this.d = DataCameraGetPushPlayBackParams.getInstance();
        File file = new File(d.a(context, "DJI Album/"));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void a() {
    }

    private void e() {
        this.m = new File(d.a(this.n, "DJI Album/.tmp"));
        if (this.m.exists()) {
            this.m.delete();
        }
        try {
            this.m.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void b() {
        DJIVideoPackManager.getInstance().clearVideoData();
        DataCameraAckReceiveFiles.getInstance().clear();
        c.a().a(this);
        e();
        this.z = false;
        this.A = false;
        a(false);
    }

    private void f() {
        this.o = null;
        this.l = null;
        this.q = 0;
        this.k = 0;
        this.e = -1;
        this.p = new byte[this.r];
        this.f = 1;
        int deleteChioceNum = this.d.getDeleteChioceNum();
        this.g = deleteChioceNum == 0 ? 1 : deleteChioceNum;
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "start download 总数=" + deleteChioceNum, false, true);
        DataCameraVirtualKey.getInstance().setKey(KEY.Download).start(new dji.midware.e.d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "DataCameraVirtualKey Download success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataCameraVirtualKey Download " + aVar, false, true);
            }
        });
        ServiceManager.getInstance().e().pauseStatusCheck();
        if (this.w == null) {
            this.w = new Timer();
            this.w.schedule(new TimerTask(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.F != this.a.G) {
                        this.a.u.a((int) (this.a.F / 1024.0f));
                        this.a.G = this.a.F;
                        this.a.F = 0.0f;
                    }
                }
            }, 0, 1000);
        }
    }

    private void a(boolean z) {
        DJILogHelper.getInstance().LOGE("", "resetHdConfig=" + z, false, true);
        if (!z) {
            boolean isAuto = dji.pilot.c.d.c < 0 ? DataOsdGetPushConfig.getInstance().getIsAuto() : dji.pilot.c.d.c == 1;
            this.x = isAuto;
            DJILogHelper.getInstance().LOGE("", "currentIsAuto=" + this.x, false, true);
            if (this.x) {
                b(false);
                return;
            }
            this.y = dji.pilot.c.d.d < 0 ? DataOsdGetPushConfig.getInstance().getMcs() : dji.pilot.c.d.d;
            DJILogHelper.getInstance().LOGE("", "currentMcs=" + this.y, false, true);
            if (dji.midware.f.a.getInstance().d() == b.d) {
                if (this.y != 2) {
                    a(2);
                } else {
                    f();
                }
            } else if (this.y != 3) {
                a(3);
            } else {
                f();
            }
        } else if (this.z) {
            b(this.x);
        } else if (this.A) {
            a(this.y);
        }
    }

    private void b(final boolean z) {
        DataOsdSetConfig.getInstance().a(z).start(new dji.midware.e.d(this) {
            final /* synthetic */ f b;

            public void onSuccess(Object obj) {
                this.b.z = true;
                DJILogHelper.getInstance().LOGD("", "isAuto set ok", false, true);
                if (!z) {
                    this.b.a(2);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "isAuto set failure", false, true);
            }
        });
    }

    private void a(int i) {
        DataOsdSetConfig.getInstance().e(i).start(new dji.midware.e.d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "mcs set ok", false, true);
                if (!this.a.A) {
                    this.a.f();
                }
                this.a.A = true;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "mcs set failure", false, true);
            }
        });
    }

    public void c() {
    }

    public void d() {
        if (this.w != null) {
            this.w.cancel();
            this.w = null;
        }
        c.a().d(this);
        this.v.removeMessages(1);
        this.v.removeMessages(3);
        DataCameraAckReceiveFiles.getInstance().setAckCcode(AckCcode.UnableReceive).start();
        DJIVideoPackManager.getInstance().stop();
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "恢复 liveview", false, true);
        a(true);
    }

    private void a(final FILE_SELECT_MODE file_select_mode) {
        this.c.setMode(file_select_mode).start(new dji.midware.e.d(this) {
            final /* synthetic */ f b;

            public void onSuccess(Object obj) {
                this.b.u.a();
                DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "start " + file_select_mode + " 成功", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.u.a(new Exception("start " + aVar));
                DJILogHelper.getInstance().LOGE("DJIDownloadRemoteManager", "start " + file_select_mode + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar, false, true);
            }
        });
    }

    private String g() {
        if (this.i && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 1) {
            return this.B.b();
        }
        if (!this.h.contains("\\\\") && !this.h.contains("\\")) {
            return this.h;
        }
        String[] split = this.h.split("\\\\");
        return split[split.length - 1];
    }

    public void onEventBackgroundThread(p pVar) {
        switch (AnonymousClass7.a[pVar.ordinal()]) {
            case 2:
                DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "文件下载中 断连了", false, true);
                j();
                return;
            default:
                return;
        }
    }

    private int a(String str) {
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "name=" + str, true, true);
        String substring = str.substring(str.indexOf("DCIM") + 5, str.indexOf("MEDIA"));
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "folderIndex=" + substring, true, true);
        String substring2 = str.substring(str.indexOf("DJI_") + 4, str.indexOf("."));
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "mediaIndex=" + substring2, true, true);
        return (Integer.parseInt(substring) * MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER) + Integer.parseInt(substring2);
    }

    private String h() {
        return this.h.substring(this.h.indexOf(".") + 1);
    }

    public void onEventBackgroundThread(DataCameraAckReceiveFiles dataCameraAckReceiveFiles) {
        DJILogHelper.getInstance().LOGD("", "************************rcmode=" + dji.pilot.c.d.b);
        if (dji.pilot.c.d.b == MODE.a) {
            AckCcode ackCcode = AckCcode.Success;
            this.h = dataCameraAckReceiveFiles.getFileName();
            long fileSize = dataCameraAckReceiveFiles.getFileSize();
            int fileType = dataCameraAckReceiveFiles.getFileType();
            this.i = dataCameraAckReceiveFiles.getIsAllPath() == 1;
            DJILogHelper.getInstance().LOGD("", "************************filetype=" + fileType);
            if (fileType == 1 || fileType == 4) {
                dataCameraAckReceiveFiles.setAckCcode(AckCcode.UnableReceive).start();
                return;
            }
            if (fileSize != 0) {
                this.j = fileSize;
            }
            DJILogHelper.getInstance().LOGD("", "fileName=" + this.h + " fileSize=" + ((((float) fileSize) / 1024.0f) / 1024.0f));
            if (this.o == null && fileSize != 0) {
                if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 1 && this.h != null && this.i) {
                    long createTime = dataCameraAckReceiveFiles.getCreateTime();
                    int a = a(this.h);
                    this.B.a(createTime);
                    this.B.a = fileSize;
                    this.B.d = a;
                    this.B.j = dji.logic.album.a.b.f.find(h());
                    DJILogHelper.getInstance().LOGD("", "************************length=" + fileSize);
                    DJILogHelper.getInstance().LOGD("", "************************index=" + a);
                    DJILogHelper.getInstance().LOGD("", "************************getNameKey=" + this.B.b());
                }
                if (this.D && !this.v.hasMessages(1)) {
                    this.v.sendEmptyMessageDelayed(1, 2000);
                }
                try {
                    if (this.o != null) {
                        this.o.close();
                    }
                    this.o = new FileOutputStream(this.m);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.u.a(e);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (ackCcode.value() == AckCcode.Success.value() && fileSize != 0) {
                DJIVideoPackManager.getInstance().start();
                DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "暂停 liveview");
            }
            dataCameraAckReceiveFiles.setAckCcode(ackCcode).start();
            if (this.f == this.g + 1) {
                DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "文件总数已经达到");
                j();
            }
        }
    }

    private synchronized void i() {
        this.C = true;
        this.v.sendEmptyMessageDelayed(0, 2000);
        DJIVideoPackManager.getInstance().clearVideoData();
        DataCameraSetResendFiles.getInstance().a(this.e + 1).start(null);
    }

    private void j() {
        this.v.removeMessages(1);
        this.v.removeMessages(3);
        this.u.b();
        ServiceManager.getInstance().e().resumeStatusCheck();
        DJIVideoPackManager.getInstance().stop();
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "文件列表全部发送完成", false, true);
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "恢复 liveview", false, true);
    }

    private void k() {
        this.v.removeMessages(1);
        long currentTimeMillis = System.currentTimeMillis();
        this.t = currentTimeMillis - this.t;
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "当前文件接收耗时=" + this.t + "ms", false, false);
        try {
            this.o.write(this.p, 0, this.q);
            this.o.flush();
            this.o.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.u.a(e);
        }
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "当前文件最后一部分缓冲区写入耗时=" + (System.currentTimeMillis() - currentTimeMillis) + "ms", false, false);
        this.l = new File(d.a(this.n, "DJI Album/" + g()));
        if (!this.l.exists()) {
            try {
                this.l.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.m.renameTo(this.l);
        e();
        l();
        this.l = null;
        this.o = null;
        this.q = 0;
        this.k = 0;
        this.e = -1;
        DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "进入下一个文件", false, false);
        if (this.f == this.g) {
            DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "文件总数已经达到", false, false);
        }
        this.f++;
        a(FILE_SELECT_MODE.NEXT);
    }

    private void l() {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(this.l));
        this.n.sendBroadcast(intent);
        dji.pilot.usercenter.b.a.getInstance().b(this.l);
    }

    public static void a(Context context) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(d.a(context, "DOWNLOAD/DJI_0025.JPG"))));
        context.sendBroadcast(intent);
    }

    private void m() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.o.write(this.p, 0, this.q);
            this.o.flush();
        } catch (Exception e) {
            e.printStackTrace();
            this.u.a(e);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        this.q = 0;
    }

    public void onEventBackgroundThread(DataCameraGetPushFiles dataCameraGetPushFiles) {
        this.v.removeMessages(1);
        this.v.removeMessages(3);
        if (dji.pilot.c.d.b == MODE.a) {
            this.D = true;
            int index = dataCameraGetPushFiles.getIndex();
            if (this.e + 1 == index) {
                if (this.v.hasMessages(0)) {
                    this.v.removeMessages(0);
                }
                this.C = false;
                if (index == 0) {
                    this.t = System.currentTimeMillis();
                }
                this.e = index;
                Object data = dataCameraGetPushFiles.getData();
                if (this.q + data.length > this.r) {
                    m();
                }
                System.arraycopy(data, 0, this.p, this.q, data.length);
                this.F += (float) data.length;
                this.q += data.length;
                this.k = ((long) data.length) + this.k;
                float f = (((((float) this.k) * 1.0f) / ((float) this.j)) + (((float) (this.f - 1)) * 1.0f)) / ((float) this.g);
                if (this.E != f) {
                    this.u.a(this.f, this.g, f);
                    this.E = f;
                }
                if (this.j <= this.k) {
                    this.q -= (int) (this.k - this.j);
                    DJILogHelper.getInstance().LOGD("DJIDownloadRemoteManager", "MD5达到预期");
                    k();
                    this.D = false;
                }
            } else if (!this.C) {
                i();
            }
            if (this.D && !this.v.hasMessages(1)) {
                this.v.sendEmptyMessageDelayed(1, (long) this.s);
            }
        }
    }
}
