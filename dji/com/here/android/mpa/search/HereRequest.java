package com.here.android.mpa.search;

import android.text.TextUtils;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HereRequest extends DiscoveryRequest {
    private List<String> m;
    private Pattern n;

    @Online
    public static class UnsupportedFilterException extends Exception {
        public UnsupportedFilterException(String str) {
            super(str);
        }
    }

    @Online
    public HereRequest() {
        this.m = null;
        this.n = null;
        this.m = new ArrayList();
        this.n = Pattern.compile("(\\w+) (\\w+) \\|(\\w+)");
    }

    @Online
    public HereRequest setSearchCenter(GeoCoordinate geoCoordinate) {
        return (HereRequest) super.setSearchCenter(geoCoordinate);
    }

    @Online
    public HereRequest setCategoryFilter(CategoryFilter categoryFilter) {
        return (HereRequest) super.setCategoryFilter(categoryFilter);
    }

    @Online
    public HereRequest addBuildingFilter(Identifier identifier) throws UnsupportedFilterException {
        dy.a((Object) identifier, "Building identifier is null");
        Matcher matcher = this.n.matcher(identifier.toString());
        if (matcher.find()) {
            CharSequence group = matcher.group(3);
            if (!TextUtils.isEmpty(group)) {
                this.m.add(group);
                return this;
            }
        }
        throw new UnsupportedFilterException("Identifier does not contain a building ID");
    }

    @Online
    public HereRequest addBuildingFilter(String str) {
        dy.a((Object) str, "Building ID is null");
        dy.a(!str.isEmpty(), "Building ID is empty");
        this.m.add(str);
        return this;
    }

    @Online
    public ErrorCode execute(ResultListener<DiscoveryResultPage> resultListener) {
        String categoryFilter;
        a();
        this.f = PlacesApi.a().b(this.l);
        if (this.a != null) {
            this.f.a("at", this.a.getLatitude() + "," + this.a.getLongitude());
        }
        if (this.d != null) {
            categoryFilter = this.d.toString();
            if (!(categoryFilter == null || categoryFilter.isEmpty())) {
                this.f.a("cat", categoryFilter);
            }
        }
        if (!(this.m == null || this.m.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String categoryFilter2 : this.m) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(categoryFilter2);
            }
            this.f.a(Request.BUILDING_ID_REFERENCE_NAME, stringBuilder.toString());
        }
        return super.execute(resultListener);
    }
}
