import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        return new SCCUtil(adj).count();
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

class SCCUtil{
    private Graph graph;
    SCCUtil(ArrayList<Integer>[] adj){
        this.graph = new Graph(adj);
    }
    public int count(){
        int c = 0;
        Graph transposeG = graph.toTranspose();
        transposeG.depthSearch();
        while (!transposeG.postVisits.isEmpty()){
            Integer v = transposeG.postVisits.pop();
            if(graph.isVisited(v)) continue;
            graph.explore(v);
            c++;
        }
        return c;
    }





}
class Graph{
    private ArrayList<Integer>[] adj;
     final Stack<Integer> postVisits;
    private final ArrayList<Integer> visitedIndexes = new ArrayList<>();
    public Graph(ArrayList<Integer>[] adj){
        this.adj = adj;
        this.postVisits = new Stack<>();
    }
    public void depthSearch() {
        for (int i = 0; i < adj.length; i++) {
            if (isVisited(i)) continue;
            explore(i);
        }
    }

    public void explore(int index){
        visitedIndexes.add(index);
        for (int i : adj[index]) {
            if (isVisited(i)) continue;
            explore(i);
        }
        postVisit(index);
    }

    public boolean isVisited(int index){
        return visitedIndexes.contains(index);
    }

    private void postVisit(int index) {
        postVisits.push(index);
    }

    public Graph toTranspose(){
        ArrayList<Integer>[] newAdj = new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i=0;i<adj.length;i++){
            for (int j :adj[i]) {
                newAdj[j].add(i);
            }
        }
        return new Graph(newAdj);
    }
}

