package it.sauronsoftware.ftp4j.connectors;

import com.google.android.gms.location.places.Place;
import it.sauronsoftware.ftp4j.FTPConnector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SOCKS4Connector extends FTPConnector {
    private String socks4host;
    private int socks4port;
    private String socks4user;

    public SOCKS4Connector(String str, int i, String str2) {
        this.socks4host = str;
        this.socks4port = i;
        this.socks4user = str2;
    }

    public SOCKS4Connector(String str, int i) {
        this(str, i, null);
    }

    private Socket socksConnect(String str, int i, boolean z) throws IOException {
        byte[] address;
        Socket tcpConnectForDataTransferChannel;
        InputStream inputStream;
        OutputStream outputStream;
        InputStream inputStream2 = null;
        Object obj = null;
        try {
            address = InetAddress.getByName(str).getAddress();
        } catch (Exception e) {
            address = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
            int i2 = 1;
        }
        if (z) {
            try {
                tcpConnectForDataTransferChannel = tcpConnectForDataTransferChannel(this.socks4host, this.socks4port);
            } catch (IOException e2) {
                e = e2;
                OutputStream outputStream2 = null;
                Socket socket = null;
                try {
                    IOException e3;
                    throw e3;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStream2;
                    tcpConnectForDataTransferChannel = socket;
                    outputStream = outputStream2;
                }
            } catch (Throwable th2) {
                Throwable th3;
                th3 = th2;
                outputStream = null;
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
                throw th3;
            }
        }
        tcpConnectForDataTransferChannel = tcpConnectForCommunicationChannel(this.socks4host, this.socks4port);
        try {
            inputStream = tcpConnectForDataTransferChannel.getInputStream();
            try {
                outputStream = tcpConnectForDataTransferChannel.getOutputStream();
                try {
                    outputStream.write(4);
                    outputStream.write(1);
                    outputStream.write(i >> 8);
                    outputStream.write(i);
                    outputStream.write(address);
                    if (this.socks4user != null) {
                        outputStream.write(this.socks4user.getBytes("UTF-8"));
                    }
                    outputStream.write(0);
                    if (obj != null) {
                        outputStream.write(str.getBytes("UTF-8"));
                        outputStream.write(0);
                    }
                    if (read(inputStream) != 0) {
                        throw new IOException("SOCKS4Connector: invalid proxy response");
                    }
                    switch (read(inputStream)) {
                        case 90:
                            inputStream.skip(6);
                            return tcpConnectForDataTransferChannel;
                        case Place.TYPE_TAXI_STAND /*91*/:
                            throw new IOException("SOCKS4Connector: connection refused/failed");
                        case Place.TYPE_TRAIN_STATION /*92*/:
                            throw new IOException("SOCKS4Connector: cannot validate the user");
                        case 93:
                            throw new IOException("SOCKS4Connector: invalid user");
                        default:
                            throw new IOException("SOCKS4Connector: invalid proxy response");
                    }
                } catch (IOException e4) {
                    e3 = e4;
                    outputStream2 = outputStream;
                    inputStream2 = inputStream;
                    socket = tcpConnectForDataTransferChannel;
                    throw e3;
                } catch (Throwable th7) {
                    th3 = th7;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (tcpConnectForDataTransferChannel != null) {
                        tcpConnectForDataTransferChannel.close();
                    }
                    throw th3;
                }
            } catch (IOException e5) {
                e3 = e5;
                outputStream2 = null;
                socket = tcpConnectForDataTransferChannel;
                inputStream2 = inputStream;
                throw e3;
            } catch (Throwable th8) {
                th3 = th8;
                outputStream = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (tcpConnectForDataTransferChannel != null) {
                    tcpConnectForDataTransferChannel.close();
                }
                throw th3;
            }
        } catch (IOException e6) {
            e3 = e6;
            outputStream2 = null;
            socket = tcpConnectForDataTransferChannel;
            throw e3;
        } catch (Throwable th9) {
            th3 = th9;
            outputStream = null;
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
            throw th3;
        }
    }

    private int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return read;
        }
        throw new IOException("SOCKS4Connector: connection closed by the proxy");
    }

    public Socket connectForCommunicationChannel(String str, int i) throws IOException {
        return socksConnect(str, i, false);
    }

    public Socket connectForDataTransferChannel(String str, int i) throws IOException {
        return socksConnect(str, i, true);
    }
}
