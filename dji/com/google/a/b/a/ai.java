package com.google.a.b.a;

import com.google.a.a;
import com.google.a.r;
import com.google.android.gms.location.places.Place;
import java.util.regex.Pattern;

public final class ai extends u {
    private static final Pattern a = Pattern.compile("[IOQ]");
    private static final Pattern b = Pattern.compile("[A-Z0-9]{17}");

    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public ah a(r rVar) {
        if (rVar.d() != a.CODE_39) {
            return null;
        }
        CharSequence trim = a.matcher(rVar.a()).replaceAll("").trim();
        if (!b.matcher(trim).matches()) {
            return null;
        }
        try {
            if (!a(trim)) {
                return null;
            }
            CharSequence substring = trim.substring(0, 3);
            return new ah(trim, substring, trim.substring(3, 9), trim.substring(9, 17), b(substring), trim.substring(3, 8), c(trim.charAt(9)), trim.charAt(10), trim.substring(11));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static boolean a(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i += a(i2 + 1) * b(charSequence.charAt(i2));
        }
        if (charSequence.charAt(8) == b(i % 11)) {
            return true;
        }
        return false;
    }

    private static int b(char c) {
        if (c >= 'A' && c <= 'I') {
            return (c - 65) + 1;
        }
        if (c >= 'J' && c <= 'R') {
            return (c - 74) + 1;
        }
        if (c >= 'S' && c <= 'Z') {
            return (c - 83) + 2;
        }
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        throw new IllegalArgumentException();
    }

    private static int a(int i) {
        if (i >= 1 && i <= 7) {
            return 9 - i;
        }
        if (i == 8) {
            return 10;
        }
        if (i == 9) {
            return 0;
        }
        if (i >= 10 && i <= 17) {
            return 19 - i;
        }
        throw new IllegalArgumentException();
    }

    private static char b(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    private static int c(char c) {
        if (c >= 'E' && c <= 'H') {
            return (c - 69) + 1984;
        }
        if (c >= 'J' && c <= 'N') {
            return (c - 74) + 1988;
        }
        if (c == 'P') {
            return 1993;
        }
        if (c >= 'R' && c <= 'T') {
            return (c - 82) + 1994;
        }
        if (c >= 'V' && c <= 'Y') {
            return (c - 86) + 1997;
        }
        if (c >= '1' && c <= '9') {
            return (c - 49) + 2001;
        }
        if (c >= 'A' && c <= 'D') {
            return (c - 65) + 2010;
        }
        throw new IllegalArgumentException();
    }

    private static String b(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        switch (charAt) {
            case '1':
            case '4':
            case '5':
                return "US";
            case '2':
                return "CA";
            case '3':
                if (charAt2 >= 'A' && charAt2 <= 'W') {
                    return "MX";
                }
            case '9':
                if ((charAt2 >= 'A' && charAt2 <= 'E') || (charAt2 >= '3' && charAt2 <= '9')) {
                    return "BR";
                }
            case Place.TYPE_PLACE_OF_WORSHIP /*74*/:
                if (charAt2 >= 'A' && charAt2 <= 'T') {
                    return "JP";
                }
            case 'K':
                if (charAt2 >= 'L' && charAt2 <= 'R') {
                    return "KO";
                }
            case 'L':
                return "CN";
            case 'M':
                if (charAt2 >= 'A' && charAt2 <= 'E') {
                    return "IN";
                }
            case Place.TYPE_SHOE_STORE /*83*/:
                if (charAt2 >= 'A' && charAt2 <= 'M') {
                    return "UK";
                }
                if (charAt2 >= 'N' && charAt2 <= 'T') {
                    return "DE";
                }
                break;
            case Place.TYPE_STADIUM /*86*/:
                if (charAt2 >= 'F' && charAt2 <= 'R') {
                    return "FR";
                }
                if (charAt2 >= 'S' && charAt2 <= 'W') {
                    return "ES";
                }
                break;
            case Place.TYPE_STORAGE /*87*/:
                return "DE";
            case 'X':
                if (charAt2 == '0' || (charAt2 >= '3' && charAt2 <= '9')) {
                    return "RU";
                }
            case 'Z':
                if (charAt2 >= 'A' && charAt2 <= 'R') {
                    return "IT";
                }
        }
        return null;
    }
}
