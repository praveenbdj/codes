package codes;

public class TruckTour {

    static int truckTour(int[][] petrolpumps) {
        int left = 0;
        int start = 0;
        for (int i = 0; i < petrolpumps.length; i++) {
            petrolpumps[i][0] -= petrolpumps[i][1];
            left = petrolpumps[i][0] + left;
            if (left < 0) {
                left = 0;
                start = i + 1;
            }
        }
        return start;
    }

}
