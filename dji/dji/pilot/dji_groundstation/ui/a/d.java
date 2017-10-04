package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.b;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.dji_groundstation.controller.f;
import dji.pilot.dji_groundstation.controller.h;
import dji.pilot.dji_groundstation.ui.views.EnterItemButton;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.widget.djiviewpager.DJIViewPager;
import java.util.ArrayList;

public class d extends e implements b, h {
    private static final String a = "EnterDialog";
    private DJILinearLayout g = null;
    private DJILinearLayout h = null;
    private DJIViewPager i = null;
    private FrameLayout j = null;
    private a k = null;
    private String l = "";
    private ArrayList<EnterItemButton> m = new ArrayList();

    private class a extends PagerAdapter {
        final /* synthetic */ d a;
        private dji.pilot.dji_groundstation.controller.f.a b = null;

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            int i = this.b.b;
            int i2 = this.b.c;
            int i3 = this.b.d;
            if (i3 == 0) {
                i3 = i / i2;
                if (i3 == 0) {
                    i3 = 1;
                }
                this.b.d = i3;
            }
            int i4 = i / (i2 * i3);
            return i % (i3 * i2) > 0 ? i4 + 1 : i4;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public a(d dVar, dji.pilot.dji_groundstation.controller.f.a aVar) {
            this.a = dVar;
            this.b = f.getInstance(dVar.getContext()).g();
            this.b.a(aVar);
        }

        public void a(dji.pilot.dji_groundstation.controller.f.a aVar) {
            this.b.a(aVar);
        }

        private int a(int i) {
            if (this.b == null) {
                return 0;
            }
            return (this.b.c * this.b.d) * i;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            View linearLayout = new LinearLayout(this.a.getContext());
            this.a.a(linearLayout, a(i), this.b.c, this.b.d, this.b);
            viewGroup.addView(linearLayout, layoutParams);
            return linearLayout;
        }

        public void startUpdate(ViewGroup viewGroup) {
            super.startUpdate(viewGroup);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(viewGroup.getChildAt(i));
        }
    }

