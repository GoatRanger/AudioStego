package it.sauronsoftware.ftp4j.connectors;

import com.here.odnp.debug.DebugFile;
import it.sauronsoftware.ftp4j.FTPConnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HTTPTunnelConnector extends FTPConnector {
    private String proxyHost;
    private String proxyPass;
    private int proxyPort;
    private String proxyUser;

    public HTTPTunnelConnector(String str, int i, String str2, String str3) {
        this.proxyHost = str;
        this.proxyPort = i;
        this.proxyUser = str2;
        this.proxyPass = str3;
    }

    public HTTPTunnelConnector(String str, int i) {
        this(str, i, null, null);
    }

    private Socket httpConnect(String str, int i, boolean z) throws IOException {
        Socket tcpConnectForDataTransferChannel;
        IOException e;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream = null;
        byte[] bytes = DebugFile.EOL.getBytes("UTF-8");
        String stringBuffer = new StringBuffer().append("CONNECT ").append(str).append(":").append(i).append(" HTTP/1.1").toString();
        String stringBuffer2 = new StringBuffer().append("Host: ").append(str).append(":").append(i).toString();
        if (z) {
            try {
                tcpConnectForDataTransferChannel = tcpConnectForDataTransferChannel(this.proxyHost, this.proxyPort);
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
                tcpConnectForDataTransferChannel = null;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                tcpConnectForDataTransferChannel = null;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th5) {
                    }
                }
                if (tcpConnectForDataTransferChannel != null) {
                    try {
                        tcpConnectForDataTransferChannel.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th;
            }
        }
        tcpConnectForDataTransferChannel = tcpConnectForCommunicationChannel(this.proxyHost, this.proxyPort);
        try {
            inputStream = tcpConnectForDataTransferChannel.getInputStream();
            try {
                outputStream = tcpConnectForDataTransferChannel.getOutputStream();
                outputStream.write(stringBuffer.getBytes("UTF-8"));
                outputStream.write(bytes);
                outputStream.write(stringBuffer2.getBytes("UTF-8"));
                outputStream.write(bytes);
                if (!(this.proxyUser == null || this.proxyPass == null)) {
                    outputStream.write(new StringBuffer().append("Proxy-Authorization: Basic ").append(Base64.encode(new StringBuffer().append(this.proxyUser).append(":").append(this.proxyPass).toString())).toString().getBytes("UTF-8"));
                    outputStream.write(bytes);
                }
                outputStream.write(bytes);
                outputStream.flush();
                ArrayList arrayList = new ArrayList();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine = bufferedReader.readLine();
                while (readLine != null && readLine.length() > 0) {
                    arrayList.add(readLine);
                    readLine = bufferedReader.readLine();
                }
                int size = arrayList.size();
                if (size < 1) {
                    throw new IOException("HTTPTunnelConnector: invalid proxy response");
                }
                readLine = (String) arrayList.get(0);
                if (!readLine.startsWith("HTTP/") || readLine.length() < 12) {
                    throw new IOException("HTTPTunnelConnector: invalid proxy response");
                }
                if ("200".equals(readLine.substring(9, 12))) {
                    return tcpConnectForDataTransferChannel;
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("HTTPTunnelConnector: connection failed\r\n");
                stringBuffer3.append("Response received from the proxy:\r\n");
                for (int i2 = 0; i2 < size; i2++) {
                    stringBuffer3.append((String) arrayList.get(i2));
                    stringBuffer3.append(DebugFile.EOL);
                }
                throw new IOException(stringBuffer3.toString());
            } catch (IOException e3) {
                e = e3;
                throw e;
            }
        } catch (IOException e4) {
            e = e4;
            inputStream = null;
            throw e;
        } catch (Throwable th7) {
            th = th7;
            inputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (tcpConnectForDataTransferChannel != null) {
                tcpConnectForDataTransferChannel.close();
            }
            throw th;
        }
    }

    public Socket connectForCommunicationChannel(String str, int i) throws IOException {
        return httpConnect(str, i, false);
    }

    public Socket connectForDataTransferChannel(String str, int i) throws IOException {
        return httpConnect(str, i, true);
    }
}
