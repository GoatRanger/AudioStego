package dji.pilot2.mine.a;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.c;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import dji.pilot.R;
import dji.pilot2.mine.e.d;
import java.util.ArrayList;
import java.util.List;

public class a extends BaseAdapter {
    DisplayImageOptions a = new Builder().imageScaleType(ImageScaleType.EXACTLY).showImageOnLoading(R.drawable.v2_photo_defalut).showImageOnFail(R.drawable.v2_photo_defalut).delayBeforeLoading(0).displayer(new SimpleBitmapDisplayer()).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Config.RGB_565).build();
    private List<dji.pilot2.mine.e.a> b;
    private Context c;

    public class a {
        dji.pilot2.mine.e.a a;
        int b;
        ImageView c;
        TextView d;
        TextView e;
        final /* synthetic */ a f;

        public a(a aVar) {
            this.f = aVar;
        }

        public void a(int i) {
            this.a = (dji.pilot2.mine.e.a) this.f.getItem(i);
            if (this.a.a().equals("photo")) {
                this.d.setVisibility(4);
            } else if (this.a.a().equals("video")) {
                this.d.setVisibility(0);
                int i2 = ((d) this.a).i() / 60;
                int i3 = ((d) this.a).i() % 60;
                this.d.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
            }
            this.e.setText(this.a.f());
            this.c.setBackgroundResource(R.drawable.v2_photo_defalut);
            c.a(this.f.c).a(this.c, this.a.d());
        }
    }

    public a(Context context) {
        this.c = context;
        this.b = new ArrayList();
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) ((dji.pilot2.mine.e.a) this.b.get(i)).hashCode();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.v2_profile_artwork_item_layout, null);
            a aVar2 = new a(this);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c = (ImageView) view.findViewById(R.id.cy6);
        aVar.d = (TextView) view.findViewById(R.id.cy7);
        aVar.e = (TextView) view.findViewById(R.id.cy8);
        aVar.a(i);
        return view;
    }

    public void a(List<dji.pilot2.mine.e.a> list) {
        this.b = list;
        notifyDataSetChanged();
    }
}
