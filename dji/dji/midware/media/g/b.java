package dji.midware.media.g;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface b {
    int a();

    int a(MediaFormat mediaFormat);

    void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo, long j);

    void a(String str) throws IOException;

    void b();

    void c();

    void d();
}
