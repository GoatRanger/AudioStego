package dji.pilot2.nativeaudio;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dji.frame.c.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.nativeaudio.model.DownloadTemplateBean;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel.MultiLangugeNameModel;
import dji.pilot2.nativeaudio.model.TemplateDownloadEvent;
import dji.pilot2.nativeaudio.view.BufferSeekProgressBar;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

public class NetworkAudioPreviewActivity extends DJIActivityNoFullScreen implements OnBufferingUpdateListener, OnPreparedListener, Callback, OnClickListener, dji.pilot2.nativeaudio.b.c.b, dji.pilot2.nativeaudio.view.BufferSeekProgressBar.a {
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 4;
    public static final int E = 0;
    public static final int F = 1;
    private static final int G = 65670;
    public static final String a = "template_list_id";
    public static final String b = "audio_title";
    public static final String c = "audio_subtitle";
    public static final String d = "audio_description";
    public static final String t = "audio_duration";
    public static final String u = "author_name";
    public static final String v = "author_avatar";
    public static final String w = "video_url";
    public static final String x = "download_url";
    public static final String y = "templete_type";
    public static final String z = "templete_laug";
    private c H;
    private MediaPlayer I;
    private DisplayImageOptions J;
    private Runnable K;
    private a L;
    private dji.pilot2.nativeaudio.b.c M;
    private Runnable N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private String W;
    private boolean X;
    private int Y;
    private int Z;
    private ProgressBar aA;
    private View aB;
    private BufferSeekProgressBar aC;
    private TextView aD;
    private TextView aE;
    private ImageView aF;
    private View aG;
    private View aH;
    private View aI;
    private int aa;
    private int ab;
    private int ac;
    private boolean ad;
    private boolean ae;
    private List<DownloadTemplateBean> af;
    private View ag;
    private View ah;
    private View ai;
    private View aj;
    private View ak;
    private View al;
    private ImageView am;
    private View an;
    private View ao;
    private SurfaceView ap;
    private View aq;
    private TextView ar;
    private TextView as;
    private TextView at;
    private TextView au;
    private ImageView av;
    private TextView aw;
    private DJIStateTextView ax;
    private View ay;
    private TextView az;

    public interface a {
        void a(int i);
    }

    private static class b extends AsyncTask<String, Void, Integer> {
        a a;

        private b() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        public void a(a aVar) {
            this.a = aVar;
        }

        protected Integer a(String... strArr) {
            int i = -1;
            try {
                URLConnection openConnection = new URL(strArr[0]).openConnection();
                openConnection.connect();
                i = openConnection.getContentLength();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(i);
        }

        protected void a(Integer num) {
            super.onPostExecute(num);
            if (this.a != null) {
                this.a.a(num.intValue() / 1024);
            }
        }
    }

    public static class c extends Handler {
        SparseArray<WeakReference<Runnable>> a = new SparseArray();

