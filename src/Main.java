import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Point point1 = new Point(3, new double[]{1.0, 2, 2});
        Point point2 = new Point(3, new double[]{1.0, 4, 5});
        Point point3 = new Point(3, new double[]{1.0, 3, 7});

        ArrayList<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);
        KMeans kmeans = new KMeans(3,2,points);
        kmeans.start(3,2,points);
    }
}
