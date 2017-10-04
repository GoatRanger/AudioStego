package dji.midware.media.f;

import com.facebook.internal.l;

public enum b {
    ftyp("ftyp"),
    wide("wide"),
    mdat("mdat"),
    moov("moov"),
    mvhd("mvhd"),
    udta("udta"),
    trak("trak"),
    tkhd("tkhd"),
    edts("edts"),
    mdia("mdia"),
    mdhd("mdhd"),
    hdlr("hdlr"),
    minf("minf"),
    vmhd("vmhd"),
    dinf("dinf"),
    stbl("stbl"),
    stsd("stsd"),
    avc1("avc1"),
    avcC("avcC"),
    stts("stts"),
    ctts("ctts"),
    stsc("sysc"),
    stsz("stsz"),
    stco("stco"),
    stss("stss"),
    OTHER(l.j);
    
    private String A;

    private b(String str) {
        this.A = "";
        this.A = str;
    }

    public String a() {
        return this.A;
    }

    public boolean a(String str) {
        return this.A.equals(str);
    }

    public static b find(String str) {
        b bVar = OTHER;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].a(str)) {
                return values()[i];
            }
        }
        return bVar;
    }
}
