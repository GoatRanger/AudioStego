package dji.pilot2.media;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class b {
    public static String a(String str) {
        RandomAccessFile randomAccessFile;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        try {
            randomAccessFile = new RandomAccessFile(new File(str), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            randomAccessFile = null;
        }
        if (randomAccessFile == null) {
            return null;
        }
        try {
            if (b(randomAccessFile, byteOrder) != -2555935) {
                randomAccessFile.close();
                return null;
            }
            a(randomAccessFile, byteOrder);
            if (b(randomAccessFile, byteOrder) != 1165519206) {
                return null;
            }
            int i;
            if (b(randomAccessFile, byteOrder) == 18761) {
                byteOrder = ByteOrder.LITTLE_ENDIAN;
            }
            randomAccessFile.skipBytes(6);
            short a = a(randomAccessFile, byteOrder);
            byte[] bArr = new byte[12];
            for (short s = (short) 0; s < a; s++) {
                randomAccessFile.read(bArr);
                if ((short) 306 == ByteBuffer.wrap(bArr, 0, 2).order(byteOrder).getShort()) {
                    i = ByteBuffer.wrap(bArr, 8, 4).order(byteOrder).getInt() + 12;
                    break;
                }
            }
            i = 0;
            if (i <= 0) {
                return null;
            }
            randomAccessFile.seek((long) i);
            byte[] bArr2 = new byte[20];
            randomAccessFile.read(bArr2);
            return new String(bArr2, Charset.forName("ASCII"));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static short a(RandomAccessFile randomAccessFile, ByteOrder byteOrder) {
        byte[] bArr = new byte[2];
        try {
            randomAccessFile.read(bArr);
            return ByteBuffer.wrap(bArr, 0, 2).order(byteOrder).getShort();
        } catch (IOException e) {
            e.printStackTrace();
            return (short) 0;
        }
    }

    private static int b(RandomAccessFile randomAccessFile, ByteOrder byteOrder) {
        byte[] bArr = new byte[4];
        try {
            randomAccessFile.read(bArr);
            return ByteBuffer.wrap(bArr, 0, 4).order(byteOrder).getInt();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
