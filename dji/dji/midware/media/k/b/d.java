package dji.midware.media.k.b;

import android.util.Log;
import dji.midware.media.e;
import dji.midware.natives.FPVController;
import dji.midware.natives.FPVController.TranscodeResult;
import java.nio.ByteBuffer;

public class d {
    ByteBuffer a = ByteBuffer.allocateDirect(dji.midware.media.d.q);
    private boolean b = false;

    public synchronized void a(byte[] bArr, int i, int i2, int i3) {
        if (this.b) {
            a();
        }
        this.b = true;
        try {
            this.a.clear();
            this.a.put(bArr);
            e.a("init encoder/IFrameMaker with color=" + i + " width=" + i2 + " height=" + i3);
            Log.i(e.c, "transcode_x264 init result: " + TranscodeResult.find(FPVController.native_transcodeX264Init(this.a, bArr.length, i, i2, i3)).toString());
            Log.i(e.c, "transcode_x264 start result: " + TranscodeResult.find(FPVController.native_transcodeX264Start()).toString());
        } catch (Exception e) {
            Log.e(e.c, e.b(e));
        } catch (Error e2) {
            Log.e(e.c, e2.getMessage());
        }
    }

    public synchronized void a() {
        if (this.b) {
            this.b = false;
            try {
                Log.i(e.c, "transcode_x264 stop result: " + TranscodeResult.find(FPVController.native_transcodeX264Stop()).toString());
                Log.i(e.c, "transcode_x264 deinit result: " + TranscodeResult.find(FPVController.native_transcodeX264Deinit()).toString());
            } catch (Exception e) {
                Log.e(e.c, e.b(e));
            } catch (Error e2) {
                Log.e(e.c, e2.getMessage());
            }
        }
    }

    public synchronized void a(b bVar) {
        if (this.b) {
            long currentTimeMillis = System.currentTimeMillis();
            Log.i(e.c, "Before converting IDR index=" + bVar.c() + " data size=" + bVar.a() + " time=" + currentTimeMillis);
            try {
                this.a.clear();
                int native_transcodeX264Encode = FPVController.native_transcodeX264Encode(bVar.b().c(), bVar.a(), this.a);
                bVar.b().e();
                if (native_transcodeX264Encode < 0) {
                    Log.i(e.c, "transcode_x264 convert result: " + TranscodeResult.find(native_transcodeX264Encode).toString());
                } else {
                    Log.i(e.c, "transcode_x264 convert result: SUCCESS. re=" + native_transcodeX264Encode);
                    this.a.position(0);
                    this.a.limit(native_transcodeX264Encode);
                    bVar.b().a(this.a);
                }
            } catch (Exception e) {
                Log.e(e.c, e.b(e));
            } catch (Error e2) {
                Log.e(e.c, e2.getMessage());
            }
            Log.i(e.c, "after converting IDR index=" + bVar.c() + " data size=" + bVar.a() + " execution duration (ms)=" + (System.currentTimeMillis() - currentTimeMillis));
        } else {
            Log.e(e.c, "can't convert YUF to I-frame because IFrameMaker hasn't been initialized. ");
        }
    }
}
