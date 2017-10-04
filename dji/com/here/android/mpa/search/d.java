package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.dm;
import com.nokia.maps.k;
import java.util.List;

@Internal
public class d extends Request<String> {
    @Internal
    public /* synthetic */ Request addReference(String str) {
        return a(str);
    }

    @Internal
    public /* synthetic */ Request setCollectionSize(int i) {
        return a(i);
    }

    @Internal
    public /* synthetic */ Request setMapViewport(GeoBoundingBox geoBoundingBox) {
        return a(geoBoundingBox);
    }

    d(dm dmVar) {
        super(dmVar);
    }

    @Internal
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Internal
    public d a(int i) {
        return (d) super.setCollectionSize(i);
    }

    @Internal
    public d a(String str) {
        return (d) super.addReference(str);
    }

    @Internal
    public List<String> getReferences() {
        return super.getReferences();
    }

    @Internal
    public d a(GeoBoundingBox geoBoundingBox) {
        return (d) super.setMapViewport(geoBoundingBox);
    }

    static {
        dm.a(new k<d, dm>() {
            public dm a(d dVar) {
                return (dm) dVar.f;
            }
        }, new am<d, dm>() {
            public d a(dm dmVar) {
                return dmVar != null ? new d(dmVar) : null;
            }
        });
    }
}
