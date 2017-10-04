package dji.setting.ui.rc;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DividerLinearLayout;

public class RcStickMapView extends DividerLinearLayout implements OnTouchListener {
    protected Button a;
    private ImageView b;
    private ImageView c;
    private View d;
    private boolean e = false;
    private TextView[] f;
    private ImageView[] g;
    private ImageView[] h;
    private int[] i;
    private int[] l;
    private int[] m;
    private a n;
    private int[] o = new int[]{-1, -1, -1, -1};
    private boolean[] p = new boolean[]{true, true, true, true};
    private int[] q = new int[]{R.id.setting_ui_rc_stick_txt_0, R.id.setting_ui_rc_stick_txt_1, R.id.setting_ui_rc_stick_txt_2, R.id.setting_ui_rc_stick_txt_3, R.id.setting_ui_rc_stick_txt_4, R.id.setting_ui_rc_stick_txt_5, R.id.setting_ui_rc_stick_txt_6, R.id.setting_ui_rc_stick_txt_7};
    private int[] r = new int[]{R.id.setting_ui_rc_stick_img_0, R.id.setting_ui_rc_stick_img_1, R.id.setting_ui_rc_stick_img_2, R.id.setting_ui_rc_stick_img_3, R.id.setting_ui_rc_stick_img_4, R.id.setting_ui_rc_stick_img_5, R.id.setting_ui_rc_stick_img_6, R.id.setting_ui_rc_stick_img_7};
    private int[] s = new int[]{R.id.setting_ui_rc_stick_0, R.id.setting_ui_rc_stick_1, R.id.setting_ui_rc_stick_2, R.id.setting_ui_rc_stick_3, R.id.setting_ui_rc_stick_4, R.id.setting_ui_rc_stick_5, R.id.setting_ui_rc_stick_6, R.id.setting_ui_rc_stick_7};

    public interface a {
        void onMapChange(int[] iArr, boolean[] zArr);
    }

