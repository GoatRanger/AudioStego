package dji.midware.media.k.b;

import android.media.MediaCodec.BufferInfo;
import java.nio.ByteBuffer;

public interface e$a {
    void onFrameInput(ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, int i2, int i3);
}
