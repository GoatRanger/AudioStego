package dji.pilot.publics.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJIMarqueeTextViewV2 extends DJITextView {
    private static final String a = DJIMarqueeTextView.class.getSimpleName();
    private a b = null;
    private boolean c = false;
    private int d = -1;

    private static final class a {
        private static final int a = 30;
        private static final byte b = (byte) 0;
        private static final byte c = (byte) 1;
        private static final byte d = (byte) 2;
        private static int o = 1200;
        private final WeakReference<TextView> e;
        private final Choreographer f;
        private byte g;
        private final float h;
        private float i;
        private int j;
        private float k;
        private float l;
        private float m;
        private long n;
        private FrameCallback p;
        private FrameCallback q;
        private FrameCallback r;

        public void a(int i) {
            o = i;
        }

        private a(TextView textView) {
            this.g = (byte) 0;
            this.p = new FrameCallback(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void doFrame(long j) {
                    this.a.a();
                }
            };
            this.q = new FrameCallback(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void doFrame(long j) {
                    this.a.g = (byte) 2;
                    this.a.n = SystemClock.uptimeMillis();
                    this.a.a();
                }
            };
            this.r = new FrameCallback(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void doFrame(long j) {
                    if (this.a.g == (byte) 2) {
                        if (this.a.j >= 0) {
                            this.a.j = this.a.j - 1;
                        }
                        this.a.b(this.a.j);
                    }
                }
            };
            this.h = textView.getContext().getResources().getDisplayMetrics().density * 30.0f;
            this.e = new WeakReference(textView);
            this.f = Choreographer.getInstance();
        }

        void a() {
            if (this.g == (byte) 2) {
                this.f.removeFrameCallback(this.p);
                TextView textView = (TextView) this.e.get();
                if (textView != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    long j = uptimeMillis - this.n;
                    this.n = uptimeMillis;
                    this.m = ((((float) j) / 1000.0f) * this.h) + this.m;
                    if (this.m > this.i) {
                        this.m = this.i;
                        this.f.postFrameCallbackDelayed(this.r, (long) o);
                    } else {
                        this.f.postFrameCallback(this.p);
                    }
                    textView.invalidate();
                }
            }
        }

        void b() {
            this.g = (byte) 0;
            this.f.removeFrameCallback(this.q);
            this.f.removeFrameCallback(this.r);
            this.f.removeFrameCallback(this.p);
            h();
        }

        private void h() {
            this.m = 0.0f;
            TextView textView = (TextView) this.e.get();
            if (textView != null) {
                textView.postInvalidate();
            }
        }

        void b(int i) {
            if (i == 0) {
                b();
                return;
            }
            this.j = i;
            TextView textView = (TextView) this.e.get();
            if (textView != null && textView.getLayout() != null) {
                this.g = (byte) 1;
                this.m = 0.0f;
                int width = (textView.getWidth() - textView.getCompoundPaddingLeft()) - textView.getCompoundPaddingRight();
                float lineWidth = textView.getLayout().getLineWidth(0);
                float f = ((float) width) / 3.0f;
                this.k = (lineWidth - ((float) width)) + f;
                this.i = ((float) width) + this.k;
                this.l = lineWidth + f;
                textView.postInvalidate();
                this.f.postFrameCallback(this.q);
            }
        }

        float c() {
            return this.m;
        }

        boolean d() {
            return this.g == (byte) 2;
        }

        boolean e() {
            return this.g == (byte) 0;
        }

        float f() {
            return this.l;
        }

        boolean g() {
            return this.g == (byte) 2 && this.m > this.k;
        }
    }

    public DJIMarqueeTextViewV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TruncateAt ellipsize = getEllipsize();
            if (TruncateAt.MARQUEE == ellipsize) {
                setEllipsize(null);
                setMarqueeRepeatLimit(-1);
                setSingleLine();
                this.b = new a(this);
                return;
            }
            setEllipsize(ellipsize);
        }
    }

    public void setTextColor(int i) {
        this.d = i;
        super.setTextColor(i);
    }

    public void setGravity(int i) {
        super.setGravity(i);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (!(TextUtils.equals(getText(), charSequence) || this.b == null)) {
            d();
            this.c = true;
        }
        super.setText(charSequence, bufferType);
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        a(this.b != null);
    }

    public void onWindowFocusChanged(boolean z) {
        a(z);
    }

    protected void onDraw(Canvas canvas) {
        getPaint().setColor(this.d);
        canvas.save();
        canvas.translate(0.0f, (float) (getHeight() + 10));
        super.onDraw(canvas);
        canvas.restore();
        b();
        canvas.save();
        Layout layout = getLayout();
        float height = (((float) (getHeight() - (layout.getLineBottom(0) - layout.getLineTop(0)))) * 1.0f) / 2.0f;
        if (this.b == null || !this.b.d()) {
            int width = (getWidth() - getCompoundPaddingLeft()) - getCompoundPaddingRight();
            float lineWidth = getLayout().getLineWidth(0);
            DJILogHelper.getInstance().LOGD(a, "LineW-" + lineWidth + i.b + width + i.b, false, true);
            if (lineWidth > ((float) width)) {
                canvas.translate(0.0f, height);
            } else {
                canvas.translate(0.0f, height);
            }
        } else {
            canvas.translate((-this.b.c()) * ((float) layout.getParagraphDirection(0)), height);
        }
        layout.draw(canvas, null, null, 0);
        if (this.b != null && this.b.g()) {
            canvas.translate(this.b.f() * ((float) layout.getParagraphDirection(0)), 0.0f);
            layout.draw(canvas, null, null, 0);
        }
        canvas.restore();
    }

    private boolean a() {
        int right = ((getRight() - getLeft()) - getCompoundPaddingLeft()) - getCompoundPaddingRight();
        if (right <= 0 || getLayout().getLineWidth(0) <= ((float) right)) {
            return false;
        }
        return true;
    }

    private void b() {
        if (this.c && this.b != null) {
            c();
            this.c = false;
        }
    }

    private void c() {
        if (this.b != null && this.b.e() && getLineCount() == 1 && a()) {
            this.b.b(getMarqueeRepeatLimit());
        }
    }

    private void d() {
        if (this.b != null && !this.b.e()) {
            this.b.b();
        }
    }

    private void a(boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            c();
        } else {
            d();
        }
    }

    public void setDelay(int i) {
        this.b.a(i);
    }
}
