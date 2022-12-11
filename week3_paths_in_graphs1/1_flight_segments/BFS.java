import java.util.*;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        return new DistanceUtil(adj).calculateDistance(s,t);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

class DistanceUtil {
    private final ArrayList<Integer>[] adj;
    private final int[] distances;
    private final LinkedList<Integer> indexes;

    DistanceUtil(ArrayList<Integer>[] adj) {
        this.adj = adj;
        this.distances = new int[adj.length];
        this.indexes = new LinkedList<>();
    }

    public int calculateDistance(int origin, int dest) {
        if (origin == dest) return 0;
        initDistances();
        distances[origin] = 0;
        indexes.add(origin);
        while (!indexes.isEmpty()) {
            int u = indexes.poll();
            for (int v : adj[u]) {
                if (distances[v] == Integer.MAX_VALUE) {
                    indexes.add(v);
                    distances[v] = distances[u] + 1;
                }
            }
        }

        return distances[dest] != Integer.MAX_VALUE ? distances[dest] : -1;

    }

    private void initDistances() {
        for (int i = 0; i < adj.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
    }
}





