package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraSaveParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamBrightness;
import dji.midware.data.model.P3.DataCameraTauParamConstrast;
import dji.midware.data.model.P3.DataCameraTauParamDigitalInc;
import dji.midware.data.model.P3.DataCameraTauParamOptizate;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIRoundLinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJICameraTauSceneView extends DJIRoundLinearLayout {
    private static final int b = 4096;
    private static final int c = 8192;
    private static final int d = 12288;
    private static final long e = 50;
    private static final long f = 20;
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 5;
    protected int a = 0;
    private final a[] m = new a[5];
    private DJITextView n = null;
    private Context o = null;
    private OnSeekBarChangeListener p = null;
    private OnClickListener q = null;
    private c r = null;
    private AGCType s = AGCType.j;

    private static final class a {
        public View a;
        public DJITextView b;
        public DJITextView c;
        public SeekBar d;
        public int[] e;
        public boolean f;
        public int g;
        public volatile int h;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = false;
            this.g = 0;
            this.h = Integer.MIN_VALUE;
        }
    }

    public enum b {
        SHOW,
        HIDE,
        TOSHOW,
        TOHIDE
    }

    private static final class c extends Handler {
        private final WeakReference<DJICameraTauSceneView> a;

        public c(DJICameraTauSceneView dJICameraTauSceneView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJICameraTauSceneView);
        }

        public void handleMessage(Message message) {
            DJICameraTauSceneView dJICameraTauSceneView = (DJICameraTauSceneView) this.a.get();
            if (dJICameraTauSceneView != null) {
                if (4096 <= message.what && message.what < 4101) {
                    dJICameraTauSceneView.a(false, message.what - 4096, message.arg1);
                } else if (8192 <= message.what && message.what <= 8197) {
                    dJICameraTauSceneView.b(message.what - 8192, message.arg1);
                } else if (12288 == message.what) {
                    dJICameraTauSceneView.a(message.arg2, message.arg1);
                }
            }
        }
    }

    public DJICameraTauSceneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = context;
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            this.s = DataCameraGetPushTauParam.getInstance().getAGC();
            a();
            onEventMainThread(DataCameraGetPushTauParam.getInstance());
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            dji.thirdparty.a.c.a().e(b.SHOW);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            for (int i = 0; i < 5; i++) {
                this.m[i].h = Integer.MIN_VALUE;
            }
            this.s = AGCType.j;
            dji.thirdparty.a.c.a().d(this);
            setVisibility(8);
            dji.thirdparty.a.c.a().e(b.HIDE);
        }
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        if (dataCameraGetPushTauParam.getAGC() == this.s) {
            int contrast = dataCameraGetPushTauParam.getContrast();
            if (!(this.m[3].g == contrast || this.m[3].f || (this.m[3].h != Integer.MIN_VALUE && this.m[3].h != contrast))) {
                this.m[3].g = contrast;
                this.m[3].c.setText(c(contrast));
                this.m[3].d.setProgress(contrast - this.m[3].e[0]);
            }
            contrast = dataCameraGetPushTauParam.getBrightness();
            if (!(this.m[4].g == contrast || this.m[4].f || (this.m[4].h != Integer.MIN_VALUE && this.m[4].h != contrast))) {
                this.m[4].g = contrast;
                this.m[4].c.setText(c(contrast));
                this.m[4].d.setProgress(contrast - this.m[4].e[0]);
            }
            contrast = dataCameraGetPushTauParam.getDDE();
            if (!(this.m[0].g == contrast || this.m[0].f || (this.m[0].h != Integer.MIN_VALUE && this.m[0].h != contrast))) {
                this.m[0].g = contrast;
                this.m[0].c.setText(c(contrast));
                this.m[0].d.setProgress(contrast - this.m[0].e[0]);
            }
            contrast = dataCameraGetPushTauParam.getACE();
            if (!(this.m[1].g == contrast || this.m[1].f || (this.m[1].h != Integer.MIN_VALUE && this.m[1].h != contrast))) {
                this.m[1].g = contrast;
                this.m[1].c.setText(c(contrast));
                this.m[1].d.setProgress(contrast - this.m[1].e[0]);
            }
            contrast = dataCameraGetPushTauParam.getSSO();
            if (this.m[2].g != contrast && !this.m[2].f) {
                if (this.m[2].h == Integer.MIN_VALUE || this.m[2].h == contrast) {
                    this.m[2].g = contrast;
                    this.m[2].c.setText(c(contrast));
                    this.m[2].d.setProgress(contrast - this.m[2].e[0]);
                    return;
                }
                return;
            }
            return;
        }
        go();
    }

    private void a() {
        if (AGCType.g == this.s || AGCType.h == this.s || AGCType.i == this.s) {
            a(1);
            a(0);
            a(2);
            b(3);
            b(4);
        } else if (this.s == AGCType.f) {
            a(4);
            a(0);
            a(3);
            b(1);
            b(2);
        }
    }

    private void a(int i) {
        if (!this.m[i].d.isEnabled()) {
            Drawable drawable = getResources().getDrawable(R.drawable.fpv_advanced_slider_normal_icon);
            this.m[i].d.setProgressDrawable(dji.pilot.fpv.d.b.a(getResources().getDrawable(R.drawable.fpv_playback_video_progress), false));
            this.m[i].d.setThumb(drawable);
            this.m[i].d.setThumbOffset(0);
            this.m[i].d.setEnabled(true);
        }
    }

    private void b(int i) {
        if (this.m[i].d.isEnabled()) {
            this.m[i].d.setProgressDrawable(dji.pilot.fpv.d.b.a(getResources().getDrawable(R.drawable.iso_disable_pgb), false));
            this.m[i].d.setThumb(getResources().getDrawable(R.drawable.fpv_advanced_slider_disable_icon));
            this.m[i].d.setThumbOffset(this.a);
            this.m[i].d.setEnabled(false);
            this.m[i].h = Integer.MIN_VALUE;
        }
    }

    private String c(int i) {
        return String.valueOf(i);
    }

    private int a(SeekBar seekBar) {
        for (int i = 0; i < 5; i++) {
            if (this.m[i].d == seekBar) {
                return i;
            }
        }
        return 0;
    }

    private void a(int i, int i2) {
        if (1 == i2) {
            this.m[i].h = Integer.MIN_VALUE;
            onEventMainThread(DataCameraGetPushTauParam.getInstance());
        }
    }

    private void b(final int i, int i2) {
        d dVar = null;
        if (!this.m[i].f) {
            this.m[i].h = this.m[i].e[0] + i2;
            dVar = new d(this) {
                final /* synthetic */ DJICameraTauSceneView b;

                public void onSuccess(Object obj) {
                    this.b.r.sendMessage(this.b.r.obtainMessage(12288, 0, i));
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.r.sendMessage(this.b.r.obtainMessage(12288, 1, i));
                }
            };
        }
        if (i == 0) {
            new DataCameraTauParamDigitalInc().a(this.m[i].e[0] + i2).b(false).start(dVar);
        } else if (i == 1) {
            new DataCameraTauParamConstrast().a(this.m[i].e[0] + i2).b(false).start(dVar);
        } else if (i == 2) {
            new DataCameraTauParamOptizate().a(this.m[i].e[0] + i2).b(false).start(dVar);
        } else if (i == 3) {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.T).a(this.m[i].e[0] + i2).start(dVar);
        } else if (i == 4) {
            new DataCameraTauParamBrightness().a(this.m[i].e[0] + i2).b(false).start(dVar);
        }
    }

    private void a(boolean z, int i, int i2) {
        this.r.removeMessages(i + 8192);
        if (z) {
            b(i, i2);
        } else {
            this.r.sendMessageDelayed(this.r.obtainMessage(i + 8192, i2, 0), 50);
        }
    }

    private void b() {
        this.a = getResources().getDimensionPixelSize(R.dimen.ex);
        this.r = new c(this);
        this.q = new OnClickListener(this) {
            final /* synthetic */ DJICameraTauSceneView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DataCameraSaveParams.getInstance().setMode(USER.USER1).start(null);
                this.a.go();
            }
        };
        this.p = new OnSeekBarChangeListener(this) {
            final /* synthetic */ DJICameraTauSceneView a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int a = this.a.a(seekBar);
                this.a.m[a].f = false;
                this.a.a(true, a, seekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.m[this.a.a(seekBar)].f = true;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int a = this.a.a(seekBar);
                if (z && this.a.m[a].f) {
                    this.a.m[a].c.setText(this.a.c(this.a.m[a].e[0] + i));
                    this.a.r.removeMessages(a + 4096);
                    this.a.r.sendMessageDelayed(this.a.r.obtainMessage(a + 4096, i, 0), DJICameraTauSceneView.f);
                }
            }
        };
    }

    private void c() {
        int[] iArr = new int[]{R.id.qc, R.id.qd, R.id.qe, R.id.qf, R.id.qg};
        int[] iArr2 = new int[]{R.string.tau_scene_dde, R.string.tau_scene_ace, R.string.tau_scene_sso, R.string.tau_scene_contrast, R.string.tau_scene_brightness};
        int[][] iArr3 = new int[][]{dji.pilot.fpv.camera.more.a.F_, dji.pilot.fpv.camera.more.a.G_, dji.pilot.fpv.camera.more.a.H_, dji.pilot.fpv.camera.more.a.I_, dji.pilot.fpv.camera.more.a.J_};
        for (int i = 0; i < 5; i++) {
            a aVar = new a();
            View findViewById = findViewById(iArr[i]);
            aVar.a = findViewById;
            aVar.b = (DJITextView) findViewById.findViewById(R.id.q_);
            aVar.c = (DJITextView) findViewById.findViewById(R.id.qa);
            aVar.d = (SeekBar) findViewById.findViewById(R.id.qb);
            aVar.b.setText(iArr2[i]);
            aVar.e = iArr3[i];
            aVar.d.setMax(aVar.e[1] - aVar.e[0]);
            aVar.g = aVar.e[0];
            aVar.d.setProgress(0);
            aVar.c.setText(String.valueOf(aVar.g));
            aVar.d.setOnSeekBarChangeListener(this.p);
            this.m[i] = aVar;
        }
        this.n = (DJITextView) findViewById(R.id.qh);
        this.n.setOnClickListener(this.q);
        this.n.go();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
        c();
    }
}
