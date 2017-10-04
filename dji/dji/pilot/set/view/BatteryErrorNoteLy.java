package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.model.P3.DataCenterGetBatteryHistory;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataCenterGetPushLog;
import dji.pilot.set.R;
import dji.pilot.set.view.base.b;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;

public class BatteryErrorNoteLy extends LinearLayout {
    private static final int c = 31;
    private static final String v = ",";
    TextView a;
    final Context b;
    private final ArrayList<b> d = new ArrayList(31);
    private final ArrayList<String> e = new ArrayList(32);
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private String u = null;

    public BatteryErrorNoteLy(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        Context context = this.b;
        this.f = context.getString(R.string.battery_history_normal_status);
        this.g = context.getString(R.string.battery_history_invalid_status);
        this.h = context.getString(R.string.battery_history_exception_status);
        this.i = context.getString(R.string.battery_history_firstlevel_current);
        this.j = context.getString(R.string.battery_history_secondlevel_current);
        this.k = context.getString(R.string.battery_history_firstlevel_over_temperature);
        this.l = context.getString(R.string.battery_history_secondlevel_overt_temperature);
        this.m = context.getString(R.string.battery_history_firstlevel_low_temperature);
        this.n = context.getString(R.string.battery_history_secondlevel_low_temperature);
        this.o = context.getString(R.string.battery_history_short_circuit);
        this.p = context.getString(R.string.battery_history_under_voltage);
        this.q = context.getString(R.string.battery_history_invalid);
        this.t = context.getString(R.string.battery_history_watchdog_reset);
        this.u = context.getString(R.string.battery_history_discharge);
        this.r = context.getString(R.string.battery_history_replace);
        this.s = context.getString(R.string.battery_history_no_replace);
        this.a = (TextView) findViewById(R.id.battery_log_tv);
        if (DataCenterGetBatteryHistory.getInstance().isGetted() && DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            b bVar;
            int[] history = DataCenterGetBatteryHistory.getInstance().getHistory();
            for (int b : history) {
                bVar = new b();
                bVar.b(b);
                this.d.add(bVar);
            }
            int errorType = DataCenterGetPushBatteryCommon.getInstance().getErrorType();
            bVar = new b();
            bVar.b(history[errorType]);
            this.d.add(bVar);
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                stringBuilder.append(a((b) it.next()));
            }
            if (DataCenterGetPushLog.getInstance().isGetted()) {
                this.a.setText(stringBuilder.toString());
                return;
            }
            return;
        }
        this.a.setText(this.h);
    }

    private String a(b bVar) {
        String str = this.f;
        if (bVar.a()) {
            if (bVar.b()) {
                ConnStatus c = bVar.c();
                str = this.h;
                if (c == ConnStatus.INVALID) {
                    str = this.g;
                } else if (c == ConnStatus.EXCEPTION) {
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                if (bVar.d() || bVar.e()) {
                    stringBuilder.append(this.i);
                }
                if (bVar.f() || bVar.g()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.k);
                }
                if (bVar.h() || bVar.i()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.m);
                }
                if (bVar.j()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.o);
                }
                if (bVar.k() != (byte) 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.p);
                }
                if (bVar.l() != (byte) 0) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.q);
                }
                if (bVar.o()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(v);
                    }
                    stringBuilder.append(this.u);
                }
                if (stringBuilder.length() > 0) {
                    str = stringBuilder.toString();
                }
            }
        }
        return str + "\n";
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        b bVar = new b();
        bVar.b(dataCenterGetPushBatteryCommon.getErrorType());
        String a = a(bVar);
        String charSequence = this.a.getText().toString();
        if (!charSequence.isEmpty()) {
            charSequence = charSequence + "\n";
        }
        this.a.setText(charSequence + a);
    }
}
