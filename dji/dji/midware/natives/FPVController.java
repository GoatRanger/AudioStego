package dji.midware.natives;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class FPVController {

    public enum ColorPlanar {
        YUV420P(0),
        NV12(1);
        
        private int value;

        private ColorPlanar(int i) {
            this.value = i;
        }

        int getValue() {
            return this.value;
        }
    }

    public enum TranscodeResult {
        SUCCESS(0),
        ERR_INVALID_PARAM(-1),
        ERR_NODATA(-2),
        OTHER(-100);
        
        private int value;

        private TranscodeResult(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static TranscodeResult find(int i) {
            TranscodeResult transcodeResult = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return transcodeResult;
        }
    }

    public static native int jni_audio_filters_frame_in(long j, int i, ByteBuffer byteBuffer, int i2, long j2);

    public static native int jni_audio_filters_frame_out(long j, ByteBuffer byteBuffer, ByteBuffer byteBuffer2);

    public static native long jni_audio_filters_init(String str, int i, int[] iArr, int[] iArr2, int[] iArr3, int i2, int i3, int i4);

    public static native int jni_audio_filters_release(long j);

    public static native HashMap<String, String> jni_demuxer_getMetadata(String str);

    public static native int jni_demuxer_getTrackCount(long j);

    public static native int jni_demuxer_getTrackFormat(long j, int i, ByteBuffer byteBuffer);

    public static native long jni_demuxer_init(String str);

    public static native boolean jni_demuxer_readSample(long j, Object obj, Object obj2, boolean z);

    public static native void jni_demuxer_release(long j);

    public static native boolean jni_demuxer_seekTo(long j, int i, long j2, boolean z);

    public static native int jni_image_convert_I420ToRGB565(Object obj, int i, int i2, int i3, Object obj2, int i4, int i5);

    public static native int jni_image_convert_NV21ToRGB565(Object obj, int i, int i2, Object obj2, int i3, int i4);

    public static native int native_clear();

    public static native long native_getDJIAVPaserHeaderMagic();

    public static native boolean native_getIsLiveStreamAudioMute();

    public static native int native_getLiveStreamAudioBitRate();

    public static native int native_getLiveStreamVideoBitRate();

    public static native int native_getLiveStreamVideoBufSize();

    public static native float native_getLiveStreamVideoFps();

    public static native int native_getQueueSize();

    public static native short[] native_getStreamParams();

    public static native int native_h264ParseSliceHeader(byte[] bArr, int i, int[] iArr, int[] iArr2, int[] iArr3, int i2);

    public static native int native_init(Object obj);

    public static native int native_initStreaming(String str, int i);

    public static native boolean native_isStarted();

    public static native int native_mp4MuxerAddAudioTrack(int i, int i2, int i3, int i4, long j, byte[] bArr, int i5);

    public static native int native_mp4MuxerAddVideoTrack(int i, int i2, int i3, byte[] bArr, int i4, long j);

    public static native int native_mp4MuxerInit(int i);

    public static native void native_mp4MuxerSetIsRotated(int i);

    public static native int native_mp4MuxerStart(String str);

    public static native int native_mp4MuxerStop();

    public static native int native_mp4MuxerWrite(int i, Object obj, int i2, int i3, long j, long j2);

    public static native int native_pauseParseThread(boolean z);

    public static native int native_pauseRecvThread(boolean z);

    public static native int native_putAudioData(short[] sArr, int i);

    public static native int native_putAudioDataLiveStream(short[] sArr, int i);

    public static native int native_putVideoStreamData(byte[] bArr, int i, int i2, int i3, int i4);

    public static native int native_reqCtrlInfo(byte[] bArr);

    public static native int native_sendCtrlInfo(byte[] bArr, int i, int i2);

    public static native int native_setCallObject(Object obj);

    public static native int native_setDataMode(boolean z);

    public static native int native_setDecodeMode(boolean z);

    public static native int native_setDecoderType(int i);

    public static native int native_setFrameRate(int i);

    public static native int native_setIsFixRate(boolean z);

    public static native int native_setIsLiveStreamAudioMute(boolean z);

    public static native int native_setIsNeedPacked(boolean z);

    public static native int native_setIsNeedRawData(boolean z);

    public static native int native_setIsNewRate(boolean z);

    public static native int native_setMSCChannel(int i, int i2, int i3);

    public static native int native_setOnStreamCB(Object obj, String str);

    public static native int native_setRGBBuffer(byte[] bArr, int i);

    public static native int native_setVideoDataRecver(Object obj);

    public static native int native_setVideoPackObject(Object obj);

    public static native int native_startParseThread();

    public static native int native_startRecvThread();

    public static native int native_startStream(boolean z);

    public static native int native_stopParseThread();

    public static native int native_stopRecvThread();

    public static native int native_stopStream();

    public static native int native_transSerialData(byte[] bArr, int i, int i2);

    public static native int native_transcodeX264Deinit();

    public static native int native_transcodeX264Encode(Object obj, int i, Object obj2);

    public static native int native_transcodeX264Init(Object obj, int i, int i2, int i3, int i4);

    public static native int native_transcodeX264Start();

    public static native int native_transcodeX264Stop();

    public static native int native_transcodeYUVConvert(Object obj, Object obj2, int i, int i2, int i3);

    public static native int native_transferAudioData(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public static native int native_transferVideoData(byte[] bArr, int i, byte[] bArr2, int i2);

    public static native int native_transferVideoDataDirect(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public static native int native_unInit();

    public static native int native_unInitStreaming();

    static {
        try {
            Log.d("FPVController", "try to load libstlport_shared.so");
            System.loadLibrary("stlport_shared");
            Log.d("FPVController", "try to load libdjivideo.so");
            System.loadLibrary("djivideo");
        } catch (UnsatisfiedLinkError e) {
            Log.e("FPVController", "Couldn't load lib");
        }
    }

    public static void loadLibrary() {
    }

    public static synchronized int native_transferVideoData(byte[] bArr, int i) {
        int native_transferVideoData;
        synchronized (FPVController.class) {
            native_transferVideoData = native_transferVideoData(bArr, i, null, 0);
        }
        return native_transferVideoData;
    }

    public static synchronized int native_transferVideoData(byte[] bArr, int i, int i2) {
        int native_transferVideoData;
        synchronized (FPVController.class) {
            native_transferVideoData = native_transferVideoData(bArr, i, null, i2);
        }
        return native_transferVideoData;
    }

    public static int native_transferVideoDataDirect(byte[] bArr, int i, byte[] bArr2, int i2) {
        return native_transferVideoDataDirect(bArr, 0, i, bArr2, i2);
    }

    public static int native_transferAudioData(byte[] bArr, int i, byte[] bArr2, int i2) {
        return native_transferAudioData(bArr, 0, i, bArr2, i2);
    }
}
