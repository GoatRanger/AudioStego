package dji.pilot.usercenter.f;

import dji.thirdparty.g.c;
import java.util.HashMap;
import java.util.Locale;

public class d {
    public static final int A = 32;
    public static final int B = 33;
    public static final int C = 34;
    public static final int D = 35;
    public static final int E = 36;
    public static final int F = 37;
    public static final int G = 41;
    public static final int H = 42;
    public static final int I = 43;
    public static final int J = 44;
    public static final int K = 51;
    public static final int L = 100;
    public static final int M = 101;
    public static final int N = 102;
    public static final int O = 103;
    public static final int P = 104;
    public static final int Q = 105;
    public static final int R = 106;
    public static final int S = 107;
    private static final int T = 1;
    private static final int U = 10;
    private static final int V = 11;
    private static final int W = 13;
    private static final int X = 21;
    private static final int Y = 30;
    private static final int Z = 200;
    public static final int a = 1;
    private static final int aa = 201;
    private static final int ab = 31;
    private static final int ac = 36;
    private static final int ad = 41;
    private static final int ae = 44;
    private static final int af = 51;
    private static final int ag = 51;
    private static final HashMap<String, a> ah = new HashMap();
    private static final HashMap<String, Integer> ai = new HashMap();
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    public static final int j = 10;
    public static final int k = 11;
    public static final int l = 12;
    public static final int m = 13;
    public static final int n = 21;
    public static final int o = 22;
    public static final int p = 23;
    public static final int q = 24;
    public static final int r = 25;
    public static final int s = 26;
    public static final int t = 27;
    public static final int u = 28;
    public static final int v = 29;
    public static final int w = 30;
    public static final int x = 200;
    public static final int y = 201;
    public static final int z = 31;

    public static class a {
        public final int a;
        public final String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    static {
        a("MP3", 1, "audio/mpeg");
        a("MPGA", 1, "audio/mpeg");
        a("M4A", 2, "audio/mp4");
        a("WAV", 3, "audio/x-wav");
        a("AMR", 4, "audio/amr");
        a("AWB", 5, "audio/amr-wb");
        a("WMA", 6, "audio/x-ms-wma");
        a("OGG", 7, "audio/ogg");
        a("OGG", 7, "application/ogg");
        a("OGA", 7, "application/ogg");
        a("AAC", 8, "audio/aac");
        a("AAC", 8, "audio/aac-adts");
        a("MKA", 9, "audio/x-matroska");
        a("MID", 11, "audio/midi");
        a("MIDI", 11, "audio/midi");
        a("XMF", 11, "audio/midi");
        a("RTTTL", 11, "audio/midi");
        a("SMF", 12, "audio/sp-midi");
        a("IMY", 13, "audio/imelody");
        a("RTX", 11, "audio/midi");
        a("OTA", 11, "audio/midi");
        a("MXMF", 11, "audio/midi");
        a("MPEG", 21, "video/mpeg");
        a("MPG", 21, "video/mpeg");
        a("MP4", 21, "video/mp4");
        a("M4V", 22, "video/mp4");
        a("3GP", 23, "video/3gpp");
        a("3GPP", 23, "video/3gpp");
        a("3G2", 24, "video/3gpp2");
        a("3GPP2", 24, "video/3gpp2");
        a("MKV", 27, "video/x-matroska");
        a("WEBM", 30, "video/webm");
        a("TS", 28, "video/mp2ts");
        a("AVI", 29, "video/avi");
        a("WMV", 25, "video/x-ms-wmv");
        a("ASF", 26, "video/x-ms-asf");
        a("MOV", 201, "video/mov");
        a("TIF", 37, "image/tif");
        a("JPG", 31, "image/jpeg");
        a(c.k, 31, "image/jpeg");
        a("GIF", 32, "image/gif");
        a("PNG", 33, "image/png");
        a("BMP", 34, "image/x-ms-bmp");
        a("WBMP", 35, "image/vnd.wap.wbmp");
        a("WEBP", 36, "image/webp");
        a("M3U", 41, "audio/x-mpegurl");
        a("M3U", 41, "application/x-mpegurl");
        a("PLS", 42, "audio/x-scpls");
        a("WPL", 43, "application/vnd.ms-wpl");
        a("M3U8", 44, "application/vnd.apple.mpegurl");
        a("M3U8", 44, "audio/mpegurl");
        a("M3U8", 44, "audio/x-mpegurl");
        a("FL", 51, "application/x-android-drm-fl");
        a("TXT", 100, "text/plain");
        a("HTM", 101, "text/html");
        a("HTML", 101, "text/html");
        a("PDF", 102, "application/pdf");
        a("DOC", 104, "application/msword");
        a("XLS", 105, "application/vnd.ms-excel");
        a("PPT", 106, "application/mspowerpoint");
        a("FLAC", 10, "audio/flac");
        a("ZIP", 107, "application/zip");
        a("MPG", 200, "video/mp2p");
        a("MPEG", 200, "video/mp2p");
    }

    static void a(String str, int i, String str2) {
        ah.put(str, new a(i, str2));
        ai.put(str2, Integer.valueOf(i));
    }

    public static boolean a(String str) {
        if (str == null || !str.contains("segmentLibrary_hd")) {
            return false;
        }
        return true;
    }

    public static boolean a(int i) {
        if (i < 1 || i > 10) {
            return i >= 11 && i <= 13;
        } else {
            return true;
        }
    }

    public static boolean b(int i) {
        return (i >= 21 && i <= 30) || (i >= 200 && i <= 201);
    }

    public static boolean c(int i) {
        return i >= 31 && i <= 36;
    }

    public static boolean d(int i) {
        return i >= 41 && i <= 44;
    }

    public static boolean e(int i) {
        return i >= 51 && i <= 51;
    }

    public static a b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return null;
        }
        return (a) ah.get(str.substring(lastIndexOf + 1).toUpperCase(Locale.ENGLISH));
    }

    public static boolean c(String str) {
        int e = e(str);
        return a(e) || b(e) || c(e) || d(e);
    }

    public static String d(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf >= 0) {
            lastIndexOf++;
            if (lastIndexOf < str.length()) {
                str = str.substring(lastIndexOf);
            }
        }
        lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf > 0) {
            return str.substring(0, lastIndexOf);
        }
        return str;
    }

    public static int e(String str) {
        Integer num = (Integer) ai.get(str);
        return num == null ? 0 : num.intValue();
    }

    public static String f(String str) {
        a b = b(str);
        return b == null ? null : b.b;
    }
}
