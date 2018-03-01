import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RideFinder {
    private static final int INFINITE_COST = Integer.MAX_VALUE;

    public Ride findNearestRide(List<Vehicle> vehicles, List<Ride> rides)
    {
        List<Vehicle> nonInRide = vehicles.stream().filter(x -> !x.isInRide).collect(Collectors.toList());
        Integer shortestDistance = Integer.MAX_VALUE;
        Vehicle nearestVehicle = null;
        for(Vehicle vehicle : nonInRide )
        {
            for(Ride ride : rides)
            {
                int distance = RouteUtils.getDistance(vehicle.currentPosition, ride.destination);
                if(distance < shortestDistance)
                {
                    shortestDistance = distance;
                    nearestVehicle = vehicle;
                }
            }
        }
        return null;
    }

    public void assignVehiclesToRides(List<Vehicle> vehicles, List<Ride> rides, int currentTime)
    {
        List<Vehicle> nonInRide = null;
        List<Ride> nonDoneRides = rides.stream().filter(x -> !x.hasVehicle).collect(Collectors.toList());
        for(Ride ride : nonDoneRides) {

            nonInRide = vehicles.stream().filter(x -> !x.isInRide).collect(Collectors.toList());    //if vehicle was chosen
            if(nonInRide.size() < 1)
            {
                return;
            }
            Vehicle chosenVehicle = null;
            int distance = Integer.MAX_VALUE;
            for (Vehicle vehicle : nonInRide) {
                int dis = RouteUtils.getDistance(ride.destination, vehicle.currentPosition);
                if (dis < distance && dis < ride.latestFinist - currentTime ) {
                    distance = dis;
                    chosenVehicle = vehicle;
                }
            }
            if(chosenVehicle != null)
            {
                ride.hasVehicle = true;
                chosenVehicle.isInRide = true;
                chosenVehicle.currentRide = ride;
            }

            nonDoneRides = rides.stream().filter(x -> !x.hasVehicle).collect(Collectors.toList());
        }
    }

    public List<Ride> findRoute(List<Ride> rides, List<Vehicle> vehicles) {
        while (true) {
            List<Ride> result = create(rides, vehicles);
            if (getCost(result) < INFINITE_COST) {
                return result;
            }
        }
    }

    public List<Ride> create(List<Ride> rides, final List<Vehicle> vehicles) {
        ArrayList<Ride> result = new ArrayList<>();
        List<Vehicle> vehCopy = vehicles.stream().map(Vehicle::copy).collect(Collectors.toList());
        List<Ride> toAssign = rides.stream().map(Ride::copy).collect(Collectors.toList());
        Random random = new Random();
        while (!toAssign.isEmpty()) {
            Ride route = toAssign.remove(random.nextInt(toAssign.size()));
            Vehicle vehicle = vehCopy.get(random.nextInt(vehicles.size()));
            vehicle.assignRoute(route);
            result.add(route);
        }
        return result;
    }


    public int getCost(List<Ride> rides) {
        if (!rides.stream().allMatch(Ride::isCorrect)) {
            return INFINITE_COST;
        }
        return rides.stream().mapToInt(Ride::getCost).sum();

    }


}
