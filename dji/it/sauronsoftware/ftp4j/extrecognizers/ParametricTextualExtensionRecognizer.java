package it.sauronsoftware.ftp4j.extrecognizers;

import it.sauronsoftware.ftp4j.FTPTextualExtensionRecognizer;
import java.util.ArrayList;

public class ParametricTextualExtensionRecognizer implements FTPTextualExtensionRecognizer {
    private ArrayList exts = new ArrayList();

    public ParametricTextualExtensionRecognizer(String[] strArr) {
        for (String addExtension : strArr) {
            addExtension(addExtension);
        }
    }

    public ParametricTextualExtensionRecognizer(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof String) {
                addExtension((String) obj);
            }
        }
    }

    public void addExtension(String str) {
        synchronized (this.exts) {
            this.exts.add(str.toLowerCase());
        }
    }

    public void removeExtension(String str) {
        synchronized (this.exts) {
            this.exts.remove(str.toLowerCase());
        }
    }

    public String[] getExtensions() {
        String[] strArr;
        synchronized (this.exts) {
            int size = this.exts.size();
            strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = (String) this.exts.get(i);
            }
        }
        return strArr;
    }

    public boolean isTextualExt(String str) {
        boolean contains;
        synchronized (this.exts) {
            contains = this.exts.contains(str);
        }
        return contains;
    }
}
