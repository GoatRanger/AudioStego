package dji.pilot2.cutmoment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.media.i.g;
import dji.pilot.R;
import dji.pilot2.utils.p;
import dji.pilot2.utils.q;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

@SuppressLint({"HandlerLeak"})
public class DJICutTagBar extends HorizontalScrollView {
    private static final String c = "DJICutTagBar";
    private static final int d = 0;
    private static final int e = 100;
    private static final int f = 101;
    private static final int g = 2;
    private static final int h = 1;
    private static final int i = 3;
    private static final int j = 4;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 2;
    private static final int n = 1;
    private static final int o = 3;
    private static final int p = 20;
    private float A;
    private int B = 0;
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private boolean F = false;
    private int G = 4;
    private boolean H = false;
    private boolean I = false;
    private boolean J = true;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private Handler Q = new Handler(this) {
        final /* synthetic */ DJICutTagBar a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.a.u != null) {
                this.a.u.a();
            }
        }
    };
    private Handler R = new Handler(this) {
        final /* synthetic */ DJICutTagBar a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i;
            switch (message.what) {
                case 2:
                    this.a.a(30, message.arg1, message.arg2);
                    this.a.a(this.a.y);
                    i = message.arg1;
                    int i2 = message.arg2;
                    if (hasMessages(message.what)) {
                        removeMessages(message.what);
                    }
                    Message obtainMessage = obtainMessage();
                    obtainMessage.what = 2;
                    obtainMessage.arg1 = i;
                    obtainMessage.arg2 = i2;
                    sendMessageDelayed(obtainMessage, 1);
                    return;
                case 3:
                    if (hasMessages(message.what)) {
                        removeMessages(message.what);
                    }
                    i = this.a.getScrollX();
                    if (message.arg1 == i) {
                        this.a.I = false;
                        this.a.a(message.arg1 + (this.a.getDisplayWidth() / 4), 0);
                        if (this.a.H) {
                            float d = ((float) message.arg1) * (this.a.A / this.a.t);
                            long j = ((a) this.a.y.getTag()).e;
                            long j2 = ((a) this.a.y.getTag()).f;
                            if (((DJICutMomentActivity) this.a.q).f()) {
                                ((DJICutMomentActivity) this.a.q).g();
                                return;
                            } else if (d > ((float) j2) || d < ((float) j)) {
                                this.a.Q.postDelayed(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass2 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        if (this.a.a.u != null) {
                                            this.a.a.u.a(this.a.a.y, true);
                                        }
                                    }
                                }, 0);
                                return;
                            } else {
                                this.a.Q.sendEmptyMessageAtTime(0, 20);
                                return;
                            }
                        } else if (((DJICutMomentActivity) this.a.q).f()) {
                            ((DJICutMomentActivity) this.a.q).g();
                            return;
                        } else {
                            this.a.Q.sendEmptyMessageAtTime(0, 20);
                            return;
                        }
                    }
                    Message obtainMessage2 = obtainMessage();
                    obtainMessage2.what = 3;
                    obtainMessage2.arg1 = i;
                    sendMessageDelayed(obtainMessage2, 20);
                    return;
                default:
                    return;
            }
        }
    };
    public int a;
    public int b;
    private Context q;
    private WindowManager r;
    private b s;
    private float t;
    private b u;
    private LayoutInflater v;
    private String w;
    private RelativeLayout x;
    private View y;
    private float z;

    public interface b {
        void a();

        void a(float f);

        void a(View view, boolean z);

        void b();

        void c();

        void d();

        void e();
    }

    class a extends AsyncTask<Void, Void, Void> {
        ArrayList<ArrayList<Integer>> a;
        final /* synthetic */ DJICutTagBar b;

        a(DJICutTagBar dJICutTagBar) {
            this.b = dJICutTagBar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected void a(Void voidR) {
            super.onPostExecute(voidR);
        }

        protected Void a(Void... voidArr) {
            q.a(this.b.q, this.b.w, this.a, this.b.x);
            return null;
        }

        protected void onPreExecute() {
            this.a = this.b.b(((long) this.b.A) / 1000);
            int i = 0;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                i += ((ArrayList) this.a.get(i2)).size();
            }
            for (int i3 = 0; i3 < i; i3++) {
                View inflate = this.b.v.inflate(R.layout.segment_making_scroll_item, null);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.b.E, this.b.D);
                layoutParams.leftMargin = (int) (((float) (this.b.getDisplayWidth() / 4)) + this.b.t);
                layoutParams.bottomMargin = 0;
                inflate.setLayoutParams(layoutParams);
                this.b.x.addView(inflate);
                this.b.t = ((float) layoutParams.width) + this.b.t;
            }
            this.b.B = i + 2;
            View imageView = new ImageView(this.b.q);
            imageView.setBackgroundColor(0);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((this.b.getDisplayWidth() * 3) / 4, this.b.D);
            layoutParams2.leftMargin = (int) (((float) (this.b.getDisplayWidth() / 4)) + this.b.t);
            imageView.setLayoutParams(layoutParams2);
            this.b.x.addView(imageView);
            this.b.u.d();
            this.b.z = this.b.t + ((float) this.b.getDisplayWidth());
            this.b.a = this.b.TimeToLength(a.c, (long) this.b.A);
            this.b.b = this.b.TimeToLength(a.d, (long) this.b.A);
            super.onPreExecute();
        }
    }

    public DJICutTagBar(Context context) {
        super(context);
        this.q = context;
        new a(this).execute(new Void[0]);
    }

    public DJICutTagBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = context;
        this.v = LayoutInflater.from(context);
        this.r = (WindowManager) this.q.getSystemService("window");
        this.w = ((DJICutMomentActivity) this.q).b();
        g aVar = new dji.g.b.a();
        aVar.j();
        aVar.a(this.w);
        aVar.b();
        this.A = (float) aVar.g();
        getDisplayWidth();
    }

    private void a(int i) {
        this.I = true;
        Message obtainMessage = this.R.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.arg1 = i;
        this.R.sendMessageDelayed(obtainMessage, 20);
    }

    private void a(final int i, int i2) {
        this.Q.postDelayed(new Runnable(this) {
            final /* synthetic */ DJICutTagBar b;

            public void run() {
                if (this.b.u != null) {
                    this.b.u.a((this.b.A / this.b.t) * ((float) (i - (this.b.getDisplayWidth() / 4))));
                }
            }
        }, (long) i2);
    }

    private void a(int i, int i2, int i3) {
        if (this.y != null) {
            int left;
            int right;
            LayoutParams layoutParams;
            if (i2 == 1 && i3 == 1) {
                left = this.y.getLeft();
                right = this.y.getRight();
                if (left - i >= getDisplayWidth() / 4 && !a(this.y, left - i, right) && this.y.getWidth() + i <= this.b) {
                    scrollBy(-i, 0);
                    layoutParams = new RelativeLayout.LayoutParams(this.y.getWidth() + i, this.y.getHeight());
                    layoutParams.leftMargin = left - i;
                    this.y.findViewById(R.id.cjf).getLayoutParams().width = this.y.getWidth() + i;
                    this.y.setLayoutParams(layoutParams);
                    this.K = this.y.getLeft() - getScrollX();
                    a(this.y.getLeft(), 0);
                    a(this.y, true);
                }
            } else if (i2 == 3 && i3 == 2) {
                left = this.y.getLeft();
                right = this.y.getRight();
                if (((float) (right + i)) <= this.t + ((float) (getDisplayWidth() / 4)) && !a(this.y, left, right + i) && this.y.getWidth() + i <= this.b) {
                    scrollBy(i, 0);
                    layoutParams = new RelativeLayout.LayoutParams(this.y.getWidth() + i, this.y.getHeight());
                    layoutParams.leftMargin = left;
                    this.y.findViewById(R.id.cjf).getLayoutParams().width = this.y.getWidth() + i;
                    this.y.setLayoutParams(layoutParams);
                    this.K = this.y.getRight() - getScrollX();
                    a(this.y.getRight(), 0);
                    a(this.y, true);
                }
            } else if (i2 == 1 && i3 == 2) {
                left = this.y.getLeft();
                right = this.y.getRight();
                if (this.y.getWidth() - i >= this.a && !a(this.y, left + i, right)) {
                    scrollBy(i, 0);
                    layoutParams = new RelativeLayout.LayoutParams(this.y.getWidth() - i, this.y.getHeight());
                    layoutParams.leftMargin = left + i;
                    this.y.findViewById(R.id.cjf).getLayoutParams().width = this.y.getWidth() - i;
                    this.y.setLayoutParams(layoutParams);
                    this.K = this.y.getLeft() - getScrollX();
                    a(this.y.getLeft(), 0);
                    a(this.y, false);
                }
            } else if (i2 == 3 && i3 == 1) {
                left = this.y.getLeft();
                right = this.y.getRight();
                if (this.y.getWidth() - i >= this.a && !a(this.y, left, right - i)) {
                    scrollBy(-i, 0);
                    layoutParams = new RelativeLayout.LayoutParams(this.y.getWidth() - i, this.y.getHeight());
                    layoutParams.leftMargin = left;
                    this.y.findViewById(R.id.cjf).getLayoutParams().width = this.y.getWidth() - i;
                    this.y.setLayoutParams(layoutParams);
                    this.K = this.y.getRight() - getScrollX();
                    a(this.y.getRight(), 0);
                    a(this.y, false);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x;
        int y;
        switch (motionEvent.getAction()) {
            case 0:
                DJILogHelper.getInstance().LOGI(c, "b MotionEvent.ACTION_DOWN");
                x = (int) motionEvent.getX();
                y = (int) motionEvent.getY();
                this.Q.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJICutTagBar a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.u != null) {
                            this.a.u.b();
                        }
                    }
                }, 0);
                DJILogHelper.getInstance().LOGI(c, "b MotionEvent.ACTION_DOWN mThumbPicturesTotalNum=" + this.B + " mGallery.getChildCount()=" + this.x.getChildCount());
                if (this.B < this.x.getChildCount()) {
                    if (!a()) {
                        this.y = null;
                        this.G = checkInTagTouch(x, y, this.x.getChildCount() - this.B);
                        if (this.G != 2) {
                            if (this.G != 1) {
                                if (this.G != 3) {
                                    if (this.G == 4) {
                                        this.F = false;
                                        this.H = false;
                                        this.y = null;
                                        break;
                                    }
                                }
                                this.K = x;
                                b(this.y);
                                this.y.setPressed(true);
                                a(this.y, true);
                                ((DJICutMomentActivity) this.q).a(this.y.getRight() - getScrollX());
                                this.u.a(this.y, false);
                                Log.i(c, "down x: " + x);
                                Log.i(c, "down rightEdge: " + (this.y.getRight() - getScrollX()));
                                this.F = true;
                                this.H = false;
                                break;
                            }
                            this.K = x;
                            b(this.y);
                            this.y.setPressed(true);
                            a(this.y, true);
                            ((DJICutMomentActivity) this.q).a(this.y.getLeft() - getScrollX());
                            this.u.a(this.y, false);
                            this.F = true;
                            this.H = false;
                            break;
                        }
                        this.K = x;
                        this.F = false;
                        this.H = false;
                        break;
                    }
                    this.G = isTouchInSeg(this.y, x, y);
                    Log.i(c, "isTouchInSeg " + this.G);
                    if (this.G != 1) {
                        if (this.G != 3) {
                            if (this.G == 2 || this.G == 4) {
                                this.F = false;
                                this.H = true;
                                break;
                            }
                        }
                        this.K = x;
                        this.F = true;
                        this.H = true;
                        break;
                    }
                    this.K = x;
                    this.F = true;
                    this.H = true;
                    break;
                }
                this.y = null;
                this.F = false;
                this.H = false;
                this.G = 4;
                break;
                break;
            case 1:
                DJILogHelper.getInstance().LOGI(c, "MotionEvent.ACTION_UP");
                y = getScrollX();
                if (this.B >= this.x.getChildCount()) {
                    a((getDisplayWidth() / 4) + y, 0);
                    a(y);
                } else if (this.H) {
                    if (this.G == 1 || this.G == 3) {
                        this.Q.postDelayed(new Runnable(this) {
                            final /* synthetic */ DJICutTagBar a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.u != null) {
                                    this.a.a(this.a.y);
                                    this.a.u.a(this.a.y, true);
                                    ((DJICutMomentActivity) this.a.q).a(this.a.getDisplayWidth() / 4);
                                }
                            }
                        }, 0);
                        this.G = 4;
                        a(this.y, true);
                    } else if (this.G == 2 || this.G == 4) {
                        a((getDisplayWidth() / 4) + y, 0);
                        a(y);
                    }
                } else if (this.G == 2) {
                    if (Math.abs(motionEvent.getX() - ((float) this.K)) <= ((float) this.L)) {
                        b(this.y);
                        this.y.setPressed(true);
                        a(this.y, true);
                        this.Q.postDelayed(new Runnable(this) {
                            final /* synthetic */ DJICutTagBar a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.u != null) {
                                    this.a.u.a(this.a.y, true);
                                }
                            }
                        }, 0);
                    } else {
                        a((getDisplayWidth() / 4) + y, 0);
                        a(y);
                    }
                } else if (this.G == 1 || this.G == 3) {
                    this.Q.postDelayed(new Runnable(this) {
                        final /* synthetic */ DJICutTagBar a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.u != null) {
                                this.a.a(this.a.y);
                                this.a.u.a(this.a.y, true);
                                ((DJICutMomentActivity) this.a.q).a(this.a.getDisplayWidth() / 4);
                            }
                        }
                    }, 0);
                    this.G = 4;
                    a(this.y, true);
                } else if (this.G == 4) {
                    a((getDisplayWidth() / 4) + y, 0);
                    a(y);
                }
                if (this.R.hasMessages(2)) {
                    this.R.removeMessages(2);
                    break;
                }
                break;
            case 2:
                Log.i(c, "B MotionEvent.ACTION_MOVE mIsTagDragged=" + this.F);
                if (!this.F) {
                    a(getScrollX() + (getDisplayWidth() / 4), 0);
                    break;
                }
                x = (int) motionEvent.getX();
                y = this.y.getWidth();
                int left = this.y.getLeft();
                int right = this.y.getRight();
                int scrollX = getScrollX();
                int i;
                LayoutParams layoutParams;
                Message obtainMessage;
                if (this.G != 3) {
                    if (this.G == 1) {
                        left = (this.K - x) + y;
                        i = right - left;
                        if (x > (right - y) - scrollX) {
                            if (x - this.K <= 0) {
                                if (x - this.K <= 0) {
                                    this.K = x;
                                    break;
                                }
                            }
                            if (left > this.a && left < this.b && this.y != null) {
                                layoutParams = new RelativeLayout.LayoutParams(left, this.y.getHeight());
                                layoutParams.leftMargin = i;
                                this.y.findViewById(R.id.cjf).getLayoutParams().width = left;
                                this.y.setLayoutParams(layoutParams);
                                a(this.y);
                                ((DJICutMomentActivity) this.q).a(i - scrollX);
                                a(i, 0);
                                if (i - scrollX < this.O) {
                                    if (!this.R.hasMessages(2)) {
                                        obtainMessage = this.R.obtainMessage();
                                        obtainMessage.what = 2;
                                        obtainMessage.arg1 = 1;
                                        obtainMessage.arg2 = 1;
                                        this.R.sendMessageDelayed(obtainMessage, 1);
                                        a(this.y, true);
                                    }
                                } else if (i - scrollX <= getDisplayWidth() - this.O) {
                                    if (this.R.hasMessages(2)) {
                                        this.R.removeMessages(2);
                                    }
                                    a(this.y, true);
                                } else if (!this.R.hasMessages(2)) {
                                    obtainMessage = this.R.obtainMessage();
                                    obtainMessage.what = 2;
                                    obtainMessage.arg1 = 1;
                                    obtainMessage.arg2 = 2;
                                    this.R.sendMessageDelayed(obtainMessage, 1);
                                    a(this.y, false);
                                }
                                if (i - scrollX > getDisplayWidth() - this.P) {
                                    a(this.y, false);
                                }
                            }
                            this.K = x;
                            break;
                        } else if (left > this.a && left < this.b && this.y != null && i >= getDisplayWidth() / 4 && !a(this.y, i, right)) {
                            layoutParams = new RelativeLayout.LayoutParams(left, this.y.getHeight());
                            layoutParams.leftMargin = i;
                            this.y.findViewById(R.id.cjf).getLayoutParams().width = left;
                            this.y.setLayoutParams(layoutParams);
                            a(this.y);
                            ((DJICutMomentActivity) this.q).a(i - scrollX);
                            this.K = i - scrollX;
                            a(i, 0);
                            if (i - scrollX < this.O) {
                                if (!this.R.hasMessages(2)) {
                                    obtainMessage = this.R.obtainMessage();
                                    obtainMessage.what = 2;
                                    obtainMessage.arg1 = 1;
                                    obtainMessage.arg2 = 1;
                                    this.R.sendMessageDelayed(obtainMessage, 1);
                                    a(this.y, true);
                                }
                            } else if (i - scrollX <= getDisplayWidth() - this.O) {
                                if (this.R.hasMessages(2)) {
                                    this.R.removeMessages(2);
                                }
                                a(this.y, true);
                            } else if (!this.R.hasMessages(2)) {
                                obtainMessage = this.R.obtainMessage();
                                obtainMessage.what = 2;
                                obtainMessage.arg1 = 1;
                                obtainMessage.arg2 = 2;
                                this.R.sendMessageDelayed(obtainMessage, 1);
                                a(this.y, false);
                            }
                            if (i - scrollX > getDisplayWidth() - this.P) {
                                a(this.y, false);
                                break;
                            }
                        }
                    }
                }
                right = (x - this.K) + y;
                i = left + right;
                Log.i(c, "move mOld: " + this.K);
                Log.i(c, "move newX: " + x);
                Log.i(c, "move dx: " + (x - this.K));
                if (x < (y + left) - scrollX) {
                    if (x - this.K >= 0) {
                        if (x - this.K >= 0) {
                            this.K = x;
                            break;
                        }
                    }
                    if (right > this.a && right < this.b && this.y != null) {
                        layoutParams = new RelativeLayout.LayoutParams(right, this.y.getHeight());
                        layoutParams.leftMargin = left;
                        this.y.findViewById(R.id.cjf).getLayoutParams().width = right;
                        this.y.setLayoutParams(layoutParams);
                        a(this.y);
                        ((DJICutMomentActivity) this.q).a(i - scrollX);
                        a(i, 0);
                        if (i - scrollX > getDisplayWidth() - this.O) {
                            if (!this.R.hasMessages(2)) {
                                obtainMessage = this.R.obtainMessage();
                                obtainMessage.what = 2;
                                obtainMessage.arg1 = 3;
                                obtainMessage.arg2 = 2;
                                this.R.sendMessageDelayed(obtainMessage, 1);
                                a(this.y, true);
                            }
                        } else if (i - scrollX >= this.O) {
                            if (this.R.hasMessages(2)) {
                                this.R.removeMessages(2);
                            }
                            a(this.y, true);
                        } else if (!this.R.hasMessages(2)) {
                            obtainMessage = this.R.obtainMessage();
                            obtainMessage.what = 2;
                            obtainMessage.arg1 = 3;
                            obtainMessage.arg2 = 1;
                            this.R.sendMessageDelayed(obtainMessage, 1);
                            a(this.y, false);
                        }
                        if (i - scrollX < this.P) {
                            a(this.y, false);
                        }
                    }
                    this.K = x;
                    break;
                } else if (right > this.a && right < this.b && this.y != null && ((float) i) <= this.t + ((float) (getDisplayWidth() / 4)) && !a(this.y, left, i)) {
                    layoutParams = this.y.getLayoutParams();
                    layoutParams.width = right;
                    this.y.findViewById(R.id.cjf).getLayoutParams().width = right;
                    this.y.setLayoutParams(layoutParams);
                    a(this.y);
                    ((DJICutMomentActivity) this.q).a(i - scrollX);
                    this.K = i - scrollX;
                    a(i, 0);
                    if (i - scrollX > getDisplayWidth() - this.O) {
                        if (!this.R.hasMessages(2)) {
                            obtainMessage = this.R.obtainMessage();
                            obtainMessage.what = 2;
                            obtainMessage.arg1 = 3;
                            obtainMessage.arg2 = 2;
                            this.R.sendMessageDelayed(obtainMessage, 1);
                            a(this.y, true);
                        }
                    } else if (i - scrollX >= this.O) {
                        if (this.R.hasMessages(2)) {
                            this.R.removeMessages(2);
                        }
                        a(this.y, true);
                    } else if (!this.R.hasMessages(2)) {
                        obtainMessage = this.R.obtainMessage();
                        obtainMessage.what = 2;
                        obtainMessage.arg1 = 3;
                        obtainMessage.arg2 = 1;
                        this.R.sendMessageDelayed(obtainMessage, 1);
                        a(this.y, false);
                    }
                    if (i - scrollX < this.P) {
                        a(this.y, false);
                        break;
                    }
                }
                break;
        }
        if (this.F) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void comfirmMomentTag() {
        if (this.y != null) {
            this.y.setPressed(false);
            a(this.y, true);
        }
        c(null);
        this.y = null;
        this.H = false;
    }

    private boolean a(View view, int i, int i2) {
        long displayWidth = (long) ((((float) (i - (getDisplayWidth() / 4))) * this.A) / this.t);
        long displayWidth2 = (long) ((((float) (i2 - (getDisplayWidth() / 4))) * this.A) / this.t);
        int size = this.s.c().size();
        if (size <= 1) {
            return false;
        }
        a aVar = (a) view.getTag();
        for (int i3 = 0; i3 < size; i3++) {
            a aVar2 = (a) this.s.c().get(i3);
            if (aVar != aVar2 && ((aVar2.e >= displayWidth && aVar2.e <= displayWidth2) || (aVar2.f >= displayWidth && aVar2.f <= displayWidth2))) {
                return true;
            }
        }
        return false;
    }

    protected void a(View view) {
        long j = 0;
        if (view != null) {
            a aVar = (a) view.getTag();
            int left = view.getLeft();
            int width = view.getWidth() + left;
            long displayWidth = (long) ((((float) (left - (getDisplayWidth() / 4))) * this.A) / this.t);
            if (displayWidth < 0) {
                displayWidth = 0;
            } else if (displayWidth > ((long) this.A)) {
                displayWidth = (long) this.A;
            }
            long displayWidth2 = (long) ((((float) (width - (getDisplayWidth() / 4))) * this.A) / this.t);
            if (displayWidth2 >= 0) {
                if (displayWidth2 > ((long) this.A)) {
                    j = (long) this.A;
                } else {
                    j = displayWidth2;
                }
            }
            this.s.a(aVar);
            a aVar2 = new a();
            if (a.b(displayWidth, j, this.s, aVar2) == 1) {
                changeCutPoint(this.s);
                if (this.u != null) {
                    this.u.c();
                    return;
                }
                return;
            }
            view.setTag(aVar2);
        }
    }

    private void a(View view, boolean z) {
        if (view != null) {
            DJITextView dJITextView = (DJITextView) view.findViewById(R.id.cjg);
            if (z) {
                dJITextView.setVisibility(0);
                dJITextView.setText(p.e((int) (((float) ((((a) view.getTag()).f - ((a) view.getTag()).e) + 100)) / 1000.0f)));
                if (view.isPressed()) {
                    dJITextView.setTextColor(-16777216);
                } else {
                    dJITextView.setTextColor(-1);
                }
                int width = (view.getWidth() / 2) - (dJITextView.getWidth() / 2);
                int left = (view.getLeft() + width) - getScrollX();
                int width2 = dJITextView.getWidth();
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                dJITextView.setLayoutParams(layoutParams);
                if (this.G != 1 && this.G != 3) {
                    return;
                }
                if (left < this.N) {
                    LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.addRule(15);
                    layoutParams2.addRule(9);
                    layoutParams2.leftMargin = (width + this.N) - left;
                    dJITextView.setLayoutParams(layoutParams2);
                    return;
                } else if (left + width2 > getDisplayWidth() - this.N) {
                    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(15);
                    layoutParams.addRule(11);
                    layoutParams.rightMargin = (view.getWidth() - width2) - ((((width + getDisplayWidth()) - this.N) - left) - width2);
                    dJITextView.setLayoutParams(layoutParams);
                    return;
                } else {
                    return;
                }
            }
            dJITextView.setVisibility(4);
        }
    }

    public void delCurCutSegView() {
        if (this.y != null) {
            c(null);
            this.s.a((a) this.y.getTag());
            this.x.removeView(this.y);
            this.y = null;
            this.H = false;
        }
    }

    public int checkInTagTouch(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 4;
        while (i4 < i3) {
            View childAt = this.x.getChildAt(this.B + i4);
            i5 = isTouchInSeg(childAt, i, i2);
            if (i5 == 1 || i5 == 2 || i5 == 3) {
                this.y = childAt;
                Log.i(c, "click in " + i4 + " tag");
                Log.i(c, "mCutTagSelected has been assigned");
                break;
            }
            i4++;
        }
        return i4 < i3 ? i5 : 4;
    }

    private void b(View view) {
        this.x.removeView(view);
        View dJIRelativeLayout = new DJIRelativeLayout((DJICutMomentActivity) this.q);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (this.t + ((float) getDisplayWidth())), view.getHeight());
        layoutParams.leftMargin = 0;
        dJIRelativeLayout.setLayoutParams(layoutParams);
        dJIRelativeLayout.setBackgroundColor(((DJICutMomentActivity) this.q).getResources().getColor(R.color.jx));
        this.x.addView(dJIRelativeLayout);
        this.x.addView(view);
        invalidate();
    }

    private void c(View view) {
        this.x.removeViewAt(this.x.getChildCount() - 2);
        invalidate();
    }

    public int isTouchInSeg(View view, int i, int i2) {
        Rect rect = new Rect(0, 0, 0, 0);
        view.getHitRect(rect);
        Log.i(c, "isTouchInSeg=" + rect.left + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + rect.right + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + rect.top + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + rect.bottom);
        int i3 = rect.right - rect.left;
        Rect rect2 = new Rect(rect.left, rect.top, rect.left + this.M, rect.bottom);
        Rect rect3 = new Rect((rect.left + i3) - this.M, rect.top, rect.right, rect.bottom);
        Rect rect4 = new Rect(rect.left + this.M, rect.top, (i3 + rect.left) - this.M, rect.bottom);
        int scrollX = getScrollX() + i;
        if (rect2.contains(scrollX, i2)) {
            return 1;
        }
        if (rect3.contains(scrollX, i2)) {
            return 3;
        }
        if (rect4.contains(scrollX, i2)) {
            return 2;
        }
        return 4;
    }

    public void setListener(b bVar) {
        this.u = bVar;
    }

    public b getListener() {
        return this.u;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.I) {
            Log.i(c, "change  " + i + " oldl" + i3);
            if (((float) i) <= this.t) {
                final int scrollX = getScrollX();
                this.Q.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJICutTagBar b;

                    public void run() {
                        if (this.b.u != null) {
                            this.b.u.a((this.b.A / this.b.t) * ((float) scrollX));
                        }
                    }
                }, 0);
            }
        }
    }

    public void updateScrollLocation(float f, float f2) {
        this.A = f2;
        setScrollX((int) ((this.t * f) / f2));
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.J) {
            this.J = false;
            this.D = getMeasuredHeight();
            this.E = (this.D * 16) / 9;
            this.x = new RelativeLayout(this.q);
            addView(this.x, new MarginLayoutParams(-2, -1));
            setHorizontalScrollBarEnabled(false);
            View imageView = new ImageView(this.q);
            imageView.setBackgroundColor(0);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(getDisplayWidth() / 4, this.D);
            layoutParams.leftMargin = 0;
            imageView.setLayoutParams(layoutParams);
            this.x.addView(imageView);
            new a(this).execute(new Void[0]);
        }
    }

    public void initView() {
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void changeCutPoint(b bVar) {
        this.s = bVar;
        if (!(this.s == null || this.s.c().isEmpty() || !this.s.e())) {
            int childCount = this.x.getChildCount();
            if (this.B < childCount) {
                this.x.removeViews(this.B, childCount - this.B);
            }
            int size = this.s.c().size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) this.s.c().get(i);
                int a = a(aVar.e);
                int a2 = a(aVar.f);
                View view = (DJIRelativeLayout) this.v.inflate(R.layout.v2_cut_tag, this.x, false);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2 - a, getHeight());
                layoutParams.leftMargin = a;
                view.findViewById(R.id.cjf).getLayoutParams().width = a2 - a;
                view.setLayoutParams(layoutParams);
                view.setTag(aVar);
                this.x.addView(view);
                a(view, true);
            }
            this.s.a(false);
            if (this.u != null) {
                this.u.e();
            }
        }
        invalidate();
    }

    public int TimeToLength(long j, long j2) {
        if (this.A == 0.0f) {
            this.A = (float) j2;
        }
        return (int) ((this.t * ((float) j)) / this.A);
    }

    public int LengthToTime(long j) {
        if (this.t != 0.0f) {
            return (int) ((this.A * ((float) j)) / this.t);
        }
        return 0;
    }

    private int a(long j) {
        float displayWidth = ((this.t * ((float) j)) / this.A) + ((float) (getDisplayWidth() / 4));
        if (displayWidth > this.t + ((float) (getDisplayWidth() / 4))) {
            displayWidth = this.t + ((float) (getDisplayWidth() / 4));
        } else if (displayWidth < ((float) (getDisplayWidth() / 4))) {
            displayWidth = (float) (getDisplayWidth() / 4);
        }
        return (int) displayWidth;
    }

    public void initData(String str, DJICutMomentActivity dJICutMomentActivity) {
        initView();
        this.L = ((DJICutMomentActivity) this.q).getResources().getDimensionPixelSize(R.dimen.ga);
        this.M = ((DJICutMomentActivity) this.q).getResources().getDimensionPixelSize(R.dimen.fo);
        this.N = ((DJICutMomentActivity) this.q).getResources().getDimensionPixelSize(R.dimen.ft);
        this.O = ((DJICutMomentActivity) this.q).getResources().getDimensionPixelSize(R.dimen.gh);
        this.P = ((DJICutMomentActivity) this.q).getResources().getDimensionPixelSize(R.dimen.gx);
    }

    private ArrayList<ArrayList<Integer>> b(long j) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; ((long) i2) < j; i2 += 8) {
            if (arrayList.size() < q.f) {
                Integer num = new Integer(i2);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(num);
                arrayList.add(arrayList2);
            } else {
                ((ArrayList) arrayList.get(i % q.f)).add(new Integer(i2));
            }
            i++;
        }
        return arrayList;
    }

    public int getDisplayWidth() {
        if (this.C == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.r.getDefaultDisplay().getMetrics(displayMetrics);
            this.C = displayMetrics.widthPixels;
        }
        return this.C;
    }

    private boolean a() {
        if (this.x == null || this.x.getChildCount() == 0 || this.B == 0) {
            Log.e(c, "some fields not initialized");
            return false;
        }
        int childCount = this.x.getChildCount() - this.B;
        if (childCount <= 0) {
            Log.w(c, "tags count not right");
            return false;
        }
        Log.i(c, "has cut " + childCount + " tags");
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            if (this.x.getChildAt(this.B + i2).isPressed()) {
                i++;
            }
        }
        if (i == 0) {
            return false;
        }
        if (i == 1) {
            return true;
        }
        Log.w(c, "hightlight tag count not right: " + i);
        return true;
    }

    public void cutTagBitmapClear() {
        int childCount;
        int i = 0;
        if (this.x != null) {
            childCount = this.x.getChildCount();
        } else {
            childCount = 0;
        }
        while (i < childCount) {
            ImageView imageView = (ImageView) this.x.getChildAt(i).findViewById(R.id.bl8);
            if (imageView != null) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                if (bitmapDrawable != null) {
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        bitmap.recycle();
                    }
                }
            }
            i++;
        }
    }

    public void cancelAsyncTask() {
        if (q.d.get() < q.f) {
            q.e = true;
        }
    }

    public boolean isFling() {
        return this.I;
    }
}
