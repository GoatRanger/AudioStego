package com.here.odnp.ble;

import java.io.ByteArrayInputStream;

public class BleValidator {
    static final int AppleIBeaconAdvertisementIndicatorLSB = 2;
    static final int AppleIBeaconAdvertisementIndicatorMSB = 21;
    static final int AppleIBeaconManufacturerDataMinLength = 5;
    static final int CompleteListOf16bitUUIDsType = 3;
    static final int EddystoneLocationServiceUuidLSB = 170;
    static final int EddystoneLocationServiceUuidMSB = 254;
    static final int ListOf16bitUUIDsLength = 3;
    static final int ManufacturerAppleLSB = 76;
    static final int ManufacturerAppleMSB = 0;
    static final int ManufacturerSpecificDataTagType = 255;
    static final int NokiaLocationServiceUuidLSB = 174;
    static final int NokiaLocationServiceUuidMSB = 254;
    private static final String TAG = "odnp.ble.BleValidator";

    static boolean isSupportedBleTag(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (byteArrayInputStream.available() > 0) {
            int read = byteArrayInputStream.read();
            byteArrayInputStream.mark(0);
            if (read == 0 || read > byteArrayInputStream.available()) {
                return false;
            }
            switch (byteArrayInputStream.read()) {
                case 3:
                    if (read == 3) {
                        int read2 = byteArrayInputStream.read();
                        int read3 = byteArrayInputStream.read();
                        if (read2 == NokiaLocationServiceUuidLSB && read3 == 254) {
                            return true;
                        }
                        if (read2 == EddystoneLocationServiceUuidLSB && read3 == 254) {
                            return true;
                        }
                    }
                    continue;
                    break;
                case 255:
                    if (read >= 5 && byteArrayInputStream.read() == 76 && byteArrayInputStream.read() == 0 && byteArrayInputStream.read() == 2 && byteArrayInputStream.read() == 21) {
                        return true;
                    }
                default:
                    break;
            }
            byteArrayInputStream.reset();
            byteArrayInputStream.skip((long) read);
        }
        return false;
    }
}
