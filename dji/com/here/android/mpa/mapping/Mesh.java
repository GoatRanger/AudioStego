package com.here.android.mpa.mapping;

import com.nokia.maps.MeshImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class Mesh {
    private MeshImpl a;

    protected Mesh(MeshImpl meshImpl) {
        this.a = meshImpl;
    }

    @Online
    public Mesh setVertexIndices(IntBuffer intBuffer) {
        this.a.a(intBuffer);
        return this;
    }

    @Online
    public IntBuffer getVertexIndices() {
        return this.a.b();
    }

    @Online
    public Mesh setTextureCoordinates(FloatBuffer floatBuffer) {
        this.a.b(floatBuffer);
        return this;
    }

    @Online
    public FloatBuffer getTextureCoordinates() {
        return this.a.c();
    }

    static {
        MeshImpl.a(new k<Mesh, MeshImpl>() {
            public MeshImpl a(Mesh mesh) {
                return mesh.a;
            }
        });
    }
}
