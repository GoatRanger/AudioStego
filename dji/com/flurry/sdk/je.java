package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class je<ObjectType> implements jh<ObjectType> {
    protected final jh<ObjectType> a;

    public je(jh<ObjectType> jhVar) {
        this.a = jhVar;
    }

    public void a(OutputStream outputStream, ObjectType objectType) throws IOException {
        if (this.a != null && outputStream != null && objectType != null) {
            this.a.a(outputStream, objectType);
        }
    }

    public ObjectType b(InputStream inputStream) throws IOException {
        if (this.a == null || inputStream == null) {
            return null;
        }
        return this.a.b(inputStream);
    }
}
