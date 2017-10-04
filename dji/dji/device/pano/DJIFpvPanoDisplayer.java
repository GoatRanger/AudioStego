package dji.device.pano;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.common.DJIUIEventManagerLongan.g;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.progressbar.LonganHorizontalProgressBar;
import dji.device.pano.LonganPanoStatusManager.a;
import dji.gs.e.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DJIFpvPanoDisplayer extends RelativeLayout {
    public static boolean p = true;
    public static boolean q = false;
    private static final String r = "DJIFpvPanoDisplayer";
    ImageView a;
    ImageView b;
    ImageView c;
    ImageView d;
    ProgressDialog e;
    RelativeLayout f;
    RelativeLayout g;
    DJIRelativeLayout h;
    ImageView i;
    LonganHorizontalProgressBar j;
    PhotoViewAttacher k;
    TextView l;
    Integer m;
    Integer n;
    LonganPanoStatusManager o;

    public DJIFpvPanoDisplayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        this.o.uninit();
    }

    private void a() {
        this.o = new LonganPanoStatusManager(getContext());
        this.f = (RelativeLayout) findViewById(R.id.longan_pano_display_ly);
        this.g = (RelativeLayout) findViewById(R.id.longan_pano_progress_ly);
        this.h = (DJIRelativeLayout) findViewById(R.id.longan_pano_retry_ly);
        this.c = (ImageView) findViewById(R.id.pano_retry_iv);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIFpvPanoDisplayer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o.retryDownload();
                this.a.h.setVisibility(8);
            }
        });
        this.d = (ImageView) findViewById(R.id.longan_switch_cancle_iv);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIFpvPanoDisplayer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.i = (ImageView) findViewById(R.id.longan_retry_cancle_iv);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIFpvPanoDisplayer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.j = (LonganHorizontalProgressBar) findViewById(R.id.longan_pano_progressbar);
        this.a = (ImageView) findViewById(R.id.longan_pano_screen_pano_iv);
        this.k = new PhotoViewAttacher(this.a);
        this.b = (ImageView) findViewById(R.id.longan_pano_cancle_iv);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIFpvPanoDisplayer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.l = new TextView(getContext());
        this.l.setTextColor(getResources().getColor(R.color.white));
        this.l.setTextSize(b.a);
        this.l.setGravity(14);
        this.l.setShadowLayer(6.0f, 1.0f, 1.0f, getResources().getColor(R.color.black_60P_longan));
        this.l.setText("");
        addView(this.l);
        LayoutParams layoutParams = (LayoutParams) this.l.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.addRule(14);
        b();
        if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.PANO && ServiceManager.getInstance().isConnected()) {
            this.l.setVisibility(0);
            this.l.setText(getResources().getString(R.string.longan_pano_ready_start));
        } else {
            this.l.setText("");
        }
        c.a().a(this);
    }

    private void b() {
        e();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e();
    }

    public void onEventMainThread(LonganPanoStatusManager longanPanoStatusManager) {
        if (DataGimbalGetPushAbnormalStatus.getInstance().isGetted() && !DataGimbalGetPushAbnormalStatus.getInstance().isPanoReady() && !this.o.isConnectLost) {
            return;
        }
        if (DataCameraGetPushStateInfo.getInstance().getIsGimbalBusy() && longanPanoStatusManager.getStatus() == a.READY_START) {
            this.l.setText("");
        } else if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.PANO) {
            switch (longanPanoStatusManager.getStatus()) {
                case NOT_PANOING:
                    this.l.setText("");
                    this.o.USBGotoLiveView();
                    return;
                case READY_START:
                    if (!(this.l.isShown() || this.g.isShown())) {
                        this.l.setVisibility(0);
                    }
                    this.l.setText(getResources().getString(R.string.longan_pano_ready_start));
                    return;
                case READY_NEXT:
                    this.l.setText(getResources().getString(R.string.longan_pano_ready_next));
                    return;
                case TAKING_PANO_PHOTO:
                    p = false;
                    if (!this.l.isShown()) {
                        this.l.setVisibility(0);
                    }
                    this.l.setText(longanPanoStatusManager.getCurTakedPhotoIndex() + " / " + longanPanoStatusManager.getSmallPhotoTotalNum());
                    return;
                case DOWNLOADING_PANO_PHOTO:
                    this.g.setVisibility(0);
                    this.l.setVisibility(4);
                    return;
                case REDOWNLOADING_PANO_PHOTO:
                case STITCHING:
                    return;
                case REDOWNLOADING_FAILED:
                    Log.d("pano", "reveive redown");
                    this.g.setVisibility(4);
                    this.l.setText(getResources().getString(R.string.longan_pano_download_failed));
                    this.h.setVisibility(0);
                    this.g.setVisibility(8);
                    return;
                case SWITCH_USB_FAILED:
                    this.l.setText(getResources().getString(R.string.longan_pano_switch_usb_failed));
                    c();
                    return;
                case READY_DISPLAY:
                    p = true;
                    this.l.setText("");
                    d();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(g gVar) {
        if (gVar == g.PANO_STITCHED_ONE_FRAME || gVar == g.PANO_DOWNLOADED_ONE_FILE) {
            this.j.setProgress((int) (((float) this.j.getProgress()) + this.o.getProgressUnit()));
        } else if (gVar == g.PANO_LOAD_FINISH) {
            this.j.setProgress(100);
        } else if (gVar == g.CLOSE_PANO_DISPLAYER) {
            c();
        } else if (gVar == g.PANO_TAKED_ONE) {
            if (this.f.isShown()) {
                c();
                Log.e("pano", "resetToRestart");
            }
        } else if (gVar == g.PANO_FAILED) {
            c();
        }
    }

    private void c() {
        this.o.reset();
        q = false;
        this.f.setVisibility(8);
        this.h.setVisibility(8);
        this.d.setVisibility(8);
        c.a().e(g.PANO_FINISH);
        c.a().e(m.SHOW_ALL);
        this.j.setProgress(0);
        this.l.setVisibility(0);
        p = true;
        if (this.o.mUsb_status == 1) {
            this.o.USBGotoLiveView();
        }
    }

    private void d() {
        this.g.setVisibility(4);
        this.f.setVisibility(0);
        this.a.setImageBitmap(this.o.getResultPano());
        this.k.update();
        q = true;
    }

    private void e() {
        int i = DJIPreviewActivityLongan.mScreenHeight;
        Log.d("height", "" + i + "tv" + this.l.getHeight());
        if (getResources().getConfiguration().orientation == 2) {
            this.l.setY(((float) i) * 0.91f);
        } else {
            this.l.setY(((float) i) * 0.62f);
        }
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() != dji.device.camera.a.c.b.PANO) {
            this.l.setVisibility(4);
            Log.d(r, "hide for:" + cVar.c());
            return;
        }
        this.l.setVisibility(0);
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.PANO) {
            return;
        }
        if (dataGimbalGetPushAbnormalStatus.isPanoReady()) {
            onEventMainThread(this.o);
        } else {
            this.l.setText(getResources().getString(R.string.longan_pano_incline_tip));
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a) {
            this.l.setText("");
        } else if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.PANO) {
            onEventMainThread(this.o);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.PANO || this.o.getStatus() != a.READY_START) {
            return;
        }
        if (dataCameraGetPushStateInfo.getIsGimbalBusy()) {
            this.l.setText("");
        } else {
            onEventMainThread(this.o);
        }
    }
}
