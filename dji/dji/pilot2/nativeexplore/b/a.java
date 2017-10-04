package dji.pilot2.nativeexplore.b;

import android.content.Context;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.nativeexplore.model.ArtworkDetail;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.publics.b.a$i;
import dji.thirdparty.afinal.f.b;

public class a implements a$i {
    protected Context a;
    protected ExploreItem b;
    protected a c;

    public interface a {
        void a();

        void a(ExploreItem exploreItem);
    }

    public a(Context context) {
        this.a = context;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(String str, String str2, final int i) {
        String str3 = a$i.p + str2 + d.t + str;
        DJILogHelper.getInstance().LOGI("bob", "" + str3);
        dji.thirdparty.afinal.f.a anonymousClass1 = new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ a b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                DJILogHelper.getInstance().LOGI("bob", "ArtworkDetailController:getArtworkDetail onSuccess: " + str);
                if (str == null) {
                    DJILogHelper.getInstance().LOGI("bob", "ArtworkDetailController:getArtworkDetail onSuccess  err t=null");
                    return;
                }
                ArtworkDetail artworkDetail = (ArtworkDetail) h.b(str, ArtworkDetail.class);
                if (artworkDetail != null) {
                    this.b.b = new ExploreItem(artworkDetail, i);
                    if (this.b.c != null) {
                        this.b.c.a(this.b.b);
                        return;
                    }
                    return;
                }
                this.b.c.a();
            }

            public void a(Throwable th, int i, String str) {
                if (this.b.c != null) {
                    this.b.c.a();
                }
                DJILogHelper.getInstance().LOGI("bob", "errorNo=" + i + " strMsg = " + str);
            }
        };
        b bVar = new b();
        if (f.getInstance().c()) {
            bVar.a("token", f.getInstance().n());
        } else {
            bVar.a("token", "");
        }
        DJILogHelper.getInstance().LOGI("bob", "ArtworkDetailController:getArtworkDetail url: " + str3);
        c.b(this.a).a(str3, bVar, anonymousClass1);
    }
}
