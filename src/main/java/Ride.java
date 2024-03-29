import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Ride implements Copyable<Ride>{

    final int ID;
    final Point source;
    final Point destination;
    final int earliestStart;
    final int latestFinist;
    final int routeLenght;
    int actualStart = -1;
    boolean done = false;
    boolean hasVehicle = false;

    public Ride(int ID, Point source, Point destination, int earliestStart, int latestFinist, int routeLenght, int actualStart, boolean done, boolean hasVehicle) {
        this.ID = ID;
        this.source = source;
        this.destination = destination;
        this.earliestStart = earliestStart;
        this.latestFinist = latestFinist;
        this.routeLenght = routeLenght;
        this.actualStart = actualStart;
        this.done = done;
        this.hasVehicle = hasVehicle;
    }

    private Ride(int ID, Point source, Point destination, int earliestStart, int latestFinist) {
        this.ID = ID;
        this.source = source;
        this.destination = destination;
        this.earliestStart = earliestStart;
        this.latestFinist = latestFinist;
        routeLenght = RouteUtils.getDistance(source, destination);

    }

    public int getCost() {
        return actualStart + routeLenght;
    }

    static Ride create(String input, int id) {
        List<Integer> f = Arrays.stream(input.split("\\s")).map(Integer::valueOf).collect(Collectors.toList());
        return new Ride(id, new Point(f.get(0), f.get(1)), new Point(f.get(2), f.get(3)), f.get(4), f.get(5));
    }

    boolean hasStarted() {
        return actualStart >= 0;
    }

    boolean isCorrect() {
        return actualStart >= earliestStart && actualStart + routeLenght <= latestFinist;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "ID=" + ID +
                ", source=" + source +
                ", destination=" + destination +
                ", earliestStart=" + earliestStart +
                ", latestFinist=" + latestFinist +
                '}';
    }


    @Override
    public Ride copy() {
        return new Ride(ID, source, destination, earliestStart, latestFinist, routeLenght, actualStart, done, hasVehicle);
    }
}
