package com.google.a.d.c;

import android.support.v4.media.TransportMediator;
import com.facebook.internal.l;
import com.google.api.client.http.HttpStatusCodes;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.b.a.a;
import it.sauronsoftware.ftp4j.FTPCodes;

public final class i {
    private static final int[] a = new int[]{5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] b = new int[][]{new int[]{228, 48, 15, TrackerEvent.PositioningOnlineOutdoor, 62}, new int[]{23, 68, 144, 134, 240, 92, 254}, new int[]{28, 24, 185, 166, TrackerEvent.RadioMapManualPrivateIndoor, 248, 116, 255, FTPCodes.RESTART_MARKER, 61}, new int[]{175, 138, 205, 12, 194, 168, 39, 245, 60, 97, 120}, new int[]{41, 153, 158, 91, 61, 42, 142, 213, 97, 178, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, 109, 129, 94, 254, FTPCodes.DATA_CONNECTION_OPEN, 48, 90, 188}, new int[]{15, 195, 244, 9, 233, 71, 168, 2, 188, 160, 153, 145, 253, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, l.f, 88, 205, 109, 39, 176, 21, 155, 197, 251, TrackerEvent.RadioMapManualPrivateIndoor, 155, 21, 5, 172, 254, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, 231, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, 249, TrackerEvent.PositioningHybridOutdoor, 17, 138, FTPCodes.RESTART_MARKER, 213, 141, 136, 120, 151, 233, 168, 93, 255}, new int[]{245, TransportMediator.KEYCODE_MEDIA_PAUSE, 242, 218, TransportMediator.KEYCODE_MEDIA_RECORD, 250, 162, 181, 102, 120, 84, 179, FTPCodes.SERVICE_READY_FOR_NEW_USER, 251, 80, 182, 229, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, FTPCodes.DATA_CONNECTION_OPEN, 98, 81, d.k}, new int[]{77, 193, 137, 31, 19, 38, 22, 153, 247, 105, 122, 2, 245, TrackerEvent.PositioningOfflinePrivateIndoor, 242, 8, 175, 95, 100, 9, 167, 105, FTPCodes.HELP_MESSAGE, TrackerEvent.PositioningOnlineOutdoor, 57, TrackerEvent.PositioningHybridOutdoor, 21, 1, 253, 57, 54, 101, 248, FTPCodes.SUPERFLOUS_COMMAND, 69, 50, 150, 177, FTPCodes.DATA_CONNECTION_CLOSING, 5, 9, 5}, new int[]{245, TrackerEvent.PositioningOfflineCommonIndoor, 172, TrackerEvent.RadioMapManualPrivateIndoor, 96, 32, 117, 22, 238, TrackerEvent.PositioningOfflinePrivateIndoor, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, TrackerEvent.PositioningOfflineOutdoor, 88, 120, 100, 66, 138, 186, 240, 82, 44, 176, 87, 187, 147, 160, 175, 69, 213, 92, 253, FTPCodes.DATA_CONNECTION_OPEN, 19}, new int[]{175, 9, TrackerEvent.RadioMapManualPrivateIndoor, 238, 12, 17, FTPCodes.SERVICE_READY_FOR_NEW_USER, 208, 100, 29, 175, 170, FTPCodes.USER_LOGGED_IN, 192, FTPCodes.NAME_SYSTEM_TIME, 235, 150, 159, 36, TrackerEvent.RadioMapManualPrivateIndoor, 38, 200, TrackerEvent.PositioningOfflineCommonIndoor, 54, 228, BuildConfig.VERSION_CODE, 218, 234, 117, 203, 29, 232, 144, 238, 22, 150, dji.pilot.usercenter.f.d.y, 117, 62, 207, 164, 13, 137, 245, TransportMediator.KEYCODE_MEDIA_PAUSE, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, 169, 50, 144, 210, 39, 118, FTPCodes.SUPERFLOUS_COMMAND, 188, dji.pilot.usercenter.f.d.y, 189, 143, 108, 196, 37, 185, d.k, 134, FTPCodes.USER_LOGGED_IN, 245, 63, 197, l.f, 250, 106, 185, 221, 175, 64, 114, 71, 161, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, TransportMediator.KEYCODE_MEDIA_RECORD, 188, 17, 163, 31, 176, 170, 4, 107, 232, 7, 94, 166, a.fw_, 124, 86, 47, 11, HttpStatusCodes.STATUS_CODE_NO_CONTENT}, new int[]{FTPCodes.SERVICE_READY_FOR_NEW_USER, 228, 173, 89, 251, 149, 159, 56, 89, 33, 147, 244, 154, 36, 73, TransportMediator.KEYCODE_MEDIA_PAUSE, 213, 136, 248, 180, 234, 197, 158, 177, 68, 122, 93, 213, 15, 160, FTPCodes.ENTER_PASSIVE_MODE, 236, 66, 139, 153, 185, FTPCodes.SUPERFLOUS_COMMAND, 167, 179, 25, FTPCodes.SERVICE_READY_FOR_NEW_USER, 232, 96, 210, 231, 136, TrackerEvent.RadioMapManualPrivateIndoor, 239, 181, 241, 59, 52, 172, 25, 49, 232, 211, 189, 64, 54, 108, 153, TrackerEvent.PositioningOfflineCommonIndoor, 63, 96, 103, 82, 186}};
    private static final int c = 301;
    private static final int[] d = new int[256];
    private static final int[] e = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            e[i2] = i;
            d[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private i() {
    }

    public static String a(String str, k kVar) {
        if (str.length() != kVar.i()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder stringBuilder = new StringBuilder(kVar.i() + kVar.j());
        stringBuilder.append(str);
        int a = kVar.a();
        if (a == 1) {
            stringBuilder.append(a((CharSequence) str, kVar.j()));
        } else {
            int i;
            stringBuilder.setLength(stringBuilder.capacity());
            int[] iArr = new int[a];
            int[] iArr2 = new int[a];
            int[] iArr3 = new int[a];
            for (i = 0; i < a; i++) {
                iArr[i] = kVar.a(i + 1);
                iArr2[i] = kVar.c(i + 1);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
            }
            for (int i2 = 0; i2 < a; i2++) {
                StringBuilder stringBuilder2 = new StringBuilder(iArr[i2]);
                for (i = i2; i < kVar.i(); i += a) {
                    stringBuilder2.append(str.charAt(i));
                }
                String a2 = a(stringBuilder2.toString(), iArr2[i2]);
                i = i2;
                int i3 = 0;
                while (i < iArr2[i2] * a) {
                    int i4 = i3 + 1;
                    stringBuilder.setCharAt(kVar.i() + i, a2.charAt(i3));
                    i += a;
                    i3 = i4;
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String a(CharSequence charSequence, int i) {
        return a(charSequence, 0, charSequence.length(), i);
    }

    private static String a(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i5 < a.length) {
            if (a[i5] == i3) {
                break;
            }
            i5++;
        }
        i5 = -1;
        if (i5 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr = b[i5];
        char[] cArr = new char[i3];
        for (i5 = 0; i5 < i3; i5++) {
            cArr[i5] = '\u0000';
        }
        for (int i6 = i; i6 < i + i2; i6++) {
            int charAt = charSequence.charAt(i6) ^ cArr[i3 - 1];
            i5 = i3 - 1;
            while (i5 > 0) {
                if (charAt == 0 || iArr[i5] == 0) {
                    cArr[i5] = cArr[i5 - 1];
                } else {
                    cArr[i5] = (char) (cArr[i5 - 1] ^ e[(d[charAt] + d[iArr[i5]]) % 255]);
                }
                i5--;
            }
            if (charAt == 0 || iArr[0] == 0) {
                cArr[0] = '\u0000';
            } else {
                cArr[0] = (char) e[(d[charAt] + d[iArr[0]]) % 255];
            }
        }
        char[] cArr2 = new char[i3];
        while (i4 < i3) {
            cArr2[i4] = cArr[(i3 - i4) - 1];
            i4++;
        }
        return String.valueOf(cArr2);
    }
}
