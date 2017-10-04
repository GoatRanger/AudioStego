package dji.pilot2.mine.b;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Base64;
import com.alipay.sdk.j.i;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.loopj.android.http.RequestParams;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.mine.jsonbean.UploadAttachmentBean;
import dji.pilot2.utils.k;
import dji.pilot2.utils.m;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static final f a = new f();
    private static ArrayList<String> b = new ArrayList();

    public interface a {
        void a(String str);

        void b(String str);
    }

    private f() {
    }

    public static f getInstance() {
        return a;
    }

    public void a(Context context, UploadAttachmentBean uploadAttachmentBean, final a aVar) {
        HttpEntity stringEntity;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"Annotation\":");
        stringBuilder.append(h.a(uploadAttachmentBean));
        stringBuilder.append(i.d);
        try {
            stringEntity = new StringEntity(stringBuilder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            stringEntity = null;
        }
        c.b(context).a(k.C(), stringEntity, RequestParams.APPLICATION_JSON, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ f b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    aVar.a(new JSONObject(str).getJSONObject("data").getString("AnnotationUrl"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void a(Throwable th, int i, String str) {
                aVar.b(str);
            }
        });
    }

    public static byte[] a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        bitmap.recycle();
        return byteArrayOutputStream.toByteArray();
    }

    public static String b(Bitmap bitmap) {
        return Base64.encodeToString(a(bitmap), 0);
    }

    public static String a(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[((int) file.length())];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return Base64.encodeToString(bArr, 0);
    }

    public static String a(String str) throws Exception {
        return a(new File(str));
    }

    public static UploadAttachmentBean a(String str, String str2, String str3) {
        UploadAttachmentBean uploadAttachmentBean = new UploadAttachmentBean();
        String d = d(str);
        try {
            uploadAttachmentBean.setMimeType("image/jpeg");
            uploadAttachmentBean.setSubject(c(d));
            uploadAttachmentBean.setNoteText(str2);
            uploadAttachmentBean.setFileName(d);
            uploadAttachmentBean.setObjectId(str3);
            uploadAttachmentBean.setFileContent(b(c(b(str))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadAttachmentBean;
    }

    public static Bitmap b(String str) {
        return BitmapFactory.decodeFile(str);
    }

    public static Bitmap c(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= dji.midware.media.h.b.a.e && height <= dji.midware.media.h.b.a.e) {
            return bitmap;
        }
        float f;
        Matrix matrix = new Matrix();
        if (width > height) {
            f = 1080.0f / ((float) width);
        } else {
            f = 1080.0f / ((float) height);
        }
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static String c(String str) {
        if (str != null) {
            return str.substring(0, str.lastIndexOf("."));
        }
        return "";
    }

    public static String d(String str) {
        if (str == null) {
            return "";
        }
        File file = new File(str);
        if (file.exists()) {
            return file.getName();
        }
        return "";
    }

    private static boolean e(String str) {
        if (!(str == null || str.lastIndexOf(".") == str.length())) {
            String substring = str.substring(str.lastIndexOf(".") + 1);
            b.addAll(Arrays.asList(new String[]{"jpg", "bmp", "png", "jpeg"}));
            if (b.contains(substring.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String a(Uri uri, ContentResolver contentResolver) {
        if (uri == null) {
            return "";
        }
        if (!uri.toString().startsWith(d.A)) {
            String[] strArr = new String[]{"_data", "mime_type"};
            Cursor query = contentResolver.query(uri, strArr, null, null, null);
            if (query == null) {
                return "";
            }
            query.moveToFirst();
            int columnIndex = query.getColumnIndex(strArr[0]);
            int columnIndex2 = query.getColumnIndex(strArr[1]);
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            query.close();
            if (m.c(string) || m.c(string2) || !string2.startsWith("image")) {
                return "";
            }
            return string;
        } else if (e(uri.toString())) {
            return uri.toString().replace("file:" + File.separator + File.separator, "");
        } else {
            return "";
        }
    }
}
