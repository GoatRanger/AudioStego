package dji.pilot.c;

import java.util.Locale;

public class c {
    private static String[] a = new String[]{"ALB", "AND", "AUT", "BLR", "BEL", "BIH", "BGR", "HRV", "CYP", "CZE", "DNK", "EST", "FRO", "FIN", "FRA", "DEU", "GIB", "GRC", "HUN", "ISL", "IRL", "ITA", "LVA", "LIE", "LTU", "LUX", "MKD", "MLT", "MDA", "MCO", "NLD", "NOR", "POL", "PRT", "ROU", "RUS", "SMR", "SRB", "SVK", "SVN", "ESP", "SWE", "CHE", "UKR", "GBR", "VAT", "RSB", "IMN", "XKX", "MNE"};

    public static boolean a() {
        int i = 0;
        while (i < a.length) {
            try {
                if (a[i].equals(Locale.getDefault().getISO3Country())) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
