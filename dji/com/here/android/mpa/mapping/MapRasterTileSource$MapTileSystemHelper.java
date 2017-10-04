package com.here.android.mpa.mapping;

import com.nokia.maps.annotation.Online;

@Online
public final class MapRasterTileSource$MapTileSystemHelper {
    public static String tileXYToQuadKey(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        while (i3 > 0) {
            char c = '0';
            int i4 = 1 << (i3 - 1);
            if ((i & i4) != 0) {
                c = (char) 49;
            }
            if ((i4 & i2) != 0) {
                c = (char) (((char) (c + 1)) + 1);
            }
            stringBuilder.append(c);
            i3--;
        }
        return stringBuilder.toString();
    }
}
