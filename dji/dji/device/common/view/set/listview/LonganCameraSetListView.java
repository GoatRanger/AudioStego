package dji.device.common.view.set.listview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.LonganCameraShotcutsView;
import dji.device.common.view.set.view.b;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusAid;
import dji.midware.data.model.P3.DataCameraSetPushChart;
import dji.midware.data.model.P3.DataOsdGetMicGain;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.Iterator;

public class LonganCameraSetListView extends DJIBaseListView {
    private static final int A = 7;
    private static final int B = 8;
    private static final int C = 9;
    private static final int D = 10;
    private static final int E = 11;
    private static final int F = 12;
    private static final int G = 13;
    private static final int H = 14;
    private static final int[] I = new int[]{R.layout.longan_camera_newfn_base_listview, R.layout.longan_camera_newfn_base_listview, R.layout.longan_camera_newfn_base_listview, R.layout.longan_shotcuts_style_view, R.layout.longan_camera_newfn_wbcustom_view, R.layout.longan_camera_newfn_base_listview, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_mic_gain_view, R.layout.longan_camera_newfn_center_point, R.layout.longan_camera_newfn_line_view, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_shotcuts_litsitem_switch, R.layout.longan_shotcuts_follow_focus_view, R.layout.longan_camera_newfn_base_btn};
    private static final int J = 1;
    private static final String s = "LonganCameraSetListView";
    private static final int t = 0;
    private static final int u = 1;
    private static final int v = 2;
    private static final int w = 3;
    private static final int x = 4;
    private static final int y = 5;
    private static final int z = 6;
    protected a o = new a(this);
    protected DJICameraDataManagerCompat p = DJICameraDataManagerCompat.getInstance();
    protected CameraType q = CameraType.OTHER;

    private static class a extends Handler {
        private final WeakReference<LonganCameraSetListView> a;

        public a(LonganCameraSetListView longanCameraSetListView) {
            this.a = new WeakReference(longanCameraSetListView);
        }

        public void handleMessage(Message message) {
            DJIBaseListView dJIBaseListView = (DJIBaseListView) this.a.get();
            if (message.what == 1) {
                dJIBaseListView.e.notifyDataSetChanged();
            }
        }
    }

