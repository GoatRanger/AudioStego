package dji.pilot.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.phonecamera.R;
import dji.pilot.phonecamera.a.c;
import dji.sdksharedlib.b.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a {
    public static final int a = 1;
    public static final String b = Environment.getExternalStoragePublicDirectory(DJIUsbAccessoryReceiver.c).toString();
    public static final String c = (b + "/Camera");
    public static final String d = "/DJI/Camera";
    public static final String e = "/DJI/Camera/LongExposure";
    public static final String f = "/DJI/Camera/PANO";
    public static final int g = 2;
    public static final long h = -1;
    public static final long i = -2;
    public static final long j = -3;
    public static final long k = 314572800;
    public static String l = null;
    public static final String m = "unknown";
    private static final String n = "Storage";

    public enum a {
        INTERNAL,
        EXTERNAL;
        
        private String c;

        public void a(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }
    }

    @TargetApi(19)
    public static boolean a(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            return false;
        }
        File[] externalCacheDirs = context.getExternalCacheDirs();
        if (externalCacheDirs.length >= 2 && externalCacheDirs[1] != null) {
            String absolutePath = externalCacheDirs[1].getAbsolutePath();
            absolutePath.indexOf("/Android");
            l = absolutePath.substring(0, absolutePath.indexOf("/Android"));
        }
        if (VERSION.SDK_INT < 21 || TextUtils.isEmpty(l)) {
            z = false;
        }
        return z;
    }

    public static boolean b(Context context) {
        Uri a = c.a().a(context);
        return (a == null || TextUtils.isEmpty(l) || TextUtils.isEmpty(a.toString())) ? false : true;
    }

    public static boolean c(Context context) {
        boolean z;
        boolean a = a(context);
        boolean b = c.a().b();
        if (c.a().e() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z && b && a && a(c.a().d(), context)) {
            return true;
        }
        return false;
    }

    public static void d(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DJILPStorageGrantedActivity.class);
        context.startActivity(intent);
    }

    @TargetApi(21)
    public static boolean a(Uri uri, Context context) {
        boolean z = false;
        if (!TextUtils.isEmpty(uri.toString()) && VERSION.SDK_INT >= 21) {
            String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
            Log.d(n, "isTreeUriMatchDir id: " + treeDocumentId);
            String[] split = treeDocumentId.split(":");
            if (split.length >= 2) {
                String str = split[0];
                String str2 = split[1];
                Log.d(n, "isTreeUriMatchDir type: " + str);
                Log.d(n, "isTreeUriMatchDir path: " + str2);
                Toast.makeText(context, context.getText(R.string.lp_camera_storage_notify_1), 0);
            } else if (a(context, uri)) {
                z = true;
            }
            if (!z) {
                a(context, "");
            }
        }
        return z;
    }

    public static String[] e(Context context) {
        String[] strArr = new String[2];
        strArr[0] = c;
        Uri a = c.a().a(context);
        if (!(!a(context) || a == null || TextUtils.isEmpty(a.toString()))) {
            strArr[1] = l + d;
            Log.d(n, "getStoragePaths: " + strArr[1]);
        }
        return strArr;
    }

    public static long a(String str) {
        Log.d(n, "getAvailableSpace: " + str);
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        File file = new File(str);
        file.mkdirs();
        if (file.isDirectory()) {
            String a = a(file);
            Log.d(n, "storage state = " + a);
            if ("checking".equals(a)) {
                return -2;
            }
            if (!"mounted".equals(a)) {
                return -1;
            }
            try {
                StatFs statFs = new StatFs(str);
                return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            } catch (Throwable e) {
                Log.i(n, "Fail to access external storage", e);
                return -3;
            }
        }
        Log.d(n, "getAvailableSpace: is not dir or can not write");
        return -1;
    }

    public static String a(File file) {
        if (VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(file);
        }
        try {
            if (file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return Environment.getExternalStorageState();
            }
        } catch (IOException e) {
            Log.w(n, "Failed to resolve canonical path: " + e);
        }
        return "unknown";
    }

    public static String f(Context context) {
        if (!c.a().b()) {
            Log.d(n, "getStorageDirectory: internal");
            return Environment.getExternalStorageDirectory().toString();
        } else if (c(context)) {
            Log.d(n, "getStorageDirectory: external");
            return l;
        } else {
            Log.d(n, "getStorageDirectory: other");
            return Environment.getExternalStorageDirectory().toString();
        }
    }

    private static String a(int i, int i2, String str) {
        return new SimpleDateFormat("'DJI'_yyyyMMdd_HHmmss").format(new Date(System.currentTimeMillis()));
    }

    @TargetApi(21)
    public static File a(Uri uri) {
        Log.d(n, "getFileFromDocumentUriSAF: " + uri);
        File file = null;
        if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
            String documentId = DocumentsContract.getDocumentId(uri);
            Log.d(n, "getFileFromDocumentUriSAF id: " + documentId);
            file = b(documentId);
        }
        if (file != null) {
            Log.d(n, "file: " + file.getAbsolutePath());
        } else {
            Log.d(n, "failed to find file");
        }
        return file;
    }

    @TargetApi(21)
    private static File b(String str) {
        File file = null;
        String[] split = str.split(":");
        if (split.length < 2) {
            return null;
        }
        String str2 = split[0];
        String str3 = split[1];
        Log.d(n, "getFileFromDocumentIdSAF type: " + str2);
        Log.d(n, "getFileFromDocumentIdSAF path: " + str3);
        File[] listFiles = new File("/storage").listFiles();
        if ("primary".equalsIgnoreCase(str2)) {
            file = new File(Environment.getExternalStorageDirectory(), str3);
        }
        int i = 0;
        File file2 = file;
        while (listFiles != null && i < listFiles.length && file2 == null) {
            file = new File(listFiles[i], str3);
            if (!file.exists()) {
                file = file2;
            }
            i++;
            file2 = file;
        }
        return file2;
    }

    @TargetApi(21)
    public static String g(Context context) {
        Log.d(n, "getImageFolderNameSAF");
        Uri d = c.a().d();
        Log.d(n, "getImageFolderNameSAF uri: " + d.toString());
        if (!"com.android.externalstorage.documents".equals(d.getAuthority())) {
            return c;
        }
        String treeDocumentId = DocumentsContract.getTreeDocumentId(d);
        Log.d(n, "id: " + treeDocumentId);
        String[] split = treeDocumentId.split(":");
        if (split.length >= 2) {
            String str = split[0];
            treeDocumentId = split[1];
            Log.d(n, "getImageFolderNameSAF type: " + str);
            Log.d(n, "getImageFolderNameSAF path: " + treeDocumentId);
            if ("primary".equalsIgnoreCase(str)) {
                return context.getResources().getString(R.string.lp_phone_camera_storage_internal) + ":" + c;
            }
            return context.getResources().getString(R.string.lp_phone_camera_storage_external) + ":" + l + d;
        } else if ("primary".equalsIgnoreCase(split[0])) {
            return context.getResources().getString(R.string.lp_phone_camera_storage_internal) + ":" + c;
        } else {
            return context.getResources().getString(R.string.lp_phone_camera_storage_external) + ":" + l + d;
        }
    }

    @TargetApi(21)
    public static boolean a(Context context, Uri uri) {
        boolean z = true;
        if (!"com.android.externalstorage.documents".equals(uri.getAuthority())) {
            return true;
        }
        String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
        Log.d(n, "id: " + treeDocumentId);
        if (treeDocumentId.split(":").length >= 2) {
            Toast.makeText(context, context.getText(R.string.lp_camera_storage_notify_1), 0);
            return false;
        }
        File file = new File(l + "/DJI");
        File file2 = new File(l + d);
        File file3 = new File(l + f);
        File file4 = new File(l + e);
        if (file2.exists()) {
            if (!file3.exists()) {
                if (DocumentsContract.createDocument(context.getContentResolver(), DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + d), "vnd.android.document/directory", "PANO") == null) {
                    z = false;
                }
            }
            if (file4.exists()) {
                return z;
            }
            if (DocumentsContract.createDocument(context.getContentResolver(), DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + d), "vnd.android.document/directory", "LongExposure") == null) {
                return false;
            }
            return z;
        } else if (file.exists()) {
            Uri createDocument = DocumentsContract.createDocument(context.getContentResolver(), DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + "/DJI"), "vnd.android.document/directory", b.a);
            r3 = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + d);
            if (file3.exists()) {
                r2 = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + f);
            } else {
                r2 = DocumentsContract.createDocument(context.getContentResolver(), r3, "vnd.android.document/directory", "PANO");
            }
            if (file4.exists()) {
                r3 = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + e);
            } else {
                r3 = DocumentsContract.createDocument(context.getContentResolver(), r3, "vnd.android.document/directory", "LongExposure");
            }
            if (createDocument == null || r2 == null || r3 == null) {
                return false;
            }
            return true;
        } else {
            DocumentsContract.createDocument(context.getContentResolver(), DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)), "vnd.android.document/directory", DJIUsbAccessoryReceiver.c);
            DocumentsContract.createDocument(context.getContentResolver(), DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + "/DJI"), "vnd.android.document/directory", b.a);
            r2 = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri) + d);
            r3 = DocumentsContract.createDocument(context.getContentResolver(), r2, "vnd.android.document/directory", "PANO");
            r2 = DocumentsContract.createDocument(context.getContentResolver(), r2, "vnd.android.document/directory", "LongExposure");
            if (r3 == null || r2 == null) {
                return false;
            }
            return true;
        }
    }

    @TargetApi(21)
    private static int a() {
        if (VERSION.SDK_INT < 21) {
            return 0;
        }
        Uri d = c.a().d();
        Log.d(n, "uri: " + d);
        if (!"com.android.externalstorage.documents".equals(d.getAuthority())) {
            return 0;
        }
        String treeDocumentId = DocumentsContract.getTreeDocumentId(d);
        Log.d(n, "getStorageType id: " + treeDocumentId);
        String[] split = treeDocumentId.split(":");
        if (split.length >= 2) {
            String str = split[0];
            treeDocumentId = split[1];
            Log.d(n, "getStorageType type: " + str);
            Log.d(n, "getStorageType path: " + treeDocumentId);
            if ("primary".equalsIgnoreCase(str)) {
                return 0;
            }
            return 1;
        } else if ("primary".equalsIgnoreCase(split[0])) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void h(Context context) {
        c.a().a("");
        String g = g(context);
        c.a().c(g);
        c.a().a(0);
        c.a().a(false);
        c.a().a("");
        a aVar = a.INTERNAL;
        aVar.a(g);
        dji.thirdparty.a.c.a().e(aVar);
    }

    public static void a(Context context, String str) {
        c.a().a(str);
        String g = g(context);
        c.a().c(g);
        c.a().a(a());
        a aVar;
        if (c.a().e() == 1) {
            c.a().b(str);
            c.a().a(context, str);
            c.a().a(true);
            aVar = a.EXTERNAL;
            aVar.a(g);
            dji.thirdparty.a.c.a().e(aVar);
        } else {
            c.a().a(false);
            c.a().a("");
            aVar = a.INTERNAL;
            aVar.a(g);
            dji.thirdparty.a.c.a().e(aVar);
        }
        if (g != null) {
            Toast.makeText(context, context.getText(R.string.lp_camera_storage_location_change) + g, 0).show();
        }
    }

    @TargetApi(21)
    public static void b(Context context, String str) {
        Log.d(n, "deleteMediaFile: " + str);
        if (!TextUtils.isEmpty(str)) {
            File file;
            if (str.contains(c)) {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                    return;
                }
                return;
            }
            if (TextUtils.isEmpty(l)) {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            } else if (!str.contains(l)) {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            }
            if ((a(context) || b(context)) && !TextUtils.isEmpty(l) && str.contains(l)) {
                Uri a = c.a().a(context);
                if (a != null && !TextUtils.isEmpty(a.toString())) {
                    int indexOf = str.indexOf("/DJI");
                    if (indexOf != -1) {
                        String substring = str.substring(indexOf);
                        Log.d(n, "deleteMediaFile: medieFileName = " + substring);
                        a = DocumentsContract.buildDocumentUriUsingTree(a, DocumentsContract.getTreeDocumentId(a) + substring);
                        Log.d(n, "deleteMediaFile: Authority " + a.toString());
                        DocumentsContract.deleteDocument(context.getContentResolver(), a);
                    }
                }
            }
        }
    }

    public static void a(Context context, String str, String str2, File file) throws FileNotFoundException, IOException {
        InputStream fileInputStream;
        Throwable th;
        OutputStream outputStream = null;
        Log.d(n, "copyUriToFile");
        Log.d(n, "picFile: " + file.toString());
        Uri a = a(context, 1, str2, str);
        Log.d(n, "dest dir: " + a(a).getAbsolutePath());
        try {
            fileInputStream = new FileInputStream(file);
            try {
                outputStream = context.getContentResolver().openOutputStream(a);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            Object obj = outputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    public static void a(Context context, Uri uri, File file) throws FileNotFoundException, IOException {
        Throwable th;
        OutputStream outputStream = null;
        Log.d(n, "copyUriToFile");
        Log.d(n, "saveUri: " + uri);
        Log.d(n, "picFile: " + file);
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                outputStream = context.getContentResolver().openOutputStream(uri);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            Object obj = outputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }

    @TargetApi(21)
    public static Uri a(Context context, int i, String str, String str2) throws IOException {
        try {
            Uri d = c.a().d();
            Log.d(n, "treeUri: " + d);
            Uri buildDocumentUriUsingTree = DocumentsContract.buildDocumentUriUsingTree(d, DocumentsContract.getTreeDocumentId(d) + str2);
            Log.d(n, "convert docUri: " + buildDocumentUriUsingTree);
            String str3 = "";
            if (i == 1) {
                if (str.equals("dng")) {
                    str3 = "image/dng";
                } else {
                    str3 = "image/jpeg";
                }
            } else if (i == 2) {
                str3 = "video/mp4";
            } else {
                Log.e(n, "unknown type: " + i);
                throw new RuntimeException();
            }
            d = DocumentsContract.createDocument(context.getContentResolver(), buildDocumentUriUsingTree, str3, a(i, 0, str));
            Log.d(n, "returned fileUri: " + d);
            if (d != null) {
                return d;
            }
            throw new IOException();
        } catch (IllegalArgumentException e) {
            Log.e(n, "createOutputMediaFileSAF failed");
            e.printStackTrace();
            throw new IOException();
        }
    }
}
