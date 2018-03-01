import java.util.List;

class Task {
    final List<Vehicle> vehicles;
    final List<Ride> routes;

    Task(List<Vehicle> vehicles, List<Ride> routes) {
        this.vehicles = vehicles;
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Task{" +
                "vehicles=" + vehicles +
                ", routes=" + routes +
                '}';
    }
}