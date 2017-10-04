package dji.pilot.fpv.rightbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.common.error.DJIError;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;

public class DJISwitchDefogView extends DJIStateImageView implements d {
    public boolean a = false;
    CameraType b = CameraType.OTHER;
    private boolean c = false;

    public DJISwitchDefogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJISwitchDefogView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.switchMode(!this.a.a);
                }
            });
        }
    }

    public void switchMode(final boolean z) {
        a.a(b.cg, Boolean.valueOf(z), new h(this) {
            final /* synthetic */ DJISwitchDefogView b;

            public void a() {
                this.b.a = z;
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.a();
                    }
                });
            }

            public void a(DJIError dJIError) {
            }
        });
    }

    private void a() {
        post(new Runnable(this) {
            final /* synthetic */ DJISwitchDefogView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.a) {
                    this.a.setImageResource(R.drawable.camera_defog_enable);
                } else {
                    this.a.setImageResource(R.drawable.camera_defog_disable);
                }
            }
        });
    }

    public void onEventMainThread(o oVar) {
        updateData();
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType b = i.getInstance().b();
        if (b != this.b) {
            this.b = b;
            updateData();
        }
    }

    public void updateData() {
        if (dji.pilot.fpv.d.b.k(null) && ServiceManager.getInstance().isRemoteOK()) {
            setVisibility(0);
            a.e(b.cg, new c(this) {
                final /* synthetic */ DJISwitchDefogView a;

                {
                    this.a = r1;
                }

                public void a(dji.sdksharedlib.d.a aVar) {
                    this.a.a = a.b(aVar.e());
                    this.a.a();
                }

                public void a(DJIError dJIError) {
                }
            });
            this.c = true;
            return;
        }
        setVisibility(8);
        this.c = false;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            updateData();
            a.b(this, new String[]{b.cg});
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        a.a(this);
        super.onDetachedFromWindow();
    }

    public boolean canShow() {
        return this.c;
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        updateData();
    }
}
