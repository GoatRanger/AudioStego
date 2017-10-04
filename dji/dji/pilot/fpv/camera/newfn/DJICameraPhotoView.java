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
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.camera.newfn.a.b;
import dji.pilot.fpv.camera.newfn.b.c;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIListView;
import java.util.ArrayList;

public class DJICameraPhotoView extends DJILinearLayout implements a {
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 5;
    private static final int[] m = new int[]{R.string.fpv_camera_photo_mode, R.string.fpv_camera_picturesize, R.string.fpv_camera_photoformat, R.string.fpv_camera_whitebalance, R.string.fpv_camera_picture_style, R.string.fpv_camera_filter};
    private static final int[] n = new int[]{R.layout.camera_newfn_base_expandview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_base_listview, R.layout.camera_newfn_wbcustom_view, R.layout.camera_newfn_style_view, R.layout.camera_newfn_base_listview};
    private static final int[] o = new int[]{R.string.fpv_camera_photo_mode, R.string.fpv_camera_picturesize, R.string.fpv_camera_photoformat, R.string.fpv_camera_whitebalance, R.string.fpv_camera_picture_style, R.string.fpv_camera_filter};
    protected DJIListView a = null;
    protected b b = null;
    protected final ArrayList<c> c = new ArrayList(m.length);
    protected OnItemClickListener d = null;
    protected dji.pilot.fpv.camera.more.a e = dji.pilot.fpv.camera.more.a.getInstance();
    protected boolean f = false;

    public DJICameraPhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void a() {
        int length = m.length;
        for (int i = 0; i < length; i++) {
            c cVar = new c();
            cVar.b = m[i];
            cVar.e = n[i];
            cVar.f = o[i];
            this.c.add(cVar);
        }
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (dji_pilot_fpv_camera_more_a_a == a$a.PHOTOTYPE_CHANGED_AUTO && b()) {
            this.b.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            boolean z;
            boolean b = b();
            int imageFormat = dataCameraGetPushTauParam.getImageFormat();
            if (((c) this.c.get(2)).a != imageFormat) {
                ((c) this.c.get(2)).a = imageFormat;
                imageFormat = dji.pilot.fpv.camera.more.a.a(this.e.L(), imageFormat, 0);
                ((c) this.c.get(2)).c = 0;
                ((c) this.c.get(2)).d = this.e.K()[imageFormat];
                z = true;
            } else {
                z = b;
            }
            if (z) {
                this.b.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dji.pilot.fpv.d.b.j(dataCameraGetPushStateInfo.getCameraType())) {
            boolean z;
            RecordType recordState = dataCameraGetPushStateInfo.getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                z = ((c) this.c.get(0)).h;
                ((c) this.c.get(0)).h = false;
                ((c) this.c.get(2)).h = false;
            } else {
                if (((c) this.c.get(0)).h) {
                    z = false;
                } else {
                    z = true;
                }
                ((c) this.c.get(0)).h = true;
                ((c) this.c.get(2)).h = true;
            }
            if (z) {
                this.b.notifyDataSetChanged();
                return;
            }
            return;
        }
        a(dataCameraGetPushStateInfo, false);
    }

