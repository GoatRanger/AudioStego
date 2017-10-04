package dji.phone.set.gimbalplan;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.AbstractWheelView;
import antistatic.spinnerwheel.b;
import antistatic.spinnerwheel.d;
import dji.phone.customui.DJILPRotationLinearLayout;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.c;

public class DJILPGimbalRotationPlanView extends DJILPRotationLinearLayout implements OnClickListener {
    private static final String f = DJILPGimbalRotationPlanView.class.getSimpleName();
    AbstractWheelView a;
    protected String[] b;
    protected String[] c;
    protected boolean[] d;
    c<String> e;
    private b g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private int l;
    private TextView[] m;
    private RelativeLayout n;
    private Button o;
    private boolean p;
    private b q;
    private d r;

    public DJILPGimbalRotationPlanView(Context context) {
        super(context);
        this.d = new boolean[]{false, false, false, false};
        this.l = 0;
        this.m = new TextView[4];
        this.p = false;
        this.q = new b(this) {
            final /* synthetic */ DJILPGimbalRotationPlanView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                if (this.a.p) {
                    this.a.e.i(i2);
                    this.a.m[this.a.l].setText(this.a.b[i2]);
                    Log.d(DJILPGimbalRotationPlanView.f, "onChanged: newValue = " + i2);
                }
            }
        };
        this.r = new d(this) {
            final /* synthetic */ DJILPGimbalRotationPlanView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel) {
                Log.d(DJILPGimbalRotationPlanView.f, "onScrollingStarted: wheel.getCurrentItem() = " + abstractWheel.getCurrentItem());
                this.a.p = true;
            }