    public d(Context context) {
        super(context);
        setContentView(R.layout.layout_enterdialog);
        setCancelable(false);
        this.g = (DJILinearLayout) findViewById(R.id.item_container);
        this.i = (DJIViewPager) findViewById(R.id.item_container_viewpager);
        this.h = (DJILinearLayout) findViewById(R.id.dot_container);
        this.j = (FrameLayout) findViewById(R.id.bottom_view_container);
        findViewById(R.id.close_img).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
                f.getInstance(this.a.b).b(false);
            }
        });
        this.i.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                this.a.a();
                if (this.a.k.getCount() > 1) {
                    this.a.h.getChildAt(i).setAlpha(1.0f);
                }
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        f();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a(dji.pilot.dji_groundstation.controller.f.a aVar) {
        if (aVar != null) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = (int) aVar.g;
            attributes.height = (int) aVar.h;
            attributes.y = aVar.f;
            attributes.x = aVar.e;
            attributes.gravity = aVar.i;
            this.l = aVar.k;
            if (aVar.l) {
                e();
            } else {
                d();
            }
            if (aVar != null) {
                if (this.k == null) {
                    this.k = new a(this, aVar);
                    if (this.i != null) {
                        this.i.setAdapter(this.k);
                    }
                } else {
                    this.k.a(aVar);
                    if (this.i != null) {
                        this.i.setAdapter(this.k);
                    }
                }
                int count = this.k.getCount();
                if (count > 1) {
                    for (int i = 0; i < count; i++) {
                        View imageView = new ImageView(getContext());
                        imageView.setLayoutParams(new LayoutParams(-2, -2));
                        imageView.setPadding(10, 5, 10, 5);
                        imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.flightmode_index_circle));
                        this.h.addView(imageView);
                    }
                    a();
                    this.h.getChildAt(0).setAlpha(1.0f);
                }
                if (aVar.j > 0) {
                    View inflate = LayoutInflater.from(getContext()).inflate(aVar.j, null);
                    LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    layoutParams.gravity = 17;
                    this.j.addView(inflate, layoutParams);
                }
                attributes.dimAmount = 0.0f;
                attributes.flags &= -3;
                getWindow().setAttributes(attributes);
            }
        }
    }

    private void a() {
        if (this.k.getCount() > 1) {
            for (int i = 0; i < this.k.getCount(); i++) {
                this.h.getChildAt(i).setAlpha(dji.pilot.visual.a.d.c);
            }
        }
    }

    private void a(LinearLayout linearLayout, int i, int i2, int i3, dji.pilot.dji_groundstation.controller.f.a aVar) {
        if (linearLayout != null && aVar != null) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.weight = 1.0f;
            layoutParams.gravity = 17;
            linearLayout.setOrientation(1);
            View view = null;
            int i4 = i;
            while (i4 < (i2 * i3) + i && i4 < aVar.m.size()) {
                if ((i4 - i) % i3 == 0) {
                    if (view != null) {
                        linearLayout.addView(view, layoutParams);
                    }
                    view = new DJILinearLayout(getContext());
                    view.setOrientation(0);
                }
                View view2 = view;
                View enterItemButton = new EnterItemButton(getContext());
                if (((f.b) aVar.m.get(i4)).a > 0) {
                    enterItemButton.setImage(((f.b) aVar.m.get(i4)).a);
                    enterItemButton.setTitle(((f.b) aVar.m.get(i4)).b);
                    enterItemButton.setJumpToProcotal(((f.b) aVar.m.get(i4)).d);
                    this.m.add(enterItemButton);
                }
                view2.addView(enterItemButton, layoutParams);
                i4++;
                view = view2;
            }
            if (view != null) {
                linearLayout.addView(view, layoutParams);
            }
        }
    }

    public void a(dji.pilot.dji_groundstation.a.b bVar, final Object obj) {
        if (bVar.a() != dji.pilot.dji_groundstation.a.b.EVENT_ENTERDIALOG_DATA_FINISH.a() || obj == null) {
            if (bVar.a() == dji.pilot.dji_groundstation.a.b.EVENT_ENTERDIALOG_DISMISS.a()) {
                dismiss();
            } else if (bVar.a() == dji.pilot.dji_groundstation.a.b.EVENT_SMARTDIALOG_DATA_FINISH.a()) {
                f.getInstance(getContext()).e();
            }
        } else if (obj instanceof dji.pilot.dji_groundstation.controller.f.a) {
            a(new Runnable(this) {
                final /* synthetic */ d b;

                public void run() {
                    this.b.b();
                    this.b.a((dji.pilot.dji_groundstation.controller.f.a) obj);
                    this.b.show();
                }
            });
        }
    }

    protected void onStart() {
        super.onStart();
        f();
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }

    private void b() {
        if (this.m != null) {
            this.m.clear();
        }
        if (this.g == null) {
            this.g = (DJILinearLayout) findViewById(R.id.item_container);
        } else {
            this.g.removeAllViews();
        }
        if (this.k != null) {
            this.k.notifyDataSetChanged();
        }
        if (this.h != null) {
            this.h.removeAllViews();
        }
        if (this.j != null) {
            this.j.removeAllViews();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.l != null || this.l.trim().isEmpty()) {
            e.a(this.l, getContext());
        }
    }

    public void a(g gVar, Object obj) {
        if (gVar != null && gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_SMART.ordinal()) {
            a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (dji.pilot.dji_groundstation.controller.d.getInstance().a() == d$a.Smart) {
                        this.a.b();
                        f.getInstance(this.a.b).h();
                    }
                }
            });
        } else if (gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_POINT.ordinal() || gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_TRACK.ordinal() || gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_NORMAL.ordinal() || gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_GESTURE.ordinal()) {
            dismiss();
        }
    }
}
