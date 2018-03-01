import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Vehicle> findRoute(List<Ride> rides, List<Vehicle> vehicles) {
        while (true) {
            List<Vehicle> result = create(rides, vehicles);
            return result.stream()
                    .map(Vehicle::copy)
                    .peek(v -> v.rides = v.rides.stream().filter(Ride::isCorrect).collect(Collectors.toList()))
                    .collect(Collectors.toList());
//            if (getCost(result) < INFINITE_COST) {
//                return result;
//            }
        }
    }

    private List<Vehicle> create(List<Ride> rides, final List<Vehicle> vehicles) {
        List<Vehicle> vehCopy = vehicles.stream().map(Vehicle::copy).collect(Collectors.toList());
        List<Ride> toAssign = rides.stream().map(Ride::copy).collect(Collectors.toList());
        while (!toAssign.isEmpty()) {
            assignVehicleToRoute(vehCopy, toAssign);
        }
        return vehCopy;
    }

    private void assignVehicleToRoute(List<Vehicle> vehCopy, List<Ride> rides) {
        int currentTry = 0;
        rides.sort(Comparator
                .comparingInt((Ride r) -> r.earliestStart)
                .thenComparingInt(r -> r.routeLenght));
        Vehicle vehicle;
        while (currentTry < rides.size()) {
            Ride ride = rides.get(currentTry);
            vehicle = getClosestVehicle(ride, vehCopy);
            if (vehicle == null) {
                currentTry++;
            } else {
                vehicle.assignRoute(ride);
                rides.remove(currentTry);
                return;
            }
        }
        throw new IllegalStateException("NOT ASSIGNED");
    }

    Vehicle getClosestVehicle(Ride ride, List<Vehicle> vehicles) {
        Point source = ride.source;
        List<Vehicle> validVeh = vehicles.stream().filter(v -> {
            Vehicle vehCopy = v.copy();
            Ride routeCopy = ride.copy();
            vehCopy.assignRoute(routeCopy);
            return routeCopy.isCorrect();
        }).collect(Collectors.toList());
        if (validVeh.isEmpty()) {
            System.err.println("no valid vehs");
            return null;
        }
        validVeh.sort(Comparator.comparing(r -> RouteUtils.getDistance(r.currentPosition, source)));
        return validVeh.get(0);
    }


    public int getCost(List<Vehicle> rides) {
        List<Ride> routes = rides.stream().flatMap(v -> v.rides.stream()).collect(Collectors.toList());
        List<Ride> invalid = routes.stream().filter(r -> !r.isCorrect()).collect(Collectors.toList());
        if (!invalid.isEmpty()) {
            return INFINITE_COST;
        }
        return routes.stream().mapToInt(Ride::getCost).sum();

    }


}
