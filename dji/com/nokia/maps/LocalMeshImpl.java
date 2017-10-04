package com.nokia.maps;

import com.here.android.mpa.mapping.LocalMesh;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.nio.FloatBuffer;

@Online
public class LocalMeshImpl extends MeshImpl {
    private static am<LocalMesh, LocalMeshImpl> b;

    private native void createNative();

    private native void destroyNative();

    private native float[] getVerticesNative();

    private static native LocalMeshImpl loadObjFileNative(String str);

    private native void setVerticesNative(float[] fArr);

    public static void a(am<LocalMesh, LocalMeshImpl> amVar) {
        b = amVar;
    }

    static LocalMesh a(LocalMeshImpl localMeshImpl) {
        if (localMeshImpl != null) {
            return (LocalMesh) b.a(localMeshImpl);
        }
        return null;
    }

    static {
        ce.a(LocalMesh.class);
    }

    @OnlineNative
    private LocalMeshImpl(int i) {
        this.nativeptr = i;
        this.a = 0;
    }

    public LocalMeshImpl() {
        createNative();
        this.a = 0;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }

    public void a(FloatBuffer floatBuffer) {
        if (floatBuffer.capacity() == 0) {
            throw new IllegalArgumentException("Input vertices is empty");
        } else if (floatBuffer.capacity() % 3 != 0) {
            throw new IllegalArgumentException("Input vertices should contain float triplets.");
        } else if (floatBuffer.capacity() / 3 > 65536) {
            throw new RuntimeException("Maximum number of vertices is 65536");
        } else {
            setVerticesNative(floatBuffer.array());
        }
    }

    public FloatBuffer a() {
        return FloatBuffer.wrap(getVerticesNative());
    }
}
