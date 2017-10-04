package dji.pilot.fpv.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.view.DJIRedundancyNumView;
import dji.pilot.fpv.view.DJIRedundancyNumView.a;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJISwitch;
import dji.publics.DJIUI.DJITextView;

public class i extends c {
    private static final String A = "g_config.fdi_switch.way_0";
    private static final String B = "g_config.fdi_switch.ns.default_index_0";
    private static final String C = "g_config.fdi_switch.ns.with_fdi_0";
    private static final String D = "g_config.fdi_switch.ns.by_fdi_0";
    private static final String E = "g_config.fdi_switch.ns.random_test_0";
    private static final String F = "g_config.fdi_switch.gps.default_index_0";
    private static final String G = "g_config.fdi_switch.gps.with_fdi_0";
    private static final String H = "g_config.fdi_switch.gps.by_fdi_0";
    private static final String I = "g_config.fdi_switch.gps.random_test_0";
    private static final String J = "g_config.fdi_switch.compass.default_index_0";
    private static final String K = "g_config.fdi_switch.compass.with_fdi_0";
    private static final String L = "g_config.fdi_switch.compass.by_fdi_0";
    private static final String Q = "g_config.fdi_switch.compass.random_test_0";
    private static final String R = "g_config.fdi_switch.gyro.default_index_0";
    private static final String S = "g_config.fdi_switch.gyro.with_fdi_0";
    private static final String T = "g_config.fdi_switch.gyro.by_fdi_0";
    private static final String U = "g_config.fdi_switch.gyro.random_test_0";
    private static final String V = "g_config.fdi_switch.acc.default_index_0";
    private static final String W = "g_config.fdi_switch.acc.with_fdi_0";
    private static final String X = "g_config.fdi_switch.acc.by_fdi_0";
    private static final String Y = "g_config.fdi_switch.acc.random_test_0";
    private static final String Z = "g_config.fdi_switch.baro.default_index_0";
    private static final String aa = "g_config.fdi_switch.baro.with_fdi_0";
    private static final String ab = "g_config.fdi_switch.baro.by_fdi_0";
    private static final String ac = "g_config.fdi_switch.baro.random_test_0";
    private static final String z = "g_config.fdi_switch.open_0";
    private DJISwitch a = null;
    private a ad = new a(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void a(View view, int i, boolean z) {
            if (z) {
                switch (view.getId()) {
                    case R.id.a_u:
                        this.a.a(i.B, i - 1);
                        return;
                    case R.id.a_y:
                        this.a.a(i.F, i - 1);
                        return;
                    case R.id.aa2:
                        this.a.a(i.J, i - 1);
                        return;
                    case R.id.aa6:
                        this.a.a(i.R, i - 1);
                        return;
                    case R.id.aa_:
                        this.a.a(i.V, i - 1);
                        return;
                    case R.id.aad:
                        this.a.a(i.Z, i - 1);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private OnClickListener ae = new OnClickListener(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int i = 1;
            final boolean z;
            DataFlycSetParams dataFlycSetParams;
            Number[] numberArr;
            switch (view.getId()) {
                case R.id.a_s:
                    this.a.dismiss();
                    return;
                case R.id.a_v:
                    z = !this.a.c.isSelected();
                    this.a.c.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.C});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.c.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.c.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.a_w:
                    z = !this.a.d.isSelected();
                    this.a.d.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.D});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.d.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass11 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.d.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.a_x:
                    z = !this.a.e.isSelected();
                    this.a.e.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.E});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.e.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass12 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.e.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.a_z:
                    z = !this.a.g.isSelected();
                    this.a.g.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.G});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.g.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass13 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.g.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa0:
                    z = !this.a.h.isSelected();
                    this.a.h.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.H});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.h.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass14 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.h.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa1:
                    z = !this.a.i.isSelected();
                    this.a.i.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.I});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.i.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass15 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.i.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa3:
                    z = !this.a.k.isSelected();
                    this.a.k.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.K});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.k.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass16 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.k.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa4:
                    z = !this.a.l.isSelected();
                    this.a.l.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.L});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.l.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass17 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.l.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa5:
                    z = !this.a.m.isSelected();
                    this.a.m.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.Q});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.m.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass18 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.m.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa7:
                    z = !this.a.o.isSelected();
                    this.a.o.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.S});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.o.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.o.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa8:
                    z = !this.a.p.isSelected();
                    this.a.p.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.T});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.p.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.p.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aa9:
                    z = !this.a.q.isSelected();
                    this.a.q.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.U});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.q.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass4 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.q.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aaa:
                    z = !this.a.s.isSelected();
                    this.a.s.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.W});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.s.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.s.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aab:
                    z = !this.a.t.isSelected();
                    this.a.t.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.X});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.t.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass6 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.t.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aac:
                    z = !this.a.u.isSelected();
                    this.a.u.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.Y});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.u.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass7 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.u.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aae:
                    z = !this.a.w.isSelected();
                    this.a.w.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.aa});
                    numberArr = new Number[1];
                    if (z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.w.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass8 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.w.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aaf:
                    z = !this.a.x.isSelected();
                    this.a.x.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.ab});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.x.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass9 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.x.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                case R.id.aag:
                    z = !this.a.y.isSelected();
                    this.a.y.setSelected(z);
                    dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{i.ac});
                    numberArr = new Number[1];
                    if (!z) {
                        i = 0;
                    }
                    numberArr[0] = Integer.valueOf(i);
                    dataFlycSetParams.a(numberArr);
                    dataFlycSetParams.start(new d(this) {
                        final /* synthetic */ AnonymousClass6 b;

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.b.a.y.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass10 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.b.a.y.setSelected(!z);
                                }
                            });
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    };
    private DJIRedundancyNumView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJIRedundancyNumView f = null;
    private DJITextView g = null;
    private DJITextView h = null;
    private DJITextView i = null;
    private DJIRedundancyNumView j = null;
    private DJITextView k = null;
    private DJITextView l = null;
    private DJITextView m = null;
    private DJIRedundancyNumView n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private DJITextView q = null;
    private DJIRedundancyNumView r = null;
    private DJITextView s = null;
    private DJITextView t = null;
    private DJITextView u = null;
    private DJIRedundancyNumView v = null;
    private DJITextView w = null;
    private DJITextView x = null;
    private DJITextView y = null;

    public i(Context context) {
        super(context);
        setContentView(R.layout.fpv_redundancy_dialog);
        this.a = (DJISwitch) findViewById(R.id.a_t);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                final boolean isChecked = this.a.a.isChecked();
                DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                dataFlycSetParams.a(new String[]{i.z, i.A});
                if (isChecked) {
                    dataFlycSetParams.a(new Number[]{Integer.valueOf(1), Integer.valueOf(2)});
                } else {
                    dataFlycSetParams.a(new Number[]{Integer.valueOf(0), Integer.valueOf(0)});
                }
                dataFlycSetParams.start(new d(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.a.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.a.a.setChecked(!isChecked);
                            }
                        });
                    }
                });
            }
        });
        this.b = (DJIRedundancyNumView) findViewById(R.id.a_u);
        this.b.setOnValueChangedListener(this.ad);
        this.f = (DJIRedundancyNumView) findViewById(R.id.a_y);
        this.f.setOnValueChangedListener(this.ad);
        this.j = (DJIRedundancyNumView) findViewById(R.id.aa2);
        this.j.setOnValueChangedListener(this.ad);
        this.n = (DJIRedundancyNumView) findViewById(R.id.aa6);
        this.n.setOnValueChangedListener(this.ad);
        this.r = (DJIRedundancyNumView) findViewById(R.id.aa_);
        this.r.setOnValueChangedListener(this.ad);
        this.v = (DJIRedundancyNumView) findViewById(R.id.aad);
        this.v.setOnValueChangedListener(this.ad);
        this.c = (DJITextView) findViewById(R.id.a_v);
        this.d = (DJITextView) findViewById(R.id.a_w);
        this.e = (DJITextView) findViewById(R.id.a_x);
        this.c.setOnClickListener(this.ae);
        this.d.setOnClickListener(this.ae);
        this.e.setOnClickListener(this.ae);
        this.g = (DJITextView) findViewById(R.id.a_z);
        this.h = (DJITextView) findViewById(R.id.aa0);
        this.i = (DJITextView) findViewById(R.id.aa1);
        this.g.setOnClickListener(this.ae);
        this.h.setOnClickListener(this.ae);
        this.i.setOnClickListener(this.ae);
        this.k = (DJITextView) findViewById(R.id.aa3);
        this.l = (DJITextView) findViewById(R.id.aa4);
        this.m = (DJITextView) findViewById(R.id.aa5);
        this.k.setOnClickListener(this.ae);
        this.l.setOnClickListener(this.ae);
        this.m.setOnClickListener(this.ae);
        this.o = (DJITextView) findViewById(R.id.aa7);
        this.p = (DJITextView) findViewById(R.id.aa8);
        this.q = (DJITextView) findViewById(R.id.aa9);
        this.o.setOnClickListener(this.ae);
        this.p.setOnClickListener(this.ae);
        this.q.setOnClickListener(this.ae);
        this.s = (DJITextView) findViewById(R.id.aaa);
        this.t = (DJITextView) findViewById(R.id.aab);
        this.u = (DJITextView) findViewById(R.id.aac);
        this.s.setOnClickListener(this.ae);
        this.t.setOnClickListener(this.ae);
        this.u.setOnClickListener(this.ae);
        this.w = (DJITextView) findViewById(R.id.aae);
        this.x = (DJITextView) findViewById(R.id.aaf);
        this.y = (DJITextView) findViewById(R.id.aag);
        this.w.setOnClickListener(this.ae);
        this.x.setOnClickListener(this.ae);
        this.y.setOnClickListener(this.ae);
        findViewById(R.id.a_s).setOnClickListener(this.ae);
        b();
    }

    private void b() {
        new DataFlycGetParams().setInfos(new String[]{z, B, C, D, E, F, G, H, I, J, K, L, Q, R, S, T, U, V, W, X, Y, Z, aa, ab, ac}).start(new d(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        boolean z;
                        boolean z2 = true;
                        this.a.a.a.setChecked(dji.midware.data.manager.P3.d.read(i.z).value.intValue() == 1);
                        this.a.a.b.setVaule(dji.midware.data.manager.P3.d.read(i.B).value.intValue() + 1);
                        DJITextView c = this.a.a.c;
                        if (dji.midware.data.manager.P3.d.read(i.C).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.d;
                        if (dji.midware.data.manager.P3.d.read(i.D).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.e;
                        if (dji.midware.data.manager.P3.d.read(i.E).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        this.a.a.f.setVaule(dji.midware.data.manager.P3.d.read(i.F).value.intValue() + 1);
                        c = this.a.a.g;
                        if (dji.midware.data.manager.P3.d.read(i.G).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.h;
                        if (dji.midware.data.manager.P3.d.read(i.H).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.i;
                        if (dji.midware.data.manager.P3.d.read(i.I).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        this.a.a.j.setVaule(dji.midware.data.manager.P3.d.read(i.J).value.intValue() + 1);
                        c = this.a.a.k;
                        if (dji.midware.data.manager.P3.d.read(i.K).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.l;
                        if (dji.midware.data.manager.P3.d.read(i.L).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.m;
                        if (dji.midware.data.manager.P3.d.read(i.Q).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        this.a.a.n.setVaule(dji.midware.data.manager.P3.d.read(i.R).value.intValue() + 1);
                        c = this.a.a.o;
                        if (dji.midware.data.manager.P3.d.read(i.S).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.p;
                        if (dji.midware.data.manager.P3.d.read(i.T).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.q;
                        if (dji.midware.data.manager.P3.d.read(i.U).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        this.a.a.r.setVaule(dji.midware.data.manager.P3.d.read(i.V).value.intValue() + 1);
                        c = this.a.a.s;
                        if (dji.midware.data.manager.P3.d.read(i.W).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.t;
                        if (dji.midware.data.manager.P3.d.read(i.X).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.u;
                        if (dji.midware.data.manager.P3.d.read(i.Y).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        this.a.a.v.setVaule(dji.midware.data.manager.P3.d.read(i.Z).value.intValue() + 1);
                        c = this.a.a.w;
                        if (dji.midware.data.manager.P3.d.read(i.aa).value.intValue() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        c = this.a.a.x;
                        if (dji.midware.data.manager.P3.d.read(i.ab).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setSelected(z);
                        DJITextView y = this.a.a.y;
                        if (dji.midware.data.manager.P3.d.read(i.ac).value.intValue() != 1) {
                            z2 = false;
                        }
                        y.setSelected(z2);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.mk), b.a(this.N, R.dimen.mj), 0, 17, true, false);
    }

    private void a(String str, int i) {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(new String[]{str});
        dataFlycSetParams.a(new Number[]{Integer.valueOf(i)});
        dataFlycSetParams.start(new d(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(this.a.getContext().getString(R.string.fpv_redundancy_set_success));
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.a(aVar.toString());
            }
        });
    }

    private void a(final String str) {
        this.y.post(new Runnable(this) {
            final /* synthetic */ i b;

            public void run() {
                Toast.makeText(this.b.getContext(), str, 0).show();
            }
        });
    }
}
