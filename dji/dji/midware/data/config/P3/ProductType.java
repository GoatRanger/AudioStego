package dji.midware.data.config.P3;

import dji.sdksharedlib.hardware.abstractions.c.a;

public enum ProductType {
    None(0, "Unknown"),
    Orange(1, "Inspire 1"),
    litchiC(2, "P3 Standard", true),
    litchiS(3, "P3 Advanced"),
    litchiX(4, "P3 Professional"),
    Longan(5, "OSMO", true),
    N1(6, "M100"),
    Tomato(7, "Phantom 4"),
    Grape2(8, "LB2", false, false),
    BigBanana(9, "Inspire 1 Pro", false, true),
    A3(10, "DJI Device", false, false),
    PM820(11, "DJI Device", false, true),
    P34K(12, "P3 4K", true, true),
    KumquatX(13, a.l, false, true, false),
    Olives(14, a.j, false, true),
    OrangeRAW(15, "Inspire 1 Raw"),
    A2(16, "DJI Device", false, false),
    Orange2(17, "DJI Device", false, true),
    LonganPro(18, "OSMO Pro", true, true),
    LonganRaw(19, "OSMO Raw", true, true),
    KumquatS(21, "Mavic", false, true, false),
    OrangeCV600(23, a.k),
    Pomato(24, "Phantom 4 Pro"),
    LonganZoom(20, "OSMO+", true, true),
    LonganMobile(22, "OSMO Mobile", false, true),
    N3(25, "N3 FC", false, false),
    PM820PRO(27, "PM820 Pro", false, true),
    OTHER(100, "OTHER");
    
    private int data;
    private boolean isCompleteMachine;
    private boolean isFromWifi;
    private boolean isGDR;
    private String name;

    private ProductType(int i, String str) {
        this.isFromWifi = false;
        this.isCompleteMachine = true;
        this.isGDR = true;
        this.data = i;
        this.name = str;
    }

    private ProductType(int i, String str, boolean z) {
        this.isFromWifi = false;
        this.isCompleteMachine = true;
        this.isGDR = true;
        this.data = i;
        this.name = str;
        this.isFromWifi = z;
    }

    private ProductType(int i, String str, boolean z, boolean z2) {
        this.isFromWifi = false;
        this.isCompleteMachine = true;
        this.isGDR = true;
        this.data = i;
        this.name = str;
        this.isFromWifi = z;
        this.isCompleteMachine = z2;
    }

    private ProductType(int i, String str, boolean z, boolean z2, boolean z3) {
        this.isFromWifi = false;
        this.isCompleteMachine = true;
        this.isGDR = true;
        this.data = i;
        this.name = str;
        this.isFromWifi = z;
        this.isCompleteMachine = z2;
        this.isGDR = z3;
    }

    public int value() {
        return this.data;
    }

    public String _name() {
        return this.name;
    }

    public boolean isFromWifi() {
        return this.isFromWifi;
    }

    public boolean isGDR() {
        return this.isGDR;
    }

    public boolean isCompleteMachine() {
        return this.isCompleteMachine;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static ProductType find(int i) {
        ProductType productType = OTHER;
        ProductType[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2]._equals(i)) {
                return values[i2];
            }
        }
        return productType;
    }

    public String toString() {
        return super.toString();
    }

    public static boolean isValidType(ProductType productType) {
        return (productType == null || None == productType || OTHER == productType) ? false : true;
    }
}
