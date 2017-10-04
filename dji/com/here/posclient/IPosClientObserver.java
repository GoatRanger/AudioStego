package com.here.posclient;

public interface IPosClientObserver {
    void positionUpdate(PositionEstimate positionEstimate);

    void positioningError(Status status);
}
