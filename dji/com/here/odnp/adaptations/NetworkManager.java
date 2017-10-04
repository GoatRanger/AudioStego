package com.here.odnp.adaptations;

import com.here.odnp.net.IConnectivityManager;
import com.here.odnp.net.IConnectivityManager.IConnectivityListener;
import com.here.posclient.INetworkManager;
import com.here.posclient.INetworkManager.Connection;
import com.here.posclient.INetworkManager.Proxy;
import com.here.posclient.PosClientLib.ConnectionChangeAction;

public class NetworkManager implements INetworkManager {
    private static final String TAG = "odnp.adaptations.NetworkManager";
    private final IConnectivityListener mConnChangeListener;
    private final IConnectivityListener mConnectivityListener = new IConnectivityListener() {
        public void onConnectionStateChanged(ConnectionChangeAction connectionChangeAction, Connection connection) {
            NetworkManager.this.mConnChangeListener.onConnectionStateChanged(connectionChangeAction, connection);
        }
    };
    private IConnectivityManager mConnectivityManager;

    public NetworkManager(IConnectivityListener iConnectivityListener) {
        if (iConnectivityListener == null) {
            throw new IllegalArgumentException("connectivityListener is null");
        }
        this.mConnChangeListener = iConnectivityListener;
    }

    public NetworkManager setConnectivityManager(IConnectivityManager iConnectivityManager) {
        stopListeningConnectivityChanges();
        if (iConnectivityManager == null) {
            throw new IllegalArgumentException("connectivityManager is null");
        }
        this.mConnectivityManager = iConnectivityManager;
        return this;
    }

    public void open() {
        startListeningConnectivityChanges();
    }

    public void close() {
        stopListeningConnectivityChanges();
    }

    public Connection[] getConnections() {
        if (this.mConnectivityManager == null) {
            return null;
        }
        return this.mConnectivityManager.getConnections();
    }

    public Connection getDataConnection() {
        if (this.mConnectivityManager == null) {
            return null;
        }
        return this.mConnectivityManager.getDataConnection();
    }

    public Proxy getProxy(String str) {
        if (this.mConnectivityManager == null) {
            return null;
        }
        return this.mConnectivityManager.getProxy(str);
    }

    public long getBytesTransferred(Connection connection) {
        if (this.mConnectivityManager == null) {
            return -1;
        }
        return this.mConnectivityManager.getBytesTransferred(connection);
    }

    public boolean openConnection(Connection connection) {
        if (this.mConnectivityManager == null) {
            return false;
        }
        return this.mConnectivityManager.openConnection(connection);
    }

    public void closeConnection(Connection connection) {
        if (this.mConnectivityManager != null) {
            this.mConnectivityManager.closeConnection(connection);
        }
    }

    private void startListeningConnectivityChanges() {
        if (this.mConnectivityManager != null) {
            this.mConnectivityManager.open(this.mConnectivityListener);
        }
    }

    private void stopListeningConnectivityChanges() {
        if (this.mConnectivityManager != null) {
            this.mConnectivityManager.close();
            this.mConnectivityManager = null;
        }
    }
}
