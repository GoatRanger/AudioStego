package dji.thirdparty.afinal.b.c;

import android.util.Log;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class b implements a {
    private static final String a = b.class.getSimpleName();
    private static final int b = 8192;

    public class a extends FilterInputStream {
        final /* synthetic */ b a;

        public a(b bVar, InputStream inputStream) {
            this.a = bVar;
            super(inputStream);
        }

        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j2 = skip + j2;
            }
            return j2;
        }
    }

    public byte[] a(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().toLowerCase().startsWith("http")) {
            return b(str);
        }
        File file;
        if (str.trim().toLowerCase().startsWith("file:")) {
            try {
                file = new File(new URI(str));
                if (file.exists() && file.canRead()) {
                    return a(file);
                }
                return null;
            } catch (URISyntaxException e) {
                Log.e(a, "Error in read from file - " + str + " : " + e);
                return null;
            }
        }
        file = new File(str);
        if (file.exists() && file.canRead()) {
            return a(file);
        }
        return null;
    }

    private byte[] a(File file) {
        FileInputStream fileInputStream;
        Object e;
        Throwable th;
        byte[] bArr = null;
        if (file != null) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.e(a, "Error in read from file - " + file + " : " + e);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream = bArr;
                Log.e(a, "Error in read from file - " + file + " : " + e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                fileInputStream = bArr;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    private byte[] b(String str) {
        HttpURLConnection httpURLConnection;
        a aVar;
        Object obj;
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            a aVar2;
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(HorizonalSegmentView.N);
                aVar2 = new a(this, new BufferedInputStream(httpURLConnection2.getInputStream(), 8192));
            } catch (IOException e) {
                httpURLConnection = httpURLConnection2;
                IOException iOException = e;
                aVar = null;
                try {
                    Log.e(a, "Error in downloadBitmap - " + str + " : " + obj);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (null != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            return null;
                        }
                    }
                    if (aVar != null) {
                        aVar.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (null != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e3) {
                            throw th;
                        }
                    }
                    if (aVar != null) {
                        aVar.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                httpURLConnection = httpURLConnection2;
                th = th3;
                aVar = null;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (null != null) {
                    bufferedOutputStream.close();
                }
                if (aVar != null) {
                    aVar.close();
                }
                throw th;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = aVar2.read();
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(read);
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (null != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                if (aVar2 != null) {
                    aVar2.close();
                }
                return toByteArray;
            } catch (IOException e5) {
                IOException iOException2 = e5;
                aVar = aVar2;
                httpURLConnection = httpURLConnection2;
                obj = iOException2;
                Log.e(a, "Error in downloadBitmap - " + str + " : " + obj);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (null != null) {
                    bufferedOutputStream.close();
                }
                if (aVar != null) {
                    aVar.close();
                }
                return null;
            } catch (Throwable th32) {
                Throwable th4 = th32;
                aVar = aVar2;
                httpURLConnection = httpURLConnection2;
                th = th4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (null != null) {
                    bufferedOutputStream.close();
                }
                if (aVar != null) {
                    aVar.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            obj = e6;
            aVar = null;
            httpURLConnection = null;
            Log.e(a, "Error in downloadBitmap - " + str + " : " + obj);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (null != null) {
                bufferedOutputStream.close();
            }
            if (aVar != null) {
                aVar.close();
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
            aVar = null;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (null != null) {
                bufferedOutputStream.close();
            }
            if (aVar != null) {
                aVar.close();
            }
            throw th;
        }
    }
}
