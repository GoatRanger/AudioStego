package com.here.odnp.net;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import com.here.odnp.net.IConnectivityManager.IConnectivityListener;
import com.here.posclient.INetworkManager.Connection;
import com.here.posclient.INetworkManager.Connection.MeterStatus;
import com.here.posclient.INetworkManager.Connection.Type;
import com.here.posclient.INetworkManager.Proxy;
import com.here.posclient.PosClientLib.ConnectionChangeAction;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PlatformConnectivityManager implements IConnectivityManager {
    private static final String TAG = "odnp.net.PlatformConnectivityManager";
    private final BroadcastReceiver mActiveConnectionChangeReceiver = new BroadcastReceiver() {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r4, android.content.Intent r5) {
            /*
            r3 = this;
            r1 = com.here.odnp.net.PlatformConnectivityManager.this;
            monitor-enter(r1);
            r0 = "android.net.conn.CONNECTIVITY_CHANGE";
            r2 = r5.getAction();	 Catch:{ all -> 0x0037 }
            r0 = r0.equals(r2);	 Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0093;
        L_0x000f:
            r0 = "noConnectivity";
            r2 = 0;
            r0 = r5.getBooleanExtra(r0, r2);	 Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x001d;
        L_0x0018:
            r3.reportDisconnect();	 Catch:{ all -> 0x0037 }
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
        L_0x001c:
            return;
        L_0x001d:
            r0 = "isFailover";
            r2 = 0;
            r0 = r5.getBooleanExtra(r0, r2);	 Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0026;
        L_0x0026:
            r0 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r0 = r0.mConnectivityManager;	 Catch:{ all -> 0x0037 }
            r0 = r0.getActiveNetworkInfo();	 Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x003a;
        L_0x0032:
            r3.reportDisconnect();	 Catch:{ all -> 0x0037 }
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            goto L_0x001c;
        L_0x0037:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            throw r0;
        L_0x003a:
            r2 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r0 = r2.createConnection(r0);	 Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x0047;
        L_0x0042:
            r3.reportDisconnect();	 Catch:{ all -> 0x0037 }
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            goto L_0x001c;
        L_0x0047:
            r2 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r2 = r2.mStoredConnection;	 Catch:{ all -> 0x0037 }
            r2 = r0.isSame(r2);	 Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0078;
        L_0x0053:
            r2 = r0.isConnected;	 Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x0066;
        L_0x0057:
            r2 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r2 = r2.mStoredConnection;	 Catch:{ all -> 0x0037 }
            r2 = r2.isConnected;	 Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0066;
        L_0x0061:
            r3.reportDisconnect();	 Catch:{ all -> 0x0037 }
        L_0x0064:
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            goto L_0x001c;
        L_0x0066:
            r2 = r0.isConnected;	 Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0064;
        L_0x006a:
            r2 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r2 = r2.mStoredConnection;	 Catch:{ all -> 0x0037 }
            r2 = r2.isConnected;	 Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x0064;
        L_0x0074:
            r3.reportConnected(r0);	 Catch:{ all -> 0x0037 }
            goto L_0x0064;
        L_0x0078:
            r2 = com.here.odnp.net.PlatformConnectivityManager.this;	 Catch:{ all -> 0x0037 }
            r2 = r2.mStoredConnection;	 Catch:{ all -> 0x0037 }
            r2 = r0.isSameType(r2);	 Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0089;
        L_0x0084:
            r3.reportConnectionChanged(r0);	 Catch:{ all -> 0x0037 }
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            goto L_0x001c;
        L_0x0089:
            r3.reportDisconnect();	 Catch:{ all -> 0x0037 }
            r2 = r0.isConnected;	 Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0093;
        L_0x0090:
            r3.reportConnected(r0);	 Catch:{ all -> 0x0037 }
        L_0x0093:
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            goto L_0x001c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.here.odnp.net.PlatformConnectivityManager.1.onReceive(android.content.Context, android.content.Intent):void");
        }

        void reportDisconnect() {
            if (PlatformConnectivityManager.this.mStoredConnection != null) {
                if (PlatformConnectivityManager.this.mStoredConnection.isConnected) {
                    PlatformConnectivityManager.this.mStoredConnection.isConnected = false;
                    PlatformConnectivityManager.this.reportConnection(ConnectionChangeAction.CONNECTION_DISCONNECTED, PlatformConnectivityManager.this.mStoredConnection);
                }
                PlatformConnectivityManager.this.mStoredConnection = null;
            }
        }

        void reportConnected(Connection connection) {
            PlatformConnectivityManager.this.reportConnection(ConnectionChangeAction.CONNECTION_CONNECTED, connection);
        }

        void reportConnectionChanged(Connection connection) {
            PlatformConnectivityManager.this.reportConnection(ConnectionChangeAction.CONNECTION_CHANGED, connection);
        }
    };
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private IConnectivityListener mListener;
    private Connection mStoredConnection;

    public PlatformConnectivityManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
    }

    public void closeConnection(Connection connection) {
    }

    public long getBytesTransferred(Connection connection) {
        long totalTxBytes;
        if (connection.type == Type.WIFI) {
            totalTxBytes = TrafficStats.getTotalTxBytes();
            long mobileTxBytes = TrafficStats.getMobileTxBytes();
            if (!(totalTxBytes == -1 || mobileTxBytes == -1)) {
                return totalTxBytes - mobileTxBytes;
            }
        } else if (connection.type == Type.MOBILE) {
            totalTxBytes = TrafficStats.getMobileTxBytes();
            if (totalTxBytes != -1) {
                return totalTxBytes;
            }
        }
        return -1;
    }

    public Connection[] getConnections() {
        int i = 0;
        NetworkInfo[] allNetworkInfo = this.mConnectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return new Connection[0];
        }
        ArrayList arrayList = new ArrayList();
        int length = allNetworkInfo.length;
        while (i < length) {
            Connection createConnection = createConnection(allNetworkInfo[i]);
            if (createConnection != null) {
                arrayList.add(createConnection);
            }
            i++;
        }
        return (Connection[]) arrayList.toArray(new Connection[arrayList.size()]);
    }

    @TargetApi(16)
    public Connection getDataConnection() {
        Connection createConnection = createConnection(this.mConnectivityManager.getActiveNetworkInfo());
        if (VERSION.SDK_INT >= 16 && createConnection != null && this.mConnectivityManager.isActiveNetworkMetered()) {
            createConnection.meteredStatus = MeterStatus.METERED;
        }
        this.mStoredConnection = createConnection;
        return createConnection;
    }

    public Proxy getProxy(String str) {
        try {
            List<java.net.Proxy> select = ProxySelector.getDefault().select(new URI(str));
            if (select == null) {
                return null;
            }
            Proxy proxy;
            for (java.net.Proxy proxy2 : select) {
                if (proxy2.type() == java.net.Proxy.Type.HTTP) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy2.address();
                    if (inetSocketAddress != null) {
                        Proxy proxy3 = new Proxy();
                        proxy3.address = inetSocketAddress.getHostName();
                        proxy3.port = inetSocketAddress.getPort();
                        proxy = proxy3;
                        break;
                    }
                }
            }
            proxy = null;
            return proxy;
        } catch (URISyntaxException e) {
            return null;
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }

    public boolean openConnection(Connection connection) {
        return true;
    }

    public synchronized void open(IConnectivityListener iConnectivityListener) {
        close();
        if (iConnectivityListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mListener = iConnectivityListener;
        this.mContext.registerReceiver(this.mActiveConnectionChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public synchronized void close() {
        if (this.mListener != null) {
            this.mContext.unregisterReceiver(this.mActiveConnectionChangeReceiver);
            this.mListener = null;
        }
    }

    @TargetApi(16)
    private Connection createConnection(NetworkInfo networkInfo) {
        Connection connection = null;
        if (networkInfo != null) {
            connection = new Connection();
            connection.isConnected = networkInfo.isConnected();
            connection.isRoaming = networkInfo.isRoaming();
            if (networkInfo.getType() == 1) {
                connection.type = Type.WIFI;
            } else if (networkInfo.getType() == 9) {
                connection.type = Type.ETHERNET;
            } else if (networkInfo.getType() == 0) {
                connection.type = Type.MOBILE;
            } else {
                connection.type = Type.OTHER;
            }
            if (VERSION.SDK_INT >= 16 && this.mConnectivityManager.isActiveNetworkMetered()) {
                connection.meteredStatus = MeterStatus.METERED;
            }
        }
        return connection;
    }

    private void reportConnection(ConnectionChangeAction connectionChangeAction, Connection connection) {
        if (this.mListener != null) {
            this.mStoredConnection = connection;
            this.mListener.onConnectionStateChanged(connectionChangeAction, connection);
        }
    }
}
