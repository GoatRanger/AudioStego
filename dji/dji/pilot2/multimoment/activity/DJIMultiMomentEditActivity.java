package dji.pilot2.multimoment.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.meetme.android.horizontallistview.HorizontalListView;
import dji.g.b.i;
import dji.log.DJILogHelper;
import dji.midware.media.e.f;
import dji.pilot.R;
import dji.pilot.fpv.d.c$n;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.multimoment.adapter.BitFilmAdapter;
import dji.pilot2.multimoment.adapter.d;
import dji.pilot2.multimoment.adapter.e;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.multimoment.videolib.EditRecoverInfo;
import dji.pilot2.multimoment.videolib.c;
import dji.pilot2.multimoment.videolib.imageCreator;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.pilot2.multimoment.view.HorizonalSegmentView.h;
import dji.pilot2.multimoment.view.HorizonalTemplateListView;
import dji.pilot2.multimoment.view.ViewTransitions;
import dji.pilot2.nativeaudio.DJIAudioPlayActivity;
import dji.pilot2.nativeaudio.DJINativeAudioActivity;
import dji.pilot2.nativeaudio.NetworkAudioPreviewActivity;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel.MultiBigFilmModel;
import dji.pilot2.nativeaudio.model.TemplateDownloadEvent;
import dji.pilot2.share.activity.DJIShareActivity;
import dji.pilot2.utils.g;
import dji.pilot2.utils.n;
import dji.pilot2.utils.o;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageButton;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class DJIMultiMomentEditActivity extends DJIActivityNoFullScreen implements k {
    public static final String K = "frageMulti";
    public static final int L = 2000;
    public static final String M = "moments";
    public static final String N = "duration";
    public static final int O = 1;
    public static final int P = 2;
    public static final int Q = 3;
    public static final int R = 4;
    public static final int S = 250;
    public static final double T = 10000.0d;
    public static final String X = "dji_edit_info_file_bak";
    boolean U = false;
    boolean V = false;
    protected c W;
    public int Y;
    ScheduledExecutorService Z;
    private String aA;
    private Object aB = new Object();
    private Thread aC;
    private DJIRelativeLayout aD;
    private int aE = 0;
    private long aF = System.currentTimeMillis();
    private long aG = System.currentTimeMillis();
    private long aH = System.currentTimeMillis();
    private int aI = 0;
    private DJIImageButton aJ;
    private ViewTransitions aK;
    private long aL;
    private EditRecoverInfo aM = null;
    private long aN;
    private boolean aO = false;
    private String aP;
    private Context aQ;
    private LinearLayout aR;
    private TextView aS;
    private int[] aT = new int[]{R.string.v2_bigfilm_reunion_tip, R.string.v2_bigfilm_bless_tip, R.string.v2_bigfilm_trip_tip, R.string.v2_bigfilm_music_tip, R.string.v2_bigfilm_change_tip, R.string.v2_bigfilm_happyness_tip, R.string.v2_bigfilm_love_tip};
    private int[] aU = new int[]{R.string.v2_bigfilm_reunion_content, R.string.v2_bigfilm_bless_content, R.string.v2_bigfilm_trip_content, R.string.v2_bigfilm_music_content, R.string.v2_bigfilm_change_content, R.string.v2_bigfilm_happyness_content, R.string.v2_bigfilm_love_content};
    private Handler aV = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ DJIMultiMomentEditActivity a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    private Runnable aW = new Runnable(this) {
        final /* synthetic */ DJIMultiMomentEditActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.w();
        }
    };
    private int[] aX = new int[]{R.drawable.v2_trans_cross_selector, R.drawable.v2_trans_small_selector, R.drawable.v2_trans_smallwhiter_selector, R.drawable.v2_trans_turnblack_selector};
    private HorizontalListView aa;
    private dji.pilot2.multimoment.adapter.a ab;
    private BitFilmAdapter ac;
    private dji.pilot2.multimoment.a.b ad = null;
    private HorizonalSegmentView ae = null;
    private HorizonalTemplateListView af;
    private e ag;
    private DJITextView ah;
    private DJITextView ai;
    private DJIRelativeLayout aj;
    private DJIRelativeLayout ak;
    private DJITextView al;
    private DJITextView am;
    private RelativeLayout an;
    private DJIImageView ao;
    private FragmentManager ap;
    private dji.pilot2.widget.a aq;
    private String ar;
    private boolean as = true;
    private boolean at = false;
    private boolean au = false;
    private boolean av = false;
    private Boolean aw = Boolean.valueOf(false);
    private boolean ax = false;
    private DJITextView ay;
    private DJIRelativeLayout az;

    class a extends AsyncTask<String[], Void, String> {
        final /* synthetic */ DJIMultiMomentEditActivity a;

        a(DJIMultiMomentEditActivity dJIMultiMomentEditActivity) {
            this.a = dJIMultiMomentEditActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[][]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        protected String a(String[]... strArr) {
            if (strArr[0] != null) {
                String[] strArr2 = strArr[0];
                if (strArr2 != null) {
                    this.a.a(strArr2[0]);
                }
            }
            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void a(String str) {
            super.onPostExecute(str);
        }
    }

    public enum b {
        OPTION_DEL,
        OPTION_ADD,
        OPTION_EXCHANGE
    }

    protected void onCreate(Bundle bundle) {
        String[] strArr;
        int[] iArr;
        DJILogHelper.getInstance().LOGI("bob", "DJIMultiEdit create");
        long currentTimeMillis = System.currentTimeMillis();
        this.aN = currentTimeMillis;
        this.Z = Executors.newSingleThreadScheduledExecutor();
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_multimoment_editor);
        a();
        this.Y = 0;
        this.aQ = this;
        DJILogHelper.getInstance().LOGI("bob", "after initMembers " + (System.currentTimeMillis() - currentTimeMillis));
        Intent intent = getIntent();
        this.aM = (EditRecoverInfo) intent.getSerializableExtra(X);
        this.aQ = this;
        String[] fileNames;
        int[] fileDurations;
        if (this.aM != null) {
            fileNames = this.aM.getFileNames();
            fileDurations = this.aM.getFileDurations();
            if (this.aM.isFromDraft()) {
                strArr = fileNames;
                iArr = fileDurations;
            } else {
                this.aO = true;
                strArr = fileNames;
                iArr = fileDurations;
            }
        } else {
            fileNames = intent.getStringArrayExtra(M);
            fileDurations = intent.getIntArrayExtra("duration");
            strArr = fileNames;
            iArr = fileDurations;
        }
        if (strArr.length == 1) {
        }
        if (this.aw.booleanValue()) {
            this.ag = new d(this, dji.pilot2.multimoment.template.c.getInstance().a(this));
            this.ag.a(this.Y);
        } else {
            this.ag = new dji.pilot2.multimoment.adapter.c(this, TemplateController.getInstance().getTemplates(this));
            this.Y = 1;
            this.ag.a(this.Y);
            this.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
            dji.pilot2.template.a aVar = (dji.pilot2.template.a) this.ag.b();
            if (aVar.f().booleanValue()) {
                aVar.a(Boolean.valueOf(false));
            }
        }
        this.af.setAdapter(this.ag);
        this.ac = new BitFilmAdapter(this);
        this.aa.setAdapter(this.ac);
        DJILogHelper.getInstance().LOGI("bob", "after adapter create " + (System.currentTimeMillis() - currentTimeMillis));
        this.W = new c(strArr, iArr, this.Y, (Context) this);
        this.Z.scheduleAtFixedRate(this.aW, 0, 5, TimeUnit.SECONDS);
        if (this.aM != null) {
            y();
        }
        DJILogHelper.getInstance().LOGI("bob", "after mMomentEditController create " + (System.currentTimeMillis() - currentTimeMillis));
        b();
        DJILogHelper.getInstance().LOGI("bob", "after initListeners  " + (System.currentTimeMillis() - currentTimeMillis));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ca5);
        this.ae.setIsSingleTemplate(this.aw);
        this.ae.init(linearLayout, this.W);
        this.ae.setDragDeleteView(this.an);
        DJILogHelper.getInstance().LOGI("bob", "after mHorizonalSegmentView init  " + (System.currentTimeMillis() - currentTimeMillis));
        l();
        p();
        DJILogHelper.getInstance().LOGI("bob", "after onCreate  " + (System.currentTimeMillis() - currentTimeMillis));
        dji.thirdparty.a.c.a().a(this);
    }

    private void p() {
        new a(this).execute(new String[][]{this.W.g()});
    }

    protected void a() {
        this.ae = (HorizonalSegmentView) findViewById(R.id.ca4);
        this.af = (HorizonalTemplateListView) findViewById(R.id.caj);
        this.aa = (HorizontalListView) findViewById(R.id.cak);
        this.ah = (DJITextView) findViewById(R.id.ca8);
        this.ai = (DJITextView) findViewById(R.id.caa);
        this.aj = (DJIRelativeLayout) findViewById(R.id.ca9);
        this.ak = (DJIRelativeLayout) findViewById(R.id.cab);
        this.aK = (ViewTransitions) findViewById(R.id.cb0);
        this.aR = (LinearLayout) findViewById(R.id.cal);
        this.aR.setVisibility(8);
        this.aS = (TextView) findViewById(R.id.cam);
        for (int drawable : this.aX) {
            View imageView = new ImageView(this);
            imageView.setImageDrawable(getResources().getDrawable(drawable));
            this.aK.addView(imageView);
        }
        this.al = (DJITextView) findViewById(R.id.c_v);
        this.am = (DJITextView) findViewById(R.id.c_x);
        this.an = (RelativeLayout) findViewById(R.id.ca0);
        this.an.setVisibility(4);
        this.ay = (DJITextView) findViewById(R.id.caz);
        this.az = (DJIRelativeLayout) findViewById(R.id.cay);
        this.ao = (DJIImageView) findViewById(R.id.cag);
        this.aD = (DJIRelativeLayout) findViewById(R.id.c7w);
        this.aJ = (DJIImageButton) findViewById(R.id.caf);
        findViewById(R.id.ca_).setVisibility(8);
    }

    public void a(int i) {
        if (this.ad != null) {
            this.ad.d(i);
        }
    }

    protected void b() {
        long currentTimeMillis = System.currentTimeMillis();
        this.al.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        if (dji.midware.media.d.a() < 18) {
            this.am.setEnabled(false);
            Toast.makeText(this, getString(R.string.videoeditor_unsupported), 1).show();
        }
        DJILogHelper.getInstance().LOGI("bob", "after getSDKVersion   " + (System.currentTimeMillis() - currentTimeMillis));
        this.am.setEnabled(false);
        this.am.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.W.s() > c.b) {
                    this.a.d(0);
                    return;
                }
                dji.midware.media.e.d("MultiMoment", "click concat");
                if (((int) (System.currentTimeMillis() - this.a.aF)) < 1000) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.a.ad.b(true);
                this.a.az.setVisibility(0);
                this.a.ay.setText(String.valueOf(0) + "%");
                this.a.a(this.a.az);
                this.a.t();
                this.a.k();
            }
        });
        this.ah.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.aV.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass23 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.as = true;
                        this.a.a.ao.show();
                        this.a.a.aR.setVisibility(8);
                        this.a.a.af.setVisibility(0);
                        this.a.a.aa.setVisibility(4);
                        this.a.a.ah.setTextColor(this.a.a.getResources().getColor(R.color.lo));
                        this.a.a.ai.setTextColor(this.a.a.getResources().getColor(R.color.lk));
                        this.a.a.aj.setVisibility(0);
                        this.a.a.ak.setVisibility(4);
                    }
                }, 20);
            }
        });
        this.ai.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                    this.a.aR.setVisibility(0);
                } else {
                    this.a.aR.setVisibility(8);
                }
                this.a.aV.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass24 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.as = false;
                        this.a.a.ao.go();
                        this.a.a.af.setVisibility(4);
                        this.a.a.aa.setVisibility(0);
                        this.a.a.ah.setTextColor(this.a.a.getResources().getColor(R.color.lk));
                        this.a.a.ai.setTextColor(this.a.a.getResources().getColor(R.color.lo));
                        this.a.a.aj.setVisibility(4);
                        this.a.a.ak.setVisibility(0);
                    }
                }, 20);
            }
        });
        if (this.ag instanceof dji.pilot2.multimoment.adapter.c) {
            ((dji.pilot2.multimoment.adapter.c) this.ag).a(new dji.pilot2.multimoment.adapter.c.c(this) {
                final /* synthetic */ DJIMultiMomentEditActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    DJILogHelper.getInstance().LOGD("bob", "onMoreMusicClicked");
                    if (SystemClock.elapsedRealtime() - this.a.aL > 1500) {
                        this.a.aL = SystemClock.elapsedRealtime();
                        this.a.startActivityForResult(new Intent(this.a, DJINativeAudioActivity.class), 3);
                    }
                }

                public void a(int i) {
                    DJILogHelper.getInstance().LOGD("bob", "onLocalMusicClicked");
                    int a = this.a.W.c().a();
                    if (a == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent.a()) {
                        this.a.ao.setSelected(false);
                        this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                        this.a.b((int) R.string.v2_multimoment_origial_intelligent_off);
                        this.a.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                    }
                    this.a.Y = i;
                    this.a.W.c(i);
                    this.a.ar = this.a.W.w();
                    if (a != this.a.W.c().a()) {
                        this.a.ae.initInnerView();
                    }
                    this.a.h();
                    this.a.ad.b(-1);
                    this.a.aF = System.currentTimeMillis();
                    this.a.ao.setVisibility(4);
                }

                public void b(int i) {
                    int i2 = this.a.Y;
                    int b = ((dji.pilot2.multimoment.adapter.c) this.a.ag).b(i);
                    if (i2 != b) {
                        this.a.Y = b;
                        if (b < i2) {
                            this.a.af.scrollTo(0);
                        } else {
                            this.a.af.scrollToIndex(b);
                        }
                        this.a.W.e(this.a.Y);
                    }
                    DJILogHelper.getInstance().LOGD("bob", "onCollectClicked mCurSelectTemplate=" + this.a.Y + " old=" + i2 + " index =" + i);
                }
            });
        }
        this.af.setListener(new dji.pilot2.multimoment.view.HorizonalTemplateListView.a(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void a(View view) {
                DJILogHelper.getInstance().LOGI("bob", "wb OnClickItemUpListener", false, true);
                dji.pilot2.multimoment.adapter.c.a aVar = (dji.pilot2.multimoment.adapter.c.a) view.getTag();
                if (!(aVar == null || aVar.k == 0)) {
                    if (this.a.ao.isSelected()) {
                        this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent);
                    } else {
                        this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                    }
                    this.a.W.j();
                    this.a.ae.initInnerView();
                    this.a.ac.setCurIndex(-1);
                }
                this.a.ag.a(view);
            }
        });
        this.ag.a(new dji.pilot2.multimoment.adapter.e.a(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (!this.a.ax) {
                    if (this.a.W.c() != dji.pilot2.multimoment.videolib.b.SingleEdit) {
                        this.a.ao.setVisibility(0);
                    }
                    this.a.Y = i;
                    this.a.W.c(i);
                    this.a.ar = this.a.W.w();
                    if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent || this.a.W.c() == dji.pilot2.multimoment.videolib.b.SingleEdit) {
                        this.a.ae.initInnerView();
                    }
                    this.a.h();
                    if (this.a.aw.booleanValue()) {
                        this.a.ad.b(i);
                    } else {
                        this.a.ad.b(-1);
                    }
                    this.a.aF = System.currentTimeMillis();
                }
            }
        });
        this.ae.setOnItemClickCallBack(new HorizonalSegmentView.d(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                int r = this.a.W.r();
                if (r > i) {
                    int i2;
                    dji.pilot2.template.d dVar;
                    if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                        List a = dji.pilot2.multimoment.template.c.getInstance().a(this.a);
                        for (i2 = 0; i2 < a.size(); i2++) {
                            if (((dji.pilot2.template.c) a.get(i2)).d() == this.a.o().getPosToID(this.a.W.e())) {
                                Log.i("zhang", "temp ID:" + ((dji.pilot2.template.c) a.get(i2)).d());
                                dVar = (dji.pilot2.template.d) a.get(i2);
                                break;
                            }
                        }
                        dVar = null;
                        if (dVar == null) {
                            return;
                        }
                    }
                    dVar = this.a.ag.b();
                    int size = dVar.size();
                    i2 = c.b - this.a.W.s();
                    int a2 = this.a.W.c().a();
                    if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal) {
                        size = 30;
                    }
                    Intent intent = new Intent(this.a, DJIMultiMomentAddActivity.class);
                    intent.putExtra(DJIMultiMomentAddActivity.K, r);
                    intent.putExtra(DJIMultiMomentAddActivity.L, size);
                    intent.putExtra("duration", i2);
                    intent.putExtra("mode", a2);
                    this.a.startActivityForResult(intent, 2);
                }
            }

            public void b(int i) {
                int i2 = 0;
                if (this.a.W.r() > i) {
                    dji.pilot2.template.d dVar;
                    String str = (String) this.a.W.h().get(i);
                    if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                        List a = dji.pilot2.multimoment.template.c.getInstance().a(this.a);
                        for (int i3 = 0; i3 < a.size(); i3++) {
                            if (((dji.pilot2.template.c) a.get(i3)).d() == this.a.o().getPosToID(this.a.W.e())) {
                                dVar = (dji.pilot2.template.d) a.get(i3);
                                break;
                            }
                        }
                        dVar = null;
                        if (dVar == null) {
                            return;
                        }
                    }
                    dVar = this.a.ag.b();
                    Intent intent = new Intent(this.a, DJIMultiMomentFineActivity.class);
                    intent.putExtra("filename", str);
                    intent.putExtra(DJIMultiMomentFineActivity.M, i);
                    int i4 = this.a.W.i(i);
                    int j = this.a.W.j(i);
                    int h = this.a.W.h(i);
                    this.a.W.f(i);
                    if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent || this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                        intent.putExtra(DJIMultiMomentFineActivity.L, dVar.getDurationAtOrder(i));
                    } else {
                        intent.putExtra(DJIMultiMomentFineActivity.aa, Math.min(h, 300000));
                        intent.putExtra(DJIMultiMomentFineActivity.L, Math.min(j - i4, 300000));
                    }
                    if (this.a.aw.booleanValue()) {
                        intent.putExtra(DJIMultiMomentFineActivity.L, j - i4);
                        String str2 = DJIMultiMomentFineActivity.aa;
                        if (h >= dVar.getDurationAtOrder(i)) {
                            h = dVar.getDurationAtOrder(i);
                        }
                        intent.putExtra(str2, h);
                    }
                    intent.putExtra("starttime", i4);
                    intent.putExtra("endtime", j);
                    intent.putExtra(DJIMultiMomentFineActivity.R, this.a.W.n(i));
                    intent.putExtra(DJIMultiMomentFineActivity.P, this.a.W.l(i));
                    intent.putExtra(DJIMultiMomentFineActivity.Q, this.a.W.m(i));
                    intent.putExtra(DJIMultiMomentFineActivity.U, this.a.W.k(i));
                    intent.putExtra(DJIMultiMomentFineActivity.V, this.a.W.o(i));
                    intent.putExtra(DJIMultiMomentFineActivity.W, this.a.W.p(i));
                    intent.putExtra(DJIMultiMomentFineActivity.X, this.a.W.q(i));
                    if (this.a.aw.booleanValue()) {
                        i2 = 1;
                    } else if (!(this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent || this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp)) {
                        if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal) {
                            i2 = 2;
                        } else {
                            DJILogHelper.getInstance().LOGE(this.a.TAG, "mode err when click to fine cut");
                        }
                    }
                    intent.putExtra(DJIMultiMomentFineActivity.T, i2);
                    if (this.a.U) {
                        intent.putExtra(DJIMultiMomentFineActivity.Y, true);
                    }
                    if (this.a.V) {
                        intent.putExtra(DJIMultiMomentFineActivity.Z, true);
                    }
                    this.a.startActivityForResult(intent, 1);
                }
            }

            public void a() {
            }

            public void a(Boolean bool, int i) {
                int r = this.a.W.r();
                DJILogHelper.getInstance().LOGI("bob", "onCreatingDragEndCallBack" + (bool.booleanValue() ? "deleted=true" : "deleted=false") + "index=" + i + "nCurSegsNum= " + r);
                if (i == 1 && bool.booleanValue()) {
                    if (r == 0) {
                        this.a.finish();
                        return;
                    }
                    this.a.p();
                }
                if (bool.booleanValue()) {
                    dji.pilot.fpv.d.e.b(k.q);
                } else {
                    dji.pilot.fpv.d.e.b(k.r);
                }
                if (!(this.a.ad == null || this.a.aw.booleanValue() || !bool.booleanValue())) {
                    this.a.h();
                    this.a.aF = System.currentTimeMillis();
                }
                if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                    this.a.aS.setText(this.a.W.a() + dji.pilot.usercenter.protocol.d.t + this.a.W.b());
                    this.a.ad.b(this.a.ac.getPosToID(this.a.W.e()));
                }
            }

            public void a(int i, int i2) {
                DJILogHelper.getInstance().LOGI("bob", "onDragSegTobegin= " + i + "end= " + i2);
                if (i == 0 || i2 == 0) {
                    this.a.p();
                }
                DJILogHelper.getInstance().LOGI("bob", "onDragSegTo end");
            }

            public void a(int[] iArr) {
                Log.i("zhang", "onFilterChanged");
                this.a.aK.setLocation(iArr);
            }
        });
        DJILogHelper.getInstance().LOGI("bob", "before   mHorizonalSegmentView.setScrollListener " + (System.currentTimeMillis() - currentTimeMillis));
        this.ae.setScrollListener(new h(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3, int i4) {
                if (this.a.ad != null && !this.a.ad.d()) {
                    if (this.a.ad.f()) {
                        DJILogHelper.getInstance().LOGI("bob", "onScrollChangedListener isStoped == true");
                    } else if (!this.a.ax) {
                        this.a.c(i);
                    }
                }
            }

            public void a(int i) {
                if (!this.a.ax && this.a.ad != null && !this.a.ad.e()) {
                    this.a.ad.g();
                }
            }

            public void b(int i) {
                if (this.a.ad != null && !this.a.ax) {
                    if (!this.a.ad.e()) {
                        this.a.ad.g();
                    }
                    this.a.c(i);
                }
            }

            public void c(int i) {
                if (!this.a.ax && this.a.ad != null && !this.a.ad.e()) {
                    this.a.ad.g();
                }
            }
        });
        this.aa.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.ac.isLoaded(i)) {
                    this.a.aR.setVisibility(0);
                    this.a.W.e(i);
                    this.a.W.i();
                    this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_tmp);
                    this.a.aS.setText(this.a.W.a() + dji.pilot.usercenter.protocol.d.t + this.a.W.b());
                    this.a.ae.initInnerView();
                    this.a.Y = i;
                    this.a.ar = this.a.W.w();
                    this.a.h();
                    this.a.ac.setCurIndex(i);
                    this.a.ag.a(0);
                    return;
                }
                MultiBigFilmModel tempInfoByPosition = this.a.ac.getTempInfoByPosition(i);
                if (tempInfoByPosition != null) {
                    Intent intent = new Intent();
                    intent.setClass(this.a, NetworkAudioPreviewActivity.class);
                    intent.putExtra(NetworkAudioPreviewActivity.b, tempInfoByPosition.title);
                    intent.putExtra(NetworkAudioPreviewActivity.c, tempInfoByPosition.subtitle);
                    intent.putExtra(NetworkAudioPreviewActivity.a, tempInfoByPosition.template_id);
                    intent.putExtra(NetworkAudioPreviewActivity.y, true);
                    intent.putExtra(NetworkAudioPreviewActivity.t, tempInfoByPosition.duration);
                    intent.putExtra(NetworkAudioPreviewActivity.u, tempInfoByPosition.personModel.name);
                    intent.putExtra(NetworkAudioPreviewActivity.v, tempInfoByPosition.personModel.avatar);
                    if ("zh".equals(Locale.getDefault().getLanguage())) {
                        intent.putExtra(NetworkAudioPreviewActivity.w, tempInfoByPosition.linkModel.videoLink.cn);
                        intent.putExtra(NetworkAudioPreviewActivity.x, tempInfoByPosition.linkModel.zipLink.cn);
                        intent.putExtra(NetworkAudioPreviewActivity.z, "cn");
                    } else {
                        intent.putExtra(NetworkAudioPreviewActivity.w, tempInfoByPosition.linkModel.videoLink.en);
                        intent.putExtra(NetworkAudioPreviewActivity.x, tempInfoByPosition.linkModel.zipLink.en);
                        intent.putExtra(NetworkAudioPreviewActivity.z, "en");
                    }
                    this.a.startActivity(intent);
                    return;
                }
                this.a.b((int) R.string.v2_net_error);
            }
        });
        this.ao.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis() - this.a.aG;
                DJILogHelper.getInstance().LOGE("bob", "mIntelligentDownTime d=" + currentTimeMillis);
                if (currentTimeMillis <= 1500) {
                    DJILogHelper.getInstance().LOGE("bob", "mIntelligentDownTime return");
                    return;
                }
                this.a.aG = System.currentTimeMillis();
                int i = this.a.Y;
                if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
                    this.a.ao.setSelected(false);
                    i = this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                    this.a.b((int) R.string.v2_multimoment_origial_intelligent_off);
                    this.a.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                } else if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal) {
                    this.a.ao.setSelected(true);
                    i = this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent);
                    this.a.b((int) R.string.v2_multimoment_origial_intelligent_on);
                    this.a.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent);
                } else if (this.a.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                    this.a.W.j();
                    this.a.ao.setSelected(false);
                    this.a.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                    this.a.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                    this.a.Y = 1;
                    this.a.W.e(this.a.Y);
                    this.a.ag.a(this.a.Y);
                    this.a.ae.initInnerView();
                    this.a.ac.setCurIndex(-1);
                    this.a.ad.b(-1);
                    this.a.ar = this.a.W.w();
                    this.a.h();
                    return;
                }
                if (i != this.a.Y) {
                    this.a.Y = i;
                    this.a.af.clickPosition(this.a.Y);
                    return;
                }
                this.a.ae.initInnerView();
                this.a.h();
            }
        });
        this.aJ.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (System.currentTimeMillis() - this.a.aH > 1500) {
                    this.a.aH = System.currentTimeMillis();
                    DJIImageButton dJIImageButton = (DJIImageButton) view;
                    if (dJIImageButton.isSelected()) {
                        dJIImageButton.setSelected(false);
                        this.a.W.a(this.a.r());
                        this.a.ad.a(this.a.r());
                        this.a.ad.a(dji.pilot2.multimoment.a.b.k, this.a.r());
                        this.a.b((int) R.string.v2_multimoment_origial_sound_off);
                        return;
                    }
                    dJIImageButton.setSelected(true);
                    this.a.W.a(this.a.q());
                    this.a.ad.a(this.a.q());
                    this.a.ad.a(dji.pilot2.multimoment.a.b.k, this.a.q());
                    this.a.b((int) R.string.v2_multimoment_origial_sound_on);
                }
            }
        });
        DJILogHelper.getInstance().LOGI("bob", "initListeners end   " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private boolean a(double[] dArr) {
        int i = 0;
        while (i < dArr.length && dArr[i] == 0.0d) {
            i++;
        }
        if (i != dArr.length) {
            return false;
        }
        return true;
    }

    private boolean b(double[] dArr) {
        int i = 0;
        while (i < dArr.length && dArr[i] != 1.0d) {
            i++;
        }
        if (i != dArr.length) {
            return false;
        }
        return true;
    }

    private double[] q() {
        int r = this.W.r();
        double[] dArr = new double[r];
        int i = 0;
        while (i < r) {
            if (this.ad.e(i) && this.W.k(i) == 1.0d) {
                dArr[i] = 1.0d;
            } else {
                dArr[i] = 0.0d;
            }
            i++;
        }
        return dArr;
    }

    private double[] r() {
        int r = this.W.r();
        double[] dArr = new double[r];
        for (int i = 0; i < r; i++) {
            dArr[i] = 0.0d;
        }
        return dArr;
    }

    protected void f() {
        if (this.ad != null) {
            double[] dArr;
            double[] dArr2;
            String[] strArr;
            double[] k = this.W.k();
            String[] g = this.W.g();
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            this.W.a(arrayList, arrayList2);
            double[] l = this.W.l();
            double[] m = this.W.m();
            double[] n = this.W.n();
            int[] o = this.W.o();
            double[] p = this.W.p();
            double[] dArr3 = new double[arrayList.size()];
            double[] dArr4 = new double[arrayList2.size()];
            double[] q = this.W.q();
            for (int i = 0; i < dArr4.length; i++) {
                dArr4[i] = (double) ((Integer) arrayList2.get(i)).intValue();
                dArr3[i] = (double) ((Integer) arrayList.get(i)).intValue();
            }
            if (this.aw.booleanValue()) {
                if (l.length != arrayList2.size()) {
                    double[] dArr5 = new double[arrayList2.size()];
                    dArr = new double[arrayList2.size()];
                    double[] dArr6 = new double[arrayList2.size()];
                    double[] dArr7 = new double[arrayList2.size()];
                    String[] strArr2 = new String[arrayList.size()];
                    for (int i2 = 0; i2 < dArr5.length; i2++) {
                        dArr5[i2] = l[0];
                        dArr[i2] = n[0];
                        dArr6[i2] = m[0];
                        dArr7[i2] = q[0];
                        strArr2[i2] = g[0];
                    }
                    dArr2 = dArr7;
                    q = dArr;
                    n = dArr5;
                    dArr = dArr6;
                    strArr = strArr2;
                }
                dArr2 = q;
                dArr = m;
                strArr = g;
                q = n;
                n = l;
            } else {
                this.ad.a(dji.pilot2.multimoment.a.b.j, k);
                dArr2 = q;
                dArr = m;
                strArr = g;
                q = n;
                n = l;
            }
            this.ad.b(strArr, dArr3, dArr4, this.ar);
            this.ad.a(dji.pilot2.multimoment.a.b.g, n);
            this.ad.a(dji.pilot2.multimoment.a.b.h, dArr);
            this.ad.a(dji.pilot2.multimoment.a.b.i, q);
            this.ad.a(dji.pilot2.multimoment.a.b.k, dArr2);
            this.ad.a(dji.pilot2.multimoment.a.b.l, o, p);
            if (!this.aw.booleanValue()) {
                this.ad.a(dji.pilot2.multimoment.a.b.j, k);
            }
            imageCreator.setLogoInfo(getApplicationContext(), strArr, this.aA, p.b(), b(strArr[0]), this.W.z());
        }
    }

    public c g() {
        return this.W;
    }

    protected void h() {
        if (this.ad != null) {
            double[] dArr;
            double[] dArr2;
            String[] strArr;
            double[] k = this.W.k();
            String[] g = this.W.g();
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            this.W.a(arrayList, arrayList2);
            double[] l = this.W.l();
            double[] m = this.W.m();
            double[] n = this.W.n();
            int[] o = this.W.o();
            double[] p = this.W.p();
            double[] q = this.W.q();
            double[] dArr3 = new double[arrayList.size()];
            double[] dArr4 = new double[arrayList2.size()];
            for (int i = 0; i < dArr4.length; i++) {
                dArr4[i] = (double) ((Integer) arrayList2.get(i)).intValue();
                dArr3[i] = (double) ((Integer) arrayList.get(i)).intValue();
            }
            if (!this.aw.booleanValue() || l.length == arrayList.size()) {
                dArr = q;
                dArr2 = m;
                strArr = g;
                m = l;
            } else {
                double[] dArr5 = new double[arrayList.size()];
                double[] dArr6 = new double[arrayList.size()];
                dArr2 = new double[arrayList.size()];
                double[] dArr7 = new double[arrayList.size()];
                String[] strArr2 = new String[arrayList.size()];
                for (int i2 = 0; i2 < dArr5.length; i2++) {
                    dArr5[i2] = l[0];
                    dArr6[i2] = n[0];
                    dArr2[i2] = m[0];
                    strArr2[i2] = g[0];
                    dArr7[i2] = q[0];
                }
                dArr = dArr7;
                n = dArr6;
                m = dArr5;
                strArr = strArr2;
            }
            this.ad.a(dji.pilot2.multimoment.a.b.k, dArr);
            boolean z = false;
            if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                this.ad.a(strArr, dArr3, dArr4, k, this.ar, true);
                this.ad.b(this.ac.getPosToID(this.Y));
            } else {
                if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
                    z = true;
                }
                this.ad.a(strArr, dArr3, dArr4, k, this.ar, z);
            }
            this.ad.a(dji.pilot2.multimoment.a.b.g, m);
            this.ad.a(dji.pilot2.multimoment.a.b.h, dArr2);
            this.ad.a(dji.pilot2.multimoment.a.b.i, n);
            this.ad.a(dji.pilot2.multimoment.a.b.l, o, p);
            if (!this.aw.booleanValue()) {
                this.ad.a(dji.pilot2.multimoment.a.b.j, k);
            }
            imageCreator.setLogoInfo(getApplicationContext(), strArr, this.aA, p.b(), b(strArr[0]), this.W.z());
        }
    }

    protected void i() {
        if (this.ad != null) {
            double[] dArr;
            double[] dArr2;
            String[] strArr;
            double[] k = this.W.k();
            String[] g = this.W.g();
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            this.W.a(arrayList, arrayList2);
            double[] l = this.W.l();
            double[] m = this.W.m();
            double[] n = this.W.n();
            int[] o = this.W.o();
            double[] p = this.W.p();
            double[] q = this.W.q();
            double[] dArr3 = new double[arrayList.size()];
            double[] dArr4 = new double[arrayList2.size()];
            for (int i = 0; i < dArr4.length; i++) {
                dArr4[i] = (double) ((Integer) arrayList2.get(i)).intValue();
                dArr3[i] = (double) ((Integer) arrayList.get(i)).intValue();
            }
            if (!this.aw.booleanValue() || l.length == arrayList.size()) {
                dArr = q;
                dArr2 = m;
                strArr = g;
                m = l;
            } else {
                double[] dArr5 = new double[arrayList.size()];
                double[] dArr6 = new double[arrayList.size()];
                dArr2 = new double[arrayList.size()];
                double[] dArr7 = new double[arrayList.size()];
                String[] strArr2 = new String[arrayList.size()];
                for (int i2 = 0; i2 < dArr5.length; i2++) {
                    dArr5[i2] = l[0];
                    dArr6[i2] = n[0];
                    dArr2[i2] = m[0];
                    dArr7[i2] = q[0];
                    strArr2[i2] = g[0];
                }
                dArr = dArr7;
                n = dArr6;
                m = dArr5;
                strArr = strArr2;
            }
            this.ad.a(dji.pilot2.multimoment.a.b.k, dArr);
            boolean z = false;
            if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
                z = true;
            }
            this.ad.b(strArr, dArr3, dArr4, k, this.ar, z);
            this.ad.a(dji.pilot2.multimoment.a.b.g, m);
            this.ad.a(dji.pilot2.multimoment.a.b.h, dArr2);
            this.ad.a(dji.pilot2.multimoment.a.b.i, n);
            this.ad.a(dji.pilot2.multimoment.a.b.l, o, p);
            if (!this.aw.booleanValue()) {
                this.ad.a(dji.pilot2.multimoment.a.b.j, k);
            }
            imageCreator.setLogoInfo(getApplicationContext(), strArr, this.aA, p.b(), b(strArr[0]), this.W.z());
        }
    }

    protected List<Integer> j() {
        if (this.W != null) {
            return this.W.u();
        }
        DJILogHelper.getInstance().LOGI(this.TAG, "mMomentEditController==null");
        return null;
    }

    private void b(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.v2_multimoment_sound_switch_toast_layout, null);
        ((TextView) inflate.findViewById(R.id.cu0)).setText(getString(i));
        Toast toast = new Toast(this);
        toast.setGravity(48, 0, getResources().getDimensionPixelSize(R.dimen.b_));
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }

    protected void k() {
        HashMap hashMap = new HashMap();
        if (this.aw.booleanValue()) {
            hashMap.put(dji.pilot.fpv.d.d.dH, ((dji.pilot2.template.c) dji.pilot2.multimoment.template.c.getInstance().a(this).get(this.Y)).getTemplateName());
            dji.pilot.fpv.d.e.a(k.bz_, hashMap);
            return;
        }
        hashMap.put(dji.pilot.fpv.d.d.dH, ((dji.pilot2.template.a) TemplateController.getInstance().getTemplates(this).get(this.Y)).getTemplateName());
        dji.pilot.fpv.d.e.a(k.cB_, hashMap);
        HashMap hashMap2 = new HashMap();
        String[] g = this.W.g();
        hashMap2.put(dji.pilot.fpv.d.d.dF, "" + g.length);
        dji.pilot.fpv.d.e.a(k.bA_, hashMap2);
        int length = g.length;
        float s = (float) this.W.s();
        hashMap = new HashMap();
        hashMap.put(dji.pilot.fpv.d.d.dG, "" + ((int) Math.rint((double) s)));
        dji.pilot.fpv.d.e.a(k.h, hashMap);
        dji.pilot.fpv.d.e.b(k.bM_);
        if (!this.aJ.isSelected()) {
            dji.pilot.fpv.d.e.b("v2_video_edit_mute_audio");
        }
    }

    private void s() {
        if (VERSION.SDK_INT >= 17) {
            HashMap hashMap = new HashMap();
            dji.pilot.fpv.d.e.d(this).getStringArray(R.array.em);
            dji.pilot.fpv.d.e.a(k.C, hashMap);
        }
    }

    protected void l() {
        this.ap = getFragmentManager();
        this.W.r();
        this.ad = (dji.pilot2.multimoment.a.b) this.ap.findFragmentByTag(K);
        if (this.ad == null) {
            String[] g = this.W.g();
            this.ar = this.W.w();
            if (this.ar == null) {
                finish();
                return;
            }
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            this.W.a(arrayList, arrayList2);
            double[] dArr = new double[arrayList.size()];
            double[] dArr2 = new double[arrayList2.size()];
            for (int i = 0; i < dArr2.length; i++) {
                dArr2[i] = (double) ((Integer) arrayList2.get(i)).intValue();
                dArr[i] = (double) ((Integer) arrayList.get(i)).intValue();
            }
            this.ad = dji.pilot2.multimoment.a.b.a(g, dArr, dArr2, this.ar);
            this.ap.beginTransaction().add(R.id.c_y, this.ad, K).commit();
            imageCreator.setLogoInfo(getApplicationContext(), g, this.aA, p.b(), b(g[0]), this.W.z());
            if (this.aM != null) {
                f();
                if (!this.aM.isFromDraft()) {
                    Toast.makeText(this, getString(R.string.v2_multimoment_exception_recover_tip), 1).show();
                }
                this.aM = null;
            }
        }
        if (this.ad != null) {
            this.ad.a(new dji.pilot2.multimoment.a.b.a(this) {
                final /* synthetic */ DJIMultiMomentEditActivity a;

                {
                    this.a = r1;
                }

                public void a(long j) {
                    List j2 = this.a.j();
                    int r = this.a.W.r();
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < j2.size() && i2 < r) {
                        i3 += ((Integer) j2.get(i2)).intValue();
                        if (j < ((long) i3)) {
                            break;
                        }
                        i2++;
                        i = (int) (this.a.ae.getSegTotalWidth(((Integer) j2.get(i2)).intValue()) + ((long) i));
                    }
                    int i4;
                    if (j > ((long) i3)) {
                        i4 = i3 + 2000;
                        long segViewWidth = this.a.ae.getSegViewWidth(2000);
                        if (j <= ((long) i4)) {
                            this.a.ae.smoothScrollTo((int) (((segViewWidth * ((long) (((int) j) - (i4 - 2000)))) / ((long) 2000)) + ((long) i)), 0);
                            return;
                        }
                        i = (int) (((long) i) + segViewWidth);
                        this.a.ae.smoothScrollTo((int) (((segViewWidth * ((long) (((int) j) - i4))) / ((long) 2000)) + ((long) i)), 0);
                    } else if (i2 >= j2.size()) {
                        this.a.ae.smoothScrollTo(i, 0);
                    } else {
                        i4 = ((Integer) j2.get(i2)).intValue();
                        this.a.ae.smoothScrollTo((int) (((this.a.ae.getSegTotalWidth(i4) * ((long) (((int) j) - (i3 - i4)))) / ((long) i4)) + ((long) i)), 0);
                    }
                }

                public void a() {
                    Log.i("rxq", "player onStart(): " + System.currentTimeMillis());
                    this.a.v();
                }

                public void b() {
                }
            });
        }
    }

    private void c(int i) {
        long segViewWidth;
        List j = j();
        long j2 = 0;
        int r = this.W.r();
        DJILogHelper.getInstance().LOGI("bob", "seekPlayerFragmentTo fillingNum=" + r);
        int i2 = 0;
        int i3 = 0;
        while (i3 < r + 1) {
            long j3;
            if (r == i3) {
                j3 = 2000;
                segViewWidth = this.ae.getSegViewWidth(2000);
            } else {
                j3 = (long) ((Integer) j.get(i3)).intValue();
                segViewWidth = this.ae.getSegTotalWidth((int) j3);
            }
            i2 = (int) (((long) i2) + segViewWidth);
            if (i2 >= i) {
                segViewWidth = ((j3 * ((((long) i) + segViewWidth) - ((long) i2))) / segViewWidth) + j2;
                break;
            } else {
                j2 += j3;
                i3++;
            }
        }
        segViewWidth = j2;
        if (i3 >= r + 1) {
            DJILogHelper.getInstance().LOGI("bob", "i>=fillingNum");
        } else if (segViewWidth > 0) {
            this.ad.a(segViewWidth);
        } else {
            DJILogHelper.getInstance().LOGI("bob", "HorizonalSegmentView.onScrollChangedListener() onScrollChanged sum <=0");
        }
    }

    protected void onResume() {
        super.onResume();
        Log.i("rxq", "start onResume(): " + System.currentTimeMillis());
        if (this.as) {
            this.ah.setPressed(true);
            this.ai.setPressed(false);
        }
        this.aV.postDelayed(new Runnable(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.ax) {
                    if (this.a.aw.booleanValue()) {
                        this.a.ad.b(this.a.Y);
                    }
                    if (this.a.aw.booleanValue() && DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                        int count = this.a.ag.getCount();
                        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                        int i = displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                        count *= dji.pilot.fpv.model.b.a(this.a, R.dimen.bc) + (dji.pilot.fpv.model.b.a(this.a, R.dimen.bh) * 2);
                        int i2 = (i * 4) / 5;
                        if (count < i) {
                            LayoutParams layoutParams = this.a.af.getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new LayoutParams(count, -2);
                            } else {
                                layoutParams.width = count;
                            }
                            this.a.af.setLayoutParams(layoutParams);
                        }
                    }
                }
            }
        }, 100);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    protected void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.ae != null) {
            this.ae.destroy();
        }
        b(this.az);
        dji.thirdparty.a.c.a().d(this);
        this.Z.shutdown();
        x();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        switch (i) {
            case 1:
                if (intent != null) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        extras.getString("filename");
                        int i3 = extras.getInt(DJIMultiMomentFineActivity.M);
                        long j = extras.getLong("starttime");
                        long j2 = extras.getLong("endtime");
                        double d = extras.getDouble(DJIMultiMomentFineActivity.R);
                        double d2 = extras.getDouble(DJIMultiMomentFineActivity.Q);
                        double d3 = extras.getDouble(DJIMultiMomentFineActivity.P);
                        double d4 = extras.getDouble(DJIMultiMomentFineActivity.U);
                        double d5 = extras.getDouble(DJIMultiMomentFineActivity.V);
                        int i4 = extras.getInt(DJIMultiMomentFineActivity.W);
                        double d6 = extras.getDouble(DJIMultiMomentFineActivity.X);
                        this.U = extras.getBoolean(DJIMultiMomentFineActivity.Y);
                        this.V = extras.getBoolean(DJIMultiMomentFineActivity.Z);
                        if (d4 == 0.0d) {
                            d4 = 1.0d;
                        }
                        this.W.a(i3, (int) j, (int) j2, d4, d5);
                        if (this.V) {
                            this.W.a(d3, d, d2);
                        } else {
                            this.W.a(i3, d3, d, d2);
                        }
                        if (this.U) {
                            this.W.a(i4, d6);
                        } else {
                            this.W.a(i3, i4, d6);
                        }
                        this.ae.initInnerView();
                        f();
                        this.aF = System.currentTimeMillis();
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (intent != null) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        this.W.r();
                        String[] stringArray = extras.getStringArray(M);
                        if (stringArray != null) {
                            this.ae.addMoment(Arrays.asList(stringArray));
                            if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                                this.aS.setText(this.W.a() + dji.pilot.usercenter.protocol.d.t + this.W.b());
                            }
                            f();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 3:
                DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC");
                if (intent != null) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        this.W.j();
                        this.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                        String string = extras.getString(DJIAudioPlayActivity.M);
                        DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC  " + string);
                        dji.pilot2.template.a aVar = (dji.pilot2.template.a) this.ag.getItem(this.Y);
                        TemplateController.getInstance().isContainLocalMusic();
                        ((dji.pilot2.multimoment.adapter.c) this.ag).a(string, getApplicationContext());
                        DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC  getDescription " + aVar.getDescription());
                        if (aVar.getDescription().equals(TemplateController.LOCALMUSIC)) {
                            this.ar = this.W.w();
                            DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC  " + this.ar);
                        } else {
                            DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC  mCurSelectTemplate " + this.Y);
                            this.Y = 1;
                            this.W.e(this.Y);
                            this.ag.a(this.Y);
                            this.ar = this.W.w();
                        }
                        this.ao.setVisibility(4);
                        if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
                            this.ao.setSelected(false);
                            this.W.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                            this.ag.a(dji.pilot2.multimoment.videolib.b.MultiEdit_Normal);
                            this.ae.initInnerView();
                        }
                    }
                } else {
                    int addNewTemplates = TemplateController.getInstance().addNewTemplates();
                    if (addNewTemplates != 0) {
                        DJILogHelper.getInstance().LOGI("bob", "onActivityResult REQUEST_CODE_LOCALMUSIC nCnt=" + addNewTemplates);
                        Boolean isContainLocalMusic = TemplateController.getInstance().isContainLocalMusic();
                        int collectNum = TemplateController.getInstance().getCollectNum();
                        if (isContainLocalMusic.booleanValue()) {
                            this.Y = (addNewTemplates + 1) + collectNum;
                        } else {
                            this.Y = addNewTemplates + collectNum;
                        }
                        this.W.e(this.Y);
                        this.ag.a(this.Y);
                        this.ar = this.W.w();
                    }
                }
                i();
                return;
            case 4:
                if (i2 != 1) {
                    finish();
                    return;
                } else {
                    z();
                    return;
                }
            default:
                return;
        }
    }

    public void onClickIntercept(View view) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && !this.ax) {
            finish();
        }
        return false;
    }

    private void t() {
        new Thread(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.u();
            }
        }.start();
    }

    private void d(int i) {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        bVar.setMessage(getResources().getString(R.string.v2_multimoment_export_limit));
        bVar.setNeutralButton(R.string.v2_library_02, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    private void u() {
        this.ax = true;
        final String c = p.c();
        String str = n.g(this) + c + ".mp4";
        final String[] g = this.W.g();
        dji.g.b.e[] eVarArr = new dji.g.b.e[g.length];
        double[] l = this.W.l();
        double[] n = this.W.n();
        double[] m = this.W.m();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.W.a((List) arrayList, (List) arrayList2);
        double[] k = this.W.k();
        for (int i = 0; i < eVarArr.length; i++) {
            eVarArr[i] = new dji.g.b.e(g[i], dji.g.a.a.a.e, (long) ((Integer) arrayList.get(i)).intValue(), (long) ((Integer) arrayList2.get(i)).intValue(), (((this.aw.booleanValue() | 0) | (Math.abs(l[i] - 0.0d) > 0.01d ? 1 : 0)) | (Math.abs(n[i] - 1.0d) > 0.01d ? 1 : 0)) | (Math.abs(m[i] - 1.0d) > 0.01d ? 1 : 0), k[i]);
        }
        final String str2 = str;
        i anonymousClass11 = new i(this) {
            final /* synthetic */ DJIMultiMomentEditActivity d;

            public void a() {
                this.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.d.ay.setText(String.valueOf(0) + "%");
                    }
                });
            }

            public void b(int i) {
                HashMap hashMap;
                if (this.d.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || this.d.W.A() == null) {
                    hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dH, this.d.W.d().getTemplateName());
                    dji.pilot.fpv.d.e.a(c$n.a, hashMap);
                } else {
                    hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dH, this.d.W.A().getTemplateName());
                    dji.pilot.fpv.d.e.a(c$n.a, hashMap);
                }
                this.d.ad.a(false);
                if (i == 0) {
                    int e;
                    o.a("=====concat return ok!");
                    this.d.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.d.az.setVisibility(8);
                            this.a.d.b(this.a.d.az);
                            this.a.d.ax = false;
                            this.a.d.ad.h();
                        }
                    });
                    String str = n.f(this.d) + c + ".mp4";
                    new File(str2).renameTo(new File(str));
                    this.d.aP = str;
                    g.a(this.d, str);
                    this.d.ax = false;
                    JSONArray jSONArray = new JSONArray();
                    JSONArray jSONArray2 = new JSONArray();
                    for (String file : g) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            String name = new File(file).getName();
                            int lastIndexOf = name.lastIndexOf(46);
                            String str2 = "";
                            if (lastIndexOf > 0) {
                                name = name.substring(0, lastIndexOf);
                            }
                            f f = dji.midware.media.e.e.f(name);
                            if (f != null) {
                                if (!(f.p.doubleValue() == -100.0d && f.q.doubleValue() == -100.0d)) {
                                    jSONObject.put(dji.pilot.usercenter.mode.n.y, f.p);
                                    jSONObject.put(dji.pilot.usercenter.mode.n.x, f.q);
                                    jSONArray.put(jSONObject);
                                }
                                String a = dji.pilot2.share.f.b.a(this.d.getApplicationContext(), f.a());
                                int i2 = 0;
                                while (i2 < jSONArray2.length() && jSONArray2.getString(i2).compareTo(a) != 0) {
                                    i2++;
                                }
                                if (i2 == jSONArray2.length()) {
                                    jSONArray2.put(a);
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    dji.pilot2.share.mode.a aVar = new dji.pilot2.share.mode.a(str);
                    aVar.I = jSONArray;
                    aVar.J = jSONArray2;
                    aVar.a();
                    Intent intent = new Intent(this.d, DJIShareActivity.class);
                    intent.putExtra("file_path", str);
                    intent.putExtra(DJIShareActivity.L, 2);
                    if (this.d.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                        if ("zh".equals(Locale.getDefault().getLanguage())) {
                            e = this.d.W.e();
                        } else {
                            e = this.d.W.e() + 2;
                        }
                        intent.putExtra(DJIShareActivity.Q, this.d.getString(this.d.aT[e]));
                        intent.putExtra(DJIShareActivity.R, this.d.getString(this.d.aU[e]));
                    }
                    String y = this.d.W.y();
                    if (y != null) {
                        intent.putExtra(DJIShareActivity.O, y);
                    }
                    if (this.d.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || this.d.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_DP) {
                        y = "";
                        dji.pilot2.template.d A = this.d.W.A();
                        if (A != null) {
                            y = A.getTemplateName();
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put(k.J, y);
                            dji.pilot.fpv.d.e.a(k.I, hashMap2);
                        }
                    }
                    intent.putExtra(DJIShareActivity.S, new EditRecoverInfo(this.d.W.x()));
                    this.d.startActivityForResult(intent, 4);
                    return;
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass11 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        dji.midware.media.e.d(this.a.d.TAG, "before stop");
                        if (this.a.d.ad != null) {
                            this.a.d.ad.i();
                        }
                        dji.midware.media.e.d(this.a.d.TAG, "after stop");
                    }
                }.start();
                this.d.finish();
                this.d.ax = false;
                p.d(str2);
            }

            public void a(final int i) {
                this.d.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 b;

                    public void run() {
                        this.b.d.ay.setText(String.valueOf(i) + "%");
                    }
                });
            }

            public void c(int i) {
            }
        };
        DJILogHelper.getInstance().LOGI("bob", "startConcatHwThread enter save mMusic =" + this.ar + " outPath=" + str);
        this.ad.a().a(new dji.g.b.h(eVarArr, false, this.ar, dji.g.a.a.a.d, str, true, 1280, 720, null, anonymousClass11, this.ad.o, (long) (this.aw.booleanValue() ? 2000000 : 2000000)));
    }

    public Boolean m() {
        return this.aw;
    }

    private void e(int i) {
        WindowManager.LayoutParams attributes;
        int[] c;
        int[] iArr;
        int height;
        if (i == 3) {
            this.aq = new dji.pilot2.widget.a(this, R.style.hf, 3);
            attributes = this.aq.getWindow().getAttributes();
            c = p.c((Context) this);
            attributes.width = c[0];
            attributes.height = c[1];
            this.aq.getWindow().setAttributes(attributes);
            this.aq.a(0.0f);
            iArr = new int[2];
            this.aD.getLocationInWindow(iArr);
            height = this.aD.getHeight() / 2;
            this.aq.a(iArr[0] - (height / 2), iArr[1], height, getResources().getDimensionPixelSize(R.dimen.fj));
            this.aq.show();
        } else if (i == 6) {
            this.aq = new dji.pilot2.widget.a(this, R.style.hf, 6);
            attributes = this.aq.getWindow().getAttributes();
            c = p.c((Context) this);
            attributes.width = c[0];
            attributes.height = c[1];
            this.aq.getWindow().setAttributes(attributes);
            this.aq.a(0.0f);
            iArr = new int[2];
            this.ao.getLocationInWindow(iArr);
            height = this.ao.getHeight() / 2;
            this.aq.a(iArr[0], iArr[1], height, getResources().getDimensionPixelSize(R.dimen.fj));
            this.aq.a(new dji.pilot2.widget.a.a(this) {
                final /* synthetic */ DJIMultiMomentEditActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    DJILogHelper.getInstance().LOGI("wbc", "onOkClicked");
                    dji.pilot2.widget.a.b(this.a, 6);
                    this.a.ad.c();
                }
            });
            this.aq.show();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.aw.booleanValue()) {
            if (dji.pilot2.widget.a.a(this, 3)) {
                dji.pilot2.widget.a.b(this, 3);
                this.aV.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJIMultiMomentEditActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.e(3);
                    }
                }, 300);
            } else if (dji.pilot2.widget.a.a(this, 6)) {
                this.aV.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJIMultiMomentEditActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.e(6);
                    }
                }, 300);
            }
        }
        if (z) {
            this.aD.setX((float) (getResources().getDimensionPixelSize(R.dimen.b4) - (this.aD.getWidth() / 2)));
            com.c.a.a.getInstance().a(this, false, new com.c.a.b(this) {
                final /* synthetic */ DJIMultiMomentEditActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                }
            });
        }
    }

    private void a(String str) {
        double d;
        double d2;
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        String str2 = "";
        if (lastIndexOf > 0) {
            name = name.substring(0, lastIndexOf);
        }
        f f = dji.midware.media.e.e.f(name);
        if (f == null || (f.p.doubleValue() == -100.0d && f.q.doubleValue() == -100.0d)) {
            d = 0.0d;
            d2 = 0.0d;
        } else {
            d2 = f.q.doubleValue();
            d = f.p.doubleValue();
        }
        if (d2 != 0.0d && d != 0.0d) {
            try {
                DJIGeocoderResult.get(getApplicationContext(), d2, d, new com.dji.frame.b.c(this) {
                    final /* synthetic */ DJIMultiMomentEditActivity a;

                    {
                        this.a = r1;
                    }

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void a(java.lang.Object r11) {
                        /*
                        r10 = this;
                        r5 = 0;
                        r11 = (dji.pilot.fpv.model.DJIGeocoderResult) r11;
                        if (r11 == 0) goto L_0x01ac;
                    L_0x0005:
                        r0 = r11.status;
                        if (r0 == 0) goto L_0x01ac;
                    L_0x0009:
                        r0 = r11.status;
                        r1 = "OK";
                        r0 = r0.equals(r1);
                        if (r0 == 0) goto L_0x01ac;
                    L_0x0013:
                        r0 = dji.pilot.fpv.model.DJIGeocoderResult.getStreetAdress(r11);
                        if (r0 == 0) goto L_0x00f5;
                    L_0x0019:
                        r0 = r0.address_components;
                        r6 = r0.iterator();
                        r1 = r5;
                        r2 = r5;
                        r3 = r5;
                        r4 = r5;
                    L_0x0023:
                        r0 = r6.hasNext();
                        if (r0 == 0) goto L_0x00a1;
                    L_0x0029:
                        r0 = r6.next();
                        r0 = (dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel) r0;
                        r7 = r0.types;
                        r8 = "administrative_area_level_1";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x0042;
                    L_0x0039:
                        r0 = r1;
                        r1 = r2;
                        r2 = r3;
                        r3 = r4;
                    L_0x003d:
                        r4 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        goto L_0x0023;
                    L_0x0042:
                        r7 = r0.types;
                        r8 = "locality";
                        r7 = r7.contains(r8);
                        if (r7 != 0) goto L_0x0056;
                    L_0x004c:
                        r7 = r0.types;
                        r8 = "administrative_area_level_2";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x005e;
                    L_0x0056:
                        r0 = r0.long_name;
                        r3 = r4;
                        r9 = r2;
                        r2 = r0;
                        r0 = r1;
                        r1 = r9;
                        goto L_0x003d;
                    L_0x005e:
                        r7 = r0.types;
                        r8 = "route";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x0070;
                    L_0x0068:
                        r0 = r0.long_name;
                        r2 = r3;
                        r3 = r4;
                        r9 = r1;
                        r1 = r0;
                        r0 = r9;
                        goto L_0x003d;
                    L_0x0070:
                        r7 = r0.types;
                        r8 = "sublocality";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x007f;
                    L_0x007a:
                        r0 = r1;
                        r1 = r2;
                        r2 = r3;
                        r3 = r4;
                        goto L_0x003d;
                    L_0x007f:
                        r7 = r0.types;
                        r8 = "country";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x008f;
                    L_0x0089:
                        r0 = r0.long_name;
                        r1 = r2;
                        r2 = r3;
                        r3 = r4;
                        goto L_0x003d;
                    L_0x008f:
                        r7 = r0.types;
                        r8 = "sublocality_level_1";
                        r7 = r7.contains(r8);
                        if (r7 == 0) goto L_0x01c2;
                    L_0x0099:
                        r0 = r0.long_name;
                        r9 = r1;
                        r1 = r2;
                        r2 = r3;
                        r3 = r0;
                        r0 = r9;
                        goto L_0x003d;
                    L_0x00a1:
                        r0 = java.util.Locale.getDefault();
                        r6 = r0.getLanguage();
                        r0 = "";
                        r7 = "zh";
                        r6 = r6.contains(r7);
                        if (r6 == 0) goto L_0x0132;
                    L_0x00b3:
                        r5 = com.dji.frame.c.l.a(r3);
                        if (r5 != 0) goto L_0x012a;
                    L_0x00b9:
                        r0 = com.dji.frame.c.l.a(r4);
                        if (r0 != 0) goto L_0x00d0;
                    L_0x00bf:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r0 = r0.append(r3);
                        r0 = r0.append(r4);
                        r3 = r0.toString();
                    L_0x00d0:
                        r0 = com.dji.frame.c.l.a(r2);
                        if (r0 != 0) goto L_0x00e7;
                    L_0x00d6:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r0 = r0.append(r3);
                        r0 = r0.append(r2);
                        r3 = r0.toString();
                    L_0x00e7:
                        r1 = r3;
                    L_0x00e8:
                        r0 = r10.a;
                        r2 = r0.aB;
                        monitor-enter(r2);
                        r0 = r10.a;	 Catch:{ all -> 0x01a9 }
                        r0.aA = r1;	 Catch:{ all -> 0x01a9 }
                        monitor-exit(r2);	 Catch:{ all -> 0x01a9 }
                    L_0x00f5:
                        r0 = r10.a;
                        r0 = r0.W;
                        r4 = r0.g();
                        r0 = r10.a;
                        r0 = r0.getApplicationContext();
                        r1 = r10.a;
                        r1 = r1.W;
                        r1 = r1.g();
                        r2 = r10.a;
                        r2 = r2.aA;
                        r3 = dji.pilot2.utils.p.b();
                        r5 = r10.a;
                        r6 = 0;
                        r4 = r4[r6];
                        r4 = r5.b(r4);
                        r5 = r10.a;
                        r5 = r5.W;
                        r5 = r5.z();
                        dji.pilot2.multimoment.videolib.imageCreator.setLogoInfo(r0, r1, r2, r3, r4, r5);
                        return;
                    L_0x012a:
                        r2 = com.dji.frame.c.l.a(r1);
                        if (r2 == 0) goto L_0x00e8;
                    L_0x0130:
                        r1 = r0;
                        goto L_0x00e8;
                    L_0x0132:
                        r6 = com.dji.frame.c.l.a(r3);
                        if (r6 != 0) goto L_0x01a1;
                    L_0x0138:
                        r0 = com.dji.frame.c.l.a(r2);
                        if (r0 != 0) goto L_0x01bf;
                    L_0x013e:
                        r0 = com.dji.frame.c.l.a(r4);
                        if (r0 != 0) goto L_0x0159;
                    L_0x0144:
                        if (r2 != 0) goto L_0x0171;
                    L_0x0146:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r1 = "";
                        r0 = r0.append(r1);
                        r0 = r0.append(r4);
                        r2 = r0.toString();
                    L_0x0159:
                        if (r2 != 0) goto L_0x0189;
                    L_0x015b:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r1 = "";
                        r0 = r0.append(r1);
                        r0 = r0.append(r3);
                        r0 = r0.toString();
                    L_0x016e:
                        r1 = r0;
                        goto L_0x00e8;
                    L_0x0171:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r0 = r0.append(r2);
                        r1 = ", ";
                        r0 = r0.append(r1);
                        r0 = r0.append(r4);
                        r2 = r0.toString();
                        goto L_0x0159;
                    L_0x0189:
                        r0 = new java.lang.StringBuilder;
                        r0.<init>();
                        r0 = r0.append(r2);
                        r1 = ", ";
                        r0 = r0.append(r1);
                        r0 = r0.append(r3);
                        r0 = r0.toString();
                        goto L_0x016e;
                    L_0x01a1:
                        r2 = com.dji.frame.c.l.a(r1);
                        if (r2 != 0) goto L_0x0130;
                    L_0x01a7:
                        goto L_0x00e8;
                    L_0x01a9:
                        r0 = move-exception;
                        monitor-exit(r2);	 Catch:{ all -> 0x01a9 }
                        throw r0;
                    L_0x01ac:
                        r0 = r10.a;
                        r1 = r0.aB;
                        monitor-enter(r1);
                        r0 = r10.a;	 Catch:{ all -> 0x01bc }
                        r2 = 0;
                        r0.aA = r2;	 Catch:{ all -> 0x01bc }
                        monitor-exit(r1);	 Catch:{ all -> 0x01bc }
                        goto L_0x00f5;
                    L_0x01bc:
                        r0 = move-exception;
                        monitor-exit(r1);	 Catch:{ all -> 0x01bc }
                        throw r0;
                    L_0x01bf:
                        r2 = r5;
                        goto L_0x013e;
                    L_0x01c2:
                        r0 = r1;
                        r1 = r2;
                        r2 = r3;
                        r3 = r4;
                        goto L_0x003d;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity.17.a(java.lang.Object):void");
                    }
                });
            } catch (ExceptionInInitializerError e) {
                DJILogHelper.getInstance().LOGI("wwww", "not connected ExceptionInInitializerError ");
                synchronized (this.aB) {
                    this.aA = null;
                }
            }
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private String b(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(47);
        String str2 = "";
        if (lastIndexOf > 0 && lastIndexOf2 > 0) {
            str = str.substring(lastIndexOf2 + 1, lastIndexOf);
        }
        f f = dji.midware.media.e.e.f(str);
        if (f == null) {
            return null;
        }
        Date m = f.m();
        if (m.equals(new Date(0))) {
            return null;
        }
        return simpleDateFormat.format(m);
    }

    private void a(View view) {
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        Drawable background = ((ImageView) view.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).start();
        }
    }

    private void b(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.rt);
        view.setOnClickListener(null);
        Drawable background = imageView.getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).stop();
        }
    }

    public void n() {
        this.aV.post(new Runnable(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.am.setEnabled(true);
            }
        });
    }

    public void onEventMainThread(TemplateDownloadEvent templateDownloadEvent) {
        if (templateDownloadEvent.isDownloadSuccess) {
            Log.i("zhang", "onEventMainThread success!:" + dji.pilot2.multimoment.template.c.getInstance().a());
            this.ag.notifyDataSetChanged();
            this.ac = new BitFilmAdapter(this.aQ);
            this.aa.setAdapter(this.ac);
            if (this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
                this.ac.setCurIndex(this.Y);
            }
        }
    }

    private boolean v() {
        if (this.ad == null || !this.ad.j()) {
            this.aJ.setVisibility(8);
            if (!(this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || ((dji.pilot2.multimoment.adapter.c) this.ag).c().booleanValue())) {
                this.ao.setVisibility(0);
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "sound switch invisiable.");
        } else {
            this.aJ.setVisibility(0);
            if (!(this.W.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp || ((dji.pilot2.multimoment.adapter.c) this.ag).c().booleanValue())) {
                this.ao.setVisibility(0);
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "sound switch visiable.");
        }
        if (a(this.W.q())) {
            this.aJ.setSelected(false);
        } else {
            this.aJ.setSelected(true);
        }
        if (b(this.W.k())) {
            this.aJ.setClickable(false);
        } else {
            this.aJ.setClickable(true);
        }
        return true;
    }

    private void w() {
        try {
            OutputStream openFileOutput = openFileOutput(X, 0);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
            EditRecoverInfo editRecoverInfo = new EditRecoverInfo(this.W.x());
            if (this.aO) {
                editRecoverInfo.setRecovering(true);
            } else {
                editRecoverInfo.setRecovering(false);
            }
            editRecoverInfo.setHowLong(System.currentTimeMillis() - this.aN);
            objectOutputStream.writeObject(editRecoverInfo);
            objectOutputStream.close();
            openFileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private boolean x() {
        File file = new File(getFilesDir(), X);
        if (file.exists()) {
            file.delete();
            Log.i("rxq", "deleteContrllerInstance suc!");
            return true;
        }
        Log.i("rxq", "DJI_SAVE_INSTANCE_FILE not exists!");
        return false;
    }

    private void y() {
        if (this.aM != null) {
            int i;
            double[] fast = this.aM.getFast();
            String[] fileNames = this.aM.getFileNames();
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            this.aM.getNormalTimes(arrayList, arrayList2);
            double[] segBright = this.aM.getSegBright();
            double[] segSaturation = this.aM.getSegSaturation();
            double[] segContrast = this.aM.getSegContrast();
            int[] segFilterNum = this.aM.getSegFilterNum();
            double[] segFilterMuch = this.aM.getSegFilterMuch();
            int[] iArr = new int[arrayList.size()];
            int[] iArr2 = new int[arrayList2.size()];
            for (i = 0; i < iArr2.length; i++) {
                iArr2[i] = ((Integer) arrayList2.get(i)).intValue();
                iArr[i] = ((Integer) arrayList.get(i)).intValue();
            }
            double[] segVolume = this.aM.getSegVolume();
            int length = fileNames.length;
            for (i = 0; i < length; i++) {
                this.W.a(i, iArr[i], iArr2[i], fast[i], segVolume[i]);
                this.W.a(i, segBright[i], segContrast[i], segSaturation[i]);
                this.W.a(i, segFilterNum[i], segFilterMuch[i]);
            }
        }
    }

    private void z() {
        new Thread(this) {
            final /* synthetic */ DJIMultiMomentEditActivity a;

            {
                this.a = r1;
            }

            public void run() {
                File file = new File(this.a.aP);
                if (file.exists()) {
                    file.delete();
                }
            }
        }.start();
    }

    public BitFilmAdapter o() {
        return this.ac;
    }
}
