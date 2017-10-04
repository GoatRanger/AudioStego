package com.nokia.maps;

import android.content.Context;
import com.nokia.maps.annotation.Online;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Online
public class SSLCertManager {
    private static final String a = SSLCertManager.class.getSimpleName();
    private static final String b = MapSettings.j();

    private static native boolean deployToDisk(String str, boolean z);

    private static native String[] getCertList();

    private static boolean b(Context context) {
        if (bf.a("CertResourcesPkg").booleanValue()) {
            return true;
        }
        bj.b(a, "Library (CertResourcesPkg) failed to load ...", new Object[0]);
        return false;
    }

    public static boolean a(Context context) {
        boolean z;
        File file = new File(b);
        File file2 = new File("/system/etc/security/cacerts/");
        if (file.exists()) {
            if (file.isDirectory()) {
                b(context);
                String[] certList = getCertList();
                if (certList != null && certList.length > 0) {
                    for (String file3 : certList) {
                        if (!new File(b, file3).exists()) {
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
            } else {
                bj.f(a, "Certificate location is not a directory, cannot continue.", new Object[0]);
                return false;
            }
        } else if (!file.mkdirs()) {
            return false;
        } else {
            z = true;
        }
        if (z) {
            z = c(context);
        } else {
            z = true;
        }
        if (z) {
            z = a(file, file2, false);
        }
        return z;
    }

    private static boolean a(File file, File file2, boolean z) {
        InputStream fileInputStream;
        OutputStream fileOutputStream;
        OutputStream outputStream;
        OutputStream outputStream2;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream3 = null;
        boolean z2 = true;
        File[] listFiles = file2.listFiles();
        if (listFiles == null) {
            return false;
        }
        CryptUtils instance = CryptUtils.getInstance();
        for (File file3 : listFiles) {
            if (file3.isFile()) {
                File file4 = new File(file, file3.getName());
                if (z || !file4.exists()) {
                    String x509_NAME_HASH = instance.x509_NAME_HASH(file3.getAbsolutePath().getBytes(Charset.forName("UTF-8")));
                    if (!(x509_NAME_HASH == null || x509_NAME_HASH.length() == 0)) {
                        File file5 = new File(file, x509_NAME_HASH + ".0");
                        if (!file5.exists() || z) {
                            try {
                                fileInputStream = new FileInputStream(file3);
                                try {
                                    fileOutputStream = new FileOutputStream(file4);
                                } catch (RuntimeException e) {
                                    outputStream = null;
                                    outputStream2 = null;
                                    inputStream = fileInputStream;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Exception e2) {
                                            z2 = false;
                                        }
                                    }
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    z2 = false;
                                } catch (Exception e3) {
                                    outputStream2 = null;
                                    fileOutputStream = null;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (Exception e4) {
                                            z2 = false;
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    z2 = false;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileOutputStream = null;
                                }
                                try {
                                    outputStream2 = new FileOutputStream(file5);
                                    try {
                                        fileOutputStream.write(new byte[1], 0, 1);
                                        byte[] bArr = new byte[4096];
                                        while (true) {
                                            int read = fileInputStream.read(bArr);
                                            if (read <= 0) {
                                                break;
                                            }
                                            outputStream2.write(bArr, 0, read);
                                        }
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception e5) {
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                    } catch (RuntimeException e6) {
                                        outputStream = outputStream2;
                                        outputStream2 = fileOutputStream;
                                        inputStream = fileInputStream;
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        if (outputStream != null) {
                                            outputStream.close();
                                        }
                                        z2 = false;
                                    } catch (Exception e7) {
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (outputStream2 != null) {
                                            outputStream2.close();
                                        }
                                        z2 = false;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        outputStream3 = outputStream2;
                                    }
                                } catch (RuntimeException e8) {
                                    outputStream = null;
                                    outputStream2 = fileOutputStream;
                                    inputStream = fileInputStream;
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    z2 = false;
                                } catch (Exception e9) {
                                    outputStream2 = null;
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    z2 = false;
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            } catch (RuntimeException e10) {
                                outputStream = null;
                                outputStream2 = null;
                                inputStream = null;
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                z2 = false;
                            } catch (Exception e11) {
                                outputStream2 = null;
                                fileOutputStream = null;
                                fileInputStream = null;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                z2 = false;
                            } catch (Throwable th5) {
                                th = th5;
                                fileOutputStream = null;
                                fileInputStream = null;
                            }
                        }
                    }
                }
            }
        }
        return z2;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e12) {
                throw th;
            }
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (outputStream3 != null) {
            outputStream3.close();
        }
        throw th;
    }

    private static boolean c(Context context) {
        boolean b = b(context);
        if (b) {
            return deployToDisk(b, false);
        }
        return b;
    }
}
