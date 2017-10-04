package dji.sdk.api.simulator;

import dji.sdk.api.simulator.DJISimulatorTypeDef.DJISimulatorDroneType;
import dji.sdk.api.simulator.DJISimulatorTypeDef.DJISimulatorProductType;

public interface DJISimulatorGetPushTypeCallBack {
    void onResult(DJISimulatorDroneType dJISimulatorDroneType, DJISimulatorProductType dJISimulatorProductType);
}
