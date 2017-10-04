package dji.pilot2.cutmoment;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import dji.g.b.h;
import dji.g.b.i;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.media.a.g;
import dji.midware.media.d;
import dji.midware.media.e;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.fpv.d.c.k;
import dji.pilot.usercenter.f.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.b;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.utils.n;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageButton;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DJICutMomentActivity extends DJIActivityNoFullScreen implements OnClickListener, k, c$l {
    public static final String K = "SELECTED_VIDEOS";
    public static final String L = "SELECTED_VIDEO_TYPE";
    public static final String M = "ismanager";
    public static final String N = "isInput";
    private static final String R = "DJICutMomentActivity";
    public boolean O = false;
    protected DJIImageButton P;
    Callback Q = new Callback(this) {
        final /* synthetic */ DJICutMomentActivity a;

        {
            this.a = r1;
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            int TimeToLength = this.a.aj.TimeToLength(a.c, this.a.af);
            LayoutParams layoutParams = this.a.ae.getLayoutParams();
            layoutParams.width = TimeToLength;
            this.a.ae.setLayoutParams(layoutParams);
            this.a.a(0);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (!this.a.ao) {
                this.a.m();
            }
        }
    };
    private boolean S = false;
    private final int T = 1;
    private final int U = 20;
    private b V;
    private dji.g.b.a W;
    private long X = 0;
    private DJIMovingSurfaceView Y;
    private String[] Z;
    private int aA;
    private int aB;
    private DJILinearLayout aC;
    private DJILinearLayout aD;
    private TextView aE;
    private RelativeLayout aF;
    private String aG = "";
    private ArrayList<String> aH = new ArrayList();
    private View aI;
    private View aJ;
    private TextView aa;
    private ImageView ab;
    private DJIImageButton ac;
    private DJIImageButton ad;
    private ImageView ae;
    private long af;
    private Boolean ag = Boolean.valueOf(false);
    private Handler ah;
    private DJIRelativeLayout ai;
    private DJICutTagBar aj;
    private RelativeLayout ak;
    private DJITextView al;
    private int am;
    private boolean an = false;
    private boolean ao = false;
    private dji.pilot2.widget.a ap;
    private Animation aq = null;
    private Button ar;
    private DJIRelativeLayout as;
    private long at = 0;
    private long au = 0;
    private boolean av = true;
    private double aw;
    private boolean ax = false;
    private DJITextView ay;
    private boolean az = true;

    public interface SaveCutCallBackInterface {
        void onFinished(int i);

        void onProgress(int i);

        void onStarted();
    }

    public class SaveCutCallBack implements SaveCutCallBackInterface {
        public double videosSumTime;

        public SaveCutCallBack(double d) {
            this.videosSumTime = d;
        }

        public void onStarted() {
        }

        public void onProgress(int i) {
        }

        public void onFinished(int i) {
        }
    }

    class a extends dji.pilot2.a.a {
        int a;
        int b;
        final /* synthetic */ DJICutMomentActivity c;

        a(DJICutMomentActivity dJICutMomentActivity) {
            this.c = dJICutMomentActivity;
        }

        public void a() {
            this.a = this.c.V.c().size();
            this.b = 0;
            this.c.ao = true;
            a aVar;
            long j;
            String str;
            if (!this.c.aJ.isSelected()) {
                Iterator it = this.c.V.c().iterator();
                while (it.hasNext()) {
                    String str2;
                    String absolutePath;
                    aVar = (a) it.next();
                    this.b++;
                    e.d(DJICutMomentActivity.R, "curSegmentIndex=" + this.b + " numAllSegment=" + this.a);
                    long j2 = aVar.e;
                    j = aVar.f;
                    String str3 = "" + System.currentTimeMillis();
                    if (b.e(this.c.Z[0]) || this.c.S) {
                        str2 = n.e(this.c) + str3 + ".mp4";
                    } else {
                        str2 = n.d(this.c) + str3 + ".mp4";
                    }
                    String str4 = n.g(this.c) + str3 + ".mp4";
                    str = this.c.Z[0];
                    dji.g.b.e[] eVarArr = new dji.g.b.e[]{new dji.g.b.e(str, dji.g.a.a.a(str), j2, j, false, 1.0d)};
                    double[] clipParameter = this.c.Y.getClipParameter();
                    eVarArr[0].a(clipParameter[0], clipParameter[1], clipParameter[2], clipParameter[3]);
                    i anonymousClass1 = new i(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                        }

                        public void a(int i) {
                            final int i2 = (int) ((100.0d / ((double) this.a.a)) * (((((double) i) / 100.0d) + ((double) this.a.b)) - 1.0d));
                            this.a.c.ah.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void run() {
                                    this.b.a.c.al.setText(String.valueOf(i2) + "%");
                                }
                            });
                        }

                        public void b(int i) {
                        }

                        public void c(int i) {
                            e.b("", "save error toast");
                            this.a.c.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    Toast.makeText(this.a.a.c, this.a.a.c.getString(R.string.video_cut_saving_failure), 1).show();
                                }
                            });
                        }
                    };
                    File file = new File(this.c.Z[0].substring(0, this.c.Z[0].length() - 4) + ".m4a");
                    if (file.exists()) {
                        absolutePath = file.getAbsolutePath();
                    } else {
                        absolutePath = str;
                    }
                    h hVar = new h(eVarArr, true, absolutePath, dji.g.a.a.a(absolutePath), str4, true, 1280, 720, null, anonymousClass1, null, 0);
                    hVar.a(1000 * j2);
                    if (this.c.W.a(hVar) == 0) {
                        try {
                            new File(str4).renameTo(new File(str2));
                            this.c.aG = str2;
                            this.c.aH.add(this.c.aG);
                            p.a(this.c, str2);
                            e.d(DJICutMomentActivity.R, "From cache. CaptureDate = " + dji.midware.media.e.e.a(str3, str, (int) j2, (int) j).m());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        p.d(str4);
                    }
                }
            } else if (this.c.aJ.isSelected()) {
                g gVar = new g();
                str = this.c.Z[0];
                Vector vector = new Vector();
                String e2 = n.e(this.c);
                Iterator it2 = this.c.V.c().iterator();
                while (it2.hasNext()) {
                    aVar = (a) it2.next();
                    j = aVar.e;
                    long j3 = aVar.f;
                    try {
                        vector.add(dji.midware.media.e.e.a(("hd_" + System.currentTimeMillis()) + "", str, (int) j, (int) j3));
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return;
                    }
                }
                try {
                    gVar.a(vector, e2, new g.b(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void b() {
                            e.a("Cut onStarted");
                        }

                        public void a(Exception exception) {
                            e.a(exception);
                        }

                        public void a(final int i) {
                            this.a.c.ah.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 b;

                                public void run() {
                                    this.b.a.c.al.setText(String.valueOf(i) + "%");
                                }
                            });
                        }

                        public void a() {
                            e.a("Cut onSuccess");
                        }
                    });
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c.a().a(this);
        this.Z = getIntent().getStringArrayExtra(K);
        this.O = getIntent().getBooleanExtra(M, false);
        this.S = getIntent().getBooleanExtra(N, false);
        dji.pilot2.media.c cVar = new dji.pilot2.media.c();
        cVar.a(b());
        this.aA = cVar.b();
        this.aB = cVar.c();
        float e = cVar.e();
        if (Math.abs(e) > 1.0f) {
            e.a(R, "rotate=" + e);
        }
        if (Math.abs(e - 90.0f) < 1.0f || Math.abs(e - 270.0f) < 1.0f) {
            int i = this.aA;
            this.aA = this.aB;
            this.aB = i;
        }
        setContentView(R.layout.v2_activity_cutmoment);
        DJIOriLayout.setOrientationByDevice(this);
        findViewById(R.id.c7j).setFitsSystemWindows(true);
        findViewById(R.id.c7n).setOnClickListener(this);
        this.ar = (Button) findViewById(R.id.c84);
        this.ar.setOnClickListener(this);
        this.as = (DJIRelativeLayout) findViewById(R.id.c85);
        this.P = (DJIImageButton) findViewById(R.id.c7z);
        this.P.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a.a(this.a.af, this.a.X, this.a.V, null);
                this.a.s();
                this.a.aj.changeCutPoint(this.a.V);
                if (this.a.V.c().size() != 0) {
                    this.a.as.setVisibility(4);
                }
            }
        });
        if (d.a() < 18) {
            this.ar.setEnabled(false);
            this.P.setEnabled(false);
            Toast.makeText(this, getString(R.string.videoeditor_unsupported), 1).show();
        }
        this.Y = (DJIMovingSurfaceView) findViewById(R.id.c7k);
        this.Y.setOnClickListener(this);
        this.ab = (ImageView) findViewById(R.id.c7s);
        this.ab.setOnClickListener(this);
        this.aa = (TextView) findViewById(R.id.c7r);
        this.W = new dji.g.b.a();
        this.W.a(new dji.midware.media.i.g.b(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void a(dji.midware.media.i.g gVar) {
                this.a.ah.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass12 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.ax) {
                            this.a.a.o();
                            this.a.a.ax = false;
                        }
                    }
                });
            }
        });
        if (this.Z.length > 0) {
            try {
                this.Y.getHolder().setType(3);
                this.Y.getHolder().addCallback(this.Q);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object add : this.Z) {
            arrayList.add(add);
        }
        this.V = new b(arrayList);
        this.ah = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ DJICutMomentActivity a;

            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (!this.a.an && this.a.W != null) {
                    sendEmptyMessageDelayed(1, 20);
                    this.a.X = (long) this.a.W.f();
                    Log.i("ren", "mCurrPlayTime " + this.a.X);
                    this.a.a(this.a.X);
                    this.a.aa.setText(p.f((int) (((float) this.a.X) / 1000.0f)) + dji.pilot.usercenter.protocol.d.t + p.f((int) (((float) this.a.af) / 1000.0f)));
                    if (this.a.ag.booleanValue()) {
                        if ((this.a.X < this.a.at || this.a.X > this.a.au) && this.a.av) {
                            this.a.l();
                        }
                        this.a.aj.updateScrollLocation((float) this.a.X, (float) this.a.af);
                        return;
                    }
                    this.a.aj.updateScrollLocation((float) this.a.X, (float) this.a.af);
                }
            }
        };
        this.aj = (DJICutTagBar) findViewById(R.id.c7u);
        this.aj.initData(this.Z[0], this);
        this.ai = (DJIRelativeLayout) findViewById(R.id.c7w);
        a(this.aj.getDisplayWidth() / 4);
        this.aj.setListener(new DJICutTagBar.b(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void a(float f) {
                if (f < 0.0f) {
                    f = 0.0f;
                }
                if (this.a.W != null) {
                    this.a.W.a((long) ((int) f));
                }
                this.a.X = (long) ((int) f);
                this.a.a((long) f);
                this.a.aa.setText(p.f((int) (((float) this.a.X) / 1000.0f)) + dji.pilot.usercenter.protocol.d.t + p.f((int) (((float) this.a.af) / 1000.0f)));
            }

            public void a() {
                if (!this.a.az) {
                    return;
                }
                if (this.a.an) {
                    this.a.an = false;
                    this.a.m();
                    return;
                }
                this.a.a(this.a.aj.getDisplayWidth() / 4);
                this.a.ah.sendEmptyMessageDelayed(1, 20);
                this.a.an = false;
                this.a.ab.setVisibility(4);
            }

            public void b() {
                if (!this.a.aj.isFling()) {
                    this.a.az = this.a.W.h();
                }
                this.a.ah.removeMessages(1);
                if (!this.a.Y.getMovingOnOff()) {
                    this.a.ab.setVisibility(0);
                }
            }

            public void a(View view, boolean z) {
                if (view != null) {
                    this.a.ac.setVisibility(0);
                    this.a.ad.setVisibility(0);
                    this.a.ae.setVisibility(4);
                    this.a.P.setVisibility(4);
                    this.a.ar.setVisibility(4);
                    this.a.ae.setVisibility(4);
                    this.a.ay.setVisibility(4);
                    this.a.at = ((a) view.getTag()).e;
                    this.a.au = ((a) view.getTag()).f;
                    this.a.ag = Boolean.valueOf(true);
                    if (z && this.a.az) {
                        this.a.l();
                    } else {
                        this.a.aj.getListener().a((float) this.a.aj.LengthToTime((long) this.a.aj.getScrollX()));
                    }
                }
            }

            public void c() {
                this.a.s();
                this.a.ac.setVisibility(4);
                this.a.ad.setVisibility(4);
                this.a.P.setVisibility(0);
                this.a.ay.setVisibility(4);
                this.a.ae.setVisibility(0);
                this.a.ag = Boolean.valueOf(false);
            }

            public void d() {
            }

            public void e() {
            }
        });
        this.W.a(new dji.midware.media.i.g.a(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void a(dji.midware.media.i.g gVar) {
                this.a.an = true;
                this.a.ah.removeMessages(1);
                this.a.p();
            }
        });
        this.al = (DJITextView) findViewById(R.id.c8a);
        this.ak = (RelativeLayout) findViewById(R.id.c89);
        this.ak.setOnClickListener(this);
        this.ak.setVisibility(8);
        this.ac = (DJIImageButton) findViewById(R.id.c81);
        this.ad = (DJIImageButton) findViewById(R.id.c80);
        this.ac.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.aj.delCurCutSegView();
                this.a.a(this.a.aj.getDisplayWidth() / 4);
                this.a.s();
                this.a.ac.setVisibility(4);
                this.a.ad.setVisibility(4);
                this.a.P.setVisibility(0);
                this.a.ar.setVisibility(0);
                this.a.ae.setVisibility(0);
                this.a.ay.setVisibility(4);
                this.a.ag = Boolean.valueOf(false);
                if (this.a.V.c().size() == 0) {
                    this.a.as.setVisibility(0);
                }
            }
        });
        this.ad.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.aj.comfirmMomentTag();
                this.a.ac.setVisibility(4);
                this.a.ad.setVisibility(4);
                this.a.P.setVisibility(0);
                this.a.ar.setVisibility(0);
                this.a.ae.setVisibility(0);
                this.a.ay.setVisibility(4);
                this.a.a(this.a.aj.getDisplayWidth() / 4);
                this.a.ag = Boolean.valueOf(false);
            }
        });
        this.ae = (ImageView) findViewById(R.id.c7v);
        this.aq = AnimationUtils.loadAnimation(this, R.anim.a4);
        this.ay = (DJITextView) findViewById(R.id.c7p);
        this.ay.go();
        this.ay.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
                this.a.q();
            }
        });
        this.aC = (DJILinearLayout) findViewById(R.id.c87);
        this.aD = (DJILinearLayout) findViewById(R.id.c88);
        this.aE = (TextView) findViewById(R.id.c7o);
        this.aF = (RelativeLayout) findViewById(R.id.c7t);
        if (this.Y.getMovingDrection() == dji.pilot2.cutmoment.DJIMovingSurfaceView.a.DEFAULT) {
            j();
        } else if (this.Y.getMovingDrection() == dji.pilot2.cutmoment.DJIMovingSurfaceView.a.VERTICAL) {
            a(this.aC);
        } else if (this.Y.getMovingDrection() == dji.pilot2.cutmoment.DJIMovingSurfaceView.a.HORIZONTAL) {
            a(this.aD);
        }
        this.aI = findViewById(R.id.c82);
        this.aJ = findViewById(R.id.c83);
        this.aJ.setOnClickListener(this);
        this.aJ.setSelected(false);
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.aI.setVisibility(4);
        } else {
            this.aI.setVisibility(4);
        }
    }

    private void a(DJILinearLayout dJILinearLayout) {
        dJILinearLayout.setVisibility(0);
        this.P.setVisibility(4);
        this.aE.setText(getString(R.string.v2_cutmoment_clip_film));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.aF.getLayoutParams();
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.a9);
        } else {
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.a9) * 2;
        }
        this.aF.setLayoutParams(layoutParams);
        this.ar.setText(getResources().getString(R.string.v2_cutmoment_clip_film_next));
        this.as.setVisibility(4);
        this.ae.setVisibility(4);
    }

    private void j() {
        this.Y.backToNormalLayout();
        this.aD.setVisibility(4);
        this.aC.setVisibility(4);
        this.P.setVisibility(0);
        this.aE.setText(getString(R.string.ve_create_moment));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.aF.getLayoutParams();
        layoutParams.topMargin = 0;
        this.aF.setLayoutParams(layoutParams);
        this.ar.setText(getResources().getString(R.string.confirm));
        this.as.setVisibility(0);
        this.ae.setVisibility(0);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            boolean a = dji.pilot2.widget.a.a(this, 1);
            if (a && !this.Y.getMovingOnOff()) {
                this.ah.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJICutMomentActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.o();
                        this.a.k();
                    }
                }, 600);
            } else if (a) {
                dji.pilot2.widget.a.c(true);
            }
        }
    }

    public void onPause() {
        super.onPause();
        Log.i(R, "DJICutMomentActivity onPause");
        ((AudioManager) getSystemService("audio")).abandonAudioFocus(null);
        if (!this.ao) {
            n();
        }
        this.ax = true;
        if (this.ag.booleanValue()) {
            this.aj.comfirmMomentTag();
            this.ac.setVisibility(4);
            this.ad.setVisibility(4);
            this.P.setVisibility(0);
            this.ar.setVisibility(0);
            this.ae.setVisibility(0);
            this.ay.setVisibility(4);
            a(this.aj.getDisplayWidth() / 4);
            this.ag = Boolean.valueOf(false);
        }
    }

    public void onResume() {
        super.onResume();
        Log.i(R, "DJICutMomentActivity onResume");
        ((AudioManager) getSystemService("audio")).requestAudioFocus(null, 3, 1);
        if (!this.ao && this.Y.getHolder().getSurface().isValid()) {
            Log.i(R, "DJICutMomentActivity surface isValid true");
            m();
        }
    }

    private void k() {
        dji.pilot2.widget.a.b(this, 1);
        this.ap = new dji.pilot2.widget.a(this, R.style.hf, 1);
        WindowManager.LayoutParams attributes = this.ap.getWindow().getAttributes();
        int[] c = p.c((Context) this);
        attributes.width = c[0];
        attributes.height = c[1];
        this.ap.getWindow().setAttributes(attributes);
        this.ap.a(0.0f);
        int[] iArr = new int[2];
        this.P.getLocationInWindow(iArr);
        int width = this.P.getWidth() / 2;
        this.ap.a(iArr[0], iArr[1], width, getResources().getDimensionPixelSize(R.dimen.fj));
        this.ap.show();
    }

    private void a(dji.pilot2.cutmoment.DJIMovingSurfaceView.a aVar) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.ac);
        switch (aVar) {
            case VERTICAL:
                a((int) R.drawable.v2_first_tip_moving_surface_vertical, dimensionPixelSize);
                return;
            case HORIZONTAL:
                a((int) R.drawable.v2_first_tip_moving_surface_horizental, dimensionPixelSize);
                return;
            default:
                return;
        }
    }

    private void a(int i, int i2) {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_ve_firsttip_moving_surface, (ViewGroup) findViewById(R.id.s7));
        ((DJIImageView) inflate.findViewById(R.id.s8)).setImageResource(i);
        Toast toast = new Toast(this);
        toast.setGravity(49, 0, i2);
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }

    private void l() {
        this.ah.removeMessages(1);
        if (this.at < 0) {
            this.at = 0;
        }
        this.W.a((long) ((int) this.at));
        p();
        this.av = false;
        this.ah.postDelayed(new Runnable(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.av = true;
            }
        }, 4000);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c7k:
            case R.id.c7s:
            case R.id.c7z:
                if (!this.Y.getMovingOnOff()) {
                    if (this.W.h()) {
                        o();
                        return;
                    } else {
                        p();
                        return;
                    }
                }
                return;
            case R.id.c7n:
                if (this.Y.getMovingOnOff()) {
                    dji.pilot.fpv.d.e.b(c$l.l);
                }
                finish();
                return;
            case R.id.c83:
                if (this.aJ.isSelected()) {
                    this.aJ.setSelected(false);
                    return;
                } else {
                    this.aJ.setSelected(true);
                    return;
                }
            case R.id.c84:
                if (this.Y.getMovingOnOff()) {
                    dji.pilot.fpv.d.e.b(c$l.k);
                    j();
                    if (dji.pilot2.widget.a.e()) {
                        dji.pilot2.widget.a.c(false);
                        this.ah.postDelayed(new Runnable(this) {
                            final /* synthetic */ DJICutMomentActivity a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.o();
                                this.a.k();
                            }
                        }, 600);
                    }
                }
                if (this.V.c().size() != 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dF, Integer.toString(this.V.c().size()));
                    dji.pilot.fpv.d.e.a(k.cA_, hashMap);
                    this.ab.setVisibility(4);
                    this.ah.removeMessages(1);
                    if (this.W != null) {
                        this.W.e();
                    }
                    this.ak.setVisibility(0);
                    this.aw = 0.0d;
                    Iterator it = this.V.c().iterator();
                    while (it.hasNext()) {
                        a aVar = (a) it.next();
                        this.aw += ((double) (aVar.f - aVar.e)) / 1000.0d;
                        hashMap = new HashMap();
                        hashMap.put(dji.pilot.fpv.d.d.dG, Integer.toString((int) Math.rint(this.aw)));
                        dji.pilot.fpv.d.e.a(k.by_, hashMap);
                    }
                    this.am = 0;
                    this.al.setText(String.valueOf(this.am) + "%");
                    a(this.ak);
                    Runnable aVar2 = new a(this);
                    aVar2.a(new dji.pilot2.a.e(this) {
                        final /* synthetic */ DJICutMomentActivity a;

                        {
                            this.a = r1;
                        }

                        public void a(dji.pilot2.a.a aVar) {
                            this.a.Z[0].substring(0, this.a.Z[0].lastIndexOf(47));
                            this.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.runOnUiThread(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.a.a.ak.setVisibility(0);
                                        }
                                    });
                                }
                            });
                        }

                        public void a(dji.pilot2.a.a aVar, int i, int i2) {
                        }

                        public void b(dji.pilot2.a.a aVar) {
                            if (this.a.W != null) {
                                this.a.W.d();
                            }
                            this.a.ao = false;
                            p.a(this.a);
                            DJILogHelper.getInstance().LOGD("", "cut filename: " + this.a.Z[0], false, true);
                            if (this.a.aH != null && this.a.aH.size() > 0) {
                                Intent intent = new Intent();
                                if (b.e(this.a.Z[0]) || this.a.S) {
                                    intent.putExtra(DJIMainFragmentActivity.P, true);
                                }
                                intent.putExtra(DJIMainFragmentActivity.O, this.a.aH);
                                this.a.setResult(0, intent);
                            }
                            this.a.finish();
                        }
                    });
                    new Thread(aVar2).start();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void m() {
        try {
            this.W.j();
            this.W.a(3);
            File file = new File(this.Z[0].substring(0, this.Z[0].length() - 4) + ".m4a");
            if (file.exists()) {
                this.W.a(this.Z[0], file.getAbsolutePath());
            } else {
                this.W.a(this.Z[0]);
            }
            this.W.a(this.Y.getHolder());
            this.W.b();
            this.af = (long) this.W.g();
            a(0);
            this.aa.setText(p.f(0) + dji.pilot.usercenter.protocol.d.t + p.f((int) (this.af / 1000)));
            p();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n() {
        if (this.W != null) {
            this.W.d();
            this.ab.setVisibility(4);
            this.ah.removeMessages(1);
        }
    }

    private void o() {
        if (this.W != null) {
            this.W.e();
        }
        this.ah.removeMessages(1);
        if (!this.Y.getMovingOnOff()) {
            this.ab.setVisibility(0);
        }
    }

    private void p() {
        if (this.an) {
            this.an = false;
            m();
            return;
        }
        a(this.aj.getDisplayWidth() / 4);
        this.W.c();
        this.ah.sendEmptyMessageDelayed(1, 20);
        this.an = false;
        this.ab.setVisibility(4);
    }

    public boolean a() {
        return this.W.h();
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    public void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }

    public void onDestroy() {
        if (this.W != null) {
            this.W.a();
            this.W = null;
        }
        if (this.aj != null) {
            this.aj.cutTagBitmapClear();
            this.aj.cancelAsyncTask();
        }
        b(this.ak);
        c.a().d(this);
        super.onDestroy();
    }

    public void saveToHDclick(View view) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && !this.ao) {
            finish();
        }
        return false;
    }

    private void q() {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        bVar.setMessage(getResources().getString(R.string.v2_delete_video_confirm));
        bVar.setPositiveButton(R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                final Intent intent = new Intent();
                intent.putExtra(DJIMainFragmentActivity.N, true);
                this.a.n();
                if (this.a.W != null) {
                    this.a.W.a();
                    this.a.W = null;
                }
                this.a.r();
                this.a.ah.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        this.b.a.setResult(0, intent);
                        this.b.a.finish();
                    }
                }, 500);
            }
        });
        bVar.setNegativeButton(R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJICutMomentActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    private void r() {
        File file = new File(this.Z[0]);
        if (file.exists() && !this.S) {
            file.delete();
        }
        if (this.S) {
            DJIScanerMediaManager.getInstance(this).deleteMediaFromDbByPath(this.Z[0]);
        }
        c.a().e(dji.pilot2.library.a.CacheDelete);
        File file2 = new File(this.Z[0].substring(0, this.Z[0].lastIndexOf(".")) + ".info");
        if (file2.exists()) {
            file2.delete();
        }
        f.a((Context) this, b());
    }

    private void a(long j) {
        if (j <= a.a) {
            this.ae.setX((float) ((this.aj.getDisplayWidth() / 4) - this.aj.getScrollX()));
        } else if (this.af - j <= a.b) {
            this.ae.setX((float) ((this.aj.getDisplayWidth() / 4) - this.aj.TimeToLength((a.c + j) - this.af, this.af)));
        } else {
            this.ae.setX((float) ((this.aj.getDisplayWidth() / 4) - this.aj.TimeToLength(a.a, this.af)));
        }
    }

    public String b() {
        if (this.Z == null || this.Z.length <= 0) {
            return null;
        }
        return this.Z[0];
    }

    public void a(int i) {
        this.ai.setX((float) (i - (this.ai.getWidth() / 2)));
    }

    private void s() {
        if (this.V.c().size() == 0) {
            this.ar.setText(getResources().getString(R.string.confirm));
        } else {
            this.ar.setText(getResources().getString(R.string.confirm) + "(" + this.V.c().size() + ")");
        }
    }

    public boolean f() {
        return this.ao;
    }

    public void g() {
        o();
    }

    private void a(View view) {
        Drawable background = ((ImageView) view.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).start();
        }
    }

    private void b(View view) {
        Drawable background = ((ImageView) view.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).stop();
        }
    }

    public void onEventBackgroundThread(o oVar) {
        Log.i("rxq", "onEventBackgroundThread");
        if (oVar == o.b) {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ DJICutMomentActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.aI.setVisibility(4);
                }
            });
        } else {
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ DJICutMomentActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.aI.setVisibility(4);
                }
            });
        }
    }

    public int h() {
        return this.aA;
    }

    public int i() {
        return this.aB;
    }
}
