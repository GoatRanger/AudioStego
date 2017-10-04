package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.b;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.widget.LonganPopWarnView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetFocusInfinite;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusInfinite;
import dji.midware.data.model.P3.DataCameraSetFocusWindow;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIMFDemarcateViewLongan extends DJIRoundLinearLayoutCompat {
    Context a;
    protected CameraType b = CameraType.OTHER;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private OnClickListener g = null;
    private FuselageFocusMode h = FuselageFocusMode.OTHER;
    private int i = Integer.MAX_VALUE;
    private int j = 0;

    public static class LonganAfInfiniteSwitcher extends DJIStateImageViewCompat {
        public static a a = a.AF;
        private static final int b = R.drawable.ic_focus_af;
        private static final int c = R.drawable.ic_focus_infinite;

        public enum a {
            AF,
            INFINITE
        }

        public LonganAfInfiniteSwitcher(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganAfInfiniteSwitcher a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (LonganAfInfiniteSwitcher.a == a.AF) {
                        this.a.setCameaData(a.INFINITE);
                    } else {
                        this.a.setCameaData(a.AF);
                    }
                }
            });
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (i.getInstance().c() == ProductType.LonganZoom) {
                if (c()) {
                    setVisibility(0);
                    getCameraData();
                }
                c.a().a(this);
            }
        }

        private void setFocusMode(a aVar) {
            if (aVar != a) {
                a = aVar;
                c.a().e(aVar);
            }
        }

        private void setCameaData(final a aVar) {
            boolean z;
            DataCameraSetFocusInfinite instance = DataCameraSetFocusInfinite.getInstance();
            if (aVar == a.INFINITE) {
                z = true;
            } else {
                z = false;
            }
            instance.a(z).start(new d(this) {
                final /* synthetic */ LonganAfInfiniteSwitcher b;

                public void onSuccess(Object obj) {
                    this.b.setFocusMode(aVar);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.getCameraData();
                }
            });
            setBackgroundMainThread(aVar);
        }

        private void getCameraData() {
            DataCameraGetFocusInfinite.getInstance().start(new d(this) {
                final /* synthetic */ LonganAfInfiniteSwitcher a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.setFocusMode(DataCameraGetFocusInfinite.getInstance().isFocusInfinite() ? a.INFINITE : a.AF);
                    this.a.setBackgroundMainThread(LonganAfInfiniteSwitcher.a);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }

        private void setBackgroundMainThread(final a aVar) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ LonganAfInfiniteSwitcher b;

                public void run() {
                    if (!this.b.isShown()) {
                        this.b.setVisibility(0);
                    }
                    if (aVar == a.INFINITE) {
                        this.b.setImageResource(LonganAfInfiniteSwitcher.c);
                    } else {
                        this.b.setImageResource(LonganAfInfiniteSwitcher.b);
                    }
                }
            });
        }

        public void onEventMainThread(p pVar) {
            if (pVar == p.b && isShown()) {
                getCameraData();
            }
        }

        public void onEventMainThread(b bVar) {
            if (bVar.a(0) && !isShown() && c()) {
                setVisibility(0);
                getCameraData();
            }
        }

        private boolean c() {
            if (i.getInstance().c() != ProductType.LonganZoom || b.getInstance().c(0) < b.d) {
                return false;
            }
            return true;
        }
    }

    public enum a {
        NEEDSHOW,
        SHOW,
        HIDE
    }

    public DJIMFDemarcateViewLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(getResources().getConfiguration().orientation);
    }

    public void showView() {
        if (getVisibility() != 0) {
            setVisibility(0);
            DJICameraDataManagerCompat.getInstance().prepareDemarcate();
            c.a().e(a.SHOW);
            c.a().e(m.SHOW_CONFLICT_RIGHT_VIEW);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            c.a().a(this);
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            c.a().e(a.HIDE);
            c.a().e(m.HIDE_CONFLICT_RIGHT_VIEW);
            c.a().d(this);
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_ALL) {
            c.a().e(m.HIDE_TIMELAPSE_LY);
            c.a().e(m.HIDE_INFO_BAR);
        } else if (mVar == m.HIDE_SHOTCUTS_CAMERA_LY || mVar == m.HIDE_SHOTCUTS_GIMBAL_LY) {
            show();
        } else if (mVar == m.SHOW_SHOTCUTS_CAMERA_LY || mVar == m.SHOW_SHOTCUTS_GIMBAL_LY) {
            hide();
        } else if (mVar == m.SHOW_TIMELAPSE_LY) {
            hideView();
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration.orientation);
    }

    private void a(int i) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (i == 2) {
            layoutParams.addRule(11, 0);
            layoutParams.addRule(0, R.id.longan_preview_setting_bar);
            return;
        }
        layoutParams.addRule(0, 0);
        layoutParams.addRule(11);
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.h) {
            this.h = fuselageFocusMode;
        }
        if (fuselageFocusMode == FuselageFocusMode.OneAuto || FuselageFocusMode.ContinuousAuto == fuselageFocusMode) {
            int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
            if (focusStatus != this.i) {
                if (this.i == 1 && focusStatus == 2 && this.j != 0) {
                    if (((float) dataCameraGetPushShotInfo.getShotFocusCurStroke()) > ((float) dataCameraGetPushShotInfo.getShotFocusMaxStroke()) * lecho.lib.hellocharts.d.c.a) {
                        this.j = 1;
                        this.e.setEnabled(false);
                        LonganPopWarnView.getInstance(this.a).pop(R.drawable.longan_notice, R.string.fpv_demarcate_bad_tip, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
                    } else {
                        this.j = 2;
                        this.e.setEnabled(true);
                    }
                }
                this.i = focusStatus;
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
            if (cameraType != this.b) {
                this.b = cameraType;
                if (dji.logic.f.b.n(cameraType)) {
                    show();
                } else {
                    go();
                }
            }
        }
    }

    private void a() {
        if (this.j == 0) {
            this.d.setText(R.string.camera_mf_demarcate_tip);
            this.e.setText(R.string.camera_demarcate_start);
            this.f.setText(R.string.camera_demarcate_later);
            this.e.setEnabled(true);
        } else if (1 == this.j) {
            this.d.setText(R.string.camera_mf_demarcate_step2_tip);
            this.e.setText(R.string.app_enter);
            this.f.setText(R.string.app_cancel);
            this.e.setEnabled(false);
        } else if (2 == this.j) {
            this.e.setEnabled(true);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.g = new OnClickListener(this) {
                final /* synthetic */ DJIMFDemarcateViewLongan a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view == this.a.e) {
                        if (this.a.j == 0) {
                            if (this.a.h == FuselageFocusMode.Manual || this.a.h == FuselageFocusMode.ManualFine) {
                                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
                            }
                            DataCameraSetFocusWindow.getInstance().a(2).b(2).c(1).start(null);
                            this.a.j = 1;
                            this.a.a();
                        } else if (2 == this.a.j) {
                            this.a.hideView();
                            DJICameraDataManagerCompat.getInstance().demarcateCamera();
                            this.a.j = 0;
                            this.a.a();
                            this.a.i = Integer.MAX_VALUE;
                        }
                    } else if (view == this.a.f) {
                        DJICameraDataManagerCompat.getInstance().cancelDemarcateCamera();
                        this.a.hideView();
                        this.a.j = 0;
                        this.a.a();
                        this.a.i = Integer.MAX_VALUE;
                    }
                }
            };
            this.c = (DJITextView) findViewById(R.id.camera_mf_demarcate_title);
            this.d = (DJITextView) findViewById(R.id.camera_mf_demarcate_content);
            this.e = (DJITextView) findViewById(R.id.camera_mf_demarcate_ok);
            this.f = (DJITextView) findViewById(R.id.camera_mf_demarcate_cancel);
            this.e.setOnClickListener(this.g);
            this.f.setOnClickListener(this.g);
        }
    }
}
