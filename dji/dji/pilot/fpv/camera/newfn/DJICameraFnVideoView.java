package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.pilot.R;
import dji.pilot.fpv.camera.newfn.a.b;
import dji.pilot.fpv.camera.newfn.b.c;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIListView;
import java.util.ArrayList;
import java.util.List;

public class DJICameraFnVideoView extends DJILinearLayout implements a {
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int j = 4;
    private static final int k = 5;
    private static final int l = 6;
    private static final int m = 7;
    private static final int n = 8;
    private static final int[] o = new int[]{R.string.fpv_camera_videoresolution, R.string.fpv_camera_videoformat, R.string.fpv_gensetting_videotyle_desc, R.string.fpv_camera_whitebalance, R.string.fpv_camera_picture_style, R.string.fpv_camera_filter, R.string.camera_gamma, R.string.tau_camera_videoformat, R.string.camera_encode_type};
    private static final int[] p = new int[]{R.layout.camera_newfn_base_expandview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_wbcustom_view, R.layout.camera_newfn_style_view, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_base_listview};
    private static final int[] q = new int[]{R.string.fpv_camera_videoresolution, R.string.fpv_camera_videoformat, R.string.fpv_gensetting_videotyle_desc, R.string.fpv_camera_whitebalance, R.string.fpv_camera_picture_style, R.string.fpv_camera_filter, R.string.camera_gamma, R.string.tau_camera_videoformat, R.string.camera_encode_type};
    protected DJIListView a = null;
    protected b b = null;
    protected final ArrayList<c> c = new ArrayList(o.length);
    protected OnItemClickListener d = null;
    protected dji.pilot.fpv.camera.more.a e = dji.pilot.fpv.camera.more.a.getInstance();

