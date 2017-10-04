package com.here.android.mpa.search;

import android.util.Pair;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.PlacesTilesRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.k;
import java.util.List;

public class f extends Request<e> {
    protected CategoryFilter a = null;
    private List<Pair<Integer, Integer>> b;

    @Internal
    public /* synthetic */ Request addReference(String str) {
        return a(str);
    }

    @Internal
    public /* synthetic */ Request setCollectionSize(int i) {
        return a(i);
    }

    f(PlacesTilesRequest placesTilesRequest) {
        super(placesTilesRequest);
    }

    @Internal
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Internal
    public f a(int i) {
        return (f) super.setCollectionSize(i);
    }

    @Internal
    public f a(String str) {
        return (f) super.addReference(str);
    }

    @Internal
    public List<String> getReferences() {
        return super.getReferences();
    }

    @Internal
    public ErrorCode execute(ResultListener<e> resultListener) {
        a();
        this.f = PlacesApi.a().b();
        if (this.a != null) {
            String categoryFilter = this.a.toString();
            if (!(categoryFilter == null || categoryFilter.isEmpty())) {
                this.f.a("cat", categoryFilter);
            }
            if (this.b != null) {
                for (Pair pair : this.b) {
                    this.f.a(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue());
                }
            }
        }
        return super.execute(resultListener);
    }

    static {
        PlacesTilesRequest.a(new k<f, PlacesTilesRequest>() {
            public PlacesTilesRequest a(f fVar) {
                return (PlacesTilesRequest) fVar.f;
            }
        }, new am<f, PlacesTilesRequest>() {
            public f a(PlacesTilesRequest placesTilesRequest) {
                return placesTilesRequest != null ? new f(placesTilesRequest) : null;
            }
        });
    }
}
