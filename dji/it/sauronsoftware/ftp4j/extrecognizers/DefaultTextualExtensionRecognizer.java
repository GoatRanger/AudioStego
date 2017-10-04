package it.sauronsoftware.ftp4j.extrecognizers;

import java.io.BufferedReader;
import java.util.StringTokenizer;

public class DefaultTextualExtensionRecognizer extends ParametricTextualExtensionRecognizer {
    private static DefaultTextualExtensionRecognizer instance = null;
    private static final Object lock = new Object();

    public static DefaultTextualExtensionRecognizer getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new DefaultTextualExtensionRecognizer();
            }
        }
        return instance;
    }

    private DefaultTextualExtensionRecognizer() {
        BufferedReader bufferedReader = null;
        try {
            String[] strArr = new String[]{"abc acgi aip asm asp c c cc cc com conf cpp", "csh css cxx def el etx f f f77 f90 f90 flx", "for for g h h hh hh hlb htc htm html htmls", "htt htx idc jav jav java java js ksh list", "log lsp lst lsx m m mar mcf p pas php pl pl", "pm py rexx rt rt rtf rtx s scm scm sdml sgm", "sgm sgml sgml sh shtml shtml spc ssi talk", "tcl tcsh text tsv txt uil uni unis uri uris", "uu uue vcs wml wmls wsc xml zsh"};
            for (String stringTokenizer : strArr) {
                StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer);
                while (stringTokenizer2.hasMoreTokens()) {
                    addExtension(stringTokenizer2.nextToken());
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Exception e) {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Throwable th) {
        }
    }
}