    public RcStickMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        int i = 0;
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_stick_map);
        if (!isInEditMode()) {
            int i2;
            this.c = (ImageView) findViewById(R.id.setting_ui_rc_stick_move);
            this.d = findViewById(R.id.setting_ui_rc_stick_ly_3);
            this.a = (Button) findViewById(R.id.setting_ui_rc_gimbal_speed);
            this.f = new TextView[this.q.length];
            for (i2 = 0; i2 < this.q.length; i2++) {
                this.f[i2] = (TextView) findViewById(this.q[i2]);
            }
            this.g = new ImageView[this.r.length];
            for (i2 = 0; i2 < this.r.length; i2++) {
                this.g[i2] = (ImageView) findViewById(this.r[i2]);
                this.g[i2].setOnTouchListener(this);
            }
            this.h = new ImageView[this.s.length];
            while (i < this.s.length) {
                this.h[i] = (ImageView) findViewById(this.s[i]);
                this.h[i].setOnTouchListener(this);
                i++;
            }
        }
    }

    public void setData(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, boolean[] zArr, a aVar) {
        this.m = iArr;
        this.i = iArr2;
        this.l = iArr3;
        this.o = iArr4;
        this.p = zArr;
        this.n = aVar;
        if (this.i.length == 6) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
        b();
    }

    private int a(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    private boolean a(int i) {
        for (int i2 : this.r) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean c(int i) {
        for (int i2 : this.s) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && a(view)) {
            this.e = true;
        }
        if (!this.e) {
            return super.onTouchEvent(motionEvent);
        }
        ViewParent parent;
        if (motionEvent.getAction() == 0) {
            parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            this.b = (ImageView) view;
            this.c.setVisibility(0);
            this.c.setImageResource(((Integer) view.getTag(R.id.setting_ui_rc_stick_move)).intValue());
            this.c.setX((((float) getTouchViewLeft()) + motionEvent.getX()) - ((float) (this.c.getWidth() / 2)));
            this.c.setY((((float) getTouchViewTop()) + motionEvent.getY()) - ((float) this.c.getWidth()));
        } else if (motionEvent.getAction() == 2) {
            this.c.setX((((float) getTouchViewLeft()) + motionEvent.getX()) - ((float) (this.c.getWidth() / 2)));
            this.c.setY((((float) getTouchViewTop()) + motionEvent.getY()) - ((float) (this.c.getHeight() / 2)));
        } else {
            this.e = false;
            parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
            this.c.setVisibility(8);
            int a = a(motionEvent);
            if (a > 0) {
                a(this.b.getId(), a);
            }
        }
        return true;
    }

    private void a(int i, int i2) {
        if (i != i2) {
            int a;
            int a2;
            int a3;
            int a4;
            boolean z;
            if (!a(i) || a(i2)) {
            }
            if (c(i) && c(i2)) {
                a = a(this.s, i) / 2;
                a2 = a(this.s, i) % 2;
                a3 = a(this.s, i2) / 2;
                a4 = a(this.s, i2) % 2;
                if (this.o[a3] == -1) {
                    this.o[a3] = this.o[a];
                    this.o[a] = -1;
                    if (a2 == a4) {
                        this.p[a3] = this.p[a];
                    } else {
                        this.p[a3] = !this.p[a];
                    }
                } else if (a == a3) {
                    this.p[a] = !this.p[a];
                } else {
                    a2 = this.o[a3];
                    boolean z2 = this.p[a];
                    this.o[a3] = this.o[a];
                    this.o[a] = a2;
                    this.p[a3] = this.p[a];
                    this.p[a] = z2;
                }
                z = true;
            } else {
                z = false;
            }
            if (a(i) && c(i2)) {
                a = a(this.r, i) % 2;
                a3 = a(this.s, i2) / 2;
                a4 = a(this.s, i2) % 2;
                this.o[a3] = a(this.r, i) / 2;
                if (a == a4) {
                    this.p[a3] = true;
                } else {
                    this.p[a3] = false;
                }
                z = true;
            }
            if (c(i) && a(i2)) {
                a2 = a(this.s, i) / 2;
                if (this.o[a2] == a(this.r, i2) / 2) {
                    this.o[a2] = -1;
                }
                z = true;
            }
            if (z && this.n != null) {
                this.n.onMapChange(this.o, this.p);
            }
            b();
        }
    }

    private void b() {
        int i;
        for (i = 0; i < this.i.length; i++) {
            this.g[i].setImageResource(this.i[i]);
            this.f[i].setText(this.m[i]);
            this.g[i].setTag(R.id.setting_ui_rc_stick_move, Integer.valueOf(this.i[i]));
        }
        for (i = 0; i < 4; i++) {
            if (this.o[i] >= 0) {
                if (this.p[i]) {
                    this.h[i * 2].setImageResource(this.l[this.o[i] * 2]);
                    this.h[(i * 2) + 1].setImageResource(this.l[(this.o[i] * 2) + 1]);
                    this.h[i * 2].setTag(R.id.setting_ui_rc_stick_move, Integer.valueOf(this.l[this.o[i] * 2]));
                    this.h[(i * 2) + 1].setTag(R.id.setting_ui_rc_stick_move, Integer.valueOf(this.l[(this.o[i] * 2) + 1]));
                } else {
                    this.h[i * 2].setImageResource(this.l[(this.o[i] * 2) + 1]);
                    this.h[(i * 2) + 1].setImageResource(this.l[this.o[i] * 2]);
                    this.h[i * 2].setTag(R.id.setting_ui_rc_stick_move, Integer.valueOf(this.l[(this.o[i] * 2) + 1]));
                    this.h[(i * 2) + 1].setTag(R.id.setting_ui_rc_stick_move, Integer.valueOf(this.l[this.o[i] * 2]));
                }
                this.h[i * 2].setBackgroundResource(R.drawable.setting_ui_rc_stick_btn_selected);
                this.h[(i * 2) + 1].setBackgroundResource(R.drawable.setting_ui_rc_stick_btn_selected);
                this.g[this.o[i] * 2].setImageResource(0);
                this.g[(this.o[i] * 2) + 1].setImageResource(0);
            } else {
                this.h[i * 2].setImageResource(0);
                this.h[(i * 2) + 1].setImageResource(0);
                this.h[i * 2].setBackgroundResource(R.drawable.setting_ui_rc_stick_btn_unactivated);
                this.h[(i * 2) + 1].setBackgroundResource(R.drawable.setting_ui_rc_stick_btn_unactivated);
            }
        }
    }

    private int a(MotionEvent motionEvent) {
        int i = 0;
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        int touchViewLeft = (rect.left + getTouchViewLeft()) + ((int) motionEvent.getX());
        int y = ((int) motionEvent.getY()) + (rect.top + getTouchViewTop());
        for (ImageView imageView : this.g) {
            Rect rect2 = new Rect();
            imageView.getGlobalVisibleRect(rect2);
            if (rect2.contains(touchViewLeft, y)) {
                return imageView.getId();
            }
        }
        ImageView[] imageViewArr = this.h;
        int length = imageViewArr.length;
        while (i < length) {
            ImageView imageView2 = imageViewArr[i];
            Rect rect3 = new Rect();
            imageView2.getGlobalVisibleRect(rect3);
            if (rect3.contains(touchViewLeft, y)) {
                return imageView2.getId();
            }
            i++;
        }
        return -1;
    }

    private int getTouchViewLeft() {
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        this.b.getGlobalVisibleRect(rect2);
        return rect2.left - rect.left;
    }

    private int getTouchViewTop() {
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        this.b.getGlobalVisibleRect(rect2);
        return rect2.top - rect.top;
    }

    private boolean a(View view) {
        boolean z = true;
        int id = view.getId();
        if (a(id)) {
            int a = a(this.r, id) / 2;
            for (int i : this.o) {
                if (i == a) {
                    return false;
                }
            }
            return true;
        } else if (!c(id)) {
            return false;
        } else {
            if (this.o[a(this.s, id) / 2] <= -1) {
                z = false;
            }
            return z;
        }
    }
}
