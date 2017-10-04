package dji.pilot.usercenter.f;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Video.Media;
import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Pattern;

public class e {
    public static final String a = "dd/MM/yyyy";
    public static final String b = "HH:mm:ss";
    public static final String c = "yyyy:MM:dd HH:mm:ss";
    public static final String d = "com.youku.phone";
    public static final String e = "com.youku.ui.activity.MyUploadVideoPageActivity";
    public static final String f = "com.youku.paike";
    public static final String g = "com.youku.paike.videoedit.ActivityVideoEdit";
    public static final String h = "com.google.android.youtube";
    public static final String i = "com.google.android.youtube.UploadIntentHandlingActivity";
    public static final String j = "com.google.android.apps.youtube.app.honeycomb.Shell$UploadActivity";
    public static final String k = "com.google.android.youtube.intent.action.UPLOAD";
    private static final char[] l = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i + i2;
        while (i < i3) {
            a(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void a(byte b, StringBuffer stringBuffer) {
        char c = l[(b & 240) >> 4];
        char c2 = l[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }

    public static String a(byte[] bArr, String str) {
        try {
            str = a(MessageDigest.getInstance("MD5").digest(bArr));
        } catch (Exception e) {
        }
        return str;
    }

    public static String a(String str, String str2) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[1024];
                MessageDigest instance = MessageDigest.getInstance("MD5");
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                str2 = a(instance.digest());
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                    }
                }
                return str2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream2 = fileInputStream;
                th = th3;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str2;
        } catch (Throwable th4) {
            th = th4;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return str2;
    }

    public static String a(String str) {
        try {
            return new String(Base64.encode(str.getBytes("UTF-8"), 2), "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    public static String b(String str) {
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), 2), "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }

    public static boolean c(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return Pattern.compile("^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$").matcher(str).matches();
    }

    public static boolean d(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return Pattern.compile("[a-zA-Z.]*[⺀-﹏]*").matcher(str).matches();
    }

    public static Intent e(String str) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (str != null) {
            intent.putExtra("output", Uri.fromFile(new File(str)));
        }
        return intent;
    }

    public static Intent f(String str) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        return intent;
    }

    public static Intent a(Context context, String str, a aVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        intent.setType("image/*");
        intent.setFlags(268435456);
        return intent;
    }

    public static Uri a(Context context, String str) {
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query == null || !query.moveToFirst()) {
            int i = -1;
            break;
        }
        while (!str.equals(query.getString(query.getColumnIndex("_data")))) {
            if (!query.moveToNext()) {
                int i2 = -1;
                break;
            }
        }
        i2 = query.getInt(query.getColumnIndex("_id"));
        if (query != null) {
            try {
                query.close();
            } catch (Exception e) {
            }
        }
        if (-1 != i2) {
            return Uri.withAppendedPath(uri, String.valueOf(i2));
        }
        return null;
    }

    public static Uri b(Context context, String str) {
        Uri uri = null;
        Uri uri2 = Media.EXTERNAL_CONTENT_URI;
        Cursor query = context.getContentResolver().query(uri2, new String[]{"_id"}, "_data=?", new String[]{str}, null);
        if (query != null && query.moveToFirst()) {
            uri = Uri.withAppendedPath(uri2, String.valueOf((long) query.getInt(query.getColumnIndex("_id"))));
        }
        if (query != null) {
            try {
                query.close();
            } catch (Exception e) {
            }
        }
        return uri;
    }

    public static Intent a(Context context, String str, c cVar, b bVar) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        intent.setType("video/*");
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        bVar.b = false;
        if (!(queryIntentActivities == null || queryIntentActivities.isEmpty())) {
            String[] strArr;
            if (cVar == c.a) {
                strArr = new String[]{d};
            } else if (cVar == c.b) {
                strArr = new String[]{h};
            } else {
                strArr = null;
            }
            if (strArr != null) {
                ResolveInfo resolveInfo;
                ResolveInfo resolveInfo2 = null;
                for (String equals : strArr) {
                    int size = queryIntentActivities.size();
                    for (int i = 0; i < size; i++) {
                        ResolveInfo resolveInfo3 = (ResolveInfo) queryIntentActivities.get(i);
                        if (equals.equals(resolveInfo3.activityInfo.packageName)) {
                            resolveInfo2 = resolveInfo3;
                            break;
                        }
                    }
                    if (resolveInfo2 != null) {
                        resolveInfo = resolveInfo2;
                        break;
                    }
                }
                resolveInfo = resolveInfo2;
                if (resolveInfo != null) {
                    String substring;
                    bVar.b = true;
                    Object fromFile = Uri.fromFile(new File(str));
                    int lastIndexOf = str.lastIndexOf(47);
                    if (lastIndexOf != -1) {
                        substring = str.substring(lastIndexOf + 1);
                    } else {
                        substring = str;
                    }
                    Uri b = b(context, str);
                    if (b == null) {
                        ContentResolver contentResolver = context.getContentResolver();
                        ContentValues contentValues = new ContentValues(4);
                        contentValues.put("title", substring);
                        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
                        String str2 = "video/";
                        int indexOf = substring.indexOf(46);
                        String str3 = "mp4";
                        if (indexOf != -1) {
                            str3 = substring.substring(indexOf + 1);
                        }
                        contentValues.put("mime_type", str2 + str3);
                        contentValues.put("_data", str);
                        b = contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
                    }
                    if (b != null) {
                        fromFile = b;
                    }
                    if (b == null && c.b == cVar) {
                        bVar.a = false;
                        return null;
                    }
                    bVar.a = true;
                    Intent intent2 = new Intent();
                    intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    intent2.setAction("android.intent.action.SEND");
                    intent2.setType("video/*");
                    intent2.putExtra("android.intent.extra.STREAM", fromFile);
                    intent2.putExtra("title", substring);
                    intent2.putExtra("android.intent.extra.SUBJECT", substring);
                    intent2.setData(fromFile);
                    intent2.setFlags(335544320);
                    return intent2;
                }
            }
        }
        return null;
    }
}
