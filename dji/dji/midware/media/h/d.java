package dji.midware.media.h;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import android.util.Log;
import dji.midware.media.e;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class d {
    public static String a = "GLUtil";
    public static boolean b = false;
    public static final int c = 6408;

    public static class a {
        public int a = -1;
        public int b = -1;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public static int a(int i) {
        return a(i, false);
    }

    public static int a(int i, boolean z) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        GLES20.glBindTexture(i, i2);
        a("glBindTexture mTextureID");
        GLES20.glTexParameterf(i, 10241, 9728.0f);
        GLES20.glTexParameterf(i, 10240, 9729.0f);
        if (z) {
            GLES20.glTexParameteri(i, 10242, 10497);
            GLES20.glTexParameteri(i, 10243, 10497);
        } else {
            GLES20.glTexParameteri(i, 10242, 33071);
            GLES20.glTexParameteri(i, 10243, 33071);
        }
        a("glTexParameter");
        return i2;
    }

    public static void a(int i, int i2, ByteBuffer byteBuffer, int i3, int i4) {
        GLES20.glBindTexture(i2, i);
        GLES20.glTexImage2D(i2, 0, c, i3, i4, 0, c, 5121, byteBuffer);
    }

    public static void a(String str) {
        int glGetError;
        do {
            glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                Log.e(a, str + ": glError " + glGetError);
            } else {
                return;
            }
        } while (!b);
        throw new RuntimeException(str + ": glError " + glGetError);
    }

    public static void a() {
        GLES20.glGetError();
    }

    public static a a(int i, int i2) {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        int i3 = iArr[0];
        GLES20.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        GLES20.glBindFramebuffer(36160, i3);
        GLES20.glBindTexture(3553, i4);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, c, i, i2, 0, c, 5121, null);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i4, 0);
        int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        if (glCheckFramebufferStatus != 36053) {
            Log.e(a, "framebuffer's status: " + glCheckFramebufferStatus);
        }
        return new a(i3, i4);
    }

    public static void b(int i) {
        if (i >= 0) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
    }

    public static void c(int i) {
        if (i >= 0) {
            GLES20.glDeleteFramebuffers(1, new int[]{i}, 0);
        }
    }

    public static int b() {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, IntBuffer.wrap(iArr));
        return iArr[0];
    }

    public static void d(int i) {
        e.c(b, a, "Bind frameBuffer to target. FB=" + i);
        GLES20.glBindFramebuffer(36160, i);
    }

    public static void a(ByteBuffer byteBuffer, int i, int i2, int i3) {
        GLES20.glBindTexture(3553, i);
        GLES20.glTexImage2D(3553, 0, c, i2, i3, 0, c, 5121, byteBuffer);
    }

    public static void a(String str, int i, int i2) {
        GLES20.glPixelStorei(3333, 4);
        Buffer allocateDirect = ByteBuffer.allocateDirect((i * i2) * 4);
        allocateDirect.clear();
        GLES20.glReadPixels(0, 0, i, i2, c, 5121, allocateDirect);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        allocateDirect.position(0);
        allocateDirect.limit((i * i2) * 4);
        createBitmap.copyPixelsFromBuffer(allocateDirect);
        try {
            OutputStream fileOutputStream = new FileOutputStream(str);
            if (fileOutputStream != null) {
                createBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.a(a, e);
                }
            }
        } catch (Exception e2) {
            e.a(a, e2);
        }
    }
}
