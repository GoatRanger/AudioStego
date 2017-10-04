package dji.phone.tutorial;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.b.c;
import dji.midware.data.manager.P3.p;
import dji.phone.e.a.e;
import dji.phone.h.b;
import dji.phone.preview.DJILPPreviewActivity;
import dji.phone.tutorial.c.a;
import dji.pilot.fpv.R;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class d implements a {
    private static final String a = d.class.getSimpleName();
    private Activity b;
    private DJILPToturialView c;
    private WeakReference<RelativeLayout> d;
    private RelativeLayout e;
    private WeakReference<View> f;
    private b g = b.ROTATION_0;
    private Button h;
    private Button i;
    private AtomicBoolean j = new AtomicBoolean(false);

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[p.values().length];

        static {
            try {
                a[p.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[p.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public d(Activity activity) {
        this.b = activity;
        this.f = new WeakReference(((DJILPPreviewActivity) activity).rootLayout);
        this.d = new WeakReference((RelativeLayout) ((View) this.f.get()).findViewById(R.id.lp_ble_tutorial));
        this.e = (RelativeLayout) ((RelativeLayout) this.d.get()).findViewById(R.id.bluetooth_connect);
        this.h = (Button) ((RelativeLayout) this.d.get()).findViewById(R.id.lp_ble_confirm_next_btn);
        this.i = (Button) ((RelativeLayout) this.d.get()).findViewById(R.id.lp_ble_confirm_cancel_btn);
        if (this.i != null) {
            this.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    ((RelativeLayout) this.a.d.get()).setVisibility(4);
                    this.a.j.set(false);
                }
            });
        }
        if (this.h != null) {
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    ((RelativeLayout) this.a.d.get()).setVisibility(4);
                    if (c.getInstance().c()) {
                        dji.thirdparty.a.c.a().e(new dji.phone.e.b(e.VIEW_BLE_DIALOG, dji.phone.e.a.c.f));
                    } else {
                        dji.phone.preview.a.getInstance().h();
                    }
                }
            });
        }
        dji.thirdparty.a.c.a().a(this);
    }

    public void onEventMainThread(b bVar) {
        this.g = bVar;
        Log.d(a, "onEventMainThread: getOritation = " + bVar.a() + " getRotation = " + bVar.b());
        f();
    }

    private void f() {
        Log.d(a, "resetView: mCurOrientationEvent = " + this.g.name());
        if (((RelativeLayout) this.d.get()).getVisibility() == 0) {
            ImageView imageView = (ImageView) ((View) this.f.get()).findViewById(R.id.lp_ble_status_view);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            MarginLayoutParams marginLayoutParams;
            if (this.g.a(90) || this.g.a(270)) {
                this.e.findViewById(R.id.lp_inverted_triangle).setVisibility(0);
                this.e.findViewById(R.id.lp_triangle).setVisibility(8);
                dji.phone.h.a.a(this.e, this.e.getRotation(), dji.phone.k.c.a(this.g.b()));
                marginLayoutParams = new MarginLayoutParams(this.e.getLayoutParams());
                marginLayoutParams.leftMargin = (imageView.getHeight() + layoutParams.leftMargin) - a(33.0f);
                marginLayoutParams.topMargin = layoutParams.topMargin + a(18.0f);
                this.e.setLayoutParams(new LayoutParams(marginLayoutParams));
                return;
            }
            this.e.findViewById(R.id.lp_inverted_triangle).setVisibility(8);
            this.e.findViewById(R.id.lp_triangle).setVisibility(0);
            dji.phone.h.a.a(this.e, this.e.getRotation(), dji.phone.k.c.a(this.g.b()));
            marginLayoutParams = new MarginLayoutParams(this.e.getLayoutParams());
            marginLayoutParams.leftMargin = layoutParams.leftMargin;
            marginLayoutParams.topMargin = imageView.getHeight() - a(20.0f);
            this.e.setLayoutParams(new LayoutParams(marginLayoutParams));
        }
    }

    public void a() {
        this.j.set(true);
        new Handler().post(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g();
            }
        });
    }

    private void g() {
        if (dji.phone.bluetooth.c.getInstance().b()) {
            ViewStub viewStub = (ViewStub) this.b.findViewById(R.id.lp_tutorial_view_vs);
            if (viewStub != null) {
                this.c = (DJILPToturialView) viewStub.inflate();
                this.c.setPresenter((a) this);
                a(c.b.START);
            }
        } else if (this.d != null) {
            ImageView imageView = (ImageView) ((View) this.f.get()).findViewById(R.id.lp_ble_status_view);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            MarginLayoutParams marginLayoutParams;
            if (this.g.a(90) || this.g.a(270)) {
                Log.d(a, "showFreshTutorialIfneed: " + this.g.name());
                this.e.findViewById(R.id.lp_inverted_triangle).setVisibility(0);
                this.e.findViewById(R.id.lp_triangle).setVisibility(8);
                dji.phone.h.a.a(this.e, this.e.getRotation(), dji.phone.k.c.a(this.g.b()));
                marginLayoutParams = new MarginLayoutParams(this.e.getLayoutParams());
                marginLayoutParams.leftMargin = (imageView.getHeight() + layoutParams.leftMargin) - a(25.0f);
                marginLayoutParams.topMargin = layoutParams.topMargin + a(22.0f);
                this.e.setLayoutParams(new LayoutParams(marginLayoutParams));
                this.e.requestLayout();
                ((RelativeLayout) this.d.get()).setVisibility(0);
                return;
            }
            Log.d(a, "showFreshTutorialIfneed: " + this.g.name());
            this.e.findViewById(R.id.lp_inverted_triangle).setVisibility(8);
            this.e.findViewById(R.id.lp_triangle).setVisibility(0);
            dji.phone.h.a.a(this.e, this.e.getRotation(), dji.phone.k.c.a(this.g.b()));
            marginLayoutParams = new MarginLayoutParams(this.e.getLayoutParams());
            marginLayoutParams.leftMargin = layoutParams.leftMargin;
            marginLayoutParams.topMargin = imageView.getHeight() - a(20.0f);
            this.e.setLayoutParams(new LayoutParams(marginLayoutParams));
            this.e.requestLayout();
            ((RelativeLayout) this.d.get()).setVisibility(0);
        }
    }

    public void d() {
        this.b = null;
        this.c = null;
    }

    public void onEventMainThread(p pVar) {
        switch (AnonymousClass4.a[pVar.ordinal()]) {
            case 1:
                this.c.hideWelcomPage();
                return;
            case 2:
                ViewStub viewStub = (ViewStub) this.b.findViewById(R.id.lp_tutorial_view_vs);
                if (viewStub != null) {
                    this.c = (DJILPToturialView) viewStub.inflate();
                    this.c.setPresenter((a) this);
                }
                this.c.showWelcomPage();
                return;
            default:
                return;
        }
    }

    public void b() {
        c();
        a(c.b.FINISH);
        dji.thirdparty.a.c.a().d(this);
        this.j.set(false);
    }

    public synchronized boolean e() {
        return this.j.get();
    }

    public void c() {
        if (this.c != null) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
    }

    private int a(float f) {
        return (int) ((this.b.getResources().getDisplayMetrics().density * f) + dji.pilot.visual.a.d.c);
    }

    public void a(c.b bVar) {
        dji.thirdparty.a.c.a().e(bVar);
    }
}
