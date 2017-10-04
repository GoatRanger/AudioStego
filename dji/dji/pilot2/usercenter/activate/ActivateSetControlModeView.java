package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.tencent.bugly.crashreport.BuglyLog;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.publics.DJIUI.DJIRadioButton;

public class ActivateSetControlModeView extends ActivateBaseView {
    private static final int t = 1;
    private static final int u = 2;
    private static final int v = 3;
    int c = 0;
    boolean d = true;
    private SparseArray<a> e;
    private RadioGroup f;
    private TextView g = null;
    private ImageView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private ImageView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private int r = 0;
    private int s = 0;

    private class a {
        int a;
        int b;
        ControlMode c;
        int[] d;
        int[] e;
        final /* synthetic */ ActivateSetControlModeView f;

        public a(ActivateSetControlModeView activateSetControlModeView, int i, int i2, ControlMode controlMode, int[] iArr, int[] iArr2) {
            this.f = activateSetControlModeView;
            this.a = i;
            this.b = i2;
            this.c = controlMode;
            this.d = iArr;
            this.e = iArr2;
        }
    }

    private class b extends Handler {
        final /* synthetic */ ActivateSetControlModeView a;

        private b(ActivateSetControlModeView activateSetControlModeView) {
            this.a = activateSetControlModeView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.a.a.a();
                    return;
                case 2:
                    this.a.a.b((String) message.obj);
                    return;
                case 3:
                    this.a.c();
                    return;
                default:
                    return;
            }
        }
    }

    public ActivateSetControlModeView(Context context) {
        super(context);
    }

    public ActivateSetControlModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateSetControlModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = new b();
        a();
        b();
    }

    private void a() {
        this.f = (RadioGroup) findViewById(R.id.d2);
        this.g = (TextView) findViewById(R.id.d6);
        this.h = (ImageView) findViewById(R.id.d8);
        this.i = (TextView) findViewById(R.id.d7);
        this.j = (TextView) findViewById(R.id.d9);
        this.k = (TextView) findViewById(R.id.d_);
        this.l = (TextView) findViewById(R.id.da);
        this.m = (ImageView) findViewById(R.id.dc);
        this.n = (TextView) findViewById(R.id.db);
        this.o = (TextView) findViewById(R.id.dd);
        this.p = (TextView) findViewById(R.id.de);
        this.q = (TextView) findViewById(R.id.df);
        try {
            this.f.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ ActivateSetControlModeView a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    this.a.c = i;
                    if (this.a.d) {
                        this.a.showConfirmDialog();
                    } else {
                        this.a.r = this.a.c;
                        this.a.c();
                    }
                    this.a.d = true;
                }
            });
        } catch (Exception e) {
            BuglyLog.e("activate", e.toString());
        }
    }

    public void showConfirmDialog() {
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(getContext());
        bVar.setTitle(R.string.v2_active_dialog_11);
        bVar.setMessage(R.string.v2_active_dialog_12);
        bVar.setPositiveButton(R.string.v2_active_dialog_13, new OnClickListener(this) {
            final /* synthetic */ ActivateSetControlModeView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.r = this.a.c;
                this.a.c();
                dialogInterface.dismiss();
            }
        });
        bVar.setNegativeButton(R.string.v2_active_dialog_17, new OnClickListener(this) {
            final /* synthetic */ ActivateSetControlModeView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.d();
                dialogInterface.dismiss();
            }
        });
        bVar.setCancelable(false);
        bVar.show();
    }

    private void b() {
        if (!isInEditMode()) {
            this.e = new SparseArray();
            this.e.put(R.id.d4, new a(this, R.string.activate_control_mode_1, R.string.activate_control_mode_1_diagram, ControlMode.a, new int[]{R.drawable.activate_control_mode_elevator_rudder, R.drawable.activate_control_mode_throttle_aileron}, new int[]{R.string.activate_control_mode_forward, R.string.activate_control_mode_rudder_left, R.string.activate_control_mode_rudder_right, R.string.activate_control_mode_backward, R.string.activate_control_mode_throttle_up, R.string.activate_control_mode_left, R.string.activate_control_mode_right, R.string.activate_control_mode_throttle_down}));
            this.e.put(R.id.d3, new a(this, R.string.activate_control_mode_2, R.string.activate_control_mode_2_diagram, ControlMode.b, new int[]{R.drawable.activate_control_mode_throttle_rudder, R.drawable.activate_control_mode_elevator_aileron}, new int[]{R.string.activate_control_mode_throttle_up, R.string.activate_control_mode_rudder_left, R.string.activate_control_mode_rudder_right, R.string.activate_control_mode_throttle_down, R.string.activate_control_mode_forward, R.string.activate_control_mode_left, R.string.activate_control_mode_right, R.string.activate_control_mode_backward}));
            this.e.put(R.id.d5, new a(this, R.string.activate_control_mode_3, R.string.activate_control_mode_3_diagram, ControlMode.c, new int[]{R.drawable.activate_control_mode_elevator_aileron, R.drawable.activate_control_mode_throttle_rudder}, new int[]{R.string.activate_control_mode_forward, R.string.activate_control_mode_left, R.string.activate_control_mode_right, R.string.activate_control_mode_backward, R.string.activate_control_mode_throttle_up, R.string.activate_control_mode_rudder_left, R.string.activate_control_mode_rudder_right, R.string.activate_control_mode_throttle_down}));
            this.s = R.id.d3;
            this.r = R.id.d3;
        }
    }

    private void c() {
        if (this.r != 0) {
            a aVar = (a) this.e.get(this.r);
            this.g.setText(aVar.b);
            this.h.setImageResource(aVar.d[0]);
            this.m.setImageResource(aVar.d[1]);
            this.i.setText(aVar.e[0]);
            this.j.setText(aVar.e[1]);
            this.k.setText(aVar.e[2]);
            this.l.setText(aVar.e[3]);
            this.n.setText(aVar.e[4]);
            this.o.setText(aVar.e[5]);
            this.p.setText(aVar.e[6]);
            this.q.setText(aVar.e[7]);
        }
    }

    private void d() {
        if (this.r != 0) {
            this.d = false;
            ((DJIRadioButton) findViewById(this.r)).setChecked(true);
        }
    }

    public void setData() {
        if (this.r == this.s || this.r == 0) {
            this.b.sendEmptyMessage(1);
            return;
        }
        final ControlMode controlMode = ((a) this.e.get(this.r)).c;
        DataRcSetControlMode.getInstance().a(controlMode).start(new d(this) {
            final /* synthetic */ ActivateSetControlModeView b;

            public void onSuccess(Object obj) {
                c.a("Set control mode Success Mode = " + controlMode);
                this.b.b.sendEmptyMessage(1);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                c.a("Set control mode fail Mode = " + controlMode);
                Message obtainMessage = this.b.b.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = aVar.a() + "";
                this.b.b.sendMessage(obtainMessage);
            }
        });
    }
}
