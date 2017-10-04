package com.here.android.mpa.ar;

import com.here.android.mpa.mapping.MapPolyline.CapStyle;
import com.nokia.maps.LineAttributesImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class LineAttributes {
    protected LineAttributesImpl a;

    private LineAttributes(LineAttributesImpl lineAttributesImpl) {
        this.a = lineAttributesImpl;
    }

    public LineAttributes() {
        this.a = new LineAttributesImpl();
    }

    public LineAttributes(int i, int i2, CapStyle capStyle, boolean z) {
        this.a = new LineAttributesImpl(i, i2, capStyle, z);
    }

    public LineAttributes setLineColor(int i) {
        this.a.a(i);
        return this;
    }

    public int getLineColor() {
        return this.a.a();
    }

    public LineAttributes setLineWidth(int i) {
        this.a.b(i);
        return this;
    }

    public int getLineWidth() {
        return this.a.getLineWidthNative();
    }

    public boolean isDashEnabled() {
        return this.a.isDashEnabledNative();
    }

    public LineAttributes setDashEnabled(boolean z) {
        this.a.setDashEnabledNative(z);
        return this;
    }

    public int getDashPrimaryLength() {
        return this.a.getDashPrimaryLengthNative();
    }

    public LineAttributes setDashPrimaryLength(int i) {
        this.a.c(i);
        return this;
    }

    public int getDashSecondaryLength() {
        return this.a.getDashSecondaryLengthNative();
    }

    public LineAttributes setDashSecondaryLength(int i) {
        this.a.d(i);
        return this;
    }

    public LineAttributes setCapStyle(CapStyle capStyle) {
        this.a.setLineCapStyleNative(capStyle.value());
        return this;
    }

    public CapStyle getCapStyle() {
        return CapStyle.toCapStyle(this.a.getLineCapStyleNative());
    }

    public LineAttributes enablePerspective(boolean z) {
        this.a.enablePerspective(z);
        return this;
    }

    public boolean isPerspectiveEnable() {
        return this.a.isPerspectiveEnable();
    }

    static {
        LineAttributesImpl.a(new k<LineAttributes, LineAttributesImpl>() {
            public LineAttributesImpl a(LineAttributes lineAttributes) {
                return lineAttributes.a;
            }
        }, new am<LineAttributes, LineAttributesImpl>() {
            public LineAttributes a(LineAttributesImpl lineAttributesImpl) {
                return new LineAttributes(lineAttributesImpl);
            }
        });
    }
}
