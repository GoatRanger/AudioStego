package dji.gs.views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.R;
import dji.gs.c.e;
import dji.gs.e.b;
import dji.gs.utils.a;
import dji.midware.natives.FlyForbid.FlyForbidParam;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends PopupWindow implements OnClickListener, OnSeekBarChangeListener {
    private SeekBar a;
    private TextView b;
    private e c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private int g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private int l;
    private dji.gs.e.e m;
    private ArrayList<TextView> n;
    private Context o;
    private View p;
    private EditText q;
    private EditText r;
    private Handler s = new Handler();

    public c(Context context, e eVar, View view) {
        super(context);
        this.o = context;
        this.p = view;
        setWindowLayoutMode(-2, -2);
        View inflate = LayoutInflater.from(context).inflate(R.layout.gs_marker_info, null, false);
        setContentView(inflate);
        setAnimationStyle(R.style.dialogWindowAnim);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setFocusable(true);
        setOutsideTouchable(true);
        this.a = (SeekBar) inflate.findViewById(R.id.gs_marker_info_bar);
        this.f = (TextView) inflate.findViewById(R.id.gs_marker_info_height);
        this.d = (ImageView) inflate.findViewById(R.id.gs_marker_info_delete);
        this.e = (TextView) inflate.findViewById(R.id.gs_marker_info_ok);
        this.b = (TextView) inflate.findViewById(R.id.gs_marker_info_title);
        this.h = (TextView) inflate.findViewById(R.id.gs_marker_info_front);
        this.i = (TextView) inflate.findViewById(R.id.gs_marker_info_back);
        this.j = (TextView) inflate.findViewById(R.id.gs_marker_info_left);
        this.k = (TextView) inflate.findViewById(R.id.gs_marker_info_right);
        this.q = (EditText) inflate.findViewById(R.id.gs_marker_info_lontitude_edittext);
        this.r = (EditText) inflate.findViewById(R.id.gs_marker_info_latitude_edittext);
        this.c = eVar;
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.a.setOnSeekBarChangeListener(this);
        this.l = 2;
        this.n = new ArrayList();
        this.n.add(this.h);
        this.n.add(this.i);
        this.n.add(this.j);
        this.n.add(this.k);
    }

    public void a(int i, int i2) {
        a();
        showAtLocation(this.p, 21, 0, 0);
    }

    private int a(int i) {
        return i / this.l;
    }

    private int b(int i) {
        return this.l * i;
    }

    private void a() {
        this.g = this.c.e();
        this.m = this.c.b(this.g).getInfo();
        this.b.setText(dji.gs.utils.c.a(this.o, R.string.gs_manager_point_info) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.g);
        this.a.setProgress(a(this.m.c()));
        switch (this.m.d()) {
            case LEFT:
                a(this.j);
                break;
            case RIGHT:
                a(this.k);
                break;
            case FRONT:
                a(this.h);
                break;
            case BACK:
                a(this.i);
                break;
        }
        b b = a.b(this.c.b(this.c.b(this.g).getMarker()));
        this.q.setText(String.format("%.5f", new Object[]{Double.valueOf(b.c)}));
        this.r.setText(String.format("%.5f", new Object[]{Double.valueOf(b.b)}));
    }

    private void b() {
        dismiss();
        this.s.postDelayed(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.d(this.a.g);
            }
        }, 100);
    }

    private boolean a(double d, double d2) {
        return d >= -90.0d && d <= 90.0d && d2 >= -180.0d && d2 <= 180.0d;
    }

    private boolean a(b bVar) {
        FlyForbidParam n = this.c.n();
        if (n == null || n.count <= 0) {
            return false;
        }
        for (int i = 0; i < n.count; i++) {
            if (((double) dji.gs.utils.c.a(bVar, new b(n.ForbidLat[i], n.ForbidLon[i]))) <= n.ForbidRadius[i]) {
                return true;
            }
        }
        return false;
    }

    private void c() {
        this.c.a(this.g, this.m);
        String obj = this.q.getText().toString();
        String obj2 = this.r.getText().toString();
        try {
            if (obj.length() > 0 && obj2.length() > 0) {
                double parseDouble = Double.parseDouble(obj2);
                double parseDouble2 = Double.parseDouble(obj);
                if (a(parseDouble, parseDouble2)) {
                    b bVar = new b(parseDouble, parseDouble2);
                    if (dji.gs.utils.c.a(bVar, this.c.f()) >= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
                        Toast.makeText(this.o, R.string.gs_maker_info_coordinates_too_far, 0).show();
                    } else if (a(bVar)) {
                        Toast.makeText(this.o, R.string.gs_maker_info_coordinates_in_restrict_area, 0).show();
                    } else {
                        this.c.a(this.g, bVar);
                    }
                } else {
                    Toast.makeText(this.o, R.string.gs_maker_info_invalid_coordinates, 0).show();
                }
            }
        } catch (Exception e) {
        }
        dismiss();
    }

    private void a(View view) {
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setSelected(false);
        }
        view.setSelected(true);
    }

    public void dismiss() {
        this.c.u();
        super.dismiss();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.gs_marker_info_front) {
            this.m.a(dji.gs.e.e.a.FRONT);
            a(this.h);
        } else if (id == R.id.gs_marker_info_back) {
            this.m.a(dji.gs.e.e.a.BACK);
            a(this.i);
        } else if (id == R.id.gs_marker_info_left) {
            this.m.a(dji.gs.e.e.a.LEFT);
            a(this.j);
        } else if (id == R.id.gs_marker_info_right) {
            this.m.a(dji.gs.e.e.a.RIGHT);
            a(this.k);
        } else if (id == R.id.gs_marker_info_delete) {
            b();
        } else if (id == R.id.gs_marker_info_ok) {
            c();
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.m.a(b(i));
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        c();
        dismiss();
    }
}
