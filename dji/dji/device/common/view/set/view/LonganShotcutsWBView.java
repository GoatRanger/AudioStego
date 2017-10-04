package dji.device.common.view.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelHorizontalView;
import antistatic.spinnerwheel.d;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetWhiteBalance;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class LonganShotcutsWBView extends ScrollView implements dji.device.common.view.set.view.DJIStageViewCompat.a {
    private static final int d = 4096;
    private static final int e = 8192;
    private static final long f = 50;
    private static final long g = 20;
    private static final int[] h = new int[]{R.id.longan_camera_newfn_wb_auto, R.id.longan_camera_newfn_wb_indoor, R.id.longan_camera_newfn_wb_outdoor, R.id.longan_camera_newfn_wb_tungsten, R.id.longan_camera_newfn_wb_neon, R.id.longan_camera_newfn_wb_custom};
    WheelHorizontalView a;
    dji.device.widget.b<String> b;
    protected int c = 0;
    private final b[] i = new b[h.length];
    private DJILinearLayout j = null;
    private OnClickListener k = null;
    private Context l = null;
    private boolean m = false;
    private a n = null;
    private int o = Integer.MAX_VALUE;
    private int p = 20;
    private int[] q = null;
    private d r = new d(this) {
        final /* synthetic */ LonganShotcutsWBView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel) {
            this.a.m = true;
        }

        public void b(AbstractWheel abstractWheel) {
            this.a.m = false;
            this.a.a(true, abstractWheel.getCurrentItem());
            this.a.b.i(abstractWheel.getCurrentItem());
            this.a.a.setCurrentItem(abstractWheel.getCurrentItem());
        }
    };
    private antistatic.spinnerwheel.b s = new antistatic.spinnerwheel.b(this) {
        final /* synthetic */ LonganShotcutsWBView a;

        {
            this.a = r1;
        }

        public void a(AbstractWheel abstractWheel, int i, int i2) {
            if (this.a.m) {
                this.a.a(false, i2);
                this.a.b.i(i2);
                this.a.a.setCurrentItem(abstractWheel.getCurrentItem());
            }
        }
    };

    private static final class a extends Handler {
        private final WeakReference<LonganShotcutsWBView> a;

        private a(LonganShotcutsWBView longanShotcutsWBView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(longanShotcutsWBView);
        }

        public void handleMessage(Message message) {
            LonganShotcutsWBView longanShotcutsWBView = (LonganShotcutsWBView) this.a.get();
            if (longanShotcutsWBView != null) {
                switch (message.what) {
                    case 4096:
                        return;
                    case 8192:
                        longanShotcutsWBView.c(message.arg1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b {
        public DJIRelativeLayout a;
        public DJITextView b;
        public DJIImageView c;
        public DJIImageView d;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public LonganShotcutsWBView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int whiteBalance = dataCameraGetPushShotParams.getWhiteBalance();
        int colorTemp = dataCameraGetPushShotParams.getColorTemp();
        if (whiteBalance != this.o) {
            this.o = whiteBalance;
            int whiteBalancePos = DJICameraDataManagerCompat.getInstance().getWhiteBalancePos(whiteBalance);
            for (whiteBalance = 0; whiteBalance < h.length; whiteBalance++) {
                this.i[whiteBalance].d.setVisibility(4);
            }
            this.i[whiteBalancePos].d.setVisibility(0);
        }
        if (colorTemp != this.p && !this.m) {
            this.p = colorTemp;
            a(colorTemp);
        }
    }

    private void a(int i) {
        int customWBPos = DJICameraDataManagerCompat.getInstance().getCustomWBPos(i);
        if (this.a != null) {
            this.a.setCurrentItem(customWBPos);
            this.b.i(customWBPos);
        }
    }

    private void b(int i) {
        DataCameraSetWhiteBalance dataCameraSetWhiteBalance = new DataCameraSetWhiteBalance();
        dataCameraSetWhiteBalance.a().a(this.q[i]);
        if (i == this.q.length - 1) {
            dataCameraSetWhiteBalance.b(this.p);
        }
        dataCameraSetWhiteBalance.start(null);
        Log.d("kevin 11.25", "type:" + this.q[i]);
    }

    private void b() {
        this.l = getContext();
        this.b = new dji.device.widget.b(getContext(), DJICameraDataManagerCompat.getInstance().getCutsomNameValues());
        this.b.c(R.layout.longan_wheel_item_camera_set_port);
        this.b.d(R.id.longan_camera_settings_wheel_text);
        this.a = (WheelHorizontalView) findViewById(R.id.longan_camera_shotcuts_wb_wheel);
        this.a.setViewAdapter(this.b);
        this.a.addChangingListener(this.s);
        this.a.addScrollingListener(this.r);
        this.a.setCurrentItem(0);
        this.b.i(0);
        this.c = this.l.getResources().getDimensionPixelSize(R.dimen.fpv_top_min_margin);
        this.n = new a();
        this.q = DJICameraDataManagerCompat.getInstance().getWhiteBalanceCmdIdAr();
        this.k = new OnClickListener(this) {
            final /* synthetic */ LonganShotcutsWBView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                for (int i = 0; i < LonganShotcutsWBView.h.length; i++) {
                    if (id == LonganShotcutsWBView.h[i]) {
                        this.a.b(i);
                        return;
                    }
                }
            }
        };
    }

    protected void onFinishInflate() {
        b();
        int[] iArr = DJICameraDataManagerCompat.mWhiteBalanceImgRes;
        String[] whiteBalanceAr = DJICameraDataManagerCompat.getInstance().getWhiteBalanceAr();
        for (int i = 0; i < h.length; i++) {
            b bVar = new b();
            bVar.a = (DJIRelativeLayout) findViewById(h[i]);
            bVar.b = (DJITextView) bVar.a.findViewById(R.id.longan_shotcuts_itemlist_title);
            bVar.c = (DJIImageView) bVar.a.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
            bVar.d = (DJIImageView) bVar.a.findViewById(R.id.longan_shotcuts_itemlist_arrow);
            bVar.a.setOnClickListener(this.k);
            bVar.b.setText(whiteBalanceAr[i]);
            bVar.c.setImageResource(iArr[i]);
            bVar.d.setVisibility(4);
            this.i[i] = bVar;
        }
        this.j = (DJILinearLayout) findViewById(R.id.longan_camera_newfn_wb_custom_ly);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        c.a().a(this);
    }

    public void dispatchOnPause() {
        c.a().d(this);
    }

    public View getView() {
        return this;
    }

    private void c(int i) {
        final int i2 = DJICameraDataManagerCompat.getInstance().getCutsomWBValues()[i];
        if (this.p != i2) {
            new DataCameraSetWhiteBalance().a().a(6).b(i2).start(new dji.midware.e.d(this) {
                final /* synthetic */ LonganShotcutsWBView b;

                public void onSuccess(Object obj) {
                    Log.d("kevin 11.25", "success:" + i2);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.d("kevin 11.25", "onFailure:" + aVar);
                }
            });
        }
    }

    private void a(boolean z, int i) {
        this.n.removeMessages(8192);
        if (z) {
            c(i);
        } else {
            this.n.sendMessageDelayed(this.n.obtainMessage(8192, i, 0), 50);
        }
    }
}
