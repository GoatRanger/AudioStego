package dji.pilot.fpv.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import com.dji.frame.c.e;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams.DelFileStatus;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams.FileType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.data.model.P3.DataSpecialControl.MulDelValue;
import dji.midware.data.model.P3.DataSpecialControl.PlayBrowseType;
import dji.midware.data.model.P3.DataSpecialControl.PlayCtrType;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIPBAlbumActivity;
import dji.pilot.fpv.control.f;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIScrollBar;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.i;
import dji.pilot.publics.widget.k;
import dji.pilot.publics.widget.l;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

public class DJIPlayBackView extends DJIRelativeLayout implements OnItemClickListener, OnSeekBarChangeListener {
    private static final int A = -1;
    private static final int B = 0;
    private static final int C = 1;
    private static final int D = 2;
    private static final int E = 3;
    private static final int F = -1;
    private static final int G = 0;
    private static final int H = 1;
    private static final int I = 2;
    private static final int J = 3;
    private static final int K = 4;
    private static final int L = 5;
    private static final int M = 6;
    private static final long e = 20;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 4;
    private static final int j = 36864;
    private static final long k = 2000;
    private static final String l = DJIPlayBackView.class.getSimpleName();
    private static final int m = 8192;
    private static final long n = 100;
    private static final int o = 12288;
    private static final long p = 100;
    private static final int q = 16384;
    private static final long r = 80;
    private static final int s = 20480;
    private static final int t = 24576;
    private static final int u = 28672;
    private static final int v = 32768;
    private static final int w = 39168;
    private static final int x = 68;
    private static final int y = 54;
    private static final int z = 8;
    private DJILinearLayout N;
    private DJIRelativeLayout O;
    private DJITextView P;
    private DJITextView Q;
    private DJIImageView R;
    private DJITextView S;
    private DJIRelativeLayout T;
    private DJITextView U;
    private DJITextView V;
    private View W;
    int a;
    private d aA;
    private OnTouchListener aB;
    private GestureDetector aC;
    private OnGestureListener aD;
    private int aE;
    private b aF;
    private DecimalFormat aG;
    private Context aH;
    private com.dji.frame.common.b aI;
    private float aJ;
    private float aK;
    private int aL;
    private OnGestureListener aM;
    private int aN;
    private int aO;
    private volatile boolean aP;
    private int aQ;
    private DJILinearLayout aR;
    private DJIImageView aS;
    private DJITextView aT;
    private k aU;
    private i aV;
    private l aW;
    private Animation aX;
    private Animation aY;
    private Animation aZ;
    private DJIStateImageView aa;
    private DJIGestureGridView ab;
    private DJIScrollBar ac;
    private DJIRelativeLayout ad;
    private DJIStateImageView ae;
    private DJITextView af;
    private DJIStateImageView ag;
    private DJIStateImageView ah;
    private DJITextView ai;
    private DJILinearLayout aj;
    private DJIStateImageView ak;
    private DJIStateImageView al;
    private SeekBar am;
    private DJITextView an;
    private OnClickListener ao;
    private OnClickListener ap;
    private OnClickListener aq;
    private DataCameraGetPushPlayBackParams ar;
    private DataCameraGetPushStateInfo as;
    private DataSpecialControl at;
    private int au;
    private int av;
    private dji.pilot.publics.widget.b aw;
    private dji.pilot.publics.widget.b ax;
    private c ay;
    private int az;
    int b;
    private Animation ba;
    private ScaleGestureDetector bb;
    private dji.c.a bc;
    private f bd;
    private boolean be;
    private dji.pilot.fpv.control.f.a bf;
    private float bg;
    private int bh;
    private int bi;
    private int bj;
    private dji.midware.e.d bk;
    private int bl;
    private Object bm;
    private MODE bn;
    String c;
    String d;

    public interface b {
        void a(boolean z, int i);
    }

    private class a implements OnScaleGestureListener {
        final /* synthetic */ DJIPlayBackView a;

        private a(DJIPlayBackView dJIPlayBackView) {
            this.a = dJIPlayBackView;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.a.a(scaleGestureDetector.getScaleFactor(), false);
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.a.bg = this.a.ar.getZoomSize();
            this.a.bh = (int) (this.a.bg * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.a.a(scaleGestureDetector.getScaleFactor(), true);
        }
    }

    private final class c extends BaseAdapter {
        final /* synthetic */ DJIPlayBackView a;
        private final LayoutInflater b;
        private final int c;

        public c(DJIPlayBackView dJIPlayBackView, Context context, int i) {
            this.a = dJIPlayBackView;
            this.b = LayoutInflater.from(context);
            this.c = (i - e.b(dJIPlayBackView.aH, 8.0f)) / 2;
        }

