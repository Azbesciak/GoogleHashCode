import java.util.List;

class Task {
    final List<Vehicle> vehicles;
    final List<Ride> routes;
    final int T;

    Task(List<Vehicle> vehicles, List<Ride> routes, int steps) {
        this.vehicles = vehicles;
        this.routes = routes;
        this.T = steps;
    }

    public Task(List<Ride> routes, List<Vehicle> vehicles) {
        this.routes = routes;
        this.vehicles = vehicles;
        T = 0;
    }

    public String generateOutput()
    {
        StringBuilder output = new StringBuilder();
        for(Vehicle vehicle : vehicles)
        {
            output.append(vehicle.generateOutput());
        }

        return output.toString();
    }

    @Override
    public String toString() {
        return "Task{" +
                "vehicles=" + vehicles +
                ", routes=" + routes +
                '}';
    }
}