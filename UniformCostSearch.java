import java.util.*;

public class UniformCostSearch {

    private final CubeTable startState;
    private final CubeTable goalState;
    private final PriorityQueue<Node> frontier;

    public UniformCostSearch(CubeTable start, CubeTable goal) {
        this.startState = start;
        this.goalState = goal;
        this.frontier = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return Double.compare(n1.getCost(), n2.getCost());
            }
        });
    }

    public List<Node> search() {
        Node startNode = new Node(this.startState, null, 0.0);
        Map<CubeTable, Node> explored = new HashMap<>();
        frontier.add(startNode);
        while (!frontier.isEmpty()) {
            Node currNode = frontier.poll();
            CubeTable currState = currNode.getState();
            if (currState.equals(goalState)) {
                return getPath(currNode);
            }
            if (explored.containsKey(currState)) {
                continue;
            }
            explored.put(currState, currNode);
            for (Node neighbor : currState.getNeighbors(currNode)) {
                if (!explored.containsKey(neighbor.getState())) {
                    frontier.add(neighbor);
                }
            }
        }
        return null; // no path found
    }

    private List<Node> getPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node currNode = goalNode;
        while (currNode != null) {
            path.add(0, currNode);
            currNode = currNode.getParent();
        }
        return path;
    }
}
