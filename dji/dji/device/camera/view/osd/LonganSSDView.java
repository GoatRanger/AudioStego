package dji.device.camera.view.osd;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.device.common.view.LonganBLN;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class LonganSSDView extends DJILinearLayout {
    private static final String h = "LonganSSDView";
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;
    private static final int l = 4;
    protected int a = 0;
    protected int b = 0;
    protected long c = -1;
    protected DiskStatus d = DiskStatus.OTHER;
    protected DiskStatus e = DiskStatus.OTHER;
    protected DataCameraGetPushRawParams f;
    CameraType g = CameraType.OTHER;
    private TextView m = null;
    private TextView n = null;
    private TextView o = null;
    private LonganBLN p = null;
    private TextView q = null;
    private LinearLayout r = null;
    private int[] s;
    private Handler t = new Handler(new Callback(this) {
        final /* synthetic */ LonganSSDView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.show();
                    break;
                case 3:
                    this.a.go();
                    break;
                case 4:
                    this.a.a();
                    break;
            }
            return false;
        }
    });

    public LonganSSDView(Context context) {
        super(context);
    }

    public LonganSSDView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LonganSSDView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f = DataCameraGetPushRawParams.getInstance();
        this.s = getResources().getIntArray(R.array.fpv_camera_video_fps_array);
        this.m = (TextView) findViewById(R.id.longan_ssd_remainsize_tv);
        this.n = (TextView) findViewById(R.id.longan_ssd_resolution_tv);
        this.o = (TextView) findViewById(R.id.longan_ssd_fps_tv);
        this.p = (LonganBLN) findViewById(R.id.longan_ssd_bln);
        this.q = (TextView) findViewById(R.id.longan_ssd_error_info_tv);
        this.r = (LinearLayout) findViewById(R.id.longan_ssd_info_ly);
        onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        onEventBackgroundThread(DataCameraGetPushRawParams.getInstance());
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        this.t.sendEmptyMessage(4);
    }

    protected void a() {
        DataCameraGetPushRawParams instance = DataCameraGetPushRawParams.getInstance();
        if (instance.isGetted()) {
            DiskStatus diskStatus = instance.getDiskStatus();
            if (this.d != diskStatus) {
                this.d = diskStatus;
                a(diskStatus);
            }
            b();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.g) {
            this.g = cameraType;
            if (dataCameraGetPushStateInfo.getCameraType() == CameraType.DJICameraTypeFC550Raw) {
                this.t.sendEmptyMessage(1);
            } else {
                this.t.sendEmptyMessage(3);
            }
        }
    }

    protected void a(DiskStatus diskStatus) {
        int i = 1;
        this.q.setVisibility(8);
        if (diskStatus == DiskStatus.NOTCONNECTED) {
            this.q.setText(R.string.ssd_status_error_nossd);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.NA) {
            this.q.setText(R.string.ssd_status_error_na);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.LOW_FORMATING) {
            this.q.setText(R.string.ssd_status_format_low);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FAST_FORMATING) {
            this.q.setText(R.string.ssd_status_format_fast);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.INITIALIZING) {
            this.q.setText(R.string.ssd_status_init);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.DEVICE_ERROR) {
            this.q.setText(R.string.ssd_status_recognize_failed);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.VERIFY_ERROR) {
            this.q.setText(R.string.ssd_status_verify_failed);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FULL) {
            this.q.setText(R.string.ssd_status_full);
            this.q.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.STORING) {
            this.p.setVisibility(0);
            this.p.startAnim();
            i = 0;
        } else {
            this.p.setVisibility(4);
            this.p.stopAnim();
            i = 0;
        }
        if (i != 0) {
            this.q.setVisibility(0);
            this.r.setVisibility(8);
            return;
        }
        this.q.setVisibility(8);
        c();
    }

    protected void b() {
        if (DataCameraGetPushRawParams.getInstance().isGetted()) {
            long availableCapacity = this.f.getAvailableCapacity();
            if (this.c != availableCapacity) {
                this.c = availableCapacity;
                c();
                if (availableCapacity > 1024) {
                    this.m.setText(String.valueOf(availableCapacity / 1024) + "G");
                } else {
                    this.m.setText(String.valueOf(availableCapacity) + "M");
                }
            }
            int resolution = this.f.getResolution();
            if (this.a != resolution) {
                this.a = resolution;
                c();
                if (resolution <= 5) {
                    this.n.setText("720p");
                } else if (resolution <= 13) {
                    this.n.setText("1080p");
                } else if (resolution <= 22) {
                    this.n.setText("4k");
                } else if (resolution == 24) {
                    this.n.setText("2.7k");
                }
            }
            resolution = this.f.getFps();
            if (this.b != resolution) {
                this.b = resolution;
                c();
                if (resolution > this.s.length) {
                    this.o.setText("N/A");
                } else {
                    this.o.setText(this.s[resolution] + "");
                }
            }
        }
    }

    protected void c() {
        if (!this.r.isShown() && !this.q.isShown()) {
            this.r.setVisibility(0);
        }
    }
}