            public void b(AbstractWheel abstractWheel) {
                this.a.p = false;
                this.a.e.i(abstractWheel.getCurrentItem());
                this.a.a.setCurrentItem(abstractWheel.getCurrentItem());
                Log.d(DJILPGimbalRotationPlanView.f, "onScrollingFinished: wheel.getCurrentItem() = " + abstractWheel.getCurrentItem());
                this.a.m[this.a.l].setText(this.a.b[abstractWheel.getCurrentItem()]);
                Log.d(DJILPGimbalRotationPlanView.f, "onScrollingFinished: mTextViewDirectionIndex = " + this.a.l);
                int parseInt = Integer.parseInt(this.a.c[abstractWheel.getCurrentItem()]);
                switch (this.a.l) {
                    case 0:
                        c.a().f(-parseInt);
                        return;
                    case 1:
                        c.a().f(parseInt);
                        return;
                    case 2:
                        c.a().g(parseInt);
                        return;
                    case 3:
                        c.a().g(-parseInt);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public DJILPGimbalRotationPlanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new boolean[]{false, false, false, false};
        this.l = 0;
        this.m = new TextView[4];
        this.p = false;
        this.q = /* anonymous class already generated */;
        this.r = /* anonymous class already generated */;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = getResources().getStringArray(R.array.phone_gimbal_argular_value_array);
        this.c = getResources().getStringArray(R.array.phone_gimbal_argular_value_int_array);
        this.h = (ImageView) findViewById(R.id.image_up);
        this.h.setOnClickListener(this);
        this.i = (ImageView) findViewById(R.id.image_down);
        this.i.setOnClickListener(this);
        this.j = (ImageView) findViewById(R.id.image_left);
        this.j.setOnClickListener(this);
        this.k = (ImageView) findViewById(R.id.image_right);
        this.k.setOnClickListener(this);
        this.m[0] = (TextView) findViewById(R.id.text_context_up);
        this.m[1] = (TextView) findViewById(R.id.text_context_down);
        this.m[2] = (TextView) findViewById(R.id.text_context_left);
        this.m[3] = (TextView) findViewById(R.id.text_context_right);
        this.o = (Button) findViewById(R.id.set_item_btn);
        this.o.setOnClickListener(this);
        this.n = (RelativeLayout) findViewById(R.id.lp_gimbal_argular_value_ly);
        this.e = new c(getContext(), this.c);
        this.e.c(R.layout.lp_wheel_item_gimbal_set_port);
        this.e.d(R.id.lp_gambal_settings_wheel_text);
        this.e.i();
        c();
        b();
    }

    private void b() {
        this.a.setViewAdapter(this.e);
        this.a.addChangingListener(this.q);
        this.a.addScrollingListener(this.r);
    }

    private void c() {
        this.a = (AbstractWheelView) findViewById(R.id.lp_gimbal_argular_value_wheel);
    }

    public void setPresenter(b bVar) {
        this.g = bVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void hide() {
        setVisibility(8);
        c.a().b(false);
        c.a().f(0);
        c.a().g(0);
    }

    public void show() {
        setVisibility(0);
        c.a().b(true);
    }

    public void setAngularSpeedWheelViewGoneIfVisible() {
        if (this.n.getVisibility() == 0) {
            this.n.setVisibility(4);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.image_up) {
            this.d[0] = false;
            if (this.i.isSelected()) {
                if (this.d[1]) {
                    this.i.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_down));
                }
                this.d[1] = false;
                this.i.setSelected(false);
                this.m[1].setText(this.b[0]);
                this.m[1].setTextColor(getResources().getColor(R.color.gray_text));
            }
            if (this.j.isSelected()) {
                this.d[2] = true;
                this.j.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_left_arrow_white));
                this.m[2].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.k.isSelected()) {
                this.d[3] = true;
                this.k.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_right_arrow_white));
                this.m[3].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.h.isSelected()) {
                this.l = 0;
                this.h.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_up));
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                if (this.n.getVisibility() == 4) {
                    this.n.setVisibility(0);
                    c.a().f(-Integer.parseInt(this.b[this.l].split("°")[0]));
                }
            } else {
                this.l = 0;
                this.h.setSelected(true);
                this.n.setVisibility(0);
                this.a.setCurrentItem(3);
                this.m[this.l].setText(this.b[3]);
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                c.a().f(-Integer.parseInt(this.c[3]));
            }
        }
        if (id == R.id.image_down) {
            this.d[1] = false;
            if (this.h.isSelected()) {
                if (this.d[0]) {
                    this.h.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_up));
                }
                this.d[0] = false;
                this.h.setSelected(false);
                this.m[0].setText(this.b[0]);
                this.m[0].setTextColor(getResources().getColor(R.color.gray_text));
            }
            if (this.j.isSelected()) {
                this.d[2] = true;
                this.j.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_left_arrow_white));
                this.m[2].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.k.isSelected()) {
                this.d[3] = true;
                this.k.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_right_arrow_white));
                this.m[3].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.i.isSelected()) {
                this.l = 1;
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_down));
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                if (this.n.getVisibility() == 4) {
                    this.n.setVisibility(0);
                    c.a().f(Integer.parseInt(((String) this.m[this.l].getText()).split("°")[0]));
                }
            } else {
                this.l = 1;
                this.i.setSelected(true);
                this.n.setVisibility(0);
                this.a.setCurrentItem(3);
                this.m[this.l].setText(this.b[3]);
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                c.a().f(Integer.parseInt(this.c[3]));
            }
        }
        if (id == R.id.image_left) {
            this.d[2] = false;
            if (this.k.isSelected()) {
                if (this.d[3]) {
                    this.k.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_right));
                }
                this.d[3] = false;
                this.k.setSelected(false);
                this.m[3].setText(this.b[0]);
                this.m[3].setTextColor(getResources().getColor(R.color.gray_text));
            }
            if (this.h.isSelected()) {
                this.d[0] = true;
                this.h.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_up_arrow_white));
                this.m[0].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.i.isSelected() && this.i.isEnabled()) {
                this.d[1] = true;
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_down_arrow_white));
                this.m[1].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.j.isSelected()) {
                this.l = 2;
                this.j.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_left));
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                if (this.n.getVisibility() == 4) {
                    this.n.setVisibility(0);
                    c.a().g(Integer.parseInt(this.b[this.l].split("°")[0]));
                }
            } else {
                this.l = 2;
                this.j.setSelected(true);
                this.n.setVisibility(0);
                this.a.setCurrentItem(3);
                this.m[this.l].setText(this.b[3]);
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                c.a().g(Integer.parseInt(this.c[3]));
            }
        }
        if (id == R.id.image_right) {
            this.d[3] = false;
            if (this.j.isSelected()) {
                if (this.d[2]) {
                    this.j.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_left));
                }
                this.d[2] = false;
                this.j.setSelected(false);
                this.m[2].setText(this.b[0]);
                this.m[2].setTextColor(getResources().getColor(R.color.gray_text));
            }
            if (this.h.isSelected()) {
                this.d[0] = true;
                this.h.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_up_arrow_white));
                this.m[0].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.i.isSelected()) {
                this.d[1] = true;
                this.i.setImageDrawable(getResources().getDrawable(R.drawable.lp_gimbalplan_down_arrow_white));
                this.m[1].setTextColor(getResources().getColor(R.color.white));
            }
            if (this.k.isSelected()) {
                this.l = 3;
                this.k.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_right));
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                if (this.n.getVisibility() == 4) {
                    this.n.setVisibility(0);
                    c.a().g(-Integer.parseInt(this.b[this.l].split("°")[0]));
                }
            } else {
                this.l = 3;
                this.k.setSelected(true);
                this.n.setVisibility(0);
                this.a.setCurrentItem(3);
                this.m[this.l].setText(this.b[3]);
                this.m[this.l].setTextColor(getResources().getColor(R.color.blue_txt));
                c.a().g(-Integer.parseInt(this.c[3]));
            }
        }
        if (id == R.id.set_item_btn) {
            hide();
            this.h.setSelected(false);
            this.h.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_up));
            this.i.setSelected(false);
            this.i.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_down));
            this.j.setSelected(false);
            this.j.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_left));
            this.k.setSelected(false);
            this.k.setImageDrawable(getResources().getDrawable(R.drawable.lp_selector_gimbal_plan_right));
            for (int i = 0; i < 4; i++) {
                this.d[i] = false;
                this.m[i].setText(this.b[0]);
                this.m[i].setTextColor(getResources().getColor(R.color.gray_text));
            }
            this.n.setVisibility(4);
        }
    }

    public void strengthenAlpha() {
        this.h.setAlpha(255);
        this.i.setAlpha(255);
        this.k.setAlpha(255);
        this.j.setAlpha(255);
        this.n.setAlpha(1.0f);
    }

    public void weakenAlpha() {
        this.h.setAlpha(80);
        this.i.setAlpha(80);
        this.k.setAlpha(80);
        this.j.setAlpha(80);
        for (int i = 0; i < 4; i++) {
            this.m[i].setAlpha(1.0f);
        }
        this.n.setAlpha(0.1f);
    }
}
