package com.nokia.maps;

import android.content.Context;
import android.os.Environment;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@Online
public class MapSettings {
    static boolean a = false;
    static boolean b = false;
    static a c = a.a;
    private static final String d = MapSettings.class.getSimpleName();
    private static String e;
    private static String f = null;
    private static b g = b.b;
    private static boolean h = true;

    private static native long getAssetStamp();

    private MapSettings() {
    }

    @Internal
    public static boolean a(String str) {
        e = str;
        if (str != null && str.length() > 0) {
            a = true;
        }
        return true;
    }

    @Internal
    public static String a() {
        if (e == null || e.length() == 0) {
            if (MapsEngine.e() == null || !MapsEngine.f().booleanValue()) {
                String t = t();
                if (t != null) {
                    e = b(t, File.separator) + File.separator + ".here-maps";
                } else {
                    e = q();
                    s();
                }
            } else {
                e = q();
            }
        }
        return e;
    }

    public static String b() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".here-maps" + File.separator + "diskcache-v4" + File.separator;
    }

    public static String c() {
        Long valueOf = Long.valueOf(getAssetStamp());
        return String.format("%s%s%s", new Object[]{a(), File.separator, valueOf.toString()});
    }

    public static String d() {
        return a() + File.separator + "diskcache-v4" + File.separator;
    }

    public static String e() {
        File externalFilesDir = MapsEngine.e().getExternalFilesDir(null);
        if (externalFilesDir == null) {
            return null;
        }
        if (a) {
            externalFilesDir = new File(e);
        }
        return externalFilesDir.getAbsolutePath() + File.separator + "voices-download" + File.separator;
    }

    public static String f() {
        return b() + File.separator + "uniqueDeviceId.txt";
    }

    public static String g() {
        return b() + File.separator + "privacyReportTest.txt";
    }

    public static String h() {
        return d() + File.separator + "BundleStore" + File.separator;
    }

    public static String i() {
        return d() + File.separator + "rastercache" + File.separator;
    }

    public static String j() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".here-maps" + File.separator + "certs" + File.separator;
    }

    public static b k() {
        return g;
    }

    public static boolean l() {
        return g == b.b;
    }

    public static boolean m() {
        return c != a.a;
    }

    public static void n() {
        c = a.d;
    }

    private static String q() {
        Context e = MapsEngine.e();
        if (e == null || !MapsEngine.f().booleanValue()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".here-maps";
        }
        return e.getFilesDir() + File.separator + ".here-maps";
    }

    private static File r() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".here-maps" + File.separator + "path_override");
    }

    private static boolean s() {
        File r = r();
        if (!r.exists() || r.delete()) {
            return true;
        }
        bj.b(d, "Deletion of path override file failed", new Object[0]);
        return false;
    }

    private static String t() {
        InputStream fileInputStream;
        BufferedReader bufferedReader;
        Throwable e;
        File r = r();
        if (r.exists() && r.canRead()) {
            try {
                fileInputStream = new FileInputStream(r);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
                    try {
                        String readLine = bufferedReader.readLine();
                        File file = new File(b(readLine, File.separator) + File.separator + ".here-maps");
                        if (!(file.exists() || file.mkdirs())) {
                            bj.b(d, "Could not create dir at override path location.", new Object[0]);
                            readLine = null;
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                                return readLine;
                            } catch (Throwable e2) {
                                bj.c(d, "IOException \n%s", bj.a(e2));
                                return null;
                            }
                        } else if (fileInputStream == null) {
                            return readLine;
                        } else {
                            fileInputStream.close();
                            return readLine;
                        }
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                        try {
                            bj.b(d, "File not found \n%s", bj.a(e2));
                            if (bufferedReader == null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable e22) {
                                    bj.c(d, "IOException \n%s", bj.a(e22));
                                    return null;
                                }
                            } else if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (Throwable th) {
                            e22 = th;
                            if (bufferedReader == null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable e4) {
                                    bj.c(d, "IOException \n%s", bj.a(e4));
                                }
                            } else if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw e22;
                        }
                    } catch (IOException e5) {
                        e22 = e5;
                        bj.b(d, "IOException \n%s", bj.a(e22));
                        if (bufferedReader == null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable e222) {
                                bj.c(d, "IOException \n%s", bj.a(e222));
                                return null;
                            }
                        } else if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return null;
                    }
                } catch (FileNotFoundException e6) {
                    e222 = e6;
                    bufferedReader = null;
                    bj.b(d, "File not found \n%s", bj.a(e222));
                    if (bufferedReader == null) {
                        bufferedReader.close();
                    } else if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return null;
                } catch (IOException e7) {
                    e222 = e7;
                    bufferedReader = null;
                    bj.b(d, "IOException \n%s", bj.a(e222));
                    if (bufferedReader == null) {
                        bufferedReader.close();
                    } else if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    e222 = th2;
                    bufferedReader = null;
                    if (bufferedReader == null) {
                        bufferedReader.close();
                    } else if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e222;
                }
            } catch (FileNotFoundException e8) {
                e222 = e8;
                bufferedReader = null;
                fileInputStream = null;
                bj.b(d, "File not found \n%s", bj.a(e222));
                if (bufferedReader == null) {
                    bufferedReader.close();
                } else if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (IOException e9) {
                e222 = e9;
                bufferedReader = null;
                fileInputStream = null;
                bj.b(d, "IOException \n%s", bj.a(e222));
                if (bufferedReader == null) {
                    bufferedReader.close();
                } else if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (Throwable th3) {
                e222 = th3;
                bufferedReader = null;
                fileInputStream = null;
                if (bufferedReader == null) {
                    bufferedReader.close();
                } else if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw e222;
            }
        }
        bj.e(d, "Path override file does not exist or cannot be read.", new Object[0]);
        return null;
    }

    private static final String b(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        if (str.isEmpty() || str2.isEmpty() || str.length() < str2.length()) {
            return str;
        }
        int length = str.length() - str2.length();
        while (str.indexOf(str2, length) == length) {
            length -= str2.length();
        }
        return str.substring(0, length + str2.length());
    }

    public static void a(boolean z) {
        if (c == a.d) {
            return;
        }
        if (z) {
            c = a.b;
        } else {
            c = a.c;
        }
    }

    public static boolean o() {
        return c == a.b;
    }

    public static boolean p() {
        return h;
    }

    public static boolean a(String str, String str2) {
        boolean a = a(str);
        if (a) {
            MapServiceClient.a = str2;
        }
        return a;
    }
}
