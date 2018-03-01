import java.awt.*;

public class RouteUtils {

    public static int getDistance(Point startPoint, Point destPoint)
    {
        return Math.abs(startPoint.x - destPoint.x) + Math.abs(startPoint.y - destPoint.y);
    }
}
