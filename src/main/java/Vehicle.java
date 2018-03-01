import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Vehicle implements Copyable<Vehicle>{
    final List<Ride> rides;
    boolean isInRide;
    final int ID;
    Point currentPosition;
    Ride currentRide;
    boolean ridingOnRoad = false;

    public void ride(int currentTime)
    {
        if(currentRide == null)
        {
            return;
        }
        //check if we reached start already or not
        if(!ridingOnRoad)
        {
            //start not yet reached
            if(positionsEquals(currentPosition, currentRide.source))
            {
                ridingOnRoad = true;
            }
        }


        int xDiff = currentPosition.x - currentRide.destination.x;
        boolean xPositiveDir = xDiff > 0;
        if(Math.abs(xDiff) > 0)
        {
            this.currentPosition.x += xPositiveDir ? 1 : -1;
            return;
        }


        int yDiff = currentPosition.y - currentRide.destination.y;
        boolean yPositiveDir = yDiff > 0;
        if(Math.abs(yDiff) > 0)
        {
            this.currentPosition.y += yPositiveDir ? 1 : -1;
        }

        //reached point
        isInRide = false;
        rides.add(currentRide);
    }
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

    void assignRoute(Ride ride) {
        if (ride.earliestStart > currentTime) {
            currentTime = ride.earliestStart;
        }
        if (ride.actualStart >= 0) {
            throw new IllegalStateException("ROUTE STARTED ALREADY");
        }
        ride.actualStart = currentTime;
        currentTime += ride.routeLenght;
        currentPosition = ride.destination;
        rides.add(ride);
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

    public static boolean positionsEquals(Point posA, Point posB)
    {
            return posA.x == posB.x && posA.y == posB.y;

    }

    @Override
    public Vehicle copy() {
        return new Vehicle(new ArrayList<>(rides), isInRide, ID, new Point(currentPosition.x, currentPosition.y));
    }
}