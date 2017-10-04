package com.nokia.maps;

import com.nokia.maps.annotation.Online;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

@Online
public class ConnectionInfoImpl extends BaseNativeObject {
    public static native String getApplicationCode();

    public static native String getApplicationId();

    public static native String getApplicationVersion();

    public static native String getClientSDKName();

    public static native String getClientSDKVersion();

    public static native String getDeviceId();

    public static native String getDeviceName();

    public static native String getPlatformName();

    public static native String getPlatformVersion();

    public static native String getTransportInfo();

    public static native String getUserAgent();

    public static native boolean setApplicationCode(String str);

    public static native boolean setApplicationId(String str);

    public static native boolean setApplicationVersion(String str);

    public static native boolean setClientSDKName(String str);

    public static native boolean setClientSDKVersion(String str);

    public static native boolean setDeviceId(String str);

    public static native boolean setDeviceName(String str);

    public static native boolean setPlatformName(String str);

    public static native boolean setPlatformVersion(String str);

    public static native boolean setTransportInfo(String str);

    public static native boolean setUserAgent(String str);

    public native long getBytesDownloaded();

    public static String a(boolean z) {
        BufferedReader bufferedReader;
        String str;
        IOException e;
        OutputStreamWriter outputStreamWriter;
        Throwable th;
        Object obj = 1;
        OutputStreamWriter outputStreamWriter2 = null;
        BufferedReader bufferedReader2 = null;
        OutputStreamWriter outputStreamWriter3 = null;
        try {
            File file = new File(MapSettings.b());
            if (file.exists() || file.mkdirs()) {
                Object obj2;
                file = new File(MapSettings.f());
                if (!file.exists()) {
                    obj2 = 1;
                    bufferedReader = null;
                    str = null;
                } else if (!z || System.currentTimeMillis() - file.lastModified() >= 604800000) {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null && readLine.length() == 36) {
                            obj = null;
                        }
                        if (file.delete()) {
                            Object obj3 = obj;
                            str = readLine;
                            bufferedReader = bufferedReader2;
                            obj2 = obj3;
                        } else {
                            throw new IOException("Failed to delete DID file");
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        outputStreamWriter = null;
                        try {
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    str = null;
                                }
                            }
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            str = null;
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            outputStreamWriter2 = outputStreamWriter;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    throw th;
                                }
                            }
                            if (outputStreamWriter2 != null) {
                                outputStreamWriter2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (outputStreamWriter2 != null) {
                            outputStreamWriter2.close();
                        }
                        throw th;
                    }
                } else {
                    if (null != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return null;
                        }
                    }
                    if (null == null) {
                        return null;
                    }
                    outputStreamWriter3.close();
                    return null;
                }
                if (obj2 != null) {
                    try {
                        str = UUID.randomUUID().toString();
                    } catch (IOException e5) {
                        e32 = e5;
                        outputStreamWriter = null;
                        e32.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                        }
                        str = null;
                        return str;
                    } catch (Throwable th4) {
                        th = th4;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (outputStreamWriter2 != null) {
                            outputStreamWriter2.close();
                        }
                        throw th;
                    }
                }
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                try {
                    outputStreamWriter.write(str);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                } catch (IOException e6) {
                    e32 = e6;
                    e32.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                    str = null;
                    return str;
                }
                return str;
            }
            throw new IOException("Failed to create" + file.getName());
        } catch (IOException e7) {
            e32 = e7;
            outputStreamWriter = null;
            bufferedReader = null;
            e32.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            str = null;
            return str;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (outputStreamWriter2 != null) {
                outputStreamWriter2.close();
            }
            throw th;
        }
    }
}
