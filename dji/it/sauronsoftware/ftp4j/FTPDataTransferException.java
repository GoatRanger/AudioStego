package it.sauronsoftware.ftp4j;

public class FTPDataTransferException extends Exception {
    private static final long serialVersionUID = 1;

    public FTPDataTransferException(String str, Throwable th) {
        super(str, th);
    }

    public FTPDataTransferException(String str) {
        super(str);
    }

    public FTPDataTransferException(Throwable th) {
        super(th);
    }
}
