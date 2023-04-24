import java.util.*;

public class AStarSearch {

    public double heuristics(CubeTable state) {
        double cost = 0.0;
        int N = state.getN();
        int K = state.getK();
        int[][] table = new int[N][K];
        for (int i = 0; i < N; i++) {
            int[] cube = state.getState()[i];
            int x = cube[0];
            int y = cube[1];
            int above = cube[2];
            if (above == -1) {
                cost += Math.abs(x - (i + 1)) + y;
                table[x - 1][y] = i + 1;
            } else {
                cost += 1;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (table[i][j] != 0) {
                    int minDistance = Integer.MAX_VALUE;
                    for (int k = 0; k < N; k++) {
                        for (int l = 0; l < N; l++) {
                            if (table[k][l] != 0 && table[k][l] != table[i][j]) {
                                int distance = Math.abs(k - 1) + Math.abs(l - j);
                                if (distance < minDistance) {
                                    minDistance = distance;
                                }
                            }
                        }
                    }
                    cost += minDistance;
                }
            }
        }
        return cost;
    }

    public List<Node> search(CubeTable initial, CubeTable goal) {
        List<Node> open = new ArrayList<>();
        Set<CubeTable> closed = new HashSet<>();
        Node start = new Node(initial, null, 0.0);
        start.setCost(heuristics(initial));
        open.add(start);
        int extensions = 0;
        while (!open.isEmpty()) {
            Node current = null;
            double minCost = Double.MAX_VALUE;
            for (Node node : open) {
                double cost = node.getCost();
                if (minCost > cost) {
                    minCost = cost;
                    current = node;
                }
            }
            open.remove(current);
            CubeTable currentState = current.getState();
            if (currentState.equals(goal)) {
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.getParent();
                }
                Collections.reverse(path);
                double totalCost = path.get(path.size() - 1).getCost();
                System.out.println("Path : " + path);
                System.out.println("Total cost : " + totalCost);
                System.out.println("Extensions :" + extensions);
                return path;
            }
            closed.add(currentState);
            List<Node> neighbors = currentState.getNeighbors(current);
            for (Node neighbor : neighbors) {
                CubeTable neighborState = neighbor.getState();
                double neighborCost = neighbor.getCost();
                if (closed.contains(neighborState)) {// condition in order to check if a state is revisited
                    continue;
                }
                double testCost = current.getCost() + neighborCost;
                boolean neighborInOpen = false;
                for (Node openNode : open) {
                    if (openNode.getState().equals(neighborState)) {
                        neighborInOpen = true;
                        break;
                    }
                }
                if (!neighborInOpen
                        || testCost + heuristics(neighborState) < neighbor.getCost() + heuristics(neighborState)) {
                    neighbor.setParent(current);
                    neighbor.setCost(testCost);
                    if (neighborInOpen) {
                        open.remove(neighbor);
                    }
                    open.add(neighbor);
                    extensions++;
                }
            }
        }
        return null;
    }

}
