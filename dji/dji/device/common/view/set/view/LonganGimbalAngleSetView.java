package dji.device.common.view.set.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import dji.device.common.view.DJIStateTextView;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalSetAngle;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganGimbalAngleSetView extends ScrollView implements a {
    private static final String j = "LonganGimbalAngleSetView";
    TextView a;
    TextView b;
    EditText c;
    EditText d;
    DJIStateTextView e;
    int f = Integer.MIN_VALUE;
    int g = Integer.MIN_VALUE;
    int h = Integer.MIN_VALUE;
    int i = Integer.MIN_VALUE;

    public LonganGimbalAngleSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        this.a = (TextView) findViewById(R.id.longan_gimbal_angle_cur_yaw_tv);
        this.b = (TextView) findViewById(R.id.longan_gimbal_angle_cur_pitch_tv);
        this.c = (EditText) findViewById(R.id.longan_gimbal_angle_yaw);
        this.d = (EditText) findViewById(R.id.longan_gimbal_angle_pitch);
        this.c.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ LonganGimbalAngleSetView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                if (!charSequence2.contains("-") || charSequence2.length() != 1) {
                    if (charSequence2.isEmpty()) {
                        this.a.h = Integer.MIN_VALUE;
                    } else {
                        try {
                            this.a.h = Integer.parseInt(charSequence2);
                        } catch (Exception e) {
                        }
                    }
                    if (this.a.a()) {
                        this.a.e.setEnabled(true);
                    } else {
                        this.a.e.setEnabled(false);
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.d.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ LonganGimbalAngleSetView a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                if (!charSequence2.contains("-") || charSequence2.length() != 1) {
                    if (charSequence2.isEmpty()) {
                        this.a.i = Integer.MIN_VALUE;
                    } else {
                        try {
                            this.a.i = Integer.parseInt(charSequence2);
                        } catch (Exception e) {
                        }
                    }
                    if (this.a.a()) {
                        this.a.e.setEnabled(true);
                    } else {
                        this.a.e.setEnabled(false);
                    }
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.e = (DJIStateTextView) findViewById(R.id.longan_gimbal_angle_confirm_btn);
        this.e.setEnabled(false);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganGimbalAngleSetView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int yaw = DataGimbalGetPushParams.getInstance().getYaw();
                int pitch = DataGimbalGetPushParams.getInstance().getPitch();
                if (this.a.h != Integer.MIN_VALUE) {
                    yaw = this.a.h * 10;
                }
                if (this.a.i != Integer.MIN_VALUE) {
                    pitch = this.a.i * 10;
                }
                DataGimbalSetAngle.getInstance().a(yaw).b(pitch).d(100).e(4).f(1).a(5000, 1, new d(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        DJILogHelper.getInstance().LOGD(LonganGimbalAngleSetView.j, "DJIMethod : onSuccess (140)", false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGE(LonganGimbalAngleSetView.j, "DJIMethod : onFailure (145)" + aVar, true, false);
                    }
                });
            }
        });
    }

    private boolean a() {
        if (this.h == Integer.MIN_VALUE && this.i == Integer.MIN_VALUE) {
            return false;
        }
        if (this.i != Integer.MIN_VALUE && (this.i < -90 || this.i > 30)) {
            return false;
        }
        if (this.h == Integer.MIN_VALUE || (this.h >= -320 && this.h <= 320)) {
            return true;
        }
        return false;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        this.a.setText((dataGimbalGetPushParams.getYaw() / 10) + "");
        this.b.setText((dataGimbalGetPushParams.getPitch() / 10) + "");
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
