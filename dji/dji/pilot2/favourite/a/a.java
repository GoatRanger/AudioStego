package dji.pilot2.favourite.a;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot.R;
import dji.pilot2.nativeexplore.activity.ArtworkDetailActivity;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreItem.ExploreType;
import dji.pilot2.nativeexplore.view.e;
import java.util.List;

public class a extends e {
    Context a;
    List<ExploreItem> b;
    boolean c = false;
    int d = 3;
    private View e;
    private View f;

    public class a implements OnClickListener {
        ExploreItem[] a;
        ViewGroup b;
        int c;
        final /* synthetic */ a d;

        public a(a aVar) {
            this.d = aVar;
        }

        private void a() {
            for (int i = 0; i < this.d.d; i++) {
                View childAt = this.b.getChildAt(i);
                View findViewById = childAt.findViewById(R.id.cu9);
                ImageView imageView = (ImageView) childAt.findViewById(R.id.cu8);
                TextView textView = (TextView) childAt.findViewById(R.id.cu_);
                ExploreItem exploreItem = null;
                if ((this.c * this.d.d) + i < this.d.b.size()) {
                    exploreItem = (ExploreItem) this.d.b.get((this.c * this.d.d) + i);
                }
                if (exploreItem == null || exploreItem.type == null) {
                    imageView.setVisibility(4);
                    findViewById.setVisibility(8);
                    textView.setVisibility(8);
                } else {
                    imageView.setVisibility(0);
                    if (ExploreType.video.equals(exploreItem.type)) {
                        findViewById.setVisibility(0);
                        int i2 = exploreItem.duration / 60;
                        int i3 = exploreItem.duration % 60;
                        textView.setVisibility(0);
                        textView.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
                    } else if (ExploreType.photo.equals(exploreItem.type)) {
                        findViewById.setVisibility(8);
                        textView.setVisibility(8);
                    }
                    if (exploreItem.displayImageUrl != null) {
                        c.a(this.d.a).a(imageView, exploreItem.displayImageUrl);
                    }
                    imageView.setTag(Integer.valueOf(i));
                    imageView.setOnClickListener(this);
                }
            }
        }

        public void onClick(View view) {
            int i = 0;
            if (view.getTag() != null && (view.getTag() instanceof Integer)) {
                i = ((Integer) view.getTag()).intValue();
            }
            ExploreItem exploreItem = (ExploreItem) this.d.b.get(i + (this.c * this.d.d));
            Intent intent = new Intent(this.d.a, ArtworkDetailActivity.class);
            intent.putExtra(ArtworkDetailActivity.t, h.a(exploreItem));
            this.d.a.startActivity(intent);
        }
    }

    public a(Context context) {
        this.a = context;
        this.e = LayoutInflater.from(context).inflate(R.layout.v2_explore_refresh_footer, null);
        this.f = new View(context);
    }

    public void a(List list) {
        this.b = list;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public int getCount() {
        int i = 0;
        if (this.b == null) {
            return 0;
        }
        int size = this.b.size() / this.d;
        if (this.b.size() % this.d != 0) {
            i = 1;
        }
        return (i + size) + 1;
    }

    public Object getItem(int i) {
        if (this.b == null || i < 0 || i >= this.b.size()) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i != getCount() - 1) {
            View inflate;
            if (view == null || view.getTag() == null) {
                inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_my_collection_artwork_line, null);
                inflate.setTag(new a(this));
            } else {
                inflate = view;
            }
            a aVar = (a) inflate.getTag();
            aVar.b = (ViewGroup) inflate;
            aVar.c = i;
            aVar.a();
            return inflate;
        } else if (this.c) {
            return this.f;
        } else {
            return this.e;
        }
    }

    public boolean isEnabled(int i) {
        return false;
    }
}
