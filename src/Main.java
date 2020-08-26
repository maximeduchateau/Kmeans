import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Point point1 = new Point(2, new double[]{10, 10});
        // Point point2 = new Point(2, new double[]{25, 25});
        Point point3 = new Point(2, new double[]{50, 50});

        ArrayList<Point> points = new ArrayList<>();
        points.add(point1);
        // points.add(point2);
        points.add(point3);

        KMeans kmeans = new KMeans(2,2, points);
        kmeans.start();
    }
}
