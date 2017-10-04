package dji.pilot2.academy.widget;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import com.dji.frame.c.c;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot2.academy.model.AcademyVideoInfo.VideoInfo;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.mine.view.FixRatioImageView;
import dji.pilot2.nativeexplore.activity.FullScreenLandscapeWebActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import java.util.ArrayList;
import java.util.List;

public class b extends BaseAdapter {
    private List<VideoInfo> a;
    private Context b;
    private int c = 1;

    public static class a {
        public String a = "你好啊";
        public String b = "因为自己的某个问题回复被称为鸡汤，所以感到困惑，才提出此问，看了那么多回复，发现对于鸡汤文，大家每个人心中都有自己的定义和看法，@ 李鹏程 提醒的是，关注什么是鸡汤文，妄图求得鸡汤文与非鸡汤文之间的准确分界实则是愚蠢的行为。通过这个问题我也在反思自己。";
        public String c = "14MB";
    }

    final class b {
        public RelativeLayout a;
        public RelativeLayout b;
        public DJIStateTextView c;
        public DJIStateTextView d;
        public DJIStateTextView e;
        public DJIStateImageView f;
        public FixRatioImageView g;
        public DJIStateTextView h;
        public DJIStateTextView i;
        public DJIStateTextView j;
        public DJIStateImageView k;
        public FixRatioImageView l;
        final /* synthetic */ b m;

        b(b bVar) {
            this.m = bVar;
        }

        void a(int i, int i2) {
            int i3 = i * i2;
            int i4 = i3 + 1;
            final VideoInfo a = this.m.b(i3);
            if (a != null) {
                this.c.setText(a.name);
                this.d.setVisibility(8);
                this.e.setText(a.remark);
                c.a(this.m.b).a(this.g, a.thumbnails);
                this.f.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        Intent intent = new Intent(this.b.m.b, FullScreenLandscapeWebActivity.class);
                        intent.putExtra(DJIWebviewFragment.o, a.url);
                        this.b.m.b.startActivity(intent);
                    }
                });
            } else {
                this.a.setVisibility(4);
            }
            if (i2 == 2) {
                a = this.m.b(i4);
                if (a != null) {
                    this.h.setText(a.name);
                    this.i.setVisibility(8);
                    this.j.setText(a.remark);
                    c.a(this.m.b).a(this.l, a.thumbnails);
                    this.k.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ b b;

                        public void onClick(View view) {
                            Intent intent = new Intent(this.b.m.b, DJISupportShareWebviewActivity.class);
                            intent.putExtra(DJIWebviewFragment.o, a.url);
                            this.b.m.b.startActivity(intent);
                        }
                    });
                    return;
                }
                this.b.setVisibility(4);
                return;
            }
            this.b.setVisibility(8);
        }
    }

    public b(Context context) {
        this.b = context;
        this.a = new ArrayList();
    }

    public b(Context context, int i) {
        this.b = context;
        this.c = i;
        this.a = new ArrayList();
    }

    public boolean a(List<VideoInfo> list) {
        if (list != null && list.size() > 0) {
            this.a.clear();
            this.a.addAll(list);
            notifyDataSetChanged();
        }
        return true;
    }

    public int getCount() {
        return (this.a.size() % this.c != 0 ? 1 : 0) + (this.a.size() / this.c);
    }

    public Object getItem(int i) {
        int size = (this.a.size() % this.c != 0 ? 1 : 0) + (this.a.size() / this.c);
        if (i < 0 || size <= i) {
            return null;
        }
        return this.a.get(i);
    }

    private VideoInfo b(int i) {
        if (i < 0 || this.a.size() <= i) {
            return null;
        }
        return (VideoInfo) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void a(int i) {
        this.c = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.v2_new_academy_video_cview, null);
            b bVar2 = new b(this);
            bVar2.a = (RelativeLayout) view.findViewById(R.id.cw0);
            bVar2.b = (RelativeLayout) view.findViewById(R.id.cw1);
            bVar2.c = (DJIStateTextView) bVar2.a.findViewById(R.id.cw4);
            bVar2.d = (DJIStateTextView) bVar2.a.findViewById(R.id.cw5);
            bVar2.e = (DJIStateTextView) bVar2.a.findViewById(R.id.cw6);
            bVar2.f = (DJIStateImageView) bVar2.a.findViewById(R.id.cw3);
            bVar2.g = (FixRatioImageView) bVar2.a.findViewById(R.id.cw2);
            bVar2.h = (DJIStateTextView) bVar2.b.findViewById(R.id.cw4);
            bVar2.i = (DJIStateTextView) bVar2.b.findViewById(R.id.cw5);
            bVar2.j = (DJIStateTextView) bVar2.b.findViewById(R.id.cw6);
            bVar2.k = (DJIStateImageView) bVar2.b.findViewById(R.id.cw3);
            bVar2.l = (FixRatioImageView) bVar2.b.findViewById(R.id.cw2);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a(i, this.c);
        return view;
    }
}
