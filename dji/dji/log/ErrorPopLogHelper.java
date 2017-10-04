package dji.log;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.debug.DebugFile;
import dji.midware.data.manager.P3.ServiceManager;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ErrorPopLogHelper {
    private static final int DELAY_CLOSE_FOS = 5000;
    private static ErrorPopLogHelper INSTANCE = null;
    private static final int MSG_CLOSE_FOS = 100;
    public static final String POP_LOGS_RELATIVE_PATH = "/LOG/ERROR_POP_LOG/";
    public static final String TAG_TITLE = "## ";
    private Date date = new Date();
    Calendar mCalendar = GregorianCalendar.getInstance();
    private SimpleDateFormat mFormatDate = new SimpleDateFormat("dd-MM-yyyy", Locale.CHINA);
    private FileOutputStream mFos = null;
    private Handler mHandler = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    if (ErrorPopLogHelper.this.mFos != null) {
                        try {
                            ErrorPopLogHelper.this.mFos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ErrorPopLogHelper.this.mFos = null;
                        break;
                    }
                    break;
            }
            return false;
        }
    });
    private String mLogPath;

    public static class PopLogDetailItem {
        public String mContent = "";
        public String mTitle = "";
    }

    public static synchronized ErrorPopLogHelper getInstance(Context context) {
        ErrorPopLogHelper errorPopLogHelper;
        synchronized (ErrorPopLogHelper.class) {
            if (INSTANCE == null) {
                INSTANCE = new ErrorPopLogHelper(context);
            }
            errorPopLogHelper = INSTANCE;
        }
        return errorPopLogHelper;
    }

    private ErrorPopLogHelper(Context context) {
        this.mLogPath = d.a(context, POP_LOGS_RELATIVE_PATH);
    }

    public void logStr(String str, String str2) {
        if (ServiceManager.getInstance() != null && ServiceManager.getInstance().isRemoteOK()) {
            this.mHandler.removeMessages(100);
            String format = this.mFormatDate.format(this.date);
            if (Environment.getExternalStorageState().equals("mounted")) {
                try {
                    File file = new File(this.mLogPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else if (file.getUsableSpace() - file.getFreeSpace() > 104857600) {
                        f.e(file);
                        file.mkdirs();
                    }
                    if (this.mFos == null) {
                        this.mFos = new FileOutputStream(this.mLogPath + format, true);
                    }
                    format = TAG_TITLE;
                    this.mCalendar.setTime(new Date());
                    PopLogDetailItem popLogDetailItem = new PopLogDetailItem();
                    if ("".equals(str2)) {
                        popLogDetailItem.mTitle = "" + addZero(this.mCalendar.get(11), 2) + ":" + addZero(this.mCalendar.get(12), 2) + ":" + addZero(this.mCalendar.get(13), 2);
                        popLogDetailItem.mContent = str;
                        format = format + popLogDetailItem.mTitle + "\n" + popLogDetailItem.mContent;
                    } else {
                        popLogDetailItem.mTitle = addZero(this.mCalendar.get(11), 2) + ":" + addZero(this.mCalendar.get(12), 2) + ":" + addZero(this.mCalendar.get(13), 2) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str;
                        popLogDetailItem.mContent = str2;
                        format = format + "" + popLogDetailItem.mTitle + "\n" + popLogDetailItem.mContent;
                    }
                    c.a().e(popLogDetailItem);
                    this.mFos.write((format + DebugFile.EOL).getBytes());
                    this.mFos.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } finally {
                    this.mHandler.sendEmptyMessageDelayed(100, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                }
            }
        }
    }

    private String addZero(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        while (stringBuffer.length() < i2) {
            stringBuffer.insert(0, "0");
        }
        return stringBuffer.toString();
    }
}
