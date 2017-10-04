package dji.pilot2.nativeexplore.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.explore.activity.DJIMediaDisplayActivity;
import dji.pilot2.nativeexplore.activity.ExploreLikesActivity;
import dji.pilot2.nativeexplore.activity.GLMapActivity;
import dji.pilot2.nativeexplore.model.MiddleListModel;
import dji.pilot2.nativeexplore.model.MiddleListModel.MiddleItemModel;

public class c extends a<MiddleListModel> {
    private b e;
    private GLExploreList f;

    class a {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public ImageView f;
        public TextView g;
        public TextView h;
        public TextView i;
        final /* synthetic */ c j;

        a(c cVar) {
            this.j = cVar;
        }
    }

    class b extends BaseAdapter {
        final /* synthetic */ c a;

        b(c cVar) {
            this.a = cVar;
        }

        public int getCount() {
            if (this.a.a == null || ((MiddleListModel) this.a.a).items.size() <= 0) {
                return 0;
            }
            return ((MiddleListModel) this.a.a).items.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(this.a.b).inflate(R.layout.gl_listitem_layout, null);
                aVar = new a(this.a);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a = (ImageView) view.findViewById(R.id.ai1);
            aVar.b = (TextView) view.findViewById(R.id.ai2);
            aVar.c = (TextView) view.findViewById(R.id.ai3);
            aVar.e = (TextView) view.findViewById(R.id.ai4);
            aVar.f = (ImageView) view.findViewById(R.id.ai5);
            aVar.g = (TextView) view.findViewById(R.id.ai7);
            aVar.h = (TextView) view.findViewById(R.id.ai8);
            aVar.d = (TextView) view.findViewById(R.id.ai6);
            aVar.i = (TextView) view.findViewById(R.id.ai9);
            aVar.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b.a.b, DJIMediaDisplayActivity.class);
                    intent.putExtra("preview_photo", "photo");
                    intent.putExtra("preview_thumburl", ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).image_large);
                    intent.putExtra("preview_title", ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).title);
                    intent.putExtra(ExploreLikesActivity.a, "explore_tmp");
                    intent.putExtra(DJIMediaDisplayActivity.b, true);
                    this.b.a.b.startActivity(intent);
                    ((Activity) this.b.a.b).overridePendingTransition(17432576, 17432577);
                }
            });
            aVar.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(this.b.a.b, GLMapActivity.class);
                    intent.putExtra("latitude", ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).shooting_latitude);
                    intent.putExtra(GLMapActivity.r, ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).shooting_longitude);
                    intent.putExtra(GLMapActivity.s, ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).take_off_latitude);
                    intent.putExtra(GLMapActivity.t, ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).take_off_longitude);
                    intent.putExtra(GLMapActivity.u, ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).image);
                    intent.putExtra(GLMapActivity.w, ((MiddleItemModel) ((MiddleListModel) this.b.a.a).items.get(i)).title);
                    intent.putExtra(GLMapActivity.v, ((MiddleListModel) this.b.a.a).location);
                    this.b.a.b.startActivity(intent);
                }
            });
            aVar.d.setText("" + (i + 1));
            aVar.a.setBackgroundResource(17170445);
            aVar.a.setImageBitmap(null);
            if (this.a.a != null && ((MiddleListModel) this.a.a).items.size() > 0) {
                this.a.d.displayImage(((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).image, aVar.a);
                aVar.e.setText(this.a.b.getString(R.string.v2_gl_iso) + ":" + ((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).iso);
                aVar.c.setText(this.a.b.getString(R.string.v2_gl_speed) + ":" + ((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).shutter);
                aVar.b.setText(this.a.b.getString(R.string.v2_gl_scence) + ":" + ((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).aperture);
                aVar.g.setText(((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).title);
                aVar.h.setText(((MiddleItemModel) ((MiddleListModel) this.a.a).items.get(i)).description);
            }
            view.setTag(aVar);
            return view;
        }
    }

    public c(MiddleListModel middleListModel, Context context) {
        super(middleListModel, context);
    }

    public void a(MiddleListModel middleListModel) {
        this.c = LayoutInflater.from(this.b).inflate(R.layout.gl_middle_layout, null);
        this.f = (GLExploreList) this.c.findViewById(R.id.aie);
        this.e = new b(this);
        this.f.setAdapter(this.e);
        this.e.notifyDataSetChanged();
    }

    public void b(MiddleListModel middleListModel) {
        this.a = middleListModel;
    }

    public void b() {
        this.e.notifyDataSetChanged();
    }

    public View a() {
        return this.c;
    }

    public int a(int i) {
        int i2 = 0;
        if (i < this.f.getChildCount()) {
            int i3 = 0;
            while (i3 < i) {
                int height = this.f.getChildAt(i3).getHeight() + i2;
                i3++;
                i2 = height;
            }
        }
        return i2;
    }
}
