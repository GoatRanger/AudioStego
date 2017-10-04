package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCommonSetMultiParam;
import dji.midware.e.d;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.sdksharedlib.b.b;
import dji.thirdparty.a.c;

public class DJICameraStyleView extends ScrollView implements dji.pilot.fpv.view.DJIStageView.a {
    private static final String a = DJICameraStyleView.class.getSimpleName();
    private static final int[] b = new int[]{R.id.oh, R.id.oi, R.id.oj, R.id.ok};
    private static final String[] c = new String[]{"Sharpe", b.s, b.t};
    private static final int d = -1;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private final a[] h = new a[b.length];
    private DJITextView i = null;
    private DJITextView j = null;
    private DJITextView k = null;
    private DJIRelativeLayout l = null;
    private DJIImageView m = null;
    private DJIImageView n = null;
    private OnClickListener o = null;
    private int p = Integer.MAX_VALUE;
    private int q = Integer.MAX_VALUE;
    private int r = Integer.MAX_VALUE;
    private byte[] s = null;
    private volatile int t = Integer.MAX_VALUE;
    private int u = Integer.MAX_VALUE;
    private int v = -1;
    private final int[] w = dji.pilot.fpv.camera.more.a.u_;

    private static final class a {
        public DJILinearLayout a;
        public DJITextView b;
        public DJITextView c;
        public DJITextView d;
        public DJITextView e;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }
    }

    public DJICameraStyleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a(dataCameraGetPushShotParams, false);
    }

    private void a(DataCameraGetPushShotParams dataCameraGetPushShotParams, boolean z) {
        byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
        byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
        byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
        int length = dji.pilot.fpv.camera.more.a.t_.length - 1;
        if (this.u == length && this.t == length) {
            a(sharpe, contrast, saturation);
            return;
        }
        int i = 0;
        while (i < dji.pilot.fpv.camera.more.a.t_.length) {
            byte[] bArr = dji.pilot.fpv.camera.more.a.t_[i];
            if (bArr[0] == contrast && bArr[1] == saturation && sharpe == bArr[2]) {
                break;
            }
            i++;
        }
        i = length;
        if (this.t == Integer.MAX_VALUE) {
            this.t = i;
        }
        if (this.u != i) {
            this.u = i;
            a(i);
            if (i == length) {
                this.l.show();
                if (-1 == this.v) {
                    this.v = 0;
                    d();
                }
                a(sharpe, contrast, saturation);
                return;
            }
            this.l.go();
            if (-1 != this.v) {
                this.v = -1;
                d();
            }
        }
    }

    private void a(int i) {
        for (a aVar : this.h) {
            aVar.a.setSelected(false);
        }
        this.h[i].a.setSelected(true);
    }

    private void a(int i, int i2, int i3) {
        if (i != this.p) {
            this.p = i;
            this.i.setText(c(i));
        }
        if (i2 != this.q) {
            this.q = i2;
            this.j.setText(c(i2));
        }
        if (i3 != this.r) {
            this.r = i3;
            this.k.setText(c(i3));
        }
        if (this.u == dji.pilot.fpv.camera.more.a.t_.length - 1) {
            dji.pilot.fpv.camera.more.a.t_[this.u][0] = (byte) this.q;
            dji.pilot.fpv.camera.more.a.t_[this.u][1] = (byte) this.r;
            dji.pilot.fpv.camera.more.a.t_[this.u][2] = (byte) this.p;
        }
    }

    private void b(final int i) {
        if (!this.h[i].a.isSelected()) {
            byte[] bArr = dji.pilot.fpv.camera.more.a.t_[i];
            if (this.s == null) {
                this.s = new byte[10];
                this.s[0] = (byte) 3;
                this.s[1] = (byte) dji.midware.data.config.P3.b.a.T.a();
                this.s[2] = (byte) 1;
                this.s[4] = (byte) dji.midware.data.config.P3.b.a.V.a();
                this.s[5] = (byte) 1;
                this.s[7] = (byte) dji.midware.data.config.P3.b.a.R.a();
                this.s[8] = (byte) 1;
            }
            DataCommonSetMultiParam dataCommonSetMultiParam = new DataCommonSetMultiParam();
            this.s[3] = bArr[0];
            this.s[6] = bArr[1];
            this.s[9] = bArr[2];
            dataCommonSetMultiParam.a(this.s);
            dataCommonSetMultiParam.start(new d(this) {
                final /* synthetic */ DJICameraStyleView b;

                public void onSuccess(Object obj) {
                    this.b.t = i;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void b() {
        int i;
        dji.pilot.fpv.camera.more.a.getInstance().A();
        if (this.v == 0) {
            if (this.p > this.w[0]) {
                i = this.p - 1;
            }
            i = Integer.MIN_VALUE;
        } else if (this.v == 1) {
            if (this.q > this.w[0]) {
                i = this.q - 1;
            }
            i = Integer.MIN_VALUE;
        } else {
            if (this.v == 2 && this.r > this.w[0]) {
                i = this.r - 1;
            }
            i = Integer.MIN_VALUE;
        }
        if (i != Integer.MIN_VALUE) {
            new DataBaseCameraSetting().a(c[this.v]).a(i).start(new d(this) {
                final /* synthetic */ DJICameraStyleView a;

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

    private void c() {
        int i;
        dji.pilot.fpv.camera.more.a.getInstance().A();
        if (this.v == 0) {
            if (this.p < this.w[1]) {
                i = this.p + 1;
            }
            i = Integer.MIN_VALUE;
        } else if (this.v == 1) {
            if (this.q < this.w[1]) {
                i = this.q + 1;
            }
            i = Integer.MIN_VALUE;
        } else {
            if (this.v == 2 && this.r < this.w[1]) {
                i = this.r + 1;
            }
            i = Integer.MIN_VALUE;
        }
        if (i != Integer.MIN_VALUE) {
            new DataBaseCameraSetting().a(c[this.v]).a(i).start(new d(this) {
                final /* synthetic */ DJICameraStyleView a;

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

    private void a(boolean z) {
        this.i.setEnabled(z);
        this.j.setEnabled(z);
        this.k.setEnabled(z);
    }

    private void d() {
        this.i.setSelected(false);
        this.j.setSelected(false);
        this.k.setSelected(false);
        if (this.v != -1) {
            if (this.v == 0) {
                this.i.setSelected(true);
            } else if (this.v == 1) {
                this.j.setSelected(true);
            } else if (this.v == 2) {
                this.k.setSelected(true);
            }
        }
    }

    private String c(int i) {
        return String.format("%+d", new Object[]{Integer.valueOf(i)});
    }

    private void e() {
        this.o = new OnClickListener(this) {
            final /* synthetic */ DJICameraStyleView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                int id = view.getId();
                if (R.id.oe == id) {
                    if (this.a.u != dji.pilot.fpv.camera.more.a.t_.length - 1) {
                        this.a.b(DJICameraStyleView.b.length - 1);
                    } else if (this.a.v != 0) {
                        this.a.v = 0;
                        this.a.d();
                    }
                } else if (R.id.of == id) {
                    if (this.a.u != dji.pilot.fpv.camera.more.a.t_.length - 1) {
                        this.a.b(DJICameraStyleView.b.length - 1);
                    } else if (1 != this.a.v) {
                        this.a.v = 1;
                        this.a.d();
                    }
                } else if (R.id.og == id) {
                    if (this.a.u != dji.pilot.fpv.camera.more.a.t_.length - 1) {
                        this.a.b(DJICameraStyleView.b.length - 1);
                    } else if (2 != this.a.v) {
                        this.a.v = 2;
                        this.a.d();
                    }
                } else if (R.id.om == id) {
                    this.a.b();
                } else if (R.id.on == id) {
                    this.a.c();
                } else {
                    while (i < DJICameraStyleView.b.length) {
                        if (id == DJICameraStyleView.b[i]) {
                            this.a.b(i);
                            return;
                        }
                        i++;
                    }
                }
            }
        };
    }

    protected void onFinishInflate() {
        e();
        String[] R = dji.pilot.fpv.camera.more.a.getInstance().R();
        for (int i = 0; i < b.length; i++) {
            a aVar = new a();
            aVar.a = (DJILinearLayout) findViewById(b[i]);
            aVar.b = (DJITextView) aVar.a.findViewById(R.id.od);
            aVar.c = (DJITextView) aVar.a.findViewById(R.id.oe);
            aVar.d = (DJITextView) aVar.a.findViewById(R.id.of);
            aVar.e = (DJITextView) aVar.a.findViewById(R.id.og);
            aVar.b.setText(R[i]);
            byte[] bArr = dji.pilot.fpv.camera.more.a.t_[i];
            aVar.c.setText(c(bArr[0]));
            aVar.d.setText(c(bArr[1]));
            aVar.e.setText(c(bArr[2]));
            aVar.a.setOnClickListener(this.o);
            this.h[i] = aVar;
        }
        this.i = this.h[b.length - 1].c;
        this.j = this.h[b.length - 1].d;
        this.k = this.h[b.length - 1].e;
        this.l = (DJIRelativeLayout) findViewById(R.id.ol);
        this.m = (DJIImageView) findViewById(R.id.om);
        this.n = (DJIImageView) findViewById(R.id.on);
        this.i.setBackgroundResource(R.drawable.selector_camera_value);
        this.j.setBackgroundResource(R.drawable.selector_camera_value);
        this.k.setBackgroundResource(R.drawable.selector_camera_value);
        this.i.setOnClickListener(this.o);
        this.j.setOnClickListener(this.o);
        this.k.setOnClickListener(this.o);
        this.m.setOnClickListener(this.o);
        this.n.setOnClickListener(this.o);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.t = Integer.MAX_VALUE;
        this.u = Integer.MAX_VALUE;
        a(DataCameraGetPushShotParams.getInstance(), true);
        c.a().a(this);
    }

    public void dispatchOnPause() {
        c.a().d(this);
    }

    public View getView() {
        return this;
    }
}
