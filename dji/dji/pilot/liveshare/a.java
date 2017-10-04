package dji.pilot.liveshare;

import android.media.AudioRecord;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import dji.midware.natives.FPVController;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class a {
    private static final String AudioName = "/sdcard/rawpcm.raw";
    private static final String NewAudioName = "/sdcard/new.wav";
    private volatile boolean cancel = false;
    private int dataSize;
    FileOutputStream fos = null;
    private a frameCallback;
    private int frequency;
    private Thread thread;
    private volatile double volume;
    private Thread volumeThread;

    public interface a {
        void handleFrame(short[] sArr, int i);
    }

    private static final class b {
        private static final a mInstance = new a();

        private b() {
        }
    }

    public static a getInstance() {
        return b.mInstance;
    }

    public void setFrameCallback(a aVar) {
        this.frameCallback = aVar;
    }

    public void start(int i) {
        Log.d("", "DJIAudioRecorder start");
        this.frequency = i;
        this.cancel = false;
        this.thread = new Thread(new Runnable() {
            public void run() {
                a.this.recordThread();
            }
        });
        this.thread.start();
        this.volumeThread = new Thread(new Runnable() {
            public void run() {
                dji.pilot.f.a.a aVar = new dji.pilot.f.a.a(258);
                Log.d("", "run: volumeThread start");
                while (!a.this.cancel) {
                    try {
                        Log.d("", "volumeThread: event.volume = " + a.this.volume);
                        aVar.K = a.this.volume;
                        c.a().e(aVar);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.volumeThread.start();
    }

    public void recordThread() {
        int minBufferSize = AudioRecord.getMinBufferSize(this.frequency, 16, 2);
        AudioRecord audioRecord = new AudioRecord(5, this.frequency, 16, 2, minBufferSize);
        audioRecord.startRecording();
        int i = minBufferSize / 2;
        this.dataSize = i;
        short[] sArr = new short[i];
        try {
            File file = new File(AudioName);
            if (file.exists()) {
                file.delete();
            }
            this.fos = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!this.cancel) {
            int read = audioRecord.read(sArr, 0, i);
            if (read > 0) {
                FPVController.native_putAudioDataLiveStream(sArr, read);
                long j = 0;
                for (int i2 = 0; i2 < sArr.length; i2++) {
                    j += (long) (sArr[i2] * sArr[i2]);
                }
                this.volume = Math.log10(((double) j) / ((double) read)) * 10.0d;
            } else if (read < 0) {
            }
            try {
                Thread.sleep(10);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        audioRecord.stop();
    }

    public void stop() {
        this.cancel = true;
        if (this.thread != null) {
            try {
                this.thread.join();
            } catch (InterruptedException e) {
            }
            this.thread = null;
        }
    }

    public byte[] ShortArray2ByteArray(short[] sArr, int i) {
        byte[] bArr = new byte[(i * 2)];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            short s = (short) (sArr[i2] >> 2);
            bArr[i2] = (byte) (s & 255);
            bArr[i2 + 1] = (byte) ((s & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        }
        return bArr;
    }

    private void copyWaveFile(String str, String str2) {
        long j = 0 + 44;
        long j2 = (long) 88200;
        byte[] bArr = new byte[(this.dataSize * 2)];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream.getChannel().size();
            WriteWaveFileHeader(fileOutputStream, size, 44 + size, 44100, 1, j2);
            while (fileInputStream.read(bArr) != -1) {
                fileOutputStream.write(bArr);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void WriteWaveFileHeader(FileOutputStream fileOutputStream, long j, long j2, long j3, int i, long j4) throws IOException {
        fileOutputStream.write(new byte[]{(byte) 82, (byte) 73, (byte) 70, (byte) 70, (byte) ((int) (255 & j2)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255)), (byte) 87, (byte) 65, (byte) 86, (byte) 69, (byte) 102, (byte) 109, (byte) 116, (byte) 32, (byte) 16, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) i, (byte) 0, (byte) ((int) (255 & j3)), (byte) ((int) ((j3 >> 8) & 255)), (byte) ((int) ((j3 >> 16) & 255)), (byte) ((int) ((j3 >> 24) & 255)), (byte) ((int) (255 & j4)), (byte) ((int) ((j4 >> 8) & 255)), (byte) ((int) ((j4 >> 16) & 255)), (byte) ((int) ((j4 >> 24) & 255)), (byte) 4, (byte) 0, (byte) 16, (byte) 0, (byte) 100, (byte) 97, (byte) 116, (byte) 97, (byte) ((int) (255 & j)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))}, 0, 44);
    }
}
