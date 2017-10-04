package it.sauronsoftware.ftp4j;

import dji.pilot.fpv.d.c$i;
import dji.pilot.phonecamera.h;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class FTPConnector {
    protected int closeTimeout;
    private Socket connectingCommunicationChannelSocket;
    protected int connectionTimeout;
    protected int readTimeout;
    private boolean useSuggestedAddressForDataConnections;

    public abstract Socket connectForCommunicationChannel(String str, int i) throws IOException;

    public abstract Socket connectForDataTransferChannel(String str, int i) throws IOException;

    protected FTPConnector(boolean z) {
        this.connectionTimeout = 10;
        this.readTimeout = 10;
        this.closeTimeout = 10;
        String property = System.getProperty(FTPKeys.PASSIVE_DT_USE_SUGGESTED_ADDRESS);
        if ("true".equalsIgnoreCase(property) || c$i.b.equalsIgnoreCase(property) || "1".equals(property)) {
            this.useSuggestedAddressForDataConnections = true;
        } else if (h.e.equalsIgnoreCase(property) || c$i.c.equalsIgnoreCase(property) || "0".equals(property)) {
            this.useSuggestedAddressForDataConnections = false;
        } else {
            this.useSuggestedAddressForDataConnections = z;
        }
    }

    protected FTPConnector() {
        this(false);
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeout = i;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setCloseTimeout(int i) {
        this.closeTimeout = i;
    }

    public void setUseSuggestedAddressForDataConnections(boolean z) {
        this.useSuggestedAddressForDataConnections = z;
    }

    boolean getUseSuggestedAddressForDataConnections() {
        return this.useSuggestedAddressForDataConnections;
    }

    protected Socket tcpConnectForCommunicationChannel(String str, int i) throws IOException {
        try {
            this.connectingCommunicationChannelSocket = new Socket();
            this.connectingCommunicationChannelSocket.setKeepAlive(true);
            this.connectingCommunicationChannelSocket.setSoTimeout(this.readTimeout * 1000);
            this.connectingCommunicationChannelSocket.setSoLinger(true, this.closeTimeout);
            this.connectingCommunicationChannelSocket.connect(new InetSocketAddress(str, i), this.connectionTimeout * 1000);
            Socket socket = this.connectingCommunicationChannelSocket;
            return socket;
        } finally {
            this.connectingCommunicationChannelSocket = null;
        }
    }

    protected Socket tcpConnectForDataTransferChannel(String str, int i) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(this.readTimeout * 1000);
        socket.setSoLinger(true, this.closeTimeout);
        socket.setReceiveBufferSize(524288);
        socket.setSendBufferSize(524288);
        socket.connect(new InetSocketAddress(str, i), this.connectionTimeout * 1000);
        return socket;
    }

    public void abortConnectForCommunicationChannel() {
        if (this.connectingCommunicationChannelSocket != null) {
            try {
                this.connectingCommunicationChannelSocket.close();
            } catch (Throwable th) {
            }
        }
    }
}
