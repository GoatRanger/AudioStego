package com.here.odnp.net;

import com.here.posclient.INetworkManager;
import com.here.posclient.INetworkManager.Connection;
import com.here.posclient.PosClientLib.ConnectionChangeAction;

public interface IConnectivityManager extends INetworkManager {

    public interface IConnectivityListener {
        void onConnectionStateChanged(ConnectionChangeAction connectionChangeAction, Connection connection);
    }

    void close();

    void open(IConnectivityListener iConnectivityListener);
}
