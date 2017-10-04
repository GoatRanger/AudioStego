package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.os.BuildCompat;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

public class ContextCompat {
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    public static boolean startActivities(Context context, Intent[] intentArr) {
        return startActivities(context, intentArr, null);
    }

    public static boolean startActivities(Context context, Intent[] intentArr, Bundle bundle) {
        int i = VERSION.SDK_INT;
        if (i >= 16) {
            ContextCompatJellybean.startActivities(context, intentArr, bundle);
            return true;
        } else if (i < 11) {
            return false;
        } else {
            ContextCompatHoneycomb.startActivities(context, intentArr);
            return true;
        }
    }

    public static File getDataDir(Context context) {
        if (BuildCompat.isAtLeastN()) {
            return ContextCompatApi24.getDataDir(context);
        }
        String str = context.getApplicationInfo().dataDir;
        return str != null ? new File(str) : null;
    }

    public static File[] getObbDirs(Context context) {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            return ContextCompatKitKat.getObbDirs(context);
        }
        File obbDir;
        if (i >= 11) {
            obbDir = ContextCompatHoneycomb.getObbDir(context);
        } else {
            obbDir = buildPath(Environment.getExternalStorageDirectory(), DIR_ANDROID, DIR_OBB, context.getPackageName());
        }
        return new File[]{obbDir};
    }

    public static File[] getExternalFilesDirs(Context context, String str) {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            return ContextCompatKitKat.getExternalFilesDirs(context, str);
        }
        File externalFilesDir;
        if (i >= 8) {
            externalFilesDir = ContextCompatFroyo.getExternalFilesDir(context, str);
        } else {
            externalFilesDir = buildPath(Environment.getExternalStorageDirectory(), DIR_ANDROID, "data", context.getPackageName(), DIR_FILES, str);
        }
        return new File[]{externalFilesDir};
    }

    public static File[] getExternalCacheDirs(Context context) {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            return ContextCompatKitKat.getExternalCacheDirs(context);
        }
        File externalCacheDir;
        if (i >= 8) {
            externalCacheDir = ContextCompatFroyo.getExternalCacheDir(context);
        } else {
            externalCacheDir = buildPath(Environment.getExternalStorageDirectory(), DIR_ANDROID, "data", context.getPackageName(), "cache");
        }
        return new File[]{externalCacheDir};
    }

    private static File buildPath(File file, String... strArr) {
        int length = strArr.length;
        int i = 0;
        File file2 = file;
        while (i < length) {
            File file3;
            String str = strArr[i];
            if (file2 == null) {
                file3 = new File(str);
            } else if (str != null) {
                file3 = new File(file2, str);
            } else {
                file3 = file2;
            }
            i++;
            file2 = file3;
        }
        return file2;
    }

    public static final Drawable getDrawable(Context context, int i) {
        int i2 = VERSION.SDK_INT;
        if (i2 >= 21) {
            return ContextCompatApi21.getDrawable(context, i);
        }
        if (i2 >= 16) {
            return context.getResources().getDrawable(i);
        }
        synchronized (sLock) {
            if (sTempValue == null) {
                sTempValue = new TypedValue();
            }
            context.getResources().getValue(i, sTempValue, true);
            i2 = sTempValue.resourceId;
        }
        return context.getResources().getDrawable(i2);
    }

    public static final ColorStateList getColorStateList(Context context, int i) {
        if (VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColorStateList(context, i);
        }
        return context.getResources().getColorStateList(i);
    }

    public static final int getColor(Context context, int i) {
        if (VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColor(context, i);
        }
        return context.getResources().getColor(i);
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static final File getNoBackupFilesDir(Context context) {
        if (VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getNoBackupFilesDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }

    public static File getCodeCacheDir(Context context) {
        if (VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getCodeCacheDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }

    private static synchronized File createFilesDir(File file) {
        synchronized (ContextCompat.class) {
            if (!(file.exists() || file.mkdirs() || file.exists())) {
                Log.w(TAG, "Unable to create files subdir " + file.getPath());
                file = null;
            }
        }
        return file;
    }

    public static Context createDeviceProtectedStorageContext(Context context) {
        if (BuildCompat.isAtLeastN()) {
            return ContextCompatApi24.createDeviceProtectedStorageContext(context);
        }
        return null;
    }

    @Deprecated
    public static Context createDeviceEncryptedStorageContext(Context context) {
        return createDeviceProtectedStorageContext(context);
    }

    public static boolean isDeviceProtectedStorage(Context context) {
        if (BuildCompat.isAtLeastN()) {
            return ContextCompatApi24.isDeviceProtectedStorage(context);
        }
        return false;
    }

    @Deprecated
    public static boolean isDeviceEncryptedStorage(Context context) {
        return isDeviceProtectedStorage(context);
    }
}
