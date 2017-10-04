package dji.pilot.active;

import android.content.Context;
import android.util.Log;
import com.alipay.sdk.h.a;
import com.dji.frame.c.h;
import com.google.api.client.http.UrlEncodedParser;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataGlassActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.pilot.R;
import dji.pilot.active.DJIActiveDeviceModel.DJIDeviceModel;
import dji.pilot.active.DJIActiveSnModel.DJISnModel;
import dji.pilot.fpv.d.c.e;
import dji.pilot.publics.c.d;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.j;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.c;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.http.entity.StringEntity;

public class DJIActiveController implements e {
    private static ArrayList<DeviceType> M = new ArrayList(4);
    private static final String j = "re1qu2es3ta4cti9vev8eri7fy";
    private static final String k = "https://active.dji.com";
    private static final String l = "Q2Zk6umfm0isNgj9EY8QdNbC";
    private static final String m = "https://active.dji.com/api/v2/activation/term";
    private static final String n = "http://staging-dji-service-cn.aasky.net/api/v2/activation/term";
    private static final String o = "DJIActiveController";
    private static final String p = "/verify";
    private static final String q = "/errorlog";
    private static final String r = "/getem";
    private boolean A = false;
    private DataFlycActiveStatus B = DataFlycActiveStatus.getInstance();
    private DataCameraActiveStatus C = DataCameraActiveStatus.getInstance();
    private DataBatteryActiveStatus D = DataBatteryActiveStatus.getInstance();
    private DataOsdActiveStatus E = DataOsdActiveStatus.getInstance();
    private DataGlassActiveStatus F = DataGlassActiveStatus.getInstance();
    private DataGimbalActiveStatus G = DataGimbalActiveStatus.getInstance();
    private c H = c.getInstance();
    private b I;
    private Context J;
    private c K;
    private b L;
    private ArrayList<DeviceType> N = new ArrayList(4);
    private DJIActiveSnModel O;
    private int P = 0;
    private boolean Q = true;
    private a R = new a();
    private a S = a.a;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y = 0;
    private int z = 0;

    public DJIActiveController(Context context, b bVar) {
        this.J = context;
        this.I = bVar;
        this.K = com.dji.frame.c.c.b(context);
        this.L = com.dji.frame.c.c.c(context);
    }

