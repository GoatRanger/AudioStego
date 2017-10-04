package com.here.android.mpa.mapping;

import com.nokia.maps.LocalMeshImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.nio.FloatBuffer;

public final class LocalMesh extends Mesh {
    private LocalMeshImpl a;

    @Online
    public LocalMesh() {
        this(new LocalMeshImpl());
    }

    private LocalMesh(LocalMeshImpl localMeshImpl) {
        super(localMeshImpl);
        this.a = localMeshImpl;
    }

    @Online
    public LocalMesh setVertices(FloatBuffer floatBuffer) {
        this.a.a(floatBuffer);
        return this;
    }

    @Online
    public FloatBuffer getVertices() {
        return this.a.a();
    }

    static {
        LocalMeshImpl.a(new am<LocalMesh, LocalMeshImpl>() {
            public LocalMesh a(LocalMeshImpl localMeshImpl) {
                return new LocalMesh(localMeshImpl);
            }
        });
    }
}
