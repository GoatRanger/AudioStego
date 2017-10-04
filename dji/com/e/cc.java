package com.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;

public class cc {
    private RandomAccessFile a;
    private bx b;
    private File c = null;

    protected cc(bx bxVar) {
        this.b = bxVar;
    }

    private int a(int i, int i2, int i3) {
        int i4 = ((i3 - 1) * 1500) + i;
        while (i4 >= i2) {
            i4 -= 1500;
        }
        return i4;
    }

    private int a(BitSet bitSet) {
        int i = 0;
        while (i < bitSet.length()) {
            try {
                if (bitSet.get(i)) {
                    return this.b.a() + ((i * 1500) + 4);
                }
                i++;
            } catch (Throwable th) {
                bc.a(th, "DataLoopReader", "getStartPointer");
                return 0;
            }
        }
        return 0;
    }

    private ArrayList<byte[]> a(int i, int i2) {
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i <= i2) {
            try {
                this.a.seek((long) i);
                int readInt = this.a.readInt();
                this.a.readLong();
                if (readInt <= 0 || readInt > 1500) {
                    return null;
                }
                byte[] bArr = new byte[readInt];
                this.a.read(bArr);
                byte a = a(bArr);
                if (a != (byte) 3 && a != (byte) 4 && a != (byte) 41) {
                    return null;
                }
                arrayList.add(bArr);
                i += 1500;
            } catch (IOException e) {
            } catch (Throwable th) {
                bc.a(th, "DataLoopReader", "getContents");
            }
        }
        return arrayList;
    }

    private BitSet b() {
        BitSet bitSet = null;
        try {
            byte[] bArr = new byte[this.b.a()];
            try {
                this.a.read(bArr);
                bitSet = cb.a(bArr);
            } catch (IOException e) {
            }
        } catch (Throwable th) {
            bc.a(th, "DataLoopReader", "getCurrentBitset");
        }
        return bitSet;
    }

    protected byte a(byte[] bArr) {
        byte[] bArr2;
        Throwable th;
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            bArr2 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th = th2;
                bc.a(th, "DataLoopReader", "getVersion");
                return bArr2[0];
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bArr2 = null;
            th = th4;
            bc.a(th, "DataLoopReader", "getVersion");
            return bArr2[0];
        }
        return bArr2[0];
    }

    protected int a() {
        int i = 0;
        synchronized (this) {
            int i2;
            try {
                this.c = this.b.b();
                if (this.c != null) {
                    this.a = new RandomAccessFile(this.b.b(), "rw");
                    byte[] bArr = new byte[this.b.a()];
                    if (bw.e && this.a != null) {
                        try {
                            this.a.close();
                            if (this.a != null) {
                                try {
                                    this.a.close();
                                } catch (Throwable th) {
                                }
                            }
                        } catch (IOException e) {
                        }
                    }
                    this.a.read(bArr);
                    BitSet a = cb.a(bArr);
                    for (i2 = 0; i2 < a.size(); i2++) {
                        if (a.get(i2)) {
                            i++;
                        }
                    }
                }
                if (this.a != null) {
                    try {
                        this.a.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
            }
            this.c = null;
        }
        return i;
        i = i2;
        this.c = null;
        return i;
    }

    protected synchronized co a(int i) {
        co coVar = null;
        synchronized (this) {
            co coVar2;
            try {
                if (this.b != null) {
                    synchronized (this) {
                        this.c = this.b.b();
                        if (this.c == null) {
                        } else {
                            this.a = new RandomAccessFile(this.c, "rw");
                            if (bw.e && this.a != null) {
                                try {
                                    this.a.close();
                                    if (this.a != null) {
                                        try {
                                            this.a.close();
                                        } catch (Throwable th) {
                                        }
                                    }
                                } catch (IOException e) {
                                }
                            }
                            BitSet b = b();
                            if (b == null) {
                                this.c.delete();
                                if (this.a != null) {
                                    try {
                                        this.a.close();
                                    } catch (Throwable th2) {
                                    }
                                }
                            } else {
                                int a = a(b);
                                ArrayList a2 = a(a, a(a, (int) this.c.length(), i));
                                if (a2 == null) {
                                    this.c.delete();
                                    if (this.a != null) {
                                        try {
                                            this.a.close();
                                        } catch (Throwable th3) {
                                        }
                                    }
                                } else {
                                    coVar2 = new co(this.c, a2, new int[]{((a - this.b.a()) - 4) / 1500, ((r2 - this.b.a()) - 4) / 1500});
                                    if (this.a != null) {
                                        try {
                                            this.a.close();
                                        } catch (Throwable th4) {
                                        }
                                    }
                                    if (coVar2 != null) {
                                        if (coVar2.c() > 100 && coVar2.c() < 5242880) {
                                            coVar = coVar2;
                                        }
                                    }
                                    this.c.delete();
                                    this.c = null;
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e2) {
                if (this.a != null) {
                    this.a.close();
                }
            } catch (Throwable th5) {
                bc.a(th5, "DataLoopReader", "get");
            }
        }
        return coVar;
        coVar2 = null;
        if (coVar2 != null) {
            coVar = coVar2;
            return coVar;
        }
        this.c.delete();
        this.c = null;
        return coVar;
        coVar2 = null;
        if (coVar2 != null) {
            coVar = coVar2;
            return coVar;
        }
        this.c.delete();
        this.c = null;
        return coVar;
    }

    protected synchronized void a(co coVar) {
        BitSet bitSet = null;
        synchronized (this) {
            synchronized (this) {
                BitSet bitSet2 = null;
                this.c = coVar.a;
                try {
                    if (this.c == null) {
                        if (this.a != null) {
                            try {
                                this.a.close();
                                if (bitSet2.isEmpty()) {
                                    this.c.delete();
                                }
                            } catch (Throwable th) {
                            }
                        }
                    } else {
                        this.a = new RandomAccessFile(this.c, "rw");
                        byte[] bArr = new byte[this.b.a()];
                        if (bw.e && this.a != null) {
                            try {
                                this.a.close();
                                if (this.a != null) {
                                    try {
                                        this.a.close();
                                        if (bitSet2.isEmpty()) {
                                            this.c.delete();
                                        }
                                    } catch (Throwable th2) {
                                    }
                                }
                            } catch (IOException e) {
                            }
                        }
                        this.a.read(bArr);
                        bitSet = cb.a(bArr);
                        if (coVar.b()) {
                            for (int i = coVar.c[0]; i <= coVar.c[1]; i++) {
                                bitSet.set(i, false);
                            }
                            this.a.seek(0);
                            this.a.write(cb.a(bitSet));
                        }
                        if (this.a != null) {
                            this.a.close();
                            if (bitSet.isEmpty()) {
                                this.c.delete();
                            }
                        }
                        this.c = null;
                    }
                } catch (Throwable th3) {
                }
            }
        }
        return;
    }
}
