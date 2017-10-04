package dji.thirdparty.g.b.a.b;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.a.c;
import dji.thirdparty.g.b.a.a;
import java.io.PrintWriter;

public abstract class g extends c {
    public final int gt_;
    public final int gu_;

    public abstract String a();

    public g(int i, int i2) {
        this.gt_ = i;
        this.gu_ = i2;
    }

    public void a(PrintWriter printWriter) {
    }

    public String toString() {
        return "[Segment: " + a() + d.H;
    }

    public String b() {
        switch (this.gt_) {
            case 65281:
                return "For temporary private use in arithmetic coding";
            case a.q /*65472*/:
                return "Start Of Frame, Baseline DCT, Huffman coding";
            case a.r /*65473*/:
                return "Start Of Frame, Extended sequential DCT, Huffman coding";
            case a.s /*65474*/:
                return "Start Of Frame, Progressive DCT, Huffman coding";
            case a.t /*65475*/:
                return "Start Of Frame, Lossless (sequential), Huffman coding";
            case a.u /*65476*/:
                return "Define Huffman table(s)";
            case a.v /*65477*/:
                return "Start Of Frame, Differential sequential DCT, Huffman coding";
            case a.w /*65478*/:
                return "Start Of Frame, Differential progressive DCT, Huffman coding";
            case a.x /*65479*/:
                return "Start Of Frame, Differential lossless (sequential), Huffman coding";
            case a.y /*65480*/:
                return "Start Of Frame, Reserved for JPEG extensions, arithmetic coding";
            case a.z /*65481*/:
                return "Start Of Frame, Extended sequential DCT, arithmetic coding";
            case a.A /*65482*/:
                return "Start Of Frame, Progressive DCT, arithmetic coding";
            case a.B /*65483*/:
                return "Start Of Frame, Lossless (sequential), arithmetic coding";
            case a.C /*65484*/:
                return "Define arithmetic coding conditioning(s)";
            case a.D /*65485*/:
                return "Start Of Frame, Differential sequential DCT, arithmetic coding";
            case a.E /*65486*/:
                return "Start Of Frame, Differential progressive DCT, arithmetic coding";
            case a.F /*65487*/:
                return "Start Of Frame, Differential lossless (sequential), arithmetic coding";
            case 65488:
                return "Restart with modulo 8 count 0";
            case 65489:
                return "Restart with modulo 8 count 1";
            case 65490:
                return "Restart with modulo 8 count 2";
            case 65491:
                return "Restart with modulo 8 count 3";
            case 65492:
                return "Restart with modulo 8 count 4";
            case 65493:
                return "Restart with modulo 8 count 5";
            case 65494:
                return "Restart with modulo 8 count 6";
            case 65495:
                return "Restart with modulo 8 count 7";
            case 65496:
                return "Start of image";
            case 65497:
                return "End of image";
            case a.fv_ /*65498*/:
                return "Start of scan";
            case 65499:
                return "Define quantization table(s)";
            case 65500:
                return "Define number of lines";
            case 65501:
                return "Define restart interval";
            case 65502:
                return "Define hierarchical progression";
            case 65503:
                return "Expand reference component(s)";
            case 65534:
                return "Comment";
            default:
                if (this.gt_ >= 65282 && this.gt_ <= 65471) {
                    return "Reserved";
                }
                if (this.gt_ >= 65504 && this.gt_ <= a.o) {
                    return "APP" + (this.gt_ - 65504);
                }
                if (this.gt_ < 65520 || this.gt_ > 65533) {
                    return "Unknown";
                }
                return "JPG" + (this.gt_ - 65504);
        }
    }
}
