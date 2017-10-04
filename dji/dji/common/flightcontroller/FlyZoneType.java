package dji.common.flightcontroller;

public enum FlyZoneType {
    Airport(0),
    SpecialZones(1),
    MilitaryZones(2),
    CommercialAirports(11),
    PrivateCommercialAirports(12),
    RecreationalAirports(13),
    NationalParks(20),
    NOAA(21),
    Parcels(22),
    PowerPlants(23),
    Prisons(24),
    Schools(25),
    Stadiums(26),
    ProhibitedSpecialUse(27),
    RestrictedSpecialUse(28),
    TemporaryFlightRestrictions(29),
    ClassBAirSpace(15),
    ClassCAirSpace(16),
    ClassDAirSpace(17),
    ClassEAirSpace(18),
    UnpavedAirport(31),
    Heliport(19),
    Unknown(255);
    
    private int data;

    private FlyZoneType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static FlyZoneType find(int i) {
        FlyZoneType flyZoneType = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return flyZoneType;
    }
}
