package com.here.posclient;

import dji.pilot.usercenter.protocol.d;

public interface INetworkManager {

    public static class Connection {
        public boolean isConnected;
        public boolean isRoaming;
        public MeterStatus meteredStatus = MeterStatus.UNKNOWN;
        public Type type = Type.NONE;

        public enum MeterStatus {
            UNKNOWN,
            NOT_METERED,
            METERED
        }

        public enum Type {
            NONE,
            WIFI,
            MOBILE,
            ETHERNET,
            OTHER
        }

        public boolean isSame(Connection connection) {
            return connection != null && this.type == connection.type && this.meteredStatus == connection.meteredStatus && this.isRoaming == connection.isRoaming;
        }

        public boolean isSameType(Connection connection) {
            return connection != null && this.type == connection.type;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Connection [");
            stringBuilder.append("type=").append(this.type.name());
            stringBuilder.append(";roaming=").append(this.isRoaming);
            stringBuilder.append(";meteredStatus=").append(this.meteredStatus.name());
            stringBuilder.append(";connected=").append(this.isConnected);
            return stringBuilder.append(d.H).toString();
        }
    }

    public static class Proxy {
        public String address = null;
        public int port = 8080;
    }

    void closeConnection(Connection connection);

    long getBytesTransferred(Connection connection);

    Connection[] getConnections();

    Connection getDataConnection();

    Proxy getProxy(String str);

    boolean openConnection(Connection connection);
}
