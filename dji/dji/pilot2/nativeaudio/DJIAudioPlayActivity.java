package dji.pilot2.nativeaudio;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.here.odnp.config.OdnpConfigStatic;
import dji.g.b.e;
import dji.g.b.h;
import dji.g.b.i;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.k;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.nativeaudio.a.b;
import dji.pilot2.nativeaudio.b.a;
import dji.pilot2.nativeaudio.view.AudioCursor;
import dji.pilot2.utils.n;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DJIAudioPlayActivity extends DJIBaseAudioActivity implements OnCompletionListener, OnErrorListener, OnSeekCompleteListener, OnClickListener, OnItemClickListener, k {
    public static final String K = "key_list";
    public static final String L = "key_all_list";
    public static final String M = "localMusicPath";
    int N;
    private final String O = "DJIAudioPlayActivity";
    private ListView P;
    private b Q;
    private a R;
    private float S = 0.0f;
    private float T = 0.0f;
    private View U;
    private TextView V;
    private TextView W;
    private TextView X;
    private String Y;
    private List<dji.pilot2.nativeaudio.model.b> Z;
    private MediaPlayer aa;
    private long ab = 0;
    private RelativeLayout ac;
    private long ad;
    private long ae;
    private long af;
    private List<Long> ag;
    private List<Long> ah;
    private long ai;
    private long aj;
    private dji.pilot2.widget.b ak;
    private AudioCursor al;
    private View am;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private View as;
    private TextView at;
    private boolean au = false;
    private b.b av;
    private int aw = 100;
    private Context ax;
    private AudioCursor.a ay = new AudioCursor.a(this) {
        final /* synthetic */ DJIAudioPlayActivity a;

        {
            this.a = r1;
        }

        public void a() {
            long a = this.a.d((long) this.a.n());
            if (this.a.aj - this.a.ai < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                this.a.b(this.a.ai);
            } else if (this.a.aj - a < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                this.a.b(this.a.aj - OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
            } else {
                this.a.b(a);
            }
        }

        public void a(int i) {
            int a = (int) this.a.d((long) (this.a.n() + i));
            DJILogHelper.getInstance().LOGD("rxq2", "starttime:" + this.a.ai + "  endtime:" + this.a.aj + "   newtime:" + a);
            if (((long) a) < this.a.aj && ((long) a) > this.a.ai) {
                this.a.a((long) a);
            } else if (((long) a) < this.a.ai) {
                this.a.a(this.a.ai);
            } else if (((long) a) > this.a.aj) {
                this.a.a(this.a.aj);
            }
        }

        public void b() {
            this.a.az.removeMessages(0);
        }
    };
    private Handler az = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ DJIAudioPlayActivity a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == this.a.aw) {
                if (this.a.P.getChildCount() > 0) {
                    this.a.av = (b.b) this.a.P.getChildAt(0).getTag();
                    this.a.av.d.setVisibility(0);
                    this.a.av.a.setTextColor(this.a.ax.getResources().getColor(R.color.jp));
                    this.a.av.b.setTextColor(this.a.ax.getResources().getColor(R.color.jp));
                    this.a.av.c.setTextColor(this.a.ax.getResources().getColor(R.color.jp));
                    this.a.Q.a(0);
                }
            } else if (this.a.aa != null) {
                sendEmptyMessageDelayed(0, 20);
                long currentPosition = (long) this.a.aa.getCurrentPosition();
                if (currentPosition > this.a.aj) {
                    this.a.b(this.a.ai);
                }
                this.a.a(currentPosition);
            }
        }
    };

    private void a(long j) {
        this.X.setText(p.f((int) (((float) j) / 1000.0f)) + d.t + p.f((int) (((float) this.ab) / 1000.0f)));
        c((int) c(j));
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_audioplay_layout);
        a();
        this.ax = this;
        this.az.sendEmptyMessageDelayed(this.aw, 500);
    }

    protected void onDestroy() {
        if (this.aa != null) {
            this.aa.stop();
            this.aa.release();
        }
        this.az.removeMessages(0);
        b(this.as);
        super.onDestroy();
    }

    protected void a() {
        super.a();
        this.U = findViewById(R.id.chf);
        this.U.setOnClickListener(this);
        this.V = (TextView) findViewById(R.id.chh);
        this.W = (TextView) findViewById(R.id.chj);
        this.X = (TextView) findViewById(R.id.chi);
        this.P = (ListView) findViewById(R.id.chm);
        this.P.setOnItemClickListener(this);
        this.Q = new b(this);
        this.Q.a(b.a.AdapterHasTime);
        this.c.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.b.setClickable(false);
        this.ac = (RelativeLayout) findViewById(R.id.chk);
        j();
        this.as = findViewById(R.id.chn);
        this.as.setOnClickListener(this);
        this.at = (DJITextView) findViewById(R.id.c8a);
        this.al = (AudioCursor) findViewById(R.id.chl);
        this.al.setListener(this.ay);
        this.am = findViewById(R.id.che);
        Intent intent = getIntent();
        if (intent.getBooleanExtra(L, false)) {
            this.R = new a(this);
            this.R.a(new a.a(this) {
                final /* synthetic */ DJIAudioPlayActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    ArrayList b = this.a.R.b();
                    if (b.size() > 0) {
                        this.a.Z = b;
                        this.a.am.setVisibility(0);
                        this.a.au = true;
                        this.a.b.setClickable(true);
                        this.a.b();
                        this.a.f();
                        this.a.k();
                        this.a.a(0);
                        this.a.N = 0;
                        this.a.a((dji.pilot2.nativeaudio.model.b) this.a.Z.get(0));
                        this.a.l();
                        this.a.g();
                    }
                    this.a.Q.a(b);
                    this.a.P.setSelector(R.drawable.v2_audioitem_selector);
                    this.a.P.setAdapter(this.a.Q);
                    this.a.Q.notifyDataSetChanged();
                }
            });
            this.R.a();
        } else {
            ArrayList arrayList = (ArrayList) intent.getBundleExtra("list").getSerializable("key_list");
            this.Z = arrayList;
            if (arrayList.size() > 0) {
                this.am.setVisibility(0);
                this.au = true;
                this.b.setClickable(true);
                b();
                f();
                k();
                this.N = 0;
                a((dji.pilot2.nativeaudio.model.b) this.Z.get(0));
                a(0);
                l();
                g();
            }
            Log.i("zhang", "size:" + arrayList.size());
            this.Q.a(arrayList);
            this.P.setSelector(R.drawable.v2_audioitem_selector);
            this.P.setAdapter(this.Q);
            this.Q.notifyDataSetChanged();
        }
        this.P.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIAudioPlayActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("zhang", "onTouch list");
                switch (motionEvent.getAction()) {
                    case 0:
                        this.a.S = motionEvent.getX();
                        break;
                    case 1:
                        Log.i("zhang", "onTouch list:" + this.a.T);
                        if (this.a.T > 200.0f) {
                            this.a.finish();
                            return true;
                        }
                        break;
                    case 2:
                        this.a.T = motionEvent.getX() - this.a.S;
                        Log.i("zhang", "move:" + motionEvent.getX());
                        break;
                }
                return false;
            }
        });
    }

    private void b() {
        this.aa = new MediaPlayer();
        this.aa.setOnSeekCompleteListener(this);
        this.aa.setOnCompletionListener(this);
        this.aa.setOnErrorListener(this);
    }

    protected void a(boolean z) {
        super.a(z);
    }

    protected void onResume() {
        if (this.b.isClickable()) {
            g();
        }
        super.onResume();
    }

    protected void onPause() {
        h();
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    public void a(TextView textView) {
        textView.setText(getResources().getString(R.string.v2_audio_songs));
    }

    public void a(TextView textView, boolean z) {
        textView.setText(getResources().getString(R.string.v2_lib_input));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chf:
                i();
                return;
            case R.id.cv8:
                finish();
                return;
            case R.id.cv_:
                DJILogHelper.getInstance().LOGD("rxq", "import begin...");
                h();
                this.b.setClickable(false);
                this.c.setClickable(false);
                this.as.setVisibility(0);
                a(this.as);
                new Thread(this) {
                    final /* synthetic */ DJIAudioPlayActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            dji.g.b.a aVar = new dji.g.b.a();
                            aVar.a(this.a.Y);
                            "" + System.currentTimeMillis();
                            String name = new File(this.a.Y).getName();
                            String str = n.g(this.a) + name;
                            String str2 = n.c(this.a) + name;
                            e[] eVarArr = new e[]{new e(this.a.Y, dji.g.a.a.a(this.a.Y), this.a.ai, this.a.aj, false, 1.0d)};
                            dji.midware.media.e.d("DJIAudioPlayActivity", "audio import start=" + this.a.ai + " end=" + this.a.aj);
                            if (aVar.a(new h(eVarArr, true, null, null, str, true, 0, 0, null, new i(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public void a() {
                                }

                                public void a(final int i) {
                                    this.a.a.az.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 b;

                                        public void run() {
                                            this.b.a.a.at.setText(String.valueOf(i) + "%");
                                        }
                                    });
                                }

                                public void b(int i) {
                                }

                                public void c(int i) {
                                    this.a.a.runOnUiThread(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            Toast.makeText(this.a.a.a, this.a.a.a.getString(R.string.audio_cut_saving_failure), 1).show();
                                        }
                                    });
                                }
                            }, null, 0)) == 0) {
                                try {
                                    dji.pilot.fpv.d.e.b(k.F);
                                    DJILogHelper.getInstance().LOGD("DJIAudioPlayActivity", "cut music suc: " + str2);
                                    new File(str).renameTo(new File(str2));
                                    dji.midware.media.e.d("DJIAudioPlayActivity", "native music outPath=" + str2);
                                    Intent intent = new Intent();
                                    intent.putExtra(DJIAudioPlayActivity.M, str2);
                                    this.a.setResult(-1, intent);
                                    this.a.finish();
                                    return;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            DJILogHelper.getInstance().LOGD("DJIAudioPlayActivity", "cut music fail! src file: " + this.a.Y);
                            p.d(str);
                        } catch (Exception e2) {
                            dji.midware.media.e.a("DJIAudioPlayActivity", e2);
                        }
                    }
                }.start();
                return;
            default:
                return;
        }
    }

    private void f() {
        if (this.Z == null) {
            return;
        }
        if (this.Z.size() <= 0 || this.N >= this.Z.size()) {
            DJILogHelper.getInstance().LOGD("rxq", "播放列表为空.");
            return;
        }
        this.Y = ((dji.pilot2.nativeaudio.model.b) this.Z.get(this.N)).a;
        this.az.removeMessages(0);
        this.aa.reset();
        try {
            this.aa.setDataSource(this.Y);
            this.aa.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        b(this.ai);
    }

    private void g() {
        if (this.aa != null && !this.aa.isPlaying()) {
            this.U.setSelected(true);
            this.aa.start();
            this.az.sendEmptyMessageDelayed(0, 100);
        }
    }

    private void h() {
        if (this.aa != null && this.aa.isPlaying()) {
            this.U.setSelected(false);
            this.aa.pause();
            this.az.removeMessages(0);
        }
    }

    private void b(long j) {
        if (this.aa != null) {
            this.az.removeMessages(0);
            this.aa.seekTo((int) j);
        }
    }

    private void i() {
        if (this.U.isSelected()) {
            this.U.setSelected(false);
            h();
            return;
        }
        this.U.setSelected(true);
        g();
    }

    private void a(dji.pilot2.nativeaudio.model.b bVar) {
        if (bVar != null) {
            this.V.setText(bVar.c);
            this.W.setText(bVar.h);
            this.ab = bVar.e;
            this.ap = (int) c(1000);
            this.aq = (int) this.ad;
        }
    }

    private long c(long j) {
        return ((1 * j) * this.ad) / this.ab;
    }

    private long d(long j) {
        return ((1 * j) * this.ab) / this.ad;
    }

    private void j() {
        long dimensionPixelSize;
        long b = (long) p.b((Context) this);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            dimensionPixelSize = (long) getResources().getDimensionPixelSize(R.dimen.gl);
        } else {
            dimensionPixelSize = (long) getResources().getDimensionPixelSize(R.dimen.g0);
        }
        DJILogHelper.getInstance().LOGD("rxq", "screenWidth: " + b + " tmp: " + dimensionPixelSize);
        this.ad = b - (2 * dimensionPixelSize);
        this.ae = dimensionPixelSize;
    }

    private void k() {
        this.ag = new ArrayList();
        this.ah = new ArrayList();
        int size = this.Z.size();
        for (int i = 0; i < size; i++) {
            this.ag.add(Long.valueOf(0));
            this.ah.add(Long.valueOf(((dji.pilot2.nativeaudio.model.b) this.Z.get(i)).e));
        }
    }

    private void l() {
        long c = c(this.ai) + this.ae;
        long c2 = c(this.aj - this.ai);
        this.ak = new dji.pilot2.widget.b(this, this.ac, 1);
        this.ak.a((int) c, (int) c2);
        this.ak.a(p.e((int) (((this.aj - this.ai) + 500) / 1000)));
        this.ak.b(false);
        if (this.ac.getChildCount() == 2) {
            this.ac.removeViewAt(1);
        }
        this.ac.addView(this.ak.a());
        c((int) c(this.ai));
        this.al.setVisibility(0);
    }

    private void a(int i) {
        this.ai = ((Long) this.ag.get(i)).longValue();
        this.aj = ((Long) this.ah.get(i)).longValue();
    }

    private void b(int i) {
        this.ag.remove(i);
        this.ag.add(i, Long.valueOf(this.ai));
        this.ah.remove(i);
        this.ah.add(i, Long.valueOf(this.aj));
    }

    private void m() {
        int c = this.ak.c() - ((int) this.ae);
        int b = this.ak.b() + c;
        long d = d((long) c);
        long d2 = d((long) b);
        this.ai = d;
        this.aj = d2;
        b(this.N);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i < this.Z.size()) {
            if (this.av != null) {
                this.av.d.setVisibility(4);
                this.av.a.setTextColor(this.ax.getResources().getColor(R.color.js));
                this.av.b.setTextColor(this.ax.getResources().getColor(R.color.js));
                this.av.c.setTextColor(this.ax.getResources().getColor(R.color.js));
            }
            this.av = (b.b) view.getTag();
            this.Q.a(i);
            this.al.setVisibility(4);
            this.av.d.setVisibility(0);
            this.av.a.setTextColor(this.ax.getResources().getColor(R.color.jp));
            this.av.b.setTextColor(this.ax.getResources().getColor(R.color.jp));
            this.av.c.setTextColor(this.ax.getResources().getColor(R.color.jp));
            if (this.N == i) {
                this.ai = 0;
                this.aj = ((dji.pilot2.nativeaudio.model.b) this.Z.get(i)).e;
            }
            b(this.N);
            this.N = i;
            a(i);
            a((dji.pilot2.nativeaudio.model.b) this.Z.get(i));
            f();
            l();
            g();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        f();
        g();
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        this.az.sendEmptyMessageDelayed(0, 20);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.b.isClickable()) {
            finish();
        }
        return false;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int[] iArr = new int[2];
            this.ac.getLocationInWindow(iArr);
            this.af = (long) iArr[1];
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.au) {
            return true;
        }
        int x;
        int y;
        switch (motionEvent.getAction()) {
            case 0:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                this.ar = a(x, y - ((int) this.af), this.ak.a());
                if (this.ar == 1 || this.ar == 3) {
                    this.an = x;
                    this.ao = y;
                    this.az.removeMessages(0);
                    this.ak.b(true);
                    break;
                }
            case 1:
                if (this.ar != 2) {
                    this.ak.b(false);
                    m();
                    if (this.ar != 1) {
                        if (this.ar == 3) {
                            if (this.aj - this.ai <= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                                b(this.ai);
                                break;
                            }
                            b(this.aj - OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                            break;
                        }
                    }
                    b(this.ai);
                    break;
                }
                this.ar = 0;
                break;
                break;
            case 2:
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                y = this.ak.b();
                int c = this.ak.c();
                int i;
                if (this.ar == 3) {
                    i = (x - this.an) + y;
                    if (i > this.ap && ((long) (c + i)) <= this.ae + this.ad && i <= this.aq) {
                        this.ak.a(this.ak.c(), i);
                        this.ak.a(p.e((int) ((d((long) i) + 500) / 1000)));
                        a(d(((long) (this.ak.c() + this.ak.b())) - this.ae));
                    }
                } else if (this.ar == 1) {
                    i = (this.an - x) + y;
                    if (i > this.ap && i <= this.aq) {
                        y = (x - this.an) + c;
                        if (((long) y) >= this.ae) {
                            this.ak.a(y, i);
                            this.ak.a(p.e((int) ((d((long) i) + 500) / 1000)));
                            a(d(((long) this.ak.c()) - this.ae));
                        }
                    }
                }
                this.an = x;
                break;
        }
        DJILogHelper.getInstance().LOGD("rxq", "mDragLeftOrRight: " + this.ar);
        return false;
    }

    public int a(int i, int i2, View view) {
        Rect rect = new Rect(0, 0, 0, 0);
        view.getHitRect(rect);
        DJILogHelper.getInstance().LOGD("rxq", "r.left: " + rect.left + " r.top: " + rect.top + " r.right: " + rect.right + " r.bottom: " + rect.bottom);
        DJILogHelper.getInstance().LOGD("rxq", "touch x: " + i + " y: " + i2);
        int i3 = rect.right - rect.left;
        int i4 = i3 / 5 > 100 ? 60 : i3 / 5;
        Rect rect2 = new Rect(rect.left, rect.top, rect.left + i4, rect.bottom);
        Rect rect3 = new Rect((rect.left + i3) - i4, rect.top, rect.right, rect.bottom);
        Rect rect4 = new Rect(rect.left + i4, rect.top, (i3 + rect.left) - i4, rect.bottom);
        if (rect2.contains(i, i2)) {
            return 1;
        }
        if (rect3.contains(i, i2)) {
            return 3;
        }
        return rect4.contains(i, i2) ? 2 : 0;
    }

    private void c(int i) {
        this.al.setX((float) (((int) (((long) i) + this.ae)) - (this.al.getWidth() / 2)));
    }

    private int n() {
        return (int) (((long) (((int) this.al.getX()) + (this.al.getWidth() / 2))) - this.ae);
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
}
