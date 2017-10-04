package com.here.android.mpa.mapping.customization;

import android.util.SparseArray;
import com.nokia.maps.CustomizableSchemeImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;

@HybridPlus
public class CustomizableScheme {
    private CustomizableSchemeImpl a;

    @HybridPlus
    public enum ErrorCode {
        ERROR_NONE(0),
        ERROR_UNKNOWN(1),
        ERROR_OUT_OF_MEMORY(2),
        ERROR_INVALID_PARAMETERS(3),
        ERROR_INVALID_OPERATION(4);
        
        private static SparseArray<ErrorCode> b;
        int a;

        static {
            b = new SparseArray();
            ErrorCode[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                ErrorCode errorCode = values[i];
                b.put(errorCode.a, errorCode);
                i++;
            }
        }

        private ErrorCode(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        public static ErrorCode getCode(int i) {
            return (ErrorCode) b.get(i);
        }
    }

    private CustomizableScheme() {
    }

    @HybridPlusNative
    private CustomizableScheme(CustomizableSchemeImpl customizableSchemeImpl) {
        this.a = customizableSchemeImpl;
    }

    public boolean isValid() {
        return this.a.isValidNative();
    }

    public String getName() {
        return this.a.getNameNative();
    }

    public ErrorCode setVariableValue(SchemeColorProperty schemeColorProperty, int i, ZoomRange zoomRange) {
        return this.a.a(schemeColorProperty, i, zoomRange);
    }

    public int getVariableValue(SchemeColorProperty schemeColorProperty, double d) {
        return this.a.a(schemeColorProperty, d);
    }

    public ErrorCode setVariableValue(b bVar, a aVar, ZoomRange zoomRange) {
        return this.a.a(bVar, aVar, zoomRange);
    }

    public a getVariableValue(b bVar, double d) {
        return this.a.a(bVar, d);
    }

    public ErrorCode setVariableValue(SchemeFloatProperty schemeFloatProperty, float f, ZoomRange zoomRange) {
        return this.a.a(schemeFloatProperty, f, zoomRange);
    }

    public float getVariableValue(SchemeFloatProperty schemeFloatProperty, double d) {
        return this.a.a(schemeFloatProperty, d);
    }

    public ErrorCode setVariableValue(SchemeIntegerProperty schemeIntegerProperty, int i, ZoomRange zoomRange) {
        return this.a.a(schemeIntegerProperty, i, zoomRange);
    }

    public int getVariableValue(SchemeIntegerProperty schemeIntegerProperty, double d) {
        return this.a.a(schemeIntegerProperty, d);
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CustomizableScheme customizableScheme = (CustomizableScheme) obj;
        if (this.a == null) {
            if (customizableScheme.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(customizableScheme.a)) {
            return true;
        } else {
            return false;
        }
    }

    static {
        CustomizableSchemeImpl.a(new k<CustomizableScheme, CustomizableSchemeImpl>() {
            public CustomizableSchemeImpl a(CustomizableScheme customizableScheme) {
                return customizableScheme.a;
            }
        }, new am<CustomizableScheme, CustomizableSchemeImpl>() {
            public CustomizableScheme a(CustomizableSchemeImpl customizableSchemeImpl) {
                if (customizableSchemeImpl != null) {
                    return new CustomizableScheme(customizableSchemeImpl);
                }
                return null;
            }
        });
    }
}
