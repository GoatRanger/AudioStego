package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalRoninGetUserParams;
import dji.midware.data.model.P3.DataGimbalRoninSetUserParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.midware.util.i;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MotorParams extends LinearLayout {
    public static int a = p.length;
    private static final String f = "ronin_config";
    private static final String g = "camera_type";
    private static final int l = 2;
    private static final String m = "key_default_user_params_ver";
    private static final String n = "ronin/";
    private static final String o = "userparams.json";
    private static final Number[][] p;
    private static final String[] q = new String[]{"ronin_sensibility_yaw", "ronin_sensibility_pitch", "ronin_sensibility_roll", "ronin_strength_yaw", "ronin_strength_pitch", "ronin_strength_roll", "ronin_filter_yaw", "ronin_filter_pitch", "ronin_filter_roll", "ronin_feedback_yaw", "ronin_feedback_pitch", "ronin_feedback_roll"};
    private List<TextView> b = new ArrayList();
    private LinearLayout c = null;
    private TextView d = null;
    private CameraTypeSpinner e = null;
    private SharedPreferences h;
    private int i = 0;
    private a j = null;
    private boolean k = false;
    private OnClickListener r = new OnClickListener(this) {
        final /* synthetic */ MotorParams a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int parseInt;
            int i = 100;
            int i2 = 0;
            for (int i3 = 0; i3 < this.a.b.size(); i3++) {
                if (view == this.a.b.get(i3)) {
                    ((TextView) this.a.b.get(i3)).setSelected(true);
                    ParamInfo paramInfo = null;
                    if (i3 < 12) {
                        paramInfo = e.read(MotorParams.q[i3]);
                    }
                    i = paramInfo.range.b.intValue();
                    i2 = paramInfo.range.a.intValue();
                } else {
                    ((TextView) this.a.b.get(i3)).setSelected(false);
                }
            }
            try {
                parseInt = Integer.parseInt(((TextView) view).getText().toString());
            } catch (Exception e) {
                parseInt = 0;
            }
            new b(this.a.getContext(), (TextView) view, i2, i, parseInt, this.a.s).show();
        }
    };
    private c s = new c(this) {
        final /* synthetic */ MotorParams a;

        {
            this.a = r1;
        }

        public void a(TextView textView, final int i) {
            int i2 = 0;
            while (i2 < this.a.b.size()) {
                if (textView != this.a.b.get(i2) || i2 >= 12) {
                    i2++;
                } else if (this.a.a((TextView) this.a.b.get(i2), Integer.valueOf(i), MotorParams.q[i2])) {
                    DataGimbalRoninSetUserParams.getInstance().a(MotorParams.q[i2], Integer.valueOf(i)).start(new d(this) {
                        final /* synthetic */ AnonymousClass4 c;

                        public void onSuccess(Object obj) {
                            this.c.a.a(i2, i);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                    return;
                } else {
                    return;
                }
            }
        }
    };

    public enum a {
        TRUE
    }

    static {
        r0 = new Number[3][];
        r0[0] = new Number[]{Integer.valueOf(73), Integer.valueOf(75), Integer.valueOf(70), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(25), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(60)};
        r0[1] = new Number[]{Integer.valueOf(55), Integer.valueOf(45), Integer.valueOf(45), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(60), Integer.valueOf(60)};
        r0[2] = new Number[]{Integer.valueOf(50), Integer.valueOf(45), Integer.valueOf(45), Integer.valueOf(20), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(60), Integer.valueOf(60)};
        p = r0;
    }

    public MotorParams(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.h = context.getSharedPreferences(f, 0);
            this.i = this.h.getInt(g, p.length);
            if (i.b(getContext(), m, 0) != 2) {
                c();
            }
            this.j = a.getInstance();
            if (this.j == null) {
                this.j = a.a(com.dji.frame.c.d.a(context, n), o);
            }
        }
    }

    private void c() {
        File file = new File(com.dji.frame.c.d.a(getContext(), "ronin/userparams.json"));
        if (file.exists()) {
            file.delete();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        if (this.j != null && this.k) {
            this.j.a();
            this.k = false;
        }
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.e = (CameraTypeSpinner) findViewById(R.id.setting_ui_ronin_camera_type);
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_force_yaw));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_force_pitch));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_force_roll));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_hard_yaw));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_hard_pitch));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_hard_roll));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_filter_yaw));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_filter_pitch));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_filter_roll));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_feedback_control_yaw));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_feedback_control_pitch));
            this.b.add((TextView) findViewById(R.id.setting_ui_ronin_motor_settings_feedback_control_roll));
            for (TextView onClickListener : this.b) {
                onClickListener.setOnClickListener(this.r);
            }
            this.c = (LinearLayout) findViewById(R.id.setting_ui_ronin_motor_settings_adv_ly);
            this.c.setVisibility(8);
            this.d = (TextView) findViewById(R.id.setting_ui_ronin_motor_settings_adv_tv);
            this.d.setSelected(false);
            this.d.setText(R.string.setting_ui_ronin_motor_settings_unfold_adv);
            findViewById(R.id.setting_ui_ronin_motor_settings_adv_btn).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MotorParams a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.c.getVisibility() == 0) {
                        this.a.c.setVisibility(8);
                        this.a.d.setText(R.string.setting_ui_ronin_motor_settings_unfold_adv);
                        this.a.d.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_ui_arrow_down, 0, 0, 0);
                        return;
                    }
                    this.a.c.setVisibility(0);
                    this.a.d.setText(R.string.setting_ui_ronin_motor_settings_fold_adv);
                    this.a.d.setCompoundDrawablesWithIntrinsicBounds(R.drawable.setting_ui_arrow_up, 0, 0, 0);
                }
            });
            this.e.getSpinner().setData(getContext().getResources().getStringArray(R.array.setting_ui_ronin_camera_types), this.i, new b(this) {
                final /* synthetic */ MotorParams a;

                {
                    this.a = r1;
                }

                public void onItemClick(int i) {
                    this.a.a(i, true, true);
                }
            });
            a(this.i, true, false);
            d();
        }
    }

    private boolean a(TextView textView, Number number, String str) {
        boolean isCorrect = e.read(str).isCorrect(number);
        if (isCorrect) {
            textView.setTextColor(getContext().getResources().getColor(R.color.white));
        } else {
            textView.setTextColor(getContext().getResources().getColor(R.color.red));
        }
        return isCorrect;
    }

    private void a(int i, boolean z, boolean z2) {
        this.i = i;
        this.h.edit().putInt(g, this.i).commit();
        if (z) {
            a(z2);
        }
    }

    private void a(boolean z) {
        boolean z2;
        int i;
        if (this.i < p.length) {
            z2 = false;
        } else {
            z2 = true;
        }
        for (i = 0; i < 12; i++) {
            ((TextView) this.b.get(i)).setEnabled(z2);
            if (z2) {
                ((TextView) this.b.get(i)).setTextColor(-1);
            } else {
                ((TextView) this.b.get(i)).setTextColor(-7829368);
            }
        }
        if (z2) {
            int length = this.i - p.length;
            if (this.j != null && length < this.j.a.size()) {
                dji.setting.ui.gimbal.ronin.a.a aVar = (dji.setting.ui.gimbal.ronin.a.a) this.j.a.get(length);
                ((TextView) this.b.get(0)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.a)}));
                ((TextView) this.b.get(1)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.b)}));
                ((TextView) this.b.get(2)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.c)}));
                ((TextView) this.b.get(3)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.d)}));
                ((TextView) this.b.get(4)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.e)}));
                ((TextView) this.b.get(5)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.f)}));
                ((TextView) this.b.get(6)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.g)}));
                ((TextView) this.b.get(7)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.h)}));
                ((TextView) this.b.get(8)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.i)}));
                ((TextView) this.b.get(9)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.j)}));
                ((TextView) this.b.get(10)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.k)}));
                ((TextView) this.b.get(11)).setText(String.format("%d", new Object[]{Integer.valueOf(aVar.l)}));
                if (z) {
                    a(q, new Number[]{Integer.valueOf(aVar.a), Integer.valueOf(aVar.b), Integer.valueOf(aVar.c), Integer.valueOf(aVar.d), Integer.valueOf(aVar.e), Integer.valueOf(aVar.f), Integer.valueOf(aVar.g), Integer.valueOf(aVar.h), Integer.valueOf(aVar.i), Integer.valueOf(aVar.j), Integer.valueOf(aVar.k), Integer.valueOf(aVar.l)});
                    return;
                }
                return;
            }
            return;
        }
        for (i = 0; i < 12; i++) {
            if (!z2) {
                ((TextView) this.b.get(i)).setText(String.format("%d", new Object[]{p[this.i][i]}));
            }
        }
        if (z) {
            a(q, p[this.i]);
        }
    }

    private void a(String[] strArr, Number[] numberArr) {
        DataGimbalRoninSetUserParams.getInstance().a(strArr).a(numberArr).start(new d(this) {
            final /* synthetic */ MotorParams a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.d();
            }
        });
    }

    private void d() {
        DataGimbalRoninGetUserParams.getInstance().setInfos(q).start(new d(this) {
            final /* synthetic */ MotorParams a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.e();
            }
        });
    }

    private void e() {
        this.c.post(new Runnable(this) {
            final /* synthetic */ MotorParams a;

            {
                this.a = r1;
            }

            public void run() {
                int[] iArr = new int[MotorParams.q.length];
                for (int i = 0; i < MotorParams.q.length; i++) {
                    iArr[i] = e.read(MotorParams.q[i]).value.intValue();
                    ((TextView) this.a.b.get(i)).setText(String.format("%d", new Object[]{Integer.valueOf(iArr[i])}));
                }
                if (this.a.i < MotorParams.p.length) {
                    int i2;
                    boolean z;
                    for (i2 = 0; i2 < MotorParams.q.length; i2++) {
                        if (MotorParams.p[this.a.i][i2].intValue() != iArr[i2]) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                    if (!z) {
                        this.a.i = MotorParams.p.length;
                        for (i2 = 0; i2 < 12; i2++) {
                            this.a.a(i2, iArr[i2]);
                        }
                        this.a.a(MotorParams.p.length, false, false);
                    }
                }
            }
        });
    }

    private void a(int i, int i2) {
        int length = this.i - p.length;
        if (this.j != null && length < this.j.a.size()) {
            dji.setting.ui.gimbal.ronin.a.a aVar = (dji.setting.ui.gimbal.ronin.a.a) this.j.a.get(length);
            switch (i) {
                case 0:
                    aVar.a = i2;
                    break;
                case 1:
                    aVar.b = i2;
                    break;
                case 2:
                    aVar.c = i2;
                    break;
                case 3:
                    aVar.d = i2;
                    break;
                case 4:
                    aVar.e = i2;
                    break;
                case 5:
                    aVar.f = i2;
                    break;
                case 6:
                    aVar.g = i2;
                    break;
                case 7:
                    aVar.h = i2;
                    break;
                case 8:
                    aVar.i = i2;
                    break;
                case 9:
                    aVar.j = i2;
                    break;
                case 10:
                    aVar.k = i2;
                    break;
                case 11:
                    aVar.l = i2;
                    break;
            }
            this.k = true;
        }
    }

    private void f() {
        int length = a - p.length;
        if (this.j != null) {
            dji.setting.ui.gimbal.ronin.a.a aVar = (dji.setting.ui.gimbal.ronin.a.a) this.j.a.get(length);
            aVar.a = e.read(q[0]).range.c.intValue();
            aVar.b = e.read(q[1]).range.c.intValue();
            aVar.c = e.read(q[2]).range.c.intValue();
            aVar.d = e.read(q[3]).range.c.intValue();
            aVar.e = e.read(q[4]).range.c.intValue();
            aVar.f = e.read(q[5]).range.c.intValue();
            aVar.g = e.read(q[6]).range.c.intValue();
            aVar.h = e.read(q[7]).range.c.intValue();
            aVar.i = e.read(q[8]).range.c.intValue();
            aVar.j = e.read(q[9]).range.c.intValue();
            aVar.k = e.read(q[10]).range.c.intValue();
            aVar.l = e.read(q[11]).range.c.intValue();
            this.e.getSpinner().setIndex(a);
            a(a, true, true);
        }
    }

    public void onEventMainThread(a aVar) {
        f();
    }
}