        public int getCount() {
            return 8;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.b.inflate(R.layout.fpv_playback_preview_item, null);
            }
            LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -1;
                layoutParams.height = this.c;
            } else {
                layoutParams = new AbsListView.LayoutParams(-1, this.c);
            }
            view.setLayoutParams(layoutParams);
            return view;
        }
    }

    private static final class d extends Handler {
        private final WeakReference<DJIPlayBackView> a;

        public d(DJIPlayBackView dJIPlayBackView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIPlayBackView);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            DJIPlayBackView dJIPlayBackView = (DJIPlayBackView) this.a.get();
            if (dJIPlayBackView != null && dJIPlayBackView.getVisibility() == 0) {
                switch (message.what) {
                    case 8192:
                        if (message.arg1 != 1 || dJIPlayBackView.ar.isGetted()) {
                            dJIPlayBackView.f(false);
                            return;
                        } else {
                            dJIPlayBackView.aA.sendMessageDelayed(obtainMessage(8192, 1, 0), 50);
                            return;
                        }
                    case 12288:
                        dJIPlayBackView.g(false);
                        return;
                    case 16384:
                        dJIPlayBackView.f(message.arg1);
                        return;
                    case 20480:
                        dJIPlayBackView.u();
                        return;
                    case 24576:
                        dJIPlayBackView.b(Integer.parseInt(message.obj.toString()), message.arg1, message.arg2);
                        return;
                    case DJIPlayBackView.u /*28672*/:
                        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.MEDIASDDOWNLOADEND);
                        dJIPlayBackView.x();
                        return;
                    case 32768:
                        dJIPlayBackView.b(message.arg1);
                        return;
                    case DJIPlayBackView.j /*36864*/:
                        if (message.arg1 == 0) {
                            dJIPlayBackView.a(0, true, true);
                            dJIPlayBackView.a(0, true);
                            return;
                        } else if (message.arg1 == 1) {
                            dJIPlayBackView.a(1, true, true);
                            dJIPlayBackView.b(2, true);
                            return;
                        } else if (message.arg1 == 3) {
                            dJIPlayBackView.a(3, true, true);
                            return;
                        } else if (message.arg1 == 4) {
                            dJIPlayBackView.bf.a(null);
                            return;
                        } else {
                            return;
                        }
                    case DJIPlayBackView.w /*39168*/:
                        if (message.arg1 != 1) {
                            z = false;
                        }
                        dJIPlayBackView.c(z);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIPlayBackView(Context context) {
        this(context, null);
    }

    public DJIPlayBackView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIPlayBackView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = -1;
        this.av = -1;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = 0;
        this.aA = null;
        this.aB = null;
        this.aC = null;
        this.aD = null;
        this.aE = 0;
        this.aF = null;
        this.aG = new DecimalFormat("##0.00");
        this.aH = null;
        this.aI = null;
        this.aJ = 0.0f;
        this.aK = 0.0f;
        this.aL = 0;
        this.aM = null;
        this.aN = 0;
        this.aO = 0;
        this.aP = false;
        this.aQ = 0;
        this.aR = null;
        this.aS = null;
        this.aT = null;
        this.aU = null;
        this.aV = null;
        this.aW = null;
        this.aX = null;
        this.aY = null;
        this.aZ = null;
        this.ba = null;
        this.bd = null;
        this.be = false;
        this.bf = null;
        this.bi = 0;
        this.bj = 0;
        this.bk = new dji.midware.e.d(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "change download mode success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "change download mode " + aVar, false, true);
            }
        };
        this.a = -1;
        this.b = 0;
        this.c = getContext().getString(R.string.app_downloading_pgs);
        this.d = getContext().getString(R.string.app_downloading);
        this.bl = 0;
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            this.aH = getContext();
            this.bc = new dji.c.a();
            this.aI = com.dji.frame.c.c.d(this.aH);
            this.aA = new d(this);
            this.ar = DataCameraGetPushPlayBackParams.getInstance();
            this.at = DataSpecialControl.getInstance();
            this.as = DataCameraGetPushStateInfo.getInstance();
            this.aX = AnimationUtils.loadAnimation(this.aH, R.anim.bt);
            this.aY = AnimationUtils.loadAnimation(this.aH, R.anim.bu);
            this.aZ = AnimationUtils.loadAnimation(this.aH, R.anim.be);
            this.ba = AnimationUtils.loadAnimation(this.aH, R.anim.bf);
            this.aL = this.aH.getResources().getDimensionPixelSize(R.dimen.m4);
            c();
            d();
            b();
        }
    }

    private void b() {
        this.aM = new OnGestureListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f2) >= ((float) this.a.aE)) {
                    DataCameraGetPushPlayBackParams.MODE mode = this.a.ar.getMode();
                    if (DataCameraGetPushPlayBackParams.MODE.Multiple == mode || DataCameraGetPushPlayBackParams.MODE.MultipleDel == mode) {
                        if (motionEvent2.getY() > motionEvent.getY()) {
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.PAGEUP, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling down", false, true);
                        } else {
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.PAGEDOWN, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling up", false, true);
                        }
                    }
                }
                return false;
            }

            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }
        };
    }

    private void a(float f, boolean z) {
        int i = 100;
        DataCameraGetPushPlayBackParams.MODE mode = this.ar.getMode();
        if (DataCameraGetPushPlayBackParams.MODE.SingleLarge.ordinal() == mode.ordinal() || DataCameraGetPushPlayBackParams.MODE.Single.ordinal() == mode.ordinal()) {
            int i2 = (int) ((this.bg * f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            if (i2 >= 100) {
                if (i2 > 300) {
                    i = 300;
                } else {
                    i = i2;
                }
            }
            if (Math.abs(this.bh - i) > this.bh / 10 || (z && this.bh != i)) {
                this.bh = i;
                this.at.setPlayBackBrowserScaleType((short) i).start(0);
            }
        }
    }

    private void c() {
        this.ao = new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.a9c == id) {
                    this.a.y();
                } else if (R.id.a9e == id) {
                    this.a.G();
                }
            }
        };
        this.ap = new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.a94 == id) {
                    this.a.z();
                } else if (R.id.a95 == id) {
                    this.a.A();
                }
            }
        };
        this.aq = new OnClickListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.a9m == id) {
                    this.a.C();
                } else if (R.id.a9n == id) {
                    this.a.D();
                } else if (R.id.a9o == id) {
                    this.a.E();
                } else if (R.id.a9k == id) {
                    this.a.F();
                } else if (R.id.a9q == id) {
                    this.a.H();
                } else if (R.id.a9r == id) {
                    dji.pilot.fpv.d.e.a("FPV_PlaybackView_VideoView_Button_StopPlayStart");
                    this.a.I();
                }
            }
        };
    }

    private void a(float f, float f2) {
        int photoWidth = this.ar.getPhotoWidth();
        int photoHeight = this.ar.getPhotoHeight();
        int i = this.aN;
        int i2 = this.aO;
        float zoomSize = this.ar.getZoomSize();
        float f3 = ((float) photoWidth) / zoomSize;
        float f4 = ((float) photoHeight) / zoomSize;
        int i3 = (int) (((f / ((float) DJIBaseActivity.screenWidth)) * f3) + ((float) i));
        int i4 = (int) (((float) i2) + ((f2 / ((float) DJIBaseActivity.screenHeight)) * f4));
        int i5 = (int) (f3 / 2.0f);
        int i6 = i5 + 1;
        i5 = (photoWidth - i5) - 1;
        int i7 = (int) (f4 / 2.0f);
        photoWidth = i7 + 1;
        photoHeight = (photoHeight - i7) - 1;
        if (i3 >= i6) {
            if (i3 > i5) {
                i6 = i5;
            } else {
                i6 = i3;
            }
        }
        if (i4 >= photoWidth) {
            if (i4 > photoHeight) {
                photoWidth = photoHeight;
            } else {
                photoWidth = i4;
            }
        }
        if (Math.abs(i - i6) <= 20 && Math.abs(i2 - photoWidth) <= 16) {
            return;
        }
        if (Math.abs(this.bi - i6) > 20 || Math.abs(this.bj - photoWidth) > 16) {
            this.bi = i6;
            this.bj = photoWidth;
            this.at.setPlayBackBrowserType(PlayBrowseType.DRAG, (byte) (i6 / 20), (byte) (photoWidth / 20)).start(0);
        }
    }

    private void d() {
        this.aE = ViewConfiguration.get(this.aH).getScaledMinimumFlingVelocity();
        this.aB = new OnTouchListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                DataCameraGetPushPlayBackParams.MODE mode = this.a.ar.getMode();
                if (DataCameraGetPushPlayBackParams.MODE.SingleLarge.ordinal() != mode.ordinal() && DataCameraGetPushPlayBackParams.MODE.Single.ordinal() != mode.ordinal() && DataCameraGetPushPlayBackParams.MODE.SinglePlay.ordinal() != mode.ordinal() && DataCameraGetPushPlayBackParams.MODE.SinglePause.ordinal() != mode.ordinal()) {
                    return false;
                }
                if (motionEvent.getPointerCount() == 1) {
                    this.a.aC.onTouchEvent(motionEvent);
                    return true;
                }
                this.a.bb.onTouchEvent(motionEvent);
                return true;
            }
        };
        this.aD = new OnGestureListener(this) {
            final /* synthetic */ DJIPlayBackView a;

            {
                this.a = r1;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                this.a.z();
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (this.a.ar.getMode() != DataCameraGetPushPlayBackParams.MODE.SingleLarge || this.a.ar.getZoomSize() <= 1.0f) {
                    return false;
                }
                this.a.aJ = this.a.aJ + f;
                this.a.aK = this.a.aK + f2;
                if (Math.abs(this.a.aJ) >= 68.0f || Math.abs(this.a.aK) >= 54.0f) {
                    this.a.a(this.a.aJ, this.a.aK);
                }
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(f) >= ((float) this.a.aE)) {
                    DataCameraGetPushPlayBackParams.MODE mode = this.a.ar.getMode();
                    if (DataCameraGetPushPlayBackParams.MODE.Single == mode) {
                        if (motionEvent2.getX() > motionEvent.getX()) {
                            dji.pilot.fpv.d.e.a("FPV_PlayBackView_SingleView_SwipeGesture_left");
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.LEFT, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling left", false, true);
                        } else {
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.RIGHT, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling right", false, true);
                        }
                    } else if (DataCameraGetPushPlayBackParams.MODE.Multiple == mode || DataCameraGetPushPlayBackParams.MODE.MultipleDel == mode) {
                        if (motionEvent2.getY() > motionEvent.getY()) {
                            dji.pilot.fpv.d.e.a("FPV_PlaybackView_MultipleView_swipeGesture_Down");
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.PAGEDOWN, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling down", false, true);
                        } else {
                            dji.pilot.fpv.d.e.a("FPV_PlaybackView_MultipleView_swipeGesture_Up");
                            this.a.at.setPlayBackBrowserType(PlayBrowseType.PAGEUP, (byte) 0, (byte) 0).start(DJIPlayBackView.e);
                            DJILogHelper.getInstance().LOGD("", "here fling up", false, true);
                        }
                    }
                }
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                if (this.a.ar.getMode() == DataCameraGetPushPlayBackParams.MODE.SingleLarge && this.a.ar.getZoomSize() > 1.0f) {
                    int centerX = this.a.ar.getCenterX();
                    int centerY = this.a.ar.getCenterY();
                    this.a.aN = centerX;
                    this.a.aO = centerY;
                    this.a.aJ = 0.0f;
                    this.a.aK = 0.0f;
                    this.a.bi = 0;
                    this.a.bj = 0;
                }
                return true;
            }
        };
        this.bb = new ScaleGestureDetector(this.aH, new a());
        this.aC = new GestureDetector(this.aH, this.aD);
    }

    private void e() {
        this.N = (DJILinearLayout) findViewById(R.id.a9a);
        this.O = (DJIRelativeLayout) findViewById(R.id.a9b);
        this.P = (DJITextView) findViewById(R.id.a9c);
        this.Q = (DJITextView) findViewById(R.id.a9d);
        this.R = (DJIImageView) findViewById(R.id.a9i);
        this.S = (DJITextView) findViewById(R.id.a9e);
        this.T = (DJIRelativeLayout) findViewById(R.id.a9f);
        this.U = (DJITextView) findViewById(R.id.a9h);
        this.V = (DJITextView) findViewById(R.id.a9g);
        this.P.setOnClickListener(this.ao);
        this.S.setOnClickListener(this.ao);
    }

    private void f() {
        this.W = findViewById(R.id.a94);
        this.aa = (DJIStateImageView) findViewById(R.id.a95);
        this.ab = (DJIGestureGridView) findViewById(R.id.a99);
        this.ac = (DJIScrollBar) findViewById(R.id.a9_);
        this.aa.setOnClickListener(this.ap);
        this.ab.setOnItemClickListener(this);
        this.ab.setGestureListener(this.aM);
        this.ac.setEnabled(false);
        this.W.setOnTouchListener(this.aB);
        this.aR = (DJILinearLayout) findViewById(R.id.a96);
        this.aS = (DJIImageView) findViewById(R.id.a97);
        this.aT = (DJITextView) findViewById(R.id.a98);
    }

    private void g() {
        this.ad = (DJIRelativeLayout) findViewById(R.id.a9j);
        this.ae = (DJIStateImageView) findViewById(R.id.a9m);
        this.af = (DJITextView) findViewById(R.id.a9l);
        this.ag = (DJIStateImageView) findViewById(R.id.a9n);
        this.ah = (DJIStateImageView) findViewById(R.id.a9o);
        this.ai = (DJITextView) findViewById(R.id.a9k);
        this.aj = (DJILinearLayout) findViewById(R.id.a9p);
        this.ak = (DJIStateImageView) findViewById(R.id.a9q);
        this.al = (DJIStateImageView) findViewById(R.id.a9r);
        this.am = (SeekBar) findViewById(R.id.a9s);
        this.an = (DJITextView) findViewById(R.id.a9t);
        this.ae.setOnClickListener(this.aq);
        this.ag.setOnClickListener(this.aq);
        this.ah.setOnClickListener(this.aq);
        this.ai.setOnClickListener(this.aq);
        this.ak.setOnClickListener(this.aq);
        this.al.setOnClickListener(this.aq);
        this.am.setOnSeekBarChangeListener(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            e();
            f();
            g();
        }
    }

    public void setOnFullScreenListener(b bVar) {
        this.aF = bVar;
    }

    public void show() {
        if (this.ay == null) {
            this.ay = new c(this, this.aH, this.az);
            this.ab.setAdapter(this.ay);
        }
        super.show();
        g(true);
        this.aA.sendMessageDelayed(this.aA.obtainMessage(8192, 1, 0), 100);
        dji.thirdparty.a.c.a().a(this);
    }

    public void hide() {
        h();
        super.hide();
    }

    public void go() {
        h();
        super.go();
    }

    private void h() {
        this.bc.a();
        o();
        dji.thirdparty.a.c.a().d(this);
        this.aA.removeMessages(8192);
        this.aQ = 0;
        this.au = -1;
        this.av = -1;
        if (this.aP) {
            this.aP = false;
        }
    }

    public void setCenterHeight(int i) {
        this.az = (i - dji.pilot.fpv.model.b.a(this.aH, R.dimen.m7)) - dji.pilot.fpv.model.b.a(this.aH, R.dimen.m6);
    }

    private void i() {
        int i = this.aQ;
        if (i != 0) {
            l();
            o();
            x();
            this.W.setVisibility(0);
            this.aR.show();
            this.P.show();
            this.Q.go();
            this.T.go();
            this.R.go();
            this.S.go();
            this.aa.go();
            this.ab.go();
            this.ac.setVisibility(4);
            this.ad.hide();
            this.aj.go();
            if ((i & 2) != 0) {
                this.aT.setText(R.string.fpv_playback_sdcard_removal);
            } else if ((i & 4) != 0) {
                this.aT.setText(R.string.fpv_playback_sdcard_error);
            } else if ((i & 1) != 0) {
                this.aT.setText(R.string.fpv_playback_sdcard_nothing);
            }
        }
    }

    private void a(int i, boolean z, boolean z2) {
        this.aR.go();
        c(false);
        if (i != this.au || z) {
            this.au = i;
            if (i == 0) {
                x();
                this.T.show();
                this.R.go();
                this.W.setVisibility(0);
                this.aa.go();
                this.ab.show();
                this.ac.setVisibility(0);
                this.ad.show();
                this.aj.go();
                if (z2) {
                    a(0, true);
                }
            } else if (1 == i) {
                x();
                this.P.show();
                this.Q.show();
                this.S.go();
                this.R.go();
                this.T.go();
                this.W.setVisibility(0);
                this.aa.go();
                this.ab.go();
                this.ac.setVisibility(8);
                a(true);
                this.ae.go();
                this.ai.show();
                this.ai.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fpv_playback_preview, 0, 0, 0);
                this.ai.setText("");
                this.aj.go();
                if (z2) {
                    b(2, true);
                }
            } else if (2 == i) {
                x();
                this.P.show();
                this.Q.show();
                this.S.go();
                this.T.go();
                this.R.go();
                this.W.setVisibility(0);
                this.ab.go();
                this.ac.setVisibility(8);
                this.ae.go();
                this.ai.go();
                if (z2) {
                    c(4, true);
                }
            } else if (3 == i) {
                this.av = -1;
                this.P.show();
                this.Q.go();
                this.S.go();
                this.T.go();
                this.R.go();
                this.aa.go();
                this.ab.go();
                this.ac.setVisibility(4);
                this.ad.hide();
                this.aj.go();
                this.W.setVisibility(4);
                this.aR.go();
                if (dji.pilot.c.d.b != DataRcSetMaster.MODE.b) {
                    int deleteChioceNum = DataCameraGetPushPlayBackParams.getInstance().getDeleteChioceNum();
                    if (deleteChioceNum == 0) {
                        deleteChioceNum = 1;
                    }
                    a(1, deleteChioceNum, 0);
                    return;
                }
                c(true);
            }
        }
    }

    private void a(int i, boolean z) {
        if ((z || i != this.av) && this.au == 0) {
            this.av = i;
            if (i == 0) {
                this.P.show();
                this.Q.go();
                this.S.go();
                a(false);
                this.af.go();
                this.ae.go();
                this.ai.show();
                this.ai.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                this.ai.setText(R.string.app_select);
            } else if (1 == i) {
                this.P.go();
                this.Q.show();
                this.S.go();
                j();
                this.af.go();
                this.ae.show();
                this.ae.setImageResource(R.drawable.fpv_playback_unselectall);
                this.ai.show();
                this.ai.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                this.ai.setText(R.string.app_cancel);
            }
        }
    }

    private void b(int i, boolean z) {
        if ((z || i != this.av) && this.au == 1) {
            this.av = i;
            if (2 == i) {
                this.af.hide();
            } else if (3 == i) {
                this.af.show();
            }
        }
    }

    private void c(int i, boolean z) {
        if ((z || i != this.av) && this.au == 2) {
            this.av = i;
            if (4 == i) {
                this.aa.show();
                a(true);
                this.aj.go();
                this.af.hide();
                this.ae.go();
                this.ai.show();
                this.ai.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fpv_playback_preview, 0, 0, 0);
                this.ai.setText("");
                this.bc.a();
            } else if (5 == i) {
                this.aa.go();
                this.ad.go();
                if (this.N.isShown()) {
                    this.aj.show();
                }
                this.ak.setImageResource(R.drawable.fpv_playback_video_pause);
                c(0);
                String fileName = DataCameraGetPushPlayBackParams.getInstance().getFileName();
                DJILogHelper.getInstance().LOGD(l, "name=" + fileName, false, true);
                String[] split = fileName.split("\\\\");
                split = split[split.length - 1].split("\\.");
                DJILogHelper.getInstance().LOGD(l, "names2=" + split.length, false, true);
                fileName = split[0];
                this.bc.a(dji.pilot.fpv.control.b.a + fileName + ".amr");
                DJILogHelper.getInstance().LOGD(l, "filename=" + fileName, false, true);
            } else if (6 == i) {
                this.aa.go();
                this.ad.go();
                this.aj.show();
                this.ak.setImageResource(R.drawable.fpv_playback_video_play);
                c(0);
            }
        }
    }

    private void a(boolean z) {
        if (this.N.isShown()) {
            this.ad.show();
        }
        if (z) {
            this.ag.show();
            this.ag.setEnabled(true);
            if (dji.pilot.c.d.b == null || !(dji.pilot.c.d.b == DataRcSetMaster.MODE.b || dji.pilot.c.d.b == DataRcSetMaster.MODE.c)) {
                this.ah.show();
            }
            DJILogHelper.getInstance().LOGD("", "rcmode[" + dji.pilot.c.d.b + dji.pilot.usercenter.protocol.d.H, false, true);
            if (this.ar.getFileType() == FileType.DNG) {
                this.ah.setEnabled(false);
                return;
            } else {
                this.ah.setEnabled(true);
                return;
            }
        }
        this.ag.go();
        this.ah.go();
    }

    private void j() {
        this.ag.show();
        this.ag.setEnabled(true);
        this.ag.setImageResource(R.drawable.fpv_playback_delete);
        if (!(dji.pilot.c.d.b == DataRcSetMaster.MODE.b || dji.pilot.c.d.b == DataRcSetMaster.MODE.c)) {
            this.ah.show();
        }
        this.ah.setEnabled(true);
    }

    private void k() {
        if (this.aw == null) {
            this.aw = dji.pilot.publics.widget.b.a(this.aH, (int) R.string.fpv_playback_del_image, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dji.pilot.fpv.d.e.a("FPV_PlayBackView_DeleteAlertView_Button_CancelDelete");
                    this.a.m();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dji.pilot.fpv.d.e.a("FPV_PlayBackView_DeleteAlertView_Button_ConfirmDelete");
                    this.a.n();
                }
            });
            this.aw.setCancelable(true);
            this.aw.setCanceledOnTouchOutside(true);
        }
        if (this.aw != null && !this.aw.isShowing()) {
            if (this.au == 0) {
                this.aw.a(getResources().getString(R.string.fpv_playback_del_multiple_images, new Object[]{Integer.valueOf(this.ar.getDeleteChioceNum())}));
            } else if (1 == this.au) {
                this.aw.a((int) R.string.fpv_playback_del_image);
            } else if (2 == this.au) {
                this.aw.a((int) R.string.fpv_playback_del_video);
            }
            this.aw.show();
        }
    }

    private void l() {
        if (this.aw != null && this.aw.isShowing()) {
            this.aw.dismiss();
            this.aw = null;
        }
    }

    private void m() {
        l();
    }

    private void n() {
        if (this.au == 0) {
            l();
            a((int) R.string.app_deleting);
            this.at.setPlayBackBrowserType(PlayBrowseType.ENTER, (byte) 0, (byte) 0).start(e);
        } else if (1 == this.au) {
            this.at.setPlayBackBrowserType(PlayBrowseType.DELETE, (byte) 0, (byte) 0).start(e);
            l();
        } else if (2 == this.au) {
            this.at.setPlayBackBrowserType(PlayBrowseType.DELETE, (byte) 0, (byte) 0).start(e);
            l();
        }
    }

    private void a(int i) {
        if (this.aU == null) {
            this.aU = k.a(this.aH, i);
        }
        if (this.aU != null && !this.aU.isShowing()) {
            this.aU.show();
        }
    }

    private void o() {
        if (this.aU != null && this.aU.isShowing()) {
            this.aU.dismiss();
            this.aU = null;
        }
    }

    private void p() {
        if (this.ax == null) {
            this.ax = dji.pilot.publics.widget.b.a(this.aH, (int) R.string.fpv_playback_download_files, (int) R.string.fpv_playback_download_desc, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.r();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.s();
                }
            });
            this.ax.setCancelable(true);
            this.ax.setCanceledOnTouchOutside(true);
        }
        if (this.ax != null && !this.ax.isShowing()) {
            this.ax.show();
        }
    }

    private void q() {
        if (this.ax != null && this.ax.isShowing()) {
            this.ax.dismiss();
            this.ax = null;
        }
    }

    private void r() {
        q();
    }

    private void s() {
        q();
        DataCameraSetMode.getInstance().a(MODE.DOWNLOAD).start(this.bk);
        a(3, true, true);
    }

    private void t() {
        if (this.bd == null) {
            this.bf = new dji.pilot.fpv.control.f.a(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.aP = true;
                }

                public void a(int i, int i2, float f) {
                    this.a.aA.sendMessage(this.a.aA.obtainMessage(24576, i2, (int) (i2 != 0 ? 1000.0f * f : 0.0f), Integer.valueOf(i)));
                }

                public void a(Exception exception) {
                }

                public void b() {
                    this.a.aA.sendEmptyMessage(DJIPlayBackView.u);
                }

                public void a(int i) {
                    this.a.aA.sendMessage(this.a.aA.obtainMessage(32768, i, 0));
                }

                public void a(boolean z) {
                    int i;
                    d w = this.a.aA;
                    d w2 = this.a.aA;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    w.sendMessage(w2.obtainMessage(DJIPlayBackView.w, i, 0));
                }
            };
            this.bd = new f(this.aH, this.bf);
            this.bd.b();
        }
    }

    private void u() {
        b(true);
        Toast.makeText(this.aH.getApplicationContext(), this.aH.getString(R.string.app_download_fail), 0).show();
    }

    private void v() {
        if (this.bd != null) {
            this.bd.c();
        }
    }

    private void w() {
        if (this.bd != null) {
            this.bd.d();
            this.bd.a();
            this.bd = null;
            this.bf = null;
            DataCameraSetMode.getInstance().a(MODE.PLAYBACK).start(this.bk);
        }
    }

    private void a(int i, int i2, int i3) {
        if (this.aV == null) {
            this.aV = i.a(this.aH, R.string.fpv_playback_download_tip, this.aH.getString(R.string.app_downloading, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}), i3).e(8);
            this.aV.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJIPlayBackView a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.w();
                }
            });
        } else {
            this.aV.b(this.aH.getString(R.string.app_downloading, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)})).c(this.aH.getString(R.string.app_downloading_pgs, new Object[]{Integer.valueOf(0)})).d(i3);
        }
        if (this.aV != null && !this.aV.isShowing()) {
            t();
            this.aV.show();
        }
    }

    private void b(boolean z) {
        if (this.aV != null && this.aV.isShowing() && !z) {
            this.aV.e(8).b(0);
        }
    }

    private void b(int i, int i2, int i3) {
        if (this.aV != null && this.aV.isShowing()) {
            if (this.a != i) {
                this.a = i;
                this.aV.b(String.format(this.d, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            this.aV.d(i3);
        }
    }

    private void b(int i) {
        if (this.aV != null && this.aV.isShowing()) {
            this.aV.c(String.format(this.c, new Object[]{Integer.valueOf(i)}));
        }
    }

    private void x() {
        w();
        if (this.aV != null && this.aV.isShowing()) {
            this.aV.dismiss();
            this.aV = null;
        }
    }

    private void c(boolean z) {
        if (this.aW == null) {
            this.aW = new l(getContext());
            this.aW.b((int) R.string.playback_download_master_downloading);
        }
        if (z) {
            this.aW.show();
        } else if (this.aW.isShowing()) {
            this.aW.dismiss();
        }
    }

    public void onEventMainThread(dji.setting.ui.rc.RcMasterSlaveView.c cVar) {
        if (cVar.a == DataRcSetMaster.MODE.a) {
            if (this.av != 0) {
                this.ah.show();
            }
            DJILogHelper.getInstance().LOGD("", "rcmode[" + cVar.a + dji.pilot.usercenter.protocol.d.H, false, true);
            return;
        }
        this.ah.go();
    }

    private void y() {
        if (this.au == 2 && (this.av == 6 || this.av == 5)) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_VideoView_Button_Back");
            I();
        } else if (this.aH instanceof DJIBaseActivity) {
            dji.pilot.fpv.d.e.a("FPV_PlayBackView_Button_Back");
            ((DJIBaseActivity) this.aH).finishThis();
        } else {
            go();
        }
    }

    private void z() {
        if ((this.au == 1 && (this.av == 3 || this.av == 2)) || (this.au == 2 && this.av == 4)) {
            if (this.ad.getVisibility() != 0) {
                this.ad.show();
                this.ad.startAnimation(this.aZ);
                this.N.show();
                this.N.startAnimation(this.aX);
                if (this.aF != null) {
                    this.aF.a(false, 0);
                    return;
                }
                return;
            }
            this.ad.go();
            this.ad.startAnimation(this.ba);
            this.N.go();
            this.N.startAnimation(this.aY);
            if (this.aF != null) {
                this.aF.a(true, 0);
            }
        } else if (this.au != 2) {
        } else {
            if (this.av != 6 && this.av != 5) {
                return;
            }
            if (this.aj.getVisibility() != 0) {
                this.aj.show();
                this.aj.startAnimation(this.aZ);
                this.N.show();
                this.N.startAnimation(this.aX);
                if (this.aF != null) {
                    this.aF.a(false, 0);
                    return;
                }
                return;
            }
            this.aj.go();
            this.aj.startAnimation(this.ba);
            this.N.go();
            this.N.startAnimation(this.aY);
            if (this.aF != null) {
                this.aF.a(true, 0);
            }
        }
    }

    private void A() {
        if (2 == this.au && 4 == this.av) {
            dji.pilot.fpv.d.e.a("FPV_Playback_SingleView_Button_StartPlayVideo");
            this.at.setPlayBackBrowserType(PlayBrowseType.ENTER, (byte) 0, (byte) 0).start(e);
        }
    }

    private void B() {
        if (this.au != 0 || 1 != this.av) {
            return;
        }
        if (this.ar.isCurPageSelected()) {
            this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY_DEL, MulDelValue.PAGE_CANCEL.value(), (byte) 0).start(e);
        } else {
            this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY_DEL, MulDelValue.PAGE_SELECT.value(), (byte) 0).start(e);
        }
    }

    private void C() {
        if (this.au == 0) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_MultipleView_Button_SelectAll");
            if (1 != this.av) {
                return;
            }
            if (this.ar.getDeleteChioceNum() == this.ar.getTotalNum()) {
                this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY_DEL, MulDelValue.ALL_CANCEL.value(), (byte) 0).start(e);
            } else {
                this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY_DEL, MulDelValue.ALL_SELECT.value(), (byte) 0).start(e);
            }
        } else if (2 != this.au && 1 == this.au) {
        }
    }

    private void D() {
        if (this.au == 0) {
            dji.pilot.fpv.d.e.a("FPV_PlayBackView_MultipleView_Button_Delete");
            if (1 == this.av) {
                k();
            } else if (this.av == 0) {
                this.at.setPlayBackBrowserType(PlayBrowseType.DELETE, (byte) 0, (byte) 0).start(e);
            }
        } else if (1 == this.au) {
            dji.pilot.fpv.d.e.a("FPV_PlayBackView_SingleView_Button_Delete");
            k();
        } else if (2 == this.au) {
            dji.pilot.fpv.d.e.a("FPV_PlayBackView_SingleView_Button_Delete");
            k();
        }
    }

    private void E() {
        if (this.au == 0) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackVew_MultipleView_Button_DownloadFiles");
        } else {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_SingleView_Button_Download");
        }
        p();
    }

    private void F() {
        if (this.au == 0) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_MultipleView_Button_Edit");
            if (1 == this.av) {
                this.at.setPlayBackBrowserType(PlayBrowseType.CANCEL, (byte) 0, (byte) 0).start(e);
            } else if (this.av == 0) {
                this.at.setPlayBackBrowserType(PlayBrowseType.DELETE, (byte) 0, (byte) 0).start(e);
            }
        } else if (2 == this.au) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_SingleView_Button_EnterMultipleView");
            this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY, (byte) 0, (byte) 0).start(e);
        } else if (1 == this.au) {
            dji.pilot.fpv.d.e.a("FPV_PlaybackView_SingleView_Button_EnterMultipleView");
            this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY, (byte) 0, (byte) 0).start(e);
        }
    }

    private void G() {
        this.aH.startActivity(new Intent(this.aH, DJIPBAlbumActivity.class));
    }

    private void H() {
        if (5 == this.av) {
            this.at.setPlayBackBrowserType(PlayBrowseType.ENTER, (byte) 0, (byte) 0).start(e);
        } else if (6 == this.av) {
            this.at.setPlayBackBrowserType(PlayBrowseType.ENTER, (byte) 0, (byte) 0).start(e);
        }
    }

    private void I() {
        this.at.setPlayBackBrowserType(PlayBrowseType.UP, (byte) 0, (byte) 0).start(e);
    }

    private void J() {
        this.at.setPlayBackPlayCtr(PlayCtrType.TouchProgress, (byte) ((this.am.getProgress() * 100) / this.am.getMax())).start(e);
    }

    private void K() {
        this.af.setText(this.aH.getString(R.string.fpv_playback_scale_param, new Object[]{this.aG.format((double) this.ar.getZoomSize())}));
    }

    private void a(int i, int i2) {
        this.Q.setText(this.aH.getString(R.string.fpv_playback_selected, new Object[]{Integer.valueOf(i)}));
    }

    private void b(int i, int i2) {
        this.Q.setText(this.aH.getString(R.string.fpv_playback_number, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    private void c(int i, int i2) {
        this.V.setText(String.valueOf(i));
        this.U.setText(String.valueOf(i2));
    }

    private void c(int i) {
        d(i, 100);
    }

    private void d(int i, int i2) {
        this.am.setMax(i2);
        this.am.setProgress(i);
    }

    private String d(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return this.aH.getString(R.string.fpv_videotime, new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }

    private void e(int i, int i2) {
        this.an.setText(this.aH.getString(R.string.fpv_playback_videotime, new Object[]{d(i), d(i2)}));
    }

    private void f(int i, int i2) {
        DJILogHelper.getInstance().LOGD(l, "index[" + i + "]count[" + i2 + dji.pilot.usercenter.protocol.d.H, false, true);
    }

    private void d(boolean z) {
        DJILogHelper.getInstance().LOGD(l, "Single Download[" + z + dji.pilot.usercenter.protocol.d.H, false, true);
        if (z) {
            this.R.show();
        } else {
            this.R.go();
        }
    }

    private void e(boolean z) {
        this.ah.setEnabled(z);
    }

    private void g(int i, int i2) {
        int i3 = i2 == 0 ? 1 : ((i2 - 1) / 8) + 1;
        int i4 = i == 0 ? 1 : ((i - 1) / 8) + 1;
        this.ac.setIndex(i4).setMax(i3).updateState();
        DJILogHelper.getInstance().LOGD(l, "index[" + i + "]count[" + i2 + "]cp[" + i4 + "]pn[" + i3 + "]ch[" + this.az + dji.pilot.usercenter.protocol.d.H, false, true);
    }

    private void e(int i) {
        if (i > 0) {
            this.ag.setEnabled(true);
            this.ah.setEnabled(true);
            return;
        }
        this.ag.setEnabled(false);
        this.ah.setEnabled(false);
    }

    private void a(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        if (!dataCameraGetPushPlayBackParams.isCurPageSelected()) {
        }
    }

    private void b(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        if (dataCameraGetPushPlayBackParams.getTotalNum() == dataCameraGetPushPlayBackParams.getDeleteChioceNum()) {
            this.ae.setImageResource(R.drawable.fpv_playback_selectall);
        } else {
            this.ae.setImageResource(R.drawable.fpv_playback_unselectall);
        }
    }

    private void a(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams, boolean z) {
        int i = 2;
        FileType fileType = dataCameraGetPushPlayBackParams.getFileType();
        int ordinal = fileType.ordinal();
        DJILogHelper.getInstance().LOGD("", "type[" + fileType.toString() + "]ordinal[" + ordinal + "]str[" + fileType.toString() + dji.pilot.usercenter.protocol.d.H, false, true);
        if (FileType.JPEG.ordinal() == ordinal || FileType.DNG.ordinal() == ordinal) {
            a(1, false, false);
            if (z) {
                i = 3;
            }
            b(i, false);
            e(FileType.DNG.ordinal() != ordinal);
        } else if (FileType.VIDEO.ordinal() == ordinal) {
            a(2, false, false);
            c(4, false);
            e(true);
        }
        i = dataCameraGetPushPlayBackParams.getIndex();
        int totalNum = dataCameraGetPushPlayBackParams.getTotalNum();
        K();
        b(i, totalNum);
        f(i, totalNum);
        d(dataCameraGetPushPlayBackParams.isSingleDownloaded());
    }

    private void a(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams, int i) {
        a(2, false, false);
        c(i, false);
        int progress = dataCameraGetPushPlayBackParams.getProgress();
        if (progress >= 0 && progress <= 100) {
            c(progress);
        }
        progress = dataCameraGetPushPlayBackParams.getIndex();
        int totalNum = dataCameraGetPushPlayBackParams.getTotalNum();
        e(dataCameraGetPushPlayBackParams.getCurrent(), dataCameraGetPushPlayBackParams.getTotalTime());
        b(progress, totalNum);
        f(progress, totalNum);
    }

    private void b(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams, int i) {
        a(0, false, false);
        a(i, false);
        int index = dataCameraGetPushPlayBackParams.getIndex();
        int totalNum = dataCameraGetPushPlayBackParams.getTotalNum();
        int deleteChioceNum = dataCameraGetPushPlayBackParams.getDeleteChioceNum();
        if (deleteChioceNum == 66535) {
            a(0, totalNum);
        } else {
            a(deleteChioceNum, totalNum);
        }
        c(dataCameraGetPushPlayBackParams.getTotalVideoNum(), dataCameraGetPushPlayBackParams.getTotalPhotoNum());
        g(index, totalNum);
        if (i == 1) {
            a(dataCameraGetPushPlayBackParams);
            b(dataCameraGetPushPlayBackParams);
            e(deleteChioceNum);
        }
    }

    private void f(int i) {
        if (this.au == 0) {
            if (this.av == 0) {
                this.at.setPlayBackBrowserType(PlayBrowseType.SINGLE, (byte) i, (byte) 0).start(e);
            } else if (1 == this.av) {
                this.at.setPlayBackBrowserType(PlayBrowseType.MULTIPLY_DEL, (byte) i, (byte) 0).start(e);
            }
            DJILogHelper.getInstance().LOGE(l, "SubMode[" + this.av + "]PreviewItemClick[" + i + dji.pilot.usercenter.protocol.d.H, false, true);
        }
    }

    private void c(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        a(dataCameraGetPushPlayBackParams, false);
    }

    private void d(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        a(dataCameraGetPushPlayBackParams, true);
    }

    private void e(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        a(dataCameraGetPushPlayBackParams, 5);
    }

    private void f(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        a(dataCameraGetPushPlayBackParams, 6);
    }

    private void g(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        b(dataCameraGetPushPlayBackParams, 0);
        if (dataCameraGetPushPlayBackParams.getDelFileStatus() == DelFileStatus.COMPLETED) {
            o();
        }
    }

    private void h(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        b(dataCameraGetPushPlayBackParams, 1);
        if (dataCameraGetPushPlayBackParams.getDelFileStatus() == DelFileStatus.COMPLETED) {
            o();
        }
    }

    private void i(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        a(3, false, false);
    }

    private void f(boolean z) {
        if (this.aQ == 0 || this.aQ == 1) {
            DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams = this.ar;
            int totalNum = dataCameraGetPushPlayBackParams.getTotalNum();
            DJILogHelper.getInstance().LOGD("", "total=" + totalNum + "]del[" + dataCameraGetPushPlayBackParams.getDelFileStatus() + dji.pilot.usercenter.protocol.d.H, false, true);
            if (totalNum > 0) {
                this.aQ &= -2;
                DataCameraGetPushPlayBackParams.MODE mode = dataCameraGetPushPlayBackParams.getMode();
                DJILogHelper.getInstance().LOGD("", "playback=" + mode.toString() + "]del[" + dataCameraGetPushPlayBackParams.getDelFileStatus() + dji.pilot.usercenter.protocol.d.H, false, true);
                if (DataCameraGetPushPlayBackParams.MODE.Single == mode) {
                    c(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.SingleLarge == mode) {
                    d(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.SinglePlay == mode) {
                    e(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.SinglePause == mode) {
                    f(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.Multiple == mode) {
                    g(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.MultipleDel == mode) {
                    h(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.Download == mode) {
                    i(dataCameraGetPushPlayBackParams);
                    return;
                } else if (DataCameraGetPushPlayBackParams.MODE.OTHER == mode && z && this.aQ == 0) {
                    a(1, true, false);
                    b(2, false);
                    K();
                    return;
                } else {
                    return;
                }
            }
            this.aQ |= 1;
            i();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams) {
        if (this.bl != dataCameraGetPushPlayBackParams.getDeleteChioceNum() || dataCameraGetPushPlayBackParams.getMode().equals(this.bm)) {
            this.bl = dataCameraGetPushPlayBackParams.getDeleteChioceNum();
            this.bm = dataCameraGetPushPlayBackParams.getMode();
            DJILogHelper.getInstance().LOGD("", "choice num=" + this.bl + " mode=" + this.bm);
        }
        DJILogHelper.getInstance().LOGD(l, "playback index[" + dataCameraGetPushPlayBackParams.getIndex() + "]count[" + dataCameraGetPushPlayBackParams.getTotalNum() + dji.pilot.usercenter.protocol.d.H, false, false);
        if (!this.aA.hasMessages(8192)) {
            this.aA.sendEmptyMessageDelayed(8192, 100);
        }
    }

    private void g(boolean z) {
        boolean sDCardInsertState = this.as.getSDCardInsertState();
        int i = this.aQ;
        if (sDCardInsertState) {
            SDCardState sDCardState = this.as.getSDCardState();
            if (sDCardState == SDCardState.None) {
                this.aQ |= 2;
            } else {
                this.aQ &= -3;
                if (sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal || sDCardState == SDCardState.Unknow) {
                    this.aQ |= 4;
                } else {
                    this.aQ &= -5;
                }
            }
        } else {
            this.aQ |= 2;
        }
        if (i == this.aQ && !z) {
            return;
        }
        if (this.aQ == 0) {
            a(this.au, true, false);
            if (this.au == 1) {
                b(this.av, true);
                return;
            } else if (this.au == 0) {
                a(this.av, true);
                return;
            } else if (this.au == 2) {
                c(this.av, true);
                return;
            } else {
                return;
            }
        }
        i();
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (!this.aA.hasMessages(12288)) {
            this.aA.sendEmptyMessageDelayed(12288, 100);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == this.am) {
            J();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.au == 0 && i < this.ar.getFileNum()) {
            if (1 == this.av) {
                this.aI.a(com.dji.frame.common.b.b);
            }
            long j2 = r;
            if (this.aA.hasMessages(16384)) {
                j2 = 136;
            }
            this.aA.sendMessageDelayed(this.aA.obtainMessage(16384, i, 0), j2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean isBackPBack() {
        return this.be;
    }

    public void setIsBackPBack(boolean z) {
        this.be = z;
    }
}
