package com.flurry.sdk;

import com.flurry.sdk.hl.a;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public final class gj {
    private static final String a = gj.class.getSimpleName();

    public static hl a(File file) {
        Closeable fileInputStream;
        Closeable dataInputStream;
        Throwable e;
        hl hlVar;
        if (file == null || !file.exists()) {
            return null;
        }
        jh aVar = new a();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                dataInputStream = new DataInputStream(fileInputStream);
            } catch (Exception e2) {
                e = e2;
                dataInputStream = null;
                try {
                    in.a(3, a, "Error loading legacy agent data.", e);
                    jz.a(dataInputStream);
                    jz.a(fileInputStream);
                    hlVar = null;
                    return hlVar;
                } catch (Throwable th) {
                    e = th;
                    jz.a(dataInputStream);
                    jz.a(fileInputStream);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                dataInputStream = null;
                jz.a(dataInputStream);
                jz.a(fileInputStream);
                throw e;
            }
            try {
                if (dataInputStream.readUnsignedShort() != 46586) {
                    in.a(3, a, "Unexpected file type");
                    jz.a(dataInputStream);
                    jz.a(fileInputStream);
                    return null;
                }
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                if (readUnsignedShort != 2) {
                    in.a(6, a, "Unknown agent file version: " + readUnsignedShort);
                    jz.a(dataInputStream);
                    jz.a(fileInputStream);
                    return null;
                }
                hlVar = (hl) aVar.b(dataInputStream);
                jz.a(dataInputStream);
                jz.a(fileInputStream);
                return hlVar;
            } catch (Exception e3) {
                e = e3;
                in.a(3, a, "Error loading legacy agent data.", e);
                jz.a(dataInputStream);
                jz.a(fileInputStream);
                hlVar = null;
                return hlVar;
            }
        } catch (Exception e4) {
            e = e4;
            dataInputStream = null;
            fileInputStream = null;
            in.a(3, a, "Error loading legacy agent data.", e);
            jz.a(dataInputStream);
            jz.a(fileInputStream);
            hlVar = null;
            return hlVar;
        } catch (Throwable th3) {
            e = th3;
            dataInputStream = null;
            fileInputStream = null;
            jz.a(dataInputStream);
            jz.a(fileInputStream);
            throw e;
        }
    }
}
