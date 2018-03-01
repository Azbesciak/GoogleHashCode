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
                Integer distance = getDistance(vehicle, ride);
                if(distance < shortestDistance)
                {
                    shortestDistance = distance;
                    nearestVehicle = vehicle;
                }
            }
        }
        return
    }

    public Integer getDistance(Vehicle vehicle, Ride ride)
    {
        return Math.abs(vehicle.currentPosition.x - ride.destination.x) + Math.abs(vehicle.currentPosition.y - ride.destination.y);
    }
}
