import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Task1 {

    static final List<String> files = Arrays.asList("a_example", "b_should_be_easy", "c_no_hurry", "d_metropolis", "e_high_bonus");
    static  String getInstance(int index) {
        return "instances/" + files.get(index) + ".in";
    }

    public static void main(String[] args) throws IOException {
        final List<String> linesOfFile;
        try (Stream<String> lines = Files.lines(Paths.get(getInstance(1)))) {
            linesOfFile = lines.collect(Collectors.toList());
        }
        Task task = createTask(linesOfFile);
//        JTFun(task);
        WKFun(task);
    }


    private static void JTFun(Task task) {
        RideFinder rideFinder = new RideFinder();
        for(int t = 1; t <= task.T; t++)
        {
            rideFinder.assignVehiclesToRides(task.vehicles, task.routes, t);

            for(Vehicle vehicle : task.vehicles)
            {
                vehicle.ride(t);
            }
        }
        System.out.println(task.generateOutput());
    }

    private static void WKFun(Task task) {
        RideFinder rideFinder = new RideFinder();
        List<Vehicle> vehicles = rideFinder.findRoute(task.routes, task.vehicles);
        vehicles.stream().map(Vehicle::generateOutput).forEach(System.out::println);
    }


    private static Task createTask(List<String> linesOfFile) {
        String s = linesOfFile.get(0);
        String[] firstRow = s.split("\\s");
        int rows = parseInt(firstRow[0]);
        int columns = parseInt(firstRow[1]);
        int vehiclesAmount = parseInt(firstRow[2]);
        int rides = parseInt(firstRow[3]);
        int bonus = parseInt(firstRow[4]);
        int steps = parseInt(firstRow[5]);
        List<Ride> routes = readRoutes(linesOfFile);
        List<Vehicle> vehicles = initializeVehicles(vehiclesAmount);
        return new Task(vehicles, routes, steps);
    }

    private static List<Ride> readRoutes(List<String> input) {
        AtomicInteger id = new AtomicInteger(0);
        return input.stream().skip(1)
                .map(line -> Ride.create(line, id.incrementAndGet()))
                .collect(Collectors.toList());
    }

    private static List<Vehicle> initializeVehicles(int amount) {
        return IntStream.range(0, amount).mapToObj(Vehicle::new).collect(Collectors.toList());
    }
}
