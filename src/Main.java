import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Create points
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(2, new double[]{1, 1}));
        points.add(new Point(2, new double[]{2, 2}));
        points.add(new Point(2, new double[]{30, 30}));
        points.add(new Point(2, new double[]{50, 50}));
        points.add(new Point(2, new double[]{51, 51}));
        points.add(new Point(2, new double[]{52, 52}));

        // Invoke KMeans clustering
        KMeans kmeans = new KMeans(2, 2, points, 5);
        kmeans.start();

        // Visualize clustering
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("KMeans Clustering");
        mainFrame.setContentPane(new ScatterPlot2D());
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
