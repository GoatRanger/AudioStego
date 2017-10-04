package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.common.error.DJIError;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinnerWithDesc;
import dji.thirdparty.a.c;

public class TapZoomMultiView extends ItemViewSpinnerWithDesc {
    private static int f = 1;
    int[] a = new int[]{1, 2, 3, 4, 5};

    public TapZoomMultiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.e.setData(new String[]{"1x", "2x", "3x", "4x", "5x"}, 1, (b) this);
        }
    }

    public static int getCurMulti() {
        return f;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            b();
        }
    }

    private void b() {
        if (i.getInstance().b() == CameraType.DJICameraTypeGD600) {
            setVisibility(0);
            a.e(dji.sdksharedlib.b.b.ce, new dji.sdksharedlib.c.c(this) {
                final /* synthetic */ TapZoomMultiView a;

                {
                    this.a = r1;
                }

                public void a(dji.sdksharedlib.d.a aVar) {
                    final int a = a.a(aVar.e());
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            TapZoomMultiView.f = a;
                            this.b.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.e.setIndex(TapZoomMultiView.f - 1);
                                }
                            });
                        }
                    });
                }

                public void a(DJIError dJIError) {
                }
            });
            return;
        }
        setVisibility(8);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.e.setEnabled(z);
    }

    public void onItemClick(final int i) {
        a.a(dji.sdksharedlib.b.b.ce, Integer.valueOf(this.a[i]), new h(this) {
            final /* synthetic */ TapZoomMultiView b;

            public void a() {
                TapZoomMultiView.f = i;
            }

            public void a(DJIError dJIError) {
            }
        });
    }

    public void onEventMainThread(o oVar) {
        b();
    }
}
