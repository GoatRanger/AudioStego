package dji.midware.media.d;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import dji.midware.media.e;
import java.io.IOException;
import java.nio.ByteBuffer;

public class b extends c implements d {
    private static String d = "AndroidNativeDemuxer";
    MediaExtractor a = new MediaExtractor();

    public b() {
        e.d(d, "create a AndroidNativeDemuxer");
    }

    public boolean a() {
        return this.a.advance();
    }

    public int b() {
        return this.a.getTrackCount();
    }

    public int c() {
        return this.a.getSampleFlags();
    }

    public long d() {
        return this.a.getSampleTime();
    }

    public MediaFormat a(int i) {
        return this.a.getTrackFormat(i);
    }

    public void a(String str) throws IOException {
        this.a.setDataSource(str);
    }

    public int a(ByteBuffer byteBuffer, int i) {
        return this.a.readSampleData(byteBuffer, i);
    }

    public void a(long j, int i) {
        this.a.seekTo(j, i);
    }

    public int e() {
        return this.a.getSampleTrackIndex();
    }

    public void b(int i) {
        this.a.selectTrack(i);
    }

    public void c(int i) {
        this.a.unselectTrack(i);
    }

    public void f() {
        this.a.release();
    }
}
