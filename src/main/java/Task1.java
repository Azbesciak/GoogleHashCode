import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Task1 {

    static  String getInstance(String file) {
        return "instances/" + file + ".in";
    }

    public static void main(String[] args) throws IOException {
        String fileName = "a_example";
        final List<String> linesOfFile;
        try (Stream<String> lines = Files.lines(Paths.get(getInstance(fileName)))) {
            linesOfFile = lines.collect(Collectors.toList());
        }
        Task task = createTask(linesOfFile);
        RideFinder rideFinder = new RideFinder();
        for(int T = 1; T <= task.T; T++)
        {
            rideFinder.findNearestRide();
        }
        System.out.println(task);
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
