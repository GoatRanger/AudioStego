package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.template.d;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class c extends e implements k {
    protected static final String K = "MultiTemplateListAdaper";
    List<dji.pilot2.template.a> L;
    dji.pilot2.multimoment.videolib.b M;
    c N;

    public interface c {
        void a();

        void a(int i);

        void b(int i);
    }

    public class a extends b {
        DJITextView a = null;
        DJIImageView b;
        DJIImageView c;
        DJITextView d;
        DJITextView e;
        DJITextView f;
        View g;
        DJIImageView h;
        DJITextView i;
        DJITextView j;
        public int k;
        final /* synthetic */ c l;

        public a(c cVar) {
            this.l = cVar;
            super(cVar);
        }

        public void a(int i) {
            dji.pilot2.template.a aVar = (dji.pilot2.template.a) TemplateController.getInstance().templates.get(i);
            this.k = i;
            DJILogHelper.getInstance().LOGI("bob", "onCollectClicked notifyDataSetChanged bean = " + aVar.e() + "position = " + i);
            this.f.setText(aVar.getTemplateName());
            this.b.setImageBitmap(aVar.getThumbnailBitmap());
            String description = aVar.getDescription();
            if (description == TemplateController.MOREMUSIC) {
                this.c.setVisibility(8);
                this.h.setVisibility(8);
                this.g.setVisibility(8);
                this.i.setVisibility(8);
                this.j.setVisibility(8);
                this.f.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
            } else if (description == TemplateController.LOCALMUSIC) {
                this.h.setVisibility(8);
                this.g.setVisibility(8);
                this.i.setVisibility(8);
                this.j.setVisibility(0);
                this.j.setText(this.l.b.getString(R.string.v2_localmusic_name));
                if (this.l.d == i) {
                    this.l.f = this.c;
                    this.c.setVisibility(0);
                    this.j.setTextColor(this.l.b.getResources().getColorStateList(R.color.j0));
                    this.f.setTextColor(this.l.b.getResources().getColorStateList(R.color.j0));
                    this.j.setBackgroundResource(R.drawable.v2_localmusic_bg);
                    return;
                }
                this.c.setVisibility(4);
                this.j.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
                this.f.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
                this.j.setBackgroundResource(R.drawable.v2_localmusic_normal_bg);
            } else {
                this.j.setVisibility(8);
                if (this.l.M == dji.pilot2.multimoment.videolib.b.MultiEdit_Normal) {
                    this.g.setVisibility(8);
                } else {
                    this.g.setVisibility(0);
                    this.e.setText(String.valueOf(aVar.size()));
                    this.d.setText(p.e((int) (aVar.getTotalDurations() / 1000)));
                }
                if (this.l.d == i) {
                    this.l.f = this.c;
                    this.c.setVisibility(0);
                    this.h.setVisibility(0);
                    if (this.g.getVisibility() == 0) {
                        this.e.setTextColor(this.l.b.getResources().getColorStateList(R.color.j0));
                        this.d.setTextColor(this.l.b.getResources().getColorStateList(R.color.j0));
                    }
                    this.f.setTextColor(this.l.b.getResources().getColorStateList(R.color.j0));
                } else {
                    this.c.setVisibility(4);
                    this.h.setVisibility(4);
                    if (this.g.getVisibility() == 0) {
                        this.e.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
                        this.d.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
                    }
                    this.f.setTextColor(this.l.b.getResources().getColorStateList(R.color.om));
                }
                if (aVar.e() != 0) {
                    this.h.setVisibility(0);
                    this.h.setImageResource(R.drawable.fillheart);
                } else {
                    this.h.setImageResource(R.drawable.emptyheat);
                }
                if (aVar.f().booleanValue()) {
                    this.i.setVisibility(0);
                } else {
                    this.i.setVisibility(8);
                }
            }
        }
    }

    public interface b {
        void a(int i);
    }

    public void a(c cVar) {
        this.N = cVar;
    }

    public c(Context context, List<dji.pilot2.template.a> list) {
        super(context);
        this.L = list;
    }

    public void a(int i) {
        super.a(i);
    }

    public void a(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null && this.e != null) {
            dji.pilot2.template.a aVar2 = (dji.pilot2.template.a) TemplateController.getInstance().templates.get(aVar.k);
            String description = aVar2.getDescription();
            if (description == TemplateController.MOREMUSIC) {
                if (this.N != null) {
                    e.b(k.H);
                }
                this.N.a();
            } else if (this.d != aVar.k) {
                if (this.f != null) {
                    this.f.setVisibility(4);
                }
                if (aVar2.f().booleanValue()) {
                    aVar2.a(Boolean.valueOf(false));
                }
                this.d = aVar.k;
                this.f = view.findViewById(R.id.cu2);
                this.f.setVisibility(0);
                notifyDataSetChanged();
                if (description != TemplateController.LOCALMUSIC) {
                    this.e.a(aVar.k);
                } else if (this.N != null) {
                    this.N.a(aVar.k);
                }
            }
        }
    }

    public void a() {
        if (TemplateController.getInstance().delLocalMusicTemplateBean().booleanValue()) {
            notifyDataSetChanged();
        }
    }

    public Boolean a(String str, Context context) {
        if (!TemplateController.getInstance().addLocalMusicTemplateBean(str, context).booleanValue()) {
            return Boolean.valueOf(false);
        }
        notifyDataSetChanged();
        return Boolean.valueOf(true);
    }

    public Boolean a(String str, Bitmap bitmap, Context context) {
        if (!TemplateController.getInstance().addLocalMusicTemplateBean(str, bitmap, context).booleanValue()) {
            return Boolean.valueOf(false);
        }
        notifyDataSetChanged();
        return Boolean.valueOf(true);
    }

    public int b(int i) {
        Boolean d;
        dji.pilot2.template.a aVar = (dji.pilot2.template.a) this.L.get(i);
        dji.pilot2.template.a aVar2 = (dji.pilot2.template.a) this.L.get(this.d);
        Boolean.valueOf(false);
        if (aVar.e() != 0) {
            d = d(i);
        } else {
            d = c(i);
        }
        this.d = this.L.indexOf(aVar2);
        if (d.booleanValue()) {
            DJILogHelper.getInstance().LOGI("bob", "onCollectClicked notifyDataSetChanged bean = " + aVar.e() + "selectedTemplateIndex = " + this.d);
            notifyDataSetChanged();
        }
        return this.d;
    }

    protected Boolean c(int i) {
        if (TemplateController.getInstance().setCollect(i).booleanValue()) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    protected Boolean d(int i) {
        if (TemplateController.getInstance().delCollect(i).booleanValue()) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.c.inflate(R.layout.v2_multimoment_template_list_layout, null);
            a aVar2 = new a(this);
            aVar2.b = (DJIImageView) view.findViewById(R.id.cu1);
            aVar2.c = (DJIImageView) view.findViewById(R.id.cu2);
            aVar2.d = (DJITextView) view.findViewById(R.id.ctq);
            aVar2.f = (DJITextView) view.findViewById(R.id.cu5);
            aVar2.e = (DJITextView) view.findViewById(R.id.cts);
            aVar2.g = view.findViewById(R.id.ctp);
            aVar2.h = (DJIImageView) view.findViewById(R.id.ctt);
            aVar2.i = (DJITextView) view.findViewById(R.id.cu3);
            aVar2.j = (DJITextView) view.findViewById(R.id.cu6);
            aVar2.k = i;
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                if (this.b.N != null) {
                    DJILogHelper.getInstance().LOGI("bob", "on collect buttondown");
                    e.b(k.E);
                    this.b.N.b(i);
                }
            }
        });
        aVar.a(i);
        view.requestLayout();
        return view;
    }

    public d b() {
        return (d) this.L.get(this.d);
    }

    public Boolean c() {
        if (((dji.pilot2.template.a) this.L.get(this.d)).getDescription() == TemplateController.LOCALMUSIC) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public int getCount() {
        return this.L == null ? 0 : this.L.size();
    }

    public Object getItem(int i) {
        return this.L.get(i);
    }

    public void a(dji.pilot2.multimoment.videolib.b bVar) {
        this.M = bVar;
        notifyDataSetChanged();
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }
}
