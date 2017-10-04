package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.wifi.WifiConfiguration;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Switch;
import dji.pilot.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class e {
    View a;
    Switch b;
    View c;
    private Context d;
    private ListView e;
    private View f;
    private d g;
    private HashMap<String, WifiConfiguration> h;
    private ArrayList<String> i = new ArrayList();
    private PopupWindow j = null;
    private f k;
    private boolean l = false;

    class a implements OnItemClickListener {
        final /* synthetic */ e a;

        a(e eVar) {
            this.a = eVar;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.k.onWifiSelected((WifiConfiguration) this.a.h.get(this.a.i.get(i)));
        }
    }

    public e(Context context, View view, f fVar) {
        this.d = context;
        this.f = view;
        this.k = fVar;
        d();
    }

    private void d() {
        this.a = LayoutInflater.from(this.d).inflate(R.layout.activate_wifilist_view, null);
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.a();
                return false;
            }
        });
        this.e = (ListView) this.a.findViewById(R.id.f4);
        if (!d.getInstance().a()) {
            d.getInstance().a(this.d);
        }
        this.g = d.getInstance();
        this.b = (Switch) this.a.findViewById(R.id.f2);
        this.b.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (this.a.l) {
                    this.a.l = false;
                } else if (z) {
                    this.a.g.d();
                } else {
                    Intent intent = new Intent();
                    intent.setFlags(268435456);
                    intent.setAction("android.settings.SETTINGS");
                    this.a.d.startActivity(intent);
                    this.a.g.e();
                    this.a.a();
                }
            }
        });
        this.c = this.a.findViewById(R.id.f1);
    }

    public void a() {
        if (this.j != null) {
            this.j.dismiss();
            this.k.onPopDismiss();
        }
    }

    public int b() {
        return this.i.size();
    }

    public void c() {
        int width;
        this.l = true;
        this.b.setChecked(d.getInstance().h());
        this.k.onPopShow();
        if (this.j == null) {
            width = this.f.getWidth();
            if (width == 0) {
                width = -2;
            }
            this.j = new PopupWindow(this.a, width, -2);
        }
        this.h = this.g.f();
        this.i.clear();
        for (Entry entry : this.h.entrySet()) {
            if (!((WifiConfiguration) entry.getValue()).SSID.equals(a.getInstance().b())) {
                this.i.add(entry.getKey());
            }
        }
        this.e.setAdapter(new ArrayAdapter(this.d, R.layout.activate_wifi_list_item, this.i));
        int a = c.a(this.d) / 2;
        width = c.a(this.e);
        if (a <= width) {
            width = a;
        }
        this.e.setLayoutParams(new LayoutParams(-1, width));
        this.e.setOnItemClickListener(new a(this));
        this.j.setFocusable(true);
        this.j.setBackgroundDrawable(new BitmapDrawable());
        this.j.setOutsideTouchable(false);
        this.j.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                this.a.k.onPopDismiss();
            }
        });
        int[] iArr = new int[2];
        this.f.getLocationOnScreen(iArr);
        if (this.j.isShowing()) {
            this.j.dismiss();
        }
        this.c.measure(0, 0);
        this.j.showAtLocation(this.f, 0, iArr[0], ((iArr[1] - width) - this.c.getMeasuredHeight()) - c.a(this.d, 50));
    }
}
