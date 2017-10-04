public class TigerBarnes extends eecs.Gui {
    static int yardsToPin;
    static int elevationTotal;
    static int windSpeed;
    static int clubPartOne;
    static int clubPartTwo;
    static int clubPartThree;
    static double windFactor;
    static double windOne;
    static double windTwo;
    static double windThree;
    static int clubTotal;

    public static void main(String[] args) {
        input();
        findWindFactor();
        determineTheClubPartOne();
        determineTheClubPartTwo();
        determineTheClubPartThree();
        combineAnswers();
        outputClub();
    }
     // end main

    public static void input() {
        yardsToPin = getInt("How many yards are there to the pin?");
        elevationTotal = getInt("What is the elevation?");
        windSpeed = getInt("What is the wind speed?");
    }

    public static void determineTheClubPartOne() {
        if (yardsToPin <= 80) {
            clubPartOne = 3;
        } else if (yardsToPin >= 121) {
            clubPartOne = 5;
        } else {
            clubPartOne = 4;
        }
    }

    public static void findWindFactor() {
        windOne = (windSpeed / 10);
        windTwo = Math.pow(windOne, 2);
        windThree = Math.sqrt(5);
        windFactor = windTwo + windThree;
    }

    public static void determineTheClubPartTwo() {
        if (windFactor > 5) {
            clubPartTwo = 1;
        } else if (windFactor < -5) {
            clubPartTwo = -1;
        } else {
            clubPartTwo = 0;
        }
    }

    public static void determineTheClubPartThree() {
        if (elevationTotal > 15) {
            clubPartThree = 1;
        } else if (elevationTotal < -15) {
            clubPartThree = -1;
        } else {
            clubPartThree = 0;
        }
    }

    public static void combineAnswers() {
        clubTotal = clubPartOne + clubPartTwo + clubPartThree;
    }

    public static void outputClub() {
        if (clubTotal == 1) {
            printLine("FS");
        } else if (clubTotal == 2) {
            printLine("LWII");
        } else if (clubTotal == 3) {
            printLine("LW");
        } else if (clubTotal == 4) {
            printLine("SW");
        } else if (clubTotal == 5) {
            printLine("PW");
        } else if (clubTotal == 6) {
            printLine("9I");
        } else if (clubTotal == 7) {
            printLine("8I");
        }
    }
}
 // end Tiger
