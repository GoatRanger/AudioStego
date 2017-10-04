package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import java.util.ArrayList;
import java.util.List;

public class RemoteControllerParamsView extends LinearLayout {
    private static final String[] a = new String[]{"yaw_dead_zone", "pitch_dead_zone", "yaw_expo", "pitch_expo", "yaw_time_expo", "pitch_time_expo"};
    private boolean b = false;
    private List<TextView> c = new ArrayList();
    private OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ RemoteControllerParamsView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int parseInt;
            int i = 100;
            int i2 = 0;
            for (int i3 = 0; i3 < this.a.c.size(); i3++) {
                if (view == this.a.c.get(i3)) {
                    ((TextView) this.a.c.get(i3)).setSelected(true);
                    ParamInfo read = e.read(RemoteControllerParamsView.a[i3]);
                    i = read.range.b.intValue();
                    i2 = read.range.a.intValue();
                } else {
                    ((TextView) this.a.c.get(i3)).setSelected(false);
                }
            }
            try {
                parseInt = Integer.parseInt(((TextView) view).getText().toString());
            } catch (Exception e) {
                parseInt = 0;
            }
            new b(this.a.getContext(), (TextView) view, i2, i, parseInt, this.a.e).show();
        }
    };
    private c e = new c(this) {
        final /* synthetic */ RemoteControllerParamsView a;

        {
            this.a = r1;
        }

        public void a(TextView textView, int i) {
            int i2 = 0;
            while (i2 < this.a.c.size()) {
                if (textView != this.a.c.get(i2)) {
                    i2++;
                } else if (this.a.a((TextView) this.a.c.get(i2), Integer.valueOf(i), RemoteControllerParamsView.a[i2])) {
                    DataGimbalSetUserParams.getInstance().a(RemoteControllerParamsView.a[i2], Integer.valueOf(i)).start(new d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(a aVar) {
                            this.a.a.c();
                        }
                    });
                    return;
                } else {
                    return;
                }
            }
        }
    };

    public RemoteControllerParamsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.b = true;
            b();
        }
    }

    protected void onDetachedFromWindow() {
        this.b = false;
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_dead_zone_tv1));
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_dead_zone_tv2));
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_max_speed_tv1));
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_max_speed_tv2));
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_smooth_tv1));
            this.c.add((TextView) findViewById(R.id.setting_ui_ronin_remote_controller_setting_smooth_tv2));
            for (TextView onClickListener : this.c) {
                onClickListener.setOnClickListener(this.d);
            }
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

    private void b() {
        DataGimbalGetUserParams.getInstance().setInfos(a).start(new d(this) {
            final /* synthetic */ RemoteControllerParamsView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.c();
            }

            public void onFailure(a aVar) {
                if (this.a.b) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.a.b();
                }
            }
        });
    }

    private void c() {
        ((TextView) this.c.get(0)).post(new Runnable(this) {
            final /* synthetic */ RemoteControllerParamsView a;

            {
                this.a = r1;
            }

            public void run() {
                for (int i = 0; i < RemoteControllerParamsView.a.length; i++) {
                    ((TextView) this.a.c.get(i)).setText(String.format("%d", new Object[]{Integer.valueOf(e.read(RemoteControllerParamsView.a[i]).value.intValue())}));
                }
            }
        });
    }
}
