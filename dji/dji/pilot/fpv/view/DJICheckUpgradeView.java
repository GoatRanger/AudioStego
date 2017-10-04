package dji.pilot.fpv.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.Data1860GetPushCheckStatus;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.FirmErrorType;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushCheckStatus;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataEyeGetPushSensorException;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetHdvtPushException;
import dji.midware.data.model.P3.DataOsdGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.IMU_INITFAIL_REASON;
import dji.pilot.R;
import dji.pilot.fpv.d.c$a;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.e.d;
import dji.pilot.publics.widget.DJIStateButton;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIListView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.Locale;

public class DJICheckUpgradeView extends DJIRelativeLayout implements c$a, dji.pilot.fpv.view.DJIStageView.a {
    private static final int C = 0;
    private static final int D = 1;
    private static final int E = 2;
    private boolean A = false;
    private OnClickListener B;
    private DJIListView a = null;
    private DJILinearLayout n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private a q = null;
    private String[] r;
    private String[] s = null;
    private final ArrayList<b> t;
    private final ArrayList<b> u = new ArrayList(0);
    private Context v;
    private IMU_INITFAIL_REASON w = IMU_INITFAIL_REASON.None;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[IMU_INITFAIL_REASON.values().length];

        static {
            try {
                a[IMU_INITFAIL_REASON.GyroDead.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[IMU_INITFAIL_REASON.AcceDead.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[IMU_INITFAIL_REASON.CompassDead.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[IMU_INITFAIL_REASON.BarometerDead.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[IMU_INITFAIL_REASON.BarometerNegative.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[IMU_INITFAIL_REASON.CompassModTooLarge.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[IMU_INITFAIL_REASON.GyroBiasTooLarge.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[IMU_INITFAIL_REASON.AcceBiasTooLarge.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[IMU_INITFAIL_REASON.CompassNoiseTooLarge.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[IMU_INITFAIL_REASON.BarometerNoiseTooLarge.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[IMU_INITFAIL_REASON.WaitingMcStationary.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[IMU_INITFAIL_REASON.AcceMoveTooLarge.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[IMU_INITFAIL_REASON.McHeaderMoved.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[IMU_INITFAIL_REASON.McVirbrated.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    private final class a extends BaseAdapter {
        final /* synthetic */ DJICheckUpgradeView a;
        private final LayoutInflater b;

        private a(DJICheckUpgradeView dJICheckUpgradeView, Context context) {
            this.a = dJICheckUpgradeView;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return this.a.u.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public int getItemViewType(int i) {
            b bVar = (b) this.a.u.get(i);
            if (bVar == null || !"rc".equals(bVar.e)) {
                return 0;
            }
            return 1;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            View view2;
            int itemViewType = getItemViewType(i);
            if (view != null) {
                if (itemViewType == 0) {
                    cVar = (c) view.getTag();
                    view2 = view;
                }
                cVar = null;
                view2 = view;
            } else if (itemViewType == 0) {
                c cVar2 = new c();
                view = this.b.inflate(R.layout.fpv_checkupgrade_item, null);
                cVar2.a = (DJITextView) view.findViewById(R.id.zp);
                cVar2.b = (DJITextView) view.findViewById(R.id.zr);
                cVar2.c = (DJIImageView) view.findViewById(R.id.zq);
                cVar2.d = (DJIStateButton) view.findViewById(R.id.zo);
                view.setTag(cVar2);
                cVar = cVar2;
                view2 = view;
            } else {
                if (itemViewType == 1) {
                    cVar = null;
                    view2 = this.b.inflate(R.layout.fpv_rc_upgrade_view, null);
                }
                cVar = null;
                view2 = view;
            }
            if (itemViewType == 1) {
                ((DJIRcUpgradeView) view2).handleRcStatus();
            } else if (itemViewType == 0) {
                b bVar = (b) this.a.u.get(i);
                cVar.a.setText(bVar.a);
                if (bVar.c) {
                    view2.setVisibility(0);
                    if (bVar.d || d.a(bVar.b)) {
                        cVar.b.go();
                        cVar.c.go();
                        if (this.a.hasVideo() && bVar.d) {
                            cVar.d.setVisibility(0);
                            cVar.d.setOnClickListener(this.a.B);
                        } else {
                            cVar.d.setVisibility(8);
                        }
                    } else {
                        cVar.b.setGravity(19);
                        cVar.b.setText(bVar.b);
                        cVar.b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_list_repair, 0, 0, 0);
                        cVar.b.show();
                        cVar.c.show();
                        cVar.d.setVisibility(8);
                    }
                } else {
                    view2.setVisibility(8);
                }
            }
            return view2;
        }
    }

    private static final class b {
        public String a;
        public String b;
        public boolean c;
        public boolean d;
        public String e;

        private b() {
            this.a = "";
            this.b = "";
            this.c = false;
            this.d = false;
            this.e = null;
        }
    }

    private static final class c {
        public DJITextView a;
        public DJITextView b;
        public DJIImageView c;
        public DJIStateButton d;

        private c() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public DJICheckUpgradeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = context;
        this.r = context.getResources().getStringArray(R.array.c7);
        this.s = context.getResources().getStringArray(R.array.c8);
        this.t = new ArrayList(this.r.length + 1);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.B = new OnClickListener(this) {
                final /* synthetic */ DJICheckUpgradeView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view.getId() == R.id.zo) {
                        this.a.a();
                    }
                }
            };
            b();
            this.q = new a(getContext());
            this.a = (DJIListView) findViewById(R.id.wy);
            this.a.setAdapter(this.q);
            this.n = (DJILinearLayout) findViewById(R.id.wv);
            this.o = (DJITextView) findViewById(R.id.ww);
            this.p = (DJITextView) findViewById(R.id.wx);
            c();
        }
    }

    private void a() {
        String str = "https://www.skypixel.com/videos/3-en-2-mp4";
        ProductType c = i.getInstance().c();
        if (Locale.CHINA.getLanguage().equals(Locale.getDefault().getLanguage())) {
            if (c == ProductType.litchiS || c == ProductType.litchiX) {
                str = "https://www.skypixel.com/videos/3-cn-mp4-2eb3541a-4a59-4c86-93d9-43ea46e073d7";
            } else if (c == ProductType.litchiC) {
                str = "https://www.skypixel.com/videos/p3c-mp4-72162f06-0aa4-4a66-bc3e-1bcf420dac32";
            } else if (c == ProductType.Orange) {
                str = "https://www.skypixel.com/videos/mp4-3178edc5-523f-4063-870b-5f64162d9b7c";
            }
        } else if (c == ProductType.litchiS || c == ProductType.litchiX) {
            str = "https://www.skypixel.com/videos/3-en-2-mp4";
        } else if (c == ProductType.litchiC) {
            str = "https://www.skypixel.com/videos/phantom-3-tutorials-upgrading-the-firmware-on-the-phantom-3-standard-mp4";
        } else if (c == ProductType.Orange) {
            str = "https://www.skypixel.com/videos/inspire-1-tutorials-upgrading-firmware-on-the-inspire-1-mp4";
        }
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    private void b() {
        for (int i = 0; i < this.r.length; i++) {
            b bVar = new b();
            bVar.a = this.r[i];
            bVar.b = this.s[i];
            this.t.add(i, bVar);
        }
        b bVar2 = new b();
        bVar2.c = false;
        bVar2.a = "";
        bVar2.b = "";
        this.t.add(this.r.length, bVar2);
    }

    public void dispatchOnStart() {
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnStop() {
        dji.thirdparty.a.c.a().d(this);
    }

    public void onEventMainThread(DataEyeGetPushSensorException dataEyeGetPushSensorException) {
        ((b) this.t.get(71)).c = dataEyeGetPushSensorException.isLeft3DTOFAbnormal();
        ((b) this.t.get(72)).c = dataEyeGetPushSensorException.isRight3DTOFAbnormal();
        c();
    }

    public void onEventMainThread(DataOsdGetHdvtPushException dataOsdGetHdvtPushException) {
        ((b) this.t.get(65)).c = dataOsdGetHdvtPushException.getUavRfStatus();
        ((b) this.t.get(66)).c = dataOsdGetHdvtPushException.getGndRfStatus();
        ((b) this.t.get(67)).c = dataOsdGetHdvtPushException.getChannelEncryptStatus();
        c();
    }

    public void onEventMainThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        boolean z = false;
        ((b) this.t.get(50)).c = data2100GetPushCheckStatus.isForeSightLeftAbnormal();
        ((b) this.t.get(51)).c = data2100GetPushCheckStatus.isForeSightRightAbnormal();
        ((b) this.t.get(52)).c = data2100GetPushCheckStatus.isDownSightLeftAbnormal();
        ((b) this.t.get(53)).c = data2100GetPushCheckStatus.isDownSightRightAbnormal();
        ((b) this.t.get(54)).c = data2100GetPushCheckStatus.isBackSightLeftAbnormal();
        ((b) this.t.get(55)).c = data2100GetPushCheckStatus.isBackSightRightAbnormal();
        ((b) this.t.get(56)).c = data2100GetPushCheckStatus.isDownSightDemarkAbnormal();
        ((b) this.t.get(57)).c = data2100GetPushCheckStatus.isForeSightDemarkAbnormal();
        ((b) this.t.get(58)).c = data2100GetPushCheckStatus.isBackSightDemarkAbnormal();
        b bVar = (b) this.t.get(59);
        boolean z2 = data2100GetPushCheckStatus.is1860UsbAbnormal() || data2100GetPushCheckStatus.isMCUARTAbnormal() || data2100GetPushCheckStatus.isSwaveAbnormal() || data2100GetPushCheckStatus.isMCUARTSendAbnormal() || data2100GetPushCheckStatus.isCPLDConnAbnormal();
        bVar.c = z2;
        bVar = (b) this.t.get(60);
        if (data2100GetPushCheckStatus.isAutoExpAbnormal() || data2100GetPushCheckStatus.isDepthImageAbnormal() || data2100GetPushCheckStatus.isVOAbnormal() || data2100GetPushCheckStatus.isAvoidanceAbnormal()) {
            z2 = true;
        } else {
            z2 = false;
        }
        bVar.c = z2;
        bVar = (b) this.t.get(61);
        if (data2100GetPushCheckStatus.isStoreAbnormal() || data2100GetPushCheckStatus.isInnerAbnormal() || data2100GetPushCheckStatus.isLRTAbnormal()) {
            z = true;
        }
        bVar.c = z;
        ((b) this.t.get(62)).c = data2100GetPushCheckStatus.isPropellerCover();
        ((b) this.t.get(63)).c = data2100GetPushCheckStatus.isEasySelfCalResult();
        ((b) this.t.get(64)).c = data2100GetPushCheckStatus.needPcCalibrate();
        c();
    }

    public void onEventMainThread(Data1860GetPushCheckStatus data1860GetPushCheckStatus) {
        boolean hasException = data1860GetPushCheckStatus.hasException();
        boolean isCPLDI2CAbnormal = data1860GetPushCheckStatus.isCPLDI2CAbnormal();
        boolean isVisualSPIAbnormal = data1860GetPushCheckStatus.isVisualSPIAbnormal();
        if (this.y != hasException || this.z != isCPLDI2CAbnormal || this.A != isVisualSPIAbnormal) {
            this.y = hasException;
            this.z = isCPLDI2CAbnormal;
            this.A = isVisualSPIAbnormal;
            if (hasException) {
                ((b) this.t.get(49)).c = true;
                ((b) this.t.get(49)).a += String.format(Locale.US, "(0x%x)", new Object[]{Integer.valueOf(data1860GetPushCheckStatus.getStatus())});
                if (isCPLDI2CAbnormal || isVisualSPIAbnormal) {
                    ((b) this.t.get(49)).b = this.v.getResources().getString(R.string.check_1860_return_solution);
                } else {
                    ((b) this.t.get(49)).b = this.v.getResources().getString(R.string.check_1860_restart_solution);
                }
            } else {
                ((b) this.t.get(49)).c = false;
            }
            c();
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean isAllowImuInitfailReason = dataOsdGetPushCommon.isAllowImuInitfailReason();
        IMU_INITFAIL_REASON iMUinitFailReason = dataOsdGetPushCommon.getIMUinitFailReason();
        if (!(iMUinitFailReason == this.w && this.x == isAllowImuInitfailReason)) {
            this.w = iMUinitFailReason;
            this.x = isAllowImuInitfailReason;
            ((b) this.t.get(47)).c = false;
            if (isAllowImuInitfailReason) {
                switch (AnonymousClass2.a[iMUinitFailReason.ordinal()]) {
                    case 1:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_GyroDead);
                        break;
                    case 2:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_AcceDead);
                        break;
                    case 3:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_CompassDead);
                        break;
                    case 4:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_BarometerDead);
                        break;
                    case 5:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_BarometerNegative);
                        break;
                    case 6:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_CompassModTooLarge);
                        break;
                    case 7:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReasonGyroBiasToolLarge);
                        break;
                    case 8:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_AcceBiasTooLarge);
                        break;
                    case 9:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_CompassNoiseTooLarge);
                        break;
                    case 10:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_BarometerNoiseTooLarge);
                        break;
                    case 11:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_WaitingAircraftStationary);
                        break;
                    case 12:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_AcceMoveTooLarge);
                        break;
                    case 13:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_AircraftHeadMoved);
                        break;
                    case 14:
                        ((b) this.t.get(47)).c = true;
                        ((b) this.t.get(47)).a = this.v.getResources().getString(R.string.DJIIMUInitFailReason_AircraftVirbrated);
                        break;
                }
            }
            c();
        }
        if (!dataOsdGetPushCommon.isMotorUp()) {
            int[] a = d.a(dataOsdGetPushCommon.getMotorStartCauseNoStartAction());
            if (a[0] > 0) {
                ((b) this.t.get(this.r.length)).c = true;
                ((b) this.t.get(this.r.length)).a = this.v.getString(a[0]);
                if (a[1] > 0) {
                    ((b) this.t.get(this.r.length)).b = this.v.getString(a[1]);
                } else {
                    ((b) this.t.get(this.r.length)).b = "";
                }
                c();
            } else if (((b) this.t.get(this.r.length)).c) {
                ((b) this.t.get(this.r.length)).c = false;
                c();
            }
        }
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        int errorType = dataCenterGetPushBatteryCommon.getErrorType();
        dji.pilot.battery.a.c cVar = new dji.pilot.battery.a.c();
        cVar.a(errorType);
        ((b) this.t.get(45)).c = cVar.l() != (byte) 0;
        ((b) this.t.get(46)).c = dataCenterGetPushBatteryCommon.isNeedStudy();
        c();
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        ((b) this.t.get(42)).c = (dataFlycGetPushSmartBattery.getStatus() & 2048) == 2048;
        c();
    }

    public void onEventMainThread(DataDm368_gGetPushCheckStatus dataDm368_gGetPushCheckStatus) {
        ((b) this.t.get(40)).c = dataDm368_gGetPushCheckStatus.getEncryptStatus();
        ((b) this.t.get(41)).c = dataDm368_gGetPushCheckStatus.get68013ConnectStatus();
        if (dji.pilot.publics.e.a.c(null)) {
            ((b) this.t.get(40)).c = false;
            ((b) this.t.get(41)).c = false;
        }
        c();
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        ((b) this.t.get(37)).c = dataCameraGetPushStateInfo.getFirmUpgradeErrorState() != FirmErrorType.NO;
        ((b) this.t.get(38)).c = dataCameraGetPushStateInfo.getSensorState();
        ((b) this.t.get(39)).c = dataCameraGetPushStateInfo.getHotState();
        c();
    }

    public void onEventMainThread(DataCenterGetPushCheckStatus dataCenterGetPushCheckStatus) {
        ((b) this.t.get(34)).c = dataCenterGetPushCheckStatus.getBatteryConnectStatus();
        ((b) this.t.get(35)).c = dataCenterGetPushCheckStatus.getGpsConnectStatus();
        ((b) this.t.get(36)).c = dataCenterGetPushCheckStatus.getMcConnectStatus();
        c();
    }

    public void onEventMainThread(DataBatteryGetPushCheckStatus dataBatteryGetPushCheckStatus) {
        boolean z;
        boolean z2 = false;
        b bVar = (b) this.t.get(31);
        if (dataBatteryGetPushCheckStatus.getFirstDischargeStatus() || dataBatteryGetPushCheckStatus.getSecondDischargeStatus()) {
            z = true;
        } else {
            z = false;
        }
        bVar.c = z;
        bVar = (b) this.t.get(32);
        if (dataBatteryGetPushCheckStatus.getFirstOverheatStatus() || dataBatteryGetPushCheckStatus.getSecondOverheatStatus()) {
            z = true;
        } else {
            z = false;
        }
        bVar.c = z;
        bVar = (b) this.t.get(33);
        if (dataBatteryGetPushCheckStatus.getFirstLowheatStatus() || dataBatteryGetPushCheckStatus.getSecondLowheatStatus()) {
            z2 = true;
        }
        bVar.c = z2;
        c();
    }

    public void onEventMainThread(DataOsdGetPushCheckStatus dataOsdGetPushCheckStatus) {
        ((b) this.t.get(23)).c = dataOsdGetPushCheckStatus.getFPGAinitStatus();
        ((b) this.t.get(24)).c = dataOsdGetPushCheckStatus.get58GinitStatus();
        ((b) this.t.get(25)).c = dataOsdGetPushCheckStatus.getF330initStatus();
        ((b) this.t.get(26)).c = dataOsdGetPushCheckStatus.getGPSinitStatus();
        ((b) this.t.get(27)).c = dataOsdGetPushCheckStatus.getEncryptStatus();
        ((b) this.t.get(28)).c = dataOsdGetPushCheckStatus.getStickMiddleStatus();
        ((b) this.t.get(29)).c = dataOsdGetPushCheckStatus.getPowerStatus();
        ((b) this.t.get(30)).c = dataOsdGetPushCheckStatus.getTimeoutStatus();
        ((b) this.t.get(44)).c = dataOsdGetPushCheckStatus.getResetStatus();
        ((b) this.t.get(48)).c = dataOsdGetPushCheckStatus.isInHighTemperature();
        c();
    }

    public void onEventMainThread(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        boolean z = false;
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 4) {
            b bVar = (b) this.t.get(11);
            boolean z2 = dataFlycGetPushCheckStatus.getIMUAdvanceCaliStatus() || dataFlycGetPushCheckStatus.getIMUBasicCaliStatus();
            bVar.c = z2;
            ((b) this.t.get(12)).c = dataFlycGetPushCheckStatus.getIMUHorizontalCaliStatus();
            ((b) this.t.get(13)).c = dataFlycGetPushCheckStatus.getVersionStatus();
            ((b) this.t.get(14)).c = dataFlycGetPushCheckStatus.getIMUDirectionStatus();
            if (dataFlycGetPushCheckStatus.getIMUInitStatus() && ((b) this.t.get(15)).c != dataFlycGetPushCheckStatus.getIMUInitStatus()) {
                e.c(c$a.aS_);
            }
            ((b) this.t.get(15)).c = dataFlycGetPushCheckStatus.getIMUInitStatus();
            ((b) this.t.get(16)).c = dataFlycGetPushCheckStatus.getPressInitStatus();
            ((b) this.t.get(17)).c = dataFlycGetPushCheckStatus.getAccDataStatus();
            ((b) this.t.get(18)).c = dataFlycGetPushCheckStatus.getGyroscopeStatus();
            ((b) this.t.get(19)).c = dataFlycGetPushCheckStatus.getPressDataStatus();
            ((b) this.t.get(20)).c = dataFlycGetPushCheckStatus.getAircraftAttiStatus();
            ((b) this.t.get(21)).c = dataFlycGetPushCheckStatus.getIMUDataStatus();
            ((b) this.t.get(22)).c = dataFlycGetPushCheckStatus.getDataLoggerStatus();
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 5) {
                bVar = (b) this.t.get(43);
                if (dataFlycGetPushCheckStatus.getLastIMUAdvanceCaliStatus() || dataFlycGetPushCheckStatus.getLastIMUBasicCaliStatus()) {
                    z = true;
                }
                bVar.c = z;
            }
        } else {
            ((b) this.t.get(5)).c = dataFlycGetPushCheckStatus.getIMUAdvanceCaliStatus();
            ((b) this.t.get(6)).c = dataFlycGetPushCheckStatus.getIMUBasicCaliStatus();
            ((b) this.t.get(7)).c = dataFlycGetPushCheckStatus.getIMUHorizontalCaliStatus();
            ((b) this.t.get(8)).c = dataFlycGetPushCheckStatus.getVersionStatus();
            ((b) this.t.get(9)).c = dataFlycGetPushCheckStatus.getIMUDirectionStatus();
            if (dataFlycGetPushCheckStatus.getIMUInitStatus() && ((b) this.t.get(10)).c != dataFlycGetPushCheckStatus.getIMUInitStatus()) {
                e.c(c$a.aS_);
            }
            ((b) this.t.get(10)).c = dataFlycGetPushCheckStatus.getIMUInitStatus();
        }
        c();
    }

