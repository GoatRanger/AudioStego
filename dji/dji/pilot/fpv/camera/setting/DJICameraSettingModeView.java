package dji.pilot.fpv.camera.setting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.d;
import dji.pilot.fpv.d.e;
import dji.publics.DJIUI.DJILinearLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DJICameraSettingModeView extends DJILinearLayout implements OnClickListener, s {
    protected static final int[] a = new int[]{R.id.jo, R.id.jp, R.id.jq, R.id.jr, R.id.js};
    protected static final ExposureMode[] b = new ExposureMode[]{ExposureMode.b, ExposureMode.d, ExposureMode.c, ExposureMode.e, ExposureMode.h};
    protected final View[] c = new View[a.length];
    protected Context d = null;
    protected ExposureMode e = ExposureMode.i;
    protected final ArrayList<ExposureMode> f = new ArrayList(b.length);
    protected CameraType g = CameraType.OTHER;
    protected MODE h = MODE.OTHER;
    protected int i = 0;

    public DJICameraSettingModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        if (!isInEditMode()) {
            this.i = context.getResources().getDimensionPixelSize(R.dimen.ei);
        }
    }

    private void a(CameraType cameraType) {
        Object obj = null;
        this.g = cameraType;
        this.f.clear();
        for (Object add : b) {
            this.f.add(add);
        }
        if (b.b(cameraType) && DataCameraGetPushShotInfo.getInstance().getSupportType() >= 2 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            obj = 1;
        }
        boolean h = b.h(cameraType);
        boolean a = b.a(cameraType);
        if (obj == null) {
            this.f.remove(ExposureMode.h);
        }
        if (!h) {
            this.f.remove(ExposureMode.d);
        }
        if (!a) {
            this.f.remove(ExposureMode.c);
        }
        a(this.f);
    }

    public void handleCameraStateChanged(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (cameraType != this.g || mode != this.h) {
            this.h = mode;
            a(cameraType);
        }
    }

    public void setSelectedMode(ExposureMode exposureMode) {
        if (this.e != exposureMode) {
            int length = b.length;
            for (int i = 0; i < length; i++) {
                if (b[i] == this.e) {
                    this.c[i].setSelected(false);
                } else if (b[i] == exposureMode) {
                    this.c[i].setSelected(true);
                }
            }
            this.e = exposureMode;
            Map hashMap = new HashMap();
            hashMap.put(d.dH, "" + this.e.a());
            e.a(s.dk, hashMap);
        }
    }

    private void a(List<ExposureMode> list) {
        int size = list.size();
        int i = this.i;
        if (size == 2) {
            i = (int) (((float) this.i) * 1.15f);
        }
        if (size == 5) {
            i = (int) (((float) this.i) * 0.8f);
        }
        int length = b.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3;
            for (i3 = 0; i3 < size; i3++) {
                if (list.get(i3) == b[i2]) {
                    i3 = 1;
                    break;
                }
            }
            i3 = 0;
            if (i3 != 0) {
                LayoutParams layoutParams = this.c[i2].getLayoutParams();
                layoutParams.width = i;
                this.c[i2].setLayoutParams(layoutParams);
                this.c[i2].setVisibility(0);
            } else {
                this.c[i2].setVisibility(8);
            }
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            int length = a.length;
            for (int i = 0; i < length; i++) {
                this.c[i] = findViewById(a[i]);
                this.c[i].setOnClickListener(this);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        int i = 0;
        int length = a.length;
        while (i < length) {
            if (a[i] != id) {
                i++;
            } else if (this.e != b[i]) {
                a.a("Set Before[" + this.e + "]Now[" + b[i] + dji.pilot.usercenter.protocol.d.H);
                new DataCameraSetExposureMode().a(b[i].a()).start(null);
                return;
            } else {
                return;
            }
        }
    }
}
