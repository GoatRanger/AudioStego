package com.e;

class bt {
    protected static byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = null;
        if (!(bArr == null || bArr.length == 0)) {
            try {
                int indexOf = new String(bArr, "UTF-8").indexOf(0);
                if (indexOf <= 0) {
                    i = 1;
                } else if (indexOf + 1 <= i) {
                    i = indexOf + 1;
                }
                bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr2[i - 1] = (byte) 0;
            } catch (Throwable th) {
                bc.a(th, "BytesTool", "StrBytesToVarBytes");
            }
        }
        return bArr2;
    }
}
