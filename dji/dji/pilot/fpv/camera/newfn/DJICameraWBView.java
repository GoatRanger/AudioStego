package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetWhiteBalance;
import dji.midware.e.d;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJICameraWBView extends ScrollView implements dji.pilot.fpv.view.DJIStageView.a {
    private static final int b = 4096;
    private static final int c = 8192;
    private static final long d = 50;
    private static final long e = 20;
    private static final int[] f = new int[]{R.id.ow, R.id.ox, R.id.oy, R.id.oz, R.id.p0, R.id.p4, R.id.p1, R.id.p2, R.id.p3};
    protected int a = 0;
    private final c[] g = new c[f.length];
    private DJILinearLayout h = null;
    private DJITextView i = null;
    private SeekBar j = null;
    private OnClickListener k = null;
    private OnSeekBarChangeListener l = null;
    private Context m = null;
    private boolean n = false;
    private b o = null;
    private int p = 0;
    private int q = 0;
    private int r = Integer.MAX_VALUE;
    private int s = Integer.MAX_VALUE;
    private int[] t = dji.pilot.fpv.camera.more.a.q_;
    private int u = (dji.pilot.fpv.camera.more.a.q_[1] - dji.pilot.fpv.camera.more.a.q_[0]);
    private int[] v = null;
    private a w = new a();

    private class a implements Runnable {
        final /* synthetic */ DJICameraWBView a;

        private a(DJICameraWBView dJICameraWBView) {
            this.a = dJICameraWBView;
        }

        public void run() {
            this.a.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
        }
    }

    private static final class b extends Handler {
        private final WeakReference<DJICameraWBView> a;

        private b(DJICameraWBView dJICameraWBView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJICameraWBView);
        }

        public void handleMessage(Message message) {
            DJICameraWBView dJICameraWBView = (DJICameraWBView) this.a.get();
            if (dJICameraWBView != null) {
                switch (message.what) {
                    case 4096:
                        dJICameraWBView.a(false, dJICameraWBView.j.getProgress());
                        return;
                    case 8192:
                        dJICameraWBView.b(message.arg1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class c {
        public DJILinearLayout a;
        public DJITextView b;
        public DJIImageView c;

        private c() {
            this.a = null;
            this.b = null;
            this.c = null;
        }
    }

    public DJICameraWBView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int whiteBalance = dataCameraGetPushShotParams.getWhiteBalance();
        int colorTemp = dataCameraGetPushShotParams.getColorTemp();
        if (whiteBalance != this.r) {
            int p;
            this.r = whiteBalance;
            p = dji.pilot.fpv.camera.more.a.getInstance().p(whiteBalance);
            for (int i = 0; i < f.length; i++) {
                this.g[i].a.setSelected(false);
            }
            this.g[p].a.setSelected(true);
            if (p == this.v.length - 1) {
                this.h.show();
                this.o.post(this.w);
            } else {
                this.h.go();
            }
        }
        if (whiteBalance == this.v[this.v.length - 1] && colorTemp != this.s && !this.n) {
            this.s = colorTemp;
            this.i.setText(this.m.getString(R.string.fpv_camera_wb_format, new Object[]{Integer.valueOf(colorTemp * 100)}));
            p = colorTemp - this.t[0];
            this.j.setProgress(p);
            a(this.i, this.j, p, this.u, false);
        }
    }

    private void a(int i) {
        DataCameraSetWhiteBalance dataCameraSetWhiteBalance = new DataCameraSetWhiteBalance();
        dataCameraSetWhiteBalance.a().a(this.v[i]);
        if (i == this.v.length - 1) {
            int colorTemp = DataCameraGetPushShotParams.getInstance().getColorTemp();
            if (colorTemp < this.t[0] || colorTemp > this.t[1]) {
                if (this.s != Integer.MAX_VALUE) {
                    colorTemp = this.s;
                } else {
                    colorTemp = (this.t[0] + this.t[1]) / 2;
                }
            }
            dataCameraSetWhiteBalance.b(colorTemp);
        } else {
            dataCameraSetWhiteBalance.b(0);
        }
        dataCameraSetWhiteBalance.start(null);
    }

    private void b() {
        this.m = getContext();
        Resources resources = this.m.getResources();
        this.p = resources.getDimensionPixelSize(R.dimen.eu);
        this.q = resources.getDimensionPixelSize(R.dimen.i9) - (this.p * 2);
        this.a = resources.getDimensionPixelSize(R.dimen.n4);
        this.o = new b();
        this.v = dji.pilot.fpv.camera.more.a.getInstance().Q();
        this.k = new OnClickListener(this) {
            final /* synthetic */ DJICameraWBView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                for (int i = 0; i < DJICameraWBView.f.length; i++) {
                    if (id == DJICameraWBView.f[i]) {
                        this.a.a(i);
                        return;
                    }
                }
            }
        };
        this.l = new OnSeekBarChangeListener(this) {
            final /* synthetic */ DJICameraWBView a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z && this.a.n && this.a.j == seekBar) {
                    this.a.o.removeMessages(4096);
                    this.a.o.sendEmptyMessageDelayed(4096, DJICameraWBView.e);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.n = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.n = false;
                if (this.a.j == seekBar) {
                    this.a.o.removeMessages(4096);
                    this.a.a(true, seekBar.getProgress());
                }
            }
        };
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            b();
            int[] iArr = dji.pilot.fpv.camera.more.a.o_;
            String[] P = dji.pilot.fpv.camera.more.a.getInstance().P();
            int i = 0;
            while (i < f.length) {
                c cVar = new c();
                cVar.a = (DJILinearLayout) findViewById(f[i]);
                cVar.b = (DJITextView) cVar.a.findViewById(R.id.ko);
                cVar.c = (DJIImageView) cVar.a.findViewById(R.id.kn);
                cVar.a.setOnClickListener(this.k);
                cVar.b.setText(P[i]);
                cVar.c.setImageResource(iArr[i]);
                if (f[i] == R.id.p4 || f[i] == R.id.p1 || f[i] == R.id.p2) {
                    cVar.a.go();
                }
                if (f[i] == R.id.p3) {
                    cVar.a.show();
                }
                this.g[i] = cVar;
                i++;
            }
            this.h = (DJILinearLayout) findViewById(R.id.p5);
            this.i = (DJITextView) findViewById(R.id.p6);
            this.j = (SeekBar) findViewById(R.id.p7);
            this.j.setOnSeekBarChangeListener(this.l);
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        int i = 0;
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        dji.thirdparty.a.c.a().a(this);
        if (i.getInstance().b() == CameraType.DJICameraTypeFC220S) {
            this.t = dji.pilot.fpv.camera.more.a.r_;
            this.u = dji.pilot.fpv.camera.more.a.r_[1] - dji.pilot.fpv.camera.more.a.r_[0];
            this.j.setMax(this.u);
        } else {
            this.t = dji.pilot.fpv.camera.more.a.q_;
            this.u = dji.pilot.fpv.camera.more.a.q_[1] - dji.pilot.fpv.camera.more.a.q_[0];
            this.j.setMax(this.u);
        }
        DJILinearLayout dJILinearLayout = this.g[4].a;
        if (dji.pilot.fpv.d.b.k(null)) {
            i = 8;
        }
        dJILinearLayout.setVisibility(i);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d(this);
    }

    public View getView() {
        return this;
    }

    private void b(int i) {
        int i2 = this.t[0] + i;
        if (this.r != i2) {
            new DataCameraSetWhiteBalance().a().a(6).b(i2).start(new d(this) {
                final /* synthetic */ DJICameraWBView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void a(boolean z, int i) {
        this.o.removeMessages(8192);
        this.i.setText(this.m.getString(R.string.fpv_camera_wb_format, new Object[]{Integer.valueOf((this.t[0] + i) * 100)}));
        a(this.i, this.j, i, this.u, false);
        if (z) {
            b(i);
        } else {
            this.o.sendMessageDelayed(this.o.obtainMessage(8192, i, 0), 50);
        }
    }

    protected void a(DJITextView dJITextView, SeekBar seekBar, int i, int i2, boolean z) {
        int measureText = (int) dJITextView.getPaint().measureText(dJITextView.getText().toString());
        int i3 = seekBar.getThumb().getBounds().left;
        if (i3 <= 0) {
            i3 = seekBar.getWidth();
            if (i3 <= 0) {
                i3 = this.q;
            }
            i3 = (i3 * i) / i2;
        }
        measureText = this.a + (((i3 + seekBar.getThumbOffset()) + this.p) - (measureText / 2));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) dJITextView.getLayoutParams();
        if (measureText != marginLayoutParams.leftMargin) {
            marginLayoutParams.leftMargin = measureText;
            dJITextView.setLayoutParams(marginLayoutParams);
        }
    }
}
