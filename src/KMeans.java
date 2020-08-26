import java.util.ArrayList;
import java.util.Arrays;

public class KMeans {

    private int numDim;
    private int k;
    private ArrayList<Point> points;
    private Point[] centroids;
    private ArrayList<ArrayList<Point>> pointsPerCentroid;


    public KMeans(int numDim, int k, ArrayList points) {
        this.numDim = numDim;
        this.k = k;
        this.points = points;
    }

    /**
     * Start the KMeans algorithm.
     */
    public void start() {
        centroids = initCentroids();

        while (true) {

            System.out.println("Centroids: " + Arrays.toString(centroids));

            assignPointsToCentroid();
            Point[] newCentroids = moveCentroids();

            if (stableCentroids(newCentroids)) {
                System.out.println(centroids);
                break;
            }
            centroids = newCentroids;
        }
    }

    /**
     * Generate an initial set of centroids.
     *
     * @return initial set of centroids
     */
    // TODO: Use k 'random' points as centroids (instead of k random positions - this allows faster convergence)
    private Point[] initCentroids() {
        // Centroids is list of k points
        Point[] centroids = new Point[k];

        // Generate k random points
        for (int i = 0; i < k; i++) {

            Point point = new Point(numDim);

            for (int j = 0; j < numDim; j++) {
                point.setDim(j, 100 * Math.random());
            }

            centroids[i] = point;
        }
        return centroids;
    }

    /**
     * Assign each point in points to the closest centroid
     *
     * @return
     */
    public void assignPointsToCentroid() {

        // Completely overwrite previous assignment
        this.pointsPerCentroid = new ArrayList<>();

        // Generate empty list for each centroid
        for (int i = 0; i < k; ++i) {
            pointsPerCentroid.add(new ArrayList<>());
        }

        double minDist = 0;
        int idxOfClosestCentroid = -1;

        for (Point p : points) {
            for (int j = 0; j < k; ++j) {

                // Compute distance from point p to centroid j
                double dist = p.distanceTo(centroids[j]);

                // Point is closer, then retain centroid j as closest centroid
                if (idxOfClosestCentroid == -1 || dist < minDist) {
                    minDist = dist;
                    idxOfClosestCentroid = j;
                }
            }

            pointsPerCentroid.get(idxOfClosestCentroid).add(p);
        }
    }

    public Point[] moveCentroids() {

        Point[] centroids = new Point[k];

        for(int centroidIdx = 0; centroidIdx < k; centroidIdx++) {

            // Create new point representing new position of centroid
            Point centroid = new Point(numDim);

            for(Point p: pointsPerCentroid.get(centroidIdx)) {

                // Compute new value in dimension dim for centroid
                for (int dim = 0; dim < numDim; dim++) {
                    centroid.setDim(dim, centroid.getLocation(dim) + p.getLocation(dim));
                }
            }

            // Insert centroid in list
            centroids[centroidIdx] = centroid;
        }

        // Normalize position of new centroids
        for(int centroidIdx = 0; centroidIdx < k; centroidIdx++) {

            Point centroid = centroids[centroidIdx];

            // Get the number of points assign to centroid c
            int m = pointsPerCentroid.get(centroidIdx).size();

            for (int dim = 0; dim < numDim; dim++) {
                centroid.setDim(dim, centroid.getLocation(dim) / Math.max(m, 1));
            }
        }

        return centroids;
    }

    public boolean stableCentroids(Point[] centroids) {
        for(int i = 0; i < k; ++i) {
            for(int dim = 0; dim < numDim; ++dim) {
                if(this.centroids[i].getLocation(dim) != centroids[i].getLocation(dim)) {
                    return false;
                }
            }
        }

        return true;
    }
}