    public void onEventMainThread(DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus) {
        DataGimbalGetPushType instance;
        boolean z = true;
        ((b) this.t.get(0)).c = dataGimbalGetPushCheckStatus.getGyroscopeStatus();
        ((b) this.t.get(1)).c = dataGimbalGetPushCheckStatus.getPitchStatus();
        ((b) this.t.get(2)).c = dataGimbalGetPushCheckStatus.getRollStatus();
        ((b) this.t.get(3)).c = dataGimbalGetPushCheckStatus.getYawStatus();
        ((b) this.t.get(4)).c = dataGimbalGetPushCheckStatus.getDataReceiveStatus();
        if (((b) this.t.get(4)).c) {
            ProductType c = i.getInstance().c();
            if (c == ProductType.Tomato || c == ProductType.Pomato) {
                ((b) this.t.get(4)).b = this.v.getResources().getString(R.string.check_gimbal_mc_conn_tomato_solution);
            } else {
                ((b) this.t.get(4)).b = this.v.getResources().getString(R.string.check_gimbal_mc_conn_solution);
            }
        }
        if (dataGimbalGetPushCheckStatus.getIMUCalibrateMatchStatus()) {
            instance = DataGimbalGetPushType.getInstance();
        } else {
            instance = DataGimbalGetPushType.getInstance();
        }
        if (instance.isGetted() && instance.getType() == DJIGimbalType.WM220) {
            ((b) this.t.get(68)).c = dataGimbalGetPushCheckStatus.getLimitStatus() == 1;
            b bVar = (b) this.t.get(69);
            if (dataGimbalGetPushCheckStatus.getLimitStatus() != 2) {
                z = false;
            }
            bVar.c = z;
            ((b) this.t.get(70)).c = dataGimbalGetPushCheckStatus.getVibrateStatus();
        }
        c();
    }

