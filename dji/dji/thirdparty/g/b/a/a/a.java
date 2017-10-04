package dji.thirdparty.g.b.a.a;

import dji.thirdparty.g.b.b.c.h;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class a extends dji.thirdparty.g.a.c implements dji.thirdparty.g.b.a.a {

    public static class a extends dji.thirdparty.g.f {
        public a(String str) {
            super(str);
        }
    }

    private static abstract class b {
        protected abstract void a(OutputStream outputStream) throws IOException;

        private b() {
        }
    }

    private static class c extends b {
        public final byte[] a;
        public final byte[] b;
        public final InputStream c;

        public c(byte[] bArr, byte[] bArr2) {
            super();
            this.a = bArr;
            this.b = bArr2;
            this.c = null;
        }

        public c(byte[] bArr, InputStream inputStream) {
            super();
            this.a = bArr;
            this.b = null;
            this.c = inputStream;
        }

        protected void a(OutputStream outputStream) throws IOException {
            outputStream.write(this.a);
            if (this.b != null) {
                outputStream.write(this.b);
                return;
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int read = this.c.read(bArr);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    try {
                        this.c.close();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
            }
        }
    }

    private static class d extends b {
        public final int a;
        public final byte[] b;
        public final byte[] c;
        public final byte[] d;

        public d(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super();
            this.a = i;
            this.b = bArr;
            this.c = bArr2;
            this.d = bArr3;
        }

        protected void a(OutputStream outputStream) throws IOException {
            outputStream.write(this.b);
            outputStream.write(this.c);
            outputStream.write(this.d);
        }
    }

    private static class e extends d {
        public e(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            super(i, bArr, bArr2, bArr3);
        }
    }

    private static class f {
        public final List a;
        public final List b;

        public f(List list, List list2) {
            this.a = list;
            this.b = list2;
        }
    }

    public a() {
        a(77);
    }

    public a(int i) {
        a(i);
    }

    private f a(dji.thirdparty.g.a.a.a aVar) throws dji.thirdparty.g.e, IOException {
        final List arrayList = new ArrayList();
        final List arrayList2 = new ArrayList();
        new dji.thirdparty.g.b.a.d().a(aVar, new dji.thirdparty.g.b.a.d.a(this) {
            final /* synthetic */ a c;

            public boolean a() {
                return true;
            }

            public void a(int i, byte[] bArr, byte[] bArr2) {
                arrayList.add(new c(bArr, bArr2));
            }

            public boolean a(int i, byte[] bArr, InputStream inputStream) {
                arrayList.add(new c(bArr, inputStream));
                return true;
            }

            public boolean a(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws dji.thirdparty.g.e, IOException {
                if (i != dji.thirdparty.g.b.a.a.k) {
                    arrayList.add(new d(i, bArr, bArr2, bArr3));
                } else if (dji.thirdparty.g.a.c.d(bArr3, dji.thirdparty.g.b.a.a.fr_)) {
                    e eVar = new e(i, bArr, bArr2, bArr3);
                    arrayList.add(eVar);
                    arrayList2.add(eVar);
                } else {
                    arrayList.add(new d(i, bArr, bArr2, bArr3));
                }
                return true;
            }
        });
        return new f(arrayList, arrayList2);
    }

    public void a(File file, OutputStream outputStream) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.c(file), outputStream);
    }

    public void a(byte[] bArr, OutputStream outputStream) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.b(bArr), outputStream);
    }

    public void b(InputStream inputStream, OutputStream outputStream) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.d(inputStream, null), outputStream);
    }

    public void a(dji.thirdparty.g.a.a.a aVar, OutputStream outputStream) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(outputStream, a(aVar).a, null);
    }

    public void a(File file, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.c(file), outputStream, hVar);
    }

    public void a(byte[] bArr, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.b(bArr), outputStream, hVar);
    }

    public void a(InputStream inputStream, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(new dji.thirdparty.g.a.a.d(inputStream, null), outputStream, hVar);
    }

    public void a(dji.thirdparty.g.a.a.a aVar, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        dji.thirdparty.g.b.b.c.b cVar;
        f a = a(aVar);
        List list = a.a;
        if (a.b.size() > 0) {
            cVar = new dji.thirdparty.g.b.b.c.c(hVar.j, d("trimmed exif bytes", ((d) a.b.get(0)).d, 6));
        } else {
            cVar = new dji.thirdparty.g.b.b.c.d(hVar.j);
        }
        a(outputStream, list, a(cVar, hVar, true));
    }

    public void b(byte[] bArr, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        b(new dji.thirdparty.g.a.a.b(bArr), outputStream, hVar);
    }

    public void b(InputStream inputStream, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        b(new dji.thirdparty.g.a.a.d(inputStream, null), outputStream, hVar);
    }

    public void b(File file, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        b(new dji.thirdparty.g.a.a.c(file), outputStream, hVar);
    }

    public void b(dji.thirdparty.g.a.a.a aVar, OutputStream outputStream, h hVar) throws dji.thirdparty.g.e, IOException, dji.thirdparty.g.f {
        a(outputStream, a(aVar).a, a(new dji.thirdparty.g.b.b.c.d(hVar.j), hVar, true));
    }

    private void a(OutputStream outputStream, List list, byte[] bArr) throws dji.thirdparty.g.f, IOException {
        int i = 0;
        int g = g();
        try {
            int i2;
            byte[] b;
            outputStream.write(ft_);
            int i3 = 0;
            int i4 = 0;
            while (i3 < list.size()) {
                if (((b) list.get(i3)) instanceof e) {
                    i2 = 1;
                } else {
                    i2 = i4;
                }
                i3++;
                i4 = i2;
            }
            if (i4 == 0 && bArr != null) {
                b = b((int) dji.thirdparty.g.b.a.a.k, g);
                if (bArr.length > 65535) {
                    throw new a("APP1 Segment is too long: " + bArr.length);
                }
                byte[] b2 = b(bArr.length + 2, g);
                if (((d) list.get(0)).a == 65504) {
                    list.add(0, new e(dji.thirdparty.g.b.a.a.k, b, b2, bArr));
                } else {
                    list.add(0, new e(dji.thirdparty.g.b.a.a.k, b, b2, bArr));
                }
            }
            i4 = 0;
            while (i < list.size()) {
                b bVar = (b) list.get(i);
                if (!(bVar instanceof e)) {
                    bVar.a(outputStream);
                    i2 = i4;
                } else if (i4 != 0) {
                    i2 = i4;
                } else if (bArr == null) {
                    i2 = 1;
                } else {
                    byte[] b3 = b((int) dji.thirdparty.g.b.a.a.k, g);
                    if (bArr.length > 65535) {
                        throw new a("APP1 Segment is too long: " + bArr.length);
                    }
                    b = b(bArr.length + 2, g);
                    outputStream.write(b3);
                    outputStream.write(b);
                    outputStream.write(bArr);
                    i2 = 1;
                }
                i++;
                i4 = i2;
            }
        } finally {
            try {
                outputStream.close();
            } catch (Throwable e) {
                dji.thirdparty.g.c.c.a(e);
            }
        }
    }

    private byte[] a(dji.thirdparty.g.b.b.c.b bVar, h hVar, boolean z) throws IOException, dji.thirdparty.g.f {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (z) {
            byteArrayOutputStream.write(fr_);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
        }
        bVar.a(byteArrayOutputStream, hVar);
        return byteArrayOutputStream.toByteArray();
    }
}