    public DJICameraFnVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void a() {
        int length = o.length;
        for (int i = 0; i < length; i++) {
            c cVar = new c();
            cVar.b = o[i];
            cVar.e = p[i];
            cVar.f = q[i];
            this.c.add(cVar);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        boolean z = false;
        RecordType recordState;
        boolean z2;
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                z2 = ((c) this.c.get(7)).h;
                ((c) this.c.get(7)).h = false;
                z = z2;
            } else {
                if (!((c) this.c.get(7)).h) {
                    z = true;
                }
                ((c) this.c.get(7)).h = true;
            }
        } else {
            recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                z2 = ((c) this.c.get(2)).h;
                ((c) this.c.get(2)).h = false;
                z = z2;
            } else {
                if (!((c) this.c.get(2)).h) {
                    z = true;
                }
                ((c) this.c.get(2)).h = true;
            }
        }
        if (z) {
            this.b.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int i;
            int videoFormat = (dataCameraGetPushTauParam.getVideoFormat() * 100) + dataCameraGetPushTauParam.getVideoFps();
            if (((c) this.c.get(7)).a != videoFormat) {
                ((c) this.c.get(7)).a = videoFormat;
                c cVar = (c) this.c.get(7);
                cVar.d = this.e.aB()[dji.pilot.fpv.camera.more.a.a(this.e.aC(), videoFormat, 0)];
                ((c) this.c.get(7)).c = 0;
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                this.b.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = 1;
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            c cVar;
            List list = this.c;
            int videoFormat = dataCameraGetPushShotParams.getVideoFormat();
            int videoFps = dataCameraGetPushShotParams.getVideoFps();
            int i2 = (videoFormat * 100) + videoFps;
            if (((c) list.get(0)).a != i2) {
                ((c) list.get(0)).a = i2;
                ((c) list.get(0)).c = dji.pilot.fpv.camera.more.a.getInstance().b(videoFormat, videoFps);
                videoFormat = 1;
            } else {
                videoFormat = 0;
            }
            videoFps = dataCameraGetPushShotParams.getVideoStoreFormat();
            if (((c) list.get(1)).a != videoFps) {
                ((c) list.get(1)).a = videoFps;
                cVar = (c) list.get(1);
                cVar.c = dji.pilot.fpv.camera.more.a.x_[this.e.v(videoFps)];
                videoFormat = 1;
            }
            videoFps = dataCameraGetPushShotParams.getVideoStandard();
            if (((c) list.get(2)).a != videoFps) {
                ((c) list.get(2)).a = videoFps;
                cVar = (c) list.get(2);
                cVar.c = dji.pilot.fpv.camera.more.a.y_[this.e.t(videoFps)];
                videoFormat = 1;
            }
            videoFps = dataCameraGetPushShotParams.getDigitalFilter();
            if (((c) list.get(5)).a != videoFps) {
                ((c) list.get(5)).a = videoFps;
                videoFormat = dji.pilot.fpv.camera.more.a.a(this.e.T(), videoFps, 0);
                ((c) list.get(5)).c = 0;
                ((c) list.get(5)).d = this.e.S()[videoFormat];
                videoFormat = 1;
            }
            videoFps = dataCameraGetPushShotParams.getWhiteBalance();
            if (((c) list.get(3)).a != videoFps) {
                ((c) list.get(3)).a = videoFps;
                cVar = (c) list.get(3);
                cVar.c = dji.pilot.fpv.camera.more.a.o_[this.e.p(videoFps)];
                videoFormat = 1;
            }
            byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
            byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
            byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
            videoFps = dji.pilot.fpv.camera.more.a.t_.length - 1;
            for (int i3 = 0; i3 < dji.pilot.fpv.camera.more.a.t_.length; i3++) {
                byte[] bArr = dji.pilot.fpv.camera.more.a.t_[i3];
                if (bArr[0] == contrast && bArr[1] == saturation && sharpe == bArr[2]) {
                    videoFps = i3;
                    break;
                }
            }
            if (((c) list.get(4)).a != videoFps) {
                ((c) list.get(4)).a = videoFps;
                ((c) list.get(4)).c = dji.pilot.fpv.camera.more.a.s_[videoFps];
                videoFormat = 1;
            }
            videoFps = dataCameraGetPushShotParams.getConstrastEhance();
            if (videoFps != ((c) list.get(6)).a) {
                videoFormat = dji.pilot.fpv.camera.more.a.a(this.e.an(), videoFps, 0);
                ((c) list.get(6)).c = 0;
                ((c) list.get(6)).d = this.e.am()[videoFormat];
                videoFormat = 1;
            }
            videoFps = dataCameraGetPushShotParams.getPrimaryVideoEncodeType().a();
            if (videoFps != ((c) list.get(8)).a) {
                videoFormat = dji.pilot.fpv.camera.more.a.a(this.e.ap(), videoFps, 0);
                ((c) list.get(8)).c = 0;
                ((c) list.get(8)).d = this.e.ao()[videoFormat];
            } else {
                i = videoFormat;
            }
            if (i != 0) {
                this.b.notifyDataSetChanged();
            }
        }
    }

    private void b() {
        this.d = new OnItemClickListener(this) {
            final /* synthetic */ DJICameraFnVideoView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                c a = this.a.b.a(i);
                int indexOf = this.a.c.indexOf(a);
                ViewParent parent = this.a.getParent();
                if (parent instanceof DJIStageView) {
                    a createStageView = ((DJIStageView) parent).createStageView(a.e, a.f, true);
                    if (indexOf == 0) {
                        ((DJICameraBaseExpandView) createStageView).updateData(1);
                    } else if (1 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(2);
                    } else if (2 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(3);
                    } else if (5 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(4);
                    } else if (7 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(104);
                    } else if (6 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(9);
                    } else if (8 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(10);
                    }
                }
            }
        };
        this.b = new b(getContext(), this.c);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
            this.a = (DJIListView) findViewById(R.id.ov);
            this.a.setAdapter(this.b);
            this.a.setOnItemClickListener(this.d);
        }
    }

    private boolean a(int i, boolean z) {
        c cVar = (c) this.c.get(i);
        boolean z2 = z != cVar.g;
        cVar.g = z;
        return z2;
    }

    public void dispatchOnStart() {
        boolean z = true;
        boolean z2 = false;
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        int a;
        RecordType recordState;
        if (dji.pilot.fpv.d.b.j(cameraType)) {
            a = a(8, false) | (((((((a(5, false) | a(0, false)) | a(3, false)) | a(4, false)) | a(1, false)) | a(2, false)) | a(6, false)) | a(7, true));
            recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                z = a | ((c) this.c.get(7)).h;
                ((c) this.c.get(7)).h = false;
                z2 = z;
            } else {
                if (!(a == 0 && ((c) this.c.get(7)).h)) {
                    z2 = true;
                }
                ((c) this.c.get(7)).h = true;
            }
        } else if (dji.pilot.fpv.d.b.k(cameraType)) {
            a = a(8, false) | (((((((a(5, true) | a(0, false)) | a(3, true)) | a(4, false)) | a(1, true)) | a(2, true)) | a(6, false)) | a(7, false));
            recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                z = a | ((c) this.c.get(7)).h;
                ((c) this.c.get(7)).h = false;
                z2 = z;
            } else {
                if (!(a == 0 && ((c) this.c.get(7)).h)) {
                    z2 = true;
                }
                ((c) this.c.get(7)).h = true;
            }
        } else {
            int a2 = (a(8, CameraType.DJICameraTypeFC6310 == cameraType) | a(6, false)) | ((((((a(5, true) | a(0, true)) | a(3, true)) | a(4, true)) | a(1, true)) | a(2, true)) | a(7, false));
            RecordType recordState2 = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState2 == RecordType.START || recordState2 == RecordType.STARTING) {
                if (a2 == 0 && !((c) this.c.get(2)).h) {
                    z = false;
                }
                ((c) this.c.get(2)).h = false;
                z2 = z;
            } else {
                if (!(a2 == 0 && ((c) this.c.get(2)).h)) {
                    z2 = true;
                }
                ((c) this.c.get(2)).h = true;
            }
        }
        if (z2) {
            this.b.notifyDataSetChanged();
        }
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(DataCameraGetPushTauParam.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d(this);
    }

    public View getView() {
        return this;
    }
}
