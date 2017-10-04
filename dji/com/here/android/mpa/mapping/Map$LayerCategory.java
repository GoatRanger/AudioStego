package com.here.android.mpa.mapping;

import com.google.android.gms.location.places.Place;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum Map$LayerCategory {
    LAND(1),
    WATER(2),
    LABEL_OCEAN(3),
    LABEL_SEA(4),
    LABEL_WATER_OTHER(5),
    BEACH(6),
    WOODLAND(7),
    DESERT(8),
    GLACIER(9),
    LABEL_BEACH(10),
    LABEL_WOODLAND(11),
    LABEL_DESERT(12),
    LABEL_GLACIER(13),
    AIRPORT_AREA(14),
    AMUSEMENT_PARK(15),
    ANIMAL_PARK(16),
    BUILTUP(17),
    CEMETERY(18),
    GOLF_COURSE(19),
    HARBOR_AREA(20),
    HOSPITAL_CAMPUS(21),
    INDUSTRIAL_COMPLEX(22),
    MILITARY_BASE(23),
    NATIONAL_PARK(24),
    NATIVE_RESERVATION(25),
    OUTLINE_MILITARY_BASE(26),
    OUTLINE_NATIONAL_PARK(27),
    OUTLINE_NATIVE_RESERVATION(28),
    CITY_PARK(29),
    PEDESTRIAN_AREA(30),
    RAILYARD(31),
    SHOPPING_COMPLEX(32),
    SPORTS_COMPLEX(33),
    UNIVERSITY_CAMPUS(34),
    LABEL_AIRPORT_AREA(35),
    LABEL_AMUSEMENT_PARK(36),
    LABEL_ANIMAL_PARK(37),
    LABEL_CEMETERY(38),
    LABEL_GOLF_COURSE(39),
    LABEL_HARBOR_AREA(40),
    LABEL_HOSPITAL_CAMPUS(41),
    LABEL_INDUSTRIAL_COMPLEX(42),
    LABEL_MILITARY_BASE(43),
    LABEL_NATIONAL_PARK(44),
    LABEL_NATIVE_RESERVATION(45),
    LABEL_CITY_PARK(46),
    LABEL_PEDESTRIAN_AREA(47),
    LABEL_RAILYARD(48),
    LABEL_SHOPPING_COMPLEX(49),
    LABEL_SPORTS_COMPLEX(50),
    LABEL_UNIVERSITY_CAMPUS(51),
    STREET_CATEGORY_0(52),
    STREET_CATEGORY_1(53),
    STREET_CATEGORY_2(54),
    STREET_CATEGORY_3(55),
    STREET_CATEGORY_4(56),
    STREET_CATEGORY_PEDESTRIAN(57),
    STREET_CATEGORY_WALKWAY(58),
    LABEL_STREET_CATEGORY_0(59),
    LABEL_STREET_CATEGORY_1(60),
    LABEL_STREET_CATEGORY_2(61),
    LABEL_STREET_CATEGORY_3(62),
    LABEL_STREET_CATEGORY_4(63),
    LABEL_STREET_CATEGORY_PEDESTRIAN(64),
    LABEL_STREET_CATEGORY_WALKWAY(65),
    ROADSIGN_ICON(66),
    EXIT_SIGN(67),
    BORDER_COUNTRY(68),
    BORDER_STATE(69),
    BORDER_REGIONAL(70),
    BORDER_BUILTUP(71),
    BORDER_LINE_OF_CONTROL(102),
    NEIGHBORHOOD_AREA(72),
    LAND_PARCEL(73),
    LABEL_CONTINENT(74),
    LABEL_MAJOR_COUNTRY(75),
    LABEL_MINOR_COUNTRY(76),
    LABEL_STATE(77),
    LABEL_STATE_ABBREVIATION(78),
    LABEL_CITY_CAPITAL(79),
    LABEL_CITY_STATE_CAPITAL(80),
    LABEL_CITY_OTHER(81),
    LABEL_NEIGHBORHOOD_AREA(82),
    PUBLIC_TRANSIT_LINE(83),
    LABEL_PUBLIC_TRANSIT_LINE(84),
    ICON_PUBLIC_TRANSIT_STATION(85),
    LABEL_PUBLIC_TRANSIT_STATION(86),
    RELIEF(87),
    BACKGROUND(88),
    LABEL_MOUNTAIN(89),
    ICON_MOUNTAIN(90),
    LABEL_ISLAND(91),
    BUILDING(92),
    LABEL_BUILDING(93),
    POINT_ADDRESS(94),
    PEDESTRIAN_FEATURE(95),
    RAILROAD(96),
    FERRY(97),
    LABEL_FERRY(98),
    POI_ICON(99),
    POI_LABEL(100),
    ABSTRACT_CITY_MODEL(101);
    
    int a;

    private Map$LayerCategory(int i) {
        this.a = i;
    }

    static Map$LayerCategory a(int i) {
        switch (i) {
            case 1:
                return LAND;
            case 2:
                return WATER;
            case 3:
                return LABEL_OCEAN;
            case 4:
                return LABEL_SEA;
            case 5:
                return LABEL_WATER_OTHER;
            case 6:
                return BEACH;
            case 7:
                return WOODLAND;
            case 8:
                return DESERT;
            case 9:
                return GLACIER;
            case 10:
                return LABEL_BEACH;
            case 11:
                return LABEL_WOODLAND;
            case 12:
                return LABEL_DESERT;
            case 13:
                return LABEL_GLACIER;
            case 14:
                return AIRPORT_AREA;
            case 15:
                return AMUSEMENT_PARK;
            case 16:
                return ANIMAL_PARK;
            case 17:
                return BUILTUP;
            case 18:
                return CEMETERY;
            case 19:
                return GOLF_COURSE;
            case 20:
                return HARBOR_AREA;
            case 21:
                return HOSPITAL_CAMPUS;
            case 22:
                return INDUSTRIAL_COMPLEX;
            case 23:
                return MILITARY_BASE;
            case 24:
                return NATIONAL_PARK;
            case 25:
                return NATIVE_RESERVATION;
            case 26:
                return OUTLINE_MILITARY_BASE;
            case 27:
                return OUTLINE_NATIONAL_PARK;
            case 28:
                return OUTLINE_NATIVE_RESERVATION;
            case 29:
                return CITY_PARK;
            case 30:
                return PEDESTRIAN_AREA;
            case 31:
                return RAILYARD;
            case 32:
                return SHOPPING_COMPLEX;
            case 33:
                return SPORTS_COMPLEX;
            case 34:
                return UNIVERSITY_CAMPUS;
            case 35:
                return LABEL_AIRPORT_AREA;
            case 36:
                return LABEL_AMUSEMENT_PARK;
            case 37:
                return LABEL_ANIMAL_PARK;
            case 38:
                return LABEL_CEMETERY;
            case 39:
                return LABEL_GOLF_COURSE;
            case 40:
                return LABEL_HARBOR_AREA;
            case 41:
                return LABEL_HOSPITAL_CAMPUS;
            case 42:
                return LABEL_INDUSTRIAL_COMPLEX;
            case 43:
                return LABEL_MILITARY_BASE;
            case 44:
                return LABEL_NATIONAL_PARK;
            case 45:
                return LABEL_NATIVE_RESERVATION;
            case 46:
                return LABEL_CITY_PARK;
            case 47:
                return LABEL_PEDESTRIAN_AREA;
            case 48:
                return LABEL_RAILYARD;
            case 49:
                return LABEL_SHOPPING_COMPLEX;
            case 50:
                return LABEL_SPORTS_COMPLEX;
            case 51:
                return LABEL_UNIVERSITY_CAMPUS;
            case 52:
                return STREET_CATEGORY_0;
            case 53:
                return STREET_CATEGORY_1;
            case 54:
                return STREET_CATEGORY_2;
            case 55:
                return STREET_CATEGORY_3;
            case 56:
                return STREET_CATEGORY_4;
            case 57:
                return STREET_CATEGORY_PEDESTRIAN;
            case 58:
                return STREET_CATEGORY_WALKWAY;
            case 59:
                return LABEL_STREET_CATEGORY_0;
            case 60:
                return LABEL_STREET_CATEGORY_1;
            case 61:
                return LABEL_STREET_CATEGORY_2;
            case 62:
                return LABEL_STREET_CATEGORY_3;
            case 63:
                return LABEL_STREET_CATEGORY_4;
            case 64:
                return LABEL_STREET_CATEGORY_PEDESTRIAN;
            case 65:
                return LABEL_STREET_CATEGORY_WALKWAY;
            case Place.TYPE_MUSEUM /*66*/:
                return ROADSIGN_ICON;
            case 67:
                return EXIT_SIGN;
            case Place.TYPE_PAINTER /*68*/:
                return BORDER_COUNTRY;
            case 69:
                return BORDER_STATE;
            case Place.TYPE_PARKING /*70*/:
                return BORDER_REGIONAL;
            case 71:
                return BORDER_BUILTUP;
            case 72:
                return NEIGHBORHOOD_AREA;
            case 73:
                return LAND_PARCEL;
            case Place.TYPE_PLACE_OF_WORSHIP /*74*/:
                return LABEL_CONTINENT;
            case 75:
                return LABEL_MAJOR_COUNTRY;
            case 76:
                return LABEL_MINOR_COUNTRY;
            case 77:
                return LABEL_STATE;
            case Place.TYPE_REAL_ESTATE_AGENCY /*78*/:
                return LABEL_STATE_ABBREVIATION;
            case 79:
                return LABEL_CITY_CAPITAL;
            case 80:
                return LABEL_CITY_STATE_CAPITAL;
            case Place.TYPE_RV_PARK /*81*/:
                return LABEL_CITY_OTHER;
            case Place.TYPE_SCHOOL /*82*/:
                return LABEL_NEIGHBORHOOD_AREA;
            case Place.TYPE_SHOE_STORE /*83*/:
                return PUBLIC_TRANSIT_LINE;
            case Place.TYPE_SHOPPING_MALL /*84*/:
                return LABEL_PUBLIC_TRANSIT_LINE;
            case Place.TYPE_SPA /*85*/:
                return ICON_PUBLIC_TRANSIT_STATION;
            case Place.TYPE_STADIUM /*86*/:
                return LABEL_PUBLIC_TRANSIT_STATION;
            case Place.TYPE_STORAGE /*87*/:
                return RELIEF;
            case 88:
                return BACKGROUND;
            case 89:
                return LABEL_MOUNTAIN;
            case 90:
                return ICON_MOUNTAIN;
            case Place.TYPE_TAXI_STAND /*91*/:
                return LABEL_ISLAND;
            case Place.TYPE_TRAIN_STATION /*92*/:
                return BUILDING;
            case 93:
                return LABEL_BUILDING;
            case Place.TYPE_UNIVERSITY /*94*/:
                return POINT_ADDRESS;
            case 95:
                return PEDESTRIAN_FEATURE;
            case Place.TYPE_ZOO /*96*/:
                return RAILROAD;
            case 97:
                return FERRY;
            case 98:
                return LABEL_FERRY;
            case 99:
                return POI_ICON;
            case 100:
                return POI_LABEL;
            case 101:
                return ABSTRACT_CITY_MODEL;
            case 102:
                return BORDER_LINE_OF_CONTROL;
            default:
                throw new RuntimeException("Missing mapping check for more categories.");
        }
    }
}
