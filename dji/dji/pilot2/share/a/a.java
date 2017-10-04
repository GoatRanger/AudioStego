package dji.pilot2.share.a;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.nativeexplore.model.ClickedBannerBean;
import dji.pilot2.share.mode.ContestsModel.Event;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.ArrayList;
import java.util.List;

public class a extends PagerAdapter implements OnClickListener, o {
    private Context T;
    private List<Event> U;
    private List<View> V = new ArrayList();
    private List<ClickedBannerBean> W;
    private DJIDeviceType X = DJIOriLayout.getDeviceType();
    private boolean Y;
    private a Z;

    public interface a {
        void a(Event event, View view);
    }

    public a(Context context, List<Event> list) {
        this.T = context;
        this.U = list;
        this.W = c.c(context).c(ClickedBannerBean.class);
        this.Y = context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
        b();
    }

    public void a(a aVar) {
        this.Z = aVar;
    }

    private void b() {
        DJILogHelper.getInstance().LOGI("Lyric", "set Banner List: " + this.U.size());
        for (int i = 0; i < this.U.size(); i++) {
            View inflate = LayoutInflater.from(this.T).inflate(R.layout.v2_share_contest_banner_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ckj);
            if (this.X == DJIDeviceType.Pad) {
                if (this.Y) {
                    c.a(this.T).a(imageView, ((Event) this.U.get(i)).cn_pad_banner_url);
                } else {
                    c.a(this.T).a(imageView, ((Event) this.U.get(i)).en_pad_banner_url);
                }
            } else if (this.Y) {
                c.a(this.T).a(imageView, ((Event) this.U.get(i)).cn_mobile_banner_url);
            } else {
                c.a(this.T).a(imageView, ((Event) this.U.get(i)).en_mobile_banner_url);
            }
            inflate.findViewById(R.id.czh).setOnClickListener(this);
            this.V.add(inflate);
        }
        notifyDataSetChanged();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getCount() {
        if (this.V.size() <= 1) {
            return this.V.size();
        }
        return Integer.MAX_VALUE;
    }

    public int a() {
        return this.V.size();
    }

    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.V == null || this.V.size() > 3) {
            viewGroup.removeView((View) this.V.get(i % this.V.size()));
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Log.i("rxq", "posision:" + i);
        Log.i("rxq", "container hcode:" + viewGroup.hashCode() + " view hcode:" + ((View) this.V.get(i % this.V.size())).hashCode());
        ViewGroup viewGroup2 = (ViewGroup) ((View) this.V.get(i % this.V.size())).getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView((View) this.V.get(i % this.V.size()));
        }
        viewGroup.addView((View) this.V.get(i % this.V.size()));
        if (this.V.get(i % this.V.size()) != null) {
            ImageView imageView = (ImageView) ((View) this.V.get(i % this.V.size())).findViewById(R.id.ckj);
            if (imageView instanceof ImageView) {
                if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                    if (this.Y) {
                        c.a(this.T).a(imageView, ((Event) this.U.get(i % this.V.size())).cn_mobile_banner_url);
                    } else {
                        c.a(this.T).a(imageView, ((Event) this.U.get(i % this.V.size())).en_mobile_banner_url);
                    }
                } else if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                    if (this.Y) {
                        c.a(this.T).a(imageView, ((Event) this.U.get(i % this.V.size())).cn_pad_banner_url);
                    } else {
                        c.a(this.T).a(imageView, ((Event) this.U.get(i % this.V.size())).en_pad_banner_url);
                    }
                }
            }
        }
        return this.V.get(i % this.V.size());
    }

    private boolean a(ViewGroup viewGroup, View view) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) == view) {
                return true;
            }
        }
        return false;
    }

    public void onClick(View view) {
        Event a;
        switch (view.getId()) {
            case R.id.czg:
                a = a(view, (int) R.id.czg);
                if (a != null) {
                    String str = a.learn_more;
                    if (str != null && !str.equals("")) {
                        Intent intent = new Intent(this.T, DJISupportShareWebviewActivity.class);
                        intent.putExtra(DJIWebviewFragment.o, str);
                        this.T.startActivity(intent);
                        return;
                    }
                    return;
                }
                return;
            case R.id.czh:
                e.b(o.cH_);
                a = a(view, (int) R.id.czh);
                if (a != null && this.Z != null) {
                    this.Z.a(a, view);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private Event a(View view, int i) {
        int i2 = 0;
        while (i2 < this.V.size()) {
            if (view == ((View) this.V.get(i2)).findViewById(i) && i2 < this.U.size() && this.U.get(i2) != null) {
                return (Event) this.U.get(i2);
            }
            i2++;
        }
        return null;
    }
}
