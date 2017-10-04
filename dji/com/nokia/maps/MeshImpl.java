package com.nokia.maps;

import com.here.android.mpa.mapping.Mesh;
import com.nokia.maps.annotation.Online;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@Online
public class MeshImpl extends BaseNativeObject {
    private static k b = null;
    protected int a;

    private native float[] getTextureCoordinates(int i);

    private native int[] getVertexIndices(int i);

    private native boolean isValidNative(int i);

    private native void setTextureCoordinates(float[] fArr, int i);

    private native void setVertexIndices(int[] iArr, int i);

    static MeshImpl a(Mesh mesh) {
        if (b != null) {
            return (MeshImpl) b.a(mesh);
        }
        return null;
    }

    public static void a(k kVar) {
        b = kVar;
    }

    protected MeshImpl() {
    }

    public void a(IntBuffer intBuffer) {
        if (intBuffer.capacity() == 0) {
            throw new IllegalArgumentException("Input parameter IntBuffer contains no vertex index.");
        } else if (intBuffer.capacity() % 3 != 0) {
            throw new IllegalArgumentException("Vertex Indices must be in sets of 3");
        } else {
            setVertexIndices(intBuffer.array(), this.a);
        }
    }

    public IntBuffer b() {
        return IntBuffer.wrap(getVertexIndices(this.a));
    }

    public void b(FloatBuffer floatBuffer) {
        if (floatBuffer.capacity() == 0) {
            throw new IllegalArgumentException("Input parameter FloatBuffer contains no texture coordinate.");
        } else if (floatBuffer.capacity() % 2 != 0) {
            throw new IllegalArgumentException("Texture Coordinates must be in sets of 2");
        } else {
            setTextureCoordinates(floatBuffer.array(), this.a);
        }
    }

    @Online
    public FloatBuffer c() {
        return FloatBuffer.wrap(getTextureCoordinates(this.a));
    }

    public boolean d() {
        return isValidNative(this.a);
    }
}
