package dji.publics.DJIObject;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import com.tencent.android.tpush.common.Constants;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class b implements UncaughtExceptionHandler {
    private static b b;
    private UncaughtExceptionHandler a;
    private SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
    private String d;
    private Context e;

    public enum a {
        Crashed
    }

    private b() {
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    public void a(Context context) {
        this.e = context;
        this.d = d.a(context, "/LOG/CRASH/");
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a(th) || this.a == null) {
            Log.e("DJICrashHandler", "uncaughtException 2");
            c.a().e(a.Crashed);
            Log.e("DJICrashHandler", "uncaughtException 3");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("DJICrashHandler", "uncaughtException 4");
            this.a.uncaughtException(thread, th);
            ((ActivityManager) this.e.getSystemService(Constants.FLAG_ACTIVITY_NAME)).killBackgroundProcesses(this.e.getPackageName());
            Process.killProcess(Process.myPid());
            Log.e("DJICrashHandler", "uncaughtException 5");
            return;
        }
        Log.e("DJICrashHandler", "uncaughtException 1");
        this.a.uncaughtException(thread, th);
        Process.killProcess(Process.myPid());
    }

    public boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        b(th);
        return true;
    }

    private String b(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        String str = "crash-" + this.c.format(new Date()) + ".txt";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(this.d);
                if (!file.exists()) {
                    file.mkdirs();
                } else if (f.c(file) > 52428800) {
                    f.e(file);
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(this.d + str);
                fileOutputStream.write(stringBuffer.toString().getBytes());
                fileOutputStream.close();
                return str;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
