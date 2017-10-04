package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.camera.view.focus.DJIMFFocusRingViewCompat.a;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.widget.LonganPopWarnView;
import dji.logic.f.b;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ShotFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusStroke;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

@Deprecated
public class LonganFocalSeekBar extends DJILinearLayout implements OnClickListener, OnSeekBarChangeListener {
    DJIStateImageViewCompat a;
    DJIStateImageViewCompat b;
    SeekBar c;
    CameraType d = CameraType.OTHER;
    int e;
    int f = -1;
    boolean g;
    boolean h = false;
    int i = 0;
    private FuselageFocusMode j = FuselageFocusMode.OTHER;
    private int k = 0;

    public LonganFocalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoRotate);
        this.f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AutoRotate_landscapeMargin_Left, -1);
        obtainStyledAttributes.recycle();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(getResources().getConfiguration());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJIStateImageViewCompat) findViewById(R.id.longan_focal_min_iv);
        this.a.setOnClickListener(this);
        this.b = (DJIStateImageViewCompat) findViewById(R.id.longan_focal_max_iv);
        this.b.setOnClickListener(this);
        this.c = (SeekBar) findViewById(R.id.longan_focal_sb);
        this.c.setOnSeekBarChangeListener(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        c.a().a((Object) this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d((Object) this);
    }

    private void a(Configuration configuration) {
        if (configuration.orientation == 2) {
            setTranslationX((float) (this.f - (getWidth() / 2)));
            setTranslationY((float) ((DJIPreviewActivityLongan.mScreenHeight / 2) - (getHeight() / 2)));
            setRotation(90.0f);
            this.a.setRotation(-90.0f);
            this.b.setRotation(-90.0f);
            return;
        }
        setTranslationX((float) ((DJIPreviewActivityLongan.mScreenWidth / 2) - (getWidth() / 2)));
        setTranslationY((float) ((DJIPreviewActivityLongan.mScreenHeight - this.f) - (getHeight() / 2)));
        setRotation(0.0f);
        this.a.setRotation(0.0f);
        this.b.setRotation(0.0f);
    }

    public void showView() {
        if (getVisibility() != 0 && b.n(this.d)) {
            if (this.j == FuselageFocusMode.Manual || this.j == FuselageFocusMode.ManualFine) {
                this.k = DJICameraDataManagerCompat.getInstance().getDemarcateValue();
                onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                animShow();
                this.h = true;
            }
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            c.a().e(a.c);
            animGo();
            this.h = false;
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.d != cameraType) {
            this.d = cameraType;
            if (b.n(cameraType)) {
                showView();
            } else {
                hideView();
            }
        }
    }

    public void onEventMainThread(DJICameraDataManagerCompat.a aVar) {
        if (DJICameraDataManagerCompat.a.e == aVar && getVisibility() == 0) {
            int demarcateValue = DJICameraDataManagerCompat.getInstance().getDemarcateValue();
            if (this.k != demarcateValue) {
                this.k = demarcateValue;
                this.c.setMax(DataCameraGetPushShotInfo.getInstance().getShotFocusMaxStroke() - demarcateValue);
                int shotFocusCurStroke = DataCameraGetPushShotInfo.getInstance().getShotFocusCurStroke();
                if (!this.g && this.e == shotFocusCurStroke) {
                    this.c.setProgress(shotFocusCurStroke - demarcateValue);
                    this.e = shotFocusCurStroke;
                }
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (DataCameraGetPushShotInfo.getInstance().isGetted()) {
            FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
            if (fuselageFocusMode != this.j) {
                this.j = fuselageFocusMode;
                if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                    showView();
                } else {
                    hideView();
                }
            }
            if (isShown()) {
                this.c.setMax(dataCameraGetPushShotInfo.getShotFocusMaxStroke() - this.k);
                int shotFocusCurStroke = dataCameraGetPushShotInfo.getShotFocusCurStroke();
                if (this.e != shotFocusCurStroke && !this.g) {
                    this.c.setProgress(shotFocusCurStroke - this.k);
                    this.e = shotFocusCurStroke;
                }
            }
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.ConnectLose) {
            this.j = FuselageFocusMode.OTHER;
        }
    }

    private void a() {
        boolean z;
        boolean z2 = true;
        DJIStateImageViewCompat dJIStateImageViewCompat = this.a;
        if (this.c.getProgress() != this.k) {
            z = true;
        } else {
            z = false;
        }
        dJIStateImageViewCompat.setEnabled(z);
        DJIStateImageViewCompat dJIStateImageViewCompat2 = this.b;
        if (this.c.getProgress() == this.c.getMax()) {
            z2 = false;
        }
        dJIStateImageViewCompat2.setEnabled(z2);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.i++;
        if (z && this.i >= 10) {
            a(i);
            this.i = 0;
        }
        if (!this.g) {
            a();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (DataCameraGetPushShotInfo.getInstance().getShotFocusMode() == ShotFocusMode.Manual) {
            LonganPopWarnView.getInstance(getContext()).pop(R.drawable.longan_notice, R.string.fpv_shottype_mf_tip, LonganPopWarnView.a.a);
        } else {
            this.g = true;
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        a(seekBar.getProgress());
        a();
        this.g = false;
    }

    private void a(int i) {
        new DataCameraSetFocusStroke().a(i).start(new 1(this));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_focal_min_iv) {
            a(this.k);
        } else if (id == R.id.longan_focal_max_iv) {
            a(this.c.getMax());
        }
    }

    public void onEventMainThread(DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == DJIMFDemarcateViewLongan.a.c) {
            showView();
        } else if (aVar == DJIMFDemarcateViewLongan.a.a) {
            hideView();
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.a) {
            hide();
        } else if (mVar == m.H) {
            if (this.h) {
                show();
            }
        } else if (mVar == m.r || mVar == m.p) {
            hide();
        } else if (mVar == m.a) {
            hide();
        } else if (mVar == m.b && this.h) {
            show();
        }
    }
}
