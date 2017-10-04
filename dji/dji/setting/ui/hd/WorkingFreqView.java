package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.e.d;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class WorkingFreqView extends ItemViewSpinner {
    public static final int a = 0;
    public static final int b = 2;
    private a c;
    private int[] g = new int[]{0, 2};
    private String[] h = new String[]{"2.4G", "5.8G"};
    private int i = 0;

    public interface a {
        void a(int i);
    }

    public void setOnOfdmWoringFreqListener(a aVar) {
        this.c = aVar;
    }

    public WorkingFreqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a() {
        if (a.e()) {
            setVisibility(0);
            this.f.setData(this.h, 0, (b) this);
            DataOsdGetPushConfig.getInstance().start(new d(this) {
                final /* synthetic */ WorkingFreqView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.i = this.a.a.a(DataOsdGetPushConfig.getInstance().getWorkingFreq());
                            this.a.a.f.setIndex(this.a.a.i);
                            if (this.a.a.c != null) {
                                this.a.a.c.a(this.a.a.g[this.a.a.i]);
                            }
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
            return;
        }
        setVisibility(8);
    }

    private int a(int i) {
        for (int i2 = 0; i2 < this.g.length; i2++) {
            if (i == this.g[i2]) {
                return i2;
            }
        }
        return 0;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onItemClick(final int i) {
        DataOsdSetConfig.getInstance().h(this.g[i]).start(new d(this) {
            final /* synthetic */ WorkingFreqView b;

            public void onSuccess(Object obj) {
                this.b.i = i;
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b.c != null) {
                            this.a.b.c.a(this.a.b.g[this.a.b.i]);
                        }
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.f.setIndex(this.a.b.i);
                    }
                });
            }
        });
    }
}
