package it.sauronsoftware.ftp4j.connectors;

import it.sauronsoftware.ftp4j.FTPConnector;
import java.io.IOException;
import java.net.Socket;

public class DirectConnector extends FTPConnector {
    public Socket connectForCommunicationChannel(String str, int i) throws IOException {
        return tcpConnectForCommunicationChannel(str, i);
    }

    public Socket connectForDataTransferChannel(String str, int i) throws IOException {
        return tcpConnectForDataTransferChannel(str, i);
    }
}
