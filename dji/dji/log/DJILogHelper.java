package dji.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.dji.frame.c.b;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import com.here.odnp.debug.DebugFile;
import dji.midware.data.config.P3.DeviceType;
import dji.thirdparty.d.e;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class DJILogHelper {
    protected static boolean DEBUGABLE = false;
    private static DJILogHelper INSTANCE;
    protected static boolean OPEN = false;
    private static String[] filterTag = new String[0];
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal();
    private Date date = new Date();
    private String dirName;
    private SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    @SuppressLint({"SimpleDateFormat"})
    private SimpleDateFormat fromatDataForItem = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private SimpleDateFormat logFormatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private String nfzDirName;
    private String noVideoLogDirName;

    private DJILogHelper() {
    }

    public static synchronized DJILogHelper getInstance() {
        DJILogHelper dJILogHelper;
        synchronized (DJILogHelper.class) {
            if (INSTANCE == null) {
                INSTANCE = new DJILogHelper();
                e.a("DJI_GO").a(3).b(1);
            }
            dJILogHelper = INSTANCE;
        }
        return dJILogHelper;
    }

    public void autoHandle() {
        if (OPEN && LogHelper.getIntance() != null) {
            LogHelper.getIntance().autoHandle();
        }
    }

    public void closeLog() {
        if (LogHelper.getIntance() != null) {
            LogHelper.getIntance().closeLog();
        }
    }

    public void init(Context context) {
        DEBUGABLE |= b.c(context);
        boolean z = OPEN && DEBUGABLE;
        OPEN = z;
        this.dirName = d.a(context, "/LOG/CACHE/");
        this.nfzDirName = d.a(context, "/LOG/NFZ/");
        this.noVideoLogDirName = d.a(context, "/LOG/NO_VIDEO/");
        if (OPEN) {
            LogHelper.createIntance(context);
        }
    }

    public String getLogParentDir() {
        return this.dirName;
    }

    public String getLogName() {
        return "log-" + getDateFormat().format(this.date) + ".txt";
    }

    public void LOGI(String str, String str2) {
        if (DEBUGABLE) {
            Log.i(str, str2);
        }
    }

    public void LOGD(String str, String str2) {
        if (DEBUGABLE) {
            Log.d(str, str2);
        }
    }

    public void LOGE(String str, String str2) {
        if (DEBUGABLE) {
            Log.e(str, str2);
        }
    }

    public void LOGI(String str, String str2, boolean z, boolean z2) {
        if (!checkTagFilter(str)) {
            if (DEBUGABLE) {
                Log.i(str, str2);
            }
            if (OPEN) {
                if (z) {
                    saveLogInfo2File(str + " i: " + str2);
                }
                if (z2) {
                    LogHelper.getIntance().updateLog(DeviceType.APP, str2);
                }
            }
        }
    }

    public void LOGD(String str, String str2, boolean z, boolean z2) {
        if (!checkTagFilter(str)) {
            if (DEBUGABLE) {
                Log.d(str, str2);
            }
            if (OPEN) {
                if (z) {
                    saveLogInfo2File(str + " d: " + str2);
                }
                if (z2) {
                    LogHelper.getIntance().updateLog(DeviceType.APP, str2);
                }
            }
        }
    }

    public void LOGE(String str, String str2, boolean z, boolean z2) {
        if (!checkTagFilter(str)) {
            if (DEBUGABLE) {
                Log.e(str, str2);
            }
            if (OPEN) {
                if (z) {
                    saveLogInfo2File(str + " e: " + str2);
                }
                if (z2) {
                    LogHelper.getIntance().updateLog(DeviceType.APP, str2);
                }
            }
        }
    }

    public void LOGI(String str, String str2, String str3) {
        if (DEBUGABLE) {
            Log.i(str, str2);
        }
        saveLogInfo2File(str3, str + " i: " + str2);
    }

    public void LOGD(String str, String str2, String str3) {
        if (DEBUGABLE) {
            Log.d(str, str2);
        }
        saveLogInfo2File(str3, "d: " + str2);
    }

    public void LOGE(String str, String str2, String str3) {
        if (DEBUGABLE) {
            Log.e(str, str2);
        }
        saveLogInfo2File(str3, "e: " + str2);
    }

    public void pLogD(String str, String str2, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).a(str2, objArr);
        }
    }

    public void pLogD(String str, Object obj) {
        if (!DEBUGABLE) {
            return;
        }
        if (obj != null) {
            e.b(str).a(obj);
        } else {
            e.b(str).a("null");
        }
    }

    public void pLogE(String str, String str2, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).b(str2, objArr);
        }
    }

    public void pLogE(String str, int i, String str2, Object... objArr) {
        if (DEBUGABLE) {
            e.a(str, i).b(str2, objArr);
        }
    }

    public void pLogE(String str, Throwable th, String str2, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).a(th, str2, objArr);
        }
    }

    public void pLogI(String str, String str2, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).d(str2, objArr);
        }
    }

    public void pLogDFile(String str, String str2, String str3, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).a(str3, objArr);
        }
        saveLogInfo2File(str2, "D: " + createMessage(str3, objArr) + " @" + getDateFormatForItem());
    }

    public void pLogDFile(String str, String str2, Object obj) {
        String str3;
        if (DEBUGABLE) {
            if (obj != null) {
                e.b(str).a(obj);
            } else {
                e.b(str).a("null");
            }
        }
        if (obj == null) {
            str3 = "null";
        } else if (obj.getClass().isArray()) {
            str3 = Arrays.deepToString((Object[]) obj);
        } else {
            str3 = obj.toString();
        }
        saveLogInfo2File(str2, "D: " + str3 + " @" + getDateFormatForItem());
    }

    public void pLogEFile(String str, String str2, String str3, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).b(str3, objArr);
        }
        saveLogInfo2File(str2, "E: " + createMessage(str3, objArr) + " @" + getDateFormatForItem());
    }

    public void pLogIFile(String str, String str2, String str3, Object... objArr) {
        if (DEBUGABLE) {
            e.b(str).d(str3, objArr);
        }
        saveLogInfo2File(str2, "I: " + createMessage(str3, objArr) + " @" + getDateFormatForItem());
    }

    public void pJson(String str) {
        e.c(str);
    }

    public void pXml(String str) {
        e.d(str);
    }

    private String createMessage(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    private String getDateFormatForItem() {
        return this.fromatDataForItem.format(new Date());
    }

    private static DateFormat getDateFormat() {
        DateFormat dateFormat = (DateFormat) threadLocal.get();
        if (dateFormat != null) {
            return dateFormat;
        }
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        threadLocal.set(dateFormat);
        return dateFormat;
    }

    private void saveLogInfo2File(String str) {
        String str2 = str + DebugFile.EOL;
        String str3 = "cache-" + getDateFormat().format(this.date) + ".txt";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(this.dirName);
                if (!file.exists()) {
                    file.mkdirs();
                } else if (file.getUsableSpace() - file.getFreeSpace() > 104857600) {
                    f.e(file);
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.dirName + str3, true);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean checkTagFilter(String str) {
        if (filterTag.length == 0) {
            return false;
        }
        for (String equals : filterTag) {
            if (equals.equals(str)) {
                return false;
            }
        }
        return true;
    }

    private void saveLogInfo2File(String str, String str2) {
        String str3 = str2 + DebugFile.EOL;
        String str4 = str + "/log-" + getDateFormat().format(this.date) + ".txt";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(this.dirName + str + dji.pilot.usercenter.protocol.d.t);
                if (!file.exists()) {
                    file.mkdirs();
                } else if (file.getUsableSpace() - file.getFreeSpace() > 10485760) {
                    f.e(file);
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.dirName + str4, true);
                fileOutputStream.write(str3.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void nfzSaveCrashInfo2File(String str) {
        String str2 = str + DebugFile.EOL;
        String str3 = "cache-" + this.formatData.format(new Date()) + ".txt";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(this.nfzDirName);
                if (!file.exists()) {
                    file.mkdirs();
                } else if (file.getUsableSpace() - file.getFreeSpace() > 104857600) {
                    f.e(file);
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.nfzDirName + str3, true);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void logNoVideoData() {
        Date date = new Date();
        String str = this.logFormatData.format(date) + ": no video data\r\n";
        String str2 = "log-" + this.formatData.format(date) + ".txt";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(this.noVideoLogDirName);
                if (!file.exists()) {
                    file.mkdirs();
                } else if (file.getUsableSpace() - file.getFreeSpace() > 104857600) {
                    f.e(file);
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.noVideoLogDirName + str2, true);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void NFZSavedLOGE(String str, String str2, boolean z, boolean z2) {
        if (DEBUGABLE) {
            Log.e(str, str2);
        }
        if (z) {
            nfzSaveCrashInfo2File(str2);
        }
        if (z2 && DEBUGABLE) {
            LogHelper.getIntance().updateLog(DeviceType.APP, str2);
        }
    }
}
