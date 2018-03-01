import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Task1 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

    }

    private static Task createTask() {
        String s = scanner.nextLine();
        String[] firstRow = s.split("\\s");
        int rows = parseInt(firstRow[0]);
        int columns = parseInt(firstRow[1]);
        int vehicles = parseInt(firstRow[2]);
        int rides = parseInt(firstRow[3]);
        int bonus = parseInt(firstRow[4]);
        int steps = parseInt(firstRow[5]);

    }

    private static List<Ride> readRoutes() {
        int id = 0;
        List<Ride> routes = new ArrayList<>();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            Ride route = Ride.create(line, id++);
            routes.add(route);
        }
        return routes;




    }

    public void generateOutput(List<Vehicle> vehicles)
    {
        StringBuilder output = new StringBuilder();
        for(Vehicle vehicle:vehicles)
        {
            output.append(vehicle.generateOutput());
        }

        System.out.println(output);
    }
}
