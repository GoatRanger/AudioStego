package it.sauronsoftware.ftp4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.SSLSocketFactory;

public class FTPCommunicationChannel {
    private String charsetName = null;
    private ArrayList communicationListeners = new ArrayList();
    private Socket connection = null;
    private NVTASCIIReader reader = null;
    private NVTASCIIWriter writer = null;

    public FTPCommunicationChannel(Socket socket, String str) throws IOException {
        this.connection = socket;
        this.charsetName = str;
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        this.reader = new NVTASCIIReader(inputStream, str);
        this.writer = new NVTASCIIWriter(outputStream, str);
    }

    public void addCommunicationListener(FTPCommunicationListener fTPCommunicationListener) {
        this.communicationListeners.add(fTPCommunicationListener);
    }

    public void removeCommunicationListener(FTPCommunicationListener fTPCommunicationListener) {
        this.communicationListeners.remove(fTPCommunicationListener);
    }

    public void close() {
        try {
            this.connection.close();
        } catch (Exception e) {
        }
    }

    public FTPCommunicationListener[] getCommunicationListeners() {
        int size = this.communicationListeners.size();
        FTPCommunicationListener[] fTPCommunicationListenerArr = new FTPCommunicationListener[size];
        for (int i = 0; i < size; i++) {
            fTPCommunicationListenerArr[i] = (FTPCommunicationListener) this.communicationListeners.get(i);
        }
        return fTPCommunicationListenerArr;
    }

    private String read() throws IOException {
        String readLine = this.reader.readLine();
        if (readLine == null) {
            throw new IOException("FTPConnection closed");
        }
        Iterator it = this.communicationListeners.iterator();
        while (it.hasNext()) {
            ((FTPCommunicationListener) it.next()).received(readLine);
        }
        return readLine;
    }

    public void sendFTPCommand(String str) throws IOException {
        this.writer.writeLine(str);
        Iterator it = this.communicationListeners.iterator();
        while (it.hasNext()) {
            ((FTPCommunicationListener) it.next()).sent(str);
        }
    }

    public FTPReply readFTPReply() throws IOException, FTPIllegalReplyException {
        int i;
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            String read = read();
            if (read.trim().length() != 0) {
                String substring;
                if (read.startsWith("\n")) {
                    substring = read.substring(1);
                } else {
                    substring = read;
                }
                int length = substring.length();
                if (i3 != 0 || length >= 3) {
                    int parseInt;
                    try {
                        parseInt = Integer.parseInt(substring.substring(0, 3));
                    } catch (Exception e) {
                        if (i3 == 0) {
                            throw new FTPIllegalReplyException();
                        }
                        parseInt = 0;
                    }
                    if (i3 == 0 || parseInt == 0 || parseInt == i3) {
                        if (i3 == 0) {
                            i = parseInt;
                        } else {
                            i = i3;
                        }
                        if (parseInt > 0) {
                            if (length <= 3) {
                                if (length == 3) {
                                    break;
                                }
                                arrayList.add(substring);
                            } else {
                                char charAt = substring.charAt(3);
                                arrayList.add(substring.substring(4, length));
                                if (charAt == ' ') {
                                    break;
                                } else if (charAt == '-') {
                                    i3 = i;
                                } else {
                                    throw new FTPIllegalReplyException();
                                }
                            }
                        }
                        arrayList.add(substring);
                        i3 = i;
                    } else {
                        throw new FTPIllegalReplyException();
                    }
                }
                throw new FTPIllegalReplyException();
            }
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        while (i2 < size) {
            strArr[i2] = (String) arrayList.get(i2);
            i2++;
        }
        return new FTPReply(i, strArr);
    }

    public void changeCharset(String str) throws IOException {
        this.charsetName = str;
        this.reader.changeCharset(str);
        this.writer.changeCharset(str);
    }

    public void ssl(SSLSocketFactory sSLSocketFactory) throws IOException {
        this.connection = sSLSocketFactory.createSocket(this.connection, this.connection.getInetAddress().getHostName(), this.connection.getPort(), true);
        InputStream inputStream = this.connection.getInputStream();
        OutputStream outputStream = this.connection.getOutputStream();
        this.reader = new NVTASCIIReader(inputStream, this.charsetName);
        this.writer = new NVTASCIIWriter(outputStream, this.charsetName);
    }
}
