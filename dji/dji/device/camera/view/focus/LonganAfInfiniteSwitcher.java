package dji.device.camera.view.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetFocusInfinite;
import dji.midware.data.model.P3.DataCameraSetFocusInfinite;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganAfInfiniteSwitcher extends DJIStateImageViewCompat {
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
            if (ServiceManager.getInstance().isConnected()) {
                getCameraData();
            }
            c.a().a(this);
            return;
        }
        setVisibility(8);
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
        getHandler().post(new Runnable(this) {
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
}