    public static void a(ArrayList<DeviceType> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            M.clear();
            M = (ArrayList) arrayList.clone();
        }
    }

    public void a(String str, int i) throws Exception {
        DJILogHelper.getInstance().LOGD(o, "errorLog 发送", false, true);
        this.R.a();
        String b = b(M);
        this.R.a = this.S.name();
        this.R.b = str;
        this.R.c = b;
        this.R.d = i + "";
        this.R.e = "activation";
        DJILogHelper.getInstance().LOGD(o, "[errorLog] \nerrorLog: " + this.R.a + "\n" + " responseJson: " + this.R.b + "\n" + " devices: " + this.R.c + "\n" + " errorCode: " + this.R.d + "\n" + " errorType: " + this.R.e, true, true);
        this.K.a("https://active.dji.com/errorlog", b(this.J, h.a(this.R)), UrlEncodedParser.CONTENT_TYPE, new 1(this));
    }

    public void a() throws Exception {
        DJILogHelper.getInstance().LOGD(o, "在线激活  start", false, true);
        this.N.clear();
        if (this.I != null) {
            this.I.a();
        }
        DJILogHelper.getInstance().LOGD(o, "getServerStatus " + M.size(), false, true);
        String b = b(M);
        Log.i(o, b);
        this.K.a("https://active.dji.com/verify", a(this.J, b), UrlEncodedParser.CONTENT_TYPE, new 6(this));
    }

    private static StringEntity a(Context context, String str) throws Exception {
        String str2 = System.currentTimeMillis() + "";
        String str3 = "date=" + str2 + "";
        String str4 = "appVersion=" + context.getString(R.string.versionname);
        String o = f.getInstance().o();
        String n = f.getInstance().n();
        str2 = (str2 + str + "android" + context.getString(R.string.versionname) + f.getInstance().j()) + o + n;
        DJILogHelper.getInstance().LOGD("signa", "--------------signnature: " + str2, false, true);
        str2 = "signature=" + j.a(str2, l);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append(a.b);
        stringBuilder.append("info=" + str);
        stringBuilder.append(a.b);
        stringBuilder.append("os=android");
        stringBuilder.append(a.b);
        stringBuilder.append(str4);
        stringBuilder.append(a.b);
        stringBuilder.append("email=" + f.getInstance().j());
        stringBuilder.append(a.b);
        stringBuilder.append("user_id=" + o);
        stringBuilder.append(a.b);
        stringBuilder.append("token=" + n);
        stringBuilder.append(a.b);
        stringBuilder.append(str2);
        return new StringEntity(stringBuilder.toString().replace("+", "%2B"), "UTF-8");
    }

    private StringEntity b(Context context, String str) throws Exception {
        String str2 = System.currentTimeMillis() + "";
        String str3 = "date=" + str2;
        String str4 = "appVersion=" + context.getString(R.string.versionname);
        String o = f.getInstance().o();
        String n = f.getInstance().n();
        str2 = (str2 + str + "android" + context.getString(R.string.versionname) + f.getInstance().j()) + o + n;
        DJILogHelper.getInstance().LOGD("signa", "---------------signnature: " + str2, false, true);
        str2 = "signature=" + j.a(str2, l);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append(a.b);
        stringBuilder.append("info=" + str);
        stringBuilder.append(a.b);
        stringBuilder.append("os=android");
        stringBuilder.append(a.b);
        stringBuilder.append(str4);
        stringBuilder.append(a.b);
        stringBuilder.append("email=" + f.getInstance().j());
        stringBuilder.append(a.b);
        stringBuilder.append("user_id=" + o);
        stringBuilder.append(a.b);
        stringBuilder.append("token=" + n);
        stringBuilder.append(a.b);
        stringBuilder.append(str2);
        String stringBuilder2 = stringBuilder.toString();
        DJILogHelper.getInstance().LOGD("", "post参数" + stringBuilder2, true, true);
        return new StringEntity(stringBuilder2, "UTF-8");
    }

    public static void a(Context context) throws Exception {
        b c = com.dji.frame.c.c.c(context);
        c b = com.dji.frame.c.c.b(context);
        List<DJIActiveLocalModel> b2 = c.b(DJIActiveLocalModel.class, "isUploaded=0", "id desc");
        DJILogHelper.getInstance().LOGD(o, "本地激活记录 count=" + b2.size(), false, true);
        if (b2.size() > 0) {
            for (DJIActiveLocalModel dJIActiveLocalModel : b2) {
                b.a("https://active.dji.com/verify", a(context, dJIActiveLocalModel.content), UrlEncodedParser.CONTENT_TYPE, new 7(dJIActiveLocalModel, c));
            }
            Thread.sleep(500);
        }
    }

    public static String a(ProductType productType) {
        return d.getInstance().a(productType).activeName;
    }

    public static String b(ProductType productType) {
        return d.getInstance().a(productType).activePlaneName;
    }

    public static ProductType a(String str) {
        return d.getInstance().a(str);
    }

    private String b(ArrayList<DeviceType> arrayList) {
        DJIActiveDeviceModel dJIActiveDeviceModel = new DJIActiveDeviceModel();
        dJIActiveDeviceModel.models = new ArrayList(4);
        String string = this.J.getString(R.string.versionname);
        String j = f.getInstance().j();
        String a = a(i.getInstance().c());
        String o = f.getInstance().o();
        String k = f.getInstance().k();
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (cameraType == CameraType.DJICameraTypeFC550) {
            a = DJIGlobalService.b ? "inspire1 pro" : "X5";
        } else if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            a = DJIGlobalService.b ? "inspire1 raw" : "X5-Raw";
        } else if (CameraType.DJICameraTypeTau336 == cameraType) {
            if (!DJIGlobalService.b) {
                a = "XT336";
            }
        } else if (CameraType.DJICameraTypeTau640 == cameraType) {
            if (!DJIGlobalService.b) {
                a = "XT640";
            }
        } else if (CameraType.DJICameraTypeCV600 == cameraType) {
            if (!DJIGlobalService.b) {
                a = "Z3";
            }
        } else if (CameraType.DJICameraTypeGD600 == cameraType && !DJIGlobalService.b) {
            a = "Z30";
        }
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        if (arrayList.contains(DeviceType.FLYC)) {
            DJIDeviceModel dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.FLYC.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.deviceName = dji.pilot2.usercenter.activate.a.getInstance().g();
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.FLYC).getFirmVer(".");
            dJIDeviceModel.sn = this.B.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.CAMERA)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.CAMERA.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.CAMERA).getFirmVer(".");
            dJIDeviceModel.sn = this.C.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.BATTERY)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.BATTERY.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.BATTERY).getFirmVer(".");
            if (dji.pilot.publics.e.a.d(null)) {
                dJIDeviceModel.sn = this.H.b();
            } else {
                dJIDeviceModel.sn = this.D.getPushSN();
            }
            DJILogHelper.getInstance().LOGD("", "bat act sn=" + dJIDeviceModel.sn, true, true);
            if (dJIDeviceModel.sn.equals("")) {
                dJIDeviceModel.sn = DJIGlobalService.i;
                DJILogHelper.getInstance().LOGD("", "bat act sn2=" + dJIDeviceModel.sn, true, true);
            }
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.OSD)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.OSD.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.OSD).getFirmVer(".");
            dJIDeviceModel.sn = this.E.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.OFDM)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.OFDM.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.OFDM).getFirmVer(".");
            dJIDeviceModel.sn = this.E.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.GLASS)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.GLASS.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.GLASS).getFirmVer(".");
            dJIDeviceModel.sn = this.F.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        if (arrayList.contains(DeviceType.GIMBAL)) {
            dJIDeviceModel = new DJIDeviceModel();
            dJIDeviceModel.deviceType = DeviceType.GIMBAL.value();
            dJIDeviceModel.email = j;
            dJIDeviceModel.productType = a;
            dJIDeviceModel.firewareVersion = dataCommonGetVersion.setDeviceType(DeviceType.GIMBAL).getFirmVer(".");
            dJIDeviceModel.sn = this.G.getPushSN();
            dJIDeviceModel.appVersion = string;
            dJIDeviceModel.uid = o;
            dJIDeviceModel.registerPhone = k;
            dJIActiveDeviceModel.models.add(dJIDeviceModel);
        }
        return h.a(dJIActiveDeviceModel.models);
    }

    private void a(DJIActiveSnModel dJIActiveSnModel) {
        this.P = 0;
        Log.i(o, "checkSnModels");
        if (dJIActiveSnModel.item == null || dJIActiveSnModel.item.size() == 0) {
            this.S = a.c;
            if (this.I != null) {
                this.I.c();
                return;
            }
            return;
        }
        Iterator it = dJIActiveSnModel.item.iterator();
        while (it.hasNext()) {
            DJISnModel dJISnModel = (DJISnModel) it.next();
            Log.i(o, "model.enabled:" + dJISnModel.enabled);
            if (dJISnModel.enabled == 0) {
                dji.pilot2.usercenter.activate.c.a("激活失败 ： sn失败 ：号码不存在或未找到");
            } else if (dJISnModel.enabled == 1) {
                dji.pilot2.usercenter.activate.c.a("sn成功 ：");
                this.P++;
            } else if (dJISnModel.enabled == 2) {
                dji.pilot2.usercenter.activate.c.a("激活失败 ：sn多次成功 ：");
                this.P++;
            } else {
                dji.pilot2.usercenter.activate.c.a("激活失败 ：sn失败 ：已存在激活记录");
            }
        }
        if (this.P == 0) {
            this.S = a.c;
            if (this.I != null) {
                this.I.c();
            }
        } else if (ServiceManager.getInstance().isConnected()) {
            it = dJIActiveSnModel.item.iterator();
            while (it.hasNext()) {
                dJISnModel = (DJISnModel) it.next();
                if (dJISnModel.enabled == 1 || dJISnModel.enabled == 2) {
                    a(dJISnModel);
                }
            }
        } else {
            this.S = a.e;
            if (this.I != null) {
                this.I.c();
            }
        }
    }

    private void b(DJIActiveSnModel dJIActiveSnModel) {
        this.P = 0;
        Log.i(o, "checkSnWifiModels");
        if (dJIActiveSnModel.item == null || dJIActiveSnModel.item.size() == 0) {
            this.S = a.c;
            if (this.I != null) {
                this.I.c();
                return;
            }
            return;
        }
        Iterator it = dJIActiveSnModel.item.iterator();
        while (it.hasNext()) {
            DJISnModel dJISnModel = (DJISnModel) it.next();
            Log.i(o, "checkSnWifiModels model.enabled:" + dJISnModel.enabled);
            if (dJISnModel.enabled == 0) {
                dji.pilot2.usercenter.activate.c.a("sn失败 ：号码不存在或未找到");
                this.S = a.d;
                if (this.I != null) {
                    this.I.c();
                }
            } else if (dJISnModel.enabled == 1) {
                DJILogHelper.getInstance().LOGD(o, "sn成功 ：", false, true);
                this.P++;
                this.S = a.f;
                if (this.I != null) {
                    this.I.d();
                }
            } else if (dJISnModel.enabled == 2) {
                this.S = a.f;
                this.P++;
                DJILogHelper.getInstance().LOGD(o, "sn多次成功 ：", false, true);
                if (this.I != null) {
                    this.I.d();
                }
            } else {
                dji.pilot2.usercenter.activate.c.a("sn失败 ：已存在激活记录");
                this.S = a.d;
                if (this.I != null) {
                    this.I.c();
                }
            }
        }
    }

    public boolean b() {
        if (this.P == 0 || this.O == null || this.O.item == null || this.P != this.O.item.size()) {
            return false;
        }
        return true;
    }

    public a c() {
        return this.S;
    }

    private void h() {
        if (!ServiceManager.getInstance().isConnected() || this.O.item == null) {
            this.S = a.e;
            if (this.I != null) {
                this.I.c();
                return;
            }
            return;
        }
        Iterator it = this.O.item.iterator();
        while (it.hasNext()) {
            DJISnModel dJISnModel = (DJISnModel) it.next();
            if (dJISnModel.enabled == 1 || dJISnModel.enabled == 2) {
                a(dJISnModel);
            }
        }
    }

    public void d() {
        this.N.clear();
        this.Q = false;
        String str = "";
        DJILogHelper.getInstance().LOGD(o, "离线激活  start", false, true);
        Iterator it = M.iterator();
        while (it.hasNext()) {
            DeviceType deviceType = (DeviceType) it.next();
            switch (5.a[deviceType.ordinal()]) {
                case 1:
                    str = this.B.getPushSN();
                    break;
                case 2:
                    str = this.C.getPushSN();
                    break;
                case 3:
                    str = this.D.getPushSN();
                    break;
                case 4:
                case 5:
                    str = this.E.getPushSN();
                    break;
                case 6:
                    str = this.F.getSN();
                    break;
                case 7:
                    str = this.G.getPushSN();
                    break;
                default:
                    break;
            }
            a(deviceType.value(), str, System.currentTimeMillis());
        }
    }

    private void a(DJISnModel dJISnModel) {
        a(dJISnModel.deviceType, dJISnModel.sn, dJISnModel.timestamp);
    }

    private void a(int i, String str, long j) {
        Calendar instance = Calendar.getInstance();
        if (j != 0) {
            instance.clear();
            instance.setTimeInMillis(j);
        }
        this.s = instance.get(1);
        this.t = instance.get(2) + 1;
        this.u = instance.get(5);
        this.v = instance.get(11);
        this.w = instance.get(12);
        this.x = instance.get(13);
        switch (5.a[DeviceType.find(i).ordinal()]) {
            case 1:
                b(str);
                return;
            case 2:
                c(str);
                return;
            case 3:
                e(str);
                return;
            case 4:
                f(str);
                return;
            case 5:
                f(str);
                return;
            case 6:
                d(str);
                return;
            case 7:
                g(str);
                return;
            default:
                return;
        }
    }

    private void b(String str) {
        Log.i(o, "activeFlyc");
        this.B.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.B.getPushSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 8(this));
    }

    private void c(String str) {
        this.C.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.C.getPushSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 9(this));
    }

    private void d(String str) {
        this.F.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.F.getSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 10(this));
    }

    private void e(String str) {
        if (dji.pilot.publics.e.a.d(null)) {
            this.D.setIndexMultiBattery(this.H.c()).setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.H.b()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 11(this));
        } else {
            this.D.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.D.getPushSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 12(this));
        }
    }

    private void f(String str) {
        this.E.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.E.getPushSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 13(this));
    }

    private void g(String str) {
        this.G.setType(dji.midware.data.model.b.a.b.SET).setActiveStatus(true).setSN(this.G.getPushSN()).setYear(this.s).setMonth(this.t).setDay(this.u).setHour(this.v).setMin(this.w).setSecond(this.x).start(new 2(this));
    }

    private void a(DeviceType deviceType) {
        this.y++;
        Log.i(o, "activeSuccessOne activeSucNum:" + this.y);
        if (deviceType == DeviceType.FLYC) {
            this.A = true;
        }
        if (deviceType == DeviceType.OFDM && dji.pilot.fpv.d.b.h(null)) {
            this.A = true;
        }
        if (deviceType == DeviceType.GIMBAL && dji.pilot2.usercenter.activate.a.getInstance().j()) {
            this.A = true;
        }
        this.N.add(deviceType);
        i();
    }

    private void b(DeviceType deviceType) {
        this.z++;
        dji.pilot2.usercenter.activate.c.a("activeFailOne activeFailNum:" + this.z);
        i();
    }

    private void i() {
        Log.i(o, "onActiveOver devices.size():" + M.size());
        if (this.Q) {
            dji.pilot2.usercenter.activate.c.a("activeSucNum:" + this.y + " activeFailNum:" + this.z);
            if (this.y + this.z != this.P) {
                return;
            }
            if (this.A && this.Q) {
                this.S = null;
                Log.i(o, "success");
                dji.pilot.fpv.d.e.c(e.eL_);
                if (this.I != null) {
                    this.I.b();
                    return;
                }
                return;
            }
            this.S = a.d;
            if (this.I != null) {
                this.I.c();
            }
        } else if (this.y + this.z != M.size()) {
        } else {
            if (this.Q || this.y <= 0) {
                this.S = a.d;
                if (this.I != null) {
                    this.I.c();
                    return;
                }
                return;
            }
            j();
            this.S = null;
            if (this.I != null) {
                this.I.b();
            }
        }
    }

    private void j() {
        String b = b(this.N);
        Object dJIActiveLocalModel = new DJIActiveLocalModel();
        dJIActiveLocalModel.content = b;
        dJIActiveLocalModel.addtime = System.currentTimeMillis();
        if (b != null && !b.equals("")) {
            this.L.a(dJIActiveLocalModel);
        }
    }

    public void e() {
        if (this.S != null) {
            switch (5.b[this.S.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    try {
                        a();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                case 4:
                case 5:
                    a(this.O);
                    break;
                case 6:
                    h();
                    break;
            }
            if (this.I != null) {
                this.I.a();
            }
        } else if (this.I != null) {
            this.I.b();
        }
    }

    public boolean f() {
        dji.midware.data.model.b.a aVar;
        ProductType c = i.getInstance().c();
        if (dji.logic.f.d.c(c)) {
            aVar = this.G;
        } else if (dji.pilot.fpv.d.b.h(c)) {
            aVar = this.E;
        } else {
            aVar = this.B;
        }
        String pushSN = aVar.getPushSN();
        if (pushSN.equals("FFFFFFFFFF")) {
            this.S = a.g;
            dji.pilot2.usercenter.activate.c.a("checkoutLoaclInfo sn error data=" + pushSN);
            if (this.I != null) {
                this.I.c();
                return false;
            }
        }
        return true;
    }

    private StringEntity a(Context context, String str, String str2, String str3) throws Exception {
        String str4 = "android";
        String string = context.getString(R.string.versionname);
        String pushSN = DataFlycActiveStatus.getInstance().getPushSN();
        String _name = i.getInstance().c()._name();
        String n = f.getInstance().n();
        String str5 = "os=" + str4;
        String str6 = "app_version=" + string;
        String str7 = "term_version=" + str2;
        String str8 = "pp_version=" + str3;
        String str9 = "sn=" + pushSN;
        String str10 = "product_type=" + _name;
        String str11 = "firmware_version=" + str;
        String str12 = "token=" + n;
        str4 = n + str2 + pushSN + _name + str + str4 + string + str3;
        DJILogHelper.getInstance().LOGD("signa", "--------------signnature: " + str4, false, true);
        str4 = "signature=" + j.a(str4, l);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str12);
        stringBuilder.append(a.b);
        stringBuilder.append(str7);
        stringBuilder.append(a.b);
        stringBuilder.append(str9);
        stringBuilder.append(a.b);
        stringBuilder.append(str10);
        stringBuilder.append(a.b);
        stringBuilder.append(str11);
        stringBuilder.append(a.b);
        stringBuilder.append(str5);
        stringBuilder.append(a.b);
        stringBuilder.append(str6);
        stringBuilder.append(a.b);
        stringBuilder.append(str8);
        stringBuilder.append(a.b);
        stringBuilder.append(str4);
        return new StringEntity(stringBuilder.toString(), "UTF-8");
    }

    public void a(String str, String str2) {
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.FLYC).start(new 3(this, dataCommonGetVersion, str, str2), 200, 3);
    }

    private void a(String str, String str2, String str3) throws Exception {
        this.K.a(m, a(this.J, str, str2, str3), UrlEncodedParser.CONTENT_TYPE, new 4(this));
    }
}
