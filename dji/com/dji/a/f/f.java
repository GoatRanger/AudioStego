package com.dji.a.f;

import java.io.Closeable;
import java.io.IOException;

public final class f {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
