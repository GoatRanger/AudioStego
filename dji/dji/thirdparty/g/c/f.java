package dji.thirdparty.g.c;

import android.support.v4.view.ViewCompat;
import dji.thirdparty.g.h;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class f implements h {
    private f() {
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(4096);
            try {
                InputStream bufferedInputStream = new BufferedInputStream(inputStream);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 4096);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e) {
                        c.a(e);
                    }
                }
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static byte[] a(File file) throws IOException {
        InputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] a = a(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e) {
                        c.a(e);
                    }
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static void a(byte[] bArr, File file) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        Throwable e;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                a((InputStream) byteArrayInputStream, file);
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
            } catch (Throwable th) {
                e2 = th;
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e3) {
                        c.a(e3);
                    }
                }
                throw e2;
            }
        } catch (Throwable th2) {
            e2 = th2;
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            throw e2;
        }
    }

    public static void a(InputStream inputStream, File file) throws IOException {
        Throwable e;
        FileOutputStream fileOutputStream = null;
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            OutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                a(inputStream, fileOutputStream2);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
            } catch (Throwable th) {
                e2 = th;
                OutputStream outputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e3) {
                        c.a(e3);
                    }
                }
                throw e2;
            }
        } catch (Throwable th2) {
            e2 = th2;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e2;
        }
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        a(inputStream, outputStream, true);
    }

    public static void a(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        Throwable e;
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr, 0, bArr.length);
                        if (read <= 0) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    if (z) {
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Throwable e2) {
                                c.a(e2);
                            }
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (Throwable e22) {
                                c.a(e22);
                            }
                        }
                    }
                } catch (Throwable th) {
                    e22 = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (z) {
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable e3) {
                                c.a(e3);
                            }
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (Throwable e4) {
                                c.a(e4);
                            }
                        }
                    }
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                bufferedOutputStream = null;
                bufferedInputStream = bufferedInputStream2;
                if (z) {
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                }
                throw e22;
            }
        } catch (Throwable th3) {
            e22 = th3;
            bufferedOutputStream = null;
            if (z) {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            throw e22;
        }
    }

    public static final boolean a(File file, File file2) throws IOException {
        FileChannel channel;
        FileChannel channel2;
        Throwable th;
        try {
            channel = new FileInputStream(file).getChannel();
            try {
                channel2 = new FileOutputStream(file2).getChannel();
            } catch (Throwable th2) {
                th = th2;
                channel2 = null;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (Throwable e) {
                        c.a(e);
                    }
                }
                if (channel2 != null) {
                    try {
                        channel2.close();
                    } catch (Throwable e2) {
                        c.a(e2);
                    }
                }
                throw th;
            }
            try {
                for (long j = 0; j < channel.size(); j += channel.transferTo(j, (long) ViewCompat.MEASURED_STATE_TOO_SMALL, channel2)) {
                }
                channel.close();
                FileChannel fileChannel = null;
                try {
                    channel2.close();
                    channel = null;
                    if (null != null) {
                        try {
                            fileChannel.close();
                        } catch (Throwable th3) {
                            c.a(th3);
                        }
                    }
                    if (null != null) {
                        try {
                            channel.close();
                        } catch (Throwable th32) {
                            c.a(th32);
                        }
                    }
                    return true;
                } catch (Throwable th4) {
                    th32 = th4;
                    channel = null;
                    if (channel != null) {
                        channel.close();
                    }
                    if (channel2 != null) {
                        channel2.close();
                    }
                    throw th32;
                }
            } catch (Throwable th5) {
                th32 = th5;
                if (channel != null) {
                    channel.close();
                }
                if (channel2 != null) {
                    channel2.close();
                }
                throw th32;
            }
        } catch (Throwable th6) {
            th32 = th6;
            channel2 = null;
            channel = null;
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
            throw th32;
        }
    }
}
