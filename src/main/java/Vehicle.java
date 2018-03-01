import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Vehicle{
    final List<Ride> rides;
    boolean isInRide;
    final int ID;
    Point currentPosition;
    Ride currentRide;

    public void ride()
    {
        if(currentRide == null)
        {
            return;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "rides=" + rides +
                ", isInRide=" + isInRide +
                ", currentPosition=" + currentPosition +
                ", id=" + ID +
                '}';
    }

//    @Override
//    public Vehicle copy() {
//        return new Vehicle(new ArrayList<>(rides), ID, isInRide, new Point(currentPosition.x, currentPosition.y));
//    }
}