import java.util.ArrayList;
import java.util.Arrays;
public class KMeans {


    public KMeans(int numDim, int k,ArrayList points ) {



    }
    public void start(int numDim, int k, ArrayList points){
        ArrayList<ArrayList<Point>> pointsPerCentroid = new ArrayList<>();
        double[][] centroids = new double[][];
        centroids = initCentroids(numDim, k, centroids);

                while (true) {
                    assignPointsToCentroid(numDim,k,centroids,points,pointsPerCentroid);
                    double [][] newCentroid = moveCentroid(numDim,k,centroids,pointsPerCentroid);
                            if( Arrays.deepEquals(newCentroid,centroids)){
                                System.out.println(centroids);
                                break;
                            }
                centroids = newCentroid;

                }}

    public double [][] moveCentroid(int numDim,int k,double [][] centroids, ArrayList<ArrayList<Point>> pointsPerCentroid)
    {
        for(int f = 0; f<k; f++){
            for (int g=0;g<numDim;g++){
                centroids[f][g]= 0;}}
    for (int i=0;i<k;i++){
        for (int j = 0; j < pointsPerCentroid.get(i).size(); i++) {
            for (int l=0;l<numDim;l++){
                centroids[i][l]= pointsPerCentroid.get(j).getLocation(l)/pointsPerCentroid.get(i).size();
            }
                        }
        }
    return centroids;
        }



    private double[][] initCentroids(int numDim, int k, double [][] centroids){
                    for(int i=0; i<k; i++){
                        for (int j=0;j<numDim;j++){
                            centroids[i][j]= 100* Math.random();
                        }
            }return centroids;
        }








       public ArrayList<ArrayList<Integer>>assignPointsToCentroid (int numDim, int k, double [][] centroids, ArrayList points, ArrayList<ArrayList<Point>> pointsPerCentroid){


        for(int i=0;i<k;++i) {
            pointsPerCentroid.add(new ArrayList<>());
        }double minDist = 0;
        int idxOfClosestCentroid = -1;
        for (Object p: points){
        for (int j=0; j<k;++j){
        double dist = p.distanceTo(p,centroids,j);
        if(idxOfClosestCentroid==-1|| dist<minDist){
        minDist=dist;
        idxOfClosestCentroid=j;
        }
        }

        pointsPerCentroid.get(idxOfClosestCentroid).add(p);
        }}}

