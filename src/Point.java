import java.util.Arrays;

public class Point {
    private int numDim;
    double[] location;

    public Point(int numDim) {
        this.numDim = numDim;
        this.location = new double[numDim];
    }

    public Point(int numDim, double[] location) {
        this.numDim = numDim;
        this.location = location;
    }

    /**
     * Function to compute euclid distance from current Point to the given point
     *
     * @param p, point to compute distance to
     * @return double, representing euclid distance to point
     */
    public double distanceTo(Point p) {
        double distanceToSquared = 0;

        for (int i = 0; i < numDim; i++) {
            distanceToSquared += Math.pow(this.getLocation(i) - p.getLocation(i), 2);
        }

        return Math.sqrt(distanceToSquared);
    }

    /**
     * Set value of point in a specific dimension
     *
     * @param dim, the dimension to set
     * @param value, value to set the dimension to
     */
    public void setDim(int dim, double value) {
        location[dim] = value;
    }

    /**
     * Get value of specific dimension
     *
     * @param i, the dimension to fetch
     * @return double, representing the value of the dimension
     */
    public double getLocation(int i) {
        return this.location[i];
    }
    public double[] getFullLocation(){ return this.location; }

    @Override
    // ToString is called automatically whenever one attempts to print the object
    public String toString() {
        return Arrays.toString(location);
    }
}