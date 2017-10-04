package it.sauronsoftware.ftp4j.connectors;

import it.sauronsoftware.ftp4j.FTPCodes;
import it.sauronsoftware.ftp4j.FTPCommunicationChannel;
import it.sauronsoftware.ftp4j.FTPConnector;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.IOException;
import java.net.Socket;

public class FTPProxyConnector extends FTPConnector {
    public static int STYLE_OPEN_COMMAND = 1;
    public static int STYLE_SITE_COMMAND = 0;
    private String proxyHost;
    private String proxyPass;
    private int proxyPort;
    private String proxyUser;
    public int style;

    public FTPProxyConnector(String str, int i, String str2, String str3) {
        super(true);
        this.style = STYLE_SITE_COMMAND;
        this.proxyHost = str;
        this.proxyPort = i;
        this.proxyUser = str2;
        this.proxyPass = str3;
    }

    public FTPProxyConnector(String str, int i) {
        this(str, i, "anonymous", "ftp4j");
    }

    public void setStyle(int i) {
        if (i == STYLE_OPEN_COMMAND || i == STYLE_SITE_COMMAND) {
            this.style = i;
            return;
        }
        throw new IllegalArgumentException("Invalid style");
    }

    public Socket connectForCommunicationChannel(String str, int i) throws IOException {
        Socket tcpConnectForCommunicationChannel = tcpConnectForCommunicationChannel(this.proxyHost, this.proxyPort);
        FTPCommunicationChannel fTPCommunicationChannel = new FTPCommunicationChannel(tcpConnectForCommunicationChannel, "ASCII");
        try {
            if (fTPCommunicationChannel.readFTPReply().getCode() != FTPCodes.SERVICE_READY_FOR_NEW_USER) {
                throw new IOException("Invalid proxy response");
            }
            if (this.style == STYLE_SITE_COMMAND) {
                fTPCommunicationChannel.sendFTPCommand(new StringBuffer().append("USER ").append(this.proxyUser).toString());
                try {
                    Object obj;
                    switch (fTPCommunicationChannel.readFTPReply().getCode()) {
                        case FTPCodes.USER_LOGGED_IN /*230*/:
                            obj = null;
                            break;
                        case FTPCodes.USERNAME_OK /*331*/:
                            obj = 1;
                            break;
                        default:
                            throw new IOException("Proxy authentication failed");
                    }
                    if (obj != null) {
                        fTPCommunicationChannel.sendFTPCommand(new StringBuffer().append("PASS ").append(this.proxyPass).toString());
                        try {
                            if (fTPCommunicationChannel.readFTPReply().getCode() != FTPCodes.USER_LOGGED_IN) {
                                throw new IOException("Proxy authentication failed");
                            }
                        } catch (FTPIllegalReplyException e) {
                            throw new IOException("Invalid proxy response");
                        }
                    }
                    fTPCommunicationChannel.sendFTPCommand(new StringBuffer().append("SITE ").append(str).append(":").append(i).toString());
                } catch (FTPIllegalReplyException e2) {
                    throw new IOException("Invalid proxy response");
                }
            } else if (this.style == STYLE_OPEN_COMMAND) {
                fTPCommunicationChannel.sendFTPCommand(new StringBuffer().append("OPEN ").append(str).append(":").append(i).toString());
            }
            return tcpConnectForCommunicationChannel;
        } catch (FTPIllegalReplyException e3) {
            throw new IOException("Invalid proxy response");
        }
    }

    public Socket connectForDataTransferChannel(String str, int i) throws IOException {
        return tcpConnectForDataTransferChannel(str, i);
    }
}
