package dji.pilot.fpv.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_COLOR;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_REASON;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LedStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DJILightStatusView extends DJILinearLayout {
    private static final boolean a = false;
    private static final int b = 4096;
    private DJIImageView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private LED_REASON f = null;
    private List<LedStatus> g = null;
    private boolean h = false;
    private FLYC_STATE i = FLYC_STATE.OTHER;
    private boolean j = false;
    private int k = 0;
    private a l = null;

    private static final class a extends Handler {
        private final WeakReference<DJILightStatusView> a;

        private a(DJILightStatusView dJILightStatusView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJILightStatusView);
        }

        public void handleMessage(Message message) {
            DJILightStatusView dJILightStatusView = (DJILightStatusView) this.a.get();
            if (dJILightStatusView != null) {
                switch (message.what) {
                    case 4096:
                        dJILightStatusView.updateLedStatus();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJILightStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private int a(LED_COLOR led_color) {
        if (LED_COLOR.RED == led_color) {
            return R.drawable.light_red;
        }
        if (LED_COLOR.GREEN == led_color) {
            return R.drawable.light_green;
        }
        if (LED_COLOR.BLUE == led_color) {
            return R.drawable.light_blue;
        }
        if (LED_COLOR.YELLOW == led_color) {
            return R.drawable.light_yellow;
        }
        if (LED_COLOR.DEEP_RED == led_color) {
            return R.drawable.light_deep_red;
        }
        if (LED_COLOR.CYAN == led_color) {
            return R.drawable.light_cyan;
        }
        if (LED_COLOR.PURPLE == led_color || LED_COLOR.PURPLE1 == led_color || LED_COLOR.PURPLE2 == led_color || LED_COLOR.PURPLE3 == led_color) {
            return R.drawable.light_purple;
        }
        if (LED_COLOR.WHITE == led_color) {
            return R.drawable.light_white;
        }
        return R.drawable.light_normal;
    }

    private boolean a(List<LedStatus> list, List<LedStatus> list2) {
        if (list != list2) {
            if (list == null || list2 == null) {
                return false;
            }
            int size = list.size();
            if (size != list2.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                LedStatus ledStatus = (LedStatus) list.get(i);
                LedStatus ledStatus2 = (LedStatus) list2.get(i);
                if (ledStatus.mColor != ledStatus2.mColor || ledStatus.mInterval != ledStatus2.mInterval) {
                    return false;
                }
            }
        }
        return true;
    }

    private int a(LED_REASON led_reason) {
        int a = b.a(led_reason);
        if (led_reason == null) {
            return a;
        }
        if (a != R.string.fpv_led_normal && a != R.string.fpv_led_fdi_vibrate) {
            return a;
        }
        int a2 = b.a(this.i, this.j);
        if (a2 == R.string.ctrl_mode_popti) {
            if (this.h) {
                return R.string.fpv_led_ioc_opti;
            }
            return R.string.fpv_led_opti;
        } else if (a2 == R.string.ctrl_mode_pgps) {
            if (this.h) {
                return R.string.fpv_led_ioc_gps;
            }
            return R.string.fpv_led_gps;
        } else if (a2 != R.string.ctrl_mode_patti && a2 != R.string.ctrl_mode_atti) {
            return a;
        } else {
            if (this.h) {
                return R.string.fpv_led_ioc_atti;
            }
            return R.string.fpv_led_atti;
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean isIOCEnabled = dataOsdGetPushHome.isIOCEnabled();
        if (this.h != isIOCEnabled) {
            this.h = isIOCEnabled;
            if (this.f == LED_REASON.NORMAL_LED || this.f == LED_REASON.FDI_VIBRATE) {
                a(a(this.f));
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        boolean isVisionUsed = dataOsdGetPushCommon.isVisionUsed();
        if (this.i != flycState || this.j != isVisionUsed) {
            this.i = flycState;
            this.j = isVisionUsed;
            if (this.f == LED_REASON.NORMAL_LED || this.f == LED_REASON.FDI_VIBRATE) {
                a(a(this.f));
            }
        }
    }

    public void onEventMainThread(DataFlycGetPushLedStatus dataFlycGetPushLedStatus) {
        if (b.c()) {
            updateLedStatus();
            return;
        }
        LED_REASON ledReason = dataFlycGetPushLedStatus.getLedReason();
        List ledStatus = dataFlycGetPushLedStatus.getLedStatus();
        if (ledReason != this.f || !a(ledStatus, this.g)) {
            this.l.removeMessages(4096);
            this.f = ledReason;
            this.g = dataFlycGetPushLedStatus.getLedStatus();
            a(a(ledReason));
            this.k = 0;
            updateLedStatus();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            a();
            a((int) R.string.fpv_led_normal);
            updateLedStatus();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            a();
            a((int) R.string.fpv_led_normal);
            updateLedStatus();
        }
    }

    public void onEventMainThread(dji.setting.ui.flyc.SdModeView.a aVar) {
        if (aVar == dji.setting.ui.flyc.SdModeView.a.SUCCESS) {
            a();
            a((int) R.string.fpv_led_normal);
            updateLedStatus();
        }
    }

    private void a() {
        this.l.removeMessages(4096);
        this.f = null;
        this.g = null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        onEventMainThread(DataOsdGetPushHome.getInstance());
        onEventMainThread(DataOsdGetPushCommon.getInstance());
        onEventMainThread(DataFlycGetPushLedStatus.getInstance());
    }

    protected void onDetachedFromWindow() {
        a();
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void updateLedStatus() {
        if (this.g == null || this.g.isEmpty()) {
            this.c.setBackgroundResource(a(LED_COLOR.OFF));
        } else if (this.g.size() == 1) {
            this.c.setBackgroundResource(a(((LedStatus) this.g.get(0)).mColor));
        } else {
            LedStatus ledStatus = (LedStatus) this.g.get(this.k);
            this.c.setBackgroundResource(a(ledStatus.mColor));
            this.l.sendEmptyMessageDelayed(4096, (long) ledStatus.mInterval);
            int i = this.k + 1;
            this.k = i;
            this.k = i % this.g.size();
        }
    }

    private List<LedStatus> b() {
        List<LedStatus> arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            LedStatus ledStatus = new LedStatus();
            ledStatus.mColor = LED_COLOR.find(i);
            ledStatus.mInterval = (((i + 1) * 200) + i) + (((i + 1) * (i + 1)) * 100);
            arrayList.add(ledStatus);
        }
        return arrayList;
    }

    private void c() {
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.l = new a();
            this.c = (DJIImageView) findViewById(R.id.a52);
            this.d = (DJITextView) findViewById(R.id.a53);
            this.e = (DJITextView) findViewById(R.id.a54);
            c();
        }
    }

    private void a(int i) {
        if (i == R.string.fpv_led_imu_error || i == R.string.fpv_led_packing_fail || i == R.string.fpv_led_imu_gyro_lost || i == R.string.fpv_led_imu_bad_atti || i == R.string.fpv_led_system_error || i == R.string.fpv_led_imu_need_cali || i == R.string.fpv_led_compass_need_cali || i == R.string.fpv_led_failsafe || i == R.string.fpv_led_battery_warning || i == R.string.fpv_led_battery_error || i == R.string.fpv_led_imu_warning || i == R.string.fpv_led_fdi_vibrate) {
            this.e.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            this.e.setTextColor(-1);
        }
        this.e.setText(i);
    }
}
