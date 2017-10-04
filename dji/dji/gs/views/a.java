package dji.gs.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dji.frame.c.c;
import dji.gs.R;
import dji.gs.c.e;

public class a extends PopupWindow {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private View d;
    private e e;
    private View f;
    private SharedPreferences g;
    private Editor h;
    private String i = "map_type";
    private int j = -1;
    private TextView k;
    private TextView l;

    public a(Context context, e eVar, View view) {
        this.e = eVar;
        this.d = view;
        this.g = PreferenceManager.getDefaultSharedPreferences(context);
        this.h = this.g.edit();
        this.f = LayoutInflater.from(context).inflate(R.layout.gs_map_type, null, false);
        setContentView(this.f);
        setWindowLayoutMode(-2, -2);
        setAnimationStyle(R.style.dialogWindowAnim);
        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setFocusable(true);
        setOutsideTouchable(true);
        this.a = (ImageView) this.f.findViewById(R.id.gs_maptype_standard);
        this.b = (ImageView) this.f.findViewById(R.id.gs_maptype_hybrid);
        this.c = (ImageView) this.f.findViewById(R.id.gs_maptype_satellite);
        this.k = (TextView) this.f.findViewById(R.id.gs_maptype_hybrid_desc);
        this.l = (TextView) this.f.findViewById(R.id.gs_maptype_satellite_desc);
        if (!eVar.B()) {
            this.k.setText(R.string.gs_maptype_satellite);
            this.l.setText(R.string.gs_maptype_night);
        }
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.gs_maptype_standard) {
                    this.a.e.h(dji.gs.e.a.b);
                    if (this.a.h != null) {
                        this.a.h.putInt(this.a.i, dji.gs.e.a.b);
                        this.a.h.commit();
                    }
                } else if (id == R.id.gs_maptype_hybrid) {
                    this.a.e.h(dji.gs.e.a.e);
                    if (this.a.h != null) {
                        this.a.h.putInt(this.a.i, dji.gs.e.a.e);
                        this.a.h.commit();
                    }
                } else if (id == R.id.gs_maptype_satellite) {
                    this.a.e.h(dji.gs.e.a.c);
                    if (this.a.h != null) {
                        this.a.h.putInt(this.a.i, dji.gs.e.a.c);
                        this.a.h.commit();
                    }
                }
                this.a.a.setSelected(false);
                this.a.b.setSelected(false);
                this.a.c.setSelected(false);
                view.setSelected(true);
                this.a.dismiss();
            }
        };
        this.a.setOnClickListener(anonymousClass1);
        this.b.setOnClickListener(anonymousClass1);
        this.c.setOnClickListener(anonymousClass1);
        if (this.g != null) {
            this.j = this.g.getInt(this.i, -1);
        }
        if (this.j == dji.gs.e.a.b) {
            this.a.setSelected(true);
        } else if (this.j == dji.gs.e.a.c) {
            this.c.setSelected(true);
        } else if (this.j == dji.gs.e.a.e) {
            this.b.setSelected(true);
        } else {
            this.a.setSelected(true);
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public void a() {
        this.b.setVisibility(8);
    }

    public void b() {
        showAsDropDown(this.d, 100 - this.f.getMeasuredWidth(), 0);
        c.a(this.f);
    }

    public void dismiss() {
        super.dismiss();
        dji.thirdparty.a.c.a().e(com.dji.frame.c.c.a.a);
    }
}
