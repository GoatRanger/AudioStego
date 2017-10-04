package dji.midware.stat;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import com.tencent.android.tpush.common.Constants;
import dji.log.DJILogHelper;
import dji.midware.media.e;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

public class StatService {
    public static final long BYTES_IN_MEGA = 1048576;
    public static final int CPU_MEASURE_WINDOW = 1000;
    private static boolean DEBUG = false;
    public static boolean OPEN = false;
    private static boolean SHOW_ON_VIEW = false;
    public static final int STAT_WINDOW_MS = 10000;
    private static String TAG = "StatService";
    private static int c_decoder;
    private static int c_hub;
    private static int c_main;
    private static int c_preview;
    private static Context ctx;
    private static StatService instance = null;
    private static LinkedList objectSet = new LinkedList();
    private static ReferenceQueue q_decoder = new ReferenceQueue();
    private static ReferenceQueue q_hub = new ReferenceQueue();
    private static ReferenceQueue q_main = new ReferenceQueue();
    private static ReferenceQueue q_preview = new ReferenceQueue();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
    private SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
    private FileOutputStream fos;
    private Handler handler;
    private a linuxUtils = new a();
    private HashMap<String, StatBase> statSet = new HashMap();
    private HandlerThread thread = new HandlerThread("DJIStatService");

    public static synchronized StatService getInstance() {
        StatService statService;
        synchronized (StatService.class) {
            if (instance == null) {
                instance = new StatService();
            }
            statService = instance;
        }
        return statService;
    }

    public static synchronized void destroyInstance() {
        synchronized (StatService.class) {
            if (instance != null) {
                instance.onDestroy();
            }
        }
    }

    public static void setContext(Context context) {
        ctx = context;
        getInstance();
    }

    private void initLogFile() {
        if (ctx != null) {
            String str = "Stat_" + this.fileNameFormat.format(new Date()) + ".txt";
            String a = d.a(ctx, "/LOG/STATISTICS/");
            if (Environment.getExternalStorageState().equals("mounted")) {
                try {
                    File file = new File(a);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else if (file.getUsableSpace() - file.getFreeSpace() > 104857600) {
                        f.e(file);
                        file.mkdirs();
                    }
                    e.b(TAG, "create statistics file: " + a + str);
                    this.fos = new FileOutputStream(a + str, true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onDestroy() {
        try {
            if (this.fos != null) {
                this.fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StatService() {
        if (OPEN) {
            initLogFile();
            this.thread.start();
            this.handler = new Handler(this.thread.getLooper());
            this.handler.postDelayed(new Runnable(this) {
                final /* synthetic */ StatService a;

                {
                    this.a = r1;
                }

                public void run() {
                    e.c(StatService.DEBUG, StatService.TAG, "stat service update");
                    try {
                        this.a.saveAllEventToFile();
                    } catch (Exception e) {
                        e.a(StatService.TAG, e);
                    }
                    if (StatService.OPEN) {
                        this.a.handler.postDelayed(this, 10000);
                    }
                }
            }, 10000);
        }
    }

    private void saveAllEventToFile() {
        StringBuilder stringBuilder = new StringBuilder("Time=" + this.dateFormat.format(new Date()) + "\n");
        Object arrayList = new ArrayList();
        synchronized (this.statSet) {
            for (StatBase statBase : this.statSet.values()) {
                arrayList.add("DJIStat : " + statBase.getName() + "=" + statBase.getValueAndReset() + "\n");
            }
        }
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next());
        }
        stringBuilder.append("DJIStat : CPU=" + this.linuxUtils.a(1000) + "\n");
        saveProcessMemoryStat(stringBuilder);
        stringBuilder.append("\n");
        String stringBuilder2 = stringBuilder.toString();
        DJILogHelper.getInstance().LOGI("DJIStat", stringBuilder2, false, SHOW_ON_VIEW);
        try {
            if (this.fos != null) {
                this.fos.write(stringBuilder2.getBytes());
                this.fos.flush();
            }
        } catch (Exception e) {
            e.a(TAG, e);
        }
    }

    private void saveSystemMemoryStat(StringBuilder stringBuilder) {
        ((ActivityManager) ctx.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryInfo(new MemoryInfo());
        stringBuilder.append(String.format("DJIStat : Mem: avail=%d, total=%d, low=%b\n", new Object[]{Long.valueOf(r1.availMem / BYTES_IN_MEGA), Long.valueOf(r1.totalMem / BYTES_IN_MEGA), Boolean.valueOf(r1.lowMemory)}));
    }

    private void saveProcessMemoryStat(StringBuilder stringBuilder) {
        Runtime runtime = Runtime.getRuntime();
        stringBuilder.append(String.format("DJIStat : Mem: free=%d, total=%d, max=%d\n", new Object[]{Long.valueOf(runtime.freeMemory() / BYTES_IN_MEGA), Long.valueOf(runtime.totalMemory() / BYTES_IN_MEGA), Long.valueOf(runtime.maxMemory() / BYTES_IN_MEGA)}));
    }

    public void postEvent(Class<? extends StatBase> cls, String str, float f) {
        if (OPEN) {
            try {
                synchronized (this.statSet) {
                    StatBase statBase = (StatBase) this.statSet.get(str);
                    if (statBase == null) {
                        try {
                            statBase = (StatBase) cls.getConstructor(new Class[]{String.class}).newInstance(new Object[]{str});
                        } catch (Exception e) {
                            e.a(TAG, e);
                        }
                        if (statBase == null) {
                            return;
                        }
                        this.statSet.put(str, statBase);
                    }
                    statBase.addEvent((double) f);
                }
            } catch (Exception e2) {
                e.a(TAG, e2);
            }
        }
    }

    public void countActivity() {
        while (q_preview.poll() != null) {
            c_preview--;
        }
        while (q_hub.poll() != null) {
            c_hub--;
        }
        while (q_main.poll() != null) {
            c_main--;
        }
        while (q_decoder.poll() != null) {
            c_decoder--;
        }
        e.a("Preview=" + c_preview + " Hub=" + c_hub + " Decoder=" + c_decoder);
    }
}
