package projekt.routenplaner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        // initialising and starting the path search
        Main main = new Main ();
        Graph graph = loadVertex();
        try {
            boolean run = false;
            do {
                run = false;

                InputStreamReader inputStream = new InputStreamReader ( System.in );
                BufferedReader br = new BufferedReader ( inputStream );
                System.out.println ( "Enter your start destination: " );
                String start = br.readLine ();
                System.out.println ( "Enter your end destination: " );
                String end = br.readLine ();

                // Starts the calculation for searching the Path
                // Sometimes the Inputreader gets wrong results, because of this the getShortestPath method throws a error. The Reason for this is unknown
                // For testing purposes you may write by hand the values in
                graph.getShortestPath ( start, end );

                // Outputting the Result
                System.out.println ( "The path you need to follow to get from " + start + " to " + end + " is: " );
                System.out.println ( graph.getPathList () );

                // Asking if User wants to rerun
                System.out.println ("Do you want to calculate another Path (yes/no): ");
                String rerunString = br.readLine();
                if(rerunString.equals("yes")){
                    run = true;
                }
            } while (run);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace ();
        }
    }

    // All the destinations with the time they take up
    public static Graph loadVertex(){
        Graph graph = new Graph();
        graph.addNode ("GE", Arrays.asList(new Node ( "NE", 2), new Node ( "TH", 2), new Node ( "LG", 5)));
        graph.addNode ("NE", Arrays.asList(new Node ("CF", 1), new Node ("BI", 1), new Node ( "BE", 1), new Node ( "GE", 2)));
        graph.addNode ("CF", Arrays.asList(new Node ("NE", 1), new Node ("BI", 1)));
        graph.addNode ("BI", Arrays.asList(new Node ("CF", 1), new Node ( "BE", 1), new Node ( "BS", 2), new Node ( "ZH", 2)));
        graph.addNode ("BE", Arrays.asList(new Node ("BI", 1), new Node ( "NE", 1), new Node ( "TH", 1), new Node ( "ZH", 2)));
        graph.addNode ("LU", Arrays.asList(new Node ("ZH", 1), new Node ( "LG", 3), new Node ( "TH", 2)));
        graph.addNode ("LG", Arrays.asList(new Node ("LU", 3), new Node ( "GE", 5), new Node ( "CH", 3)));
        graph.addNode ("CH", Arrays.asList(new Node ("LG", 3), new Node ( "LU", 3), new Node ( "VD", 1), new Node ( "ZH", 2)));
        graph.addNode ("VD", Arrays.asList(new Node ("CH", 1), new Node ("ZH", 2)));
        graph.addNode ("SG", Arrays.asList(new Node ("VD", 1), new Node ("KO", 1), new Node ("WI", 1)));
        graph.addNode ("KO", Arrays.asList(new Node ("SG", 1), new Node ("SH", 1), new Node ("WI", 1)));
        graph.addNode ("SH", Arrays.asList(new Node ("KO", 1), new Node ("WI", 1), new Node ("BS", 2)));
        graph.addNode ("BS", Arrays.asList(new Node ("SH", 2), new Node ("ZH", 2), new Node ("BI", 2)));
        graph.addNode ("ZH", Arrays.asList(new Node ("BS", 2), new Node ("WI", 1), new Node ("CH", 2), new Node ("LU", 1), new Node ("BE", 2), new Node ("BI", 2)));
        graph.addNode ("TH", Arrays.asList(new Node ("BE", 1), new Node ("GE", 2), new Node ("LU", 2)));
        graph.addNode ("WI", Arrays.asList(new Node ("SH", 1), new Node ("KO", 1), new Node ("ZH", 1)));
        return graph;
    }
}
