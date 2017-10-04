package com.here.odnp.util;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class OdnpFileManager {
    private static final String EXTERNALDATA_DIR = ".here-positioning";
    private static final String PRIVATE_DATA_DIR = "data";
    private static final String TAG = "odnp.util.FileManager";

    public static File getPrivateDir(Context context) {
        return context.getDir("data", 0);
    }

    public static File getDataDir(Context context) {
        if (Environment.isExternalStorageRemovable()) {
            return getPrivateDir(context);
        }
        return new File(Environment.getExternalStorageDirectory(), EXTERNALDATA_DIR);
    }

    public static boolean copy(File file, File file2) {
        if (!file.isDirectory()) {
            copyData(file, file2);
        } else if (!file2.isDirectory() && !file2.mkdirs()) {
            return false;
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            for (File file3 : listFiles) {
                if (!copy(file3, new File(file2, file3.getName()))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean copyData(File file, File file2) {
        InputStream bufferedInputStream;
        OutputStream bufferedOutputStream;
        InputStream inputStream;
        Throwable th;
        int close;
        OutputStream outputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                try {
                    OdnpIOUtils.copy(bufferedInputStream, bufferedOutputStream);
                    return OdnpIOUtils.close(bufferedOutputStream) & (OdnpIOUtils.close(bufferedInputStream) & 1);
                } catch (Exception e) {
                    inputStream = bufferedInputStream;
                    return OdnpIOUtils.close(bufferedOutputStream) & (OdnpIOUtils.close(inputStream) & 0);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    outputStream = bufferedOutputStream;
                    th = th3;
                    close = OdnpIOUtils.close(outputStream) & (OdnpIOUtils.close(bufferedInputStream) & 1);
                    throw th;
                }
            } catch (Exception e2) {
                bufferedOutputStream = null;
                inputStream = bufferedInputStream;
                return OdnpIOUtils.close(bufferedOutputStream) & (OdnpIOUtils.close(inputStream) & 0);
            } catch (Throwable th4) {
                th = th4;
                close = OdnpIOUtils.close(outputStream) & (OdnpIOUtils.close(bufferedInputStream) & 1);
                throw th;
            }
        } catch (Exception e3) {
            bufferedOutputStream = null;
            return OdnpIOUtils.close(bufferedOutputStream) & (OdnpIOUtils.close(inputStream) & 0);
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream = null;
            close = OdnpIOUtils.close(outputStream) & (OdnpIOUtils.close(bufferedInputStream) & 1);
            throw th;
        }
    }
}
