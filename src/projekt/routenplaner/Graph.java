package projekt.routenplaner;

import java.util.*;

class Graph {

    private final Map<String, List<Node>> nodes;
    private List<String> pathList;

    public Graph() {
        pathList = new ArrayList<> ();
        this.nodes = new HashMap<String, List<Node>> ();
    }

    // add nodes
    public void addNode(String string, List<Node> nodes) {
        this.nodes.put(string, nodes );
    }

    //calculates the Path
    public void getShortestPath(String start, String finish) {
        final Map<String, Integer> distances = new HashMap<String, Integer>();
        final Map<String, Node> previous = new HashMap<String, Node>();
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();

        for(String node : this.nodes.keySet()) {
            if (node == start) {
                distances.put(node, 0);
                nodes.add(new Node (node, 0));
            } else {
                distances.put(node, Integer.MAX_VALUE);
                nodes.add(new Node (node, Integer.MAX_VALUE));
            }
            previous.put(node, null);
        }

        while (!nodes.isEmpty()) {
            Node smallest = nodes.poll();
            if (smallest.getId() == finish) {
                final List<String> path = new ArrayList<String>();
                while (previous.get(smallest.getId()) != null) {
                    path.add(smallest.getId());
                    smallest = previous.get(smallest.getId());
                }
                // saves the Path and ends this method
                pathList = formatPath(path, start);
                return;
            }

            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }

            for (Node neighbor : this.nodes.get(smallest.getId())) {
                Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
                if (alt < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alt);
                    previous.put(neighbor.getId(), smallest);

                    forloop:
                    for(Node n : nodes) {
                        if (n.getId() == neighbor.getId()) {
                            nodes.remove(n);
                            n.setDistance(alt);
                            nodes.add(n);
                            break forloop;
                        }
                    }
                }
            }
        }
        pathList.add("A error occured while finding the Path");
    }

    //format the List for better view of the content
    private List<String> formatPath(List<String> list, String start){
        List<String> newSolutionList = new ArrayList<>();
        newSolutionList.add(start);

        for(int i = list.size() -1 ; i >= 0; i--){
            newSolutionList.add(list.get(i));
        }
        return newSolutionList;
    }

    public List<String> getPathList(){
        return pathList;
    }
}
