package dji.pilot.fpv.activity;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;
import android.view.Surface;
import dji.midware.data.manager.P3.i;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.h.b.a;
import dji.midware.usbhost.P3.NativeRcController;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class k implements OnFrameAvailableListener, Renderer {
    public static final int a = 6407;
    public static final int b = 33635;
    public static final int c = 2;
    public static final boolean d = false;
    public static int e = 0;
    public static SurfaceTexture f = null;
    private static String h = "VideoRender";
    private static final int k = 4;
    private static final int l = 20;
    private static final int m = 0;
    private static final int n = 3;
    private static int z = 36197;
    private final Queue<Runnable> A;
    GLSurfaceView g = null;
    private int i = 0;
    private int j = 0;
    private final float[] o = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    private FloatBuffer p;
    private final String q = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private final String r = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private float[] s = new float[16];
    private float[] t = new float[16];
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;

    public k(GLSurfaceView gLSurfaceView) {
        this.g = gLSurfaceView;
        this.p = ByteBuffer.allocateDirect(this.o.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.p.put(this.o).position(0);
        this.A = new LinkedList();
        Matrix.setIdentityM(this.t, 0);
    }

    public void onDrawFrame(GL10 gl10) {
        if (f != null) {
            f.attachToGLContext(e);
        }
        synchronized (this.A) {
            while (!this.A.isEmpty()) {
                ((Runnable) this.A.poll()).run();
            }
        }
        f.updateTexImage();
        f.getTransformMatrix(this.t);
        GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.u);
        a("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(z, e);
        this.p.position(0);
        GLES20.glVertexAttribPointer(this.x, 3, 5126, false, 20, this.p);
        a("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.x);
        a("glEnableVertexAttribArray maPositionHandle");
        this.p.position(3);
        GLES20.glVertexAttribPointer(this.y, 3, 5126, false, 20, this.p);
        a("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.y);
        a("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.s, 0);
        GLES20.glUniformMatrix4fv(this.v, 1, false, this.s, 0);
        GLES20.glUniformMatrix4fv(this.w, 1, false, this.t, 0);
        GLES20.glDrawArrays(5, 0, 4);
        a("glDrawArrays");
        GLES20.glFinish();
        f.detachFromGLContext();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.i = a.e;
        this.j = 720;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        System.out.println("onSurfaceCreated");
        this.u = a("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        if (this.u != 0) {
            this.x = GLES20.glGetAttribLocation(this.u, "aPosition");
            a("glGetAttribLocation aPosition");
            if (this.x == -1) {
                throw new RuntimeException("Could not get attrib location for aPosition");
            }
            this.y = GLES20.glGetAttribLocation(this.u, "aTextureCoord");
            a("glGetAttribLocation aTextureCoord");
            if (this.y == -1) {
                throw new RuntimeException("Could not get attrib location for aTextureCoord");
            }
            this.v = GLES20.glGetUniformLocation(this.u, "uMVPMatrix");
            a("glGetUniformLocation uMVPMatrix");
            if (this.v == -1) {
                throw new RuntimeException("Could not get attrib location for uMVPMatrix");
            }
            this.w = GLES20.glGetUniformLocation(this.u, "uSTMatrix");
            a("glGetUniformLocation uSTMatrix");
            if (this.w == -1) {
                throw new RuntimeException("Could not get attrib location for uSTMatrix");
            }
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            e = iArr[0];
            GLES20.glBindTexture(z, e);
            a("glBindTexture mTextureID");
            GLES20.glTexParameterf(z, 10241, 9728.0f);
            GLES20.glTexParameterf(z, 10240, 9729.0f);
            f = new SurfaceTexture(e);
            f.setOnFrameAvailableListener(this);
            Surface surface = new Surface(f);
            f.detachFromGLContext();
        }
    }

    void a() {
        try {
            InputStream openRawResource = this.g.getResources().openRawResource(DJIVideoDecoder.getIframeRawId(i.getInstance().c(), 1280, 720));
            int available = openRawResource.available();
            byte[] bArr = new byte[available];
            openRawResource.read(bArr);
            NativeRcController.b(bArr, available);
            openRawResource.close();
        } catch (Exception e) {
        }
    }

    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
    }

    private int a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                Log.e(h, "Could not compile shader " + i + ":");
                Log.e(h, GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                return 0;
            }
        }
        return glCreateShader;
    }

    private int a(String str, String str2) {
        int a = a(35633, str);
        if (a == 0) {
            return 0;
        }
        int a2 = a(35632, str2);
        if (a2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, a);
            a("glAttachShader");
            GLES20.glAttachShader(glCreateProgram, a2);
            a("glAttachShader");
            GLES20.glLinkProgram(glCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                Log.e(h, "Could not link program: ");
                Log.e(h, GLES20.glGetProgramInfoLog(glCreateProgram));
                GLES20.glDeleteProgram(glCreateProgram);
                return 0;
            }
        }
        return glCreateProgram;
    }

    private void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e(h, str + ": glError " + glGetError);
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }

    protected void a(Runnable runnable) {
        synchronized (this.A) {
            this.A.add(runnable);
        }
    }

    protected void a(Surface surface, int i, int i2) {
    }
}
