import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Task1 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Task task = createTask();
        System.out.println(task);
    }

    private static Task createTask() {
        String s = scanner.nextLine();
        String[] firstRow = s.split("\\s");
        int rows = parseInt(firstRow[0]);
        int columns = parseInt(firstRow[1]);
        int vehiclesAmount = parseInt(firstRow[2]);
        int rides = parseInt(firstRow[3]);
        int bonus = parseInt(firstRow[4]);
        int steps = parseInt(firstRow[5]);
        List<Ride> routes = readRoutes(rides);
        List<Vehicle> vehicles = initializeVehicles(vehiclesAmount);
        return new Task(vehicles, routes);
    }

    private static List<Ride> readRoutes(int amount) {
        int id = 0;
        List<Ride> routes = new ArrayList<>();
        while(amount-- > 0) {
            String line = scanner.nextLine();
            Ride route = Ride.create(line, id++);
            routes.add(route);
        }
        return routes;
    }

    private static List<Vehicle> initializeVehicles(int amount) {
        return IntStream.range(0, amount).mapToObj(Vehicle::new).collect(Collectors.toList());
    }

    public String generateOutput(List<Vehicle> vehicles)
    {
        StringBuilder output = new StringBuilder();
        for(Vehicle vehicle:vehicles)
        {
            output.append(vehicle.generateOutput());
        }

        return output.toString();
    }
}
