import java.util.List;
import java.util.stream.Collectors;

public class RideFinder {
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

    public void assignVehiclesToRides(List<Vehicle> vehicles, List<Ride> rides)
    {
        List<Vehicle> nonInRide = vehicles.stream().filter(x -> !x.isInRide).collect(Collectors.toList());
        List<Ride> nonDoneRides = rides.stream().filter(x -> !x.hasVehicle).collect(Collectors.toList());

    }

    public List<Ride> create(List<Ride> toAssign, List<Vehicle> vehicles) {

        while (!toAssign.isEmpty()) {

        }
    }

}

public class VehicleRide
{
    Vehicle vehicle;
        for(Ride ride : rides)
        {
            int distance = Integer.MAX_VALUE;
            Vehicle chosenVehicle = null;
            for(Vehicle vehicle : nonInRide)
            {
                int dis  = RouteUtils.getDistance(ride.destination, vehicle.currentPosition);
                 if(dis < distance)
                 {
                     distance = dis;
                     chosenVehicle = vehicle;
                 }
            }
            ride.hasVehicle = true;
            chosenVehicle.isInRide = true;
        }
    }

}
