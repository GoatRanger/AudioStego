package it.sauronsoftware.ftp4j;

import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.connectors.DirectConnector;
import it.sauronsoftware.ftp4j.extrecognizers.DefaultTextualExtensionRecognizer;
import it.sauronsoftware.ftp4j.listparsers.DOSListParser;
import it.sauronsoftware.ftp4j.listparsers.EPLFListParser;
import it.sauronsoftware.ftp4j.listparsers.MLSDListParser;
import it.sauronsoftware.ftp4j.listparsers.NetWareListParser;
import it.sauronsoftware.ftp4j.listparsers.UnixListParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.SSLSocketFactory;

public class FTPClient {
    private static final DateFormat MDTM_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final int MLSD_ALWAYS = 1;
    public static final int MLSD_IF_SUPPORTED = 0;
    public static final int MLSD_NEVER = 2;
    private static final Pattern PASV_PATTERN = Pattern.compile("\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}");
    private static final Pattern PWD_PATTERN = Pattern.compile("\"/.*\"");
    public static final int SECURITY_FTP = 0;
    public static final int SECURITY_FTPES = 2;
    public static final int SECURITY_FTPS = 1;
    private static final int SEND_AND_RECEIVE_BUFFER_SIZE = 65536;
    public static final int TYPE_AUTO = 0;
    public static final int TYPE_BINARY = 2;
    public static final int TYPE_TEXTUAL = 1;
    private Object abortLock = new Object();
    private boolean aborted = false;
    private boolean authenticated = false;
    private long autoNoopTimeout = 0;
    private AutoNoopTimer autoNoopTimer;
    private String charset = null;
    private FTPCommunicationChannel communication = null;
    private ArrayList communicationListeners = new ArrayList();
    private boolean compressionEnabled = false;
    private boolean connected = false;
    private FTPConnector connector = new DirectConnector();
    private boolean consumeAborCommandReply = false;
    private boolean dataChannelEncrypted = false;
    private InputStream dataTransferInputStream = null;
    private OutputStream dataTransferOutputStream = null;
    private String host = null;
    private ArrayList listParsers = new ArrayList();
    private Object lock = new Object();
    private int mlsdPolicy = 0;
    private boolean mlsdSupported = false;
    private boolean modezEnabled = false;
    private boolean modezSupported = false;
    private long nextAutoNoopTime;
    private boolean ongoingDataTransfer = false;
    private FTPListParser parser = null;
    private boolean passive = true;
    private String password;
    private int port = 0;
    private boolean restSupported = false;
    private int security = 0;
    private SSLSocketFactory sslSocketFactory = ((SSLSocketFactory) SSLSocketFactory.getDefault());
    private FTPTextualExtensionRecognizer textualExtensionRecognizer = DefaultTextualExtensionRecognizer.getInstance();
    private int type = 0;
    private String username;
    private boolean utf8Supported = false;

    class AnonymousClass2 implements FTPDataTransferConnectionProvider {
        private final FTPClient this$0;
        private final String val$pasvHost;
        private final int val$pasvPort;

        AnonymousClass2(FTPClient fTPClient, String str, int i) {
            this.this$0 = fTPClient;
            this.val$pasvHost = str;
            this.val$pasvPort = i;
        }

        public Socket openDataTransferConnection() throws FTPDataTransferException {
            try {
                String access$300 = FTPClient.access$200(this.this$0).getUseSuggestedAddressForDataConnections() ? this.val$pasvHost : FTPClient.access$300(this.this$0);
                Socket connectForDataTransferChannel = FTPClient.access$200(this.this$0).connectForDataTransferChannel(access$300, this.val$pasvPort);
                if (FTPClient.access$000(this.this$0)) {
                    return FTPClient.access$100(this.this$0, connectForDataTransferChannel, access$300, this.val$pasvPort);
                }
                return connectForDataTransferChannel;
            } catch (Throwable e) {
                throw new FTPDataTransferException("Cannot connect to the remote server", e);
            }
        }

        public void dispose() {
        }
    }

    private class AutoNoopTimer extends Thread {
        private final FTPClient this$0;

        private AutoNoopTimer(FTPClient fTPClient) {
            this.this$0 = fTPClient;
        }

        AutoNoopTimer(FTPClient fTPClient, AnonymousClass1 anonymousClass1) {
            this(fTPClient);
        }

        public void run() {
            synchronized (FTPClient.access$500(this.this$0)) {
                if (FTPClient.access$600(this.this$0) <= 0 && FTPClient.access$700(this.this$0) > 0) {
                    FTPClient.access$602(this.this$0, System.currentTimeMillis() + FTPClient.access$700(this.this$0));
                }
                while (!Thread.interrupted() && FTPClient.access$700(this.this$0) > 0) {
                    long access$600 = FTPClient.access$600(this.this$0) - System.currentTimeMillis();
                    if (access$600 > 0) {
                        try {
                            FTPClient.access$500(this.this$0).wait(access$600);
                        } catch (InterruptedException e) {
                        }
                    }
                    if (System.currentTimeMillis() >= FTPClient.access$600(this.this$0)) {
                        try {
                            this.this$0.noop();
                        } catch (Throwable th) {
                        }
                    }
                }
            }
        }
    }

    static boolean access$000(FTPClient fTPClient) {
        return fTPClient.dataChannelEncrypted;
    }

    static Socket access$100(FTPClient fTPClient, Socket socket, String str, int i) throws IOException {
        return fTPClient.ssl(socket, str, i);
    }

    static FTPConnector access$200(FTPClient fTPClient) {
        return fTPClient.connector;
    }

    static String access$300(FTPClient fTPClient) {
        return fTPClient.host;
    }

    static Object access$500(FTPClient fTPClient) {
        return fTPClient.lock;
    }

    static long access$600(FTPClient fTPClient) {
        return fTPClient.nextAutoNoopTime;
    }

    static long access$602(FTPClient fTPClient, long j) {
        fTPClient.nextAutoNoopTime = j;
        return j;
    }

    static long access$700(FTPClient fTPClient) {
        return fTPClient.autoNoopTimeout;
    }

    public FTPClient() {
        addListParser(new UnixListParser());
        addListParser(new DOSListParser());
        addListParser(new EPLFListParser());
        addListParser(new NetWareListParser());
        addListParser(new MLSDListParser());
    }

    public FTPConnector getConnector() {
        FTPConnector fTPConnector;
        synchronized (this.lock) {
            fTPConnector = this.connector;
        }
        return fTPConnector;
    }

    public void setConnector(FTPConnector fTPConnector) {
        synchronized (this.lock) {
            this.connector = fTPConnector;
        }
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        synchronized (this.lock) {
            this.sslSocketFactory = sSLSocketFactory;
        }
    }

