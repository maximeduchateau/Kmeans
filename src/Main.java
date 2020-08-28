import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Point point1 = new Point(2, new double[]{1, 1});
        Point point2 = new Point(2, new double[]{1, 1});
        Point point6 = new Point(2, new double[]{25, 25});
        Point point3 = new Point(2, new double[]{50, 50});
        Point point4 = new Point(2, new double[]{51, 51});
        Point point5 = new Point(2, new double[]{52, 52});

        ArrayList<Point> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point6);
        points.add(point3);
        points.add(point4);
        points.add(point5);

        KMeans kmeans = new KMeans(2, 2, points);
        kmeans.start();
    }
}
