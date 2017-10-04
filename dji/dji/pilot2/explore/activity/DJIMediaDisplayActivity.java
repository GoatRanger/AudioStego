package dji.pilot2.explore.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot2.nativeexplore.activity.ExploreLikesActivity;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class DJIMediaDisplayActivity extends Activity implements OnClickListener {
    public static final String a = "/ExploreCache";
    public static final String b = "from_gl";
    private dji.logic.album.a.b A;
    private int B;
    private boolean C = false;
    private String D;
    private String E;
    private WebView c;
    private PhotoView d;
    private ProgressBar e;
    private ProgressBar f;
    private Context g;
    private boolean h = false;
    private String i = "http://dn-skypixel-image.qbox.me/uploads/newphotos/491096df997ea570db3723907e27b66c/1920x1920";
    private String j = "https://www.djivideos.com/video_play/e001f909-1ed7-47c7-850b-27170dfb2a8e";
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private final String q = "DJIMediaDisplayActivity";
    private ImageLoader r;
    private DJITextView s;
    private DJITextView t;
    private DJIRelativeLayout u;
    private DJIStateImageView v;
    private DJIStateImageView w;
    private DJIStateImageView x;
    private String y = dji.pilot2.mine.e.a.a;
    private dji.pilot2.mine.e.a.a z = new dji.pilot2.mine.e.a.a();

    class a extends WebChromeClient {
        final /* synthetic */ DJIMediaDisplayActivity a;

        a(DJIMediaDisplayActivity dJIMediaDisplayActivity) {
            this.a = dJIMediaDisplayActivity;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            Log.i("zc", "progess" + i);
            this.a.e.setProgress(i);
        }
    }

    class b extends WebViewClient {
        final /* synthetic */ DJIMediaDisplayActivity a;

        b(DJIMediaDisplayActivity dJIMediaDisplayActivity) {
            this.a = dJIMediaDisplayActivity;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Log.i("zc", MessageKey.MSG_ACCEPT_TIME_START);
            this.a.e.setVisibility(0);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            Log.i("zc", MessageKey.MSG_ACCEPT_TIME_END);
            this.a.e.setVisibility(8);
            if (webView.canGoBack()) {
                this.a.w.show();
            } else {
                this.a.w.go();
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            Toast.makeText(this.a.g, "网络连接错误", 0).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
        }
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(4);
        }
        this.g = this;
        getWindow().addFlags(128);
        setContentView(R.layout.v2_explore_media_layout);
        this.r = ImageLoader.getInstance();
        a();
        this.D = new File(getExternalCacheDir().getAbsoluteFile(), a).getAbsolutePath();
        File file = new File(this.D);
        if (!file.exists()) {
            file.mkdir();
        }
        this.E = this.D + String.format("/%s.png", new Object[]{this.l});
        b();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getStringExtra("preview_photo");
            if (this.k == null || !this.k.equals("photo")) {
                this.k = intent.getStringExtra("preview_video");
                if (this.k == null || !this.k.equals("video")) {
                    Log.i("DJIMediaDisplayActivity", "media type is null");
                    finish();
                } else {
                    this.h = true;
                    this.l = intent.getStringExtra(ExploreLikesActivity.a);
                    this.o = intent.getStringExtra("preview_targeturl");
                    Intent intent2 = new Intent(this, DJISupportShareWebviewActivity.class);
                    intent2.putExtra(DJIWebviewFragment.o, this.o);
                    intent2.putExtra(DJIWebviewFragment.p, "");
                    Log.i("DJIMediaDisplayActivity", "finish!");
                    startActivity(intent2);
                    finish();
                }
            } else {
                this.h = false;
                this.C = intent.getBooleanExtra(b, false);
                this.l = intent.getStringExtra(ExploreLikesActivity.a);
                this.m = intent.getStringExtra("preview_thumburl");
                this.n = intent.getStringExtra("preview_originurl");
            }
            this.p = intent.getStringExtra("preview_title");
            return;
        }
        finish();
    }

    private void b() {
        this.c = (WebView) findViewById(R.id.cln);
        this.d = (PhotoView) findViewById(R.id.clm);
        this.e = (ProgressBar) findViewById(R.id.clo);
        this.f = (ProgressBar) findViewById(R.id.clp);
        this.s = (DJITextView) findViewById(R.id.clq);
        this.u = (DJIRelativeLayout) findViewById(R.id.c6y);
        this.v = (DJIStateImageView) findViewById(R.id.clj);
        this.w = (DJIStateImageView) findViewById(R.id.c98);
        this.x = (DJIStateImageView) findViewById(R.id.cll);
        this.t = (DJITextView) findViewById(R.id.clk);
        this.A = dji.logic.album.a.b.getInstance();
        this.x.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        if (this.h) {
            this.f.setVisibility(8);
            this.s.go();
            this.d.setVisibility(8);
            this.t.setText(this.p);
        } else {
            this.u.go();
        }
        this.s.setOnClickListener(this);
        this.d.setOnViewTapListener(new OnViewTapListener(this) {
            final /* synthetic */ DJIMediaDisplayActivity a;

            {
                this.a = r1;
            }

            public void onViewTap(View view, float f, float f2) {
                this.a.finish();
            }
        });
        this.c.setVisibility(8);
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setPluginState(PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setAppCacheMaxSize(8388608);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getDir(MapTilsCacheAndResManager.MAP_CACHE_PATH_NAME, 0).getPath());
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        this.c.getSettings().setUseWideViewPort(true);
        this.c.getSettings().setLoadWithOverviewMode(true);
        this.c.setVerticalScrollBarEnabled(false);
        this.c.setWebChromeClient(new a(this));
        this.c.setWebViewClient(new b(this));
        if (this.h) {
            this.c.setVisibility(0);
            this.c.loadUrl(this.o);
            this.f.setVisibility(8);
        } else if (this.A.b(this.l)) {
            this.s.go();
            this.d.setImageBitmap(this.A.a(this.l));
            this.f.setVisibility(8);
        } else {
            if (this.C) {
                this.s.go();
            } else {
                this.s.show();
            }
            String str = this.m;
            File file = new File(this.E);
            if (file.exists()) {
                str = a$e.a + file.getAbsolutePath();
            }
            this.r.displayImage(str, this.d, new ImageLoadingListener(this) {
                final /* synthetic */ DJIMediaDisplayActivity a;

                {
                    this.a = r1;
                }

                public void onLoadingStarted(String str, View view) {
                    Log.i("DJIMediaDisplayActivity", "loading start!");
                }

                public void onLoadingFailed(String str, View view, FailReason failReason) {
                    Log.i("DJIMediaDisplayActivity", "loading failed!");
                }

                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    Log.i("DJIMediaDisplayActivity", "loading complete!");
                    this.a.f.setVisibility(8);
                }

                public void onLoadingCancelled(String str, View view) {
                }
            });
        }
        if (!this.h && !this.C && this.n != null && !new File(this.E).exists()) {
            new AsyncTask<String, Void, Integer>(this) {
                final /* synthetic */ DJIMediaDisplayActivity a;

                protected java.lang.Integer a(java.lang.String... r4) {
                    /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                    /*
                    r3 = this;
                    r0 = 0;
                    r0 = r4[r0];
                    r1 = 0;
                    r2 = new java.net.URL;	 Catch:{ Exception -> 0x001d, all -> 0x0029 }
                    r2.<init>(r0);	 Catch:{ Exception -> 0x001d, all -> 0x0029 }
                    r0 = r2.openConnection();	 Catch:{ Exception -> 0x001d, all -> 0x0029 }
                    r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x001d, all -> 0x0029 }
                    if (r0 == 0) goto L_0x0014;
                L_0x0011:
                    r0.disconnect();
                L_0x0014:
                    r0 = r0.getContentLength();
                    r0 = java.lang.Integer.valueOf(r0);
                L_0x001c:
                    return r0;
                L_0x001d:
                    r0 = move-exception;
                    r0 = -1;
                    r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x001d, all -> 0x0029 }
                    if (r1 == 0) goto L_0x001c;
                L_0x0025:
                    r1.disconnect();
                    goto L_0x001c;
                L_0x0029:
                    r0 = move-exception;
                    if (r1 == 0) goto L_0x002f;
                L_0x002c:
                    r1.disconnect();
                L_0x002f:
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.explore.activity.DJIMediaDisplayActivity.3.a(java.lang.String[]):java.lang.Integer");
                }

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Integer) obj);
                }

                protected void a(Integer num) {
                    this.a.B = num.intValue();
                    if (this.a.B != -1) {
                        String format = new DecimalFormat("##0.00").format((double) (((float) this.a.B) / 1000000.0f));
                        this.a.s.setText(this.a.getResources().getString(R.string.v2_explore_downloadpic, new Object[]{format}));
                    }
                }
            }.execute(new String[]{this.n});
        }
    }

    public void a(dji.pilot2.mine.e.a.a aVar) {
        dji.pilot2.share.b.b bVar = new dji.pilot2.share.b.b(this);
        if (this.y.equals("photo")) {
            bVar.a(dji.pilot2.share.e.a.a.CONTENT_IMG);
        } else if (this.y.equals("video")) {
            bVar.a(dji.pilot2.share.e.a.a.CONTENT_VIDEO);
        }
        bVar.a(dji.pilot2.share.b.b.a.EXPLORE_MINE);
        bVar.a(aVar);
        bVar.a(aVar.b());
        bVar.show();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c98:
                finish();
                return;
            case R.id.clj:
                Log.i("DJIMediaDisplayActivity", "web view go back !" + this.c.canGoBack());
                if (this.c == null || !this.c.canGoBack()) {
                    finish();
                    return;
                } else {
                    this.c.goBack();
                    return;
                }
            case R.id.cll:
                this.z.c = this.p;
                this.z.b = this.o;
                a(this.z);
                return;
            case R.id.clq:
                if (this.n != null) {
                    this.r.loadImage(this.n, new ImageLoadingListener(this) {
                        final /* synthetic */ DJIMediaDisplayActivity a;

                        {
                            this.a = r1;
                        }

                        public void onLoadingStarted(String str, View view) {
                            this.a.f.setVisibility(0);
                        }

                        public void onLoadingFailed(String str, View view, FailReason failReason) {
                            this.a.f.setVisibility(8);
                        }

                        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                            this.a.f.setVisibility(8);
                            if (bitmap != null) {
                                this.a.s.go();
                                this.a.d.setImageBitmap(bitmap);
                                if (this.a.l != null) {
                                    this.a.A.a(this.a.l, bitmap);
                                }
                                final File file = new File(this.a.E);
                                if (!file.exists()) {
                                    new AsyncTask<Bitmap, Void, Void>(this) {
                                        final /* synthetic */ AnonymousClass4 b;

                                        protected /* synthetic */ Object doInBackground(Object[] objArr) {
                                            return a((Bitmap[]) objArr);
                                        }

                                        protected Void a(Bitmap... bitmapArr) {
                                            OutputStream fileOutputStream;
                                            Exception e;
                                            Throwable th;
                                            try {
                                                fileOutputStream = new FileOutputStream(file);
                                                try {
                                                    bitmapArr[0].compress(CompressFormat.PNG, 100, fileOutputStream);
                                                    if (fileOutputStream != null) {
                                                        try {
                                                            fileOutputStream.close();
                                                        } catch (IOException e2) {
                                                        }
                                                    }
                                                } catch (Exception e3) {
                                                    e = e3;
                                                    try {
                                                        e.printStackTrace();
                                                        if (fileOutputStream != null) {
                                                            try {
                                                                fileOutputStream.close();
                                                            } catch (IOException e4) {
                                                            }
                                                        }
                                                        file.deleteOnExit();
                                                        return null;
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        if (fileOutputStream != null) {
                                                            try {
                                                                fileOutputStream.close();
                                                            } catch (IOException e5) {
                                                            }
                                                        }
                                                        throw th;
                                                    }
                                                }
                                            } catch (Exception e6) {
                                                e = e6;
                                                fileOutputStream = null;
                                                e.printStackTrace();
                                                if (fileOutputStream != null) {
                                                    fileOutputStream.close();
                                                }
                                                file.deleteOnExit();
                                                return null;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                fileOutputStream = null;
                                                if (fileOutputStream != null) {
                                                    fileOutputStream.close();
                                                }
                                                throw th;
                                            }
                                            file.deleteOnExit();
                                            return null;
                                        }
                                    }.execute(new Bitmap[]{bitmap});
                                }
                            }
                        }

                        public void onLoadingCancelled(String str, View view) {
                            this.a.f.setVisibility(8);
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }
}
