package com.google.api.client.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class IOUtils {
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(inputStream, outputStream, true);
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        try {
            ByteStreams.copy(inputStream, outputStream);
        } finally {
            if (z) {
                inputStream.close();
            }
        }
    }

    public static long computeLength(StreamingContent streamingContent) throws IOException {
        OutputStream byteCountingOutputStream = new ByteCountingOutputStream();
        try {
            streamingContent.writeTo(byteCountingOutputStream);
            return byteCountingOutputStream.count;
        } finally {
            byteCountingOutputStream.close();
        }
    }

    @Beta
    public static byte[] serialize(Object obj) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        serialize(obj, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Beta
    public static void serialize(Object obj, OutputStream outputStream) throws IOException {
        try {
            new ObjectOutputStream(outputStream).writeObject(obj);
        } finally {
            outputStream.close();
        }
    }

    @Beta
    public static <S extends Serializable> S deserialize(byte[] bArr) throws IOException {
        if (bArr == null) {
            return null;
        }
        return deserialize(new ByteArrayInputStream(bArr));
    }

    @Beta
    public static <S extends Serializable> S deserialize(InputStream inputStream) throws IOException {
        try {
            Serializable serializable = (Serializable) new ObjectInputStream(inputStream).readObject();
            inputStream.close();
            return serializable;
        } catch (Throwable e) {
            IOException iOException = new IOException("Failed to deserialize object");
            iOException.initCause(e);
            throw iOException;
        } catch (Throwable th) {
            inputStream.close();
        }
    }

    @Beta
    public static boolean isSymbolicLink(File file) throws IOException {
        boolean z = false;
        try {
            Class cls = Class.forName("java.nio.file.Files");
            Class cls2 = Class.forName("java.nio.file.Path");
            Object invoke = File.class.getMethod("toPath", new Class[0]).invoke(file, new Object[0]);
            return ((Boolean) cls.getMethod("isSymbolicLink", new Class[]{cls2}).invoke(null, new Object[]{invoke})).booleanValue();
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            Throwables.propagateIfPossible(cause, IOException.class);
            throw new RuntimeException(cause);
        } catch (ClassNotFoundException e2) {
            if (File.separatorChar == '\\') {
                return z;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return file.getCanonicalFile().equals(file.getAbsoluteFile()) ? true : z;
        } catch (IllegalArgumentException e3) {
            if (File.separatorChar == '\\') {
                return z;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file.getCanonicalFile().equals(file.getAbsoluteFile())) {
            }
            return file.getCanonicalFile().equals(file.getAbsoluteFile()) ? true : z;
        } catch (SecurityException e4) {
            if (File.separatorChar == '\\') {
                return z;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file.getCanonicalFile().equals(file.getAbsoluteFile())) {
            }
            return file.getCanonicalFile().equals(file.getAbsoluteFile()) ? true : z;
        } catch (IllegalAccessException e5) {
            if (File.separatorChar == '\\') {
                return z;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file.getCanonicalFile().equals(file.getAbsoluteFile())) {
            }
            return file.getCanonicalFile().equals(file.getAbsoluteFile()) ? true : z;
        } catch (NoSuchMethodException e6) {
            if (File.separatorChar == '\\') {
                return z;
            }
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file.getCanonicalFile().equals(file.getAbsoluteFile())) {
            }
            return file.getCanonicalFile().equals(file.getAbsoluteFile()) ? true : z;
        }
    }
}
