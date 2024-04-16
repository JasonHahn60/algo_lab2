import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// This class contains the main arguments for each function. Used the functions provided
// to access the necessary info for the function.


// DO NOT INCLUDE THIS FILE IN YOUR SUBMISSION

public class Problem {
    public static final boolean MST_MODIFY = false;
    // public property for one of three types of problems
    private ArrayList<Region> regions; // this is a list of all Regions, populated by Driver class

    public Problem(){
        regions = new ArrayList<Region>();
    }; // default constructor

    // returns a list of all regions
    public ArrayList<Region> getRegions() {
        return regions;
    }


    // set regions
    // Should only be called by Driver class
    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

  
    // Clears minDist value for all cities
    public void reset_minDist(){
        for(Region region : regions){
            region.setMinDist(Integer.MAX_VALUE);
        }
    }


    public void writeMST_Graph( String fileName) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            int numRegions = regions.size();
            int numEdges = 0;
            for (Region region : regions) {
                numEdges += region.getMST_Neighbors().size();
            }
            numEdges /= 2; // Since each edge is counted twice, divide by 2 to get the actual number of edges
            writer.write(numRegions + " " + numEdges + "\n");
            for (Region region : regions) {
                int regionName = region.getName();
                ArrayList<Region> neighbors = region.getMST_Neighbors();
                ArrayList<Integer> weights = region.getMST_Weights();

                writer.write(regionName + " ");
                int i =0;
                for(Region neigh : neighbors){
                    writer.write(neigh.getName() + " "  );
                }

                writer.write("\n" + regionName + " ");
                for(Region neigh : neighbors){
                    writer.write(weights.get(i++) + " "  );
                }
                writer.write("\n");

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file");
            e.printStackTrace();
        }
    }

}
