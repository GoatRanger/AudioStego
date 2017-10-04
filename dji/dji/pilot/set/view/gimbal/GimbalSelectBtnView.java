package dji.pilot.set.view.gimbal;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import antistatic.spinnerwheel.WheelVerticalView;
import antistatic.spinnerwheel.a.d;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataGimbalGetPushUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public class GimbalSelectBtnView extends Button implements OnClickListener {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private int j = -1;
    private int k = -1;
    private String[] l = new String[]{"table_choice", "pitch_speed", "roll_speed", "yaw_speed", "pitch_deadband", "roll_deadband", "yaw_deadband", "pitch_accel", "roll_accel", "yaw_accel", "pitch_expo", "yaw_expo", "pitch_time_expo", "yaw_time_expo"};
    private String m = "";
    private Dialog n;
    private Button o;
    private Button p;
    private WheelVerticalView q;
    private d r;
    private Context s;
    private int t = 0;
    private int u = 0;
    private int v = 10;

    public GimbalSelectBtnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        setOnClickListener(this);
        this.s = context;
    }

    public void setDirAndOption(int i, int i2) {
        this.j = i;
        this.k = i2;
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.set);
        this.j = obtainStyledAttributes.getInt(R.styleable.set_dir, 0);
        this.k = obtainStyledAttributes.getInt(R.styleable.set_option, 0);
        getCommend();
        this.r = new d(context, this.u, this.v);
        this.r.a(-13421773);
        a(DataGimbalGetPushUserParams.getInstance());
    }

    protected void onAttachedToWindow() {
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
    }

    public void onEventMainThread(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        a(dataGimbalGetPushUserParams);
    }

    protected void a() {
        this.n = new Dialog(this.s, R.style.myDialogTheme);
        this.n.setContentView(R.layout.set_item_wheel_vertical_view);
        Window window = this.n.getWindow();
        LayoutParams attributes = window.getAttributes();
        window.setGravity(80);
        window.setAttributes(attributes);
        Display defaultDisplay = this.n.getWindow().getWindowManager().getDefaultDisplay();
        LayoutParams attributes2 = window.getAttributes();
        attributes2.height = (int) (((double) defaultDisplay.getHeight()) * 0.5d);
        attributes2.width = defaultDisplay.getWidth() * 1;
        window.setAttributes(attributes2);
        this.q = (WheelVerticalView) this.n.findViewById(R.id.set_gimbal_setting_dir_option);
        this.p = (Button) this.n.findViewById(R.id.wheel_cancel_btn);
        this.o = (Button) this.n.findViewById(R.id.wheel_done_btn);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GimbalSelectBtnView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.n.dismiss();
            }
        });
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GimbalSelectBtnView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int currentItem = this.a.q.getCurrentItem() + this.a.u;
                this.a.n.dismiss();
                DataGimbalSetUserParams.getInstance().a(this.a.m, Integer.valueOf(currentItem)).start(new dji.midware.e.d(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(a aVar) {
                    }
                });
            }
        });
        this.q.setViewAdapter(this.r);
        this.q.setBackgroundColor(-1);
        this.q.setVisibleItems(5);
        int i = this.u;
        int i2 = 0;
        while (i <= this.v && this.t != i) {
            i2++;
            i++;
        }
        if (i2 >= (this.v - this.u) + 1) {
            i2 = 0;
        }
        this.q.setCurrentItem(i2, false);
        this.n.show();
    }

    public void onClick(View view) {
        a();
    }

    private void a(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        switch (this.j) {
            case 0:
                switch (this.k) {
                    case 0:
                        this.t = dataGimbalGetPushUserParams.getYawSpeed();
                        break;
                    case 1:
                        this.t = dataGimbalGetPushUserParams.getYawDeadband();
                        break;
                    case 2:
                        this.t = dataGimbalGetPushUserParams.getYawAccel();
                        break;
                    default:
                        break;
                }
            case 1:
                switch (this.k) {
                    case 0:
                        this.t = dataGimbalGetPushUserParams.getPitchSpeed();
                        break;
                    case 1:
                        this.t = dataGimbalGetPushUserParams.getPitchDeadband();
                        break;
                    case 2:
                        this.t = dataGimbalGetPushUserParams.getPitchAccel();
                        break;
                    default:
                        break;
                }
            case 2:
                switch (this.k) {
                    case 0:
                        this.t = dataGimbalGetPushUserParams.getRollSpeed();
                        break;
                    case 1:
                        this.t = dataGimbalGetPushUserParams.getRollDeadband();
                        break;
                    case 2:
                        this.t = dataGimbalGetPushUserParams.getRollAccel();
                        break;
                    default:
                        break;
                }
            case 3:
                switch (this.k) {
                    case 0:
                        this.t = dataGimbalGetPushUserParams.getStickYawSpeed();
                        break;
                    case 3:
                        this.t = dataGimbalGetPushUserParams.getStickYawSmooth();
                        break;
                    default:
                        break;
                }
            case 4:
                switch (this.k) {
                    case 0:
                        this.t = dataGimbalGetPushUserParams.getStickPitchSpeed();
                        break;
                    case 3:
                        this.t = dataGimbalGetPushUserParams.getStickPitchSmooth();
                        break;
                    default:
                        break;
                }
        }
        setText(this.t + "");
        switch (dataGimbalGetPushUserParams.getPresetID()) {
            case 0:
            case 1:
                setClickable(true);
                setEnabled(true);
                return;
            case 3:
            case 4:
            case 5:
                setClickable(false);
                setEnabled(false);
                return;
            default:
                return;
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.n != null) {
            this.n.dismiss();
        }
    }

    private void getCommend() {
        switch (this.j) {
            case 0:
                switch (this.k) {
                    case 0:
                        this.m = this.l[3];
                        this.u = 1;
                        this.v = 100;
                        return;
                    case 1:
                        this.m = this.l[6];
                        this.u = 0;
                        this.v = 90;
                        return;
                    case 2:
                        this.m = this.l[9];
                        this.u = 0;
                        this.v = 30;
                        return;
                    default:
                        return;
                }
            case 1:
                switch (this.k) {
                    case 0:
                        this.m = this.l[1];
                        this.u = 1;
                        this.v = 100;
                        return;
                    case 1:
                        this.m = this.l[4];
                        this.u = 0;
                        this.v = 90;
                        return;
                    case 2:
                        this.m = this.l[7];
                        this.u = 0;
                        this.v = 30;
                        return;
                    default:
                        return;
                }
            case 2:
                switch (this.k) {
                    case 0:
                        this.m = this.l[2];
                        this.u = 1;
                        this.v = 100;
                        return;
                    case 1:
                        this.m = this.l[5];
                        this.u = 0;
                        this.v = 30;
                        return;
                    case 2:
                        this.m = this.l[8];
                        this.u = 0;
                        this.v = 30;
                        return;
                    default:
                        return;
                }
            case 3:
                switch (this.k) {
                    case 0:
                        this.m = this.l[11];
                        this.u = 1;
                        this.v = 100;
                        return;
                    case 3:
                        this.m = this.l[13];
                        this.u = 0;
                        this.v = 30;
                        return;
                    default:
                        return;
                }
            case 4:
                switch (this.k) {
                    case 0:
                        this.m = this.l[10];
                        this.u = 1;
                        this.v = 100;
                        return;
                    case 3:
                        this.m = this.l[12];
                        this.u = 0;
                        this.v = 30;
                        return;
                    default:
                        return;
                }
            default:
                this.m = this.l[1];
                return;
        }
    }
}
