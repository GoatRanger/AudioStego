package dji.pilot.newfpv.view;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.common.error.DJIError;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.e.a;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.visual.util.c;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.c.h;
import dji.setting.ui.flyc.b;

public class FpvShortcutView extends RelativeLayout {
    private DJIStateImageView a;
    private DJIStateImageView b;
    private DJIStateImageView c;
    private DJIStateImageView d;
    private SeekBar e;
    private RelativeLayout f;
    private OnClickListener g;
    private DJIGenSettingDataManager h;
    private Animation i;
    private Animation j;

    public FpvShortcutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.h = DJIGenSettingDataManager.getInstance();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJIStateImageView) findViewById(R.id.abd);
        this.b = (DJIStateImageView) findViewById(R.id.abe);
        this.c = (DJIStateImageView) findViewById(R.id.abf);
        this.d = (DJIStateImageView) findViewById(R.id.abg);
        this.e = (SeekBar) findViewById(R.id.abi);
        this.f = (RelativeLayout) findViewById(R.id.abb);
        this.g = new OnClickListener(this) {
            final /* synthetic */ FpvShortcutView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.abb:
                        this.a.b();
                        return;
                    case R.id.abd:
                        this.a.c();
                        return;
                    case R.id.abe:
                        DataSpecialControl.getInstance().resetGimbal().start(20);
                        return;
                    case R.id.abf:
                        b.a(HOMETYPE.a, this.a.getContext());
                        return;
                    case R.id.abg:
                        b.a(HOMETYPE.b, this.a.getContext());
                        return;
                    default:
                        return;
                }
            }
        };
        this.a.setOnClickListener(this.g);
        this.b.setOnClickListener(this.g);
        this.c.setOnClickListener(this.g);
        this.d.setOnClickListener(this.g);
        this.f.setOnClickListener(this.g);
        this.e.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ FpvShortcutView a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    LayoutParams attributes = ((Activity) this.a.getContext()).getWindow().getAttributes();
                    attributes.screenBrightness = ((float) i) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
                    ((Activity) this.a.getContext()).getWindow().setAttributes(attributes);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        a();
    }

    private void a() {
        this.i = AnimationUtils.loadAnimation(getContext(), R.anim.bp);
        this.j = AnimationUtils.loadAnimation(getContext(), R.anim.bq);
        this.i.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ FpvShortcutView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.j.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ FpvShortcutView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(4);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void animShow() {
        if (this.i.hasStarted() && !this.i.hasEnded()) {
            return;
        }
        if ((!this.j.hasStarted() || this.j.hasEnded()) && a.c(null)) {
            clearAnimation();
            startAnimation(this.i);
        }
    }

    private void b() {
        if (this.i.hasStarted() && !this.i.hasEnded()) {
            return;
        }
        if (!this.j.hasStarted() || this.j.hasEnded()) {
            clearAnimation();
            startAnimation(this.j);
        }
    }

    private void c() {
        if (c.h()) {
            final boolean m = this.h.m();
            DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), m ? CameraOrientation.Landscape : CameraOrientation.Portrait, new h(this) {
                final /* synthetic */ FpvShortcutView b;

                public void a() {
                    this.b.h.c(!m);
                }

                public void a(DJIError dJIError) {
                    this.b.h.c(m);
                }
            });
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            d();
        }
    }

    private void d() {
        LayoutParams attributes = ((Activity) getContext()).getWindow().getAttributes();
        if (attributes.screenBrightness != -1.0f) {
            this.e.setProgress((int) (attributes.screenBrightness * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
            return;
        }
        try {
            this.e.setProgress((int) ((((float) System.getInt(getContext().getContentResolver(), "screen_brightness")) / 255.0f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        dji.thirdparty.a.c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        if (!a.c(productType) && isShown()) {
            b();
        }
    }
}
