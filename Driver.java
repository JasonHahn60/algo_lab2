// This Driver file will be replaced by ours during grading
// Do not include this file in your final submission

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Driver {

    private static String filename; // input file name
    private static boolean testMST; // set to true by -c flag
    public static  Program2 program = new Program2(); // instance of your graph
    public static  boolean testDiameter;


    private static void usage() { // error message
        System.err.println("usage: java Driver [-d] [-m] <filename>");
        System.err.println("\t-m\tTest findMinimumLength implementation");
        System.err.println("\t-d\tTest diameter implementation");
        System.exit(1);
    }

    public static void main(String[] args) throws Exception {
        Problem problem = new Problem();   

        parseArgs(args);
        problem.setRegions(parseInputFile(filename));;
        testRun(problem);
    }

    public static void parseArgs(String[] args) {
        if (args.length == 0) {
            usage();
        }
        filename = "";
        testMST = false;
        for (String s : args) {
            if (s.equals("-m")) {
                testMST = true;
            } else if(s.equals("-d")){
                testDiameter = true;
            }
            else if (!s.startsWith("-")) {
                filename = s;
            } else {
                System.err.printf("Unknown option: %s\n", s);
                usage();
            }
        }

    }

    public static ArrayList<Region> parseInputFile(String filename) throws FileNotFoundException {
        ArrayList<Region> regions = new ArrayList<>();

        int numV = 0, numE = 0;
        Scanner sc = new Scanner(new File(filename));
        String[] inputSize = sc.nextLine().split(" ");
        numV = Integer.parseInt(inputSize[0]);
        numE = Integer.parseInt(inputSize[1]);
        HashMap<Integer, ArrayList<NeighborWeightTuple>> tempNeighbors = new HashMap<>();
        for (int i = 0; i < numV; ++i) {

            String[] pairs = sc.nextLine().split(" ");
            String[] pricePairs = sc.nextLine().split(" ");

            Integer currNode = Integer.parseInt(pairs[0]);
            Region currentStudent = new Region(currNode);
            regions.add(currNode, currentStudent);
            ArrayList<NeighborWeightTuple> currNeighbors = new ArrayList<>();
            tempNeighbors.put(currNode, currNeighbors);

            for (int k = 1; k < pairs.length; k++) {
                Integer neighborVal = Integer.parseInt(pairs[k]);
                Integer priceVal = Integer.parseInt(pricePairs[k]);
                currNeighbors.add(new NeighborWeightTuple(neighborVal, priceVal));
            }
        }
        for (int i = 0; i < regions.size(); ++i) {
            Region currStudent = regions.get(i);
            ArrayList<NeighborWeightTuple> neighbors = tempNeighbors.get(i);
            for (NeighborWeightTuple neighbor : neighbors) {
                currStudent.setNeighborAndWeight(regions.get(neighbor.neighborID), neighbor.weight);
            }
        }
        sc.close();
        return regions;
    }

    // feel free to alter this method however you wish, we will replace it with our own version during grading
    public static void testRun(Problem problem) throws FileNotFoundException {
        
        if(testDiameter){
            System.out.println("Diameter of MST: \n" + program.calculateDiameter(problem));
        } else if (testMST) {
            System.out.println("Minimum total MST length: \n" + program.findMinimumLength(problem));
        } else {
            usage();
        }
    }

    private static boolean assertEquals(int a, int b) {
        // print out the two values and if they are equal or not
        System.out.println(a + " == " + b + " : " + (a == b));
        return a == b;
    }

    private static class NeighborWeightTuple {
        public Integer neighborID;
        public Integer weight;

        NeighborWeightTuple(Integer neighborID, Integer weight) {
            this.neighborID = neighborID;
            this.weight = weight;
        }
    }

}
