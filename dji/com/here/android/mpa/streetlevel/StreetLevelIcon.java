package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.nokia.maps.PanoramaIcon;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;

public final class StreetLevelIcon extends StreetLevelIconBase {
    private PanoramaIcon a;

    public StreetLevelIcon(GeoCoordinate geoCoordinate, Image image) {
        this(new PanoramaIcon(geoCoordinate, image));
    }

    @HybridPlusNative
    private StreetLevelIcon(PanoramaIcon panoramaIcon) {
        super(panoramaIcon);
        this.a = panoramaIcon;
    }

    public StreetLevelIcon setSize(StreetLevelIconSize streetLevelIconSize) {
        this.a.a(streetLevelIconSize);
        return this;
    }

    public StreetLevelIconSize getSize() {
        return this.a.b();
    }

    public StreetLevelIcon setAttachmentIdentifier(Identifier identifier) {
        this.a.a(identifier);
        return this;
    }

    public Identifier getAttachmentIdentifier() {
        return this.a.e();
    }

    static {
        PanoramaIcon.a(new k<StreetLevelIcon, PanoramaIcon>() {
            public PanoramaIcon a(StreetLevelIcon streetLevelIcon) {
                return streetLevelIcon.a;
            }
        }, new am<StreetLevelIcon, PanoramaIcon>() {
            public StreetLevelIcon a(PanoramaIcon panoramaIcon) {
                if (panoramaIcon != null) {
                    return new StreetLevelIcon(panoramaIcon);
                }
                return null;
            }
        });
    }
}
