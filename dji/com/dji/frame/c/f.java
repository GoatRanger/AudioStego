package com.dji.frame.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.here.odnp.debug.DebugFile;
import dji.thirdparty.afinal.c.d;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class f {
    private static int a = 3;
    private static ExecutorService b = Executors.newFixedThreadPool(a, new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(4);
            return thread;
        }
    });
    private static Object c = new Object();
    private static boolean d = false;

    private static class a extends d<Object, Void, String> {
        private final com.dji.frame.b.a a;

        protected /* synthetic */ Object b(Object[] objArr) {
            return a(objArr);
        }

        public a(com.dji.frame.b.a aVar) {
            this.a = aVar;
        }

        protected String a(Object... objArr) {
            synchronized (f.c) {
                while (f.d && !e()) {
                    try {
                        f.c.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.a.a();
            return null;
        }

        protected void a(String str) {
            super.a(str);
            synchronized (f.c) {
                f.c.notifyAll();
            }
        }
    }

    private static String d(String str, String str2) {
        return str + File.separator + str2;
    }

    public static Boolean a(String str) {
        return Boolean.valueOf(new File(str).exists());
    }

    public static String a(String str, String str2) {
        return a(new File(d(str, str2)));
    }

    public static String a(File file) {
        String str = "";
        if (!file.exists()) {
            return str;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str2 = "";
            StringBuilder stringBuilder = new StringBuilder();
            while (str2 != null) {
                str2 = bufferedReader.readLine();
                if (str2 == null) {
                    break;
                }
                stringBuilder.append(str2.trim());
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static byte[] b(File file) {
        byte[] bArr = new byte[((int) file.length())];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bArr, 0, bArr.length);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return bArr;
    }

    public static String a(Context context, int i) {
        String str;
        InputStream openRawResource = context.getResources().openRawResource(i);
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = openRawResource.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Exception e) {
                Exception exception = e;
                str = null;
                Exception exception2 = exception;
            }
        }
        String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF_8");
        try {
            str = str2.replace(DebugFile.EOL, "\n");
            try {
                byteArrayOutputStream.close();
                openRawResource.close();
            } catch (Exception e2) {
                exception2 = e2;
                exception2.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            exception2 = e3;
            str = str2;
            exception2.printStackTrace();
            return str;
        }
        return str;
    }

    private static void a(String str, String str2, String str3, Boolean bool) {
        try {
            FileWriter fileWriter = new FileWriter(d(str, str2), bool.booleanValue());
            fileWriter.write(str3, 0, str3.length());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void a(File file, String str, Boolean bool) {
        try {
            FileWriter fileWriter = new FileWriter(file, bool.booleanValue());
            fileWriter.write(str, 0, str.length());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2, String str3) {
        a(str, str2, str3, Boolean.valueOf(false));
    }

    public static void a(File file, String str) {
        a(file, str, Boolean.valueOf(false));
    }

    public static void a(String str, byte[] bArr, int i, int i2) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr, i, i2);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void b(String str, String str2, String str3) {
        a(str, str2, str3, Boolean.valueOf(true));
    }

    public static void b(File file, String str) {
        a(file, str, Boolean.valueOf(true));
    }

    public static void a(final Bitmap bitmap, final String str, final String str2) {
        if (bitmap != null && str != null && str2 != null) {
            new a(new com.dji.frame.b.a() {
                public void a() {
                    try {
                        File file = new File(str);
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        file = new File(f.d(str, str2));
                        file.createNewFile();
                        OutputStream fileOutputStream = new FileOutputStream(file);
                        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).a(b, new Object[]{"ok"});
        }
    }

    public static void b(Bitmap bitmap, String str, String str2) throws IOException {
        if (bitmap != null && str != null && str2 != null) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
            file = new File(d(str, str2));
            file.createNewFile();
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public static long c(File file) throws FileNotFoundException {
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        long j = 0;
        for (int i = 0; i < length; i++) {
            if (listFiles[i].isDirectory()) {
                j += c(listFiles[i]);
            } else {
                j += listFiles[i].length();
            }
        }
        return j;
    }

    public static ArrayList<File> d(File file) throws FileNotFoundException {
        ArrayList<File> arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                if (listFiles[i].isDirectory()) {
                    arrayList.addAll(d(listFiles[i]));
                } else {
                    arrayList.add(listFiles[i]);
                }
            }
        }
        return arrayList;
    }

    public static void e(File file) throws FileNotFoundException {
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        for (int i = 0; i < length; i++) {
            if (listFiles[i].isDirectory()) {
                e(listFiles[i]);
            } else {
                listFiles[i].delete();
            }
        }
    }

    public static void a(File file, int i) throws FileNotFoundException {
        c(file);
        if (c(file) > ((long) ((i * 1024) * 1024))) {
            e(file);
        }
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf < 0) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }

    public static byte[] c(String str) throws IOException {
        IOException e;
        Throwable th;
        File file = new File(str);
        if (file.exists()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 1024);
                        if (-1 == read) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    byteArrayOutputStream.close();
                    return toByteArray;
                } catch (IOException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                        byteArrayOutputStream.close();
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
                bufferedInputStream = null;
                e.printStackTrace();
                throw e;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                bufferedInputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        }
        throw new FileNotFoundException(str);
    }

    public static byte[] d(String str) throws IOException {
        IOException e;
        Throwable th;
        FileChannel fileChannel = null;
        File file = new File(str);
        if (file.exists()) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileChannel = fileInputStream.getChannel();
                    ByteBuffer allocate = ByteBuffer.allocate((int) fileChannel.size());
                    do {
                    } while (fileChannel.read(allocate) > 0);
                    byte[] array = allocate.array();
                    try {
                        fileChannel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return array;
                } catch (IOException e4) {
                    e = e4;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileChannel.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                        throw th;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                fileInputStream = fileChannel;
                e.printStackTrace();
                throw e;
            } catch (Throwable th3) {
                th = th3;
                Object obj = fileChannel;
                fileChannel.close();
                fileInputStream.close();
                throw th;
            }
        }
        throw new FileNotFoundException(str);
    }

    public static byte[] e(String str) throws IOException {
        IOException e;
        Throwable th;
        FileChannel fileChannel = null;
        try {
            FileChannel channel = new RandomAccessFile(str, "r").getChannel();
            try {
                MappedByteBuffer load = channel.map(MapMode.READ_ONLY, 0, channel.size()).load();
                System.out.println(load.isLoaded());
                byte[] bArr = new byte[((int) channel.size())];
                if (load.remaining() > 0) {
                    load.get(bArr, 0, load.remaining());
                }
                try {
                    channel.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return bArr;
            } catch (IOException e3) {
                IOException iOException = e3;
                fileChannel = channel;
                e2 = iOException;
                try {
                    e2.printStackTrace();
                    throw e2;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        fileChannel.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileChannel = channel;
                th = th4;
                fileChannel.close();
                throw th;
            }
        } catch (IOException e4) {
            e2 = e4;
            e2.printStackTrace();
            throw e2;
        }
    }

    public static void b(String str, String str2) {
        try {
            if (new File(str).exists()) {
                InputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                byte[] bArr = new byte[1444];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
