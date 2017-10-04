package dji.pilot2.multimoment.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import com.here.android.mpa.mapping.Map;
import com.meetme.android.horizontallistview.HorizontalListView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.multimoment.adapter.a;
import dji.pilot2.multimoment.adapter.b;
import dji.pilot2.multimoment.videolib.c;
import dji.pilot2.multimoment.view.DJIVideoSlidingBar;
import dji.pilot2.multimoment.view.FineCutHorizonalScrollView;
import dji.pilot2.videolib.VideoLibWrapper;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;

public class DJIMultiMomentFineActivity extends DJIActivityNoFullScreen implements OnClickListener, k {
    public static final String K = "filename";
    public static final String L = "selectduration";
    public static final String M = "segnum";
    public static final String N = "starttime";
    public static final String O = "endtime";
    public static final String P = "light";
    public static final String Q = "saturation";
    public static final String R = "contrast";
    public static final String S = "frageMultifine";
    public static final String T = "singleMode";
    public static final String U = "speed";
    public static final String V = "volume";
    public static final String W = "filter_num";
    public static final String X = "filter_percent";
    public static final String Y = "filter_apply_others";
    public static final String Z = "color_apply_others";
    public static final double aA = 1.0d;
    public static final double aB = 1.0d;
    public static final double aC = 0.0d;
    public static final String aa = "singleMaxLen";
    public static final double ay = 0.0d;
    public static final double az = 1.0d;
    double aD = 0.0d;
    double aE = 1.0d;
    double aF = 1.0d;
    double aG = 1.0d;
    double aH = 0.0d;
    int aI = 0;
    double aJ = 0.8d;
    Handler aK;
    int aL = 0;
    Boolean aM = Boolean.valueOf(false);
    private long aN;
    private View aO;
    private List<View> aP = new ArrayList();
    private List<View> aQ = new ArrayList();
    private View aR;
    private HorizontalListView aS;
    private a aT;
    private View aU;
    private double[] aV = new double[]{0.0d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d, 0.8d};
    private View aW;
    private View aX;
    private View aY;
    private View aZ;
    protected b ab;
    protected FineCutHorizonalScrollView ac;
    protected DJITextView ad;
    protected DJITextView ae;
    protected DJIRelativeLayout af;
    protected DJIVideoSlidingBar ag;
    protected DJIVideoSlidingBar ah;
    protected DJIVideoSlidingBar ai;
    protected DJIVideoSlidingBar aj;
    protected DJIVideoSlidingBar ak;
    protected DJIVideoSlidingBar al;
    protected double am = 1.0d;
    protected RelativeLayout an;
    protected RelativeLayout ao;
    protected DJITextView ap;
    protected DJITextView aq;
    protected dji.pilot2.multimoment.a.a ar;
    int as;
    long at;
    String au;
    long av;
    long aw;
    long ax;
    private int ba = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.as = intent.getIntExtra(M, 0);
        this.aL = intent.getIntExtra(T, 0);
        this.au = intent.getStringExtra("filename");
        this.aD = intent.getDoubleExtra(P, 0.0d);
        this.aE = intent.getDoubleExtra(R, 1.0d);
        this.aF = intent.getDoubleExtra(Q, 1.2d);
        this.aG = intent.getDoubleExtra(U, 1.0d);
        this.aH = intent.getDoubleExtra(V, 0.0d);
        this.av = (long) intent.getIntExtra("starttime", 0);
        this.at = (long) intent.getIntExtra(L, 1);
        this.aw = (long) intent.getIntExtra("endtime", (int) this.at);
        this.aN = (long) intent.getIntExtra(aa, (int) this.at);
        this.aI = intent.getIntExtra(W, 0);
        this.aJ = intent.getDoubleExtra(X, 0.8d);
        if (this.aI != 0) {
            this.aV[this.aI] = this.aJ;
        }
        if (this.aD > 1.0d || this.aD < Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            this.aD = 0.0d;
        }
        if (this.aF > 2.0d || this.aF < 0.0d) {
            this.aF = 1.0d;
        }
        if (this.aE > 2.0d || this.aE < 0.0d) {
            this.aE = 1.0d;
        }
        if (this.aG < 0.1d || this.aG > 12.0d) {
            this.aG = 1.0d;
        }
        this.ax = (long) ((int) VideoLibWrapper.getVideoDuration(this.au));
        if (this.aL == 1 || this.aL == 2) {
            this.ax -= 250;
        }
        if (this.at == 0) {
            this.at = 1;
        }
        this.am = ((double) this.ax) / ((double) this.at);
        Log.i("zhang", "mSegTotalDuration:" + this.ax);
        Log.i("zhang", "mSegTotalDuration:" + this.ax);
        if (this.aL == 2) {
            this.am = c.c;
        }
        if (this.aL == 1) {
            setContentView(R.layout.v2_activity_singlemoment_fine);
        } else {
            setContentView(R.layout.v2_activity_multimoment_fine);
        }
        a();
        f();
        this.aK = new Handler(getMainLooper());
        g();
        n();
        r();
        this.aO.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (this.a.ba == 1) {
                        this.a.a(this.a.aI, this.a.aV[this.a.aI]);
                    } else if (this.a.ba == 2) {
                        this.a.a(this.a.aD, this.a.aF, this.a.aE);
                    }
                }
                if (motionEvent.getAction() == 0) {
                    if (this.a.ba == 1) {
                        this.a.a(0, 0.0d);
                    } else if (this.a.ba == 2) {
                        this.a.a(0.0d, 1.0d, 1.0d);
                    }
                }
                return false;
            }
        });
        this.aO.setOnClickListener(this);
    }

    protected void a() {
        this.aO = findViewById(R.id.cb5);
        this.ac = (FineCutHorizonalScrollView) findViewById(R.id.cb9);
        this.ad = (DJITextView) findViewById(R.id.cb1);
        this.ae = (DJITextView) findViewById(R.id.cb3);
        this.af = (DJIRelativeLayout) findViewById(R.id.c7w);
        this.ag = (DJIVideoSlidingBar) findViewById(R.id.cbn);
        this.ah = (DJIVideoSlidingBar) findViewById(R.id.cbp);
        this.ai = (DJIVideoSlidingBar) findViewById(R.id.cbr);
        this.aj = (DJIVideoSlidingBar) findViewById(R.id.cbc);
        this.ak = (DJIVideoSlidingBar) findViewById(R.id.cbu);
        this.al = (DJIVideoSlidingBar) findViewById(R.id.cbh);
        this.ak.setRange(0, 100, false);
        this.al.setRange(0, 100, false);
        this.ap = (DJITextView) findViewById(R.id.cb7);
        this.aq = (DJITextView) findViewById(R.id.cb8);
        this.ao = (RelativeLayout) findViewById(R.id.ca3);
        this.aY = findViewById(R.id.cbe);
        this.aZ = findViewById(R.id.cbk);
        this.aW = findViewById(R.id.cbf);
        this.aW.setOnClickListener(this);
        this.aW.setSelected(false);
        this.aX = findViewById(R.id.cbl);
        this.aX.setOnClickListener(this);
        this.aX.setSelected(false);
        this.ad.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.ae.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
                Intent intent = new Intent(this.a, DJIMultiMomentEditActivity.class);
                intent.putExtra("filename", this.a.au);
                intent.putExtra(DJIMultiMomentFineActivity.M, this.a.as);
                intent.putExtra(DJIMultiMomentFineActivity.R, this.a.aE);
                intent.putExtra(DJIMultiMomentFineActivity.Q, this.a.aF);
                intent.putExtra(DJIMultiMomentFineActivity.P, this.a.aD);
                intent.putExtra(DJIMultiMomentFineActivity.V, this.a.aH);
                intent.putExtra("starttime", this.a.av);
                intent.putExtra("endtime", this.a.aw);
                intent.putExtra(DJIMultiMomentFineActivity.W, this.a.aI);
                intent.putExtra(DJIMultiMomentFineActivity.X, this.a.aJ);
                if (this.a.aW.isSelected()) {
                    intent.putExtra(DJIMultiMomentFineActivity.Y, true);
                } else {
                    intent.putExtra(DJIMultiMomentFineActivity.Y, false);
                }
                if (this.a.aX.isSelected()) {
                    intent.putExtra(DJIMultiMomentFineActivity.Z, true);
                } else {
                    intent.putExtra(DJIMultiMomentFineActivity.Z, false);
                }
                if (this.a.aL == 1) {
                    intent.putExtra(DJIMultiMomentFineActivity.L, this.a.at);
                } else {
                    intent.putExtra(DJIMultiMomentFineActivity.U, this.a.aG);
                }
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        });
    }

    public void b() {
        if (this.aE != 1.0d) {
            e.b(k.bL_);
        }
        if (this.aD != 0.0d) {
            e.b(k.bJ_);
        }
        if (this.aF != 1.0d) {
            e.b(k.bK_);
        }
        if (this.aL != 1 && this.aG != 1.0d) {
            e.b(k.bI_);
        }
    }

    public void f() {
        if (this.ax < this.aw) {
            this.aw = (long) ((int) this.ax);
            this.av = this.aw - ((long) ((int) this.at));
        }
        int a = dji.pilot.fpv.model.b.a(this, R.dimen.b6);
        this.ab = new b(this);
        this.ab.a(a, this.at, this.au, this.ac);
        this.ac.initDatas(this.ab, this.aG);
        final int GetTotalLength = (int) ((this.ac.GetTotalLength() * this.av) / this.ax);
        this.ac.post(new Runnable(this) {
            final /* synthetic */ DJIMultiMomentFineActivity b;

            public void run() {
                this.b.ac.scrollTo(GetTotalLength, 0);
            }
        });
        this.ac.setMoveCallBack(new FineCutHorizonalScrollView.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void b(int i) {
                if (this.a.ar != null) {
                    this.a.ar.a(false);
                }
            }

            public void c(int i) {
                this.a.ap.setVisibility(0);
                if (i == 1) {
                    this.a.ap.setText(this.a.getResources().getString(R.string.v2_multimoment_fine_tip_long));
                } else {
                    this.a.ap.setText(this.a.getResources().getString(R.string.v2_multimoment_fine_tip_short));
                }
                this.a.aK.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.ap.setVisibility(4);
                    }
                }, 500);
            }

            public void d(int i) {
            }

            public void a(int i, int i2, int i3, int i4) {
                if ((this.a.ar != null && this.a.ar.c()) || i <= 0) {
                    return;
                }
                if ((this.a.aL == 1 || this.a.aL == 2) && this.a.ar != null) {
                    this.a.ar.a((long) ((int) (((double) (((long) i) * this.a.ax)) / (((double) this.a.ac.GetTotalLength()) * this.a.aG))));
                    return;
                }
                int GetTotalLength = (int) (((double) (((long) i) * this.a.ax)) / (((double) this.a.ac.GetTotalLength()) * this.a.aG));
                this.a.av = (long) ((int) (((double) GetTotalLength) * this.a.aG));
                this.a.aw = this.a.av + ((long) ((int) (((double) this.a.at) * this.a.aG)));
                if (this.a.aw > this.a.ax) {
                    this.a.aw = (long) ((int) this.a.ax);
                    this.a.av = this.a.aw - ((long) ((int) (((double) this.a.at) * this.a.aG)));
                }
                if (this.a.ar != null) {
                    this.a.b(this.a.ab.e);
                    this.a.ar.a((long) ((int) (((double) this.a.av) / this.a.aG)));
                }
            }
        });
    }

    protected void g() {
        String[] strArr = new String[]{this.au};
        double[] dArr = new double[1];
        double[] dArr2 = new double[]{0.0d};
        dArr2[0] = (double) this.ax;
        this.ar = (dji.pilot2.multimoment.a.a) getFragmentManager().findFragmentByTag(S);
        if (this.ar == null) {
            this.ar = dji.pilot2.multimoment.a.a.a(strArr, dArr, dArr2);
            getFragmentManager().beginTransaction().add(R.id.cb4, this.ar, S).commit();
        }
        if (this.ar != null) {
            this.ar.h();
            this.ar.a(new dji.pilot2.multimoment.a.a.a(this) {
                final /* synthetic */ DJIMultiMomentFineActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    if (this.a.ar != null) {
                        DJILogHelper.getInstance().LOGE("bob", "onStop mStartTime=" + this.a.av + "mSpeedValue = " + this.a.aG + "mEndTime=" + this.a.aw);
                        this.a.ar.b((int) (((double) this.a.av) / this.a.aG));
                    }
                }

                public void b() {
                    if (this.a.ar.i()) {
                        this.a.ar.a(this.a.aH);
                    }
                }

                public void a(long j) {
                    int i = (int) this.a.aw;
                    int i2 = (int) this.a.av;
                    if (((long) i2) <= j && j < ((long) i)) {
                        this.a.aM = Boolean.valueOf(false);
                        if (this.a.aL == 1 || this.a.aL == 2) {
                            this.a.ac.smoothScrollTo((int) ((double) ((this.a.ac.GetTotalLength() * j) / this.a.ax)), 0);
                            return;
                        }
                        long j2 = (long) this.a.ab.e;
                        this.a.b((int) ((double) ((((j - ((long) i2)) * this.a.ac.GetTotalLength()) / this.a.ax) + j2)));
                    } else if (this.a.aM.booleanValue() || (j >= ((long) i2) && j < ((long) i))) {
                        DJILogHelper.getInstance().LOGE("bob", "mStartTime=" + this.a.av + "mEndTime=" + this.a.aw + "timeMs = " + j);
                    } else {
                        DJILogHelper.getInstance().LOGE("bob", "mStartTime/mSpeedValue = " + (((double) this.a.av) / this.a.aG));
                        this.a.ar.a((long) ((int) (((double) this.a.av) / this.a.aG)));
                        this.a.aM = Boolean.valueOf(true);
                        if (this.a.aL == 1 || this.a.aL == 2) {
                            this.a.ac.smoothScrollTo((int) ((double) ((((long) i2) * this.a.ac.GetTotalLength()) / this.a.ax)), 0);
                            return;
                        }
                        this.a.b(this.a.ab.e);
                    }
                }

                public void c() {
                }
            });
            o();
            a(this.aI, this.aJ);
        }
    }

    public void a(long j, long j2) {
        if (((double) j) <= ((double) this.ax) / this.aG) {
            this.at = (long) (((double) j) * this.aG);
            this.av = (long) (((double) j2) * this.aG);
            this.aw = this.av + this.at;
            if (this.ar != null) {
                this.ar.a((long) (((double) this.av) / this.aG));
            }
            this.am = (double) (this.ax / this.at);
            if (this.aL == 2) {
                this.am = c.c;
            }
        }
    }

    public long h() {
        return (long) (((double) this.ax) / this.aG);
    }

    public int i() {
        return (int) (((double) this.av) / this.aG);
    }

    public int j() {
        return this.aL;
    }

    public int k() {
        return (int) (((double) this.aN) / this.aG);
    }

    public long l() {
        if (this.aL == 2) {
            return (long) ((int) (((double) this.at) / this.aG));
        }
        return this.at;
    }

    public void m() {
        if (this.aL == 0) {
            this.aw = (long) ((int) (((double) this.av) + (((double) this.at) * this.aG)));
        }
        if (this.ar != null) {
            b(this.ab.e);
            this.ar.a((long) ((int) (((double) this.av) / this.aG)));
        }
    }

    protected void n() {
        if (this.aL != 1) {
            this.aj.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
                final /* synthetic */ DJIMultiMomentFineActivity a;

                {
                    this.a = r1;
                }

                public void a(View view, double d, boolean z) {
                    DJILogHelper.getInstance().LOGI("bob", "OnValueChanged value= " + d + "mMaxSpeed = " + this.a.am);
                    if (d == 0.0d) {
                        this.a.aG = 1.0d;
                    } else if (d < 0.0d) {
                        this.a.aG = (100.0d + (0.9d * d)) / 100.0d;
                    } else {
                        this.a.aG = ((1.1d * d) / 10.0d) + 1.0d;
                    }
                    if (this.a.aG < 0.1d) {
                        this.a.aG = 0.1d;
                    }
                    if (this.a.aG > 12.0d) {
                        this.a.aG = 12.0d;
                    }
                    if (this.a.aL == 2 && this.a.aG > 1.0d) {
                        this.a.aG = (((this.a.aG - 1.0d) * 3.0d) / 11.0d) + 1.0d;
                    }
                    if (this.a.aG > this.a.am) {
                        double d2;
                        this.a.aG = this.a.am;
                        if (this.a.aG < 1.0d) {
                            d2 = (double) ((int) ((((this.a.aG * 100.0d) - 100.0d) * 10.0d) / 9.0d));
                        } else if (this.a.aG == 1.0d) {
                            d2 = 0.0d;
                        } else {
                            d2 = (((this.a.aG - 1.0d) * 10.0d) * 10.0d) / 11.0d;
                        }
                        DJILogHelper.getInstance().LOGI("bob", "value = " + d2);
                        this.a.aj.setValue(d2);
                    }
                    this.a.b(z, this.a.aG, R.string.v2_multimoment_fine_property_speed);
                    if (z) {
                        this.a.p();
                        this.a.o();
                        this.a.m();
                        this.a.ac.speedChange(this.a.aG);
                    }
                    DJILogHelper.getInstance().LOGI("bob", "mSpeedValue = " + this.a.aG + "mMaxSpeed = " + this.a.am);
                    view.invalidate();
                }
            });
        }
        this.ah.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(View view, double d, boolean z) {
                this.a.a(z, d, (int) R.string.v2_multimoment_fine_property_color);
                if (z) {
                    this.a.aF = ((double) (((int) d) + 100)) / 100.0d;
                    this.a.o();
                }
                view.invalidate();
            }
        });
        this.ag.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(View view, double d, boolean z) {
                this.a.a(z, d, (int) R.string.v2_multimoment_fine_property_light);
                if (z) {
                    this.a.aD = ((double) ((int) d)) / 100.0d;
                    this.a.o();
                }
                view.invalidate();
            }
        });
        this.ai.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(View view, double d, boolean z) {
                this.a.a(z, d, (int) R.string.v2_multimoment_fine_property_b_and_w);
                if (z) {
                    if (d < 0.0d) {
                        d *= 0.75d;
                    }
                    this.a.aE = ((double) (((int) d) + 100)) / 100.0d;
                    this.a.o();
                }
                view.invalidate();
            }
        });
        this.ak.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(View view, double d, boolean z) {
                this.a.c(z, d, R.string.v2_multimoment_slide_bar_tip);
                if (z) {
                    this.a.aH = ((double) ((int) d)) / 100.0d;
                    if (this.a.ar.i()) {
                        this.a.ar.a(this.a.aH);
                    }
                }
            }
        });
        this.al.setOnValueChanged(new DJIVideoSlidingBar.a(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(View view, double d, boolean z) {
                this.a.a(z, d);
                if (z) {
                    this.a.aJ = ((double) ((int) d)) / 100.0d;
                    this.a.aV[this.a.aI] = this.a.aJ;
                    this.a.a(this.a.aI, this.a.aJ);
                }
            }
        });
        this.aS = (HorizontalListView) findViewById(R.id.cak);
        this.aS.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.aT.a(view);
            }
        });
        this.aT = new a(this, dji.pilot2.multimoment.template.b.getInstance().b(this));
        this.aT.a(new a.b(this) {
            final /* synthetic */ DJIMultiMomentFineActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                DJILogHelper.getInstance().LOGD(this.a.TAG, "switch video filter to pos: " + i);
                this.a.a(i, true);
            }
        });
        this.aS.setAdapter(this.aT);
    }

    private void p() {
        if (this.aG != 1.0d && this.ar.i() && this.aH != 0.0d) {
            this.aH = 0.0d;
            this.ak.setValue(0.0d);
            this.ar.a(0.0d);
        }
    }

    private void a(boolean z, double d, int i) {
        if (z) {
            this.aq.go();
            return;
        }
        if (d > 0.0d) {
            this.aq.setText(String.format(getString(i) + " +%d", new Object[]{Integer.valueOf((int) d)}));
        } else {
            this.aq.setText(String.format(getString(i) + " %d", new Object[]{Integer.valueOf((int) d)}));
        }
        this.aq.show();
    }

    private void b(boolean z, double d, int i) {
        if (z) {
            this.aq.go();
            return;
        }
        if (d > 0.0d) {
            this.aq.setText(String.format(getString(i) + " X%.1f", new Object[]{Double.valueOf(d)}));
        } else {
            this.aq.setText(String.format(getString(i) + " %.1f", new Object[]{Double.valueOf(d)}));
        }
        this.aq.show();
    }

    private void c(boolean z, double d, int i) {
        if (z) {
            this.aq.go();
            return;
        }
        if (d > 0.0d) {
            this.aq.setText(String.format(getString(i) + " %d%%", new Object[]{Integer.valueOf((int) d)}));
        } else {
            this.aq.setText(String.format(getString(i) + " %d", new Object[]{Integer.valueOf((int) d)}));
        }
        this.aq.show();
    }

    private void a(boolean z, double d) {
        if (z) {
            this.aq.go();
            return;
        }
        if (d > 0.0d) {
            this.aq.setText(String.format("%d%%", new Object[]{Integer.valueOf((int) d)}));
        } else {
            this.aq.setText(String.format("%d", new Object[]{Integer.valueOf((int) d)}));
        }
        this.aq.show();
    }

    protected void o() {
        if (this.ar != null) {
            double[] dArr = new double[]{this.aD};
            this.ar.a(dji.pilot2.multimoment.a.a.e, dArr);
            dArr[0] = this.aF;
            this.ar.a(dji.pilot2.multimoment.a.a.f, dArr);
            dArr[0] = this.aE;
            this.ar.a(dji.pilot2.multimoment.a.a.g, dArr);
            dArr[0] = this.aG;
            this.ar.a(dji.pilot2.multimoment.a.a.h, dArr);
        }
    }

    protected void a(double d, double d2, double d3) {
        if (this.ar != null) {
            double[] dArr = new double[]{d};
            this.ar.a(dji.pilot2.multimoment.a.a.e, dArr);
            dArr[0] = d2;
            this.ar.a(dji.pilot2.multimoment.a.a.f, dArr);
            dArr[0] = d3;
            this.ar.a(dji.pilot2.multimoment.a.a.g, dArr);
        }
    }

    protected void onStart() {
        super.onStart();
        e.b(this);
    }

    protected void onResume() {
        super.onResume();
        if (this.av != 0) {
            this.aK.postDelayed(new Runnable(this) {
                final /* synthetic */ DJIMultiMomentFineActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.ar.a((long) ((int) (((double) this.a.av) / this.a.aG)));
                }
            }, 200);
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        e.c(this);
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.ac != null) {
            this.ac.onDestroy();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            b(getResources().getDimensionPixelSize(R.dimen.b8));
            q();
            this.aT.a(this.aI);
            a(this.aI, false);
            a(this.aI);
            if (getIntent().getBooleanExtra(Y, false)) {
                this.aW.setSelected(true);
            }
            if (getIntent().getBooleanExtra(Z, false)) {
                this.aX.setSelected(true);
            }
        }
    }

    private void q() {
        this.ah.setValue((double) ((int) ((this.aF * 100.0d) - 100.0d)));
        this.ag.setValue((double) ((int) (this.aD * 100.0d)));
        this.ai.setValue((double) ((int) ((this.aE * 100.0d) - 100.0d)));
        this.ak.setValue((double) ((int) (this.aH * 100.0d)));
        this.al.setValue((double) ((int) (this.aJ * 100.0d)));
        if (this.aL != 1) {
            int i;
            if (this.aG < 1.0d) {
                i = (int) ((((this.aG * 100.0d) - 100.0d) * 10.0d) / 9.0d);
            } else if (this.aG == 1.0d) {
                i = 0;
            } else {
                i = (int) (((this.aG - 1.0d) * 10.0d) / 1.1d);
            }
            if (this.aL == 2 && this.aG > 1.0d) {
                i = (int) (((this.aG - 1.0d) * 100.0d) / 3.0d);
            }
            this.aj.setValue((double) i);
            DJILogHelper.getInstance().LOGI("mmm", "value=" + i + ", mSpeedValue= " + this.aG);
            return;
        }
        this.aj.setValue(1.0d);
    }

    private void b(int i) {
        this.af.setX((float) (i - (this.af.getWidth() / 2)));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cbf:
                if (this.aW.isSelected()) {
                    this.aW.setSelected(false);
                    return;
                } else {
                    this.aW.setSelected(true);
                    return;
                }
            case R.id.cbl:
                if (this.aX.isSelected()) {
                    this.aX.setSelected(false);
                    return;
                } else {
                    this.aX.setSelected(true);
                    return;
                }
            case R.id.cby:
            case R.id.cbz:
            case R.id.cc0:
            case R.id.cc1:
                s();
                a(view);
                return;
            default:
                return;
        }
    }

    private void r() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.cbx);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(i);
            if (viewGroup2.getChildCount() != 0) {
                viewGroup2.setOnClickListener(this);
                this.aP.add(viewGroup2);
            }
        }
        this.aQ.add((ViewGroup) findViewById(R.id.cbb));
        this.aQ.add((ViewGroup) findViewById(R.id.cbd));
        this.aQ.add((ViewGroup) findViewById(R.id.cbj));
        this.aQ.add((ViewGroup) findViewById(R.id.cbt));
        this.aR = findViewById(R.id.cbw);
        this.aU = findViewById(R.id.cbi);
        this.aR.setOnClickListener(this);
        this.aU.setOnClickListener(this);
        s();
        if (this.aL != 1) {
            a((View) this.aP.get(0));
        } else {
            a((View) this.aP.get(1));
        }
    }

    private void s() {
        int size = this.aP.size();
        for (int i = 0; i < size; i++) {
            ((View) this.aP.get(i)).setSelected(false);
            ((View) this.aQ.get(i)).setVisibility(4);
        }
    }

    private void a(View view) {
        if (this.aQ.size() == this.aP.size()) {
            this.ba = this.aP.indexOf(view);
            view.setSelected(true);
            ((View) this.aQ.get(this.aP.indexOf(view))).setVisibility(0);
            if ((this.aP.indexOf(view) == 3 && !this.ar.i()) || (this.aP.indexOf(view) == 0 && this.aL == 1)) {
                this.aR.setVisibility(0);
            } else if (this.aP.indexOf(view) != 3 || this.aG == 1.0d) {
                this.aR.setVisibility(4);
            } else {
                this.aR.setVisibility(0);
            }
            if (this.aP.indexOf(view) == 1) {
                this.aY.setVisibility(0);
                this.aZ.setVisibility(4);
                if (this.aI < this.aS.getFirstVisiblePosition() || this.aI > this.aS.getLastVisiblePosition()) {
                    a(this.aI);
                }
            } else if (this.aP.indexOf(view) == 2) {
                this.aZ.setVisibility(0);
                this.aY.setVisibility(4);
            } else {
                this.aY.setVisibility(4);
                this.aZ.setVisibility(4);
            }
            if (this.aP.indexOf(view) == 1 || this.aP.indexOf(view) == 2) {
                this.aO.setVisibility(0);
            } else {
                this.aO.setVisibility(4);
            }
        }
    }

    private void a(int i, double d) {
        int[] iArr = new int[1];
        double[] dArr = new double[]{i};
        dArr[0] = d;
        this.ar.a(dji.pilot2.multimoment.a.a.i, iArr, dArr);
    }

    public void a(int i) {
        int dimensionPixelSize;
        Resources resources = getResources();
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            dimensionPixelSize = (resources.getDimensionPixelSize(R.dimen.bh) * 2) + resources.getDimensionPixelSize(R.dimen.a8s);
        } else {
            dimensionPixelSize = (resources.getDimensionPixelSize(R.dimen.bh) * 2) + resources.getDimensionPixelSize(R.dimen.bi);
        }
        int i2 = (i * dimensionPixelSize) - dimensionPixelSize;
        dimensionPixelSize *= 11;
        if (i2 <= 0) {
            i2 = 0;
        }
        if (i2 <= dimensionPixelSize) {
            dimensionPixelSize = i2;
        }
        this.aS.scrollTo(dimensionPixelSize);
    }

    private void a(int i, boolean z) {
        if (i == 0) {
            this.aU.setVisibility(0);
        } else {
            this.aU.setVisibility(4);
        }
        this.al.setValue((double) ((int) (this.aV[i] * 100.0d)));
        this.aI = i;
        this.aJ = this.aV[i];
        if (z) {
            a(i, this.aV[i]);
        }
    }
}
