package dji.pilot2.library;

import dji.log.DJILogHelper;
import dji.logic.album.a.d.a;
import dji.logic.album.a.e;
import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.pilot.fpv.d.b;
import dji.pilot.playback.litchi.h;
import dji.pilot.publics.e.c;
import dji.pilot2.simulator.d;

public class g {
    private static g c = null;
    int a;
    private e b;

    private g() {
        this.b = null;
        this.a = 0;
        this.b = h.getInstance().g();
    }

    public static g getInstance() {
        if (c == null) {
            c = new g();
        }
        return c;
    }

    public void a(final a<DJIAlbumDirInfo> aVar) {
        if (!d.getInstance().e() || d.h()) {
            d.getInstance().a(false);
            aVar.a(DJIAlbumPullErrorType.h);
            return;
        }
        DJILogHelper.getInstance().LOGD("", "********************************pullfileBegin***********************", true, true);
        final MODE b = c.b();
        if (a(b)) {
            DataCameraSetMode.getInstance().a(b).start(new dji.midware.e.d(this) {
                final /* synthetic */ g c;

                public void onSuccess(Object obj) {
                    this.c.a = 0;
                    if (!d.getInstance().e()) {
                        d.getInstance().a(false);
                        aVar.a(DJIAlbumPullErrorType.h);
                    }
                    this.c.b.a(aVar);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    g gVar = this.c;
                    gVar.a++;
                    if (!d.getInstance().e()) {
                        d.getInstance().a(false);
                        aVar.a(DJIAlbumPullErrorType.h);
                        this.c.a = 0;
                    } else if (this.c.a > 3 || !this.c.a(b)) {
                        this.c.a = 0;
                    } else {
                        this.c.a(aVar);
                    }
                }
            });
        } else if (d.getInstance().e()) {
            this.b.a(aVar);
        } else {
            d.getInstance().a(false);
            aVar.a(DJIAlbumPullErrorType.h);
        }
    }

    private boolean a(MODE mode) {
        if (b.c(i.getInstance().c()) || b.k(null) || DataCameraGetPushStateInfo.getInstance().getMode() == mode || !d.getInstance().e()) {
            return false;
        }
        return true;
    }
}
