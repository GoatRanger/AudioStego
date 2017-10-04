package dji.pilot2.media.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.Settings.System;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.ortiz.touch.TouchImageView;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.R;
import dji.pilot.fpv.d.c.j;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.publics.widget.b;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.library.DJILibraryPhotoView;
import dji.pilot2.library.MixAlbumSyncManager;
import dji.pilot2.library.MixAlbumSyncManager.c;
import dji.pilot2.library.d;
import dji.pilot2.library.h;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.nativeexplore.activity.PublishArtworkActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.djiviewpager.DJIViewPager;
import dji.thirdparty.g.a.i;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.g;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DJIPhotoPreveiwActivity extends DJIActivityNoFullScreen implements j {
    public static boolean B = false;
    public static final String C = "file_path";
    public static final String D = "selected";
    public static final String E = "org";
    public static final String F = "createtime";
    public static final String G = "createtimeorg";
    public static final String H = "index";
    public static final String I = "length";
    public static final String J = "pathlength";
    public static final String K = "pathstr";
    public static final String L = "candown";
    public static final String M = "input";
    public static final String N = "local";
    public static final String O = "explorepost";
    private static h aC = null;
    private static final int aE = 1001;
    private static final int aF = 1002;
    private static final int aG = 1003;
    int P = 0;
    private DJITextView Q = null;
    private DJITextView R = null;
    private DJITextView S = null;
    private DJITextView T = null;
    private DJITextView U = null;
    private DJITextView V = null;
    private DJITextView W = null;
    private DJITextView X;
    private Button Y = null;
    private DJIStateImageView Z = null;
    private Toast aA = null;
    private int aB = 0;
    private volatile boolean aD;
    private c aH = null;
    private OnClickListener aI = new OnClickListener(this) {
        final /* synthetic */ DJIPhotoPreveiwActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cei:
                    if (!DJIPhotoPreveiwActivity.B) {
                        this.a.a();
                    }
                    if (DJIPhotoPreveiwActivity.aC != null) {
                        DJIPhotoPreveiwActivity.aC.a(this.a.ag);
                        return;
                    }
                    return;
                case R.id.cej:
                    if (this.a.ag) {
                        this.a.ag = false;
                        this.a.ac.setImageResource(R.drawable.v2_media_unselect);
                        if (!this.a.ax) {
                            dji.thirdparty.a.c.a().e(dji.pilot2.library.a.PhotoUnSelect);
                            return;
                        }
                        return;
                    }
                    this.a.ag = true;
                    this.a.ac.setImageResource(R.drawable.v2_media_select);
                    if (!this.a.ax) {
                        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.PhotoSelect);
                        return;
                    }
                    return;
                case R.id.ces:
                    if (this.a.ah) {
                        this.a.ah = false;
                        this.a.aa.hide();
                        this.a.ab.setImageResource(R.drawable.v2_photo_preview_show_detail);
                        return;
                    }
                    this.a.ah = true;
                    this.a.aa.show();
                    this.a.ab.setImageResource(R.drawable.v2_photo_preview_hide_detail);
                    return;
                case R.id.cet:
                    this.a.q();
                    return;
                case R.id.ceu:
                    if (DJIPhotoPreveiwActivity.B) {
                        this.a.i();
                        return;
                    }
                    this.a.Y.setEnabled(true);
                    this.a.Z.setVisibility(4);
                    return;
                case R.id.cez:
                    if (!DJIPhotoPreveiwActivity.B) {
                        this.a.g();
                        return;
                    }
                    return;
                case R.id.cf0:
                    this.a.finish();
                    return;
                case R.id.cf1:
                    if (this.a.aD) {
                        this.a.aD = false;
                        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.SELECTCLEAR);
                        if (!DJIPhotoPreveiwActivity.B) {
                            if (DJILibraryPhotoView.C.isOrg) {
                                DJIPhotoEditorActivity.a(this.a, DJILibraryPhotoView.C.orgPath, dji.pilot.publics.objects.a.a);
                            } else {
                                DJIPhotoEditorActivity.a(this.a, this.a.af, dji.pilot.publics.objects.a.a);
                            }
                            this.a.r();
                        }
                        this.a.aD = true;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private final TouchImageView[] aJ = new TouchImageView[3];
    private DJIViewPager aK = null;
    private int aL = -1;
    private a aM = null;
    private DJILinearLayout aa = null;
    private DJIImageView ab = null;
    private DJIImageView ac = null;
    private DJIImageView ad = null;
    private Button ae = null;
    private String af = null;
    private boolean ag = false;
    private boolean ah = false;
    private boolean ai = false;
    private boolean aj = false;
    private boolean ak = false;
    private RelativeLayout al;
    private b am = null;
    private dji.pilot.playback.litchi.h an = null;
    private DJIStateTextView ao;
    private DJIStateTextView ap;
    private RelativeLayout aq;
    private long ar;
    private long as;
    private int at;
    private long au;
    private int av;
    private String aw;
    private boolean ax;
    private boolean ay;
    private Handler az = null;

    private final class a extends PagerAdapter {
        ArrayList<DJISycAlbumModel> a = MixAlbumSyncManager.getInstance(this.b).getShowList();
        final /* synthetic */ DJIPhotoPreveiwActivity b;

        public a(DJIPhotoPreveiwActivity dJIPhotoPreveiwActivity) {
            this.b = dJIPhotoPreveiwActivity;
        }

        public int getCount() {
            if (this.b.ax) {
                return 1;
            }
            return this.a == null ? 0 : this.a.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int length = i % this.b.aJ.length;
            if (this.b.ax) {
                View b = this.b.b(this.b.af);
                viewGroup.addView(b);
                return b;
            }
            this.a = MixAlbumSyncManager.getInstance(this.b).getShowList();
            if (this.b.aJ[length].getParent() != null) {
                viewGroup.removeView(this.b.aJ[length]);
                this.b.aJ[length] = new TouchImageView(this.b);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.gravity = 17;
                this.b.aJ[length].setLayoutParams(layoutParams);
            }
            this.b.a(this.a, this.b.aJ[length], i);
            viewGroup.addView(this.b.aJ[length]);
            this.b.aK.setObjectForPosition(this.b.aJ[length], i);
            return this.b.aJ[length];
        }
    }

    public static void a(Context context, String str, boolean z, int i) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("file_path", str);
            bundle.putBoolean(M, z);
            bundle.putBoolean(D, false);
            bundle.putBoolean(O, true);
            Intent intent = new Intent(context, DJIPhotoPreveiwActivity.class);
            intent.setFlags(131072);
            intent.putExtras(bundle);
            ((Activity) context).startActivityForResult(intent, 11);
            ((Activity) context).overridePendingTransition(17432576, 17432577);
        }
    }

    public static void a(Context context, String str, boolean z, boolean z2, boolean z3, int i, long j, long j2, int i2, long j3, int i3, String str2) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("file_path", str);
            bundle.putBoolean(D, z3);
            bundle.putBoolean(E, z);
            bundle.putLong("createtime", j);
            bundle.putLong(G, j2);
            bundle.putInt("index", i2);
            bundle.putLong(I, j3);
            bundle.putInt(J, i3);
            bundle.putString(K, str2);
            bundle.putBoolean(L, z2);
            aC = null;
            com.dji.frame.c.b.a(context, DJIPhotoPreveiwActivity.class, bundle, i);
        }
    }

    public static void a(Context context, String str, boolean z, boolean z2, boolean z3, int i) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("file_path", str);
            bundle.putBoolean(D, z3);
            bundle.putBoolean(E, z);
            bundle.putBoolean(L, z2);
            aC = null;
            com.dji.frame.c.b.a(context, DJIPhotoPreveiwActivity.class, bundle, i);
        }
    }

    public static void a(Context context, String str, boolean z, boolean z2, boolean z3, boolean z4, int i) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("file_path", str);
            bundle.putBoolean(D, z3);
            bundle.putBoolean(E, z);
            bundle.putBoolean(N, z4);
            bundle.putBoolean(L, z2);
            aC = null;
            com.dji.frame.c.b.a(context, DJIPhotoPreveiwActivity.class, bundle, i);
        }
    }

    public static void a(Context context, String str, boolean z, boolean z2, int i, h hVar) {
        if (str != null && str.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("file_path", str);
            bundle.putBoolean(M, z);
            bundle.putBoolean(D, z2);
            aC = hVar;
            com.dji.frame.c.b.a(context, DJIPhotoPreveiwActivity.class, bundle, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aD = true;
        Intent intent = getIntent();
        if (intent != null) {
            this.af = intent.getStringExtra("file_path");
            this.ag = intent.getBooleanExtra(D, false);
            this.ai = intent.getBooleanExtra(E, false);
            this.ar = intent.getLongExtra("createtime", 0);
            this.as = intent.getLongExtra(G, 0);
            this.at = intent.getIntExtra("index", 0);
            this.au = intent.getLongExtra(I, 0);
            this.av = intent.getIntExtra(J, 0);
            this.aw = intent.getStringExtra(K);
            this.ax = intent.getBooleanExtra(M, false);
            this.ay = intent.getBooleanExtra(N, false);
            B = false;
            d.getInstance().e(false);
            this.aj = intent.getBooleanExtra(L, false);
            this.ak = intent.getBooleanExtra(O, false);
            File file = new File(this.af);
            if (this.af == null || !file.exists()) {
                Log.v("photo", "file not exists!");
                r();
                return;
            }
            setContentView(R.layout.v2_activity_photo_preview);
            DJIOriLayout.setOrientationByDevice(this);
            this.W = (DJITextView) findViewById(R.id.cf0);
            this.W.setVisibility(8);
            this.X = (DJITextView) findViewById(R.id.cdd);
            this.an = dji.pilot.playback.litchi.h.getInstance();
            this.R = (DJITextView) findViewById(R.id.cem);
            this.S = (DJITextView) findViewById(R.id.cen);
            this.T = (DJITextView) findViewById(R.id.ceo);
            this.Q = (DJITextView) findViewById(R.id.cep);
            this.V = (DJITextView) findViewById(R.id.ceq);
            this.U = (DJITextView) findViewById(R.id.cer);
            this.ad = (DJIImageView) findViewById(R.id.cei);
            this.ae = (Button) findViewById(R.id.cf1);
            this.aa = (DJILinearLayout) findViewById(R.id.cel);
            this.aa.hide();
            this.ao = (DJIStateTextView) findViewById(R.id.cew);
            this.ap = (DJIStateTextView) findViewById(R.id.cex);
            this.aq = (RelativeLayout) findViewById(R.id.cev);
            this.al = (RelativeLayout) findViewById(R.id.cey);
            this.ab = (DJIImageView) findViewById(R.id.ces);
            if (this.ax) {
                this.ab.go();
            } else {
                this.ab.show();
            }
            this.ac = (DJIImageView) findViewById(R.id.cej);
            if (this.ag) {
                this.ac.setImageResource(R.drawable.v2_media_select);
            } else {
                this.ac.setImageResource(R.drawable.v2_media_unselect);
            }
            this.Y = (Button) findViewById(R.id.cet);
            this.Y.setOnClickListener(this.aI);
            this.W.setOnClickListener(this.aI);
            this.Z = (DJIStateImageView) findViewById(R.id.ceu);
            this.Z.setVisibility(4);
            View findViewById = findViewById(R.id.cf2);
            findViewById.setOnClickListener(this.aI);
            findViewById.setVisibility(8);
            DJIStateImageView dJIStateImageView = (DJIStateImageView) findViewById(R.id.cez);
            dJIStateImageView.setOnClickListener(this.aI);
            if (this.ax) {
                dJIStateImageView.go();
                this.Y.setVisibility(8);
                this.ae.setVisibility(8);
                this.X.setVisibility(8);
            } else {
                a(DJILibraryPhotoView.C.isOrg, DJILibraryPhotoView.C.orgPath);
                this.W.go();
                if (this.ay) {
                    this.Y.setVisibility(8);
                }
            }
            if (this.ak) {
                this.ac.setVisibility(4);
                this.al.setVisibility(8);
                this.aq.setVisibility(0);
                this.ao.setVisibility(0);
                this.ap.setVisibility(0);
                this.ao.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DJIPhotoPreveiwActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.finish();
                    }
                });
                this.ap.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DJIPhotoPreveiwActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Intent intent = new Intent(this.a, PublishArtworkActivity.class);
                        intent.putExtra(PublishArtworkActivity.a, this.a.af);
                        this.a.startActivity(intent);
                        this.a.setResult(-1);
                        this.a.finish();
                    }
                });
            }
            n();
            Handler handler = new Handler();
            Runnable anonymousClass4 = new Runnable(this) {
                final /* synthetic */ DJIPhotoPreveiwActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.ae.setOnClickListener(this.a.aI);
                    this.a.ad.setOnClickListener(this.a.aI);
                    this.a.findViewById(R.id.ceu).setOnClickListener(this.a.aI);
                    this.a.ab.setOnClickListener(this.a.aI);
                    this.a.ac.setOnClickListener(this.a.aI);
                    this.a.az = new Handler(new Callback(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public boolean handleMessage(Message message) {
                            switch (message.what) {
                                case 1001:
                                    DJIPhotoPreveiwActivity.B = false;
                                    d.getInstance().e(false);
                                    this.a.a.Y.setEnabled(true);
                                    this.a.a.aK.setPagingEnabled(true);
                                    this.a.a.ab.setVisibility(0);
                                    this.a.a.a(DJILibraryPhotoView.C.isOrg, DJILibraryPhotoView.C.orgPath);
                                    break;
                                case 1003:
                                    if (!d.getInstance().i()) {
                                        this.a.a.Y.setEnabled(true);
                                        this.a.a.Y.setText(R.string.v2_photo_preview_download_fail);
                                        DJIPhotoPreveiwActivity.B = false;
                                        this.a.a.aK.setPagingEnabled(true);
                                        this.a.a.q();
                                        break;
                                    }
                                    this.a.a.aB = this.a.a.aB + 1;
                                    if (this.a.a.aB >= 30) {
                                        this.a.a.Y.setEnabled(true);
                                        this.a.a.Y.setText(R.string.v2_photo_preview_download_fail);
                                        DJIPhotoPreveiwActivity.B = false;
                                        this.a.a.aK.setPagingEnabled(true);
                                        this.a.a.aB = 0;
                                        break;
                                    }
                                    this.a.a.az.sendEmptyMessageDelayed(1003, 100);
                                    break;
                            }
                            return false;
                        }
                    });
                }
            };
            this.aA = Toast.makeText(this, R.string.v2_library_download_org, 0);
            handler.postDelayed(anonymousClass4, 100);
            this.aH = new c(this) {
                final /* synthetic */ DJIPhotoPreveiwActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.aM.notifyDataSetChanged();
                }
            };
            MixAlbumSyncManager.getInstance(getApplicationContext()).registerViewPagerListener(this.aH);
            o();
            return;
        }
        Log.v("photo", "Intent is null");
        r();
    }

    private int f() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    protected void onDestroy() {
        p();
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        System.putInt(getContentResolver(), "accelerometer_rotation", 0);
    }

    protected void onPause() {
        super.onPause();
        System.putInt(getContentResolver(), "accelerometer_rotation", 1);
    }

    public void a(Bitmap bitmap) {
    }

    private void a(boolean z, String str) {
        Log.i("zc", "org :" + str);
        if (z) {
            this.Y.setVisibility(8);
            this.ab.setVisibility(0);
            Options a = dji.pilot2.utils.a.a(str);
            this.T.setText(String.format("%dx%d", new Object[]{Integer.valueOf(a.outWidth), Integer.valueOf(a.outHeight)}));
            try {
                e a2;
                e a3;
                e a4;
                e a5;
                e eVar;
                CharSequence charSequence;
                CharSequence charSequence2;
                CharSequence a6;
                i a7 = g.a(new File(str));
                e a8;
                if (a7 instanceof dji.thirdparty.g.b.b.g) {
                    dji.thirdparty.g.b.b.g gVar = (dji.thirdparty.g.b.b.g) a7;
                    a8 = gVar.a(f.ex);
                    a2 = gVar.a(f.dy);
                    a3 = gVar.a(f.aN);
                    a4 = gVar.a(f.dx);
                    a5 = gVar.a(f.eH);
                    eVar = a8;
                } else {
                    dji.thirdparty.g.b.a.b bVar = (dji.thirdparty.g.b.a.b) a7;
                    a8 = bVar.a(f.ex);
                    a2 = bVar.a(f.dy);
                    a3 = bVar.a(f.aN);
                    a4 = bVar.a(f.dx);
                    a5 = bVar.a(f.eH);
                    eVar = a8;
                }
                if (eVar == null) {
                    charSequence = "";
                } else {
                    Object obj = "" + eVar.n();
                }
                if (a2 == null) {
                    charSequence2 = "";
                } else {
                    Object obj2 = "" + a2.o();
                }
                String trim = a3 == null ? "" : a3.j().trim();
                float o = a4 == null ? 0.0f : (float) a4.o();
                String j = a5.j();
                if (j == null) {
                    a6 = dji.pilot2.media.b.a(str);
                    if (a6 == null) {
                        a6 = a(str);
                    }
                } else {
                    a6 = j.replaceFirst(":", "-").replaceFirst(":", "-");
                }
                if (a6 != null) {
                    this.R.setText(a6);
                }
                if (trim != null) {
                    this.S.setText(dji.pilot2.share.f.b.a((Context) this, trim));
                }
                if (charSequence2 != null) {
                    this.Q.setText(charSequence2);
                }
                if (o != 0.0f) {
                    j = "";
                    if (o >= 1.0f) {
                        a6 = j + o;
                    } else {
                        a6 = j + "1/" + Math.round(1.0f / o);
                    }
                    this.V.setText(a6);
                }
                if (charSequence != null) {
                    this.U.setText(charSequence);
                    return;
                }
                return;
            } catch (dji.thirdparty.g.e e) {
                e.printStackTrace();
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.aa.hide();
        this.ab.setImageResource(R.drawable.v2_photo_preview_show_detail);
        this.ab.setVisibility(8);
        this.Y.setText(String.format(getString(R.string.v2_photo_preview_download), new Object[]{""}));
        this.Y.setVisibility(0);
        this.Y.setClickable(true);
    }

    private String a(String str) {
        String name = new File(str).getName();
        if (name != null) {
            String[] split = name.split("_");
            if (split.length == 3) {
                return DateFormat.format("yyyy-MM-dd hh:mm:ss", Long.parseLong(split[2].substring(0, split[2].indexOf(46)))).toString();
            }
        }
        return null;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            a();
        }
        return false;
    }

    public void a() {
        r();
    }

    private void g() {
        Builder bVar = new dji.pilot2.publics.object.b(this);
        bVar.setMessage(R.string.fpv_playback_del_image);
        bVar.setPositiveButton(R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIPhotoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.h();
            }
        });
        bVar.setNegativeButton(R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ DJIPhotoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    private void h() {
        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.PhotoDelete);
        r();
    }

    private void i() {
        if (this.am == null) {
            this.am = b.a((Context) this, (int) R.string.fpv_playback_cancel_download, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPhotoPreveiwActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.a.am != null && this.a.am.isShowing()) {
                        this.a.am.dismiss();
                        this.a.am = null;
                    }
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPhotoPreveiwActivity a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dji.pilot.playback.litchi.h.getInstance().c();
                    if (this.a.am != null && this.a.am.isShowing()) {
                        this.a.am.dismiss();
                        this.a.am = null;
                    }
                    dji.pilot.playback.litchi.h.getInstance().c();
                    DJIPhotoPreveiwActivity.B = false;
                    d.getInstance().e(false);
                    this.a.aK.setPagingEnabled(true);
                    this.a.Z.setVisibility(8);
                    this.a.Y.setEnabled(true);
                }
            });
            this.am.setCancelable(true);
            this.am.setCanceledOnTouchOutside(true);
        }
        if (this.am != null && !this.am.isShowing()) {
            this.am.a((int) R.string.fpv_playback_cancel_download);
            this.am.show();
        }
    }

    private void j() {
        Toast makeText = Toast.makeText(this, R.string.v2_library_06, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private void k() {
        if (d.getInstance().i()) {
            this.Y.setEnabled(false);
            this.Y.setText(R.string.ve_downloading_video);
            B = true;
            this.aK.setPagingEnabled(false);
            this.aB = 0;
            this.az.sendEmptyMessageDelayed(1003, 100);
            return;
        }
        a(this.aA);
        this.aA.setGravity(17, 0, 0);
        this.aA.setDuration(100);
        this.aA.show();
    }

    public void a(Toast toast) {
        switch (d.getInstance().g()) {
            case 0:
                a(0, toast);
                return;
            case 1:
                a((int) R.string.v2_library_syn_pic_error1, toast);
                return;
            case 2:
                a((int) R.string.v2_library_syn_pic_error2, toast);
                return;
            case 3:
                a((int) R.string.v2_library_syn_pic_error3, toast);
                return;
            case 4:
                a((int) R.string.v2_library_syn_pic_error4, toast);
                return;
            case 5:
                a((int) R.string.v2_library_syn_pic_error5, toast);
                return;
            case 6:
                a((int) R.string.v2_library_syn_pic_error6, toast);
                return;
            case 7:
                a((int) R.string.v2_library_syn_pic_error7, toast);
                return;
            case 8:
                a((int) R.string.v2_library_syn_pic_error8, toast);
                return;
            default:
                return;
        }
    }

    private void a(int i, Toast toast) {
        if (i == 0) {
            toast.setText(R.string.v2_library_download_org);
        } else {
            toast.setText(i);
        }
    }

    private int l() {
        MixAlbumSyncManager.getInstance(this).getShowList();
        Log.i("video", "index:" + MixAlbumSyncManager.getInstance(this).isInShowList(DJILibraryPhotoView.C));
        return MixAlbumSyncManager.getInstance(this).isInShowList(DJILibraryPhotoView.C);
    }

    private void m() {
        for (int i = 0; i < this.aJ.length; i++) {
            this.aJ[i] = new TouchImageView(this);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.aJ[i].setLayoutParams(layoutParams);
        }
    }

    private void n() {
        m();
        this.aK = (DJIViewPager) findViewById(R.id.cek);
        this.aM = new a(this);
        this.aK.setAdapter(this.aM);
        this.aK.setTransitionEffect(DJIViewPager.b.Standard);
        this.aK.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ DJIPhotoPreveiwActivity a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                ArrayList showList = MixAlbumSyncManager.getInstance(this.a).getShowList();
                DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) showList.get(i);
                int length;
                if (dJISycAlbumModel.fileLevel != 2) {
                    if (dJISycAlbumModel != null) {
                        if (dJISycAlbumModel.isOrg) {
                            this.a.af = dJISycAlbumModel.mLocalInfo.e;
                        } else {
                            this.a.af = dJISycAlbumModel.mLocalInfo.e;
                        }
                        if (dJISycAlbumModel.fileLevel != 3 || dJISycAlbumModel.isOrg) {
                            this.a.aj = false;
                        } else {
                            this.a.aj = true;
                        }
                        if (DJILibraryPhotoView.B.contains(dJISycAlbumModel)) {
                            this.a.ac.setImageResource(R.drawable.v2_media_select);
                            this.a.ag = true;
                        } else {
                            this.a.ac.setImageResource(R.drawable.v2_media_unselect);
                            this.a.ag = false;
                        }
                        this.a.a(dJISycAlbumModel.isOrg, dJISycAlbumModel.orgPath);
                        DJILibraryPhotoView.setCurrentPreviewAlbum(dJISycAlbumModel);
                        if (dJISycAlbumModel.fileLevel == 5) {
                            this.a.Y.setVisibility(8);
                        }
                        length = i % this.a.aJ.length;
                    }
                    this.a.aL = i;
                } else if (i > this.a.aL) {
                    length = i + 1;
                    if (length >= showList.size()) {
                        this.a.aK.setCurrentItem(this.a.aL);
                        return;
                    }
                    r2 = length - 1;
                    this.a.aK.setCurrentItem(length);
                } else {
                    length = i - 1;
                    if (length < 0) {
                        this.a.aK.setCurrentItem(this.a.aL);
                        return;
                    }
                    r2 = length + 1;
                    this.a.aK.setCurrentItem(length);
                }
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.aK.setCurrentItem(l());
    }

    private void a(ArrayList<DJISycAlbumModel> arrayList, ImageView imageView, int i) {
        String str;
        DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) arrayList.get(i);
        if (dJISycAlbumModel.isOrg) {
            str = dJISycAlbumModel.mLocalInfo.e;
        } else {
            str = dJISycAlbumModel.mLocalInfo.e;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.af, options);
        if (options.outWidth > 1000) {
            options.inSampleSize = options.outWidth / 1000;
            if (options.inSampleSize > 8) {
                options.inSampleSize = 8;
            } else if (options.inSampleSize < 1) {
                options.inSampleSize = 1;
            }
        } else {
            options.inSampleSize = 1;
        }
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                Bitmap a = dji.pilot2.utils.a.a(decodeFile, new ExifInterface(str));
                if (decodeFile != a) {
                    decodeFile.recycle();
                } else {
                    a = decodeFile;
                }
                imageView.setImageBitmap(a);
            }
        } catch (OutOfMemoryError e) {
            Toast.makeText(this, getString(R.string.v2_out_of_memory_info), 1).show();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private ImageView b(String str) {
        if (str == null) {
            return null;
        }
        ImageView imageView = new ImageView(this);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.af, options);
        if (options.outWidth > 1000) {
            options.inSampleSize = options.outWidth / 1000;
            if (options.inSampleSize > 8) {
                options.inSampleSize = 8;
            } else if (options.inSampleSize < 1) {
                options.inSampleSize = 1;
            }
        } else {
            options.inSampleSize = 1;
        }
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile == null) {
                return imageView;
            }
            Bitmap a = dji.pilot2.utils.a.a(decodeFile, new ExifInterface(str));
            if (decodeFile != a) {
                decodeFile.recycle();
            } else {
                a = decodeFile;
            }
            imageView.setImageBitmap(a);
            return imageView;
        } catch (OutOfMemoryError e) {
            Toast.makeText(this, getString(R.string.v2_out_of_memory_info), 1).show();
            return imageView;
        } catch (IOException e2) {
            e2.printStackTrace();
            return imageView;
        }
    }

    private void o() {
        dji.thirdparty.a.c.a().a(this);
    }

    private void p() {
        dji.thirdparty.a.c.a().d(this);
    }

    private void q() {
        if (!d.getInstance().d()) {
            k();
        } else if (DJILibraryPhotoView.C.fileLevel != 3) {
            j();
        } else if (DJILibraryPhotoView.C.mRemoteInfo.j.ordinal() != dji.logic.album.a.b.f.b.ordinal()) {
            B = true;
            d.getInstance().e(true);
            this.aK.setPagingEnabled(false);
            this.Z.setVisibility(0);
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.b = DJILibraryPhotoView.C.mRemoteInfo.b;
            dJIAlbumFileInfo.c = DJILibraryPhotoView.C.mRemoteInfo.c;
            dJIAlbumFileInfo.j = dji.logic.album.a.b.f.find(0);
            dJIAlbumFileInfo.d = DJILibraryPhotoView.C.mRemoteInfo.d;
            dJIAlbumFileInfo.a = DJILibraryPhotoView.C.mRemoteInfo.a;
            dJIAlbumFileInfo.k = DJILibraryPhotoView.C.mRemoteInfo.k;
            dJIAlbumFileInfo.l = DJILibraryPhotoView.C.mRemoteInfo.l;
            this.an.a(getApplicationContext(), this.Y, this.Z, this, null, this.az, dJIAlbumFileInfo);
            this.Y.setEnabled(false);
            dji.pilot.fpv.d.e.b(j.b);
        } else {
            Toast makeText = Toast.makeText(this, R.string.v2_library_23, 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        }
    }

    private void r() {
        d.getInstance().d(false);
        d.getInstance().e(false);
        if (aC != null) {
            Log.i("zhang", "finish view");
            aC.a(this.ag);
        }
        finish();
        aC = null;
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    protected void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }
}
