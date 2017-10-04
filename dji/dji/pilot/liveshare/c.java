package dji.pilot.liveshare;

import android.content.Context;
import android.media.MediaCodec.BufferInfo;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.media.c$b;
import dji.midware.media.k.b.e;
import dji.midware.media.k.b.e$a;
import dji.midware.natives.FPVController;
import dji.phone.j.d;
import dji.pilot.f.a.a;
import java.nio.ByteBuffer;

public class c implements c$b, e$a {
    public static String TAG = "VideoForStreaming";
    private Context context;
    private int isIFrame;
    private boolean isStarted;

    public void setContext(Context context) {
        this.context = context;
    }

    public static c getInstance() {
        return a.access$000();
    }

    public void onEncodeData(byte[] bArr, int i, int i2, boolean z) {
        DJILogHelper.getInstance().LOGD(TAG, "onEncodeData: length: " + bArr.length + ", width: " + i + ", height: " + i2 + ", size: " + (i * i2) + ", is key frame: " + z);
        this.isIFrame = z ? 1 : 0;
        FPVController.native_putVideoStreamData(bArr, bArr.length, i, i2, this.isIFrame);
    }

    protected void onStartStream() {
        Log.d(TAG, "onStartStream: url: " + a.F);
        d.getInstance().b(this.context, this);
        if (!dji.phone.c.a.b()) {
            e.getInstance().a(this);
        }
        this.isStarted = true;
    }

    protected void onEndStream() {
        d.getInstance().b();
        e.getInstance().b(this);
        this.isStarted = false;
        DJILogHelper.getInstance().LOGI(TAG, "onEndStream() completion");
    }

    public void onFrameInput(ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, int i2, int i3) {
        byteBuffer.position(bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        DJILogHelper.getInstance().LOGI(TAG, "frame size " + bufferInfo.size + "frame height " + i3 + "frame width " + i2);
        byte[] bArr = new byte[bufferInfo.size];
        byteBuffer.get(bArr, bufferInfo.offset, bufferInfo.size);
        if ((bufferInfo.flags & 1) > 0) {
            this.isIFrame = 1;
        } else {
            this.isIFrame = 0;
        }
        FPVController.native_putVideoStreamData(bArr, bArr.length, i2, i3, this.isIFrame);
        DJILogHelper.getInstance().LOGI(TAG, "write a frame. pts=" + bufferInfo.presentationTimeUs);
    }
}
