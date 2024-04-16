   /*
 * Name: Jon Snow
 * EID: got001
 */

// DO NOT MODIFY THIS FILE IN ANY WAY !!! 😂
// DO NOT INCLUDE THIS FILE IN YOUR SUBMISSION

import java.util.*;

public class Region implements Comparable<Region> {
    private int minDist;
    private int name;
    private ArrayList<Region> neighbors;
    private ArrayList<Integer> weights;

    private ArrayList<Region> mst_neighbors;
    private ArrayList<Integer> mst_weights;

    private int index;

    public Region(int x) {
        name = x;
        index = x;
        minDist = Integer.MAX_VALUE;
        
        neighbors = new ArrayList<Region>();
        weights = new ArrayList<Integer>();
        
        mst_neighbors = new ArrayList<Region>();
        mst_weights = new ArrayList<Integer>();
    }

    public int compareTo(Region c){
        return c.minDist > this.minDist ? -1 : 1;
    }

    public void setNeighborAndWeight(Region n, Integer w) {
        neighbors.add(n);
        weights.add(w);
    }

    public void setMST_NeighborAndWeight(Region n, Integer w) {
        mst_neighbors.add(n);
        mst_weights.add(w);
    }

    public int getIndex() { return index;}
    public void setIndex(int x) { index = x; }

    public ArrayList<Region> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public ArrayList<Region> getMST_Neighbors() {
        return mst_neighbors;
    }

    public ArrayList<Integer> getMST_Weights() {
        return mst_weights;
    }

    public int getMinDist() { return minDist; }

    public void setMinDist(int x) {
        minDist = x;
    }

    public void resetMinDist() {
        minDist = Integer.MAX_VALUE;
    }

    public int getName() {
        return name;
    }
    
    // clear neighbors array
    public void clearNeighbors(){
        neighbors = new ArrayList<Region>();
        weights = new ArrayList<Integer>();
    }

    public void clearMSTNeighbors(){
        mst_neighbors = new ArrayList<Region>();
        mst_weights = new ArrayList<Integer>();
    }

}
