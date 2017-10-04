package dji.setting.ui.battery;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dji.frame.c.e;
import dji.common.battery.DJIBatteryStatus;
import dji.common.battery.DJIBatteryWarningInformation;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DividerLinearLayout;
import java.util.ArrayList;
import java.util.List;

public class HistoryBatteryView extends DividerLinearLayout implements d {
    private static final String b = ",";
    private dji.pilot.battery.a.b A = dji.pilot.battery.a.b.getInstance();
    private dji.pilot.battery.a.b.a B = null;
    private OnItemClickListener C = null;
    private List<DJIBatteryWarningInformation> D = new ArrayList();
    private a E = null;
    private Context F = null;
    private int G = 0;
    private c H = null;
    private c I = null;
    private c J = null;
    private int a = -1;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private ListView u = null;
    private TextView v = null;
    private LinearLayout w = null;
    private TextView x = null;
    private LinearLayout y = null;
    private TextView z = null;

    private final class a extends BaseAdapter {
        final /* synthetic */ HistoryBatteryView a;
        private final LayoutInflater b;

        public a(HistoryBatteryView historyBatteryView, Context context) {
            this.a = historyBatteryView;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            if (this.a.D == null || this.a.D.size() == 0) {
                return 0;
            }
            if (dji.pilot.publics.e.a.e(null)) {
                return this.a.D.size();
            }
            return 1;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            CharSequence string;
            if (view == null) {
                b bVar2 = new b();
                view = this.b.inflate(R.layout.setting_ui_battery_history_item, null);
                bVar2.a = (RelativeLayout) view.findViewById(R.id.battery_history_normal_ly);
                bVar2.b = (TextView) view.findViewById(R.id.battery_history_item_tv);
                bVar2.c = (ImageView) view.findViewById(R.id.battery_history_item_arrow_img);
                bVar2.d = (ViewStub) view.findViewById(R.id.battery_history_item_info);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            DJIBatteryWarningInformation dJIBatteryWarningInformation = (DJIBatteryWarningInformation) this.a.D.get(i);
            TextView textView = bVar.b;
            if (i == 0) {
                string = this.a.F.getString(R.string.setting_ui_battery_history_current);
            } else {
                string = this.a.F.getString(R.string.setting_ui_battery_history_record, new Object[]{Integer.valueOf(i)});
            }
            textView.setText(string);
            if (bVar.e == null) {
                bVar.e = (LinearLayout) bVar.d.inflate();
                bVar.f = (TextView) bVar.e.findViewById(R.id.battery_history_info_tv);
            }
            bVar.c.setVisibility(8);
            bVar.e.setVisibility(0);
            bVar.f.setText(this.a.a(dJIBatteryWarningInformation));
            if (dJIBatteryWarningInformation.hasError()) {
                bVar.f.setTextColor(Color.parseColor("#99ff0000"));
            } else {
                bVar.f.setTextColor(Color.parseColor("#ff129c27"));
            }
            return view;
        }
    }

    private static final class b {
        public RelativeLayout a;
        public TextView b;
        public ImageView c;
        public ViewStub d;
        public LinearLayout e;
        public TextView f;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public HistoryBatteryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = context;
        b();
    }

    private void a() {
        Context context = this.F;
        this.c = context.getString(R.string.setting_ui_battery_history_normal_status);
        this.d = context.getString(R.string.setting_ui_battery_history_invalid_status);
        this.e = context.getString(R.string.setting_ui_battery_history_exception_status);
        this.f = context.getString(R.string.setting_ui_battery_history_firstlevel_current);
        this.g = context.getString(R.string.setting_ui_battery_history_secondlevel_current);
        this.h = context.getString(R.string.setting_ui_battery_history_firstlevel_over_temperature);
        this.i = context.getString(R.string.setting_ui_battery_history_secondlevel_overt_temperature);
        this.l = context.getString(R.string.setting_ui_battery_history_firstlevel_low_temperature);
        this.m = context.getString(R.string.setting_ui_battery_history_secondlevel_low_temperature);
        this.n = context.getString(R.string.setting_ui_battery_history_short_circuit);
        this.o = context.getString(R.string.setting_ui_battery_history_under_voltage);
        this.p = context.getString(R.string.setting_ui_battery_history_invalid);
        this.s = context.getString(R.string.setting_ui_battery_history_watchdog_reset);
        this.t = context.getString(R.string.setting_ui_battery_history_discharge);
        this.q = context.getString(R.string.setting_ui_battery_history_replace);
        this.r = context.getString(R.string.setting_ui_battery_history_no_replace);
    }

