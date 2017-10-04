package dji.midware.a;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.msgpack.core.MessagePack.Code;

public class d {
    public static final int a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 99;
    private static final byte[] h = new byte[]{(byte) -86, (byte) -69};
    private static final byte[] i = new byte[]{Code.UINT8, Code.ARRAY32};
    public int f;
    public int g;
    private HashMap<Integer, byte[]> j = new HashMap();

    public class a implements Serializable {
        public int a;
        public int b;
        public byte[] c;
        final /* synthetic */ d d;

        public a(d dVar) {
            this.d = dVar;
        }
    }

    public enum b {
        DJIBaseCommData_Test(0),
        DJIBaseCommData_Who(1),
        DJIBaseCommData_Event(2),
        DJIBaseCommData_Data(3);
        
        private int e;

        private b(int i) {
            this.e = 0;
            this.e = i;
        }

        public int a() {
            return this.e;
        }
    }

    public ArrayList<Byte> a() {
        ArrayList<Byte> arrayList = new ArrayList();
        for (Entry entry : this.j.entrySet()) {
            int length;
            Object key = entry.getKey();
            byte[] bArr = (byte[]) entry.getValue();
            a aVar = new a(this);
            aVar.a = ((Integer) key).intValue();
            aVar.b = bArr.length;
            aVar.c = bArr;
            byte[] array = ByteBuffer.allocate(4).putInt(aVar.a).array();
            for (length = array.length - 1; length >= 0; length--) {
                arrayList.add(Byte.valueOf(array[length]));
            }
            byte[] array2 = ByteBuffer.allocate(4).putInt(aVar.b).array();
            for (length = array2.length - 1; length >= 0; length--) {
                arrayList.add(Byte.valueOf(array2[length]));
            }
            for (length = bArr.length - 1; length >= 0; length--) {
                arrayList.add(Byte.valueOf(bArr[length]));
            }
        }
        return arrayList;
    }

    public int a(b bVar) {
        int i = 0;
        byte[] bArr = (byte[]) this.j.get(bVar);
        int i2 = 0;
        while (i < bArr.length) {
            i2 |= (bArr[i] << (((bArr.length - 1) - i) * 8)) & (255 << (((bArr.length - 1) - i) * 8));
            i++;
        }
        return i2;
    }

    public byte[] a(int i) {
        return (byte[]) this.j.get(Integer.valueOf(i));
    }

    public void a(int i, int i2) {
        byte[] bArr = new byte[4];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr[i3] = (byte) ((i >> (((bArr.length - 1) - i3) * 8)) & 255);
        }
        a(bArr, i2);
    }

    public void a(byte[] bArr, int i) {
        this.j.put(Integer.valueOf(i), bArr);
    }

    private byte[] a(Byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return bArr2;
    }

    public byte[] b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(h[0]));
        arrayList.add(Byte.valueOf(h[1]));
        ArrayList a = a();
        short length = (short) a.toArray().length;
        arrayList.add(Byte.valueOf((byte) (length & 255)));
        arrayList.add(Byte.valueOf((byte) ((length >> 8) & 255)));
        Byte[] bArr = (Byte[]) a.toArray(new Byte[a.size()]);
        for (Byte byteValue : bArr) {
            arrayList.add(Byte.valueOf(byteValue.byteValue()));
        }
        arrayList.add(Byte.valueOf(i[0]));
        arrayList.add(Byte.valueOf(i[1]));
        return a((Byte[]) arrayList.toArray(new Byte[arrayList.size()]));
    }

    public boolean b(byte[] bArr, int i) {
        if (bArr == null || i != 30) {
            return false;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        if (b != h[0] || b2 != h[1] || bArr[i - 2] != i[0] || bArr[i - 1] != i[1]) {
            return false;
        }
        r6 = new int[2];
        int[] iArr = new int[]{bArr[4], bArr[16]};
        iArr[0] = bArr[12];
        iArr[1] = bArr[24];
        if (r6[0] == b.DJIBaseCommData_Who.ordinal()) {
            this.f = iArr[0];
        } else if (r6[0] == b.DJIBaseCommData_Event.ordinal()) {
            this.g = iArr[0];
        }
        if (r6[1] == b.DJIBaseCommData_Who.ordinal()) {
            this.f = iArr[1];
            return true;
        } else if (r6[1] != b.DJIBaseCommData_Event.ordinal()) {
            return true;
        } else {
            this.g = iArr[1];
            return true;
        }
    }
}
