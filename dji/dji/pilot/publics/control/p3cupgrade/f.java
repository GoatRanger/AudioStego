package dji.pilot.publics.control.p3cupgrade;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.login.widget.ToolTipPopup;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DataCommonGetPushUpgradeStatusInfo;
import dji.midware.data.model.P3.DataCommonRequestReceiveData;
import dji.midware.data.model.P3.DataCommonRequestUpgrade;
import dji.midware.data.model.P3.DataCommonTranslateComplete;
import dji.pilot.R;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class f implements dji.pilot.publics.control.upgrade.c.b {
    public static final String a = "UpgradeP3cFtpModeTask";
    private static String g;
    private String b;
    private String c;
    private String d;
    private dji.pilot.publics.control.upgrade.e.a e;
    private g f;
    private dji.pilot.publics.control.p3cupgrade.b.g h;
    private ProductType i;
    private e j;
    private boolean k = false;
    private boolean l = false;
    private boolean m;
    private boolean n = false;
    private int o;
    private b p;
    private Runnable q;
    private boolean r = false;

    public static class a {
        public int a;
        public String b;
        public f c;

        public a(f fVar, int i, String str) {
            this.a = i;
            this.b = str;
            this.c = fVar;
        }
    }

    private class b extends Handler {
        final /* synthetic */ f a;

        public b(f fVar, Looper looper) {
            this.a = fVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (f.values()[message.what]) {
                case MSG_ID_INIT:
                    this.a.f = g.INITING;
                    this.a.h();
                    this.a.a("固件升级初始化");
                    break;
                case MSG_ID_START:
                    if (this.a.f == g.INITING) {
                        this.a.f = g.CHECKING_STATUS;
                        this.a.k();
                        this.a.a("固件检查是否正常，是否需要升级");
                        break;
                    }
                    break;
                case MSG_ID_CHECK_STATUS_FAILS:
                    if (this.a.f == g.CHECKING_STATUS) {
                        this.a.f = g.FAILS;
                        this.a.a("检查设备状态失败");
                        break;
                    }
                    break;
                case MSG_ID_CHECK_STATUS_SUCCESS:
                    if (this.a.f == g.CHECKING_STATUS) {
                        this.a.f = g.ASSEMBLING;
                        this.a.c();
                        this.a.a("固件检查成功，开始组装升级包");
                        break;
                    }
                    break;
                case MSG_ID_ASSEMBLE_FILE_FAILS:
                    if (this.a.f == g.ASSEMBLING) {
                        this.a.f = g.FAILS;
                        this.a.a("组包失败");
                        break;
                    }
                    break;
                case MSG_ID_ASSEMBLE_FILE_SUCCESS:
                    if (this.a.f == g.ASSEMBLING) {
                        this.a.f = g.UPLOADING;
                        this.a.r();
                        this.a.a("固件组包成功，开始上传");
                        break;
                    }
                    break;
                case MSG_ID_UPLOAD_FILE_FAILS:
                    if (this.a.f == g.UPLOADING) {
                        this.a.f = g.FAILS;
                        this.a.a("上传文件失败");
                        break;
                    }
                    break;
                case MSG_ID_UPLOAD_FILE_SUCCESS:
                    if (this.a.f == g.UPLOADING) {
                        this.a.f = g.UPGRADING;
                        this.a.d();
                        this.a.a("上传成功，更新固件中");
                        break;
                    }
                    break;
                case MSG_ID_RECOVERY:
                    if (this.a.f == g.INITING) {
                        this.a.f = g.UPGRADING;
                        this.a.d();
                        this.a.a("恢复模式，等待相机推送升级数据");
                        break;
                    }
                    break;
                case MSG_ID_DEVICES_UPGRDE_FAILS:
                    if (this.a.f == g.UPGRADING) {
                        this.a.f = g.FAILS;
                        this.a.a("设备更新固件失败");
                        break;
                    }
                    break;
                case MSG_ID_DEVICES_UPGRDE_SUCCESS:
                    if (this.a.f == g.UPGRADING) {
                        this.a.f = g.SUCCESS;
                        this.a.a("设备更新固件成功");
                        break;
                    }
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }
            dji.thirdparty.a.c.a().e(this.a.f);
            e.a(f.a, String.format("%s task = %s, status = %s, msgId = %s", new Object[]{f.a, "" + this.a.h.a.groupName, "" + this.a.f, "" + r0}));
            if (this.a.f == g.FAILS || this.a.f == g.SUCCESS) {
                this.a.b();
            }
        }
    }

    public static class c {
        public String a;

        public c(String str) {
            this.a = str;
        }
    }

    public static class d {
        public String a;

        public d(String str) {
            this.a = str;
        }
    }

    public static class e {
        public int a;
    }

    public enum f {
        MSG_ID_INIT,
        MSG_ID_RECOVERY,
        MSG_ID_START,
        MSG_ID_CHECK_STATUS_FAILS,
        MSG_ID_CHECK_STATUS_SUCCESS,
        MSG_ID_ASSEMBLE_FILE_FAILS,
        MSG_ID_ASSEMBLE_FILE_SUCCESS,
        MSG_ID_UPLOAD_FILE_FAILS,
        MSG_ID_UPLOAD_FILE_SUCCESS,
        MSG_ID_DEVICES_UPGRDE_FAILS,
        MSG_ID_DEVICES_UPGRDE_SUCCESS
    }

    public enum g {
        INITING,
        START,
        CHECKING_STATUS,
        ASSEMBLING,
        UPLOADING,
        UPGRADING,
        SUCCESS,
        FAILS
    }

    public e a() {
        return this.j;
    }

    public void a(int i) {
        this.j.a = i;
        if (this.n) {
            dji.thirdparty.a.c.a().e(this.j);
        }
    }

    public void a(ProductType productType, dji.pilot.publics.control.p3cupgrade.b.g gVar, String str, String str2, boolean z) {
        this.n = true;
        this.o = 8;
        this.b = str;
        this.i = productType;
        this.c = str2;
        this.h = gVar;
        this.j = new e();
        this.m = z;
        n();
        dji.thirdparty.a.c.a().a(this);
        a(f.MSG_ID_INIT);
    }

    private void h() {
        com.dji.frame.c.d.a(this.c);
        if (this.k) {
            a(f.MSG_ID_RECOVERY);
        } else {
            a(f.MSG_ID_START);
        }
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public void a(dji.pilot.publics.control.upgrade.d dVar) {
        if (this.f == g.UPLOADING) {
            a("开始上传升级文件");
        }
    }

    public void b(dji.pilot.publics.control.upgrade.d dVar) {
        if (this.f == g.UPLOADING) {
            b("上传文件，已上传：" + dVar.k + ", 总：" + dVar.i);
            a((int) ((20 * dVar.k) / dVar.i));
            this.o--;
        }
    }

    public void a(dji.pilot.publics.control.upgrade.d dVar, int i) {
        if (this.h.a.isCameraGroup && this.f == g.UPGRADING) {
            if (this.r) {
                p();
                this.r = false;
            } else {
                a("发送相机传输完成标记成功");
                i();
            }
        }
        if (this.f == g.UPLOADING) {
            a((int) ((20 * dVar.k) / dVar.i));
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_SUCCESS.ordinal()));
        }
    }

    private void i() {
        if (this.q == null) {
            this.q = new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.f == g.UPGRADING) {
                        this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_activity_finish_fails, "等待推送返回超市"));
                        this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
                    }
                }
            };
        }
        j();
        this.p.postDelayed(this.q, com.alipay.e.a.a.c.a.a.b);
    }

    private void j() {
        if (this.q != null) {
            this.p.removeCallbacks(this.q);
        }
    }

    public void a(dji.pilot.publics.control.upgrade.d dVar, int i, String str) {
        if (this.f == g.UPLOADING) {
            if (dji.pilot.fpv.d.b.h(this.i) && this.h.a.groupName.equals(dji.sdksharedlib.b.b.a) && this.o > 0) {
                t();
            } else {
                a(String.format("上传文件失败%d, %s", new Object[]{Integer.valueOf(i), str}));
                a(new a(f.MSG_ID_UPLOAD_FILE_FAILS, R.string.v2_activity_upgrade_upload_fails, "上传文件失败"));
                this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_FAILS.ordinal()));
            }
        }
        if (this.h.a.isCameraGroup && this.f == g.UPGRADING) {
            a("发送相机传输完成标记失败");
            a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_activity_upgrade_upload_fails, "发送相机传输完成标记失败"));
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_FAILS.ordinal()));
        }
    }

    private void k() {
        if (this.i == ProductType.litchiC || this.i == ProductType.P34K) {
            m();
        } else if (!dji.pilot.fpv.d.b.h(this.i)) {
        } else {
            if (this.h.a.groupName.equals("longan368")) {
                this.p.postDelayed(new Runnable(this) {
                    final /* synthetic */ f a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.l();
                    }
                }, ToolTipPopup.a);
            } else {
                l();
            }
        }
    }

    private void l() {
        a("checkStatusLongan");
        if (ServiceManager.getInstance().isConnected() && ServiceManager.getInstance().isRemoteOK()) {
            a("checkStatusLongan 1");
            if (this.h.a.isCameraGroup || this.h.a.groupName.equals("longan368")) {
                MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
                a(this.h.a.groupName + " 模式： " + mode);
                if (mode == MODE.TAKEPHOTO) {
                    this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
                    return;
                } else {
                    DataCameraSetMode.getInstance().a(MODE.TAKEPHOTO).start(new dji.midware.e.d(this) {
                        final /* synthetic */ f a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            int i = dji.gs.c.e.b;
                            if (this.a.h.a.isCameraGroup) {
                                i = 500;
                            }
                            this.a.p.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    if (this.a.a.h.a.groupName.equals("longan368")) {
                                        this.a.a.a("368状态非拍照模式，切换状态成功 " + this.a.a.h.a.groupName);
                                    } else {
                                        this.a.a.a("相机状态非拍照模式，切换状态成功 " + this.a.a.h.a.groupName);
                                    }
                                    this.a.a.p.sendMessage(this.a.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
                                }
                            }, (long) i);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            if (this.a.m) {
                                this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
                                return;
                            }
                            if (this.a.h.a.groupName.equals("longan368")) {
                                this.a.a("368状态非拍照模式，手动切换失败:" + aVar);
                            } else {
                                this.a.a("相机状态非拍照模式，手动切换失败:" + aVar);
                            }
                            this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_camera_fails, "相机状态非拍照模式，手动切换失败"));
                            this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_FAILS.ordinal()));
                        }
                    });
                    return;
                }
            }
            a("checkStatusLongan 2");
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
            return;
        }
        this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_FAILS.ordinal()));
    }

    private void m() {
        if (!ServiceManager.getInstance().isConnected() || !ServiceManager.getInstance().isRemoteOK()) {
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_FAILS.ordinal()));
        } else if (!this.h.a.isCameraGroup) {
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
        } else if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
        } else {
            DataCameraSetMode.getInstance().a(MODE.TAKEPHOTO).start(new dji.midware.e.d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("相机状态非拍照模式，切换成拍照模式成功");
                            this.a.a.p.sendMessage(this.a.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
                        }
                    }, 2000);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (this.a.m) {
                        this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_SUCCESS.ordinal()));
                        return;
                    }
                    this.a.a("相机状态非拍照模式，手动切换失败");
                    this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_camera_fails, "相机状态非拍照模式，手动切换失败"));
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_CHECK_STATUS_FAILS.ordinal()));
                }
            });
        }
    }

    public void b() {
        o();
        dji.thirdparty.a.c.a().d(this);
        if (!dji.pilot.publics.e.d.a(this.d)) {
            dji.pilot.usercenter.f.c.e(this.d);
        }
        if (this.h.a.isCameraGroup) {
            String str = this.c + dji.pilot.usercenter.protocol.d.t + this.h.a.extraStartFile;
            if (!dji.pilot.publics.e.d.a(str)) {
                dji.pilot.usercenter.f.c.e(str);
            }
            str = null;
            if (this.i == ProductType.litchiC) {
                str = "P3C_FW_DEBUG";
            } else if (dji.pilot.fpv.d.b.h(this.i)) {
                str = "WM610_FW_DEBUG";
            } else if (this.i == ProductType.P34K) {
                str = "P3XW_FW_DEBUG";
            }
            if (str != null) {
                dji.pilot.usercenter.f.c.e(this.c + dji.pilot.usercenter.protocol.d.t + str);
            }
        }
        this.n = false;
    }

    private void n() {
        if (this.p != null) {
            o();
        }
        this.p = new b(this, g.getInstance().a());
    }

    private void o() {
        if (this.p != null) {
            this.p = null;
        }
    }

    public void c() {
        boolean a;
        int i = 0;
        dji.pilot.publics.control.upgrade.e.c a2 = dji.pilot.publics.control.upgrade.e.a(this.b, this.i, false);
        if (a2.a) {
            this.d = this.c + dji.pilot.usercenter.protocol.d.t + this.h.a.ftpDstFileName;
            File file = new File(this.d);
            if (file.exists()) {
                file.delete();
            }
            dji.pilot.publics.control.upgrade.e.a a3;
            if (this.h.a.isCameraGroup) {
                ArrayList arrayList = new ArrayList();
                Iterator it = this.h.b.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    dji.pilot.publics.control.upgrade.e.a a4 = a2.a(str);
                    if (a4 != null) {
                        arrayList.add(a4);
                    } else {
                        DJILogHelper.getInstance().LOGE(a, "FirmwareInfo info is null, device = " + str);
                    }
                }
                if (this.i == ProductType.Longan) {
                    a3 = a2.a("0800");
                    if (a3 != null) {
                        arrayList.add(a3);
                    } else {
                        DJILogHelper.getInstance().LOGE(a, "FirmwareInfo info is null, device = 0800");
                    }
                }
                a = dji.pilot.publics.control.upgrade.e.a(a2.b, arrayList, this.b, this.d);
            } else {
                a3 = a2.a((String) this.h.a.devices.get(0));
                this.e = a3;
                StringBuilder stringBuilder = new StringBuilder();
                byte[] bArr = this.e.j;
                int length = bArr.length;
                while (i < length) {
                    stringBuilder.append(", " + bArr[i]);
                    i++;
                }
                a("md5 is : " + stringBuilder.toString());
                a = dji.pilot.publics.control.upgrade.e.a(a3, this.b, this.d);
            }
        } else {
            a = false;
        }
        if (a) {
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_ASSEMBLE_FILE_SUCCESS.ordinal()));
        } else {
            this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_ASSEMBLE_FILE_FAILS.ordinal()));
        }
    }

    private void p() {
        String str = this.c + dji.pilot.usercenter.protocol.d.t + this.h.a.extraStartFile;
        File file = new File(str);
        if (file.exists() || c(str)) {
            dji.pilot.publics.control.upgrade.d dVar = new dji.pilot.publics.control.upgrade.d();
            dVar.i = file.length();
            dVar.b = this.h.a.extraStartFile;
            dVar.a = this.h.a.ftpUrl;
            dVar.g = this.h.a.ftpUsername;
            dVar.h = this.h.a.ftpPwd;
            dVar.c = str;
            dVar.l = this;
            dji.pilot.publics.control.upgrade.c.getInstance().a(dVar);
            return;
        }
        this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
        a("创建升级传输文件失败");
        a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_activity_upgrade_upload_fails, "上传文件失败"));
    }

    private void q() {
        String str;
        if (this.i == ProductType.litchiC) {
            str = "P3C_FW_DEBUG";
        } else if (this.i == ProductType.Longan) {
            str = "WM610_FW_DEBUG";
        } else if (this.i == ProductType.P34K) {
            str = "P3XW_FW_DEBUG";
        } else {
            return;
        }
        String str2 = this.c + dji.pilot.usercenter.protocol.d.t + str;
        File file = new File(str2);
        if (file.exists() || c(str2)) {
            dji.pilot.publics.control.upgrade.d dVar = new dji.pilot.publics.control.upgrade.d();
            dVar.i = file.length();
            dVar.b = str;
            dVar.a = this.h.a.ftpUrl;
            dVar.g = this.h.a.ftpUsername;
            dVar.h = this.h.a.ftpPwd;
            dVar.c = str2;
            dVar.l = this;
            dji.pilot.publics.control.upgrade.c.getInstance().a(dVar);
            this.r = true;
            return;
        }
        this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
        a("创建升级传输文件失败");
        a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_activity_upgrade_upload_fails, "上传文件失败"));
    }

    public void d() {
        if (this.h.a.isCameraGroup) {
            if (this.l) {
                q();
            } else {
                p();
            }
        } else if (dji.pilot.fpv.d.b.h(this.i) && this.h.a.groupName.equals("longan368")) {
            r0 = new DataCommonTranslateComplete();
            r1 = this.h.a.getDeviceType(0);
            int deviceModule = this.h.a.getDeviceModule(0);
            a("md5 is : " + this.e.j);
            r0.setReceiveType(r1).setReceiveId(deviceModule).setMD5(this.e.j).setTimeOut(180000).start(new dji.midware.e.d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_SUCCESS.ordinal()));
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
                    this.a.a("发送传输完成标记失败");
                    this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_activity_upgrade_upload_fails, "发送传输完成标记失败"));
                }
            });
        } else {
            r0 = new DataCommonTranslateComplete();
            r1 = this.h.a.getDeviceType(0);
            r0.setReceiveType(r1).setReceiveId(this.h.a.getDeviceModule(0)).setMD5(this.e.j).start(new dji.midware.e.d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("发送传输完成标记成功，等待更新固件进度推送");
                    this.a.i();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
                    this.a.a("发送传输完成标记失败" + aVar);
                    this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_activity_upgrade_upload_fails, "发送传输完成标记失败"));
                }
            });
        }
    }

    private void r() {
        if (dji.pilot.fpv.d.b.h(this.i) && this.h.a.groupName.equals(dji.sdksharedlib.b.b.a)) {
            DataCommonRequestUpgrade.getInstance().setReceiveType(DeviceType.CAMERA).start(new dji.midware.e.d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass9 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("相机状态非升级模式，切换成升级模式成功");
                            this.a.a.e();
                        }
                    }, 500);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.a("相机状态非下载模式，手动切换失败" + aVar);
                    if (dji.pilot.fpv.d.b.h(this.a.i) && this.a.h.a.groupName.equals(dji.sdksharedlib.b.b.a) && this.a.o > 0) {
                        this.a.t();
                        return;
                    }
                    this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_camera_fails, "相机状态非升级模式，切换成升级模式失败"));
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_FAILS.ordinal()));
                }
            });
        } else if (dji.pilot.fpv.d.b.h(this.i) && this.h.a.groupName.equals("longan368")) {
            DataCommonRequestUpgrade.getInstance().setReceiveType(DeviceType.DM368).start(new dji.midware.e.d(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a("sky368状态非升级模式，切换成升级模式成功");
                            this.a.a.s();
                        }
                    }, 10000);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.a("sky368状态非升级模式，手动切换失败");
                    this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_camera_fails, "相机状态非升级模式，切换成升级模式失败"));
                    this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_FAILS.ordinal()));
                }
            });
        } else {
            e();
        }
    }

    private void s() {
        new DataCommonRequestReceiveData().setReceiveType(DeviceType.DM368).setReceiveId(0).setDataLength(0).start(new dji.midware.e.d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a("发送文件长度成功");
                this.a.e();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_camera_fails, "发送升级文件长度失败"));
                this.a.p.sendMessage(this.a.p.obtainMessage(f.MSG_ID_UPLOAD_FILE_FAILS.ordinal()));
            }
        });
    }

    public void e() {
        dji.pilot.publics.control.upgrade.d dVar = new dji.pilot.publics.control.upgrade.d();
        dVar.i = new File(this.d).length();
        dVar.b = this.h.a.ftpDstFileName;
        dVar.a = this.h.a.ftpUrl;
        dVar.g = this.h.a.ftpUsername;
        dVar.h = this.h.a.ftpPwd;
        dVar.c = this.d;
        dVar.l = this;
        dji.pilot.publics.control.upgrade.c.getInstance().a(dVar);
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public void onEventBackgroundThread(DataCommonGetPushUpgradeStatus dataCommonGetPushUpgradeStatus) {
        if (this.f != g.UPGRADING) {
            return;
        }
        if (DeviceType.find(this.h.a.pushDevice) != dataCommonGetPushUpgradeStatus.getSenderDeviceType()) {
            a("收到推送包，但不是当前升级模块的，无视它");
            return;
        }
        i();
        DataCommonGetPushUpgradeStatusInfo descList = dataCommonGetPushUpgradeStatus.getDescList();
        if (descList == null) {
            return;
        }
        if (descList.mUpgradeState == 1) {
            a("固件校验");
        } else if (descList.mUpgradeState == 2) {
            a("用户确认：" + descList.mUserTime);
        } else if (descList.mUpgradeState == 3) {
            a("升级进行中: 总：" + descList.mUpgradeTimes + "; 当前：" + descList.mCurUpgradeIndex + "; 进度：" + descList.mUpgradeProcess);
            a(((descList.mUpgradeProcess * 80) / 100) + 20);
        } else if (descList.mUpgradeState == 4) {
            if (descList.mUpgradeResult == 1) {
                a("升级结束: 升级成功");
                a(100);
                this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_SUCCESS.ordinal()));
            } else {
                a("升级结束: 失败：" + descList.mUpgradeResult);
                a(new a(f.MSG_ID_DEVICES_UPGRDE_FAILS, R.string.v2_upgrade_tip_upgrade_fails, "升级失败"));
                this.p.sendMessage(this.p.obtainMessage(f.MSG_ID_DEVICES_UPGRDE_FAILS.ordinal()));
            }
            j();
        } else {
            a("固件推送状态返回未知码");
        }
    }

    private void a(String str) {
        g = str + this.h.a.groupName;
        e.a(a, g);
        if (this.n) {
            dji.thirdparty.a.c.a().e(new d(g));
        }
    }

    private void b(String str) {
        if (this.n) {
            dji.thirdparty.a.c.a().e(new c(str));
        }
    }

    public String f() {
        return g;
    }

    private boolean c(String str) {
        boolean z;
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                fileOutputStream.write(new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1});
                fileOutputStream.flush();
                fileOutputStream.close();
                z = true;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                z = false;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                    }
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileOutputStream = null;
            z = false;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return z;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileOutputStream = null;
            th = th4;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        return z;
    }

    public static String g() {
        return g;
    }

    private void a(a aVar) {
        if (aVar != null && this.n) {
            dji.thirdparty.a.c.a().e(aVar);
            e.a(a, "setError 有错误发生咯~~" + aVar.b);
        }
    }

    private void a(f fVar) {
        if (fVar != null && this.p != null) {
            this.p.sendEmptyMessage(fVar.ordinal());
        }
    }

    private void t() {
        a("osmo相机固件上传失败，重新上传：" + this.o);
        this.o--;
        this.p.postDelayed(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.r();
            }
        }, 2000);
    }
}
