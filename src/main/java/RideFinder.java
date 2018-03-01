import java.awt.*;
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
                Integer distance = getDistance(vehicle.currentPosition, ride.destination);
                if(distance < shortestDistance)
                {
                    shortestDistance = distance;
                    nearestVehicle = vehicle;
                }
            }
        }
    }

    public void assignVehiclesToRides(List<Vehicle> vehicles, List<Ride> rides)
    {
        List<Vehicle> nonInRide = vehicles.stream().filter(x -> !x.isInRide).collect(Collectors.toList());
        List<Ride> nonDoneRides = rides.stream().filter(x -> !x.hasVehicle).collect(Collectors.toList());

    }

    public static Integer getDistance(Point startPoint, Point destPoint)
    {
        return Math.abs(startPoint.x - destPoint.x) + Math.abs(startPoint.y - destPoint.y);
    }
}

public class VehicleRide
{
    Vehicle vehicle;

}
