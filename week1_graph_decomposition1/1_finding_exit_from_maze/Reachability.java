import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        return new DepthSearchUtil(adj).search(x, y);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
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
        System.out.println(reach(adj, x, y));
    }
}

class DepthSearchUtil {
    private ArrayList<Integer>[] adj;
    private ArrayList<Integer> visitedList = new ArrayList<>();

    DepthSearchUtil(ArrayList<Integer>[] adj) {
        this.adj = adj;
    }
    private Boolean isVisited(int item) {
        return visitedList.contains(item);
    }

    public int search(int x, int y) {
        if (adj[x].contains(y))
            return 1;
        if ( isVisited(x))
            return 0;
        visitedList.add(x);
        for (int i : adj[x]) {
            if (search(i, y) == 1)
                return 1;
        }

        return 0;
    }
}

