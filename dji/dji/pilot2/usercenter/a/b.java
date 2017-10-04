package dji.pilot2.usercenter.a;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.logic.f.d;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.usercenter.b.b.a;
import dji.pilot.usercenter.b.b.c;
import dji.pilot2.usercenter.activate.g;
import dji.publics.DJIUI.DJIOriLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class b implements OnClickListener, s, g {
    int a = 0;
    ArrayList<View> b = new ArrayList();
    ArrayList<View> c = new ArrayList();
    boolean d = false;
    ProgressDialog e;
    private ImageView f;
    private TextView g;
    private LinearLayout h;
    private AnimationSet i;
    private AnimationSet j;
    private int k = 300;
    private int l = 200;
    private dji.pilot.usercenter.b.b m;
    private Context n;
    private RelativeLayout o;
    private c p = new c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f.setClickable(false);
            this.a.g.setClickable(false);
        }

        public void b() {
            dji.pilot2.usercenter.activate.b bVar = (dji.pilot2.usercenter.activate.b) this.a.b.get(this.a.m.b());
            bVar.onShow();
            if (!bVar.canGoNext()) {
                this.a.g.setVisibility(4);
            } else if (bVar.canShowTopView()) {
                this.a.g.setVisibility(0);
            }
            if (!bVar.canGoPre()) {
                this.a.f.setVisibility(4);
            } else if (bVar.canShowTopView()) {
                this.a.f.setVisibility(0);
            }
            this.a.f.setClickable(true);
            this.a.g.setClickable(true);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass3 a;

                {
                    this.a = r1;
                }

                public void run() {
                    int b = this.a.a.m.b();
                    for (int i = 0; i < this.a.a.b.size(); i++) {
                        if (i != b) {
                            ((View) this.a.a.b.get(i)).setVisibility(8);
                        }
                    }
                }
            }, 400);
        }
    };
    private View q;

    public void a() {
        this.m.d();
        int b = this.m.b();
        if (b < this.b.size()) {
            this.q = (View) this.b.get(b);
            if (((dji.pilot2.usercenter.activate.b) this.q).canShowTopView()) {
                this.o.setVisibility(0);
                if (this.d) {
                    this.a++;
                } else {
                    this.d = true;
                }
                h();
            } else {
                this.o.setVisibility(4);
            }
        }
        this.e.cancel();
        this.f.setClickable(true);
        this.g.setClickable(true);
    }

    private void g() {
        if (((dji.pilot2.usercenter.activate.b) this.b.get(this.m.b())).handleGoNext()) {
            this.e.show();
            this.f.setClickable(false);
            this.g.setClickable(false);
        }
    }

    public void c() {
        this.m.c();
        if (this.a > 0) {
            this.a--;
            h();
        } else {
            this.o.setVisibility(4);
            this.d = false;
        }
        this.e.cancel();
        this.f.setClickable(true);
        this.g.setClickable(true);
        ((dji.pilot2.usercenter.activate.b) this.b.get(this.m.b())).handleGoPre();
    }

    private void h() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            if (i == this.a) {
                ((View) this.c.get(i)).setBackgroundResource(R.drawable.v2_circle_blue);
            } else {
                ((View) this.c.get(i)).setBackgroundResource(R.drawable.v2_circle_press);
            }
        }
    }

    public void b() {
    }

    public void a(String str) {
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(this.n);
        bVar.setMessage(str);
        bVar.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.show();
    }

    public void b(String str) {
        this.e.cancel();
        this.f.setClickable(true);
        this.g.setClickable(true);
        dji.pilot2.publics.object.b bVar = new dji.pilot2.publics.object.b(this.n);
        bVar.setTitle(R.string.activate_setting_result);
        bVar.setMessage(String.format(this.n.getString(R.string.activate_setting_fail_tips), new Object[]{str}));
        bVar.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        bVar.setCancelable(false);
        bVar.show();
    }

    public b(Context context, DJIOriLayout dJIOriLayout) {
        this.n = context;
        a(dJIOriLayout);
        j();
        i();
        k();
        l();
        e();
    }

    public void d() {
        dji.pilot.usercenter.b.b.a();
    }

    public void e() {
        this.e = new ProgressDialog(this.n);
        this.e.setMessage(this.n.getString(R.string.activate_setting));
        this.e.setCancelable(false);
    }

    public void f() {
        ((dji.pilot2.usercenter.activate.b) this.b.get(this.m.b())).onResume();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c4y:
                c();
                return;
            case R.id.c50:
                g();
                return;
            default:
                return;
        }
    }

    private void a(DJIOriLayout dJIOriLayout) {
        this.o = (RelativeLayout) dJIOriLayout.findViewById(R.id.fw);
        this.f = (ImageView) dJIOriLayout.findViewById(R.id.c4y);
        this.g = (TextView) dJIOriLayout.findViewById(R.id.c50);
        this.h = (LinearLayout) dJIOriLayout.findViewById(R.id.c4z);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        if (!(dji.pilot2.usercenter.activate.c.b() || d.a(null))) {
            this.b.add(dJIOriLayout.findViewById(R.id.fx));
        }
        this.b.add(dJIOriLayout.findViewById(R.id.fz));
        if (!d.a(null)) {
            this.b.add(dJIOriLayout.findViewById(R.id.g0));
            if (!dji.logic.c.b.getInstance().a(null)) {
                this.b.add(dJIOriLayout.findViewById(R.id.e4));
            }
        }
        if (dji.pilot.fpv.d.b.m(i.getInstance().c())) {
            this.b.add(dJIOriLayout.findViewById(R.id.g1));
        }
        if (!d.a(null)) {
            this.b.add(dJIOriLayout.findViewById(R.id.g2));
            this.b.add(dJIOriLayout.findViewById(R.id.g3));
        }
        if (dji.logic.c.b.getInstance().b() && i.getInstance().g()) {
            this.b.add(dJIOriLayout.findViewById(R.id.g4));
        }
        this.b.add(dJIOriLayout.findViewById(R.id.g5));
        this.b.add(dJIOriLayout.findViewById(R.id.g6));
        this.b.add(dJIOriLayout.findViewById(R.id.g7));
        if (this.b.size() > 0) {
            ((View) this.b.get(0)).setVisibility(0);
        }
    }

    private void i() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((dji.pilot2.usercenter.activate.b) ((View) it.next())).setMainTopViewControl(this);
        }
    }

    private void j() {
        this.m = new dji.pilot.usercenter.b.b(this.p);
        this.i = (AnimationSet) AnimationUtils.loadAnimation(this.n, R.anim.e);
        this.j = (AnimationSet) AnimationUtils.loadAnimation(this.n, R.anim.d);
    }

    private void k() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            dji.pilot.usercenter.b.b.b bVar = new dji.pilot.usercenter.b.b.b();
            bVar.b = 0;
            a aVar = new a();
            aVar.a = this.k;
            aVar.a(this.i, view, 0, (long) this.l);
            aVar.a(this.j);
            bVar.a(aVar);
            this.m.a(bVar);
        }
    }

    private void l() {
        int a = dji.pilot2.usercenter.activate.c.a(this.n, 9);
        LayoutParams layoutParams = new LayoutParams(a, a);
        int size = this.b.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            if (((dji.pilot2.usercenter.activate.b) this.b.get(i)).canShowTopView()) {
                if (i2 != 0) {
                    View view = new View(this.n);
                    view.setAlpha(0.0f);
                    view.setPadding(a, 0, a, 0);
                    this.h.addView(view, layoutParams);
                    i3 = i2;
                } else {
                    i3 = 1;
                }
                View imageView = new ImageView(this.n);
                imageView.setBackgroundResource(R.drawable.v2_circle);
                imageView.setPadding(a, 0, a, 0);
                this.h.addView(imageView, layoutParams);
                this.c.add(imageView);
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
    }
}
