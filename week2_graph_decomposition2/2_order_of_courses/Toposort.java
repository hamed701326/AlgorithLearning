import java.util.*;
import java.util.stream.Collectors;

public class Toposort {
    private static List<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<>();
        //write your code here
        return new Util(adj).topoSort();
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        //write your code here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        List<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

class Util {
    private final Stack<Integer> postVisits;
    private final ArrayList<Integer> visitedIndexes = new ArrayList<>();
    private final ArrayList<Integer>[] adj;

    public Util(ArrayList<Integer>[] adj) {
        this.adj = adj;
        this.postVisits = new Stack<>();
    }

    public List<Integer> topoSort() {
        depthSearch();
        List<Integer> result = new ArrayList<>();
        while (!postVisits.isEmpty()) result.add(postVisits.pop());
        return result;
    }

    private void depthSearch() {
        for (int i = 0; i < adj.length; i++) {
            if (isVisited(i)) continue;
            explore(i);
        }
    }


    private void explore(int index) {
        visitedIndexes.add(index);
        for (int i : adj[index]) {
            if (isVisited(i)) continue;
            explore(i);
        }
        postVisit(index);


    }

    private boolean isVisited(int index) {
        return visitedIndexes.contains(index);
    }


    private void postVisit(int index) {
        postVisits.push(index);
    }
}

