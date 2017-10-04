package dji.midware.stat;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class a {
    private static final int a = 2;
    private static final int b = 5;

    public String a() {
        RandomAccessFile randomAccessFile;
        IOException e;
        Throwable th;
        String str = null;
        try {
            randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            try {
                str = randomAccessFile.readLine();
                try {
                    randomAccessFile.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    try {
                        randomAccessFile.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        randomAccessFile.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222 = e4;
            randomAccessFile = str;
            e222.printStackTrace();
            randomAccessFile.close();
            return str;
        } catch (Throwable th3) {
            randomAccessFile = str;
            th = th3;
            randomAccessFile.close();
            throw th;
        }
        return str;
    }

    public float a(String str, String str2) {
        String[] split = str.split("\\s");
        long b = b(split);
        long a = a(split);
        split = str2.split("\\s");
        long b2 = b(split);
        long a2 = a(split);
        if (b < 0 || a < 0 || b2 < 0 || a2 < 0 || a2 + b2 <= a + b || a2 < a) {
            return -1.0f;
        }
        return (((float) (a2 - a)) / ((float) ((b2 + a2) - (b + a)))) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
    }

    public long a(String[] strArr) {
        long j = 0;
        for (int i = 2; i < strArr.length; i++) {
            if (i != 5) {
                try {
                    j += Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        return j;
    }

    public long b(String[] strArr) {
        try {
            return Long.parseLong(strArr[5]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String a(int i) {
        RandomAccessFile randomAccessFile;
        IOException e;
        Throwable th;
        String str = null;
        try {
            randomAccessFile = new RandomAccessFile("/proc/" + i + "/stat", "r");
            try {
                str = randomAccessFile.readLine();
                try {
                    randomAccessFile.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    try {
                        randomAccessFile.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        randomAccessFile.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222 = e4;
            randomAccessFile = str;
            e222.printStackTrace();
            randomAccessFile.close();
            return str;
        } catch (Throwable th3) {
            randomAccessFile = str;
            th = th3;
            randomAccessFile.close();
            throw th;
        }
        return str;
    }

    public float a(String str, String str2, long j) {
        long c = c(str.split("\\s"));
        long c2 = c(str2.split("\\s"));
        if (c < 0 || c2 < c || ((double) j) <= 0.0d) {
            return -1.0f;
        }
        return (DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * ((float) (c2 - c))) / ((float) j);
    }

    public long c(String[] strArr) {
        return Long.parseLong(strArr[14]) + Long.parseLong(strArr[15]);
    }

    public long d(String[] strArr) {
        return Long.parseLong(strArr[16]) + Long.parseLong(strArr[17]);
    }

    public float a(long j) {
        String a = a();
        if (a == null) {
            return -1.0f;
        }
        try {
            Thread.sleep(j);
        } catch (Exception e) {
        }
        String a2 = a();
        if (a2 != null) {
            return a(a, a2);
        }
        return -1.0f;
    }

    public float a(int i, long j) {
        String a = a(i);
        String a2 = a();
        if (a == null || a2 == null) {
            return -1.0f;
        }
        try {
            Thread.sleep(j);
            String a3 = a(i);
            String a4 = a();
            if (a3 == null || a4 == null) {
                return -1.0f;
            }
            return a(a, a3, a(a4.split("\\s")) - a(a2.split("\\s")));
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0f;
        }
    }
}
