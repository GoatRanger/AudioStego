package dji.pilot.fpv.topbar;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJIFpvTopLitchiView extends DJIFpvTopBaseView {
    protected static final int cN = 1;
    protected DJITextView cI = null;
    protected String[] cJ = null;
    protected int cK = -1;
    protected int cL = -1;
    CameraType cM = CameraType.OTHER;
    private a dJ = new a(this);

    protected static final class a extends Handler {
        private final WeakReference<DJIFpvTopLitchiView> a;

        public a(DJIFpvTopLitchiView dJIFpvTopLitchiView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvTopLitchiView);
        }

        public void handleMessage(Message message) {
            DJIFpvTopLitchiView dJIFpvTopLitchiView = (DJIFpvTopLitchiView) this.a.get();
            if (dJIFpvTopLitchiView != null) {
                switch (message.what) {
                    case 1:
                        dJIFpvTopLitchiView.U();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFpvTopLitchiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.cI = (DJITextView) findViewById(R.id.ad6);
            this.cJ = getContext().getResources().getStringArray(R.array.c3);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        super.onEventBackgroundThread(dataCameraGetPushShotParams);
        if (!this.dJ.hasMessages(1)) {
            this.dJ.sendEmptyMessageDelayed(1, 500);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        super.onEventBackgroundThread(dataCameraGetPushStateInfo);
        CameraType b = i.getInstance().b();
        if (b != this.cM) {
            this.cM = b;
            if (b.k(b)) {
                this.cI.show();
                U();
                return;
            }
            this.cI.go();
        }
    }

    protected void U() {
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            int whiteBalance = this.ch.getWhiteBalance();
            int colorTemp = this.ch.getColorTemp();
            if (whiteBalance == 6) {
                if (colorTemp != this.cL) {
                    this.cL = colorTemp;
                    this.cI.setText(a(this.aL.getString(R.string.fpv_wb, new Object[]{String.valueOf(String.valueOf(colorTemp * 100) + "K")}), 3, false));
                }
            } else if (whiteBalance != this.cK) {
                this.cK = whiteBalance;
                Object obj = this.cJ[whiteBalance];
                this.cI.setText(a(this.aL.getString(R.string.fpv_wb, new Object[]{obj}), 3, false));
            }
        }
    }
}
