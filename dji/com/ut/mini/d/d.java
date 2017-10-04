package com.ut.mini.d;

import com.alipay.sdk.h.a;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.UrlEncodedParser;
import com.here.odnp.debug.DebugFile;
import com.loopj.android.http.AsyncHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public final class d {
    static {
        System.setProperty("http.keepAlive", "true");
    }

    public static byte[] a(int i, String str, Map<String, Object> map, boolean z) {
        Exception e;
        DataOutputStream dataOutputStream;
        Throwable th;
        if (n.a(str)) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (httpURLConnection == null) {
                return null;
            }
            if (i == 2 || i == 3) {
                httpURLConnection.setDoOutput(true);
            }
            httpURLConnection.setDoInput(true);
            if (i == 2 || i == 3) {
                try {
                    httpURLConnection.setRequestMethod(HttpMethods.POST);
                } catch (ProtocolException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            httpURLConnection.setRequestMethod(HttpMethods.GET);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(35000);
            httpURLConnection.setRequestProperty(dji.sdksharedlib.b.d.ck, "Keep-Alive");
            if (z) {
                httpURLConnection.setRequestProperty(AsyncHttpClient.HEADER_ACCEPT_ENCODING, "gzip,deflate");
            }
            httpURLConnection.setInstanceFollowRedirects(true);
            byte[] bArr = null;
            if (i == 2 || i == 3) {
                byte[] bArr2;
                int length;
                if (i == 2) {
                    httpURLConnection.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=GJircTeP");
                } else if (i == 3) {
                    httpURLConnection.setRequestProperty(AsyncHttpClient.HEADER_CONTENT_TYPE, UrlEncodedParser.CONTENT_TYPE);
                }
                if (map == null || map.size() <= 0) {
                    bArr2 = null;
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    Set keySet = map.keySet();
                    String[] strArr = new String[keySet.size()];
                    keySet.toArray(strArr);
                    for (String str2 : f.a().a(strArr, true)) {
                        if (i == 2) {
                            bArr = (byte[]) map.get(str2);
                            if (bArr != null) {
                                try {
                                    byteArrayOutputStream.write(String.format("--GJircTeP\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: application/octet-stream \r\n\r\n", new Object[]{str2, str2}).getBytes());
                                    byteArrayOutputStream.write(bArr);
                                    byteArrayOutputStream.write(DebugFile.EOL.getBytes());
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } else if (i == 3) {
                            String str3 = (String) map.get(str2);
                            if (byteArrayOutputStream.size() > 0) {
                                try {
                                    byteArrayOutputStream.write((a.b + str2 + "=" + str3).getBytes());
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            } else {
                                try {
                                    byteArrayOutputStream.write((str2 + "=" + str3).getBytes());
                                } catch (IOException e322) {
                                    e322.printStackTrace();
                                }
                            }
                        }
                    }
                    if (i == 2) {
                        try {
                            byteArrayOutputStream.write("--GJircTeP--\r\n".getBytes());
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    bArr2 = byteArrayOutputStream.toByteArray();
                }
                if (bArr2 != null) {
                    length = bArr2.length;
                } else {
                    length = 0;
                }
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(length));
                bArr = bArr2;
            }
            DataOutputStream dataOutputStream2 = null;
            try {
                long currentTimeMillis;
                byte[] bArr3;
                int read;
                httpURLConnection.connect();
                if ((i == 2 || i == 3) && bArr != null && bArr.length > 0) {
                    DataOutputStream dataOutputStream3 = new DataOutputStream(httpURLConnection.getOutputStream());
                    try {
                        dataOutputStream3.write(bArr);
                        dataOutputStream3.flush();
                        dataOutputStream2 = dataOutputStream3;
                    } catch (Exception e4) {
                        e = e4;
                        dataOutputStream = dataOutputStream3;
                        try {
                            e.printStackTrace();
                            if (dataOutputStream != null) {
                                return null;
                            }
                            try {
                                dataOutputStream.close();
                                return null;
                            } catch (IOException e32222) {
                                e32222.printStackTrace();
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream2 = dataOutputStream;
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (IOException e322222) {
                                    e322222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream2 = dataOutputStream3;
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        throw th;
                    }
                }
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e3222222) {
                        e3222222.printStackTrace();
                    }
                }
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                if (z) {
                    try {
                        if (AsyncHttpClient.ENCODING_GZIP.equals(httpURLConnection.getContentEncoding())) {
                            inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                            currentTimeMillis = System.currentTimeMillis();
                            bArr3 = new byte[2048];
                            do {
                                read = inputStream.read(bArr3, 0, 2048);
                                if (read != -1) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr3, 0, read);
                            } while (System.currentTimeMillis() - currentTimeMillis <= 10000);
                            byteArrayOutputStream2.reset();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream2.size() <= 0) {
                                return byteArrayOutputStream2.toByteArray();
                            }
                            return null;
                        }
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        if (inputStream == null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                            return null;
                        } catch (Exception e7) {
                            e7.printStackTrace();
                            return null;
                        }
                    } catch (Throwable th4) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e72) {
                                e72.printStackTrace();
                            }
                        }
                    }
                }
                inputStream = new DataInputStream(httpURLConnection.getInputStream());
                currentTimeMillis = System.currentTimeMillis();
                bArr3 = new byte[2048];
                do {
                    read = inputStream.read(bArr3, 0, 2048);
                    if (read != -1) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr3, 0, read);
                } while (System.currentTimeMillis() - currentTimeMillis <= 10000);
                byteArrayOutputStream2.reset();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2.size() <= 0) {
                    return null;
                }
                return byteArrayOutputStream2.toByteArray();
            } catch (Exception e8) {
                e5 = e8;
                dataOutputStream = null;
                e5.printStackTrace();
                if (dataOutputStream != null) {
                    return null;
                }
                dataOutputStream.close();
                return null;
            } catch (Throwable th5) {
                th = th5;
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                throw th;
            }
        } catch (MalformedURLException e9) {
            e9.printStackTrace();
            return null;
        } catch (IOException e62) {
            e62.printStackTrace();
            return null;
        }
    }
}
