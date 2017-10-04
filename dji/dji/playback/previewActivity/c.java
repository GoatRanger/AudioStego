package dji.playback.previewActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.media.activity.DJIPhotoEditorActivity;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.playback.entryActivity.d;
import dji.playback.entryActivity.e;
import dji.playback.entryActivity.h;
import dji.playback.previewActivity.b.a;
import dji.playback.previewActivity.b.b;
import dji.playback.previewActivity.widget.DJISliderbar;
import dji.playback.previewActivity.widget.HackyViewPager;
import dji.publics.DJIUI.DJITextView;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class c extends Fragment implements b {
    private static final int K = 120;
    public static int a;
    public static int b;
    private SurfaceHolder A;
    private boolean B = false;
    private boolean C = false;
    private AudioManager D;
    private Timer E;
    private TimerTask F;
    private View G;
    private boolean H = true;
    private boolean I = true;
    private final Handler J = new Handler();
    private int L = 0;
    private final Runnable M = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        @SuppressLint({"InlinedApi"})
        public void run() {
            if (this.a.G != null) {
                int i;
                if (VERSION.SDK_INT >= 19) {
                    i = 5895;
                } else {
                    i = 1799;
                }
                this.a.G.setSystemUiVisibility(i);
            }
        }
    };
    WakeLock c;
    private a d;
    private int e = 0;
    private List<h> f;
    private HackyViewPager g;
    private d h;
    private ScaleAnimation i;
    private ScaleAnimation j;
    private ScaleAnimation k;
    private ScaleAnimation l;
    private RelativeLayout m;
    private RelativeLayout n;
    private DJIStateImageView o;
    private DJITextView p;
    private DJIStateTextView q;
    private LinearLayout r;
    private DJIStateImageView s;
    private DJIStateImageView t;
    private DJIStateImageView u;
    private DJISliderbar v;
    private DJIStateImageView w;
    private DJIStateImageView x;
    private MediaPlayer y = new MediaPlayer();
    private String z;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ArrayList parcelableArrayList = arguments.getParcelableArrayList("data");
            this.e = arguments.getInt("index");
            if (parcelableArrayList != null) {
                this.f = (List) parcelableArrayList.get(0);
                DJILogHelper.getInstance().LOGI("bob", "DJIPlaybackPreviewFragment size = " + this.f.size() + " index= " + this.e);
            }
        }
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (getResources().getConfiguration().orientation == 2) {
                int i;
                a = displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
                if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
                    i = displayMetrics.widthPixels;
                } else {
                    i = displayMetrics.heightPixels;
                }
                b = i;
            } else {
                a = displayMetrics.widthPixels;
                b = displayMetrics.widthPixels;
            }
        } else {
            Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (getResources().getConfiguration().orientation == 2) {
                a = point.x > point.y ? point.x : point.y;
                b = point.y > point.x ? point.x : point.y;
            } else {
                a = point.x;
                b = point.y;
            }
        }
        e();
        DJILogHelper.getInstance().LOGI("bob", "mScreenWidth = " + a + " mScreenHeight=" + b);
    }

    private String a(String str) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM dd,yyyy", Locale.ENGLISH);
        }
        return simpleDateFormat.format(date);
    }

    private void a(View view) {
        this.m = (RelativeLayout) view.findViewById(R.id.cxt);
        this.o = (DJIStateImageView) view.findViewById(R.id.ago);
        this.p = (DJITextView) view.findViewById(R.id.agp);
        this.q = (DJIStateTextView) view.findViewById(R.id.agq);
        if (this.f != null) {
            this.p.setText(a(((h) this.f.get(this.e)).e));
        }
        this.q.setText(getString(R.string.share));
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                h hVar = (h) this.a.f.get(this.a.e);
                if (hVar.h == d.b.Type_IMG) {
                    Intent intent = new Intent();
                    intent.setClass(this.a.getActivity(), DJIPhotoEditorActivity.class);
                    intent.putExtra("key_filepath", hVar.c);
                    this.a.startActivity(intent);
                    return;
                }
                String[] strArr = new String[1];
                int[] iArr = new int[]{hVar.c};
                iArr[0] = hVar.d;
                Intent intent2 = new Intent(this.a.getActivity(), DJIMultiMomentEditActivity.class);
                intent2.putExtra(DJIMultiMomentEditActivity.M, strArr);
                intent2.putExtra("duration", iArr);
                this.a.startActivity(intent2);
            }
        });
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.getActivity().finish();
            }
        });
    }

    private void a(h hVar) {
        if (hVar.h == d.b.Type_IMG) {
            this.r.setVisibility(0);
            this.w.setVisibility(0);
            this.t.setVisibility(4);
            this.s.setVisibility(4);
            this.u.setVisibility(4);
            return;
        }
        this.r.setVisibility(0);
        this.w.setVisibility(8);
        this.t.setVisibility(0);
        this.s.setVisibility(0);
        this.u.setVisibility(0);
    }

    private void b(View view) {
        this.D = (AudioManager) getActivity().getSystemService("audio");
        this.D.getStreamMaxVolume(1);
        this.n = (RelativeLayout) view.findViewById(R.id.gg);
        this.r = (LinearLayout) view.findViewById(R.id.ah7);
        this.s = (DJIStateImageView) view.findViewById(R.id.ah8);
        this.t = (DJIStateImageView) view.findViewById(R.id.ah9);
        this.u = (DJIStateImageView) view.findViewById(R.id.ah_);
        this.w = (DJIStateImageView) view.findViewById(R.id.ahc);
        this.x = (DJIStateImageView) view.findViewById(R.id.ahb);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                a.a aVar;
                h hVar = (h) this.a.f.get(this.a.e);
                a.a aVar2 = new a.a();
                try {
                    ExifInterface exifInterface = new ExifInterface(hVar.c);
                    DJILogHelper.getInstance().LOGI("bob", "TAG_ISO = " + exifInterface.getAttribute("ISOSpeedRatings") + " TAG_APERTURE =" + exifInterface.getAttribute("FNumber") + "TAG_DATETIME=" + exifInterface.getAttribute("DateTime") + "TAG_FLASH=" + exifInterface.getAttribute("Flash") + "TAG_ORIENTATION" + exifInterface.getAttribute(dji.sdksharedlib.b.b.bW) + "TAG_MAKE =" + exifInterface.getAttribute("Make") + "TAG_MODEL =" + exifInterface.getAttribute(dji.pilot2.utils.a.a.m) + "TAG_IMAGE_WIDTH =" + exifInterface.getAttribute("ImageWidth") + "TAG_IMAGE_LENGTH =" + exifInterface.getAttribute("ImageLength") + " TAG_EXPOSURE_TIME=" + exifInterface.getAttribute("ExposureTime") + " TAG_DATETIME_DIGITIZED=" + exifInterface.getAttribute("DateTimeDigitized") + " TAG_SUBSEC_TIME=" + exifInterface.getAttribute("SubSecTime") + " TAG_SUBSEC_TIME_ORIG=" + exifInterface.getAttribute("SubSecTimeOriginal") + " TAG_SUBSEC_TIME_DIG=" + exifInterface.getAttribute("SubSecTimeDigitized"));
                    aVar2.a(exifInterface.getAttribute("ISOSpeedRatings"));
                    aVar2.c(exifInterface.getAttribute("FNumber"));
                    aVar2.f(exifInterface.getAttribute("DateTime"));
                    aVar2.e(exifInterface.getAttribute(dji.pilot2.utils.a.a.m));
                    aVar2.b(exifInterface.getAttribute("ExposureTime"));
                    aVar2.g(hVar.c);
                    DJILogHelper.getInstance().LOGI("bob", "width =   " + hVar.j + " height=" + hVar.i);
                    aVar2.d(exifInterface.getAttribute("ImageWidth") + "X" + exifInterface.getAttribute("ImageLength"));
                    aVar = aVar2;
                } catch (IOException e) {
                    e.printStackTrace();
                    aVar = null;
                }
                if (aVar != null) {
                    a.a(this.a.getActivity(), aVar, new OnDismissListener(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                            this.a.a.M.run();
                        }
                    });
                }
            }
        });
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                dji.playback.entryActivity.c.a(this.a.getActivity(), ((h) this.a.f.get(this.a.e)).h, new dji.playback.entryActivity.c.a(this) {
                    final /* synthetic */ AnonymousClass11 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        DJILogHelper.getInstance().LOGI("bob", "onOkClicked");
                        if (this.a.a.y != null) {
                            this.a.a.y.stop();
                            this.a.a.y.release();
                            this.a.a.y = null;
                            if (this.a.a.E != null) {
                                this.a.a.E.cancel();
                                this.a.a.E = null;
                            }
                        }
                        int size = this.a.a.f.size();
                        h hVar = (h) this.a.a.f.remove(this.a.a.e);
                        if (hVar.h.equals(d.b.Type_IMG)) {
                            dji.thirdparty.a.c.a().e(e.a.EventHGPhotoDel);
                        } else if (hVar.h.equals(d.b.Type_VIDEO)) {
                            dji.thirdparty.a.c.a().e(new e.b());
                        }
                        dji.pilot.storage.a.b(this.a.a.getActivity(), hVar.c);
                        if (size <= 1) {
                            this.a.a.getActivity().finish();
                            return;
                        }
                        int currentItem = this.a.a.g.getCurrentItem();
                        if (currentItem >= size - 1) {
                            currentItem--;
                        }
                        this.a.a.a(currentItem);
                        this.a.a.a();
                        this.a.a.g.setAdapter(this.a.a.h);
                        this.a.a.g.setCurrentItem(currentItem);
                        this.a.a.g.setPagingEnabled(true);
                        Activity activity = this.a.a.getActivity();
                        if (activity != null) {
                            activity.setResult(1);
                        }
                    }

                    public void b() {
                        DJILogHelper.getInstance().LOGI("bob", "OnCancelClicked");
                    }

                    public void c() {
                        this.a.a.M.run();
                    }
                });
                View b = this.a.h.b();
                if (b != null) {
                    Object tag = b.getTag();
                    if (tag != null && (tag instanceof d.a)) {
                        d.a aVar = (d.a) tag;
                        if (this.a.y != null && this.a.y.isPlaying()) {
                            this.a.y.pause();
                            this.a.g.setPagingEnabled(true);
                            aVar.c();
                            this.a.c(0);
                        }
                    }
                }
            }
        });
        if (this.f != null && this.f.size() > this.e) {
            a((h) this.f.get(this.e));
        }
        this.s.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DJILogHelper.getInstance().LOGI("bob", "pre page");
                if (this.a.g.getCurrentItem() <= 0) {
                    Activity activity = this.a.getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass12 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.getActivity(), R.string.v2_hg_toastpre, 0).show();
                            }
                        });
                    }
                } else if (this.a.g != null) {
                    View b = this.a.h.b();
                    if (b != null) {
                        Object tag = b.getTag();
                        if (tag != null && (tag instanceof d.a)) {
                            d.a aVar = (d.a) tag;
                            if (aVar.e == d.b.Type_VIDEO && this.a.y != null && this.a.y.isPlaying()) {
                                aVar.c();
                                this.a.d();
                                this.a.g.setPagingEnabled(true);
                            }
                        }
                    }
                    this.a.g.setCurrentItem(this.a.g.getCurrentItem() - 1, true);
                }
            }
        });
        this.u.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DJILogHelper.getInstance().LOGI("bob", "next page");
                if (this.a.g.getCurrentItem() >= this.a.h.getCount() - 1) {
                    Activity activity = this.a.getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.getActivity(), R.string.v2_hg_toastnext, 0).show();
                            }
                        });
                    }
                } else if (this.a.g != null) {
                    View b = this.a.h.b();
                    if (b != null) {
                        Object tag = b.getTag();
                        if (tag != null && (tag instanceof d.a)) {
                            d.a aVar = (d.a) tag;
                            if (aVar.e == d.b.Type_VIDEO && this.a.y != null && this.a.y.isPlaying()) {
                                aVar.c();
                                this.a.d();
                                this.a.g.setPagingEnabled(true);
                            }
                        }
                    }
                    this.a.g.setCurrentItem(this.a.g.getCurrentItem() + 1, true);
                }
            }
        });
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DJILogHelper.getInstance().LOGI("bob", "bottom click mIndex=" + this.a.e);
                View b = this.a.h.b();
                if (b != null) {
                    Object tag = b.getTag();
                    if (tag == null || !(tag instanceof d.a)) {
                        DJILogHelper.getInstance().LOGI("bob", "bottom click o = null || o !instanceof DJIPlaybackPreviewPagerAdapter.ConfigureHolder");
                        return;
                    } else if (this.a.H) {
                        d.a aVar = (d.a) tag;
                        if (!aVar.a()) {
                            DJILogHelper.getInstance().LOGI("bob", "bottom click !holder.isSurfaceInit()");
                            h hVar = (h) this.a.f.get(this.a.e);
                            aVar.a(hVar);
                            this.a.z = hVar.c;
                            this.a.g.setPagingEnabled(false);
                            if (aVar.l != null) {
                                this.a.B = true;
                                this.a.A = aVar.l;
                                this.a.c();
                                this.a.b(0);
                                return;
                            }
                            return;
                        } else if (this.a.z == null) {
                            DJILogHelper.getInstance().LOGI("bob", "bottom click mPath==null mIsSurfaceCreated=" + this.a.B);
                            this.a.z = ((h) this.a.f.get(this.a.e)).c;
                            this.a.g.setPagingEnabled(false);
                            if (this.a.B) {
                                this.a.c();
                                this.a.b(0);
                                return;
                            }
                            DJILogHelper.getInstance().LOGI("bob", "bottom click mPath==null holder.mHolder= " + aVar.l);
                            if (aVar.l != null) {
                                this.a.B = true;
                                this.a.A = aVar.l;
                                this.a.c();
                                aVar.d();
                                this.a.g.setPagingEnabled(false);
                                this.a.b(0);
                                return;
                            }
                            return;
                        } else {
                            DJILogHelper.getInstance().LOGI("bob", "bottom click mPath!=null  mMediaPlayer= " + this.a.y);
                            if (this.a.y == null) {
                                return;
                            }
                            if (this.a.y.isPlaying()) {
                                DJILogHelper.getInstance().LOGI("bob", "bottom click isPlaying mMediaPlayer.isPlaying()=" + this.a.y.isPlaying());
                                this.a.y.pause();
                                this.a.g.setPagingEnabled(true);
                                aVar.c();
                                this.a.c(0);
                                return;
                            }
                            DJILogHelper.getInstance().LOGI("bob", "bottom click pause mMediaPlayer.isPlaying()=" + this.a.y.isPlaying());
                            this.a.y.start();
                            aVar.d();
                            this.a.g.setPagingEnabled(false);
                            this.a.b(0);
                            return;
                        }
                    } else {
                        h hVar2 = (h) this.a.f.get(this.a.e);
                        Intent intent = new Intent(this.a.getContext(), DJIPlaybackVideoActivity.class);
                        intent.putExtra("title", hVar2.e);
                        intent.putExtra("duration", hVar2.d);
                        intent.putExtra("path", hVar2.c);
                        intent.putExtra("width", hVar2.j);
                        intent.putExtra("height", hVar2.i);
                        this.a.startActivity(intent);
                        return;
                    }
                }
                DJILogHelper.getInstance().LOGI("bob", "bottom click v = null");
            }
        });
    }

    private void a() {
        this.h = new d(this.f, this);
        this.h.a(new d.b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(h hVar, int i, int i2) {
                DJILogHelper.getInstance().LOGI("bob", "onVideoImageClicked mIsPlayInited=");
                if (this.a.H) {
                    View b = this.a.h.b();
                    if (b != null) {
                        Object tag = b.getTag();
                        if (tag == null || !(tag instanceof d.a)) {
                            DJILogHelper.getInstance().LOGI("bob", "onVideoImageClicked bottom click o = null || o !instanceof DJIPlaybackPreviewPagerAdapter.ConfigureHolder");
                            return;
                        }
                        d.a aVar = (d.a) tag;
                        if (this.a.z == null) {
                            this.a.z = hVar.c;
                            this.a.g.setPagingEnabled(false);
                            if (this.a.B) {
                                if (this.a.z != null) {
                                    this.a.c();
                                    this.a.b(i2);
                                    return;
                                }
                                return;
                            } else if (aVar.l != null) {
                                this.a.B = true;
                                this.a.A = aVar.l;
                                this.a.c();
                                this.a.b(i2);
                                return;
                            } else {
                                return;
                            }
                        } else if (this.a.y != null) {
                            if (this.a.y.isPlaying()) {
                                this.a.c(i2);
                                return;
                            }
                            DJILogHelper.getInstance().LOGI("bob", "onVideoImageClicked " + this.a.y.isPlaying());
                            if (i2 == 0) {
                                this.a.y.start();
                                this.a.g.setPagingEnabled(false);
                                this.a.b(i2);
                                return;
                            }
                            return;
                        } else if (aVar.l != null) {
                            this.a.B = true;
                            this.a.A = aVar.l;
                            this.a.c();
                            this.a.b(i2);
                            return;
                        } else {
                            return;
                        }
                    }
                    DJILogHelper.getInstance().LOGI("bob", "onVideoImageClicked bottom click v = null");
                    return;
                }
                Intent intent = new Intent(this.a.getContext(), DJIPlaybackVideoActivity.class);
                intent.putExtra("title", hVar.e);
                intent.putExtra("duration", hVar.d);
                intent.putExtra("path", hVar.c);
                intent.putExtra("width", hVar.j);
                intent.putExtra("height", hVar.i);
                this.a.startActivity(intent);
            }

            public void a(h hVar, int i) {
                DJILogHelper.getInstance().LOGI("bob", "onPhotoImageClicked");
                if (this.a.m.getVisibility() == 0) {
                    this.a.m.clearAnimation();
                    this.a.m.startAnimation(this.a.i);
                    this.a.n.clearAnimation();
                    this.a.n.startAnimation(this.a.k);
                    this.a.e();
                    return;
                }
                this.a.m.clearAnimation();
                this.a.m.startAnimation(this.a.j);
                this.a.n.clearAnimation();
                this.a.n.startAnimation(this.a.l);
                this.a.f();
            }

            public void a(SurfaceHolder surfaceHolder, int i) {
                DJILogHelper.getInstance().LOGI("bob", "listener surfaceCreated position=" + i + " mIndex=" + this.a.e);
                if (this.a.e == i) {
                    this.a.A = surfaceHolder;
                    this.a.B = true;
                    if (this.a.y != null && this.a.z != null) {
                        this.a.c();
                        this.a.b(0);
                        return;
                    }
                    return;
                }
                DJILogHelper.getInstance().LOGI("bob", "surfacecreated error");
            }

            public void a(SurfaceHolder surfaceHolder, int i, int i2, int i3, int i4) {
                DJILogHelper.getInstance().LOGI("bob", "listener surfaceChanged position=" + i4 + " mIndex=" + this.a.e);
                if (this.a.e != i4) {
                    DJILogHelper.getInstance().LOGI("bob", "surfaceChanged error");
                }
            }

            public void b(SurfaceHolder surfaceHolder, int i) {
                DJILogHelper.getInstance().LOGI("bob", "listener surfaceDestroyed position=" + i + " mIndex=" + this.a.e);
                if (this.a.e == i) {
                    this.a.B = false;
                    if (this.a.y != null) {
                        this.a.y.stop();
                        this.a.y.release();
                        this.a.y = null;
                        this.a.E.cancel();
                        this.a.E = null;
                        return;
                    }
                    return;
                }
                DJILogHelper.getInstance().LOGI("bob", "surfaceDestroyed else");
            }

            public void a(int i, int i2, int i3, int i4) {
                DJILogHelper.getInstance().LOGI("bob", "onScrollChanged scrollX=" + i);
                if (this.a.y != null && !this.a.y.isPlaying() && this.a.h != null) {
                    Object tag = this.a.h.b().getTag();
                    if (tag instanceof d.a) {
                        d.a aVar = (d.a) tag;
                        int scrollX = (int) ((((long) aVar.g.getScrollX()) * ((long) aVar.f.d)) / ((long) aVar.g.getTotalWidth()));
                        DJILogHelper.getInstance().LOGI("bob", "onScrollChanged seekD = " + scrollX);
                        this.a.y.seekTo(scrollX);
                    }
                }
            }

            public void a(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onActionMove scrollX=" + i);
            }

            public void b(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onActionUp scrollX=" + i);
            }

            public void c(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onActionDown scrollX=" + i);
                if (this.a.y != null && this.a.y.isPlaying()) {
                    this.a.y.pause();
                    this.a.c(0);
                    Object tag = this.a.h.b().getTag();
                    if (tag instanceof d.a) {
                        ((d.a) tag).c();
                    }
                }
            }
        });
    }

    private void a(int i) {
        h hVar = (h) this.f.get(i);
        this.e = i;
        if (hVar.h == d.b.Type_VIDEO) {
            this.t.setVisibility(0);
            this.w.setVisibility(4);
            this.s.setVisibility(0);
            this.u.setVisibility(0);
        } else {
            this.w.setVisibility(0);
            this.t.setVisibility(4);
            this.s.setVisibility(4);
            this.u.setVisibility(4);
        }
        this.z = null;
        this.A = null;
        this.B = false;
    }

    private void c(View view) {
        this.g = (HackyViewPager) view.findViewById(R.id.ah6);
        a();
        this.g.setAdapter(this.h);
        this.g.setCurrentItem(this.e);
        this.g.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                DJILogHelper.getInstance().LOGI("bob", " onPageScrolled positon=" + i);
            }

            public void onPageSelected(int i) {
                DJILogHelper.getInstance().LOGI("bob", "onPageSelected position");
                if (i < this.a.f.size()) {
                    this.a.a(i);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void b(int i) {
        if (i != 1) {
            this.t.setImageResource(R.drawable.v2_playbackpreview_pause);
        } else if (this.m.getVisibility() == 0) {
            this.m.clearAnimation();
            this.m.startAnimation(this.i);
            this.n.clearAnimation();
            this.n.startAnimation(this.k);
            this.r.setVisibility(8);
            e();
        } else {
            this.m.clearAnimation();
            this.m.startAnimation(this.j);
            this.n.clearAnimation();
            this.n.startAnimation(this.l);
            this.r.setVisibility(0);
            f();
        }
    }

    private void c(int i) {
        if (i == 0) {
            if (this.m.getVisibility() == 4) {
                this.m.clearAnimation();
                this.m.startAnimation(this.j);
                this.n.clearAnimation();
                this.n.startAnimation(this.l);
                this.r.setVisibility(0);
                f();
            }
            this.t.setImageResource(R.drawable.v2_playbackpreview_playsmall);
        } else if (this.m.getVisibility() == 0) {
            this.m.clearAnimation();
            this.m.startAnimation(this.i);
            this.n.clearAnimation();
            this.n.startAnimation(this.k);
            this.r.setVisibility(8);
            e();
        } else {
            this.r.setVisibility(0);
            this.m.clearAnimation();
            this.m.startAnimation(this.j);
            this.n.clearAnimation();
            this.n.startAnimation(this.l);
            f();
        }
    }

    private void b() {
        this.i = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1, dji.pilot.visual.a.d.c, 1, 0.0f);
        this.i.setDuration(120);
        this.i.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.m.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.j = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, dji.pilot.visual.a.d.c, 1, 0.0f);
        this.j.setDuration(120);
        this.j.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.m.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.k = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1, dji.pilot.visual.a.d.c, 1, 1.0f);
        this.k.setDuration(120);
        this.k.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.n.setVisibility(4);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.l = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, dji.pilot.visual.a.d.c, 1, 1.0f);
        this.l.setDuration(120);
        this.l.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.a.n.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void d(View view) {
        a(view);
        b(view);
        c(view);
        b();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (getResources().getConfiguration().orientation == 2) {
            inflate = layoutInflater.inflate(R.layout.v2_playbackpreview_fragment_land, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.v2_playbackpreview_fragment, viewGroup, false);
        }
        d(inflate);
        this.G = inflate;
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.M.run();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.D.getStreamVolume(1);
        f();
        this.c = ((PowerManager) getActivity().getSystemService("power")).newWakeLock(536870922, "bob");
        this.c.acquire();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onPause() {
        super.onPause();
        if (this.c != null) {
            this.c.release();
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    private void c() {
        if (this.y == null) {
            this.y = new MediaPlayer();
        }
        if (this.y.isPlaying()) {
            this.y.stop();
        }
        this.y.reset();
        this.y.setDisplay(this.A);
        try {
            this.y.setDataSource(this.z);
            this.y.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            DJILogHelper.getInstance().LOGI("bob", " setDataSource prepare exception");
        }
        this.y.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (this.a.h != null) {
                    Object tag = this.a.h.b().getTag();
                    if (tag instanceof d.a) {
                        d.a aVar = (d.a) tag;
                        ((d.a) tag).e();
                    }
                }
                this.a.B = false;
                this.a.z = null;
                this.a.A = null;
                this.a.r.setVisibility(0);
                this.a.g.setPagingEnabled(true);
                if (this.a.y != null) {
                    this.a.y.stop();
                    this.a.y.release();
                    this.a.y = null;
                }
                this.a.c(0);
                if (this.a.E != null) {
                    this.a.E.cancel();
                    this.a.E = null;
                }
            }
        });
        this.E = new Timer("progress");
        this.E.schedule(new TimerTask(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                Activity activity = this.a.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            Object tag = this.a.a.h.b().getTag();
                            if ((tag instanceof d.a) && this.a.a.y != null && this.a.a.y.isPlaying()) {
                                ((d.a) tag).a(this.a.a.y.getCurrentPosition(), this.a.a.y.getDuration());
                            }
                        }
                    });
                }
            }
        }, 0, 50);
        this.y.start();
    }

    private void d() {
        if (this.y != null && this.y.isPlaying()) {
            this.y.stop();
            this.y.release();
            this.y = null;
            this.E.cancel();
            this.E = null;
        }
    }

    private void e() {
        if (this.I) {
            this.I = false;
            this.J.postDelayed(this.M, 120);
        }
    }

    @SuppressLint({"InlinedApi"})
    private void f() {
        this.J.removeCallbacks(this.M);
        if (!this.I) {
            int i;
            if (VERSION.SDK_INT >= 19) {
                i = 3332;
            } else {
                i = 1284;
            }
            if (this.G != null) {
                this.G.setSystemUiVisibility(i);
            }
            this.I = true;
        }
    }

    private int g() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
