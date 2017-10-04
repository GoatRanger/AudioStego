package com.google.api.client.util.escape;

import android.support.v4.media.TransportMediator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class PercentEscaper extends UnicodeEscaper {
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    public static final String SAFEUSERINFOCHARS_URLENCODER = "-_.!~*'():$&,;=";
    public static final String SAFE_PLUS_RESERVED_CHARS_URLENCODER = "-_.!~*'()@:$&,;=+/?";
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final char[] URI_ESCAPED_SPACE = new char[]{'+'};
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        } else if (z && str.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        } else if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        } else {
            this.plusForSpace = z;
            this.safeOctets = createSafeOctets(str);
        }
    }

    private static boolean[] createSafeOctets(String str) {
        int i;
        int i2 = 0;
        char[] toCharArray = str.toCharArray();
        int i3 = 122;
        for (char max : toCharArray) {
            i3 = Math.max(max, i3);
        }
        boolean[] zArr = new boolean[(i3 + 1)];
        for (i = 48; i <= 57; i++) {
            zArr[i] = true;
        }
        for (i = 65; i <= 90; i++) {
            zArr[i] = true;
        }
        for (i = 97; i <= 122; i++) {
            zArr[i] = true;
        }
        i = toCharArray.length;
        while (i2 < i) {
            zArr[toCharArray[i2]] = true;
            i2++;
        }
        return zArr;
    }

    protected int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    protected char[] escape(int i) {
        if (i < this.safeOctets.length && this.safeOctets[i]) {
            return null;
        }
        if (i == 32 && this.plusForSpace) {
            return URI_ESCAPED_SPACE;
        }
        if (i <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
            return new char[]{'%', UPPER_HEX_DIGITS[i & 15], UPPER_HEX_DIGITS[i >>> 4]};
        } else if (i <= 2047) {
            r0 = new char[6];
            r0[0] = '%';
            r0[3] = '%';
            r0[5] = UPPER_HEX_DIGITS[i & 15];
            r1 = i >>> 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[2] = UPPER_HEX_DIGITS[r1 & 15];
            r0[1] = UPPER_HEX_DIGITS[(r1 >>> 4) | 12];
            return r0;
        } else if (i <= 65535) {
            r0 = new char[9];
            r1 = i >>> 4;
            r0[7] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r0[2] = UPPER_HEX_DIGITS[r1 >>> 2];
            return r0;
        } else if (i <= 1114111) {
            r0 = new char[12];
            r1 = i >>> 4;
            r0[10] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[8] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[7] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r0[2] = UPPER_HEX_DIGITS[(r1 >>> 2) & 7];
            return r0;
        } else {
            throw new IllegalArgumentException("Invalid unicode character value " + i);
        }
    }
}
