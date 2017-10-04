package com.nokia.maps;

import android.graphics.Bitmap;
import android.view.View;
import com.here.android.mpa.ar.ARObject.IconType;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.ar.a;

public class f extends ARObjectImpl {
    private a d = new a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            ImageImpl a = this.a.a(obj2);
            if (a != null) {
                this.a.setIcon(IconType.FRONT.ordinal(), a);
            }
            return false;
        }
    };
    private a e = new a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            ImageImpl a = this.a.a(obj2);
            if (a != null) {
                this.a.setIcon(IconType.BACK.ordinal(), a);
            }
            return false;
        }
    };
    private a f = new a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            ImageImpl a = this.a.a(obj2);
            if (a != null) {
                this.a.setIcon(IconType.DOWN.ordinal(), a);
            }
            return false;
        }
    };

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap) {
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap, Image image) {
        super(image);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap, int i) {
        super(i);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap, Image image, Image image2, Image image3) {
        super(image, image2, image3);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap, int i, int i2, int i3) {
        super(i, i2, i3);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, Bitmap bitmap, String str, String str2, String str3) {
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(bitmap)));
        b(geoCoordinate);
        if (str != null) {
            a(str, this.d);
        }
        if (str2 != null) {
            c(str2, this.f);
        }
        if (str3 != null) {
            b(str3, this.e);
        }
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view) {
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view, Image image) {
        super(image);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view, int i) {
        super(i);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view, Image image, Image image2, Image image3) {
        super(image, image2, image3);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view, int i, int i2, int i3) {
        super(i, i2, i3);
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    public f(GeoCoordinate geoCoordinate, View view, String str, String str2, String str3) {
        setIcon(IconType.INFO.ordinal(), ImageImpl.a(bc.a(view)));
        b(geoCoordinate);
        if (str != null) {
            a(str, this.d);
        }
        if (str2 != null) {
            c(str2, this.f);
        }
        if (str3 != null) {
            b(str3, this.e);
        }
        setCoordinate(GeoCoordinateImpl.get(geoCoordinate));
    }

    private void b(GeoCoordinate geoCoordinate) {
        if (geoCoordinate == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }
    }

    private ImageImpl a(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return null;
        }
        a aVar = (a) obj;
        if (aVar.a == null || aVar.b <= 0 || aVar.c <= 0) {
            return null;
        }
        ImageImpl a = ImageImpl.a(new Image());
        a.a(aVar.a, aVar.b, aVar.c);
        return a;
    }
}
