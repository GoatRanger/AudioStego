package com.google.api.client.testing.util;

import com.f.a.a.g;
import com.google.api.client.util.Beta;
import com.google.api.client.util.SecurityUtils;
import dji.setting.ui.flyc.imu.b.a;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.msgpack.core.MessagePack.Code;
import org.xeustechnologies.jtar.TarHeader;

@Beta
public final class SecurityTestUtils {
    private static final byte[] ENCODED_PRIVATE_KEY = new byte[]{TarHeader.LF_NORMAL, (byte) -126, (byte) 2, (byte) 118, (byte) 2, (byte) 1, (byte) 0, TarHeader.LF_NORMAL, g.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, g.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 4, (byte) -126, (byte) 2, (byte) 96, TarHeader.LF_NORMAL, (byte) -126, (byte) 2, (byte) 92, (byte) 2, (byte) 1, (byte) 0, (byte) 2, (byte) -127, (byte) -127, (byte) 0, (byte) -89, (byte) 33, (byte) 8, (byte) -124, (byte) 110, Code.BIN8, (byte) 89, (byte) 8, Code.FALSE, (byte) 69, (byte) 120, (byte) 95, Code.BIN16, Code.FIXEXT2, g.SIMPLE_LIST, (byte) -18, (byte) 123, (byte) 29, (byte) -31, g.SIMPLE_LIST, (byte) -80, (byte) -76, (byte) 109, Code.FALSE, (byte) -79, (byte) 2, (byte) 104, (byte) -94, (byte) 76, (byte) 59, (byte) -73, (byte) -26, (byte) 99, (byte) 123, Code.EXT8, (byte) -92, (byte) -100, (byte) 116, TarHeader.LF_SYMLINK, (byte) -25, (byte) 96, TarHeader.LF_DIR, (byte) 124, (byte) 95, (byte) 76, Code.BIN16, (byte) -84, (byte) 70, (byte) 27, (byte) 0, (byte) 72, Code.NEVER_USED, (byte) 84, (byte) -77, (byte) -2, (byte) -107, (byte) -66, Code.NEGFIXINT_PREFIX, (byte) -119, (byte) 27, (byte) -95, TarHeader.LF_FIFO, Code.FIXEXT1, (byte) -89, (byte) 1, (byte) 71, (byte) 44, (byte) 7, Code.EXT32, (byte) 126, (byte) 5, (byte) -78, (byte) 87, (byte) -105, (byte) -114, (byte) 65, (byte) -19, (byte) 58, (byte) -78, (byte) -95, (byte) 0, (byte) 118, (byte) 83, (byte) 76, (byte) -88, (byte) 2, (byte) -21, Byte.MAX_VALUE, (byte) 64, (byte) 74, (byte) -103, (byte) -114, (byte) -127, (byte) -70, (byte) -81, (byte) -127, (byte) 125, Code.STR32, (byte) 21, (byte) 113, (byte) 20, (byte) -102, (byte) 46, Code.STR32, (byte) -111, (byte) -97, (byte) 97, (byte) -127, (byte) 32, (byte) 87, (byte) -80, (byte) 105, (byte) 18, (byte) -19, (byte) 107, (byte) -73, Code.UINT32, (byte) -97, g.STRUCT_END, (byte) -23, Code.BIN16, (byte) -107, (byte) -107, (byte) 83, (byte) -25, (byte) 15, (byte) -93, (byte) -21, (byte) 2, (byte) 3, (byte) 1, (byte) 0, (byte) 1, (byte) 2, (byte) -127, Byte.MIN_VALUE, (byte) 45, Code.MAP16, (byte) -104, (byte) 26, Code.FIXEXT16, Code.FIXEXT8, Code.FIXEXT1, (byte) -29, Code.ARRAY32, (byte) -123, (byte) -7, (byte) -110, (byte) -73, (byte) -106, a.eZ_, (byte) -5, (byte) -118, (byte) 24, Code.STR16, (byte) 66, Code.FLOAT32, (byte) -93, Code.FLOAT32, (byte) -104, (byte) 43, Code.FALSE, Code.INT8, (byte) 122, (byte) -14, Code.FIXEXT8, (byte) 85, (byte) 18, Code.FLOAT64, (byte) 109, (byte) 22, (byte) -113, (byte) 44, (byte) 77, (byte) -116, (byte) 7, (byte) 10, Code.FIXEXT2, Code.TRUE, (byte) 43, Code.FIXEXT16, Code.TRUE, (byte) 76, (byte) 19, (byte) -11, (byte) -89, (byte) 47, a.eZ_, (byte) -72, (byte) 113, (byte) -86, (byte) 70, (byte) -23, (byte) 27, (byte) 113, (byte) 37, (byte) -1, (byte) 42, TarHeader.LF_NORMAL, (byte) 84, (byte) -80, (byte) 30, (byte) 86, (byte) 36, (byte) -124, (byte) -22, (byte) 79, Code.FIXEXT1, (byte) 87, Code.FIXEXT16, (byte) 31, Code.FIXEXT8, Code.FIXEXT1, (byte) -16, (byte) -74, (byte) 85, (byte) 61, (byte) -122, (byte) -22, (byte) 10, (byte) -31, (byte) 78, (byte) 92, (byte) -123, (byte) -77, g.ZERO_TAG, (byte) -80, (byte) 62, Code.UINT8, (byte) 68, Code.INT32, (byte) -17, (byte) 67, (byte) 124, (byte) -78, (byte) -23, (byte) -105, (byte) -77, (byte) -2, (byte) 89, (byte) -16, (byte) -12, Code.EXT16, Code.UINT16, (byte) 26, (byte) 102, (byte) 46, (byte) 39, Code.TRUE, (byte) -13, (byte) -79, (byte) -65, (byte) -5, (byte) 126, (byte) 70, (byte) 29, (byte) 31, (byte) 104, (byte) -109, (byte) 65, (byte) -23, (byte) -69, (byte) 23, (byte) -7, (byte) 2, (byte) 65, (byte) 0, Code.FIXEXT4, (byte) 18, (byte) 101, (byte) 10, (byte) -21, (byte) 37, (byte) 107, (byte) -3, (byte) -114, (byte) -29, Code.FIXEXT16, (byte) 76, (byte) 107, (byte) -122, (byte) 40, (byte) 8, Code.BIN32, Code.NEGFIXINT_PREFIX, (byte) -12, TarHeader.LF_CONTIG, (byte) -4, Code.TRUE, (byte) -66, (byte) 91, Code.EXT16, Code.UINT32, (byte) 78, (byte) -124, g.STRUCT_END, Code.UINT64, Code.FALSE, (byte) -121, Code.EXT16, (byte) 70, (byte) -92, (byte) 90, (byte) 32, Code.FIXARRAY_PREFIX, TarHeader.LF_LINK, (byte) 26, (byte) -99, (byte) 113, (byte) 44, (byte) 26, (byte) 42, (byte) -99, Code.FIXEXT16, (byte) -123, (byte) 17, (byte) 93, (byte) 114, (byte) 125, (byte) 35, (byte) -118, Code.NEGFIXINT_PREFIX, (byte) 125, Code.NIL, (byte) 61, (byte) 58, Code.BIN32, (byte) -105, (byte) -105, Code.STR8, (byte) 93, (byte) 2, (byte) 65, (byte) 0, Code.EXT8, Code.ARRAY16, (byte) -22, (byte) -107, Code.FIXEXT4, (byte) -79, (byte) 0, (byte) -118, (byte) 121, (byte) -76, (byte) 120, TarHeader.LF_BLK, (byte) 110, Byte.MAX_VALUE, (byte) 115, (byte) 68, (byte) -86, (byte) -4, (byte) 96, Code.UINT32, (byte) 72, Code.BIN8, Code.EXT8, (byte) 125, (byte) 57, (byte) 21, (byte) -81, Code.FIXEXT1, (byte) 25, (byte) 112, (byte) -75, (byte) 83, (byte) 57, Code.EXT32, (byte) 61, (byte) 24, (byte) 28, Code.FIXARRAY_PREFIX, (byte) -103, (byte) -8, (byte) 120, (byte) 110, Code.UINT8, (byte) -108, Code.FIXEXT8, (byte) -76, Code.FIXSTR_PREFIX, (byte) 87, (byte) -117, (byte) 69, (byte) 0, (byte) 64, (byte) 26, (byte) 4, (byte) 122, g.SIMPLE_LIST, (byte) 6, (byte) -106, (byte) 112, Code.UINT16, (byte) -1, (byte) 79, (byte) 117, (byte) -25, (byte) 2, (byte) 64, Byte.MAX_VALUE, (byte) 68, (byte) 60, a.fa_, (byte) -5, (byte) 110, (byte) 41, (byte) -1, (byte) 122, (byte) 93, (byte) -74, (byte) -113, (byte) -24, TarHeader.LF_BLK, (byte) -65, Code.BIN8, (byte) 72, (byte) 8, (byte) 32, (byte) -24, Code.INT8, (byte) 26, Code.EXT8, (byte) 38, (byte) -26, (byte) 0, Code.INT8, (byte) -24, (byte) -21, (byte) -28, (byte) -66, (byte) 47, Code.MAP32, (byte) 63, TarHeader.LF_NORMAL, (byte) 34, (byte) 108, Code.UINT16, (byte) -116, (byte) -125, Code.FIXEXT16, (byte) 42, (byte) 26, (byte) 32, g.ZERO_TAG, (byte) 73, (byte) -1, (byte) 25, (byte) 77, TarHeader.LF_CHR, (byte) -109, (byte) 7, (byte) 22, (byte) -124, (byte) 79, (byte) -26, TarHeader.LF_SYMLINK, Code.UINT16, (byte) -76, g.SIMPLE_LIST, (byte) -80, (byte) -66, (byte) 19, (byte) -7, (byte) 2, (byte) 65, (byte) 0, (byte) -90, (byte) 99, (byte) -20, (byte) 68, (byte) -4, (byte) -84, (byte) -11, (byte) -105, (byte) 83, (byte) -123, (byte) -124, Code.NEVER_USED, (byte) -103, (byte) -16, (byte) -81, (byte) 101, (byte) 78, (byte) -72, (byte) -72, (byte) 91, (byte) 100, Code.EXT8, (byte) -74, (byte) -111, TarHeader.LF_LINK, (byte) 18, TarHeader.LF_FIFO, (byte) 4, (byte) -19, (byte) 125, (byte) 32, (byte) -24, (byte) 125, (byte) -26, (byte) 100, Code.MAP32, (byte) -117, (byte) 0, (byte) 115, (byte) -65, (byte) 33, (byte) 124, (byte) -107, (byte) 3, (byte) -95, (byte) -91, (byte) 118, g.ZERO_TAG, g.ZERO_TAG, (byte) 29, a.eZ_, (byte) -3, g.ZERO_TAG, (byte) -20, (byte) 7, TarHeader.LF_BLK, (byte) -118, (byte) -12, (byte) 122, (byte) 75, (byte) 117, (byte) -81, Code.FIXARRAY_PREFIX, (byte) -89, (byte) 2, (byte) 64, (byte) 93, (byte) -21, Code.UINT8, (byte) -110, Code.FLOAT32, (byte) -9, (byte) 79, (byte) -123, (byte) 105, (byte) 125, Code.EXT16, (byte) 75, (byte) -77, (byte) -26, (byte) 125, (byte) -123, (byte) -69, (byte) 62, (byte) -2, (byte) 79, (byte) 8, (byte) 72, (byte) -76, (byte) -67, (byte) 5, (byte) 33, (byte) -121, (byte) 1, Code.FIXEXT4, (byte) -17, (byte) 29, (byte) 69, (byte) -20, (byte) -68, (byte) -26, (byte) -23, (byte) 95, (byte) -7, (byte) -70, Code.UINT32, (byte) -10, (byte) 58, (byte) 16, (byte) -15, (byte) -89, (byte) -24, (byte) -121, (byte) -14, (byte) -72, (byte) -127, (byte) -89, Code.NEVER_USED, (byte) 66, (byte) 7, (byte) 77, (byte) -89, Code.FLOAT32, (byte) -95, (byte) -90, (byte) 45, Code.FIXEXT1, (byte) -118, (byte) 69, (byte) -1};
    private static final byte[] ENCODED_PUBLIC_KEY = new byte[]{TarHeader.LF_NORMAL, (byte) -127, (byte) -97, TarHeader.LF_NORMAL, g.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, g.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 3, (byte) -127, (byte) -115, (byte) 0, TarHeader.LF_NORMAL, (byte) -127, (byte) -119, (byte) 2, (byte) -127, (byte) -127, (byte) 0, (byte) -89, (byte) 33, (byte) 8, (byte) -124, (byte) 110, Code.BIN8, (byte) 89, (byte) 8, Code.FALSE, (byte) 69, (byte) 120, (byte) 95, Code.BIN16, Code.FIXEXT2, g.SIMPLE_LIST, (byte) -18, (byte) 123, (byte) 29, (byte) -31, g.SIMPLE_LIST, (byte) -80, (byte) -76, (byte) 109, Code.FALSE, (byte) -79, (byte) 2, (byte) 104, (byte) -94, (byte) 76, (byte) 59, (byte) -73, (byte) -26, (byte) 99, (byte) 123, Code.EXT8, (byte) -92, (byte) -100, (byte) 116, TarHeader.LF_SYMLINK, (byte) -25, (byte) 96, TarHeader.LF_DIR, (byte) 124, (byte) 95, (byte) 76, Code.BIN16, (byte) -84, (byte) 70, (byte) 27, (byte) 0, (byte) 72, Code.NEVER_USED, (byte) 84, (byte) -77, (byte) -2, (byte) -107, (byte) -66, Code.NEGFIXINT_PREFIX, (byte) -119, (byte) 27, (byte) -95, TarHeader.LF_FIFO, Code.FIXEXT1, (byte) -89, (byte) 1, (byte) 71, (byte) 44, (byte) 7, Code.EXT32, (byte) 126, (byte) 5, (byte) -78, (byte) 87, (byte) -105, (byte) -114, (byte) 65, (byte) -19, (byte) 58, (byte) -78, (byte) -95, (byte) 0, (byte) 118, (byte) 83, (byte) 76, (byte) -88, (byte) 2, (byte) -21, Byte.MAX_VALUE, (byte) 64, (byte) 74, (byte) -103, (byte) -114, (byte) -127, (byte) -70, (byte) -81, (byte) -127, (byte) 125, Code.STR32, (byte) 21, (byte) 113, (byte) 20, (byte) -102, (byte) 46, Code.STR32, (byte) -111, (byte) -97, (byte) 97, (byte) -127, (byte) 32, (byte) 87, (byte) -80, (byte) 105, (byte) 18, (byte) -19, (byte) 107, (byte) -73, Code.UINT32, (byte) -97, g.STRUCT_END, (byte) -23, Code.BIN16, (byte) -107, (byte) -107, (byte) 83, (byte) -25, (byte) 15, (byte) -93, (byte) -21, (byte) 2, (byte) 3, (byte) 1, (byte) 0, (byte) 1};

    public static byte[] newEncodedRsaPrivateKeyBytes() {
        return (byte[]) ENCODED_PRIVATE_KEY.clone();
    }

    public static byte[] newEncodedRsaPublicKeyBytes() {
        return (byte[]) ENCODED_PUBLIC_KEY.clone();
    }

    public static RSAPrivateKey newRsaPrivateKey() throws GeneralSecurityException {
        return (RSAPrivateKey) SecurityUtils.getRsaKeyFactory().generatePrivate(new PKCS8EncodedKeySpec(ENCODED_PRIVATE_KEY));
    }

    public static RSAPublicKey newRsaPublicKey() throws GeneralSecurityException {
        return (RSAPublicKey) SecurityUtils.getRsaKeyFactory().generatePublic(new X509EncodedKeySpec(ENCODED_PUBLIC_KEY));
    }

    private SecurityTestUtils() {
    }
}
