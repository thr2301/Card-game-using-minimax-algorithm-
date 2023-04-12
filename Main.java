public class Main {
    public static void main(String[] args) {
        CubeTable startState = new CubeTable(N, K, state);
        CubeTable goalState = new CubeTable(N, K, goal);
        UniformCostSearch ucs = new UniformCostSearch(startState, goalState);
        List<Node> path = ucs.search();
    }
}