package dji.pilot.fpv.model;

import android.util.Log;
import dji.midware.data.manager.P3.n;
import java.util.ArrayList;
import java.util.List;

public class e extends n {
    private List<a> a = new ArrayList();

    public class a {
        public int a;
        public int b;
        public int[] c = new int[4];
        final /* synthetic */ e d;

        public a(e eVar) {
            this.d = eVar;
        }
    }

    public e(boolean z) {
        super(z);
    }

    public List<a> a() {
        return this.a;
    }

    public void clear() {
        this.a.clear();
    }

    public void a(int i, int i2, String str) {
        a aVar = new a(this);
        aVar.a = i;
        aVar.b = i2;
        String[] split = str.split("\\.");
        int i3 = 0;
        while (i3 < 4 && i3 < split.length) {
            if (split[i3].equals("")) {
                aVar.c[i3] = 0;
            } else {
                try {
                    aVar.c[i3] = Integer.parseInt(split[i3].trim());
                } catch (Exception e) {
                    e.printStackTrace();
                    aVar.c[i3] = 0;
                }
            }
            i3++;
        }
        this.a.add(aVar);
    }

    protected void doPack() {
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        if (bArr.length % 6 != 0) {
            Log.d("FlightRecord", "Firmware data length:" + bArr.length);
            return;
        }
        int i = 0;
        while (i < bArr.length) {
            a aVar = new a(this);
            aVar.a = bArr[i];
            i++;
            aVar.b = bArr[i];
            i++;
            int i2 = 0;
            while (i2 < aVar.c.length) {
                aVar.c[i2] = bArr[i];
                i2++;
                i++;
            }
            this.a.add(aVar);
        }
    }

    public byte[] getRecData() {
        byte[] bArr = new byte[(this.a.size() * 6)];
        int i = 0;
        for (a aVar : this.a) {
            bArr[i] = (byte) aVar.a;
            i++;
            bArr[i] = (byte) aVar.b;
            i++;
            int i2 = 0;
            while (i2 < aVar.c.length) {
                bArr[i] = (byte) aVar.c[i2];
                i2++;
                i++;
            }
        }
        return bArr;
    }
}
