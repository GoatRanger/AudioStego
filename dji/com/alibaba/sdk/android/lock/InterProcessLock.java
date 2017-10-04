package com.alibaba.sdk.android.lock;

import android.content.Context;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import com.alibaba.sdk.android.util.IOUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

public class InterProcessLock {
    private Object a = new Object();
    private File b;
    private volatile a c;
    private String d;
    public boolean disabled;
    public boolean enableUTLog;
    public long tryLockTimeoutTimeMill = 2000;

    private static class a {
        public FileOutputStream a;
        public FileLock b;

        private a() {
        }
    }

    public InterProcessLock(Context context, String str) {
        this.b = context.getDir("alisdk_locks", 0);
        if (!this.b.exists()) {
            this.b.mkdirs();
        }
        this.d = str;
    }

    public synchronized boolean tryLock() {
        return tryLock(null);
    }

    public synchronized boolean tryLock(String str) {
        boolean z;
        Throwable th;
        FileLock fileLock;
        if (this.disabled || this.c != null) {
            z = true;
        } else {
            Closeable closeable = null;
            try {
                File file = new File(this.b, this.d);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    FileChannel channel = fileOutputStream.getChannel();
                    long currentTimeMillis = System.currentTimeMillis() + this.tryLockTimeoutTimeMill;
                    long j = this.tryLockTimeoutTimeMill / 4;
                    FileLock fileLock2 = null;
                    while (true) {
                        try {
                            fileLock2 = a(channel);
                            if (fileLock2 == null && System.currentTimeMillis() - currentTimeMillis < 0) {
                                synchronized (this.a) {
                                    this.a.wait(j);
                                }
                            } else if (fileLock2 != null) {
                                if (this.enableUTLog) {
                                    a("tryLock_" + str, false, "msg", "fail to get lock");
                                }
                                z = false;
                            } else {
                                if (this.enableUTLog) {
                                    a("tryLock_" + str, true, "msg", "get lock");
                                }
                                this.c = new a();
                                this.c.b = fileLock2;
                                this.c.a = fileOutputStream;
                                z = true;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            FileOutputStream fileOutputStream2 = fileOutputStream;
                            fileLock = fileLock2;
                            closeable = fileOutputStream2;
                        }
                    }
                    if (fileLock2 != null) {
                        if (this.enableUTLog) {
                            a("tryLock_" + str, true, "msg", "get lock");
                        }
                        this.c = new a();
                        this.c.b = fileLock2;
                        this.c.a = fileOutputStream;
                        z = true;
                    } else {
                        if (this.enableUTLog) {
                            a("tryLock_" + str, false, "msg", "fail to get lock");
                        }
                        z = false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    Object obj = fileOutputStream;
                    fileLock = null;
                    if (fileLock == null && closeable != null) {
                        IOUtils.closeQuietly(closeable);
                    }
                    if (this.enableUTLog) {
                        a("tryLock_" + str, false, "msg", "fail to get lock, the message is " + th.getMessage());
                    }
                    z = false;
                    return z;
                }
            } catch (Throwable th4) {
                th = th4;
                fileLock = null;
                IOUtils.closeQuietly(closeable);
                if (this.enableUTLog) {
                    a("tryLock_" + str, false, "msg", "fail to get lock, the message is " + th.getMessage());
                }
                z = false;
                return z;
            }
        }
        return z;
    }

    private static FileLock a(FileChannel fileChannel) {
        try {
            return fileChannel.tryLock(0, 10, false);
        } catch (Throwable th) {
            return null;
        }
    }

    public synchronized boolean unLock() {
        return unLock(null);
    }

    public synchronized boolean unLock(String str) {
        boolean z = true;
        synchronized (this) {
            if (!this.disabled) {
                if (this.c == null) {
                    z = false;
                } else {
                    if (this.c.b != null) {
                        try {
                            this.c.b.release();
                            if (this.enableUTLog) {
                                a("unLock_" + str, true, "msg", "release lock");
                            }
                        } catch (Throwable th) {
                        }
                    }
                    if (this.c.a != null) {
                        IOUtils.closeQuietly(this.c.a);
                    }
                    this.c = null;
                }
            }
        }
        return z;
    }

    private static void a(String str, boolean z, String... strArr) {
        try {
            UserTrackerService userTrackerService = (UserTrackerService) com.alibaba.sdk.android.b.a.e.a(UserTrackerService.class, null);
            if (userTrackerService != null) {
                Map hashMap = new HashMap();
                hashMap.put("process", CommonUtils.getCurrentProcessName());
                if (strArr.length > 1) {
                    int length = strArr.length;
                    for (int i = 0; i < length; i += 2) {
                        hashMap.put(strArr[i], strArr[i + 1]);
                    }
                }
                userTrackerService.sendCustomHit(str, 0, z ? "success" : "error", hashMap);
            }
        } catch (Throwable th) {
        }
    }
}
