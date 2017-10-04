package com.facebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.aa;
import dji.pilot.usercenter.protocol.d;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FacebookContentProvider extends ContentProvider {
    private static final String a = FacebookContentProvider.class.getName();
    private static final String b = "content://com.facebook.app.FacebookContentProvider";

    public static String a(String str, UUID uuid, String str2) {
        return String.format("%s%s/%s/%s", new Object[]{b, str, uuid.toString(), str2});
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        Pair a = a(uri);
        if (a == null) {
            throw new FileNotFoundException();
        }
        try {
            return ParcelFileDescriptor.open(aa.a((UUID) a.first, (String) a.second), 268435456);
        } catch (FileNotFoundException e) {
            Log.e(a, "Got unexpected exception:" + e);
            throw e;
        }
    }

    Pair<UUID, String> a(Uri uri) {
        try {
            String[] split = uri.getPath().substring(1).split(d.t);
            String str = split[0];
            return new Pair(UUID.fromString(str), split[1]);
        } catch (Exception e) {
            return null;
        }
    }
}
