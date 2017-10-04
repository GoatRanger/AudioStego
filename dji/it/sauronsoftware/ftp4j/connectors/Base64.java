package it.sauronsoftware.ftp4j.connectors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class Base64 {
    static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    static char pad = '=';

    Base64() {
    }

    public static String encode(String str) throws RuntimeException {
        try {
            return new String(encode(str.getBytes()), "ASCII");
        } catch (Throwable e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    public static String encode(String str, String str2) throws RuntimeException {
        try {
            try {
                return new String(encode(str.getBytes(str2)), "ASCII");
            } catch (Throwable e) {
                throw new RuntimeException("ASCII is not supported!", e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException(new StringBuffer().append("Unsupported charset: ").append(str2).toString(), e2);
        }
    }

    public static String decode(String str) throws RuntimeException {
        try {
            return new String(decode(str.getBytes("ASCII")));
        } catch (Throwable e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    public static String decode(String str, String str2) throws RuntimeException {
        try {
            try {
                return new String(decode(str.getBytes("ASCII")), str2);
            } catch (Throwable e) {
                throw new RuntimeException(new StringBuffer().append("Unsupported charset: ").append(str2).toString(), e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException("ASCII is not supported!", e2);
        }
    }

    public static byte[] encode(byte[] bArr) throws RuntimeException {
        return encode(bArr, 0);
    }

    public static byte[] encode(byte[] bArr, int i) throws RuntimeException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(byteArrayInputStream, byteArrayOutputStream, i);
            try {
                byteArrayInputStream.close();
            } catch (Throwable th) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } catch (Throwable th3) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th4) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static byte[] decode(byte[] bArr) throws RuntimeException {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decode(byteArrayInputStream, byteArrayOutputStream);
            try {
                byteArrayInputStream.close();
            } catch (Throwable th) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } catch (Throwable th3) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th4) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        encode(inputStream, outputStream, 0);
    }

    public static void encode(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        OutputStream base64OutputStream = new Base64OutputStream(outputStream, i);
        copy(inputStream, base64OutputStream);
        base64OutputStream.commit();
    }

    public static void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(new Base64InputStream(inputStream), outputStream);
    }

    public static void encode(File file, File file2, int i) throws IOException {
        OutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    encode(fileInputStream, fileOutputStream, i);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void encode(File file, File file2) throws IOException {
        Throwable th;
        InputStream inputStream = null;
        OutputStream fileOutputStream;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    encode(fileInputStream, fileOutputStream);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void decode(File file, File file2) throws IOException {
        Throwable th;
        InputStream inputStream = null;
        OutputStream fileOutputStream;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    decode(fileInputStream, fileOutputStream);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
