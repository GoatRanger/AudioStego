package dji.pilot.set.view.a;

import android.util.Log;
import dji.midware.data.config.P3.DeviceType;

public class b {
    private static final String f = "FirmwareVersion";
    public String a = null;
    public DeviceType b = null;
    public int c = -1;
    public long d = -1;
    public String e = null;

    public b(String str, String str2) {
        a(str);
        b(str2);
    }

    public b(String str) {
        a(str);
    }

    public void a(String str) {
        this.a = str;
        this.b = DeviceType.find(Integer.valueOf(str.substring(0, 2)).intValue());
        this.c = Integer.valueOf(str.substring(2, 4)).intValue();
    }

    public void b(String str) {
        this.e = str;
        String[] split = str.split("\\.");
        if (split.length != 4) {
            Log.d("TAG", str);
        }
        if (this.b == DeviceType.CAMERA) {
            this.d = (long) (Integer.valueOf(split[2] + split[3]).intValue() + ((((Integer.valueOf(split[0]).intValue() * 256) * 256) * 256) + ((Integer.valueOf(split[1]).intValue() * 256) * 256)));
            return;
        }
        this.d = (long) (Integer.valueOf(split[3]).intValue() + (((((Integer.valueOf(split[0]).intValue() * 256) * 256) * 256) + ((Integer.valueOf(split[1]).intValue() * 256) * 256)) + (Integer.valueOf(split[2]).intValue() * 256)));
    }

    public String toString() {
        return this.a + ":" + this.e;
    }
}
