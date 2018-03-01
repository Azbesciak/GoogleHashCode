import java.util.ArrayList;
import java.util.List;

class Vehicle {
        List<Ride> rides = new ArrayList<>();
        boolean isInRide;
        int ID;


        public String generateOutput()
        {
            StringBuilder output = new StringBuilder();
            output.append(ID);
            output.append(' ');
            for(int i = 0; i < rides.size();i++)
            {
                output.append(String.format("%d", rides.get(i).ID) + (i == rides.size() - 1 ? "" : " ") );
            }
            return output.toString();
        }
}
