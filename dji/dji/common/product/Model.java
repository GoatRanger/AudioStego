package dji.common.product;

import dji.sdksharedlib.hardware.abstractions.c.a;

public enum Model {
    Inspire_1("Inspire 1"),
    Inspire_1_Pro("Inspire 1 Pro"),
    Inspire_1_Raw("Inspire 1 RAW"),
    Matrice_100("Matrice 100"),
    Phantom_3_Advanced("Phantom 3 Advanced"),
    Phantom_3_Professional("Phantom 3 Professional"),
    Phantom_3_Standard("Phantom 3 Standard"),
    Phantom_3_4K("Phantom 3 4K"),
    Phantom_4("Phantom 4"),
    Osmo("Osmo"),
    Osmo_Mobile("Osmo Mobile"),
    Osmo_Pro("Osmo Pro"),
    Osmo_Raw("Osmo RAW"),
    M600("Matrice 600"),
    Inspire_2("Inspire 2"),
    Phantom4_Pro("Phantom 4 Pro"),
    A3("A3"),
    N3("Dji Devices"),
    UnknownAircraft("Unknown Aircraft"),
    MavicPro(a.l),
    ZenmuseZ3(a.k),
    OsmoPlus("Osmo+");
    
    private String value;

    private Model(String str) {
        this.value = str;
    }

    public String getDisplayName() {
        return this.value;
    }
}