        public void a(int i, Runnable runnable) {
            if (runnable != null) {
                this.a.put(i, new WeakReference(runnable));
            }
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference weakReference = (WeakReference) this.a.get(Integer.valueOf(message.what).intValue());
            if (weakReference != null) {
                Runnable runnable = (Runnable) weakReference.get();
                if (runnable != null) {
                    try {
                        runnable.run();
                        return;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                this.a.remove(message.what);
            }
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJILogHelper.getInstance().LOGD("Lyric", "onCreate");
        setContentView(R.layout.v2_activity_network_audio_preview);
        a();
        b();
        com.c.a.a.getInstance().c(25).a(255).b(this);
    }

    protected void onStart() {
        DJILogHelper.getInstance().LOGD("Lyric", "onStart");
        if (this.ad && !this.ae) {
            this.ae = true;
            this.H.postDelayed(this.K, 500);
        }
        super.onStart();
    }

    protected void onStop() {
        DJILogHelper.getInstance().LOGD("Lyric", "onStop");
        this.ae = false;
        super.onStop();
    }

    protected void onDestroy() {
        DJILogHelper.getInstance().LOGD("Lyric", "onDestroy");
        super.onDestroy();
        this.I.release();
    }

    private void a() {
        int i = 0;
        this.J = new Builder().showImageOnLoading(R.drawable.v2_template_djigo).cacheInMemory(true).cacheOnDisc(false).build();
        this.I = new MediaPlayer();
        this.ad = false;
        this.ae = false;
        this.ab = 0;
        this.H = new c();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(b);
        String stringExtra2 = intent.getStringExtra(c);
        String stringExtra3 = intent.getStringExtra(d);
        this.aa = intent.getIntExtra(a, Integer.MIN_VALUE);
        this.R = intent.getStringExtra(t);
        this.S = intent.getStringExtra(u);
        this.T = intent.getStringExtra(v);
        this.U = intent.getStringExtra(w);
        this.V = intent.getStringExtra(x);
        this.X = intent.getBooleanExtra(y, false);
        this.O = b(stringExtra);
        this.P = b(stringExtra2);
        this.Q = b(stringExtra3);
        this.W = intent.getStringExtra(z);
        this.Z = 0;
        this.af = com.dji.frame.c.c.c(this).c(DownloadTemplateBean.class);
        DJILogHelper.getInstance().LOGI("Lyric", "templateListId: " + this.aa);
        for (int i2 = 0; i2 < this.af.size(); i2++) {
            DownloadTemplateBean downloadTemplateBean = (DownloadTemplateBean) this.af.get(i2);
            DJILogHelper.getInstance().LOGI("Lyric", "DownloadTemplateBean: " + downloadTemplateBean.listId + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + downloadTemplateBean.templateId);
        }
        if (this.af != null) {
            while (i < this.af.size()) {
                downloadTemplateBean = (DownloadTemplateBean) this.af.get(i);
                if (downloadTemplateBean == null || this.aa != downloadTemplateBean.listId) {
                    i++;
                } else if (TemplateController.getInstance().containsId(downloadTemplateBean.templateId)) {
                    this.Z = 1;
                } else {
                    this.af.remove(downloadTemplateBean);
                    com.dji.frame.c.c.c(this).f(downloadTemplateBean);
                }
            }
        }
        this.K = new Runnable(this) {
            final /* synthetic */ NetworkAudioPreviewActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.ae) {
                    int currentPosition = this.a.I.getCurrentPosition();
                    int i = (currentPosition + 500) / 60000;
                    int i2 = ((currentPosition + 500) % 60000) / 1000;
                    this.a.aD.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
                    this.a.aC.setProgress(currentPosition);
                    this.a.H.postDelayed(this.a.K, 500);
                }
            }
        };
        this.L = new a(this) {
            final /* synthetic */ NetworkAudioPreviewActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.Z == 0 && i != -1) {
                    this.a.Y = i;
                    this.a.az.setText(this.a.getResources().getString(R.string.v2_network_audio_download_with_size, new Object[]{Integer.valueOf(this.a.Y)}));
                }
            }
        };
        this.N = new Runnable(this) {
            final /* synthetic */ NetworkAudioPreviewActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.ac == 1) {
                    if (this.a.an != null) {
                        this.a.an.setVisibility(8);
                    }
                    if (this.a.aj != null && this.a.aj.getId() == R.id.cci) {
                        this.a.aj.setVisibility(8);
                    }
                }
            }
        };
        this.H.a(G, this.N);
        if (this.X) {
            this.M = new dji.pilot2.nativeaudio.b.c(dji.pilot2.nativeaudio.b.c.a.BigFilm, this.W);
        } else {
            this.M = new dji.pilot2.nativeaudio.b.c(dji.pilot2.nativeaudio.b.c.a.Muti, this.W);
        }
        this.M.a(this);
    }

    private void b() {
        this.ag = findViewById(R.id.cc_);
        this.ah = findViewById(R.id.ccc);
        this.ai = findViewById(R.id.cco);
        this.an = findViewById(R.id.cce);
        this.am = (ImageView) findViewById(R.id.cch);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.am.getBackground();
        this.ax = (DJIStateTextView) findViewById(R.id.ccb);
        if (this.X) {
            this.ax.setText(getString(R.string.v2_bigfilm_download_title));
        }
        animationDrawable.start();
        this.ao = findViewById(R.id.cca);
        this.ap = (SurfaceView) findViewById(R.id.ccd);
        this.aq = findViewById(R.id.ccg);
        this.ar = (TextView) findViewById(R.id.ccv);
        this.as = (TextView) findViewById(R.id.ccw);
        this.at = (TextView) findViewById(R.id.cd0);
        this.au = (TextView) findViewById(R.id.ccx);
        this.av = (ImageView) findViewById(R.id.cd1);
        this.aw = (TextView) findViewById(R.id.cd2);
        this.ay = findViewById(R.id.cd3);
        this.az = (TextView) findViewById(R.id.cd5);
        this.aA = (ProgressBar) findViewById(R.id.cd4);
        this.aB = findViewById(R.id.cd6);
        this.aH = findViewById(R.id.ccy);
        this.aI = findViewById(R.id.ccz);
        f();
        if (this.Z == 0) {
            this.ay.setOnClickListener(this);
            b bVar = new b();
            bVar.a(this.L);
            bVar.execute(new String[]{this.V});
        } else {
            this.az.setText(R.string.v2_network_audio_downloaded);
            this.ay.setClickable(false);
        }
        this.aB.setOnClickListener(this);
        this.ar.setText(this.O);
        this.as.setText(this.P);
        if (this.Q == null || this.Q.equals("")) {
            this.aH.setVisibility(8);
            this.aI.setVisibility(8);
        } else {
            this.at.setText(this.Q);
        }
        this.au.setText(this.R);
        this.aw.setText(this.S);
        ImageLoader.getInstance().displayImage(this.T, this.av, this.J);
        this.ap.getHolder().addCallback(this);
    }

    private void f() {
        this.ac = 0;
        this.aC = (BufferSeekProgressBar) findViewById(R.id.ccs);
        this.aD = (TextView) findViewById(R.id.ccr);
        this.aE = (TextView) findViewById(R.id.cct);
        this.aF = (ImageView) findViewById(R.id.ccq);
        this.aG = findViewById(R.id.ccu);
        this.aj = findViewById(R.id.ccp);
        this.an.setVisibility(4);
        this.ak = findViewById(R.id.cd7);
        this.al = findViewById(R.id.cd8);
        if (this.ak != null) {
            this.ak.setVisibility(0);
        }
        if (this.al != null) {
            this.al.setVisibility(0);
        }
        LayoutParams layoutParams = this.ah.getLayoutParams();
        layoutParams.height = -2;
        this.ah.setLayoutParams(layoutParams);
        h();
    }

    private void g() {
        this.ac = 1;
        this.aC = (BufferSeekProgressBar) findViewById(R.id.ccl);
        this.aD = (TextView) findViewById(R.id.cck);
        this.aE = (TextView) findViewById(R.id.ccm);
        this.aF = (ImageView) findViewById(R.id.ccj);
        this.aG = findViewById(R.id.ccn);
        this.aj = findViewById(R.id.cci);
        this.an.setVisibility(0);
        if (this.ak != null) {
            this.ak.setVisibility(8);
        }
        if (this.al != null) {
            this.al.setVisibility(8);
        }
        h();
        LayoutParams layoutParams = this.ah.getLayoutParams();
        layoutParams.height = -1;
        this.ah.setLayoutParams(layoutParams);
        b(true);
    }

    private void h() {
        this.aC.setStrokeWidth((int) getResources().getDimension(R.dimen.gj));
        if (this.ad) {
            int i = (this.ab + 500) / 60000;
            int i2 = ((this.ab + 500) % 60000) / 1000;
            this.aC.setMaxProgress(this.ab);
            this.aE.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            this.aC.setMaxBufferProgress(100);
            this.aC.setOnSeekProgressCallBack(this);
        }
        try {
            if (this.I.isPlaying()) {
                this.aF.setImageResource(R.drawable.v2_template_pause);
            } else {
                this.aF.setImageResource(R.drawable.v2_template_play_triangle);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.aF.setImageResource(R.drawable.v2_template_play_triangle);
        }
        this.aj.setVisibility(0);
        b(false);
    }

    private void b(boolean z) {
        if (z) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags |= 1024;
            getWindow().setAttributes(attributes);
            return;
        }
        attributes = getWindow().getAttributes();
        attributes.flags &= -1025;
        getWindow().setAttributes(attributes);
    }

    private String b(String str) {
        MultiLangugeNameModel multiLangugeNameModel = (MultiLangugeNameModel) h.b(str, MultiLangugeNameModel.class);
        String str2 = multiLangugeNameModel != null ? Locale.getDefault().getCountry().equals("CN") ? multiLangugeNameModel.cn : Locale.getDefault().getCountry().equals("JP") ? multiLangugeNameModel.jp : multiLangugeNameModel.en : null;
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    public void onClickHandler(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.cca:
                    if (this.M != null) {
                        this.M.a();
                    }
                    finish();
                    return;
                case R.id.ccd:
                case R.id.ccg:
                case R.id.ccj:
                case R.id.ccq:
                    try {
                        if (this.I.isPlaying()) {
                            this.I.pause();
                            this.aq.setVisibility(0);
                            this.aF.setImageResource(R.drawable.v2_template_play_triangle);
                        } else {
                            this.I.start();
                            this.aq.setVisibility(4);
                            this.aF.setImageResource(R.drawable.v2_template_pause);
                            if (!this.ad) {
                                this.am.setVisibility(0);
                                this.aF.setImageResource(R.drawable.v2_template_play_triangle);
                            }
                        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        if (!this.ad) {
                            this.am.setVisibility(0);
                            this.aF.setImageResource(R.drawable.v2_template_play_triangle);
                        }
                    }
                    if (this.ac == 1) {
                        if (this.an != null) {
                            this.an.setVisibility(0);
                        }
                        if (this.aj != null && this.aj.getId() == R.id.cci) {
                            this.aj.setVisibility(0);
                        }
                    }
                    if (this.H.hasMessages(G)) {
                        this.H.removeMessages(G);
                    }
                    this.H.sendEmptyMessageDelayed(G, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                    return;
                case R.id.ccf:
                case R.id.ccn:
                    if (DJIOriLayout.getDeviceType().equals(DJIDeviceType.Phone)) {
                        setRequestedOrientation(1);
                    }
                    this.ag.setVisibility(0);
                    this.ai.setVisibility(0);
                    this.aj.setVisibility(8);
                    f();
                    if (this.H.hasMessages(G)) {
                        this.H.removeMessages(G);
                        return;
                    }
                    return;
                case R.id.ccu:
                    this.ag.setVisibility(8);
                    this.ai.setVisibility(8);
                    this.aj.setVisibility(8);
                    g();
                    if (getResources().getConfiguration().orientation != 2) {
                        setRequestedOrientation(0);
                    }
                    if (this.H.hasMessages(G)) {
                        this.H.removeMessages(G);
                    }
                    this.H.sendEmptyMessageDelayed(G, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cd3:
                this.ay.setClickable(false);
                this.M.a(this.V, (Context) this);
                try {
                    if (this.I.isPlaying()) {
                        this.I.pause();
                        return;
                    }
                    return;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.cd6:
                if (this.M != null) {
                    this.M.a();
                }
                this.ay.setOnClickListener(this);
                this.aA.setProgress(0);
                this.aB.setVisibility(4);
                if (this.Y != -1) {
                    this.az.setText(getResources().getString(R.string.v2_network_audio_download_with_size, new Object[]{Integer.valueOf(this.Y)}));
                    return;
                }
                this.az.setText(R.string.v2_network_audio_download);
                return;
            default:
                return;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        DJILogHelper.getInstance().LOGI("Lyric", "surface created");
        try {
            if (!this.ad) {
                this.I.setOnPreparedListener(this);
                this.I.setOnBufferingUpdateListener(this);
                this.I.setDataSource(this.U);
                this.I.prepareAsync();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        DJILogHelper.getInstance().LOGI("Lyric", "surface changed");
        this.I.setSurface(surfaceHolder.getSurface());
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        DJILogHelper.getInstance().LOGI("Lyric", "surface destroyed");
        if (this.I.isPlaying()) {
            this.I.pause();
            this.aq.setVisibility(0);
        }
        this.I.setSurface(null);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.ad = true;
        this.ae = true;
        try {
            this.I.setSurface(this.ap.getHolder().getSurface());
            this.ab = this.I.getDuration();
            int i = (this.ab + 500) / 60000;
            int i2 = ((this.ab + 500) % 60000) / 1000;
            this.aC.setMaxProgress(this.ab);
            this.aE.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            this.aC.setMaxBufferProgress(100);
            this.aC.setOnSeekProgressCallBack(this);
            DJILogHelper.getInstance().LOGI("Lyric", "duration: " + this.R);
            this.H.postDelayed(this.K, 500);
            this.I.start();
            this.am.setVisibility(4);
            this.aF.setImageResource(R.drawable.v2_template_pause);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        DJILogHelper.getInstance().LOGD("Lyric", "buffer update: " + i);
        this.aC.setBufferProgress(i);
    }

    public void a(float f) {
        if (this.ad) {
            int duration = (int) (((float) this.I.getDuration()) * f);
            this.I.seekTo(duration);
            this.aC.setProgress(duration);
        }
    }

    public void a(boolean z) {
        this.Z = 2;
        DJILogHelper.getInstance().LOGI("Lyric", "download start");
        this.aB.setVisibility(0);
    }

    public void a(int i) {
        DJILogHelper.getInstance().LOGI("Lyric", "download progress: " + i);
        this.az.setText(getResources().getString(R.string.v2_network_audio_downloading, new Object[]{Integer.valueOf(i)}));
        this.aA.setProgress(i);
    }

    public void a(String str) {
        InputStream inputStream = null;
        if (str == null) {
            a(null, 0, "");
            return;
        }
        File file = new File(str);
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DJILogHelper.getInstance().LOGI("Lyric", "inputStream: " + file.getAbsolutePath());
        if (inputStream != null) {
            if (this.X) {
                dji.pilot2.multimoment.template.c.getInstance().a((Context) this, str);
                dji.pilot2.multimoment.template.c.getInstance().b();
            } else {
                try {
                    dji.pilot2.template.a aVar = new dji.pilot2.template.a(this, inputStream);
                    DJILogHelper.getInstance().LOGI("Lyric", "template: " + aVar);
                    if (aVar != null) {
                        DownloadTemplateBean downloadTemplateBean = new DownloadTemplateBean();
                        downloadTemplateBean.listId = this.aa;
                        downloadTemplateBean.templateId = aVar.d();
                        com.dji.frame.c.c.c(this).a(downloadTemplateBean);
                    }
                    TemplateController.getInstance().updateTemplate(this, str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.az.setText(R.string.v2_network_audio_downloaded);
            this.aB.setVisibility(4);
            this.Z = 1;
            dji.thirdparty.a.c.a().e(new TemplateDownloadEvent(this.aa, true));
            DJILogHelper.getInstance().LOGI("Lyric", "download success");
        }
    }

    public void a(Throwable th, int i, String str) {
        this.Z = 4;
        this.az.setText(R.string.v2_network_audio_download_failed);
        this.aA.setProgress(0);
        this.aB.setVisibility(4);
        this.H.postDelayed(new Runnable(this) {
            final /* synthetic */ NetworkAudioPreviewActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.az.setText(R.string.v2_network_audio_download_retry);
                    this.a.ay.setOnClickListener(this.a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
        DJILogHelper.getInstance().LOGI("Lyric", "download failed");
        DJILogHelper.getInstance().LOGI("Lyric", "errorNo:" + i + " Msg:" + str);
    }

    public void onBackPressed() {
        if (this.ac == 1) {
            onClickHandler(findViewById(R.id.ccn));
        } else {
            onClickHandler(findViewById(R.id.cca));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
