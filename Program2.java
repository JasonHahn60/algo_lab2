/*
 * Name: Jason Hahn
 * EID: jh73942
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Program2 {

    public Region findMinEdge(ArrayList<Region> regions){
        Integer min_weight = 1001;
        Region min_weight_node = null;
        Region min_weight_node_parent = null;
        for(Region region: regions){
            ArrayList<Region> neighbors = region.getNeighbors();
            ArrayList<Integer> weights = region.getWeights();
            for(int i = 0; i < neighbors.size(); i++){
                if((weights.get(i) < min_weight) && (!regions.contains(neighbors.get(i)))){
                    //found new minimum edge between region and min_weight_node
                    min_weight = weights.get(i);
                    min_weight_node = neighbors.get(i);
                    min_weight_node_parent = region;
                }
            }
        }
        if(min_weight_node != null){
            min_weight_node.setMinDist(min_weight_node_parent.getMinDist() + min_weight);
            min_weight_node_parent.setMST_NeighborAndWeight(min_weight_node, min_weight);
            min_weight_node.setMST_NeighborAndWeight(min_weight_node_parent, min_weight);
        }

        return min_weight_node;
    }

    /**
     * findMinimumLength()
     * @param problem  - contains the regions of the graph.
     * 
     * @return The sum of all of the edges of the MST.
     * 
     * @function Should track the edges in the MST using region.mst_neighbors and region.mst_weights
     *  This function will not modify the mst_lists when run Gradescope if called in calculateDiameter()
     */
    public int findMinimumLength(Problem problem) {

        problem.getRegions().get(0).setMinDist(0);

        //set_to_explore is the set of nodes in which we look for the smallest weighted edge that does not connect to a node that has already been explored
        ArrayList<Region> set_to_explore = new ArrayList<>();
        set_to_explore.add(problem.getRegions().get(0));

        //iterate until every node in problem has been explored ie in set_to_explore
        while(set_to_explore.size() != problem.getRegions().size()){
            Region min_node = findMinEdge(set_to_explore);
            if(min_node != null){
                set_to_explore.add(min_node);
            }
        }

        //calculate sum of all edges in MST
        int sum = 0;
        ArrayList<Region> MST = problem.getRegions();
        ArrayList<Region> explored = new ArrayList<>();
        for(Region region : MST){
            for(int i = 0; i < region.getMST_Neighbors().size(); i++){
                if(!explored.contains(region.getMST_Neighbors().get(i))){
                    sum += region.getMST_Weights().get(i);
                }
            }
            explored.add(region);
        }
        //System.out.println("Length: " + sum);
        return sum;
    }

    

    /* calculateDiameter(Problem problem)
     * 
     * @param problem  - contains the regions of the problem. Each region has an adjacency list
     * defined by mst_neighbors and mst_weights, which defines the provided MST.
     * 
     */
    public int calculateDiameter(Problem problem) {

        //Call findMinimumLength in your code to get MST. In gradescope, we will provide the mst in each regions mst_neighbors nad mst_weights list
        //2-DFS approach
        int length = findMinimumLength(problem);
        ArrayList<Region> MST = problem.getRegions();  
        Region furthest_node1 = DFS_max(MST, MST.get(0));
        Region furthest_node2 = DFS_max(MST, furthest_node1);

        return furthest_node2.getIndex();
    }

    //returns the region furthest from start
    public Region DFS_max(ArrayList<Region> MST, Region start){
        Queue<Region> q = new PriorityQueue<>();
        int[] distances = new int[MST.size()];
        for(int i = 0; i < MST.size(); i++){distances[i] = -1;}
        distances[start.getName()] = 0;

        q.add(start);
        Region farthest = start;

        while(!q.isEmpty()){
            Region current = q.remove();
            if(distances[current.getName()] > distances[farthest.getName()]){
                farthest = current;
            }
            for(Region neighbor: current.getMST_Neighbors()){
                if(distances[neighbor.getName()] == -1){
                    distances[neighbor.getName()] = distances[current.getName()] + 1;
                    q.add(neighbor);
                }
            }
        }
        farthest.setIndex(distances[farthest.getName()]);//storing the max distance in the index variable so i can acess it in calculateDiameter... illegal maybe idc
        return farthest;
    }

}
