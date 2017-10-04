package dji.pilot.gallery.previewActivity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.gallery.entryActivity.g;
import dji.pilot.gallery.previewActivity.widget.GalleryHScrollView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class d extends PagerAdapter {
    private List<g> a;
    private b b;
    private View c;
    private c d;
    private Handler e = new Handler(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.notifyDataSetChanged();
            super.handleMessage(message);
        }
    };
    private List<View> f = new ArrayList();

    public interface b {
        void a(int i);

        void a(int i, int i2, int i3, int i4);

        void a(SurfaceHolder surfaceHolder, int i);

        void a(SurfaceHolder surfaceHolder, int i, int i2, int i3, int i4);

        void a(g gVar, int i);

        void a(g gVar, int i, int i2);

        void b(int i);

        void b(SurfaceHolder surfaceHolder, int i);

        void c(int i);
    }

    public class a implements Callback {
        public DJIImageView a;
        public DJIImageView b;
        public PhotoView c;
        public int d;
        public dji.pilot.gallery.entryActivity.d.b e;
        public g f;
        public GalleryHScrollView g;
        public LinearLayout h;
        public DJIImageView i;
        public View j;
        public SurfaceView k;
        public SurfaceHolder l;
        public LinearLayout m;
        public DJITextView n;
        public DJITextView o;
        public RelativeLayout p;
        public dji.pilot.gallery.previewActivity.widget.GalleryHScrollView.a q;
        final /* synthetic */ d r;

        public a(d dVar) {
            this.r = dVar;
        }

        private void b(int i, int i2) {
            DJILogHelper.getInstance().LOGI("bob", "videoWidth = " + i + " videoHeight=" + i2);
            if (i2 < i) {
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                if (i2 < i) {
                    float f = (float) i;
                    this.r.d;
                    f /= (float) c.a;
                    layoutParams.height = (int) Math.ceil((double) (((float) i2) / f));
                    this.r.d;
                    layoutParams.width = c.a;
                    DJILogHelper.getInstance().LOGI("bob", "vWidth = " + f + " params.height=" + layoutParams.height);
                }
                DJILogHelper.getInstance().LOGI("bob", "params.width = " + layoutParams.width + " params.height=" + layoutParams.height);
                layoutParams.addRule(13);
                this.k.setLayoutParams(layoutParams);
            }
        }

        private void a(View view, int i, int i2) {
            if (this.r.d.getActivity().getResources().getConfiguration().orientation != 2) {
                DJILogHelper.getInstance().LOGI("bob", "videoWidth = " + i + " videoHeight=" + i2);
                if (i2 < i) {
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    if (i2 < i) {
                        float f = (float) i;
                        this.r.d;
                        f /= (float) c.a;
                        layoutParams.height = (int) Math.ceil((double) (((float) i2) / f));
                        this.r.d;
                        layoutParams.width = c.a;
                        DJILogHelper.getInstance().LOGI("bob", "vWidth = " + f + " params.height=" + layoutParams.height);
                    }
                    DJILogHelper.getInstance().LOGI("bob", "params.width = " + layoutParams.width + " params.height=" + layoutParams.height);
                    layoutParams.addRule(13);
                    view.setLayoutParams(layoutParams);
                }
            } else if (i2 > i) {
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                if (i2 > i) {
                    this.r.d;
                    int i3 = c.a;
                    this.r.d;
                    if (i3 < c.b) {
                        this.r.d;
                        i3 = c.a;
                    } else {
                        this.r.d;
                        i3 = c.b;
                    }
                    float f2 = (float) i3;
                    float f3 = ((float) i2) / f2;
                    layoutParams2.height = (int) f2;
                    layoutParams2.width = (int) Math.ceil((double) (((float) i) / f3));
                    DJILogHelper.getInstance().LOGI("bob", "vWidth = " + f3 + " params.height=" + layoutParams2.height + "params.width =" + layoutParams2.width);
                }
                DJILogHelper.getInstance().LOGI("bob", " params.height=" + layoutParams2.height + "params.width = " + layoutParams2.width);
                layoutParams2.addRule(13);
                view.setLayoutParams(layoutParams2);
            }
        }

        public boolean a() {
            if (this.k.getVisibility() == 0) {
                return true;
            }
            return false;
        }

        public void a(int i, int i2) {
            DJILogHelper.getInstance().LOGI("bob", "cur == " + i);
            if (i > 1) {
                this.a.setVisibility(4);
            }
            if (this.g.getVisibility() == 0) {
                this.g.smoothScrollTo((this.g.getTotalWidth() * i) / i2, 0);
                int i3 = i / 60000;
                int i4 = (i % 60000) / 1000;
                this.n.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
                i3 = i2 / 60000;
                i4 = (i2 % 60000) / 1000;
                this.o.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
            }
        }

        public void a(g gVar) {
            this.b.setVisibility(4);
            this.j.setVisibility(0);
            a(this.k, gVar.j, gVar.i);
            this.k.setVisibility(0);
            this.g.setVisibility(0);
            this.m.setVisibility(0);
            this.i.setVisibility(0);
        }

        public void b() {
            if (this.b.getVisibility() == 0) {
                this.b.setVisibility(4);
            } else {
                this.b.setVisibility(0);
            }
        }

        public void c() {
            this.b.setVisibility(0);
        }

        public void d() {
            this.b.setVisibility(4);
        }

        public void e() {
            this.a.setVisibility(0);
            this.b.setVisibility(0);
            this.j.setVisibility(4);
            this.k.setVisibility(4);
            this.g.setVisibility(4);
            this.i.setVisibility(4);
            this.m.setVisibility(4);
        }

        public void a(final g gVar, final int i) {
            DJILogHelper.getInstance().LOGI("bob", "ConfigureHolder configue");
            this.l = null;
            this.j.setVisibility(4);
            this.m.setVisibility(4);
            this.k.getHolder().addCallback(this);
            this.k.setVisibility(4);
            this.e = gVar.h;
            this.f = gVar;
            if (gVar.h == dji.pilot.gallery.entryActivity.d.b.Type_IMG) {
                this.a.setVisibility(8);
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                dji.pilot.gallery.entryActivity.b.getInstance().c(gVar.c, this.c);
            } else {
                this.q = new dji.pilot.gallery.previewActivity.widget.GalleryHScrollView.a(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a(int i, int i2, int i3, int i4) {
                        if (this.a.r.b != null) {
                            this.a.r.b.a(i, i2, i3, i4);
                        }
                    }

                    public void a(int i) {
                        if (this.a.r.b != null) {
                            this.a.r.b.a(i);
                        }
                    }

                    public void b(int i) {
                        if (this.a.r.b != null) {
                            this.a.r.b.b(i);
                        }
                    }

                    public void c(int i) {
                        if (this.a.r.b != null) {
                            this.a.r.b.c(i);
                        }
                    }
                };
                this.g.setVisibility(0);
                this.g.init(this.h, gVar.d, gVar.c, this.q);
                this.a.setVisibility(0);
                a(this.a, gVar.j, gVar.i);
                this.b.setVisibility(0);
                this.c.setVisibility(8);
                dji.pilot.gallery.entryActivity.b.getInstance().a(gVar.c, this.a, 1, false);
            }
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    int i = 0;
                    DJILogHelper.getInstance().LOGI("bob", "mSurface onClick");
                    if (this.c.b.getVisibility() == 0) {
                        this.c.b.setVisibility(4);
                    } else if (this.c.p.getVisibility() == 0) {
                        this.c.p.setVisibility(4);
                        i = 1;
                    } else {
                        this.c.p.setVisibility(0);
                        i = 1;
                    }
                    this.c.r.b.a(gVar, i, i);
                }
            });
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    DJILogHelper.getInstance().LOGI("bob", "mVideoImage onClick");
                    if (this.c.r.b != null) {
                        if (this.c.a.getVisibility() == 0) {
                            this.c.b.setVisibility(4);
                            this.c.j.setVisibility(0);
                            this.c.a(this.c.k, gVar.j, gVar.i);
                            this.c.k.setVisibility(0);
                            this.c.g.setVisibility(0);
                            this.c.m.setVisibility(0);
                            this.c.g.init(this.c.h, gVar.d, gVar.c, this.c.q);
                            this.c.i.setVisibility(0);
                        } else if (this.c.b.getVisibility() == 0) {
                            this.c.b.setVisibility(4);
                        } else {
                            this.c.b.setVisibility(0);
                        }
                        this.c.r.b.a(gVar, i, 0);
                    }
                }
            });
            this.c.setOnViewTapListener(new OnViewTapListener(this) {
                final /* synthetic */ a c;

                public void onViewTap(View view, float f, float f2) {
                    if (this.c.r.b != null && this.c.e == dji.pilot.gallery.entryActivity.d.b.Type_IMG) {
                        this.c.r.b.a(gVar, i);
                    }
                }
            });
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.l = surfaceHolder;
            if (this.r.b != null) {
                this.r.b.a(surfaceHolder, this.d);
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (this.r.b != null) {
                this.r.b.a(surfaceHolder, i, i2, i3, this.d);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.l = null;
            if (this.r.b != null) {
                this.r.b.b(surfaceHolder, this.d);
            }
        }
    }

    public d(List<g> list, c cVar) {
        this.a = list;
        this.d = cVar;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a() {
        this.e.sendMessage(new Message());
    }

    public void a(List<g> list) {
        this.a = list;
        a();
    }

    public int getCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        DJILogHelper.getInstance().LOGI("bob", "instantiateItem mDatas.size=" + this.a.size() + " position=" + i);
        if (i >= this.a.size()) {
            return null;
        }
        g gVar = (g) this.a.get(i);
        if (this.f.size() <= 0) {
            View inflate = ((LayoutInflater) viewGroup.getContext().getSystemService("layout_inflater")).inflate(R.layout.gallery_preview_pager, null);
            a aVar = new a(this);
            aVar.c = (PhotoView) inflate.findViewById(R.id.ahe);
            aVar.b = (DJIImageView) inflate.findViewById(R.id.gi);
            aVar.a = (DJIImageView) inflate.findViewById(R.id.ahd);
            aVar.k = (SurfaceView) inflate.findViewById(R.id.gh);
            aVar.j = inflate.findViewById(R.id.gf);
            aVar.g = (GalleryHScrollView) inflate.findViewById(R.id.gk);
            aVar.h = (LinearLayout) inflate.findViewById(R.id.gn);
            aVar.i = (DJIImageView) inflate.findViewById(R.id.go);
            aVar.m = (LinearLayout) inflate.findViewById(R.id.gj);
            aVar.n = (DJITextView) inflate.findViewById(R.id.gl);
            aVar.o = (DJITextView) inflate.findViewById(R.id.gm);
            aVar.p = (RelativeLayout) inflate.findViewById(R.id.ahf);
            aVar.d = i;
            inflate.setTag(aVar);
            aVar.a(gVar, i);
            viewGroup.addView(inflate);
            return inflate;
        }
        View view = (View) this.f.remove(0);
        a aVar2 = (a) view.getTag();
        aVar2.d = i;
        aVar2.a(gVar, i);
        viewGroup.addView(view);
        return view;
    }

    public void finishUpdate(ViewGroup viewGroup) {
        super.finishUpdate(viewGroup);
    }

    public int getItemPosition(Object obj) {
        return ((a) ((View) obj).getTag()).d;
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        this.c = (View) obj;
    }

    public View b() {
        return this.c;
    }
}
