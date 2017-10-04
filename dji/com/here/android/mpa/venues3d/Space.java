package com.here.android.mpa.venues3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.HashMap;
import java.util.Map;

public final class Space extends Area {
    private static Map<String, Bitmap> c = new HashMap();
    Content a = null;
    String b = null;

    @HybridPlus
    public enum SpaceType {
        SPACE,
        FACILITY
    }

    @HybridPlusNative
    private native Content getContentNative();

    @HybridPlusNative
    private native String getIconUriNative();

    @HybridPlusNative
    private native int getSpaceTypeNative();

    @HybridPlus
    public native int getFloorNumber();

    @HybridPlus
    public native String getFloorSynonym();

    @HybridPlus
    public native String getVenueName();

    @HybridPlusNative
    private Space(int i) {
        super(i);
    }

    private static Bitmap a(Context context, Uri uri) {
        String uri2 = uri.toString();
        if (!c.containsKey(uri2)) {
            Options options = new Options();
            options.inScaled = true;
            options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
            options.inDensity = context.getResources().getDisplayMetrics().densityDpi;
            Bitmap decodeFile = BitmapFactory.decodeFile(uri2, options);
            if (decodeFile != null) {
                c.put(uri2, decodeFile);
            }
        }
        return (Bitmap) c.get(uri2);
    }

    @HybridPlus
    public Content getContent() {
        if (this.a == null) {
            this.a = getContentNative();
        }
        return this.a;
    }

    @HybridPlus
    public SpaceType getType() {
        return SpaceType.values()[getSpaceTypeNative()];
    }

    @HybridPlus
    public Bitmap getIcon(Context context) {
        if (this.b == null) {
            this.b = getIconUriNative();
        }
        Uri parse = this.b.isEmpty() ? null : Uri.parse(this.b);
        if (parse != null) {
            return a(context, parse);
        }
        return null;
    }
}
