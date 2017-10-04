package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import dji.midware.data.model.P3.DataFlycDownloadWayPointMissionMsg;
import dji.midware.data.model.P3.DataFlycDownloadWayPointMsgByIndex;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.b;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DJIWaitDownloadMissionView extends DJILinearLayout implements a {
    private ProgressBar a = null;
    private DJITextView b = null;
    private final Handler c = new Handler();
    private boolean d = false;
    private boolean e = false;

    public DJIWaitDownloadMissionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.a.setProgress(0);
        this.b.setText("0%");
        switch (dji.pilot.groundStation.a.a.getInstance(null).z()) {
            case WP_AUTO:
                downloadWayPointMession();
                return;
            default:
                return;
        }
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            ((DJITextView) findViewById(R.id.ano)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIWaitDownloadMissionView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.e = true;
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                }
            });
            this.a = (ProgressBar) findViewById(R.id.anm);
            this.a.setProgress(0);
            this.b = (DJITextView) findViewById(R.id.ann);
            this.b.setText("0%");
        }
    }

    public void setProgress(final int i) {
        this.c.post(new Runnable(this) {
            final /* synthetic */ DJIWaitDownloadMissionView b;

            public void run() {
                this.b.a.setProgress(i);
                this.b.b.setText(String.format("%d%%", new Object[]{Integer.valueOf(i)}));
            }
        });
    }

    public void dismiss(boolean z, boolean z2, DJIWPCollectionItem dJIWPCollectionItem) {
        final Semaphore semaphore = new Semaphore(0);
        final boolean z3 = z;
        final DJIWPCollectionItem dJIWPCollectionItem2 = dJIWPCollectionItem;
        final boolean z4 = z2;
        this.c.post(new Runnable(this) {
            final /* synthetic */ DJIWaitDownloadMissionView e;

            public void run() {
                if (z3) {
                    switch (dji.pilot.groundStation.a.a.getInstance(null).z()) {
                        case WP_AUTO:
                            dji.pilot.groundStation.a.a.getInstance(null).c(this.e.a(dJIWPCollectionItem2));
                            ((DJIStageView) this.e.getParent()).createStageView(R.layout.gs_way_point_status_view, 21, false);
                            break;
                    }
                } else if (!z4) {
                    ((DJIStageView) this.e.getParent()).createStageView(R.layout.gs_wait_download_mission_failed_view, 30, false);
                }
                semaphore.release();
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void downloadWayPointMession() {
        final DataFlycDownloadWayPointMissionMsg instance = DataFlycDownloadWayPointMissionMsg.getInstance();
        final DataFlycDownloadWayPointMsgByIndex instance2 = DataFlycDownloadWayPointMsgByIndex.getInstance();
        final DJIWPCollectionItem dJIWPCollectionItem = new DJIWPCollectionItem();
        final Semaphore semaphore = new Semaphore(0);
        this.d = false;
        instance.start(new d(this) {
            final /* synthetic */ DJIWaitDownloadMissionView e;

            public void onSuccess(Object obj) {
                if (instance.getResult() == 0) {
                    final int wayPointCount = instance.getWayPointCount();
                    dji.pilot.groundStation.a.a.getInstance(null).a(instance.getFinishAction());
                    dji.pilot.groundStation.a.a.getInstance(null).c(instance.getIdleSpeed());
                    for (int i = 0; i < wayPointCount; i++) {
                        instance2.a(i);
                        instance2.start(new d(this) {
                            final /* synthetic */ AnonymousClass4 c;

                            public void onSuccess(Object obj) {
                                if (instance2.a() == 0) {
                                    this.c.e.setProgress((i * 100) / wayPointCount);
                                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = new DJIWPCollectionItem$WayPoint(this.c.e.a(instance2.c()), this.c.e.a(instance2.d()), (double) instance2.e());
                                    dJIWPCollectionItem$WayPoint.setCraftYaw(instance2.g());
                                    dJIWPCollectionItem$WayPoint.setGimbalPitch(instance2.h());
                                    dJIWPCollectionItem.getPoints().add(dJIWPCollectionItem$WayPoint);
                                } else {
                                    this.c.e.d = true;
                                }
                                semaphore.release();
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.c.e.d = true;
                                semaphore.release();
                            }
                        });
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                        }
                        if (this.e.d || this.e.e) {
                            break;
                        }
                    }
                    if (this.e.e) {
                        this.e.dismiss(false, true, dJIWPCollectionItem);
                    } else if (this.e.d) {
                        this.e.dismiss(false, false, dJIWPCollectionItem);
                    } else {
                        this.e.dismiss(true, false, dJIWPCollectionItem);
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.e.d = true;
                this.e.dismiss(false, false, dJIWPCollectionItem);
            }
        });
    }

    private double a(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }

    private DJIWPCollectionItem a(DJIWPCollectionItem dJIWPCollectionItem) {
        List h = dji.pilot.groundStation.a.a.getInstance(null).h();
        for (int i = 0; i < h.size(); i++) {
            DJIWPCollectionItem dJIWPCollectionItem2 = (DJIWPCollectionItem) h.get(i);
            if (dJIWPCollectionItem2.getPoints().size() == dJIWPCollectionItem.getPoints().size()) {
                List points = dJIWPCollectionItem.getPoints();
                List points2 = dJIWPCollectionItem2.getPoints();
                int i2 = 0;
                while (i2 < dJIWPCollectionItem2.getPoints().size()) {
                    if (Math.abs(((DJIWPCollectionItem$WayPoint) points.get(i2)).getLat() - ((DJIWPCollectionItem$WayPoint) points2.get(i2)).getLat()) >= 1.0E-6d || Math.abs(((DJIWPCollectionItem$WayPoint) points.get(i2)).getLng() - ((DJIWPCollectionItem$WayPoint) points2.get(i2)).getLng()) >= 1.0E-6d || Math.abs(((DJIWPCollectionItem$WayPoint) points.get(i2)).getHeight() - ((DJIWPCollectionItem$WayPoint) points2.get(i2)).getHeight()) >= 1.0E-6d) {
                        i2++;
                    } else {
                        dji.pilot.groundStation.a.a.getInstance(null).d(true);
                        return dJIWPCollectionItem2;
                    }
                }
                continue;
            }
        }
        dJIWPCollectionItem.setCreatedDate(System.currentTimeMillis());
        dJIWPCollectionItem.setDistance(b.a(dJIWPCollectionItem.getPoints()));
        dji.pilot.groundStation.a.a.getInstance(null).d(false);
        return dJIWPCollectionItem;
    }
}
