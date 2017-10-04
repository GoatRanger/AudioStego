package com.nokia.maps;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.AttributeSet;

public class v extends GLSurfaceView {
    private static String a = "Nokia GL Surface View";

    public v(Context context) {
        super(context);
        a(context);
    }

    public v(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    protected void a(Context context) {
        if (Build.MODEL.equalsIgnoreCase("SDK")) {
            setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        } else {
            setEGLContextClientVersion(2);
            setEGLConfigChooser(new at(context));
        }
        try {
            getClass().getMethod("setPreserveEGLContextOnPause", new Class[]{Boolean.TYPE}).invoke(this, new Object[]{Boolean.valueOf(true)});
        } catch (NoSuchMethodException e) {
            bj.e(a, "Unable to get method. Probably older SDK.", new Object[0]);
        } catch (Exception e2) {
            bj.b(a, "Reflection error!", new Object[0]);
        }
    }
}
