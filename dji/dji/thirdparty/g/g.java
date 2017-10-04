package dji.thirdparty.g;

import dji.thirdparty.g.a.a.a;
import dji.thirdparty.g.a.a.b;
import dji.thirdparty.g.a.a.d;
import dji.thirdparty.g.a.i;
import dji.thirdparty.g.c.c;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class g implements h {
    public static b a(a aVar) throws e, IOException {
        InputStream inputStream = null;
        try {
            inputStream = aVar.a();
            int read = inputStream.read();
            int read2 = inputStream.read();
            if (read < 0 || read2 < 0) {
                throw new e("Couldn't read magic numbers to guess format.");
            }
            b bVar;
            read &= 255;
            read2 &= 255;
            if (read == 71 && read2 == 73) {
                bVar = b.f;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e) {
                        c.a(e);
                    }
                }
            } else if (read == 137 && read2 == 80) {
                bVar = b.e;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
            } else if (read == 255 && read2 == 216) {
                bVar = b.i;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22) {
                        c.a(e22);
                    }
                }
            } else if (read == 66 && read2 == 77) {
                bVar = b.j;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222) {
                        c.a(e222);
                    }
                }
            } else if (read == 77 && read2 == 77) {
                bVar = b.h;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2222) {
                        c.a(e2222);
                    }
                }
            } else if (read == 73 && read2 == 73) {
                bVar = b.h;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22222) {
                        c.a(e22222);
                    }
                }
            } else if (read == 56 && read2 == 66) {
                bVar = b.k;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222222) {
                        c.a(e222222);
                    }
                }
            } else if (read == 80 && read2 == 49) {
                bVar = b.l;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2222222) {
                        c.a(e2222222);
                    }
                }
            } else if (read == 80 && read2 == 52) {
                bVar = b.l;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22222222) {
                        c.a(e22222222);
                    }
                }
            } else if (read == 80 && read2 == 50) {
                bVar = b.m;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222222222) {
                        c.a(e222222222);
                    }
                }
            } else if (read == 80 && read2 == 53) {
                bVar = b.m;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2222222222) {
                        c.a(e2222222222);
                    }
                }
            } else if (read == 80 && read2 == 51) {
                bVar = b.n;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22222222222) {
                        c.a(e22222222222);
                    }
                }
            } else if (read == 80 && read2 == 54) {
                bVar = b.n;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222222222222) {
                        c.a(e222222222222);
                    }
                }
            } else {
                if (read == 151 && read2 == 74) {
                    read = inputStream.read();
                    read2 = inputStream.read();
                    if (read < 0 || read2 < 0) {
                        throw new e("Couldn't read magic numbers to guess format.");
                    }
                    read2 &= 255;
                    if ((read & 255) == 66 && read2 == 50) {
                        bVar = b.q;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable e2222222222222) {
                                c.a(e2222222222222);
                            }
                        }
                    }
                }
                bVar = b.d;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22222222222222) {
                        c.a(e22222222222222);
                    }
                }
            }
            return bVar;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e222222222222222) {
                    c.a(e222222222222222);
                }
            }
        }
    }

    private static final d b(a aVar) throws e, IOException {
        int i = 0;
        b a = a(aVar);
        if (!a.equals(b.d)) {
            d[] a2 = d.a();
            for (d dVar : a2) {
                if (dVar.a(a)) {
                    return dVar;
                }
            }
        }
        String e = aVar.e();
        if (e != null) {
            d[] a3 = d.a();
            while (i < a3.length) {
                d dVar2 = a3[i];
                if (dVar2.a(e)) {
                    return dVar2;
                }
                i++;
            }
        }
        throw new e("Can't parse this format.");
    }

    public static i a(byte[] bArr) throws e, IOException {
        return a(bArr, null);
    }

    public static i a(byte[] bArr, Map map) throws e, IOException {
        return a(new b(bArr), map);
    }

    public static i a(InputStream inputStream, String str) throws e, IOException {
        return a(inputStream, str, null);
    }

    public static i a(InputStream inputStream, String str, Map map) throws e, IOException {
        return a(new d(inputStream, str), map);
    }

    public static i a(File file) throws e, IOException {
        return a(file, null);
    }

    public static i a(File file, Map map) throws e, IOException {
        return a(new dji.thirdparty.g.a.a.c(file), map);
    }

    private static i a(a aVar, Map map) throws e, IOException {
        return b(aVar).a(aVar, map);
    }
}
