import java.awt.*;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Task1 {
    public static void main(String[] args) {


    }

    private Task createTask() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] firstRow = s.split("\\s");
        int rows = parseInt(firstRow[0]);
        int columns = parseInt(firstRow[1]);
        int vehicles = parseInt(firstRow[2]);
        int rides = parseInt(firstRow[3]);
        int bonus = parseInt(firstRow[4]);
        int steps = parseInt(firstRow[5]);


    }


    class Task {
        List<Vehicle> vehicles;
        List<Route> routes;
    }

    class Vehicle {

    }

    class Route {
        Point source;
        Point destination;
        int earliestStart;
        int latestFinist;
    }
}
