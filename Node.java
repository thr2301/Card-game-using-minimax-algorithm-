public class Node {
    private CubeTable state;
    private Node parent;
    private double cost;

    public Node(CubeTable state, Node parent, double cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    public CubeTable getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public double getCost() {
        return cost;
    }

    public void setState(CubeTable state) {
        this.state = state;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}