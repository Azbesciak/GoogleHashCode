import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Route {

    final int ID;
    final Point source;
    final Point destination;
    final int earliestStart;
    final int latestFinist;

    private Route(int ID, Point source, Point destination, int earliestStart, int latestFinist) {
        this.ID = ID;
        this.source = source;
        this.destination = destination;
        this.earliestStart = earliestStart;
        this.latestFinist = latestFinist;
    }

    static Route create(String input, int id) {
        List<Integer> f = Arrays.stream(input.split("\\s")).map(Integer::valueOf).collect(Collectors.toList());
        return new Route(id, new Point(f.get(0), f.get(1)), new Point(f.get(2), f.get(3)), f.get(4), f.get(5));
    }


}