    private void b() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_history);
        if (!isInEditMode()) {
            a();
            this.G = e.b(this.F, 1.0f);
            this.C = new OnItemClickListener(this) {
                final /* synthetic */ HistoryBatteryView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                }
            };
            this.E = new a(this, getContext());
            this.a = dji.pilot.battery.a.a.getInstance().D() - 1;
            if (this.a < 0) {
                this.H = dji.sdksharedlib.a.b.d("InternalSerialNumber");
                this.I = dji.sdksharedlib.a.b.d(dji.sdksharedlib.b.a.i);
                this.J = dji.sdksharedlib.a.b.d(dji.sdksharedlib.b.a.k);
                return;
            }
            this.H = dji.sdksharedlib.a.b.a(0, "InternalSerialNumber");
            this.I = dji.sdksharedlib.a.b.a(0, dji.sdksharedlib.b.a.i);
            this.J = dji.sdksharedlib.a.b.a(0, dji.sdksharedlib.b.a.k);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.u = (ListView) findViewById(R.id.battery_history_lv);
        this.v = (TextView) findViewById(R.id.battery_history_empty_ly);
        this.w = (LinearLayout) findViewById(R.id.battery_history_serialno_ly);
        this.x = (TextView) findViewById(R.id.battery_serialno_value_tv);
        this.y = (LinearLayout) findViewById(R.id.battery_history_chargetims_ly);
        this.z = (TextView) findViewById(R.id.battery_chargetimes_value_tv);
        this.u.setAdapter(this.E);
        this.u.setOnItemClickListener(this.C);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.sdksharedlib.a.a.a(this, new c[]{this.H, this.I, this.J});
            c();
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        c();
    }

    private void c() {
        d();
        updateChargeTime();
        e();
    }

    public void updateChargeTime() {
        Integer num = (Integer) dji.sdksharedlib.a.a.a(this.I);
        if (num != null) {
            this.y.setVisibility(0);
            this.z.setText(String.valueOf(num));
            return;
        }
        this.y.setVisibility(8);
    }

    private void d() {
        String str = (String) dji.sdksharedlib.a.a.a(this.H);
        if (str == null) {
            this.w.setVisibility(8);
            return;
        }
        this.w.setVisibility(0);
        this.x.setText(str);
    }

    private void e() {
        this.v.setText(R.string.setting_ui_battery_history_nothing);
        this.u.setEmptyView(this.v);
        DJIBatteryWarningInformation[] dJIBatteryWarningInformationArr = (DJIBatteryWarningInformation[]) dji.sdksharedlib.a.a.a(this.J);
        this.D.clear();
        if (dJIBatteryWarningInformationArr != null && dJIBatteryWarningInformationArr.length > 0) {
            for (Object add : dJIBatteryWarningInformationArr) {
                this.D.add(add);
            }
        }
        this.u.setDividerHeight(0);
        this.E.notifyDataSetChanged();
    }

    private String a(DJIBatteryWarningInformation dJIBatteryWarningInformation) {
        String str = this.c;
        DJIBatteryStatus dJIBatteryStatus = (DJIBatteryStatus) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.m);
        if (dJIBatteryStatus != null && dJIBatteryStatus != DJIBatteryStatus.NORMAL) {
            str = this.e;
            if (dJIBatteryStatus == DJIBatteryStatus.INVALID) {
                return this.d;
            }
            if (dJIBatteryStatus == DJIBatteryStatus.EXCEPTION) {
                return str;
            }
        } else if (dJIBatteryWarningInformation.hasError()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (dJIBatteryWarningInformation.isCurrentOverload()) {
                stringBuilder.append(this.f);
            }
            if (dJIBatteryWarningInformation.isOverHeating()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.h);
            }
            if (dJIBatteryWarningInformation.isLowTemperature()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.l);
            }
            if (dJIBatteryWarningInformation.isShortCircuit()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.n);
            }
            if (dJIBatteryWarningInformation.getUnderVoltageBatteryCellIndex() != (short) 0) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.o);
            }
            if (dJIBatteryWarningInformation.getDamagedBatteryCellIndex() != (short) 0) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.p);
            }
            if (dJIBatteryWarningInformation.isCustomDischargeEnabled()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(b);
                }
                stringBuilder.append(this.t);
            }
            if (stringBuilder.length() > 0) {
                return stringBuilder.toString();
            }
        }
        return str;
    }
}