    public void onEventMainThread(a$d dji_pilot_publics_control_a_d) {
        c();
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            int size = this.t.size();
            for (int i = 0; i < size; i++) {
                b bVar = (b) this.t.get(i);
                if (!"rc".equals(bVar.e)) {
                    bVar.c = false;
                }
            }
            c();
        }
    }

    private void c() {
        this.u.clear();
        d();
        for (int i = 0; i < this.t.size(); i++) {
            b bVar = (b) this.t.get(i);
            if (bVar.c) {
                this.u.add(bVar);
            }
        }
        this.q.notifyDataSetChanged();
    }

    public void dispatchOnResume() {
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCenterGetPushCheckStatus.getInstance());
        onEventMainThread(DataBatteryGetPushCheckStatus.getInstance());
        onEventMainThread(DataOsdGetPushCheckStatus.getInstance());
        onEventMainThread(DataFlycGetPushCheckStatus.getInstance());
        onEventMainThread(DataGimbalGetPushCheckStatus.getInstance());
        if (DataDm368_gGetPushCheckStatus.getInstance().isGetted()) {
            onEventMainThread(DataDm368_gGetPushCheckStatus.getInstance());
        }
        onEventMainThread(DataFlycGetPushSmartBattery.getInstance());
        if (DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            onEventMainThread(DataCenterGetPushBatteryCommon.getInstance());
        }
        if (Data1860GetPushCheckStatus.getInstance().isGetted()) {
            onEventMainThread(Data1860GetPushCheckStatus.getInstance());
        }
        if (Data2100GetPushCheckStatus.getInstance().isGetted()) {
            onEventMainThread(Data2100GetPushCheckStatus.getInstance());
        }
        if (DataOsdGetHdvtPushException.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetHdvtPushException.getInstance());
        }
        if (DataEyeGetPushSensorException.getInstance().isGetted()) {
            onEventMainThread(DataEyeGetPushSensorException.getInstance());
        }
    }

    private void d() {
        String h = dji.pilot.publics.control.a.getInstance().h();
        if (dji.pilot.publics.control.rc.b.getInstance().f()) {
            b bVar = new b();
            bVar.c = true;
            bVar.d = true;
            bVar.e = "rc";
            bVar.a = getContext().getResources().getString(R.string.checklist_rc_upgrade);
            this.u.add(bVar);
        }
        if (!h.contains("mc")) {
            bVar = new b();
            bVar.c = true;
            bVar.d = true;
            ProductType c = i.getInstance().c();
            if (c == ProductType.Grape2 || c == ProductType.A2) {
                bVar.a = getContext().getResources().getString(R.string.checklist_lb2_upgrade);
            } else if (c == ProductType.Tomato || c == ProductType.Pomato) {
                bVar.a = getContext().getResources().getString(R.string.checklist_p4_needupgrade);
            } else {
                bVar.a = getContext().getResources().getString(R.string.checklist_flyc_upgrade);
            }
            this.u.add(bVar);
        }
        if (!dji.pilot.fpv.d.b.h()) {
            if (!(h.contains(dji.publics.b.a.b.v) || dji.pilot.publics.e.a.g())) {
                bVar = new b();
                bVar.c = true;
                bVar.d = true;
                bVar.a = getContext().getResources().getString(R.string.checklist_camera_upgrade);
                this.u.add(bVar);
            }
            if (!h.contains("battery")) {
                bVar = new b();
                bVar.c = true;
                bVar.d = true;
                bVar.a = getContext().getResources().getString(R.string.checklist_battery_upgrade);
                this.u.add(bVar);
            }
            if (h.contains("lb2")) {
                b bVar2 = new b();
                bVar2.c = true;
                bVar2.d = true;
                bVar2.a = getContext().getResources().getString(R.string.checklist_lb2module_upgrade);
                this.u.add(bVar2);
            }
        }
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public boolean hasVideo() {
        ProductType c = i.getInstance().c();
        return c == ProductType.litchiS || c == ProductType.litchiX || c == ProductType.Orange || c == ProductType.litchiC;
    }
}