    private void a(DataCameraGetPushStateInfo dataCameraGetPushStateInfo, boolean z) {
        boolean z2 = true;
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (!dji.pilot.fpv.d.b.j(cameraType) && !dji.pilot.fpv.d.b.k(cameraType)) {
            boolean beInTrackingMode = dataCameraGetPushStateInfo.beInTrackingMode();
            if (this.f != beInTrackingMode || z) {
                boolean z3;
                this.f = beInTrackingMode;
                boolean z4 = ((c) this.c.get(0)).g == beInTrackingMode;
                c cVar = (c) this.c.get(0);
                if (beInTrackingMode) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                cVar.g = z3;
                cVar = (c) this.c.get(1);
                if (beInTrackingMode) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                cVar.g = z3;
                cVar = (c) this.c.get(2);
                if (beInTrackingMode) {
                    z2 = false;
                }
                cVar.g = z2;
                if (z4) {
                    ((c) this.c.get(0)).a = Integer.MAX_VALUE;
                    this.b.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean b() {
        int a = this.e.l().a();
        int n = this.e.n();
        int i = (a * 100) + n;
        if (((c) this.c.get(0)).a == i) {
            return false;
        }
        ((c) this.c.get(0)).a = i;
        ((c) this.c.get(0)).c = this.e.a(a, n);
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType()) && a == TYPE.g.a()) {
            ((c) this.c.get(0)).d = String.valueOf(n);
            ((c) this.c.get(0)).c = R.drawable.advanced_more_photomode_notime;
            return true;
        }
        ((c) this.c.get(0)).d = null;
        return true;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = 1;
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int o;
            c cVar;
            boolean b = b();
            int imageFormat = dataCameraGetPushShotParams.getImageFormat();
            if (((c) this.c.get(2)).a != imageFormat) {
                ((c) this.c.get(2)).a = imageFormat;
                o = this.e.o(imageFormat);
                ((c) this.c.get(2)).d = null;
                ((c) this.c.get(2)).c = this.e.O()[o];
                b = true;
            }
            imageFormat = dataCameraGetPushShotParams.getImageRatio().value();
            if (((c) this.c.get(1)).a != imageFormat) {
                ((c) this.c.get(1)).a = imageFormat;
                cVar = (c) this.c.get(1);
                cVar.c = dji.pilot.fpv.camera.more.a.v_[this.e.q(imageFormat)];
                b = true;
            }
            imageFormat = dataCameraGetPushShotParams.getWhiteBalance();
            if (((c) this.c.get(3)).a != imageFormat) {
                ((c) this.c.get(3)).a = imageFormat;
                cVar = (c) this.c.get(3);
                cVar.c = dji.pilot.fpv.camera.more.a.o_[this.e.p(imageFormat)];
                b = true;
            }
            byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
            byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
            byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
            imageFormat = dji.pilot.fpv.camera.more.a.t_.length - 1;
            for (int i2 = 0; i2 < dji.pilot.fpv.camera.more.a.t_.length; i2++) {
                byte[] bArr = dji.pilot.fpv.camera.more.a.t_[i2];
                if (bArr[0] == contrast && bArr[1] == saturation && sharpe == bArr[2]) {
                    imageFormat = i2;
                    break;
                }
            }
            if (((c) this.c.get(4)).a != imageFormat) {
                ((c) this.c.get(4)).a = imageFormat;
                ((c) this.c.get(4)).c = dji.pilot.fpv.camera.more.a.s_[imageFormat];
                b = true;
            }
            imageFormat = dataCameraGetPushShotParams.getDigitalFilter();
            if (((c) this.c.get(5)).a != imageFormat) {
                ((c) this.c.get(5)).a = imageFormat;
                o = dji.pilot.fpv.camera.more.a.a(this.e.T(), imageFormat, 0);
                ((c) this.c.get(5)).c = 0;
                ((c) this.c.get(5)).d = this.e.S()[o];
            } else {
                boolean z = b;
            }
            if (i != 0) {
                this.b.notifyDataSetChanged();
            }
        }
    }

    public void dispatchOnStart() {
        boolean z;
        boolean z2 = true;
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            z = ((c) this.c.get(5)).g;
            ((c) this.c.get(5)).g = false;
            ((c) this.c.get(1)).g = false;
            ((c) this.c.get(3)).g = false;
            ((c) this.c.get(4)).g = false;
            RecordType recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                if (!(z || ((c) this.c.get(0)).h)) {
                    z2 = false;
                }
                ((c) this.c.get(0)).h = false;
                ((c) this.c.get(2)).h = false;
                z = z2;
            } else {
                if (z || !((c) this.c.get(0)).h) {
                    z = true;
                } else {
                    z = false;
                }
                ((c) this.c.get(0)).h = true;
                ((c) this.c.get(2)).h = true;
            }
        } else if (dji.pilot.fpv.d.b.k(null)) {
            z = !((c) this.c.get(5)).g;
            ((c) this.c.get(5)).g = true;
            ((c) this.c.get(3)).g = true;
            ((c) this.c.get(0)).g = true;
            ((c) this.c.get(1)).g = false;
            ((c) this.c.get(2)).g = false;
            ((c) this.c.get(4)).g = false;
            if (z || !((c) this.c.get(0)).h) {
                z = true;
            } else {
                z = false;
            }
            ((c) this.c.get(0)).h = true;
            ((c) this.c.get(2)).h = true;
        } else {
            z = !((c) this.c.get(5)).g;
            ((c) this.c.get(5)).g = true;
            ((c) this.c.get(1)).g = true;
            ((c) this.c.get(3)).g = true;
            ((c) this.c.get(4)).g = true;
            if (z || !((c) this.c.get(0)).h) {
                z = true;
            } else {
                z = false;
            }
            ((c) this.c.get(0)).h = true;
            ((c) this.c.get(2)).h = true;
        }
        if (z) {
            ((c) this.c.get(0)).a = Integer.MAX_VALUE;
            this.b.notifyDataSetChanged();
        }
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(DataCameraGetPushTauParam.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        a(DataCameraGetPushStateInfo.getInstance(), true);
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d(this);
    }

    private void c() {
        this.d = new OnItemClickListener(this) {
            final /* synthetic */ DJICameraPhotoView a;

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
                        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                            ((DJICameraBaseExpandView) createStageView).updateData(2);
                        } else {
                            ((DJICameraBaseExpandView) createStageView).updateData(0);
                        }
                    } else if (2 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(1);
                    } else if (1 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(0);
                    } else if (5 == indexOf) {
                        ((DJICameraBaseListView) createStageView).updateData(4);
                    }
                }
            }
        };
        this.b = new b(getContext(), this.c);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            c();
            this.a = (DJIListView) findViewById(R.id.oc);
            this.a.setAdapter(this.b);
            this.a.setOnItemClickListener(this.d);
        }
    }

    public View getView() {
        return this;
    }
}
