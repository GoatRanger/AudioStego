package dji.midware.sdr.log;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.os.StatFs;
import dji.log.DJILogHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DJISdrLogDataReciever {
    private static boolean DEBUG = true;
    private static final int LOG_CACHE_SIZE = 819200;
    private static String TAG = "DJISdrLogDataReciever";
    private static SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
    private static FileOutputStream fosLogFilePort1 = null;
    private static FileOutputStream fosLogFilePort2 = null;
    private static FileOutputStream fosLogFilePort3 = null;
    private static DJISdrLogDataReciever instance = null;
    private static final boolean isOpenSdrLog = false;
    private static boolean isRecieveLog = false;
    private static Context mContext = null;
    private static final String sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String sdrFileEnd = "-Port";
    private static final String sdrFilePrex = "SdrLog-";
    private static final String sdrLogDirName = (Environment.getExternalStorageDirectory() + "/DJI/SDR_LOG/");
    private static String sdr_port1_fileName;
    private static String sdr_port2_fileName;
    private static String sdr_port3_fileName;
    private int log1_cur_size = 0;
    private int log2_cur_size = 0;
    private int log3_cur_size = 0;
    private byte[] port1_log_cache = new byte[819200];
    private byte[] port2_log_cache = new byte[819200];
    private byte[] port3_log_cache = new byte[819200];

    public static DJISdrLogDataReciever getInstance(Context context) {
        if (instance == null) {
            instance = new DJISdrLogDataReciever();
            mContext = context.getApplicationContext();
            initLogFile();
        }
        return instance;
    }

    public static DJISdrLogDataReciever getInstance() {
        return instance;
    }

    public static void checkStorage() {
        String str = System.getenv("EXTERNAL_STORAGE");
        File file = new File(str);
        if (DEBUG) {
            DJILogHelper.getInstance().LOGD(TAG, "checkStorage extStore= " + str, false, true);
            DJILogHelper.getInstance().LOGD(TAG, "checkStorage f_exts= " + file.exists(), false, true);
        }
        str = System.getenv("SECONDARY_STORAGE");
        file = new File(str);
        if (DEBUG) {
            DJILogHelper.getInstance().LOGD(TAG, "checkStorage secStore= " + str, false, true);
            DJILogHelper.getInstance().LOGD(TAG, "checkStorage f_secs= " + file.exists(), false, true);
        }
    }

    private static long getAvailableStore(String str) {
        StatFs statFs = new StatFs(str);
        long availableBlocks = ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1024) / 1024;
        if (DEBUG) {
            DJILogHelper.getInstance().LOGD(TAG, "getAvailableStore size= " + availableBlocks, false, true);
        }
        return availableBlocks;
    }

    private static void initLogFile() {
    }

    public boolean getIsRecieveFlag() {
        return isRecieveLog;
    }

    public void createLogFiles() {
    }

    public void onRecvLogPort1(byte[] bArr, int i) {
        onAoaRecvLogPort1(bArr, 0, i);
    }

    public void onAoaRecvLogPort1(byte[] bArr, int i, int i2) {
    }

    public void onRecvLogPort2(byte[] bArr, int i) {
        onAoaRecvLogPort2(bArr, 0, i);
    }

    public void onAoaRecvLogPort2(byte[] bArr, int i, int i2) {
    }

    public void onRecvLogPort3(byte[] bArr, int i) {
        onAoaRecvLogPort3(bArr, 0, i);
    }

    public void onAoaRecvLogPort3(byte[] bArr, int i, int i2) {
    }

    public void onDestroy() {
        try {
            if (fosLogFilePort1 != null) {
                fosLogFilePort1.write(this.port1_log_cache, 0, this.log1_cur_size);
                this.log1_cur_size = 0;
                fosLogFilePort1.close();
                fosLogFilePort1 = null;
            }
            if (fosLogFilePort2 != null) {
                fosLogFilePort2.write(this.port2_log_cache, 0, this.log2_cur_size);
                this.log2_cur_size = 0;
                fosLogFilePort2.close();
                fosLogFilePort2 = null;
            }
            if (fosLogFilePort3 != null) {
                fosLogFilePort3.write(this.port3_log_cache, 0, this.log3_cur_size);
                this.log3_cur_size = 0;
                fosLogFilePort3.close();
                fosLogFilePort3 = null;
            }
            if (DEBUG) {
                MediaScannerConnection.scanFile(mContext, new String[]{sdrLogDirName + sdr_port1_fileName}, null, null);
                MediaScannerConnection.scanFile(mContext, new String[]{sdrLogDirName + sdr_port2_fileName}, null, null);
                MediaScannerConnection.scanFile(mContext, new String[]{sdrLogDirName + sdr_port3_fileName}, null, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanLogFile() {
        try {
            if (fosLogFilePort1 != null) {
                fosLogFilePort1.write(this.port1_log_cache, 0, this.log1_cur_size);
                this.log1_cur_size = 0;
                fosLogFilePort1.close();
                fosLogFilePort1 = null;
            }
            if (fosLogFilePort2 != null) {
                fosLogFilePort2.write(this.port2_log_cache, 0, this.log2_cur_size);
                this.log2_cur_size = 0;
                fosLogFilePort2.close();
                fosLogFilePort2 = null;
            }
            if (fosLogFilePort3 != null) {
                fosLogFilePort3.write(this.port3_log_cache, 0, this.log3_cur_size);
                this.log3_cur_size = 0;
                fosLogFilePort3.close();
                fosLogFilePort3 = null;
            }
        } catch (Exception e) {
        }
    }
}