    public SSLSocketFactory getSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (this.lock) {
            sSLSocketFactory = this.sslSocketFactory;
        }
        return sSLSocketFactory;
    }

    public void setSecurity(int i) throws IllegalStateException, IllegalArgumentException {
        if (i == 0 || i == 1 || i == 2) {
            synchronized (this.lock) {
                if (this.connected) {
                    throw new IllegalStateException("The security level of the connection can't be changed while the client is connected");
                }
                this.security = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid security");
    }

    public int getSecurity() {
        return this.security;
    }

    private Socket ssl(Socket socket, String str, int i) throws IOException {
        return this.sslSocketFactory.createSocket(socket, str, i, true);
    }

    public void setPassive(boolean z) {
        synchronized (this.lock) {
            this.passive = z;
        }
    }

    public void setType(int i) throws IllegalArgumentException {
        if (i == 0 || i == 2 || i == 1) {
            synchronized (this.lock) {
                this.type = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid type");
    }

    public int getType() {
        int i;
        synchronized (this.lock) {
            i = this.type;
        }
        return i;
    }

    public void setMLSDPolicy(int i) throws IllegalArgumentException {
        if (this.type == 0 || this.type == 1 || this.type == 2) {
            synchronized (this.lock) {
                this.mlsdPolicy = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid MLSD policy");
    }

    public int getMLSDPolicy() {
        int i;
        synchronized (this.lock) {
            i = this.mlsdPolicy;
        }
        return i;
    }

    public String getCharset() {
        String str;
        synchronized (this.lock) {
            str = this.charset;
        }
        return str;
    }

    public void setCharset(String str) {
        synchronized (this.lock) {
            this.charset = str;
            if (this.connected) {
                try {
                    this.communication.changeCharset(pickCharset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isResumeSupported() {
        boolean z;
        synchronized (this.lock) {
            z = this.restSupported;
        }
        return z;
    }

    public boolean isCompressionSupported() {
        return this.modezSupported;
    }

    public void setCompressionEnabled(boolean z) {
        this.compressionEnabled = z;
    }

    public boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }

    public FTPTextualExtensionRecognizer getTextualExtensionRecognizer() {
        FTPTextualExtensionRecognizer fTPTextualExtensionRecognizer;
        synchronized (this.lock) {
            fTPTextualExtensionRecognizer = this.textualExtensionRecognizer;
        }
        return fTPTextualExtensionRecognizer;
    }

    public void setTextualExtensionRecognizer(FTPTextualExtensionRecognizer fTPTextualExtensionRecognizer) {
        synchronized (this.lock) {
            this.textualExtensionRecognizer = fTPTextualExtensionRecognizer;
        }
    }

    public boolean isAuthenticated() {
        boolean z;
        synchronized (this.lock) {
            z = this.authenticated;
        }
        return z;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.lock) {
            z = this.connected;
        }
        return z;
    }

    public boolean isPassive() {
        boolean z;
        synchronized (this.lock) {
            z = this.passive;
        }
        return z;
    }

    public String getHost() {
        String str;
        synchronized (this.lock) {
            str = this.host;
        }
        return str;
    }

    public int getPort() {
        int i;
        synchronized (this.lock) {
            i = this.port;
        }
        return i;
    }

    public String getPassword() {
        String str;
        synchronized (this.lock) {
            str = this.password;
        }
        return str;
    }

    public String getUsername() {
        String str;
        synchronized (this.lock) {
            str = this.username;
        }
        return str;
    }

    public void setAutoNoopTimeout(long j) {
        synchronized (this.lock) {
            if (this.connected && this.authenticated) {
                stopAutoNoopTimer();
            }
            long j2 = this.autoNoopTimeout;
            this.autoNoopTimeout = j;
            if (!(j2 == 0 || j == 0 || this.nextAutoNoopTime <= 0)) {
                this.nextAutoNoopTime -= j2 - j;
            }
            if (this.connected && this.authenticated) {
                startAutoNoopTimer();
            }
        }
    }

    public long getAutoNoopTimeout() {
        long j;
        synchronized (this.lock) {
            j = this.autoNoopTimeout;
        }
        return j;
    }

    public void addCommunicationListener(FTPCommunicationListener fTPCommunicationListener) {
        synchronized (this.lock) {
            this.communicationListeners.add(fTPCommunicationListener);
            if (this.communication != null) {
                this.communication.addCommunicationListener(fTPCommunicationListener);
            }
        }
    }

    public void removeCommunicationListener(FTPCommunicationListener fTPCommunicationListener) {
        synchronized (this.lock) {
            this.communicationListeners.remove(fTPCommunicationListener);
            if (this.communication != null) {
                this.communication.removeCommunicationListener(fTPCommunicationListener);
            }
        }
    }

    public FTPCommunicationListener[] getCommunicationListeners() {
        FTPCommunicationListener[] fTPCommunicationListenerArr;
        synchronized (this.lock) {
            int size = this.communicationListeners.size();
            fTPCommunicationListenerArr = new FTPCommunicationListener[size];
            for (int i = 0; i < size; i++) {
                fTPCommunicationListenerArr[i] = (FTPCommunicationListener) this.communicationListeners.get(i);
            }
        }
        return fTPCommunicationListenerArr;
    }

    public void addListParser(FTPListParser fTPListParser) {
        synchronized (this.lock) {
            this.listParsers.add(fTPListParser);
        }
    }

    public void removeListParser(FTPListParser fTPListParser) {
        synchronized (this.lock) {
            this.listParsers.remove(fTPListParser);
        }
    }

    public FTPListParser[] getListParsers() {
        FTPListParser[] fTPListParserArr;
        synchronized (this.lock) {
            int size = this.listParsers.size();
            fTPListParserArr = new FTPListParser[size];
            for (int i = 0; i < size; i++) {
                fTPListParserArr[i] = (FTPListParser) this.listParsers.get(i);
            }
        }
        return fTPListParserArr;
    }

    public String[] connect(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        int i;
        if (this.security == 1) {
            i = 990;
        } else {
            i = 21;
        }
        return connect(str, i);
    }

    public String[] connect(String str, int i) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        String[] messages;
        Socket socket = null;
        synchronized (this.lock) {
            if (this.connected) {
                throw new IllegalStateException(new StringBuffer().append("Client already connected to ").append(str).append(" on port ").append(i).toString());
            }
            try {
                socket = this.connector.connectForCommunicationChannel(str, i);
                if (this.security == 1) {
                    socket = ssl(socket, str, i);
                }
                this.communication = new FTPCommunicationChannel(socket, pickCharset());
                Iterator it = this.communicationListeners.iterator();
                while (it.hasNext()) {
                    this.communication.addCommunicationListener((FTPCommunicationListener) it.next());
                }
                FTPReply readFTPReply = this.communication.readFTPReply();
                if (readFTPReply.isSuccessCode()) {
                    this.connected = true;
                    this.authenticated = false;
                    this.parser = null;
                    this.host = str;
                    this.port = i;
                    this.username = null;
                    this.password = null;
                    this.utf8Supported = false;
                    this.restSupported = false;
                    this.mlsdSupported = false;
                    this.modezSupported = false;
                    this.dataChannelEncrypted = false;
                    messages = readFTPReply.getMessages();
                    if (!(this.connected || socket == null)) {
                        try {
                            socket.close();
                        } catch (Throwable th) {
                        }
                    }
                } else {
                    throw new FTPException(readFTPReply);
                }
            } catch (IOException e) {
                throw e;
            } catch (Throwable th2) {
                if (!(this.connected || socket == null)) {
                    try {
                        socket.close();
                    } catch (Throwable th3) {
                    }
                }
            }
        }
        return messages;
    }

    public void abortCurrentConnectionAttempt() {
        this.connector.abortConnectForCommunicationChannel();
    }

    public void disconnect(boolean z) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (this.connected) {
                if (this.authenticated) {
                    stopAutoNoopTimer();
                }
                if (z) {
                    this.communication.sendFTPCommand("QUIT");
                    FTPReply readFTPReply = this.communication.readFTPReply();
                    if (!readFTPReply.isSuccessCode()) {
                        throw new FTPException(readFTPReply);
                    }
                }
                this.communication.close();
                this.communication = null;
                this.connected = false;
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
    }

    public void abruptlyCloseCommunication() {
        if (this.communication != null) {
            this.communication.close();
            this.communication = null;
        }
        this.connected = false;
        stopAutoNoopTimer();
    }

    public void login(String str, String str2) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        login(str, str2, null);
    }

    public void login(String str, String str2, String str3) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        Object obj = null;
        synchronized (this.lock) {
            if (this.connected) {
                FTPReply readFTPReply;
                Object obj2;
                if (this.security == 2) {
                    this.communication.sendFTPCommand("AUTH TLS");
                    if (this.communication.readFTPReply().isSuccessCode()) {
                        this.communication.ssl(this.sslSocketFactory);
                    } else {
                        this.communication.sendFTPCommand("AUTH SSL");
                        readFTPReply = this.communication.readFTPReply();
                        if (readFTPReply.isSuccessCode()) {
                            this.communication.ssl(this.sslSocketFactory);
                        } else {
                            throw new FTPException(readFTPReply.getCode(), "SECURITY_FTPES cannot be applied: the server refused both AUTH TLS and AUTH SSL commands");
                        }
                    }
                }
                this.authenticated = false;
                this.communication.sendFTPCommand(new StringBuffer().append("USER ").append(str).toString());
                readFTPReply = this.communication.readFTPReply();
                switch (readFTPReply.getCode()) {
                    case FTPCodes.USER_LOGGED_IN /*230*/:
                        obj2 = null;
                        break;
                    case FTPCodes.USERNAME_OK /*331*/:
                        int i = 1;
                        break;
                    default:
                        throw new FTPException(readFTPReply);
                }
                if (obj2 != null) {
                    if (str2 == null) {
                        throw new FTPException((int) FTPCodes.USERNAME_OK);
                    }
                    this.communication.sendFTPCommand(new StringBuffer().append("PASS ").append(str2).toString());
                    readFTPReply = this.communication.readFTPReply();
                    switch (readFTPReply.getCode()) {
                        case FTPCodes.USER_LOGGED_IN /*230*/:
                            break;
                        case FTPCodes.NEED_ACCOUNT /*332*/:
                            obj = 1;
                            break;
                        default:
                            throw new FTPException(readFTPReply);
                    }
                }
                if (obj != null) {
                    if (str3 == null) {
                        throw new FTPException((int) FTPCodes.NEED_ACCOUNT);
                    }
                    this.communication.sendFTPCommand(new StringBuffer().append("ACCT ").append(str3).toString());
                    FTPReply readFTPReply2 = this.communication.readFTPReply();
                    switch (readFTPReply2.getCode()) {
                        case FTPCodes.USER_LOGGED_IN /*230*/:
                            break;
                        default:
                            throw new FTPException(readFTPReply2);
                    }
                }
                this.authenticated = true;
                this.username = str;
                this.password = str2;
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        postLoginOperations();
        startAutoNoopTimer();
    }

    private void postLoginOperations() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            this.utf8Supported = false;
            this.restSupported = false;
            this.mlsdSupported = false;
            this.modezSupported = false;
            this.dataChannelEncrypted = false;
            this.communication.sendFTPCommand("FEAT");
            FTPReply readFTPReply = this.communication.readFTPReply();
            if (readFTPReply.getCode() == 211) {
                String[] messages = readFTPReply.getMessages();
                for (int i = 1; i < messages.length - 1; i++) {
                    String toUpperCase = messages[i].trim().toUpperCase();
                    if ("REST STREAM".equalsIgnoreCase(toUpperCase)) {
                        this.restSupported = true;
                    } else if ("UTF8".equalsIgnoreCase(toUpperCase)) {
                        this.utf8Supported = true;
                        this.communication.changeCharset("UTF-8");
                    } else if ("MLSD".equalsIgnoreCase(toUpperCase)) {
                        this.mlsdSupported = true;
                    } else if ("MODE Z".equalsIgnoreCase(toUpperCase) || toUpperCase.startsWith("MODE Z ")) {
                        this.modezSupported = true;
                    }
                }
            }
            if (this.utf8Supported) {
                this.communication.sendFTPCommand("OPTS UTF8 ON");
                this.communication.readFTPReply();
            }
            if (this.security == 1 || this.security == 2) {
                this.communication.sendFTPCommand("PBSZ 0");
                this.communication.readFTPReply();
                this.communication.sendFTPCommand("PROT P");
                if (this.communication.readFTPReply().isSuccessCode()) {
                    this.dataChannelEncrypted = true;
                }
            }
        }
    }

    public void logout() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("REIN");
                FTPReply readFTPReply = this.communication.readFTPReply();
                if (readFTPReply.isSuccessCode()) {
                    stopAutoNoopTimer();
                    this.authenticated = false;
                    this.username = null;
                    this.password = null;
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void noop() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                try {
                    this.communication.sendFTPCommand("NOOP");
                    FTPReply readFTPReply = this.communication.readFTPReply();
                    if (readFTPReply.isSuccessCode()) {
                    } else {
                        throw new FTPException(readFTPReply);
                    }
                } finally {
                    touchAutoNoopTimer();
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public FTPReply sendCustomCommand(String str) throws IllegalStateException, IOException, FTPIllegalReplyException {
        FTPReply readFTPReply;
        synchronized (this.lock) {
            if (this.connected) {
                this.communication.sendFTPCommand(str);
                touchAutoNoopTimer();
                readFTPReply = this.communication.readFTPReply();
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        return readFTPReply;
    }

    public FTPReply sendSiteCommand(String str) throws IllegalStateException, IOException, FTPIllegalReplyException {
        FTPReply readFTPReply;
        synchronized (this.lock) {
            if (this.connected) {
                this.communication.sendFTPCommand(new StringBuffer().append("SITE ").append(str).toString());
                touchAutoNoopTimer();
                readFTPReply = this.communication.readFTPReply();
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        return readFTPReply;
    }

    public void changeAccount(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("ACCT ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public String currentDirectory() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        String substring;
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("PWD");
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    String[] messages = readFTPReply.getMessages();
                    if (messages.length != 1) {
                        throw new FTPIllegalReplyException();
                    }
                    Matcher matcher = PWD_PATTERN.matcher(messages[0]);
                    if (matcher.find()) {
                        substring = messages[0].substring(matcher.start() + 1, matcher.end() - 1);
                    } else {
                        throw new FTPIllegalReplyException();
                    }
                }
                throw new FTPException(readFTPReply);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return substring;
    }

    public void changeDirectory(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("CWD ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void changeDirectoryUp() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("CDUP");
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public Date modifiedDate(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        Date parse;
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("MDTM ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    String[] messages = readFTPReply.getMessages();
                    if (messages.length != 1) {
                        throw new FTPIllegalReplyException();
                    }
                    try {
                        parse = MDTM_DATE_FORMAT.parse(messages[0]);
                    } catch (ParseException e) {
                        throw new FTPIllegalReplyException();
                    }
                }
                throw new FTPException(readFTPReply);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return parse;
    }

    public long fileSize(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        long parseLong;
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("TYPE I");
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    this.communication.sendFTPCommand(new StringBuffer().append("SIZE ").append(str).toString());
                    readFTPReply = this.communication.readFTPReply();
                    touchAutoNoopTimer();
                    if (readFTPReply.isSuccessCode()) {
                        String[] messages = readFTPReply.getMessages();
                        if (messages.length != 1) {
                            throw new FTPIllegalReplyException();
                        }
                        try {
                            parseLong = Long.parseLong(messages[0]);
                        } catch (Throwable th) {
                            FTPIllegalReplyException fTPIllegalReplyException = new FTPIllegalReplyException();
                        }
                    } else {
                        throw new FTPException(readFTPReply);
                    }
                }
                throw new FTPException(readFTPReply);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return parseLong;
    }

    public void rename(String str, String str2) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("RNFR ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.getCode() != FTPCodes.PENDING_FURTHER_INFORMATION) {
                    throw new FTPException(readFTPReply);
                }
                this.communication.sendFTPCommand(new StringBuffer().append("RNTO ").append(str2).toString());
                readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void deleteFile(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("DELE ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void deleteDirectory(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("RMD ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void createDirectory(String str) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand(new StringBuffer().append("MKD ").append(str).toString());
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public String[] help() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        String[] messages;
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("HELP");
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    messages = readFTPReply.getMessages();
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return messages;
    }

    public String[] serverStatus() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
        String[] messages;
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                this.communication.sendFTPCommand("STAT");
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    messages = readFTPReply.getMessages();
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return messages;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public it.sauronsoftware.ftp4j.FTPFile[] list(java.lang.String r12) throws java.lang.IllegalStateException, java.io.IOException, it.sauronsoftware.ftp4j.FTPIllegalReplyException, it.sauronsoftware.ftp4j.FTPException, it.sauronsoftware.ftp4j.FTPDataTransferException, it.sauronsoftware.ftp4j.FTPAbortedException, it.sauronsoftware.ftp4j.FTPListParseException {
        /*
        r11 = this;
        r10 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r0 = 1;
        r1 = 0;
        r3 = 0;
        r5 = r11.lock;
        monitor-enter(r5);
        r2 = r11.connected;	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0019;
    L_0x000e:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0016 }
        r1 = "Client not connected";
        r0.<init>(r1);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0016 }
        throw r0;
    L_0x0019:
        r2 = r11.authenticated;	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0025;
    L_0x001d:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0016 }
        r1 = "Client not authenticated";
        r0.<init>(r1);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0025:
        r2 = r11.communication;	 Catch:{ all -> 0x0016 }
        r4 = "TYPE A";
        r2.sendFTPCommand(r4);	 Catch:{ all -> 0x0016 }
        r2 = r11.communication;	 Catch:{ all -> 0x0016 }
        r2 = r2.readFTPReply();	 Catch:{ all -> 0x0016 }
        r11.touchAutoNoopTimer();	 Catch:{ all -> 0x0016 }
        r4 = r2.isSuccessCode();	 Catch:{ all -> 0x0016 }
        if (r4 != 0) goto L_0x0041;
    L_0x003b:
        r0 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0041:
        r2 = r11.openDataTransferChannel();	 Catch:{ all -> 0x0016 }
        r4 = r11.mlsdPolicy;	 Catch:{ all -> 0x0016 }
        if (r4 != 0) goto L_0x0102;
    L_0x0049:
        r0 = r11.mlsdSupported;	 Catch:{ all -> 0x0016 }
        r4 = r0;
    L_0x004c:
        if (r4 == 0) goto L_0x010c;
    L_0x004e:
        r0 = "MLSD";
    L_0x0050:
        if (r12 == 0) goto L_0x006f;
    L_0x0052:
        r6 = r12.length();	 Catch:{ all -> 0x0016 }
        if (r6 <= 0) goto L_0x006f;
    L_0x0058:
        r6 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0016 }
        r6.<init>();	 Catch:{ all -> 0x0016 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0016 }
        r6 = " ";
        r0 = r0.append(r6);	 Catch:{ all -> 0x0016 }
        r0 = r0.append(r12);	 Catch:{ all -> 0x0016 }
        r0 = r0.toString();	 Catch:{ all -> 0x0016 }
    L_0x006f:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
        r6.<init>();	 Catch:{ all -> 0x0016 }
        r7 = r11.communication;	 Catch:{ all -> 0x0016 }
        r7.sendFTPCommand(r0);	 Catch:{ all -> 0x0016 }
        r7 = r2.openDataTransferConnection();	 Catch:{ all -> 0x0110 }
        r2.dispose();	 Catch:{ all -> 0x0115 }
        r2 = r11.abortLock;	 Catch:{ all -> 0x0115 }
        monitor-enter(r2);	 Catch:{ all -> 0x0115 }
        r0 = 1;
        r11.ongoingDataTransfer = r0;	 Catch:{ all -> 0x0118 }
        r0 = 0;
        r11.aborted = r0;	 Catch:{ all -> 0x0118 }
        r0 = 0;
        r11.consumeAborCommandReply = r0;	 Catch:{ all -> 0x0118 }
        monitor-exit(r2);	 Catch:{ all -> 0x0118 }
        r0 = r7.getInputStream();	 Catch:{ IOException -> 0x0212 }
        r11.dataTransferInputStream = r0;	 Catch:{ IOException -> 0x0212 }
        r0 = r11.modezEnabled;	 Catch:{ IOException -> 0x0212 }
        if (r0 == 0) goto L_0x00a0;
    L_0x0097:
        r0 = new java.util.zip.InflaterInputStream;	 Catch:{ IOException -> 0x0212 }
        r2 = r11.dataTransferInputStream;	 Catch:{ IOException -> 0x0212 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0212 }
        r11.dataTransferInputStream = r0;	 Catch:{ IOException -> 0x0212 }
    L_0x00a0:
        r2 = new it.sauronsoftware.ftp4j.NVTASCIIReader;	 Catch:{ IOException -> 0x0212 }
        r8 = r11.dataTransferInputStream;	 Catch:{ IOException -> 0x0212 }
        if (r4 == 0) goto L_0x011b;
    L_0x00a6:
        r0 = "UTF-8";
    L_0x00a8:
        r2.<init>(r8, r0);	 Catch:{ IOException -> 0x0212 }
    L_0x00ab:
        r0 = r2.readLine();	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        if (r0 == 0) goto L_0x01b7;
    L_0x00b1:
        r8 = r0.length();	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        if (r8 <= 0) goto L_0x00ab;
    L_0x00b7:
        r6.add(r0);	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        goto L_0x00ab;
    L_0x00bb:
        r0 = move-exception;
        r1 = r2;
    L_0x00bd:
        r2 = r11.abortLock;	 Catch:{ all -> 0x00cd }
        monitor-enter(r2);	 Catch:{ all -> 0x00cd }
        r4 = r11.aborted;	 Catch:{ all -> 0x00ca }
        if (r4 == 0) goto L_0x0120;
    L_0x00c4:
        r0 = new it.sauronsoftware.ftp4j.FTPAbortedException;	 Catch:{ all -> 0x00ca }
        r0.<init>();	 Catch:{ all -> 0x00ca }
        throw r0;	 Catch:{ all -> 0x00ca }
    L_0x00ca:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00ca }
        throw r0;	 Catch:{ all -> 0x00cd }
    L_0x00cd:
        r0 = move-exception;
    L_0x00ce:
        if (r1 == 0) goto L_0x00d3;
    L_0x00d0:
        r1.close();	 Catch:{ Throwable -> 0x01ad }
    L_0x00d3:
        r7.close();	 Catch:{ Throwable -> 0x01b0 }
    L_0x00d6:
        r1 = 0;
        r11.dataTransferInputStream = r1;	 Catch:{ all -> 0x0115 }
        r2 = r11.abortLock;	 Catch:{ all -> 0x0115 }
        monitor-enter(r2);	 Catch:{ all -> 0x0115 }
        r1 = r11.aborted;	 Catch:{ all -> 0x01b3 }
        r3 = 0;
        r11.ongoingDataTransfer = r3;	 Catch:{ all -> 0x020c }
        r3 = 0;
        r11.aborted = r3;	 Catch:{ all -> 0x020c }
        monitor-exit(r2);	 Catch:{ all -> 0x020c }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x00e6:
        r0 = move-exception;
    L_0x00e7:
        r2 = r11.communication;	 Catch:{ all -> 0x0016 }
        r2 = r2.readFTPReply();	 Catch:{ all -> 0x0016 }
        r11.touchAutoNoopTimer();	 Catch:{ all -> 0x0016 }
        r3 = r2.getCode();	 Catch:{ all -> 0x0016 }
        if (r3 == r10) goto L_0x0197;
    L_0x00f6:
        r3 = r2.getCode();	 Catch:{ all -> 0x0016 }
        if (r3 == r9) goto L_0x0197;
    L_0x00fc:
        r0 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0102:
        r4 = r11.mlsdPolicy;	 Catch:{ all -> 0x0016 }
        if (r4 != r0) goto L_0x0109;
    L_0x0106:
        r4 = r0;
        goto L_0x004c;
    L_0x0109:
        r4 = r3;
        goto L_0x004c;
    L_0x010c:
        r0 = "LIST";
        goto L_0x0050;
    L_0x0110:
        r0 = move-exception;
        r2.dispose();	 Catch:{ all -> 0x0115 }
        throw r0;	 Catch:{ all -> 0x0115 }
    L_0x0115:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00e7;
    L_0x0118:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0118 }
        throw r0;	 Catch:{ all -> 0x0115 }
    L_0x011b:
        r0 = r11.pickCharset();	 Catch:{ IOException -> 0x0212 }
        goto L_0x00a8;
    L_0x0120:
        r4 = new it.sauronsoftware.ftp4j.FTPDataTransferException;	 Catch:{ all -> 0x00ca }
        r6 = "I/O error in data transfer";
        r4.<init>(r6, r0);	 Catch:{ all -> 0x00ca }
        throw r4;	 Catch:{ all -> 0x00ca }
    L_0x0128:
        r1 = r11.consumeAborCommandReply;	 Catch:{ all -> 0x0016 }
        if (r1 == 0) goto L_0x0134;
    L_0x012c:
        r1 = r11.communication;	 Catch:{ all -> 0x0016 }
        r1.readFTPReply();	 Catch:{ all -> 0x0016 }
        r1 = 0;
        r11.consumeAborCommandReply = r1;	 Catch:{ all -> 0x0016 }
    L_0x0134:
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0135:
        r0 = r11.consumeAborCommandReply;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0141;
    L_0x0139:
        r0 = r11.communication;	 Catch:{ all -> 0x0016 }
        r0.readFTPReply();	 Catch:{ all -> 0x0016 }
        r0 = 0;
        r11.consumeAborCommandReply = r0;	 Catch:{ all -> 0x0016 }
    L_0x0141:
        r2 = r6.size();	 Catch:{ all -> 0x0016 }
        r7 = new java.lang.String[r2];	 Catch:{ all -> 0x0016 }
    L_0x0147:
        if (r3 >= r2) goto L_0x0154;
    L_0x0149:
        r0 = r6.get(r3);	 Catch:{ all -> 0x0016 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0016 }
        r7[r3] = r0;	 Catch:{ all -> 0x0016 }
        r3 = r3 + 1;
        goto L_0x0147;
    L_0x0154:
        if (r4 == 0) goto L_0x0167;
    L_0x0156:
        r0 = new it.sauronsoftware.ftp4j.listparsers.MLSDListParser;	 Catch:{ all -> 0x0016 }
        r0.<init>();	 Catch:{ all -> 0x0016 }
        r1 = r0.parse(r7);	 Catch:{ all -> 0x0016 }
    L_0x015f:
        if (r1 != 0) goto L_0x0195;
    L_0x0161:
        r0 = new it.sauronsoftware.ftp4j.FTPListParseException;	 Catch:{ all -> 0x0016 }
        r0.<init>();	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0167:
        r0 = r11.parser;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0171;
    L_0x016b:
        r0 = r11.parser;	 Catch:{ FTPListParseException -> 0x0190 }
        r1 = r0.parse(r7);	 Catch:{ FTPListParseException -> 0x0190 }
    L_0x0171:
        if (r1 != 0) goto L_0x015f;
    L_0x0173:
        r0 = r11.listParsers;	 Catch:{ all -> 0x0016 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0016 }
    L_0x0179:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x015f;
    L_0x017f:
        r0 = r2.next();	 Catch:{ all -> 0x0016 }
        r0 = (it.sauronsoftware.ftp4j.FTPListParser) r0;	 Catch:{ all -> 0x0016 }
        r1 = r0.parse(r7);	 Catch:{ FTPListParseException -> 0x018c }
        r11.parser = r0;	 Catch:{ FTPListParseException -> 0x018c }
        goto L_0x015f;
    L_0x018c:
        r0 = move-exception;
        r0 = r1;
        r1 = r0;
        goto L_0x0179;
    L_0x0190:
        r0 = move-exception;
        r0 = 0;
        r11.parser = r0;	 Catch:{ all -> 0x0016 }
        goto L_0x0171;
    L_0x0195:
        monitor-exit(r5);	 Catch:{ all -> 0x0016 }
        return r1;
    L_0x0197:
        r2 = r11.communication;	 Catch:{ all -> 0x0016 }
        r2 = r2.readFTPReply();	 Catch:{ all -> 0x0016 }
        if (r1 != 0) goto L_0x0128;
    L_0x019f:
        r1 = r2.getCode();	 Catch:{ all -> 0x0016 }
        r3 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        if (r1 == r3) goto L_0x0128;
    L_0x01a7:
        r0 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x01ad:
        r1 = move-exception;
        goto L_0x00d3;
    L_0x01b0:
        r1 = move-exception;
        goto L_0x00d6;
    L_0x01b3:
        r0 = move-exception;
        r1 = r3;
    L_0x01b5:
        monitor-exit(r2);	 Catch:{ all -> 0x020c }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x01b7:
        if (r2 == 0) goto L_0x01bc;
    L_0x01b9:
        r2.close();	 Catch:{ Throwable -> 0x01e9 }
    L_0x01bc:
        r7.close();	 Catch:{ Throwable -> 0x01eb }
    L_0x01bf:
        r0 = 0;
        r11.dataTransferInputStream = r0;	 Catch:{ all -> 0x0115 }
        r7 = r11.abortLock;	 Catch:{ all -> 0x0115 }
        monitor-enter(r7);	 Catch:{ all -> 0x0115 }
        r2 = r11.aborted;	 Catch:{ all -> 0x01ed }
        r0 = 0;
        r11.ongoingDataTransfer = r0;	 Catch:{ all -> 0x0209 }
        r0 = 0;
        r11.aborted = r0;	 Catch:{ all -> 0x0209 }
        monitor-exit(r7);	 Catch:{ all -> 0x0209 }
        r0 = r11.communication;	 Catch:{ all -> 0x0016 }
        r0 = r0.readFTPReply();	 Catch:{ all -> 0x0016 }
        r11.touchAutoNoopTimer();	 Catch:{ all -> 0x0016 }
        r7 = r0.getCode();	 Catch:{ all -> 0x0016 }
        if (r7 == r10) goto L_0x01f1;
    L_0x01dd:
        r7 = r0.getCode();	 Catch:{ all -> 0x0016 }
        if (r7 == r9) goto L_0x01f1;
    L_0x01e3:
        r1 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0016 }
        r1.<init>(r0);	 Catch:{ all -> 0x0016 }
        throw r1;	 Catch:{ all -> 0x0016 }
    L_0x01e9:
        r0 = move-exception;
        goto L_0x01bc;
    L_0x01eb:
        r0 = move-exception;
        goto L_0x01bf;
    L_0x01ed:
        r0 = move-exception;
        r1 = r3;
    L_0x01ef:
        monitor-exit(r7);	 Catch:{ all -> 0x0207 }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x01f1:
        r0 = r11.communication;	 Catch:{ all -> 0x0016 }
        r0 = r0.readFTPReply();	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0135;
    L_0x01f9:
        r2 = r0.getCode();	 Catch:{ all -> 0x0016 }
        r7 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        if (r2 == r7) goto L_0x0135;
    L_0x0201:
        r1 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0016 }
        r1.<init>(r0);	 Catch:{ all -> 0x0016 }
        throw r1;	 Catch:{ all -> 0x0016 }
    L_0x0207:
        r0 = move-exception;
        goto L_0x01ef;
    L_0x0209:
        r0 = move-exception;
        r1 = r2;
        goto L_0x01ef;
    L_0x020c:
        r0 = move-exception;
        goto L_0x01b5;
    L_0x020e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ce;
    L_0x0212:
        r0 = move-exception;
        goto L_0x00bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.ftp4j.FTPClient.list(java.lang.String):it.sauronsoftware.ftp4j.FTPFile[]");
    }

    public FTPFile[] list() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException, FTPListParseException {
        return list(null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] listNames() throws java.lang.IllegalStateException, java.io.IOException, it.sauronsoftware.ftp4j.FTPIllegalReplyException, it.sauronsoftware.ftp4j.FTPException, it.sauronsoftware.ftp4j.FTPDataTransferException, it.sauronsoftware.ftp4j.FTPAbortedException, it.sauronsoftware.ftp4j.FTPListParseException {
        /*
        r11 = this;
        r10 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        r9 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r1 = 0;
        r3 = 0;
        r4 = r11.lock;
        monitor-enter(r4);
        r0 = r11.connected;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x001a;
    L_0x000f:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0017 }
        r1 = "Client not connected";
        r0.<init>(r1);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r11.authenticated;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x0026;
    L_0x001e:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0017 }
        r1 = "Client not authenticated";
        r0.<init>(r1);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x0026:
        r0 = r11.communication;	 Catch:{ all -> 0x0017 }
        r2 = "TYPE A";
        r0.sendFTPCommand(r2);	 Catch:{ all -> 0x0017 }
        r0 = r11.communication;	 Catch:{ all -> 0x0017 }
        r0 = r0.readFTPReply();	 Catch:{ all -> 0x0017 }
        r11.touchAutoNoopTimer();	 Catch:{ all -> 0x0017 }
        r2 = r0.isSuccessCode();	 Catch:{ all -> 0x0017 }
        if (r2 != 0) goto L_0x0042;
    L_0x003c:
        r1 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0042:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0017 }
        r5.<init>();	 Catch:{ all -> 0x0017 }
        r0 = r11.openDataTransferChannel();	 Catch:{ all -> 0x0017 }
        r2 = r11.communication;	 Catch:{ all -> 0x0017 }
        r6 = "NLST";
        r2.sendFTPCommand(r6);	 Catch:{ all -> 0x0017 }
        r6 = r0.openDataTransferConnection();	 Catch:{ all -> 0x00d8 }
        r0.dispose();	 Catch:{ all -> 0x00dd }
        r2 = r11.abortLock;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r0 = 1;
        r11.ongoingDataTransfer = r0;	 Catch:{ all -> 0x00e0 }
        r0 = 0;
        r11.aborted = r0;	 Catch:{ all -> 0x00e0 }
        r0 = 0;
        r11.consumeAborCommandReply = r0;	 Catch:{ all -> 0x00e0 }
        monitor-exit(r2);	 Catch:{ all -> 0x00e0 }
        r0 = r6.getInputStream();	 Catch:{ IOException -> 0x018a }
        r11.dataTransferInputStream = r0;	 Catch:{ IOException -> 0x018a }
        r0 = r11.modezEnabled;	 Catch:{ IOException -> 0x018a }
        if (r0 == 0) goto L_0x0079;
    L_0x0070:
        r0 = new java.util.zip.InflaterInputStream;	 Catch:{ IOException -> 0x018a }
        r2 = r11.dataTransferInputStream;	 Catch:{ IOException -> 0x018a }
        r0.<init>(r2);	 Catch:{ IOException -> 0x018a }
        r11.dataTransferInputStream = r0;	 Catch:{ IOException -> 0x018a }
    L_0x0079:
        r2 = new it.sauronsoftware.ftp4j.NVTASCIIReader;	 Catch:{ IOException -> 0x018a }
        r0 = r11.dataTransferInputStream;	 Catch:{ IOException -> 0x018a }
        r7 = r11.pickCharset();	 Catch:{ IOException -> 0x018a }
        r2.<init>(r0, r7);	 Catch:{ IOException -> 0x018a }
    L_0x0084:
        r0 = r2.readLine();	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        if (r0 == 0) goto L_0x0137;
    L_0x008a:
        r1 = r0.length();	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        if (r1 <= 0) goto L_0x0084;
    L_0x0090:
        r5.add(r0);	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        goto L_0x0084;
    L_0x0094:
        r0 = move-exception;
        r1 = r2;
    L_0x0096:
        r2 = r11.abortLock;	 Catch:{ all -> 0x00a6 }
        monitor-enter(r2);	 Catch:{ all -> 0x00a6 }
        r5 = r11.aborted;	 Catch:{ all -> 0x00a3 }
        if (r5 == 0) goto L_0x00e3;
    L_0x009d:
        r0 = new it.sauronsoftware.ftp4j.FTPAbortedException;	 Catch:{ all -> 0x00a3 }
        r0.<init>();	 Catch:{ all -> 0x00a3 }
        throw r0;	 Catch:{ all -> 0x00a3 }
    L_0x00a3:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00a3 }
        throw r0;	 Catch:{ all -> 0x00a6 }
    L_0x00a6:
        r0 = move-exception;
    L_0x00a7:
        if (r1 == 0) goto L_0x00ac;
    L_0x00a9:
        r1.close();	 Catch:{ Throwable -> 0x012d }
    L_0x00ac:
        r6.close();	 Catch:{ Throwable -> 0x0130 }
    L_0x00af:
        r1 = 0;
        r11.dataTransferInputStream = r1;	 Catch:{ all -> 0x00dd }
        r2 = r11.abortLock;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r1 = r11.aborted;	 Catch:{ all -> 0x0133 }
        r3 = 0;
        r11.ongoingDataTransfer = r3;	 Catch:{ all -> 0x0184 }
        r3 = 0;
        r11.aborted = r3;	 Catch:{ all -> 0x0184 }
        monitor-exit(r2);	 Catch:{ all -> 0x0184 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x00bf:
        r0 = move-exception;
    L_0x00c0:
        r2 = r11.communication;	 Catch:{ all -> 0x0017 }
        r2 = r2.readFTPReply();	 Catch:{ all -> 0x0017 }
        r3 = r2.getCode();	 Catch:{ all -> 0x0017 }
        if (r3 == r9) goto L_0x0119;
    L_0x00cc:
        r3 = r2.getCode();	 Catch:{ all -> 0x0017 }
        if (r3 == r8) goto L_0x0119;
    L_0x00d2:
        r0 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0017 }
        r0.<init>(r2);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x00d8:
        r1 = move-exception;
        r0.dispose();	 Catch:{ all -> 0x00dd }
        throw r1;	 Catch:{ all -> 0x00dd }
    L_0x00dd:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00c0;
    L_0x00e0:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00e0 }
        throw r0;	 Catch:{ all -> 0x00dd }
    L_0x00e3:
        r5 = new it.sauronsoftware.ftp4j.FTPDataTransferException;	 Catch:{ all -> 0x00a3 }
        r7 = "I/O error in data transfer";
        r5.<init>(r7, r0);	 Catch:{ all -> 0x00a3 }
        throw r5;	 Catch:{ all -> 0x00a3 }
    L_0x00eb:
        r1 = r11.consumeAborCommandReply;	 Catch:{ all -> 0x0017 }
        if (r1 == 0) goto L_0x00f7;
    L_0x00ef:
        r1 = r11.communication;	 Catch:{ all -> 0x0017 }
        r1.readFTPReply();	 Catch:{ all -> 0x0017 }
        r1 = 0;
        r11.consumeAborCommandReply = r1;	 Catch:{ all -> 0x0017 }
    L_0x00f7:
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x00f8:
        r0 = r11.consumeAborCommandReply;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0104;
    L_0x00fc:
        r0 = r11.communication;	 Catch:{ all -> 0x0017 }
        r0.readFTPReply();	 Catch:{ all -> 0x0017 }
        r0 = 0;
        r11.consumeAborCommandReply = r0;	 Catch:{ all -> 0x0017 }
    L_0x0104:
        r1 = r5.size();	 Catch:{ all -> 0x0017 }
        r2 = new java.lang.String[r1];	 Catch:{ all -> 0x0017 }
    L_0x010a:
        if (r3 >= r1) goto L_0x0117;
    L_0x010c:
        r0 = r5.get(r3);	 Catch:{ all -> 0x0017 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0017 }
        r2[r3] = r0;	 Catch:{ all -> 0x0017 }
        r3 = r3 + 1;
        goto L_0x010a;
    L_0x0117:
        monitor-exit(r4);	 Catch:{ all -> 0x0017 }
        return r2;
    L_0x0119:
        r2 = r11.communication;	 Catch:{ all -> 0x0017 }
        r2 = r2.readFTPReply();	 Catch:{ all -> 0x0017 }
        if (r1 != 0) goto L_0x00eb;
    L_0x0121:
        r1 = r2.getCode();	 Catch:{ all -> 0x0017 }
        if (r1 == r10) goto L_0x00eb;
    L_0x0127:
        r0 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0017 }
        r0.<init>(r2);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x012d:
        r1 = move-exception;
        goto L_0x00ac;
    L_0x0130:
        r1 = move-exception;
        goto L_0x00af;
    L_0x0133:
        r0 = move-exception;
        r1 = r3;
    L_0x0135:
        monitor-exit(r2);	 Catch:{ all -> 0x0184 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x0137:
        if (r2 == 0) goto L_0x013c;
    L_0x0139:
        r2.close();	 Catch:{ Throwable -> 0x0166 }
    L_0x013c:
        r6.close();	 Catch:{ Throwable -> 0x0168 }
    L_0x013f:
        r0 = 0;
        r11.dataTransferInputStream = r0;	 Catch:{ all -> 0x00dd }
        r2 = r11.abortLock;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r1 = r11.aborted;	 Catch:{ all -> 0x016a }
        r0 = 0;
        r11.ongoingDataTransfer = r0;	 Catch:{ all -> 0x0182 }
        r0 = 0;
        r11.aborted = r0;	 Catch:{ all -> 0x0182 }
        monitor-exit(r2);	 Catch:{ all -> 0x0182 }
        r0 = r11.communication;	 Catch:{ all -> 0x0017 }
        r0 = r0.readFTPReply();	 Catch:{ all -> 0x0017 }
        r2 = r0.getCode();	 Catch:{ all -> 0x0017 }
        if (r2 == r9) goto L_0x016e;
    L_0x015a:
        r2 = r0.getCode();	 Catch:{ all -> 0x0017 }
        if (r2 == r8) goto L_0x016e;
    L_0x0160:
        r1 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0166:
        r0 = move-exception;
        goto L_0x013c;
    L_0x0168:
        r0 = move-exception;
        goto L_0x013f;
    L_0x016a:
        r0 = move-exception;
        r1 = r3;
    L_0x016c:
        monitor-exit(r2);	 Catch:{ all -> 0x0182 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x016e:
        r0 = r11.communication;	 Catch:{ all -> 0x0017 }
        r0 = r0.readFTPReply();	 Catch:{ all -> 0x0017 }
        if (r1 != 0) goto L_0x00f8;
    L_0x0176:
        r1 = r0.getCode();	 Catch:{ all -> 0x0017 }
        if (r1 == r10) goto L_0x00f8;
    L_0x017c:
        r1 = new it.sauronsoftware.ftp4j.FTPException;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0182:
        r0 = move-exception;
        goto L_0x016c;
    L_0x0184:
        r0 = move-exception;
        goto L_0x0135;
    L_0x0186:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00a7;
    L_0x018a:
        r0 = move-exception;
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sauronsoftware.ftp4j.FTPClient.listNames():java.lang.String[]");
    }

    public void upload(File file) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        upload(file, 0, null);
    }

    public void upload(File file, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        upload(file, 0, fTPDataTransferListener);
    }

    public void upload(File file, long j) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        upload(file, j, null);
    }

    public void upload(File file, long j, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    upload(file.getName(), fileInputStream, j, j, fTPDataTransferListener);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    }
                    return;
                } catch (IllegalStateException e) {
                    throw e;
                } catch (IOException e2) {
                    throw e2;
                } catch (FTPIllegalReplyException e3) {
                    throw e3;
                } catch (FTPException e4) {
                    throw e4;
                } catch (FTPDataTransferException e5) {
                    throw e5;
                } catch (FTPAbortedException e6) {
                    throw e6;
                } catch (Throwable th2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                }
            } catch (Throwable e7) {
                throw new FTPDataTransferException(e7);
            }
        }
        throw new FileNotFoundException(file.getAbsolutePath());
    }

    public void upload(String str, InputStream inputStream, long j, long j2, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                int i = this.type;
                if (i == 0) {
                    i = detectType(str);
                }
                if (i == 1) {
                    this.communication.sendFTPCommand("TYPE A");
                } else if (i == 2) {
                    this.communication.sendFTPCommand("TYPE I");
                }
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    FTPDataTransferConnectionProvider openDataTransferChannel = openDataTransferChannel();
                    if (this.restSupported || j > 0) {
                        try {
                            this.communication.sendFTPCommand(new StringBuffer().append("REST ").append(j).toString());
                            readFTPReply = this.communication.readFTPReply();
                            touchAutoNoopTimer();
                            if (readFTPReply.getCode() != FTPCodes.PENDING_FURTHER_INFORMATION && (!(readFTPReply.getCode() == FTPCodes.SYNTAX_ERROR_IN_PARAMETERS || readFTPReply.getCode() == 502) || j > 0)) {
                                throw new FTPException(readFTPReply);
                            }
                        } catch (Throwable th) {
                            openDataTransferChannel.dispose();
                        }
                    }
                    boolean z = false;
                    this.communication.sendFTPCommand(new StringBuffer().append("STOR ").append(str).toString());
                    try {
                        Socket openDataTransferConnection = openDataTransferChannel.openDataTransferConnection();
                        openDataTransferChannel.dispose();
                        synchronized (this.abortLock) {
                            this.ongoingDataTransfer = true;
                            this.aborted = false;
                            this.consumeAborCommandReply = false;
                        }
                        try {
                            inputStream.skip(j2);
                            this.dataTransferOutputStream = openDataTransferConnection.getOutputStream();
                            if (this.modezEnabled) {
                                this.dataTransferOutputStream = new DeflaterOutputStream(this.dataTransferOutputStream);
                            }
                            if (fTPDataTransferListener != null) {
                                fTPDataTransferListener.started();
                            }
                            if (i == 1) {
                                Reader inputStreamReader = new InputStreamReader(inputStream);
                                Writer outputStreamWriter = new OutputStreamWriter(this.dataTransferOutputStream, pickCharset());
                                char[] cArr = new char[65536];
                                while (true) {
                                    int read = inputStreamReader.read(cArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, 0, read);
                                    outputStreamWriter.flush();
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read);
                                    }
                                }
                            } else if (i == 2) {
                                byte[] bArr = new byte[65536];
                                while (true) {
                                    int read2 = inputStream.read(bArr);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    this.dataTransferOutputStream.write(bArr, 0, read2);
                                    this.dataTransferOutputStream.flush();
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read2);
                                    }
                                }
                            }
                            if (this.dataTransferOutputStream != null) {
                                try {
                                    this.dataTransferOutputStream.close();
                                } catch (Throwable th2) {
                                }
                            }
                            try {
                                openDataTransferConnection.close();
                            } catch (Throwable th3) {
                            }
                            this.dataTransferOutputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                            FTPReply readFTPReply2 = this.communication.readFTPReply();
                            touchAutoNoopTimer();
                            if (readFTPReply2.getCode() == 150 || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_ALREADY_OPEN) {
                                readFTPReply2 = this.communication.readFTPReply();
                                if (z || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_CLOSING) {
                                    if (this.consumeAborCommandReply) {
                                        this.communication.readFTPReply();
                                        this.consumeAborCommandReply = false;
                                    }
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.completed();
                                    }
                                } else {
                                    throw new FTPException(readFTPReply2);
                                }
                            }
                            throw new FTPException(readFTPReply2);
                        } catch (Throwable e) {
                            synchronized (this.abortLock) {
                                if (this.aborted) {
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.aborted();
                                    }
                                    throw new FTPAbortedException();
                                }
                                if (fTPDataTransferListener != null) {
                                    fTPDataTransferListener.failed();
                                }
                                throw new FTPDataTransferException("I/O error in data transfer", e);
                            }
                        } catch (Throwable th4) {
                            if (this.dataTransferOutputStream != null) {
                                this.dataTransferOutputStream.close();
                            }
                            openDataTransferConnection.close();
                            this.dataTransferOutputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                        }
                    } catch (Throwable th5) {
                    }
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void append(File file) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        append(file, null);
    }

    public void append(File file, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    append(file.getName(), fileInputStream, 0, fTPDataTransferListener);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    }
                    return;
                } catch (IllegalStateException e) {
                    throw e;
                } catch (IOException e2) {
                    throw e2;
                } catch (FTPIllegalReplyException e3) {
                    throw e3;
                } catch (FTPException e4) {
                    throw e4;
                } catch (FTPDataTransferException e5) {
                    throw e5;
                } catch (FTPAbortedException e6) {
                    throw e6;
                } catch (Throwable th2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                }
            } catch (Throwable e7) {
                throw new FTPDataTransferException(e7);
            }
        }
        throw new FileNotFoundException(file.getAbsolutePath());
    }

    public void append(String str, InputStream inputStream, long j, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                int i = this.type;
                if (i == 0) {
                    i = detectType(str);
                }
                if (i == 1) {
                    this.communication.sendFTPCommand("TYPE A");
                } else if (i == 2) {
                    this.communication.sendFTPCommand("TYPE I");
                }
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    boolean z = false;
                    FTPDataTransferConnectionProvider openDataTransferChannel = openDataTransferChannel();
                    this.communication.sendFTPCommand(new StringBuffer().append("APPE ").append(str).toString());
                    try {
                        Socket openDataTransferConnection = openDataTransferChannel.openDataTransferConnection();
                        openDataTransferChannel.dispose();
                        synchronized (this.abortLock) {
                            this.ongoingDataTransfer = true;
                            this.aborted = false;
                            this.consumeAborCommandReply = false;
                        }
                        try {
                            inputStream.skip(j);
                            this.dataTransferOutputStream = openDataTransferConnection.getOutputStream();
                            if (this.modezEnabled) {
                                this.dataTransferOutputStream = new DeflaterOutputStream(this.dataTransferOutputStream);
                            }
                            if (fTPDataTransferListener != null) {
                                fTPDataTransferListener.started();
                            }
                            if (i == 1) {
                                Reader inputStreamReader = new InputStreamReader(inputStream);
                                Writer outputStreamWriter = new OutputStreamWriter(this.dataTransferOutputStream, pickCharset());
                                char[] cArr = new char[65536];
                                while (true) {
                                    int read = inputStreamReader.read(cArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, 0, read);
                                    outputStreamWriter.flush();
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read);
                                    }
                                }
                            } else if (i == 2) {
                                byte[] bArr = new byte[65536];
                                while (true) {
                                    int read2 = inputStream.read(bArr);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    this.dataTransferOutputStream.write(bArr, 0, read2);
                                    this.dataTransferOutputStream.flush();
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read2);
                                    }
                                }
                            }
                            if (this.dataTransferOutputStream != null) {
                                try {
                                    this.dataTransferOutputStream.close();
                                } catch (Throwable th) {
                                }
                            }
                            try {
                                openDataTransferConnection.close();
                            } catch (Throwable th2) {
                            }
                            this.dataTransferOutputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                            FTPReply readFTPReply2 = this.communication.readFTPReply();
                            touchAutoNoopTimer();
                            if (readFTPReply2.getCode() == 150 || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_ALREADY_OPEN) {
                                readFTPReply2 = this.communication.readFTPReply();
                                if (z || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_CLOSING) {
                                    if (this.consumeAborCommandReply) {
                                        this.communication.readFTPReply();
                                        this.consumeAborCommandReply = false;
                                    }
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.completed();
                                    }
                                } else {
                                    throw new FTPException(readFTPReply2);
                                }
                            }
                            throw new FTPException(readFTPReply2);
                        } catch (Throwable e) {
                            synchronized (this.abortLock) {
                                if (this.aborted) {
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.aborted();
                                    }
                                    throw new FTPAbortedException();
                                }
                                if (fTPDataTransferListener != null) {
                                    fTPDataTransferListener.failed();
                                }
                                throw new FTPDataTransferException("I/O error in data transfer", e);
                            }
                        } catch (Throwable th3) {
                            if (this.dataTransferOutputStream != null) {
                                this.dataTransferOutputStream.close();
                            }
                            openDataTransferConnection.close();
                            this.dataTransferOutputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                        }
                    } catch (Throwable th4) {
                    }
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void download(String str, File file) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        download(str, file, 0, null);
    }

    public void download(String str, File file, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        download(str, file, 0, fTPDataTransferListener);
    }

    public void download(String str, File file, long j) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        download(str, file, j, null);
    }

    public void download(String str, File file, long j, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        try {
            OutputStream fileOutputStream = new FileOutputStream(file, j > 0);
            try {
                download(str, fileOutputStream, j, fTPDataTransferListener);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th) {
                    }
                }
            } catch (IllegalStateException e) {
                throw e;
            } catch (IOException e2) {
                throw e2;
            } catch (FTPIllegalReplyException e3) {
                throw e3;
            } catch (FTPException e4) {
                throw e4;
            } catch (FTPDataTransferException e5) {
                throw e5;
            } catch (FTPAbortedException e6) {
                throw e6;
            } catch (Throwable th2) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                    }
                }
            }
        } catch (Throwable e7) {
            throw new FTPDataTransferException(e7);
        }
    }

    public void download(String str, OutputStream outputStream, long j, FTPDataTransferListener fTPDataTransferListener) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
        synchronized (this.lock) {
            if (!this.connected) {
                throw new IllegalStateException("Client not connected");
            } else if (this.authenticated) {
                int i = this.type;
                if (i == 0) {
                    i = detectType(str);
                }
                if (i == 1) {
                    this.communication.sendFTPCommand("TYPE A");
                } else if (i == 2) {
                    this.communication.sendFTPCommand("TYPE I");
                }
                FTPReply readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    FTPDataTransferConnectionProvider openDataTransferChannel = openDataTransferChannel();
                    if (this.restSupported || j > 0) {
                        try {
                            this.communication.sendFTPCommand(new StringBuffer().append("REST ").append(j).toString());
                            readFTPReply = this.communication.readFTPReply();
                            touchAutoNoopTimer();
                            if (readFTPReply.getCode() != FTPCodes.PENDING_FURTHER_INFORMATION && (!(readFTPReply.getCode() == FTPCodes.SYNTAX_ERROR_IN_PARAMETERS || readFTPReply.getCode() == 502) || j > 0)) {
                                throw new FTPException(readFTPReply);
                            }
                        } catch (Throwable th) {
                            openDataTransferChannel.dispose();
                        }
                    }
                    boolean z = false;
                    this.communication.sendFTPCommand(new StringBuffer().append("RETR ").append(str).toString());
                    try {
                        Socket openDataTransferConnection = openDataTransferChannel.openDataTransferConnection();
                        openDataTransferChannel.dispose();
                        synchronized (this.abortLock) {
                            this.ongoingDataTransfer = true;
                            this.aborted = false;
                            this.consumeAborCommandReply = false;
                        }
                        try {
                            this.dataTransferInputStream = openDataTransferConnection.getInputStream();
                            if (this.modezEnabled) {
                                this.dataTransferInputStream = new InflaterInputStream(this.dataTransferInputStream);
                            }
                            if (fTPDataTransferListener != null) {
                                fTPDataTransferListener.started();
                            }
                            if (i == 1) {
                                Reader inputStreamReader = new InputStreamReader(this.dataTransferInputStream, pickCharset());
                                Writer outputStreamWriter = new OutputStreamWriter(outputStream);
                                char[] cArr = new char[65536];
                                while (true) {
                                    int read = inputStreamReader.read(cArr, 0, cArr.length);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, 0, read);
                                    outputStreamWriter.flush();
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read);
                                    }
                                }
                            } else if (i == 2) {
                                byte[] bArr = new byte[65536];
                                while (true) {
                                    int read2 = this.dataTransferInputStream.read(bArr, 0, bArr.length);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    outputStream.write(bArr, 0, read2);
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.transferred(read2);
                                    }
                                }
                            }
                            if (this.dataTransferInputStream != null) {
                                try {
                                    this.dataTransferInputStream.close();
                                } catch (Throwable th2) {
                                }
                            }
                            try {
                                openDataTransferConnection.close();
                            } catch (Throwable th3) {
                            }
                            this.dataTransferInputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                            FTPReply readFTPReply2 = this.communication.readFTPReply();
                            touchAutoNoopTimer();
                            if (readFTPReply2.getCode() == 150 || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_ALREADY_OPEN) {
                                readFTPReply2 = this.communication.readFTPReply();
                                if (z || readFTPReply2.getCode() == FTPCodes.DATA_CONNECTION_CLOSING) {
                                    if (this.consumeAborCommandReply) {
                                        this.communication.readFTPReply();
                                        this.consumeAborCommandReply = false;
                                    }
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.completed();
                                    }
                                } else {
                                    throw new FTPException(readFTPReply2);
                                }
                            }
                            throw new FTPException(readFTPReply2);
                        } catch (Throwable e) {
                            synchronized (this.abortLock) {
                                if (this.aborted) {
                                    if (fTPDataTransferListener != null) {
                                        fTPDataTransferListener.aborted();
                                    }
                                    throw new FTPAbortedException();
                                }
                                if (fTPDataTransferListener != null) {
                                    fTPDataTransferListener.failed();
                                }
                                throw new FTPDataTransferException("I/O error in data transfer", e);
                            }
                        } catch (Throwable th4) {
                            if (this.dataTransferInputStream != null) {
                                this.dataTransferInputStream.close();
                            }
                            openDataTransferConnection.close();
                            this.dataTransferInputStream = null;
                            synchronized (this.abortLock) {
                                z = this.aborted;
                                this.ongoingDataTransfer = false;
                                this.aborted = false;
                            }
                        }
                    } catch (Throwable th5) {
                    }
                } else {
                    throw new FTPException(readFTPReply);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    private int detectType(String str) throws IOException, FTPIllegalReplyException, FTPException {
        int lastIndexOf = str.lastIndexOf(46) + 1;
        int length = str.length();
        if (lastIndexOf <= 0 || lastIndexOf >= length - 1) {
            return 2;
        }
        if (this.textualExtensionRecognizer.isTextualExt(str.substring(lastIndexOf, length).toLowerCase())) {
            return 1;
        }
        return 2;
    }

    private FTPDataTransferConnectionProvider openDataTransferChannel() throws IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException {
        FTPReply readFTPReply;
        if (this.modezSupported && this.compressionEnabled) {
            if (!this.modezEnabled) {
                this.communication.sendFTPCommand("MODE Z");
                readFTPReply = this.communication.readFTPReply();
                touchAutoNoopTimer();
                if (readFTPReply.isSuccessCode()) {
                    this.modezEnabled = true;
                }
            }
        } else if (this.modezEnabled) {
            this.communication.sendFTPCommand("MODE S");
            readFTPReply = this.communication.readFTPReply();
            touchAutoNoopTimer();
            if (readFTPReply.isSuccessCode()) {
                this.modezEnabled = false;
            }
        }
        if (this.passive) {
            return openPassiveDataTransferChannel();
        }
        return openActiveDataTransferChannel();
    }

    private FTPDataTransferConnectionProvider openActiveDataTransferChannel() throws IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException {
        FTPDataTransferConnectionProvider anonymousClass1 = new FTPDataTransferServer(this) {
            private final FTPClient this$0;

            {
                this.this$0 = r1;
            }

            public Socket openDataTransferConnection() throws FTPDataTransferException {
                Socket openDataTransferConnection = super.openDataTransferConnection();
                if (FTPClient.access$000(this.this$0)) {
                    try {
                        openDataTransferConnection = FTPClient.access$100(this.this$0, openDataTransferConnection, openDataTransferConnection.getInetAddress().getHostName(), openDataTransferConnection.getPort());
                    } catch (Throwable e) {
                        openDataTransferConnection.close();
                    } catch (Throwable th) {
                    }
                }
                return openDataTransferConnection;
                throw new FTPDataTransferException(e);
            }
        };
        int port = anonymousClass1.getPort();
        int i = port >>> 8;
        port &= 255;
        int[] pickLocalAddress = pickLocalAddress();
        this.communication.sendFTPCommand(new StringBuffer().append("PORT ").append(pickLocalAddress[0]).append(",").append(pickLocalAddress[1]).append(",").append(pickLocalAddress[2]).append(",").append(pickLocalAddress[3]).append(",").append(i).append(",").append(port).toString());
        FTPReply readFTPReply = this.communication.readFTPReply();
        touchAutoNoopTimer();
        if (readFTPReply.isSuccessCode()) {
            return anonymousClass1;
        }
        anonymousClass1.dispose();
        try {
            anonymousClass1.openDataTransferConnection().close();
        } catch (Throwable th) {
        }
        throw new FTPException(readFTPReply);
    }

    private FTPDataTransferConnectionProvider openPassiveDataTransferChannel() throws IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException {
        this.communication.sendFTPCommand("PASV");
        FTPReply readFTPReply = this.communication.readFTPReply();
        touchAutoNoopTimer();
        if (readFTPReply.isSuccessCode()) {
            int i;
            String substring;
            String[] messages = readFTPReply.getMessages();
            for (i = 0; i < messages.length; i++) {
                Matcher matcher = PASV_PATTERN.matcher(messages[i]);
                if (matcher.find()) {
                    substring = messages[i].substring(matcher.start(), matcher.end());
                    break;
                }
            }
            substring = null;
            if (substring == null) {
                throw new FTPIllegalReplyException();
            }
            StringTokenizer stringTokenizer = new StringTokenizer(substring, ",");
            i = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt4 = Integer.parseInt(stringTokenizer.nextToken());
            return new AnonymousClass2(this, new StringBuffer().append(i).append(".").append(parseInt).append(".").append(parseInt2).append(".").append(parseInt3).toString(), Integer.parseInt(stringTokenizer.nextToken()) | (parseInt4 << 8));
        }
        throw new FTPException(readFTPReply);
    }

    public void abortCurrentDataTransfer(boolean z) throws IOException, FTPIllegalReplyException {
        synchronized (this.abortLock) {
            if (this.ongoingDataTransfer && !this.aborted) {
                if (z) {
                    this.communication.sendFTPCommand("ABOR");
                    touchAutoNoopTimer();
                    this.consumeAborCommandReply = true;
                }
                if (this.dataTransferInputStream != null) {
                    try {
                        this.dataTransferInputStream.close();
                    } catch (Throwable th) {
                    }
                }
                if (this.dataTransferOutputStream != null) {
                    try {
                        this.dataTransferOutputStream.close();
                    } catch (Throwable th2) {
                    }
                }
                this.aborted = true;
            }
        }
    }

    private String pickCharset() {
        if (this.charset != null) {
            return this.charset;
        }
        if (this.utf8Supported) {
            return "UTF-8";
        }
        return System.getProperty("file.encoding");
    }

    private int[] pickLocalAddress() throws IOException {
        int[] pickForcedLocalAddress = pickForcedLocalAddress();
        if (pickForcedLocalAddress == null) {
            return pickAutoDetectedLocalAddress();
        }
        return pickForcedLocalAddress;
    }

    private int[] pickForcedLocalAddress() {
        Object obj = null;
        int[] iArr = null;
        String property = System.getProperty(FTPKeys.ACTIVE_DT_HOST_ADDRESS);
        if (property != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property, ".");
            if (stringTokenizer.countTokens() == 4) {
                int[] iArr2 = new int[4];
                int i = 0;
                while (i < 4) {
                    try {
                        iArr2[i] = Integer.parseInt(stringTokenizer.nextToken());
                    } catch (NumberFormatException e) {
                        iArr2[i] = -1;
                    }
                    if (iArr2[i] < 0 || iArr2[i] > 255) {
                        break;
                    }
                    i++;
                }
                int i2 = 1;
                if (obj != null) {
                    iArr = iArr2;
                }
            }
            if (obj == null) {
                System.err.println(new StringBuffer().append("WARNING: invalid value \"").append(property).append("\" for the ").append(FTPKeys.ACTIVE_DT_HOST_ADDRESS).append(" system property. The value should ").append("be in the x.x.x.x form.").toString());
            }
        }
        return iArr;
    }

    private int[] pickAutoDetectedLocalAddress() throws IOException {
        byte[] address = InetAddress.getLocalHost().getAddress();
        int i = address[0] & 255;
        int i2 = address[1] & 255;
        int i3 = address[2] & 255;
        int i4 = address[3] & 255;
        return new int[]{i, i2, i3, i4};
    }

    public String toString() {
        String stringBuffer;
        int i = 0;
        synchronized (this.lock) {
            int i2;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(getClass().getName());
            stringBuffer2.append(" [connected=");
            stringBuffer2.append(this.connected);
            if (this.connected) {
                stringBuffer2.append(", host=");
                stringBuffer2.append(this.host);
                stringBuffer2.append(", port=");
                stringBuffer2.append(this.port);
            }
            stringBuffer2.append(", connector=");
            stringBuffer2.append(this.connector);
            stringBuffer2.append(", security=");
            switch (this.security) {
                case 0:
                    stringBuffer2.append("SECURITY_FTP");
                    break;
                case 1:
                    stringBuffer2.append("SECURITY_FTPS");
                    break;
                case 2:
                    stringBuffer2.append("SECURITY_FTPES");
                    break;
            }
            stringBuffer2.append(", authenticated=");
            stringBuffer2.append(this.authenticated);
            if (this.authenticated) {
                stringBuffer2.append(", username=");
                stringBuffer2.append(this.username);
                stringBuffer2.append(", password=");
                StringBuffer stringBuffer3 = new StringBuffer();
                for (i2 = 0; i2 < this.password.length(); i2++) {
                    stringBuffer3.append('*');
                }
                stringBuffer2.append(stringBuffer3);
                stringBuffer2.append(", restSupported=");
                stringBuffer2.append(this.restSupported);
                stringBuffer2.append(", utf8supported=");
                stringBuffer2.append(this.utf8Supported);
                stringBuffer2.append(", mlsdSupported=");
                stringBuffer2.append(this.mlsdSupported);
                stringBuffer2.append(", mode=modezSupported");
                stringBuffer2.append(this.modezSupported);
                stringBuffer2.append(", mode=modezEnabled");
                stringBuffer2.append(this.modezEnabled);
            }
            stringBuffer2.append(", transfer mode=");
            stringBuffer2.append(this.passive ? "passive" : "active");
            stringBuffer2.append(", transfer type=");
            switch (this.type) {
                case 0:
                    stringBuffer2.append("TYPE_AUTO");
                    break;
                case 1:
                    stringBuffer2.append("TYPE_TEXTUAL");
                    break;
                case 2:
                    stringBuffer2.append("TYPE_BINARY");
                    break;
            }
            stringBuffer2.append(", textualExtensionRecognizer=");
            stringBuffer2.append(this.textualExtensionRecognizer);
            FTPListParser[] listParsers = getListParsers();
            if (listParsers.length > 0) {
                stringBuffer2.append(", listParsers=");
                for (i2 = 0; i2 < listParsers.length; i2++) {
                    if (i2 > 0) {
                        stringBuffer2.append(", ");
                    }
                    stringBuffer2.append(listParsers[i2]);
                }
            }
            FTPCommunicationListener[] communicationListeners = getCommunicationListeners();
            if (communicationListeners.length > 0) {
                stringBuffer2.append(", communicationListeners=");
                while (i < communicationListeners.length) {
                    if (i > 0) {
                        stringBuffer2.append(", ");
                    }
                    stringBuffer2.append(communicationListeners[i]);
                    i++;
                }
            }
            stringBuffer2.append(", autoNoopTimeout=");
            stringBuffer2.append(this.autoNoopTimeout);
            stringBuffer2.append(d.H);
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    private void startAutoNoopTimer() {
        if (this.autoNoopTimeout > 0) {
            this.autoNoopTimer = new AutoNoopTimer(this, null);
            this.autoNoopTimer.start();
        }
    }

    private void stopAutoNoopTimer() {
        if (this.autoNoopTimer != null) {
            this.autoNoopTimer.interrupt();
            this.autoNoopTimer = null;
        }
    }

    private void touchAutoNoopTimer() {
        if (this.autoNoopTimer != null) {
            this.nextAutoNoopTime = System.currentTimeMillis() + this.autoNoopTimeout;
        }
    }
}
