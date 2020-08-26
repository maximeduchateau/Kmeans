import java.util.*;
public class Point {
    private int numDim;
    double[] location;

    public Point (int numDim, double[] location){
        this.numDim = numDim;
        this.location = location;
    }
    public double distanceTo(Point p, double [][] centroids, int j){
        double distanceToSquared = 0;
        for (int i=1;i<=numDim;i++){
            distanceToSquared+=Math.pow(p.getLocation(i)-centroids[j][i],2);

        }
        double distanceTo = Math.sqrt(distanceToSquared);
        return distanceTo;

    }

    public double getLocation(int i) {
        return this.location[i];
    }

}