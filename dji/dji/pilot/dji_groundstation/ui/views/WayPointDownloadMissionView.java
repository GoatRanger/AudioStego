package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycDownloadWayPointMissionMsg;
import dji.midware.data.model.P3.DataFlycDownloadWayPointMsgByIndex;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import java.util.concurrent.Semaphore;

public class WayPointDownloadMissionView extends FrameLayout {
    private static final String a = "DownloadMissionView";
    private WayPointUploadProgressView b = null;
    private Handler c = null;
    private boolean d = false;

    public WayPointDownloadMissionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_waypoint_download_mission, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (WayPointUploadProgressView) findViewById(R.id.waypoint_download_progressbar);
        this.c = this.b.getUpdateHandler();
        a();
    }

    private void a() {
        DataFlycDownloadWayPointMissionMsg.getInstance().start(new d(this) {
            final /* synthetic */ WayPointDownloadMissionView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (DataFlycDownloadWayPointMissionMsg.getInstance().getResult() == 0) {
                    final int wayPointCount = DataFlycDownloadWayPointMissionMsg.getInstance().getWayPointCount();
                    e.getInstance().a(DataFlycDownloadWayPointMissionMsg.getInstance().getFinishAction());
                    e.getInstance().a(DataFlycDownloadWayPointMissionMsg.getInstance().getIdleSpeed());
                    e.getInstance().q();
                    final Semaphore semaphore = new Semaphore(0);
                    for (int i = 0; i < wayPointCount; i++) {
                        DataFlycDownloadWayPointMsgByIndex.getInstance().a(i);
                        DataFlycDownloadWayPointMsgByIndex.getInstance().start(new d(this) {
                            final /* synthetic */ AnonymousClass1 d;

                            public void onSuccess(Object obj) {
                                if (DataFlycDownloadWayPointMsgByIndex.getInstance().a() == 0) {
                                    Message obtain = Message.obtain();
                                    obtain.what = 258;
                                    obtain.arg1 = (i * 100) / wayPointCount;
                                    this.d.a.c.sendMessage(obtain);
                                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = new DJIWPCollectionItem$WayPoint(f.a(DataFlycDownloadWayPointMsgByIndex.getInstance().c()), f.a(DataFlycDownloadWayPointMsgByIndex.getInstance().d()), (double) DataFlycDownloadWayPointMsgByIndex.getInstance().e());
                                    dJIWPCollectionItem$WayPoint.setCraftYaw(DataFlycDownloadWayPointMsgByIndex.getInstance().g());
                                    dJIWPCollectionItem$WayPoint.setGimbalPitch(DataFlycDownloadWayPointMsgByIndex.getInstance().h());
                                    e.getInstance().a(dJIWPCollectionItem$WayPoint);
                                    this.d.a.d = false;
                                } else {
                                    this.d.a.d = true;
                                }
                                semaphore.release();
                            }

                            public void onFailure(a aVar) {
                                semaphore.release();
                                this.d.a.d = true;
                            }
                        });
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            Log.v(WayPointDownloadMissionView.a, e.toString());
                        }
                        if (this.a.d) {
                            break;
                        }
                    }
                    if (this.a.d) {
                        dji.pilot.dji_groundstation.controller.f.getInstance(this.a.getContext()).b(false);
                        this.a.b();
                        return;
                    }
                    e.getInstance().u();
                    dji.pilot.dji_groundstation.controller.d.getInstance().b(c.g);
                    return;
                }
                dji.pilot.dji_groundstation.controller.f.getInstance(this.a.getContext()).b(false);
                this.a.b();
            }

            public void onFailure(a aVar) {
                this.a.d = true;
                dji.pilot.dji_groundstation.controller.f.getInstance(this.a.getContext()).b(false);
                this.a.b();
            }
        });
    }

    private void b() {
        dji.pilot.dji_groundstation.a.a aVar = new dji.pilot.dji_groundstation.a.a();
        aVar.a = R.string.gsnew_gs_wait_download_mession_title;
        aVar.b = R.string.gsnew_gs_wait_download_mession_failed_desc;
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        aVar.h = R.string.gsnew_gs_wait_dialog_cancel;
        aVar.g = "gs://flightmode/main";
        aVar.j = R.string.gsnew_gs_way_point_upload_failed_retry;
        aVar.i = "gs://smartmode/waypoint/downloadMisson";
        dji.pilot.dji_groundstation.controller.d.getInstance().a(g.EVENT_SMARTMODE_ERROR_PUSH_TO_CONFIRM_DIALOG, aVar);
    }
}
