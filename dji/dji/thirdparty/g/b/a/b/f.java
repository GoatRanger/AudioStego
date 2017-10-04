package dji.thirdparty.g.b.a.b;

import dji.thirdparty.g.c.c;
import dji.thirdparty.g.e;
import java.io.IOException;
import java.io.InputStream;

public class f extends g {
    public f(int i, int i2, InputStream inputStream) throws e, IOException {
        super(i, i2);
        if (f()) {
            System.out.println("SOSSegment marker_length: " + i2);
        }
        c.b("SOS", i2);
        int a = a("number_of_components_in_scan", inputStream, "Not a Valid JPEG File");
        c.b("number_of_components_in_scan", a);
        for (int i3 = 0; i3 < a; i3++) {
            c.b("scan_component_selector", a("scan_component_selector", inputStream, "Not a Valid JPEG File"));
            c.b("ac_dc_entrooy_coding_table_selector", a("ac_dc_entrooy_coding_table_selector", inputStream, "Not a Valid JPEG File"));
        }
        c.b("start_of_spectral_selection", a("start_of_spectral_selection", inputStream, "Not a Valid JPEG File"));
        c.b("end_of_spectral_selection", a("end_of_spectral_selection", inputStream, "Not a Valid JPEG File"));
        c.b("successive_approximation_bit_position", a("successive_approximation_bit_position", inputStream, "Not a Valid JPEG File"));
        if (f()) {
            System.out.println("");
        }
    }

    public String a() {
        return "SOS (" + b() + ")";
    }
}
