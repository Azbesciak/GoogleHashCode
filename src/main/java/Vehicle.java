import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Vehicle implements Copyable<Vehicle>{
    final List<Ride> rides;
    boolean isInRide;
    final int ID;
    Point currentPosition;
    int currentTime;

    public String generateOutput() {
        StringBuilder output = new StringBuilder();
        output.append(ID);
        output.append(' ');
        for (int i = 0; i < rides.size(); i++) {
            output.append(String.format("%d", rides.get(i).ID)).append(i == rides.size() - 1 ? "" : " ");
        }
        return output.toString();
    }

    public Vehicle(int id) {
        this.rides = new ArrayList<>();
        this.ID = id;
        currentPosition = new Point(0, 0);
    }

    public Vehicle(List<Ride> rides, boolean isInRide, int ID, Point currentPosition) {
        this.rides = rides;
        this.isInRide = isInRide;
        this.ID = ID;
        this.currentPosition = currentPosition;
    }

    Vehicle moveTo(Point point) {
        Vehicle copy = copy();
        copy.currentTime += RouteUtils.getDistance(currentPosition, point);
        copy.currentPosition = point;
        return copy;
    }

    Vehicle alignToRoute(Ride ride) {
        Vehicle copy = copy();
        if (ride.earliestStart > currentTime) {
            copy.currentTime = ride.earliestStart;
        }
        copy.currentTime += ride.routeLenght;
        return copy;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "rides=" + rides +
                ", isInRide=" + isInRide +
                ", currentPosition=" + currentPosition +
                ", id=" + ID +
                '}';
    }

    @Override
    public Vehicle copy() {
        return new Vehicle(new ArrayList<>(rides), isInRide, ID, new Point(currentPosition.x, currentPosition.y));
    }
}