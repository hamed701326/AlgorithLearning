import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        return new ConnectComponentUtil(adj).countComponent();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

class ConnectComponentUtil {
    private final ArrayList<Integer>[] adj;
    private final ArrayList<Integer> visitedList = new ArrayList<>();

    ConnectComponentUtil(ArrayList<Integer>[] adj) {
        this.adj = adj;
    }

    private Boolean isVisited(int item) {
        return visitedList.contains(item);
    }
    public int countComponent(){
        int cc = 0;
        for (int i=0; i< adj.length;++i){
            if (!isVisited(i)) {
                explore(i);
                cc++;
            }
        }
        return cc;
    }
    public void explore(int x) {
        if ( isVisited(x))
            return;
        visitedList.add(x);
        for (int i : adj[x]) {
            explore(i);
        }
    }
}