    public LonganCameraSetListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.a = b.a;
        this.b = I;
    }

    protected void f() {
        DJILogHelper.getInstance().LOGD(s, "mPhotoAdapter:" + (this.e == null), false, true);
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        onEventMainThread(dji.device.camera.view.focus.a.b.a.hideUI);
        c.a().a(this);
        j();
        i();
    }

    protected void h() {
        this.k = new OnItemClickListener(this) {
            final /* synthetic */ LonganCameraSetListView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                boolean z = true;
                dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.a.d.get(i);
                ViewParent parent = this.a.getParent();
                if (!(parent instanceof DJIStageViewCompat)) {
                    return;
                }
                boolean isHistogramEnable;
                if (6 == i) {
                    isHistogramEnable = DataCameraGetPushStateInfo.getInstance().isHistogramEnable();
                    DataCameraSetPushChart instance = DataCameraSetPushChart.getInstance();
                    if (isHistogramEnable) {
                        z = false;
                    }
                    instance.a(z).start(null);
                } else if (14 == i) {
                    DJICameraDataManagerCompat.getInstance().redemarcateCamera();
                } else if (11 == i) {
                    isHistogramEnable = DataCameraGetPushShotInfo.getInstance().isDigitalFocusAEnable();
                    r1 = DataCameraGetPushShotInfo.getInstance().isDigitalFocusMEnable();
                    r2 = DataCameraSetFocusAid.getInstance();
                    if (r1) {
                        z = false;
                    }
                    r2.a(isHistogramEnable, z).start(new d(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void onSuccess(Object obj) {
                            boolean z;
                            DJILogHelper instance = DJILogHelper.getInstance();
                            String str = "";
                            StringBuilder append = new StringBuilder().append("@@@MFFFFF:");
                            if (r1) {
                                z = false;
                            } else {
                                z = true;
                            }
                            instance.LOGD(str, append.append(z).toString(), false, true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            boolean z;
                            DJILogHelper instance = DJILogHelper.getInstance();
                            String str = "";
                            StringBuilder append = new StringBuilder().append("@@@MFFFFF:");
                            if (r1) {
                                z = false;
                            } else {
                                z = true;
                            }
                            instance.LOGD(str, append.append(z).append("ccode:").append(aVar).toString(), false, true);
                        }
                    });
                } else if (12 == i) {
                    isHistogramEnable = DataCameraGetPushShotInfo.getInstance().isDigitalFocusAEnable();
                    r1 = DataCameraGetPushShotInfo.getInstance().isDigitalFocusMEnable();
                    r2 = DataCameraSetFocusAid.getInstance();
                    if (isHistogramEnable) {
                        z = false;
                    }
                    r2.a(z, r1).start(new d(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void onSuccess(Object obj) {
                            boolean z;
                            DJILogHelper instance = DJILogHelper.getInstance();
                            String str = "";
                            StringBuilder append = new StringBuilder().append("@@@AFFFFF:");
                            if (isHistogramEnable) {
                                z = false;
                            } else {
                                z = true;
                            }
                            instance.LOGD(str, append.append(z).toString(), false, true);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            boolean z;
                            DJILogHelper instance = DJILogHelper.getInstance();
                            String str = "";
                            StringBuilder append = new StringBuilder().append("@@@AFFFFF:");
                            if (isHistogramEnable) {
                                z = false;
                            } else {
                                z = true;
                            }
                            instance.LOGD(str, append.append(z).append("ccode:").append(aVar).toString(), false, true);
                        }
                    });
                } else if (10 == i) {
                    c.a().e(m.CHANGE_DZOOM);
                } else {
                    dji.device.common.view.set.view.DJIStageViewCompat.a createStageView = ((DJIStageViewCompat) parent).createStageView(aVar.n, aVar.o, true, -1, -1);
                    if (i == 0) {
                        ((LonganSencondarySetListView) createStageView).updateData(0);
                    } else if (1 == i) {
                        ((LonganSencondarySetListView) createStageView).updateData(1);
                    } else if (2 == i) {
                        ((LonganSencondarySetListView) createStageView).updateData(2);
                    } else if (5 == i) {
                        ((LonganSencondarySetListView) createStageView).updateData(5);
                    }
                }
            }
        };
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = 1;
        if (LonganCameraShotcutsView.a) {
            int imageFormat = dataCameraGetPushShotParams.getImageFormat();
            if (((dji.device.common.view.set.b.a) this.d.get(1)).g != imageFormat) {
                ((dji.device.common.view.set.b.a) this.d.get(1)).g = imageFormat;
                dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) this.d.get(1);
                aVar.l = DJICameraDataManagerCompat.mPictureFormatImgRes[this.p.getPictureFormatPos(imageFormat)];
                imageFormat = 1;
            } else {
                imageFormat = 0;
            }
            int videoFormat = dataCameraGetPushShotParams.getVideoFormat();
            int videoFps = dataCameraGetPushShotParams.getVideoFps();
            int i2 = (videoFormat * 100) + videoFps;
            if (((dji.device.common.view.set.b.a) this.d.get(0)).g != i2) {
                ((dji.device.common.view.set.b.a) this.d.get(0)).g = i2;
                ((dji.device.common.view.set.b.a) this.d.get(0)).l = DJICameraDataManagerCompat.getInstance().getVideoFpsImgRes(videoFormat, videoFps);
                imageFormat = 1;
            }
            videoFormat = dataCameraGetPushShotParams.getWhiteBalance();
            if (((dji.device.common.view.set.b.a) this.d.get(4)).g != videoFormat) {
                ((dji.device.common.view.set.b.a) this.d.get(4)).g = videoFormat;
                imageFormat = this.p.getWhiteBalancePos(videoFormat);
                if (imageFormat < DJICameraDataManagerCompat.mWhiteBalanceImgRes.length) {
                    ((dji.device.common.view.set.b.a) this.d.get(4)).l = DJICameraDataManagerCompat.mWhiteBalanceImgRes[imageFormat];
                }
                imageFormat = 1;
            }
            videoFormat = dataCameraGetPushShotParams.getDigitalFilter();
            if (((dji.device.common.view.set.b.a) this.d.get(2)).g != videoFormat) {
                ((dji.device.common.view.set.b.a) this.d.get(2)).g = videoFormat;
                imageFormat = this.p.getDigitalFilterPos(videoFormat);
                ((dji.device.common.view.set.b.a) this.d.get(2)).l = 0;
                ((dji.device.common.view.set.b.a) this.d.get(2)).m = DJICameraDataManagerCompat.getInstance().getDigitalFilterArAll()[imageFormat];
                imageFormat = 1;
            }
            byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
            byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
            byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
            int i3 = (((sharpe * 100) + (contrast * 10)) + saturation) + 1000;
            if (((dji.device.common.view.set.b.a) this.d.get(3)).g != i3) {
                ((dji.device.common.view.set.b.a) this.d.get(3)).g = i3;
                byte[][] bArr = DJICameraDataManagerCompat.mPictureStyleValue;
                int i4 = 0;
                while (i4 < bArr.length) {
                    if (sharpe == bArr[i4][0] && contrast == bArr[i4][1] && saturation == bArr[i4][2]) {
                        imageFormat = i4;
                        break;
                    }
                    i4++;
                }
                imageFormat = 0;
                ((dji.device.common.view.set.b.a) this.d.get(3)).l = 0;
                ((dji.device.common.view.set.b.a) this.d.get(3)).m = getContext().getString(DJICameraDataManagerCompat.mStyleNameStr[imageFormat]);
                imageFormat = 1;
            }
            boolean isHistogramEnable = DataCameraGetPushStateInfo.getInstance().isHistogramEnable();
            if (((dji.device.common.view.set.b.a) this.d.get(6)).h != isHistogramEnable) {
                ((dji.device.common.view.set.b.a) this.d.get(6)).h = isHistogramEnable;
            } else {
                i = imageFormat;
            }
            if (i != 0) {
                this.e.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(DJIUIEventManagerLongan.d dVar) {
        i();
    }

    private void i() {
        DataOsdGetMicGain.getInstance().start(new d(this) {
            final /* synthetic */ LonganCameraSetListView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int micGain = DataOsdGetMicGain.getInstance().getMicGain();
                if (!((dji.device.common.view.set.b.a) this.a.d.get(7)).m.equals("" + micGain)) {
                    ((dji.device.common.view.set.b.a) this.a.d.get(7)).m = "" + micGain;
                    ((dji.device.common.view.set.b.a) this.a.d.get(2)).l = 0;
                    this.a.o.sendEmptyMessage(1);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(dji.pilot.set.a.a aVar) {
        if (g.a == aVar.a) {
            j();
        }
    }

    private void j() {
        int f = dji.pilot.set.a.f(getContext());
        if (((dji.device.common.view.set.b.a) this.d.get(5)).l != DJICameraDataManagerCompat.mShowGridImgRes[f]) {
            ((dji.device.common.view.set.b.a) this.d.get(5)).l = DJICameraDataManagerCompat.mShowGridImgRes[f];
            this.e.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.a.b.a aVar) {
        boolean[] zArr = new boolean[1];
        switch (aVar) {
            case showUI:
                a(13, true, zArr);
                break;
            case hideUI:
                a(13, false, zArr);
                break;
        }
        if (zArr[0]) {
            this.e.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (LonganCameraShotcutsView.a) {
            boolean[] zArr = new boolean[1];
            boolean isHistogramEnable = DataCameraGetPushStateInfo.getInstance().isHistogramEnable();
            if (((dji.device.common.view.set.b.a) this.d.get(6)).h != isHistogramEnable) {
                zArr[0] = true;
                ((dji.device.common.view.set.b.a) this.d.get(6)).h = isHistogramEnable;
            }
            CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
            if (this.q != cameraType) {
                this.q = cameraType;
                if (dji.logic.f.b.a(cameraType)) {
                    a(8, true, zArr);
                    a(9, true, zArr);
                    a(11, true, zArr);
                    a(12, true, zArr);
                    a(14, true, zArr);
                } else {
                    a(14, false, zArr);
                    a(11, false, zArr);
                    a(12, false, zArr);
                    a(9, false, zArr);
                    a(8, false, zArr);
                }
            }
            if (!dji.logic.f.b.e(this.q) || DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
                a(10, false, zArr);
            } else {
                a(10, true, zArr);
            }
            if (zArr[0]) {
                this.e.notifyDataSetChanged();
            }
        }
    }

    private dji.device.common.view.set.b.a a(int i, boolean z, boolean[] zArr) {
        dji.device.common.view.set.b.a a = a(i);
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) it.next();
            if (aVar.equals(a)) {
                if (aVar.i == z) {
                    return aVar;
                }
                aVar.i = z;
                zArr[0] = true;
                return aVar;
            }
        }
        return null;
    }

    private dji.device.common.view.set.b.a b(int i, boolean z, boolean[] zArr) {
        dji.device.common.view.set.b.a a = a(i);
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            dji.device.common.view.set.b.a aVar = (dji.device.common.view.set.b.a) it.next();
            if (aVar.equals(a)) {
                if (aVar.h == z) {
                    return aVar;
                }
                aVar.h = z;
                zArr[0] = true;
                return aVar;
            }
        }
        return null;
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (LonganCameraShotcutsView.a) {
            boolean[] zArr = new boolean[1];
            boolean isDigitalFocusMEnable = dataCameraGetPushShotInfo.isDigitalFocusMEnable();
            boolean isDigitalFocusAEnable = dataCameraGetPushShotInfo.isDigitalFocusAEnable();
            b(11, isDigitalFocusMEnable, zArr);
            b(12, isDigitalFocusAEnable, zArr);
            if (zArr[0]) {
                this.e.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case CHANGE_DZOOM:
                postDelayed(new Runnable(this) {
                    final /* synthetic */ LonganCameraSetListView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        boolean b = dji.pilot.set.a.b(this.a.getContext(), g.k);
                        if (((dji.device.common.view.set.b.a) this.a.d.get(10)).h != b) {
                            ((dji.device.common.view.set.b.a) this.a.d.get(10)).h = b;
                            this.a.e.notifyDataSetChanged();
                        }
                    }
                }, 100);
                return;
            case SHOW_SHOTCUTS_CAMERA_LY:
                onEventMainThread(DataCameraGetPushShotInfo.getInstance());
                onEventMainThread(DataCameraGetPushShotParams.getInstance());
                onEventMainThread(DataCameraGetPushStateInfo.getInstance());
                return;
            default:
                return;
        }
    }
}
