package dji.pilot2.nativeexplore.a;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.d;
import dji.pilot.fpv.d.e;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.nativeexplore.model.ClickedBannerBean;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.BannerModel;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a extends PagerAdapter implements OnClickListener, o {
    private Context T;
    private List<BannerModel> U;
    private List<View> V = new ArrayList();
    private List<ClickedBannerBean> W;
    private DJIDeviceType X = DJIOriLayout.getDeviceType();

    public a(Context context) {
        this.T = context;
        this.W = c.c(context).c(ClickedBannerBean.class);
    }

    public void a(List<BannerModel> list) {
        this.U = list;
        DJILogHelper.getInstance().LOGI("Lyric", "set Banner List: " + list.size());
        int i = 0;
        while (i < list.size()) {
            View inflate = LayoutInflater.from(this.T).inflate(R.layout.v2_explore_banner_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.ckj);
            View findViewById = inflate.findViewById(R.id.ckk);
            if (this.X == DJIDeviceType.Pad) {
                c.a(this.T).a(imageView, ((BannerModel) list.get(i)).pad_img);
            } else {
                c.a(this.T).a(imageView, ((BannerModel) list.get(i)).mobile_img);
            }
            if (this.W != null) {
                int i2 = 0;
                while (i2 < this.W.size()) {
                    if (this.W.get(i2) != null && ((ClickedBannerBean) this.W.get(i2)).url.equals(((BannerModel) list.get(i)).target_url)) {
                        findViewById.setVisibility(4);
                        break;
                    }
                    i2++;
                }
            }
            inflate.setOnClickListener(this);
            this.V.add(inflate);
            i++;
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
        viewGroup.removeView((View) this.V.get(i % this.V.size()));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = (ViewGroup) ((View) this.V.get(i % this.V.size())).getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView((View) this.V.get(i % this.V.size()));
        }
        viewGroup.addView((View) this.V.get(i % this.V.size()));
        if (this.V.get(i % this.V.size()) != null) {
            ImageView imageView = (ImageView) ((View) this.V.get(i % this.V.size())).findViewById(R.id.ckj);
            if (imageView instanceof ImageView) {
                if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                    c.a(this.T).a(imageView, ((BannerModel) this.U.get(i % this.V.size())).mobile_img);
                } else if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                    c.a(this.T).a(imageView, ((BannerModel) this.U.get(i % this.V.size())).pad_img);
                }
            }
        }
        return this.V.get(i % this.V.size());
    }

    public void onClick(View view) {
        int i = 0;
        while (i < this.V.size()) {
            if (view == this.V.get(i)) {
                String str = "";
                if (i < this.U.size() && this.U.get(i) != null) {
                    str = ((BannerModel) this.U.get(i)).target_url;
                }
                View findViewById = view.findViewById(R.id.ckk);
                if (findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(4);
                    ClickedBannerBean clickedBannerBean = new ClickedBannerBean();
                    clickedBannerBean.url = str;
                    this.W.add(clickedBannerBean);
                    try {
                        c.c(this.T).a(clickedBannerBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (str != null && !str.equals("")) {
                    Intent intent = new Intent(this.T, DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, str);
                    this.T.startActivity(intent);
                    HashMap hashMap = new HashMap();
                    hashMap.put(d.dH, ((BannerModel) this.U.get(i)).name);
                    e.a(o.cD_, hashMap);
                    return;
                }
                return;
            }
            i++;
        }
    }
}
