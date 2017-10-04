package com.nokia.maps;

import dji.pilot.usercenter.protocol.e;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

class ap {
    static Object a = new Object();
    private static int b = 12440;

    static EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        bj.b("GLContextHelper", "creating OpenGL ES 2.0 context", new Object[0]);
        a("Before eglCreateContext", egl10);
        EGLContext eglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{b, 2, 12344});
        a("After eglCreateContext", egl10);
        return eglCreateContext;
    }

    private static void a(String str, EGL10 egl10) {
        while (egl10.eglGetError() != e.ap) {
            bj.c("GLContextHelper", String.format("%s: EGL error: 0x%x", new Object[]{str, Integer.valueOf(egl10.eglGetError())}), new Object[0]);
        }
    }
}
