package com.fasterxml.jackson.core;

import android.support.v4.media.TransportMediator;
import java.io.Serializable;
import java.util.Arrays;

public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    static final char PADDING_CHAR_NONE = '\u0000';
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    protected final transient int _maxLineLength;
    protected final String _name;
    protected final transient char _paddingChar;
    protected final transient boolean _usesPadding;

    public Base64Variant(String str, String str2, boolean z, char c, int i) {
        int i2 = 0;
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = i;
        int length = str2.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, this._base64ToAsciiC, 0);
        Arrays.fill(this._asciiToBase64, -1);
        while (i2 < length) {
            char c2 = this._base64ToAsciiC[i2];
            this._base64ToAsciiB[i2] = (byte) c2;
            this._asciiToBase64[c2] = i2;
            i2++;
        }
        if (z) {
            this._asciiToBase64[c] = -2;
        }
    }

    public Base64Variant(Base64Variant base64Variant, String str, int i) {
        this(base64Variant, str, base64Variant._usesPadding, base64Variant._paddingChar, i);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c, int i) {
        this._asciiToBase64 = new int[128];
        this._base64ToAsciiC = new char[64];
        this._base64ToAsciiB = new byte[64];
        this._name = str;
        Object obj = base64Variant._base64ToAsciiB;
        System.arraycopy(obj, 0, this._base64ToAsciiB, 0, obj.length);
        obj = base64Variant._base64ToAsciiC;
        System.arraycopy(obj, 0, this._base64ToAsciiC, 0, obj.length);
        obj = base64Variant._asciiToBase64;
        System.arraycopy(obj, 0, this._asciiToBase64, 0, obj.length);
        this._usesPadding = z;
        this._paddingChar = c;
        this._maxLineLength = i;
    }

    protected Object readResolve() {
        return Base64Variants.valueOf(this._name);
    }

    public String getName() {
        return this._name;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c) {
        return c == this._paddingChar;
    }

    public boolean usesPaddingChar(int i) {
        return i == this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public byte getPaddingByte() {
        return (byte) this._paddingChar;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public int decodeBase64Char(char c) {
        return c <= '' ? this._asciiToBase64[c] : -1;
    }

    public int decodeBase64Char(int i) {
        return i <= TransportMediator.KEYCODE_MEDIA_PAUSE ? this._asciiToBase64[i] : -1;
    }

    public int decodeBase64Byte(byte b) {
        return b <= Byte.MAX_VALUE ? this._asciiToBase64[b] : -1;
    }

    public char encodeBase64BitsAsChar(int i) {
        return this._base64ToAsciiC[i];
    }

    public int encodeBase64Chunk(int i, char[] cArr, int i2) {
        int i3 = i2 + 1;
        cArr[i2] = this._base64ToAsciiC[(i >> 18) & 63];
        int i4 = i3 + 1;
        cArr[i3] = this._base64ToAsciiC[(i >> 12) & 63];
        i3 = i4 + 1;
        cArr[i4] = this._base64ToAsciiC[(i >> 6) & 63];
        i4 = i3 + 1;
        cArr[i3] = this._base64ToAsciiC[i & 63];
        return i4;
    }

    public void encodeBase64Chunk(StringBuilder stringBuilder, int i) {
        stringBuilder.append(this._base64ToAsciiC[(i >> 18) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 12) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 6) & 63]);
        stringBuilder.append(this._base64ToAsciiC[i & 63]);
    }

    public int encodeBase64Partial(int i, int i2, char[] cArr, int i3) {
        int i4 = i3 + 1;
        cArr[i3] = this._base64ToAsciiC[(i >> 18) & 63];
        int i5 = i4 + 1;
        cArr[i4] = this._base64ToAsciiC[(i >> 12) & 63];
        if (this._usesPadding) {
            int i6 = i5 + 1;
            cArr[i5] = i2 == 2 ? this._base64ToAsciiC[(i >> 6) & 63] : this._paddingChar;
            i4 = i6 + 1;
            cArr[i6] = this._paddingChar;
            return i4;
        } else if (i2 != 2) {
            return i5;
        } else {
            i4 = i5 + 1;
            cArr[i5] = this._base64ToAsciiC[(i >> 6) & 63];
            return i4;
        }
    }

    public void encodeBase64Partial(StringBuilder stringBuilder, int i, int i2) {
        stringBuilder.append(this._base64ToAsciiC[(i >> 18) & 63]);
        stringBuilder.append(this._base64ToAsciiC[(i >> 12) & 63]);
        if (this._usesPadding) {
            stringBuilder.append(i2 == 2 ? this._base64ToAsciiC[(i >> 6) & 63] : this._paddingChar);
            stringBuilder.append(this._paddingChar);
        } else if (i2 == 2) {
            stringBuilder.append(this._base64ToAsciiC[(i >> 6) & 63]);
        }
    }

    public byte encodeBase64BitsAsByte(int i) {
        return this._base64ToAsciiB[i];
    }

    public int encodeBase64Chunk(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = this._base64ToAsciiB[(i >> 18) & 63];
        int i4 = i3 + 1;
        bArr[i3] = this._base64ToAsciiB[(i >> 12) & 63];
        i3 = i4 + 1;
        bArr[i4] = this._base64ToAsciiB[(i >> 6) & 63];
        i4 = i3 + 1;
        bArr[i3] = this._base64ToAsciiB[i & 63];
        return i4;
    }

    public int encodeBase64Partial(int i, int i2, byte[] bArr, int i3) {
        int i4 = i3 + 1;
        bArr[i3] = this._base64ToAsciiB[(i >> 18) & 63];
        int i5 = i4 + 1;
        bArr[i4] = this._base64ToAsciiB[(i >> 12) & 63];
        if (this._usesPadding) {
            byte b = (byte) this._paddingChar;
            int i6 = i5 + 1;
            bArr[i5] = i2 == 2 ? this._base64ToAsciiB[(i >> 6) & 63] : b;
            i4 = i6 + 1;
            bArr[i6] = b;
            return i4;
        } else if (i2 != 2) {
            return i5;
        } else {
            i4 = i5 + 1;
            bArr[i5] = this._base64ToAsciiB[(i >> 6) & 63];
            return i4;
        }
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public String encode(byte[] bArr, boolean z) {
        int i;
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder(((length >> 2) + length) + (length >> 3));
        if (z) {
            stringBuilder.append('\"');
        }
        int i2 = 0;
        int i3 = length - 3;
        int maxLineLength = getMaxLineLength() >> 2;
        while (i2 <= i3) {
            i = i2 + 1;
            int i4 = i + 1;
            i = i4 + 1;
            encodeBase64Chunk(stringBuilder, (((bArr[i2] << 8) | (bArr[i] & 255)) << 8) | (bArr[i4] & 255));
            i2 = maxLineLength - 1;
            if (i2 <= 0) {
                stringBuilder.append('\\');
                stringBuilder.append('n');
                i2 = getMaxLineLength() >> 2;
            }
            maxLineLength = i2;
            i2 = i;
        }
        i = length - i2;
        if (i > 0) {
            maxLineLength = i2 + 1;
            i2 = bArr[i2] << 16;
            if (i == 2) {
                length = maxLineLength + 1;
                i2 |= (bArr[maxLineLength] & 255) << 8;
            }
            encodeBase64Partial(stringBuilder, i2, i);
        }
        if (z) {
            stringBuilder.append('\"');
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return this._name;
    }
}
