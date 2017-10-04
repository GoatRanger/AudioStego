package dji.pilot2.multimoment.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.k;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.videolib.VideoLibWrapper;
import dji.publics.DJIUI.DJIImageView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class HorizonalSegmentView extends HorizontalScrollView implements k {
    public static final int K = 2;
    public static final int L = 2;
    public static final int M = 4;
    public static final int N = 6000;
    public static final int O = 2000;
    public static final int P = 2000;
    public static int Q = 0;
    public static int R = 0;
    private static int U = -1;
    private static int V = -2;
    private static int W = -3;
    private static final int aG = 4096;
    private static int aa = -4;
    int S = 0;
    Boolean T = Boolean.valueOf(false);
    private OnDragListener aA = new OnDragListener(this) {
        final /* synthetic */ HorizonalSegmentView a;

        {
            this.a = r1;
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {
                case 1:
                    this.a.ak.a();
                    break;
                case 3:
                    if (view.getId() == R.id.ca2 || view.getId() == R.id.ca1) {
                        this.a.ae.d(this.a.at - 1);
                        this.a.deleteItemAt(this.a.at);
                        this.a.getInnerChildAt(this.a.at).setVisibility(0);
                        this.a.ak.a(Boolean.valueOf(true), this.a.at);
                        this.a.av = Boolean.valueOf(true);
                        this.a.ax = Boolean.valueOf(true);
                        break;
                    }
                case 4:
                    if (view.getId() == R.id.ca2 || view.getId() == R.id.ca1) {
                        this.a.ai.setBackground(this.a.getResources().getDrawable(R.drawable.drag_delete_ball_shape));
                        this.a.ah.setVisibility(4);
                        if (!this.a.av.booleanValue()) {
                            ((DJIMultiMomentEditActivity) this.a.ab).a(0);
                            break;
                        }
                    }
                    break;
                case 5:
                    if (view.getId() == R.id.ca2 || view.getId() == R.id.ca1) {
                        this.a.ai.setBackground(this.a.getResources().getDrawable(R.drawable.drag_red_delete_ball_shape));
                        break;
                    }
                case 6:
                    if (view.getId() == R.id.ca2 || view.getId() == R.id.ca1) {
                        this.a.ai.setBackground(this.a.getResources().getDrawable(R.drawable.drag_delete_ball_shape));
                        break;
                    }
            }
            return true;
        }
    };
    private OnDragListener aB = new OnDragListener(this) {
        final /* synthetic */ HorizonalSegmentView a;

        {
            this.a = r1;
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (intValue != HorizonalSegmentView.V && intValue != HorizonalSegmentView.U && intValue != HorizonalSegmentView.aa && intValue != HorizonalSegmentView.W) {
                intValue++;
                if (this.a.ae.r() > intValue - 1) {
                    switch (dragEvent.getAction()) {
                        case 1:
                            this.a.ax = Boolean.valueOf(false);
                            this.a.av = Boolean.valueOf(false);
                            this.a.aw = Boolean.valueOf(false);
                            if (intValue == this.a.as) {
                                this.a.l();
                                view.setVisibility(4);
                                this.a.scrollBy((view.getLeft() - this.a.getScrollX()) - (this.a.getWidth() / 2), 0);
                                break;
                            }
                            break;
                        case 2:
                            int x = (int) (dragEvent.getX() + ((float) (this.a.getSegViewWidth(HorizonalSegmentView.N) / 2)));
                            int x2 = (int) (dragEvent.getX() - ((float) (this.a.getSegViewWidth(HorizonalSegmentView.N) / 2)));
                            if (Math.abs(((view.getLeft() + x) - this.a.getScrollX()) - this.a.getScreenWidth()) <= HorizonalSegmentView.R) {
                                this.a.smoothScrollBy(HorizonalSegmentView.R, 0);
                            }
                            if (Math.abs((x2 + view.getLeft()) - this.a.getScrollX()) <= HorizonalSegmentView.R) {
                                this.a.smoothScrollBy(-HorizonalSegmentView.R, 0);
                            }
                            if (intValue != this.a.as && Math.abs(x - view.getWidth()) <= HorizonalSegmentView.R) {
                                view.setVisibility(4);
                                this.a.a(this.a.as, intValue);
                                this.a.av = Boolean.valueOf(true);
                                this.a.getInnerChildAt(this.a.as).setVisibility(0);
                                this.a.as = intValue;
                                break;
                            }
                        case 3:
                            DJILogHelper.getInstance().LOGI(dji.publics.b.a.b.m, "before onCreatingDragEndCallBack");
                            this.a.ak.a(this.a.av, -1);
                            DJILogHelper.getInstance().LOGI(dji.publics.b.a.b.m, "2after onCreatingDragEndCallBack");
                            view.setAlpha(1.0f);
                            this.a.getInnerChildAt(this.a.at).setVisibility(0);
                            this.a.ax = Boolean.valueOf(true);
                            break;
                        case 4:
                            view.setVisibility(0);
                            this.a.ah.setVisibility(4);
                            if (!this.a.av.booleanValue()) {
                                this.a.av = Boolean.valueOf(false);
                                ((DJIMultiMomentEditActivity) this.a.ab).a(0);
                            }
                            if (!this.a.ax.booleanValue()) {
                                this.a.ak.a(this.a.av, -1);
                                this.a.ax = Boolean.valueOf(true);
                            }
                            if (!this.a.aw.booleanValue()) {
                                this.a.aw = Boolean.valueOf(true);
                                this.a.m();
                                break;
                            }
                            break;
                        case 5:
                            break;
                        case 6:
                            if (intValue != this.a.as) {
                                view.setVisibility(0);
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            switch (dragEvent.getAction()) {
                case 3:
                    DJILogHelper.getInstance().LOGI("bob", "before onCreatingDragEndCallBack");
                    this.a.ak.a(Boolean.valueOf(false), -1);
                    DJILogHelper.getInstance().LOGI("bob", "2after onCreatingDragEndCallBack");
                    this.a.getInnerChildAt(this.a.at).setVisibility(0);
                    break;
            }
            return true;
        }
    };
    private b aC = null;
    private a aD = null;
    private f aE = null;
    private final HashMap<String, WeakReference<ImageView>> aF = new HashMap(20);
    private Context ab;
    private LayoutInflater ac;
    private LinearLayout ad;
    private dji.pilot2.multimoment.videolib.c ae;
    private int af;
    private int ag;
    private RelativeLayout ah;
    private ImageView ai;
    private int aj = -1;
    private d ak;
    private h al;
    private int am;
    private GestureDetector an;
    private int ao;
    private int ap;
    private boolean aq = false;
    private float ar = 0.0f;
    private int as;
    private int at;
    private View au = null;
    private Boolean av = Boolean.valueOf(false);
    private Boolean aw = Boolean.valueOf(false);
    private Boolean ax = Boolean.valueOf(false);
    private boolean ay = true;
    private OnTouchListener az = new OnTouchListener(this) {
        final /* synthetic */ HorizonalSegmentView a;

        {
            this.a = r1;
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Integer num = (Integer) view.getTag();
            if (num.intValue() >= 0) {
                this.a.as = num.intValue() + 1;
                this.a.at = this.a.as;
                this.a.au = view;
                if (!this.a.an.onTouchEvent(motionEvent)) {
                    switch (motionEvent.getAction() & 255) {
                        case 0:
                            if (this.a.al != null) {
                                this.a.al.c(this.a.getScrollX());
                                break;
                            }
                            break;
                    }
                }
                return true;
            }
            return false;
        }
    };

    public interface d {
        void a();

        void a(int i);

        void a(int i, int i2);

        void a(Boolean bool, int i);

        void a(int[] iArr);

        void b(int i);
    }

    public interface h {
        void a(int i);

        void a(int i, int i2, int i3, int i4);

        void b(int i);

        void c(int i);
    }

    private static final class a extends Handler {
        private final WeakReference<HorizonalSegmentView> a;

        public a(Looper looper, HorizonalSegmentView horizonalSegmentView) {
            super(looper);
            this.a = new WeakReference(horizonalSegmentView);
        }

        public void handleMessage(Message message) {
            HorizonalSegmentView horizonalSegmentView = (HorizonalSegmentView) this.a.get();
            if (horizonalSegmentView != null) {
                switch (message.what) {
                    case 4096:
                        Bitmap frameAtTime;
                        Bitmap createScaledBitmap;
                        String str = (String) message.obj;
                        int i = message.arg2;
                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                        int i2 = i * 1000 == 0 ? 2000 : i * 1000;
                        try {
                            mediaMetadataRetriever.setDataSource(str);
                            frameAtTime = mediaMetadataRetriever.getFrameAtTime((long) i2);
                        } catch (Exception e) {
                            e.printStackTrace();
                            frameAtTime = null;
                        }
                        if (frameAtTime == null) {
                            frameAtTime = VideoLibWrapper.getFrameAtTime(str, (long) i2, 0);
                        }
                        if (frameAtTime != null) {
                            createScaledBitmap = Bitmap.createScaledBitmap(frameAtTime, 320, (frameAtTime.getHeight() * 320) / frameAtTime.getWidth(), false);
                            if (!(createScaledBitmap.equals(frameAtTime) || frameAtTime == null || frameAtTime.isRecycled())) {
                                frameAtTime.recycle();
                            }
                        } else {
                            createScaledBitmap = frameAtTime;
                        }
                        g gVar = new g();
                        gVar.c = createScaledBitmap;
                        gVar.a = str;
                        gVar.b = i;
                        horizonalSegmentView.aE.obtainMessage(4096, gVar).sendToTarget();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b extends HandlerThread {
        public b(String str) {
            super(str, 1);
        }
    }

    private class c extends SimpleOnGestureListener {
        final /* synthetic */ HorizonalSegmentView a;

        private c(HorizonalSegmentView horizonalSegmentView) {
            this.a = horizonalSegmentView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return super.onSingleTapConfirmed(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            this.a.ah.setVisibility(0);
            ((DJIMultiMomentEditActivity) this.a.ab).a(4);
            this.a.au.startDrag(ClipData.newPlainText("", ""), new e(this.a, this.a.au), this.a.au, 0);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }
    }

    private class e extends DragShadowBuilder {
        final /* synthetic */ HorizonalSegmentView a;
        private ImageView b;
        private Drawable c = this.b.getDrawable();

        public e(HorizonalSegmentView horizonalSegmentView, View view) {
            this.a = horizonalSegmentView;
            super(view);
            this.b = (ImageView) ((LinearLayout) view).getChildAt(0);
        }

        public void onDrawShadow(Canvas canvas) {
            this.c.draw(canvas);
        }

        public void onProvideShadowMetrics(Point point, Point point2) {
            int width = this.b.getWidth();
            int height = this.b.getHeight();
            point.set(width, height);
            point2.set(width / 2, height / 2);
        }
    }

    private static final class f extends Handler {
        private final WeakReference<HorizonalSegmentView> a;

        public f(HorizonalSegmentView horizonalSegmentView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(horizonalSegmentView);
        }

        public void handleMessage(Message message) {
            HorizonalSegmentView horizonalSegmentView = (HorizonalSegmentView) this.a.get();
            if (horizonalSegmentView != null) {
                switch (message.what) {
                    case 4096:
                        g gVar = (g) message.obj;
                        String str = gVar.a;
                        Bitmap bitmap = gVar.c;
                        String a = HorizonalSegmentView.a(str, gVar.b);
                        WeakReference weakReference = (WeakReference) horizonalSegmentView.aF.remove(a);
                        if (weakReference != null) {
                            ImageView imageView = (ImageView) weakReference.get();
                            if (imageView != null && bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                                dji.pilot2.media.e.getInstance().a(a, bitmap);
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class g {
        public String a;
        public int b;
        public Bitmap c;

        private g() {
        }
    }

    private void a(Context context) {
        this.ab = context;
        this.ac = LayoutInflater.from(this.ab);
        this.an = new GestureDetector(context, new c());
        Q = dji.pilot.fpv.model.b.a(this.ab, R.dimen.b4);
        R = dji.pilot.fpv.model.b.a(this.ab, R.dimen.ft);
        this.ap = context.getResources().getDimensionPixelSize(R.dimen.b9);
        this.ao = (this.ap * 16) / 9;
        this.ar = 10.0f;
    }

    public void destroy() {
        this.aF.clear();
        this.aE.removeMessages(4096);
        this.aD.removeMessages(4096);
        this.aD = null;
        this.aC.quit();
        this.aC = null;
    }

    public HorizonalSegmentView(Context context) {
        super(context);
        a(context);
    }

    public HorizonalSegmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HorizonalSegmentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void init(LinearLayout linearLayout, dji.pilot2.multimoment.videolib.c cVar) {
        this.ad = linearLayout;
        int[] iArr = new int[2];
        this.ad.getLocationOnScreen(iArr);
        this.ag = iArr[0];
        this.af = iArr[1];
        this.am = dji.pilot.fpv.model.b.a(this.ab, R.dimen.b9);
        this.aC = new b("videothumb");
        this.aC.start();
        this.aD = new a(this.aC.getLooper(), this);
        this.aE = new f(this);
        this.ae = cVar;
        initInnerView();
    }

    public void setIsSingleTemplate(Boolean bool) {
        this.T = bool;
    }

    protected void a(int i, LinearLayout linearLayout, int i2, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i3 = 0; i3 < i; i3 += N) {
            LayoutParams layoutParams;
            RelativeLayout relativeLayout = (RelativeLayout) this.ac.inflate(R.layout.v2_multimoment_horizontal_item, null);
            long segViewWidth;
            if (i - i3 > N) {
                segViewWidth = getSegViewWidth(N);
                layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
                if (i3 == 0) {
                    layoutParams.setMargins(2, 0, 0, 0);
                    this.S = (int) ((segViewWidth + 2) + ((long) this.S));
                } else {
                    layoutParams.setMargins(0, 0, 0, 0);
                    this.S = (int) (segViewWidth + ((long) this.S));
                }
            } else {
                segViewWidth = getSegViewWidth(i - i3);
                layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
                if (i3 == 0) {
                    layoutParams.setMargins(2, 0, 2, 0);
                    this.S = (int) ((segViewWidth + 4) + ((long) this.S));
                } else {
                    layoutParams.setMargins(0, 0, 2, 0);
                    this.S = (int) ((segViewWidth + 2) + ((long) this.S));
                }
            }
            relativeLayout.setLayoutParams(layoutParams);
            a((DJIImageView) relativeLayout.findViewById(R.id.ctw), str, i3);
            linearLayout.addView(relativeLayout);
        }
        if (this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal || this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent) {
            relativeLayout = (RelativeLayout) this.ac.inflate(R.layout.v2_multimoment_transition, null);
            long segViewWidth2 = getSegViewWidth(2000);
            this.S = (int) (((long) this.S) + segViewWidth2);
            Log.i("divide", "width:" + segViewWidth2);
            layoutParams = new LinearLayout.LayoutParams((int) segViewWidth2, -1);
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HorizonalSegmentView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int[] iArr = new int[2];
                    view.getLocationInWindow(iArr);
                    Log.i("zhang", "location :" + iArr[0] + "," + iArr[1]);
                    this.a.ak.a(iArr);
                }
            });
            relativeLayout.setLayoutParams(layoutParams);
            linearLayout.addView(relativeLayout);
        }
        DJILogHelper.getInstance().LOGI("bob", "addSegImages  " + (System.currentTimeMillis() - currentTimeMillis));
    }

    protected void b(int i, LinearLayout linearLayout, int i2, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i3 = 0; i3 < i; i3 += N) {
            LayoutParams layoutParams;
            ImageView imageView = new ImageView(this.ab);
            long segViewWidth;
            if (i - i3 > N) {
                segViewWidth = getSegViewWidth(N);
                layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
                if (i3 == 0) {
                    layoutParams.setMargins(2, 0, 0, 0);
                    this.S = (int) ((segViewWidth + 2) + ((long) this.S));
                } else {
                    layoutParams.setMargins(0, 0, 0, 0);
                    this.S = (int) (segViewWidth + ((long) this.S));
                }
            } else {
                segViewWidth = getSegViewWidth(i - i3);
                layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
                if (i3 == 0) {
                    layoutParams.setMargins(2, 0, 2, 0);
                    this.S = (int) ((segViewWidth + 4) + ((long) this.S));
                } else {
                    layoutParams.setMargins(0, 0, 2, 0);
                    this.S = (int) ((segViewWidth + 2) + ((long) this.S));
                }
            }
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            a(imageView, str, i3);
            linearLayout.addView(imageView);
        }
        if (this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal || this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent || this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
            View imageView2 = new ImageView(this.ab);
            long segViewWidth2 = getSegViewWidth(2000);
            this.S = (int) (((long) this.S) + segViewWidth2);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(20, -1);
            int a = dji.pilot.fpv.model.b.a(this.ab, R.dimen.ga);
            int i4 = (int) ((segViewWidth2 / 2) - 5);
            layoutParams2.setMargins(i4, a, i4, a);
            imageView2.setLayoutParams(layoutParams2);
            imageView2.setScaleType(ScaleType.CENTER_INSIDE);
            imageView2.setImageResource(R.drawable.v2_multimoment_transitions);
            imageView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HorizonalSegmentView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Log.i("divide", "click");
                    int[] iArr = new int[2];
                    view.getLocationInWindow(iArr);
                    Log.i("zhang", "location :" + iArr[0] + "," + iArr[1]);
                    this.a.ak.a(iArr);
                }
            });
            linearLayout.addView(imageView2);
        }
        DJILogHelper.getInstance().LOGI("bob", "addSegImages  " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public long getSegViewWidth(int i) {
        return (long) (((((float) this.ao) * 1.0f) * ((float) i)) / 6000.0f);
    }

    public long getSegTotalWidth(int i) {
        if (this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal || this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_Intelligent || this.ae.c() == dji.pilot2.multimoment.videolib.b.MultiEdit_tmp) {
            return (long) (((float) ((getSegViewWidth(i) + 4) + getSegViewWidth(2000))) + this.ar);
        }
        return getSegViewWidth(i) + 4;
    }

    public void initInnerView() {
        long currentTimeMillis = System.currentTimeMillis();
        List u = this.ae.u();
        List h = this.ae.h();
        this.ad.removeAllViews();
        b();
        int min = Math.min(u.size(), h.size());
        DJILogHelper.getInstance().LOGI("bob", "initInnerView  before loop" + (System.currentTimeMillis() - currentTimeMillis));
        for (int i = 0; i < min; i++) {
            int intValue = ((Integer) u.get(i)).intValue();
            Log.i("zhang", "time is :" + intValue);
            View linearLayout = new LinearLayout(this.ab);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            b(intValue, linearLayout, i, (String) h.get(i));
            linearLayout.setTag(Integer.valueOf(i));
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HorizonalSegmentView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.ak != null) {
                        this.a.ak.b(((Integer) view.getTag()).intValue());
                    }
                }
            });
            linearLayout.setOnTouchListener(this.az);
            linearLayout.setOnDragListener(this.aB);
            this.ad.addView(linearLayout, layoutParams);
        }
        DJILogHelper.getInstance().LOGI("bob", "initInnerView after loop " + (System.currentTimeMillis() - currentTimeMillis));
        f();
        if (a().booleanValue()) {
            addPlusSegFast(min + 2);
        }
        d();
        DJILogHelper.getInstance().LOGI("bob", "initInnerView  " + (System.currentTimeMillis() - currentTimeMillis));
    }

    protected Boolean a() {
        if (this.ae == null) {
            DJILogHelper.getInstance().LOGI("bob", "isNeedAddPlusSeg mEditController is null");
        }
        return this.ae.t();
    }

    private Boolean k() {
        int childCount = this.ad.getChildCount();
        if (childCount <= 2) {
            DJILogHelper.getInstance().LOGI("bob", "isHavePlusSeg err nCnt=" + childCount);
            return Boolean.valueOf(false);
        }
        View childAt = this.ad.getChildAt(childCount - 2);
        if (childAt == null) {
            return Boolean.valueOf(false);
        }
        if (((Integer) childAt.getTag()).intValue() == V) {
            return Boolean.valueOf(true);
        }
        childAt = this.ad.getChildAt(childCount - 1);
        if (childAt == null) {
            return Boolean.valueOf(false);
        }
        if (((Integer) childAt.getTag()).intValue() == V) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public void addMoment(List<String> list) {
        list.size();
        int r = this.ae.r();
        int a = this.ae.a((List) list);
        List u = this.ae.u();
        for (int i = 0; i < a; i++) {
            int intValue = ((Integer) u.get(i + r)).intValue();
            View linearLayout = new LinearLayout(this.ab);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            b(intValue, linearLayout, i + r, (String) list.get(i));
            linearLayout.setTag(Integer.valueOf(i + r));
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HorizonalSegmentView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.ak != null) {
                        this.a.ak.b(((Integer) view.getTag()).intValue());
                    }
                }
            });
            linearLayout.setOnTouchListener(this.az);
            linearLayout.setOnDragListener(this.aB);
            this.ad.addView(linearLayout, (r + i) + 1, layoutParams);
        }
        if (!a().booleanValue()) {
            delPlusSeg();
        }
        c();
        this.ad.invalidate();
    }

    protected void b() {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(Q, -1);
        View view = new View(this.ab);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.rgb(0, 0, 0));
        this.ad.addView(view);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        view.setOnDragListener(this.aB);
        view.setTag(Integer.valueOf(W));
    }

    public int getSegWidth() {
        return this.S;
    }

    protected int getScreenWidth() {
        Context context = this.ab;
        Context context2 = this.ab;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    protected void c() {
        int screenWidth = getScreenWidth() - Q;
        if (screenWidth < 0) {
            screenWidth = getScreenWidth();
        }
        int r = this.ae.r();
        View childAt;
        LayoutParams layoutParams;
        if (k().booleanValue()) {
            childAt = this.ad.getChildAt(r + 3);
            if (childAt == null) {
                DJILogHelper.getInstance().LOGE("bob", "can not get endView have plus");
                return;
            }
            layoutParams = childAt.getLayoutParams();
            screenWidth -= this.ao;
            if (screenWidth < 0) {
                screenWidth = getScreenWidth();
            }
            if (layoutParams.width != screenWidth) {
                layoutParams.width = screenWidth;
                childAt.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        childAt = this.ad.getChildAt(r + 2);
        if (childAt == null) {
            DJILogHelper.getInstance().LOGE("bob", "can not get endView no plus");
            return;
        }
        layoutParams = childAt.getLayoutParams();
        if (layoutParams.width != screenWidth) {
            layoutParams.width = screenWidth;
            childAt.setLayoutParams(layoutParams);
        }
    }

    protected void d() {
        int screenWidth = getScreenWidth() - Q;
        if (screenWidth < 0) {
            screenWidth = getScreenWidth();
        }
        if (k().booleanValue()) {
            screenWidth -= this.ao;
            if (screenWidth < 0) {
                screenWidth = getScreenWidth();
            }
        }
        LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth, -1);
        View view = new View(this.ab);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.rgb(0, 0, 0));
        this.ad.addView(view);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        view.setTag(Integer.valueOf(aa));
        view.setOnDragListener(this.aB);
    }

    protected void e() {
        long segViewWidth = getSegViewWidth(2000);
        RelativeLayout relativeLayout = (RelativeLayout) this.ac.inflate(R.layout.v2_multimoment_horizontal_item, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
        layoutParams.setMargins(2, 0, 2, 0);
        relativeLayout.setLayoutParams(layoutParams);
        ((DJIImageView) relativeLayout.findViewById(R.id.ctw)).setImageDrawable(getResources().getDrawable(R.drawable.v2_djilogo));
        relativeLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        relativeLayout.setTag(Integer.valueOf(U));
        relativeLayout.setOnDragListener(this.aB);
        this.S = (int) ((segViewWidth + 4) + ((long) this.S));
        this.ad.addView(relativeLayout, layoutParams);
    }

    protected void f() {
        View imageView = new ImageView(this.ab);
        long segViewWidth = getSegViewWidth(2000);
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
        layoutParams.setMargins(2, 0, 2, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.v2_djilogo);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        imageView.setTag(Integer.valueOf(U));
        imageView.setOnDragListener(this.aB);
        this.S = (int) ((segViewWidth + 4) + ((long) this.S));
        this.ad.addView(imageView);
    }

    public void delPlusSeg() {
        View childAt = this.ad.getChildAt(this.ae.r() + 2);
        if (childAt != null && ((Integer) childAt.getTag()).intValue() == V) {
            this.ad.removeViewAt(this.ae.r() + 2);
        }
    }

    public void addPlusSegFast(int i) {
        long segViewWidth = getSegViewWidth(N);
        View imageView = new ImageView(this.ab);
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) segViewWidth, -1);
        layoutParams.setMargins(2, 0, 2, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(R.drawable.v2_virtual_rect);
        imageView.setImageResource(R.drawable.v2_multimoment_add);
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setTag(Integer.valueOf(V));
        imageView.setOnDragListener(this.aB);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.ak != null) {
                    this.a.ak.a(((Integer) view.getTag()).intValue());
                }
            }
        });
        this.ad.addView(imageView, i, layoutParams);
    }

    public void addPlusSeg(int i) {
        RelativeLayout relativeLayout = (RelativeLayout) this.ac.inflate(R.layout.v2_multimoment_horizontal_item, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) getSegViewWidth(N), -1);
        layoutParams.setMargins(2, 0, 2, 0);
        relativeLayout.setLayoutParams(layoutParams);
        DJIImageView dJIImageView = (DJIImageView) relativeLayout.findViewById(R.id.ctw);
        dJIImageView.setImageDrawable(getResources().getDrawable(R.drawable.v2_multimoment_add));
        DJIImageView dJIImageView2 = (DJIImageView) relativeLayout.findViewById(R.id.cty);
        dJIImageView2.setBackground(getResources().getDrawable(R.drawable.v2_virtual_rect));
        dJIImageView2.setVisibility(0);
        LayoutParams layoutParams2 = dJIImageView.getLayoutParams();
        layoutParams2.height = -2;
        layoutParams2.width = -2;
        dJIImageView.setLayoutParams(layoutParams2);
        relativeLayout.setTag(Integer.valueOf(V));
        relativeLayout.setOnDragListener(this.aB);
        relativeLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HorizonalSegmentView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.ak != null) {
                    this.a.ak.a(((Integer) view.getTag()).intValue());
                }
            }
        });
        this.ad.addView(relativeLayout, i, layoutParams);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.al != null) {
            this.al.a(i, i2, i3, i4);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                DJILogHelper.getInstance().LOGI("bob", "HorizonalSegmentView ACTION_DOWN");
                if (this.al != null) {
                    this.al.c(getScrollX());
                    break;
                }
                break;
            case 1:
                DJILogHelper.getInstance().LOGI("bob", "HorizonalSegmentView ACTION_UP");
                if (this.al != null) {
                    this.al.a(getScrollX());
                    break;
                }
                break;
            case 2:
                if (this.al != null) {
                    this.al.b(getScrollX());
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnItemClickCallBack(d dVar) {
        this.ak = dVar;
    }

    public void setScrollListener(h hVar) {
        this.al = hVar;
    }

    public View getInnerChildAt(int i) {
        return this.ad.getChildAt(i);
    }

    private LinearLayout a(int i) {
        return (LinearLayout) getInnerChildAt(i);
    }

    public void deleteItemAt(int i) {
        int i2 = i - 1;
        int r = this.ae.r();
        Boolean k = k();
        DJILogHelper.getInstance().LOGI("bob", "nCurSegsNumm = " + r + " index = " + i2);
        int i3 = i2;
        while (i3 + 1 <= r) {
            i3++;
            a(i3 + 1).setTag(Integer.valueOf(i3 - 1));
            a(i3 + 1).postInvalidate();
        }
        a(i2 + 1).setOnDragListener(null);
        this.ad.removeViewAt(i2 + 1);
        if (!k.booleanValue() && a().booleanValue()) {
            addPlusSegFast(r + 2);
        }
        c();
    }

    public void setDragDeleteView(RelativeLayout relativeLayout) {
        this.ah = relativeLayout;
        this.ah.setOnDragListener(this.aA);
        this.ai = (ImageView) this.ah.findViewById(R.id.ca2);
        this.ai.setOnDragListener(this.aA);
        ((RelativeLayout) this.ah.findViewById(R.id.ca1)).setOnDragListener(this.aA);
    }

    private void a(int i, int i2) {
        int i3;
        if (i > i2) {
            i3 = -1;
        } else {
            i3 = 1;
        }
        DJILogHelper.getInstance().LOGI("bob", "begin = " + i + "end = " + i2);
        this.ae.a(i, i2);
        if (this.ak != null) {
            this.ak.a(i, i2);
        }
        List u = this.ae.u();
        List h = this.ae.h();
        for (int i4 = i - 1; i4 != i2 - 1; i4 += i3) {
            a(i4, (String) h.get(i4), ((Integer) u.get(i4)).intValue());
        }
        a(i2 - 1, (String) h.get(i2 - 1), (int) N);
    }

    private void l() {
        List u = this.ae.u();
        List h = this.ae.h();
        int min = Math.min(u.size(), h.size());
        int i = 0;
        while (i != min) {
            a(i, (String) h.get(i), this.as + -1 == i ? N : ((Integer) u.get(i)).intValue());
            i++;
        }
    }

    private void m() {
        List u = this.ae.u();
        List h = this.ae.h();
        int min = Math.min(u.size(), h.size());
        for (int i = 0; i != min; i++) {
            a(i, (String) h.get(i), ((Integer) u.get(i)).intValue());
        }
    }

    private void a(int i, String str, int i2) {
        LinearLayout a = a(i + 1);
        if (a != null) {
            a.removeAllViews();
            b(i2, a, i, str);
            a(i + 1).postInvalidate();
        }
    }

    protected static String a(String str, int i) {
        return i == 0 ? str : str + String.valueOf(i);
    }

    void a(ImageView imageView, String str, int i) {
        if (imageView != null && str != null) {
            String a = a(str, i);
            Bitmap a2 = dji.pilot2.media.e.getInstance().a(a);
            if (a2 != null) {
                imageView.setImageBitmap(a2);
            } else if (this.aF.containsKey(a)) {
                this.aF.put(a, new WeakReference(imageView));
            } else {
                this.aF.put(a, new WeakReference(imageView));
                this.aD.obtainMessage(4096, 0, i, str).sendToTarget();
            }
        }
    }
}
