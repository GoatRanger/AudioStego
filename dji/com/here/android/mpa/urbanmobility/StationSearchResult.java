package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.av;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;
import java.util.List;

@HybridPlus
public final class StationSearchResult {
    private av a;

    private StationSearchResult(av avVar) {
        if (avVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = avVar;
    }

    public List<Station> getStations() {
        return this.a.a();
    }

    public Collection<Line> getLines() {
        return this.a.b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((StationSearchResult) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        av.a(new am<StationSearchResult, av>() {
            public StationSearchResult a(av avVar) {
                return new StationSearchResult(avVar);
            }
        });
    }
}
