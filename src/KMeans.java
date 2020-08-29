import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class KMeans {

    // Hyperparamters
    private int numDim;
    private int k;
    private int numRuns;

    // Clustering
    private ArrayList<Point> points;
    private Point[] centroids;
    private ArrayList<ArrayList<Point>> pointsPerCentroid;

    // Optimal clustering
    private double optimalCost;
    private ArrayList<ArrayList<Point>> optimalClusters;
    private Point[] optimalCentroids;


    public KMeans(int numDim, int k, ArrayList<Point> points, int numRuns) {
        this.numDim = numDim;
        this.k = k;
        this.points = points;
        this.numRuns = numRuns;
    }

    /**
     * Start the KMeans algorithm.
     */
    public void start() {

        for (int run = 0; run < numRuns; run++) {

            System.out.println("Initiating run:" + run);
            centroids = initCentroids();
            System.out.println(Arrays.toString(centroids));

            while (true) {
                assignPointsToCentroid();
                Point[] newCentroids = moveCentroids();

                if (stableCentroids(newCentroids)) {
                    break;
                }

                this.centroids = newCentroids;
                System.out.println(Arrays.toString(centroids));
            }

            // Assess correctness of clustering
            double totalCost = computeCost();
            System.out.println("Cost (run" + run + ") :" + totalCost);
            if (run == 0 || totalCost < optimalCost) {
                optimalCost = totalCost;
                optimalClusters = pointsPerCentroid;
                optimalCentroids = centroids;
            }
        }

        System.out.println("---------");
        System.out.println("optimal cost: " + optimalCost);
        System.out.println("optimal centroids: " + Arrays.toString(optimalCentroids));
        System.out.println("optimal clusters: " + optimalClusters);
    }

    /**
     * Generate an initial set of centroids.
     *
     * @return initial set of centroids
     */
    private Point[] initCentroids() {
        // Centroids is list of k points
        Point[] centroids = new Point[k];
        Set<Integer> pointIdx = new HashSet<>();
        Random r = new Random();

        for(int i = 0; i < k; ++i) {

            while(true) {
                int next = r.nextInt(this.points.size());

                if(!pointIdx.contains(next)) {
                    pointIdx.add(next);
                    centroids[i] = this.points.get(next);
                    break;
                }
            }
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

            idxOfClosestCentroid = -1;

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

        for (int centroidIdx = 0; centroidIdx < k; centroidIdx++) {

            // Create new point representing new position of centroid
            Point centroid = new Point(numDim);

            for (Point p : pointsPerCentroid.get(centroidIdx)) {

                // Compute new value in dimension dim for centroid
                for (int dim = 0; dim < numDim; dim++) {
                    centroid.setDim(dim, centroid.getLocation(dim) + p.getLocation(dim));
                }
            }

            // Insert centroid in list
            centroids[centroidIdx] = centroid;
        }

        // Normalize position of new centroids
        for (int centroidIdx = 0; centroidIdx < k; centroidIdx++) {

            // Get the number of points assign to centroid c
            int m = pointsPerCentroid.get(centroidIdx).size();

            for (int dim = 0; dim < numDim; dim++) {
                centroids[centroidIdx].setDim(dim, centroids[centroidIdx].getLocation(dim) / Math.max(m, 1));
            }
        }

        return centroids;
    }

    public boolean stableCentroids(Point[] newCentroids) {

        for (int i = 0; i < k; ++i) {
            for (int dim = 0; dim < numDim; ++dim) {
                if (this.centroids[i].getLocation(dim) != newCentroids[i].getLocation(dim)) {
                    return false;
                }
            }
        }

        return true;
    }

    public double computeCost() {
        double dist = 0;
        for (int i = 0; i < k; i++) {
            for (Point point : pointsPerCentroid.get(i)) {
                dist += point.distanceTo(centroids[i]);
            }
        }
        return dist;
    }
}



