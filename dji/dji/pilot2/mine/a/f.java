package dji.pilot2.mine.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.mine.b.d;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import java.util.List;

public class f extends BaseAdapter {
    private Context a;
    private List<LevelInfo> b;
    private int[][] c = new int[][]{d.a, d.b, d.c, d.d};
    private int[][] d = new int[][]{d.e, d.f, d.g, d.h};
    private int[] e = new int[]{R.string.medal_pilot_name, R.string.medal_director_name, R.string.medal_photographer_name, R.string.medal_sharer_name};
    private int[] f = new int[]{R.string.medal_pilot_next_level, R.string.medal_director_next_level, R.string.medal_photographer_next_level, R.string.medal_sharer_next_level};

    public class a {
        public ImageView a;
        public TextView b;
        public TextView c;
        final /* synthetic */ f d;

        public a(f fVar) {
            this.d = fVar;
        }

        public void a(int i) {
            LevelInfo levelInfo;
            if (this.d.b.size() <= i) {
                levelInfo = new LevelInfo();
            } else {
                levelInfo = (LevelInfo) this.d.b.get(i);
            }
            int[] iArr = this.d.c[i];
            int[] iArr2 = this.d.d[i];
            if (levelInfo.level <= 0) {
                this.a.setImageResource(iArr2[0]);
            } else {
                int i2 = levelInfo.level - 1;
                if (i2 >= iArr.length) {
                    i2 = iArr.length - 1;
                }
                this.a.setImageResource(iArr[i2]);
            }
            this.b.setText(this.d.a.getResources().getString(this.d.e[i], new Object[]{Integer.valueOf(levelInfo.level)}));
            this.c.setText(this.d.a.getResources().getString(this.d.f[i], new Object[]{Integer.valueOf(levelInfo.level + 1), Integer.valueOf(levelInfo.exp), Integer.valueOf(levelInfo.nextexp)}));
            if (levelInfo.level >= iArr.length) {
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
            }
        }
    }

    public f(Context context) {
        this.a = context;
    }

    public void a(List<LevelInfo> list) {
        this.b = list;
    }

    public int getCount() {
        return 4;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_medal_level, null);
            view.setTag(new a(this));
        }
        a aVar = (a) view.getTag();
        aVar.a = (ImageView) view.findViewById(R.id.csb);
        aVar.b = (TextView) view.findViewById(R.id.csc);
        aVar.c = (TextView) view.findViewById(R.id.csf);
        aVar.a(i);
        return view;
    }
}
