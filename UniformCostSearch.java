import java.util.*;

public class UniformCostSearch {
    private CubeTable start;
    private CubeTable goal;
    private List<Node> frontier;

    public UniformCostSearch(CubeTable start, CubeTable goal) {
        this.start = start;
        this.goal = goal;
        this.frontier = new ArrayList<>();
    }

    public CubeTable getStart() {
        return start;
    }

    public CubeTable getGoal() {
        return goal;
    }

    public List<Node> getFrontier() {
        return frontier;
    }

    public void setStart(CubeTable start) {
        this.start = start;
    }

    public void setGoal(CubeTable goal) {
        this.goal = goal;
    }

    public void setFrontier(List<Node> frontier) {
        this.frontier = frontier;
    }

    public List<Node> getPath(Node goalNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = goalNode;
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = currentNode.getParent();
        }
        return path;
    }

    public Node findMinCostNode(List<Node> nodes) {
        Node minNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.getCost() < minNode.getCost()) {
                minNode = node;
            }
        }
        return minNode;
    }

    public boolean isInFrontier(Node node) {
        for (Node child : frontier) {
            if (child.getState().equals(node.getState())) {
                return true;
            }
        }
        return false;
    }

    public void addToFrontier(Node node) {
        int i;
        for (i = 0; i < frontier.size(); i++) {
            if (node.getCost() < frontier.get(i).getCost()) {
                frontier.add(i, node);
                break;
            }
        }
        if (i == frontier.size()) {
            frontier.add(node);
        }
    }

    public List<Node> search() {
        Node startNode = new Node(this.start, null, 0.0);
        addToFrontier(startNode);
        int extensions = 0;
        while (!frontier.isEmpty()) {
            Node currentNode = findMinCostNode(frontier);
            frontier.remove(currentNode);
            CubeTable currentCube = currentNode.getState();
            if (currentCube.equals(goal)) {
                List<Node> path = getPath(currentNode);
                double totalCost = currentNode.getCost();
                System.out.println("Path : " + path);
                System.out.println("Total cost : " + totalCost);
                System.out.println("Extensions : " + extensions);
                return path;
            }
            for (Node neighbor : currentCube.getNeighbors(currentNode)) {
                double newCost = currentNode.getCost() + neighbor.getCost();
                if (!isInFrontier(neighbor) || newCost < neighbor.getCost()) {
                    neighbor.setCost(newCost);
                    neighbor.setParent(currentNode);
                    addToFrontier(neighbor);
                    extensions++;
                }
            }

        }
        return null;
    }
}
