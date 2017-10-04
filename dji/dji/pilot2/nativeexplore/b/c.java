package dji.pilot2.nativeexplore.b;

import android.content.Context;
import com.dji.frame.c.h;
import dji.pilot2.nativeexplore.model.ClosedAdsModel;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.AdsModel;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.BannerModel;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.ExploreActivityModel;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.publics.b.a$i;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.List;

public class c implements a$i {
    public List<ExploreItem> a;
    public List<BannerModel> b;
    public List<ExploreActivityModel> c;
    public List<ClosedAdsModel> d;
    private Context t;
    private boolean u = false;
    private boolean v = false;
    private a w = null;

    public interface a {
        void a();
    }

    public c(Context context) {
        this.t = context;
        this.d = com.dji.frame.c.c.c(context).c(ClosedAdsModel.class);
    }

    public void a() {
        this.v = true;
        b bVar = new b();
        if (this.t.getResources().getConfiguration().locale.getCountry().equals("CN")) {
            bVar.a("lang", "cn");
        } else {
            bVar.a("lang", "en");
        }
        com.dji.frame.c.c.b(this.t).a(a$i.o, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (str != null) {
                    ExploreBannerAdsModel exploreBannerAdsModel = (ExploreBannerAdsModel) h.b(str, ExploreBannerAdsModel.class);
                    if (exploreBannerAdsModel != null && exploreBannerAdsModel.status == 0) {
                        this.a.b = exploreBannerAdsModel.banners;
                        this.a.c = exploreBannerAdsModel.event_banners;
                        List arrayList = new ArrayList();
                        for (AdsModel adsModel : exploreBannerAdsModel.ads) {
                            boolean z;
                            for (ClosedAdsModel closedAdsModel : this.a.d) {
                                if (closedAdsModel != null && closedAdsModel.url != null && adsModel != null && closedAdsModel.url.equals(adsModel.target_url)) {
                                    z = true;
                                    break;
                                }
                            }
                            z = false;
                            if (!z) {
                                arrayList.add(new ExploreItem(adsModel));
                            }
                        }
                        this.a.a = arrayList;
                        this.a.u = true;
                        if (this.a.w != null) {
                            this.a.w.a();
                        }
                    }
                }
                this.a.v = false;
            }

            public void a(Throwable th, int i, String str) {
                this.a.v = false;
            }
        });
    }

    public void a(a aVar) {
        this.w = aVar;
    }

    public boolean b() {
        return this.u;
    }

    public boolean c() {
        return this.v;
    }
}
