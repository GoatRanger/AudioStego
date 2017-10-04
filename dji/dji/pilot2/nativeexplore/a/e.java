package dji.pilot2.nativeexplore.a;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.c;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.pilot.R;
import dji.pilot2.mine.activity.ProfileTestActivity;
import dji.pilot2.nativeexplore.activity.GLExporeActivity;
import dji.pilot2.nativeexplore.model.GuideListModel.GuideModel;
import java.util.List;

public class e extends dji.pilot2.nativeexplore.view.e {
    Context a;
    List<GuideModel> b;
    private View c;
    private View d;
    private boolean e;
    private DisplayImageOptions f = new Builder().showImageOnLoading(R.drawable.v2_avatar_default).cacheInMemory(true).cacheOnDisc(false).build();

    public class a implements OnClickListener {
        View a;
        int b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        ImageView h;
        ImageView i;
        final /* synthetic */ e j;

        public a(e eVar, View view) {
            this.j = eVar;
            this.a = view;
            this.c = (TextView) view.findViewById(R.id.coz);
            this.d = (TextView) view.findViewById(R.id.cp0);
            this.f = (TextView) view.findViewById(R.id.coy);
            this.e = (TextView) view.findViewById(R.id.cp1);
            this.g = (TextView) view.findViewById(R.id.cp3);
            this.h = (ImageView) view.findViewById(R.id.cp2);
            this.i = (ImageView) view.findViewById(R.id.cox);
        }

        public void a() {
            GuideModel guideModel = (GuideModel) this.j.b.get(this.b);
            this.c.setText(guideModel.title);
            this.e.setText(guideModel.description);
            this.d.setText(this.j.a.getResources().getString(R.string.v2_6_my_collection_location_count, new Object[]{Integer.valueOf(guideModel.nodes_count)}));
            this.f.setText(guideModel.city);
            c.a(this.j.a).a(this.i, guideModel.cover_image);
            if (guideModel.account != null) {
                this.g.setText(guideModel.account.name);
                ImageLoader.getInstance().displayImage(guideModel.account.avatar, this.h, this.j.f);
            }
            this.a.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.h.setOnClickListener(this);
        }

        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.cp2:
                case R.id.cp3:
                    GuideModel guideModel = (GuideModel) this.j.b.get(this.b);
                    intent = new Intent(this.j.a, ProfileTestActivity.class);
                    intent.putExtra("user_id", guideModel.account.id);
                    intent.putExtra("user_name", guideModel.account.name);
                    intent.putExtra(ProfileTestActivity.I, guideModel.account.avatar);
                    intent.putExtra("country", guideModel.account.country_code);
                    this.j.a.startActivity(intent);
                    return;
                default:
                    String str = ((GuideModel) this.j.b.get(this.b)).id;
                    intent = new Intent(this.j.a, GLExporeActivity.class);
                    intent.putExtra("id", str);
                    this.j.a.startActivity(intent);
                    return;
            }
        }
    }

    public e(Context context) {
        this.a = context;
        this.c = LayoutInflater.from(context).inflate(R.layout.v2_explore_refresh_footer, null);
        this.d = new View(context);
    }

    public void a(List list) {
        this.b = list;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size() + 1;
        }
        return 0;
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
        if (i != this.b.size()) {
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.v2_guide_list_item, null);
                view.setTag(new a(this, view));
            }
            a aVar = (a) view.getTag();
            aVar.b = i;
            aVar.a();
            return view;
        } else if (this.e) {
            return this.d;
        } else {
            return this.c;
        }
    }

    public boolean isEnabled(int i) {
        return false;
    }
}
