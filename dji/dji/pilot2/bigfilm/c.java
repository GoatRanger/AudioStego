package dji.pilot2.bigfilm;

public enum c {
    TransitionType_GradualChange(0),
    TransitionType_SpyChange(1);
    
    private int c;

    private c(int i) {
        this.c = i;
    }

    public int a() {
        return this.c;
    }

    public boolean a(int i) {
        return this.c == i;
    }

    public static c find(int i) {
        c[] values = values();
        for (int i2 = 0; i2 < values.length; i2++) {
            if (values[i2].a(i)) {
                return values[i2];
            }
        }
        return null;
    }

    public static c convert(String str) {
        if (str.equals("gradulchange")) {
            return TransitionType_GradualChange;
        }
        if (str.equals("spychange")) {
            return TransitionType_SpyChange;
        }
        return null;
    }

    public static float getInfluencePreSegDuration(c cVar, float f) {
        if (cVar == TransitionType_GradualChange) {
            return (-f) / 2.0f;
        }
        if (cVar == TransitionType_SpyChange) {
            return -f;
        }
        return 0.0f;
    }

    public static float getInfluenceNextSegDuration(c cVar, float f) {
        if (cVar == TransitionType_GradualChange) {
            return (-f) / 2.0f;
        }
        if (cVar == TransitionType_SpyChange) {
            return -f;
        }
        return 0.0f;
    }
}
