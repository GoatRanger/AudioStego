package dji.thirdparty.g.c;

import android.support.v4.media.TransportMediator;
import com.here.posclient.analytics.TrackerEvent;
import it.sauronsoftware.ftp4j.FTPCodes;

public abstract class h implements dji.thirdparty.g.a.a {
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 3;
    public static final int n = 4;
    public static final int o = 5;
    public static final int p = -1;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private static class b extends h {
        private b() {
            super();
        }

        public int a(byte[] bArr, int i, boolean z) throws a {
            while (i < bArr.length) {
                if (bArr[i] != (byte) 0) {
                    i++;
                } else if (z) {
                    return i + 1;
                } else {
                    return i;
                }
            }
            return bArr.length;
        }
    }

    private static abstract class c extends h {
        protected static final int q = 0;
        protected static final int r = 1;
        protected int s = 0;

        public c(int i) {
            super();
            this.s = i;
        }

        public boolean a(byte[] bArr, int i, boolean z, boolean z2) throws a {
            while (i != bArr.length) {
                if (i >= bArr.length - 1) {
                    return false;
                }
                int i2 = i + 1;
                int i3 = bArr[i] & 255;
                i = i2 + 1;
                int i4 = bArr[i2] & 255;
                i2 = this.s == 0 ? i3 : i4;
                if (i3 == 0 && i4 == 0) {
                    return z;
                }
                if (i2 >= 216) {
                    if (i2 >= 220 || i >= bArr.length - 1) {
                        return false;
                    }
                    i3 = i + 1;
                    i2 = bArr[i] & 255;
                    i = i3 + 1;
                    i3 = bArr[i3] & 255;
                    if (this.s != 0) {
                        i2 = i3;
                    }
                    if (i2 < FTPCodes.SERVICE_READY_FOR_NEW_USER) {
                        return false;
                    }
                }
            }
            if (z2) {
                return false;
            }
            return true;
        }

        public int a(byte[] bArr, int i, boolean z) throws a {
            while (i != bArr.length) {
                if (i > bArr.length - 1) {
                    throw new a("Terminator not found.");
                }
                int i2 = i + 1;
                int i3 = bArr[i] & 255;
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (this.s == 0) {
                    i2 = i3;
                } else {
                    i2 = i5;
                }
                if (i3 == 0 && i5 == 0) {
                    return !z ? i4 - 2 : i4;
                } else {
                    if (i2 < 216) {
                        i = i4;
                    } else if (i4 > bArr.length - 1) {
                        throw new a("Terminator not found.");
                    } else {
                        i2 = i4 + 1;
                        i4 = bArr[i4] & 255;
                        i = i2 + 1;
                        i2 = bArr[i2] & 255;
                        if (this.s != 0) {
                            i4 = i2;
                        }
                        if (i4 < FTPCodes.SERVICE_READY_FOR_NEW_USER) {
                            throw new a("Invalid code point.");
                        }
                    }
                }
            }
            return bArr.length;
        }
    }

    private static class d extends c {
        public d(int i) {
            super(i);
        }
    }

    private static class e extends c {
        public e() {
            super(0);
        }

        public int a(byte[] bArr, int i, boolean z) throws a {
            if (i >= bArr.length - 1) {
                throw new a("Missing BOM.");
            }
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i3 == 255 && i2 == 254) {
                this.s = 1;
            } else if (i3 == 254 && i2 == 255) {
                this.s = 0;
            } else {
                throw new a("Invalid byte order mark.");
            }
            return super.a(bArr, i4, z);
        }
    }

    private static class f extends h {
        private f() {
            super();
        }

        public int a(byte[] bArr, int i, boolean z) throws a {
            while (i != bArr.length) {
                if (i > bArr.length) {
                    throw new a("Terminator not found.");
                }
                int i2 = i + 1;
                int i3 = bArr[i] & 255;
                if (i3 == 0) {
                    return !z ? i2 - 1 : i2;
                } else {
                    if (i3 <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
                        i = i2;
                    } else if (i3 <= TrackerEvent.RadioMapManualPrivateIndoor) {
                        if (i2 >= bArr.length) {
                            throw new a("Invalid unicode.");
                        }
                        i = i2 + 1;
                        i2 = bArr[i2] & 255;
                        if (i2 < 128 || i2 > 191) {
                            throw new a("Invalid code point.");
                        }
                    } else if (i3 <= 239) {
                        if (i2 >= bArr.length - 1) {
                            throw new a("Invalid unicode.");
                        }
                        i3 = i2 + 1;
                        i2 = bArr[i2] & 255;
                        if (i2 < 128 || i2 > 191) {
                            throw new a("Invalid code point.");
                        }
                        i = i3 + 1;
                        i2 = bArr[i3] & 255;
                        if (i2 < 128 || i2 > 191) {
                            throw new a("Invalid code point.");
                        }
                    } else if (i3 > 244) {
                        throw new a("Invalid code point.");
                    } else if (i2 >= bArr.length - 2) {
                        throw new a("Invalid unicode.");
                    } else {
                        i3 = i2 + 1;
                        i2 = bArr[i2] & 255;
                        if (i2 < 128 || i2 > 191) {
                            throw new a("Invalid code point.");
                        }
                        i2 = i3 + 1;
                        i3 = bArr[i3] & 255;
                        if (i3 < 128 || i3 > 191) {
                            throw new a("Invalid code point.");
                        }
                        i = i2 + 1;
                        i2 = bArr[i2] & 255;
                        if (i2 < 128 || i2 > 191) {
                            throw new a("Invalid code point.");
                        }
                    }
                }
            }
            return bArr.length;
        }
    }

    protected abstract int a(byte[] bArr, int i, boolean z) throws a;

    private h() {
    }

    public static final boolean a(String str) {
        try {
            return str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
        } catch (Throwable e) {
            throw new RuntimeException("Error parsing string.", e);
        }
    }

    private static int c(byte[] bArr, int i) {
        for (int i2 = i; i2 < bArr.length - 1; i2 += 2) {
            int i3 = bArr[i + 1] & 255;
            if ((bArr[i] & 255) == 0 && i3 == 0) {
                return i2;
            }
        }
        return -1;
    }

    public final int a(byte[] bArr, int i) throws a {
        return a(bArr, i, true);
    }

    public final int b(byte[] bArr, int i) throws a {
        return a(bArr, i, false);
    }

    public static h getInstance(int i) throws a {
        switch (i) {
            case 0:
                return new b();
            case 1:
            case 2:
                return new e();
            case 3:
                return new d(77);
            case 4:
                return new d(73);
            case 5:
                return new f();
            default:
                throw new a("Unknown char encoding code: " + i);
        }
    }
}
