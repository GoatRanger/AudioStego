package dji.setting.ui.hd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.setting.ui.widget.a;
import dji.thirdparty.a.c;

public class HDSwitch extends DividerLinearLayout implements d, b {
    private static final int d = 5;
    private static final int e = 1;
    protected int a;
    protected TextView b;
    protected DJISpinnerButton c;
    private String[] f = new String[]{"720p", "1080p"};
    private CameraVideoFrameRate[] g = new CameraVideoFrameRate[]{CameraVideoFrameRate.FrameRate_24fps, CameraVideoFrameRate.FrameRate_25fps, CameraVideoFrameRate.FrameRate_30fps, CameraVideoFrameRate.FrameRate_48fps, CameraVideoFrameRate.FrameRate_50fps, CameraVideoFrameRate.FrameRate_60fps};
    private CameraVideoFrameRate[] h = new CameraVideoFrameRate[]{CameraVideoFrameRate.FrameRate_24fps, CameraVideoFrameRate.FrameRate_25fps};
    private int i = 0;

    public HDSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public void onItemClick(int i) {
        if (i == 0) {
            DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.cb), Boolean.valueOf(false), new h(this) {
                final /* synthetic */ HDSwitch a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a();
                }

                public void a(DJIError dJIError) {
                    this.a.b();
                }
            });
        } else {
            a.a(getContext(), R.string.setting_ui_hd_hd_warning_information, new OnClickListener(this) {
                final /* synthetic */ HDSwitch a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.b), CameraMode.RecordVideo, new h(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            boolean z = false;
                            CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = (CameraVideoResolutionAndFrameRate) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.d);
                            if (cameraVideoResolutionAndFrameRate != null) {
                                boolean z2 = cameraVideoResolutionAndFrameRate.getResolution().equals(CameraVideoResolution.Resolution_1920x1080) ? cameraVideoResolutionAndFrameRate.getFrameRate().value() > 5 : true;
                                if (!cameraVideoResolutionAndFrameRate.getResolution().equals(CameraVideoResolution.Resolution_2704X1520)) {
                                    z = z2;
                                } else if (cameraVideoResolutionAndFrameRate.getFrameRate().value() > 1) {
                                    z = true;
                                }
                            } else {
                                z = true;
                            }
                            if (z) {
                                DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.d), new CameraVideoResolutionAndFrameRate(CameraVideoResolution.Resolution_1920x1080, CameraVideoFrameRate.FrameRate_48fps), new h(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void a() {
                                        DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.cb), Boolean.valueOf(true), new h(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void a() {
                                                this.a.a.a.a.a();
                                            }

                                            public void a(DJIError dJIError) {
                                                this.a.a.a.a.b();
                                            }
                                        });
                                    }

                                    public void a(DJIError dJIError) {
                                        this.a.a.a.b();
                                    }
                                });
                            } else {
                                DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.cb), Boolean.valueOf(true), new h(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void a() {
                                        this.a.a.a.a();
                                    }

                                    public void a(DJIError dJIError) {
                                        this.a.a.a.b();
                                    }
                                });
                            }
                        }

                        public void a(DJIError dJIError) {
                            this.a.a.b();
                        }
                    });
                }
            }, new OnClickListener(this) {
                final /* synthetic */ HDSwitch a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            dji.sdksharedlib.a.a.b(this, new String[]{dji.sdksharedlib.b.b.cb});
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.sdksharedlib.a.a.a(this);
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_hd_hd_switch);
        this.b = (TextView) findViewById(R.id.setting_ui_item_title);
        this.c = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
        if (!isInEditMode()) {
        }
    }

    private void a() {
        int i = 0;
        if (i.getInstance().c().equals(ProductType.KumquatX) || i.getInstance().c().equals(ProductType.KumquatS)) {
            setVisibility(0);
            b();
            Boolean valueOf = Boolean.valueOf(dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.cb)));
            DJISpinnerButton dJISpinnerButton = this.c;
            if (valueOf.booleanValue()) {
                i = 1;
            }
            dJISpinnerButton.setIndex(i);
            return;
        }
        setVisibility(8);
    }

    private void b() {
        this.c.setData(this.f, 0, (b) this);
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar != null && aVar2 != null && aVar2.e() != null) {
            a();
        } else if (aVar2 != null && aVar2.e() == null) {
            b();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        a();
    }
}
