package dji.pilot.fpv.model;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class b$c extends DefaultHandler {
    private b$b a;
    private b$a b;
    private String c;

    b$c() {
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.a = new b$b();
        this.a.c = new ArrayList();
    }

    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if ("firmware".equals(str2)) {
            this.b = new b$a();
        } else {
            this.c = str2;
        }
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if ("firmware".equals(str2)) {
            this.a.c.add(this.b);
        } else if ("filename".equals(str2) || "compress".equals(str2) || "priority".equals(str2) || "product".equals(str2) || "version".equals(str2)) {
            this.c = "";
        }
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        String str = new String(cArr, i, i2);
        if ("filename".equals(this.c)) {
            this.b.a = str;
        } else if ("compress".equals(this.c)) {
            this.b.b = Integer.valueOf(str).intValue();
        } else if ("priority".equals(this.c)) {
            this.b.c = Integer.valueOf(str).intValue();
        } else if ("product".equals(this.c)) {
            this.a.a = str;
        } else if ("version".equals(this.c)) {
            this.a.b = str;
        }
    }
}
